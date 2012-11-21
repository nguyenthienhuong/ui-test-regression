package org.exoplatform.selenium.platform.ecms.functional.dms.siteexplorer.createnode;

import static org.exoplatform.selenium.TestLogger.info;
import java.util.concurrent.TimeUnit;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.*;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;

public class ECMS_DMS_SE_SampleNode extends EcmsBase{

	public static final By CANCEL_CHOOSE_TEMPLATE=By.xpath("//div[@title='Cancel']");

	public static By SITE_MANAGEMENT_ACME = By.xpath("//a[@title='acme ']");

	public static final String UPLOADED_FILE_PATH ="TestData/Winter.jpg";

	public static By SAMPLE_NODE_LINHTEXT = By.xpath("//a[@title='Sample node']");

	@BeforeMethod
	public void beforeMethods() throws Exception {
		initSeleniumTest();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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

	/* Case 1: Add Sample Node document in Content Folder
	 * Create 1 Content Folder
	 * Create 1 Sample Node document in Content Folder
	 * Delete Sample Node document
	 * Delete Content Folder
	 */

	@Test
	public void test01_CreateSampleNodeInContentFolder()
	{   
		String FOLDER_NAME01="CREATE_07_01_Folder";
		String FOLDER_TITLE01="CREATE_07_01_Folder";
		By FOLDER_LINK01= By.xpath("//a[@title='"+FOLDER_TITLE01+" "+"']");
		String SAMPLE_NAME01="CREATE_07_01_Sample";
		String SAMPLE_TITLE01="CREATE_07_01_Sample";
		By SAMPLE_LINK01 =By.xpath("//a[@title='"+SAMPLE_TITLE01+" "+"']");

		info("Go to Site Explorer");
		goToSiteExplorer();

		info ("Create new content folder" );
		createNewContentFolder(FOLDER_NAME01,FOLDER_TITLE01);

		info("Verify folder is created successfully");
		assert isElementPresent(FOLDER_LINK01):"Content Folder is not created";

		info("Go to new folder");
		goToNode(FOLDER_LINK01);

		info ("Click on Add new content in action bar" );
		goToAddNewContent();

		info("Create new sample node");
		createNewSampleNode(SAMPLE_TITLE01,SAMPLE_NAME01,"");

		info("Verify document is created successfully");
		assert isElementPresent(SAMPLE_LINK01):"Sample Node document is not created";

		info ("Delete Content Folder" );
		deleteDocument(FOLDER_LINK01);
		pause(1000);
	}

	/* Case 2: Add Sample Node document in Document Folder
	 * Create 1 Document Folder
	 * Select Document Folder and verify that cannot created Sample Node document inside
	 * Delete Document Folder
	 */
	@Test
	public void test02_CreateSampleNodeInDocumentFolder()
	{   
		String FOLDER_NAME02="CREATE_07_02_Folder";
		String FOLDER_TITLE02="CREATE_07_02_Folder";
		By FOLDER_LINK02= By.xpath("//a[@title='"+FOLDER_TITLE02+" "+"']");

		info("Go to Site Explorer");
		goToSiteExplorer();

		info ("Create new document folder" );
		createNewDocumentFolder(FOLDER_TITLE02,FOLDER_NAME02);

		info("Verify document is created");
		assert isElementPresent(FOLDER_LINK02): "Document Folder is not created";

		info("Select created new folder");
		goToNode(FOLDER_LINK02);

		info ("Click on Add Content in action bar" );
		goToAddNewContent();

		info("Verify that Sample Node template is not existing");
		assert isElementNotPresent(SAMPLE_NODE_LINHTEXT):"Sample Node template still existing";

		info("Click on Cancel button");
		goToNode(CANCEL_CHOOSE_TEMPLATE);
		pause(500);

		info("Delete folder");
		deleteDocument(FOLDER_LINK02);
		pause(1000);

		info("Verify folder is not existing");
		assert isElementNotPresent(FOLDER_LINK02): "Document Folder still exisiting";
	}

	/* Case 3: Add Sample Node document in Article document
	 * Create 1 Article document
	 * Select Article and verify that cannot created Sample Node document inside
	 * Delete Article document
	 */
	@Test
	public void test03_CreateSampleNodeInArticle()
	{   
		String ARTICLE_NAME="CREATE_07_03_Article";
		String ARTICLE_TITLE="CREATE_07_03_Article";
		By ARTICLE_LINK= By.xpath("//a[@title='"+ARTICLE_TITLE+" "+"']");

		info("Go to Site Explorer");
		goToSiteExplorer();

		info("Click on Add Content icon in action bar");
		goToAddNewContent();

		info ("Create new Article document" );
		createNewArticle(ARTICLE_TITLE,ARTICLE_NAME, "This is summary", "This is content" );

		info("Verify that document is created sucessfully");
		assert isElementPresent(ARTICLE_LINK): "Article document is not created";

		info("Go to Article document");
		goToNode(ARTICLE_LINK);

		info("Click on Add Content icon in action bar");
		goToAddNewContent();

		info ("Check sample node template is not existing" );
		assert isElementNotPresent(SAMPLE_NODE_LINHTEXT):"Sample Node template still existing";

		info("Click on Cancel button");
		goToNode(CANCEL_CHOOSE_TEMPLATE);
		pause(1000);

		info("Delete document");
		deleteDocument(ARTICLE_LINK);
		pause(1000);
	}

	/* Case 4: Add Sample Node document in Sample Node document
	 * Create 1 Sample Node document
	 * Select Sample Node and verify that cannot created Sample Node document inside
	 * Delete Sample Node document
	 */
	@Test
	public void test06_CreateSampleNodeInSampleNode()
	{   
		String SAMPLE_NAME="CREATE_07_06_Sample";
		String SAMPLE_TITLE="CREATE_07_06_Sample";
		By SAMPLE_LINK= By.xpath("//a[@title='"+SAMPLE_TITLE+" "+"']");

		info("Go to Site Explorer");
		goToSiteExplorer();

		info ("Click on Add Content icon in action bar" );
		goToAddNewContent();

		info ("Create Sample Node document" );
		createNewSampleNode(SAMPLE_TITLE,SAMPLE_NAME,"");

		info("Verify document is created");
		assert isElementPresent(SAMPLE_LINK): "Sample Node document is not created";

		info("Go to created document");
		goToNode(SAMPLE_LINK);

		info("CLick on Add Document in action bar");
		goToAddNewContent();

		info("Verify Sample Node template is not existing");
		assert isElementNotPresent(SAMPLE_NODE_LINHTEXT):"Sample Node template still existing";

		info("Click on Cancel button");
		goToNode(CANCEL_CHOOSE_TEMPLATE);
		pause(1000);

		info("Delete document");
		deleteDocument(SAMPLE_LINK);
		pause(1000);
	}

	/* Case 5: Add Sample Node in File Plan document
	 * Create 1 File Plan document
	 * Select File Plan and verify that cannot created Sample Node document inside
	 * Delete File Plan document
	 */
	@Test
	public void test07_CreateSampleNodeInFilePlan()
	{  
		String FILEPLAN_NAME="CREATE_07_07_FilePlan";
		By FILEPLAN_LINK= By.xpath("//a[@title='"+FILEPLAN_NAME+" "+"']");

		info("Go to Site Explorer");
		goToSiteExplorer();

		info ("Click New content in action bar" );
		goToAddNewContent();

		info ("Create new File Plan document" );
		createNewFilePlan(FILEPLAN_NAME,"catInden","disAut", "oriOrg","event");

		info("Verify document is created");	      
		assert isElementPresent(FILEPLAN_LINK):"File Plan document is not created";

		info("Go to created document");
		goToNode(FILEPLAN_LINK);

		info ("Click New content in action bar" );
		goToAddNewContent();

		info ("Check Sample Node template is not existing" );
		assert isElementNotPresent(SAMPLE_NODE_LINHTEXT):"Sample Node template still existing";

		info("Click on Cancel button");
		goToNode(CANCEL_CHOOSE_TEMPLATE);
		pause(1000);

		info("Delete document");
		deleteDocument(FILEPLAN_LINK);
		pause(1000);
	}

	/* Case 6: Create Sample Node document in Kofax document
	 * Create 1 Kofax document
	 * Create 1 Sample Node document in Kofax document
	 * Delete Kofax document
	 */
	@Test
	public void test08_CreateSampleNodeInKofax()
	{   
		String KOFAX_NAME="CREATE_07_08_Kofax";
		By KOFAX_LINK= By.xpath("//a[@title='"+KOFAX_NAME+" "+"']");
		String SAMPLE_NAME08="CREATE_07_08_Sample";
		String SAMPLE_TITLE08="CREATE_07_08_Sample";
		By SAMPLE_LINK08= By.xpath("//a[@title='"+SAMPLE_TITLE08+" "+"']");

		info("Go to Site Explorer");
		goToSiteExplorer();

		info ("Click on Add Content icon" );
		goToAddNewContent();

		info("Create new Kofax document");
		createNewKofax(KOFAX_NAME);    

		info("Verify document is created");
		assert isElementPresent(KOFAX_LINK):"Kofax document is not created";

		info("Go to Kofax document");
		goToNode(KOFAX_LINK);

		info ("Click on Add Content in action bar" );
		goToAddNewContent();

		info ("Create new Sampe Node" );
		createNewSampleNode(SAMPLE_NAME08,SAMPLE_TITLE08,"");

		info("Verify document is created");
		assert isElementPresent(SAMPLE_LINK08): "Sample Node document is not created";
		pause (1000);

		info ("Delete Kofax" );
		deleteDocument(KOFAX_LINK);
		pause(1000);

	}

	/* Case 09: Create Sample Node In Uploaded File
	 * Upload a File
	 * Open this File
	 * Verify there is no New Content on action bar to choose
	 * Delete Uploaded file
	 */
	@Test
	public void test09_SampleNodeInUploadedFile()
	{
		By NEW_CONTENT_PATH=By.xpath("//a[@title='New Content']");
		String DATA_UPLOADED_FILE_NAME = "FNC_ECMS_FEX_CREATE_07_009";
		By UPLOADED_FILE = By.xpath("//a[@title='"+DATA_UPLOADED_FILE_NAME+".jpg "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Go to Site management/ acme");
		pause(500);
		goToNode(SITE_MANAGEMENT_ACME);

		info("Upload a File");
		pause(1000);
		uploadFile(DATA_UPLOADED_FILE_NAME, UPLOADED_FILE_PATH);

		info("Open File");
		goToNode(UPLOADED_FILE);

		info("Verify no 'New Content' on action bar");
		pause(1000);
		assert isElementNotPresent(NEW_CONTENT_PATH):"Failed: New content is displayed";

		info("Open File");
		pause(500);
		goToNode(UPLOADED_FILE);

		info("Delete data");
		pause(1000);
		deleteDocument(UPLOADED_FILE);

		info("Verify uploaded file is deleted");
		pause(1000);
	}

	/* Case 10: Create Sample Node Without Title
	 * Create Sample Node not fill title
	 * Verify message: Title is required
	 */
	@Test
	public void test10_SampleNodeWithoutTitle()
	{
		String MESSAGE_NO_TITLE ="The field \"Title\" is required.";
		String SAMPLE_NODE_TITLE="FNC_ECMS_FEX_CREATE_07_010";

		info("Go to CE");
		goToSiteExplorer();

		info("Go to Site management/ acme");
		pause(500);
		goToNode(SITE_MANAGEMENT_ACME);

		info("Click New Content");
		goToAddNewContent();

		info("Create Sample node");
		pause(500);
		createNewSampleNode("", SAMPLE_NODE_TITLE, UPLOADED_FILE_PATH);

		info("Verify message");
		pause(1000);
		assert isTextPresent(MESSAGE_NO_TITLE):"Message not found!";
	}
	
	/* Case 12: Create Sample Node In Locked Content Folder By Locker
	 * Create content folder
	 * Lock content folder
	 * Create Sample Node in locked content folder
	 * Verify Sample Node is created
	 * Delete locked content folder including sample node
	 */

	@Test
	public void test11_SampleNodeInLockedContentFolderByLocker()
	{
		String CONTENT_FOLDER_NAME="Content_Folder_To_Lock";
		String CONTENT_FOLDER_LOCK="//a[contains(text(), '"+CONTENT_FOLDER_NAME+"')]";
		String SAMPLE_NODE_TITLE="FNC_ECMS_FEX_CREATE_07_011";
		By CONTENT_FOLDER_PATH=By.xpath("//a[@title='"+CONTENT_FOLDER_NAME+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Go to Site management/ acme");
		pause(500);
		goToNode(SITE_MANAGEMENT_ACME);

		info("Create Content Folder");
		createNewContentFolder(CONTENT_FOLDER_NAME, CONTENT_FOLDER_NAME);
		pause(1000);

		info("Go to content folder");
		goToNode(CONTENT_FOLDER_PATH);

		info("Lock Node");
		lockNode(CONTENT_FOLDER_PATH);

		info("Verify node is locked");
		pause(1000);
		checkLockNode(CONTENT_FOLDER_LOCK);

		info("Add Content");
		goToAddNewContent();

		info("Create Sample node");
		createNewSampleNode(SAMPLE_NODE_TITLE, SAMPLE_NODE_TITLE,UPLOADED_FILE_PATH);

		info("Verify content of Sample Node");
		pause(1000);
		assert isTextPresent(SAMPLE_NODE_TITLE):"This is not content of sample node";

		info("Go to content folder");
		goToNode(By.xpath(CONTENT_FOLDER_LOCK));

		info("Delete Content Folder including sample node");
		pause(500);
		deleteDocument(By.xpath(CONTENT_FOLDER_LOCK));
		pause(1000);
	}

	/* Case 12: Create Sample Node In Locked Document Folder By Locker
	 * Create document folder
	 * Lock document folder
	 * Click New Content on action bar
	 * Verify there is no Sample Node in the list
	 * Delete locked document folder
	 */

	@Test
	public void test12_SampleNodeInLockedDocumentFolderByLocker()
	{
		String DOCUMENT_FOLDER_NAME="Document_Folder_To_Lock";
		By DOCUMENT_FOLDER_PATH=By.xpath("//a[@title='"+DOCUMENT_FOLDER_NAME+" "+"']");
		String DOCUMENT_FOLDER_LOCK="//a[contains(text(), '"+DOCUMENT_FOLDER_NAME+"')]";

		info("Go to CE");
		goToSiteExplorer();

		info("Go to Site management/ acme");
		pause(500);
		goToNode(SITE_MANAGEMENT_ACME);

		info("Create Document Folder");
		createNewDocumentFolder(DOCUMENT_FOLDER_NAME, DOCUMENT_FOLDER_NAME);
		pause(500);

		info("Go to Document Folder");
		goToNode(DOCUMENT_FOLDER_PATH);

		info("Lock Node");
		pause(1000);
		lockNode(DOCUMENT_FOLDER_PATH);
		pause(1000);

		info("Verify node is locked");
		checkLockNode(DOCUMENT_FOLDER_LOCK);

		info("Add Content");
		goToAddNewContent();

		info("Verify Sample Node is not in template list");
		pause(1000);
		assert isElementNotPresent(SAMPLE_NODE_LINHTEXT):"Failed: Sample node found";
		info("you can not add an Sample Node in a locked document folder");

		info("Go to Document Folder");
		goToNode(By.xpath(DOCUMENT_FOLDER_LOCK));

		info("Delete Document Folder");
		pause(1000);
		deleteDocument(By.xpath(DOCUMENT_FOLDER_LOCK));
		pause(1000);
	}

	/* Case 13: Create Sample Node In Locked Kofax document By Locker
	 * Create Kofax
	 * Lock Kofax
	 * Create Sample node in locked Kofax
	 * Verify Sample Node is created
	 * Delete locked Kofax including sample node
	 */

	@Test
	public void test13_SampleNodeInLockedKoFaxDocumentByLocker()
	{
		String KOFAX_NAME="Kofax_Document_To_Lock";
		By KOFAX_PATH=By.xpath("//a[@title='"+KOFAX_NAME+" "+"']");
		String KOFAX_LOCK="//a[contains(text(), '"+KOFAX_NAME+"')]";
		String SAMPLE_NODE_TITLE="FNC_ECMS_FEX_CREATE_07_013";


		info("Go to CE");
		goToSiteExplorer();

		info("Go to Site management/ acme");
		pause(500);
		goToNode(SITE_MANAGEMENT_ACME);

		info("Click New Content");
		goToAddNewContent();

		info("Create Kofax Document");
		createNewKofax(KOFAX_NAME);

		info("Lock Node");
		pause(1000);
		lockNode(KOFAX_PATH);
		pause(1000);

		info("Verify node is locked");
		checkLockNode(KOFAX_LOCK);

		info("Add Content");
		goToAddNewContent();

		info("Create Sample node");
		createNewSampleNode(SAMPLE_NODE_TITLE, SAMPLE_NODE_TITLE, UPLOADED_FILE_PATH);

		info("Verify content of Sample Node");
		pause(1000);
		assert isTextPresent(SAMPLE_NODE_TITLE):"Text not found";
		info("you can add a Sample Node in a locked Kofax document");		

		info("Click Kofax Document");
		goToNode(By.xpath(KOFAX_LOCK));

		info("Delete Kofax Document including Sample Node");
		pause(1000);
		deleteDocument(By.xpath(KOFAX_LOCK));
		pause(1000);
	}
	/* Case 14_01: Create Sample Node In Locked Article By Locker
	 * Create Article
	 * Lock article
	 * Click New Content on action bar
	 * Verify there is no Sample Node in the list
	 * Delete locked Article
	 */

	@Test
	public void test14_01_SampleNodeInLockedArticleByLocker()
	{
		String ARTICLE_NAME="Article_To_Lock";
		String ARTICLE_SUMMARY="Summary of article";
		String ARTICLE_CONTENT="Content of artile";
		String ARTICLE_LOCK="//a[contains(text(), '"+ARTICLE_NAME+"')]";
		By ARTICLE_PATH=By.xpath("//a[@title='"+ARTICLE_NAME+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Go to Site management/ acme");
		pause(500);
		goToNode(SITE_MANAGEMENT_ACME);

		info("Click New Content");
		goToAddNewContent();

		info("Create Article");
		pause(500);
		createNewArticle(ARTICLE_NAME, ARTICLE_NAME, ARTICLE_SUMMARY,ARTICLE_CONTENT );

		info("Lock Node");
		pause(1000);
		lockNode(ARTICLE_PATH);
		pause(1000);

		info("Verify node is locked");
		checkLockNode(ARTICLE_LOCK);

		info("Add Content");
		goToAddNewContent();

		info("Verify Sample Node is not in template list");
		pause(1000);
		assert isElementNotPresent(SAMPLE_NODE_LINHTEXT):"Failed: sample node in the list";
		info("you can not add an Sample Node in a locked Article");

		info("Click Article");
		goToNode(By.xpath(ARTICLE_LOCK));

		info("Delete Article");
		pause(1000);
		deleteDocument(By.xpath(ARTICLE_LOCK));
		pause(1000);
	}

	/* Case 14_02: Create Sample Node In Locked SampleNode By Locker
	 * Create SampleNode
	 * Lock SampleNode
	 * Click New Content on action bar
	 * Verify there is no Sample Node in the list
	 * Delete locked SampleNode
	 */

	@Test
	public void test14_02_SampleNodeInLockedSampleNodeByLocker()
	{
		String SAMPLE_NODE_NAME="SampleNode_To_Lock";
		String SAMPLE_NODE_LOCK="//a[contains(text(), '"+SAMPLE_NODE_NAME+"')]";
		By SAMPLE_NODE_PATH=By.xpath("//a[@title='"+SAMPLE_NODE_NAME+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Go to Site management/ acme");
		pause(500);
		goToNode(SITE_MANAGEMENT_ACME);

		info("Click New Content");
		goToAddNewContent();

		info("Create Sample node");
		createNewSampleNode(SAMPLE_NODE_NAME, SAMPLE_NODE_NAME, UPLOADED_FILE_PATH);

		info("Lock Node");
		pause(1000);
		lockNode(SAMPLE_NODE_PATH);
		pause(500);

		info("Verify node is locked");
		checkLockNode(SAMPLE_NODE_LOCK);

		info("Add Content");
		goToAddNewContent();

		info("Verify Sample Node is not in template list");
		pause(1000);
		assert isElementNotPresent(SAMPLE_NODE_LINHTEXT):"Failed: sample node in the list";
		info("you can not add an Sample Node in a sample node");	

		info("Click SampleNode");
		goToNode(By.xpath(SAMPLE_NODE_LOCK));

		info("Delete Sample node");
		pause(500);
		deleteDocument(By.xpath(SAMPLE_NODE_LOCK));
		pause(500);
	}

	/* Case 14_03: Create Sample Node In Locked FilePlan By Locker
	 * Create File plan
	 * Lock File plan
	 * Click New Content on action bar
	 * Verify there is no Sample Node in the list
	 * Delete locked File plan
	 */

	@Test
	public void test14_03_SampleNodeInLockedFilePlanByLocker()
	{
		String FILE_PLAN_INFO="FilePlan_To_Lock";
		String FILE_PLAN_LOCK="//a[contains(text(),'"+FILE_PLAN_INFO+"')]";
		By FILE_PLAN_PATH=By.xpath("//a[@title='"+FILE_PLAN_INFO+" "+"']");

		info("Go to CE");
		goToSiteExplorer();

		info("Go to Site management/ acme");
		pause(500);
		goToNode(SITE_MANAGEMENT_ACME);

		info("Click New Content");
		goToAddNewContent();

		info("Create File plan");
		createNewFilePlan(FILE_PLAN_INFO, FILE_PLAN_INFO, FILE_PLAN_INFO, FILE_PLAN_INFO, FILE_PLAN_INFO);

		info("Lock Node");
		pause(1000);
		lockNode(FILE_PLAN_PATH);
		pause(500);

		info("Verify node is locked");
		checkLockNode(FILE_PLAN_LOCK);

		info("Add Content");
		goToAddNewContent();

		info("Verify Sample Node is not in template list");
		pause(1000);
		assert isElementNotPresent(SAMPLE_NODE_LINHTEXT):"Failed: sample node in the list";
		info("you can not add an Sample Node in a sample node");	

		info("Go To File Plan");
		goToNode(By.xpath(FILE_PLAN_LOCK));

		info("Delete File plan");
		pause(500);
		deleteDocument(By.xpath(FILE_PLAN_LOCK));
		pause(500);
	}
}