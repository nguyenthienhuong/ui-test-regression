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
public class GroupTest extends PlatformBase{
	
	String groupName = "testgroup"	  ; 
	String groupLabel= ""			  ; 
	String groupDesc = "group example"; 
	boolean verify   = true			  ;
	
	String userName  = "demo";
	String memberShip= "member"; 
	boolean select   =  true; 
	
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
	
	@Test(groups={"platform", "add new group"})
	public void testAddNewGroup(){
		System.out.println("--Create a new group--");
		signIn("root", "gtn");
		pause(1000);
		goToUsersAndGroupsManagement();
		pause(1000);
		chooseGroupTab();
		pause(1000);
		addGroup(groupName, groupLabel, groupDesc,verify);
		signOut();
		pause(1000);
		
	}
	
	@Test(groups={"platform", "add new group"})
	public void testSelectAndEditGroup(){
		System.out.println("--Edit a new group--");
		signIn("root", "gtn");
		pause(1000);
		goToUsersAndGroupsManagement();
		pause(1000);
		chooseGroupTab();
		pause(1000);
		selectGroup(groupName);
		editGroup(groupName, true);
		type(ELEMENT_INPUT_LABEL, "", true);
		type(ELEMENT_TEXTAREA_DESCRIPTION, "edit a group description", true);
		save();
		pause(1000);
		signOut();
		pause(1000);
	}
	
	@Test(groups={"platform", "add new group"})
	public void testAddUserIntoGroup(){
		System.out.println("--Add user to a group--");
		signIn("root", "gtn");
		pause(1000);
		goToUsersAndGroupsManagement();
		pause(1000);
		chooseGroupTab();
		pause(1000);
		selectGroup(groupName);
		addUsersToGroup(userName, memberShip, select, verify);
		pause(1000);
		signOut();
		pause(1000);
		
	}
	
	
	@Test(groups={"platform", "add new group"})
	public void testDeleteGroup(){
		System.out.println("--Delete a new group--");
		signIn("root", "gtn");
		pause(1000);
		goToUsersAndGroupsManagement();
		pause(1000);
		chooseGroupTab();
		pause(1000);
		selectGroup(groupName);
		deleteGroup(groupName, true);
		pause(3000);
		signOut();
		pause(1000);
		
		
	} 
	

}
