package org.exoplatform.selenium.platform.exogtn.functional.group.administration;

import java.util.HashMap;
import java.util.Map;
import org.exoplatform.selenium.platform.ManageAccount;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;
import static org.exoplatform.selenium.platform.UserGroupManagement.*;
import static org.exoplatform.selenium.platform.ManageApplications.*;
import static org.exoplatform.selenium.platform.PageManagement.*;
import static org.exoplatform.selenium.platform.NavigationManagement.*;
import static org.exoplatform.selenium.TestLogger.*;

/**
 * @author thaopth
 * date: 26/09/2012
 */
public class EXOGTN_Group_Administration_ApplicationRegistry_SetRightApplication extends ManageAccount {
	
	@BeforeMethod()
	public void beforeTest() throws Exception
	{
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		driver.manage().window().maximize();
	}
	
	@AfterMethod()
	public void afterTest() throws Exception
	{
		driver.quit();
	}
	
	//Define data
	public static By ELEMENT_APPLICATIONS_LINK = By.linkText("Applications");
	public static By ELEMENT_EDIT_LINK = By.linkText("Edit");

	/*
	 * case No 39: Limit access right for application
	 * Create new user, add user into platform/administration group with membership is "member"
	 * Go to application registry, create new category with access permission is: Platform/Administration, member is is "member"
	 * Add application into new category with same access right as category
	 * Login as new user, add page with portlet in new category --> display "protected content"
	 * Login as user belongs to "Platform/Administration (manager), add page with portlet in new category --> Can user this portlet
	 */
	@Test
	public void test03_LimitAccessRightForApplication () {
		//Data
		String DATA_USER_NAME = "puca";
		String DATA_PASS = "123456";
		String DATA_FIRST_NAME = "puca";
		String DATA_LAST_NAME = "test";
		String DATA_EMAIL = ""+DATA_USER_NAME+"@exoplatform.com";
		String DATA_LANGUAGE = "English";
		String DATA_CATEGORY_NAME = "Categorytest";
		By DATA_CATEGORY_PATH = By.xpath("//a[@title='"+DATA_CATEGORY_NAME+"']");
		String DATA_APPLICATION_TYPE = "Portlet";
		String DATA_APP_DISPLAY_NAME = "Administration Toolbar Portlet";
		Map<String, String> permissions = new HashMap<String, String>();
		permissions.put("Platform/Administration", "member");
		String DATA_GROUP = "Platform/Administration";
		String DATA_MEMBER_SHIP = "manager";
		String DATA_NODENAME1 = "Case03_01";
		String DATA_NODENAME2 = "Case03_02"; 
		Map<String, String> DATA_PORTLET_ID = new HashMap<String, String>();
		DATA_PORTLET_ID.put("Categorytest/AdminToolbarPortlet", "");
		By ELEMENT_CONTAINER = By.className("PortletLayoutDecorator");

		/*----- Step 1: Add new user and add user to group-----*/	
		signIn("john", "gtn");
		goToNewStaff();
		addNewUserAccount(DATA_USER_NAME, DATA_PASS, DATA_PASS, DATA_FIRST_NAME, DATA_LAST_NAME, DATA_EMAIL, DATA_FIRST_NAME, DATA_LANGUAGE, true);

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
		waitForTextNotPresent("Organization");
		addUsersToGroup(DATA_USER_NAME, "member", false, true);

		/*----------Step 2: Add new application-------*/
		debug("Go to application registry");
		goToApplicationRegistry();

		debug("Add category");
		addNewCategoryAtManageApplications(DATA_CATEGORY_NAME, DATA_CATEGORY_NAME, DATA_CATEGORY_NAME, false, permissions, true);

		debug("Add application into category");
		addApplicationToCategory(DATA_CATEGORY_NAME, false, null, DATA_APPLICATION_TYPE, DATA_APP_DISPLAY_NAME, false, DATA_GROUP, DATA_MEMBER_SHIP);

		/*-------Step 3: Add new page by user who has access right on portlet-----*/ 
		debug("Go to Page editor");
		goToAddPageEditor();

		addNewPageEditor(DATA_NODENAME1, DATA_NODENAME1, DATA_LANGUAGE, DATA_CATEGORY_NAME, DATA_PORTLET_ID, true);
		goToEditPageEditor();

		//Verify portlet is displayed
		mouseOver(ELEMENT_CONTAINER, true);
		waitForElementPresent(By.xpath("//div[text()='Administration Toolbar Portlet']"));
		click(ELEMENT_PAGE_FINISH_BUTTON);
		waitForTextNotPresent("Page Editor");

		signOut();
		driver.get(baseUrl);

		/* Step 4: Sign in as new user and add page with portlet which user does not have access right*/
		signIn(DATA_USER_NAME, DATA_PASS);

		debug("Go to page editor");
		goToAddPageEditor();

		debug("Add new page");
		addNewPageEditor(DATA_NODENAME2, DATA_NODENAME2, DATA_LANGUAGE, DATA_CATEGORY_NAME, DATA_PORTLET_ID, true);

		debug("Verify portlet displayed as protected content");
		goToEditPageEditor();
		mouseOver(ELEMENT_CONTAINER, true);
		
		waitForElementPresent(By.xpath("//div[text()='Protected Content']"));
		click(ELEMENT_PAGE_FINISH_BUTTON);
		waitForTextNotPresent("Page Editor");

		signOut();
		driver.get(baseUrl);

		/*-----------------Delete data------------*/
		signIn("john", "gtn");

		debug("Go to application registry");
		goToApplicationRegistry();
		waitForElementPresent(DATA_CATEGORY_PATH);
		click(DATA_CATEGORY_PATH);

		debug("Delete category");
		deleteCategoryAtManageApplications(DATA_CATEGORY_NAME, true);

		debug("Go to manage page");
		goToManagePages();

		debug("Delete page");
		deletePage(PageType.GROUP, DATA_NODENAME1);
		
		deletePage(PageType.PORTAL, DATA_NODENAME2);
		
		debug("Go to portal site");
		goToPortalSites();

		debug("Delete node");
		deleteNode("intranet", "Home", DATA_NODENAME2, true);

		debug("Go to group site");
		goToGroupSites();

		debug("Delete node");
		deleteNode("Administration", "Application Manager",DATA_NODENAME1 , false);

		debug("Go to user and group management");
		goToUsersAndGroupsManagement();

		debug("Delete user");
		deleteUser(DATA_USER_NAME);
	}
	
	/*Case 40: Limit access right for application not in public category
	 * 
	 */
	@Test
	public void test04_LimitAccessRightForApplicationInNotPublicCategory () {
		String DATA_USER_NAME = "sandra";
		String DATA_PASS = "123456";
		String DATA_FIRST_NAME = "sandra";
		String DATA_LAST_NAME = "bullock";
		String DATA_EMAIL = ""+DATA_USER_NAME+"@exoplatform.com";
		String DATA_LANGUAGE = "English";
		String DATA_CATEGORY_NAME = "Category04";
		By DATA_CATEGORY_PATH = By.xpath("//a[@title='"+DATA_CATEGORY_NAME+"']");
		String DATA_APPLICATION_TYPE = "Portlet";
		String DATA_APP_DISPLAY_NAME = "Invitation Spaces Portlet";
		Map<String, String> permissions = new HashMap<String, String>();
		permissions.put("Platform/Administration", "manager");
		String DATA_GROUP = "Platform/Administration";
		String DATA_MEMBER_SHIP = "manager";
		String DATA_NODENAME = "Case04";
		By ELEMENT_PAGE_DISPLAY_NAME = By.id("i18nizedLabel");
		By ELEMENT_ABORT_BUTTON = By.xpath("//a[@title='Abort']");
		By ELEMENT_YES_BUTTON = By.linkText("Yes");


		/*----- Step 1: Add new user and add user to group-----*/	
		signIn("john", "gtn");
		goToNewStaff();
		addNewUserAccount(DATA_USER_NAME, DATA_PASS, DATA_PASS, DATA_FIRST_NAME, DATA_LAST_NAME, DATA_EMAIL, DATA_FIRST_NAME, DATA_LANGUAGE, true);

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
		addUsersToGroup(DATA_USER_NAME, "member", false, true);

		/*----------Step 2: Add new application-------*/
		debug("Go to application registry");
		goToApplicationRegistry();

		debug("Add category");
		addNewCategoryAtManageApplications(DATA_CATEGORY_NAME, DATA_CATEGORY_NAME, DATA_CATEGORY_NAME, false, permissions, true);

		debug("Add application into category");
		addApplicationToCategory(DATA_CATEGORY_NAME, false, null, DATA_APPLICATION_TYPE, DATA_APP_DISPLAY_NAME, false, DATA_GROUP, DATA_MEMBER_SHIP);

		signOut();
		driver.get(baseUrl);

		/*----Step 3: Add new page when user does not have access right on category----*/
		signIn(DATA_USER_NAME, DATA_PASS);

		debug("Go to page editor");
		goToAddPageEditor();

		debug("Add new page");
		type(ELEMENT_INPUT_NODE_NAME, DATA_NODENAME, true);
		type(ELEMENT_PAGE_DISPLAY_NAME, DATA_NODENAME, true);

		click(ELEMENT_PAGE_EDITOR_NEXT_STEP);
		waitForTextPresent("Empty Layout");
		click(ELEMENT_PAGE_EDITOR_NEXT_STEP);
		
		debug("Verify new user has not access right on category created above");
		waitForElementNotPresent(DATA_CATEGORY_PATH);
		click(ELEMENT_ABORT_BUTTON);
		waitForMessage("Modifications have been made. Are you sure to close without saving?");
		click(ELEMENT_YES_BUTTON);
		waitForTextPresent(DATA_FIRST_NAME);
		signOut();
		driver.get(baseUrl);
		
		//Delete data
		signIn("john", "gtn");
		goToUsersAndGroupsManagement();
		deleteUser(DATA_USER_NAME);
		goToApplicationRegistry();
		click(DATA_CATEGORY_PATH);
		deleteCategoryAtManageApplications(DATA_CATEGORY_NAME, true);
	}
}