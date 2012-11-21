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

public class ECMS_DMS_SE_Kofax extends EcmsBase {

	public static By SITE_MANAGEMENT_ACME = By.xpath("//a[@title='acme ']");

	public static By KOFAX_LINHTEXT = By.xpath("//a[@title='Kofax document']");

	public static String KOFAX_NAME = "Kofax_Document_Name";

	public static By KOFAX_PATH = By.xpath("//a[@title='"+ KOFAX_NAME +" "+"']");	

	public static By NEW_CONTENT_LINHTEXT = By.xpath("//a[@title='New Content']");

	public static final String UPLOADED_FILE_PATH ="TestData/Winter.jpg";

	@BeforeMethod
	public void beforeMethods() throws Exception {
		initSeleniumTest();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseUrl);
		actions = new Actions(driver);
		loginEcms("john","gtn");
	}

	@AfterMethod
	public void afterTest() throws Exception {
		info("Log out");
		logoutEcms ();

		info("Close Window");
		driver.quit();
	}

	/* Create content folder and select it
	 * Add Kofax in content folder
	 * Delete content folder including Kofax document
	 */
	@Test
	public void test01_AddKoFaxInContentFolder()
	{   
		String CONTENT_FOLDER_NAME="Content_Folder";
		By CONTENT_FOLDER_PATH = By.xpath("//a[@title='"+CONTENT_FOLDER_NAME+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Go to Site management/ acme");
		goToNode(SITE_MANAGEMENT_ACME);

		info("Create Content Folder");
		createNewContentFolder(CONTENT_FOLDER_NAME, CONTENT_FOLDER_NAME);

		info("Go to content folder");
		goToNode(CONTENT_FOLDER_PATH);

		info("Add Content");
		goToAddNewContent();

		info("Create Kofax Document");
		createNewKofax(KOFAX_NAME);

		info("Verify Kofax is created");
		waitForElementPresent(KOFAX_PATH);

		//delete kofax
		deleteDocument(KOFAX_PATH);
		
		info("Delete Content Folder");
		deleteDocument(CONTENT_FOLDER_PATH);

	}

	/* Create document folder and select it
	 * Click Add Content on action bar
	 * Verify there is no Kofax in the list
	 * Delete document folder 
	 */
	@Test
	public void test02_AddKoFaxInDocumentFolder()
	{
		String DOCUMENT_FOLDER_NAME="Document_Folder";
		By DOCUMENT_FOLDER_PATH = By.xpath("//a[@title='"+DOCUMENT_FOLDER_NAME+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Go to Site management/ acme");
		goToNode(SITE_MANAGEMENT_ACME);

		info("Create Document Folder");
		createNewDocumentFolder(DOCUMENT_FOLDER_NAME, DOCUMENT_FOLDER_NAME);

		info("Go to document folder");
		goToNode(DOCUMENT_FOLDER_PATH);

		info("Add Content");
		goToAddNewContent();

		info("Verify Kofax is not in the list");
		waitForElementNotPresent(KOFAX_LINHTEXT);

		info("Go to document folder");
		goToNode(DOCUMENT_FOLDER_PATH);

		info("Delete Document Folder");
		deleteDocument(DOCUMENT_FOLDER_PATH);
	}

	/* Create an article
	 * Select article, create Kofax under article
	 * Verify output
	 * Delete article including Kofax document
	 */
	@Test
	public void test03_AddKoFaxInArticle()
	{
		String ARTICLE_NAME_TITLE="Article_Document";
		String ARTICLE_SUM="Summary of article";
		String ARTICLE_CONTENT="Content of article";
		By ARTICLE_PATH = By.xpath("//a[@title='"+ARTICLE_NAME_TITLE+" "+"']");	

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

		info("Create Kofax");
		createNewKofax(KOFAX_NAME);

		info("Verify content of Kofax");
		waitForTextPresent(KOFAX_NAME); 

		info("Click Article");
		goToNode(ARTICLE_PATH);

		info("Delete Article and Kofax");
		deleteDocument(KOFAX_PATH);
		deleteDocument(ARTICLE_PATH);
	}

	/* Create 1 file
	 * Open this file and verify there is no New Content on action bar
	 * Delete file
	 */ 
	@Test
	public void test04_AddKoFaxInFileDocument()
	{
		String FILE_NAME_TITLE="File_Document";
		String FILE_NAME_CONTENT="Content of File";
		By FILE_PATH = By.xpath("//a[@title='"+FILE_NAME_TITLE+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Go to Site management/ acme");
		goToNode(SITE_MANAGEMENT_ACME);

		info("Click New Content");
		goToAddNewContent();

		info("Create File");
		pause(2000);
		createNewFile(FILE_NAME_TITLE, FILE_NAME_CONTENT, FILE_NAME_TITLE);

		info("Verify no New Content on action bar");
		waitForElementNotPresent(NEW_CONTENT_LINHTEXT);

		info("Delete File");
		deleteDocument(FILE_PATH);
	}

	/* Create 1 Podcast
	 * Open this Podcast and verify there is no New Content on action bar
	 * Delete Podcast
	 */
	@Test
	public void test05_AddKoFaxInPodcastDocument()
	{
		String PODCAST_TITLE_NAME="PODCAST_Document";
		String PODCAST_LINK="www.google.com";
		By PODCAST_PATH = By.xpath("//a[@title='"+PODCAST_TITLE_NAME+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Go to Site management/ acme");
		goToNode(SITE_MANAGEMENT_ACME);

		info("Click New Content");
		goToAddNewContent();

		info("Create Podcast");
		createNewPodcast(PODCAST_TITLE_NAME, PODCAST_TITLE_NAME, PODCAST_LINK);

		info("Verify no New Content on action bar");
		waitForElementNotPresent(NEW_CONTENT_LINHTEXT);

		info("Delete Podcast");
		deleteDocument(PODCAST_PATH);

	}

	/* Create 1 Sample Node
	 * Open this Sample Node and Add Kofax under Sample Node
	 * Verify results
	 * Delete Sample Node including Kofax
	 */
	@Test
	public void test06_AddKoFaxInSampleNode()
	{
		String SAMPLE_NODE_NAME_TITLE="Sample_Node";
		By SAMPLE_NODE_PATH=By.xpath("//a[@title='"+SAMPLE_NODE_NAME_TITLE+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Go to Site management/ acme");
		goToNode(SITE_MANAGEMENT_ACME);

		info("Click New Content");
		goToAddNewContent();

		info("Create Sample node");
		createNewSampleNode(SAMPLE_NODE_NAME_TITLE, SAMPLE_NODE_NAME_TITLE, UPLOADED_FILE_PATH);

		info("Add Content");
		goToAddNewContent();

		info("Create Kofax Document");
		createNewKofax(KOFAX_NAME);

		info("Verify Kofax is created");
		waitForTextPresent(KOFAX_NAME);

		deleteDocument(KOFAX_PATH);
		deleteDocument(SAMPLE_NODE_PATH);

	}

	/* Create 1 File plan
	 * Open this File plan and Add Kofax under File plan
	 * Verify results
	 * Delete File plan including Kofax
	 */
	@Test
	public void test07_AddKoFaxInFilePlan()
	{
		String FILE_PLAN_INFO="File_Plan_Info";
		By FILE_PLAN_PATH=By.xpath("//a[@title='"+FILE_PLAN_INFO+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Go to Site management/ acme");
		goToNode(SITE_MANAGEMENT_ACME);

		info("Click New Content");
		goToAddNewContent();

		info("Create Sample node");
		createNewFilePlan(FILE_PLAN_INFO, FILE_PLAN_INFO, FILE_PLAN_INFO, FILE_PLAN_INFO, FILE_PLAN_INFO);

		info("Add Content");
		goToAddNewContent();

		info("Create File plan");
		createNewKofax(KOFAX_NAME);

		info("Verify Kofax is created");
		waitForTextPresent(KOFAX_NAME);

		info("Select File Plan");
		goToNode(FILE_PLAN_PATH);

		deleteDocument(KOFAX_PATH);
		deleteDocument(FILE_PLAN_PATH);
	}

	/* Create 1 file
	 * Open this file and verify there is no New Content on action bar
	 * Delete file
	 */ 
	@Test
	public void test08_AddKoFaxInKofax()
	{
		info("Go to CE");
		goToSiteExplorer();

		info("Go to Site management/ acme");
		goToNode(SITE_MANAGEMENT_ACME);

		info("Click New Content");
		goToAddNewContent();

		info("Create Kofax");
		createNewKofax(KOFAX_NAME);

		info("Click New Content");
		goToAddNewContent();

		info("Verify no Kofax in template list");
		waitForElementNotPresent(KOFAX_LINHTEXT);

		info("Select kofax again");
		goToNode(KOFAX_PATH);

		info("Delete Kofax");
		deleteDocument(KOFAX_PATH);
	}

	/* Upload 1 file
	 * Open this file and verify there is no New Content on action bar
	 * Delete uploaded file
	 */ 
	@Test
	public void test09_AddKoFaxInUploadedFile()
	{
		String UPLOADED_FILE_NAME="Uploaded_Image";
		By UPLOADED_FILE_XPATH = By.xpath("//a[@title='"+UPLOADED_FILE_NAME+".jpg "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Go to Site management/ acme");
		goToNode(SITE_MANAGEMENT_ACME);

		info("Click Upload");
		uploadFile(UPLOADED_FILE_NAME, UPLOADED_FILE_PATH);

		info("Open File");
		goToNode(UPLOADED_FILE_XPATH);

		info("Verify no New Content on action bar");
		waitForElementNotPresent(NEW_CONTENT_LINHTEXT);

		info("Delete File");
		deleteDocument(UPLOADED_FILE_XPATH);

	}
}
