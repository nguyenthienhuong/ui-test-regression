package org.exoplatform.selenium.platform.ecms.functional.dms.siteexplorer.publication;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.*;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*; 
import static org.exoplatform.selenium.platform.ecms.ContextMenu.*;
import org.exoplatform.selenium.platform.ecms.ActionBar;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author VuNA
 * Date: 09/10/2012
 */

public class ECMS_DMS_SE_PublicationStatus extends ActionBar{
	public String DATA_USER = "john";
	public String DATA_PASS = "gtn";

	public By DRIVER_SITES_MANAGEMENT = By.xpath("//a[@class='DriveLabel' and @title = 'Sites Management']");
	public By ELEMENT_PUBLICATION = By.xpath("//div[@id='DMSMenuItemContainer']//a[@title='Publications']");
	public By ELEMENT_HISTORY_TAB = By.xpath("//div[@class='MiddleTab' and text() = 'History']");
	public By ELEMENT_DOCUMENT_DOC = By.linkText("conditions.doc");
	public By ELEMENT_PENDING_ARTICLE = By.xpath(".//*[@id='UIPublicationPanel']/fieldset[1]/div/div[2]/a[2]");
	public By ELEMENT_APPROVED_ARTICLE = By.xpath(".//*[@id='UIPublicationPanel']/fieldset[1]/div/div[2]/a[3]");
	public By ELEMENT_STAGED_ARTICLE = By.xpath(".//*[@id='UIPublicationPanel']/fieldset[1]/div/div[2]/a[4]");
	public By ELEMENT_PUBLISHED_ARTICLE = By.xpath(".//*[@id='UIPublicationPanel']/fieldset[1]/div/div[2]/a[5]");
	public By ELEMENT_CLOSE_TAB = By.xpath(".//*[@id='UIPublicationHistory']/div/a[text()='Close']");
	public By ELEMENT_PUBLISHED_STATUS = By.xpath("//td/div[text()='Revision:1']/following::td[contains(text(),'Published')]");
	public String ELEMENT_STAGED_STATUS = "//td/div[@title='${titleOfFile}']/following::td[contains(text(),'Staged[current revision]')]";
	public By ELEMENT_DATE_INPUT_FROM = By.id("UIPublicationPanelStartDateInput");
	public By ELEMENT_DATE_INPUT_TO = By.id("UIPublicationPanelEndDateInput");
	public String MESSAGE_INVALID_DATE_TIME = "The datetime format is invalid. Please check again.";
	public String ELEMENT_ACME_SITES_LINK = DEFAULT_BASEURL.concat("/portal/acme");

	@BeforeMethod
	public void beforeMethods() throws Exception {
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		info("Login ECMS with " + DATA_USER);
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

	/*-- Case ID 001
	 *-- Check showing Manage publication form 
	 * --*/
	@Test
	public void test01_CheckShowingManagePublicationForm(){
		info("-- Step 1: Show site explorer by legal user --");

		goToSiteExplorer();

		chooseDrive(DRIVER_SITES_MANAGEMENT);

		goToNodeByPath("acme/documents");

		click(ELEMENT_DOCUMENT_DOC);

		click(ELEMENT_PUBLICATION);

		info("-- Step 2: Show Manage Publication form --");

		waitForTextPresent("Revision: conditions.doc");

		click(ELEMENT_HISTORY_TAB);

		waitForTextPresent("Date");
		click(ELEMENT_CLOSE_TAB);

		info("-- Sign Out --");
	}

	/*-- Case ID 002 
	 *-- Add Pending status from Draft status
	 * --*/
	@Test
	public void test02_AddPendingStatusFromDraftStatus(){
		String titleArticle = "test02";
		String nameArticle = "test02";
		String summaryArticle = "summary of article";
		String contentArticle = "content of article";

		info("-- Step 1: Show site explorer by legal user --");

		goToSiteExplorer();

		chooseDrive(DRIVER_SITES_MANAGEMENT);

		goToNodeByPath("acme");

		info("-- Step 2: Create new node with draft status --");

		goToAddNewContent();

		createNewArticle(titleArticle, nameArticle, summaryArticle, contentArticle);

		waitForTextPresent(titleArticle);

		info("-- Step 3: Show Manage Publication form of a draft node --");

		click(ELEMENT_PUBLICATION);

		waitForTextPresent("Draft[current revision]");

		info("-- Step 4: Change status --");

		click(ELEMENT_PENDING_ARTICLE);

		waitForTextPresent("Pending[current revision]");

		save();

		info("-- Restore original data --");

		deleteDocument(By.linkText(titleArticle));

		info("-- Sign Out --");	
	}

	/*-- Case ID 003 
	 *-- Add Approve status from Draft status 
	 * --*/
	@Test
	public void test03_AddApproveStatusFromDraftStatus(){
		String titleArticle = "test03";
		String nameArticle = "test03";
		String summaryArticle = "summary of article";
		String contentArticle = "content of article";

		info("-- Step 1: Show site explorer by legal user --");

		goToSiteExplorer();

		chooseDrive(DRIVER_SITES_MANAGEMENT);

		goToNodeByPath("acme");

		info("-- Step 2: Create new node with draft status --");

		goToAddNewContent();

		createNewArticle(titleArticle, nameArticle, summaryArticle, contentArticle);

		waitForTextPresent(titleArticle);

		info("-- Step 3: Show Manage Publication form of a draft node --");

		click(ELEMENT_PUBLICATION);

		waitForTextPresent("Draft[current revision]");

		info("-- Step 4: Change status --");

		click(ELEMENT_APPROVED_ARTICLE);

		waitForTextPresent("Approved[current revision]");

		save();

		info("-- Restore original data --");

		deleteDocument(By.linkText(titleArticle));

		info("-- Sign Out --");		
	}

	/*-- Case ID 004
	 *-- Add Stage status from Draft status 
	 *--*/
	@Test
	public void test04_AddStageStatusFromDraftStatus(){
		String titleArticle = "test04";
		String nameArticle = "test04";
		String summaryArticle = "summary of article";
		String contentArticle = "content of article";

		info("-- Step 1: Show site explorer by legal user --");

		goToSiteExplorer();

		chooseDrive(DRIVER_SITES_MANAGEMENT);

		goToNodeByPath("acme");

		info("-- Step 2: Create new node with draft status --");

		goToAddNewContent();

		createNewArticle(titleArticle, nameArticle, summaryArticle, contentArticle);

		waitForTextPresent(titleArticle);

		info("-- Step 3: Show Manage Publication form of a draft node --");

		click(ELEMENT_PUBLICATION);

		waitForTextPresent("Draft[current revision]");

		info("-- Step 4: Change status --");

		click(ELEMENT_STAGED_ARTICLE);

		waitForTextPresent("Staged[current revision]");

		save();

		info("-- Restore original data --");

		deleteDocument(By.linkText(titleArticle));

		info("-- Sign Out --");		
	}

	/*-- Case ID 005 
	 *-- Add Published status from Draft status
	 *--*/
	@Test
	public void test05_AddPublishedStatusFromDraftStatus(){
		String titleOfDocument = "test05_AddPublishedStatus";
		String nameOfDocument = "test05_AddPublishedStatus";
		String pathFile = "TestData/Winter.jpg";
		String fileName = "ECMS_PublicationStatus_Case05_AddPublishedStatus";

		info("-- Step 1: Show site explorer by legal user --");

		goToSiteExplorer();

		chooseDrive(DRIVER_SITES_MANAGEMENT);

		goToNodeByPath("acme/documents");

		info("-- Step 2: Create new node with draft status --");

		goToAddNewContent();

		createNewPictureOnHeadLayout(nameOfDocument, titleOfDocument, pathFile);

		waitForTextPresent(titleOfDocument);

		info("-- Step 3: Show Manage Publication form of a draft node --");

		click(ELEMENT_PUBLICATION);

		waitForTextPresent("Draft[current revision]");

		info("-- Step 4: Change status --");

		click(ELEMENT_PUBLISHED_ARTICLE);

		waitForTextPresent("Published");

		save();

		waitForTextPresent(titleOfDocument);

		//Verify that Content is published
		verifyFileIsDisplayed(titleOfDocument, fileName);

		info("-- Restore original data --");

		goToSiteExplorer();

		deleteDocument(By.linkText(titleOfDocument));

		info("-- Sign Out --");		
	}

	/*-- Case ID 006 
	 *-- Publish content without setting publication time 
	 * --*/
	@Test
	public void test06_PublishContentWithoutSettingPublicationTime(){
		String nameOfFile = "test06_PublishContentWithoutSettingPublicationTime"; 
		String titleOfFile = "test06_PublishContentWithoutSettingPublicationTime";
		String contentOfFile = "Publish content without setting publication time";
		String pathFile = "TestData/Winter.jpg";
		String summaryOfFile = "Publish content without setting publication time";
		String fileName = "ECMS_PublicationStatus_Case06_WithoutSettingPublicationTime";

		info("-- Step 1: Show site explorer by legal user --");

		goToSiteExplorer();

		chooseDrive(DRIVER_SITES_MANAGEMENT);

		goToNodeByPath("acme/web contents/News");

		info("-- Step 2: Create new node with draft status --");

		goToAddNewContent();

		createNewFreeLayoutWebContent(titleOfFile, nameOfFile, contentOfFile, pathFile, summaryOfFile,"","");

		waitForTextPresent(titleOfFile);

		info("-- Step 3: Show Manage Publication form of a draft node --");

		click(ELEMENT_PUBLICATION);

		waitForTextPresent("Draft[current revision]");

		info("-- Step 4: Change status to published --");

		click(ELEMENT_PUBLISHED_ARTICLE);

		waitForElementPresent(ELEMENT_PUBLISHED_STATUS);

		save();

		waitForTextPresent(titleOfFile);

		//Verify that Content is published
		verifyFileIsDisplayed(titleOfFile, fileName);

		info("-- Restore original data --");

		goToSiteExplorer();

		deleteDocument(By.linkText(titleOfFile));
		
		info("-- Sign Out --");
	}

	/*-- Case ID 07
	 *-- Publish content with setting publication time in both From and To field
	 *--*/
	@Test
	public void test07_PublishContentWithSettingPublicationTimeInBothFromAndToField(){
		String nameOfFile = "test07_PublishContentWithSettingPublicationTime"; 
		String titleOfFile = "test07_PublishContentWithSettingPublicationTime";
		String contentOfFile = "Publish content with setting publication time in both From and To field";
		String pathFile = "TestData/Winter.jpg";
		String summaryOfFile = "Publish content with setting publication time in both From and To field";
		String fileNameFromTime = "ECMS_PublicationStatus_Case07_SettingPublicationFromTime";
		String fileNameEndTime = "ECMS_PublicationStatus_Case07_SettingPublicationEndTime";
		String stagedStatus = ELEMENT_STAGED_STATUS.replace("${titleOfFile}", titleOfFile);
		
		info("-- Step 1: Show site explorer by legal user --");

		goToSiteExplorer();

		chooseDrive(DRIVER_SITES_MANAGEMENT);

		goToNodeByPath("acme/web contents/News");

		info("-- Step 2: Create new node with draft status --");

		goToAddNewContent();

		createNewFreeLayoutWebContent(titleOfFile, nameOfFile, contentOfFile, pathFile, summaryOfFile,"","");

		waitForTextPresent(titleOfFile);

		info("-- Step 3: Show Manage Publication form of a draft node --");

		click(ELEMENT_PUBLICATION);

		waitForTextPresent("Draft[current revision]");

		info("-- Step 4: Change status to staged --");

		click(ELEMENT_STAGED_ARTICLE);

		waitForElementPresent(stagedStatus);

		info("-- Step 5: Setting time to publish content --");

		String currentDateTime = getCurrentDateTime();

		String oneMinuteAfterCurrentTime = addMinuteToCurrentDateTime();

		type(ELEMENT_DATE_INPUT_FROM, currentDateTime, true);

		type(ELEMENT_DATE_INPUT_TO, oneMinuteAfterCurrentTime, true);

		save();

		waitForElementNotPresent(stagedStatus);

		goToNodeByPath("News");
		
		//waiting system to go into effect
		pause(30000);
		
		verifyFileIsDisplayed(titleOfFile, fileNameFromTime);

		info("-- Verify that Content is unpublished after 1 minute --");
		pause(80000);

		driver.get(ELEMENT_ACME_SITES_LINK);
		waitForTextNotPresent(titleOfFile);
		captureScreen(fileNameEndTime);

		info("-- Restore original data --");

		goToSiteExplorer();

		deleteDocument(By.linkText(titleOfFile));	
		
		info("-- Sign Out --");
	}

	/*-- Case ID 008
	 *-- Publish content when only set time in From field
	 *--*/
	@Test
	public void test08_PublishContentWhenOnlySetTimeInFromField(){
		String nameOfFile = "test08_PublishContent_SetTimeInFromField"; 
		String titleOfFile = "test08_PublishContent_SetTimeInFromField";
		String contentOfFile = "Publish content when only set time in From field";
		String pathFile = "TestData/Winter.jpg";
		String summaryOfFile = "Publish content when only set time in From field";
		String fileNameFromTime = "ECMS_PublicationStatus_Case08_SetTimeInFromField";

		String stagedStatus = ELEMENT_STAGED_STATUS.replace("${titleOfFile}", titleOfFile);

		info("-- Step 1: Show site explorer by legal user --");

		goToSiteExplorer();

		chooseDrive(DRIVER_SITES_MANAGEMENT);

		goToNodeByPath("acme/web contents/News");

		info("-- Step 2: Create new node with draft status --");

		goToAddNewContent();

		createNewFreeLayoutWebContent(titleOfFile, nameOfFile, contentOfFile, pathFile, summaryOfFile,"","");

		waitForTextPresent(titleOfFile);

		info("-- Step 3: Show Manage Publication form of a draft node --");

		click(ELEMENT_PUBLICATION);

		waitForTextPresent("Draft[current revision]");

		info("-- Step 4: Change status to staged --");

		click(ELEMENT_STAGED_ARTICLE);

		waitForElementPresent(stagedStatus);

		info("-- Step 5: Setting time on From field --");

		String oneMinuteAfterCurrentTime = addMinuteToCurrentDateTime();

		type(ELEMENT_DATE_INPUT_FROM, oneMinuteAfterCurrentTime, true);

		save();

		waitForElementNotPresent(stagedStatus);

		goToNodeByPath("News");

		info("-- Verify that Content is published after 1 minute --");
		//waiting system to go into effect
		pause(120000);
		
		verifyFileIsDisplayed(titleOfFile, fileNameFromTime);

		info("-- Restore original data --");

		goToSiteExplorer();

		deleteDocument(By.linkText(titleOfFile));
		
		info("-- Sign Out --");
	}

	/*-- Case ID 009
	 *-- Publish content when only set time in To field
	 *-- */
	@Test
	public void test09_PublishContentWhenOnlySetTimeInToField(){
		String nameOfFile = "test09_PublishContent_SetTimeInToField"; 
		String titleOfFile = "test09_PublishContent_SetTimeInToField";
		String contentOfFile = "Publish content when only set time in To field";
		String pathFile = "TestData/Winter.jpg";
		String summaryOfFile = "Publish content when only set time in To field";
		String fileNameToTime = "ECMS_PublicationStatus_Case09_SetTimeInToField";
		String fileNameEndTime = "ECMS_PublicationStatus_Case09_EndTime";
		
		String stagedStatus = ELEMENT_STAGED_STATUS.replace("${titleOfFile}", titleOfFile);

		info("-- Step 1: Show site explorer by legal user --");

		goToSiteExplorer();

		chooseDrive(DRIVER_SITES_MANAGEMENT);

		goToNodeByPath("acme/web contents/News");

		info("-- Step 2: Create new node with draft status --");

		goToAddNewContent();

		createNewFreeLayoutWebContent(titleOfFile, nameOfFile, contentOfFile, pathFile, summaryOfFile,"","");

		waitForTextPresent(titleOfFile);

		info("-- Step 3: Show Manage Publication form of a draft node --");

		click(ELEMENT_PUBLICATION);

		waitForTextPresent("Draft[current revision]");

		info("-- Step 4: Change status to staged --");

		click(ELEMENT_STAGED_ARTICLE);

		waitForElementPresent(stagedStatus);

		info("-- Step 5: Setting time to End field --");

		String oneMinuteAfterCurrentTime = addMinuteToCurrentDateTime();

		type(ELEMENT_DATE_INPUT_TO, oneMinuteAfterCurrentTime, true);

		save();

		waitForElementNotPresent(stagedStatus);
		
		goToNodeByPath("News");
		
		//waiting system to go into effect
		pause(120000);
		
		verifyFileIsDisplayed(titleOfFile, fileNameToTime);
		
		info("-- Verify that Content is unpublished after End time--");
		pause(180000);

		driver.get(ELEMENT_ACME_SITES_LINK);
		waitForTextNotPresent(titleOfFile);
		captureScreen(fileNameEndTime);
		
		info("-- Restore original data --");

		goToSiteExplorer();

		deleteDocument(By.linkText(titleOfFile));
		
		info("-- Sign Out --");
	}

	/*-- Case 010
	 *-- Publish content when put invalid 
	 * --*/
	@Test
	public void test10_PublishContentWhenPutInvalid (){
		String nameOfFile = "test10_PublishContent_InputInvalidTime"; 
		String titleOfFile = "test10_PublishContent_InputInvalidTime";
		String contentOfFile = "Publish content when put invalid";
		String pathFile = "TestData/Winter.jpg";
		String summaryOfFile = "Publish content when put invalid";

		info("-- Step 1: Show site explorer by legal user --");

		goToSiteExplorer();

		chooseDrive(DRIVER_SITES_MANAGEMENT);

		goToNodeByPath("acme/web contents/News");

		info("-- Step 2: Create new node with draft status --");

		goToAddNewContent();

		createNewFreeLayoutWebContent(titleOfFile, nameOfFile, contentOfFile, pathFile, summaryOfFile,"","");

		waitForTextPresent(titleOfFile);

		info("-- Step 3: Show Manage Publication form of a draft node --");

		click(ELEMENT_PUBLICATION);

		waitForTextPresent("Draft[current revision]");

		info("-- Step 4: Change status to published --");

		click(ELEMENT_PUBLISHED_ARTICLE);

		waitForElementPresent(ELEMENT_PUBLISHED_STATUS);

		info("-- Step 5: Setting time with invalid data --");

		type(ELEMENT_DATE_INPUT_FROM, "10/10/2012", true);

		save();
		
		waitForMessage(MESSAGE_INVALID_DATE_TIME);
		
		closeMessageDialog();
		
		close();
		
		info("-- Restore original data --");

		deleteDocument(By.linkText(titleOfFile));
		
		info("-- Sign Out --");
	}
	
	/*---- Auxiliary function ----*/
	public void verifyFileIsDisplayed(String titleOfFile, String fileName){
		pause(1000);
		mouseOver(By.linkText("My Sites"), true);
		mouseOverAndClick(By.linkText("acme"));
		waitForTextPresent(titleOfFile);
		captureScreen(fileName);
	}

	//Get current date time 
	public String getCurrentDateTime(){
		//MM/dd/yyyy HH:mm:ss
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		return (dateFormat.format(date));
	}

	//Add 1 minute to current date time
	public String addMinuteToCurrentDateTime(){
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, 3);
		return (dateFormat.format(cal.getTime()));			
	}
}
