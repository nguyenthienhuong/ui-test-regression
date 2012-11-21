package org.exoplatform.selenium.platform.ecms.functional.admin.categoriestags;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ManageAccount.*;
import static org.exoplatform.selenium.platform.ecms.WcmAdmin.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.*;
import org.exoplatform.selenium.platform.ecms.ActionBar;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *@author VuNA
 *@date: 15/10/2012
 */
public class ECMS_Admin_ManageCategories_CategoryCreate extends ActionBar{
	public String DATA_USER = "john";
	public String DATA_PASS = "gtn";

	//Data for these test cases
	//String categoryTreeName = "testCase";
	String categoryName = "category1";
	String categoryWorkspace = "collaboration";
	String nodeHomePath = "sites content/live/acme";
	boolean selectUserOrGroup = true; 
	boolean selectMembership = false; 
	String username = "john";
	String searchOption = "User Name"; 
	String groupID = "platform/administrators"; 
	String membership = "*"; 
	String rightOptions = "Set Property Right/Remove Right";
	String actionName = "test";
	String optionLifeCycle = "User Action/Content Addition"; 
	String nodeTargetPath = "jcr:system/exo:ecm";

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

	/*-- Case No 020 / ID 001
	 *-- Add category in Category tree when put valid data into name field
	 * --*/
	@Test
	public void test01_AddCategoryInCategoryTreeWhenPutValidDataIntoNameField(){
		info("-- Step 1: Create new Category tree --");

		String categoryTreeName = "testCase01";

		goToContentAdministration();

		click(ELEMENT_ADD_CATEGORY_TREE_BUTTON);

		addNewCategoryTree_Step1(categoryTreeName, categoryWorkspace, nodeHomePath);

		next();

		addNewCategoryTree_Step2(false, true, groupID, "*", username, true, false, true, false);

		next();

		addNewCategoryTree_Step3(actionName, optionLifeCycle, nodeTargetPath);

		waitForTextPresent(categoryTreeName);

		info("-- Step 2 & 3: Open form to add category and Add Category--");

		addChildCategory(categoryTreeName, categoryName);

		info("-- Restore original data --");

		close();

		deleteCategory(categoryTreeName);

		info("-- Sign Out --");

		signOut();
	}

	/*-- Case No 025 / ID 006
	 *-- Add permission for Category to user
	 *--*/
	@Test
	public void test06_AddPermissionForCategoryToUser(){
		info("-- Step 1: Create a Category --");

		String categoryTreeName = "testCase06";

		goToContentAdministration();

		click(ELEMENT_ADD_CATEGORY_TREE_BUTTON);
		addNewCategoryTree_Step1(categoryTreeName, categoryWorkspace, nodeHomePath);

		next();

		addNewCategoryTree_Step2(false, false, groupID, "manager", username, true, false, true, false);

		next();

		addNewCategoryTree_Step3(actionName, optionLifeCycle, nodeTargetPath);

		waitForTextPresent(categoryTreeName);

		info("-- Step 2: Open form to add permission --");

		addChildCategory(categoryTreeName, categoryName);

		info("-- Step 3: Add permission for user --");

		click(ELEMENT_PERMISSION_MANAGEMENT_ICON.replace("${categoryName}", categoryName));

		info("-- Step 4 & 5: Select user to add permission and Select permission for user --");	

		setPermissionForUserOnManageCategory(true, "mary", false, groupID, "member", true, true, false, false);

		info("-- Restore original data --");

		close();

		close();

		deleteCategory(categoryTreeName);

		info("-- Sign Out --");

		signOut();		
	}

	/*-- Case No 026 / ID 007
	 *-- Add permission for Category to group (1) 
	 *--*/
	@Test
	public void test07_AddPermissionForCategoryToGroup1(){
		info("-- Step 1: Create a Category --");

		String categoryTreeName = "testCase07";

		goToContentAdministration();

		click(ELEMENT_ADD_CATEGORY_TREE_BUTTON);
		addNewCategoryTree_Step1(categoryTreeName, categoryWorkspace, nodeHomePath);

		next();

		addNewCategoryTree_Step2(true, false, groupID, "manager", username, true, false, true, false);

		next();

		addNewCategoryTree_Step3(actionName, optionLifeCycle, nodeTargetPath);

		waitForTextPresent(categoryTreeName);

		info("-- Step 2: Open form to add permission --");

		addChildCategory(categoryTreeName, categoryName);

		click(ELEMENT_PERMISSION_MANAGEMENT_ICON.replace("${categoryName}", categoryName));

		info("-- Step 3 & 4 & 5: Add permission for group / Select group in form to add permission / Select permission for group --");

		setPermissionForUserOnManageCategory(false, "mary", true, "platform/web-contributors", "manager", true, false, false, true);

		close();

		close();

		info("-- Verify that all the users with added membership can do these actions--");

		signOut();

		driver.get(baseUrl);

		loginEcms("mary", "gtn");

		goToSiteExplorer();

		goToNodeByPath("acme/"+ categoryTreeName +"");

		waitForTextPresent(categoryName);

		deleteDocument(By.linkText(categoryName));

		signOut();

		driver.get(baseUrl);

		loginEcms(DATA_USER, DATA_PASS);

		goToContentAdministration();

		deleteCategory(categoryTreeName);

		info("-- Sign Out --");

		signOut();
	}

	/*-- Case No 027 / ID 008
	 *-- Add permission for Category to group (2) 
	 *--*/
	@Test
	public void test08_AddPermissionForCategoryToGroup2(){
		info("-- Step 1: Create a Category --");

		String categoryTreeName = "testCase08";

		goToContentAdministration();

		click(ELEMENT_ADD_CATEGORY_TREE_BUTTON);
		addNewCategoryTree_Step1(categoryTreeName, categoryWorkspace, nodeHomePath);

		next();

		addNewCategoryTree_Step2(true, false, groupID, "manager", username, true, false, true, false);

		next();

		addNewCategoryTree_Step3(actionName, optionLifeCycle, nodeTargetPath);

		waitForTextPresent(categoryTreeName);

		info("-- Step 2: Open form to add permission --");

		addChildCategory(categoryTreeName, categoryName);

		click(ELEMENT_PERMISSION_MANAGEMENT_ICON.replace("${categoryName}", categoryName));

		info("-- Step 3 & 4 & 5: Add permission for group / Select group in form to add permission / Select permission for group --");

		deletePermission("*:/platform/web-contributors", true);

		setPermissionForUserOnManageCategory(false, "", true, "platform/web-contributors", "*", true, false, false, true);

		close();

		close();

		info("-- Verify that all the users with added membership can do these actions--");

		signOut();

		driver.get(baseUrl);

		loginEcms("james", "gtn");

		goToSiteExplorer();

		goToNodeByPath("acme/"+ categoryTreeName +"");

		waitForTextPresent(categoryName);

		deleteDocument(By.linkText(categoryName));

		signOut();

		driver.get(baseUrl);

		loginEcms(DATA_USER, DATA_PASS);

		goToContentAdministration();

		deleteCategory(categoryTreeName);

		info("-- Sign Out --");

		signOut();
	}

	/*-- Case No 028 / ID 009
	 *-- Add permission for Category to everybody 
	 *--*/
	@Test
	public void test09_AddPermissionForCategoryToEverybody(){
		info("-- Step 1: Create a Category --");

		String categoryTreeName = "testCase09";

		goToContentAdministration();

		click(ELEMENT_ADD_CATEGORY_TREE_BUTTON);
		addNewCategoryTree_Step1(categoryTreeName, categoryWorkspace, nodeHomePath);

		next();

		addNewCategoryTree_Step2(true, false, groupID, "manager", username, true, false, true, false);

		next();

		addNewCategoryTree_Step3(actionName, optionLifeCycle, nodeTargetPath);

		waitForTextPresent(categoryTreeName);

		info("-- Step 2: Open form to add permission --");

		addChildCategory(categoryTreeName, categoryName);

		click(ELEMENT_PERMISSION_MANAGEMENT_ICON.replace("${categoryName}", categoryName));

		info("-- Step 3 & 4 & 5: Add permission for group / Select group in form to add permission / Select permission for group --");

		setPermissionForUserOnManageCategory(false, "", false, "", "", true, true, true, true);

		close();

		close();

		info("-- Verify that all the users with added membership can do these actions--");

		signOut();

		driver.get(baseUrl);

		loginEcms("james", "gtn");

		goToSiteExplorer();

		goToNodeByPath("acme/"+ categoryTreeName +"");

		waitForTextPresent(categoryName);

		signOut();

		driver.get(baseUrl);

		loginEcms(DATA_USER, DATA_PASS);

		goToContentAdministration();

		deleteCategory(categoryTreeName);

		info("-- Sign Out --");

		signOut();
	}

	/*-- Case No 029 / ID 010
	 *-- Edit permission of user/group 
	 *--*/
	@Test
	public void test10_EditPermissionOfUserOrGroup(){
		info("-- Step 1: Create a Category --");

		String categoryTreeName = "testCase10";

		goToContentAdministration();

		click(ELEMENT_ADD_CATEGORY_TREE_BUTTON);
		addNewCategoryTree_Step1(categoryTreeName, categoryWorkspace, nodeHomePath);

		next();

		addNewCategoryTree_Step2(true, false, groupID, "manager", username, true, false, true, false);

		next();

		addNewCategoryTree_Step3(actionName, optionLifeCycle, nodeTargetPath);

		waitForTextPresent(categoryTreeName);

		info("-- Step 2: Open form to add permission --");

		addChildCategory(categoryTreeName, categoryName);

		click(ELEMENT_PERMISSION_MANAGEMENT_ICON.replace("${categoryName}", categoryName));

		setPermissionForUserOnManageCategory(true, "mary", false, "", "", true, false, false, false);

		info("-- Step 3: Edit permission of Category --");

		close();

		click(ELEMENT_PERMISSION_MANAGEMENT_ICON.replace("${categoryName}", categoryName));

		editPermissionUserOrGroup("mary", true, false, true, true);

		//Verify that Permission of Category to user/group is edited

		By verifyEditUser = By.xpath(".//*[@id='PermissionInfo']/table/tbody/tr[8]/td[4]/div[@title='true']");

		waitForElementPresent(verifyEditUser);

		info("-- Restore original data --");

		close();

		close();

		deleteCategory(categoryTreeName);

		info("-- Sign Out --");

		signOut();	
	}

	/*-- Case No 030 / ID 011
	 *-- Edit permission of system 
	 *--*/
	@Test
	public void test11_EditPermissionOfSystem(){
		info("-- Step 1: Create a Category --");

		String categoryTreeName = "testCase11";

		goToContentAdministration();

		click(ELEMENT_ADD_CATEGORY_TREE_BUTTON);
		addNewCategoryTree_Step1(categoryTreeName, categoryWorkspace, nodeHomePath);

		next();

		addNewCategoryTree_Step2(true, false, groupID, "manager", username, true, false, true, false);

		next();

		addNewCategoryTree_Step3(actionName, optionLifeCycle, nodeTargetPath);

		waitForTextPresent(categoryTreeName);

		info("-- Step 2: Open form to add permission --");

		addChildCategory(categoryTreeName, categoryName);

		click(ELEMENT_PERMISSION_MANAGEMENT_ICON.replace("${categoryName}", categoryName));

		setPermissionForUserOnManageCategory(true, "mary", false, "", "", true, false, false, true);

		info("-- Step 3: Edit permission of Category --");

		close();

		click(ELEMENT_PERMISSION_MANAGEMENT_ICON.replace("${categoryName}", categoryName));

		waitForTextPresent("Permission Management");

		editPermissionUserOrGroup("__system", false, false, false, false);

		waitForElementNotPresent(ELEMENT_SELECT_USER_IN_PERMISSION_MANAGEMENT);

		info("-- Restore original data --");

		close();

		close();

		deleteCategory(categoryTreeName);

		info("-- Sign Out --");

		signOut();
	}

	/*-- Case No 031 / ID 012
	 *-- Delete permission of user/group
	 *--*/
	@Test
	public void test12_DeletePermissionOfUserOrGroup(){
		info("-- Step 1: Create a Category --");

		String categoryTreeName = "testCase12";

		goToContentAdministration();

		click(ELEMENT_ADD_CATEGORY_TREE_BUTTON);
		addNewCategoryTree_Step1(categoryTreeName, categoryWorkspace, nodeHomePath);

		next();

		addNewCategoryTree_Step2(true, false, groupID, "manager", username, true, false, true, false);

		next();

		addNewCategoryTree_Step3(actionName, optionLifeCycle, nodeTargetPath);

		waitForTextPresent(categoryTreeName);

		info("-- Step 2: Open form to add permission --");

		addChildCategory(categoryTreeName, categoryName);

		click(ELEMENT_PERMISSION_MANAGEMENT_ICON.replace("${categoryName}", categoryName));

		setPermissionForUserOnManageCategory(true, "demo", false, "", "", true, true, false, false);

		info("-- Step 3: Delete permission of Category --");

		close();

		click(ELEMENT_PERMISSION_MANAGEMENT_ICON.replace("${categoryName}", categoryName));

		deletePermission("demo", true);

		info("-- Restore original data --");

		close();

		close();

		deleteCategory(categoryTreeName);

		info("-- Sign Out --");

		signOut();	
	}

	/*-- Case No 032 / ID 013
	 *-- Delete permission of system
	 *--*/
	@Test
	public void test13_DeletePermissionOfSystem(){
		info("-- Step 1: Create a Category --");

		String categoryTreeName = "testCase13";

		goToContentAdministration();

		click(ELEMENT_ADD_CATEGORY_TREE_BUTTON);
		addNewCategoryTree_Step1(categoryTreeName, categoryWorkspace, nodeHomePath);

		next();

		addNewCategoryTree_Step2(true, false, groupID, "manager", username, true, false, true, false);

		next();

		addNewCategoryTree_Step3(actionName, optionLifeCycle, nodeTargetPath);

		waitForTextPresent(categoryTreeName);

		info("-- Step 2: Open form to add permission --");

		addChildCategory(categoryTreeName, categoryName);

		click(ELEMENT_PERMISSION_MANAGEMENT_ICON.replace("${categoryName}", categoryName));

		setPermissionForUserOnManageCategory(false, "", true, "developers", "*", true, true, false, false);

		info("-- Step 3: Delete permission of system --");

		close();

		click(ELEMENT_PERMISSION_MANAGEMENT_ICON.replace("${categoryName}", categoryName));

		deletePermission("__system", false);

		waitForMessage(MEESAGE_INFO_DELETE_PERMISSION_SYSTEM);

		closeMessageDialog();

		info("-- Restore original data --");

		close();

		close();

		deleteCategory(categoryTreeName);

		info("-- Sign Out --");

		signOut();	
	}

	/*-- Case No 033 / ID 014
	 *-- Delete permission of Category in case there is only one permission of this Category (except system)
	 *-- Status: Pending (test case does not correspond to PLF 3.5.x)
	 *--*/
	/*@Test
	public void test14_DeletePermissionOfCategory(){
		info("-- Step 1: Create a Category --");

		String categoryTreeName = "testCase14";

		goToContentAdministration();

		click(ELEMENT_ADD_CATEGORY_TREE_BUTTON);
		addNewCategoryTree_Step1(categoryTreeName, categoryWorkspace, nodeHomePath);

		next();

		addNewCategoryTree_Step2(true, false, groupID, "manager", username, true, false, true, true);

		next();

		addNewCategoryTree_Step3(actionName, optionLifeCycle, nodeTargetPath);

		waitForTextPresent(categoryTreeName);

		info("-- Step 2: Open form to add permission --");

		addChildCategory(categoryTreeName, categoryName);

		click(ELEMENT_PERMISSION_MANAGEMENT_ICON.replace("${categoryName}", categoryName));

		setPermissionForUserOnManageCategory(false, "", true, "platform/users", "*", true, false, false, true);

		info("-- Step 3: Delete permission of Category --");

		close();

		click(ELEMENT_PERMISSION_MANAGEMENT_ICON.replace("${categoryName}", categoryName));

		String[] userGroupToDeletes = {"*:/platform/administrators", "*:/platform/web-contributors", "any", "john"};

		for (String userGroupToDelete: userGroupToDeletes){
			deletePermission(userGroupToDelete, false);
		}

		close();

		close();

		info("-- Verify that user at step 2 (in the added group) cannot delete permission of this user/group --");

		//signOut();

		//driver.get(baseUrl);

		//loginEcms("james", "gtn");

		goToSiteExplorer();

		goToNodeByPath("acme/"+ categoryTreeName +"/"+ categoryName +"");

		click(By.linkText("System"));

		click(By.linkText("Permissions"));

		waitForTextPresent("Permission Management");

		By verifyRemoveIconNotPresent = By.xpath("//img[@class = 'DeleteIcon' and @title = 'Remove Permission']");

		waitForElementNotPresent(verifyRemoveIconNotPresent);

		//signOut();

		info("-- Restore original data --");

		//driver.get(baseUrl);

		//loginEcms(DATA_USER, DATA_PASS);

		goToContentAdministration();

		deleteCategory(categoryTreeName);

		info("-- Sign Out --");

		signOut();	
	}*/	
}