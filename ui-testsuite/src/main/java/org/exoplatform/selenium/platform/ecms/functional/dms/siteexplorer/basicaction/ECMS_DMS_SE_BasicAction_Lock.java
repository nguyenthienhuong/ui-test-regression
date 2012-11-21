package org.exoplatform.selenium.platform.ecms.functional.dms.siteexplorer.basicaction;

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
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.*;

public class ECMS_DMS_SE_BasicAction_Lock extends EcmsBase {
	String DATA_USER = "john";
	String DATA_PASS = "gtn";
	By ELEMENT_ACME_NODE = By.xpath("//a[@title='acme ']");

	@BeforeMethod
	public void beforeMethods() throws Exception {
		initSeleniumTest();
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

	/*Case 01: Lock a node
	 * Create a node
	 * Lock this node
	 */
	@Test
	public void test01_LockANode () {
		String DATA_ARTICLE_TITLE = "EMCS_SE_BasicAction_Lock_Case01";
		By ARTICLE_PATH = By.xpath("//a[@title='"+DATA_ARTICLE_TITLE+" "+"']");
		By ARTICLE_PATH_LOCKED =By.xpath("//a[contains(text(), '"+DATA_ARTICLE_TITLE+"')]");

		info("Go to site explorer");
		goToSiteExplorer();

		info("Go to amce node");
		waitForElementPresent(ELEMENT_ACME_NODE);
		goToNode(ELEMENT_ACME_NODE);

		info("Click add new content");
		goToAddNewContent();

		info("Create new article");
		createNewArticle(DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE);

		info("Lock node");
		lockNode(ARTICLE_PATH);

		info("Check locked node");
		checkLockNode(ARTICLE_PATH_LOCKED);

		//Delete data
		deleteDocument(ARTICLE_PATH_LOCKED);
	}
	/*Case02: Unlock a node by locker
	 * Create a node
	 * Lock node
	 * Unlock node
	 */
	@Test
	public void test02_UnlockNodeByLocker () {
		String DATA_ARTICLE_TITLE = "EMCS_SE_BasicAction_Lock_Case02";
		By ARTICLE_PATH = By.xpath("//a[@title='"+DATA_ARTICLE_TITLE+" "+"']");
		By ARTICLE_PATH_LOCKED =By.xpath("//a[contains(text(), '"+DATA_ARTICLE_TITLE+"')]");

		info("Go to site explorer");
		goToSiteExplorer();

		info("Go to amce node");
		waitForElementPresent(ELEMENT_ACME_NODE);
		goToNode(ELEMENT_ACME_NODE);

		info("Click add new content");
		goToAddNewContent();

		info("Create new article");
		createNewArticle(DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE);

		info("Lock node");
		lockNode(ARTICLE_PATH);

		info("Check locked node");
		checkLockNode(ARTICLE_PATH_LOCKED);

		info("Unlock node by locker");
		unLockNode(ARTICLE_PATH_LOCKED);
		waitForElementPresent(ARTICLE_PATH);

		//Delete data
		deleteDocument(ARTICLE_PATH);
	}
	
	/*Case03: Unlock a node by user is  not locker 
	 * create new node
	 * lock node by user John
	 * login with mary
	 * check mary can not unlock this node
	 */
	@Test
	public void test03_UnlockNodeByUserIsNotLocker(){
		String DATA_ARTICLE_TITLE = "EMCS_SE_BasicAction_Lock_Case03";
		By ARTICLE_PATH = By.linkText(DATA_ARTICLE_TITLE);
		
		//create new node: article document
		goToSiteExplorer();
		goToAddNewContent();
		info("Create new article document");
		createNewArticle(DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, "", "");
		waitForElementPresent(ARTICLE_PATH);
		
		//lock node
		goToNode(ARTICLE_PATH);
		lockNode(ARTICLE_PATH);
		
		//check log node
		checkLockNode(ARTICLE_PATH);
		driver.close();
		
		//login with mary
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		loginEcms("mary", "gtn");
		goToSiteExplorer();
		
		//check mary cannot unlock this node
		goToNode(ARTICLE_PATH);
		rightClickOnElement(ARTICLE_PATH);
		waitForElementNotPresent(ELEMENT_MENU_UNLOCK);
		waitForElementNotPresent(ELEMENT_MENU_LOCK);
		info("User cannot lock or unlock node");
		logoutEcms();
		
		//delete data with user John
		loginEcms(DATA_USER, DATA_PASS);
		goToSiteExplorer();
		deleteData(ARTICLE_PATH);
	}

	/*Case 04: Lock a node while parent node is being in locked status
	 * Login
	 * Create node and its child node
	 * Lock parent node
	 * Lock child node
	 */
	@Test
	public void test04_LockANodeWhileParentNodeIsBeingLocked() {
		String DATA_ARTICLE_TITLE = "EMCS_SE_BasicAction_Lock_Case04";
		By ARTICLE_PATH = By.xpath("//a[@title='"+DATA_ARTICLE_TITLE+" "+"']");
		By ARTICLE_PATH_LOCKED = By.xpath("//a[contains(text(), '"+DATA_ARTICLE_TITLE+"')]");
		String DATA_KOFAX_NAME = "test04";
		By KOFAX_PATH = By.xpath("//a[@title='"+DATA_KOFAX_NAME+" "+"']");
		By KOFAX_PATH_LOCKED = By.xpath("//a[contains(text(), '"+DATA_KOFAX_NAME+"')]");
		String ENABLE_DMS_STRUC_ID = "enableStructure";

		/*Step 1: Create parent node and child node, lock parent node*/
		info("Go to site explorer");
		goToSiteExplorer();

		info("Go to amce node");
		waitForElementPresent(ELEMENT_ACME_NODE);
		goToNode(ELEMENT_ACME_NODE);

		info("Click add new content");
		goToAddNewContent();

		info("Create new article document");
		createNewArticle(DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE);

		info("Click add new content");
		goToAddNewContent();

		info("Add new kofax document");
		createNewKofax(DATA_KOFAX_NAME);
		pause(1000);

		info("Lock parent node");
		lockNode(ARTICLE_PATH);

		info("Check locked node");
		checkLockNode(ARTICLE_PATH_LOCKED);
		pause(500);

		/*Step 2: Lock child node*/
		info("Enable DMS structure");
		checkPreferenceOption(ENABLE_DMS_STRUC_ID);

		waitForElementPresent(ARTICLE_PATH_LOCKED);
		goToNode(ARTICLE_PATH_LOCKED);

		info("Lock child node");
		lockNode(KOFAX_PATH);

		info("Check locked node");
		checkLockNode(KOFAX_PATH_LOCKED);

		//Delete data
		deleteDocument(ARTICLE_PATH_LOCKED);
	}

	/*Case05: Unlock a node while parent node is being in lock status
	 * Login
	 * Create node and its child node
	 * Lock parent node
	 * Lock child node
	 * Unlock child node
	 */
	@Test
	public void test05_UnLockANodeWhileParentNodeIsBeingLocked() {

		String DATA_ARTICLE_TITLE = "EMCS_SE_BasicAction_Lock_Case05";
		By ARTICLE_PATH = By.xpath("//a[@title='"+DATA_ARTICLE_TITLE+" "+"']");
		By ARTICLE_PATH_LOCKED = By.xpath("//a[contains(text(), '"+DATA_ARTICLE_TITLE+"')]");
		String DATA_KOFAX_NAME = "test05";
		By KOFAX_PATH = By.xpath("//a[@title='"+DATA_KOFAX_NAME+" "+"']");
		By KOFAX_PATH_LOCKED = By.xpath("//a[contains(text(), '"+DATA_KOFAX_NAME+"')]");
		String ENABLE_DMS_STRUC_ID = "enableStructure";

		/*Step 1: Create parent node and child node, lock parent node*/
		info("Go to site explorer");
		goToSiteExplorer();

		info("Go to amce node");
		waitForElementPresent(ELEMENT_ACME_NODE);
		goToNode(ELEMENT_ACME_NODE);

		info("Click add new content");
		goToAddNewContent();

		info("Create new article document");
		createNewArticle(DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE);

		info("Click add new content");
		goToAddNewContent();

		info("Add new kofax document");
		createNewKofax(DATA_KOFAX_NAME);
		pause(1000);

		info("Lock parent node");
		lockNode(ARTICLE_PATH);

		info("Check locked node");
		checkLockNode(ARTICLE_PATH_LOCKED);
		pause(1000);

		/*Step 2: Lock child node and unlock child node*/
		info("Enable DMS structure");
		checkPreferenceOption(ENABLE_DMS_STRUC_ID);
		pause(500);
		

		waitForElementPresent(ARTICLE_PATH_LOCKED);
		goToNode(ARTICLE_PATH_LOCKED);

		info("Lock child node");
		lockNode(KOFAX_PATH);

		info("Check locked node");
		checkLockNode(KOFAX_PATH_LOCKED);

		info("Unlock child node");
		unLockNode(KOFAX_PATH_LOCKED);
		waitForElementPresent(KOFAX_PATH);

		//Delete data
		deleteDocument(ARTICLE_PATH_LOCKED);
	}
	
	/*Case 06: Lock node while it is being checked-in
	 * Create node
	 * Check in this node
	 * Verify cannot lock check in node
	 */
	@Test
	public void test06_LockNodeWhileItIsBeingCheckedIn() {
		String DATA_FILE_NAME = "EMCS_DMS_SE_Lock_Case06";
		By FILE_PATH = By.xpath("//a[@title='"+DATA_FILE_NAME+" "+"']");

		info("Go to site explorer");
		goToSiteExplorer();

		info("Go to amce node");
		waitForElementPresent(ELEMENT_ACME_NODE);
		goToNode(ELEMENT_ACME_NODE);

		info("Click add new content");
		goToAddNewContent();

		info("Create new file document");
		createNewFile(DATA_FILE_NAME, DATA_FILE_NAME, DATA_FILE_NAME);
		waitForTextPresent(DATA_FILE_NAME);
		
		info("Check in file document");
		checkInNode(FILE_PATH);
		
		info("Verify cannot lock checked-in node");
		rightClickOnElement(FILE_PATH);
		pause(500);
		waitForElementNotPresent(By.linkText("Lock"));
		
		//Delete data
		checkOutNode(FILE_PATH);
		deleteDocument(FILE_PATH);
	}
	/*Case 07: Lock child node while its parent is being check in status
	 * Create parent node
	 * Create child node
	 * Check in parent node
	 * Lock child node
	 */
	@Test
	public void test07_LockChildNodeWhileItsParentIsInCheckinStatus () {
		String DATA_ARTICLE_NAME = "EMCS_DMS_SE_Lock_Case07";
		By ARTICLE_PATH = By.xpath("//a[@title='"+DATA_ARTICLE_NAME+" "+"']");
		String DATA_KOFAX_NAME = "kofaxtest";
		By KOFAX_PATH = By.xpath("//a[@title='"+DATA_KOFAX_NAME+" "+"']");
		By KOFAX_PATH_LOCKED = By.xpath("//a[contains(text(), '"+DATA_KOFAX_NAME+"')]");
		String ENABLE_DMS_STRUC_ID = "enableStructure";
		
		info("Go to site explorer");
		goToSiteExplorer();

		info("Go to amce node");
		waitForElementPresent(ELEMENT_ACME_NODE);
		goToNode(ELEMENT_ACME_NODE);

		info("Click add new content");
		goToAddNewContent();

		info("Create new announcement document");
		createNewArticle(DATA_ARTICLE_NAME, DATA_ARTICLE_NAME, DATA_ARTICLE_NAME, DATA_ARTICLE_NAME);
		waitForTextPresent(DATA_ARTICLE_NAME);
		
		info("Create child node of new document");
		goToAddNewContent();
		createNewKofax(DATA_KOFAX_NAME);
		pause(1000);
		
		info("Check in parent node");
		checkInNode(ARTICLE_PATH);
		
		info("Enable DMS structure");
		checkPreferenceOption(ENABLE_DMS_STRUC_ID);
		pause(500);
		
		info("Lock child node");
		lockNode(KOFAX_PATH);
		
		info("Check unlock node");
		checkLockNode(KOFAX_PATH_LOCKED);
		
		//Delete data
		goToNode(ARTICLE_PATH);
		checkOutNode(ARTICLE_PATH);
		deleteDocument(ARTICLE_PATH);
	}
	
	/*Case 08: Lock a node when user has not set property right
	 * Login John
	 * Create document
	 * Set right for this document: James has all permission except set property
	 * Login as James
	 * James cannot lock this documents
	 */
	@Test
	public void test08_LockNodeWhenUserHasNotSetPropertyRight () {
		By ELEMENT_SYSTEM_TAB = By.linkText("System");

		By ELEMENT_PERMISSION_LINK = By.linkText("Permissions");

		By DELETE_PLF_ADMIN_PERMISSION=By.xpath("//div[@title='*:/platform/administrators']/following::div/img[@title='Remove Permission']");

		By DELETE_PLF_WEBCONTRIBUTOR_PERMISSION=By.xpath("//div[@title='*:/platform/web-contributors']/following::div/img[@title='Remove Permission']");

		By DELETE_ANY_PERMISSION=By.xpath("//div[@title='any']/following::div/img[@title='Remove Permission']");

		String CONFIRM_REMOVE_PERMISSION_MSS="Are you sure to remove this permission?";

		By SELECT_USER=By.xpath("//img[@alt='Select User']");

		By SELECT_JAMES=By.xpath("//div[@title='james']/following::div/img[@class='SelectPageIcon']");

		By SELECT_READ_RIGHT=By.id("read");
		By SELECT_REMOVE_RIGHT=By.id("remove");
		By SELECT_ADD_NODE_RIGHT = By.id("add_node");
		String DATA_ARTICLE_NAME = "ECMS_DMS_SE_BasicAction_Lock_Case08";
		By ARTICLE_PATH = By.xpath("//a[@title='"+DATA_ARTICLE_NAME+" "+"']");
		
		info("Go to CE");
		goToSiteExplorer();
		
		info("Click New Content");
		goToAddNewContent();

		info("Create article");
		createNewArticle(DATA_ARTICLE_NAME,  DATA_ARTICLE_NAME, DATA_ARTICLE_NAME, DATA_ARTICLE_NAME); 

		info("Click System tab");
		click(ELEMENT_SYSTEM_TAB);

		info("Click Permission");
		click(ELEMENT_PERMISSION_LINK);

		info("Delete permisson of all except John");
		pause(500);
		click(DELETE_PLF_ADMIN_PERMISSION);
		waitForConfirmation(CONFIRM_REMOVE_PERMISSION_MSS);

		pause(500);
		click(DELETE_PLF_WEBCONTRIBUTOR_PERMISSION);
		waitForConfirmation(CONFIRM_REMOVE_PERMISSION_MSS);

		pause(500);
		click(DELETE_ANY_PERMISSION);
		waitForConfirmation(CONFIRM_REMOVE_PERMISSION_MSS);

		info("Click Add User icon");
		click(SELECT_USER);

		info("Choose James");
		click(SELECT_JAMES);

		info("Choose all rights accept Set property");
		check(SELECT_READ_RIGHT);
		check(SELECT_ADD_NODE_RIGHT);
		check(SELECT_REMOVE_RIGHT);

		info("Save then close");
		click(ELEMENT_SAVE_BUTTON);
		click(ELEMENT_CLOSE_BUTTON);

		info("Logout then login as James");
		logoutEcms();
		loginEcms("james", "gtn");

		info("Go to CE");
		goToSiteExplorer();
		
		info("Right click on node");
		rightClickOnElement(ARTICLE_PATH);
		
		info("Verify user cannot lock document");
		waitForElementNotPresent(By.linkText("Lock"));
		
		info("Logout");
		logoutEcms();
		
		//Login as John and delete data
		loginEcms(DATA_USER, DATA_PASS);
		
		goToSiteExplorer();
		
		deleteDocument(ARTICLE_PATH);
	}
	
	/*Case 09: Automatically unlock a node when user logout
	 * Login
	 * Create node
	 * Lock this node
	 * Logout
	 * Login --> Locked node is unlock
	 */
	@Test
	public void test09_AutomaticallyUnlockANodeWhenUserLogout () {
		String DATA_ARTICLE_TITLE = "EMCS_SE_BasicAction_Lock_Case09";
		By ARTICLE_PATH = By.xpath("//a[@title='"+DATA_ARTICLE_TITLE+" "+"']");
		By ARTICLE_PATH_LOCKED =By.xpath("//a[contains(text(), '"+DATA_ARTICLE_TITLE+"')]");

		info("Go to site explorer");
		goToSiteExplorer();

		info("Go to amce node");
		waitForElementPresent(ELEMENT_ACME_NODE);
		goToNode(ELEMENT_ACME_NODE);

		info("Click add new content");
		goToAddNewContent();

		info("Create new article");
		createNewArticle(DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE);

		info("Lock node");
		lockNode(ARTICLE_PATH);

		info("Check locked node");
		checkLockNode(ARTICLE_PATH_LOCKED);
		
		info("Logout");
		logoutEcms();
		
		info("Login again");
		loginEcms(DATA_USER, DATA_PASS);
		
		goToSiteExplorer();
		goToNode(ELEMENT_ACME_NODE);
		rightClickOnElement(ARTICLE_PATH_LOCKED);
		waitForElementPresent(ELEMENT_MENU_LOCK);
		
		//Delete data
		deleteDocument(ARTICLE_PATH);
	}
}