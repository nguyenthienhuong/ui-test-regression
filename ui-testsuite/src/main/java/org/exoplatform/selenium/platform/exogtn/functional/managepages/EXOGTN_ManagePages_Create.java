package org.exoplatform.selenium.platform.exogtn.functional.managepages;

import static org.exoplatform.selenium.TestLogger.*;

import java.util.HashMap;
import java.util.Map;

import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.platform.ManageAccount.*;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;
import static org.exoplatform.selenium.platform.PageManagement.*;

/**
 * @author NhungVT, ThaoPTH
 * @date: 18/09/2012	
 */

public class EXOGTN_ManagePages_Create extends PlatformBase 
{
	//Define data
	public String INTRANET_LINK = "";
	public String ACME_LINK = "";
	public String PAGE_NAME = "";
	public String PAGE_TITLE = "";
	
	public final static By ELEMENT_ADD_PAGE_BUTTON = By.linkText("Add New Page");
	public final static By ELEMENT_PAGE_NAME_INPUT = By.xpath("//input[@id='name']");
	public final static By ELEMENT_PAGE_TITLE_INPUT = By.xpath("//input[@id='title']");
	public final static By ELEMENT_OWNER_TYPE = By.xpath("//select[@name='ownerType']");
	public final static By ELEMENT_OWNER_ID_INTRANET = By.xpath("//input[@id='ownerId' and @value='intranet']");
	public final static By CANCEL_BUTTON = By.linkText("Cancel");
	
	public String ADMINISTRATION_LINK = "//a[text()='Administration']";
	public String MANAGEMENT_LINK = "//a[text()='Management']";
	public String HOME_LINK = "//a[@title='Home']";
	public String ABORT_BUTTON = "//a[text()='Abort']";
	public String PORTAL_MANAGEMENT_LINK = "//a[@title='Portal Administration']";
	public String APPLICATION_MANAGER_LINK = "//a[@title='Application Manager']";
	public String PAGE_MANAGER_LINK = "//a[@title='Page Manager']";
	public String ADD_USERS_LINK = "//a[@title='Add Users']";
	public String USERS_GROUP_MANAGER_LINK = "//a[@title='User and Group Manager']";
	public String UP_LEVEL_ICON = "//a[@title='Up Level']";
	public String DEFAULT_NODE = "//div[contains(text(),'/default')]";
	public String MY_SITES_LINK = "//a[contains(text(),'My Sites')]";
	public String INTRANET_LINK_TEXT = "//a[text()='intranet']";
	public String NODE_NAME_INPUT = "//input[@id='pageName']";
	public String NEXT_BUTTON = "//a[text()='Next']";
	public String EMPTY_LAYOUT = "//div[text()='Empty Layout']";
	public String OK_BUTTON = "//a[text()='OK']";
	
	WebElement ELEMENT = null;
	
	//Messages
	public String BLANK_NODE_NAME_MESSAGE = "The field \"Node Name\" is required.";
	public String NODE_NAME_START_NUMBER_MESSAGE = "The field \"Node Name\" must start with a character and must not contain special characters.";
	public String NODE_NAME_START_SPECIAL_MESSAGE = "Only alpha, digit, dash and underscore characters allowed for the field \"Node Name\".";
	public String PAGE_NAME_EXIST_MESSAGE = "This page name already exists.";
	
	@BeforeMethod()
	public void beforeTest() throws Exception
	{
		initSeleniumTest();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseUrl);
		actions = new Actions(driver);
		signIn("john", "gtn");
	}
	
	/*--------------- Manage Page - Create #1 ---------------*/
	
	//Check showing add new page form (Portal, Group)
	@Test()
	public void test01_CheckShowNewPageForm()
	{
		info("-START test01_CheckShowNewPageForm");
		
		//Check Showing on Portal's page
		
		//Goto Edit >  Page > Add Page of Portal
		goToAddPageEditor();
		
		//Verify Left panel 
		waitForElementPresent(HOME_LINK);
		
		//Verify Right panel
		waitForElementPresent(NODE_NAME_INPUT);
		
		//Click Abort button
		click(ABORT_BUTTON);
		
		//Check Showing on Group's page
		
		//Goto Settings > Administration > Management
		mouseOver(ELEMENT_LINK_SETUP, true);
		pause(500);
		mouseOver(ADMINISTRATION_LINK, true);
		pause(500);
		mouseOverAndClick(MANAGEMENT_LINK);
		pause(500);
		
		//Goto Edit >  Page > Add Page of Group
		goToAddPageEditor();
		
		//Verify Left panel 
		waitForElementPresent(PORTAL_MANAGEMENT_LINK);
		
		//Verify Right panel
		waitForElementPresent(NODE_NAME_INPUT);
		
		//Click Abort button
		click(ABORT_BUTTON);
		
		info("-END test01_CheckShowNewPageForm");
	}
	
	//Check showing pages list of selected navigation at step 1 of Create page  form
	@Test()
	public void test06_CheckShowPageListOfSelectedNavigation()
	{
		info("-START test06_CheckShowPageListOfSelectedNavigation");
		
		//Check Showing on Group's page
		
		//Goto Settings > Administration > Management
		mouseOver(ELEMENT_LINK_SETUP, true);
		pause(500);
		mouseOver(ADMINISTRATION_LINK, true);
		pause(500);
		mouseOverAndClick(MANAGEMENT_LINK);
		pause(500);
		
		//Goto Edit >  Page > Add Page of Portal
		goToAddPageEditor();
		
		//Click on Portal Management
		waitForElementPresent(PORTAL_MANAGEMENT_LINK);
		click(PORTAL_MANAGEMENT_LINK);
		
		//Verify sub-pages of Portal Management
		waitForElementPresent(APPLICATION_MANAGER_LINK);
		waitForElementPresent(PAGE_MANAGER_LINK);
		waitForElementPresent(ADD_USERS_LINK);
		waitForElementPresent(USERS_GROUP_MANAGER_LINK);
		
		//Click on Up Level icon
		click(UP_LEVEL_ICON);
		
		//Verify Selected Page Node is /default
		waitForElementPresent(DEFAULT_NODE);
		
		//Click Abort button
		click(ABORT_BUTTON);
		
		//Check Showing on Portal's page
		
		//Goto My Sites > intranet > Home
		mouseOver(MY_SITES_LINK, true);
		pause(500);
		mouseOverAndClick(INTRANET_LINK_TEXT);
		pause(500);
		
		//Goto Edit >  Page > Add Page of Portal
		goToAddPageEditor();
		
		//Click on Home
		waitForElementPresent(HOME_LINK);
		click(HOME_LINK);
		
		//Click on Up Level icon
		waitForElementPresent(UP_LEVEL_ICON);
		click(UP_LEVEL_ICON);
		
		//Verify Selected Page Node is /default
		waitForElementPresent(DEFAULT_NODE);
		
		//Click Abort button
		click(ABORT_BUTTON);
		
		info("-END test06_CheckShowPageListOfSelectedNavigation");
	}
	
	//Complete step 1 in Create page  with valid value
	@Test()
	public void test07_CompleteStep1WithValidValue()
	{
		info("-START test07_CompleteStep1WithValidValue");
		
		//Goto Edit >  Page > Add Page of Portal
		goToAddPageEditor();
		
		//Verify Left panel 
		waitForElementPresent(HOME_LINK);
		
		//Verify Right panel
		waitForElementPresent(NODE_NAME_INPUT);
		
		//Input valid value for all fields at Step1
		type(NODE_NAME_INPUT, "Page1", true);
		
		//Click Next button
		click(NEXT_BUTTON);
		
		//Verify Step 2
		waitForTextPresent("Select a Page Layout Template.");
		waitForElementPresent(EMPTY_LAYOUT);
		
		//Click Abort button
		click(ABORT_BUTTON);
		
		info("-END test07_CompleteStep1WithValidValue");
	}
	
	//Complete step 1 in Create page with blank Node Name
	@Test()
	public void test08_CompleteStep1WithBlankNodeName()
	{
		info("-START test08_CompleteStep1WithBlankNodeName");
		
		//Goto Edit >  Page > Add Page of Portal
		goToAddPageEditor();
		
		//Verify Left panel 
		waitForElementPresent(HOME_LINK);
		
		//Verify Right panel
		waitForElementPresent(NODE_NAME_INPUT);
		
		//Click Next button with Blank Node Name
		click(NEXT_BUTTON);
		
		//Verify alert message
		waitForTextPresent(BLANK_NODE_NAME_MESSAGE);
		click(OK_BUTTON);
		
		//Click Abort button
		click(ABORT_BUTTON);
		
		info("-END test08_CompleteStep1WithBlankNodeName");
	}
	
	//Complete step 1 in Create page with Node Name start with number
	@Test()
	public void test09_CompleteStep1WithNodeNameStartNumber()
	{
		info("-START test09_CompleteStep1WithNodeNameStartNumber");
		
		//Goto Edit >  Page > Add Page of Portal
		goToAddPageEditor();
		
		//Verify Left panel 
		waitForElementPresent(HOME_LINK);
		
		//Verify Right panel
		waitForElementPresent(NODE_NAME_INPUT);
		
		//Input node name start with number at Step1
		type(NODE_NAME_INPUT, "1Page", true);
		
		//Click Next button
		click(NEXT_BUTTON);
		
		//Verify Step 2
		waitForTextPresent(NODE_NAME_START_NUMBER_MESSAGE);
		click(OK_BUTTON);
		
		//Click Abort button
		click(ABORT_BUTTON);
		
		info("-END test09_CompleteStep1WithNodeNameStartNumber");
	}
	
	//Complete step 1 in Create page wizard with Node Name start with dot and underscore characters 
	@Test()
	public void test10_CompleteStep1WithNodeNameStartSpeacialChars()
	{		
		info("-START test10_CompleteStep1WithNodeNameStartSpeacialChars");
		
		//Goto Edit >  Page > Add Page of Portal
		goToAddPageEditor();
		
		//Verify Left panel 
		waitForElementPresent(HOME_LINK);
		
		//Verify Right panel
		waitForElementPresent(NODE_NAME_INPUT);
		
		//Input node name start with dash char at Step1
		type(NODE_NAME_INPUT, "-Page", true);
		
		//Click Next button
		click(NEXT_BUTTON);
		
		//Verify Step 2
		waitForTextPresent(NODE_NAME_START_NUMBER_MESSAGE);
		click(OK_BUTTON);
		
		//Reset Node Name value
		ELEMENT = waitForAndGetElement(By.xpath(NODE_NAME_INPUT));
		ELEMENT.clear();
		
		//Input node name start with dot char at Step1
		type(NODE_NAME_INPUT, ".Page", true);
		
		//Click Next button
		click(NEXT_BUTTON);
		
		//Verify Step 2
		waitForTextPresent(NODE_NAME_START_SPECIAL_MESSAGE);
		click(OK_BUTTON);
		
		//Click Abort button
		click(ABORT_BUTTON);
		
		info("-END test10_CompleteStep1WithNodeNameStartSpeacialChars");
	}
	
	/*--------------- Manage Page - Create #2 ---------------*/
	
	@Test 
	/* FNC_GTN_POR_MNP_20_022
	 * Create portal page
	 * Create group page same name as portal page
	 */
	public void test22_CreateGroupPageSameNameWithPortalPage()
	{
		info("-START test22_CreateGroupPageSameNameWithPortalPage");
		
		goToManagePages();
		waitForElementPresent(ELEMENT_ADD_PAGE_BUTTON);
		
		PAGE_NAME = "FNC_GTN_POR_MNP_20_022";
		PAGE_TITLE = "FNC_GTN_POR_MNP_20_022";

		debug("Add new page for current portal");
		addNewPageAtManagePages(PageType.PORTAL, PAGE_NAME, PAGE_TITLE, true, null, "Platform/Administration", "manager");
		debug("Verify new page is create successfully");
		waitForTextPresent(PAGE_NAME);

		debug("Add new page with same name for group");
		waitForElementPresent(ELEMENT_ADD_PAGE_BUTTON);
		addNewPageAtManagePages(PageType.GROUP, PAGE_NAME, PAGE_TITLE, true, null, "Platform/Administration", "manager");
		waitForTextPresent(PAGE_NAME);

		//Delete data
		goToManagePages();
		deletePage(PageType.PORTAL, PAGE_NAME);
		waitForTextNotPresent(PAGE_NAME);
		deletePage(PageType.GROUP, PAGE_NAME);
		waitForTextNotPresent(PAGE_NAME);
		
		info("-END test22_CreateGroupPageSameNameWithPortalPage");
	}
	
	@Test
	/* FNC_GTN_POR_MNP_20_023
	 * Create page for group
	 */
	public void test23_CreatePageForGroup ()
	{
		info("-START test23_CreatePageForGroup");
		
		PAGE_NAME = "FNC_GTN_POR_MNP_20_023";
		PAGE_TITLE = "FNC_GTN_POR_MNP_20_023";
		
		Map<String, String> permissions = new HashMap<String, String>();
		permissions.put("Platform/Content Management", "manager");

		debug("Go to manage page");
		goToManagePages();

		debug("add new page");
		waitForElementPresent(ELEMENT_ADD_PAGE_BUTTON);
		addNewPageAtManagePages(PageType.GROUP, PAGE_NAME, PAGE_TITLE, false, permissions,"Platform/Administration", "manager");
		waitForTextPresent(PAGE_NAME);

		//Delete data
		deletePage(PageType.GROUP, PAGE_NAME);
		waitForTextNotPresent(PAGE_NAME);
		
		info("-END test23_CreatePageForGroup");
	}
	
	@Test
	/* FNC_GTN_POR_MNP_20_024
	 * Create page for portal
	 */
	public void test24_CreatePortalPage () 
	{
		info("-START test24_CreatePortalPage");
		
		PAGE_NAME = "FNC_GTN_POR_MNP_20_024";
		PAGE_TITLE = "FNC_GTN_POR_MNP_20_024";

		debug("Go to manage page");
		goToManagePages();

		debug("add new page");
		waitForElementPresent(ELEMENT_ADD_PAGE_BUTTON);
		addNewPageAtManagePages(PageType.PORTAL, PAGE_NAME, PAGE_TITLE, true, null,"Platform/Administration", "manager");
		waitForTextPresent(PAGE_NAME);

		//Deleta data
		debug("Delete page");
		deletePage(PageType.PORTAL, PAGE_NAME);
		waitForTextNotPresent(PAGE_NAME);
		
		info("-END test24_CreatePortalPage");
	}
	
	@Test
	/* FNC_GTN_POR_MNP_20_025
	 * Create page for portal
	 * Create page same name in one portal
	 */
	public void test25_CreatePagesSameNameInSamePortal () 
	{
		info("-START test25_CreatePagesSameNameInSamePortal");
		
		INTRANET_LINK = baseUrl+"/portal/intranet/";
		PAGE_NAME = "FNC_GTN_POR_MNP_20_025";
		PAGE_TITLE = "FNC_GTN_POR_MNP_20_025";

		debug("Go to intranet");
		driver.get(INTRANET_LINK);

		debug("Go to manage page");
		goToManagePages();
		waitForElementPresent(ELEMENT_ADD_PAGE_BUTTON);

		debug("Add new page");
		waitForElementPresent(ELEMENT_ADD_PAGE_BUTTON);
		addNewPageAtManagePages(PageType.PORTAL, PAGE_NAME, PAGE_TITLE, true, null, "Platform/Content Management", "*");
		waitForTextPresent(PAGE_NAME);

		goToManagePages();

		click(ELEMENT_ADD_PAGE_BUTTON);

		waitForTextPresent("Page Settings");

		select(ELEMENT_SELECT_OWNER_TYPE, "portal");
		waitForElementPresent(ELEMENT_OWNER_ID_INTRANET);

		type(ELEMENT_PAGE_NAME_INPUT, PAGE_NAME, true);
		type(ELEMENT_PAGE_TITLE_INPUT, PAGE_TITLE, true);
		save();

		waitForMessage(PAGE_NAME_EXIST_MESSAGE);
		click(OK_BUTTON);
		click(CANCEL_BUTTON);

		//Delete data
		goToManagePages();
		deletePage(PageType.PORTAL, PAGE_NAME);
		
		info("-END test25_CreatePagesSameNameInSamePortal");
	}
	
	@Test
	/* FNC_GTN_POR_MNP_20_028
	 * Create page for group
	 * Create portal page with name same as group page
	 */
	public void test28_CreatePortalPageSameNameAsGroupPage ()
	{
		info("-START test28_CreatePortalPageSameNameAsGroupPage");
		
		INTRANET_LINK = baseUrl+"/portal/intranet";
		PAGE_NAME = "FNC_GTN_POR_MNP_20_028";
		PAGE_TITLE = "FNC_GTN_POR_MNP_20_028";
		
		debug("Go to intranet");
		driver.get(INTRANET_LINK);

		debug("Go to manage page");
		goToManagePages();
		waitForElementPresent(ELEMENT_ADD_PAGE_BUTTON);

		debug("Add new page for group");
		addNewPageAtManagePages(PageType.GROUP, PAGE_NAME, PAGE_TITLE, true, null, "Platform/Visitors", "*");
		waitForTextPresent(PAGE_NAME);
		
		debug("Add new page for portal");
		waitForElementPresent(ELEMENT_ADD_PAGE_BUTTON);
		addNewPageAtManagePages(PageType.PORTAL, PAGE_NAME, PAGE_TITLE, true, null, "Platform/Administration", "manager");
		waitForTextPresent(PAGE_NAME);
		
		//Delete data
		deletePage(PageType.PORTAL, PAGE_NAME);
		waitForTextNotPresent(PAGE_NAME);
		deletePage(PageType.GROUP, PAGE_NAME);
		waitForTextNotPresent(PAGE_NAME);		
		
		info("-END test28_CreatePortalPageSameNameAsGroupPage");
	}
	
	@AfterMethod()
	public void afterTest() throws Exception
	{
		driver.quit();
	}
}