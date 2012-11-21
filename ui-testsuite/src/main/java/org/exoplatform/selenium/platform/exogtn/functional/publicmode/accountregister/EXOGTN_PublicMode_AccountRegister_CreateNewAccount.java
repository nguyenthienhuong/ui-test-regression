package org.exoplatform.selenium.platform.exogtn.functional.publicmode.accountregister;

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
 *@date: 01/10/2012
 */
public class EXOGTN_PublicMode_AccountRegister_CreateNewAccount extends ManageAccount {

	String password = "exoplatform"; 
	String confirmPassword = "exoplatform"; 
	String firstName = "Exo";
	String lastName = "Sea";
	String email = "exosea@platform.com";
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

		String username = "userexocase01";

		// Disable use captcha to run automated test case
		signIn("root", "gtn");
		goToRegisterPageInPublicMode(driver);
		goToEditPageEditor();
		setUseCaptcha(false, false);
		signOut();

		//Start a test case
		info("-- Step 1: Show Account register form --");
		click(ELEMENT_REGISTER_ACCOUNT_LINK);
		waitForTextPresent("Create a New Account");

		info("-- Step 2: Complete adding new account with valid values --");
		addNewUserAccountInPublicMode(username, password, confirmPassword, firstName, lastName, email, true);

		info("-- Step 3: Check registered accounts list after adding new --");
		driver.get(baseUrl);
		signIn("root", "gtn");
		goToUsersAndGroupsManagement();
		searchUser(username, searchOption);

		info("-- Restore Original data values after testing --");
		deleteUser(username);
		goToRegisterPageInPublicMode(driver);
		goToEditPageEditor();
		setUseCaptcha(true, false);

		info("-- Sign Out --");
		waitForTextPresent("Root Root");
		signOut();
	}

	/*-- Case ID 003
	 *-- Create new account with user name the same with existing but different by lower/upper case
	 *-- Case N/A
	 * */

	/*-- Case ID 008
	 *-- Create new account with User name the same with existing
	 * */
	@Test
	public void test08_CreateNewAccountWithUserNameTheSameWithExisting(){
		String username = "userexocase02";

		// Disable use captcha to run automated test case
		signIn("root", "gtn");
		goToRegisterPageInPublicMode(driver);
		goToEditPageEditor();
		setUseCaptcha(false, false);
		signOut();

		info("-- Step 1: Create the first account --");
		click(ELEMENT_REGISTER_ACCOUNT_LINK);
		waitForTextPresent("Create a New Account");
		addNewUserAccountInPublicMode(username, password, confirmPassword, firstName, lastName, email, true);

		info("-- Step 2: Create same User name account --");
		addNewUserAccountInPublicMode(username, password, confirmPassword, firstName, lastName, "email@exo.com", false);
		waitForMessage(MESSAGE_DUPLICATE_ACCOUNT);
		closeMessageDialog();

		info("-- Restore Original data values after testing --");
		driver.get(baseUrl);
		signIn("root", "gtn");
		goToUsersAndGroupsManagement();
		deleteUser(username);
		goToRegisterPageInPublicMode(driver);
		goToEditPageEditor();
		setUseCaptcha(true, false);

		info("-- Sign Out --");
		waitForTextPresent("Root Root");
		signOut();
	}

	/*-- Case ID 012
	 *-- Create new account when copy from Password and paste to Confirm Password field.
	 * */
	@Test
	public void test12_CreateNewAccountWhenCopyFromPasswordAndPasteToConfirmPasswordField(){

		String username = "userexocase12";

		signIn("root", "gtn");

		info("-- Step 1: Show register new account form --");
		goToRegisterPageInPublicMode(driver);
		goToEditPageEditor();
		// Disable use captcha to run automated test case
		setUseCaptcha(false, false);

		info("-- Step 2: Create new account --");
		type(ELEMENT_INPUT_USERNAME, username, true);
		type(ELEMENT_INPUT_PASSWORD, password, true);
		copyPaste(ELEMENT_INPUT_PASSWORD, password, ELEMENT_INPUT_CONFIRM_PASSWORD_PUBLIC_MODE);
		type(ELEMENT_INPUT_FIRSTNAME, firstName, true);
		type(ELEMENT_INPUT_LASTNAME, lastName, true);
		type(ELEMENT_INPUT_EMAIL_PUBLIC_MODE, email, true);
		click(ELEMENT_SUBSCRIBE_BUTTON);
		waitForMessage(MESSAGE_ALERT_PASSWORD);
		closeMessageDialog();
		click(ELEMENT_RESET_BUTTON);

		info("-- Restore Original data values after testing --");
		goToEditPageEditor();
		setUseCaptcha(true, false);

		info("-- Sign Out --");
		waitForTextPresent("Root Root");
		signOut();
	}

	/*-- Case ID 024
	 *-- Create new account with invalid format Email address 
	 */
	@Test
	public void test24_CreateNewAccountWithInvalidFormatEmailAddress(){
		String username = "userexocase24";

		signIn("root", "gtn");

		info("-- Step 1: Show register new account form --");
		goToRegisterPageInPublicMode(driver);
		goToEditPageEditor();
		// Disable use captcha to run automated test case
		setUseCaptcha(false, false);

		info("-- Add new account with invalid format Email address --");
		addNewUserAccountInPublicMode(username, password, confirmPassword, firstName, lastName, "exo.com", false);
		waitForMessage(MESSAGE_INVALID_EMAIL_ADDRESS);
		closeMessageDialog();
		click(ELEMENT_RESET_BUTTON);

		info("-- Restore Original data values after testing --");
		goToEditPageEditor();
		setUseCaptcha(true, false);

		info("-- Sign Out --");
		waitForTextPresent("Root Root");
		signOut();
	}
}