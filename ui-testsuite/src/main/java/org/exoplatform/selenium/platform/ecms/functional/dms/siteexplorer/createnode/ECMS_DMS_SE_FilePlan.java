package org.exoplatform.selenium.platform.ecms.functional.dms.siteexplorer.createnode;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import static org.exoplatform.selenium.TestLogger.*;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.*;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;

public class ECMS_DMS_SE_FilePlan extends EcmsBase {
	public static final String USER = "john";
	public static final String PASS= "gtn";
	public static final By ELEMENT_DRIVE_COLLABORATION= By.xpath("//a[@title='collaboration']");
	public static final By ELEMENT_DRIVE= By.xpath("//a[@title='Show Drives']");
	public static final String ELEMENT_PREFER_DMS_STRUCTURE= "enableStructure";
	public static final String DATA_CON_NAME_01= "FNC_ECMS_FEX_CREATE_08_01_CON";
	public static final String DATA_CON_TITLE_01= "FNC_ECMS_FEX_CREATE_08_01_CON";
	public static final String DATA_FPLAN_NAME_01= "FNC_ECMS_FEX_CREATE_08_01_FPLAN";
	public static final String DATA_FPLAN_NAME_02= "FNC_ECMS_FEX_CREATE_08_02_FPLAN";
	public static final String DATA_DOC_NAME_02= "FNC_ECMS_FEX_CREATE_08_02_DOC";
	public static final String DATA_DOC_TITLE_02= "FNC_ECMS_FEX_CREATE_08_02_DOC";
	public static final String DATA_ART_NAME_03= "FNC_ECMS_FEX_CREATE_08_03_ART";
	public static final String DATA_ART_TITLE_03= "FNC_ECMS_FEX_CREATE_08_03_ART";
	public static final String DATA_FILE_NAME_04= "FNC_ECMS_FEX_CREATE_08_04_FILE";
	public static final String DATA_FILE_TITLE_04= "FNC_ECMS_FEX_CREATE_08_04_FILE";
	public static final String DATA_PODCAST_NAME_05= "FNC_ECMS_FEX_CREATE_08_05_POD";
	public static final String DATA_PODCAST_TITLE_05= "FNC_ECMS_FEX_CREATE_08_05_POD";
	public static final String DATA_SAM_NAME_06= "FNC_ECMS_FEX_CREATE_08_06_SAM";
	public static final String DATA_SAM_TITLE_06= "FNC_ECMS_FEX_CREATE_08_06_SAM";
	public static final String DATA_SAM_FILE_06="TestData/FNC_ECMS_FEX_CREATE_08_06.png";
	public static final String DATA_FPLAN_NAME_07= "FNC_ECMS_FEX_CREATE_08_07_FPLAN";
	public static final String DATA_KOFAX_NAME_08= "FNC_ECMS_FEX_CREATE_08_08_KOFAX";
	public static final String DATA_FPLAN_NAME_08= "FNC_ECMS_FEX_CREATE_08_08_FPLAN";
	public static final String DATA_FILE_NAME_09= "FNC_ECMS_FEX_CREATE_08_09_FILE";
	public static final String DATA_FILE_LINK_09= "TestData/FNC_ECMS_FEX_CREATE_08_09_FILE.png";
	public static final String DATA_CON_NAME_10= "FNC_ECMS_FEX_CREATE_08_10_CON";
	public static final String DATA_CON_TITLE_10= "FNC_ECMS_FEX_CREATE_08_10_CON";
	public static final String DATA_FPLAN_NAME_10= "FNC_ECMS_FEX_CREATE_08_10_CON";
	public static final String DATA_KOFAX_NAME_11= "FNC_ECMS_FEX_CREATE_08_11_KOFAX";
	public static final String DATA_FPLAN_NAME_11= "FNC_ECMS_FEX_CREATE_08_11_FPLAN";
	public static final String DATA_ART_NAME_12= "FNC_ECMS_FEX_CREATE_08_12_ART";
	public static final String DATA_ART_TITLE_12= "FNC_ECMS_FEX_CREATE_08_12_ART";
	public static final String DATA_SAM_NAME_12= "FNC_ECMS_FEX_CREATE_08_06_SAM";
	public static final String DATA_SAM_TITLE_12= "FNC_ECMS_FEX_CREATE_08_06_SAM";
	public static final String DATA_SAM_FILE_12="TestData/FNC_ECMS_FEX_CREATE_08_12.png";
	
	//add a file plan in a content folder
	@Test
	public void test01_AddFilePlanInContentFolder() {
		By bContentFolder = By.xpath("//a[@title='"+ DATA_CON_TITLE_01 + " ']");
		By bFilePlan = By.xpath("//a[@title='" + DATA_FPLAN_NAME_01+ " ']");
		info("Add a file plan in a content folder");

		// choose Collaboration drive and create a content folder
		chooseDrive(ELEMENT_DRIVE_COLLABORATION);
		createNewContentFolder(DATA_CON_TITLE_01, DATA_CON_NAME_01);
		waitForElementPresent(bContentFolder);

		//create a file plan
		goToNode(bContentFolder);
		goToAddNewContent();
		createNewFilePlan(DATA_FPLAN_NAME_01, "file plan", "file plan", "file plan", "file plan");
		waitForElementPresent(bFilePlan);

		pause(10000);
		//delete file plan
		deleteDocument(bFilePlan);

		//delete content folder
		deleteDocument(bContentFolder);
	}

	//add a file plan in a document folder
	@Test
	public void test02_AddFilePlanInDocumentFolder() {
		By bDocFolder = By.xpath("//a[@title='"+ DATA_DOC_TITLE_02 + " ']");
		By bFilePlan = By.xpath("//a[@title='" + DATA_FPLAN_NAME_02+ " ']");
		info("Add a file plan in a document folder");

		// choose Collaboration drive and create a document folder
		chooseDrive(ELEMENT_DRIVE_COLLABORATION);
		createNewDocumentFolder(DATA_DOC_TITLE_02, DATA_DOC_NAME_02);
		waitForElementPresent(bDocFolder);

		//create a file plan
		goToNode(bDocFolder);
		goToAddNewContent();
		createNewFilePlan(DATA_FPLAN_NAME_02, "file plan", "file plan", "file plan", "file plan");
		waitForElementPresent(bFilePlan);
		pause(10000);
		//delete file plan
		deleteDocument(bFilePlan);

		//delete content folder
		deleteDocument(bDocFolder);
	}

	//add a file plan in an article document
	@Test
	public void test03_AddFilePlanInArticle() {
		By bArticle = By.xpath("//a[@title='"+ DATA_ART_TITLE_03 + " ']");
		info("Add a file plan in an article document");

		// choose Collaboration drive and create an article
		chooseDrive(ELEMENT_DRIVE_COLLABORATION);
		goToAddNewContent();
		createNewArticle(DATA_ART_TITLE_03,DATA_ART_NAME_03, "summary article", "content of article");
		waitForElementPresent(bArticle);

		//check whether to create a file plan
		goToAddNewContent();
		waitForElementPresent(By.xpath("//div[text()='Select your template in the list below']"));
		assert isElementNotPresent(By.linkText("File Plan")): "Fail! Still add a file plan";
		goToNode(bArticle);

		//delete content folder
		deleteDocument(bArticle);
	}

	//add a file plan in a file document
	@Test
	public void test04_AddFilePlanInFileDocument() {
		By bFile = By.xpath("//a[@title='"+ DATA_FILE_TITLE_04 + " ']");
		info("Add a file plan in a file document");

		// choose Collaboration drive and create a file
		chooseDrive(ELEMENT_DRIVE_COLLABORATION);
		goToAddNewContent();
		createNewFile(DATA_FILE_NAME_04, "content of file", DATA_FILE_TITLE_04);
		waitForElementPresent(bFile);

		//check whether to create a new content
		assert isElementNotPresent(ELEMENT_MENU_NEW_CONTENT_LINK): "Fail! Still add new content";

		//delete content folder
		deleteDocument(bFile);
	}

	//add a file plan in a podcast document
	@Test
	public void test05_AddFilePlanInPodcastDocument() {
		By bPodcast = By.xpath("//a[@title='"+ DATA_PODCAST_TITLE_05 + " ']");
		info("Add a file plan in a podcast document");

		// choose Collaboration drive and create a file
		chooseDrive(ELEMENT_DRIVE_COLLABORATION);
		goToAddNewContent();
		createNewPodcast(DATA_PODCAST_NAME_05, DATA_PODCAST_TITLE_05, "link of podcast");
		waitForElementPresent(bPodcast);

		//check whether to create a new content
		assert isElementNotPresent(ELEMENT_MENU_NEW_CONTENT_LINK): "Fail! Still add new content";

		//delete content folder
		deleteDocument(bPodcast);
	}

	//add a file plan in a sample node document
	@Test
	public void test06_AddFilePlanInSampleNode() {
		By bSam = By.xpath("//a[@title='"+ DATA_SAM_TITLE_06 + " ']");
		info("Add a file plan in a sample node document");

		// choose Collaboration drive and create a sample node
		chooseDrive(ELEMENT_DRIVE_COLLABORATION);
		goToAddNewContent();
		createNewSampleNode( DATA_SAM_TITLE_06, DATA_SAM_NAME_06, DATA_SAM_FILE_06);
		waitForElementPresent(bSam);

		//check whether to create a new content
		goToAddNewContent();
		waitForElementPresent(By.xpath("//div[text()='Select your template in the list below']"));
		assert isElementNotPresent(By.linkText("File Plan")): "Fail! Still add a file plan";
		goToNode(bSam);
		
		//delete content folder
		deleteDocument(bSam);
	}
	//add a file plan in a file plan
	@Test
	public void test07_AddFilePlanInFilePlan() {
		By bFPlan = By.xpath("//a[@title='"+ DATA_FPLAN_NAME_07 + " ']");
		info("Add a file plan in a file plan");

		// choose Collaboration drive and create a file plan
		chooseDrive(ELEMENT_DRIVE_COLLABORATION);
		goToAddNewContent();
		createNewFilePlan(DATA_FPLAN_NAME_07,"file plan", "file plan", "file plan", "file plan");
		waitForElementPresent(bFPlan);

		//check whether to create a file plan
		goToAddNewContent();
		waitForElementPresent(By.xpath("//div[text()='Select your template in the list below']"));
		assert isElementNotPresent(By.linkText("File Plan")): "Fail! Still add a file plan";

		//delete content folder
		goToNode(bFPlan);
		pause(5000);
		deleteDocument(bFPlan);
	}

	//add a file plan in a kofax document 
	@Test
	public void test08_AddFilePlanInKofaxDocument() {
		By bKox = By.xpath("//a[@title='"+ DATA_KOFAX_NAME_08 + " ']");
		By bFilePlan = By.xpath("//a[@title='" + DATA_FPLAN_NAME_08+ " ']");
		info("Add a file plan in a kofax document");

		// choose Collaboration drive and create a kofax document
		chooseDrive(ELEMENT_DRIVE_COLLABORATION);
		goToAddNewContent();
		createNewKofax(DATA_KOFAX_NAME_08);
		waitForElementPresent(bKox);

		//create a file plan
		goToAddNewContent();
		createNewFilePlan(DATA_FPLAN_NAME_08, "file plan", "file plan", "file plan", "file plan");
		checkPreferenceOption(ELEMENT_PREFER_DMS_STRUCTURE);
		waitForElementPresent(bFilePlan);
		pause(10000);

		//delete file plan
		deleteDocument(bFilePlan);

		//delete content folder
		deleteDocument(bKox);
	}

	//add a file plan in an uploaded file
	@Test
	public void test09_AddFilePlanInUploadedFile() {
		By bUpload = By.xpath("//a[@title='"+ DATA_FILE_NAME_09 + ".png ']");
		info("Add a file plan in a uploaded file");

		// choose Collaboration drive and create an uploaded file
		chooseDrive(ELEMENT_DRIVE_COLLABORATION);
		uploadFile(DATA_FILE_NAME_09, DATA_FILE_LINK_09) ;
		waitForElementPresent(bUpload);
		goToNode(bUpload);

		//check whether to create a new content
		waitForElementNotPresent(ELEMENT_MENU_NEW_CONTENT_LINK);

		//delete content folder
		deleteDocument(bUpload);
	}
	//add a file plan in a locked content folder
	@Test
	public void test10_AddFilePlanInLockedContentFolder() {
		By bLockedContentFolder = By.xpath("//a[@title='"+ DATA_CON_TITLE_10 + " (Locked by "+ USER+ ")']");
		By bContentFolder = By.xpath("//a[@title='"+ DATA_CON_TITLE_10 + " ']");
		By bFilePlan = By.xpath("//a[@title='" + DATA_FPLAN_NAME_10+ " ']");
		info("Add a file plan in a locked content folder");

		// choose Collaboration drive and create a locked content folder
		chooseDrive(ELEMENT_DRIVE_COLLABORATION);
		createNewContentFolder(DATA_CON_TITLE_10, DATA_CON_NAME_10);
		waitForElementPresent(bContentFolder);
		lockNode(bContentFolder);

		//create a file plan
		goToNode(bLockedContentFolder);
		goToAddNewContent();
		createNewFilePlan(DATA_FPLAN_NAME_10, "file plan", "file plan", "file plan", "file plan");
		waitForElementPresent(bFilePlan);

		pause(10000);
		//delete file plan
		deleteDocument(bFilePlan);

		//delete content folder
		deleteDocument(bLockedContentFolder);
	}
	//add a file plan in a locked kofax document 
	@Test
	public void test11_AddFilePlanInLockedKofaxDocument() {
		By bKox = By.xpath("//a[@title='"+ DATA_KOFAX_NAME_11 + " ']");
		By bLockedKox = By.xpath("//a[@title='"+ DATA_KOFAX_NAME_11 + " (Locked by "+ USER+ ")']");
		By bFilePlan = By.xpath("//a[@title='" + DATA_FPLAN_NAME_11+ " ']");
		info("Add a file plan in a locked kofax document");

		// choose Collaboration drive and create a locked kofax document
		chooseDrive(ELEMENT_DRIVE_COLLABORATION);
		goToAddNewContent();
		createNewKofax(DATA_KOFAX_NAME_11);
		waitForElementPresent(bKox);
		lockNode(bKox);

		//create a file plan
		goToAddNewContent();
		createNewFilePlan(DATA_FPLAN_NAME_11, "file plan", "file plan", "file plan", "file plan");
		checkPreferenceOption(ELEMENT_PREFER_DMS_STRUCTURE);
		waitForElementPresent(bFilePlan);
		pause(10000);

		//delete file plan
		deleteDocument(bFilePlan);

		//delete content folder
		deleteDocument(bLockedKox);
	}
	//add a file plan in a locked article document
	@Test
	public void test12_AddFilePlanInLockedArticle() {
		By bArticle = By.xpath("//a[@title='"+ DATA_ART_TITLE_12 + " ']");
		By bLockedArticle = By.xpath("//a[@title='"+ DATA_ART_TITLE_12 + " (Locked by "+ USER+ ")']");
		info("Add a file plan in a locked article document");

		// choose Collaboration drive and create a locked article
		chooseDrive(ELEMENT_DRIVE_COLLABORATION);
		goToAddNewContent();
		createNewArticle(DATA_ART_TITLE_12,DATA_ART_NAME_12, "summary article", "content of article");
		waitForElementPresent(bArticle);
		lockNode(bArticle);

		//check whether to create a file plan
		goToAddNewContent();
		waitForElementPresent(By.xpath("//div[text()='Select your template in the list below']"));
		assert isElementNotPresent(By.linkText("File Plan")): "Fail! Still add a file plan";
		goToNode(bLockedArticle);

		//delete content folder
		deleteDocument(bLockedArticle);
	}

	//add a file plan in a locked sample node document
	@Test
	public void test12_AddFilePlanInLockedSampleNode() {
		By bSam = By.xpath("//a[@title='"+ DATA_SAM_TITLE_12 + " ']");
		By bLockedSam = By.xpath("//a[@title='"+ DATA_SAM_TITLE_12 + " (Locked by "+ USER+ ")']");
		info("Add a file plan in a sample node document");

		// choose Collaboration drive and create a sample node
		chooseDrive(ELEMENT_DRIVE_COLLABORATION);
		goToAddNewContent();
		createNewSampleNode( DATA_SAM_TITLE_12, DATA_SAM_NAME_12, DATA_SAM_FILE_12);
		waitForElementPresent(bSam);
		lockNode(bSam);
		
		//check whether to create a new content
		goToAddNewContent();
		waitForElementPresent(By.xpath("//div[text()='Select your template in the list below']"));
		assert isElementNotPresent(By.linkText("File Plan")): "Fail! Still add a file plan";
		goToNode(bLockedSam);
		//delete content folder
		deleteDocument(bLockedSam);
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
		logoutEcms();
		driver.close();
	}

}
