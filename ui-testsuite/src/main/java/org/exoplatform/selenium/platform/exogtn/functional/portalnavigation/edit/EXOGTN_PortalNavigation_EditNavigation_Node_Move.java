package org.exoplatform.selenium.platform.exogtn.functional.portalnavigation.edit;

import java.util.concurrent.TimeUnit;

import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.exoplatform.selenium.TestLogger.*;
import static org.exoplatform.selenium.platform.ManageAccount.*;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;

public class EXOGTN_PortalNavigation_EditNavigation_Node_Move extends PlatformBase
{
	public String EDIT_ACME_NAVIGATION = "//div[text()='acme']/following::a[text()='Edit Navigation']";
	public String MOVE_UP_LINK = "Move Up";
	public String MOVE_DOWN_LINK = "Move Down";
	public String OVERVIEW = "//a[contains(text(),'Overview')]";
	public String ACCOUNT = "//a[contains(text(),'New Account')]";
	public String PRODUCTS = "//a[contains(text(),'Products')]";
	public String QUESTIONS = "//a[contains(text(),'Questions?')]";
	public String OVERVIEW_POSITION = "//div[2]/div/a";
	public String ACCOUNT_POSITION = "//div[7]/div/a";
	public String PRODUCTS_OLD_POSITION = "//div[4]/div/a";
	public String PRODUCTS_NEW_POSITION = "//div[2]/div/div/div[3]/div/a";
	public String QUESTIONS_OLD_POSITION = "//div[5]/div/a";
	public String QUESTIONS_NEW_POSITION = "//div[6]/div/a";
	
	@BeforeMethod()
	public void beforeTest() throws Exception
	{
		initSeleniumTest();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseUrl);
		actions = new Actions(driver);
		signIn("john", "gtn");
	}
	
	//Change order of node in case Move Up or Move Down
	@Test()
	public void test01_ChangeNodeOrder()
	{
		info("-START test01_ChangeNodeOrder");
		
		//Goto Edit Navigation
		info("---Goto Edit Navigation");
		goToPortalSites();
		
		//Click on Edit Navigation of acme
		info("---Click on Edit Navigation of ACME");
		click(EDIT_ACME_NAVIGATION);
		
		info("---Verify can't Move Up order of first node");
		
		//Verify position of node OVERVIEW 
		info("-----Verify position of node OVERVIEW before Move Up");
		waitForElementPresent(OVERVIEW_POSITION);
		assert isElementPresent(OVERVIEW_POSITION):"Get position of Overview failed";
		
		info("-----Click on Move Up item of node OVERVIEW");
		//Right click on node OVERVIEW
		rightClickOnElement(OVERVIEW);
		
		//Click on Move Up item
		click(By.linkText(MOVE_UP_LINK));
		
		//Verify nothing happens ~ position of node OVERVIEW is not changed
		info("-----Verify position of node OVERVIEW is not changed after Move Up");
		waitForElementPresent(OVERVIEW_POSITION);
		assert isElementPresent(OVERVIEW_POSITION):"Get position of Overview failed";
		
		info("---Verify can't Move Down order of last node");
		
		//Verify position of node ACCOUNT 
		info("-----Verify position of node NEW ACCOUNT before Move Down");
		waitForElementPresent(ACCOUNT_POSITION);
		assert isElementPresent(ACCOUNT_POSITION):"Get position of New Account failed";
		
		info("-----Click on Move Down of node NEW ACCOUNT");
		//Right click on node
		rightClickOnElement(ACCOUNT);
		
		//Click on Move Down item
		click(By.linkText(MOVE_DOWN_LINK));
		
		//Verify nothing happens ~ position of node New ACCOUNT is not changed
		info("-----Verify position of node NEW ACCOUNT is not changed after Moving Down");
		waitForElementPresent(ACCOUNT_POSITION);
		assert isElementPresent(ACCOUNT_POSITION):"Get position of New Account failed";
		
		info("---Verify can Move Up node is not first node");
		
		//Verify position of node PRODUCTS
		info("-----Verify position of node PRODUCTS before Moving Up");
		waitForElementPresent(PRODUCTS_OLD_POSITION);
		assert isElementPresent(PRODUCTS_OLD_POSITION):"Get position of Products before Moving Up failed";
		
		info("-----Click on Move Up item of node PRODUCTS");
		//Right click on node PRODUCTS
		rightClickOnElement(PRODUCTS);
		
		//Click on Move Up item of node PRODUCTS
		click(By.linkText(MOVE_UP_LINK));
		
		//Verify position of PRODUCTS is changed
		info("-----Verify position of node PRODUCTS is changed after Moving Up");
		waitForElementPresent(PRODUCTS_NEW_POSITION);
		assert isElementPresent(PRODUCTS_NEW_POSITION):"Get position of Products after Move Up failed";
		
		info("---Verify can Move Down node which is not last node");
		
		//Verify position of node QUESTIONS?
		info("-----Verify position of node QUESTIONS? before Moving Down");
		waitForElementPresent(QUESTIONS_OLD_POSITION);
		assert isElementPresent(QUESTIONS_OLD_POSITION):"Get position of Questions before Moving Down failed";
		
		info("-----Click on Move Down item of node QUESTIONS?");
		//Right click on node QUESTIONS?
		rightClickOnElement(QUESTIONS);
		
		//Click on Move Down item of node QUESTIONS?
		click(By.linkText(MOVE_DOWN_LINK));
		
		//Verify position of QUESTIONS? is changed
		info("-----Verify position of node QUESTIONS? is changed after Moving Down");
		waitForElementPresent(QUESTIONS_NEW_POSITION);
		assert isElementPresent(QUESTIONS_NEW_POSITION):"Get position of Questions after Moving Down failed";
		
		info("-END test01_ChangeNodeOrder");
	}
	
	@AfterMethod()
	public void afterTest() throws Exception
	{
		signOut();
		driver.quit();
	}
}