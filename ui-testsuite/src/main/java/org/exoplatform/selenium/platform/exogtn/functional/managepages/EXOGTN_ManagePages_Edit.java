package org.exoplatform.selenium.platform.exogtn.functional.managepages;

import static org.exoplatform.selenium.TestLogger.debug;

/**
 * @author thaopth
 * @date: 18/09/2012
 */

import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.platform.ManageAccount.*;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;
import static org.exoplatform.selenium.platform.UserGroupManagement.*;
import static org.exoplatform.selenium.platform.PageManagement.*;

public class EXOGTN_ManagePages_Edit extends PlatformBase {
	//Define data
	By ELEMENT_VIEWPAGEPRO_BUTTON = By.linkText("View Page properties");
	By ELEMENT_PERMISSION_SET_TAB = By.xpath("//div[text()='Permission Settings']");
	By ELEMENT_EDIT_PERMISSION_SET = By.xpath("//a[text()='Edit Permission Settings']");
	By ELEMENT_SELECT_PERMISSION = By.linkText("Select Permission");
	By GROUP_PLATFORM = By.linkText("Platform");
	By GROUP_ADMIN = By.linkText("Administration");
	By ELEMENT_MEMBERSHIP_MEMBER = By.linkText("member");
	By ELEMENT_FINISH_BUTTON = By.xpath("//a[@title='Finish']");
	By ELEMENT_EDIT_PAGE_LINK = By.linkText("Edit");
	By ELEMEMT_PAGE_MENU = By.linkText("Page");
	By ELEMENT_PAGE_LAYOUT = By.linkText("Layout");
	By ELEMENT_PUBLIC_CHECKBOX = By.xpath("//input[@name='publicMode']");
	By ELEMENT_ABORT_BUTTON = By.xpath("//a[@title='Abort']");
	By ELEMENT_CANCEL_BUTTON = By.linkText("Cancel");

	//Edit a page at Manage Pages
	public void editPageAtManagePages(PageType type, String pageTitle){
		String pageEditIcon = ELEMENT_PAGE_EDIT_ICON.replace("${page}", pageTitle);
		searchPageByTitle(type, pageTitle);
		click(pageEditIcon);
		pause(1000);

	}

	@BeforeMethod
	public void beforeMethods() throws Exception {
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void afterTest() throws Exception {
		signOut();
		driver.quit();
	}

	@Test 
	/* FNC_GTN_POR_MNP_22_019: Change edit right on portal page while editing portal page's properties
	 * Add new user
	 * Add user into group Platform/Administration with membership is "member"
	 * Create page for portal
	 * Change "edit permission" of page
	 * Login as user who belongs to group has edit permission
	 * Verify
	 */
	public void test19_ChangeEditRightOnPortalPage ()
	{
		String USER_NAME= "mimi";
		String INTRANET_LINK = baseUrl+"/portal/intranet/";
		String PAGE_NAME = "FNC_GTN_POR_MNP_22_019";
		String PAGE_TITLE = "FNC_GTN_POR_MNP_22_019";

		signIn("john", "gtn");

		goToNewStaff();
		addNewUserAccount(USER_NAME, "123456", "123456", USER_NAME, "test", "mimi@exoplatform.com", USER_NAME, "English", true);
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
		addUsersToGroup(USER_NAME, "member", false, true);

		debug("Go to intranet");
		driver.get(INTRANET_LINK);

		debug("Go to manage page");
		goToManagePages();

		debug("page management");
		editPageAtManagePages(PageType.GROUP, "Page Management");

		//edit access right for page management
		waitForElementPresent(ELEMENT_VIEWPAGEPRO_BUTTON);
		click(ELEMENT_VIEWPAGEPRO_BUTTON);

		waitForElementPresent(ELEMENT_PERMISSION_SET_TAB);
		click(ELEMENT_PERMISSION_SET_TAB);

		debug("Set view permission for page management");
		setViewPermissions("Platform/Administration", "*");


		//Change edit permission of page

		waitForElementPresent(ELEMENT_EDIT_PERMISSION_SET);
		click(ELEMENT_EDIT_PERMISSION_SET);

		debug("Set edit permission for page management");
		setEditPermissions("Platform/Administration", "member");

		debug("Save edit page form");
		save();

		debug("click finish button");
		waitForElementPresent(ELEMENT_FINISH_BUTTON);
		click(ELEMENT_FINISH_BUTTON);

		//Add new page for portal
		debug("Add new page for portal");
		addNewPageAtManagePages(PageType.PORTAL, PAGE_NAME, PAGE_TITLE, true, null, "Platform/Administration", "manager");
		waitForTextPresent(PAGE_NAME);

		debug("Edit new page");
		editPageAtManagePages(PageType.PORTAL, PAGE_TITLE);

		debug("Change edit permission of new page");
		waitForElementPresent(ELEMENT_VIEWPAGEPRO_BUTTON);
		click(ELEMENT_VIEWPAGEPRO_BUTTON);

		waitForElementPresent(ELEMENT_PERMISSION_SET_TAB);
		click(ELEMENT_PERMISSION_SET_TAB);

		waitForElementPresent(ELEMENT_EDIT_PERMISSION_SET);
		click(ELEMENT_EDIT_PERMISSION_SET);

		debug("Set edit permission for new page");
		setEditPermissions("Platform/Administration", "member");

		save();
		waitForElementPresent(ELEMENT_FINISH_BUTTON);
		click(ELEMENT_FINISH_BUTTON);
		waitForElementNotPresent(ELEMENT_FINISH_BUTTON);
	

		signOut();
		driver.get(baseUrl);

		//Login new user and view edit page form
		signIn(USER_NAME, "123456");
		goToManagePages();
		mouseOver(ELEMENT_EDIT_PAGE_LINK, true);
		mouseOver(ELEMEMT_PAGE_MENU, true);
		mouseOverAndClick(ELEMENT_PAGE_LAYOUT);
		waitForTextPresent("View Page properties");

		//Abort edit page form
		click(ELEMENT_ABORT_BUTTON);
		waitForElementNotPresent(ELEMENT_ABORT_BUTTON);
		signOut();
		driver.get(baseUrl);

		signIn("john", "gtn");

		//Delete data
		goToUsersAndGroupsManagement();
		deleteUser(USER_NAME);
		goToManagePages();
		deletePage(PageType.PORTAL, PAGE_NAME);
		waitForTextNotPresent(PAGE_NAME);

	}

	@Test
	/* FNC_GTN_POR_MNP_22_021: Check change after edit page properties with finish
	 * Create new page
	 * Edit page properties: page title
	 * Verify new title of page
	 * Delete data
	 */
	public void test22_CheckChangesAfterEditPagePropertiesWithFinish () 
	{
		//Define data
		String PAGE_NAME = "FNC_GTN_POR_MNP_22_021";
		String PAGE_TITLE = "FNC_GTN_POR_MNP_22_021";

		signIn("john", "gtn");

		debug("Go to manage pages");
		goToManagePages();

		debug("Add new page");
		addNewPageAtManagePages(PageType.PORTAL, PAGE_NAME, PAGE_TITLE, true, null, "Platform/Administration", "*");
		waitForTextPresent(PAGE_TITLE);

		debug("Edit page");
		editPageAtManagePages(PageType.PORTAL, PAGE_TITLE);

		//Go to view page properties
		debug("Click view page properties");
		waitForElementPresent(ELEMENT_VIEWPAGEPRO_BUTTON);
		click(ELEMENT_VIEWPAGEPRO_BUTTON);

		//Change page title
		debug("Change page title");
		type(ELEMENT_INPUT_TITLE, "FNC_GTN_POR_MNP_22_021_test", true);

		debug("Save edit properties form");
		save();

		debug("click finish button");
		waitForElementPresent(ELEMENT_FINISH_BUTTON);
		click(ELEMENT_FINISH_BUTTON);

		//Open edit page form again and verify new page title
		editPageAtManagePages(PageType.PORTAL, "FNC_GTN_POR_MNP_22_021_test");

		debug("Click view page properties");
		waitForElementPresent(ELEMENT_VIEWPAGEPRO_BUTTON);
		click(ELEMENT_VIEWPAGEPRO_BUTTON);
		waitForElementPresent(By.xpath("//input[@id='title' and @value='FNC_GTN_POR_MNP_22_021_test']"));

		debug("Cancel and abort edit page form");
		click(ELEMENT_CANCEL_BUTTON);
		click(ELEMENT_ABORT_BUTTON);

		//Delete data
		deletePage(PageType.PORTAL, "FNC_GTN_POR_MNP_22_021_test");
		waitForTextNotPresent("FNC_GTN_POR_MNP_22_021_test");
	}
}