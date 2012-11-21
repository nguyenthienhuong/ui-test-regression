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
import static org.exoplatform.selenium.platform.PageManagement.*;

/**
 *@author NhungVT
 *@date: 24/09/2012
 */

public class EXOGTN_GroupNavigation_Node_Delete extends PlatformBase
{
	//Define data
	public By ADMIN_EDIT_NAVIGATION_LINK = By.xpath("//td/div[text()='Administration']/ancestor::tr/td/a[text()='Edit Navigation']");
	public By SITES_MANAGEMENT_LINK = By.xpath("//a[@title='Sites Management']");
	public By NODE_ADDED = By.xpath("//a[text()='POR_GRNAVIGATION_25_06']");
	public String NODE_NAME = "POR_GRNAVIGATION_25_06";
	public String PAGE_SELECTOR_NAME = "POR_GRNAVIGATION_25_06_PAGE";
	
	@BeforeMethod
	public void beforeMethods() 
	{
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		driver.manage().window().maximize();
		signIn("john", "gtn");
	}
	
	//Delete node with deleting confirmation
	@Test()
	public void test01_DeleteNodeWithConfirmation()
	{
		Map<String, String> languages = new HashMap<String, String>();
		languages.put("English", "");
		
		//Goto Setting > Portal > Group Sites
		goToGroupSites();
		
		//Click Edit Navigation of Employees
		waitForElementPresent(ADMIN_EDIT_NAVIGATION_LINK);
		click(ADMIN_EDIT_NAVIGATION_LINK);
		
		//Add Node for test
		addNodeForGroup("Administration", "Sites Management", false, NODE_NAME, true, languages, NODE_NAME, PAGE_SELECTOR_NAME, PAGE_SELECTOR_NAME, true, true);
		
		//Verify added data
		waitForElementPresent(ADMIN_EDIT_NAVIGATION_LINK);
		click(ADMIN_EDIT_NAVIGATION_LINK);
		waitForElementPresent(SITES_MANAGEMENT_LINK);
		click(SITES_MANAGEMENT_LINK);
		waitForElementPresent(NODE_ADDED);
		
		//Delete data
		deleteNode("Administration", "Sites Management", NODE_NAME, false);
		
		//Verify page selector still exists
		goToManagePages();
		searchPageByTitle(PageType.GROUP, PAGE_SELECTOR_NAME);
		
		//Delete data
		deletePage(PageType.GROUP, PAGE_SELECTOR_NAME);
	}
	
	@AfterMethod()
	public void afterTest() throws Exception
	{
		signOut();
		driver.quit();
	}
}