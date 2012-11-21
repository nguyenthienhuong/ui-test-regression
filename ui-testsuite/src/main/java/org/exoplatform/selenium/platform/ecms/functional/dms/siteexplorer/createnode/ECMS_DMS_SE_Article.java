package org.exoplatform.selenium.platform.ecms.functional.dms.siteexplorer.createnode;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.TestLogger.*;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.*;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;

/*
 * @author: Lientm
 * @date: 8/2012
 */
public class ECMS_DMS_SE_Article extends EcmsBase{

	public static final String DATA_USER = "john";
	public static final String DATA_PASS = "gtn";

	public static final By ELEMENT_NEW_CONTENT_LINK_XPATH =By.xpath("//a[@title = 'New Content']") ; 

	public static final By ELEMENT_NEW_ARTICLE_POPUP_XPATH = By.xpath("//div[@class='UIPopupWindow UIDragObject ExoMessageDecorator']");
	public static final By ELEMENT_NEW_ARTICLE_MESSAGE_XPATH = By.xpath("//span[@class='PopupIcon WarningMessageIcon']");
	public static final String ELEMENT_NEW_ARTICLE_MESSAGE_TITLE_BLANK = "The field \"Title\" is required.";
	public static final String ELEMENT_NEW_ARTICLE_MESSAGE_NAME_BLANK = "The field \"Name\" is required.";
	public static final String ELEMENT_NEW_ARTICLE_MESSAGE_SUMMARY_BLANK = "Summary is empty";
	public static final String ELEMENT_NEW_ARTICLE_MESSAGE_CONTENT_BLANK = "Content is empty";

	@BeforeMethod
	public void beforeMethods() throws Exception {
		initSeleniumTest();
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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

	/*case1: Add Article document in Content folder
	 * create a new content folder
	 * add new article to content folder just add
	 * delete content folder
	 */
	@Test
	public void test01_AddArticleToContentFolder(){
		String DATA_CONTENT_FOLDER_TITLE = "ECMS_DMS_SE_Article_Contentfolder_01";
		By ELEMENT_CONTENT_FOLDER = By.xpath("//a[@title='" + DATA_CONTENT_FOLDER_TITLE + " " +"']");
		String DATA_ARTICLE_TITLE = "ECMS_DMS_SE_Article_article_01";
		By ELEMENT_ARTICLE = By.xpath("//a[@title='" + DATA_ARTICLE_TITLE +" " + "']");

		//create new content folder
		goToSiteExplorer();

		debug("Create new content folder with title:"+ DATA_CONTENT_FOLDER_TITLE);
		createNewContentFolder(DATA_CONTENT_FOLDER_TITLE,DATA_CONTENT_FOLDER_TITLE);
		waitForElementPresent(ELEMENT_CONTENT_FOLDER);

		//add new article to content folder just add
		goToNode(ELEMENT_CONTENT_FOLDER);
		goToAddNewContent();

		debug("Add new article document with title:"+DATA_ARTICLE_TITLE);
		createNewArticle(DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE);
		waitForElementPresent(ELEMENT_ARTICLE);


		deleteDocument(ELEMENT_ARTICLE);

		//delete content folder
		goToNode(ELEMENT_CONTENT_FOLDER);
		deleteDocument(ELEMENT_CONTENT_FOLDER);
	}

	/*case2: Add Article document in Document folder
	 * create new document folder
	 * check article link is not in the template list
	 * delete document folder
	 */
	@Test
	public void test02_AddArticleToDocumentFolder(){
		String DATA_DOCUMENT_FOLDER_TITLE = "ECMS_DMS_SE_Article_document_folder_02";
		By ELEMENT_DOCUMENT_FOLDER = By.xpath("//a[@title='"+DATA_DOCUMENT_FOLDER_TITLE+" "+"']");

		//create new document folder
		goToSiteExplorer();

		debug("Create new document folder with title:"+ DATA_DOCUMENT_FOLDER_TITLE);
		createNewDocumentFolder(DATA_DOCUMENT_FOLDER_TITLE, DATA_DOCUMENT_FOLDER_TITLE);
		waitForElementPresent(ELEMENT_DOCUMENT_FOLDER);

		//check article link is not in the template list
		goToNode(ELEMENT_DOCUMENT_FOLDER);
		goToAddNewContent();
		waitForElementNotPresent(ELEMENT_ARTICLE_LINK);
		//delete document folder
		goToNode(ELEMENT_DOCUMENT_FOLDER);
		deleteDocument(ELEMENT_DOCUMENT_FOLDER);
	}

	/*case3: Add Article document in Article document
	 * add new article
	 * check cannot add article to article
	 * delete document
	 */
	@Test
	public void test03_AddArticleToArticleDocument(){
		String DATA_ARTICLE_TITLE = "ECMS_DMS_SE_Article_article_03";
		By ELEMENT_ARTICLE = By.xpath("//a[@title='"+DATA_ARTICLE_TITLE+" "+"']");

		//create new article
		goToSiteExplorer();
		goToAddNewContent();

		info("Create new article with title:"+DATA_ARTICLE_TITLE);
		createNewArticle(DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE);
		waitForElementPresent(ELEMENT_ARTICLE);

		//check cannot add article to article
		goToNode(ELEMENT_ARTICLE);
		goToAddNewContent();
		waitForElementNotPresent(ELEMENT_ARTICLE_LINK);
		//delete article document
		goToNode(ELEMENT_ARTICLE);
		deleteDocument(ELEMENT_ARTICLE);
	}

	/*case4: Add Article document in File document
	 * add a new File document
	 * check cant not add new article document to File document
	 * delete new File document
	 */
	@Test
	public void test04_AddArticleToFileDocument(){
		String DATA_FILE_DOCUMENT_TITLE = "ECMS_DMS_SE_Article_filedocument_04";
		By ELEMENT_FILE_DOCUMENT = By.xpath("//a[@title='"+DATA_FILE_DOCUMENT_TITLE+" "+"']");

		//add new File Document
		goToSiteExplorer();
		goToAddNewContent();
		debug("Create new file document with title:"+DATA_FILE_DOCUMENT_TITLE);
		createNewFile(DATA_FILE_DOCUMENT_TITLE, DATA_FILE_DOCUMENT_TITLE, DATA_FILE_DOCUMENT_TITLE);
		waitForElementPresent(ELEMENT_FILE_DOCUMENT);
		info("Create new file document successfully");
		//check cannot add article to file document
		goToNode(ELEMENT_FILE_DOCUMENT);
		waitForElementNotPresent(ELEMENT_NEW_CONTENT_LINK_XPATH);
		//delete file document
		deleteDocument(ELEMENT_FILE_DOCUMENT);
	}

	/*case5: Add Article document in Podcast document
	 * add new Podcast 
	 * check cannot add article to podcast document
	 * delete podcast document
	 */
	@Test
	public void test05_AddArticleToPodcastDocument(){
		String DATA_PODCAST_DOCUMENT_TITLE = "ECMS_DMS_SE_Article_podcast_05";
		By ELEMENT_PODCAST = By.xpath("//a[@title='"+DATA_PODCAST_DOCUMENT_TITLE+" "+"']");

		//add new podcast document
		goToSiteExplorer();
		goToAddNewContent();

		debug("Create new podcast document with title:"+DATA_PODCAST_DOCUMENT_TITLE);
		createNewPodcast(DATA_PODCAST_DOCUMENT_TITLE, DATA_PODCAST_DOCUMENT_TITLE, DATA_PODCAST_DOCUMENT_TITLE);
		waitForElementPresent(ELEMENT_PODCAST);

		info("Create new podcast document successfully");
		//check cannot add article to podcast document -> has not new content link
		goToNode(ELEMENT_PODCAST);
		waitForElementNotPresent(ELEMENT_NEW_CONTENT_LINK_XPATH);
		//delete podcast
		deleteDocument(ELEMENT_PODCAST);
	}

	/*case6: Add Article document in Sample Node document
	 * add new sample node document
	 * check cant not add article to sample node document
	 * delete sample node document
	 */
	@Test
	public void test06_AddArticleToSampleNode(){
		String DATA_SAMPLE_NODE_TITLE = "ECMS_DMS_SE_Article_samplenode_06";
		String DATA_SAMPLE_NODE_IMG = "TestData/ECMS_DMS_SE_Article.jpg";
		By ELEMENT_SAMPLE_NODE = By.xpath("//a[@title='"+DATA_SAMPLE_NODE_TITLE+" "+"']");

		//add new sample node document
		goToSiteExplorer();
		goToAddNewContent();
		debug("Create new sample node with title:"+DATA_SAMPLE_NODE_TITLE);
		createNewSampleNode(DATA_SAMPLE_NODE_TITLE, DATA_SAMPLE_NODE_TITLE, DATA_SAMPLE_NODE_IMG);
		waitForElementPresent(ELEMENT_SAMPLE_NODE);
		info("Create new sample document successfully");
		//check no add article to sample document -. has not article link
		goToNode(ELEMENT_SAMPLE_NODE);
		goToAddNewContent();
		waitForElementNotPresent(ELEMENT_ARTICLE_LINK);
		//delete sample node
		goToNode(ELEMENT_SAMPLE_NODE);
		deleteDocument(ELEMENT_SAMPLE_NODE);
	}

	/*case7: Add Article document in File Plan document
	 * add new File plan document
	 * check cant not add article to File plan document
	 * delete file plan document
	 */
	@Test
	public void test07_AddArticleToFilePlan(){
		String DATA_FILE_PLAN_TITLE = "ECMS_DMS_SE_Article_article_07";
		By ELEMENT_FILE_PLAN = By.xpath("//a[@title='"+DATA_FILE_PLAN_TITLE+" "+"']");

		//add new file plan document
		goToSiteExplorer();
		goToAddNewContent();
		debug("Create new file plan with title:"+DATA_FILE_PLAN_TITLE);
		createNewFilePlan(DATA_FILE_PLAN_TITLE, DATA_FILE_PLAN_TITLE, DATA_FILE_PLAN_TITLE, DATA_FILE_PLAN_TITLE, DATA_FILE_PLAN_TITLE);
		waitForElementPresent(ELEMENT_FILE_PLAN);
		info("Create new file plan document successfully");
		//check cannot add article to podcast document -> has not article link
		goToNode(ELEMENT_FILE_PLAN);
		goToAddNewContent();
		waitForElementNotPresent(ELEMENT_ARTICLE_LINK);
		//delete file plan
		goToNode(ELEMENT_FILE_PLAN);
		deleteDocument(ELEMENT_FILE_PLAN);
	}

	/*case8: Add Article document in Kofax document
	 * add new kofax document
	 * add new article document to kofax document
	 * check add document successfully
	 * delete kofax document
	 */
	@Test
	public void test08_AddArticleToKofax(){
		String DATA_ARTICLE_TITLE = "ECMS_DMS_SE_Article_article_08";
		By ELEMENT_ARTICLE = By.xpath("//a[@title='"+DATA_ARTICLE_TITLE+" "+"']");
		String DATA_KOFAX_TITLE = "ECMS_DMS_SE_Article_kofax_08";
		By ELEMENT_KOFAX = By.xpath("//a[@title='"+DATA_KOFAX_TITLE+" "+"']");

		//add new kofax document
		goToSiteExplorer();
		goToAddNewContent();
		debug("Create new kofax document with title:"+ DATA_KOFAX_TITLE);
		createNewKofax(DATA_KOFAX_TITLE);
		waitForElementPresent(ELEMENT_KOFAX);
		info("Create new kofax document successfully");
		//add new article document
		goToNode(ELEMENT_KOFAX);
		goToAddNewContent();
		debug("Add new article document to kofax document with title:"+DATA_ARTICLE_TITLE);
		createNewArticle(DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE);
		waitForElementPresent(ELEMENT_ARTICLE);
		info("Add new article document to a kofax document successfully");
		deleteDocument(ELEMENT_ARTICLE);
		//delete kofax document
		goToNode(ELEMENT_KOFAX);
		deleteDocument(ELEMENT_KOFAX);
	}

	/*case9: Select an uploaded file and see on action bar
	 * upload a file
	 * check cannot add article document to this uploaded file
	 * delete uploaded file 
	 */
	@Test
	public void test09_AddArticleToUploadFile(){
		String DATA_UPLOAD_FILE_NAME = "ECMS_DMS_SE_Article_upload_09";
		String DATA_UPLOAD_FILE_LINK = "TestData/ECMS_DMS_SE_Article.jpg";
		By ELEMENT_UPLOAD = By.xpath("//a[@title='"+DATA_UPLOAD_FILE_NAME+".jpg "+"']");

		//upload file
		goToSiteExplorer();
		debug("Upload new file with nam:"+DATA_UPLOAD_FILE_NAME);
		uploadFile(DATA_UPLOAD_FILE_NAME, DATA_UPLOAD_FILE_LINK);
		waitForElementPresent(ELEMENT_UPLOAD);
		info("Upload file successfully");
		//check cannot add article document to uploaded file: has not new content link
		goToNode(ELEMENT_UPLOAD);
		waitForElementNotPresent(ELEMENT_NEW_CONTENT_LINK_XPATH);
		//delete uploaded file
		deleteDocument(ELEMENT_UPLOAD);
	}

	/*case10: Add Article with blank name or title
	 * add article document with name blank or title blank
	 */
	@Test
	public void test10_AddArticleWithNameOrTitleBlank(){
		String DATA_ARTICLE_TITLE = "ECMS_DMS_SE_Article_article_10";

		//add article with title blank
		goToSiteExplorer();
		goToNode(ELEMENT_NEW_CONTENT_LINK_XPATH);
		createNewArticle("", DATA_ARTICLE_TITLE, "", "");
		assert isElementPresent(ELEMENT_NEW_ARTICLE_POPUP_XPATH):"No title blank alert";
		//check message when save
		assert getText(ELEMENT_NEW_ARTICLE_MESSAGE_XPATH).contains(ELEMENT_NEW_ARTICLE_MESSAGE_TITLE_BLANK):"Wrong message";
		click(By.linkText("OK"));
		//add article with name blank;
		type(ELEMENT_ARTICLE_TITLE_TEXTBOX,DATA_ARTICLE_TITLE,false);
		waitForAndGetElement(ELEMENT_ARTICLE_NAME_TEXTBOX).clear();
		click(ELEMENT_SAVE_CLOSE_BUTTON);
		assert isElementPresent(ELEMENT_NEW_ARTICLE_POPUP_XPATH):"No name blank alert";
		//check message when save
		assert getText(ELEMENT_NEW_ARTICLE_MESSAGE_XPATH).contains(ELEMENT_NEW_ARTICLE_MESSAGE_NAME_BLANK):"Wrong message";
		click(By.linkText("OK"));		
	}

	/*case11: Add Article with blank 'Summary' or 'Content'
	 * create new article with summary or conntent blank
	 * view summary & content
	 * delete this article document
	 */
	@Test
	public void test11_AddArticleWithSummaryOrContentBlank(){
		String DATA_ARTICLE_TITLE = "ECMS_DMS_SE_Article_article_11";
		By ELEMENT_ARTICLE = By.xpath("//a[@title='"+DATA_ARTICLE_TITLE+" "+"']");

		//create new article with summary blank
		goToSiteExplorer();
		goToNode(ELEMENT_NEW_CONTENT_LINK_XPATH);
		debug("Create new article with title:"+DATA_ARTICLE_TITLE+" and summary, content blank");
		createNewArticle(DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, "", "");
		waitForElementPresent(ELEMENT_ARTICLE);

		//view summary & content
		assert isTextPresent(ELEMENT_NEW_ARTICLE_MESSAGE_SUMMARY_BLANK):"Wrong message";
		assert isTextPresent(ELEMENT_NEW_ARTICLE_MESSAGE_CONTENT_BLANK):"Wrong message";
		//delete data
		deleteDocument(ELEMENT_ARTICLE);
	}

	/*case12: Add Article document in a locked Content folder by locker
	 * create new content folder
	 * lock this content folder
	 * add new article document to this content folder successfully
	 * delete content folder
	 */
	@Test
	public void test12_AddArticleToLockedContentFolder(){
		String DATA_CONTENT_FOLDER_TITLE = "ECMS_DMS_SE_Article_contentfolder_12";
		By ELEMENT_CONTENT_FOLDER = By.xpath("//a[contains(text(),'"+DATA_CONTENT_FOLDER_TITLE+"')]");
		String DATA_ARTICLE_TITLE = "ECMS_DMS_SE_Article_article_12";
		By ELEMENT_ARTICLE = By.xpath("//a[@title='"+DATA_ARTICLE_TITLE+" "+"']");

		//create new content folder
		goToSiteExplorer();
		debug("Create new content folder with title: "+DATA_CONTENT_FOLDER_TITLE);
		createNewContentFolder(DATA_CONTENT_FOLDER_TITLE,DATA_CONTENT_FOLDER_TITLE);
		waitForElementPresent(ELEMENT_CONTENT_FOLDER);
		info("Create new content foldeR successfully");
		//lock this content folder
		lockNode(ELEMENT_CONTENT_FOLDER);
		//check lock successfully
		assert checkLockNode(ELEMENT_CONTENT_FOLDER):"Can not lock this content folder";
		//add new article to this content folder successfully
		goToNode(ELEMENT_CONTENT_FOLDER);
		goToAddNewContent();
		debug("Add new article with title: "+DATA_ARTICLE_TITLE);
		createNewArticle(DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE);
		waitForElementPresent(ELEMENT_ARTICLE);

		//delete article
		deleteDocument(ELEMENT_ARTICLE);

		//delete content folder
		goToNode(ELEMENT_CONTENT_FOLDER);
		deleteDocument(ELEMENT_CONTENT_FOLDER);
	}

	/*case13: Add Article document in a locked Document folder by locker
	 * create new document folder
	 * lock this document folder
	 * check cannot add new article to document folder
	 * delete document folder
	 */
	@Test
	public void test13_AddArticleToLockedDocumentFolder(){
		String DATA_DOCUMENT_FOLDER_TITLE = "ECMS_DMS_SE_Article_documentfolder_13";
		By ELEMENT_DOCUMENT_FOLDER = By.xpath("//a[contains(text(),'"+DATA_DOCUMENT_FOLDER_TITLE+"')]");


		//create new document folder
		goToSiteExplorer();
		debug("Create new document folder with title: "+DATA_DOCUMENT_FOLDER_TITLE);
		createNewDocumentFolder(DATA_DOCUMENT_FOLDER_TITLE, DATA_DOCUMENT_FOLDER_TITLE);
		waitForElementPresent(ELEMENT_DOCUMENT_FOLDER);
		info("create new document folder successfully");
		//lock this document folder
		lockNode(ELEMENT_DOCUMENT_FOLDER);
		//check lock successfully
		assert checkLockNode(ELEMENT_DOCUMENT_FOLDER):"Can not lock this document folder";
		//check cannot add new article to document folder -> has not article link
		goToNode(ELEMENT_DOCUMENT_FOLDER);
		goToAddNewContent();
		waitForElementNotPresent(ELEMENT_ARTICLE_LINK);
		//delete document folder
		goToNode(ELEMENT_DOCUMENT_FOLDER);
		deleteDocument(ELEMENT_DOCUMENT_FOLDER);
	}


	/*case14: Add Article document in a locked node: Kofax document  by locker
	 * create new Kofax document
	 * lock this Kofax document
	 * add new article to this Kofax Document successfully
	 * delete Kofax document
	 */
	@Test
	public void test14_AddArticleToLockedKofaxDocument(){
		String DATA_KOFAX_TITLE = "ECMS_DMS_SE_Article_kofax_14";
		By ELEMENT_KOFAX = By.xpath("//a[contains(text(),'"+DATA_KOFAX_TITLE+"')]");
		String DATA_ARTICLE_TITLE = "ECMS_DMS_SE_Article_article_14";
		By ELEMENT_ARTICLE = By.xpath("//a[@title='"+DATA_ARTICLE_TITLE+" "+"']");

		//add new kofax document
		goToSiteExplorer();
		goToAddNewContent();
		debug("Create new kofax document with title "+DATA_KOFAX_TITLE);
		createNewKofax(DATA_KOFAX_TITLE);
		waitForElementPresent(ELEMENT_KOFAX);
		info("Create new kofax document successful");
		//lock this Kofax
		lockNode(ELEMENT_KOFAX);
		//check lock successfully
		assert checkLockNode(ELEMENT_KOFAX):"Can not lock fofax document";
		//add new article document successfully
		goToNode(ELEMENT_KOFAX);
		//Add new article into kofax document
		goToAddNewContent();
		debug("add new article with title: "+DATA_ARTICLE_TITLE);
		createNewArticle(DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE);
		waitForElementPresent(ELEMENT_ARTICLE);
		info("Add new article into koafax document successfully");

		//delete article
		deleteDocument(ELEMENT_ARTICLE);

		//delete kofax document
		goToNode(ELEMENT_KOFAX);
		deleteDocument(ELEMENT_KOFAX);
	}

	/*case15: Add Article document in a locked node (Article, Sample node, File Flan document) by locker
	 * create new Sample node
	 * lock this Sample node
	 * check cannot add new article to this Sample node
	 * delete this Sample node
	 */
	@Test
	public void test15_AddArticleToLockedSampleNode(){
		String DATA_SAMPLE_NODE_TITLE = "ECMS_DMS_SE_Article_samplenode_15";
		String DATA_SAMPLE_NODE_IMG = "TestData/ECMS_DMS_SE_Article.jpg";
		By ELEMENT_SAMPLE_NODE = By.xpath("//a[contains(text(),'"+DATA_SAMPLE_NODE_TITLE+"')]");

		//add new sample node document
		goToSiteExplorer();
		goToAddNewContent();
		debug("create new sample node with title: "+DATA_SAMPLE_NODE_TITLE);
		createNewSampleNode(DATA_SAMPLE_NODE_TITLE, DATA_SAMPLE_NODE_TITLE, DATA_SAMPLE_NODE_IMG);
		waitForElementPresent(ELEMENT_SAMPLE_NODE);
		info("Create new sample node successfully");
		//lock this sample node document
		lockNode(ELEMENT_SAMPLE_NODE);
		//check lock successfully
		assert checkLockNode(ELEMENT_SAMPLE_NODE):"Can not lock sample node";
		//check cannot add article to sample node
		goToNode(ELEMENT_SAMPLE_NODE);
		goToAddNewContent();
		waitForElementNotPresent(ELEMENT_ARTICLE_LINK);
		//delete sample node
		goToNode(ELEMENT_SAMPLE_NODE);
		pause(500);
		deleteDocument(ELEMENT_SAMPLE_NODE);
	}
}