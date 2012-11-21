package org.exoplatform.selenium.platform.exogtn.functional.groupnavigation;

import java.util.HashMap;
import java.util.Map;

import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.platform.ManageAccount.*;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;
import static org.exoplatform.selenium.platform.NavigationManagement.*;
import static org.exoplatform.selenium.platform.GroupNavigation.*;

/**
 * @author NhungVT
 * @date: 24/09/2012	
 */

public class EXOGTN_GroupNavigation_Node_CutPaste extends PlatformBase
{
	//Define data
	public By ADMIN_EDIT_NAVIGATION_LINK = By.xpath("//td/div[text()='Administration']/ancestor::tr/td/a[text()='Edit Navigation']");
	public By DEVELOP_EDIT_NAVIGATION_LINK = By.xpath("//td/div[text()='Development']/ancestor::tr/td/a[text()='Edit Navigation']");
	public By PORTAL_ADMINISTRATION_LINK = By.xpath("//a[@title='Portal Administration']");
	public By GROUP_NAVIGATION_LINK = By.xpath("//a[@title='Group Navigation']");
	public By MANAGEMENT_LINK = By.xpath("//a[@title='Management']");
	public By IDE_LINK = By.xpath("//a[@title='IDE']");
	public By CHILD_NODE = By.xpath("(//a[contains(text(),'POR_GRNAVIGATION_25_05_002')])[2]");
	public By OK_BUTTON = By.xpath("//a[text()='OK']");
	public By CLOSE_NAVIGATION_ICON = By.xpath("//a[contains(@title,'Close Window')]");
	public String NODE_NAME = "POR_GRNAVIGATION_25_05_002";

	//Product Messages
	public String SAME_PLACE_MESSAGE = "The source and the destination must be different.";

	@BeforeMethod()
	public void beforeTest() throws Exception
	{
		initSeleniumTest();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseUrl);
		actions = new Actions(driver);
		driver.manage().window().maximize();
		signIn("john", "gtn");
	}
	
	//Cut/Paste node to same place
	@Test()
	public void test01_CutPasteNodeInSamePlace()
	{
		//Goto Setting > Portal > Group Sites
		goToGroupSites();
		
		//Click Edit navigation of Administration 
		waitForElementPresent(ADMIN_EDIT_NAVIGATION_LINK);
		click(ADMIN_EDIT_NAVIGATION_LINK);
		
		//Copy Node Portal Administration 
		cutNode(PORTAL_ADMINISTRATION_LINK);
		
		//Paste to Portal Administration
		pasteNode(PORTAL_ADMINISTRATION_LINK);
		
		//Verify message confirmation
		waitForMessage(SAME_PLACE_MESSAGE);
		click(OK_BUTTON);
	}
	
	//Cut/Paste a node to new place in the same navigation
	@Test()
	public void test02_CutPasteNodeInSameNavigation()
	{
		Map<String, String> languages = new HashMap<String, String>();
		languages.put("English", "");
		
		//Goto Setting > Portal > Group Sites
		goToGroupSites();
		
		//Click Edit navigation of Administration 
		waitForElementPresent(ADMIN_EDIT_NAVIGATION_LINK);
		click(ADMIN_EDIT_NAVIGATION_LINK);
		
		//Add child node for Group Navigation
		addNodeForGroup("Administration", "Group Navigation", false, NODE_NAME, true, languages, NODE_NAME, null, null, true, true);
		
		//Click Edit navigation of Administration
		waitForElementPresent(ADMIN_EDIT_NAVIGATION_LINK);
		click(ADMIN_EDIT_NAVIGATION_LINK);
		
		//Copy added child node
		click(GROUP_NAVIGATION_LINK);
		waitForElementPresent(By.xpath("//a[@title='"+NODE_NAME+"']"));
		cutNode(By.xpath("//a[@title='"+NODE_NAME+"']"));
		
		//Paste to Node Sites Management 
		click(MANAGEMENT_LINK);
		pasteNode(MANAGEMENT_LINK);
		
		//Save
		save();
		pause(1000);
		
		//Click Edit navigation of Administration
		waitForElementPresent(ADMIN_EDIT_NAVIGATION_LINK);
		click(ADMIN_EDIT_NAVIGATION_LINK);
		waitForElementPresent(MANAGEMENT_LINK);
		click(MANAGEMENT_LINK);
		waitForElementPresent(CHILD_NODE);
		
		//Delete test data
		deleteNode("Administration", "Management", NODE_NAME, false);
	}
	
	//Cut/Paste a node to new place in different navigation
	@Test()
	public void test03_CutPasteNodeInDiffNavigation()
	{
		//Goto Setting > Portal > Group Sites
		goToGroupSites();
		
		//Click Edit navigation of Administration 
		waitForElementPresent(ADMIN_EDIT_NAVIGATION_LINK);
		click(ADMIN_EDIT_NAVIGATION_LINK);
		
		//Copy Node Portal Administration 
		cutNode(PORTAL_ADMINISTRATION_LINK);
		waitForElementPresent(CLOSE_NAVIGATION_ICON);
		click(CLOSE_NAVIGATION_ICON);
		
		//Click Edit navigation of Development
		waitForElementPresent(DEVELOP_EDIT_NAVIGATION_LINK);
		click(DEVELOP_EDIT_NAVIGATION_LINK);
		waitForElementPresent(IDE_LINK);
		pasteNode(IDE_LINK);
		click(CLOSE_NAVIGATION_ICON);
	}
	
	@AfterMethod()
	public void afterTest() throws Exception
	{
		signOut();
		driver.quit();
	}
}