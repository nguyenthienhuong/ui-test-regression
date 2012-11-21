package org.exoplatform.selenium.platform.exogtn.functional.group.administration;

import static org.exoplatform.selenium.platform.ManageAccount.*;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;
import static org.exoplatform.selenium.platform.ManageApplications.*;
import static org.exoplatform.selenium.platform.UserGroupManagement.*;
import java.util.HashMap;
import java.util.Map;
import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EXOGTN_Group_Administration_ApplicationRegistry_EditCategory extends PlatformBase
{
	//Define Data
	public String CATE_NAME_01 = "PLT_APPR_05_02_CATE1";
	public String CATE_DISP_NAME_01 = "PLT_APPR_05_02_CATE1";
	public String CATE_NAME_02 = "PLT_APPR_05_02_CATE2";
	public String CATE_DISP_NAME_02 = "PLT_APPR_05_02_CATE2";
	public String APP_ALL_PEOPLE = "All People Portlet";
	public String APP_ANSWER = "Answers Portlet";

	public String DATA_USER_NAME_01 = "user10";
	public String DATA_USER_NAME_02 = "sun21";
	public String DATA_PASSWORD = "123456";
	public String DATA_FIRST_NAME = "FirstMoon";
	public String DATA_LAST_NAME = "LastMoon";
	public String DATA_EMAIL03 = DATA_USER_NAME_01+"@exoplatform.com";
	public String DATA_EMAIL04 = DATA_USER_NAME_02+"@exoplatform.com";
	public String DATA_LANGUAGE = "English";
	public String PLATFORM_ADMIN_DATA_GROUP = "Platform/Administration";
	public String MANAGER_DATA_MEMBER_SHIP = "manager";

	public By CATEGORIES_AREA = By.xpath("//div[text()='Categories']"); 
	public By VIEW_PAGE_PROPERTIES = By.xpath("//a[text()='View Page properties']");
	public By PERMISSION_SETTINGS = By.xpath("//div[text()='Permission Settings']");
	public By PORTLET_CONTAINER = By.className("PortletLayoutDecorator");
	public By EDIT_PORTLET = By.xpath("//a[@title='Edit Portlet']");
	public By SAVE_AND_CLOSE_BUTTON = By.xpath("//a[text()='Save And Close']");
	public By FINISH_ICON = By.xpath("//a[@title='Finish']");
	public By ACCESS_PERMISSION = By.xpath("//div[text()='Access Permission']");

	//	public static void addApplicationToCategory (String categoryTitle, boolean addNewApps, String newDisplayName, String applicationType, String displayName, boolean publicMode, String groupId, String membership ) {
	//		By ELEMENT_ADD_APPS_BUTTON = By.xpath("//a[@title='"+categoryTitle+"']/following::a[@title='Add an Application to this Category']");
	//		By ELEMENT_DISPLAY_NAME_TEXTBOX = By.id("displayName");
	//		By ELEMNET_ADD_BUTTON = By.linkText("Add");
	//		By ELEMENT_APPS_TYPE = By.id("type");
	//		By ELEMENT_APPS_EXISTING = By.xpath("//span[@id='label' and text()='"+displayName+"']/../..//input[@name='application']");
	//		waitForElementPresent(ELEMENT_ADD_APPS_BUTTON);
	//		click(ELEMENT_ADD_APPS_BUTTON);
	//		waitForElementPresent(ELEMNET_ADD_BUTTON);
	//
	//		//add new app
	//		if (addNewApps) {
	//			waitForElementPresent(ELEMENT_DISPLAY_NAME_TEXTBOX);
	//			type(ELEMENT_DISPLAY_NAME_TEXTBOX, newDisplayName, true);
	//			select(ELEMENT_APPS_TYPE, applicationType);
	//		}
	//		else
	//		{
	//			select(ELEMENT_APPS_TYPE, applicationType);
	//			click(ELEMENT_APPS_EXISTING);
	//		}
	//		click(ELEMNET_ADD_BUTTON);
	//
	//		//set permission
	//		if (publicMode) makeItPublic(true);
	//		else {
	//			makeItPublic(false);
	//			setViewPermissions(groupId, membership);
	//		}
	//	}

	//	public void deleteApplication(String categoryTitle, String applicationName) {
	//		By CAT_XPATH = By.xpath("//a[@title='"+categoryTitle+"']");
	//		By DELETE_APP_ICON = By.xpath("//a[@title='"+applicationName+"']/following::a[@title='Delete Application']");
	//		waitForElementPresent(CAT_XPATH);
	//		click(CAT_XPATH);
	//		waitForElementPresent(DELETE_APP_ICON);
	//		click(DELETE_APP_ICON);
	//		waitForConfirmation("Are you sure to delete this application?");
	//		pause(1000);
	//		waitForTextNotPresent(applicationName);
	//		info("'"+applicationName+"' was deleted successfully");
	//	}

	@BeforeMethod()
	public void beforeTest() throws Exception
	{
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		driver.manage().window().maximize();
		signIn("john", "gtn");
	}

	//Change access right on category from public to be limited by group(s)
	@Test()
	public void test03_ChangeAccessRightFromPublicToLimited()
	{	
		//Add new user belongs to group Plaform/Administrator with membership is member 
		goToNewStaff();
		addNewUserAccount(DATA_USER_NAME_01, DATA_PASSWORD, DATA_PASSWORD, DATA_FIRST_NAME, DATA_LAST_NAME, DATA_EMAIL03, DATA_FIRST_NAME, DATA_LANGUAGE, true);

		//Go to user and group management page
		goToUsersAndGroupsManagement();

		//Select group tab
		chooseGroupTab();

		//Select group
		selectGroup(PLATFORM_ADMIN_DATA_GROUP);
		//		selectGroup("Administration");

		//Add user to group
		addUsersToGroup(DATA_USER_NAME_01, "member", false, true);

		//Go to Application Registry page > setting access right for page is public
		goToApplicationRegistry();
		waitForElementPresent(CATEGORIES_AREA);
		goToEditPageEditor();
		waitForElementPresent(VIEW_PAGE_PROPERTIES);
		click(VIEW_PAGE_PROPERTIES);
		waitForElementPresent(PERMISSION_SETTINGS);
		click(PERMISSION_SETTINGS);
		waitForElementPresent(ELEMENT_CHECKBOX_PUBLIC_MODE);
		//		check(ELEMENT_CHECKBOX_PUBLIC_MODE);
		makeItPublic(true);
		save();

		//Setting access right for portlet is public
		mouseOver(PORTLET_CONTAINER, false);
		click(EDIT_PORTLET);
		waitForElementPresent(ACCESS_PERMISSION);
		click(ACCESS_PERMISSION);
		waitForElementPresent(ELEMENT_CHECKBOX_PUBLIC_MODE);
		makeItPublic(true);
		//		check(ELEMENT_CHECKBOX_PUBLIC_MODE);
		waitForElementPresent(SAVE_AND_CLOSE_BUTTON);
		click(SAVE_AND_CLOSE_BUTTON);
		click(FINISH_ICON);

		//Add new category
		waitForElementPresent(CATEGORIES_AREA);
		addNewCategoryAtManageApplications(CATE_NAME_01, CATE_DISP_NAME_01, "", true, null, true);

		//Add Apps into added Category
		addApplicationToCategory(CATE_NAME_01, false, "All People", "Portlet", APP_ALL_PEOPLE, true, null, null);
		addApplicationToCategory(CATE_NAME_01, false, "Q&A", "Portlet", APP_ANSWER, true, null, null);

		//Edit permission of added Category from Public --> Limited 	
		Map<String, String> permissions = new HashMap<String, String>();
		permissions.put(PLATFORM_ADMIN_DATA_GROUP, MANAGER_DATA_MEMBER_SHIP);
		editCategoryAtManageApplications(CATE_NAME_01, CATE_DISP_NAME_01, "", false, permissions, false);

		//Sign out and Sign in as added user
		waitForTextPresent("John Smith");
		signOut();
		driver.get(baseUrl);
		signIn(DATA_USER_NAME_01, DATA_PASSWORD);

		//Go to Application Registry page 
		goToApplicationRegistry();

		//Verify added user can't see above edited Category 
		waitForElementNotPresent(CATE_NAME_01);

		//Sign out and Sign in as root
		signOut();
		driver.get(baseUrl);
		signIn("root", "gtn");

		//Go to Application Registry page 
		goToApplicationRegistry();

		//Reset default data

		waitForElementPresent(CATEGORIES_AREA);
		goToEditPageEditor();
		waitForElementPresent(VIEW_PAGE_PROPERTIES);
		click(VIEW_PAGE_PROPERTIES);
		waitForElementPresent(PERMISSION_SETTINGS);
		click(PERMISSION_SETTINGS);
		waitForElementPresent(ELEMENT_CHECKBOX_PUBLIC_MODE);
		uncheck(ELEMENT_CHECKBOX_PUBLIC_MODE);
		waitForElementPresent(ELEMENT_ADD_PERMISSION_BUTTON);
		//		click(ELEMENT_ADD_PERMISSION_BUTTON);
		setViewPermissions(PLATFORM_ADMIN_DATA_GROUP, "*");
		save();
		pause(500);
		click(FINISH_ICON);

		//Delete data
		goToApplicationRegistry();
		deleteCategoryAtManageApplications(CATE_NAME_01, true);

		//Delete user
		goToUsersAndGroupsManagement();
		deleteUser(DATA_USER_NAME_01);
	}

	//Change access right on category from limited by group(s) to be public
	@Test()
	public void test04_ChangeAccessRightFromLimitedToPublic()
	{
		//Add new user belongs to group Plaform/Administrator with membership is member 
		goToNewStaff();
		addNewUserAccount(DATA_USER_NAME_02, DATA_PASSWORD, DATA_PASSWORD, DATA_FIRST_NAME, DATA_LAST_NAME, DATA_EMAIL04, DATA_FIRST_NAME, DATA_LANGUAGE, true);

		//Go to user and group management page
		goToUsersAndGroupsManagement();

		//Select group tab
		chooseGroupTab();

		//Select group
		selectGroup(PLATFORM_ADMIN_DATA_GROUP);

		//Add user to group
		addUsersToGroup(DATA_USER_NAME_02, "manager", false, true);

		//Go to Application Registry page > setting access right user
		goToApplicationRegistry();
		waitForElementPresent(CATEGORIES_AREA);
		goToEditPageEditor();
		waitForElementPresent(VIEW_PAGE_PROPERTIES);
		click(VIEW_PAGE_PROPERTIES);
		waitForElementPresent(PERMISSION_SETTINGS);
		click(PERMISSION_SETTINGS);
		waitForElementPresent(ELEMENT_CHECKBOX_PUBLIC_MODE);
		makeItPublic(false);
		setViewPermissions(PLATFORM_ADMIN_DATA_GROUP, MANAGER_DATA_MEMBER_SHIP);
		save();
		click(FINISH_ICON);
		pause(500);
		//Go to Application Registry page 
		//		goToApplicationRegistry();

		//Add new category
		Map<String, String> permissions = new HashMap<String, String>();
		permissions.put("Organization", "*");
		addNewCategoryAtManageApplications(CATE_NAME_02, CATE_DISP_NAME_02, "", false, permissions, true);

		//Sign out and Sign in as added user
		signOut();
		driver.get(baseUrl);
		signIn(DATA_USER_NAME_02, DATA_PASSWORD);
		//Go to Application Registry page check no Catetory CATE_NAME_02
		goToApplicationRegistry();
		waitForElementPresent(CATEGORIES_AREA);
		goToEditPageEditor();
		waitForElementNotPresent(CATE_NAME_02);
		click(FINISH_ICON);
		pause(500);
		//Sign out and Sign in as root user
		signOut();
		driver.get(baseUrl);
		signIn("john", "gtn");
		goToApplicationRegistry();
		//Edit permission of added Category from Limited --> public
		editCategoryAtManageApplications(CATE_NAME_02, CATE_DISP_NAME_02, "", true, null, false);

		//Sign out and Sign in as added user to check can see category
		signOut();
		driver.get(baseUrl);
		signIn(DATA_USER_NAME_02, DATA_PASSWORD);
		//Verify added user can see above edited Category 
		goToApplicationRegistry();
		waitForElementPresent(CATEGORIES_AREA);
		goToEditPageEditor();
		waitForElementPresent(CATE_NAME_02);

		//Log in as root to edit category
		//Sign out and Sign in as root
		signOut();
		driver.get(baseUrl);
		signIn("root", "gtn");

		//Go to Application Registry page 
		goToApplicationRegistry();

		//Verify root can see above edited Category 
		waitForElementPresent(CATE_NAME_02);

		//Delete data
		deleteCategoryAtManageApplications(CATE_NAME_02, true);

		//Delete user
		goToUsersAndGroupsManagement();
		deleteUser(DATA_USER_NAME_02);
	}

	@AfterMethod()
	public void afterTest() throws Exception
	{
		driver.quit();
	}
}