package org.exoplatform.selenium.platform.ecms.functional.admin.categoriestags;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.platform.ecms.WcmAdmin.*;
import static org.exoplatform.selenium.platform.UserGroupManagement.*;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.*;
import static org.exoplatform.selenium.TestLogger.*;

public class ECMS_Admin_ManageCategories_Display extends EcmsBase {

	public static final String DATA_USER = "john";
	public static final String DATA_PASS = "gtn";
	public static final By ELEMENT_ADD_CATEGORY_IMG = By.xpath("//img[@title='Add category']");
	public static final By ELEMENT_ADD_CATEGORY_CHECKBOX = By.id("categoryPro");
	public static final By ELEMENT_CLOSE_WINDOW = By.xpath("//a[@title='Close Window']");
	public static final By ELEMENT_ALERT_VISIBLE = By.xpath("//div[@class='UIPopupWindow UIDragObject ExoMessageDecorator' and contains(@style,'visible')]");	

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

	/*case01: Check the displaying of Category in Sites Explorer/ System drive
	 * add new category tree with workspace = dms system
	 * go to DMS Administration Drive
	 * check can see category tree just added in /exo:ecm/exo:taxonomyTrees/definition
	 */
	@Test
	public void test01_CheckDisplayOfCategoryInSystemDrive(){
		String DATA_CATEGORY_TREE_NAME = "ECMS_Admin_ManageCategories_Display_tree_01";
		By ELEMENT_CATEGORY_TREE = By.xpath("//div[@title='" + DATA_CATEGORY_TREE_NAME + "']");
		String DATA_ACTION_NAME = "ECMS_Admin_ManageCategories_Display_action_01";

		String[] DATA1 = {DATA_CATEGORY_TREE_NAME, "collaboration","sites content/live/acme"};
		String[] DATA2 = {"",""};
		String[] DATA3 = {DATA_ACTION_NAME,"User Action/Content Addition","jcr:system/exo:ecm"};

		//add new category tree
		goToContentAdministration();
		addNewCategoryTree(DATA1, true, false, DATA2,"mary", true, false, false, false, DATA3);

		//go to AMS Administration Drive
		goToSiteExplorer();
		click(ELEMENT_SHOW_DRIVES);
		click(ELEMENT_DMS_ADMIN_DRIVE);
		selectGroup("exo:ecm/exo:taxonomyTrees/definition");
		waitForElementPresent(ELEMENT_CATEGORY_TREE);
		assert isElementPresent(ELEMENT_CATEGORY_TREE):"display category tree is false";
		info("Category tree is displaying");

		//delete data
		goToContentAdministration();
		deleteCategory(DATA_CATEGORY_TREE_NAME);
		waitForElementNotPresent(ELEMENT_ALERT_VISIBLE);		  
	}

	/*case02: Check the displaying of Category when add category for node (document/uploaded file)
	 * add new category tree
	 * create new document: article document
	 * go to add category/action bar
	 * check category tree, user can see just added category 
	 */
	@Test
	public void test02_CheckDisplayOfCategoryWhenAddCategoryForNode(){
		String DATA_CATEGORY_TREE_NAME = "ECMS_Admin_ManageCategories_Display_tree_02";
		String DATA_ACTION_NAME = "ECMS_Admin_ManageCategories_Display_action_02";
		String DATA_ARTICLE = "ECMS_Admin_ManageCategories_Display_article_02";
		By ELEMENT_ARTICLE = By.linkText(DATA_ARTICLE);
		By ELEMENT_CATEGORY_OPTION = By.xpath("//select[@id='taxonomyTree']/option[@value='" + DATA_CATEGORY_TREE_NAME + "']");
		String[] DATA1 = {DATA_CATEGORY_TREE_NAME, "collaboration","sites content/live/acme"};
		String[] DATA2 = {"",""};
		String[] DATA3 = {DATA_ACTION_NAME,"User Action/Content Addition","jcr:system/exo:ecm"};

		//add new category tree
		goToContentAdministration();
		addNewCategoryTree(DATA1, true, false, DATA2,"mary", true, false, false, false, DATA3);

		//create new document: article document
		goToSiteExplorer();
		goToAddNewContent();
		info("Create new article title: " + DATA_ARTICLE);
		createNewArticle(DATA_ARTICLE, DATA_ARTICLE, DATA_ARTICLE, DATA_ARTICLE);
		waitForElementPresent(ELEMENT_ARTICLE);
		info("Creating new article document is successful");

		//go to add category/action bar
		goToNode(ELEMENT_ARTICLE);
		goToNode(ELEMENT_CATEGORIES_LINK);
		waitForElementPresent(ELEMENT_PERMISSION_MANAGEMENT_POPUP);
		click(ELEMENT_SELECT_CATEGORY_TAB);
		click(ELEMENT_CATEGORY_TREE_BOX);
		waitForElementPresent(ELEMENT_CATEGORY_OPTION);
		info("Category tree is diplaying");
		click(ELEMENT_CLOSE_BUTTON);

		//delete data
		deleteData(ELEMENT_ARTICLE);
		goToContentAdministration();
		deleteCategory(DATA_CATEGORY_TREE_NAME);
		waitForElementNotPresent(ELEMENT_ALERT_VISIBLE);	
	}

	/*case03: Check the displaying of Category tree when user does not have permission to view when add category for node (document/uploaded file) 
	 * create new category tree
	 * set permission for user does not have read node permission
	 * create node by user does not have read node permission
	 * check can not read node by user does not have read node permission
	 */
	@Test
	public void test03_CheckDisplayOfCategoryWhenUserNotHaveReadPermission(){
		String DATA_CATEGORY_TREE_NAME = "ECMS_Admin_ManageCategories_Display_tree_03";
		String DATA_ACTION_NAME = "ECMS_Admin_ManageCategories_Display_action_03";
		String DATA_ARTICLE = "ECMS_Admin_ManageCategories_Display_article_03";
		By ELEMENT_ARTICLE = By.linkText(DATA_ARTICLE);
		By ELEMENT_CATEGORY_OPTION = By.xpath("//select[@id='taxonomyTree']/option[@value='" + DATA_CATEGORY_TREE_NAME + "']");
		String[] DATA1 = {DATA_CATEGORY_TREE_NAME, "collaboration","sites content/live/acme"};
		String[] DATA2 = {"",""};
		String[] DATA3 = {DATA_ACTION_NAME,"User Action/Content Addition","jcr:system/exo:ecm"};

		//add new category tree
		goToContentAdministration();
		addNewCategoryTree(DATA1, true, false, DATA2,"mary", true, false, false, false, DATA3);

		//delete permission default
		info("Edit category tree");
		click(By.xpath("//div[@title='" + DATA_CATEGORY_TREE_NAME + "']/../../td/div/img[@class='Edit16x16Icon']"));
		click(ELEMENT_PREVIOUS_BUTTON);
		click(ELEMENT_PREVIOUS_BUTTON);
		deletePermission("*:/platform/administrators",true);
		deletePermission("*:/platform/users",true);
		deletePermission("mary",true);
		deletePermission("any",true);
		deletePermission("*:/platform/web-contributors",true);
		click(ELEMENT_CLOSE_BUTTON);
		logoutEcms();

		//create node by user does not have read node permission
		loginEcms("mary", "gtn");
		goToSiteExplorer();
		goToAddNewContent();
		info("Create new article title: " + DATA_ARTICLE);
		createNewArticle(DATA_ARTICLE, DATA_ARTICLE, DATA_ARTICLE, DATA_ARTICLE);
		waitForElementPresent(ELEMENT_ARTICLE);
		info("Creating new article document is successfully");

		//check can not read node by user not having read permission
		goToNode(ELEMENT_ARTICLE);
		goToNode(ELEMENT_CATEGORIES_LINK);
		waitForElementPresent(ELEMENT_PERMISSION_MANAGEMENT_POPUP);
		click(ELEMENT_SELECT_CATEGORY_TAB);
		click(ELEMENT_CATEGORY_TREE_BOX);
		waitForElementNotPresent(ELEMENT_CATEGORY_OPTION);
		info("category tree is not diplaying");
		click(ELEMENT_CLOSE_BUTTON);

		//delete data
		deleteData(ELEMENT_ARTICLE);
		logoutEcms();
		loginEcms(DATA_USER, DATA_PASS);
		goToContentAdministration();
		deleteCategory(DATA_CATEGORY_TREE_NAME);
		waitForElementNotPresent(ELEMENT_ALERT_VISIBLE);
	}

	/*case04: Check the displaying of Category in Sites Explorer while creating document or uploading file
	 * create new category tree
	 * upload file, check can see new category when upload file
	 */
	@Test
	public void test04_CheckDisplayOfCategoryWhileUploadFile(){
		String DATA_CATEGORY_TREE_NAME = "ECMS_Admin_ManageCategories_Display_tree_04";
		String DATA_ACTION_NAME = "ECMS_Admin_ManageCategories_Display_action_04";
		String DATA_UPLOAD_FILE_NAME = "ECMS_Admin_ManageCategories_Display_UploadFile_04";
		String DATA_UPLOAD_FILE_LINK = "TestData/ECMS_Admin_ManageCategories_Display.jpg";
		By ELEMENT_CATEGORY_OPTION = By.xpath("//select[@id='taxonomyTree']/option[@value='" + DATA_CATEGORY_TREE_NAME + "']");
		String[] DATA1 = {DATA_CATEGORY_TREE_NAME, "collaboration","sites content/live/acme"};
		String[] DATA2 = {"",""};
		String[] DATA3 = {DATA_ACTION_NAME,"User Action/Content Addition","jcr:system/exo:ecm"};

		//add new category tree
		goToContentAdministration();
		addNewCategoryTree(DATA1, true, false, DATA2,"mary", true, false, false, false, DATA3);

		//upload new file
		goToSiteExplorer();
		goToNode(ELEMENT_UPLOAD_LINK_XPATH);
		type(ELEMENT_UPLOAD_FILE_NAME_ID, DATA_UPLOAD_FILE_NAME, false);
		driver.switchTo().frame(waitForAndGetElement(ELEMENT_UPLOAD_IMG_FRAME_XPATH));
		type(ELEMENT_UPLOAD_IMG_ID, getAbsoluteFilePath(DATA_UPLOAD_FILE_LINK), false);
		info("Upload file "+getAbsoluteFilePath(DATA_UPLOAD_FILE_LINK));
		switchToParentWindow();
		waitForElementPresent(ELEMENT_UPLOAD_FINISH_XPATH);
		click(ELEMENT_ADD_ITEM);

		//check can see new category in add category
		click(ELEMENT_CATEGORY_TREE_BOX);
		waitForElementPresent(ELEMENT_CATEGORY_OPTION);
		info("category tree is diplaying");
		click(ELEMENT_CLOSE_WINDOW);

		//delete data
		goToContentAdministration();
		deleteCategory(DATA_CATEGORY_TREE_NAME);
		waitForElementNotPresent(ELEMENT_ALERT_VISIBLE);
	}

	/*case04: Check the displaying of Category in Sites Explorer while creating document or uploading file
	 * create new category tree
	 * create document: kofax document, check that user can see new category when creating document
	 */
	@Test
	public void test04_CheckDisplayOfCategoryWhileCreatingDocument(){
		String DATA_CATEGORY_TREE_NAME = "ECMS_Admin_ManageCategories_Display_tree_04_2";
		String DATA_ACTION_NAME = "ECMS_Admin_ManageCategories_Display_action_04_2";
		By ELEMENT_CATEGORY_OPTION = By.xpath("//select[@id='taxonomyTree']/option[@value='" + DATA_CATEGORY_TREE_NAME + "']");

		String[] DATA1 = {DATA_CATEGORY_TREE_NAME, "collaboration","sites content/live/acme"};
		String[] DATA2 = {"",""};
		String[] DATA3 = {DATA_ACTION_NAME,"User Action/Content Addition","jcr:system/exo:ecm"};

		//add new category tree
		goToContentAdministration();
		addNewCategoryTree(DATA1, true, false, DATA2,"mary", true, false, false, false, DATA3);

		//create new kofax document
		goToSiteExplorer();
		goToAddNewContent();
		click(ELEMENT_KOFAX_LINK);
		click(ELEMENT_ADD_ITEM);

		//check user can see new category while adding category
		click(ELEMENT_CATEGORY_TREE_BOX);
		waitForElementPresent(ELEMENT_CATEGORY_OPTION);
		info("Category tree is diplaying");
		click(ELEMENT_CLOSE_WINDOW);

		//delete data
		goToContentAdministration();
		deleteCategory(DATA_CATEGORY_TREE_NAME);
		waitForElementNotPresent(ELEMENT_ALERT_VISIBLE);
	}

	/*case05: Check the displaying of Category when do advanced search using category
	 * create new category tree
	 * check can see category when do advanced search using category
	 */
	@Test
	public void test05_CheckDisplayOfCategoryWhenDoAdvancedSearch(){
		String DATA_CATEGORY_TREE_NAME = "ECMS_Admin_ManageCategories_Display_tree_05";
		String DATA_ACTION_NAME = "ECMS_Admin_ManageCategories_Display_action_05";
		By ELEMENT_CATEGORY_OPTION = By.xpath("//select[@id='taxonomyTree']/option[@value='" + DATA_CATEGORY_TREE_NAME + "']");
		String[] DATA1 = {DATA_CATEGORY_TREE_NAME, "collaboration","sites content/live/acme"};
		String[] DATA2 = {"",""};
		String[] DATA3 = {DATA_ACTION_NAME,"User Action/Content Addition","jcr:system/exo:ecm"};

		//add new category tree
		goToContentAdministration();
		addNewCategoryTree(DATA1, true, false, DATA2,"mary", true, false, false, false, DATA3);

		//check user can see category when do advanced search using category
		info("Go to Saved Search/Advanced Search/Constraint Form");
		goToSiteExplorer();
		goToAdvancedSearch();
		waitForElementPresent(By.linkText("Show/Hide Constraint Form"));
		if (getElement(ELEMENT_ADD_CATEGORY_IMG) == null){
			click(By.linkText("Show/Hide Constraint Form"));
		}
		if (waitForAndGetElement(ELEMENT_ADD_CATEGORY_CHECKBOX).isSelected() == false){
			click(ELEMENT_ADD_CATEGORY_CHECKBOX);
		}	
		click(ELEMENT_ADD_CATEGORY_IMG);
		click(ELEMENT_CATEGORY_TREE_BOX);
		waitForElementPresent(ELEMENT_CATEGORY_OPTION);
		info("Category tree is diplaying");

		//delete data
		goToContentAdministration();
		deleteCategory(DATA_CATEGORY_TREE_NAME);
		waitForElementNotPresent(ELEMENT_ALERT_VISIBLE);
	}

	/*case06: Check the displaying of Category when do advanced search using category in case user does not have permission to view Category tree
	 * create new category tree
	 * set permission for user does not have read child category permission
	 * check can not see category when do advanced search using category
	 */
	@Test
	public void test06_CheckDisplayOfCategoryWhenAdvancedSearchInCaseUserNotHavePermission(){
		String DATA_CATEGORY_TREE_NAME = "ECMS_Admin_ManageCategories_Display_tree_06";
		String DATA_ACTION_NAME = "ECMS_Admin_ManageCategories_Display_action_06";
		By ELEMENT_CATEGORY_OPTION = By.xpath("//select[@id='taxonomyTree']/option[@value='" + DATA_CATEGORY_TREE_NAME + "']");
		String[] DATA1 = {DATA_CATEGORY_TREE_NAME, "collaboration","sites content/live/acme"};
		String[] DATA2 = {"",""};
		String[] DATA3 = {DATA_ACTION_NAME,"User Action/Content Addition","jcr:system/exo:ecm"};

		//add new category tree
		goToContentAdministration();
		addNewCategoryTree(DATA1, true, false, DATA2,"mary", true, false, false, false, DATA3);

		//delete permission default
		info("Edit category tree");
		click(By.xpath("//div[@title='" + DATA_CATEGORY_TREE_NAME + "']/../../td/div/img[@class='Edit16x16Icon']"));
		click(ELEMENT_PREVIOUS_BUTTON);
		click(ELEMENT_PREVIOUS_BUTTON);
		deletePermission("*:/platform/administrators",true);
		deletePermission("*:/platform/users",true);
		deletePermission("mary",true);
		deletePermission("any",true);
		deletePermission("*:/platform/web-contributors",true);
		logoutEcms();

		//check can not see category when do advanced search using category
		loginEcms("mary", "gtn");
		goToSiteExplorer();
		info("Go to Saved Search/Advanced Search/Constraint Form");
		goToAdvancedSearch();
		waitForElementPresent(By.linkText("Show/Hide Constraint Form"));
		if (getElement(ELEMENT_ADD_CATEGORY_IMG) == null){
			click(By.linkText("Show/Hide Constraint Form"));
		}
		if (waitForAndGetElement(ELEMENT_ADD_CATEGORY_CHECKBOX).isSelected() == false){
			click(ELEMENT_ADD_CATEGORY_CHECKBOX);
		}		  
		click(ELEMENT_ADD_CATEGORY_IMG);
		click(ELEMENT_CATEGORY_TREE_BOX);
		waitForElementNotPresent(ELEMENT_CATEGORY_OPTION);
		info("Category tree is not diplaying");
		logoutEcms();

		//delete data
		loginEcms(DATA_USER, DATA_PASS);
		goToContentAdministration();
		deleteCategory(DATA_CATEGORY_TREE_NAME);
		waitForElementNotPresent(ELEMENT_ALERT_VISIBLE);
	}

	/*case07: Check the displaying of some Category when do advanced search using category in case user does not have permission to view Category tree
	 * create new category tree and 2 categories to tree
	 * set permission for user does not have read child category
	 * check that user can not see child category when do advanced search using category
	 */
	@Test
	public void test07_CheckDisplayOfSomeCategoryWhenAdvancedSearchInCaseUserNotHavePermission(){
		String DATA_CATEGORY_TREE_NAME = "ECMS_Admin_ManageCategories_Display_tree_07";
		String DATA_ACTION_NAME = "ECMS_Admin_ManageCategories_Display_action_07";
		By ELEMENT_CATEGORY_OPTION = By.xpath("//select[@id='taxonomyTree']/option[@value='" + DATA_CATEGORY_TREE_NAME + "']");
		String DATA_CATEGORY_TREE_CHILD_01 = "ECMS_Admin_ManageCategories_Create_tree_child_01";
		String[] DATA1 = {DATA_CATEGORY_TREE_NAME, "collaboration","sites content/live/acme"};
		String[] DATA2 = {"",""};
		String[] DATA3 = {DATA_ACTION_NAME,"User Action/Content Addition","jcr:system/exo:ecm"};

		//add new category tree
		goToContentAdministration();
		addNewCategoryTree(DATA1, true, false, DATA2,"mary", true, false, false, false, DATA3);

		//create 2 new categories
		click(By.xpath("//div[@title='" + DATA_CATEGORY_TREE_NAME + "']/../../td/div/img[@class='Edit16x16Icon']"));
		addChildCategory(DATA_CATEGORY_TREE_NAME, DATA_CATEGORY_TREE_CHILD_01);
		expandNode(DATA_CATEGORY_TREE_NAME, true);

		//set permission for mary not having read permission in these two categories
		info("Set permission for category");
		click(By.xpath("//div[@title='" + DATA_CATEGORY_TREE_CHILD_01 + "']/../../td/div/img[@title='Permission Management']"));
		click(By.xpath("//div[@id='UITabContent' and @style='display: block;;']//*[@title='*:/platform/administrators']/../../td/div/img[@class='DeleteIcon']"));
		acceptAlert();
		click(By.xpath("//div[@id='UITabContent' and @style='display: block;;']//*[@title='*:/platform/users']/../../td/div/img[@class='DeleteIcon']"));
		acceptAlert();
		click(By.xpath("//div[@id='UITabContent' and @style='display: block;;']//*[@title='mary']/../../td/div/img[@class='DeleteIcon']"));
		acceptAlert();
		click(By.xpath("//div[@id='UITabContent' and @style='display: block;;']//*[@title='any']/../../td/div/img[@class='DeleteIcon']"));
		acceptAlert();
		click(By.xpath("//div[@id='UITabContent' and @style='display: block;;']//*[@title='*:/platform/web-contributors']/../../td/div/img[@class='DeleteIcon']"));
		acceptAlert();

		click(ELEMENT_CLOSE_WINDOW);
		logoutEcms();

		//check can not see category when do advanced search using category
		loginEcms("mary", "gtn");
		goToSiteExplorer();
		info("Go to Saved Search/Advanced Search/Constraint Form");
		goToAdvancedSearch();
		waitForElementPresent(By.linkText("Show/Hide Constraint Form"));
		if (getElement(ELEMENT_ADD_CATEGORY_IMG) == null){
			click(By.linkText("Show/Hide Constraint Form"));
		}
		if (waitForAndGetElement(ELEMENT_ADD_CATEGORY_CHECKBOX).isSelected() == false){
			click(ELEMENT_ADD_CATEGORY_CHECKBOX);
		}		  
		click(ELEMENT_ADD_CATEGORY_IMG);
		click(ELEMENT_CATEGORY_TREE_BOX);
		waitForElementPresent(ELEMENT_CATEGORY_OPTION);
		info("Category tree is displaying");
		select(ELEMENT_CATEGORY_TREE_BOX,DATA_CATEGORY_TREE_NAME);
		waitForElementNotPresent(By.linkText(DATA_CATEGORY_TREE_CHILD_01));
		info("Category is not diplaying");
		logoutEcms();

		//delete data
		loginEcms(DATA_USER, DATA_PASS);
		goToContentAdministration();
		deleteCategory(DATA_CATEGORY_TREE_NAME);
		waitForElementNotPresent(ELEMENT_ALERT_VISIBLE);
	}
}
