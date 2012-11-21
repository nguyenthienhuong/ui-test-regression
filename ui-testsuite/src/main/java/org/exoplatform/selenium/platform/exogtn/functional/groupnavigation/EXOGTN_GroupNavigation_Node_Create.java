package org.exoplatform.selenium.platform.exogtn.functional.groupnavigation;

import static org.exoplatform.selenium.platform.ManageAccount.*;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;
import static org.exoplatform.selenium.platform.PageManagement.*;
import static org.exoplatform.selenium.platform.NavigationManagement.*;
import static org.exoplatform.selenium.platform.GroupNavigation.*;
import static org.exoplatform.selenium.TestLogger.info;
import java.util.HashMap;
import java.util.Map;
import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *@author HangNTT
 * @date: 20/09/2012
 */
public class EXOGTN_GroupNavigation_Node_Create extends PlatformBase{

	By ELEMENT_EDIT_NAV_GROUP = By.xpath("//td/div[text()='Administration']/ancestor::tr/td/a[text()='Edit Navigation']");
	By UP_LEVEL = By.xpath("//a[@title='Up Level']");

	By ADD_NODE_BUTTON = By.xpath("//a[text()='Add Node']");
	By NODE_NAME = By.xpath("//input[@id='name']");
	By NODE_LABEL = By.xpath("//input[@id='i18nizedLabel']");
	By PAGE_SELECTOR = By.xpath("//div[text()='Page Selector']");
	By PAGE_NAME = By.id("pageName");
	By CREATE_PAGE_BUTTON = By.linkText("Create Page");
	
	// define add new node
	public void addNode(String nodeNameInput, String nodeLabelInput, String pageNameInput)
	{
		//Click add node button
		click(ADD_NODE_BUTTON);
		pause(1000);
		//Input node name
		waitForElementPresent(NODE_NAME);
		type(NODE_NAME, nodeNameInput, true);
		pause(500);
		//Input node label
		waitForElementPresent(NODE_LABEL);
		type(NODE_LABEL, nodeLabelInput, true);
		//Click Page selector tab
		waitForElementPresent(PAGE_SELECTOR);
		click(PAGE_SELECTOR);
		//Input page name
		waitForElementPresent(PAGE_NAME);
		type(PAGE_NAME,pageNameInput,true);
		//Click create page
		waitForElementPresent(CREATE_PAGE_BUTTON);
		click(CREATE_PAGE_BUTTON);
		pause(1000);
	}

	@BeforeMethod
	public void beforeMethods() {
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		driver.manage().window().maximize();
	}
	
	//Create node in the first level
	@Test
	public void test01_CreateNodeInTheFirstLevel() {	
		signIn("john", "gtn");
		goToGroupSites();
		click(ELEMENT_EDIT_NAV_GROUP);
		click(UP_LEVEL);
		//Add node
		info("Add node at the first level");
		addNode("GROUPNAV_25_02_0001", "GROUPNAV_25_02_0001", "GROUPNAV_25_02_0001");
		//Click Save on add node form
		info("Click Save on add node form");
		save();
		//Click Save on Navigation form
		info("Click save on navigation form");
		save();
		waitForTextNotPresent("GROUPNAV_25_02_0001");
		
		// Delete new node
		info("Delete node");
		deleteNode("Administration", "GROUPNAV_25_02_0001", "GROUPNAV_25_02_0001", true);
		waitForTextPresent("Administration");
		info("Delete page");
		goToManagePages();
		info("Delete page");
		deletePage(PageType.GROUP,"GROUPNAV_25_02_0001");
		info("Verify page is deleted");
		waitForTextNotPresent("GROUPNAV_25_02_0001");
	}
	
	@Test
	public void test02_CreateNodeAsChildOfExistingNode () {	

		//String CURRTENT_NODE = ELEMENT_NODE_LINK.replace("${nodeLabel}", "Portal Administration");
		String PAGE_NAME = null;
		String PAGE_TITLE = null;

		Map<String, String> languages = new HashMap<String, String>();
		languages.put("English", "");

		signIn("john", "gtn");
		goToGroupSites();
		click(ELEMENT_EDIT_NAV_GROUP);
		// add node is child of existing node
		addNodeForGroup("Administration", "Portal Administration", false, "GROUPNAV_25_02_002", true, languages, "GROUPNAV_25_02_002", PAGE_NAME, PAGE_TITLE, true, true);
		// delete node
		info("Delete node");
		deleteNode("Administration", "GROUPNAV_25_02_002", "GROUPNAV_25_02_002", true);
		waitForTextPresent("Administration");
	}

	@AfterMethod()
	public void afterTest() throws Exception
	{
		signOut();
		driver.quit();
	}
}