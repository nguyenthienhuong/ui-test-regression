package org.exoplatform.selenium.platform.ecms.functional.dms.siteexplorer.basicaction;

import static org.exoplatform.selenium.TestLogger.*;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.*;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.*;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 * @author: ThuNTN
 * @date: 24/09/2012
 */

public class ECMS_DMS_SE_BasicAction_Edit extends EcmsBase {
	public static final String ELEMENT_ARTICLE_SUM="//div[contains(@id,'CurrentSummary') and @title='Double-click to edit']";
	public static final By ELEMENT_ARTICLE_SUM_FRAME=By.xpath("//table[@class='cke_editor']/tbody/tr[2]/td/iframe");
	public static final By ELEMENT_ACCEPT_ARTICLE_SUM=By.xpath("//form[contains(@id,'EditSummaryForm')]/a[2]");
	public static final By ELEMENT_ARTICLE_CONT_FRAME=By.xpath("//td[contains(@id,'cke_contents_newText')]/iframe");
	public static final By ELEMENT_ACCEPT_ARTICLE_CONT=By.xpath("//form[contains(@id,'EditTextForm')]/a[2]");
	public static final String ELEMENT_ARTICLE_CONT="//div[contains(@id,'CurrentText') and @title='Double-click to edit']";
	public static final By ELEMENT_UPLOAD_REMOVE=By.xpath("//img[@class='ActionIcon Remove16x16Icon']");
	public static String USER = "john";
	public static String PASS = "gtn";

	String DATA_UPLOAD_IMG_2="TestData/FNC_ECMS_FEX_ACTION_09_2.png";
	String DATA_UPLOAD_IMG_1="TestData/FNC_ECMS_FEX_ACTION_09_1.png";

	//edit document by clicking on Save & Close button
	@Test
	public void test02_EditDocumentWithSaveCloseButton() {
		String DATA_ARTICLE_TITLE_02="FNC_ECMS_FEX_ACTION_09_02";
		String DATA_ARTICLE_TITLE_02_EDIT="FNC_ECMS_FEX_ACTION_09_02 edit";

		By bDocument=By.xpath("//a[@title='" + DATA_ARTICLE_TITLE_02 + " ']");
		By bDocEdit= By.xpath("//a[@title='" + DATA_ARTICLE_TITLE_02_EDIT + " ']");

		info("Edit document by clicking on Save & Close button");
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToAddNewContent();
		createNewArticle(DATA_ARTICLE_TITLE_02, DATA_ARTICLE_TITLE_02, DATA_ARTICLE_TITLE_02,DATA_ARTICLE_TITLE_02);
		waitForElementPresent(bDocument);
		editArticle(DATA_ARTICLE_TITLE_02, DATA_ARTICLE_TITLE_02_EDIT, DATA_ARTICLE_TITLE_02_EDIT, DATA_ARTICLE_TITLE_02_EDIT, ELEMENT_SAVE_CLOSE_BUTTON);

		//delete document
		deleteDocument(bDocEdit);
	}
	//edit document by clicking on Save button
	@Test
	public void test03_EditDocumentWithSaveButton() {
		String DATA_ARTICLE_TITLE_03="FNC_ECMS_FEX_ACTION_09_03";
		String DATA_ARTICLE_TITLE_03_EDIT="FNC_ECMS_FEX_ACTION_09_03 edit";
		By bDocument=By.xpath("//a[@title='" + DATA_ARTICLE_TITLE_03 + " ']");
		By bDocEdit= By.xpath("//a[contains(@title,'" + DATA_ARTICLE_TITLE_03_EDIT + "')]");

		info("Edit document by clicking on Save button");
		//choose  site management drive, and create an article
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToAddNewContent();
		createNewArticle(DATA_ARTICLE_TITLE_03, DATA_ARTICLE_TITLE_03, DATA_ARTICLE_TITLE_03, DATA_ARTICLE_TITLE_03);
		waitForElementPresent(bDocument);

		//edit article
		editArticle(DATA_ARTICLE_TITLE_03, DATA_ARTICLE_TITLE_03_EDIT, DATA_ARTICLE_TITLE_03_EDIT, DATA_ARTICLE_TITLE_03_EDIT, ELEMENT_SAVE_BUTTON);
		waitForElementPresent(ELEMENT_SAVE_BUTTON);

		//delete document
		goToNode(bDocEdit);
		deleteDocument(bDocEdit);
	}
	//edit document by clicking on Close button
	@Test
	public void test04_EditDocumentWithCloseButton() {
		String DATA_ARTICLE_TITLE_04="FNC_ECMS_FEX_ACTION_09_04";
		String DATA_ARTICLE_TITLE_04_EDIT="FNC_ECMS_FEX_ACTION_09_04 edit";
		By bDocument=By.xpath("//a[@title='" + DATA_ARTICLE_TITLE_04 + " ']");
		By bDocEdit= By.xpath("//a[contains(@title,'" + DATA_ARTICLE_TITLE_04_EDIT + "')]");

		info("Edit document by clicking on Save button");
		//choose  site management drive, and create an article
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToAddNewContent();
		createNewArticle(DATA_ARTICLE_TITLE_04, DATA_ARTICLE_TITLE_04, DATA_ARTICLE_TITLE_04, DATA_ARTICLE_TITLE_04);
		waitForElementPresent(bDocument);

		//edit article
		editArticle(DATA_ARTICLE_TITLE_04, DATA_ARTICLE_TITLE_04_EDIT, DATA_ARTICLE_TITLE_04, DATA_ARTICLE_TITLE_04, ELEMENT_CLOSE_BUTTON);
		waitForElementNotPresent(bDocEdit);

		//delete document
		goToNode(bDocument);
		deleteDocument(bDocument);
	}
	//edit document by clicking on Save and Close buttons
	@Test
	public void test05_EditDocumentWithSaveAndCloseButtons() {
		String DATA_ARTICLE_TITLE_05="FNC_ECMS_FEX_ACTION_09_05";
		String DATA_ARTICLE_TITLE_05_EDIT="FNC_ECMS_FEX_ACTION_09_05 edit";
		By bDocument=By.xpath("//a[@title='" + DATA_ARTICLE_TITLE_05 + " ']");
		By bDocEdit= By.xpath("//a[contains(@title,'" + DATA_ARTICLE_TITLE_05_EDIT + "')]");

		info("Edit document by clicking on  Save and Close buttons");
		//choose  site management drive, and create an article
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToAddNewContent();
		createNewArticle(DATA_ARTICLE_TITLE_05, DATA_ARTICLE_TITLE_05, DATA_ARTICLE_TITLE_05, DATA_ARTICLE_TITLE_05);
		waitForElementPresent(bDocument);

		//edit article
		editArticle(DATA_ARTICLE_TITLE_05, DATA_ARTICLE_TITLE_05_EDIT, DATA_ARTICLE_TITLE_05_EDIT, DATA_ARTICLE_TITLE_05_EDIT, ELEMENT_SAVE_BUTTON);
		click(ELEMENT_CLOSE_BUTTON);

		//check if to finish editing
		waitForElementNotPresent(ELEMENT_SAVE_BUTTON);
		waitForElementPresent(bDocEdit);

		//delete document
		goToNode(bDocEdit);
		deleteDocument(bDocEdit);
	}
	//edit  name of an article
	@Test
	public void test06_EditNameOfArticle() {
		String DATA_ARTICLE_TITLE_06="FNC_ECMS_FEX_ACTION_09_06";
		String DATA_ARTICLE_TITLE_06_EDIT="FNC_ECMS_FEX_ACTION_09_06 edit";
		By bDocument=By.xpath("//a[contains(@title,'" + DATA_ARTICLE_TITLE_06 + " ')]");
		info("Edit  name of an article");

		//choose site management drive, and create an article
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToAddNewContent();
		createNewArticle(DATA_ARTICLE_TITLE_06, DATA_ARTICLE_TITLE_06, DATA_ARTICLE_TITLE_06, DATA_ARTICLE_TITLE_06);
		waitForElementPresent(bDocument);

		//edit article
		goToEditDocument(DATA_ARTICLE_TITLE_06);
		// check if to edit "Name" field
		try{

			type(ELEMENT_ARTICLE_NAME_TEXTBOX,DATA_ARTICLE_TITLE_06_EDIT,true);
		}
		catch(InvalidElementStateException e){
			goToNode(bDocument);
			deleteDocument(bDocument);
			assert true;
		}	
	}

	//edit title of an article
	@Test
	public void test07_EditTitleOfArticle() {
		String DATA_ARTICLE_TITLE_07="FNC_ECMS_FEX_ACTION_09_07";
		String DATA_ARTICLE_TITLE_07_EDIT="FNC_ECMS_FEX_ACTION_09_07 edit";
		By bDocument=By.xpath("//a[contains(@title,'" + DATA_ARTICLE_TITLE_07 + " ')]");
		info("Edit title of an article");

		//choose site management drive, and create an article
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToAddNewContent();
		createNewArticle(DATA_ARTICLE_TITLE_07, DATA_ARTICLE_TITLE_07, DATA_ARTICLE_TITLE_07, DATA_ARTICLE_TITLE_07);
		waitForElementPresent(bDocument);

		editArticle(DATA_ARTICLE_TITLE_07, DATA_ARTICLE_TITLE_07_EDIT, DATA_ARTICLE_TITLE_07, DATA_ARTICLE_TITLE_07,ELEMENT_SAVE_CLOSE_BUTTON);
		//delete document
		deleteDocument(bDocument);
	}

	//edit summary of an article
	@Test
	public void test08_EditSummaryOfArticle() {
		String DATA_ARTICLE_TITLE_08="FNC_ECMS_FEX_ACTION_09_08";
		String DATA_ARTICLE_TITLE_08_EDIT="FNC_ECMS_FEX_ACTION_09_08 edit";
		By bDocument=By.xpath("//a[contains(@title,'" + DATA_ARTICLE_TITLE_08 + " ')]");
		info("Edit summary of an article");

		//choose site management drive, and create an article
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToAddNewContent();
		createNewArticle(DATA_ARTICLE_TITLE_08, DATA_ARTICLE_TITLE_08, DATA_ARTICLE_TITLE_08, DATA_ARTICLE_TITLE_08);
		waitForElementPresent(bDocument);

		editArticle(DATA_ARTICLE_TITLE_08, DATA_ARTICLE_TITLE_08, DATA_ARTICLE_TITLE_08_EDIT, DATA_ARTICLE_TITLE_08,ELEMENT_SAVE_CLOSE_BUTTON);
		//delete document
		deleteDocument(bDocument);
	}

	//edit content of an article
	@Test
	public void test09_EditContentOfArticle() {
		String DATA_ARTICLE_TITLE_09="FNC_ECMS_FEX_ACTION_09_09";
		String DATA_ARTICLE_TITLE_09_EDIT="FNC_ECMS_FEX_ACTION_09_09 edit";
		By bDocument=By.xpath("//a[contains(@title,'" + DATA_ARTICLE_TITLE_09 + " ')]");
		info("Edit  content of an article");

		//choose  site management drive, and create an article
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToAddNewContent();
		createNewArticle(DATA_ARTICLE_TITLE_09, DATA_ARTICLE_TITLE_09, DATA_ARTICLE_TITLE_09, DATA_ARTICLE_TITLE_09);
		waitForElementPresent(bDocument);

		editArticle(DATA_ARTICLE_TITLE_09, DATA_ARTICLE_TITLE_09, DATA_ARTICLE_TITLE_09, DATA_ARTICLE_TITLE_09_EDIT,ELEMENT_SAVE_CLOSE_BUTTON);
		//delete document
		deleteDocument(bDocument);
	}
	// switch to edit mode

	//edit  summary of an article in edit mode
	@Test
	public void test10_EditSummaryOfArticleInEditMode() {
		String DATA_ARTICLE_TITLE_10="FNC_ECMS_FEX_ACTION_09_10";
		By bDocument=By.xpath("//a[contains(@title,'" + DATA_ARTICLE_TITLE_10 + " ')]");
		info("Edit  summary of an article");

		//choose  site management drive, and create an article
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToAddNewContent();
		createNewArticle(DATA_ARTICLE_TITLE_10, DATA_ARTICLE_TITLE_10, DATA_ARTICLE_TITLE_10, DATA_ARTICLE_TITLE_10);
		waitForElementPresent(bDocument);

		//edit  article
		editArticleInline(ELEMENT_ARTICLE_SUM, ELEMENT_ARTICLE_SUM_FRAME, ELEMENT_ACCEPT_ARTICLE_SUM, "edited sum");
		//delete document
		deleteDocument(bDocument,50000);
	}

	//edit  content of an article in edit mode
	@Test
	public void test11_EditContentOfArticleInEditMode() {
		String DATA_ARTICLE_TITLE_11="FNC_ECMS_FEX_ACTION_09_11";
		By bDocument=By.xpath("//a[contains(@title,'" + DATA_ARTICLE_TITLE_11 + " ')]");
		info("Edit  content of an article");

		//choose  site management drive, and create an article
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToAddNewContent();
		createNewArticle(DATA_ARTICLE_TITLE_11, DATA_ARTICLE_TITLE_11, DATA_ARTICLE_TITLE_11, DATA_ARTICLE_TITLE_11);
		waitForElementPresent(bDocument);

		//edit  article
		editArticleInline(ELEMENT_ARTICLE_CONT, ELEMENT_ARTICLE_CONT_FRAME, ELEMENT_ACCEPT_ARTICLE_CONT, "edited content");
		//delete document
		deleteDocument(bDocument);
	}

	//edit  name of a file document in edit mode
	@Test
	public void test12_EditNameOfFileDocument() {
		String DATA_FILE_NAME_12="FNC_ECMS_FEX_ACTION_09_12";
		By bDocument=By.xpath("//a[contains(@title,'" + DATA_FILE_NAME_12 + " ')]");
		info("Edit  name of a file");

		//choose  site management drive, and create a file document
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToAddNewContent();
		createNewFile(DATA_FILE_NAME_12, DATA_FILE_NAME_12, DATA_FILE_NAME_12);
		waitForElementPresent(bDocument);

		//edit a file document
		goToEditDocument(DATA_FILE_NAME_12);
		// check if to edit  "Name" field
		try{
			type(ELEMENT_NEWFILE_NAME_TEXTBOX,DATA_FILE_NAME_12,true);
		}
		catch(InvalidElementStateException e){
			goToNode(bDocument);
			deleteDocument(bDocument);
			assert true;
		}	
	}

	//edit content of file document
	@Test
	public void test13_EditContentOfFile(){	
		String DATA_FILE_TITLE_13_EDIT="FNC_ECMS_FEX_ACTION_09_13 edit";
		String DATA_FILE_TITLE_13="FNC_ECMS_FEX_ACTION_09_13";
		By bDocument=By.xpath("//a[@title='"+DATA_FILE_TITLE_13+" ']");
		info("Edit content of file document");

		//choose  site management drive, and create a file document	
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToAddNewContent();
		createNewFile(DATA_FILE_TITLE_13, DATA_FILE_TITLE_13, DATA_FILE_TITLE_13);
		waitForElementPresent(bDocument);

		//edit and delete file
		editFile(DATA_FILE_TITLE_13, DATA_FILE_TITLE_13_EDIT, DATA_FILE_TITLE_13);
		deleteDocument(bDocument);
	}

	//edit name of podcast
	@Test
	public void test14_EditNameOfPodcast(){	
		String DATA_PODCAST_TITLE_14="FNC_ECMS_FEX_ACTION_09_14";
		By bDocument=By.xpath("//a[contains(@title,'" + DATA_PODCAST_TITLE_14 + " ')]");
		info("Edit  name of a podcast");

		//choose  site management drive, and create a podcast
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToAddNewContent();
		createNewPodcast(DATA_PODCAST_TITLE_14, DATA_PODCAST_TITLE_14, DATA_PODCAST_TITLE_14);
		waitForElementPresent(bDocument);

		// edit a podcast document
		goToEditDocument(DATA_PODCAST_TITLE_14);

		// check if to edit  "Name" field
		try{
			type(ELEMENT_PODCAST_NAME_TEXTBOX,DATA_PODCAST_TITLE_14,true);
		}
		catch(InvalidElementStateException e){
			goToNode(bDocument);
			deleteDocument(bDocument);
			assert true;
		}	
	}
	//edit title of podcast
	@Test
	public void test15_EditTitleOfPodcast(){	
		String DATA_PODCAST_TITLE_15_EDIT="FNC_ECMS_FEX_ACTION_09_15 edit";
		String DATA_PODCAST_TITLE_15="FNC_ECMS_FEX_ACTION_09_15";
		By bDocument=By.xpath("//a[@title='"+DATA_PODCAST_TITLE_15+" ']");
		By bDocEdit=By.xpath("//a[@title='"+DATA_PODCAST_TITLE_15_EDIT+" ']");
		info("Edit title of podcast document");

		//choose  site management drive, and create a podcast document	
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToAddNewContent();
		createNewPodcast(DATA_PODCAST_TITLE_15, DATA_PODCAST_TITLE_15, DATA_PODCAST_TITLE_15);
		waitForElementPresent(bDocument);

		//edit and delete podcast
		editPodcast(DATA_PODCAST_TITLE_15, DATA_PODCAST_TITLE_15_EDIT, DATA_PODCAST_TITLE_15);
		deleteDocument(bDocEdit);
	}
	//edit description of podcast
	@Test
	public void test16_EditDescriptionOfPodcast(){	
		String DATA_PODCAST_TITLE_16_EDIT="FNC_ECMS_FEX_ACTION_09_16 edit";
		String DATA_PODCAST_TITLE_16="FNC_ECMS_FEX_ACTION_09_16";
		By bDocument=By.xpath("//a[@title='"+DATA_PODCAST_TITLE_16+" ']");
		info("Edit description of podcast document");

		//choose  site management drive, and create a podcast document	
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToAddNewContent();
		createNewPodcast(DATA_PODCAST_TITLE_16, DATA_PODCAST_TITLE_16, DATA_PODCAST_TITLE_16);
		waitForElementPresent(bDocument);

		//edit and delete podcast
		editPodcast(DATA_PODCAST_TITLE_16, DATA_PODCAST_TITLE_16, DATA_PODCAST_TITLE_16,DATA_PODCAST_TITLE_16_EDIT);
		deleteDocument(bDocument);
	}

	//edit title of podcast
	@Test
	public void test17_EditTitleOfPodcastInline(){	
		String DATA_PODCAST_TITLE_17_EDIT="FNC_ECMS_FEX_ACTION_09_17 edit";
		String DATA_PODCAST_TITLE_17="FNC_ECMS_FEX_ACTION_09_17";
		By bDocument=By.xpath("//a[@title='"+DATA_PODCAST_TITLE_17+" ']");
		By bDocEdit=By.xpath("//a[@title='"+DATA_PODCAST_TITLE_17_EDIT+" ']");
		info("Edit title of podcast document inline");

		//choose  site management drive, and create a podcast document	
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToAddNewContent();
		createNewPodcast(DATA_PODCAST_TITLE_17, DATA_PODCAST_TITLE_17, DATA_PODCAST_TITLE_17);
		waitForElementPresent(bDocument);

		//edit and delete podcast
		editPodcastInline(ELEMENT_PODCAST_TITLE_INLINE, ELEMENT_PODCAST_TITLE_INPUT, ELEMENT_PODCAST_TITLE_ACCEPT, DATA_PODCAST_TITLE_17_EDIT);
		deleteDocument(bDocEdit);
	}
	//edit description of podcast inline
	@Test
	public void test18_EditDescriptionOfPodcastInline(){	
		String DATA_PODCAST_TITLE_18_EDIT="FNC_ECMS_FEX_ACTION_09_18 edit";
		String DATA_PODCAST_TITLE_18="FNC_ECMS_FEX_ACTION_09_18";
		By bDocument=By.xpath("//a[@title='"+DATA_PODCAST_TITLE_18+" ']");

		info("Edit Description of podcast document inline");
		//choose  site management drive, and create a podcast document	
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToAddNewContent();
		createNewPodcast(DATA_PODCAST_TITLE_18, DATA_PODCAST_TITLE_18, DATA_PODCAST_TITLE_18);
		waitForElementPresent(bDocument);

		//edit and delete podcast
		editPodcastInline(ELEMENT_PODCAST_DESC_INLINE, ELEMENT_PODCAST_DESC_INPUT, ELEMENT_PODCAST_DESC_ACCEPT, DATA_PODCAST_TITLE_18_EDIT);
		deleteDocument(bDocument);
	}
	//edit name of sample node
	@Test
	public void test20_EditNameOfSampleNode(){	
		String DATA_SAMPLE_TITLE_20="FNC_ECMS_FEX_ACTION_09_20";
		By bDocument=By.xpath("//a[contains(@title,'" + DATA_SAMPLE_TITLE_20 + " ')]");
		info("Edit  name of a sample node");

		//choose  site management drive, and create a sample node
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToAddNewContent();
		createNewSampleNode(DATA_SAMPLE_TITLE_20, DATA_SAMPLE_TITLE_20, DATA_SAMPLE_TITLE_20);
		waitForElementPresent(bDocument);

		// edit a sample node document
		goToEditDocument(DATA_SAMPLE_TITLE_20);

		// check if to edit  "Name" field
		try{
			type(ELEMENT_SAMPLENODE_NAME_TEXTBOX,DATA_SAMPLE_TITLE_20,true);
		}
		catch(InvalidElementStateException e){
			goToNode(bDocument);
			deleteDocument(bDocument);
			assert true;
		}	
	}

	//edit title of sample node
	@Test
	public void test21_EditTitleOfSampleNode(){	
		String DATA_SAMPLE_TITLE_21_EDIT="FNC_ECMS_FEX_ACTION_09_21 edit";
		String DATA_SAMPLE_TITLE_21="FNC_ECMS_FEX_ACTION_09_21";
		By bDocument=By.xpath("//a[@title='"+DATA_SAMPLE_TITLE_21+" ']");
		By bDocEdit=By.xpath("//a[@title='"+DATA_SAMPLE_TITLE_21_EDIT+" ']");

		info("edit title of sample node document");
		//choose  site management drive, and create a sample node document	
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToAddNewContent();
		createNewSampleNode(DATA_SAMPLE_TITLE_21, DATA_SAMPLE_TITLE_21, DATA_UPLOAD_IMG_2);
		waitForElementPresent(bDocument);

		//edit and delete sample node
		editSampleNode(DATA_SAMPLE_TITLE_21, DATA_SAMPLE_TITLE_21_EDIT, DATA_SAMPLE_TITLE_21,DATA_SAMPLE_TITLE_21);
		deleteDocument(bDocEdit);
	}
	//Edit summary of sample node document
	@Test
	public void test22_EditSummaryOfSampleNode(){	
		String DATA_SAMPLE_TITLE_22_EDIT="FNC_ECMS_FEX_ACTION_09_22 edit";
		String DATA_SAMPLE_TITLE_22="FNC_ECMS_FEX_ACTION_09_22";
		By bDocument=By.xpath("//a[@title='"+DATA_SAMPLE_TITLE_22+" ']");

		info("Edit summary of sample node document");
		//choose  site management drive, and create a sample node document	
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToAddNewContent();
		createNewSampleNode(DATA_SAMPLE_TITLE_22, DATA_SAMPLE_TITLE_22, DATA_UPLOAD_IMG_2);
		waitForElementPresent(bDocument);

		//edit and delete sample node
		editSampleNode(DATA_SAMPLE_TITLE_22, DATA_SAMPLE_TITLE_22, DATA_SAMPLE_TITLE_22_EDIT,DATA_SAMPLE_TITLE_22);
		deleteDocument(bDocument);
	}
	//Edit Content of sample node document
	@Test
	public void test23_EditContentOfSampleNode(){	
		String DATA_SAMPLE_TITLE_23_EDIT="FNC_ECMS_FEX_ACTION_09_23 edit";
		String DATA_SAMPLE_TITLE_23="FNC_ECMS_FEX_ACTION_09_23";
		By bDocument=By.xpath("//a[@title='"+DATA_SAMPLE_TITLE_23+" ']");

		info("Edit content of sample node document");

		//choose  site management drive, and create a sample node document	
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToAddNewContent();
		createNewSampleNode(DATA_SAMPLE_TITLE_23, DATA_SAMPLE_TITLE_23, DATA_UPLOAD_IMG_2);
		waitForElementPresent(bDocument);

		//edit and delete sample node
		editSampleNode(DATA_SAMPLE_TITLE_23, DATA_SAMPLE_TITLE_23, DATA_SAMPLE_TITLE_23,DATA_SAMPLE_TITLE_23_EDIT);
		deleteDocument(bDocument);
	}

	//Edit title of sample node document inline
	@Test
	public void test24_EditTitleOfSampleNodeInline(){	
		String DATA_SAMPLE_TITLE_24_EDIT="FNC_ECMS_FEX_ACTION_09_24 edit";
		String DATA_SAMPLE_TITLE_24="FNC_ECMS_FEX_ACTION_09_24";
		By bDocument=By.xpath("//a[@title='"+DATA_SAMPLE_TITLE_24+" ']");
		By bDocEdit=By.xpath("//a[@title='"+DATA_SAMPLE_TITLE_24_EDIT+" ']");
		info("Edit title of sample node document inline");

		//choose  site management drive, and create a sample node document	
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToAddNewContent();
		createNewSampleNode(DATA_SAMPLE_TITLE_24, DATA_SAMPLE_TITLE_24, DATA_UPLOAD_IMG_2);
		waitForElementPresent(bDocument);

		//edit and delete sample node
		editSampleNodeInline(ELEMENT_SAMPLE_TITLE_INLINE,ELEMENT_SAMPLE_TITLE_INPUT,DATA_SAMPLE_TITLE_24_EDIT,ELEMENT_SAMPLE_TITLE_ACCEPT);
		deleteDocument(bDocEdit);
	}
	//Edit summary of sample node document inline
	@Test
	public void test25_EditSummaryOfSampleNodeInline(){	
		String DATA_SAMPLE_TITLE_25_EDIT="FNC_ECMS_FEX_ACTION_09_25 edit";
		String DATA_SAMPLE_TITLE_25="FNC_ECMS_FEX_ACTION_09_25";

		By bDocument=By.xpath("//a[@title='"+DATA_SAMPLE_TITLE_25+" ']");

		info("Edit summary of sample node document inline");
		//choose  site management drive, and create a sample node document	
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToAddNewContent();
		createNewSampleNode(DATA_SAMPLE_TITLE_25, DATA_SAMPLE_TITLE_25, DATA_UPLOAD_IMG_2);
		waitForElementPresent(bDocument);

		//edit and delete sample node
		editSampleNodeInline(ELEMENT_SAMPLE_SUM_INLINE,ELEMENT_SAMPLE_SUM_INPUT,DATA_SAMPLE_TITLE_25_EDIT,ELEMENT_SAMPLE_SUM_ACCEPT);
		deleteDocument(bDocument);
	}

	//Edit content of sample node document in-line
	@Test
	public void test26_EditContentOfSampleNodeInline(){	
		String DATA_SAMPLE_TITLE_26_EDIT="FNC_ECMS_FEX_ACTION_09_26 edit";
		String DATA_SAMPLE_TITLE_26="FNC_ECMS_FEX_ACTION_09_26";

		By bDocument=By.xpath("//a[@title='"+DATA_SAMPLE_TITLE_26+" ']");

		info("Edit content of sample node document inline");
		//choose  site management drive, and create a sample node document	
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToAddNewContent();
		createNewSampleNode(DATA_SAMPLE_TITLE_26, DATA_SAMPLE_TITLE_26, DATA_UPLOAD_IMG_2);
		waitForElementPresent(bDocument,80000);

		//edit and delete sample node
		editSampleNodeInline(ELEMENT_SAMPLE_CONT_INLINE,ELEMENT_SAMPLE_CONT_INPUT,DATA_SAMPLE_TITLE_26_EDIT,ELEMENT_SAMPLE_CONT_ACCEPT);
		deleteDocument(bDocument);
	}
	//edit name of file document
	@Test
	public void test27_EditNameOfFileDocument(){	
		String DATA_FILE_TITLE_27="FNC_ECMS_FEX_ACTION_09_27";
		By bDocument=By.xpath("//a[contains(@title,'" + DATA_FILE_TITLE_27 + " ')]");
		info("Edit  name of a file document");

		//choose  site management drive, and create a file document
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToAddNewContent();
		createNewFile(DATA_FILE_TITLE_27, DATA_FILE_TITLE_27, DATA_FILE_TITLE_27);
		waitForElementPresent(bDocument);

		// edit a file document
		goToEditDocument(DATA_FILE_TITLE_27);

		// check if to edit  "Name" field
		try{
			type(ELEMENT_NEWFILE_NAME_TEXTBOX,DATA_FILE_TITLE_27,true);
		}
		catch(InvalidElementStateException e){
			goToNode(bDocument);
			deleteDocument(bDocument);
			assert true;
		}	
	}

	//edit name of kofax document
	@Test
	public void test28_EditNameOfKofaxDocument(){	
		String DATA_KOX_TITLE_28="FNC_ECMS_FEX_ACTION_09_28";
		By bDocument=By.xpath("//a[contains(@title,'" + DATA_KOX_TITLE_28 + " ')]");
		info("Edit  name of a kofax document");

		//choose  site management drive, and create a kofax document
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToAddNewContent();
		createNewKofax(DATA_KOX_TITLE_28);
		waitForElementPresent(bDocument);

		// edit a kofax document
		goToEditDocument(DATA_KOX_TITLE_28);

		// check if to edit  "Name" field
		try{
			type(ELEMENT_KOFAX_NAME_TEXTBOX,DATA_KOX_TITLE_28,true);
		}
		catch(InvalidElementStateException e){
			goToNode(bDocument);
			deleteDocument(bDocument);
			assert true;
		}	
	}

	//edit uploaded file
	@Test
	public void test29_EditUploadedFile(){	
		String DATA_UPLOAD_TITLE_29_EDIT="FNC_ECMS_FEX_ACTION_09_29 edit";
		String DATA_UPLOAD_TITLE_29="FNC_ECMS_FEX_ACTION_09_29";	
		By bDocument=By.xpath("//a[contains(@title,'" + DATA_UPLOAD_TITLE_29 + ".png ')]");
		info("Edit uploaded file");

		//choose  site management drive, and create a uploaded file
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		uploadFile(DATA_UPLOAD_TITLE_29, DATA_UPLOAD_IMG_2);
		waitForElementPresent(bDocument);
		goToNode(bDocument);

		// edit a uploaded file
		goToEditDocument(DATA_UPLOAD_TITLE_29 + ".png");
		editUploadedFile(DATA_UPLOAD_TITLE_29 + ".png", DATA_UPLOAD_IMG_1, DATA_UPLOAD_TITLE_29_EDIT, DATA_UPLOAD_TITLE_29_EDIT, DATA_UPLOAD_TITLE_29_EDIT, DATA_UPLOAD_TITLE_29_EDIT);
		deleteDocument(bDocument);
	}
	//edit locked document
	@Test
	public void test30_EditLockedDocument(){	
		String DATA_ARTICLE_TITLE_30="FNC_ECMS_FEX_ACTION_09_30";
		By bDocument=By.xpath("//a[contains(@title,'" + DATA_ARTICLE_TITLE_30 + " ')]");
		info("Edit locked document");

		//choose  site management drive, and create a locked document
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToAddNewContent();
		createNewArticle(DATA_ARTICLE_TITLE_30, DATA_ARTICLE_TITLE_30, DATA_ARTICLE_TITLE_30, DATA_ARTICLE_TITLE_30);
		waitForElementPresent(bDocument);
		lockNode(bDocument);

		// edit a article
		goToEditDocument(DATA_ARTICLE_TITLE_30);
		goToNode(bDocument);
		waitForElementNotPresent(ELEMENT_SAVE_CLOSE_BUTTON);
		deleteDocument(bDocument);
	}

	/*case31: Edit locked document by user isn't locker
	 * create new document with user John
	 * Lock document by John
	 * check can not edit this document with user Mary
	 */
	@Test
	public void test31_EditLockedDocumentByUserIsNotLocker(){
		String DATA_ARTICLE_TITLE="FNC_ECMS_FEX_ACTION_09_31";
		By ELEMENT_ARTICLE = By.linkText(DATA_ARTICLE_TITLE);

		//create new document with John: article document
		goToSiteExplorer();
		goToAddNewContent();
		createNewArticle(DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, "", "");
		waitForElementPresent(ELEMENT_ARTICLE);
		info("Create new article document is successful");

		//lock node with John
		goToNode(ELEMENT_ARTICLE);
		lockNode(ELEMENT_ARTICLE);

		//check lock node
		checkLockNode(ELEMENT_ARTICLE);
		driver.close();

		//login with mary
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		loginEcms("mary", "gtn");
		goToSiteExplorer();

		//check can not edit this document with user Mary
		goToNode(ELEMENT_ARTICLE);
		waitForElementNotPresent(ELEMENT_EDIT_LINK);
		rightClickOnElement(ELEMENT_ARTICLE);
		waitForElementNotPresent(ELEMENT_MENU_EDIT_ITEM);
		info("Can not edit locked document with user is not locker");
		logoutEcms();

		//delete data with John
		loginEcms(USER, PASS);
		goToSiteExplorer();
		deleteData(ELEMENT_ARTICLE);		
	}

	//edit a document in a locked document
	@Test
	public void test32_EditDocumentInLockedDocument(){	
		String DATA_ARTICLE_TITLE_32="FNC_ECMS_FEX_ACTION_09_32";
		String DATA_PODCAST_TITLE_32="FNC_ECMS_FEX_ACTION_09_32_sam";
		By bDocument=By.xpath("//a[contains(@title,'" + DATA_ARTICLE_TITLE_32 + " ')]");
		By bChild=By.xpath("//a[contains(@title,'" + DATA_PODCAST_TITLE_32 + " ')]");

		info("Edit a document in a locked document");

		//choose site management drive, and create an article
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToAddNewContent();
		createNewArticle(DATA_ARTICLE_TITLE_32, DATA_ARTICLE_TITLE_32, DATA_ARTICLE_TITLE_32, DATA_ARTICLE_TITLE_32);
		waitForElementPresent(bDocument);

		//add a child node - podcast
		goToAddNewContent();
		createNewPodcast(DATA_PODCAST_TITLE_32, DATA_PODCAST_TITLE_32, DATA_PODCAST_TITLE_32);
		lockNode(bDocument);
		checkPreferenceOption(ELEMENT_ENABLE_DMS_STRUCTURE);
		goToNode(bChild);

		//check expected: can edit a podcast, and cannot modify  'name' field
		goToEditDocument(DATA_PODCAST_TITLE_32);
		try{
			type(ELEMENT_PODCAST_NAME_TEXTBOX,DATA_PODCAST_TITLE_32,true);
		}
		catch(InvalidElementStateException e){
			goToNode(bDocument);
			waitForElementNotPresent(ELEMENT_SAVE_CLOSE_BUTTON);
			deleteDocument(bDocument);
			assert true;
		}
	}
	//edit checked in document
	@Test
	public void test33_EditCheckedInDocument(){	
		String DATA_ARTICLE_TITLE_33="FNC_ECMS_FEX_ACTION_09_33";
		By bDocument=By.xpath("//a[contains(@title,'" + DATA_ARTICLE_TITLE_33 + " ')]");
		info("Edit locked document");

		//choose  site management drive, and create an article document
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToAddNewContent();
		createNewArticle(DATA_ARTICLE_TITLE_33, DATA_ARTICLE_TITLE_33, DATA_ARTICLE_TITLE_33, DATA_ARTICLE_TITLE_33);
		waitForElementPresent(bDocument);
		checkInNode(bDocument);

		// edit if to edit
		waitForElementNotPresent(ELEMENT_EDIT_LINK);
		checkOutNode(bDocument);
		//delete
		deleteDocument(bDocument);
	}
	//edit a document in a checked in document
	@Test
	public void test34_EditDocumentInCheckedInDocument(){	
		String DATA_ARTICLE_TITLE_34="FNC_ECMS_FEX_ACTION_09_34";
		String DATA_PODCAST_TITLE_34="FNC_ECMS_FEX_ACTION_09_34_pod";

		By bDocument=By.xpath("//a[contains(@title,'" + DATA_ARTICLE_TITLE_34 + " ')]");
		info("Edit a document in a checked in document");

		//choose  site management drive, and create an article document
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToAddNewContent();
		createNewArticle(DATA_ARTICLE_TITLE_34, DATA_ARTICLE_TITLE_34, DATA_ARTICLE_TITLE_34, DATA_ARTICLE_TITLE_34);
		waitForElementPresent(bDocument);

		//add a child node - podcast
		goToAddNewContent();
		createNewPodcast(DATA_PODCAST_TITLE_34, DATA_PODCAST_TITLE_34, DATA_PODCAST_TITLE_34);
		waitForElementPresent(By.xpath("//td[contains(text(),'"+ DATA_PODCAST_TITLE_34+"')]"));
		//check in  parent document
		checkInNode(bDocument);

		// Check if to edit  child
		waitForElementPresent(ELEMENT_EDIT_LINK);
		checkOutNode(bDocument);
		deleteDocument(bDocument);
	}

	//edit a document by a user with no edit permission
	@Test
	public void test35_EditDocumentInCheckedInDocument(){	
		String DATA_ARTICLE_TITLE_35="FNC_ECMS_FEX_ACTION_09_35";
		By bDocument=By.xpath("//a[contains(@title,'" + DATA_ARTICLE_TITLE_35 + " ')]");

		info("Edit document in checked-in document");
		//choose  site management drive, and create an article document
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToAddNewContent();
		createNewArticle(DATA_ARTICLE_TITLE_35, DATA_ARTICLE_TITLE_35, DATA_ARTICLE_TITLE_35, DATA_ARTICLE_TITLE_35);
		waitForElementPresent(bDocument);

		//set permission for node: james has not Edit permission
		setPermissionForDocument("james", true, false, true, true);
		logoutEcms();
		driver.get(baseUrl);
		loginEcms("james", PASS);

		// Check whether to edit  article
		goToSiteExplorer();
		goToNode(bDocument);
		waitForElementNotPresent(ELEMENT_EDIT_LINK);
		logoutEcms();
		driver.get(baseUrl);

		//delete data
		loginEcms(USER,PASS);
		goToSiteExplorer();
		deleteDocument(bDocument);
	}

	//Edit free layout content document
	@Test
	public void test36_EditFreeLayoutContent(){	
		String DATA_FREE_TITLE_36="FNC_ECMS_FEX_ACTION_09_36";
		String DATA_FREE_TITLE_36_EDIT="FNC_ECMS_FEX_ACTION_09_36 edit";

		By bDocument=By.xpath("//a[@title='"+DATA_FREE_TITLE_36+" ']");
		By bDocEdit=By.xpath("//a[@title='"+DATA_FREE_TITLE_36_EDIT+" ']");
		info("Edit free layout content document");
		//choose  site management drive, and create a free layout content	
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToAddNewContent();
		createNewFreeLayoutWebContent(DATA_FREE_TITLE_36, DATA_FREE_TITLE_36, DATA_FREE_TITLE_36, DATA_UPLOAD_IMG_1, DATA_FREE_TITLE_36, DATA_FREE_TITLE_36, DATA_FREE_TITLE_36);
		waitForElementPresent(bDocument);
		//edit and delete sample node
		editFreeLayoutWebContent(DATA_FREE_TITLE_36, DATA_FREE_TITLE_36_EDIT, DATA_FREE_TITLE_36_EDIT, DATA_UPLOAD_IMG_2, DATA_FREE_TITLE_36_EDIT, DATA_FREE_TITLE_36_EDIT, DATA_FREE_TITLE_36_EDIT);
		deleteDocument(bDocEdit);
	}

	//Edit a free layout content in-line
	@Test
	public void test37_EditFreeLayoutContentInline(){	
		String DATA_FREE_TITLE_37="FNC_ECMS_FEX_ACTION_09_37";
		String DATA_FREE_TITLE_37_EDIT="FNC_ECMS_FEX_ACTION_09_37 edit";
		By bDocument=By.xpath("//a[@title='"+DATA_FREE_TITLE_37+" ']");

		info("Edit content of sample node document inline");
		//choose  site management drive, and create a sample node document	
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToNodeByPath("acme/web contents");
		goToAddNewContent();
		createNewFreeLayoutWebContent(DATA_FREE_TITLE_37, DATA_FREE_TITLE_37, DATA_FREE_TITLE_37, DATA_UPLOAD_IMG_1, DATA_FREE_TITLE_37, DATA_FREE_TITLE_37, DATA_FREE_TITLE_37);
		waitForElementPresent(bDocument);
		//edit and delete sample node
		editFreeLayoutWebContentInline(ELEMENT_FREE_CONT_INLINE,ELEMENT_FREE_CONT_INPUT,DATA_FREE_TITLE_37_EDIT,ELEMENT_FREE_CONT_ACCEPT);
		deleteDocument(bDocument,80000);
	}

	//Edit a picture on head web layout 
	@Test
	public void test38_EditPictureOnHeadWebLayout(){	
		String DATA_PICTURE_TITLE_38="FNC_ECMS_FEX_ACTION_09_38";
		String DATA_PICTURE_TITLE_38_EDIT="FNC_ECMS_FEX_ACTION_09_38 edit";

		By bDocument=By.xpath("//a[@title='"+DATA_PICTURE_TITLE_38+" ']");
		By bDocEdit=By.xpath("//a[@title='"+DATA_PICTURE_TITLE_38_EDIT+" ']");
		info("Edit a picture on head web layout ");

		//choose site management drive, and create a picture on head web layout 	
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToNodeByPath("acme/web contents");
		goToAddNewContent();
		createNewPictureOnHeadLayout(DATA_PICTURE_TITLE_38, DATA_PICTURE_TITLE_38, DATA_UPLOAD_IMG_2);
		waitForElementPresent(bDocument);

		//edit and delete sample node
		editPictureOnHeadLayout(DATA_PICTURE_TITLE_38,DATA_PICTURE_TITLE_38_EDIT,DATA_UPLOAD_IMG_1,"50","50","fr",DATA_PICTURE_TITLE_38);
		deleteDocument(bDocEdit,50000);
	}
	//Edit a css file 
	@Test
	public void test39_EditPictureOnHeadWebLayout(){	
		String DATA_CSS_TITLE_39="FNC_ECMS_FEX_ACTION_09_39";
		By bDocument=By.xpath("//a[@title='"+DATA_CSS_TITLE_39+" ']");
		info("Edit a css file");

		//choose  site management drive, and create a css file 	
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToNodeByPath("acme/css");
		goToAddNewContent();
		createNewCssFile(DATA_CSS_TITLE_39, "1", "p {color:yellow;}");
		waitForElementPresent(bDocument);

		//edit and delete css file
		editCssFile(DATA_CSS_TITLE_39,"20","p {color: red;}");

		//verify expected result: text of paragraph is changed into red  
		mouseOver((ELEMENT_MYSITE_LINK), true);
		click(ELEMENT_ACME_SITE_LINK);
		clearCache();
		WebElement body = waitForAndGetElement(ELEMENT_BODY);
		assert body.getCssValue("color").contains("rgba(255,0,0,1)"):"Set up for page is not right";
		goToSiteExplorer();

		deleteDocument(bDocument,50000);
	}
	//Edit a js file 
	@Test
	public void test40_EditJsFile(){	
		String DATA_JS_TITLE_40="FNC_ECMS_FEX_ACTION_09_40";
		String DATA_JS_DATA="alert (\"Hello!\");";
		String DATA_JS_DATA_EDIT="alert (\"eXo!\");";
		By bDocument=By.xpath("//a[@title='"+DATA_JS_TITLE_40+" ']");
		info("Edit a js file");

		//choose site management drive, and create a js file 	
		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);
		goToNodeByPath("acme/js");
		addJSFile(DATA_JS_TITLE_40,"1", DATA_JS_DATA, "true");
		waitForElementPresent(bDocument);

		//edit js file
		editJsFile(DATA_JS_TITLE_40,"2",DATA_JS_DATA_EDIT,"true");

		//verify expected result: display alert: eXo!
		clearCache();
		pause(1000);
		waitForConfirmation("eXo!");

		//delete js file
		deleteDocument(bDocument,50000);
	}

	@BeforeMethod
	public void beforeMethod() {
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		loginEcms(USER, PASS);
		goToSiteExplorer();
	}
	@AfterMethod
	public void afterMethod() {
		//		logoutEcms();
		driver.quit();
	}
}
