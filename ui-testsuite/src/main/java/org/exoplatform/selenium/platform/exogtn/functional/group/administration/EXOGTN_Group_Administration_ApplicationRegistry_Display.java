package org.exoplatform.selenium.platform.exogtn.functional.group.administration;

import static org.exoplatform.selenium.platform.ManageAccount.*;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;

import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *@author NhungVT
 *@date: 25/09/2012
 */

public class EXOGTN_Group_Administration_ApplicationRegistry_Display extends PlatformBase
{
	public By ELEMENT_APPLICATIONS_LINK = By.xpath("//a[text()='Applications']");
	public By CATEGORIES_FORM = By.xpath("//div[text()='Categories']"); 
	public By IMPORT_APPLICATION = By.xpath("//div[text()='Import Applications']");
	public By EDIT_LINK = By.xpath("//a[text()='Edit']");
	public By PAGE_LINK = By.xpath("//a[text()='Page']");
	public By LAYOUT_LINK = By.xpath("//a[text()='Layout']");
	public By APPS_REG_PORTLET = By.className("PortletLayoutDecorator");
	public By EDIT_PORTLET = By.xpath("//a[@title='Edit Portlet']");
	public String SHOW_IMPORT_CHECKBOX = "//input[@id='showImport']";
	public By SHOW_IMPORT_UNCHECK = By.xpath("//input[@id='showImport' and @value='false']");
	public By SHOW_IMPORT_CHECKED = By.xpath("//input[@id='showImport' and @value='true']");
	public By FINISH_ICON = By.xpath("//a[@title='Finish']");
	
	//Function is defined to open Edit Portlet page
	public void goToEditPortlet()
	{
		mouseOver(EDIT_LINK, false);
		pause(500);
		mouseOver(PAGE_LINK, false);
		waitForElementPresent(LAYOUT_LINK);
		click(LAYOUT_LINK);
	}
	
	@BeforeMethod()
	public void beforeTest() throws Exception
	{
		initSeleniumTest();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseUrl);
		actions = new Actions(driver);
		driver.manage().window().maximize();
		signIn("john", "gtn");
	}
	
	//Show Application portlet when check or un-check Change show import
	@Test()
	public void test01_ShowImportApplication()
	{
		//goto Application
		goToApplicationRegistry();
		
		//Verify Categories display as default
		waitForElementPresent(CATEGORIES_FORM);
		
		//Import Application don't show as default
		waitForElementNotPresent(IMPORT_APPLICATION);
		
		//goto Edit Portlet
		goToEditPortlet();
		
		//Click on Edit Portlet icon
		mouseOver(APPS_REG_PORTLET, false);
		click(EDIT_PORTLET);
		
		//Verify Change Show Import checkbox is uncheck
		waitForElementPresent(SHOW_IMPORT_UNCHECK);
		
		//Select checkbox
		check(By.xpath(SHOW_IMPORT_CHECKBOX));
		save();
		close();
		
		//Verify Change Show Import checkbox is checked
		mouseOver(APPS_REG_PORTLET, false);
		click(EDIT_PORTLET);
		waitForElementNotPresent(SHOW_IMPORT_UNCHECK);
		waitForElementPresent(SHOW_IMPORT_CHECKED);
		close();
		
		//Click Finish
		click(FINISH_ICON);
		
		//Verify Import Applications is shown
		waitForElementPresent(IMPORT_APPLICATION);
		
		//Reset data
		goToEditPortlet();
		mouseOver(APPS_REG_PORTLET, false);
		click(EDIT_PORTLET);
		waitForElementPresent(SHOW_IMPORT_CHECKED);
		uncheck(SHOW_IMPORT_CHECKBOX);
		save();
		close();
		click(FINISH_ICON);
		waitForElementNotPresent(IMPORT_APPLICATION);
	}
	
	@AfterMethod()
	public void afterTest() throws Exception
	{
		driver.quit();
	}
}