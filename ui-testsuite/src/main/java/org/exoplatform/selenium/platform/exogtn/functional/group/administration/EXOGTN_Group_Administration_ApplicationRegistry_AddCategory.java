package org.exoplatform.selenium.platform.exogtn.functional.group.administration;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;
import static org.exoplatform.selenium.platform.ManageApplications.*;
import org.exoplatform.selenium.platform.ManageAccount;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EXOGTN_Group_Administration_ApplicationRegistry_AddCategory extends ManageAccount{

	String categoryName = "testNewCategory";
	String displayName = "testNewCategory";
	String categoryDescription = "adding a new category";
	String messageDuplicateCategory = "This category already exists, please enter another category name.";
	boolean verify = true;

	@BeforeMethod
	public void setUpBeforeTest() throws Exception {
		initSeleniumTest();
		actions = new Actions(driver);
		driver.get(baseUrl);
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void afterTest() throws Exception {
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/*-- Case ID 001
	 *-- Add new category with valid value  
	 * --*/
	@Test
	public void test01_AddNewCategoryWithValidValue(){
		boolean publicMode = false;
		Map<String, String> permissions = new HashMap<String, String>();
		permissions.put("Platform/Administration", "*");

		signIn("root", "gtn");

		info("-- Step 1: Show Application Registry form --");
		goToApplicationRegistry();
		waitForTextPresent("Categories");

		info("-- Step 2 & 3: Show Add new category form and Add new category with valid value -- ");
		addNewCategoryAtManageApplications(categoryName, displayName, categoryDescription, publicMode, permissions, verify);
		
		info("-- Delete data after testing --");
		deleteCategoryAtManageApplications(categoryName, verify);

		info("-- Sign Out --");
		waitForTextPresent("Root Root");
		signOut();
	}

	/*-- Case ID 007
	 *-- Add same name categories 
	 * --*/
	@Test
	public void test07_AddSameNameCategories(){
		boolean publicMode = false;
		Map<String, String> permissions = new HashMap<String, String>();
		permissions.put("Platform/Administration", "member");

		signIn("root", "gtn");

		info("-- Step 1: Add the first category --");
		goToApplicationRegistry();
		waitForTextPresent("Categories");
		addNewCategoryAtManageApplications(categoryName, displayName, categoryDescription, publicMode, permissions, verify);

		info("-- Step 2: Add same name category --");
		addNewCategoryAtManageApplications(categoryName, displayName, categoryDescription, true, null, false);
		waitForMessage(messageDuplicateCategory);
		closeMessageDialog();
		
		info("-- Delete data after testing --");
		deleteCategoryAtManageApplications(categoryName, verify);
		
		info("-- Sign Out --");
		waitForTextPresent("Root Root");
		signOut();
	}
	
	/*-- Case ID 008
	 *-- Add new category with category name the same with existing but different by lower/upper case
	 * --*/
	@Test
	public void test08_AddNewCategoryWithCategoryNameTheSameWithExistingButDifferentByLowerUpperCase(){
		boolean publicMode = false;
		Map<String, String> permissions = new HashMap<String, String>();
		permissions.put("Platform/Administration", "member");

		signIn("root", "gtn");

		info("-- Step 1: Show Application Registry form --");
		goToApplicationRegistry();
		waitForTextPresent("Categories");
		
		info("-- Step 2 & 3: Show Add new category form and Add the first category with valid value -- ");
		addNewCategoryAtManageApplications(categoryName, displayName, categoryDescription, publicMode, permissions, verify);
		
		info("-- Step 4: Create new same Category name --");
		addNewCategoryAtManageApplications("TESTNEWCATEGORY", "TESTNEWCATEGORY", categoryDescription, true, null, verify);
		
		info("-- Delete data after testing --");
		selectCategoryAtManageApplications(categoryName);
		deleteCategoryAtManageApplications(categoryName, verify);
		selectCategoryAtManageApplications("TESTNEWCATEGORY");
		deleteCategoryAtManageApplications("TESTNEWCATEGORY", verify);
		
		info("-- Sign Out --");
		waitForTextPresent("Root Root");
		signOut();
	}
	
	/*-- Case ID 014
	 *-- Add public category in Application registry
	 * --*/
	@Test
	public void test014_AddPublicCategoryInApplicationRegistry(){
		boolean publicMode = true;
		Map<String, String> permissions = new HashMap<String, String>();
		permissions.put("Platform/Administration", "member");

		signIn("root", "gtn");

		info("-- Step 1: Show Application Registry form --");
		goToApplicationRegistry();
		waitForTextPresent("Categories");
		
		info("-- Step 2 & 3 & 4: Show Add new category form and Check when choose public option for new category and Save -- ");
		addNewCategoryAtManageApplications(categoryName, displayName, categoryDescription, publicMode, null, verify);
		
		info("-- Delete data after testing --");
		deleteCategoryAtManageApplications(categoryName, verify);
		
		info("-- Sign Out --");
		waitForTextPresent("Root Root");
		signOut();
	}
	
	/*-- Case ID 015
	 *-- Add private category in Application registry
	 * --*/
	@Test
	public void test015_AddPrivateCategoryInApplicationRegistry(){
		boolean publicMode = false;
		Map<String, String> permissions = new HashMap<String, String>();
		permissions.put("Platform/Administration", "member");

		signIn("root", "gtn");

		info("-- Step 1: Show Application Registry form --");
		goToApplicationRegistry();
		waitForTextPresent("Categories");
		
		info("-- Step 2 & 3: Show Add new category form and Check form to select group/membership for permission -- ");
		info("-- Step 4 & 5: Check after select group/membership to assign right on new category and Save --");
		addNewCategoryAtManageApplications(categoryName, displayName, categoryDescription, publicMode, permissions, verify);
	
		info("-- Delete data after testing --");
		deleteCategoryAtManageApplications(categoryName, verify);
		
		info("-- Sign Out --");
		waitForTextPresent("Root Root");
		signOut();
	}	
}