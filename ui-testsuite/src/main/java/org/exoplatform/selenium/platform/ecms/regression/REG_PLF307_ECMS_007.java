package org.exoplatform.selenium.platform.ecms.regression;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.ActionBar.goToAddNewContent;

import org.exoplatform.selenium.platform.ecms.ContentTemplate;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.SiteExplorer;
import org.exoplatform.selenium.platform.ecms.WcmAdmin;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class REG_PLF307_ECMS_007 extends EcmsBase{
	public static final String DATA_USER = "john";
	public static final String DATA_PASS = "gtn";
	
	public static final By ELEMENT_ADD_BUTTON = By.linkText("Add");
	public static final By ELEMENT_OK_BUTTON = By.linkText("OK");
	public static final By ELEMENT_SAVE_BUTTON = By.linkText("Save");
	public static final By ELEMENT_REGISTER_BUTTON = By.linkText("Register");
	public static final By ELEMENT_ADD_TYPE_BUTTON = By.linkText("Add Type");
	public static final By ELEMENT_ADD_PROPERTY_BUTTON = By.linkText("Add Property");
	public static final By ELEMENT_NAMESPACE_REGISTER = By.linkText("Namespace Registry");
	public static final By ELEMENT_CONTENT_TYPES = By.xpath("//div[contains(text(),'Content Types')]");
	public static final By ELEMENT_CONTENT_PRESENTATION = By.xpath("//div[contains(text(),'Content Presentation')]");
	public static final By ELEMENT_MANAGE_NODETYPE = By.linkText("Manage Node Type");
	public static final By ELEMENT_MANAGE_TEMPLATE = By.linkText("Manage Templates");
	public static final By ELEMENT_INPUT_NAMESPACE_PREFIX = By.xpath("//input[@id = 'namespace']");
	public static final By ELEMENT_INPUT_URI_PREFIX = By.xpath("//input[@id='uri']");
	public static final By ELEMENT_INPUT_NODETYPE_NAME = By.xpath("//input[@id='nodeTypeName']");
	public static final By ELEMENT_NAMESPACE_SELECT = By.xpath("//select[@id='namespace']");
	public static final By ELEMENT_NAMESPACE_PROPERTY_SELECT = By.xpath("//select[@id='propertyNamespace']");
	public static final By ELEMENT_ADD_SUPER_TYPE = By.xpath("//img[@class='AddIcon16x16 AddSuperTypeIcon']");
	public static final By ELEMENT_PROPERTY_TAB=By.xpath("//div[@class='MiddleTab' and contains(text(),'Property')]");
	public static By ELEMENT_SELECT_SUPER_TYPE= By.xpath("//img[@title='Add Super Type']");
	public static final By ELEMENT_SELECT_NTBASE = By.xpath("//input[@id='nt:base']");
	public static final By ELEMENT_DEFINITION_NAME = By.xpath("//input[@id='propertyname']");
	public static By ELEMENT_ECME_LINK = By.xpath("//a[@style='cursor: pointer;' and @title='acme']");

	public static final String ELEMENT_NAMESPACE_PREFIX="zzz";
	public static final String ELEMENT_URI="http://www.google.com";
	public static final String ELEMEMT_NODETYPE_NAME="nodetype1";
	
	
	
	@BeforeMethod
	public void beforeMethods() throws Exception {
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		info("Login ECMS with "+DATA_USER);
		loginEcms(DATA_USER, DATA_PASS);
	}

	@AfterMethod
	public void afterMethods() throws Exception {
		info("Logout ECMS");
		logoutEcms();
		driver.manage().deleteAllCookies();
		driver.quit();
		actions = null;
	}
	/*
	 * - Go to Content Types -> Manage Node type
	 * - Add a new node type
	 * * Choose Name Space: zzz
	 * * node type name: nodetype1
	 * * super type: nt:base
	 * * Move to tab Property: choose name space zzz, definition name: titulo, Click Add Propert
	 * * click on save on Node type tab
	 * - Content Presentation -> Manage Templates
	 * - Add the new template with label "Teste do nodetype1"
	 * - Choose "Any permission" and save it.
	 * - Click on the "Content Explorer" tab > 
	 * - Create a new content folder "test_nodetype" in "/classic/web contents
	 * - Click on "Add Content"- Choose the template "Teste do nodetype1"- You can write anything in the fields, don't fill up the jcr_mixinTypes- Click on "Save as draft"- Click on close- Click on manage publications -> published
	 * - Go to Classic/ overview page- Click on "Site Editor -> Edit Page"- Add a "Content List" portlet- Edit the "content list" to set the "Folder Path" to the /classic/web contents/test_nodetype folder- Click on Save- Click on Close- Click on the Finish button
	 */
	@Test
	public void test_displayCustomNodeType(){
		//Register new namespace
		registerNewNameSpace();
		//Go to Manage Node Type
		goToManageNodeType();
		//Add new node type
		addNewNodeType();
		//Add new template
		AddNewTemplate();
		//Go to Site Explorer 
		goToSiteExplorer();
		//Goto acme/web content
		doubleClickOnElement("//a[@title='acme')]");
		doubleClickOnElement("//a[@title='web contents')]");
		//Create new content folder
		ContentTemplate.createNewContentFolder("test_nodetype","test_nodetype");
		//Add Content use the created template
		goToAddNewContent();
		//Public ducument
		SiteExplorer.publicDocument();
		//Go to OverView Page
		goToOverView();
		//Go to EditPage Editor 
		goToPageCreationWinzard();
		//Add Content List portlet
		click(ELEMENT_MENU_CONTENT_LINK);
		dragAndDropToObject(ELEMENT_ADD_CONTENT_LIST_PORTLET,ELEMENT_DROP_TARGET_HAS_LAYOUT);
		//Capture screen
		captureScreen("REG_PLF307_ECMS_007");
	}
	private static void addNewNodeType(){
		click(ELEMENT_ADD_BUTTON);
		//Select space name
		select(ELEMENT_NAMESPACE_SELECT, ELEMENT_NAMESPACE_PREFIX);
		type(ELEMENT_INPUT_NODETYPE_NAME, ELEMEMT_NODETYPE_NAME, false);
		click(ELEMENT_SELECT_SUPER_TYPE);
		click(ELEMENT_SELECT_NTBASE);
		click(ELEMENT_ADD_TYPE_BUTTON);
		//Move to Property tab
		click(ELEMENT_PROPERTY_TAB);
		//Select space name
		select(ELEMENT_NAMESPACE_PROPERTY_SELECT, ELEMENT_NAMESPACE_PREFIX);
		//Input definition name
		type(ELEMENT_DEFINITION_NAME, "titulo", false);
		//Click to Add Property button
		click(ELEMENT_ADD_PROPERTY_BUTTON);
		click(ELEMENT_SAVE_BUTTON);
		click(ELEMENT_OK_BUTTON);
	}
	private static void registerNewNameSpace(){
		goToContentAdministration();
		click(ELEMENT_CONTENT_TYPES);
		click(ELEMENT_NAMESPACE_REGISTER);
		click(ELEMENT_REGISTER_BUTTON);
		type(ELEMENT_INPUT_NAMESPACE_PREFIX, ELEMENT_NAMESPACE_PREFIX, false);
		type(ELEMENT_INPUT_URI_PREFIX, ELEMENT_URI, false);
		click(ELEMENT_SAVE_BUTTON);
		
	}
	private static void goToManageNodeType(){
		click(ELEMENT_CONTENT_TYPES);
		click(ELEMENT_MANAGE_NODETYPE);
		waitForElementPresent(By.xpath("//div[@class='FunctionTitle' and text() = 'Manage Node Type']"));
	}
	private static void AddNewTemplate(){
		click(ELEMENT_CONTENT_PRESENTATION);
		click(ELEMENT_MANAGE_TEMPLATE);
		click(ELEMENT_ADD_BUTTON);
		WcmAdmin.fillAddNewTemplateForm("Teste do nodetype1","app:application","Platform/Administration/Content Management","*");
	}
}
