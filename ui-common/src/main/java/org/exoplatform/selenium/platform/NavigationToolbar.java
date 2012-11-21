package org.exoplatform.selenium.platform;

import static org.exoplatform.selenium.TestLogger.info;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NavigationToolbar extends PlatformBase {
	
	public static By ELEMENT_APPLICATIONS_LINK = By.linkText("Applications");
	public static By ELEMENT_EDIT_LINK = By.linkText("Edit");
	public static By ELEMENT_PAGE_LINK = By.linkText("Page");
	public static By ELEMENT_EDIT_LAYOUT = By.linkText("Layout");

	//Go to portal sites
	public static void goToPortalSites() {
		info("--Go to Portal Site Management--");
		waitForAndGetElement(By.xpath(ELEMENT_LINK_SETUP));
		mouseOver(ELEMENT_LINK_SETUP, false);
		pause(500);
		mouseOver(ELEMENT_LINK_PORTAL, false);
		pause(500);
		WebElement element;
		element = waitForAndGetElement(ELEMENT_LINK_SITE);
		actions.moveToElement(element).click(element).build().perform();
		pause(500);
	}

	//Go to Portal Manage Pages	
	public static void goToManagePages() {
		info("--Go to Portal Site Management--");
		waitForAndGetElement(By.xpath(ELEMENT_LINK_SETUP));
		mouseOver(ELEMENT_LINK_SETUP, false);
		pause(500);
		mouseOver(ELEMENT_LINK_PORTAL, false);
		pause(500);
		WebElement element;
		element = waitForAndGetElement(ELEMENT_LINK_PAGES);
		actions.moveToElement(element).click(element).build().perform();
		pause(500);
	}

	//Go to Dashboard
	public static void goToDashboard(){
		info("--Go to Dashboard page--");
		WebElement element = driver.findElement(By.id("UserNavigationTabsContainer"));
		actions.moveToElement(element).build().perform();
		driver.findElement(By.linkText("Dashboard")).click();	
	}

	//Go to User management page
	public static void goToNewStaff() {
		//info("Go to New Staff");
		goToPage(ELEMENT_SEARCH_ICON_REGISTER, ELEMENT_LINK_SETUP, ELEMENT_LINK_USERS, ELEMENT_LINK_ADD_USERS);
	}

	//Go to My Account
	public static void goToMyAccount(){
		WebElement UI = driver.findElement(By.id("UserNavigationTabsContainer"));
		actions.moveToElement(UI).build().perform();
		driver.findElement(By.linkText("My Account")).click();	
		pause(500);
	}

	//Go to Portal/Group Sites
	public static void goToGroupSites(){
		info("--Go to Portal Site Management--");
		waitForAndGetElement(By.xpath(ELEMENT_LINK_SETUP));
		mouseOver(ELEMENT_LINK_SETUP, false);
		pause(500);
		mouseOver(ELEMENT_LINK_PORTAL, false);
		pause(500);
		WebElement element;
		element = waitForAndGetElement(ELEMENT_LINK_GROUP);
		actions.moveToElement(element).click(element).build().perform();
		pause(500);
	}

	//Go to add page locator with Editor
	public static void goToAddPageEditor(){
		waitForAndGetElement(By.xpath(ELEMENT_LINK_EDITOR));
		mouseOver(ELEMENT_LINK_EDITOR, true);
		pause(500);
		mouseOver(ELEMENT_LINK_EDITOR_PAGE, true);
		pause(500);
		WebElement element = waitForAndGetElement(ELEMENT_LINK_EDITOR_ADD_PAGE);
		actions.moveToElement(element).click(element).build().perform();
		pause(500);
	}

	public static void goToUsersAndGroupsManagement() {
		info("--Go to Users and groups management--");
		goToPage(ELEMENT_LINK_SETUP, ELEMENT_LINK_SETUP, ELEMENT_LINK_USERS, ELEMENT_GROUP_AND_ROLE_LINK);
	}
	
	//Go to Portal Application Registry
	public static void goToApplicationRegistry() {
		info("--Go to Portal Application Registry--");
		mouseOver(ELEMENT_LINK_SETUP, false);
		pause(500);
		waitForElementPresent(ELEMENT_APPLICATIONS_LINK);
		click(ELEMENT_APPLICATIONS_LINK);
		pause(500);
	}
	
	public static void goToEditPageEditor () {
		info("----Go to Edit page editor----");
		mouseOver(ELEMENT_EDIT_LINK,false);
		pause(500);
		mouseOver(ELEMENT_PAGE_LINK,false);
		pause(500);
		click(ELEMENT_EDIT_LAYOUT);
		pause(500);
	}
	
	//Go to change language for user interface
	public static void goToChangeLanguageForUserInterface(){
		info("--Go to change language for user interface--");
		WebElement UI = driver.findElement(By.id("UserNavigationTabsContainer"));
		actions.moveToElement(UI).build().perform();
		driver.findElement(By.linkText("Change Language")).click();	
		pause(500);
	}

	//Go to register page in public mode
	public static void goToRegisterPageInPublicMode(WebDriver driverTest){
		String registerPageLink = baseUrl.concat("/portal/intranet/Register");
		driverTest.get(registerPageLink);
		waitForTextPresent("Create a New Account");
	}
}
