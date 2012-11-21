package org.exoplatform.selenium.platform.exogtn.functional.privatemode.group.organization;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;
import static org.exoplatform.selenium.platform.UserGroupManagement.*;
import org.exoplatform.selenium.platform.ManageAccount;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *@author VuNA
 *@date: 26/09/2012
 */
public class EXOGTN_PrivateMode_Group_Organization_Create_NewAccount extends ManageAccount{

	String password = "exoplatform"; 
	String confirmPassword = "exoplatform"; 
	String firstName = "first"; 
	String lastName = "last name"; 
	String email = "testaccount@platform.com"; 
	String userNameGiven = ""; 
	String language = "English"; 
	boolean verify = true;
	String searchOption = "User Name";

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
	 *-- Create new account with valid values  
	 * --*/
	@Test
	public void test01_CreateNewAccountWithValidValues(){
		String username = "valid_value"; 

		signIn("root", "gtn");

		info("-- Step 1: Show register new account form --");
		goToNewStaff();

		info("-- Step 2: Complete adding new account with valid values --");
		addNewUserAccount(username,password,confirmPassword,firstName,
				lastName,email,userNameGiven,language,verify);

		info("-- Step 3: Check registered accounts list after adding new --");
		goToUsersAndGroupsManagement();
		searchUser(username, searchOption);

		info("-- Delete data after testing--");
		deleteUser(username);

		info("-- Sign Out --");
		waitForTextPresent("Root Root");
		signOut();
	}

	/*-- Case ID 003
	 *-- Create new account with user name the same with existing but different by lower/upper case  
	 *-- Case N/A
	 * --*/

	/*-- Case ID 008
	 *-- Create new account with User name the same with existing  
	 * --*/
	@Test
	public void test08_CreateNewAccountWithUserNameTheSameWithExisting(){
		String username = "existing_account"; 
		String messageDuplicateUsername = "This username already exists, please enter another one.";

		signIn("root", "gtn");

		info("-- Step 1: Register the first account --");
		goToNewStaff();
		addNewUserAccount(username,password,confirmPassword,firstName,
				lastName,email,userNameGiven,language,verify);

		info("-- Step 2: Create same User name account --");
		addNewUserAccount(username,password,confirmPassword,firstName,
				lastName,email,userNameGiven,language,false);
		waitForMessage(messageDuplicateUsername);
		closeMessageDialog();

		info("-- Delete data after testing--");
		goToUsersAndGroupsManagement();
		searchUser(username, searchOption);
		deleteUser(username);

		info("-- Sign Out --");
		waitForTextPresent("Root Root");
		signOut();
	}

	/*-- Case ID 012
	 *-- Create new account when copy from Password and paste to Confirm Password field  
	 * --*/
	@Test
	public void test12_CreateNewAccountWhenCopyFromPasswordAndPasteToConfirmPasswordField(){
		String username = "copy_paste_pass"; 
		String messageAlertPassword = "Password and Confirm Password must be the same.";

		signIn("root", "gtn");

		info("-- Step 1: Show register new account form --");
		goToNewStaff();

		info("-- Step 2: Create new User account --");
		type(ELEMENT_INPUT_USERNAME, username, true);
		type(ELEMENT_INPUT_PASSWORD, password, true);
		copyPaste(ELEMENT_INPUT_PASSWORD, password, ELEMENT_INPUT_CONFIRM_PASSWORD);
		type(ELEMENT_INPUT_FIRSTNAME, firstName, true);
		type(ELEMENT_INPUT_LASTNAME, lastName, true);
		type(ELEMENT_INPUT_EMAIL, email, true);
		save();
		waitForMessage(messageAlertPassword);
		closeMessageDialog();

		info("-- Sign Out --");
		waitForTextPresent("Root Root");
		signOut();
	}

	/*-- Case ID 024
	 *-- Create new account with invalid format Email address 
	 * --*/
	@Test
	public void test24_CreateNewAccountWithInvalidFormatEmailAddress(){
		String username = "invalidEmail"; 
		String messageInvalidFormatEmailAddress = "Your email address is invalid. Please enter another one.";

		signIn("root", "gtn");

		info("-- Step 1: Show register new account form --");
		goToNewStaff();

		info("-- Step 2: Create new User account --");
		addNewUserAccount(username,password,confirmPassword,firstName,
				lastName,"email.platform",userNameGiven,language,false);
		waitForMessage(messageInvalidFormatEmailAddress);
		closeMessageDialog();

		info("-- Sign Out --");
		waitForTextPresent("Root Root");
		signOut();
	}

	/*-- Case ID 028
	 *-- Create new account with Email address existing 
	 * --*/
	@Test
	public void test28_CreateNewAccountWithEmailAddressExisting(){
		String username = "existing_email";
		String usernameTest = "new_existing_email";
		String messageDuplicateEmailAddress = "This email already exists, please enter another one.";
		signIn("root", "gtn");

		info("-- Step 1: Show register new account form --");
		goToNewStaff();

		info("-- Step 2: Add new account --");
		addNewUserAccount(username,password,confirmPassword,firstName,
				lastName,email,userNameGiven,language,verify);

		info("-- Step 3: Add new account with Email existing --");
		addNewUserAccount(usernameTest,password,confirmPassword,firstName,
				lastName,email,userNameGiven,language,false);
		waitForMessage(messageDuplicateEmailAddress);
		closeMessageDialog();

		info("-- Delete data after testing--");
		goToUsersAndGroupsManagement();
		searchUser(username, searchOption);
		deleteUser(username);

		info("-- Sign Out --");
		waitForTextPresent("Root Root");
		signOut();
	}
}