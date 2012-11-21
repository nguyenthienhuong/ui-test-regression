package org.exoplatform.selenium.platform.exogtn.functional.portalnavigation.edit;

import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.TestLogger.*;
import static org.exoplatform.selenium.platform.ManageAccount.*;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;
import static org.exoplatform.selenium.platform.UserGroupManagement.*;
import static org.exoplatform.selenium.platform.PageManagement.*;
import static org.exoplatform.selenium.platform.NavigationManagement.*;


public class EXOGTN_PortalNavigation_EditNavigation_Node_Create extends PlatformBase {
	//Define data
	public static final By ADD_NODE_BUTTON = By.xpath("//a[text()='Add Node']");
	public static final By NODE_NAME = By.xpath("//input[@id='name']");
	public static final By UP_LEVEL = By.xpath("//a[@title='Up Level']");
	public static By NODE_LABEL = By.xpath("//input[@id='i18nizedLabel']");
	public static By PAGE_SELECTOR = By.xpath("//div[text()='Page Selector']");
	public static By PAGE_NAME = By.id("pageName");
	public static By CREATE_PAGE_BUTTON = By.linkText("Create Page");
	public static By OK_BUTTON = By.linkText("OK") ;
	public static By CLOSE_BUTTON = By.xpath("//a[@title='Close Window']");
	public static By PAGE_LINK = By.linkText("PORNAV_14_01_043");
	public static By ELEMENT_EDIT_CONTENT_LINK = By.linkText("Content");
	public static By ELEMENT_EDIT_SITE_LAYOUT = By.linkText("Layout");
	public static By ELEMENT_ADD_SITE_LINK = By.linkText("Add Site");
	public final static String ELEMENT_VIEW_PROPERTY="//a[@class='PageProfileIcon']";
	public final static String ELEMENT_PERMISSION_SETTING_TAB= "//div[contains(text(),'Permission Settings')]";
	public final static String ELEMENT_EDIT_PERMISSION_LINK="//a[text()='Edit Permission Settings']";
	public final static String ELEMENT_EDIT_PAGE_SAVE="//a[text()='Save']";
	public final static String ELEMENT_EDIT_PAGE_FINISH="//a[@title='Finish']";
	public final static String ELEMENT_GROUP_DEVELOPMENT="Development";
	public final static String ELEMENT_GROUP_PLATFORM="Platform";
	public final static String ELEMENT_GROUP_ADMIN="Administration";
	public final static By ELEMENT_MY_SITE_MENU = By.linkText("My Sites");
	public final static By ELEMENT_INTRANET_MENU = By.linkText("intranet");
	public final static By ELEMENT_HOME_MENU = By.linkText("Home");
	public final static By ELEMENT_EDIT_MENU = By.linkText("Edit");
	public final static By ELEMENT_PAGE_MENU = By.linkText("Page");
	public final static By ELEMENT_LAYOUT_MENU = By.linkText("Layout");
	public final static By ELEMENT_ADD_PAGE_MENU = By.linkText("Add Page");
	public final static By ELEMENT_SITE_MENU = By.linkText("Site");
	
	public static WebElement element = null;

	/*------------------Common function---------------------*/

	//Add node 
	public void addNode (String nodeNameInput, String nodeLabelInput, String pageNameInput)
	{
		WebElement element;
		//Click add node button
		click(ADD_NODE_BUTTON);
		pause(1000);
		//Input node name
		element = waitForAndGetElement(NODE_NAME);
		element.sendKeys(nodeNameInput);
		pause(500);
		//Input node label
		element = waitForAndGetElement(NODE_LABEL);
		element.sendKeys(nodeLabelInput);
		//Click Page selector tab
		element = waitForAndGetElement(PAGE_SELECTOR);
		element.click();
		//Input page name
		element = waitForAndGetElement(PAGE_NAME);
		element.sendKeys(pageNameInput);
		//Click create page
		element = waitForAndGetElement(CREATE_PAGE_BUTTON);
		element.click();
		pause(1000);
	}
	//Up level
	public void upLevel ()
	{
		element = waitForAndGetElement(UP_LEVEL);
		element.click();
		pause(1000);
	}
	//Add node by searching page
	public void addNodeForPortal(String currentNavigation, String currentNodeLabel, boolean useAddNodeLink, String nodeName, boolean extendedLabelMode, 
			Map<String, String> languages, String nodeLabel, String pageName, String pageTitle, boolean verifyPage, boolean verifyNode, boolean select){
		String ELEMENT_SELECT_PAGE_SEARCH="//a[@title='Quick Search']";
		String ELEMENT_SELECT_PAGE_BUTTON= "//img[@title='Select Page']";
		String ELEMENT_SEARCH_SELECT_PAGE_BUTTON= "//a[text()='Search and Select Page']";
		
		//String node = ELEMENT_NODE_LINK.replace("${nodeLabel}", nodeLabel);
		String currentNode = ELEMENT_NODE_LINK.replace("${nodeLabel}", currentNodeLabel);
		editNavigation(currentNavigation);

		info("--Adding new node at navigation--");		
		if (useAddNodeLink){
			click(currentNode);
			click(ELEMENT_ADD_NODE_LINK);
		}else{

			click(currentNode);
			pause(500);
			rightClickOnElement(currentNode);
			if (currentNode.equals(ELEMENT_NAVIGATION_HOME_NODE)) {
				click(ELEMENT_NODE_ADD_NEW_TOP_NODE);
			} else {
				click(ELEMENT_NODE_ADD_NEW);
			}		

		}
		waitForTextPresent("Page Node Settings");
		type(ELEMENT_INPUT_NAME, nodeName, true);

		if (extendedLabelMode) {
			for (String language : languages.keySet()) {
				select(ELEMENT_SELECT_LANGUAGE, language);
				pause(500);

			}
		} else {
			uncheck(ELEMENT_CHECKBOX_EXTENDED_LABEL_MODE);
			type(ELEMENT_INPUT_LABEL, nodeLabel, true);
		}

		click(ELEMENT_PAGE_SELECTOR_TAB);

		if (!select) {
			info("--Create new page");
			type(ELEMENT_INPUT_PAGE_NAME, pageName, true);
			type(ELEMENT_INPUT_PAGE_TITLE, pageTitle, true);
			click(ELEMENT_CREATE_PAGE_LINK);
			if (verifyPage) {
				waitForElementNotPresent(ELEMENT_CREATE_PAGE_LINK);
			} else {
				return;
			}
		} else {
			//info("-- Select Page --");
			
			click(ELEMENT_SEARCH_SELECT_PAGE_BUTTON);
			pause(1000);
						
			WebElement element = waitForAndGetElement(By.xpath("//div[@class='QuickSet']/input[@id='pageTitle']"));
			element.sendKeys(pageTitle);
			
			click(ELEMENT_SELECT_PAGE_SEARCH);
			
			click(ELEMENT_SELECT_PAGE_BUTTON);
			
		}

		info("-- Save add node for portal --");
		pause(1000);
		save();
		if (verifyNode) {
			waitForTextNotPresent("Page Node Settings");
			waitForTextPresent(nodeName);
			save();
			waitForTextNotPresent("Navigation Management");
		}

	}
	
	@BeforeMethod
	public void beforeMethods() throws Exception {
		initSeleniumTest();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseUrl);
		actions = new Actions(driver);
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void afterTest() throws Exception {
		signOut();
		driver.quit();
	}
	
	//FNC_GTN_POR_PORNAVIGATION_14_01_006: Create node at first level by using add node button
	@Test 
	public void test06_CreateNodeAtFirstLevel()
	{

		info("Sign as John");
		signIn("john", "gtn");

		//Go to portal site
		info("Go to portal site");
		goToPortalSites();

		// Edit intranet navigation
		info("Open edit intranet navigation form");
		editNavigation("intranet");

		//Up Level
		info("Click up level");
		upLevel();

		//Add node
		info("Add node at the first level");
		addNode("PORNAV_14_01_006", "PORNAV_14_01_006", "page_PORNAV_14_01_006");

		//Click Save on add node form
		info("Click Save on add node form");
		save();
		pause(1000);

		//Click Save on Navigation form
		info("Click save on navigation form");
		save();
		pause(2000);

		//Verify new node
		info("verify new node");
		editNavigation("intranet");
		assert isElementPresent(By.linkText("PORNAV_14_01_006")):"New node is created";
		pause(1000);

		/*-----------------------Delete data----------------*/

		info("Delete node");
		deleteNode("intranet", "PORNAV_14_01_006", "PORNAV_14_01_006", true);

		info("Verify node is deleted");
		editNavigation("intranet");
		assert isElementNotPresent("//a[@title='PORNAV_14_01_006']"): "Node is not present";

		info("Go to manage page");
		goToManagePages();

		info("Delete page");
		deletePage(PageType.PORTAL,"page_PORNAV_14_01_006");

		info("Verify page is deleted");
		assert isElementNotPresent("//div[@title='page_PORNAV_14_01_006']"):"Page is not present";

	}
	
	//FNC_GTN_POR_PORNAVIGATION_14_01_007: Create node at first level by right click
	@Test 
	
	public void test07_AddNodeAtFirstLevelByRightClick()
	{
		info("Sign as John");
		signIn("john", "gtn");

		info("Go to portal site");
		goToPortalSites();

		info("Open edit intranet navigation form");
		editNavigation("intranet");

		//Up Level
		info("Click up level");
		upLevel();

		//Right click
		info("Right click on blank area");
		rightClickOnElement(ELEMENT_NAVIGATION_HOME_NODE);

		//Add node
		info("Add new node");
		addNode("PORNAV_14_01_007", "PORNAV_14_01_007", "page_PORNAV_14_01_007");

		//Click Save on add node form
		info("Click Save on add node form");
		save();
		pause(1000);

		//Click Save on Navigation form
		info("Click save on navigation form");
		save();
		pause(2000);

		//Verify new node
		info("verify new node");
		editNavigation("intranet");
		assert isElementPresent(By.linkText("PORNAV_14_01_007")): "New node is created";
		pause(1000);

		/*----------------------Delete data----------------*/
		//Delete node
		info("Delete node");
		deleteNode("intranet", "PORNAV_14_01_007", "PORNAV_14_01_007", true);

		//Verify node is not present
		info("Verify node is deleted");
		editNavigation("intranet");
		assert isElementNotPresent("//a[@title='PORNAV_14_01_007']"):"Node is not present";

		//Go to manage page
		info("Go to manage page");
		goToManagePages();

		//Delete page
		info("Delete page");
		deletePage(PageType.PORTAL,"page_PORNAV_14_01_007");

		//Verify Page node present
		info("Verify page is deleted");
		assert isElementNotPresent("//div[@title='page_PORNAV_14_01_007']"):"Page is not present";


	}
	//FNC_GTN_POR_PORNAVIGATION_14_01_008: Add child node by right click
	@Test
	public void test08_AddChildNodeByRightClick()
	{
		//Define data
		Map<String, String> languages = new HashMap<String, String>();
		languages.put("English", "");

		info("Sign as John");
		signIn("john", "gtn");

		//Go to portal site
		info("Go to portal site");
		goToPortalSites();

		//Add child node for existing node by right click
		info("Add child node for existing node by right click");
		addNodeForPortal("intranet", "Home", false, "PORNAV_14_01_007", true, languages, "PORNAV_14_01_007", null, null, true, true, true);

		//Delete data
		info("Delete node");
		deleteNode("intranet", "Home", "PORNAV_14_01_007", true);


	}
	//FNC_GTN_POR_PORNAVIGATION_14_01_016: Add 2 nodes with same name in one level
	@Test
	public void test16_AddNodeSameNameInOneLevel()
	{
		//Define data
		Map<String, String> languages = new HashMap<String, String>();
		languages.put("English", "");

		info("Sign as John");
		signIn("john", "gtn");

		//Go to portal site
		info("Go to portal site");
		goToPortalSites();

		//Add child node for existing node
		info("Add child node for Overview node");
		addNodeForPortal("acme", "Overview", true, "PORNAV_14_01_016", true, languages, "PORNAV_14_01_016", "page_PORNAV_14_01_016", "page_PORNAV_14_01_016", true, true, false);

		//Open edit navigation form
		info("Open edit navigation form of amce site");
		editNavigation("acme");

		//Add node with the same name in parent node
		info("Add node with the same name with PORNAV_14_01_016");
		click("//a[@title='Overview']");
		addNode("PORNAV_14_01_016", "PORNAV_14_01_016", "page_PORNAV_14_01_016");
		//Click Save on add node form
		save();
		//Verify warning message
		info("Verify warning message: This node name already exists");
		waitForTextPresent("This node name already exists.");

		//Confirm warning message
		info("Confirm warning message");
		element = waitForAndGetElement(OK_BUTTON);
		element.click();
		pause(1000);

		//Close add node form
		info("Close add node form");
		element = waitForAndGetElement(CLOSE_BUTTON);
		element.click();

		/*-----------------------Delete data--------------------*/

		deleteNode("acme", "Overview", "PORNAV_14_01_016", true);		
		goToManagePages();
		deletePage(PageType.PORTAL, "page_PORNAV_14_01_016");
	}
	
	//FNC_GTN_POR_PORNAVIGATION_14_01_017: Add 2 nodes with same name in Different Level
	@Test 
	public void test17_AddNodeSameNameInDifferentLevel()
	{
		//Define data
		Map<String, String> languages = new HashMap<String, String>();
		languages.put("English", "");
		String nodeName = "PORNAV_14_01_017";

		signIn("john", "gtn");

		//Go to portal site
		info("Go to portal site");
		goToPortalSites();

		//Add node
		info("Add child node for an existing node: Forums");
		addNodeForPortal("intranet", "Forums", true, nodeName, true, languages, nodeName, "page01_PORNAV_14_01_017", "page01_PORNAV_14_01_017", true, true,false);

		//Add node with same name in other parent node
		info("Add node for an existing node: Wiki");
		addNodeForPortal("intranet", "Wiki", false, nodeName, true, languages, nodeName, "page02_PORNAV_14_01_017", "page02_PORNAV_14_01_017", true, true,false);

		/*------------------------------Delete data-------------------------*/
		info("Delete node");
		editNavigation("intranet");
		//Select parent node: Forum
		click(By.linkText("Forums"));
		rightClickOnElement("//a[@title='PORNAV_14_01_017']");
		click(By.linkText("Delete Node"));
		waitForConfirmation("Are you sure to delete this node?");
		save();
		assert isElementNotPresent(By.linkText(nodeName)):"Node is not present";
		pause(500);
		
		//Select parent node: Wiki
		editNavigation("intranet");
		click(By.linkText("Wiki"));
		rightClickOnElement("//a[@title='PORNAV_14_01_017']");
		click(By.linkText("Delete Node"));
		waitForConfirmation("Are you sure to delete this node?");
		save();
		assert isElementNotPresent(By.linkText(nodeName)):"Node is not present";
		pause(500);
		
		//deleteNode("intranet", "Forums", "PORNAV_14_01_017", true);
		//deleteNode("intranet", "Wiki", nodeName, true);

		//Go to manage page
		info("Go to manage page");
		goToManagePages();
		info("Delete page");
		//Delete page1
		deletePage(PageType.PORTAL,"page01_PORNAV_14_01_017");
		//Delete page2
		deletePage(PageType.PORTAL,"page02_PORNAV_14_01_017");
	}
	
	//FNC_GTN_POR_PORNAVIGATION_14_01_032: Add node with page name is same with existing
	@Test
	public void test32_AddNodeWithPageNameSameWithExisting()
	{
		//Define data
		Map<String, String> languages = new HashMap<String, String>();
		languages.put("English", "");

		signIn("john", "gtn");

		//Go to portal site
		info("Go to portal site");
		goToPortalSites();

		//Add node
		info("Add new node");
		addNodeForPortal("acme", "Overview", true, "PORNAV_14_01_032", true, languages, "PORNAV_14_01_032", "page_PORNAV_14_01_032", "page_PORNAV_14_01_032", true, true,false);

		/*Add node with same page name*/
		info("Add node with name the same as page name");
		editNavigation("acme");
		//Select parent node
		click(By.linkText("News"));
		//Add node
		addNode("PORNAV_14_01_032_02", "PORNAV_14_01_032_02", "page_PORNAV_14_01_032");

		//Verify warning message
		debug("Verify warning message: This page name already exists");
		waitForMessage("This page name already exists.");
		//Confirm warning message
		debug("Confirm warning message");
		click(OK_BUTTON);
		//Close add node form				
		click(CLOSE_BUTTON);

		/*-----------------------------Delete data----------------------------*/
		//Delete node
		deleteNode("acme", "Overview", "PORNAV_14_01_032", true);

		//Delete Page
		goToManagePages();
		deletePage(PageType.PORTAL, "page_PORNAV_14_01_032");
	}

	//FNC_GTN_POR_PORNAVIGATION_14_01_043: Show site editor
	@Test
	public void test43_ShowSiteEditor()
	{
		//Define data
		Map<String, String> languages = new HashMap<String, String>();
		languages.put("English", "");

		signIn("john", "gtn");

		//Go to add user page
		debug("Go to New Staff page");
		goToNewStaff();

		//Add new user
		debug("Add new user");
		addNewUserAccount("thaopth", "123456", "123456", "thao", "pham", "thaopth@exoplatform.com", "thao", "English", true);
		info("add user successsfully");

		//Go to user and group management page
		debug("Go to user and group management page");
		goToUsersAndGroupsManagement();

		//Select group tab
		debug("Select group tab");
		chooseGroupTab();

		//Select group
		debug("Select group");
		selectGroup("Platform");
		selectGroup("Administration");

		//Add user to group
		debug("Add user to group");
		addUsersToGroup("thaopth", "manager", false, true);
		//Logout John
		debug("Logout john");
		driver.get(baseUrl);
		signOut();

		//Login as new user
		debug("Sign in as new user");
		signIn("thaopth", "123456");

		//Go to page management
		debug("Go to manage page");
		goToManagePages();
		//Create page
		debug("Add page");
		addNewPageAtManagePages(PageType.PORTAL, "page_PORNAV_14_01_043", "page_PORNAV_14_01_043", true, null, "Platform/Administration", "manager");
		//Logout
		driver.get(baseUrl);
		signOut();

		//Login as John
		signIn("john", "gtn");

		//Go to site link
		debug("Go to portal site");
		goToPortalSites();

		//Add node
		debug("Add node with added page above");
		addNodeForPortal("intranet", "Home", true, "PORNAV_14_01_043", true, languages, "PORNAV_14_01_043", "page_PORNAV_14_01_043","page_PORNAV_14_01_043", true, true, true);

		//Logout John
		debug("Logout John");
		driver.get(baseUrl);
		signOut();

		//Login new user
		debug("Login new user");
		signIn("thaopth", "123456");
		//Mouse over My sites link	
		waitForElementPresent(ELEMENT_MY_SITE_MENU, 500);
		mouseOver(ELEMENT_MY_SITE_MENU, true);
		//Mouse over intranet link
		waitForElementPresent(ELEMENT_INTRANET_MENU, 500);
		mouseOver(ELEMENT_INTRANET_MENU, true);
		//Mouse over Home link
		waitForElementPresent(ELEMENT_HOME_MENU, 500);
		mouseOver(ELEMENT_HOME_MENU, true);
		//Click on new page
		waitForElementPresent(PAGE_LINK, 500);
		click(PAGE_LINK);

		/*--------------Verify Site editor menu-------*/
		//Mouse over site editor link
		debug("mouse over site editor link");
		waitForElementPresent(ELEMENT_LINK_EDITOR, 500);
		mouseOver(ELEMENT_LINK_EDITOR, true);
		//Verify edit content link
		assert isElementPresent(ELEMENT_EDIT_CONTENT_LINK):"Edit content link presently";
		//Verify edit page link
		assert isElementPresent(ELEMENT_PAGE_MENU):"Edit page menu presently";
		//Mouse over edit page link
		mouseOver(ELEMENT_PAGE_MENU, true);
		//Verify edit page layout link
		assert isElementPresent(ELEMENT_LAYOUT_MENU):"Edit page layout presently";
		//Verify add page link
		assert isElementPresent(ELEMENT_ADD_PAGE_MENU):"Add page link presently";
		//Mouse over Edit site link
		waitForElementPresent(ELEMENT_SITE_MENU, 500);
		mouseOver(ELEMENT_SITE_MENU, true);
		//Verify edit site layout link
		assert isElementPresent(ELEMENT_EDIT_SITE_LAYOUT):"Edit site link presently";
		//Verify add site link
		assert isElementPresent( ELEMENT_ADD_SITE_LINK):"Add site link presently";
		driver.get(baseUrl);
		signOut();
		
		/*----------------Delete data---------------*/
		signIn("john", "gtn");
		goToPortalSites();
		deleteNode("intranet", "Home", "PORNAV_14_01_043", true);
		goToManagePages();
		deletePage(PageType.PORTAL, "page_PORNAV_14_01_043");
		goToUsersAndGroupsManagement();
		deleteUser("thaopth");

	}
	// FNC_GTN_POR_PORNAVIGATION_14_01_044: Check Site Editor menu when a user have a Edit right
		@Test
		public void test44_CheckEditMenuWithEditRight() {
			//data
			String username="checkmenu2";
			String pass="123456";
			Map <String, String> language = new HashMap<String, String>();
			language.put("English", "");
			String pageName = "FNC_GTN_POR_14_01";
			String pageTitle= "FNC_GTN_POR_14_01";
			String nodeName="FNC_GTN_POR_14_01";
			
			signIn("root", "gtn");
			info("Check Site Editor menu when a user has a Edit right");
					
			//create a user		
			goToNewStaff();
			addNewUserAccount(username, pass, pass, "check", "menu", "menu4@exoplatform.com", "", "", true);
			
			//add the user to groups
			goToUsersAndGroupsManagement();
			chooseGroupTab();
			selectGroup(ELEMENT_GROUP_DEVELOPMENT);
			addUsersToGroup(username, "manager", true, true);
			selectGroup(ELEMENT_GROUP_PLATFORM);
			selectGroup(ELEMENT_GROUP_ADMIN);
			addUsersToGroup(username, "member", true, true);
			
			//create a page
			goToManagePages();
			addNewPageAtManagePages(PageType.PORTAL, pageName, pageTitle, 
					true, null, "Platform/Administration", "member");
					
			// create a node
			goToPortalSites(); 
			addNodeForPortal("intranet", "Home", true, nodeName, true, language,
					"FNC_GTN_POR_14_01", pageName, pageTitle, true, true, true); 
			
			//edit a page
			goToManagePages();
			editPageAtManagePages(PageType.PORTAL, pageName);
			click(ELEMENT_VIEW_PROPERTY);
			click(ELEMENT_PERMISSION_SETTING_TAB);
			click(ELEMENT_EDIT_PERMISSION_LINK);
			setEditPermissions("Platform/Administration", "*");
			click(ELEMENT_EDIT_PAGE_SAVE);
			click(ELEMENT_EDIT_PAGE_FINISH);
			pause(1000);
			driver.get(baseUrl);
			signOut();
			
			//login as a user that have just been created  
			signIn(username, pass);
			
			//go to the new page
			mouseOver(ELEMENT_MY_SITE_MENU, true);
			actions.moveToElement(waitForAndGetElement(ELEMENT_MY_SITE_MENU)).build().perform();
			pause(500);
			mouseOver(ELEMENT_INTRANET_MENU, true);
			actions.moveToElement(waitForAndGetElement(ELEMENT_INTRANET_MENU)).build().perform();
			pause(500);
			mouseOver(ELEMENT_HOME_MENU, true);
			actions.moveToElement(waitForAndGetElement(ELEMENT_HOME_MENU)).build().perform();
			pause(500);
			waitForAndGetElement(By.linkText(nodeName)).click();
			pause(500);
			
			//check expected result
			actions.moveToElement(waitForAndGetElement(ELEMENT_EDIT_MENU)).build().perform();
			pause(500);

			actions.moveToElement(waitForAndGetElement(ELEMENT_PAGE_MENU)).build().perform();
			pause(500);

			//check whether to view Edit page, Edit layout, Add page
			assert isElementPresent(ELEMENT_LAYOUT_MENU): "Fail to view 'Edit page' link ";
			assert isElementPresent(ELEMENT_ADD_PAGE_MENU): "Fail to view 'Add page' link ";
			actions.moveToElement(waitForAndGetElement(ELEMENT_SITE_MENU)).build().perform();
			pause(500);
			assert isElementPresent(ELEMENT_LAYOUT_MENU): "Fail to view 'Edit layout' link ";
			driver.get(baseUrl);
			signOut();

			signIn("root", "gtn");
			//delete user
			goToUsersAndGroupsManagement();
			chooseUserTab();
			deleteUser(username);
			
			//delete page
			goToManagePages();
			deletePage(PageType.PORTAL, pageName);  
			
			//delete node
			goToPortalSites();
			deleteNode("intranet", "Home", nodeName, true);
			
		}
		//FNC_GTN_POR_PORNAVIGATION_14_01_045: Check Site Editor menu when a user has not a Edit right
		@Test
		public void test45_CheckEditMenuWithoutEditRight() {
			//data
			String username="checkmenu1";
			String pass="123456";
			Map <String, String> language = new HashMap<String, String>();
			language.put("English", "");
			String pageName = "FNC_GTN_POR_14_01";
			String pageTitle= "FNC_GTN_POR_14_01";
			String nodeName="FNC_GTN_POR_14_01";
			
			info("Check Site Editor menu when a user has not a Edit right");
			signIn("root", "gtn");
			
			//create a user		
			goToNewStaff();
			addNewUserAccount(username, pass, pass, "check", "menu", "menu1@exoplatform.com", "", "", true);
			
			//add the user to groups
			goToUsersAndGroupsManagement();
			chooseGroupTab();
			selectGroup(ELEMENT_GROUP_DEVELOPMENT);
			addUsersToGroup(username, "manager", true, true);
			selectGroup(ELEMENT_GROUP_PLATFORM);
			selectGroup(ELEMENT_GROUP_ADMIN);
			addUsersToGroup(username, "member", true, true);
			
			//create a page
			goToManagePages();
			addNewPageAtManagePages(PageType.PORTAL, pageName, pageTitle, 
					true, null, "Platform/Administration", "member");
					
			// create a node
			goToPortalSites(); 
			addNodeForPortal("intranet", "Home", true, nodeName, true, language,
					"FNC_GTN_POR_14_01", pageName, pageTitle, true, true, true); 
			
			//edit a page
			goToManagePages();
			editPageAtManagePages(PageType.PORTAL, pageName);
			click(ELEMENT_VIEW_PROPERTY);
			click(ELEMENT_PERMISSION_SETTING_TAB);
			click(ELEMENT_EDIT_PERMISSION_LINK);
			setEditPermissions("Platform/Administration", "manager");
			click(ELEMENT_EDIT_PAGE_SAVE);
			click(ELEMENT_EDIT_PAGE_FINISH);
			pause(1000);
			driver.get(baseUrl);
			signOut();
			
			//login as a user that have just been created  
			signIn(username, pass);
			
			//go to the new page

			actions.moveToElement(waitForAndGetElement(ELEMENT_MY_SITE_MENU)).build().perform();
			pause(500);

			actions.moveToElement(waitForAndGetElement(ELEMENT_INTRANET_MENU)).build().perform();
			pause(500);

			actions.moveToElement(waitForAndGetElement(ELEMENT_HOME_MENU)).build().perform();
			pause(1000);
			waitForAndGetElement(By.linkText(nodeName)).click();
			pause(500);
			
			//check expected result

			actions.moveToElement(waitForAndGetElement(ELEMENT_EDIT_MENU)).build().perform();
			pause(500);
	
			actions.moveToElement(waitForAndGetElement(ELEMENT_SITE_MENU)).build().perform();
			pause(500);
			assert isElementPresent(ELEMENT_LAYOUT_MENU): "Fail to view 'Edit layout' link ";
			driver.get(baseUrl);
			signOut();

			signIn("root", "gtn");
			//delete user
			goToUsersAndGroupsManagement();
			chooseUserTab();
			deleteUser(username); 
			
			//delete page
			goToManagePages();
			deletePage(PageType.PORTAL, pageName);  
			
			//delete node
			goToPortalSites();
			deleteNode("intranet", "Home", nodeName, true);
		}
}