package org.exoplatform.selenium.platform.ecms;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;

import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ContentTemplate extends EcmsBase {
	
	//Article
	public static final By ELEMENT_ARTICLE_LINK = By.xpath("//div[@title='Article']");
	public static final By ELEMENT_ARTICLE_TITLE_TEXTBOX = By.id("title");	
	public static final By ELEMENT_ARTICLE_NAME_TEXTBOX = By.id("name");
	public static final By ELEMENT_SUMMARY_SOURCE_BUTTON = By.id("cke_9_label");
	public static final By ELEMENT_CONTENT_SOURCE_BUTTON = By.id("cke_45_label");
	public static final By ELEMENT_ARTICLE_SUMMARY_SOURCE_FRAME = By.xpath("//td[@id='cke_contents_summary']/textarea");
	public static final By ELEMENT_ARTICLE_CONTENT_SOURCE_FRAME = By.xpath("//td[@id='cke_contents_content']/textarea");
	public static final By ELEMENT_ARTICLE_SUMMARY_FRAME = By.xpath("//td[@id='cke_contents_summary']/iframe");
	public static final By ELEMENT_ARTICLE_CONTENT_FRAME = By.xpath("//td[@id='cke_contents_content']/iframe");
	//public static final By ELEMENT_SAVE_CLOSE_BUTTON = By.linkText("Save & Close");

	// Announcement
	public static final By ELEMENT_ANNOUNCEMENT_LINK = By.linkText("Announcement");
	public static final By ELEMENT_ANNOUNCEMENT_NAME_TEXTBOX = By.id("name");
	public static final By ELEMENT_ANNOUNCEMENT_SUMMARY_FRAME = By.xpath("//td[@id='cke_contents_exo:summary']/iframe");

	//WebContent
	public static final By ELEMENT_WEBCONTENT_LINK =By.linkText("Free layout webcontent");
	public static final By ELEMENT_WEBCONTENT_TITLE_TEXTBOX =By.id("title");	
	public static final By ELEMENT_WEBCONTENT_NAME_TEXTBOX =By.id("name");	
	public static final By ELEMENT_WEBCONTENT_CONTENT_FRAME = By.xpath("//td[contains(@id,'cke_contents_htmlData')]/iframe");
	public static final By ELEMENT_WEBCONTENT_ILLUSTRATION_TAB = By.xpath("//div[contains(text(),'Illustration')]");
	public static final By ELEMENT_WEBCONTENT_UPLOAD_FRAME =By.xpath("//*[contains(@id,'uploadFrame')]");
	public static final By ELEMENT_WEBCONTENT_FILE_IMAGE = By.id("file");
	public static final By ELEMENT_WEBCONTENT_SUMMARY_FRAME = By.xpath("//td[@id='cke_contents_exo:summary']/iframe");
	public static final By ELEMENT_WEBCONTENT_ADVANCE_TAB = By.xpath("//div[contains(text(),'Advanced')]");
	public static final By ELEMENT_WEBCONTENT_CSS_TEXTAREA = By.xpath("//textarea[contains(@id,'ContentCSS')]");
	public static final By ELEMENT_WEBCONTENT_JS_TEXTAREA = By.xpath("//textarea[contains(@id,'ContentJS')]");
	
	public static final By ELEMENT_NEWFILE_LINK =By.linkText("File");
	public static final By ELEMENT_NEWFILE_NAME_TEXTBOX =By.id("name");
	public static final By ELEMENT_NEWFILE_CONTENT_FRAME = By.xpath("//td[@id='cke_contents_contentHtml']/iframe");
	public static final By ELEMENT_NEWFILE_TITLE_TEXTBOX =By.id("title0");
	//SampleNode
	public static final By ELEMENT_SAMPLENODE_LINK =By.linkText("Sample node");
	public static final By ELEMENT_SAMPLENODE_TITLE_TEXTBOX = By.id("title");
	public static final By ELEMENT_SAMPLENODE_NAME_TEXTBOX = By.id("name");
	public static final By ELEMENT_SAMPLENODE_ADD_ITEM_LINK = By.xpath("//img[@title = 'Add Item']");	
	public static final By ELEMENT_SAMPLENODE_SELECTITEM = By.id("taxonomyTree");
	public static final String ELEMENT_SAMPLENODE_SELECTITEM_OPTION = "acme";
	public static final By ELEMENT_SAMPLENODE_SELECTITEM_OPTION_LINK = By.xpath("//div[@class='Select16x16Icon']");
	public static final By ELEMENT_SAMPLENODE_DESCRIPTION_TEXTBOX = By.id("description");
	public static final By ELEMENT_SAMPLENODE_UPLOAD_IMG_FRAME = By.xpath("//iframe[contains(@id,'uploadFrame')]");
	public static final By ELEMENT_SAMPLENODE_FILE_IMG = By.id("file") ;
	public static final By ELEMENT_SAMPLENODE_CONTENT_TEXTAREA = By.id("summary");
	public static final By ELEMENT_SAMPLENODE_SUMMARY_TEXTAREA = By.id("content");

	//Kofax
	public static final By ELEMENT_KOFAX_LINK = By.linkText("Kofax document");
	public static final By ELEMENT_KOFAX_NAME_TEXTBOX = By.id("name");
	public static final By ELEMENT_KOFAX_ADDNODE_LINK = By.xpath("//img[@class='MultiFieldAction AddNewNodeIcon']") ;
	public static final By ELEMENT_KOFAX_ADDNODE_SELECT = By.id("taxonomyTree");
	public static final String ELEMENT_KOFAX_ADDNODE_OPTION = "acme";
	public static final By ELEMENT_KOFAX_ADDNODE_OPTION_LINK = By.xpath("//div[@class='Select16x16Icon']");
	
	//FilePlan
	public static final By ELEMENT_FILEPLAN_LINK = By.linkText("File Plan");
	public static final By ELEMENT_FILEPLAN_NAME = By.id("name");
	public static final By ELEMENT_FILEPLAN_RECORD_TAB = By.xpath("//div[contains(text(),'Record Properties')]");
	public static final By ELEMENT_FILEPLAN_RECORD_TEXTBOX = By.id("recordCategoryIdentifier");
	public static final By ELEMENT_FILEPLAN_DISPOS_TEXTBOX = By.id("dispositionAuthority");
	public static final By ELEMENT_FILEPLAN_DEFAULT_TEXTBOX  = By.id("defaultOriginatingOrganization");
	public static final By ELEMENT_FILEPLAN_RPROCESS_TAB = By.xpath("//div[contains(text(),'Process Properties')]");
	public static final By ELEMENT_FILEPLAN_RPROCESS_TRIGER = By.id("eventTrigger");	

	//Picture on Head Layout
	public static final By ELEMENT_HEAD_LAYOUT_LINK = By.linkText("Picture on head layout webcontent");
	public static final By ELEMENT_HEAD_LAYOUT_NAME_TEXTBOX = By.id("name");
	public static final By ELEMENT_HEAD_LAYOUT_TITLE_TEXTBOX = By.id("title");	
	public static final By ELEMENT_HEAD_LAYOUT_UPLOAD_FRAME = By.xpath("//iframe[contains(@id,'uploadFrame')]")	;
	public static final By ELEMENT_HEAD_LAYOUT_UPLOAD_FILE = By.id("file") ;
	
	//Product
	public static final By ELEMENT_PRODUCT_LINK = By.linkText("Product");
	public static final By ELEMENT_PRODUCT_NAME_TEXTBOX = By.id("name") ;
	public static final By ELEMENT_PRODUCT_TITLE_TEXTBOX = By.id("title");

	public static final By ELEMENT_EDIT_NODE_CHECKBOX = By.id("set_property");
	public static final By ELEMENT_REMOVE_NODE_CHECKBOX = By.id("remove");
	public static final By ELEMENT_MENU_CONTENT=By.linkText("Content");
	public static final By ELEMENT_MENU_EDIT=By.xpath("//a[@class='EditorIcon TBIcon' and text()='Edit']");
	public static final By ELEMENT_UPLOAD_ID = By.id("file");
	public static final By ELEMENT_UPLOAD_FRAME_EDIT=By.xpath("//iframe[contains(@id,'uploadFrame')]");
	public static final By ELEMENT_UPLOAD_REMOVE=By.xpath("//img[@alt='Remove Item']");
	public static final By ELEMENT_PIC_FILE_REMOVE=By.xpath("//img[@class='ActionIcon Remove16x16Icon']");

	public static final By ELEMENT_UPLOAD_TITLE=By.id("title0");
	public static final By ELEMENT_UPLOAD_DESC=By.id("description0");
	public static final By ELEMENT_UPLOAD_CREATOR=By.id("creator0");
	public static final By ELEMENT_UPLOAD_SOURCE=By.id("source0");

	public static final By ELEMENT_FREE_CONT_ACCEPT=By.xpath("//form[contains(@id,'EditTextForm')]/a[2]");
	public static final By ELEMENT_FREE_CONT_INPUT=By.xpath("//iframe[contains(@title,'Rich text editor, newText')]");
	public static final String ELEMENT_FREE_CONT_INLINE="//div[contains(@id,'CurrentText') and @title='Double-click to edit']";
	
	public static final By ELEMENT_SAMPLE_CONT_ACCEPT=By.xpath("//form[contains(@id,'EditContentForm')]/a[2]");
	public static final By ELEMENT_SAMPLE_CONT_INPUT=By.xpath("//textarea[contains(@id,'newContent')]");
	public static final String ELEMENT_SAMPLE_CONT_INLINE="//div[contains(@id,'CurrentContent') and @title='Double-click to edit']";
	public static final By ELEMENT_SAMPLE_SUM_ACCEPT=By.xpath("//form[contains(@id,'EditSummaryForm')]/a[2]");
	public static final By ELEMENT_SAMPLE_SUM_INPUT=By.xpath("//textarea[contains(@id,'newSummary')]");
	public static final String ELEMENT_SAMPLE_SUM_INLINE="//div[contains(@id,'CurrentSummary') and @title='Double-click to edit']";
	public static final By ELEMENT_SAMPLE_TITLE_ACCEPT=By.xpath("//form[contains(@id,'EditTdGtmplTitleForm')]/a[2]");
	public static final By ELEMENT_SAMPLE_TITLE_INPUT=By.xpath("//input[contains(@id,'newTdGtmplTitle')]");
	public static final String ELEMENT_SAMPLE_TITLE_INLINE="//div[contains(@id,'CurrentTdGtmplTitle') and @title='Double-click to edit']";
	
	//Podcast
	public static final By ELEMENT_PODCAST_LINK = By.linkText("Podcast");
	public static final By ELEMENT_PODCAST_NAME_TEXTBOX = By.id("name");
	public static final By ELEMENT_PODCAST_TITLE_TEXTBOX = By.id("title");
	public static final By ELEMENT_PODCAST_LINK_TEXTBOX = By.id("link");
	public static final String ELEMENT_PODCAST_TITLE_INLINE="//div[contains(@id,'CurrentTdGtmplTitle') and @title='Double-click to edit']";
	public static final By ELEMENT_PODCAST_TITLE_INPUT=By.xpath("//input[contains(@id,'newTdGtmplTitle')]");
	public static final By ELEMENT_PODCAST_TITLE_ACCEPT=By.xpath("//a[@title='Accept'][1]");
	public static final String ELEMENT_PODCAST_DESC_INLINE="//div[contains(@id,'CurrentDescription') and @title='Double-click to edit']";
	public static final By ELEMENT_PODCAST_DESC_INPUT=By.xpath("//textarea[contains(@id,'newDescription')]");
	public static final By ELEMENT_PODCAST_DESCRIPTION=By.id("description");
	public static final By ELEMENT_PODCAST_DESC_ACCEPT=By.xpath("//form[contains(@id,'EditDescriptionForm')]/a[2]");
	public static final String ELEMENT_MESSAGE_EDIT_INLINE="An error occurred while modifying the required property.";
	public static final String ELEMENT_MESSAGE_EDIT_CLOSE="The changes you made will be lost if you close this form.";
	public static final String ELEMENT_ENABLE_DMS_STRUCTURE= "enableStructure";
	public static final By ELEMENT_PIC_IMGWIDTH=By.id("imageWidth");
	public static final By ELEMENT_PIC_IMGHEIGHT=By.id("imageHeight");
	public static final By ELEMENT_PIC_LANG=By.id("content-lang");
	public static final By ELEMENT_PIC_CONT=By.xpath("//iframe[contains(@title,'Rich text editor, htmlData')]");
	public static final By ELEMENT_CSS_NAME = By.id("name");
	public static final By ELEMENT_CSS_PRIORITY = By.id("CSSpriority");
	public static final By ELEMENT_CSS_DATA	= By.id("contentHtml");
	public static final By ELEMENT_MYSITE_LINK = By.xpath("//a[contains(text(),'My Sites')]");
	public static final By ELEMENT_ACME_SITE_LINK = By.linkText("acme");
	public static final By ELEMENT_BODY = By.xpath("//p");
	
	public static final By ELEMENT_JS_NAME= By.id("name");
	public static final By ELEMENT_JS_ACTIVE= By.id("activeJS");
	public static final By ELEMENT_JS_PRIORITY= By.id("JSpriority");
	public static final By ELEMENT_JS_LANGUAGE= By.id("content-lang");
	public static final By ELEMENT_JS_DATA= By.id("contentHtml");
	
	//upload
	public static final By ELEMENT_UPLOAD_LINK_XPATH = By.xpath("//a[@title='Upload']");
	public static final By ELEMENT_UPLOAD_FILE_NAME_ID = By.id("name");
	public static final By ELEMENT_UPLOAD_IMG_FRAME_XPATH = By.xpath("//iframe[contains(@id,'uploadFrame')]");
	public static final By ELEMENT_UPLOAD_IMG_ID = By.id("file");
	public static final By ELEMENT_UPLOAD_FINISH_XPATH = By.xpath("//div[@class='FileNameLabel']");
	public static final By ELEMENT_REPLACE_DATA_LINK =By.linkText("Replace data");
	
	//data test
	public static final String[] DATA_SPECIAL_CHARACTER = {"`","~","!","@","#","$","%","^","&","*","(",")","-","_","+","=","{","}","[","]","|","\\",";",":","'","\"","<",",",".","/","?"};
	public static final String DATA_SPECIAL_CHARACTER_STRING = "~`!@#$%^&*()-_=+[]{}\\|;:'\",<.>?/";
	
	// add new article
	public static void createNewArticle(String title, String name, String sum, String cont) {
		click(ELEMENT_ARTICLE_LINK);
		// Input information
		type(ELEMENT_ARTICLE_TITLE_TEXTBOX,title,false);
		type(ELEMENT_ARTICLE_NAME_TEXTBOX, name, true);
		inputDataToFrame(ELEMENT_ARTICLE_SUMMARY_FRAME,sum);
		switchToParentWindow();
		inputDataToFrame(ELEMENT_ARTICLE_CONTENT_FRAME,cont);
		switchToParentWindow();
		//save
		click(ELEMENT_SAVE_CLOSE_BUTTON);
	}
	public static void createNewArticleUseSource(String title,String name, String sum, String cont) {
		click(ELEMENT_ARTICLE_LINK);
		// Input information
		type(ELEMENT_ARTICLE_TITLE_TEXTBOX,title,false);
		type(ELEMENT_ARTICLE_NAME_TEXTBOX, name, true);
		//Click to the Source button on FCKEditor of Summary field
		click(ELEMENT_SUMMARY_SOURCE_BUTTON);
		type(ELEMENT_ARTICLE_SUMMARY_SOURCE_FRAME, sum, false);
		switchToParentWindow();
		//Click to the Source button on FCKEditor of Content field
		click(ELEMENT_CONTENT_SOURCE_BUTTON);
		type(ELEMENT_ARTICLE_CONTENT_SOURCE_FRAME, cont, false);
		switchToParentWindow();
		//save
		click(ELEMENT_SAVE_CLOSE_BUTTON);
	}

	//add new announcement
	public static void createNewAnnouncement (String name, String sum){
		click(ELEMENT_ANNOUNCEMENT_LINK);
		type(ELEMENT_ANNOUNCEMENT_NAME_TEXTBOX, name, true);
		inputDataToFrame(ELEMENT_ANNOUNCEMENT_SUMMARY_FRAME,sum);
		switchToParentWindow();
		click(ELEMENT_SAVE_CLOSE_BUTTON);
	}

	//add new Free layout webcontent
	public static void createNewFreeLayoutWebContent(String title, String name, String cont, String img, String sum, String css, String js) {
		click(ELEMENT_WEBCONTENT_LINK);
		type(ELEMENT_WEBCONTENT_TITLE_TEXTBOX, title, false);
		//			waitForAndGetElement(ELEMENT_WEBCONTENT_NAME_TEXTBOX).clear();
		type(ELEMENT_WEBCONTENT_NAME_TEXTBOX, name, true);
		inputDataToFrame(ELEMENT_WEBCONTENT_CONTENT_FRAME,cont);
		switchToParentWindow();
		if (cont!="" || img !=""){
			click(ELEMENT_WEBCONTENT_ILLUSTRATION_TAB);
			if (img!=""){
				driver.switchTo().frame(waitForAndGetElement(ELEMENT_WEBCONTENT_UPLOAD_FRAME));
				type(ELEMENT_WEBCONTENT_FILE_IMAGE, getAbsoluteFilePath(img), false);
				switchToParentWindow();
				String links[] = img.split("/");
				int length = links.length;
				waitForElementPresent(By.xpath("//div[contains(text(),'" + links[length-1]+ "')]"));
			}
			inputDataToFrame(ELEMENT_WEBCONTENT_SUMMARY_FRAME,sum);
			switchToParentWindow();
		}
		if(css!="" || js !=""){
			click(ELEMENT_WEBCONTENT_ADVANCE_TAB);
			type(ELEMENT_WEBCONTENT_CSS_TEXTAREA, css, false);
			type(ELEMENT_WEBCONTENT_JS_TEXTAREA, js, false);
		}
		click(ELEMENT_SAVE_CLOSE_BUTTON);			
	}

	//add new file
	public static void createNewFile(String name, String cont, String title){
		click(ELEMENT_NEWFILE_LINK);	
		type(ELEMENT_NEWFILE_NAME_TEXTBOX, name, false);
		inputDataToFrame(ELEMENT_NEWFILE_CONTENT_FRAME,cont);
		switchToParentWindow();
		type(ELEMENT_NEWFILE_TITLE_TEXTBOX, title, false);
		click(ELEMENT_SAVE_CLOSE_BUTTON);
	}

	//add new Sample Node
	public static void createNewSampleNode(String title, String name, String img){
		click(ELEMENT_SAMPLENODE_LINK);
		type(ELEMENT_SAMPLENODE_TITLE_TEXTBOX, title, false);
		//			waitForAndGetElement(ELEMENT_SAMPLENODE_NAME_TEXTBOX).clear();
		type(ELEMENT_SAMPLENODE_NAME_TEXTBOX, name, true);
		click(ELEMENT_SAMPLENODE_ADD_ITEM_LINK);
		selectOption(ELEMENT_SAMPLENODE_SELECTITEM,ELEMENT_SAMPLENODE_SELECTITEM_OPTION);
		click(ELEMENT_SAMPLENODE_SELECTITEM_OPTION_LINK);
		type(ELEMENT_SAMPLENODE_DESCRIPTION_TEXTBOX, title, false);
		if (img !=""){
			driver.switchTo().frame(waitForAndGetElement(ELEMENT_SAMPLENODE_UPLOAD_IMG_FRAME));
			type(ELEMENT_SAMPLENODE_FILE_IMG, getAbsoluteFilePath(img), false);
			switchToParentWindow();
			waitForElementPresent(ELEMENT_UPLOAD_FINISH_XPATH);
		}
		type(ELEMENT_SAMPLENODE_CONTENT_TEXTAREA, title, false);
		type(ELEMENT_SAMPLENODE_SUMMARY_TEXTAREA, title, false);
		click(ELEMENT_SAVE_CLOSE_BUTTON);
	}

	// add new Kofax document
	public static void createNewKofax(String name){
		click(ELEMENT_KOFAX_LINK);
		type(ELEMENT_KOFAX_NAME_TEXTBOX, name, false);
		click(ELEMENT_KOFAX_ADDNODE_LINK);
		selectOption(ELEMENT_KOFAX_ADDNODE_SELECT,ELEMENT_KOFAX_ADDNODE_OPTION);	
		click(ELEMENT_KOFAX_ADDNODE_OPTION_LINK);
		click(ELEMENT_SAVE_CLOSE_BUTTON);
	}

	// add new File Plan
	public static void createNewFilePlan(String name, String catInden, String disAut, String oriOrg, String event){
		click(ELEMENT_FILEPLAN_LINK);	
		type(ELEMENT_FILEPLAN_NAME, name, false);
		click(ELEMENT_FILEPLAN_RECORD_TAB);
		type(ELEMENT_FILEPLAN_RECORD_TEXTBOX, catInden, false);
		type(ELEMENT_FILEPLAN_DISPOS_TEXTBOX, disAut, false);
		type(ELEMENT_FILEPLAN_DEFAULT_TEXTBOX, oriOrg, false);

		click(ELEMENT_FILEPLAN_RPROCESS_TAB);
		type(ELEMENT_FILEPLAN_RPROCESS_TRIGER, event, false);
		click(ELEMENT_SAVE_CLOSE_BUTTON);
	}

	// add new podcast
	public static void createNewPodcast(String name, String title, String link){
		click(ELEMENT_PODCAST_LINK);
		type(ELEMENT_PODCAST_NAME_TEXTBOX, name, false);
		type(ELEMENT_PODCAST_TITLE_TEXTBOX, title, false);
		type(ELEMENT_PODCAST_LINK_TEXTBOX, link, false);
		click(ELEMENT_SAVE_CLOSE_BUTTON);
		waitForElementNotPresent(ELEMENT_SAVE_CLOSE_BUTTON);
	}

	//add new Picture on head layout webcontent
	public static void createNewPictureOnHeadLayout (String name, String title, String file){
		click(ELEMENT_HEAD_LAYOUT_LINK);
		type(ELEMENT_HEAD_LAYOUT_NAME_TEXTBOX, name, false);
		type(ELEMENT_HEAD_LAYOUT_TITLE_TEXTBOX, title, false);
		if (file!=""){
			driver.switchTo().frame(waitForAndGetElement(ELEMENT_HEAD_LAYOUT_UPLOAD_FRAME));
			type(ELEMENT_HEAD_LAYOUT_UPLOAD_FILE, getAbsoluteFilePath(file), false);
			switchToParentWindow();
			waitForElementPresent(ELEMENT_UPLOAD_FINISH_XPATH);
		}
		click(ELEMENT_SAVE_CLOSE_BUTTON);
	}

	//add new product
	public static void createNewProduct (String name, String title){
		click(ELEMENT_PRODUCT_LINK);
		type(ELEMENT_PRODUCT_NAME_TEXTBOX, name, false);
		type(ELEMENT_PRODUCT_TITLE_TEXTBOX, title, false);
		click(ELEMENT_SAVE_CLOSE_BUTTON);
	}

	//add new Content Folder
	public static void createNewContentFolder(String title, String name) {
		for (int repeat = 0;; repeat++)	{	
			if (repeat >= ACTION_REPEAT) {
				Assert.fail("Cannot perform the action after " + ACTION_REPEAT + "tries");
			}
			mouseOver(ELEMENT_MENU_NEW_CONTENT_LINK, true);
			click(ELEMENT_NEW_FOLDER_LINK);

			if (waitForElementPresent(ELEMENT_FOLDER_TITLE_TEXTBOX,30000,0) != null) break;
			pause(WAIT_INTERVAL);
			info("retry...[" + repeat + "]");
		}
		selectOption(ELEMENT_FOLDER_TYPE_OPTION,ELEMENT_CONTENT_FOLDER_TYPE);
		type(ELEMENT_FOLDER_TITLE_TEXTBOX, title, false);
		//			waitForAndGetElement(ELEMENT_FOLDER_NAME_TEXTBOX).clear();
		type(ELEMENT_FOLDER_NAME_TEXTBOX, name, true);
		click(ELEMENT_SAVE_BUTTON);
	}

	//add new Document Folder
	public static void createNewDocumentFolder(String title, String name){
		click(ELEMENT_NEW_FOLDER_LINK);
		selectOption(ELEMENT_FOLDER_TYPE_OPTION, ELEMENT_DOCUMENT_FOLDER_TYPE);
		type(ELEMENT_FOLDER_TITLE_TEXTBOX, title, false);
		//			waitForAndGetElement(ELEMENT_FOLDER_NAME_TEXTBOX).clear();
		type(ELEMENT_FOLDER_NAME_TEXTBOX, name, true);
		click(ELEMENT_SAVE_BUTTON);
	}

	//upload file
	public static void uploadFile(String fileName, String link){
		goToNode(ELEMENT_UPLOAD_LINK_XPATH);
		type(ELEMENT_UPLOAD_FILE_NAME_ID, fileName, false);
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_UPLOAD_IMG_FRAME_XPATH));
		type(ELEMENT_UPLOAD_IMG_ID, getAbsoluteFilePath(link), false);
		info("Upload file "+getAbsoluteFilePath(link));
		switchToParentWindow();
		waitForElementPresent(ELEMENT_UPLOAD_FINISH_XPATH);
		click(ELEMENT_SAVE_BUTTON);
		click(ELEMENT_CLOSE_BUTTON);
	}
	public static void uploadTheSameFile(String fileName, String link){
		goToNode(ELEMENT_UPLOAD_LINK_XPATH);
		type(ELEMENT_UPLOAD_FILE_NAME_ID, fileName, false);
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_UPLOAD_IMG_FRAME_XPATH));
		type(ELEMENT_UPLOAD_IMG_ID, getAbsoluteFilePath(link), false);
		info("Upload file "+getAbsoluteFilePath(link));
		switchToParentWindow();
		waitForElementPresent(ELEMENT_UPLOAD_FINISH_XPATH);
		click(ELEMENT_SAVE_BUTTON);
	}
	public static void addJSFile(String name, String... data)
	{
		goToAddNewContent();
		type(ELEMENT_JS_NAME, name, true);
		if (data.length >0)
		{
			type(ELEMENT_JS_PRIORITY,data[0],true);
		}
		if (data.length > 1)
			type(ELEMENT_JS_DATA,data[1], true);

		if (data.length >2 )
			selectOption(ELEMENT_JS_ACTIVE, data[2]);

		if (data.length > 3)
			selectOption(ELEMENT_JS_LANGUAGE, data[3]);
		click(ELEMENT_SAVE_CLOSE_BUTTON);
	}
	
	public static void createNewCssFile(String name, String prior, String data){
		type(ELEMENT_CSS_NAME, name, false);
		type(ELEMENT_CSS_PRIORITY, prior, false);
		type(ELEMENT_CSS_DATA, data, true);
		click(ELEMENT_SAVE_CLOSE_BUTTON);
	}
	
	// Edit content template
	public static void editArticle(String title, String title_edit, String sum, String cont, By save)
	{
		goToEditDocument(title);
		type(ELEMENT_ARTICLE_TITLE_TEXTBOX,title_edit,true);

		inputDataToFrame(ELEMENT_ARTICLE_SUMMARY_FRAME,sum,true);
		switchToParentWindow();
		inputDataToFrame(ELEMENT_ARTICLE_CONTENT_FRAME,cont,true);
		switchToParentWindow();
		//save
		click(save);
		if (save ==ELEMENT_CLOSE_BUTTON)
			waitForConfirmation(ELEMENT_MESSAGE_EDIT_CLOSE);
		//check if to finish editing
		if((save == ELEMENT_SAVE_CLOSE_BUTTON) || (save ==ELEMENT_CLOSE_BUTTON))
		{ 	
			waitForElementNotPresent(ELEMENT_SAVE_CLOSE_BUTTON,50000);
		}
		//verify result of editing
		if (save != ELEMENT_CLOSE_BUTTON)
			waitForElementPresent(By.xpath("//a[contains(@title,'"+title_edit+" ')]"),50000);
	}
	
	public static void editCssFile(String name, String prior, String data){
		goToEditDocument(name); 
		type(ELEMENT_CSS_PRIORITY, prior, true);
		type(ELEMENT_CSS_DATA, data, true);
		click(ELEMENT_SAVE_CLOSE_BUTTON);
		waitForElementNotPresent(ELEMENT_SAVE_CLOSE_BUTTON);
	}
	public static void changeEditMode()
	{
		mouseOver(ELEMENT_MENU_EDIT,true);
		mouseOverAndClick(ELEMENT_MENU_CONTENT);
	}
	//edit an article inline
	public static void editArticleInline(String openField, By frame, By accept, String value)
	{
		changeEditMode();
		doubleClickOnElement(openField);
		inputDataToFrame(frame,value,true);
		switchToParentWindow();
		click(accept);
	}
	public static void editFile(String title_edit, String cont_edit, String title){

		goToEditDocument(title)	;
		inputDataToFrame(ELEMENT_NEWFILE_CONTENT_FRAME,cont_edit,true);
		switchToParentWindow();
		type(ELEMENT_NEWFILE_TITLE_TEXTBOX, title_edit, true);
		click(ELEMENT_SAVE_CLOSE_BUTTON);
		waitForElementNotPresent(ELEMENT_SAVE_CLOSE_BUTTON);
		waitForElementPresent(By.xpath("//a[@title='"+title_edit+" ']"));
	}
	//edit a podcast
	public static void editPodcast(String title, String title_edit, String...link_edit){
		goToEditDocument(title);
		type(ELEMENT_PODCAST_TITLE_TEXTBOX, title_edit, true);
		if (link_edit.length > 0)
			type(ELEMENT_PODCAST_LINK_TEXTBOX, link_edit[0], true);
		if (link_edit.length > 1)
			type(ELEMENT_PODCAST_DESCRIPTION, link_edit[1], true);
		click(ELEMENT_SAVE_CLOSE_BUTTON);
		waitForElementNotPresent(ELEMENT_SAVE_CLOSE_BUTTON);
		waitForElementPresent(By.xpath("//a[@title='"+ title_edit+" ']"));
	}
	public static void editPodcastInline(String openField, By inputField, By accept, String value)
	{
		changeEditMode();
		doubleClickOnElement(openField);
		try{
			type(inputField, value, true);
			click(accept);

		}catch(UnhandledAlertException e)
		{	
			assert false: "Fail to edit inline!";
		}
		//verify to edit successfully
		waitForElementPresent(By.xpath("//div[contains(text(),'"+value+"')]"));		
	}

	public static void editSampleNode(String title, String title_edit, String sum_edit, String cont_edit){
		goToEditDocument(title);
		type(ELEMENT_SAMPLENODE_TITLE_TEXTBOX, title_edit, true);
		type(ELEMENT_SAMPLENODE_CONTENT_TEXTAREA, cont_edit, true);
		type(ELEMENT_SAMPLENODE_SUMMARY_TEXTAREA, sum_edit, true);
		click(ELEMENT_SAVE_CLOSE_BUTTON);
		waitForElementNotPresent(ELEMENT_SAVE_CLOSE_BUTTON);
		waitForElementPresent(By.xpath("//a[@title='"+ title_edit+" ']"));
	}
	public static void editSampleNodeInline(String openField, By inputField, String value, By accept)
	{
		changeEditMode();
		doubleClickOnElement(openField);
		type(inputField,value,true);
		click(accept);
		waitForElementPresent(By.xpath("//div[contains(text(),'"+value+"')]"));
	}

	public static void editPictureOnHeadLayout (String title, String title_edit, String file, String...data){
		goToEditDocument(title);
		//click(ELEMENT_UPLOAD_REMOVE);
		click(ELEMENT_PIC_FILE_REMOVE);
		type(ELEMENT_HEAD_LAYOUT_TITLE_TEXTBOX, title_edit, true);

		if (file!=""){
			driver.switchTo().frame(waitForAndGetElement(ELEMENT_HEAD_LAYOUT_UPLOAD_FRAME));
			type(ELEMENT_HEAD_LAYOUT_UPLOAD_FILE, getAbsoluteFilePath(file), false);
			switchToParentWindow();
			waitForElementPresent(ELEMENT_UPLOAD_FINISH_XPATH);
		}
		if (data.length >0)
			type(ELEMENT_PIC_IMGWIDTH, data[0],true);
		if (data.length > 1)
			type(ELEMENT_PIC_IMGHEIGHT, data[1], true);
		if (data.length > 2)
			selectOption(ELEMENT_PIC_LANG, data[2]);
		if (data.length > 3)
		{
			inputDataToFrame(ELEMENT_PIC_CONT, data[3], true);
			switchToParentWindow();
		}
		click(ELEMENT_SAVE_CLOSE_BUTTON);
		waitForElementNotPresent(ELEMENT_SAVE_CLOSE_BUTTON);
		waitForElementPresent(By.xpath("//a[@title='"+title_edit +" ']"),50000);
	}

	public static void editJsFile(String name, String... data)
	{
		goToEditDocument(name);

		if (data.length >0)
		{
			type(ELEMENT_JS_PRIORITY,data[0],true);
		}
		if (data.length > 1)
			type(ELEMENT_JS_DATA,data[1], true);

		if (data.length >2 )
			selectOption(ELEMENT_JS_ACTIVE, data[2]);

		if (data.length > 3)
			selectOption(ELEMENT_JS_LANGUAGE, data[3]);
		click(ELEMENT_SAVE_CLOSE_BUTTON);
		waitForElementNotPresent(ELEMENT_SAVE_CLOSE_BUTTON);
	}
	

	public static void editPictureOnHeadLayout (String title, String title_edit, String file){
		goToEditDocument(title);

		type(ELEMENT_HEAD_LAYOUT_TITLE_TEXTBOX, title_edit, false);

		if (file!=""){
			driver.switchTo().frame(waitForAndGetElement(ELEMENT_HEAD_LAYOUT_UPLOAD_FRAME));
			type(ELEMENT_HEAD_LAYOUT_UPLOAD_FILE, getAbsoluteFilePath(file), false);
			switchToParentWindow();
			waitForElementPresent(ELEMENT_UPLOAD_FINISH_XPATH);
		}
		click(ELEMENT_SAVE_CLOSE_BUTTON);
	}

	public static void editUploadedFile(String name,String uploadFile, String title,String desc, String creator, String source)
	{
		//goToEditDocument(name);
		click(ELEMENT_UPLOAD_REMOVE);
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_UPLOAD_FRAME_EDIT));
		type(ELEMENT_UPLOAD_ID, getAbsoluteFilePath(uploadFile), false);
		switchToParentWindow();
		type(ELEMENT_UPLOAD_TITLE,title, true);
		type(ELEMENT_UPLOAD_DESC, desc, true);
		type(ELEMENT_UPLOAD_CREATOR, creator, true);
		type(ELEMENT_UPLOAD_SOURCE, source, true);
		click(ELEMENT_SAVE_CLOSE_BUTTON);
		waitForElementNotPresent(ELEMENT_SAVE_CLOSE_BUTTON);
	}

	public static void editFreeLayoutWebContentInline(String openField, By inputField, String value, By accept)
	{
		changeEditMode();
		doubleClickOnElement(openField);
		inputDataToFrame(inputField, value, true);
		switchToParentWindow();
		click(accept);
		waitForElementPresent(By.xpath("//p[contains(text(),'"+value+"')]"));
	}

	public static void editFreeLayoutWebContent(String title, String title_edit,  String cont, String img, String sum, String css, String js) 
	{
		goToEditDocument(title);
		type(ELEMENT_WEBCONTENT_TITLE_TEXTBOX, title_edit, true);
		//		waitForAndGetElement(ELEMENT_WEBCONTENT_NAME_TEXTBOX).clear();

		inputDataToFrame(ELEMENT_WEBCONTENT_CONTENT_FRAME,cont,true);
		switchToParentWindow();
		if (cont!="" || img !=""){
			click(ELEMENT_WEBCONTENT_ILLUSTRATION_TAB);
			waitForElementPresent(By.xpath("//div[@class='SelectedTab']/div/div/div[contains(text(),'Illustration')]"));
			if (img!=""){
				WebElement removeIcon = waitForAndGetElement(ELEMENT_UPLOAD_REMOVE);
				if (isDisplay(removeIcon))
				{
					click(ELEMENT_UPLOAD_REMOVE); }
				pause(1000);
				driver.switchTo().frame(waitForAndGetElement(ELEMENT_WEBCONTENT_UPLOAD_FRAME));
				type(ELEMENT_WEBCONTENT_FILE_IMAGE, getAbsoluteFilePath(img), false);
				switchToParentWindow();
				String links[] = img.split("/");
				int length = links.length;
				waitForElementPresent(By.xpath("//div[contains(text(),'" + links[length-1]+ "')]"));

			}
			inputDataToFrame(ELEMENT_WEBCONTENT_SUMMARY_FRAME,sum,true);
			switchToParentWindow();
		}
		if(css!="" || js !=""){
			click(ELEMENT_WEBCONTENT_ADVANCE_TAB);
			type(ELEMENT_WEBCONTENT_CSS_TEXTAREA, css, true);
			type(ELEMENT_WEBCONTENT_JS_TEXTAREA, js, true);
		}
		click(ELEMENT_SAVE_CLOSE_BUTTON);	
		waitForElementNotPresent(ELEMENT_SAVE_CLOSE_BUTTON,50000);
		waitForElementPresent(By.xpath("//a[@title='"+title_edit+" ']"));
	}
}
