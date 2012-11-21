package org.exoplatform.selenium.platform;

import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.platform.ManageAccount.*;


public class LoginTest extends PlatformBase{
	
	@BeforeGroups(groups = {"platform"})
	public void beforeTest() throws Exception {
		driver = new FirefoxDriver();
    	actions = new Actions(driver);
    	baseUrl = "http://localhost:8080";
    	driver.get(baseUrl + "/portal/intranet/");
    	pause(3000);
		
	}

	@AfterGroups(groups = {"platform"})
	public void afterTest() throws Exception {
		driver.quit();
	}
	
	@Test(groups = {"platform", "LoginTest"})
	public void testLoginasRoot(){
		signIn("root", "gtn");
		pause(2000);
		waitForTextPresent("Root");
		pause(2000);
		signOut();
	}
	@Test(groups = {"platform", "LoginTest"})
	public void testLoginasJack(){
		signIn("demo", "gtn");
		pause(2000);
		waitForTextPresent("Jack Miller");
		pause(2000);
		signOut();
	}
	@Test(groups = {"platform", "LoginTest"})
	public void testLoginasMary(){
		signIn("mary", "gtn");
		pause(2000);
		waitForTextPresent("Mary Williams");
		pause(2000);
		signOut();
	}
	@Test(groups = {"platform", "LoginTest"})
	public void testLoginasJames(){
		signIn("james", "gtn");
		pause(2000);
		waitForTextPresent("James Davis");
		pause(2000);
		signOut();
	}	
	@Test(groups = {"platform", "LoginTest"})
	public void testLoginasJohn(){	
		signIn("john", "gtn");
		pause(2000);
		waitForTextPresent("John Smith");
		pause(2000);
		signOut();
		
	}
	
	
}
