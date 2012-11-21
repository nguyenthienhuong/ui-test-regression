package org.exoplatform.selenium.platform.exogtn.functional.portalsettings.language;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;
import static org.exoplatform.selenium.platform.UserGroupManagement.*;

import org.exoplatform.selenium.platform.ManageAccount;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *@author VuNA
 *@date: 26/09/2012
 */
public class EXOGTN_PortalSettings_LanguageSettings_ChangeLanguage extends ManageAccount{
	/*-- Data for test case --*/
	String ELEMENT_CURRENT_PORTAL_CONFIG = ELEMENT_SELECT_EDIT_PORTAL_CONFIG.replace("${portalName}", "intranet");
	String MESSAGE_UPDATE_INFO = "The user profile has been updated.";
	By ELEMENT_USER_SETTING_LANGUAGE = By.xpath(".//*[@id='UITabContent']/div/div/div/div/div/div[2]/a");
	By ELEMENT_USER_SETTING_LANGUAGE_ORIGINAL = By.xpath(".//*[@id='UITabContent']/div/div/div/div/div/div[1]/a");
	By ELEMENT_ACME_SITE = By.linkText("Login to the ACME website");
	By ELEMENT_PORTAL_CHANGE_LANGUAGE = By.xpath(".//*[@id='UIWCMLoginPortlet']/a[2]");
	By ELEMENT_USER_PROFILE_SETTING_LANGUAGE = By.id("user.language");
	By ELEMENT_LINK_EDITOR_INFRENCH = By.linkText("Editer");
	By ELEMENT_LINK_EDITOR_EDIT_LAYOUT_INFRENCH = By.linkText("Mise en page");
	By ELEMENT_APPLY_BUTTON_INFRENCH = By.linkText("Appliquer");
	String MESSAGE_SUCCESSFULLY_REGISTERED_ACCOUNT_INFRENCH = "Votre compte a été créé";

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

	/*-- Case ID 002
	 *-- Change language
	 * --*/
	@Test
	public void test02_ChangeLanguage() {

		signIn("john", "gtn");

		info("-- Step 1: Show form to change language --");
		goToChangeLanguageForUserInterface();
		waitForTextPresent("Interface Language Settings");

		info("-- Step 2: Change language --");
		click(ELEMENT_USER_SETTING_LANGUAGE);
		apply();
		waitForTextNotPresent("My Sites");
		waitForTextPresent("Mes Sites");

		info("-- Step 3: Check displaying language when sign out --");
		signOutInFrench(); 
		/*-- Verify that portal is displayed in language of current using portal not new selected language at step 2 --*/
		captureScreen("portalSettings_languageSettings_changeLanguage_caseID02_step3");

		info("-- Step 4: Check displaying language when sign in again--");
		driver.get(baseUrl);
		signIn("john", "gtn");
		/*-- Verify that portal is displayed in new selected language at step 2 --*/
		waitForTextPresent("Mes Sites");
		captureScreen("portalSettings_languageSettings_changeLanguage_caseID02_step4");

		info("-- Restore Original data values after testing --");
		goToChangeLanguageForUserInterfaceInFrench();
		click(ELEMENT_USER_SETTING_LANGUAGE_ORIGINAL);
		applyInFrench();
		waitForTextPresent("My Sites");

		info("-- Sign Out --");	
		waitForTextPresent("John Smith");
		signOut();	
	}

	/*-- Case ID 003  
	 *-- Check display language of portal in public mode
	 */
	@Test
	public void test03_CheckDisplayLanguageOfPortalInPublicMode(){

		info("-- Step 1: Show portal in public mode --");
		click(ELEMENT_ACME_SITE);

		info("-- Step 2: Show form to change language --");
		click(ELEMENT_PORTAL_CHANGE_LANGUAGE);

		info("-- Step 3: Change language --");
		click(ELEMENT_USER_SETTING_LANGUAGE);
		apply();
		waitForTextNotPresent("Overview");
		waitForTextPresent("Présentation");

		info("-- Restore Original data values after testing --");
		click(ELEMENT_PORTAL_CHANGE_LANGUAGE);
		click(ELEMENT_USER_SETTING_LANGUAGE_ORIGINAL);
		applyInFrench();
		waitForTextPresent("Overview");
	}

	/*-- Case ID 004  
	 *-- Check display language of portal in private mode with demo account 
	 *-- 
	 *--*/
	@Test
	public void test04_CheckDisplayLanguageOfPortalInPrivateModeWithDemoAccount(){
		info("-- Step 1: Check display after login to portal --");
		signIn("root", "gtn");
		waitForTextPresent("My Sites");
		captureScreen("portalSettings_languageSettings_checkDisplayLanguage_caseID04_step1");
		signOut();
		deleteCookieTest(driver);
		driver.close();

		info("-- Step 2: Check displaying language when language of browser don't support by portal with user account demo --");
		// choose a browser's language that portal doesn't support this language: e.g, Vietnamese
		WebDriver driverTest = initNewDriverSeleniumTest(Language.vi);
		actions = new Actions(driverTest);
		driverTest.get(baseUrl);
		signIn("root", "gtn");
		waitForTextPresent("My Sites");
		captureScreen("portalSettings_languageSettings_checkDisplayLanguage_caseID04_step2");

		info("-- Step 3: Check when change language--");
		goToChangeLanguageForUserInterface();
		waitForTextPresent("Interface Language Settings");
		click(ELEMENT_USER_SETTING_LANGUAGE);
		apply();
		waitForTextNotPresent("My Sites");
		waitForTextPresent("Mes Sites");
		signOutInFrench(); 
		driverTest.close();

		info("-- Restore Original data values after testing --");
		//set a browser's language to default: English
		WebDriver driverOriginal = initNewDriverSeleniumTest(Language.en);
		actions = new Actions(driverOriginal);
		driverOriginal.get(baseUrl);
		signIn("root", "gtn");
		goToChangeLanguageForUserInterfaceInFrench();
		click(ELEMENT_USER_SETTING_LANGUAGE_ORIGINAL);
		applyInFrench();
		waitForTextPresent("My Sites");

		info("-- Sign Out --");	
		waitForTextPresent("Root Root");
		signOut();	
	}

	/*-- Case ID 005
	 *-- Check display language of portal in private mode with new account 
	 *-- 
	 *--*/
	@Test
	public void test05_CheckDisplayLanguageOfPortalInPrivateModeWithNewAccount(){
		String username = "useratexoplatformeu"; 
		String password = "exoplatform"; 
		String confirmPassword = "exoplatform"; 
		String firstName = "first"; 
		String lastName = "last name"; 
		String email = "testaccount@platform.com"; 
		String userNameGiven = ""; 
		String language = "English"; 
		boolean verify = true;

		info("-- Step 1: Register new account --");
		signIn("root", "gtn");
		goToNewStaff();
		addNewUserAccount(username,password,confirmPassword,firstName,
				lastName,email,userNameGiven,language,verify);
		signOut();

		info("-- Step 2: Check display after login to portal --");
		driver.get(baseUrl);
		signIn(username, password);
		captureScreen("portalSettings_languageSettings_checkDisplayLanguage_caseID05_step2");
		signOut();
		deleteCookieTest(driver);
		driver.close();

		info("-- Step 3: Check displaying language when language of browser don't support by portal with new account --");
		WebDriver driverTest = initNewDriverSeleniumTest(Language.vi);
		actions = new Actions(driverTest);
		driverTest.get(baseUrl);
		signIn(username, password);
		captureScreen("portalSettings_languageSettings_checkDisplayLanguage_caseID05_step3");

		info("-- Step 4: Check when change language --");
		goToChangeLanguageForUserInterface();
		waitForTextPresent("Interface Language Settings");
		click(ELEMENT_USER_SETTING_LANGUAGE);
		apply();
		waitForTextNotPresent("My Sites");
		waitForTextPresent("Mes Sites");
		captureScreen("portalSettings_languageSettings_checkDisplayLanguage_caseID05_step4");
		goToChangeLanguageForUserInterfaceInFrench();
		click(ELEMENT_USER_SETTING_LANGUAGE_ORIGINAL);
		applyInFrench();
		waitForTextPresent("My Sites");
		signOut(); 
		driverTest.close();

		info("-- Restore Original data values after testing --");
		//set a browser's language to default: English
		WebDriver driverOriginal = initNewDriverSeleniumTest(Language.en);
		actions = new Actions(driverOriginal);
		driverOriginal.get(baseUrl);
		signIn("root", "gtn");
		//delete user demo
		goToUsersAndGroupsManagement();
		deleteUser(username);

		info("-- Sign Out --");	
		waitForTextPresent("Root Root");
		signOut();	
	}

	/*-- Case ID 006
	 *-- Check display language of portal after change language in Organization portlet 
	 *--*/
	@Test
	public void test06_CheckDisplayLanguageOfPortalAfterChangeLanguageInOrganizationPortlet(){
		signIn("root", "gtn");

		info("-- Step 1: Show form to edit a specific user --");
		goToUsersAndGroupsManagement();
		editUser("demo");
		waitForTextPresent("User Name");
		click(ELEMENT_USER_PROFILE_TAB);

		info("-- Step 2: Change displaying language for user --");
		waitForTextPresent("Language");
		select(ELEMENT_USER_PROFILE_SETTING_LANGUAGE, "French");
		save();
		waitForMessage(MESSAGE_UPDATE_INFO);
		closeMessageDialog();
		signOut();

		info("-- Step 3: Check when change language --");
		//Sign In portal by account which edited above
		driver.get(baseUrl);
		signIn("demo", "gtn");
		waitForTextNotPresent("My Sites");
		waitForTextPresent("Mes Sites");
		signOutInFrench();

		info("-- Restore Original data values after testing --");
		driver.get(baseUrl);
		signIn("root", "gtn");
		goToUsersAndGroupsManagement();
		editUser("demo");
		waitForTextPresent("User Name");
		click(ELEMENT_USER_PROFILE_TAB);
		waitForTextPresent("Language");
		select(ELEMENT_USER_PROFILE_SETTING_LANGUAGE, "English");
		save();
		waitForMessage(MESSAGE_UPDATE_INFO);
		closeMessageDialog();

		info("-- Sign Out --");
		waitForTextPresent("Root Root");
		signOut();	
	}

	/*-- Case ID 007
	 *-- Check display language of portal with new account is created in public mode
	 *-- 
	 *--*/
	@Test
	public void test07_CheckDisplayLanguageOfPortalWithNewAccountIsCreatedInPublicMode(){
		/*-- Prepare data for test case
	    --   Disable use captcha
		--*/
		String username = "useratexoplatformvn";
		String password = "exoplatform"; 
		String confirmPassword = "exoplatform"; 
		String firstName = "Exo";
		String lastName = "Sea";
		String email = "exosea@platform.com";

		signIn("root", "gtn");

		goToRegisterPageInPublicMode(driver);
		goToEditPageEditor();
		setUseCaptcha(false, false);
		signOut();
		deleteCookieTest(driver);
		driver.close();

		// choose a browser's language: French
		WebDriver driverTest = initNewDriverSeleniumTest(Language.fr);
		actions = new Actions(driverTest);

		//Start a test case
		info("-- Step 1: Create new account in public mode --");
		click(ELEMENT_GO_TO_PORTAL);
		click(ELEMENT_REGISTER_LINK);

		info("-- Step 2: Complete adding new account with valid values --");
		addNewUserAccountInPublicMode(username, password, confirmPassword, firstName, lastName, email, false);
		waitForMessage(MESSAGE_SUCCESSFULLY_REGISTERED_ACCOUNT_INFRENCH);
		closeMessageDialog();

		info("-- Step 3: Check display after login to portal --");
		driverTest.get(baseUrl);
		signIn(username,password);
		waitForTextPresent("Mes Sites");
		captureScreen("portalSettings_languageSettings_checkDisplayLanguage_caseID07_step3");
		signOutInFrench();

		info("-- Restore Original data values after testing --");
		driverTest.get(baseUrl);
		signIn("root", "gtn");
		goToRegisterPageInPublicMode(driverTest);
		goToEditPageEditor();
		setUseCaptcha(true, false);
		deleteCookieTest(driverTest);
		driverTest.close();

		//set a browser's language to default: English
		WebDriver driverOriginal = initNewDriverSeleniumTest(Language.en);
		actions = new Actions(driverOriginal);

		//Delete user demo 
		signIn("root", "gtn");
		goToUsersAndGroupsManagement();
		deleteUser(username);

		//Finish a test case
		info("-- Sign Out--");
		waitForTextPresent("Root Root");
		signOut();
	}

	/*----- Auxiliary functions -----*/
	public void signOutInFrench(){
		mouseOver(By.id("UserNavigationTabsContainer"), true);
		mouseOverAndClick(By.linkText("Se Déconnecter"));	
		pause(500);
	}

	public void goToChangeLanguageForUserInterfaceInFrench(){
		mouseOver(By.id("UserNavigationTabsContainer"), true);
		mouseOverAndClick(By.linkText("Changer de Langue"));	
		pause(500);
	}

	//Go to edit page layout in french
	/*public void goToEditPageLayoutInFrench(){
		info("--Go to edit page layout--");
		waitForElementPresent(ELEMENT_LINK_EDITOR_INFRENCH);
		mouseOver(ELEMENT_LINK_EDITOR_INFRENCH, true);
		pause(500);
		mouseOver(ELEMENT_LINK_EDITOR_PAGE, true);
		pause(500);
		mouseOverAndClick(ELEMENT_LINK_EDITOR_EDIT_LAYOUT_INFRENCH);
		pause(500);
	}*/

	public void applyInFrench(){
		waitForAndGetElement(ELEMENT_APPLY_BUTTON_INFRENCH);
		click(ELEMENT_APPLY_BUTTON_INFRENCH);
	}	

	public static enum Language{
		en, fr, vi;
	}

	public static WebDriver getDriver(Language language){
		String locale = language.toString();
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("intl.accept_languages", locale);
		driver = new FirefoxDriver(profile);
		return driver;
	}

	public static WebDriver initNewDriverSeleniumTest(Language language){
		driver = getDriver(language);
		driver.get(baseUrl);
		driver.manage().window().maximize();
		return driver;
	}

	public static void deleteCookieTest(WebDriver driverTest){
		driverTest.manage().deleteCookieNamed("LR_COOKIE_SESSION_START");
		driverTest.manage().deleteCookieNamed("JSESSIONIDSSO");
		driverTest.manage().deleteCookieNamed("JSESSIONID");
	}
}