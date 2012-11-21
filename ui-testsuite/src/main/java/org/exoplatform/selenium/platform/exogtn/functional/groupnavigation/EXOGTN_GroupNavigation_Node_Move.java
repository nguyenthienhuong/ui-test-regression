package org.exoplatform.selenium.platform.exogtn.functional.groupnavigation;

/**
 *@author HangNTT
 * @date: 24/09/2012
 */

import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.exoplatform.selenium.TestLogger.*;
import static org.exoplatform.selenium.platform.ManageAccount.*;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;

public class EXOGTN_GroupNavigation_Node_Move extends PlatformBase
{
	By ELEMENT_EDIT_NAV_GROUP = By.xpath("//td/div[text()='Administration']/ancestor::tr/td/a[text()='Edit Navigation']");
	String MOVE_UP_LINK = "Move Up";
	String MOVE_DOWN_LINK = "Move Down";
	By PORTAL_ADMINISTRATION = By.xpath("//a[contains(text(),'Portal Administration')]");
	By MANAGEMENT = By.xpath("//a[contains(text(),'Management')]");
	By MONITORING = By.linkText("Monitoring");
	By GROUP_NAVIGATION = By.xpath("//a[contains(text(),'Group Navigation')]");
	By SITES_MANAGEMENT = By.xpath("//a[contains(text(),'Sites Management')]");
	By PORTAL_ADMINISTRATION_POSITION = By.xpath("//div[2]/div/a");
	By MANAGEMENT_POSITION = By.xpath("//div[2]/div/div/div[3]/div/a");
	By SITES_MANAGEMENT_POSITION =By.xpath("//div[6]/div/a");
	By MONITORING_OLD_POSITION = By.xpath("//div[2]/div/div/div[4]/div/a");
	By MONITORING_NEW_POSITION = By.xpath("//div[3]/div/a");
	By GROUP_NAVIGATION_OLD_POSITION = By.xpath("//div[5]/div/a");
	By GROUP_NAVIGATION_NEW_POSITION = By.xpath("//div[6]/div/a");
	
	@BeforeMethod()
	public void beforeTest() throws Exception
	{
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		signIn("john", "gtn");
		driver.manage().window().maximize();
	}
	
	//Change order of node in case Move Up or Move Down
	@Test()
	public void test01_ChangeNodeOrder()
	{
		info("-START test01_ChangeNodeOrder");
		
		//Goto Edit Navigation
		info("---Goto Edit Navigation of Group");
		goToGroupSites();
		
		//Click on Edit Navigation of group
		info("---Click on Edit Navigation of Group");
		click(ELEMENT_EDIT_NAV_GROUP);
		
		info("---Verify can't Move Up order of first node");
		
		//Verify position of node PORTAL_ADMINISTRATION 
		info("-----Verify position of node PORTAL_ADMINISTRATION before Move Up");
		waitForElementPresent(PORTAL_ADMINISTRATION_POSITION);
		info("Get position of Portal Administration failed");
		
		info("-----Click on Move Up item of node Management");
		//Right click on node PORTAL_ADMINISTRATION
		rightClickOnElement(PORTAL_ADMINISTRATION);
		
		//Click on Move Up item
		click(By.linkText(MOVE_UP_LINK));
		
		//Verify nothing happens ~ position of node PORTAL_ADMINISTRATION is not changed
		info("-----Verify position of node PORTAL_ADMINISTRATION is not changed after Moving Up");
		waitForElementPresent(PORTAL_ADMINISTRATION_POSITION);
				
		info("---Verify can't Move Down order of last node");
		
		//Verify position of node SITES_MANAGEMENT 
		info("-----Verify position of node SITES_MANAGEMENT before Move Down");
		waitForElementPresent(SITES_MANAGEMENT_POSITION);
				
		info("-----Click on Move Down of node SITES_MANAGEMENT");
		//Right click on node
		rightClickOnElement(SITES_MANAGEMENT);
		
		//Click on Move Down item
		click(By.linkText(MOVE_DOWN_LINK));
		
		//Verify nothing happens ~ position of node SITES_MANAGEMENT is not changed
		info("-----Verify position of node SITES_MANAGEMENT is not changed after Moveing Down");
		waitForElementPresent(SITES_MANAGEMENT_POSITION);
			
		info("---Verify Moving Up node is not first node");
		
		//Verify position of node MONITORING
		info("-----Verify position of node MONITORING before Moving Up");
		waitForElementPresent(MONITORING_OLD_POSITION);
				
		info("-----Click on Move Up item of node MONITORING");
		//Right click on node MONITORING
		rightClickOnElement(MONITORING);
		
		//Click on Move Up item of node MONITORING
		click(By.linkText(MOVE_UP_LINK));
		
		//Verify position of MONITORING is changed
		info("-----Verify position of node MONITORING is changed after Move Up");
		waitForElementPresent(MONITORING_NEW_POSITION);
				
		info("---Verify user can move down node which is not last node");
		
		//Verify position of node GROUP_NAVIGATION
		info("-----Verify position of node QUESTIONS? before Moving Down");
		waitForElementPresent(GROUP_NAVIGATION_OLD_POSITION);
				
		info("-----Click on Move Down item of node GROUP_NAVIGATION");
		//Right click on node GROUP_NAVIGATION
		rightClickOnElement(GROUP_NAVIGATION);
		
		//Click on Move Down item of node GROUP_NAVIGATION
		click(By.linkText(MOVE_DOWN_LINK));
		
		//Verify position of GROUP_NAVIGATION is changed
		info("-----Verify position of node GROUP_NAVIGATION is changed after Moving Down");
		waitForElementPresent(GROUP_NAVIGATION_NEW_POSITION);
		info("-END test01_ChangeNodeOrder");
	}
	
	@AfterMethod()
	public void afterTest() throws Exception
	{
		signOut();
		driver.quit();
	}
}