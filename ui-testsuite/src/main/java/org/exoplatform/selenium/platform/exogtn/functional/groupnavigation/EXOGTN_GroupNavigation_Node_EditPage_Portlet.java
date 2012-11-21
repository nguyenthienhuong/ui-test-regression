package org.exoplatform.selenium.platform.exogtn.functional.groupnavigation;

import java.util.HashMap;
import java.util.Map;



import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.TestLogger.*;

import static org.exoplatform.selenium.platform.ManageAccount.signIn;
import static org.exoplatform.selenium.platform.ManageAccount.signOut;
import static org.exoplatform.selenium.platform.NavigationManagement.*;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;
import static org.exoplatform.selenium.platform.PageManagement.*;


/**
 *@author HangNTT
 * @date: 26/09/2012
 */
public class EXOGTN_GroupNavigation_Node_EditPage_Portlet extends PlatformBase {
	/**
	 * @param args
	 */
	By ELEMENT_EDIT_NAV_GROUP = By.xpath("//td/div[text()='Administration']/ancestor::tr/td/a[text()='Edit Navigation']");
	By UP_LEVEL = By.xpath("//a[@title='Up Level']");
	By ELEMENT_CONTENT_LIST_PORTLET = By.className("PortletLayoutDecorator");
	By ELEMENT_EDIT_ICON = By.xpath("//a[@title='Edit Portlet']");
	By ELEMENT_WINDOW_SETTINGS_TAB = By.xpath("//div[@id='UIMaskWorkspace']//div[text()='Window Settings']");
	By ELEMENT_FINISH_BUTTON = By.xpath("//a[@title='Finish']");
	By ELEMENT_EDIT_PAGE_PAGE_BODY_COMPONENT = By.id("UIPage");
	By ELEMENT_ROW_PAGE_CONFIGS = By.xpath("//a[contains(text(),'Row Page Configs')]");
	By ELEMENT_PAGE_CONFIGS = By.xpath("//div[@id='UIDropDownPageTemp']/div/div/div/div/div/div/div");
	By ELEMENT_TAB_CONTAINERS = By.xpath("//div[contains(text(),'Containers') and @class='MiddleTab']");
	By ELEMENT_ROWS_LAYOUT = By.linkText("Rows Layout");
	By ELEMENT_ONE_ROW_LAYOUT = By.id("oneRow");
	By ELEMENT_EDIT_CONTAINER_ICON = By.xpath("//div[@class='UIRowContainer']/div/div/div[2]/div/div[2]/div/a[1]");
	String ELEMENT_EDIT_PAGE_COMPONENT = "//div[@class='UIRowContainer']/div[${portletNumber}]/div";
	String ELEMENT_EDIT_CONTAINER = ELEMENT_EDIT_PAGE_COMPONENT.replace("${portletNumber}", "1");
	By ELEMENT_INPUT_WIDTH = By.id("width");
	By ELEMENT_INPUT_HEIGHT = By.id("height");

	public String EDIT_NODE_PAGE_LINK = "Edit Node's Page";
	
	@BeforeMethod()
	public void beforeTest() throws Exception {
		initSeleniumTest();
		actions = new Actions(driver);
		driver.get(baseUrl);
		driver.manage().window().maximize();
	}

	//Add New Page By Wizard
	@Test
	public void test18_CheckFinishFunctionAfterEditPorletWithSavingPage () {
		String NODE_NAME = "GROUPNAV_26_02_018"; 
		String DISPLAY_NAME = "GROUPNAV_26_02_018";		
		String LANGUAGE = "English";	
		Map<String, String> PORTLET_IDS = new HashMap<String, String>();
		PORTLET_IDS.put("Content/ContentListViewerPortlet","");
		String CATEGORY_TITLE = "Content";

		info("Main program");	  
		signIn("john", "gtn");
		//Add new page by wizard
		goToGroupSites();
		goToAddPageEditor();
		click(UP_LEVEL);   
		addNewPageEditor(NODE_NAME, DISPLAY_NAME, LANGUAGE, CATEGORY_TITLE, PORTLET_IDS, true);
		// Show form to edit portlet when edit node's page
		goToGroupSites();
		click(ELEMENT_EDIT_NAV_GROUP);
		info("Right click on new node");
		rightClickOnElement(By.linkText("GROUPNAV_26_02_018"));
		info("edit node's page");
		waitForElementPresent(By.linkText(EDIT_NODE_PAGE_LINK));
		click(By.linkText(EDIT_NODE_PAGE_LINK));
		// Edit portlet
		mouseOver(ELEMENT_CONTENT_LIST_PORTLET, true);
		click(ELEMENT_EDIT_ICON);
		// Choose Window Settings tab
		click(ELEMENT_WINDOW_SETTINGS_TAB);
		info("--Edit current title with valid value--");
		type(ELEMENT_INPUT_TITLE, "test18_ChangePortlet", true);
		saveAndClose();
		mouseOver(ELEMENT_CONTENT_LIST_PORTLET, true);
		waitForTextPresent("test18_ChangePortlet");
		//Check Finish
		click(ELEMENT_FINISH_BUTTON);
		save();
		//Go to Group Navigation
		click(ELEMENT_EDIT_NAV_GROUP);
		// Delete node
		deleteNode("Administration","GROUPNAV_26_02_018","GROUPNAV_26_02_018",true);
	}
	
	//Add New Page By Wizard
	@Test
	public void test23_CheckFinishFunctionOnEditingPageAfterEditedPagePortletLayout () {
		String NODE_NAME = "GROUPNAV_26_02_023"; 
		String DISPLAY_NAME = "GROUPNAV_26_02_023";		
		String LANGUAGE = "English";	
		Map<String, String> PORTLET_IDS = new HashMap<String, String>();
		PORTLET_IDS.put("Collaboration/Calendar","");
		String CATEGORY_TITLE = "Collaboration";
		String ELEMENT_COLLABORATION_CATEGORY = ELEMENT_EDIT_PAGE_CATEGORY_MENU.replace("${categoryLabel}", "Collaboration");
		By ELEMENT_APPLICATION_COLLABORATION_ANSWERS = By.id("Collaboration/AnswersPortlet");

		info("Main program");	  
		signIn("john", "gtn");
		//Add new page by wizard
		goToGroupSites();
		goToAddPageEditor();
		click(UP_LEVEL);   
		addNewPageEditor(NODE_NAME, DISPLAY_NAME, LANGUAGE, CATEGORY_TITLE, PORTLET_IDS, true);
		goToGroupSites();
		click(ELEMENT_EDIT_NAV_GROUP);
		info("Right click on new node");
		rightClickOnElement(By.linkText("GROUPNAV_26_02_023"));

		info("edit node's page");

		waitForElementPresent(By.linkText(EDIT_NODE_PAGE_LINK));
		click(By.linkText(EDIT_NODE_PAGE_LINK));

		//EditNodePage("GROUPNAV_26_02_023");
		info("--View layout of portal before change portlet layout--");
		captureScreen("case23_BeforeChange");
		info("--Select application tab on edit inline composer --");
		waitForTextPresent("Administration") ;
		click(ELEMENT_COLLABORATION_CATEGORY);
		dragAndDropToObject(ELEMENT_APPLICATION_COLLABORATION_ANSWERS, ELEMENT_EDIT_PAGE_PAGE_BODY_COMPONENT);
		click(ELEMENT_FINISH_BUTTON);
		save();
		mouseOver(By.linkText("My Groups"),true);
		click(By.linkText("GROUPNAV_26_02_023"));
		captureScreen("GROUPNAV_26_02_023");
		//Delete node
		goToGroupSites();
		click(ELEMENT_EDIT_NAV_GROUP);
		deleteNode("Administration","GROUPNAV_26_02_023","GROUPNAV_26_02_023",true);
	}

	@AfterMethod()
	public void afterTest() throws Exception {
		signOut();
		driver.quit();
	}
}