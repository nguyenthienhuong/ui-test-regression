package org.exoplatform.selenium.platform.ecms.functional.dms.siteexplorer.createnode;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import static org.exoplatform.selenium.TestLogger.*;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.*;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.*;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;

/*
 * @author: Marine
 * @editor: Lientm
 * @date: 9/2012
 * @date: 5/11/2012: Lientm update case multiple users - case 07
 */

public class ECMS_DMS_SE_CreateNodeFolder extends EcmsBase {

public static final String DATA_USER = "john";
public static final String DATA_PASS = "gtn";

public static final By ELEMENT_NEW_FOLDER= By.linkText("New Folder");
public static final String ELEMENT_NEW_FOLDER_ALERT="The field \"Title\" is required.";
public static final String ELEMENT_WARNING_MESSAGE_PERMISSION = "You do not have permission to add a new";
public static final String ELEMENT_SELECT_WEBCONTRIBUTOR = "//div[@id='PermissionInfo']//table//tr/td/div[text()='${membership}']/../../td[6]//img[@class='EditIcon']";
//public static final By ELEMENT_WARNING_MESSAGE_ICON = By.xpath("//span[@class='PopupIcon WarningMessageIcon']");
//public static final By ELEMENT_SEARCH_PAGE_ALERT = By.xpath("//div[@class='UIPopupWindow UIDragObject ExoMessageDecorator']");


	@BeforeMethod
	public void beforeMethods() throws Exception {
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);		
		info("Login to ECMS with user: "+DATA_USER);
		loginEcms(DATA_USER, DATA_PASS);
		goToSiteExplorer();

	}

	@AfterMethod
	public void afterMethods() throws Exception {
		info("Logout to ECMS");
		logoutEcms();
		driver.manage().deleteAllCookies();
		driver.quit();
		actions = null;
	}


	/* case1: Add a folder in root path
	 * create new folder (content folder) 
	 * check add successfully
	 * delete content folder
	 */
	@Test
	public void test01_AddFolderInRootPath() {
		String DATA_CONTENT_FOLDER_TITLE = "ECMS_DMS_SE_CreateNodeFolder_contentfolder_01";
		By ELEMENT_CONTENT_FOLDER = By.linkText(DATA_CONTENT_FOLDER_TITLE);
		
		//create new content folder
		info("Create new content folder with title: "+DATA_CONTENT_FOLDER_TITLE);
		createNewContentFolder(DATA_CONTENT_FOLDER_TITLE,DATA_CONTENT_FOLDER_TITLE);
		waitForElementPresent(ELEMENT_CONTENT_FOLDER);
		assert isElementPresent(ELEMENT_CONTENT_FOLDER):"Node has not been created"; 
		info("Create new content folder successfully");
		
		//delete content folder
		goToNode(ELEMENT_CONTENT_FOLDER);
		deleteDocument(ELEMENT_CONTENT_FOLDER);
	}
	
	
	/* case2: Case 2: Add folder when do not input data in 'Name' field
	 * add new folder (content folder) with name blank
	 * check message
	 */
	@Test
	public void test02_AddFolderWithBlankName() {
		String DATA_CONTENT_FOLDER_TITLE = "ECMS_DMS_SE_CreateNodeFolder_contentfolder_02";
		
		//create new content folder with name blank
		debug("Create new content folder with the title: "+DATA_CONTENT_FOLDER_TITLE +"and name blank");
		createNewContentFolder(DATA_CONTENT_FOLDER_TITLE,"");	
		checkAlertWarning("The field \"Name\" is required.");
	}


	/*case3: Add folder with special characters in 'Name'  field like !,@,#
	 * add new folder (content folder) with name field like !,@,#
	 * check create successfully
	 * delete content folder
	 */
	@Test
	public void test03_AddFolderWithSpecialCharsName() {
		String DATA_CONTENT_FOLDER_TITLE = "ECMS_DMS_SE_CreateNodeFolder_contentfolder_03";
		By ELEMENT_CONTENT_FOLDER = By.linkText(DATA_CONTENT_FOLDER_TITLE);
		
		//Create new content folder with name contains special characters
		info("Create new folder with name " + DATA_SPECIAL_CHARACTER_STRING);
		createNewContentFolder(DATA_CONTENT_FOLDER_TITLE,DATA_SPECIAL_CHARACTER_STRING);
		waitForElementPresent(ELEMENT_CONTENT_FOLDER);
		
		//delete content folder
		goToNode(ELEMENT_CONTENT_FOLDER);
		deleteDocument(ELEMENT_CONTENT_FOLDER);
	}
	
	/*case4: Add folder in a node is in 'Check in' status
	 * login
	 * crate new node (article document)
	 * check in node
	 * check can not add folder into checked in Node
	 * checkout node - delete node
	 */
	@Test
	public void test04_AddFolderInACheckinNode(){
		String DATA_ARTICLE_TITLE = "ECMS_DMS_SE_CreateNodeFolder_article_04";
		By ELEMENT_ARTICLE  = By.linkText(DATA_ARTICLE_TITLE);
		
		//create new node - article
		debug("Create new node (article document) with name: "+DATA_ARTICLE_TITLE);
		goToAddNewContent();
		createNewArticle(DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, "", "");
		waitForElementPresent(ELEMENT_ARTICLE);
		info("Create new node (article document) successfully");
		
		//check in node
		goToNode(ELEMENT_ARTICLE);
		checkInNode(ELEMENT_ARTICLE);
		
		//check can not add folder to checked in node
		goToNode(ELEMENT_ARTICLE);
		waitForElementNotPresent(ELEMENT_NEW_FOLDER);
		
		//check out and delete node
		goToNode(ELEMENT_ARTICLE);
		checkOutNode(ELEMENT_ARTICLE);
		deleteDocument(ELEMENT_ARTICLE);
		
	}
	
	/* case 5: Add folder in a node has parent is in 'Check in' status
	 * create new parent node (article document)
	 * create new child node (kofax document)
	 * check in parent node
	 * check can add folder (content folder) into child node (kofax document)
	 * delete data
	 * logout
	 */
	 
	@Test
	public void test05_AddFolderInChildHavingParentInCheckInStatus() {
		String DATA_ARTICLE_TITLE = "ECMS_DMS_SE_CreateNodeFolder_article_05";
		By ELEMENT_ARTICLE = By.linkText(DATA_ARTICLE_TITLE);
		String DATA_KOFAX_NAME = "Testkofax_05";
		By ELEMENT_KOFAX = By.linkText(DATA_KOFAX_NAME);
		String DATA_CONTENT_FOLDER_TITLE = "Testcontentfolder_05";
		By ELEMENT_CONTENT_FOLDER = By.linkText(DATA_CONTENT_FOLDER_TITLE);
		
		//create new parent node
		debug("Create new article with the title: "+DATA_ARTICLE_TITLE);
		goToAddNewContent();
		createNewArticle(DATA_ARTICLE_TITLE, DATA_ARTICLE_TITLE, "", "");
		waitForElementPresent(ELEMENT_ARTICLE);
		info("Create new parent node (article document) successfully");
		
		//create new child node: kofax document
		info("add new kofax document in this article document");
		goToNode(ELEMENT_ARTICLE);
		debug("Create new kofax document the title: "+DATA_KOFAX_NAME);
		goToAddNewContent();
		createNewKofax(DATA_KOFAX_NAME);
		checkPreferenceOption("enableStructure");
		goToNode(ELEMENT_ARTICLE);
		waitForElementPresent(ELEMENT_KOFAX);
		info("Create new child node (kofax document) sucessfully");
		
		//check in parent node
		info("Check-in the parent node");
		goToNode(ELEMENT_ARTICLE);
		checkInNode(ELEMENT_ARTICLE);
		
		//add new folder into child node
		goToNode(ELEMENT_KOFAX);
		info("Create new content folder in child node with name: "+DATA_CONTENT_FOLDER_TITLE);
		createNewContentFolder(DATA_CONTENT_FOLDER_TITLE, DATA_CONTENT_FOLDER_TITLE);
		goToNode(ELEMENT_KOFAX);
		waitForElementPresent(ELEMENT_CONTENT_FOLDER);
		
		//check out node and delete data
		goToNode(ELEMENT_ARTICLE);
		checkOutNode(ELEMENT_ARTICLE);
		deleteDocument(ELEMENT_ARTICLE);
	}
	
	/*
	 * Case 6 : Add folder when user does not have add node right
	 * create new article document
	 * set for user mary does not has add node permission
	 * login with mary
	 * check can not add folder in this article document
	 * login by john
	 * delete data
	 */
	@Test
	public void test06_AddFolderWhenUserNotHavingAddNodePermission() {
		String DATA_CONTENT_FOLDER_TITLE = "ECMS_DMS_SE_CreateNodeFolder_contentfolder_06";
		By ELEMENT_CONTENT_FOLDER = By.linkText(DATA_CONTENT_FOLDER_TITLE);
		
		//create new content folder
		debug("Create new content folder with the title: "+DATA_CONTENT_FOLDER_TITLE);
		createNewContentFolder(DATA_CONTENT_FOLDER_TITLE,DATA_CONTENT_FOLDER_TITLE);
		waitForElementPresent(ELEMENT_CONTENT_FOLDER);
		
		//set permission for user mary
		info("Set permission for user Mary does not has add node permission");
		goToNode(ELEMENT_CONTENT_FOLDER);
		setPermissionAddNodeForUser("mary",1,1);
		logoutEcms();
		
		//login with mary
		loginEcms("mary", "gtn");
		
		//check can not add folder
		goToSiteExplorer();
		goToNode(ELEMENT_CONTENT_FOLDER);
		click(ELEMENT_NEW_FOLDER_LINK);		
		checkAlertWarning(ELEMENT_WARNING_MESSAGE_PERMISSION);
		logoutEcms();
		
		//login with john to delete data
		loginEcms(DATA_USER, DATA_PASS);
		goToSiteExplorer();
		goToNode(ELEMENT_CONTENT_FOLDER);
		deleteDocument(ELEMENT_CONTENT_FOLDER);
	}
	
	
	/*
	 * Case 7 : Add folder in a locked node by user is not locker
	 * Step 1: Lock node	
	 * 	- Create a node or select existing node
	 * 	- Right click on this node and select "Lock"
	 * Step 2: Add folder into locked node	
	 * 	- Keep above user login
	 * 	- Login by another user
	 * 	- Select above locked node -> check can not see "Add Folder" on action bar
	 */
	@Test
	public void test07_AddFolderInLockedNodeByNotLocker() {		
		String DATA_CONTENT_FOLDER_TITLE = "ECMS_DMS_SE_CreateNodeFolder_contentfolder_07";
		By ELEMENT_CONTENT_FOLDER = By.linkText(DATA_CONTENT_FOLDER_TITLE);
		
		//create new content folder
		info("Create new content folder with the title: "+DATA_CONTENT_FOLDER_TITLE);
		createNewContentFolder(DATA_CONTENT_FOLDER_TITLE,DATA_CONTENT_FOLDER_TITLE);
		waitForElementPresent(ELEMENT_CONTENT_FOLDER);
		
		//lock node (content folder) by user John
		goToNode(ELEMENT_CONTENT_FOLDER);
		info("Lock this node");
		lockNode(ELEMENT_CONTENT_FOLDER);
		
		//check lock node
		assert checkLockNode(ELEMENT_CONTENT_FOLDER):"Locking node is not successful";
		info("Locking node is successful");
		driver.close();
		
		//login with user mary
		info("Init new session");
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		info("Login with Mary");
		loginEcms("mary", "gtn");
		
		//check can not see "Add Folder" on action bar
		goToSiteExplorer();
		waitForElementPresent(ELEMENT_CONTENT_FOLDER);
		click(ELEMENT_CONTENT_FOLDER);
		waitForElementNotPresent(ELEMENT_NEW_FOLDER);
		info("Cannot see New Folder link");

		//delete data with user John
		logoutEcms();
		info("login ecms with john");
		loginEcms(DATA_USER, DATA_PASS);
		
		//delete data
		goToSiteExplorer();
		goToNode(ELEMENT_CONTENT_FOLDER);
		deleteDocument(ELEMENT_CONTENT_FOLDER);
	}

	/*
	 * case 8:Add folder when do not input data in 'Title' field
	 */
	@Test
	public void test08_AddFolderWithBlankTitle() {
		String DATA_CONTENT_FOLDER_NAME = "ECMS_DMS_SE_CreateNodeFolder_contentfolder_08";

		debug("Create content folder with the title: "+DATA_CONTENT_FOLDER_NAME);
		createNewContentFolder("",DATA_CONTENT_FOLDER_NAME);
		checkAlertWarning(ELEMENT_NEW_FOLDER_ALERT);
	}

	/*
	 * case 9:Add folder with special characters in 'Title' field like !,@,#
	 */
	@Test
	public void test09_AddFolderWithSpecialCharsTitle() {
		String DATA_CONTENT_FOLDER_NAME = "ECMS_DMS_SE_CreateNodeFolder_contentfolder_09";
		By ELEMENT_CONTENT_FOLDER = By.linkText(DATA_SPECIAL_CHARACTER_STRING);
		
		//create new content folder with special characters in 'Title' field like !,@,#
		info("Create content folder with the title: "+ DATA_SPECIAL_CHARACTER_STRING);
		createNewContentFolder(DATA_SPECIAL_CHARACTER_STRING, DATA_CONTENT_FOLDER_NAME);
		waitForElementPresent(ELEMENT_CONTENT_FOLDER);
		
		//delete data
		info("Delete content folder with title contains special characters");
 		goToNode(ELEMENT_CONTENT_FOLDER);
	 	deleteDocument(ELEMENT_CONTENT_FOLDER);
	 	}
}
