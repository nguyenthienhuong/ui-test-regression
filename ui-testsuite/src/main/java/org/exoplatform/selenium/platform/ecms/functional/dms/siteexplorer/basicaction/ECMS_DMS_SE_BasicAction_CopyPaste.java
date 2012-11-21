package org.exoplatform.selenium.platform.ecms.functional.dms.siteexplorer.basicaction;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.ecms.ContextMenu;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.*;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.*;

/*
 * @author HaKT
 * @date: 24/09/2012
 */

public class ECMS_DMS_SE_BasicAction_CopyPaste extends EcmsBase {

	public static String SHOW_DMS_STRUCTURE="enableStructure";

	public static String UPLOADED_FILE_PATH ="TestData/Winter.jpg";

	public static String WARNING_MESSAGE_CANNOT_PASTE="Cannot paste the copied node type on the current node.";

	
	//Copy-Paste a Node
	public void copyPasteNode(By source, By destination){
		click(source);
		copyNode(source);
		ContextMenu.pasteNode(destination);
	}

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

	/* Go to Sites Management, stand at root path
	 * Create 1 Content folder
	 * Copy it
	 * Paste at root path
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test01_CopyFolderToRootPath()
	{ 
		String CONTENT_FOLDER_NAME="Content_Folder_01";
		By CONTENT_FOLDER_PATH=By.xpath("//a[@title='"+CONTENT_FOLDER_NAME+" "+"']");

		By VERIFY_AT_ROOT_PATH2=By.xpath("//a[@title='"+CONTENT_FOLDER_NAME+" "+"']/following::a[@title='"+CONTENT_FOLDER_NAME+" "+"']");
		By VERIFY_AT_ROOT_PATH1=By.xpath("//a[@title='acme ']/following::a[@title='"+CONTENT_FOLDER_NAME+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Create Content Folder");
		createNewContentFolder(CONTENT_FOLDER_NAME, CONTENT_FOLDER_NAME);

		info("Verify folder is created at root path");
		waitForElementPresent(VERIFY_AT_ROOT_PATH1);

		info("Copy content folder & paste at root path");
		copyPasteNode(CONTENT_FOLDER_PATH, ELEMENT_SITES_MANAGEMENT_DRIVE);

		info("Verify copied folder is at root path");
		waitForElementPresent(VERIFY_AT_ROOT_PATH2);

		info("Delete data");
		goToNode(CONTENT_FOLDER_PATH);
		rightClickOnElement(CONTENT_FOLDER_PATH);
		mouseOver(ELEMENT_MENU_DELETE, true);
		click(ELEMENT_MENU_DELETE);
		click(By.linkText("OK"));
		info(CONTENT_FOLDER_PATH.toString() + "was deleted successfully");

		pause(1000);
		goToNode(CONTENT_FOLDER_PATH);
		rightClickOnElement(CONTENT_FOLDER_PATH);
		mouseOver(ELEMENT_MENU_DELETE, true);
		click(ELEMENT_MENU_DELETE);
		click(By.linkText("OK"));
		info(CONTENT_FOLDER_PATH.toString() + "was deleted successfully");
		waitForElementNotPresent(CONTENT_FOLDER_PATH);
	}

	/* Go to Sites Management, stand at root path
	 * Create 2 Content folder
	 * Copy folder 1
	 * Paste to folder 2
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test02_CopyContentFolderToContentFolder()
	{
		String CONTENT_FOLDER_NAME="Content_Folder_02";
		By CONTENT_FOLDER_PATH=By.xpath("//a[@title='"+CONTENT_FOLDER_NAME+" "+"']");

		String CONTENT_FOLDER_NAME_01="Content_Folder_021";
		By CONTENT_FOLDER_PATH_01=By.xpath("//a[@title='"+CONTENT_FOLDER_NAME_01+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Create Content Folder");
		createNewContentFolder(CONTENT_FOLDER_NAME, CONTENT_FOLDER_NAME);

		info("Create Content Folder 01");
		createNewContentFolder(CONTENT_FOLDER_NAME_01, CONTENT_FOLDER_NAME_01);

		info("Copy content folder 1 to content folder");
		copyPasteNode(CONTENT_FOLDER_PATH_01, CONTENT_FOLDER_PATH);

		info("Verify Content folder 01 is in content folder");
		goToNode(CONTENT_FOLDER_PATH);
		waitForElementPresent(CONTENT_FOLDER_PATH_01);

		info("Delete data");
		deleteDocument(CONTENT_FOLDER_PATH);
		pause(1000);
		goToNode(CONTENT_FOLDER_PATH_01);
		deleteDocument(CONTENT_FOLDER_PATH_01);
	}

	/* Go to Sites Management
	 * Create 1 Content folder, 1 document folder
	 * Copy content folder
	 * Paste to document folder
	 * Verify warning message
	 * Delete data
	 */
	@Test
	public void test03_CopyContentFolderToDocumentFolder()
	{
		String CONTENT_FOLDER_NAME="Content_Folder_03";
		By CONTENT_FOLDER_PATH=By.xpath("//a[@title='"+CONTENT_FOLDER_NAME+" "+"']");

		String DOCUMENT_FOLDER_NAME="Document_Folder_03";
		By DOCUMENT_FOLDER_PATH=By.xpath("//a[@title='"+DOCUMENT_FOLDER_NAME+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Create Content Folder");
		createNewContentFolder(CONTENT_FOLDER_NAME, CONTENT_FOLDER_NAME);

		info("Create Document folder");
		createNewDocumentFolder(DOCUMENT_FOLDER_NAME, DOCUMENT_FOLDER_NAME);

		info("Copy content folder to document folder");
		copyPasteNode(CONTENT_FOLDER_PATH, DOCUMENT_FOLDER_PATH);

		info("Verify warning message");
		waitForTextPresent(WARNING_MESSAGE_CANNOT_PASTE);
		click(By.linkText("OK"));

		info("Delete data");
		goToNode(CONTENT_FOLDER_PATH);
		deleteDocument(CONTENT_FOLDER_PATH);
		pause(1000);
		goToNode(DOCUMENT_FOLDER_PATH);
		deleteDocument(DOCUMENT_FOLDER_PATH);
	}

	/* Go to Sites Management
	 * Create 1 Content folder, 1 article
	 * Copy content folder
	 * Paste to article
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test04_CopyContentFolderToDocument()
	{
		String ARTICLE_NAME_TITLE="Article_Document_04";

		String ARTICLE_SUM="Summary of article_04";

		String ARTICLE_CONTENT="Content of article_04";

		By ARTICLE_PATH = By.xpath("//a[@title='"+ARTICLE_NAME_TITLE+" "+"']");

		String CONTENT_FOLDER_NAME="Content_Folder_04";
		By CONTENT_FOLDER_PATH=By.xpath("//a[@title='"+CONTENT_FOLDER_NAME+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Create Content Folder");
		createNewContentFolder(CONTENT_FOLDER_NAME, CONTENT_FOLDER_NAME);

		info("Create 1 article");
		goToAddNewContent();
		createNewArticle(ARTICLE_NAME_TITLE, ARTICLE_NAME_TITLE, ARTICLE_SUM, ARTICLE_CONTENT);

		info("Copy folder to article");
		pause(1000);
		copyPasteNode(CONTENT_FOLDER_PATH, ARTICLE_PATH);

		info("Check content folder in Article");
		pause(1000);
		goToNode(ARTICLE_PATH);
		checkPreferenceOption(SHOW_DMS_STRUCTURE);
		waitForElementPresent(CONTENT_FOLDER_PATH);

		info("Delete data");
		deleteDocument(ARTICLE_PATH);
		pause(1000);
		goToNode(CONTENT_FOLDER_PATH);
		deleteDocument(CONTENT_FOLDER_PATH);
	}

	/* Go to Sites Management
	 * Create 1 Content folder, 1 document folder
	 * Copy document folder
	 * Paste to content folder
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test08_CopyDocumentFolderToContentFolder()
	{
		String CONTENT_FOLDER_NAME="Content_Folder_08";
		By CONTENT_FOLDER_PATH=By.xpath("//a[@title='"+CONTENT_FOLDER_NAME+" "+"']");

		String DOCUMENT_FOLDER_NAME="Document_Folder_08";
		By DOCUMENT_FOLDER_PATH=By.xpath("//a[@title='"+DOCUMENT_FOLDER_NAME+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Create Content Folder");
		createNewContentFolder(CONTENT_FOLDER_NAME, CONTENT_FOLDER_NAME);

		info("Create Document folder");
		createNewDocumentFolder(DOCUMENT_FOLDER_NAME, DOCUMENT_FOLDER_NAME);

		info("Copy document folder to content folder");
		copyPasteNode(DOCUMENT_FOLDER_PATH, CONTENT_FOLDER_PATH);

		info("Verify document folder is in content folder");
		goToNode(CONTENT_FOLDER_PATH);
		waitForElementPresent(DOCUMENT_FOLDER_PATH);

		info("Delete data");
		deleteDocument(CONTENT_FOLDER_PATH);
		pause(1000);
		goToNode(DOCUMENT_FOLDER_PATH);
		deleteDocument(DOCUMENT_FOLDER_PATH);
	}

	/* Go to Sites Management
	 * Create 2 document folders
	 * Copy document folder 2 to document folder 1
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test09_CopyDocumentFolderToDocumentFolder()
	{
		String DOCUMENT_FOLDER_NAME="Document_Folder_09";
		By DOCUMENT_FOLDER_PATH=By.xpath("//a[@title='"+DOCUMENT_FOLDER_NAME+" "+"']");

		String DOCUMENT_FOLDER_NAME_01="Document_Folder_091";
		By DOCUMENT_FOLDER_PATH_01=By.xpath("//a[@title='"+DOCUMENT_FOLDER_NAME_01+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Create Document folder");
		createNewDocumentFolder(DOCUMENT_FOLDER_NAME, DOCUMENT_FOLDER_NAME);

		info("Create Document folder");
		createNewDocumentFolder(DOCUMENT_FOLDER_NAME_01, DOCUMENT_FOLDER_NAME_01);

		info("Copy document folder to document folder 1");
		pause(1000);
		copyPasteNode(DOCUMENT_FOLDER_PATH, DOCUMENT_FOLDER_PATH_01);

		info("Verify document folder is pasted");
		goToNode(DOCUMENT_FOLDER_PATH_01);
		waitForElementPresent(DOCUMENT_FOLDER_PATH);

		info("Delete data");
		deleteDocument(DOCUMENT_FOLDER_PATH_01);
		pause(2000);
		goToNode(DOCUMENT_FOLDER_PATH);
		deleteDocument(DOCUMENT_FOLDER_PATH);
	}
	
	/*case17: Copy folder and paste into locked node by user is not locker
	 * login with John: create new node (folder/document/upload file). Lock node
	 * login with mary: create new node. Check can not copy this node to locked node
	 */
	@Test
	public void test17_CopyFolderAndPasteIntoLockedNodeByUserIsNotLock(){		
		String DOCUMENT_FOLDER_NAME_1 = "ECMS_DMS_SE_BasicAction_CopyPaste_folder_17_1";
		By DOCUMENT_FOLDER_PATH_1 = By.linkText(DOCUMENT_FOLDER_NAME_1);
		
		String DOCUMENT_FOLDER_NAME_2 = "ECMS_DMS_SE_BasicAction_CopyPaste_folder_17_2";
		By DOCUMENT_FOLDER_PATH_2 = By.linkText(DOCUMENT_FOLDER_NAME_2);
		
		//create new document folder
		goToSiteExplorer();
		info("Create Document folder");
		createNewDocumentFolder(DOCUMENT_FOLDER_NAME_1, DOCUMENT_FOLDER_NAME_1);
		waitForElementPresent(DOCUMENT_FOLDER_PATH_1);
		
		//lock node
		goToNode(DOCUMENT_FOLDER_PATH_1);
		lockNode(DOCUMENT_FOLDER_PATH_1);
		
		//check lock node
		checkLockNode(DOCUMENT_FOLDER_PATH_1);
		driver.close();
		
		//login with mary
		info("login with mary");
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		loginEcms("mary", "gtn");
		goToSiteExplorer();
		
		//create new document folder
		info("Create Document folder");
		createNewDocumentFolder(DOCUMENT_FOLDER_NAME_2, DOCUMENT_FOLDER_NAME_2);
		waitForElementPresent(DOCUMENT_FOLDER_PATH_2);
		
		//Check can not copy this node to locked node
		copyPasteNode(DOCUMENT_FOLDER_PATH_2, DOCUMENT_FOLDER_PATH_1);
		checkAlertWarning("The item with the path collaboration:/sites content/live/"+ DOCUMENT_FOLDER_NAME_1 + " or upper level is locked.");
		info("Can not copy a node to locked node by user is not locker");
		deleteData(DOCUMENT_FOLDER_PATH_2);
		logoutEcms();
		
		//delete data with john
		loginEcms("john", "gtn");
		goToSiteExplorer();
		deleteData(DOCUMENT_FOLDER_PATH_1);
	}

	/* Go to Sites Management
	 * Create document folder
	 * Copy it
	 * Create content folder then set James does not have add node right
	 * Paste document folder to this
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test18_CopyFolderPasteIntoNodeUserNotHaveAddNodeRight()
	{
		String DOCUMENT_FOLDER_NAME="Document_Folder_18";
		By DOCUMENT_FOLDER_PATH=By.xpath("//a[@title='"+DOCUMENT_FOLDER_NAME+" "+"']");

		String CONTENT_FOLDER_NAME="Content_Folder_18";
		By CONTENT_FOLDER_PATH=By.xpath("//a[@title='"+CONTENT_FOLDER_NAME+" "+"']");

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

		String MSS_NO_RIGHT_TO_PASTE_NODE="You do not have permission to paste to this node. Please contact the administrator to get the correct right.";

		info("Go to CE");
		goToSiteExplorer();

		info("Create Document folder");
		createNewDocumentFolder(DOCUMENT_FOLDER_NAME, DOCUMENT_FOLDER_NAME);

		info("Create Content Folder");
		createNewContentFolder(CONTENT_FOLDER_NAME, CONTENT_FOLDER_NAME);
		goToNode(CONTENT_FOLDER_PATH);

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

		info("Log out, log in as james, go to CE");
		logoutEcms();
		loginEcms("james","gtn");
		goToSiteExplorer();		

		info("Copy document folder to content folder");
		pause(1000);
		copyPasteNode(DOCUMENT_FOLDER_PATH, CONTENT_FOLDER_PATH);

		info("Verify cannot paste");
		pause(1000);
		waitForTextPresent(MSS_NO_RIGHT_TO_PASTE_NODE);
		pause(500);
		click(By.linkText("OK"));

		info("Log out, log in as john to delete data");
		logoutEcms();
		loginEcms("john","gtn");
		goToSiteExplorer();	
		goToNode(CONTENT_FOLDER_PATH);
		deleteDocument(CONTENT_FOLDER_PATH);
		pause(1000);
		goToNode(DOCUMENT_FOLDER_PATH);
		deleteDocument(DOCUMENT_FOLDER_PATH);
	}

	/* Go to Sites Management, stand at root path
	 * Create 1 Document folder
	 * Copy it
	 * Paste at root path
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test20_CopyDocumentFolderToRootPath()
	{ 
		String DOCUMENT_FOLDER_NAME="Document_Folder_20";
		By DOCUMENT_FOLDER_PATH=By.xpath("//a[@title='"+DOCUMENT_FOLDER_NAME+" "+"']");
		By VERIFY_AT_ROOT_PATH2=By.xpath("//a[@title='"+DOCUMENT_FOLDER_NAME+" "+"']/following::a[@title='"+DOCUMENT_FOLDER_NAME+" "+"']");
		By VERIFY_AT_ROOT_PATH1=By.xpath("//a[@title='acme ']/following::a[@title='"+DOCUMENT_FOLDER_NAME+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Create Document Folder");
		createNewDocumentFolder(DOCUMENT_FOLDER_NAME, DOCUMENT_FOLDER_NAME);

		info("Verify folder is created at root path");
		waitForElementPresent(VERIFY_AT_ROOT_PATH1);

		info("Copy & paste at root path");
		pause(1000);
		copyPasteNode(DOCUMENT_FOLDER_PATH, ELEMENT_SITES_MANAGEMENT_DRIVE);

		info("Verify copied folder is at root path");
		waitForElementPresent(VERIFY_AT_ROOT_PATH2);

		info("Delete data");
		goToNode(DOCUMENT_FOLDER_PATH);
		rightClickOnElement(DOCUMENT_FOLDER_PATH);
		mouseOver(ELEMENT_MENU_DELETE, true);
		click(ELEMENT_MENU_DELETE);
		click(By.linkText("OK"));
		info(DOCUMENT_FOLDER_PATH.toString() + "was deleted successfully");

		pause(1000);
		goToNode(DOCUMENT_FOLDER_PATH);
		rightClickOnElement(DOCUMENT_FOLDER_PATH);
		mouseOver(ELEMENT_MENU_DELETE, true);
		click(ELEMENT_MENU_DELETE);
		click(By.linkText("OK"));
		info(DOCUMENT_FOLDER_PATH.toString() + "was deleted successfully");
		waitForElementNotPresent(DOCUMENT_FOLDER_PATH);
	}

	/* Go to Sites Management
	 * Create 1 content folder
	 * Create 1 article, Copy it
	 * Paste in content folder
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test21_CopyArticleToContentFolder()
	{ 
		String ARTICLE_NAME_TITLE="Article_Document_21";

		String ARTICLE_SUM="Summary of article_21";

		String ARTICLE_CONTENT="Content of article_21";

		By ARTICLE_PATH = By.xpath("//a[@title='"+ARTICLE_NAME_TITLE+" "+"']");

		String CONTENT_FOLDER_NAME="Content_Folder_21";
		By CONTENT_FOLDER_PATH=By.xpath("//a[@title='"+CONTENT_FOLDER_NAME+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Create Content Folder");
		createNewContentFolder(CONTENT_FOLDER_NAME, CONTENT_FOLDER_NAME);

		info("Create article paste to content folder");
		goToAddNewContent();
		createNewArticle(ARTICLE_NAME_TITLE,  ARTICLE_NAME_TITLE, ARTICLE_SUM, ARTICLE_CONTENT);
		pause(1000);
		copyPasteNode(ARTICLE_PATH, CONTENT_FOLDER_PATH);

		info("Delete data");
		goToNode(CONTENT_FOLDER_PATH);
		deleteDocument(CONTENT_FOLDER_PATH);
		pause(1000);
		goToNode(ARTICLE_PATH);
		deleteDocument(ARTICLE_PATH);
	}

	/* Go to Sites Management
	 * Create 1 document folder
	 * Create 1 article, Copy it
	 * Paste in content folder
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test22_CopyArticleToDocumentFolder()
	{
		String ARTICLE_NAME_TITLE="Article_Document_22";
		String ARTICLE_SUM="Summary of article 22";
		String ARTICLE_CONTENT="Content of article 22";
		By ARTICLE_PATH = By.xpath("//a[@title='"+ARTICLE_NAME_TITLE+" "+"']");

		String DOCUMENT_FOLDER_NAME="Document_Folder_22";
		By DOCUMENT_FOLDER_PATH=By.xpath("//a[@title='"+DOCUMENT_FOLDER_NAME+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Create document Folder");
		createNewDocumentFolder(DOCUMENT_FOLDER_NAME, DOCUMENT_FOLDER_NAME);

		info("Create article & copy paste to document folder");
		goToAddNewContent();
		createNewArticle(ARTICLE_NAME_TITLE,  ARTICLE_NAME_TITLE, ARTICLE_SUM, ARTICLE_CONTENT);
		pause(1000);
		copyPasteNode(ARTICLE_PATH, DOCUMENT_FOLDER_PATH);

		info("Verify warning message");
		pause(1000);
		waitForTextPresent(WARNING_MESSAGE_CANNOT_PASTE);
		click(By.linkText("OK"));

		info("Delete data");
		goToNode(DOCUMENT_FOLDER_PATH);
		deleteDocument(DOCUMENT_FOLDER_PATH);
		pause(1000);
		goToNode(ARTICLE_PATH);
		deleteDocument(ARTICLE_PATH);
	}

	/* Go to Sites Management
	 * Create 1 content folder
	 * Create 1 file, Copy it
	 * Paste in content folder
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test31_CopyFileToContentFolder()
	{ 
		String CONTENT_FOLDER_NAME="Content_Folder_31";
		By CONTENT_FOLDER_PATH=By.xpath("//a[@title='"+CONTENT_FOLDER_NAME+" "+"']");
		String FILE_NAME_TITLE="File_Document_31";
		String FILE_NAME_CONTENT="Content of File";
		By FILE_PATH = By.xpath("//a[@title='"+FILE_NAME_TITLE+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Create Content Folder");
		createNewContentFolder(CONTENT_FOLDER_NAME, CONTENT_FOLDER_NAME);

		info("Create File & copy paste to content folder");
		goToAddNewContent();
		createNewFile(FILE_NAME_TITLE, FILE_NAME_CONTENT, FILE_NAME_TITLE);
		pause(1000);
		copyPasteNode(FILE_PATH, CONTENT_FOLDER_PATH);

		info("Verify result");
		goToNode(CONTENT_FOLDER_PATH);
		waitForElementPresent(FILE_PATH);

		info("Delete data");
		deleteDocument(CONTENT_FOLDER_PATH);
		pause(1000);
		goToNode(FILE_PATH);
		deleteDocument(FILE_PATH);
	}

	/* Go to Sites Management
	 * Create 1 Article
	 * Create 1 file, Copy it
	 * Paste in Article
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test32_CopyFileToOtherDocument()
	{ 
		String ARTICLE_NAME_TITLE="Article_Document_32";
		String ARTICLE_SUM="Summary of article 32";
		String ARTICLE_CONTENT="Content of article 32";
		By ARTICLE_PATH = By.xpath("//a[@title='"+ARTICLE_NAME_TITLE+" "+"']");

		String FILE_NAME_TITLE="File_Document_32";
		String FILE_NAME_CONTENT="Content of File";
		By FILE_PATH = By.xpath("//a[@title='"+FILE_NAME_TITLE+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Create Article");
		goToAddNewContent();
		createNewArticle(ARTICLE_NAME_TITLE, ARTICLE_NAME_TITLE, ARTICLE_SUM, ARTICLE_CONTENT);
		pause(1000);

		info("Choose Site Management");
		click(ELEMENT_SITES_MANAGEMENT_DRIVE);

		info("Create File");
		goToAddNewContent();
		pause(2000);
		createNewFile(FILE_NAME_TITLE, FILE_NAME_CONTENT, FILE_NAME_TITLE);
		pause(1000);

		info("Copy-paste");
		pause(1000);
		copyPasteNode(FILE_PATH, ARTICLE_PATH);
		
		info("Verify");
		pause(1000);
		goToNode(ARTICLE_PATH);		
		checkPreferenceOption(SHOW_DMS_STRUCTURE);
		waitForElementPresent(FILE_PATH);

		info("Delete data");
		goToNode(ARTICLE_PATH);
		deleteDocument(ARTICLE_PATH);
		pause(1000);
		goToNode(FILE_PATH);
		deleteDocument(FILE_PATH);
	}

	/* Go to Sites Management
	 * Create Upload a file
	 * Create 1 file, Copy it
	 * Paste in uploaded file
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test33_CopyFileToUploadedFile()
	{ 
		String UPLOADED_FILE_NAME="Uploaded_Image";
		By UPLOADED_FILE_XPATH = By.xpath("//a[@title='"+UPLOADED_FILE_NAME+".jpg "+"']");

		String FILE_NAME_TITLE="File_Document_33";
		String FILE_NAME_CONTENT="Content of File";
		By FILE_PATH = By.xpath("//a[@title='"+FILE_NAME_TITLE+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Upload a File");
		uploadFile(UPLOADED_FILE_NAME, UPLOADED_FILE_PATH);

		info("Create File");
		goToAddNewContent();
		pause(2000);
		createNewFile(FILE_NAME_TITLE, FILE_NAME_CONTENT, FILE_NAME_TITLE);
		info("Copy paste");
		pause(1000);
		copyPasteNode(FILE_PATH, UPLOADED_FILE_XPATH);

		info("Verify result");
		pause(1000);
		goToNode(UPLOADED_FILE_XPATH);
		checkPreferenceOption(SHOW_DMS_STRUCTURE);
		waitForElementPresent(FILE_PATH);

		info("Delete data");
		goToNode(UPLOADED_FILE_XPATH);
		deleteDocument(UPLOADED_FILE_XPATH);
		pause(1000);
		goToNode(FILE_PATH);
		deleteDocument(FILE_PATH);
	}

	/* Go to Sites Management
	 * Create 1 content folder
	 * Create 1 podcast, Copy it
	 * Paste in content folder
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test35_CopyPodcastToContentFolder()
	{ 
		String PODCAST_TITLE_NAME="PODCAST_Document";
		String PODCAST_LINK="www.google.com";
		By PODCAST_PATH = By.xpath("//a[@title='"+PODCAST_TITLE_NAME+" "+"']");

		String CONTENT_FOLDER_NAME="Content_Folder_35";
		By CONTENT_FOLDER_PATH=By.xpath("//a[@title='"+CONTENT_FOLDER_NAME+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Create Content Folder");
		createNewContentFolder(CONTENT_FOLDER_NAME, CONTENT_FOLDER_NAME);

		info("Create podcast & copy paste");
		goToAddNewContent();
		createNewPodcast(PODCAST_TITLE_NAME, PODCAST_TITLE_NAME, PODCAST_LINK);
		pause(1000);
		copyPasteNode(PODCAST_PATH, CONTENT_FOLDER_PATH);

		info("Open Content Folder & verify");
		goToNode(CONTENT_FOLDER_PATH);
		waitForElementPresent(PODCAST_PATH);

		info("Delete data");
		pause(1000);
		deleteDocument(CONTENT_FOLDER_PATH);
		goToNode(PODCAST_PATH);
		deleteDocument(PODCAST_PATH);
	}

	/* Go to Sites Management
	 * Create 1 Article
	 * Create 1 podcast, Copy it
	 * Paste in Article
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test36_CopyPodcastToOtherDocument()
	{ 
		String ARTICLE_NAME_TITLE="Article_Document_36";
		String ARTICLE_SUM="Summary of article 36";
		String ARTICLE_CONTENT="Content of article 36";
		By ARTICLE_PATH = By.xpath("//a[@title='"+ARTICLE_NAME_TITLE+" "+"']");

		String PODCAST_TITLE_NAME="PODCAST_Document_36";
		String PODCAST_LINK="www.google.com";
		By PODCAST_PATH = By.xpath("//a[@title='"+PODCAST_TITLE_NAME+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Create Article");
		goToAddNewContent();
		createNewArticle(ARTICLE_NAME_TITLE, ARTICLE_NAME_TITLE, ARTICLE_SUM, ARTICLE_CONTENT);

		info("Choose Site Management");
		pause(1000);
		click(ELEMENT_SITES_MANAGEMENT_DRIVE);

		info("Create Podcast & copy paste");
		goToAddNewContent();
		pause(2000);
		createNewPodcast(PODCAST_TITLE_NAME, PODCAST_TITLE_NAME, PODCAST_LINK);
		pause(1000);
		copyPasteNode(PODCAST_PATH, ARTICLE_PATH);

		info("Open Article & verify");
		goToNode(ARTICLE_PATH);
		pause(1000);
		checkPreferenceOption(SHOW_DMS_STRUCTURE);
		waitForElementPresent(PODCAST_PATH);

		info("Delete data");
		deleteDocument(ARTICLE_PATH);
		pause(1000);
		goToNode(PODCAST_PATH);
		deleteDocument(PODCAST_PATH);
	}

	/* Go to Sites Management
	 * Create 1 content folder, lock it
	 * Create 1 podcast, Copy it
	 * Paste in locked content folder
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test38_CopyPodcastToLockedNodeByLocker()
	{ 
		String CONTENT_FOLDER_NAME="Content_Folder_38";
		By CONTENT_FOLDER_PATH=By.xpath("//a[@title='"+CONTENT_FOLDER_NAME+" "+"']");
		String CONTENT_FOLDER_LOCK="//a[contains(text(), '"+CONTENT_FOLDER_NAME+"')]";

		String PODCAST_TITLE_NAME="PODCAST_Document_38";
		String PODCAST_LINK="www.google.com";
		By PODCAST_PATH = By.xpath("//a[@title='"+PODCAST_TITLE_NAME+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Create podcast");
		goToAddNewContent();
		createNewPodcast(PODCAST_TITLE_NAME, PODCAST_TITLE_NAME, PODCAST_LINK);

		info("Create Content Folder & lock");
		pause(1000);
		click(ELEMENT_SITES_MANAGEMENT_DRIVE);
		createNewContentFolder(CONTENT_FOLDER_NAME, CONTENT_FOLDER_NAME);
		pause(2000);
		lockNode(CONTENT_FOLDER_PATH);
		checkLockNode(By.xpath(CONTENT_FOLDER_LOCK));

		info("Copy-pate");
		pause(1000);
		copyPasteNode(PODCAST_PATH, By.xpath(CONTENT_FOLDER_LOCK));

		info("Open Content Folder & verify");
		goToNode(By.xpath(CONTENT_FOLDER_LOCK));
		waitForElementPresent(PODCAST_PATH);

		info("Delete data");
		deleteDocument(By.xpath(CONTENT_FOLDER_LOCK));
		pause(2000);
		goToNode(PODCAST_PATH);
		deleteDocument(PODCAST_PATH);
	}

	/* Go to Sites Management
	 * Create 1 content folder
	 * Create 1 sample node, Copy it
	 * Paste in content folder
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test39_CopySampleNodeToContentFolder()
	{ 
		String SAMPLE_NODE_NAME_TITLE="Sample_Node_Document_39";
		By SAMPLE_NODE_PATH=By.xpath("//a[@title='"+SAMPLE_NODE_NAME_TITLE+" "+"']");

		String CONTENT_FOLDER_NAME="Content_Folder_39";
		By CONTENT_FOLDER_PATH=By.xpath("//a[@title='"+CONTENT_FOLDER_NAME+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Create Content Folder");
		createNewContentFolder(CONTENT_FOLDER_NAME, CONTENT_FOLDER_NAME);

		info("Create sample node & copy");
		goToAddNewContent();
		createNewSampleNode(SAMPLE_NODE_NAME_TITLE, SAMPLE_NODE_NAME_TITLE, UPLOADED_FILE_PATH);
		pause(1000);
		copyPasteNode(SAMPLE_NODE_PATH, CONTENT_FOLDER_PATH);

		info("Open Content Folder verify");
		goToNode(CONTENT_FOLDER_PATH);
		waitForElementPresent(SAMPLE_NODE_PATH);

		info("Delete data");
		pause(1000);
		deleteDocument(CONTENT_FOLDER_PATH);
		pause(1000);
		goToNode(SAMPLE_NODE_PATH);
		deleteDocument(SAMPLE_NODE_PATH);
	}

	/* Go to Sites Management
	 * Create 1 document folder
	 * Create 1 sample node, Copy it
	 * Paste in document folder
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test40_CopySampleNodeToDocumentFolder()
	{ 
		String DOCUMENT_FOLDER_NAME="Document_Folder_40";
		By DOCUMENT_FOLDER_PATH=By.xpath("//a[@title='"+DOCUMENT_FOLDER_NAME+" "+"']");
		String SAMPLE_NODE_NAME_TITLE="Sample_Node_Document_40";
		By SAMPLE_NODE_PATH=By.xpath("//a[@title='"+SAMPLE_NODE_NAME_TITLE+" "+"']");


		info("Go to CE");
		goToSiteExplorer();

		info("Create document Folder");
		createNewDocumentFolder(DOCUMENT_FOLDER_NAME, DOCUMENT_FOLDER_NAME);

		info("Create sample node & copy paste");
		goToAddNewContent();
		createNewSampleNode(SAMPLE_NODE_NAME_TITLE, SAMPLE_NODE_NAME_TITLE, UPLOADED_FILE_PATH);
		pause(1000);
		copyPasteNode(SAMPLE_NODE_PATH,DOCUMENT_FOLDER_PATH);
		pause(1000);
		waitForTextPresent(WARNING_MESSAGE_CANNOT_PASTE);
		click(By.linkText("OK"));

		info("Delete data");
		goToNode(DOCUMENT_FOLDER_PATH);
		deleteDocument(DOCUMENT_FOLDER_PATH);
		pause(1000);
		goToNode(SAMPLE_NODE_PATH);
		deleteDocument(SAMPLE_NODE_PATH);
	}

	/* Go to Sites Management
	 * Create 1 article
	 * Create 1 sample node, Copy it
	 * Paste in article
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test41_01_CopySampleNodeToArticle()
	{ 
		String SAMPLE_NODE_NAME_TITLE="Sample_Node_Document_41_01";
		By SAMPLE_NODE_PATH=By.xpath("//a[@title='"+SAMPLE_NODE_NAME_TITLE+" "+"']");

		String ARTICLE_NAME_TITLE="Article_Document_41";

		String ARTICLE_SUM="Summary of article";

		String ARTICLE_CONTENT="Content of article";

		By ARTICLE_PATH = By.xpath("//a[@title='"+ARTICLE_NAME_TITLE+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Create Article");
		goToAddNewContent();
		createNewArticle(ARTICLE_NAME_TITLE, ARTICLE_NAME_TITLE, ARTICLE_SUM, ARTICLE_CONTENT);

		info("Choose Site Management");
		pause(1000);
		click(ELEMENT_SITES_MANAGEMENT_DRIVE);

		info("Create sample node & copy paste");
		goToAddNewContent();
		createNewSampleNode(SAMPLE_NODE_NAME_TITLE, SAMPLE_NODE_NAME_TITLE, UPLOADED_FILE_PATH);
		pause(1000);
		copyPasteNode(SAMPLE_NODE_PATH,ARTICLE_PATH);
		pause(1000);
		waitForTextPresent(WARNING_MESSAGE_CANNOT_PASTE);
		click(By.linkText("OK"));

		info("Delete data");
		goToNode(ARTICLE_PATH);
		deleteDocument(ARTICLE_PATH);
		pause(1000);
		goToNode(SAMPLE_NODE_PATH);
		deleteDocument(SAMPLE_NODE_PATH);
	}

	/* Go to Sites Management
	 * Create 1 FilePlan
	 * Create 1 sample node, Copy it
	 * Paste in FilePlan
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test41_02_CopySampleNodeToFilePlan()
	{ 
		String FILE_PLAN_INFO="File_Plan_Info_41_02";
		By FILE_PLAN_PATH=By.xpath("//a[@title='"+FILE_PLAN_INFO+" "+"']");

		String SAMPLE_NODE_NAME_TITLE="Sample_Node_Document_41_02";
		By SAMPLE_NODE_PATH=By.xpath("//a[@title='"+SAMPLE_NODE_NAME_TITLE+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Create File plan");
		goToAddNewContent();
		createNewFilePlan(FILE_PLAN_INFO, FILE_PLAN_INFO, FILE_PLAN_INFO, FILE_PLAN_INFO, FILE_PLAN_INFO);

		info("Choose Site Management");
		pause(1000);
		goToNode(ELEMENT_SITES_MANAGEMENT_DRIVE);

		info("Create sample node & copy paste");
		goToAddNewContent();
		createNewSampleNode(SAMPLE_NODE_NAME_TITLE, SAMPLE_NODE_NAME_TITLE, UPLOADED_FILE_PATH);
		pause(1000);
		copyPasteNode(SAMPLE_NODE_PATH,FILE_PLAN_PATH);
		pause(1000);
		waitForTextPresent(WARNING_MESSAGE_CANNOT_PASTE);
		click(By.linkText("OK"));

		info("Delete data");
		goToNode(FILE_PLAN_PATH);
		deleteDocument(FILE_PLAN_PATH);
		pause(1000);
		goToNode(SAMPLE_NODE_PATH);
		deleteDocument(SAMPLE_NODE_PATH);
	}

	/* Go to Sites Management
	 * Create 1st sample node
	 * Create 2nd sample node, Copy it
	 * Paste in 1st sample node
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test41_03_CopySampleNodeToSamleNode()
	{ 
		String SAMPLE_NODE_NAME_TITLE="Sample_Node_Document_41_03";
		By SAMPLE_NODE_PATH=By.xpath("//a[@title='"+SAMPLE_NODE_NAME_TITLE+" "+"']");

		String SAMPLE_NODE_NAME_TITLE_01="Sample_Node_41_03";
		By SAMPLE_NODE_PATH_01=By.xpath("//a[@title='"+SAMPLE_NODE_NAME_TITLE_01+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Create Sample node 01");
		goToAddNewContent();
		createNewSampleNode(SAMPLE_NODE_NAME_TITLE_01, SAMPLE_NODE_NAME_TITLE_01, UPLOADED_FILE_PATH);

		info("Choose Site Management");
		pause(1000);
		goToNode(ELEMENT_SITES_MANAGEMENT_DRIVE);

		info("Create sample node");
		goToAddNewContent();
		createNewSampleNode(SAMPLE_NODE_NAME_TITLE, SAMPLE_NODE_NAME_TITLE, UPLOADED_FILE_PATH);
		pause(1000);
		
		info("Copy paste");
		copyPasteNode(SAMPLE_NODE_PATH,SAMPLE_NODE_PATH_01);
		pause(1000);
		
		info("Verify");
		waitForTextPresent(WARNING_MESSAGE_CANNOT_PASTE);
		click(By.linkText("OK"));

		info("Delete data");
		goToNode(SAMPLE_NODE_PATH_01);
		deleteDocument(SAMPLE_NODE_PATH_01);
		pause(1000);
		goToNode(SAMPLE_NODE_PATH);
		deleteDocument(SAMPLE_NODE_PATH);
	}

	/* Go to Sites Management
	 * Create 1 sample node, copy it
	 * Create 1 file
	 * Paste in file
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test42_01_CopySampleNodeToFile()
	{ 
		String FILE_NAME_TITLE="File_Document_42_01";
		String FILE_NAME_CONTENT="Content of File";
		By FILE_PATH = By.xpath("//a[@title='"+FILE_NAME_TITLE+" "+"']");

		String SAMPLE_NODE_NAME_TITLE="Sample_Node_Document_42";
		By SAMPLE_NODE_PATH=By.xpath("//a[@title='"+SAMPLE_NODE_NAME_TITLE+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Create Sample node ");
		goToAddNewContent();
		createNewSampleNode(SAMPLE_NODE_NAME_TITLE, SAMPLE_NODE_NAME_TITLE, UPLOADED_FILE_PATH);

		info("Choose Site Management");
		pause(1000);
		goToNode(ELEMENT_SITES_MANAGEMENT_DRIVE);

		info("Create File & copy paste");
		goToAddNewContent();
		pause(1000);
		createNewFile(FILE_NAME_TITLE, FILE_NAME_CONTENT, FILE_NAME_TITLE);
		pause(1000);
		copyPasteNode(SAMPLE_NODE_PATH,FILE_PATH);

		info("Verify");
		goToNode(FILE_PATH);
		pause(1000);
		checkPreferenceOption(SHOW_DMS_STRUCTURE);
		waitForElementPresent(SAMPLE_NODE_PATH);

		info("Delete data");
		deleteDocument(FILE_PATH);
		pause(1000);
		goToNode(SAMPLE_NODE_PATH);
		deleteDocument(SAMPLE_NODE_PATH);
	}

	/* Go to Sites Management
	 * Create 1 sample node, copy it
	 * Create 1 podcast
	 * Paste in podcast
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test42_02_CopySampleNodeToPodcast()
	{ 
		String PODCAST_TITLE_NAME="PODCAST_Document_42_02";
		String PODCAST_LINK="www.google.com";
		By PODCAST_PATH = By.xpath("//a[@title='"+PODCAST_TITLE_NAME+" "+"']");
		String SAMPLE_NODE_NAME_TITLE="Sample_Node_Document_42_02";
		By SAMPLE_NODE_PATH=By.xpath("//a[@title='"+SAMPLE_NODE_NAME_TITLE+" "+"']");


		info("Go to CE");
		goToSiteExplorer();

		info("Create Sample node ");
		goToAddNewContent();
		createNewSampleNode(SAMPLE_NODE_NAME_TITLE, SAMPLE_NODE_NAME_TITLE, UPLOADED_FILE_PATH);

		info("Choose Site Management");
		pause(1000);
		goToNode(ELEMENT_SITES_MANAGEMENT_DRIVE);

		info("Create Podcast & copy paste");
		goToAddNewContent();
		createNewPodcast(PODCAST_TITLE_NAME, PODCAST_TITLE_NAME, PODCAST_LINK);
		pause(1000);
		copyPasteNode(SAMPLE_NODE_PATH,PODCAST_PATH);

		info("Verify");
		goToNode(PODCAST_PATH);
		pause(1000);
		checkPreferenceOption(SHOW_DMS_STRUCTURE);
		waitForElementPresent(SAMPLE_NODE_PATH);

		info("Delete data");
		deleteDocument(PODCAST_PATH);
		pause(1000);
		goToNode(SAMPLE_NODE_PATH);
		deleteDocument(SAMPLE_NODE_PATH);
	}

	/* Go to Sites Management
	 * Create 1 sample node, copy it
	 * Create 1 Kofax
	 * Paste in KOfax
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test42_03_CopySampleNodeToKofax()
	{ 
		String SAMPLE_NODE_NAME_TITLE="Sample_Node_Document_42_03";
		By SAMPLE_NODE_PATH=By.xpath("//a[@title='"+SAMPLE_NODE_NAME_TITLE+" "+"']");

		String KOFAX_NAME = "Kofax_Document_Name_42_03";
		By KOFAX_PATH = By.xpath("//a[@title='"+ KOFAX_NAME +" "+"']");	

		info("Go to CE");
		goToSiteExplorer();

		info("Create Sample node ");
		goToAddNewContent();
		createNewSampleNode(SAMPLE_NODE_NAME_TITLE, SAMPLE_NODE_NAME_TITLE, UPLOADED_FILE_PATH);

		info("Choose Site Management");
		pause(1000);
		goToNode(ELEMENT_SITES_MANAGEMENT_DRIVE);

		info("Create Kofax & copy paste");
		goToAddNewContent();
		createNewKofax(KOFAX_NAME);
		pause(1000);
		copyPasteNode(SAMPLE_NODE_PATH,KOFAX_PATH);

		info("Verify");
		goToNode(KOFAX_PATH);
		pause(1000);
		checkPreferenceOption(SHOW_DMS_STRUCTURE);
		waitForElementPresent(SAMPLE_NODE_PATH);

		info("Delete data");
		deleteDocument(KOFAX_PATH);
		pause(1000);
		goToNode(SAMPLE_NODE_PATH);
		deleteDocument(SAMPLE_NODE_PATH);
	}

	/* Go to Sites Management
	 * Create 1 sample node, copy it
	 * Upload a file
	 * Paste in uploaded file
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test43_CopySampleNodeToUploadedFile()
	{ 
		String SAMPLE_NODE_NAME_TITLE="Sample_Node_Document_43";
		By SAMPLE_NODE_PATH=By.xpath("//a[@title='"+SAMPLE_NODE_NAME_TITLE+" "+"']");

		String UPLOADED_FILE_NAME="Uploaded_Image_43";
		By UPLOADED_FILE_XPATH = By.xpath("//a[@title='"+UPLOADED_FILE_NAME+".jpg "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Upload a file ");
		uploadFile(UPLOADED_FILE_NAME, UPLOADED_FILE_PATH );

		info("Create Sample node ");
		goToAddNewContent();
		createNewSampleNode(SAMPLE_NODE_NAME_TITLE, SAMPLE_NODE_NAME_TITLE, UPLOADED_FILE_PATH);

		info("Copy Sample Node to uploaded file");		
		pause(1000);
		copyPasteNode(SAMPLE_NODE_PATH,UPLOADED_FILE_XPATH);

		info("Verify");
		goToNode(UPLOADED_FILE_XPATH);
		pause(1000);
		checkPreferenceOption(SHOW_DMS_STRUCTURE);
		waitForElementPresent(SAMPLE_NODE_PATH);

		info("Delete data");
		deleteDocument(UPLOADED_FILE_XPATH);
		pause(1000);
		goToNode(SAMPLE_NODE_PATH);
		deleteDocument(SAMPLE_NODE_PATH);
	}

	/* Go to Sites Management
	 * Create 1 sample node, copy it
	 * Upload a file, lock it
	 * Paste in locked uploaded file
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test48_CopySampleNodeToLockedUploadedFile()
	{ 
		String UPLOADED_FILE_NAME="Uploaded_Image_48";
		By UPLOADED_FILE_XPATH = By.xpath("//a[@title='"+UPLOADED_FILE_NAME+".jpg "+"']");
		String UPLOADED_FILE_LOCK="//a[contains(text(), '"+UPLOADED_FILE_NAME+"')]";

		String SAMPLE_NODE_NAME_TITLE="Sample_Node_Document_48";
		By SAMPLE_NODE_PATH=By.xpath("//a[@title='"+SAMPLE_NODE_NAME_TITLE+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Upload a file & Lock");
		uploadFile(UPLOADED_FILE_NAME, UPLOADED_FILE_PATH );
		lockNode(UPLOADED_FILE_XPATH);
		checkLockNode(By.xpath(UPLOADED_FILE_LOCK));

		info("Create Sample node ");
		goToAddNewContent();
		createNewSampleNode(SAMPLE_NODE_NAME_TITLE, SAMPLE_NODE_NAME_TITLE, UPLOADED_FILE_PATH);

		info("Copy Sample Node to uploaded file");		
		pause(1000);
		copyPasteNode(SAMPLE_NODE_PATH,By.xpath(UPLOADED_FILE_LOCK));

		info("Verify");
		goToNode(By.xpath(UPLOADED_FILE_LOCK));
		pause(1000);
		checkPreferenceOption(SHOW_DMS_STRUCTURE);
		waitForElementPresent(SAMPLE_NODE_PATH);

		info("Delete data");
		goToNode(By.xpath(UPLOADED_FILE_LOCK));
		deleteDocument(By.xpath(UPLOADED_FILE_LOCK));
		pause(1000);
		goToNode(SAMPLE_NODE_PATH);
		deleteDocument(SAMPLE_NODE_PATH);
	}

	/* Go to Sites Management
	 * Create 1 content folder
	 * Create 1 File plan, Copy it
	 * Paste in content folder
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test49_CopyFilePlanToContentFolder()
	{ 
		String FILE_PLAN_INFO="File_Plan_Info_49";
		By FILE_PLAN_PATH=By.xpath("//a[@title='"+FILE_PLAN_INFO+" "+"']");		
		String CONTENT_FOLDER_NAME="Content_Folder_49";
		By CONTENT_FOLDER_PATH=By.xpath("//a[@title='"+CONTENT_FOLDER_NAME+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Create Content Folder");
		createNewContentFolder(CONTENT_FOLDER_NAME, CONTENT_FOLDER_NAME);

		info("Create File Plan copy paste to content folder");
		goToAddNewContent();
		createNewFilePlan(FILE_PLAN_INFO, FILE_PLAN_INFO, FILE_PLAN_INFO, FILE_PLAN_INFO, FILE_PLAN_INFO);
		pause(1000);
		copyPasteNode(FILE_PLAN_PATH, CONTENT_FOLDER_PATH);

		info("Verify result");
		goToNode(CONTENT_FOLDER_PATH);
		waitForElementPresent(FILE_PLAN_PATH);

		info("Delete data");
		pause(1000);
		deleteDocument(CONTENT_FOLDER_PATH);
		pause(1000);
		goToNode(FILE_PLAN_PATH);
		deleteDocument(FILE_PLAN_PATH);
	}

	/* Go to Sites Management
	 * Create 1 article
	 * Create 1 File plan, Copy it
	 * Paste in article
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test50_01_CopyFilePlanToArticle()
	{ 
		String FILE_PLAN_INFO="File_Plan_Info_50_01";
		By FILE_PLAN_PATH=By.xpath("//a[@title='"+FILE_PLAN_INFO+" "+"']");	

		String ARTICLE_NAME_TITLE="Article_Document_50_01";
		String ARTICLE_SUM="Summary of article_50";
		String ARTICLE_CONTENT="Content of article 50";
		By ARTICLE_PATH = By.xpath("//a[@title='"+ARTICLE_NAME_TITLE+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Create Article");
		goToAddNewContent();
		createNewArticle(ARTICLE_NAME_TITLE, ARTICLE_NAME_TITLE, ARTICLE_SUM, ARTICLE_CONTENT);

		info("Choose Site Management");
		pause(1000);
		click(ELEMENT_SITES_MANAGEMENT_DRIVE);

		info("Create File Plan");
		goToAddNewContent();
		createNewFilePlan(FILE_PLAN_INFO, FILE_PLAN_INFO, FILE_PLAN_INFO, FILE_PLAN_INFO, FILE_PLAN_INFO);
		pause(1000);
		
		info("Copy paste");
		copyPasteNode(FILE_PLAN_PATH,ARTICLE_PATH);
		pause(1000);
		waitForTextPresent(WARNING_MESSAGE_CANNOT_PASTE);
		click(By.linkText("OK"));

		info("Delete data");
		goToNode(ARTICLE_PATH);
		deleteDocument(ARTICLE_PATH);
		pause(1000);
		goToNode(FILE_PLAN_PATH);
		deleteDocument(FILE_PLAN_PATH);
	}
	
	/* Go to Sites Management
	 * Create sample node
	 * Create Fileplan, Copy it
	 * Paste in sample node
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test50_02_CopyFilePlanToSamleNode()
	{ 
		String SAMPLE_NODE_NAME_TITLE="Sample_Node_Document_50_02";
		By SAMPLE_NODE_PATH=By.xpath("//a[@title='"+SAMPLE_NODE_NAME_TITLE+" "+"']");

		String FILE_PLAN_INFO="File_Plan_Info_50_02";
		By FILE_PLAN_PATH=By.xpath("//a[@title='"+FILE_PLAN_INFO+" "+"']");	

		info("Go to CE");
		goToSiteExplorer();

		info("Create Sample node 01");
		goToAddNewContent();
		createNewSampleNode(SAMPLE_NODE_NAME_TITLE, SAMPLE_NODE_NAME_TITLE, UPLOADED_FILE_PATH);

		info("Choose Site Management");
		pause(1000);
		goToNode(ELEMENT_SITES_MANAGEMENT_DRIVE);

		info("Create FIle Plan & copy paste");
		goToAddNewContent();
		createNewFilePlan(FILE_PLAN_INFO, FILE_PLAN_INFO, FILE_PLAN_INFO, FILE_PLAN_INFO, FILE_PLAN_INFO);
		pause(1000);
		copyPasteNode(FILE_PLAN_PATH,SAMPLE_NODE_PATH);
		pause(1000);
		waitForTextPresent(WARNING_MESSAGE_CANNOT_PASTE);
		click(By.linkText("OK"));

		info("Delete data");
		goToNode(FILE_PLAN_PATH);
		deleteDocument(FILE_PLAN_PATH);
		pause(1000);
		goToNode(SAMPLE_NODE_PATH);
		deleteDocument(SAMPLE_NODE_PATH);
	}
	
	/* Go to Sites Management
	 * Create 1 File Plan, copy it
	 * Create 1 file
	 * Paste in file
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test51_01_CopyFilePlanToFile()
	{ 
		String FILE_NAME_TITLE="File_Document_51_01";
		String FILE_NAME_CONTENT="Content of File";
		By FILE_PATH = By.xpath("//a[@title='"+FILE_NAME_TITLE+" "+"']");
		String FILE_PLAN_INFO="File_Plan_Info_51_01";
		By FILE_PLAN_PATH=By.xpath("//a[@title='"+FILE_PLAN_INFO+" "+"']");	

		info("Go to CE");
		goToSiteExplorer();

		info("Create File plan ");
		goToAddNewContent();
		createNewFilePlan(FILE_PLAN_INFO, FILE_PLAN_INFO, FILE_PLAN_INFO, FILE_PLAN_INFO, FILE_PLAN_INFO);

		info("Choose Site Management");
		pause(1000);
		goToNode(ELEMENT_SITES_MANAGEMENT_DRIVE);

		info("Create File & copy paste");
		goToAddNewContent();
		createNewFile(FILE_NAME_TITLE, FILE_NAME_CONTENT, FILE_NAME_TITLE);
		pause(2000);
		copyPasteNode(FILE_PLAN_PATH,FILE_PATH);

		info("Verify");
		goToNode(FILE_PATH);
		pause(1000);
		checkPreferenceOption(SHOW_DMS_STRUCTURE);
		pause(1000);
		waitForElementPresent(FILE_PLAN_PATH);

		info("Delete data");
		goToNode(FILE_PATH);
		deleteDocument(FILE_PATH);
		pause(1000);
		goToNode(FILE_PLAN_PATH);
		deleteDocument(FILE_PLAN_PATH);
	}

	/* Go to Sites Management
	 * Create 2 FilePlan
	 * Create  Copy FilePlan 1
	 * Paste in FilePlan 2
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test51_02_CopyFilePlanToFilePlan()
	{ 
		String FILE_PLAN_INFO1="File_Plan_Info_51_021";
		By FILE_PLAN_PATH1=By.xpath("//a[@title='"+FILE_PLAN_INFO1+" "+"']");
		String FILE_PLAN_INFO2="File_Plan_Info_51_022";
		By FILE_PLAN_PATH2=By.xpath("//a[@title='"+FILE_PLAN_INFO2+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Create File plan ");
		goToAddNewContent();
		createNewFilePlan(FILE_PLAN_INFO1, FILE_PLAN_INFO1, FILE_PLAN_INFO1, FILE_PLAN_INFO1, FILE_PLAN_INFO1);
		
		info("Choose Site Management");
		pause(1000);
		goToNode(ELEMENT_SITES_MANAGEMENT_DRIVE);

		info("Create File plan 2 & copy paste");
		goToAddNewContent();
		createNewFilePlan(FILE_PLAN_INFO2, FILE_PLAN_INFO2, FILE_PLAN_INFO2, FILE_PLAN_INFO2, FILE_PLAN_INFO2);
		pause(1000);
		copyPasteNode(FILE_PLAN_PATH1,FILE_PLAN_PATH2);
		pause(1000);
		
		info("Verify");
		goToNode(FILE_PLAN_PATH2);
		pause(1000);
		checkPreferenceOption(SHOW_DMS_STRUCTURE);
		pause(1000);
		waitForElementPresent(FILE_PLAN_PATH1);

		info("Delete data");
		goToNode(FILE_PLAN_PATH1);
		deleteDocument(FILE_PLAN_PATH1);
		pause(1000);
		goToNode(FILE_PLAN_PATH2);
		deleteDocument(FILE_PLAN_PATH2);
	}

	/* Go to Sites Management
	 * Create 1 File Plan, copy it
	 * Create 1 podcast
	 * Paste in podcast
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test51_03_CopyFilePlanToPodcast()
	{ 
		String PODCAST_TITLE_NAME="PODCAST_Document_51_03";
		String PODCAST_LINK="www.google.com";
		By PODCAST_PATH = By.xpath("//a[@title='"+PODCAST_TITLE_NAME+" "+"']");
		
		String FILE_PLAN_INFO="File_Plan_Info_51_03";
		By FILE_PLAN_PATH=By.xpath("//a[@title='"+FILE_PLAN_INFO+" "+"']");	

		info("Go to CE");
		goToSiteExplorer();

		info("Create File plan ");
		goToAddNewContent();
		createNewFilePlan(FILE_PLAN_INFO, FILE_PLAN_INFO, FILE_PLAN_INFO, FILE_PLAN_INFO, FILE_PLAN_INFO);

		info("Choose Site Management");
		pause(1000);
		goToNode(ELEMENT_SITES_MANAGEMENT_DRIVE);

		info("Create Podcast & copy paste");
		goToAddNewContent();
		createNewPodcast(PODCAST_TITLE_NAME, PODCAST_TITLE_NAME, PODCAST_LINK);
		pause(1000);
		copyPasteNode(FILE_PLAN_PATH,PODCAST_PATH);

		info("Verify");
		goToNode(PODCAST_PATH);
		pause(1000);
		checkPreferenceOption(SHOW_DMS_STRUCTURE);
		pause(1000);
		waitForElementPresent(FILE_PLAN_PATH);

		info("Delete data");
		pause(1000);
		goToNode(PODCAST_PATH);
		deleteDocument(PODCAST_PATH);
		pause(1000);
		goToNode(FILE_PLAN_PATH);
		deleteDocument(FILE_PLAN_PATH);
	}

	/* Go to Sites Management
	 * Create 1 File Plan, copy it
	 * Create 1 Kofax
	 * Paste in Kofax
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test51_04_CopyFilePlanToKofax()
	{ 
		String KOFAX_NAME = "Kofax_Document_Name_52_04";
		By KOFAX_PATH = By.xpath("//a[@title='"+ KOFAX_NAME +" "+"']");	
		String FILE_PLAN_INFO="File_Plan_Info_51_04";
		By FILE_PLAN_PATH=By.xpath("//a[@title='"+FILE_PLAN_INFO+" "+"']");	

		info("Go to CE");
		goToSiteExplorer();

		info("Create File plan ");
		goToAddNewContent();
		createNewFilePlan(FILE_PLAN_INFO, FILE_PLAN_INFO, FILE_PLAN_INFO, FILE_PLAN_INFO, FILE_PLAN_INFO);
		pause(1000);

		info("Choose Site Management");
		goToNode(ELEMENT_SITES_MANAGEMENT_DRIVE);

		info("Create Kofax & copy paste");
		goToAddNewContent();
		createNewKofax(KOFAX_NAME);
		pause(1000);
		copyPasteNode(FILE_PLAN_PATH,KOFAX_PATH);

		info("Verify");
		pause(1000);
		goToNode(KOFAX_PATH);
		pause(1000);
		checkPreferenceOption(SHOW_DMS_STRUCTURE);
		waitForElementPresent(FILE_PLAN_PATH);

		info("Delete data");
		goToNode(KOFAX_PATH);
		deleteDocument(KOFAX_PATH);
		pause(1000);
		goToNode(FILE_PLAN_PATH);
		deleteDocument(FILE_PLAN_PATH);
	}
	
	/* Go to Sites Management
	 * Create 1 File Plan, copy it
	 * Upload a file
	 * Paste in Uploaded file
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test52_CopyFilePlanToUploadedFile()
	{ 
		String FILE_PLAN_INFO="File_Plan_Info_52";
		By FILE_PLAN_PATH=By.xpath("//a[@title='"+FILE_PLAN_INFO+" "+"']");	
	
		String UPLOADED_FILE_NAME="Uploaded_Image_52";
		By UPLOADED_FILE_XPATH = By.xpath("//a[@title='"+UPLOADED_FILE_NAME+".jpg "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Upload a file");
		uploadFile(UPLOADED_FILE_NAME, UPLOADED_FILE_PATH );
		pause(1000);
		
		info("Create File plan ");
		goToAddNewContent();
		createNewFilePlan(FILE_PLAN_INFO, FILE_PLAN_INFO, FILE_PLAN_INFO, FILE_PLAN_INFO, FILE_PLAN_INFO);
		pause(1000);

		info("Copy-paste");
		copyPasteNode(FILE_PLAN_PATH,UPLOADED_FILE_XPATH);
		pause(1000);

		info("Verify");
		goToNode(UPLOADED_FILE_XPATH);
		pause(2000);
		info("Check Preference");
		checkPreferenceOption(SHOW_DMS_STRUCTURE);
		waitForElementPresent(FILE_PLAN_PATH);

		info("Delete data");
		goToNode(FILE_PLAN_PATH);
		deleteDocument(FILE_PLAN_PATH);
		pause(1000);
		goToNode(UPLOADED_FILE_XPATH);
		deleteDocument(UPLOADED_FILE_XPATH);
	}
	
	/* Go to Sites Management
	 * Create 1 content folder
	 * Create 1 Kofax, Copy it
	 * Paste in content folder
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test57_CopyKofaxToContentFolder()
	{ 
		String CONTENT_FOLDER_NAME="Content_Folder_57";
		By CONTENT_FOLDER_PATH=By.xpath("//a[@title='"+CONTENT_FOLDER_NAME+" "+"']");
		
		String KOFAX_NAME = "Kofax_Document_Name_57";
		By KOFAX_PATH = By.xpath("//a[@title='"+ KOFAX_NAME +" "+"']");	
		
		info("Go to CE");
		goToSiteExplorer();

		info("Create Content Folder");
		createNewContentFolder(CONTENT_FOLDER_NAME, CONTENT_FOLDER_NAME);
		pause(1000);

		info("Create Kofax & copy paste to content folder");
		goToAddNewContent();
		createNewKofax(KOFAX_NAME);
		pause(1000);
		copyPasteNode(KOFAX_PATH, CONTENT_FOLDER_PATH);

		info("Verify result");
		goToNode(CONTENT_FOLDER_PATH);
		waitForElementPresent(KOFAX_PATH);

		info("Delete data");
		goToNode(CONTENT_FOLDER_PATH);
		deleteDocument(CONTENT_FOLDER_PATH);
		pause(1000);
		goToNode(KOFAX_PATH);
		deleteDocument(KOFAX_PATH);
	}

	/* Go to Sites Management
	 * Create 1 document folder
	 * Create 1 Kofax, Copy it
	 * Paste in document folder
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test58_CopyKofaxToDocumentFolder()
	{ 
		String DOCUMENT_FOLDER_NAME="Document_Folder_58";
		By DOCUMENT_FOLDER_PATH=By.xpath("//a[@title='"+DOCUMENT_FOLDER_NAME+" "+"']");
		String KOFAX_NAME = "Kofax_Document_Name_58";
		By KOFAX_PATH = By.xpath("//a[@title='"+ KOFAX_NAME +" "+"']");	
		
		info("Go to CE");
		goToSiteExplorer();

		info("Create document Folder");
		createNewDocumentFolder(DOCUMENT_FOLDER_NAME, DOCUMENT_FOLDER_NAME);
		pause(1000);

		info("Create Kofax & copy paste");
		goToAddNewContent();
		createNewKofax(KOFAX_NAME);
		pause(1000);
		copyPasteNode(KOFAX_PATH,DOCUMENT_FOLDER_PATH);
		pause(1000);
		waitForTextPresent(WARNING_MESSAGE_CANNOT_PASTE);
		click(By.linkText("OK"));

		info("Delete data");
		goToNode(DOCUMENT_FOLDER_PATH);
		deleteDocument(DOCUMENT_FOLDER_PATH);
		pause(1000);
		goToNode(KOFAX_PATH);
		deleteDocument(KOFAX_PATH);
	}
	
	/* Go to Sites Management
	 * Create 1 Kofax, copy it
	 * Create 1 file
	 * Paste in file
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test59_CopyKofaxToDocument()
	{ 
		String FILE_NAME_TITLE="File_Document_59";
		String FILE_NAME_CONTENT="Content of File";
		By FILE_PATH = By.xpath("//a[@title='"+FILE_NAME_TITLE+" "+"']");

		String KOFAX_NAME = "Kofax_Document_Name_59";
		By KOFAX_PATH = By.xpath("//a[@title='"+ KOFAX_NAME +" "+"']");	

		info("Go to CE");
		goToSiteExplorer();

		info("Create Sample node ");
		goToAddNewContent();
		createNewKofax(KOFAX_NAME);
		pause(1000);
		
		info("Choose Site Management");
		pause(1000);
		goToNode(ELEMENT_SITES_MANAGEMENT_DRIVE);

		info("Create File & copy paste");
		goToAddNewContent();
		createNewFile(FILE_NAME_TITLE, FILE_NAME_CONTENT, FILE_NAME_TITLE);
		pause(1000);
		copyPasteNode(KOFAX_PATH,FILE_PATH);

		info("Verify");
		goToNode(FILE_PATH);
		pause(1000);
		checkPreferenceOption(SHOW_DMS_STRUCTURE);
		waitForElementPresent(KOFAX_PATH);

		info("Delete data");
		goToNode(FILE_PATH);
		deleteDocument(FILE_PATH);
		pause(1000);
		goToNode(KOFAX_PATH);
		deleteDocument(KOFAX_PATH);
	}

	/* Go to Sites Management
	 * Create 1 Kofax, copy it
	 * Upload a file
	 * Paste in uploaded file
	 * Verify result
	 * Delete data
	 */
	@Test
	public void test60_CopyKofaxToUploadedFile()
	{ 
		String KOFAX_NAME = "Kofax_Document_Name_60";
		By KOFAX_PATH = By.xpath("//a[@title='"+ KOFAX_NAME +" "+"']");	

		String UPLOADED_FILE_NAME="Uploaded_Image_60";
		By UPLOADED_FILE_XPATH = By.xpath("//a[@title='"+UPLOADED_FILE_NAME+".jpg "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Upload a file ");
		uploadFile(UPLOADED_FILE_NAME, UPLOADED_FILE_PATH );
		pause(1000);

		info("Create Kofax ");
		goToAddNewContent();
		createNewKofax(KOFAX_NAME);
		pause(1000);

		info("Copy Kofax to uploaded file");		
		pause(2000);
		copyPasteNode(KOFAX_PATH,UPLOADED_FILE_XPATH);

		info("Verify");
		goToNode(UPLOADED_FILE_XPATH);
		pause(1000);
		checkPreferenceOption(SHOW_DMS_STRUCTURE);
		pause(1000);
		waitForElementPresent(KOFAX_PATH);

		info("Delete data");
		goToNode(UPLOADED_FILE_XPATH);
		deleteDocument(UPLOADED_FILE_XPATH);
		pause(1000);
		goToNode(KOFAX_PATH);
		deleteDocument(KOFAX_PATH);
	}

}