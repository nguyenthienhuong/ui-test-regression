package org.exoplatform.selenium.platform;

import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.platform.ManageAccount.*;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;
import static org.exoplatform.selenium.platform.UserGroupManagement.*;


public class AccountTest extends PlatformBase{
	String username      = "testaccountexosea" ; 
	String password		 = "testaccount"	; 
	String confirmPassword = "testaccount"	; 
	String firstName     = "first"			; 
    String lastName      = "last name"		; 
    String email         = "exotestaccount@exo.com"; 
    String userNameGiven = ""		; 
    String language      = "English"; 
    boolean verify       = true     ;
    
    String searchOption = "User Name";
	
	@BeforeTest(groups = {"platform"})
	public void beforeTest() throws Exception {
		driver = new FirefoxDriver();
    	actions = new Actions(driver);
    	baseUrl = "http://localhost:8080";
    	driver.get(baseUrl + "/portal/intranet/");
    	pause(3000);
		
	}

	@AfterTest(groups = {"platform"})
	public void afterTest() throws Exception {
		driver.quit();
	}
	
	@Test(groups={"paltform", "AccountTest"})
	public void testAccountAddNewUser(){
		System.out.println("--Create new user account--");
        signIn("root", "gtn");
		pause(2000);
		goToNewStaff();
		pause(2000);
		addNewUserAccount(username,password,confirmPassword,firstName,
				lastName,email,userNameGiven,language,verify);
		pause(3000);
		signOut()  ;
		pause(1000);
		
	}
	
	@Test(groups={"paltform", "AccountTest"})
	public void testSearchUser(){
		System.out.println("--Search user --");
		signIn("root", "gtn");
		pause(1000);
		goToUsersAndGroupsManagement();
		pause(1000);
		searchUser(username, searchOption);
		pause(1000);	
		signOut()  ;
		pause(1000);
	}
	
	@Test(groups={"paltform", "AccountTest"})
	public void testEditUserEmail(){
		System.out.println("--Edit a user email--");
		signIn("root", "gtn");
		pause(1000);
		goToUsersAndGroupsManagement();
		editUser(username);
		type(ELEMENT_INPUT_EMAIL, "exotestaccountedit@localhost.com", true);
	    save();
	    waitForMessage("The user profile has been updated.");
	    waitForTextPresent("exotestaccountedit@localhost.com");
	    signOut()  ;
		pause(1000);
		
	}
	
	@Test(groups={"paltform", "AccountTest"})
	public void testDeleteUser(){
		System.out.println("--Delete a user account--");
		signIn("root", "gtn");
		pause(1000);
		goToUsersAndGroupsManagement();
		pause(1000);
		deleteUser(username);
		pause(1000);
		signOut()  ;
		pause(1000);
	}
	
}
