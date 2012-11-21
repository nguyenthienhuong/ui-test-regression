package org.exoplatform.selenium.platform.ecms.functional.admin.categoriestags;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ManageAccount.signOut;
import static org.exoplatform.selenium.platform.ecms.WcmAdmin.*;
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
public class ECMS_Admin_ManageCategories_CategoryAction extends ActionBar{
	public String DATA_USER = "john";
	public String DATA_PASS = "gtn";

	//Data for these test cases
	//String categoryTreeName = "testCase";
	String categoryName = "category1";
	String newCategoryName = "category2";
	String categoryWorkspace = "collaboration";
	String nodeHomePath = "sites content/live/acme";
	boolean selectUserOrGroup = true; 
	boolean selectMembership = false; 
	String username = "john";
	String searchOption = "User Name"; 
	String groupID = "platform/administrators"; 
	String membership = "*"; 
	String rightOptions = "Read Right/Add Node Right/Set Property Right/Remove Right";
	String actionName = "test";
	String optionLifeCycle = "User Action/Content Addition"; 
	String nodeTargetPath = "jcr:system/exo:ecm";	
	By ELEMENT_SELECTED_CATEGORY_NAME = By.xpath("//div[@title='"+ categoryName +"']");
	By ELEMENT_SELECTED_CATEGORY_CHILD_NAME = By.xpath("//div[@title='"+ newCategoryName +"']");

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

	/*-- Case No 034 / ID 001
	 *-- Copy/Paste a Category to a Category
	 * --*/
	@Test
	public void test01_CopyPasteACategoryToACategory(){
		info("-- Step 1: Create a Category --");

		String categoryTreeName = "testCase01";

		goToContentAdministration();

		click(ELEMENT_ADD_CATEGORY_TREE_BUTTON);

		addNewCategoryTree_Step1(categoryTreeName, categoryWorkspace, nodeHomePath);

		next();

		addNewCategoryTree_Step2(false, true, groupID, "*", username, true, false, true, false);

		next();

		addNewCategoryTree_Step3(actionName, optionLifeCycle, nodeTargetPath);

		waitForTextPresent(categoryTreeName);

		info("-- Step 2: Copy a Category --");

		addChildCategory(categoryTreeName, categoryName);

		clickUpLevel();

		addChildCategory(categoryTreeName, newCategoryName);

		info("-- Step 3: Paste a copied Category --");

		copyAndPasteCategory(categoryName, newCategoryName);

		info("-- Restore original data --");

		close();

		deleteCategory(categoryTreeName);

		info("-- Sign Out --");

		signOut();	
	}

	/*-- Case No 035 / ID 002
	 *-- Copy a Category and paste into its child node
	 * --*/
	@Test
	public void test02_CopyACategoryAndPasteIntoItsChildNode(){
		info("-- Step 1: Create a Category --");

		String categoryTreeName = "testCase02";

		goToContentAdministration();

		click(ELEMENT_ADD_CATEGORY_TREE_BUTTON);

		addNewCategoryTree_Step1(categoryTreeName, categoryWorkspace, nodeHomePath);

		next();

		addNewCategoryTree_Step2(false, true, groupID, "*", username, true, false, true, false);

		next();

		addNewCategoryTree_Step3(actionName, optionLifeCycle, nodeTargetPath);

		waitForTextPresent(categoryTreeName);

		info("-- Step 2: Copy a Category --");

		addChildCategory(categoryTreeName, categoryName);

		addChildCategory(categoryName, newCategoryName);

		clickUpLevel();

		info("-- Step 3: Paste copied Category into its child node --");

		click(ELEMENT_COPY_CATEGORY_ICON.replace("${categoryName}", categoryName));

		click(ELEMENT_SELECTED_CATEGORY_NAME);

		click(ELEMENT_PASTE_TO_CATEGORY_ICON.replace("${categoryName}", newCategoryName));

		click(ELEMENT_SELECTED_CATEGORY_CHILD_NAME);

		waitForTextPresent(categoryName);

		info("-- Restore original data --");

		close();

		deleteCategory(categoryTreeName);

		info("-- Sign Out --");

		signOut();	
	}

	/*-- Case No 036 / ID 003
	 *-- Copy a Category and paste into itself
	 * --*/
	@Test
	public void test03_CopyACategoryAndPasteIntoItself(){
		info("-- Step 1: Create a Category --");

		String categoryTreeName = "testCase03";

		goToContentAdministration();

		click(ELEMENT_ADD_CATEGORY_TREE_BUTTON);

		addNewCategoryTree_Step1(categoryTreeName, categoryWorkspace, nodeHomePath);

		next();

		addNewCategoryTree_Step2(true, false, "", "", username, true, false, true, true);

		next();

		addNewCategoryTree_Step3(actionName, optionLifeCycle, nodeTargetPath);

		waitForTextPresent(categoryTreeName);

		info("-- Step 2: Copy a Category --");

		addChildCategory(categoryTreeName, categoryName);

		click(ELEMENT_COPY_CATEGORY_ICON.replace("${categoryName}", categoryName));

		info("-- Step 3: Paste copied Category into itself --");

		click(ELEMENT_PASTE_TO_CATEGORY_ICON.replace("${categoryName}", categoryName));

		click(ELEMENT_SELECTED_CATEGORY_NAME);

		waitForTextPresent(categoryName);

		info("-- Restore original data --");

		close();

		deleteCategory(categoryTreeName);

		info("-- Sign Out --");

		signOut();	
	}

	/*-- Case No 037 / ID 004
	 *-- Copy/Paste a deleted Category
	 * --*/
	@Test
	public void test04_CopyPasteADeletedCategory(){
		info("-- Step 1: Create a Category --");

		String categoryTreeName = "testCase04";

		goToContentAdministration();

		click(ELEMENT_ADD_CATEGORY_TREE_BUTTON);

		addNewCategoryTree_Step1(categoryTreeName, categoryWorkspace, nodeHomePath);

		next();

		addNewCategoryTree_Step2(false, true, groupID, "*", username, true, false, true, false);

		next();

		addNewCategoryTree_Step3(actionName, optionLifeCycle, nodeTargetPath);

		waitForTextPresent(categoryTreeName);

		info("-- Step 2: Copy a Category --");

		addChildCategory(categoryTreeName, categoryName);

		clickUpLevel();

		addChildCategory(categoryTreeName, newCategoryName);

		click(ELEMENT_COPY_CATEGORY_ICON.replace("${categoryName}", newCategoryName));

		info("-- Step 3: Delete Category --");

		deleteCategory(newCategoryName);

		info("-- Step 4: Paste deleted Category --"); 

		click(ELEMENT_PASTE_TO_CATEGORY_ICON.replace("${categoryName}", categoryName));

		waitForMessage(MESSAGE_INFO_PASTE_TO_CATEGORY);

		closeMessageDialog();

		info("-- Restore original data --");

		close();

		deleteCategory(categoryTreeName);

		info("-- Sign Out --");

		signOut();	
	}

	/*-- Case No 038 / ID 005
	 *-- Cut a Category and paste into other Category
	 * --*/
	@Test
	public void test05_CutACategoryAndPasteIntoOtherCategory(){
		info("-- Step 1: Create a Category --");

		String categoryTreeName = "testCase05";

		goToContentAdministration();

		click(ELEMENT_ADD_CATEGORY_TREE_BUTTON);

		addNewCategoryTree_Step1(categoryTreeName, categoryWorkspace, nodeHomePath);

		next();

		addNewCategoryTree_Step2(false, true, groupID, "*", username, true, false, true, false);

		next();

		addNewCategoryTree_Step3(actionName, optionLifeCycle, nodeTargetPath);

		waitForTextPresent(categoryTreeName);

		info("-- Step 2: Copy a Category --");

		addChildCategory(categoryTreeName, categoryName);

		clickUpLevel();

		addChildCategory(categoryTreeName, newCategoryName);

		info("-- Step 3: Paste a cut Category --");

		cutAndPasteCategory(categoryName, newCategoryName);

		info("-- Restore original data --");

		close();

		deleteCategory(categoryTreeName);

		info("-- Sign Out --");

		signOut();
	}

	/*-- Case No 039 / ID 006
	 *-- Cut a Category and paste into its child node
	 * --*/
	@Test
	public void test06_CutACategoryAndPasteIntoItsChildNode(){
		info("-- Step 1: Create a Category --");

		String categoryTreeName = "testCase06";

		goToContentAdministration();

		click(ELEMENT_ADD_CATEGORY_TREE_BUTTON);

		addNewCategoryTree_Step1(categoryTreeName, categoryWorkspace, nodeHomePath);

		next();

		addNewCategoryTree_Step2(false, true, groupID, "*", username, true, false, true, false);

		next();

		addNewCategoryTree_Step3(actionName, optionLifeCycle, nodeTargetPath);

		waitForTextPresent(categoryTreeName);

		info("-- Step 2: Cut a Category --");

		addChildCategory(categoryTreeName, categoryName);

		addChildCategory(categoryName, newCategoryName);

		clickUpLevel();

		click(ELEMENT_CUT_CATEGORY_ICON.replace("${categoryName}", categoryName));

		info("-- Step 3: Paste a cut Category into its child node --");

		click(ELEMENT_SELECTED_CATEGORY_NAME);

		click(ELEMENT_PASTE_TO_CATEGORY_ICON.replace("${categoryName}", newCategoryName));

		waitForMessage(MESSAGE_INFO_PASTE_TO_CATEGORY);

		closeMessageDialog();

		info("-- Restore original data --");

		close();

		deleteCategory(categoryTreeName);

		info("-- Sign Out --");

		signOut();	
	}

	/*-- Case No 040 / ID 007
	 *-- Cut a Category and paste into itself
	 * --*/
	@Test
	public void test07_CutACategoryAndPasteIntoItself(){
		info("-- Step 1: Create a Category --");

		String categoryTreeName = "testCase07";

		goToContentAdministration();

		click(ELEMENT_ADD_CATEGORY_TREE_BUTTON);

		addNewCategoryTree_Step1(categoryTreeName, categoryWorkspace, nodeHomePath);

		next();

		addNewCategoryTree_Step2(false, true, groupID, "*", username, true, false, true, false);

		next();

		addNewCategoryTree_Step3(actionName, optionLifeCycle, nodeTargetPath);

		waitForTextPresent(categoryTreeName);

		info("-- Step 2: Cut a Category --");

		addChildCategory(categoryTreeName, categoryName);

		click(ELEMENT_CUT_CATEGORY_ICON.replace("${categoryName}", categoryName));

		info("-- Step 3: Paste cut Category into itself --");

		click(ELEMENT_PASTE_TO_CATEGORY_ICON.replace("${categoryName}", categoryName));

		waitForMessage(MESSAGE_INFO_CUT_TO_CATEGORY.replace("${pathCategory}", "/sites content/live/acme/"+ categoryTreeName +"/"+ categoryName +""));

		closeMessageDialog();

		info("-- Restore original data --");

		close();

		deleteCategory(categoryTreeName);

		info("-- Sign Out --");

		signOut();	
	}

	/*-- Case No 041 / ID 008
	 *-- Cut/Paste a deleted Category
	 * --*/
	@Test
	public void test08_CutPasteADeletedCategory(){
		info("-- Step 1: Create a Category --");

		String categoryTreeName = "testCase08";

		goToContentAdministration();

		click(ELEMENT_ADD_CATEGORY_TREE_BUTTON);

		addNewCategoryTree_Step1(categoryTreeName, categoryWorkspace, nodeHomePath);

		next();

		addNewCategoryTree_Step2(false, true, groupID, "*", username, true, false, true, false);

		next();

		addNewCategoryTree_Step3(actionName, optionLifeCycle, nodeTargetPath);

		waitForTextPresent(categoryTreeName);

		info("-- Step 2: Cut a Category --");

		addChildCategory(categoryTreeName, categoryName);

		clickUpLevel();

		addChildCategory(categoryTreeName, newCategoryName);

		click(ELEMENT_CUT_CATEGORY_ICON.replace("${categoryName}", newCategoryName));

		info("-- Step 3: Delete Category --");

		deleteCategory(newCategoryName);

		info("-- Step 4: Paste deleted Category --");

		click(ELEMENT_PASTE_TO_CATEGORY_ICON.replace("${categoryName}", categoryName));

		waitForMessage(MESSAGE_INFO_PASTE_TO_CATEGORY);

		closeMessageDialog();

		info("-- Restore original data --");

		close();

		deleteCategory(categoryTreeName);

		info("-- Sign Out --");

		signOut();	
	}

	/*-- Case No 042 / ID 009
	 *-- Delete Category
	 * --*/
	@Test
	public void test09_DeleteCategory(){
		info("-- Step 1: Create a Category --");

		String categoryTreeName = "testCase09";

		goToContentAdministration();

		click(ELEMENT_ADD_CATEGORY_TREE_BUTTON);

		addNewCategoryTree_Step1(categoryTreeName, categoryWorkspace, nodeHomePath);

		next();

		addNewCategoryTree_Step2(false, true, groupID, "*", username, true, false, true, false);

		next();

		addNewCategoryTree_Step3(actionName, optionLifeCycle, nodeTargetPath);

		waitForTextPresent(categoryTreeName);

		addChildCategory(categoryTreeName, categoryName);

		info("-- Step 2: Delete a Category --");

		deleteCategory(categoryName);

		info("-- Restore original data --");

		close();

		deleteCategory(categoryTreeName);

		info("-- Sign Out --");

		signOut();	
	}

	/*-- Case No 043 / ID 010
	 *-- Delete a Category when it is added for a document
	 *-- Status: pending*/
	/*@Test
	public void test10_DeleteACategoryWhenItIsAddedForADocument(){
		info("-- Step 1: Create a Category --");	
		info("-- Step 2: Add category for document --");
		info("-- Step 3: Delete Category --");
	}*/
}