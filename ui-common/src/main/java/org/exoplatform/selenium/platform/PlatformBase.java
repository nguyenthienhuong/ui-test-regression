package org.exoplatform.selenium.platform;

import static org.exoplatform.selenium.TestLogger.debug;
import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.*;
import java.util.ArrayList;
import java.util.List;

import org.exoplatform.selenium.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class PlatformBase extends TestBase {
	/*
	 * Default Page - http://localhost:8080/portal/default/
	 * */
	public static final String ELEMENT_GO_TO_PORTAL = "//a[text()='Login to the ACME social intranet']";
	public static final By ELEMENT_GO_TO_ACME = By.linkText("Login to the ACME website");
	/*
	 * Intranet
	 * */
	public static final String ELEMENT_SIGN_IN_LINK = "//b[contains(text(),'Sign in')]";
	
	
	/*
	 * Log in Form - Sign-out 
	 */
	public static final String ELEMENT_INPUT_USERNAME = "//input[@name='username']";
	public static final String ELEMENT_INPUT_PASSWORD = "//input[@name='password']";
	
	/*
	 * Navigation Bar /Administration Bar
	 */
	//My site
	public static final By ELEMENT_MY_SITE = By.linkText("My Sites");
	public static final By ELEMENT_ACME = By.linkText("acme");
	public static final By ELEMENT_OVERVIEW = By.linkText("Overview");
	public static final By ELEMENT_NEWS = By.linkText("News");
	
	// My spaces
	// Dashboard
	// Edit
	public static final By ELEMENT_MENU_EDIT_LINK = By.linkText("Edit");
	public static final By ELEMENT_MENU_PAGE_LINK = By.linkText("Page");
	public static final By ELEMENT_EDIT_MODE = By.xpath("//a[@class='ItemIcon QuickEditUnchecked']");
	public static final By ELEMENT_MENU_ADD_PAGE_LINK = By.linkText("Add Page");
	public static final By ELEMENT_MENU_SEO_LINK = By.xpath("//a[@title='SEO Management']");
	
	//Setting Icon
	public static final String ELEMENT_LINK_SETUP ="//img[@alt='Setup']";
	public static final String ELEMENT_LINK_USERS ="//a[text()='Users']";
	public static final String ELEMENT_LINK_ADD_USERS="//a[text()='Add Users']";
	public static final String ELEMENT_LINK_PORTAL = "//a[text()='Portal']";
	public static final String ELEMENT_LINK_SITE   = "//a[text()='Sites']";
	public static final String ELEMENT_LINK_GROUP = "//a[text()='Group Sites']";
	public static final String ELEMENT_LINK_PAGES   = "//a[text()='Pages']";
	public static final By ELEMENT_MENU_CONTENT_LINK = By.linkText("Content");
	public static final By ELEMENT_MENU_SITE_EXPLORER = By.linkText("Sites Explorer");
	public static final By ELEMENT_LINK_CONTENT_ADMIN = By.linkText("Content administration");
//	public static final By ELEMENT_LINK_CONTENT=By.linkText("Content");
	
	/* Username link -BEGIN */
	//My Account form [Username] -> My Account
	public static final String ELEMENT_CHANGE_PASSWORD_TAB = "//a[text()='Change Password' and @class='Icon ChangePass']";
	public static final String ELEMENT_ACCOUNT_PROFILE_TAB = "//a[text()='Account Profiles' and @class='Icon AccountProfiles']";
	//Account Profile Tab
	
	//Change PasswordTab
	public static final String ELEMENT_INPUT_CURRENTPASSWORD = "//input[@name='currentpass']";
	public static final String ELEMENT_INPUT_NEW_PASSWORD_MYACCOUNT = "//input[@id='newpass']";
	public static final String ELEMENT_INPUT_NEW_CONFIRM_PASSWORD_MYACCOUNT = "//input[@id='confirmnewpass']";
	//End My Account Form

	//Add user Form - (Setting -> User -> add User)
	//Account setting
	public static final String ELEMENT_ACCOUNT_SETTING_TAB = "//div[text()='Account Settings' and @class='MiddleTab']";
	public static final String ELEMENT_INPUT_CONFIRM_PASSWORD = "//input[@id='Confirmpassword']";
	public static final String ELEMENT_INPUT_NEW_PASSWORD = "//input[@id='newPassword']";
	public static final String ELEMENT_INPUT_NEW_CONFIRM_PASSWORD = "//input[@id='confirmPassword']";
	public static final String ELEMENT_INPUT_FIRSTNAME = "//input[@id='firstName']";
	public static final String ELEMENT_INPUT_LASTNAME = "//input[@id='lastName']";
	public static final String ELEMENT_INPUT_EMAIL = "//input[@id='email']";
	//User Profile
	public static final String ELEMENT_USER_PROFILE_TAB = "//div[text()='User Profile' and @class='MiddleTab']";	
	public static final String ELEMENT_INPUT_USER_NAME_GIVEN = "//input[@id='user.name.given']";
	public static final String ELEMENT_SELECT_USER_LANGUAGE = "//select[@name='user.language']";
	//End User Profile
	//End - Add User Form
	//Setting -> user -> Groups and roles
	public static final String ELEMENT_GROUP_AND_ROLE_LINK = "//a[contains(text(),'Groups and Roles')]";
		
	//Sign-out
	public static final String ELEMENT_ACCOUNT_NAME_LINK = "//a[@class='TBIcon']";
	public static final String ELEMENT_SIGN_OUT_LINK = "//a[@class='LogoutIcon']";
	/* Username link - END*/
	
	/*
	 * Context menu
	 * */
	public static final By ELEMENT_CUT_NODE = By.xpath("//a[contains(text(),'Cut')]"); 
	public static final By ELEMENT_PASTE_NODE = By.xpath(".//*[@id='NavigationNodePopupMenu']//a[@class='ItemIcon PasteNode16x16Icon']");
    public static final By ELEMENT_COPY_NODE = By.xpath("//a[contains(text(),'Copy')]");
	
	/*
	 * User and Group Management
	 * */
    //User Management TAB
    public static final String ELEMENT_USER_MANAGEMENT = "//div[@class='UserManagementIcon']/..";
    public static final String ELEMENT_USER_DELETE_ICON ="//div[@id='UIListUsersGird']//div[text()='${username}']/../..//img[@class='DeleteUserIcon']";
	public static final String ELEMENT_INPUT_SEARCH_USER_NAME = "//input[@name='searchTerm']";
	public static final String ELEMENT_SEARCH_ICON_USERS_MANAGEMENT = "//form[@id='UISearchForm']/div[2]/a";
	
	//Group Management TAB
	public static final String ELEMENT_GROUP_MANAGEMENT_TAB = "//div[@class='GroupManagementIcon']/..";
	public static final String ELEMENT_GROUP_ADD_NEW_ICON = "//div[@id='UIOrganizationPortlet']//div[@class='TitleBar']/a[@class='TreeActionIcon AddGroupIcon']";
	public static final String ELEMENT_GROUP_REMOVE_ICON = "//div[@id='UIOrganizationPortlet']//div[@class='TitleBar']/a[@class='TreeActionIcon RemoveGroupIcon']";
	public static final String ELEMENT_GROUP_EDIT_ICON = "//div[@id='UIOrganizationPortlet']//div[@class='TitleBar']/a[@class='TreeActionIcon EditGroupIcon']";
	
	//Add Group Form
	public static final String ELEMENT_INPUT_GROUP_NAME = "//input[@name='groupName']";
	public static final String ELEMENT_INPUT_LABEL = "//input[@id='label']";
	public static final String ELEMENT_TEXTAREA_DESCRIPTION = "//textarea[@id='description']";
	
//	public static final String ELEMENT_GROUP_TO_SELECT_LINK = "//a[contains(@class, 'NodeIcon') and @title='${group}']";
//	public static final String ELEMENT_GROUP_SELECTED = "//a[@class='NodeIcon PortalIcon NodeSelected' and @title='${group}']";
	public static final String ELEMENT_GROUP_SEARCH_USER_ICON = "//form[@id='UIGroupMembershipForm']/div[2]/div/table/tbody/tr[1]/td[2]/a";
	public static final String ELEMENT_GROUP_SEARCH_POPUP_ADD_ICON = "//form[@id='UIUserSelector']//div[@class='UIAction']//a[@class='ActionButton LightBlueStyle']";
	public static final String ELEMENT_SELECT_MEMBERSHIP = "//select[@name='membership']";
	public static final String ELEMENT_GROUP_USER_IN_TABLE = "//div[@class='UIUserInGroup']//div[@title='${username}']";
	
	//Membership Management
	public static final String ELEMENT_TAB_MEMBERSHIP_MANAGEMENT = "//div[@class='MembershipManagementIcon']/..";
	public static final String ELEMENT_MEMBERSHIP_EDIT_ICON = "//div[@class='UIListMembershipType']//table//tr/td/div[text()='${membership}']/../../td[5]//img[@class='EditMembershipIcon']";
	public static final String ELEMENT_MEMBERSHIP_DELETE_ICON = "//div[@class='UIListMembershipType']//table//tr/td/div[text()='${membership}']/../../td[5]//img[@class='DeleteMembershipIcon']";
	public static final String ELEMENT_NEXT_PAGE_ICON = "//a[@title='Next Page']";
	public static final String ELEMENT_INPUT_NAME = "//input[@id='name']";
	
	/*
	 * Manage Account
	 * */
	public static final String ELEMENT_SIGN_IN_CONFIRM_BUTTON = "//form[@id='UIPortalComponentLogin']//div[@class='UIAction']/*";
	public static final String ELEMENT_SELECT_SEARCH_OPTION = "//select[@name='searchOption']";
	public static final String ELEMENT_USER_EDIT_ICON = "//div[@id='UIListUsersGird']/table//tr/td/div[text()='${username}']/../../td[5]//img[@class='ViewUserInfoIcon']";
	public static final String ELEMENT_SEARCH_ICON_REGISTER = "//img[@class='SearchIcon']";
	public static final String ELEMENT_ADD_NEW_PORTAL_LINK = "//a[text()='Add New Portal']";	
	public static final String ELEMENT_CHECKBOX_SHOW_INFO_BAR_BY_DEFAULT = "//input[@name='showInfobar']";
	public static final String ELEMENT_PORTAL_IN_LIST = "//td[@class='Content']/div[@class='Label' and text()='${portalName}']";
	public static final String ELEMENT_PORTAL_DELETE_ICON = "//div[@id='UISiteManagement']//table//tr/td/div[text()='${portalName}']/../../td[2]//a[@class='DeleteIcon']";
	public static final String ELEMENT_PORTAL_EDIT_ICON = "//td[@class='Content']/div[@class='Label' and text()='${portalName}']/../../td[3]/a[@class='EditNavIcon'][2]";
	public static final String ELEMENT_EDIT_FIRST_PORTAL_CONFIG = "//div[@id='UISiteManagement']//a[@class='EditNavIcon'][2]";
	public static final String ELEMENT_SWITCH_VIEW_MODE_PORTAL = "//a[text()='Switch View Mode']";
	
	/* Add New Portal Form */
	//Portal Setting TAB
	public static final String ELEMENT_SELECT_LOCALE = "//select[@name='locale']";
	public static final String ELEMENT_SELECT_SKIN 	 = "//select[@name='skin']";
	
	//Property TAB
	public static final String ELEMENT_SELECT_SESSION_ALIVE= "//select[@name='sessionAlive']"; 
	public static final String ELEMENT_PROPERTIES_TAB = "//div[text()='Properties' and @class='MiddleTab']";
	
	//Permission Setting TAB
	public static final String ELEMENT_PERMISSION_SETTING_TAB= "//div[text()='Permission Settings' and @class='MiddleTab']";
	public static final String ELEMENT_CHECKBOX_PUBLIC_MODE = "//input[@name='publicMode']";
	public static final String ELEMENT_LINK_EDIT_PERMISSION = "//a[text()='Edit Permission Settings']";
	public static final String ELEMENT_SELECT_ACCESS_MEMBERSHIP_ITEM = "//a[text()='${membership}']";
	public static final String ELEMENT_SELECTED_ACCESS_PERMISSION_GROUP = "//div[@id='PermissionGrid']/table/tbody//div[text()='/${groupId}']";
	public static final String ELEMENT_SELECTED_ACCESS_PERMISSION_MEMBERSHIP = "//div[@id='PermissionGrid']/table/tbody//div[text()='${membership}']";
	public static final String ELEMENT_ADD_PERMISSION_BUTTON = "//a[text()='Add Permission']";
	public static final String ELEMENT_SELECT_EDIT_MEMBERSHIP_ITEM = "//div[@id='UIPermissionSelector']//a[text()='${membership}']";
	public static final String ELEMENT_SELECTED_EDIT_PERMISSION_GROUP = "// div[@class='SelectedPermissionInfo']/div[2]/div[.='/${groupId}']";
	public static final String ELEMENT_SELECTED_EDIT_PERMISSION_MEMBERSHIP = "//div[@class='SelectedPermissionInfo']/div[3]/div[.='${membership}']";
	public static final String ELEMENT_SELECT_PERMISSION_BUTTON = "//a[text()='Select Permission']";
	public static final String ELEMENT_SELECT_ACCESS_GROUP_ITEM = ".//*[@id='ListPermissionSelector']//a[@title='${group}']";
	public static final String ELEMENT_SELECT_EDIT_GROUP_ITEM = "//div[@id='UIPermissionSelector']//a[text()='${group}']";
	public static final String ELEMENT_SELECT_EDIT_PORTAL_CONFIG = "//div[@id='UISiteManagement']//table//tr/td/div[text()='${portalName}']/../../td[2]//a[@class='EditPortIcon']";
	
	////
	public static final String ELEMENT_PAGINATOR_PAGE_LINK = "//a[contains(@class, 'Number') and text()='${number}']";
	public static final String ELEMENT_PAGINATOR_TOTAL_NUMBER = "//a[@class='PagesTotalNumber']";
	public static final String ELEMENT_PAGINATOR_NEXT_ICON = "//a[@class='Icon NextPageIcon']";
	public static final String ELEMENT_PAGINATOR_SELECTED_PAGE = "//a[@class='Number PageSelected' and text()='${number}']";
	public static final String ELEMENT_MESSAGE_TEXT = "//li[@class='MessageContainer']/span[contains(@class, 'PopupIcon')]";
	public static final String ELEMENT_MESSAGE_DIALOG_CLOSE_ICON_IE = ELEMENT_MESSAGE_TEXT + "/../../../../../..//a";
	public static final String ELEMENT_MESSAGE_DIALOG_CLOSE_ICON = "//div[contains(@class, 'UIPopupWindow') and contains(@style, 'visibility: visible')]//span[text()='Messages']/..//a[@class='CloseButton']";

	/*
	 * Page Management
	 * */
	public static final String ELEMENT_ADD_NEW_PAGE_LINK = "//a[text()='Add New Page']";
	public static final String ELEMENT_INPUT_TITLE = "//input[@id='title']";
	public static final String ELEMENT_SELECT_OWNER_TYPE = "//select[@name='ownerType']";
	public static final String ELEMENT_INPUT_SEARCH_TITLE = "//input[@id='pageTitle']";
	public static final String ELEMENT_PAGE_MANAGEMENT_SEARCH_BUTTON = "//form[@id='UIPageSearchForm']/div[2]/a[@class='SearchIcon']";
	public static final String ELEMENT_PAGE_EDIT_ICON = "//div[@id='UIVirtualList']//table//tr/td/div[contains(@title, '${page}')]/../../td[5]//img[@class='EditInfoIcon']";
	public static final String ELEMENT_PAGE_DELETE_ICON = "//div[@id='UIVirtualList']//table//tr/td/div[contains(@title, '${page}')]/../../td[5]//img[@class='DeleteIcon']";
	/* Add New Page Form */
	public static final String ELEMENT_CHECKBOX_MAX_WINDOWS = "//input[@id='showMaxWindow']";
	public static final String ELEMENT_LINK_EDITOR = "//a[@class='EditorIcon TBIcon' and text() = 'Edit']";
	public static final String ELEMENT_LINK_EDITOR_PAGE = "//a[text()='Page']";
	public static final String ELEMENT_LINK_EDITOR_ADD_PAGE = "//a[text()='Add Page']";	
	public static final String ELEMENT_INPUT_NODE_NAME = "//input[@id='pageName']";
	public static final String ELEMENT_CHECKBOX_EXTENDED_LABEL_MODE = "//input[@name='switchmode']";
	public static final String ELEMENT_LINK_EDITOR_PAGE_LAYOUT="//a[@class='ItemIcon EditPageIcon' and text()='Layout']";

	public static final String ELEMENT_SELECT_LANGUAGE = "//select[@name='languages']";
	public static final String ELEMENT_INPUT_PAGE_DISPLAY_NAME = "//input[@id='pageDisplayName']";
	public static final String ELEMENT_PAGE_EDITOR_NEXT_STEP = "//div[@class='ActionBar']//a[text()='Next']";
	public static final String ELEMENT_EDIT_PAGE_CATEGORY_MENU = "//a[@class='TabLabel' and @title='${categoryLabel}']";
	public static final String ELEMENT_EDIT_PAGE_PAGE = "//div[@id='UIPage']";
	public static final String ELEMENT_PORTLET_LABEL = "//div[@class='CPortletLayoutDecorator' and contains(text(), '${portletName}')]";	
	public static final String ELEMENT_PAGE_FINISH_BUTTON = "//div[@id='UIPageEditor']//a[@title='Finish']";
	public static final By ELEMENT_PAGE_EDIT_FINISH = By.xpath("//a[@title='Finish']");
	public static final By ELEMENT_PAGE_CLOSE = By.xpath("//a[@title='Abort']");

	//PortalNavigation - http://localhost:8080/portal/g/:platform:administrators/portalnavigation
	public static final String ELEMENT_NODE_LINK = "//div[@id='UINavigationNodeSelector']//a[@title='${nodeLabel}']";
	public static final String ELEMENT_EDIT_NAVIGATION = "//div[@class='Label' and text()='${navigation}']/../../td[2]//a[@class='EditNavIcon']";
	public static final String ELEMENT_ADD_NODE_LINK = "//a[text()='Add Node']";
	public static final String ELEMENT_PAGE_SELECTOR_TAB = "//div[text()='Page Selector' and @class='MiddleTab']";
//	public static final String ELEMENT_INPUT_LOCALIZED_LABEL = "//input[@id='i18nizedLabel']";
	public static final String ELEMENT_INPUT_PAGE_NAME = "//input[@id='pageName']";
	public static final String ELEMENT_INPUT_PAGE_TITLE = "//input[@id='pageTitle']";
	public static final String ELEMENT_CREATE_PAGE_LINK = "//a[text()='Create Page']";
	public static final String ELEMENT_SEARCH_SELECT_PAGE_LINK = "//a[text()='Search and Select Page']";
	public static final String ELEMENT_CLEAR_PAGE_LINK = "//a[text()='Clear Page']";

	public static final String ELEMENT_SELECT_HOME_PAGE = "//div[@id='UIRepeater']//table//tbody/tr/td[5]/div[@class='ActionContainer']/img";
	public static final String ELEMENT_NAVIGATION_HOME_NODE = "//div[@class='HomeNode']";				 
	public static final String ELEMENT_NODE_ADD_NEW_TOP_NODE = "//div[@id='UINavigationNodeSelectorPopupMenu']/div[@class='UIContextMenuContainer']//a[@class='ItemIcon AddNode16x16Icon']";
	public static final String ELEMENT_NODE_ADD_NEW = "//div[@id='NavigationNodePopupMenu']/div[@class='UIContextMenuContainer']//a[@class='ItemIcon AddNode16x16Icon']";
	public static final String ELEMENT_NODE_DELETE = "//div[@id='NavigationNodePopupMenu']/div[@class='UIContextMenuContainer']//a[@class='ItemIcon DeleteNode16x16Icon']";
	public static final String ELEMENT_NODE_EDIT = "//div[@id='NavigationNodePopupMenu']/div[@class='UIContextMenuContainer']//a[@class='ItemIcon EditSelectedNode16x16Icon']";
//	public static final String ELEMENT_NAVIGATION_NODE_AREA= "//div[@class='Node']"; 
	/*
	 * END Page Management
	 * */
	
	/*
	 * General
	 * */
	public static final By ELEMENT_SAVE_CLOSE_BUTTON = By.linkText("Save & Close");
//	public static final By ELEMENT_SAVE_CLOSE_BUTTON = By.xpath("//a[text()='Save And Close']");
	public static final By ELEMENT_OK_BUTTON = By.xpath("//a[text()='OK']");
	public static final By ELEMENT_APPLY_BUTTON = By.linkText("Apply");
//	public static final String ELEMENT_SAVE_BUTTON 	 = "//a[text()='Save']";
	public static final By ELEMENT_SAVE_BUTTON = By.linkText("Save");
//	public static final String ELEMENT_CLOSE_BUTTON  = "//a[text()='Close']";
	public static final String ELEMENT_CANCEL_BUTTON = "//a[text()='Cancel']";
	public static final By ELEMENT_CLOSE_BUTTON = By.linkText("Close");
	
	//Account Portlet
	public static final By ELEMENT_REGISTER_ACCOUNT_PORTLET = By.className("PortletLayoutDecorator");
	public static final By ELEMENT_EDIT_ACCOUNT_PORTLET_ICON = By.xpath("//a[@title='Edit Portlet']");
	public static final By ELEMENT_CHECK_BOX_USE_CAPTCHA = By.id("useCaptcha");
	public static final By ELEMENT_EDIT_LAYOUT_FINISH_BUTTON = By.xpath("//div[@id='UIPortalComposer']//a[@class='EdittedSaveButton']");
	public static final By ELEMENT_PAGE_FINISH_BUTTON_INFRENCH = By.xpath("//div[@id='UIPageEditor']//a[@title='Terminer']");
	public static final By ELEMENT_EDIT_ACCOUNT_PORTLET_ICON_INFRENCH = By.xpath("//a[@title='Editer la Portlet']");
	public static final By ELEMENT_ERROR_ICON=By.xpath("//span[@class='PopupIcon ErrorMessageIcon']");
	public static final int ACTION_REPEAT = 5;
    /* End General
     * */
	
	//Content Administration / Advanced Configuration 
	public static final By ELEMENT_ADVANCED_CONFIGURATION_TAB = By.xpath("//div[@class = 'TabLabel' and @title = 'Advanced Configuration']");
	public static final By ELEMENT_MANAGE_LOCK_TAB = By.xpath("//div[@class = 'MiddleTab' and text() = 'Manage Lock']");
	public static final By ELEMENT_MANAGE_LOCKS = By.linkText("Manage Locks");
	
	//Edit In-line a SCV
	public static final By ELEMENT_PREFERENCE_TITLE=By.xpath("//span[contains(text(),'Content Detail Preferences')]");
	public static final By ELEMENT_CONTAINER_CONTENT = By.xpath("//div[@class='NavigationContainer']");
	public static final By ELEMENT_BUTTON_BACK = By.xpath("//a[@class='URLBackToButton']");

	//Set view permissions for portal
	public static void setViewPermissions(String groupId, String membership) {
		String membershipToSelect = ELEMENT_SELECT_ACCESS_MEMBERSHIP_ITEM.replace("${membership}", membership);
		//String selectedGroup = ELEMENT_SELECTED_ACCESS_PERM_GROUP.replace("${groupId}", groupId.replace(" ", "-").toLowerCase());
		String selectedMembership = ELEMENT_SELECTED_ACCESS_PERMISSION_MEMBERSHIP.replace("${membership}", membership);

		info("--Setting view permission to " + groupId + ", " + membership + "--");
		String[] groups = groupId.split("/");
		pause(500);
		click(ELEMENT_ADD_PERMISSION_BUTTON);
		waitForTextPresent("Browse and select a group");
		for (String group : groups) {
			String groupToSelect = ELEMENT_SELECT_ACCESS_GROUP_ITEM.replace("${group}", group);
			click(groupToSelect);
		}
		pause(500);
		click(membershipToSelect);
		pause(500);
		waitForTextNotPresent("Permission Selector");
		//waitForAndGetElement(selectedGroup);
		waitForAndGetElement(selectedMembership);
	}

	//Set edit permissions for portal
	public static void setEditPermissions(String groupId, String membership) {
		String membershipToSelect = ELEMENT_SELECT_EDIT_MEMBERSHIP_ITEM.replace("${membership}", membership);
		//String selectedGroup = ELEMENT_SELECTED_EDIT_PERM_GROUP.replace("${groupId}", groupId.replace(" ", "-").toLowerCase());
		String selectedMembership = ELEMENT_SELECTED_EDIT_PERMISSION_MEMBERSHIP.replace("${membership}", membership);

		info("--Setting edit permission to " + groupId + ", " + membership + "--");
		String[] groups = groupId.split("/");
		click(ELEMENT_SELECT_PERMISSION_BUTTON);
		pause(500);
		waitForTextPresent("Permission Selector");
		for (String group : groups) {
			String groupToSelect = ELEMENT_SELECT_EDIT_GROUP_ITEM.replace("${group}", group);
			click(groupToSelect);
		}
		click(membershipToSelect);
		waitForTextNotPresent("Permission Selector");
		//waitForAndGetElement(selectedGroup);
		waitForAndGetElement(selectedMembership);
	}

	//Define a type of page
	public static enum PageType {
		PORTAL, GROUP;
	}

	//Link to Edit a navigation
	public static void editNavigation(String currentNavigation) {
		String navigation = ELEMENT_EDIT_NAVIGATION.replace("${navigation}", currentNavigation);
		click(navigation);
		waitForTextPresent("Navigation Management");
	}

	//Close message pop-up
	public static void closeMessageDialog() {
		info("--Closing message dialog--");
		if (ieFlag) {
			click(ELEMENT_MESSAGE_DIALOG_CLOSE_ICON_IE);
		} else {
			click(ELEMENT_MESSAGE_DIALOG_CLOSE_ICON);
		}
	}

	//Copy value from Source and paste to Target
	public static void copyPaste(Object Source, String value, Object Target){ 	
		WebElement element = waitForAndGetElement(Source);
		element.sendKeys(value);
		actions.doubleClick(element).perform();
		element.sendKeys(Keys.LEFT_CONTROL + "a");
		element.sendKeys(Keys.LEFT_CONTROL + "c");
		pause(3000);
		WebElement b = waitForAndGetElement(Target);
		b.sendKeys(Keys.LEFT_CONTROL + "v");
	}

	//Go to the desired locator
	public static void goToPage(String verification, String... navigation) {
		String page = makeLink(navigation[navigation.length - 1]);
		boolean needToBeVerified = true;

		List<String> navigationList = new ArrayList<String>();

		for (int i = 0; i < (navigation.length - 1); i++) {
			String node = navigation[i];
			node = makeLink(node);
			navigationList.add(node);
		}

		try {
			for (String node : navigationList) {
				if (ieFlag) {
					actions.moveToElement(getElement(node));
				} else {
					mouseOver(node, false);
				}
			}
			mouseOverAndClick(page);
		} catch (StaleElementReferenceException e) {
			debug("StaleElementReferenceException, Retrying... :" + loopCount + "time(s)");
			checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			goToPage(verification, navigation);
			needToBeVerified = false;
		} finally {
			loopCount = 0;
		}

		if (verification != null && needToBeVerified) {
			pause(500);
			verifyLocation(verification, navigationList, page);
		}
	}

	private static String makeLink(String node) {
		if (!node.contains("//")) {
			String label = node;
			node = "//a[text()='" + label + "']";
		}
		return node;
	}

	private static void verifyLocation(String locator, List<String> navigation, String page) {
		info("verifyLocation, element: " + locator);
		int seconds = 0;
		if (isElementNotPresent(locator)) {
			pause(1000);
		}
		for (; isElementNotPresent(locator); seconds++) {
			if (seconds >= (DEFAULT_TIMEOUT/WAIT_INTERVAL) ) {
				Assert.fail("Timeout at goToPage");
			}
			pause(500);
			try {
				for (String node : navigation) {
					if (ieFlag) {
						actions.moveToElement(getElement(locator));
					} else {
						mouseOver(node, false);
					}
				}
				mouseOverAndClick(page);
			} catch (StaleElementReferenceException e) {
				debug("StaleElementReferenceException, Retrying... :" + loopCount + "time(s)");
				checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
				verifyLocation(locator, navigation, page);
				break;
			} finally {
				loopCount = 0;
			}
		}
		seconds = 0;
	}

	public static void usePaginator(String locator, String exceptionMessage) {
		String page1 = ELEMENT_PAGINATOR_PAGE_LINK.replace("${number}", "1");

		click(page1);
		pause(500);
		int totalPages = isElementPresent(ELEMENT_PAGINATOR_TOTAL_NUMBER) ? Integer.valueOf(getText(ELEMENT_PAGINATOR_TOTAL_NUMBER)) : 1;
		int i = 1;
		while (isElementNotPresent(locator)) {
			if (i == totalPages) {
				Assert.fail(exceptionMessage);
			}
			click(ELEMENT_PAGINATOR_NEXT_ICON);
			waitForAndGetElement(ELEMENT_PAGINATOR_SELECTED_PAGE.replace("${number}", String.valueOf((++i))));
			pause(500);
		}
	}

	public static void save() {
		waitForAndGetElement(ELEMENT_SAVE_BUTTON);
		click(ELEMENT_SAVE_BUTTON);
	}

	public static void close(){
		waitForAndGetElement(ELEMENT_CLOSE_BUTTON);
		click(ELEMENT_CLOSE_BUTTON);
	}
	
	public static void cancel(){
		waitForAndGetElement(ELEMENT_CANCEL_BUTTON);
		click(ELEMENT_CANCEL_BUTTON);
	}

	public static void saveAndClose(){
		waitForAndGetElement(ELEMENT_SAVE_CLOSE_BUTTON);
		click(ELEMENT_SAVE_CLOSE_BUTTON);
	}
	
	public static void apply(){
		waitForAndGetElement(ELEMENT_APPLY_BUTTON);
		click(ELEMENT_APPLY_BUTTON);
	}

	// Check UnexpectedError
	public static boolean checkUnexpectedError() {
		try {
			driver.findElement(ELEMENT_ERROR_ICON);
			return false;

		} catch (NoSuchElementException e) {
			return true;
		}
	}
	
	public void cutNode(By locator)	{
		for (int i =0;; i++){
			if (i > DEFAULT_TIMEOUT/WAIT_INTERVAL) {
				Assert.fail("Timeout");
			}
			rightClickOnElement(locator);
			if (waitForAndGetElement(ELEMENT_CUT_NODE,30000,0)!=null){
				debug("==Cut node " + locator + "==");
				click((ELEMENT_CUT_NODE));
				return;
			}
			pause(WAIT_INTERVAL);
		}
	}

	public void copyNode(By locator){
		for (int i =0;; i++){
			if (i > DEFAULT_TIMEOUT/WAIT_INTERVAL) {
				Assert.fail("Timeout");
			}
			rightClickOnElement(locator);
			if (isElementPresent(ELEMENT_COPY_NODE)){
				click((ELEMENT_COPY_NODE));
				return;
			}
			pause(WAIT_INTERVAL);
		}
	}

	public static void pasteNode(By locator) {
		for (int i =0;; i++){
			if (i>DEFAULT_TIMEOUT/WAIT_INTERVAL){
				Assert.fail("Timeout");
			}
			rightClickOnElement(locator);
			if (isElementPresent(ELEMENT_PASTE_NODE)){
				click(ELEMENT_PASTE_NODE);
				return;
			}
			pause(WAIT_INTERVAL);
		}
	}

	//Set to use captcha when registry a new account in public mode
	public static void setUseCaptcha(boolean useCaptcha, boolean useFrench){
		waitForTextPresent("Register Account");
		mouseOver(ELEMENT_REGISTER_ACCOUNT_PORTLET, true);
		if (useFrench){
			mouseOverAndClick(ELEMENT_EDIT_ACCOUNT_PORTLET_ICON_INFRENCH);
			waitForTextPresent("Mode d'édition");
		}else{
			mouseOverAndClick(ELEMENT_EDIT_ACCOUNT_PORTLET_ICON);
			waitForTextPresent("Edit Mode");
		}

		WebElement element = waitForAndGetElement(ELEMENT_CHECK_BOX_USE_CAPTCHA);
		if (useCaptcha){
			if(!element.isSelected()){
				check(ELEMENT_CHECK_BOX_USE_CAPTCHA);
			}
		}
		else{
			if(element.isSelected()){
				uncheck(ELEMENT_CHECK_BOX_USE_CAPTCHA);
			}
		}
		save();
		close();
		if (useFrench){
			click(ELEMENT_PAGE_FINISH_BUTTON_INFRENCH);
			waitForTextNotPresent("Editeur de page");
		}else{
			click(ELEMENT_PAGE_FINISH_BUTTON);
			waitForTextNotPresent("Page Editor");
		}
	}
	
	public static void next(){
		waitForAndGetElement(ELEMENT_NEWPAGE_NEXT_BUTTON);
		click(ELEMENT_NEWPAGE_NEXT_BUTTON);	
	}
}
