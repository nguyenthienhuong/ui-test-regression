package org.exoplatform.selenium.platform.ecms.functional.admin.advancedconfiguration;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.*;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.*;
import static org.exoplatform.selenium.platform.ManageAccount.*;
import static org.exoplatform.selenium.platform.UserGroupManagement.*;
import org.exoplatform.selenium.platform.ecms.ActionBar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ECMS_Admin_AdvancedConfiguration_ManageLock extends ActionBar{
	public String DATA_USER = "john";
	public String DATA_PASS = "gtn";
	
	//Data for these test cases 
	public String ELEMENT_ARTICLE_LOCKED_BY_ADMIN = "//a[@title = '${titleOfFile} (Locked by john)']";
	public String ELEMENT_ARTICLE_UNLOCK = "//a[@title='${titleOfFile} " + "']";
	public By ELEMENT_DOCUMENT = By.linkText("documents");
	public By ELEMENT_ACME_FOLDER = By.linkText("acme");
	public By ELEMENT_UNLOCK_ARTICLE = By.className("Unlock16x16Icon");
	public By ELEMENT_GROUP_MEMBERSHIP = By.linkText("manager");
	public By ELEMENT_DELETE_SELECTED_GROUP = By.xpath(".//*[@id='UILockHolderList']/table/tbody/tr[2]/td[2]/div/img");
	public By ELEMENT_DELETE_ADMIN_GROUP = By.xpath(".//*[@id='UILockHolderList']/table/tbody/tr/td[2]/div/img");

	public String ELEMENT_GROUP_MEMBERSHIP_DISPLAY = "manager";
	public String ELEMENT_GROUP_TO_SELECT = "Platform/Content Management";
	public String ELEMENT_GROUP_DISPLAY = "platform/web-contributors";
	public String MESSAGE_UNLOCK_WITHOUT_PERMISSION = "You do not have permission to unlock this node. Please contact the administrator to get the correct right.";
	public String MESSAGE_CANNOT_DELETE_GROUP = "Cannot delete the group *:/platform/administrators.";

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
		driver.quit();
		actions = null;
	}

	/*-- Case ID 001
	 *-- Unlock node when administrator has permission to view node
	 * --*/
	@Test
	public void test01_UnlockNodeWhenAdminHasPermissionToViewNode(){
		String titleArticle = "test01";
		String nameArticle = "test01";
		String summaryArticle = "summary of article";
		String contentArticle = "content of article";
		By elementArticleToLock = By.xpath(ELEMENT_ARTICLE_UNLOCK.replace("${titleOfFile}", titleArticle));

		info("-- Step 1: Lock node --");

		lockNodeInSE(titleArticle, nameArticle, summaryArticle, contentArticle);

		info("-- Step 2: Open form to unlock node --");

		goToAdminManageLockAndVerify(titleArticle);

		info("-- Step 3: Unlock node by administrator --");
		//Select “Locked Node” tab

		click(ELEMENT_UNLOCK_ARTICLE);

		waitForTextNotPresent(titleArticle);

		info("-- Restore original data --");

		goToSiteExplorer();

		deleteDocument(elementArticleToLock);

		info("-- SignOut--");

		signOut();
	}

	/*-- Case ID 002
	 *-- Unlock node when administrator does not have permission to view node
	 *--*/
	@Test
	public void test02_UnlockNodeWhenAdminDoesNotHavePermissionToViewNode(){
		String titleArticle = "test02";
		String nameArticle = "test02";
		String summaryArticle = "summary of article";
		String contentArticle = "content of article";
		By elementArticleLockedByAdmin = By.xpath(ELEMENT_ARTICLE_LOCKED_BY_ADMIN.replace("${titleOfFile}", titleArticle));

		info("-- Step 1: Lock node --");

		lockNodeInSE(titleArticle, nameArticle, summaryArticle, contentArticle);

		driver.close();

		info("-- Login with admin who does not have permission to unlock node --");

		WebDriver driverTest = initNewDriverSeleniumTest();

		actions = new Actions(driverTest);

		loginEcms("mary", "gtn");

		info("-- Step 2: Open form to unlock node --");

		goToAdminManageLockAndVerify(titleArticle);

		info("-- Step 3: Unlock node by administration with admin who does not have permission --");
		//Select “Locked Node” tab

		click(ELEMENT_UNLOCK_ARTICLE);

		waitForMessage(MESSAGE_UNLOCK_WITHOUT_PERMISSION);

		closeMessageDialog();

		info("-- Restore original data --");

		signOut();

		driverTest.get(baseUrl);

		signIn(DATA_USER, DATA_PASS);

		goToSiteExplorer();

		goToNodeByPath("acme/documents");

		deleteDocument(elementArticleLockedByAdmin);

		info("-- SignOut --");

		signOut();
	}

	/*-- Case ID 003 
	 *-- Show list locked node when node is renamed 
	 * --*/
	@Test
	public void test03_ShowListLockedNodeWhenNodeIsRenamed(){
		String titleArticle = "test03";
		String nameArticle = "test03";
		String summaryArticle = "summary of article";
		String contentArticle = "content of article";
		By elementArticleToLock = By.linkText(titleArticle);

		info("-- Step 1: Lock node --");

		lockNodeInSE(titleArticle, nameArticle, summaryArticle, contentArticle);

		info("--  Step 2: Open form to unlock node --");

		goToAdminManageLockAndVerify(titleArticle);

		info("--  Step 3: Rename node --");

		goToSiteExplorer();

		//Rename locked node
		rightClickOnElement(elementArticleToLock);

		click(ELEMENT_MENU_RENAME_NODE);

		waitForTextPresent("Rename");

		type(ELEMENT_INPUT_TITLE_NODE, "newsTest03", true);

		type(ELEMENT_INPUT_NAME_NODE, "newsTest03", true);

		save();

		waitForTextPresent("newsTest03");

		info("--  Step 4: Check list locked node after renaming --");

		goToAdminManageLockAndVerify("newsTest03");

		waitForTextNotPresent(titleArticle);

		info("-- Restore original data --");

		goToSiteExplorer();

		deleteDocument(By.xpath(ELEMENT_ARTICLE_LOCKED_BY_ADMIN.replace("${titleOfFile}", "newsTest03")));

		info("-- SignOut --");

		signOut();
	}

	/*-- Case ID 004
	 *-- Show list locked node when node is copied to other path
	 *--*/
	@Test
	public void test04_ShowListLockedNodeWhenNodeIsCopiedToOtherPath(){
		String titleArticle = "test04";
		String nameArticle = "test04";
		String summaryArticle = "summary of article";
		String contentArticle = "content of article";
		String elementLockedArticleIcon = ELEMENT_ARTICLE_LOCKED_BY_ADMIN.replace("${titleOfFile}", titleArticle);
		By elementCopiedArticleUnLock = By.xpath(ELEMENT_ARTICLE_UNLOCK.replace("${titleOfFile}", titleArticle));	
		By elementArticleToLock = By.xpath(elementLockedArticleIcon);

		info("-- Step 1: Lock node --");

		lockNodeInSE(titleArticle, nameArticle, summaryArticle, contentArticle);

		info("-- Step 2: Open form to unlock node --");

		goToAdminManageLockAndVerify(titleArticle);

		info("-- Step 3: Copy and paste node other path --");

		goToSiteExplorer();

		//Copy locked node to acme
		rightClickOnElement(elementLockedArticleIcon);

		click(ELEMENT_COPY_NODE);

		goToNodeByPath("acme");

		rightClickOnElement(ELEMENT_ACME_FOLDER);

		click(By.linkText("Paste"));

		waitForTextPresent(titleArticle);

		info("-- Step 4: Check list locked node after coyping --");

		goToAdminManageLockAndVerify(titleArticle);

		waitForTextNotPresent("/sites content/live/acme/test04");

		info("-- Restore original data --");

		goToSiteExplorer();

		deleteDocument(elementCopiedArticleUnLock);

		goToNodeByPath("documents");

		deleteDocument(elementArticleToLock);

		info("-- SignOut--");

		signOut();
	}

	/*-- Case ID 005 
	 *-- Show list locked node when node is deleted
	 * --*/
	@Test
	public void test05_ShowListLockedNodeWhenNodeIsDeleted(){
		String titleArticle = "test05";
		String nameArticle = "test05";
		String summaryArticle = "summary of article";
		String contentArticle = "content of article";
		By elementArticleToLock = By.xpath(ELEMENT_ARTICLE_LOCKED_BY_ADMIN.replace("${titleOfFile}", titleArticle));

		info("-- Step 1: Lock node --");

		lockNodeInSE(titleArticle, nameArticle, summaryArticle, contentArticle);

		info("-- Step 2: Open form to unlock node --");

		goToAdminManageLockAndVerify(titleArticle);

		info("-- Step 3: Delete locked node --");

		goToSiteExplorer();

		//Delete locked node
		deleteDocument(elementArticleToLock);

		info("-- Step 4: Check list locked node after deleting node--");

		goToContentAdministration();

		click(ELEMENT_ADVANCED_CONFIGURATION_TAB);

		click(ELEMENT_MANAGE_LOCKS);

		waitForTextNotPresent(titleArticle);

		info("-- SignOut --");	

		signOut();
	}

	/*-- Case ID 006 
	 *-- Add permission for user to unlock node
	 *--*/
	@Test
	public void test06_AddPermissionForUserToUnLockNode(){

		String titleArticle = "test06";
		String nameArticle = "test06";
		String summaryArticle = "summary of article";
		String contentArticle = "content of article";
		By elementArticleToLock = By.xpath(ELEMENT_ARTICLE_LOCKED_BY_ADMIN.replace("${titleOfFile}", titleArticle));

		info("-- Step 1: Lock node --");

		lockNodeInSE(titleArticle, nameArticle, summaryArticle, contentArticle);

		info("-- Step 2: Open form to add permission to unlock node --");

		goToAdminManageLockAndVerify(titleArticle);

		click(ELEMENT_MANAGE_LOCK_TAB);

		info("-- Step 3: Add permission for group to unlock node --");

		selectGroup(ELEMENT_GROUP_TO_SELECT);

		click(ELEMENT_GROUP_MEMBERSHIP);

		//Groups are listed on the table
		waitForTextPresent(ELEMENT_GROUP_MEMBERSHIP_DISPLAY + ":/" + ELEMENT_GROUP_DISPLAY);

		click(ELEMENT_DELETE_SELECTED_GROUP);

		waitForTextNotPresent(ELEMENT_GROUP_MEMBERSHIP_DISPLAY + ":/" + ELEMENT_GROUP_DISPLAY);

		info("-- Restore original data --");

		goToSiteExplorer();

		//Delete locked node
		deleteDocument(elementArticleToLock);

		info("-- SignOut--");

		signOut();
	}

	/*-- Case ID 007
	 *-- Delete permission of group
	 *--*/
	@Test
	public void test07_DeletePermissionOfGroup(){
		String titleArticle = "test07";
		String nameArticle = "test07";
		String summaryArticle = "summary of article";
		String contentArticle = "content of article";
		By elementArticleUnLock = By.xpath(ELEMENT_ARTICLE_UNLOCK.replace("${titleOfFile}", titleArticle));

		info("-- Step 1: Lock node --");

		lockNodeInSE(titleArticle, nameArticle, summaryArticle, contentArticle);

		info("-- Step 2: Open form to add permission to unlock node --");

		goToAdminManageLockAndVerify(titleArticle);

		click(ELEMENT_MANAGE_LOCK_TAB);

		info("-- Step 3: Add permission for group to unlock node --");

		selectGroup(ELEMENT_GROUP_TO_SELECT);

		click(ELEMENT_GROUP_MEMBERSHIP);

		//Groups are listed on the table
		waitForTextPresent(ELEMENT_GROUP_MEMBERSHIP_DISPLAY + ":/" + ELEMENT_GROUP_DISPLAY);

		driver.close();

		WebDriver driverTest = initNewDriverSeleniumTest();

		actions = new Actions(driverTest);

		info("-- Login with user in the selected group and un lock that node --");

		loginEcms("mary", "gtn");

		goToAdminManageLockAndVerify(titleArticle);

		click(ELEMENT_UNLOCK_ARTICLE);

		waitForTextNotPresent(titleArticle);

		info("-- Step 4: Delete group --");

		signOut();

		driverTest.get(baseUrl);

		loginEcms(DATA_USER, DATA_PASS);

		goToContentAdminManageLockTab();

		click(ELEMENT_DELETE_SELECTED_GROUP);

		waitForTextNotPresent(ELEMENT_GROUP_MEMBERSHIP_DISPLAY + ":/" + ELEMENT_GROUP_DISPLAY);

		info("-- Restore original data --");

		goToSiteExplorer();

		goToNodeByPath("acme/documents");

		//Delete locked node
		deleteDocument(elementArticleUnLock);

		info("-- Sign Out--");

		signOut();
	}

	/*-- Case ID 008
	 *-- Delete permission of *:/platform/administrators group
	 *--*/
	@Test
	public void test08_DeletePermissionOfGroupAdmin(){
		String titleArticle = "test08";
		String nameArticle = "test08";
		String summaryArticle = "summary of article";
		String contentArticle = "content of article";
		By elementArticleToLock = By.xpath(ELEMENT_ARTICLE_LOCKED_BY_ADMIN.replace("${titleOfFile}", titleArticle));

		info("-- Step 1: Lock node --");

		lockNodeInSE(titleArticle, nameArticle, summaryArticle, contentArticle);

		info("-- Step 2: Open form to add permission to unlock node --");

		goToAdminManageLockAndVerify(titleArticle);

		click(ELEMENT_MANAGE_LOCK_TAB);

		info("-- Step 3: Add permission for group to unlock node --");

		selectGroup(ELEMENT_GROUP_TO_SELECT);

		click(ELEMENT_GROUP_MEMBERSHIP);

		//Groups are listed on the table
		waitForTextPresent(ELEMENT_GROUP_MEMBERSHIP_DISPLAY + ":/" + ELEMENT_GROUP_DISPLAY);

		click(ELEMENT_DELETE_SELECTED_GROUP);

		waitForTextNotPresent(ELEMENT_GROUP_MEMBERSHIP_DISPLAY + ":/" + ELEMENT_GROUP_DISPLAY);

		info("-- Step 4: Delete permission of *:/platform/administrators group --");

		click(ELEMENT_DELETE_ADMIN_GROUP);

		waitForMessage(MESSAGE_CANNOT_DELETE_GROUP);

		closeMessageDialog();

		info("-- Restore original data --");

		goToSiteExplorer();

		deleteDocument(elementArticleToLock);

		info("-- SignOut --");

		signOut();
	}

	/*-- Case ID 009 
	 *-- Check unlock permission
	 *--*/
	@Test
	public void test09_CheckUnlockPermission(){
		String titleArticle = "test09";
		String nameArticle = "test09";
		String summaryArticle = "summary of article";
		String contentArticle = "content of article";
		By elementArticleToLock = By.xpath(ELEMENT_ARTICLE_LOCKED_BY_ADMIN.replace("${titleOfFile}", titleArticle));
		By elementArticleUnLock = By.xpath(ELEMENT_ARTICLE_UNLOCK.replace("${titleOfFile}", titleArticle));
		
		info("-- Step 1: Lock node --");

		lockNodeInSE(titleArticle, nameArticle, summaryArticle, contentArticle);

		info("-- Step 2: Open form to add permission to unlock node --");

		goToAdminManageLockAndVerify(titleArticle);

		click(ELEMENT_MANAGE_LOCK_TAB);

		info("-- Step 3: Add permission for group to unlock node --");

		selectGroup(ELEMENT_GROUP_TO_SELECT);

		click(ELEMENT_GROUP_MEMBERSHIP);

		//Groups are listed on the table
		waitForTextPresent(ELEMENT_GROUP_MEMBERSHIP_DISPLAY + ":/" + ELEMENT_GROUP_DISPLAY);
		
		info("-- Step 4: Check unlock permission --");
		
		driver.close();

		WebDriver driverTest = initNewDriverSeleniumTest();

		actions = new Actions(driverTest);

		info("-- Login with user in the selected group and un-lock that node --");

		loginEcms("mary", "gtn");
		
		goToSiteExplorer();

		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);

		goToNodeByPath("acme/documents");
		
		rightClickOnElement(elementArticleToLock);

		click(ELEMENT_MENU_UNLOCK);
		
		waitForElementNotPresent(elementArticleToLock);
		
		signOut();

		driverTest.get(baseUrl);

		loginEcms(DATA_USER, DATA_PASS);
		
		info("-- Restore original data --");

		goToSiteExplorer();

		goToNodeByPath("acme/documents");
		
		deleteDocument(elementArticleUnLock);
		
		goToContentAdminManageLockTab();
		
		click(ELEMENT_DELETE_SELECTED_GROUP);

		waitForTextNotPresent(ELEMENT_GROUP_MEMBERSHIP_DISPLAY + ":/" + ELEMENT_GROUP_DISPLAY);
		
		info("-- SignOut --");
		
		signOut();
	}

	/*----- Auxiliary function -----*/
	public static WebDriver initNewDriverSeleniumTest(){
		driver = new FirefoxDriver();
		driver.get(baseUrl);
		return driver;
	}

	//Create a node and lock it in Sites Explorer
	public void lockNodeInSE(String titleArticle, String nameArticle, String summaryArticle, String contentArticle){
		By elementCopiedArticleUnLock = By.xpath("//a[@title='" + titleArticle +" " + "']");
		String elementLockedArticleIcon = ELEMENT_ARTICLE_LOCKED_BY_ADMIN.replace("${titleOfFile}", titleArticle); 

		goToSiteExplorer();

		chooseDrive(ELEMENT_SITES_MANAGEMENT_DRIVE);

		goToNodeByPath("acme/documents");

		goToAddNewContent();

		createNewArticle(titleArticle, nameArticle, summaryArticle, contentArticle);

		waitForTextPresent(titleArticle);

		//Lock node
		lockNode(elementCopiedArticleUnLock);

		click(ELEMENT_DOCUMENT);

		waitForElementPresent(elementLockedArticleIcon);	
	}

	//Verify locked node at Administration/Manage Lock
	public void goToAdminManageLockAndVerify(String titleOfArticle){
		goToContentAdministration();
		click(ELEMENT_ADVANCED_CONFIGURATION_TAB);
		click(ELEMENT_MANAGE_LOCKS);
		waitForTextPresent(titleOfArticle);
	}

}
