package org.exoplatform.selenium.platform.ecms.functional.dms.siteexplorer.createnode;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import static org.exoplatform.selenium.TestLogger.*;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.*;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.*;

public class ECMS_DMS_SE_Podcast extends EcmsBase {

	public String POD_TITLE_65 = "FNC_ECMS_FEX_CREATE_06_001_pod"; 
	public String POD_NAME_65 = "FNC_ECMS_FEX_CREATE_06_001_pod";
	public String POD_LINK = "podcast";
	public String POD_TITLE_66 = "FNC_ECMS_FEX_CREATE_06_002_pod";
	public String POD_NAME_66 = "FNC_ECMS_FEX_CREATE_06_002_pod";
	public String POD_TITLE_67 = "FNC_ECMS_FEX_CREATE_06_003_pod";
	public String POD_NAME_67 = "FNC_ECMS_FEX_CREATE_06_003_pod";
	public String POD_TITLE_68 = "FNC_ECMS_FEX_CREATE_06_004_pod";
	public String POD_NAME_68 = "FNC_ECMS_FEX_CREATE_06_004_pod";
	public String POD_TITLE_69 = "FNC_ECMS_FEX_CREATE_06_005_pod";
	public String POD_NAME_69 = "FNC_ECMS_FEX_CREATE_06_005_pod";
	public String POD_TITLE_70 = "FNC_ECMS_FEX_CREATE_06_006_pod";
	public String POD_NAME_70 = "FNC_ECMS_FEX_CREATE_06_006_pod";
	public String POD_TITLE_71 = "FNC_ECMS_FEX_CREATE_06_007_pod";
	public String POD_NAME_71 = "FNC_ECMS_FEX_CREATE_06_007_pod";
	public String POD_TITLE_72 = "FNC_ECMS_FEX_CREATE_06_008_pod";
	public String POD_NAME_72 = "FNC_ECMS_FEX_CREATE_06_008_pod";
	public String POD_TITLE_73 = "FNC_ECMS_FEX_CREATE_06_009_pod";
	public String POD_NAME_73 = "FNC_ECMS_FEX_CREATE_06_009_pod";
	public String POD_TITLE_74 = "FNC_ECMS_FEX_CREATE_06_010_pod";
	public String POD_NAME_74 = "FNC_ECMS_FEX_CREATE_06_010_pod";
	public String POD_TITLE_75 = "FNC_ECMS_FEX_CREATE_06_011_pod";
	public String POD_NAME_75 = "FNC_ECMS_FEX_CREATE_06_011_pod";
	public String ART_TITLE = "FNC_ECMS_FEX_CREATE_06_003_art";
	public String ART_NAME = "FNC_ECMS_FEX_CREATE_06_003_art";
	public String ART_SUM = "article";
	public String ART_CONT = "article";
	public String CONT_TITLE = "FNC_ECMS_FEX_CREATE_06_001_CONT";
	public String CONT_NAME = "FNC_ECMS_FEX_CREATE_06_001_CONT";
	public String CONT_TIT_LOCK = "FNC_ECMS_FEX_CREATE_06_011_CONT";
	public String CONT_NAME_lock = "FNC_ECMS_FEX_CREATE_06_011_CONT";
	public String DOC_TITLE = "FNC_ECMS_FEX_CREATE_06_002_doc";
	public String DOC_NAME = "FNC_ECMS_FEX_CREATE_06_002_doc";
	public String FILE_TITLE = "FNC_ECMS_FEX_CREATE_06_004_file";
	public String FILE_NAME = "FNC_ECMS_FEX_CREATE_06_004_file";
	public String FILE_CONT = "file document";
	public String SAM_TITLE = "FNC_ECMS_FEX_CREATE_06_006_sam";
	public String SAM_NAME = "FNC_ECMS_FEX_CREATE_06_006_sam";
	public String FPLAN_cat = "file plan";
	public String FPLAN_NAME = "FNC_ECMS_FEX_CREATE_06_007_fPlan";
	public String FPLAN_DIS = "file plan";
	public String FPLAN_ORI = "file plan";
	public String FPLAN_EVENT = "file plan";
	public String KOX_NAME = "FNC_ECMS_FEX_CREATE_06_007_kox";
	public String UPLOAD_NAME= "FNC_ECMS_FEX_CREATE_06_007_up";
	public int PAUSE1 = 8000;
	public int PAUSE2 = 2000;
	
	public static final String ELEMENT_DMS_STRUCT_ADVANCE="Advanced";
	
	public static final String ELEMENT_DRIVE_SITE_MANAGE= "//a[@title='Sites Management']";
	public static final String ELEMENT_DRIVE= "//a[@title='Show Drives']";
	public static final String ELEMENT_PREFERENCE= "//a[@title='Set up your browsing preferences']";
	public static final String ELEMENT_FRAME_UPLOAD="//iframe[contains(@id,'uploadFrame')]";
	public static final String ELEMENT_PODCAST_LINK_BLANK="//span[contains(text(),'The field \"Link\" is required')]";
	public static final String ELEMENT_ERROR_ICON="//span[@class='PopupIcon ErrorMessageIcon']";
	public static final String ELEMENT_NEW_CONTENT_LINK= "//a[@title='New Content']";
	
	public static final String DATA_UPLOAD_FILE= "TestData/FNC_ECMS_FEX_CREATE_06_007.png";
	public static final String ELEMENT_PODCAST_VALIDATE_OK= "OK";
	public static final String ELEMENT_PREFER_SAVE= "Save";
	public static final String ELEMENT_PREFER_DMS_STRUCTURE= "enableStructure";
	

	/** add a podcast document in a content folder
	 * create a content folder
	 * create a new podcast
	 * check expected result 
	 * detele data
	 */
	@Test
	public void test01_AddPodcastDocumentinContentFolder() {

		info("Add a podcast document in a content folder");
		//create a content folder
		createNewContentFolder(CONT_TITLE, CONT_NAME);
		waitForElementPresent(By.xpath("//a[@title='"+ CONT_TITLE + " ']"));
		pause(500);
		goToNode(By.xpath("//a[@title='"+ CONT_TITLE + " ']"));
		pause(2000);
		goToAddNewContent();
		
		createNewPodcast(POD_NAME_65, POD_TITLE_65, POD_LINK);
		//check expected result 
		waitForElementPresent(By.xpath("//a[@title='"+ POD_TITLE_65 + " ']"));		

		goToNode(By.xpath("//a[@title='"+ CONT_TITLE + " ']"));

		//delete podcast
		deleteDocument(By.xpath("//a[@title='"+ POD_TITLE_65 + " ']"));
		
		//delete content folder
		deleteDocument(By.xpath("//a[@title='"+ CONT_TITLE + " ']"));
		
	}
	/**add a podcast document in a document folder
	 * create a document folder
	 * create a new podcast
	 * check expected result
	 * delete data
	 */
	@Test
	public void test02_AddPodcastDocumentinDocumentFolder() {

		info("Add a podcast document in a document folder");
		
		//create a document folder
		createNewDocumentFolder(DOC_TITLE,DOC_NAME);
		//check whether document folder is created
		waitForElementPresent(By.linkText(DOC_TITLE));

		//create a new podcast
		goToNode(By.xpath("//a[@title='" + DOC_TITLE + " ']"));
		goToAddNewContent();
		createNewPodcast(POD_NAME_66, POD_TITLE_66, POD_LINK);
		
		//check whether podcast document is created
		waitForElementPresent(By.xpath("//a[@title='" + POD_TITLE_66 + " ']"));
	
		//delete podcast
		deleteDocument(By.xpath("//a[@title='" + POD_TITLE_66 + " ']"));
		
		//delete document folder
		deleteDocument(By.xpath("//a[@title='"+ DOC_TITLE + " ']"));
	} 

	/**add a podcast document in an article document
	 * choose a drive
	 * create an article 
	 * create a new podcast
	 * check expected result
	 * delete data
	 */
	@Test
	public void test03_AddPodcastDocumentinArticledocument() {

		info("Add a podcast document in an article document");
		//choose a drive
		waitForAndGetElement(By.xpath(ELEMENT_DRIVE)).click();
		waitForAndGetElement(By.xpath(ELEMENT_DRIVE_SITE_MANAGE)).click();

		//create an article
		goToAddNewContent();
		createNewArticle(ART_TITLE, ART_NAME, ART_SUM, ART_CONT);
		waitForElementPresent(By.xpath("//a[@title='" + ART_TITLE + " ']"));
		
		//create a new podcast
		goToAddNewContent();
		createNewPodcast(POD_NAME_67, POD_TITLE_67, POD_LINK);
	
		//enable DMS structure
		checkPreferenceOption(ELEMENT_PREFER_DMS_STRUCTURE);
		//check if to add a podcast
		waitForElementPresent(By.xpath("//a[@title='"+POD_TITLE_67 + " ']"));
		
			
		//delete podcast
		deleteDocument(By.xpath("//a[@title='"+POD_TITLE_67 + " ']"));
		
		//delete article document
		deleteDocument(By.xpath("//a[@title='" + ART_TITLE + " ']"));
	}

	/**add a podcast document in a file document
	 * choose a drive
	 * create a File document
	 * check expected result
	 * delete data
	 */
	@Test
	public void test04_AddPodcastDocumentinFileDocument() {

		info("Add a podcast document in a file document");
		//choose a drive
		waitForAndGetElement(By.xpath(ELEMENT_DRIVE)).click();
		waitForAndGetElement(By.xpath(ELEMENT_DRIVE_SITE_MANAGE)).click();
		
		//create a File document 
		goToAddNewContent();
		createNewFile(FILE_NAME,FILE_CONT, FILE_TITLE);
		waitForElementPresent(By.xpath("//a[@title='"+FILE_TITLE + " ']"));
		
		//check whether New content can be added
		waitForElementNotPresent(By.xpath(ELEMENT_NEW_CONTENT_LINK));
		
		//delete file document	
		deleteDocument(By.xpath("//a[@title='"+FILE_TITLE + " ']"));
	}

	/** add a podcast in a podcast
	 * choose a drive
	 * create a File document
	 * create a new podcast
	 * check expected result
	 * delete data
	 */
	@Test
	public void test05_AddPodcastDocumentinPodcastDocument() {

		info("Add a podcast in a podcast");
		//choose a drive
		waitForAndGetElement(By.xpath(ELEMENT_DRIVE)).click();
		waitForAndGetElement(By.xpath(ELEMENT_DRIVE_SITE_MANAGE)).click();

		//create a podcast 
		goToAddNewContent();
		pause(500);
		createNewPodcast(POD_NAME_69, POD_TITLE_69, POD_LINK);
		waitForElementPresent(By.xpath("//a[@title='"+ POD_TITLE_69 + " ']"));
		
		//check whether to add a new content in a File document
		waitForElementNotPresent(By.xpath(ELEMENT_NEW_CONTENT_LINK));
		
		//delete podcast document

		//assert isElementPresent(By.linkText(POD_TITLE_69)): "Fail to create a podcast!";
		deleteDocument(By.xpath("//a[@title='"+ POD_TITLE_69 + " ']"));
	}

	/**add a podcast document in a sample node document
	 * choose a drive
	 * create a sample node
	 * create a new podcast
	 * check expected result
	 * delete data
	 */
	@Test
	public void test06_AddPodcastDocumentinSampleNode() {

		info("Add a podcast document in a sample node document.");
		//choose a drive
		waitForAndGetElement(By.xpath(ELEMENT_DRIVE)).click();
		waitForAndGetElement(By.xpath(ELEMENT_DRIVE_SITE_MANAGE)).click();

		//create a sample node
		goToAddNewContent();
		createNewSampleNode(SAM_TITLE, SAM_NAME, DATA_UPLOAD_FILE);
		waitForElementPresent(By.xpath("//a[@title='" + SAM_TITLE+ " ']"));
		
		//create a new podcast
		goToAddNewContent();
		pause(500);
		createNewPodcast(POD_NAME_70, POD_TITLE_70, POD_LINK);
		
		//Enable DMS structure
		checkPreferenceOption(ELEMENT_PREFER_DMS_STRUCTURE);
		//check whether to add a podcast
		waitForElementPresent(By.xpath("//a[@title='" + POD_TITLE_70 + " ']"));
	
		goToNode(By.xpath("//a[@title='" + SAM_TITLE+ " ']"));

		//delete podcast
		deleteDocument(By.xpath("//a[@title='" + POD_TITLE_70 + " ']"));

		//delete sample node document
		deleteDocument(By.xpath("//a[@title='"+ SAM_TITLE + " ']"));
	}

	/**add a podcast document in a file plan document
	 *choose a drive
	 *create a file plan
	 *create a new podcast
	 *check expected result
	 *remove data
	 *
	 */
	@Test
	public void test07_AddPodcastDocumentinFilePlan() {

		info("Add a podcast document in a file plan!");
		//choose a drive
		waitForAndGetElement(By.xpath(ELEMENT_DRIVE)).click();
		waitForAndGetElement(By.xpath(ELEMENT_DRIVE_SITE_MANAGE)).click();
		
		//create a file plan
		goToAddNewContent();
		createNewFilePlan(FPLAN_NAME, FPLAN_cat, FPLAN_DIS, FPLAN_ORI, FPLAN_EVENT);
		waitForElementPresent(By.xpath("//a[@title='"+ FPLAN_NAME+ " ']"));
		
		//create a new podcast
		goToAddNewContent();
		createNewPodcast(POD_NAME_71, POD_TITLE_71, POD_LINK);
		
		//Enable DMS structure
		checkPreferenceOption(ELEMENT_PREFER_DMS_STRUCTURE);
		//check whether to add a podcast
		waitForElementPresent(By.xpath("//a[@title='" + POD_TITLE_71 + " ']"));		
		
		goToNode(By.xpath("//a[@title='"+ FPLAN_NAME+ " ']"));
		
		//delete podcast
		deleteDocument(By.xpath("//a[@title='" + POD_TITLE_71 + " ']"));
		assert checkError(): "Error";

		//delete file plan document
		deleteDocument(By.xpath("//a[@title='"+ FPLAN_NAME + " ']"));
		
	}

	/** create a podcast in a KoFax document
	 * choose a drive
	 * create a file plan
	 * create a new podcast
	 * check expected result
	 */

	@Test
	public void test08_AddPodcastDocumentinKofaxDocument() {

		info("Create a podcast in a KoFax document");
		//choose a drive
		waitForAndGetElement(By.xpath(ELEMENT_DRIVE)).click();
		waitForAndGetElement(By.xpath(ELEMENT_DRIVE_SITE_MANAGE)).click();

		//create a file plan
		goToAddNewContent();
		createNewKofax(KOX_NAME);
		waitForElementPresent(By.xpath("//a[@title='"+ KOX_NAME + " ']"));
				
		//create a new podcast
		goToAddNewContent();
		createNewPodcast(POD_NAME_72, POD_TITLE_72, POD_LINK);
	
		//Enable DMS structure
		checkPreferenceOption(ELEMENT_PREFER_DMS_STRUCTURE);
		//check whether to create a podcast
		waitForElementPresent(By.xpath("//a[@title='" + POD_TITLE_72 + " ']"));

	
		goToNode(By.xpath("//a[@title='" + KOX_NAME+ " ']"));
		
		//delete podcast
		deleteDocument(By.xpath("//a[@title='" + POD_TITLE_72 + " ']"));
		assert checkError(): "Error";

		//delete file plan document
		
		deleteDocument(By.xpath("//a[@title='" + KOX_NAME + " ']"));
	}

	/** create a podcast in an uploaded file
	 * choose a drive
	 * create a uploaded file
	 * check expected result
	 * remover data
	 */
	@Test(groups={"file"})
	public void test09_AddPodcastDocumentinUploadedFile() {

		info("Create a podcast in an uploaded file");
		//choose a drive
		
		waitForAndGetElement(By.xpath(ELEMENT_DRIVE)).click();
		waitForAndGetElement(By.xpath(ELEMENT_DRIVE_SITE_MANAGE)).click();
		pause(500);
		uploadFile(UPLOAD_NAME, DATA_UPLOAD_FILE);
		pause(500);
		
		// check whether the uploaded file can be created
		waitForElementPresent(By.linkText(UPLOAD_NAME + ".png"));
		//check whether to show New content link
		goToNode(By.xpath("//a[@title='"+UPLOAD_NAME + ".png ']"));
		waitForElementNotPresent(By.xpath(ELEMENT_NEW_CONTENT_LINK));
				
		//delete document
		pause(1000);
		deleteDocument(By.xpath("//a[@title='"+UPLOAD_NAME + ".png ']"));
	}
	public boolean checkError()
	{
		try {
			driver.findElement(By.xpath(ELEMENT_ERROR_ICON));
			return false;

		} catch (NoSuchElementException e) {
			return true;
		}
	}

	/**create a podcast with blank link
	 * choose a drive
	 * create a podcast with blank link
	 * check expected result
	 * delete data
	 */
	@Test
	public void test10_AddPodcastDocumentBlankLink() {

		info("Create a podcast with blank link");
		//choose a drive
		waitForAndGetElement(By.xpath(ELEMENT_DRIVE)).click();
		waitForAndGetElement(By.xpath(ELEMENT_DRIVE_SITE_MANAGE)).click();
		
		//create a podcast
		goToAddNewContent();
		createNewPodcast(POD_NAME_74, POD_TITLE_74, "");
		
		//check message popup
		waitForElementPresent(By.xpath(ELEMENT_PODCAST_LINK_BLANK));
		waitForAndGetElement(By.linkText(ELEMENT_PODCAST_VALIDATE_OK)).click();

	}
	/**create a podcast in a locked node
	 * choose a drive
	 * create a content folder
	 * lock the content folder
	 * create new podcast
	 * check expected result
	 * delete data
	 */
	@Test
	public void test11_AddPodcastInLockedContentFolder() {

		info("Create a podcast in a locked node");
		//choose a drive
		waitForAndGetElement(By.xpath(ELEMENT_DRIVE)).click();
		waitForAndGetElement(By.xpath(ELEMENT_DRIVE_SITE_MANAGE)).click();
		//create a content folder
		createNewContentFolder(CONT_TIT_LOCK, CONT_NAME_lock);
		
		//check if to add a content folder
		waitForElementPresent(By.xpath("//a[@title='" + CONT_TIT_LOCK + " ']"));
		// lock the content folder
		lockNode(By.xpath("//a[@title='" + CONT_TIT_LOCK + " ']"));
		//open the content folder
		goToNode(By.xpath("//a[@title='" + CONT_TIT_LOCK + " (Locked by john)']"));
		goToAddNewContent();
		
		// create new podcast
		createNewPodcast(POD_NAME_75, POD_TITLE_75, POD_LINK);
		goToNode(By.xpath("//a[@title='" + CONT_TIT_LOCK + " (Locked by john)']"));

		//check if to add a podcast in a content folder
		waitForElementPresent(By.xpath("//a[@title='" + POD_TITLE_75 +" ']"));
		
		//delete podcast
		deleteDocument(By.xpath("//a[@title='" + POD_TITLE_75 +" ']"));

		//check to delete successfully
		waitForElementNotPresent("//a[@title='" + POD_TITLE_75 +" ']");
		//delete content folder
		deleteDocument(By.xpath("//a[@title='" + CONT_TIT_LOCK + " (Locked by john)']"));
		waitForElementNotPresent("//a[@title='" + CONT_TIT_LOCK + " (Locked by john)']");
		//assert isElementNotPresent("//a[@title='" + CONT_TIT_LOCK + " (Locked by john)']"): "Fail to remove a content folder!";
		
	}
	
	@BeforeMethod(groups={"file"})
	public void beforeMethod() {
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		loginEcms("john", "gtn");
		goToSiteExplorer();

	}

	@AfterMethod(groups={"file"})
	public void afterMethod() {
		logoutEcms();
		driver.close();
	}
	
}