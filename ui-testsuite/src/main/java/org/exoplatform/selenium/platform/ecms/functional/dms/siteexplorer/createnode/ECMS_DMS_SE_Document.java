package org.exoplatform.selenium.platform.ecms.functional.dms.siteexplorer.createnode;

import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.*;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.*;


/*
 * @author HaKT
 * @date: 18/09/2012
 */

public class ECMS_DMS_SE_Document extends EcmsBase {

	public static By SITE_MANAGEMENT_ACME = By.xpath("//a[@title='acme ']");

	public static String ARTICLE_NAME_TITLE="Article_Document";

	public static String ARTICLE_SUM="Summary of article";

	public static String ARTICLE_CONTENT="Content of article";

	public static By ARTICLE_PATH = By.xpath("//a[@title='"+ARTICLE_NAME_TITLE+" "+"']");

	public static final By ELEMENT_SAVE_BUTTON = By.linkText("Save");

	public static final By ELEMENT_CLOSE_BUTTON = By.linkText("Close");

	public static final By ELEMENT_CHANGE_CONTENT_TYPE = By.linkText("(Change Content Type)");

	public static final String MESSAGE_CLOSE_ARTICLE="The changes you made will be lost if you close this form.";

	@BeforeMethod
	public void beforeMethods() throws Exception {
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		loginEcms("john","gtn");
	}

	@AfterMethod
	public void afterTest() throws Exception {
//		logoutEcms ();
		driver.quit();
	}

	/* Go to Sites Management/ acme
	 * Click Add Content
	 * Choose Article, fill data
	 * Click Save & Close
	 * Verify document is created and Add Content form is closed
	 * Delete data
	 */
	@Test
	public void test01_AddContentWhenClickSaveAndClose()
	{ 
		info("Go to CE");
		goToSiteExplorer();

		info("Go to Site management/ acme");
		goToNode(SITE_MANAGEMENT_ACME);

		info("Click New Content");
		goToAddNewContent();

		info("Create article with Save&Close");
		createNewArticle(ARTICLE_NAME_TITLE,  ARTICLE_NAME_TITLE, ARTICLE_SUM, ARTICLE_CONTENT); 

		info("Verify Add Content form is closed");
		waitForElementNotPresent(ELEMENT_ARTICLE_SUMMARY_FRAME);
		waitForElementNotPresent(ELEMENT_ARTICLE_CONTENT_FRAME);

		info("Delete Article");
		deleteDocument(ARTICLE_PATH);

	}

	/* Go to Sites Management/ acme
	 * Click Add Content
	 * Choose Article, fill data
	 * Click Save
	 * Verify document is created and Add Content form is present
	 * Delete data
	 */
	@Test
	public void test02_AddContentWhenClickSave()
	{ 
		info("Go to CE");
		goToSiteExplorer();

		info("Go to Site management/ acme");
		goToNode(SITE_MANAGEMENT_ACME);

		info("Click New Content");
		goToAddNewContent();

		info("Create article with Save");		
		click(ELEMENT_ARTICLE_LINK);

		info(" Input information");
		type(ELEMENT_ARTICLE_TITLE_TEXTBOX,ARTICLE_NAME_TITLE,false);
		type(ELEMENT_ARTICLE_NAME_TEXTBOX, ARTICLE_NAME_TITLE, true);
		inputDataToFrame(ELEMENT_ARTICLE_SUMMARY_FRAME,ARTICLE_SUM);
		switchToParentWindow();
		inputDataToFrame(ELEMENT_ARTICLE_CONTENT_FRAME,ARTICLE_CONTENT);
		switchToParentWindow();
		info("Save");
		click(ELEMENT_SAVE_BUTTON);

		info("Verify Add Content form is present");
		pause(1000);
		assert isElementPresent(ELEMENT_ARTICLE_SUMMARY_FRAME):"Summary form is not present";
		pause(1000);
		assert isElementPresent(ELEMENT_ARTICLE_CONTENT_FRAME):"Content form is not present";

		info("Click Close");
		click(ELEMENT_CLOSE_BUTTON);

		info("Delete Article");
		deleteDocument(ARTICLE_PATH);

	}

	/* Go to Sites Management/ acme
	 * Click Add Content
	 * Choose Article, fill data
	 * Click Close
	 * Verify document is not created and Add Content form is closed
	 * Delete data
	 */
	@Test
	public void test03_AddContentWhenClickClose()
	{ 
		info("Go to CE");
		goToSiteExplorer();

		info("Go to Site management/ acme");
		goToNode(SITE_MANAGEMENT_ACME);

		info("Click New Content");
		goToAddNewContent();

		info("Create article with Close");		
		click(ELEMENT_ARTICLE_LINK);

		info(" Input information");
		type(ELEMENT_ARTICLE_TITLE_TEXTBOX,ARTICLE_NAME_TITLE,false);
		type(ELEMENT_ARTICLE_NAME_TEXTBOX, ARTICLE_NAME_TITLE, true);
		inputDataToFrame(ELEMENT_ARTICLE_SUMMARY_FRAME,ARTICLE_SUM);
		switchToParentWindow();
		inputDataToFrame(ELEMENT_ARTICLE_CONTENT_FRAME,ARTICLE_CONTENT);
		switchToParentWindow();
		info("Close");
		click(ELEMENT_CLOSE_BUTTON);

		info("Accept message.");
		waitForConfirmation(MESSAGE_CLOSE_ARTICLE);

		info("Verify Add Content form is closed");
		waitForElementNotPresent(ELEMENT_ARTICLE_SUMMARY_FRAME);
		waitForElementNotPresent(ELEMENT_ARTICLE_CONTENT_FRAME);

	}

	/* Go to Sites Management/ acme
	 * Click Add Content
	 * Choose Article, fill data
	 * Click Save then Close
	 * Verify document is created and Add Content form is closed
	 * Delete data
	 */
	@Test
	public void test04_AddContentWhenClickSaveThenClose()
	{ 
		info("Go to CE");
		goToSiteExplorer();

		info("Go to Site management/ acme");
		goToNode(SITE_MANAGEMENT_ACME);

		info("Click New Content");
		goToAddNewContent();

		info("Create article with Save");		
		click(ELEMENT_ARTICLE_LINK);

		info(" Input information");
		type(ELEMENT_ARTICLE_TITLE_TEXTBOX,ARTICLE_NAME_TITLE,false);
		type(ELEMENT_ARTICLE_NAME_TEXTBOX, ARTICLE_NAME_TITLE, true);
		inputDataToFrame(ELEMENT_ARTICLE_SUMMARY_FRAME,ARTICLE_SUM);
		switchToParentWindow();
		inputDataToFrame(ELEMENT_ARTICLE_CONTENT_FRAME,ARTICLE_CONTENT);
		switchToParentWindow();
		info("Save");
		click(ELEMENT_SAVE_BUTTON);
		click(ELEMENT_CLOSE_BUTTON);

		info("Verify Add Content form is closed");
		waitForElementNotPresent(ELEMENT_ARTICLE_SUMMARY_FRAME);
		waitForElementNotPresent(ELEMENT_ARTICLE_CONTENT_FRAME);

		info("Verify article is created");
		assert isElementPresent(ARTICLE_PATH);

		info("Delete Article");
		deleteDocument(ARTICLE_PATH);

	}

	/* Go to Sites Management/ acme
	 * Click Add Content
	 * Choose Article, fill data
	 * Click Change Content Type
	 * Verify Add Content form is replaced by Document template list 
	 */
	@Test
	public void test05_AddContentWhenClickChangeContentType()
	{ 
		String MESSAGE_CONFIRMATION="The changes you made will be lost if you change the content type.";
		By ELEMENT_TEMPLATE_LIST=By.xpath("//div[@class='SelectTemplateThumbnailView']");

		info("Go to CE");
		goToSiteExplorer();

		info("Go to Site management/ acme");
		goToNode(SITE_MANAGEMENT_ACME);

		info("Click New Content");
		goToAddNewContent();

		info("Choose article");		
		click(ELEMENT_ARTICLE_LINK);

		info("Input information");
		type(ELEMENT_ARTICLE_TITLE_TEXTBOX,ARTICLE_NAME_TITLE,false);
		type(ELEMENT_ARTICLE_NAME_TEXTBOX, ARTICLE_NAME_TITLE, true);
		inputDataToFrame(ELEMENT_ARTICLE_SUMMARY_FRAME,ARTICLE_SUM);
		switchToParentWindow();
		inputDataToFrame(ELEMENT_ARTICLE_CONTENT_FRAME,ARTICLE_CONTENT);
		switchToParentWindow();

		info("Click Change Content Type");
		click(ELEMENT_CHANGE_CONTENT_TYPE);
		waitForConfirmation(MESSAGE_CONFIRMATION);

		info("Verify template list appears");		
		assert isElementPresent(ELEMENT_TEMPLATE_LIST):"This is not template list!";
	}

	/* Go to Sites Management/ acme
	 * Click Add Content
	 * Choose Article, fill data
	 * Click Maximize icon, verify there is no address bar, side bar, footer bar
	 * Click Minimize, verify result
	 * Logout
	 */
	@Test
	public void test06_AddContentWhenClickMaximizeIcon()
	{
		By ELEMENT_MAXIMIZE=By.xpath("//a[@class='MaximizeScreen20x20Icon']");

		By ELEMENT_MINIMIZE=By.xpath("//a[@class='MinimizeScreen20x20Icon']");

		info("Go to CE");
		goToSiteExplorer();

		info("Go to Site management/ acme");
		goToNode(SITE_MANAGEMENT_ACME);

		info("Click New Content");
		goToAddNewContent();

		info("Choose article");		
		click(ELEMENT_ARTICLE_LINK);

		info("Input information");
		type(ELEMENT_ARTICLE_TITLE_TEXTBOX,ARTICLE_NAME_TITLE,false);
		type(ELEMENT_ARTICLE_NAME_TEXTBOX, ARTICLE_NAME_TITLE, true);
		inputDataToFrame(ELEMENT_ARTICLE_SUMMARY_FRAME,ARTICLE_SUM);
		switchToParentWindow();
		inputDataToFrame(ELEMENT_ARTICLE_CONTENT_FRAME,ARTICLE_CONTENT);
		switchToParentWindow();

		info("Click Maximize");
		click(ELEMENT_MAXIMIZE);

		info("Verify Add Content form is full screen");
		waitForElementNotPresent(ELEMENT_CHANGE_CONTENT_TYPE);
		pause(1000);

		info("Minimize again");
		click(ELEMENT_MINIMIZE);

		info("Verify Add Content form is in normal mode");
		waitForElementPresent(ELEMENT_CHANGE_CONTENT_TYPE);
		pause(1000);

		info("Close Article");
		click(ELEMENT_CLOSE_BUTTON);

		info("Accept message.");
		waitForConfirmation(MESSAGE_CLOSE_ARTICLE);

		info("Verify article does not exist");
		waitForElementNotPresent(ARTICLE_PATH);
	}

	/* Go to Sites Management/ acme
	 * Click Add Content to display template list
	 * Click Cancel icon
	 * Verify result
	 */
	@Test
	public void test07_CancelWhenSelectTemplate()
	{
		By ELEMENT_TEMPLATE_LIST=By.xpath("//div[@class='SelectTemplateThumbnailView']");

		By ELEMENT_RIGHT_CONTAINER=By.xpath("//div[@class='RightContainer']");

		By ELEMENT_CANCEL_TEMPLATE=By.xpath("//div[@title='Cancel']");

		info("Go to CE");
		goToSiteExplorer();

		info("Go to Site management/ acme");
		goToNode(SITE_MANAGEMENT_ACME);

		info("Click New Content");
		goToAddNewContent();

		info("CLick Cancel");
		click(ELEMENT_CANCEL_TEMPLATE);

		info("Verify template list appears");
		waitForElementPresent(ELEMENT_TEMPLATE_LIST);

		info("Click Cancel template");
		click(ELEMENT_CANCEL_TEMPLATE);

		info("Verify coming back previous directory.");
		waitForElementPresent(ELEMENT_RIGHT_CONTAINER);
	}

	/* Go to Sites Management
	 * Click Add Content and add 1 article
	 * Verify result
	 */
	@Test
	public void test08_AddContentInRootPath()
	{
		By VERIFY_AT_ROOT_PATH=By.xpath("//a[@title='acme ']/following::a[@title='"+ARTICLE_NAME_TITLE+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Click New Content");
		goToAddNewContent();

		info("Create article");
		createNewArticle(ARTICLE_NAME_TITLE,  ARTICLE_NAME_TITLE, ARTICLE_SUM, ARTICLE_CONTENT); 

		info("Verify article is created at root path");
		waitForElementPresent(VERIFY_AT_ROOT_PATH);

		info("Delete Article");
		deleteDocument(ARTICLE_PATH);

	}

	/* Go to Sites Management/ acme
	 * Click New Content
	 * Select Article
	 * input each special character in name in turn
	 * verify waring message
	 * 
	 */
	@Test
	public void test10_AddContentWithSpecialCharactersInName ()
	{
		String[] SPECIAL_CHAR_NAME={"`", "~", "!", "#", "$", "%", "&", "*", "(", ")", "=", "{", "}", "+", "|", ";", "'", "<", ">", "/", "?","\"","\\", "[","]",":"};
		String WARNING_MESSAGE="The field 'Name' contains some invalid characters. Please enter another value.";
		
		info("Go to CE");
		goToSiteExplorer();
		
		info("Go to Site management/ acme");
		goToNode(SITE_MANAGEMENT_ACME);

		info("Create article and fill special character in name");
		for (int i =0 ; i < SPECIAL_CHAR_NAME.length; i++){
			
			info("Click New Content");
			goToAddNewContent();
			
			info("Fill special chars repectively:" + SPECIAL_CHAR_NAME[i]);
			createNewArticle(SPECIAL_CHAR_NAME[i],  SPECIAL_CHAR_NAME[i], ARTICLE_SUM, ARTICLE_CONTENT);
			
			info("Verify warning message: Special is not allowed");
			waitForTextPresent(WARNING_MESSAGE);
			
			info("CLick OK");
			click(By.linkText("OK"));
			
			info("Click Close");
			click(ELEMENT_CLOSE_BUTTON);
		}
	}

	/* Go to Sites Management/ acme
	 * Create 1 article
	 * Verify New Content is displayed on action bar
	 * Check-in article
	 * Verify no New Content on action bar
	 * Check-out article
	 * Delete article
	 * Verify article is deleted
	 */
	@Test
	public void test11_AddContentInCheckinNode()
	{
		info("Go to CE");
		goToSiteExplorer();

		info("Go to Site management/ acme");
		goToNode(SITE_MANAGEMENT_ACME);

		info("Click New Content");
		goToAddNewContent();

		info("Create article");
		createNewArticle(ARTICLE_NAME_TITLE,  ARTICLE_NAME_TITLE, ARTICLE_SUM, ARTICLE_CONTENT);

		info("Verify New Content is present");
		waitForElementPresent(ELEMENT_MENU_NEW_CONTENT_LINK);

		info("Check-in article");
		checkInNode(ARTICLE_PATH);

		info("Verify no New Content on action bar");
		waitForElementNotPresent(ELEMENT_MENU_NEW_CONTENT_LINK);

		info("Checkout article");
		checkOutNode(ARTICLE_PATH);

		info("Delete Article");
		deleteDocument(ARTICLE_PATH);

	}

	/* Go to Sites Management/ acme
	 * Create 1 article
	 * Create 1 Kofax under article
	 * Check-in article
	 * Open Kofax
	 * Verify New Content is present on action bar
	 * Check-out article
	 * Delete article
	 * Verify article is deleted
	 */
	@Test
	public void test12_AddContentInNodeWhoseParentsInCheckinStatus()
	{
		String KOFAX_NAME="Kofax_Document_Name";

		String SHOW_DMS_STRUCTURE="enableStructure";

		By KOFAX_PATH = By.xpath("//a[@title='"+KOFAX_NAME+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Go to Site management/ acme");
		goToNode(SITE_MANAGEMENT_ACME);

		info("Click New Content");
		goToAddNewContent();

		info("Create article");
		createNewArticle(ARTICLE_NAME_TITLE,  ARTICLE_NAME_TITLE, ARTICLE_SUM, ARTICLE_CONTENT);

		info("Click New Content");
		goToAddNewContent();

		info("Create kofax");
		createNewKofax(KOFAX_NAME);

		info("Setup to see Kofax is child of article");
		checkPreferenceOption(SHOW_DMS_STRUCTURE);

		info("Click Article");
		goToNode(ARTICLE_PATH);

		info("Check-in article");
		checkInNode(ARTICLE_PATH);

		info("Verify article is in check-in status which means No content on action bar");
		waitForElementNotPresent(ELEMENT_MENU_NEW_CONTENT_LINK);

		info("Click Kofax");
		goToNode(KOFAX_PATH);

		info("Verify New Content is on action bar");
		waitForElementNotPresent(ELEMENT_MENU_NEW_CONTENT_LINK);

		info("Click Article");
		goToNode(ARTICLE_PATH);

		info("Checkout article");
		checkOutNode(ARTICLE_PATH);
		
		info("Delete Kofax");
		deleteDocument(KOFAX_PATH);
		
		info("Delete Article including Kofax");
		deleteDocument(ARTICLE_PATH);

	}

	/* Create 1 Article
	 * Set permission for this node for James does not have Add node right
	 * Log-out, log in as James
	 * Go to Article, Click on Add content on action bar
	 * Verify message that: no permission
	 * Log out, log in as john to delete data
	 */
	@Test
	public void test13_AddContentWhenUserDoesNotHaveAddNodeRight()
	{
		By ELEMENT_SYSTEM_TAB = By.linkText("System");

		By ELEMENT_PERMISSION_LINK = By.linkText("Permissions");

		By DELETE_PLF_ADMIN_PERMISSION=By.xpath("//div[@title='*:/platform/administrators']/following::div/img[@title='Remove Permission']");

		By DELETE_PLF_WEBCONTRIBUTOR_PERMISSION=By.xpath("//div[@title='*:/platform/web-contributors']/following::div/img[@title='Remove Permission']");

		By DELETE_ANY_PERMISSION=By.xpath("//div[@title='any']/following::div/img[@title='Remove Permission']");

		String CONFIRM_REMOVE_PERMISSION_MSS="Are you sure to remove this permission?";

		By SELECT_USER=By.xpath("//img[@alt='Select User']");

		By SELECT_JAMES=By.xpath("//div[@title='james']/following::div/img[@class='SelectPageIcon']");

		By SELECT_READ_RIGHT=By.id("read");
		By SELECT_SET_PROPERTY_RIGHT=By.id("set_property");
		By SELECT_REMOVE_RIGHT=By.id("remove");

		String MSS_NO_RIGHT_TO_ADD_NODE="You do not have permission to add a new node.";

		info("Go to CE");
		goToSiteExplorer();

		info("Click New Content");
		goToAddNewContent();

		info("Create article");
		createNewArticle(ARTICLE_NAME_TITLE,  ARTICLE_NAME_TITLE, ARTICLE_SUM, ARTICLE_CONTENT); 

		info("Click System tab");
		click(ELEMENT_SYSTEM_TAB);

		info("Click Permission");
		click(ELEMENT_PERMISSION_LINK);

		info("Delete permisson of all except John");
		pause(500);
		click(DELETE_PLF_ADMIN_PERMISSION);
		waitForConfirmation(CONFIRM_REMOVE_PERMISSION_MSS);

		pause(500);
		click(DELETE_PLF_WEBCONTRIBUTOR_PERMISSION);
		waitForConfirmation(CONFIRM_REMOVE_PERMISSION_MSS);

		pause(500);
		click(DELETE_ANY_PERMISSION);
		waitForConfirmation(CONFIRM_REMOVE_PERMISSION_MSS);

		info("Click Add User icon");
		click(SELECT_USER);

		info("Choose James");
		click(SELECT_JAMES);

		info("Choose all rights accept Add Node");
		check(SELECT_READ_RIGHT);
		check(SELECT_SET_PROPERTY_RIGHT);
		check(SELECT_REMOVE_RIGHT);

		info("Save then close");
		click(ELEMENT_SAVE_BUTTON);
		click(ELEMENT_CLOSE_BUTTON);

		info("Logout then login as James");
		logoutEcms();
		loginEcms("james", "gtn");

		info("Go to CE");
		goToSiteExplorer();

		info("Click article");
		goToNode(ARTICLE_PATH);

		info("Click New Content");
		goToAddNewContent();

		info("Verify message: no permission to add node");
		pause(1000);
		waitForTextPresent(MSS_NO_RIGHT_TO_ADD_NODE);

		info("CLick OK");
		pause(500);
		click(By.linkText("OK"));

		info("Logout then login as John to delete data");
		logoutEcms();
		loginEcms("john", "gtn");
		goToSiteExplorer();
		goToNode(ARTICLE_PATH);
		deleteDocument(ARTICLE_PATH);
	} 

	/*case14: Add Content in a locked node by user is not locker
	 * create 1 document node
	 * lock this node by user John
	 * login by user Mary
	 * check user mary can not add content for this node
	 */
	@Test
	public void test14_AddContentInLockedNodeByNotLocker()
	{
		String DATA_ARTICLE_TITLE = "ECMS_DMS_SE_Document_article_14";
		By ELEMENT_ARTICLE = By.linkText(DATA_ARTICLE_TITLE);
		
		//create new article
		goToSiteExplorer();
		goToAddNewContent();
		info("Create new article document");
		createNewArticle(DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, "", "");
		waitForElementPresent(ELEMENT_ARTICLE);
		
		//lock node
		info("Lock node");
		goToNode(ELEMENT_ARTICLE);
		lockNode(ELEMENT_ARTICLE);
		
		//check lock node
		checkLockNode(ELEMENT_ARTICLE);
		driver.close();
		
		//login with user mary
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		loginEcms("mary", "gtn");
		goToSiteExplorer();
		
		//check user mary can not add content for this node
		goToNode(ELEMENT_ARTICLE);
		waitForElementNotPresent(ELEMENT_MENU_NEW_CONTENT_LINK);
		info("User can not add content for this node");
		logoutEcms();
		
		//delete date with user John
		loginEcms("john", "gtn");
		goToSiteExplorer();
		deleteData(ELEMENT_ARTICLE);
	} 
}
