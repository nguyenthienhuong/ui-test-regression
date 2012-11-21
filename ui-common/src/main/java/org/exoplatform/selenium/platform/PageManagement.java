package org.exoplatform.selenium.platform;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;
import static org.exoplatform.selenium.platform.NavigationManagement.deleteNode;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class PageManagement extends PlatformBase {
	//Add a new page in manage pages
	public static void addNewPageAtManagePages(PageType type, String pageName, String pageTitle, boolean publicMode, 
			Map<String, String> permissions, String groupId, String membership ){

		click(ELEMENT_ADD_NEW_PAGE_LINK);
		waitForTextPresent("Page Settings");	
		switch (type){

		case PORTAL:
			select(ELEMENT_SELECT_OWNER_TYPE, "portal");
			break;
		case GROUP:	
			select(ELEMENT_SELECT_OWNER_TYPE, "group");
			break;
		default:
			break;
		}		
		type(ELEMENT_INPUT_NAME, pageName, true);
		type(ELEMENT_INPUT_TITLE, pageTitle, true);		
		
		//showMaxWindow
		check(ELEMENT_CHECKBOX_MAX_WINDOWS);
		click(ELEMENT_PERMISSION_SETTING_TAB);	
		WebElement element = waitForAndGetElement(ELEMENT_CHECKBOX_PUBLIC_MODE);		
		if (publicMode & !element.isSelected()) {
			waitForAndGetElement(ELEMENT_ADD_PERMISSION_BUTTON);
			check(ELEMENT_CHECKBOX_PUBLIC_MODE);
			waitForElementNotPresent(ELEMENT_ADD_PERMISSION_BUTTON);
		} else if (publicMode & element.isSelected()){
			waitForElementNotPresent(ELEMENT_ADD_PERMISSION_BUTTON);
		} else {
			for (String key : permissions.keySet()) {
				setViewPermissions(key, permissions.get(key));
			}
		}		
		click(ELEMENT_LINK_EDIT_PERMISSION);
		setEditPermissions(groupId, membership);
		save();
		pause(1000);
		searchPageByTitle(type, pageTitle);
	}

	//Edit a page at Manage Pages
	public static void editPageAtManagePages(PageType type, String pageTitle){
		String pageEditIcon = ELEMENT_PAGE_EDIT_ICON.replace("${page}", pageTitle);
		searchPageByTitle(type, pageTitle);
		click(pageEditIcon);
		pause(1000);
	}

	//Delete a page
	public static void deletePage(PageType type, String pageTitle, int...wait){
		int waitTime = wait.length > 0 ? wait[0] : DEFAULT_TIMEOUT;
		String pageDeleteIcon = ELEMENT_PAGE_DELETE_ICON.replace("${page}", pageTitle);
		searchPageByTitle(type, pageTitle);
		click(pageDeleteIcon);
		waitForConfirmation("Are you sure to delete this page?");
		waitForMessage("No result found.",waitTime);
		closeMessageDialog();
		waitForTextNotPresent(pageTitle);
		
		//		pause(1000);
	}

	// Search a page in Manage Pages
	public static void searchPageByTitle(PageType type, String pageTitle){
		type(ELEMENT_INPUT_SEARCH_TITLE, pageTitle, true);
		switch (type){
		case PORTAL:
			select(ELEMENT_SELECT_SEARCH_OPTION, "portal");
			break;
		case GROUP:
			select(ELEMENT_SELECT_SEARCH_OPTION, "group");
			break;
		default:
			break;
		}
		click(ELEMENT_PAGE_MANAGEMENT_SEARCH_BUTTON);
		pause(1000);
		waitForTextPresent(pageTitle);
	}

	// Input data for page
	public static void addNewPageEditor(String nodeName, String displayName, String language, String categoryTitle, 
			Map<String, String> portletIds, boolean extendedLabelMode){

		type(ELEMENT_INPUT_NODE_NAME, nodeName, true);
		WebElement element = waitForAndGetElement(ELEMENT_CHECKBOX_EXTENDED_LABEL_MODE);
		if (extendedLabelMode){
			Assert.assertTrue(element.isSelected());
			select(ELEMENT_SELECT_LANGUAGE, language);

		}else {
			uncheck(ELEMENT_CHECKBOX_EXTENDED_LABEL_MODE);
			type(ELEMENT_INPUT_PAGE_DISPLAY_NAME, displayName, true);
		}

		click(ELEMENT_PAGE_EDITOR_NEXT_STEP);
		waitForTextPresent("Empty Layout");
		click(ELEMENT_PAGE_EDITOR_NEXT_STEP);

		String category = ELEMENT_EDIT_PAGE_CATEGORY_MENU.replace("${categoryLabel}", categoryTitle);
		click(category);

		for (String portletId : portletIds.keySet()) {
			String elementEditPagePage = ELEMENT_EDIT_PAGE_PAGE;
			//String verification = PORTLET_LABEL.replace("${portletName}", portletIdsAndVerifications.get(portletId));
			dragAndDropToObject("//div[@id='" + portletId + "']//img", elementEditPagePage);
		}
		pause(500);
		click(ELEMENT_PAGE_FINISH_BUTTON);
		waitForTextNotPresent("Page Editor");
	}
	

	//	//search Page
	//		public boolean searchPage(String pageTitle){	
	//			actions.moveToElement(waitForAndGetElement(ELEMENT_MENU_SETUP_IMG)).build().perform();
	//			actions.moveToElement(waitForAndGetElement(ELEMENT_MENU_PORTAL_LINK_LINKTEXT)).build().perform();
	//			pause(100);
	//			click(ELEMENT_MENU_PAGE_LINK_LINKTEXT);
	//			
	////			waitForAndGetElement(ELEMENT_SEARCH_TITLEPAGE_TEXTBOX).clear();
	//			type(ELEMENT_SEARCH_TITLEPAGE_TEXTBOX, pageTitle, true);
	//			click(ELEMENT_SEARCH_PAGE_ICON);
	//			if (isElementNotPresent(ELEMENT_SEARCH_PAGE_ALERT))
	//			return true;	
	//			else return false;
	//		}
	//		
	//		//delete page
	//		public static void deletePage(){
	//			click(ELEMENT_DELETE_PAGE_ICON);
	//			acceptAlert();
	//			click(By.linkText("OK"));
	//			info("Delete page successfully");
	//		}
	//		
	public static void deletePageAtManagePageAndPortalNavigation(String pageName, boolean PageTypePortal, String portalName, boolean PageTypeGroup, String groupName){
		info("-- Deleting "+ pageName +" at Manage page and Portal Navigation--");
		goToManagePages();
		if (PageTypePortal){
			deletePage(PageType.PORTAL, pageName);
			//delete page at Portal navigation
			goToPortalSites();
			deleteNode(portalName, "", pageName, true);
		}
		if (PageTypeGroup){
			deletePage(PageType.GROUP, pageName);
			//delete page at Portal/Group navigation
			goToGroupSites();
			deleteNode(groupName, "", pageName, true);
		}
	}
}
