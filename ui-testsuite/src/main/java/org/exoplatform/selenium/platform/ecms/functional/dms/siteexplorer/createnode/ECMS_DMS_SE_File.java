package org.exoplatform.selenium.platform.ecms.functional.dms.siteexplorer.createnode;

import static org.exoplatform.selenium.TestLogger.*;
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
 * @author: Lientm
 * @date: 9/2012
 */
public class ECMS_DMS_SE_File extends EcmsBase {
	
	public static final String DATA_USER = "john";
	public static final String DATA_PASS = "gtn";
	
	public static final By ELEMENT_NEW_CONTENT_LINK =By.xpath("//a[@title = 'New Content']") ; 
	public static final String ELEMENT_DMS_OPTION_CHECKBOX_ID = "enableStructure";
	public static final By ELEMENT_FILE_PLAN_NUMBER_RECORD_XPATH = By.xpath("//*[@id='UIFilePlanView']/div[1]/table/tbody/tr[9]/td[2]");
	public static final By ELEMENT_NEWFILE_LINK_XPATH = By.xpath("//div[@title='File']");
	public static final By ELEMENT_NEWFILE_POPUP_MESSAGE_XPATH =By.xpath("//div[@class='UIPopupWindow UIDragObject ExoMessageDecorator']");
	public static final By ELEMENT_NEWFILE_MESSAGE_XPATH = By.xpath("//span[@class='PopupIcon WarningMessageIcon']");
	public static final String ELEMENT_NEWFILE_MESSAGE_NAME_BLANK = "The field \"Name\" is required.";
	public static final String ELEMENT_NEWFILE_MESSAGE_CONTENT_BLANK = "The field \"Content\" is required.";
	public static final String DATA_NEWFILE_SOURCE_CONTENT = "<p>new_file_test</p>";
	public static final String DATA_NEWFILE_NOSOURCE_CONTENT = "<p> new_file_test</p>";
	public static final String DATA_NEWFILE_SOURCE_CONTENT_VIEW = "new_file_test";
	public static final By ELEMENT_NEWFILE_SOURCE_LINK_XPATH = By.xpath("//a[@title='Source']");
	public static final By ELEMENT_NEWFILE_SOURCE_TEXTAREA_XPATH = By.xpath("//textarea[@class='cke_source cke_enable_context_menu']");
	public static final By ELEMENT_NEWFILE_FRAME_HTML_TAB_XPATH = By.xpath("//iframe[@class='ECMIframe']");
	public static final By ELEMENT_NEWFILE_HTML_TAB_P_XPATH =By.xpath("//html/body/p") ;
	public static final By ELEMENT_NEWFILE_TEXT_TAB_XPATH = By.xpath("//div[contains(text(),'View as Plain text')]");
	public static final String ELEMENT_NEWFILE_TEXT_TAB_P_CSS = ".TextContent>pre";
	public static final By ELEMENT_NEWFILE_MIME_COMBOX_ID =By.id("mimetype") ;
	public static final By ELEMENT_NEWFILE_TEXTAREA_ID =By.id("contentPlain") ;
	public static final String DATA_NEWFILE_TEXTAREA_CONTENT ="content text plain" ;
	public static final String ELEMENT_NEWFILE_PRE_CSS = ".Content>pre";
	
	@BeforeMethod
 	public void beforeMethods() throws Exception {
		initSeleniumTest();
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(baseUrl);
		actions = new Actions(driver);
		info("Login ECMS with user: "+DATA_USER);
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


	 /* case1: Add File document in Content folder 
	 * crate a new content folder 
	 * add new file document to this content folder 
	 * check add successfully
	 * delete this content folder logout
	 */
	@Test
	public void test01_AddFileDocumentToContentFolder() {
		String DATA_CONTENT_FOLDER_TITLE = "ECMS_DMS_SE_File_contentfolder_01";
		By ELEMENT_CONTENT_FOLDER = By.xpath("//a[@title='"+DATA_CONTENT_FOLDER_TITLE+" "+"']");
		String DATA_FILE_NAME = "ECMS_DMS_SE_File_filedocument_01";
		By ELEMENT_FILE_DOCUMENT = By.xpath("//a[@title='"+DATA_FILE_NAME+" "+"']");
		
		//create new content folder
		goToSiteExplorer();
		debug("create new content with title:"+DATA_CONTENT_FOLDER_TITLE);
		createNewContentFolder(DATA_CONTENT_FOLDER_TITLE,DATA_CONTENT_FOLDER_TITLE);
		waitForElementPresent(ELEMENT_CONTENT_FOLDER);
		info("Create new content folder successfully");
		
		//add new file to content folder just add
		goToNode(ELEMENT_CONTENT_FOLDER);
		goToAddNewContent();
		debug("add new file document with name:"+DATA_FILE_NAME);
		createNewFile(DATA_FILE_NAME, DATA_FILE_NAME, DATA_FILE_NAME);
		waitForElementPresent(ELEMENT_FILE_DOCUMENT);
		info("Add new file document in content folder successfully");
		
		//delete content folder
		goToNode(ELEMENT_CONTENT_FOLDER);
		deleteDocument(ELEMENT_CONTENT_FOLDER);
	}
	

	/*case2: Add File document in Document folder
	 * login
	 * create new document folder
	 * add new file document to this document folder
	 * check add successfully
	 * delete this document folder
	 * logout
	 */
	@Test
	public void test02_AddFileDocumentToDocumentFolder(){
		String DATA_DOCUMENT_FOLDER_TITLE = "ECMS_DMS_SE_File_documentfolder_02";
		By ELEMENT_DOCUMENT_FOLDER = By.xpath("//a[@title='"+DATA_DOCUMENT_FOLDER_TITLE+" "+"']");
		String DATA_FILE_NAME = "ECMS_DMS_SE_File_filedocument_02";
		By ELEMENT_FILE_DOCUMENT = By.xpath("//a[@title='"+DATA_FILE_NAME+" "+"']");
		
		//Create new document folder
		goToSiteExplorer();
		
		debug("create new document folder with title: "+DATA_DOCUMENT_FOLDER_TITLE);
		createNewDocumentFolder(DATA_DOCUMENT_FOLDER_TITLE, DATA_DOCUMENT_FOLDER_TITLE);
		waitForElementPresent(ELEMENT_DOCUMENT_FOLDER);
		info("create new document folder successfully");
		
		//add new file document to this document folder
		goToNode(ELEMENT_DOCUMENT_FOLDER);
		goToAddNewContent();
		debug("add new file document with name:"+DATA_FILE_NAME);
		createNewFile(DATA_FILE_NAME, DATA_FILE_NAME, DATA_FILE_NAME);
		waitForElementPresent(ELEMENT_FILE_DOCUMENT);
		info("Add new file document in document folder successfully");
		
		//delete document folder
		goToNode(ELEMENT_DOCUMENT_FOLDER);
		deleteDocument(ELEMENT_DOCUMENT_FOLDER);
	}

	/*case3: Add File document in Article document
	 * add new article document
	 * add new file document to this article document
	 * check add successfully
	 * delete article document
	 * logout
	 */
	@Test
	public void test03_AddFileDocumentToArticleDocument(){
		String DATA_ARTICLE_TITLE = "ECMS_DMS_SE_File_article_03";
		By ELEMENT_ARTICLE = By.xpath("//a[@title='"+DATA_ARTICLE_TITLE+" "+"']");
		String DATA_FILE_NAME = "ECMS_DMS_SE_File_filedocument_03";
		By ELEMENT_FILE_DOCUMENT = By.xpath("//a[@title='"+DATA_FILE_NAME+" "+"']");
		
		//create new article document
		goToSiteExplorer();
		goToAddNewContent();
		debug("Create new article document with title:"+DATA_ARTICLE_TITLE);
		createNewArticle(DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE);
		waitForElementPresent(ELEMENT_ARTICLE);
		info("Create new article document successfully");

		//add new file document to this article document
		goToNode(ELEMENT_ARTICLE);
		goToAddNewContent();
		debug("add new file document with name:"+DATA_FILE_NAME);
		createNewFile(DATA_FILE_NAME, DATA_FILE_NAME, DATA_FILE_NAME);
		checkPreferenceOption(ELEMENT_DMS_OPTION_CHECKBOX_ID);
		goToNode(ELEMENT_ARTICLE);
		waitForElementPresent(ELEMENT_FILE_DOCUMENT);
		info("Add new file document in article document successfully");
		
		//delete article document
		goToNode(ELEMENT_ARTICLE);
		deleteDocument(ELEMENT_ARTICLE);
	}

	/*case4: Add File document in File document
	 * login
	 * create new file document
	 * check can not add new file document to created file document
	 * delete created file document
	 * logout
	 */
	@Test
	public void test04_AddFileDocumentToFileDocument(){
		String DATA_FILE_NAME = "ECMS_DMS_SE_File_filedocument_04";
		By ELEMENT_FILE_DOCUMENT = By.xpath("//a[@title='"+DATA_FILE_NAME+" "+"']");
		
		//create new file document
		goToSiteExplorer();
		goToAddNewContent();
		debug("create new file document with name:"+DATA_FILE_NAME);
		createNewFile(DATA_FILE_NAME, DATA_FILE_NAME, DATA_FILE_NAME);
		waitForElementPresent(ELEMENT_FILE_DOCUMENT);
		info("create new file document successfully");
		//check can not add new file document to created file document -> has not new content link
		goToNode(ELEMENT_FILE_DOCUMENT);
		waitForElementNotPresent(ELEMENT_NEW_CONTENT_LINK);
		//delete file document
		deleteDocument(ELEMENT_FILE_DOCUMENT);
	}
	
	/*case5: Add File document in Podcast document
	 * login
	 * create new Podcast document
	 * check can not add new file document to created Podcast document
	 * delete created Podcast document
	 * logout
	 */
	@Test
	public void test05_AddFileDocumentToPodcastDocument(){
		String DATA_PODCAST_NAME = "ECMS_DMS_SE_File_podcast_05";
		By ELEMENT_PODCAST = By.xpath("//a[@title='"+DATA_PODCAST_NAME+" "+"']");
		
		//create new podcast document
		goToSiteExplorer();
		goToAddNewContent();
		debug("create new podcast with name:"+DATA_PODCAST_NAME);
		createNewPodcast(DATA_PODCAST_NAME, DATA_PODCAST_NAME, DATA_PODCAST_NAME);
		waitForElementPresent(ELEMENT_PODCAST);
		info("create new podcast successfully");
		//check can not add new file document to created podcast document -> has not new content link
		goToNode(ELEMENT_PODCAST);
		waitForElementNotPresent(ELEMENT_NEW_CONTENT_LINK);
		//delete podcast document
		deleteDocument(ELEMENT_PODCAST);
	}
	
	/*case6: Add File document in Sample Node document
	 * login
	 * create new sample node document
	 * add new file document to sample node document
	 * check add successfully
	 * delete sample node document
	 * check delete successfully
	 * logout
	 */
	@Test
	public void test06_AddFileDocumentToSampleNode(){
		String DATA_FILE_NAME = "ECMS_DMS_SE_File_filedocument_06";
		By ELEMENT_FILE_DOCUMENT = By.xpath("//a[@title='"+DATA_FILE_NAME+" "+"']");
		String DATA_SAMPLE_NODE_TITLE = "ECMS_DMS_SE_File_samplenode_06";
		By ELEMENT_SAMPLE_NODE = By.xpath("//a[@title='"+DATA_SAMPLE_NODE_TITLE+" "+"']");
		
		//create new sample node document
		goToSiteExplorer();
		goToAddNewContent();
		debug("Create new sample node document with title:"+DATA_SAMPLE_NODE_TITLE);
		createNewSampleNode(DATA_SAMPLE_NODE_TITLE, DATA_SAMPLE_NODE_TITLE,"");
		waitForElementPresent(ELEMENT_SAMPLE_NODE);
		info("Create new sample node successfully");
		//add new file document to this sample node document
		debug("add new file document into sample node with name:"+DATA_FILE_NAME);
		goToNode(ELEMENT_SAMPLE_NODE);
		goToAddNewContent();
		createNewFile(DATA_FILE_NAME, DATA_FILE_NAME, DATA_FILE_NAME);
		checkPreferenceOption("enableStructure");
		waitForElementPresent(ELEMENT_FILE_DOCUMENT);
		info("Add a file document to a sample node successfully");
		//delete sample node document
		goToNode(ELEMENT_SAMPLE_NODE);
		deleteDocument(ELEMENT_SAMPLE_NODE);
	}
	
	/*case7: Add File document in File Plan document
	 * create new file plan document
	 * add new file document to file plan document
	 * check add successfully
	 * delete file plan
	 * check delete successfully
	 */
	@Test
	public void test07_AddFileDocumentToFilePlan(){
		String DATA_FILE_NAME = "ECMS_DMS_SE_File_filedocument_07";
		By ELEMENT_FILE_DOCUMENT = By.xpath("//a[@title='"+DATA_FILE_NAME+" "+"']");
		String DATA_FILE_PLAN_NAME = "ECMS_DMS_SE_File_fileplan_07";
		By ELEMENT_FILE_PLAN = By.xpath("//a[@title='"+DATA_FILE_PLAN_NAME+" "+"']");
		
		//create new file plan
		goToSiteExplorer();
		goToAddNewContent();
		debug("create new file plan document with name: "+ DATA_FILE_PLAN_NAME);
		createNewFilePlan(DATA_FILE_PLAN_NAME, DATA_FILE_PLAN_NAME, DATA_FILE_PLAN_NAME, DATA_FILE_PLAN_NAME, DATA_FILE_PLAN_NAME);
		waitForElementPresent(ELEMENT_FILE_PLAN);
		info("Create new file plan successfully");

		//add new file document to this file plan document
		goToNode(ELEMENT_FILE_PLAN);
		goToAddNewContent();
		debug("Add new file document with name:"+DATA_FILE_NAME);
		createNewFile(DATA_FILE_NAME, DATA_FILE_NAME, DATA_FILE_NAME);
		waitForElementPresent(ELEMENT_FILE_DOCUMENT);
		info("Add a file document to a file plan document successfully");
		
		//delete file plan document
		goToNode(ELEMENT_FILE_PLAN);
		deleteDocument(ELEMENT_FILE_PLAN);
	}
	
	/*case8: Add File document in Kofax document
	 * create new kofax document
	 * add new file document to kofax document
	 * check add successfully
	 * delete kofax document
	 * check delete successfully
	 */
	@Test
	public void test08_AddFileDocumentToKofax(){
		String DATA_FILE_NAME = "ECMS_DMS_SE_File_filedocument_08";
		By ELEMENT_FILE_DOCUMENT = By.xpath("//a[@title='"+DATA_FILE_NAME+" "+"']");
		String DATA_KOFAX_NAME = "ECMS_DMS_SE_File_kofax_08";
		By ELEMENT_KOFAX = By.xpath("//a[@title='"+DATA_KOFAX_NAME+" "+"']");
		
		//create new kofax document
		goToSiteExplorer();
		goToAddNewContent();
		debug("Create new kofax document with name:"+DATA_KOFAX_NAME);
		createNewKofax(DATA_KOFAX_NAME);
		waitForElementPresent(ELEMENT_KOFAX);
		info("Create new Kofax document successfully");
		//add new file document to this kofax document
		goToNode(ELEMENT_KOFAX);
		goToAddNewContent();
		debug("add new file document with name:"+DATA_FILE_NAME);
		createNewFile(DATA_FILE_NAME, DATA_FILE_NAME, DATA_FILE_NAME);
		waitForElementPresent(ELEMENT_FILE_DOCUMENT);
		info("add new file document to kofax document successfully");
		//delete kofax document
		goToNode(ELEMENT_KOFAX);
		deleteDocument(ELEMENT_KOFAX);
	}
	
	/*case9: Add File document in uploaded file
	 * login
	 * upload a new file
	 * check can not add file document to uploaded file
	 * delete uploaded file
	 * logout
	 */
	@Test
	public void test09_AddFileDocumentToUploadFile(){
		String DATA_UPLOAD_FILE_LINK = "TestData/ECMS_DMS_SE_File.jpg";
		String DATA_UPLOAD_FILE_NAME = "ECMS_DMS_SE_File_uploadfile_09";
		By ELEMENT_UPLOAD_FILE = By.xpath("//a[@title='"+DATA_UPLOAD_FILE_NAME+".jpg "+"']");
		
		//upload a file
		goToSiteExplorer();
		debug("Upload 1 file with name:"+DATA_UPLOAD_FILE_NAME);
		uploadFile(DATA_UPLOAD_FILE_NAME,DATA_UPLOAD_FILE_LINK);
		waitForElementPresent(ELEMENT_UPLOAD_FILE); 
		info("Upload a file successfully");
		//check can not add new file document to upload file -> has not new content link
		goToNode(ELEMENT_UPLOAD_FILE);
		waitForElementNotPresent(ELEMENT_NEW_CONTENT_LINK);
		//delete podcast document
		deleteDocument(ELEMENT_UPLOAD_FILE);
	
	}
	
	/*case10: Add new File document with blank Name or Content
	 * login
	 * add new file document with name blank -> check message
	 * add new file document with content balnk - check message
	 * logout
	 */
	@Test
	public void test10_AddFileDocumentWithNameOrContentBlank(){
		String DATA_FILE_NAME = "ECMS_DMS_SE_File_filedocument_10";
		
		//add file with content blank;
		goToSiteExplorer();
		goToNode(ELEMENT_NEW_CONTENT_LINK);
		createNewFile(DATA_FILE_NAME, "", "");
		assert isElementPresent(ELEMENT_NEWFILE_POPUP_MESSAGE_XPATH):"No alert";	
		assert getText(ELEMENT_NEWFILE_MESSAGE_XPATH).contains(ELEMENT_NEWFILE_MESSAGE_CONTENT_BLANK):"Not found message alert when content blank";
		info("check message when create a file document with content blank is true");
		click(By.linkText("OK"));
		//add file document with name blank
		waitForAndGetElement(ELEMENT_NEWFILE_NAME_TEXTBOX).clear();
		inputDataToFrame(ELEMENT_NEWFILE_CONTENT_FRAME,DATA_FILE_NAME);
	    switchToParentWindow();
	    click(ELEMENT_SAVE_CLOSE_BUTTON);
		assert isElementPresent(ELEMENT_NEWFILE_POPUP_MESSAGE_XPATH):"Not has alert";
		assert getText(ELEMENT_NEWFILE_MESSAGE_XPATH).contains(ELEMENT_NEWFILE_MESSAGE_NAME_BLANK):"Not found message alert when name blank";
		info("check message when create a file document with name blank is true");
		click(By.linkText("OK")); 
	}

	/*case11: Add new text/html File document (1) input source content
	 * login
	 * create new text/html file: mime type =text/html, choose source, input to content html statement
	 * check view by 2 ways
	 * delete this text/html file
	 * logout
	 */
	@Test
	public void test11_AddTextHtmlFileChooseSource(){
		String DATA_FILE_NAME = "ECMS_DMS_SE_File_filedocument_11";
		By ELEMENT_FILE_DOCUMENT = By.xpath("//a[@title='"+DATA_FILE_NAME+" "+"']");
		
		
		//create new text/html file
		goToSiteExplorer();
		goToAddNewContent();
		info("Create new file document with name: "+DATA_FILE_NAME);
		click(ELEMENT_NEWFILE_LINK);
		type(ELEMENT_NEWFILE_NAME_TEXTBOX,DATA_FILE_NAME,false);
		click(ELEMENT_NEWFILE_SOURCE_LINK_XPATH);
		type(ELEMENT_NEWFILE_SOURCE_TEXTAREA_XPATH, DATA_NEWFILE_SOURCE_CONTENT, false);
	    click(ELEMENT_SAVE_CLOSE_BUTTON);
	    waitForElementPresent(ELEMENT_FILE_DOCUMENT);

	    //check view as html tab
		goToNode(ELEMENT_FILE_DOCUMENT);
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_NEWFILE_FRAME_HTML_TAB_XPATH));
		assert getText(ELEMENT_NEWFILE_HTML_TAB_P_XPATH).contains(DATA_NEWFILE_SOURCE_CONTENT_VIEW):"content is not true in html form";
	    switchToParentWindow();
	    info("Check html fomat of content file successfully");
	    //check view as plan text
	    click(ELEMENT_NEWFILE_TEXT_TAB_XPATH);
	    assert getText(By.cssSelector(ELEMENT_NEWFILE_TEXT_TAB_P_CSS)).contains(DATA_NEWFILE_SOURCE_CONTENT):"content is not true in text form";
	    info("Check plan text fomat of content file successfully");
	    //delete text/html file
		goToNode(ELEMENT_FILE_DOCUMENT);
		deleteDocument(ELEMENT_FILE_DOCUMENT);
	}
	
	/*case12: Add new text/html File document (2) input not source content
	 * create new text/html file: mime type =text/html, do not choose source, input to content
	 * check view 
	 * delete file document
	 */
	@Test
	public void test12_AddFileDocumentNotChooseSource(){
		String DATA_FILE_NAME = "ECMS_DMS_SE_File_filedocument_12";
		By ELEMENT_FILE_DOCUMENT = By.xpath("//a[@title='"+DATA_FILE_NAME+" "+"']");
		
		//create new text/html file, not choose source
		goToSiteExplorer();
		goToAddNewContent();
		info("Create new file document with name: "+DATA_FILE_NAME);
		createNewFile(DATA_FILE_NAME, DATA_NEWFILE_SOURCE_CONTENT_VIEW, DATA_FILE_NAME);
		waitForElementPresent(ELEMENT_FILE_DOCUMENT);
		//view html tab
		goToNode(ELEMENT_FILE_DOCUMENT);
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_NEWFILE_FRAME_HTML_TAB_XPATH));
		assert getText(ELEMENT_NEWFILE_HTML_TAB_P_XPATH).contains(DATA_NEWFILE_SOURCE_CONTENT_VIEW):"content is not true in html form";
	    switchToParentWindow();
	    info("Check html fomat of content file successfully");
	    //check view as plan text
	    click(ELEMENT_NEWFILE_TEXT_TAB_XPATH);
	    assert getText(By.cssSelector(ELEMENT_NEWFILE_TEXT_TAB_P_CSS)).contains(DATA_NEWFILE_NOSOURCE_CONTENT):"content is not true in text form";
	    info("Check plan text fomat of content file successfully");
	    //delete text/html file
		goToNode(ELEMENT_FILE_DOCUMENT);
		deleteDocument(ELEMENT_FILE_DOCUMENT);
	}
	
	/*case13: Add new text/plain File document 
	 * create new file document with: mime type = text/plain
	 * check content
	 * delete file document
	 */
	@Test
	public void test13_AddNewFileDocumentTextPlan(){
		String DATA_FILE_NAME = "ECMS_DMS_SE_File_filedocument_13";
		By ELEMENT_FILE_DOCUMENT = By.xpath("//a[@title='"+DATA_FILE_NAME+" "+"']");
		
		//create new text/plan file
		goToSiteExplorer();
		goToAddNewContent();
		click(ELEMENT_NEWFILE_LINK);	
		type(ELEMENT_NEWFILE_NAME_TEXTBOX,DATA_FILE_NAME,false);
		selectOption(ELEMENT_NEWFILE_MIME_COMBOX_ID, "text/plain");
		type(ELEMENT_NEWFILE_TEXTAREA_ID,DATA_NEWFILE_TEXTAREA_CONTENT,false);
	    click(ELEMENT_SAVE_CLOSE_BUTTON);
		waitForElementPresent(ELEMENT_FILE_DOCUMENT);
		//check view content
		goToNode(ELEMENT_FILE_DOCUMENT);
		assert getText(By.cssSelector(ELEMENT_NEWFILE_PRE_CSS)).contains(DATA_NEWFILE_TEXTAREA_CONTENT):"Not found content of file document";
	    info("Check plan text fomat of content file successfully");
		//delete file document
		deleteDocument(ELEMENT_FILE_DOCUMENT);
	}
	
	/*case14: Add new File document in locked node by locker
	 * create new content folder
	 * lock content folder
	 * check lock successfully
	 * add new file document to content folder
	 * delete content folder
	 * login
	 */
	@Test
	public void test14_AddNewFileDocumentToLockedContent(){
		String DATA_FILE_NAME = "ECMS_DMS_SE_File_filedocument_14";
		By ELEMENT_FILE_DOCUMENT = By.xpath("//a[@title='"+DATA_FILE_NAME+" "+"']");
		String DATA_CONTENT_FOLDER_TITLE = "ECMS_DMS_SE_File_contentfolder_14";
		By ELEMENT_CONTENT_FOLDER = By.xpath("//a[@title='"+DATA_CONTENT_FOLDER_TITLE+" "+"']");
		By ELEMENT_CONTENT_FOLDER_2 = By.xpath("//a[contains(@title,'"+DATA_CONTENT_FOLDER_TITLE+"')]");
		
		//create new content folder
		goToSiteExplorer();
		debug("Create new content with title:"+DATA_CONTENT_FOLDER_TITLE);
		createNewContentFolder(DATA_CONTENT_FOLDER_TITLE,DATA_CONTENT_FOLDER_TITLE);
		waitForElementPresent(ELEMENT_CONTENT_FOLDER);
		info("Create content folder successfully");
		//lock content folder
		goToNode(ELEMENT_CONTENT_FOLDER);
		lockNode(ELEMENT_CONTENT_FOLDER);
		//check node is locled
		assert checkLockNode(ELEMENT_CONTENT_FOLDER_2):"Can not lock this content folder";
		//add new file to content folder just add
		goToNode(ELEMENT_CONTENT_FOLDER_2);
		goToAddNewContent();
		debug("Add new file document with name:"+DATA_FILE_NAME);
		createNewFile(DATA_FILE_NAME, DATA_FILE_NAME, DATA_FILE_NAME);
		waitForElementPresent(ELEMENT_FILE_DOCUMENT);
		info("Add new file document to content document successfully");
		
		//delete content folder
		goToNode(ELEMENT_CONTENT_FOLDER_2);
		deleteDocument(ELEMENT_CONTENT_FOLDER_2);
	}
}
