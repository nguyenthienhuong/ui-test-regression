package org.exoplatform.selenium.platform.exogtn.functional.portalnavigation.edit;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.platform.PlatformBase;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.platform.ManageAccount.*;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;
import static org.exoplatform.selenium.platform.PageManagement.*;
import static org.exoplatform.selenium.platform.NavigationManagement.*;

/**
 *@author VuNA2
 *@date: 18/09/2012
 **/
public class EXOGTN_PortalNavigation_EditNavigation_Node_Clone extends PlatformBase{
	
	/*-- Data for test case--*/	
	String ELEMENT_NODE_NAME = "demoPage";
	String ELEMENT_DISPLAY_NAME = "";
	String ELEMENT_LANGUAGE = "English";
	boolean EXTENDED_LABEL_MODE = true;
	PageType ELEMENT_PAGE_TYPE = PageType.PORTAL;
	
	String ELEMENT_CATEGORY_TITLE = "Content";
	String ELEMENT_CURRENT_NAVIGATION = "intranet";
	String ELEMENT_PASTE_TO_NEW_PLACE = "Forums";
	String ELEMENT_CURRENT_NODE = ELEMENT_NODE_LINK.replace("${nodeLabel}", ELEMENT_NODE_NAME);
	String ELEMENT_NODE_PASTE_TO_NEW_PLACE = ELEMENT_NODE_LINK.replace("${nodeLabel}", ELEMENT_PASTE_TO_NEW_PLACE);
	String ELEMENT_COLLABORATION_CATEGORY = ELEMENT_EDIT_PAGE_CATEGORY_MENU.replace("${categoryLabel}", "Collaboration");
	String MESSAGE_DELETE_PAGE = "Are you sure to delete this page?";
	String ELEMENT_DELETE_PAGE = ELEMENT_PAGE_DELETE_ICON.replace("${page}", ELEMENT_NODE_NAME);
	
	By ELEMENT_NODE_CLONE = By.xpath("//div[@id='NavigationNodePopupMenu']/div[@class='UIContextMenuContainer']//a[@class='ItemIcon CloneNode16x16Icon']");		
	By ELEMENT_NODE_PASTE_HOME = By.xpath("//div[@id='NavigationNodePopupMenu']/div[@class='UIContextMenuContainer']//a[@class='ItemIcon PasteNode16x16Icon']");
	By ELEMENT_NODE_EDIT_PAGE = By.xpath("//div[@id='NavigationNodePopupMenu']/div[@class='UIContextMenuContainer']//a[@class='ItemIcon EditPageNode16x16Icon']"); 
	By ELEMENT_APPLICATION_CALENDAR = By.id("Collaboration/Calendar");
	By ELEMENT_EDIT_PAGE_PAGE_BODY_COMPONENT = By.id("UIPage");
	By ELEMENT_PAGE_FINISH_BUTTON = By.xpath("//div[@id='UIPageEditor']//a[@title='Finish']");
	By ELEMENT_PORTAL_TOP_CONTAINER = By.id("PortalNavigationTopContainer");
	By ELEMENT_MY_SITES = By.linkText("My Sites");
	By ELEMENT_INPUT_SEARCH_NAME = By.id("siteName");
	
	@BeforeMethod
	public void setUpBeforeTest() throws Exception {
		initSeleniumTest();
		actions = new Actions(driver);
		driver.get(baseUrl);
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void afterTest() throws Exception {
		driver.manage().deleteAllCookies();
		driver.quit();
	}
	
	/*--Case 01 PortalNavigation/Edit/EditNavigation/Node/Clone
	 *  Check Clone node does not contain sub node
	 * --*/
	@Test
	public void test01_CheckCloneNodeDoesNotContainSubNode(){
		
		Map<String, String> ELEMENT_PORTLET_ID = new HashMap<String, String>();
		ELEMENT_PORTLET_ID.put("Content/ContentListViewerPortlet","");
		 
		info("-- Start Case 01: Check Clone node does not contain sub node --");
		
		signIn("root", "gtn");
		
		info("-- Create new page by wizard for Portal --");
		
		goToAddPageEditor();
		
		addNewPageEditor(ELEMENT_NODE_NAME, ELEMENT_DISPLAY_NAME, ELEMENT_LANGUAGE, 
				         ELEMENT_CATEGORY_TITLE, ELEMENT_PORTLET_ID, EXTENDED_LABEL_MODE);
		
		goToPortalSites();
		
		editNavigation(ELEMENT_CURRENT_NAVIGATION);
		
		info("-- Right click on one node and select Clone Node --");
		rightClickOnElement(ELEMENT_CURRENT_NODE);
		
		click(ELEMENT_NODE_CLONE);
		
		info("-- Paste a node --");
		rightClickOnElement(ELEMENT_NODE_PASTE_TO_NEW_PLACE);
		click(ELEMENT_NODE_PASTE_HOME);
		waitForTextPresent(ELEMENT_NODE_NAME);
		
		info("-- Edit page of clone node --");
		rightClickOnElement(ELEMENT_CURRENT_NODE);
		//Edit Node's Page
		click(ELEMENT_NODE_EDIT_PAGE);
		waitForTextPresent("Applications");
		//Change a page's content
		click(ELEMENT_COLLABORATION_CATEGORY);
		dragAndDropToObject(ELEMENT_APPLICATION_CALENDAR, ELEMENT_EDIT_PAGE_PAGE_BODY_COMPONENT);
		click(ELEMENT_PAGE_FINISH_BUTTON);
		save();
		waitForTextPresent(ELEMENT_CURRENT_NAVIGATION);
		
		info("-- Check content of clone node's page --");
		goToNodesPage("Forums", ELEMENT_NODE_NAME);
		waitForTextPresent("Gadget Portlet");
		captureScreen("Case_01_Content_Clone_Node_Page");
		
		//Check content of original node's page
		info("-- Check content of original node's page --");
		goToNodesPage("Home", ELEMENT_NODE_NAME);
		captureScreen("Case_01_Content_Original_Node_Page");
		
		info("-- Go to Page Management, check existing of page with title of created page --");
		goToManagePages();
		searchPageByTitle(ELEMENT_PAGE_TYPE, ELEMENT_NODE_NAME);
		//There're 2 pages with the same title
		captureScreen("Case_01_Two_Pages_With_Same_Title");
		
		info("-- Delete data after testing --");
		click(ELEMENT_DELETE_PAGE);
		waitForConfirmation(MESSAGE_DELETE_PAGE);
		boolean IsNodeExist = isTextPresent(ELEMENT_NODE_NAME);
		if (IsNodeExist){
			deletePage(ELEMENT_PAGE_TYPE, ELEMENT_NODE_NAME);		
		}
		
		info("-- Delete a node at Navigation Management --");
		goToPortalSites();
		deleteNode(ELEMENT_CURRENT_NAVIGATION, "Home", ELEMENT_NODE_NAME, true);
		deleteNode(ELEMENT_CURRENT_NAVIGATION, "Forums", ELEMENT_NODE_NAME, false);
		
		info("-- Finish Case 01: Sign Out --");
		signOut();	
	}
	
	/*--Case 02 PortalNavigation/Edit/EditNavigation/Node/Clone
	 *  Check Clone node contains sub nodes
	 * --*/
	@Test
	public void test02_CheckCloneNodeContainsSubNodes(){
		info("-- Start Case 02: Check Clone node contains sub nodes --");
		
		Map<String, String> languages = new HashMap<String, String>();
		languages.put("English", "");
		
		signIn("root", "gtn");
		
		goToPortalSites();
		
		addNodeForPortal(ELEMENT_CURRENT_NAVIGATION, "Home", false, "nodeNameTest196", true, 
			             languages, "", "pageNameTest1", "pageTitle1", true, true);
			
		addNodeForPortal(ELEMENT_CURRENT_NAVIGATION, "nodeNameTest196", false, "nodeNameTest2", true, 
	                     languages, "", "pageNameTest2", "pageTitle2", true, true);
        
		editNavigation(ELEMENT_CURRENT_NAVIGATION);
		
		info("-- Right click on one node and select Clone Node --");
		String ELEMENT_CURRENT_NODE_CASE196 = ELEMENT_NODE_LINK.replace("${nodeLabel}", "nodeNameTest196");
		rightClickOnElement(ELEMENT_CURRENT_NODE_CASE196);
		click(ELEMENT_NODE_CLONE);
		
		info("-- Paste a node --");
		rightClickOnElement(ELEMENT_NODE_PASTE_TO_NEW_PLACE);
		click(ELEMENT_NODE_PASTE_HOME);
		waitForTextPresent("nodeNameTest196");
		save();
		waitForTextPresent(ELEMENT_CURRENT_NAVIGATION);
		
		info("-- Go to Page Management, check existing of pages has link to cloned nodes --");
		goToManagePages();
		searchPageByTitle(ELEMENT_PAGE_TYPE, "pageTitle");
		captureScreen("Case_02_Cloned_Pages_With_Same_Title");
		
		info("-- Delete data after testing --");
		String[] pageTitle = new String[]{"pageTitle2", "pageTitle1", "pageTitle2", "pageTitle1"};
		for (int i = 0; i < (pageTitle.length-1); i++) {	
			String pageDeleteIcon = ELEMENT_PAGE_DELETE_ICON.replace("${page}", pageTitle[i]);
			String pageDeleteIconNext = ELEMENT_PAGE_DELETE_ICON.replace("${page}", pageTitle[i+1]);
			type(ELEMENT_INPUT_SEARCH_TITLE, pageTitle[i], true);
			click(ELEMENT_PAGE_MANAGEMENT_SEARCH_BUTTON);
			click(pageDeleteIcon);
			waitForConfirmation(MESSAGE_DELETE_PAGE);
			waitForElementNotPresent(By.xpath(pageDeleteIconNext));		
		}
		closeMessageDialog();
		deletePage(ELEMENT_PAGE_TYPE, pageTitle[3]);
		
		info("-- Delete a node at Navigation Management --");
		goToPortalSites();
		deleteNode(ELEMENT_CURRENT_NAVIGATION, "Home", "nodeNameTest196", true);
		deleteNode(ELEMENT_CURRENT_NAVIGATION, "Forums", "nodeNameTest196", false);
		
		info("-- Finish Case 02: Sign Out --");
		signOut();			
	}
	
	/*--Case 03 PortalNavigation/Edit/EditNavigation/Node/Clone
	 *  Check Clone node does not link to any page
	 * --*/
	@Test
	public void test03_CheckCloneNodeDoesNotLinkToAnyPage(){
		info("-- Start Case 03: Check Clone node does not link to any page --");
		
		signIn("root", "gtn");
		
		goToPortalSites();
		
		info("-- Create new node does not link to any page --");
		editNavigation(ELEMENT_CURRENT_NAVIGATION);
		
		click(ELEMENT_ADD_NODE_LINK);
		
		waitForTextPresent("Page Node Settings");
		
		type(ELEMENT_INPUT_NAME, "nodeNameTest197", true);
		
		save();
		
		waitForTextPresent("nodeNameTest197");
		
		save();
		waitForTextPresent(ELEMENT_CURRENT_NAVIGATION);
		
		editNavigation(ELEMENT_CURRENT_NAVIGATION);
		info("-- Right click on this node and select Clone Node --");
		String ELEMENT_CURRENT_NODE_CASE197 = ELEMENT_NODE_LINK.replace("${nodeLabel}", "nodeNameTest197");
		rightClickOnElement(ELEMENT_CURRENT_NODE_CASE197);
		click(ELEMENT_NODE_CLONE);
		
		info("-- Paste a node --");
		rightClickOnElement(ELEMENT_NODE_PASTE_TO_NEW_PLACE);
		click(ELEMENT_NODE_PASTE_HOME);
		waitForTextPresent("nodeNameTest197");
		save();
		waitForTextPresent(ELEMENT_CURRENT_NAVIGATION);
		
		info("-- Go to Page Management: No new page is created --");
		goToManagePages();
		type(ELEMENT_INPUT_SEARCH_NAME, "nodeNameTest197", true);
		click(ELEMENT_PAGE_MANAGEMENT_SEARCH_BUTTON);
		waitForTextPresent("No result found.");
		closeMessageDialog();
		
		info("-- Delete data after testing --");
		goToPortalSites();
		deleteNode(ELEMENT_CURRENT_NAVIGATION, "Home", "nodeNameTest197", true);
		deleteNode(ELEMENT_CURRENT_NAVIGATION, "Forums", "nodeNameTest197", false);
		
		info("-- Finish Case 03: Sign Out --");
		signOut();		
	}
	
	/*--Case 04 PortalNavigation/Edit/EditNavigation/Node/Clone
	 *  Check Clone system node
	 * --*/
	/*@Test
	public void test04_CheckCloneSystemNode(){
		info("-- Start Case 04:  Check Clone system node --");	
	}*/
	

	public void goToNodesPage(String parentNode, String nodePageName){
		mouseOver(ELEMENT_PORTAL_TOP_CONTAINER, true);
	    mouseOver(ELEMENT_MY_SITES, true);
	    mouseOver(By.xpath("//a[text()='" + ELEMENT_CURRENT_NAVIGATION + "']"),true);
	    mouseOver(By.xpath("//a[text()= '" + parentNode + "']"),true);
	    mouseOver(By.xpath("//a[text()= '" + nodePageName + "']"), true);
		click(By.linkText(nodePageName));
	}	
}
