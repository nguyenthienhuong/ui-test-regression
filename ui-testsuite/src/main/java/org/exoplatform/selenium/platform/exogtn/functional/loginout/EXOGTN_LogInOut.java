package org.exoplatform.selenium.platform.exogtn.functional.loginout;

import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ManageAccount.*;

public class EXOGTN_LogInOut extends PlatformBase{
	
	public String MESSAGE_FAILED = "Sign in failed. Wrong username or password.";
	
	@BeforeMethod
	public void setUpBeforeTest() throws Exception {
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		driver.manage().window().maximize();
	}
	
	@AfterMethod
	public void afterTest() throws Exception {
		driver.quit();		
	}
	
	/*-- tests cases of : Portal\Login_out\Sign In --*/	
	//Check displaying of portal after signing in successfully by admin account
	@Test
	public void test02_SignInByAdminAccount(){
			info("--Login by Admin account--");
			signIn("root", "gtn");
			waitForTextPresent("Root Root");
			signOut();		
	}
	
	//Check displaying portal after signing in successfully by normal account
	@Test
	public void test03_SignInByNormalAccount(){
		info("--Login by Normal account--");
		
		//Sign in as Jack Miller
		signIn("demo", "gtn");
		waitForTextPresent("Jack Miller");
		waitForTextNotPresent("Edit");
		signOut();
		
		//Sign in as Mary
		driver.get(baseUrl);
		signIn("mary", "gtn");
		waitForTextPresent("Mary Williams");
		waitForTextNotPresent("Edit");
		signOut();	
	}
	
	//Sign in with blank User name/Password
	@Test
	public void test04_SignInWithBlankUserNameAndPassword(){
		info("--Sign in with blank password");
		signIn("root","");
		waitForTextPresent(MESSAGE_FAILED);
		driver.get(baseUrl);
		
		info("--Sign in with blank username");
		signIn("","gtn");
		waitForTextPresent(MESSAGE_FAILED);	
	}
	
	//Sign in by unregistered User
	@Test
	public void test05_SignInByUnRegisteredUsername(){
		info("--Sign in with unregistered account--");
		signIn("exogtn", "gtn");
		waitForTextPresent(MESSAGE_FAILED);	
	}
}