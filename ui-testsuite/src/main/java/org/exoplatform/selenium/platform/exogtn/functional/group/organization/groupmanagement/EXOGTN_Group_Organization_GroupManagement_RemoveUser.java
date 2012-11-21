package org.exoplatform.selenium.platform.exogtn.functional.group.organization.groupmanagement;

import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.TestLogger.*;
import static org.exoplatform.selenium.platform.ManageAccount.*;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;
import static org.exoplatform.selenium.platform.UserGroupManagement.*;

/**
 *@author HangNTT
 *@date: 24/09/2012
 */
public class EXOGTN_Group_Organization_GroupManagement_RemoveUser extends PlatformBase {
	public String ELEMENT_GROUP_NAME = "platform" ;
	public String ELEMENT_GROUP_LABEL = "Platform" ;
	public String ELEMENT_USER_NAME = "mary";
	public String ELEMENT_MEMBER_SHIP = "manager"; 
	boolean SELECT = true; 
	By VERIFY_USER_AND_ROLE_IN_GROUP = By.xpath("//td/div[@title='mary']/following::td/div[@title='manager']");
	By VERIFY_USER = By.xpath("//td/div[@title='/platform']/following::td/div[@title='manager']");
	
	@BeforeMethod()
	public void beforeTest() throws Exception {
		initSeleniumTest();
		actions = new Actions(driver);
		driver.get(baseUrl);
		driver.manage().window().maximize();
	}

	@Test
	public void test01_RemoveUserFromExistingGroup () {
		info("--login portal--");
		signIn("john", "gtn");

		info("--Go to User and group--");
		//Go to user and group management page
		goToUsersAndGroupsManagement();

		//Choose group tab
		info("--Choose group tab--");
		chooseGroupTab();

		//Add new user into group
		info("--Add user into group--");
		selectGroup("Platform");
		addUsersToGroup(ELEMENT_USER_NAME, ELEMENT_MEMBER_SHIP, SELECT, true);
		waitForElementPresent(VERIFY_USER_AND_ROLE_IN_GROUP);

		//Delete user in current group
		deleteUserInGroup(ELEMENT_GROUP_NAME, ELEMENT_GROUP_LABEL,ELEMENT_USER_NAME);
	}	

	@Test
	public void test03_CheckMembershipOfUserInUserProfileAfterRemovedFromSpecificGroup() {

		By ELEMENT_USER_MEMBERSHIP_TAB = By.xpath("//div[text()='User Membership' and @class='MiddleTab']");
		
		info("--login portal--");
		signIn("john", "gtn");

		info("--Go to User and group--");
		//Go to user and group management page
		goToUsersAndGroupsManagement();

		//Choose group tab
		info("--Choose group tab--");
		chooseGroupTab();

		//Add new user into group
		info("--Add user into group--");
		selectGroup("Platform");
		addUsersToGroup(ELEMENT_USER_NAME, ELEMENT_MEMBER_SHIP, SELECT, true);
		waitForElementPresent(VERIFY_USER_AND_ROLE_IN_GROUP);

		//Check membership of user
		info("Go to User Managemt");
		chooseUserTab();
		//Search new user
		info("--Search user--");
		searchUser(ELEMENT_USER_NAME, "User Name");

		//Edit new user
		info("--Click edit user--");
		editUser(ELEMENT_USER_NAME);
		click(ELEMENT_USER_MEMBERSHIP_TAB);
		waitForElementPresent(VERIFY_USER);
		click(ELEMENT_CANCEL_BUTTON);

		//Back to Group Management
		info("--back to Group management--");
		chooseGroupTab();

		//Delete user into group
		deleteUserInGroup(ELEMENT_GROUP_NAME, ELEMENT_GROUP_LABEL,ELEMENT_USER_NAME);

		//Check after delete user into group
		chooseUserTab();
		//Search new user
		info("--Search user--");
		searchUser(ELEMENT_USER_NAME, "User Name");

		//Edit new user
		info("--Click edit user--");
		editUser(ELEMENT_USER_NAME);
		click(ELEMENT_USER_MEMBERSHIP_TAB);
		waitForElementNotPresent(VERIFY_USER);
		click(ELEMENT_CANCEL_BUTTON);
	}

	@AfterMethod()
	public void afterTest() throws Exception
	{
		signOut();
		driver.quit();
	}
}