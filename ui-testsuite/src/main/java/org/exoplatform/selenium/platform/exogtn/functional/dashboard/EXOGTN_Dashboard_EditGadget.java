package org.exoplatform.selenium.platform.exogtn.functional.dashboard;

import static org.exoplatform.selenium.platform.ManageAccount.*;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;
import static org.exoplatform.selenium.platform.DashBoard.*;

import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *@author NhungVT
 *@date: 28/09/2012
 */

public class EXOGTN_Dashboard_EditGadget extends PlatformBase
{
	//Define data
	public String GADGET_NAME = "Top Rated Topics";
	public String GADGET_TITLE_DISPLAY = "Top voted rating topic";
	public By ADD_GADGETS_LINK = By.xpath("//a[text()='Add Gadgets']");
	public By GADGET_DIRECTORY_LIST = By.xpath("//div[@class='UIPopupWindow UIDragObject NormalStyle']");
	public By BOOKMARKS_GADGET_ON_LIST = By.xpath("//div[@class='GadgetTitle' and @title='"+GADGET_NAME+"']");
	public By CLOSE_WINDOW_BUTTON = By.xpath("//a[@title='Close Window']");
	//public By EDIT_ICON = By.xpath("//div[text()='"+GADGET_NAME+"']/preceding::div/img[@id='customize']");
	public By EDIT_ICON = By.xpath("//span[text()='"+GADGET_TITLE_DISPLAY+"']/preceding::span[@title='Edit Gadget']");
	//	public By MAXIMIZE_TO_DISPLAY_LIST = By.xpath("//select[@id='m_0_0' and @name='m_0_up_maxcount']");
	public By MAXIMIZE_TO_DISPLAY_LIST = By.xpath("//select[contains(@id,m) and contains(@name, up_maxcount)]");
	public By OPTION_5 = By.xpath("//option[@value='5' and @selected='selected']");
	//	public By OPTION_10 = By.xpath("//select[@name='m_0_up_maxcount']/option[@value='10' and @selected='selected']");
	public By OPTION_10 = By.xpath("//option[@value='5' and @selected='selected']");
	public By SAVE_BUTTON = By.xpath("//input[@type='button' and @value='Save']");

	public String DRAG_GADGETS_HERE_MESSAGE = "Drag your gadgets here.";

	@BeforeMethod()
	public void beforeTest() throws Exception 
	{
		initSeleniumTest();
		actions = new Actions(driver);
		driver.get(baseUrl);
		driver.manage().window().maximize();
		signIn("john", "gtn");
	}

	//Edit gadget preferences
	@Test()
	public void test01_EditGadgetPreference()
	{
		//Goto DashBoard
		goToDashboard();
		waitForTextPresent(DRAG_GADGETS_HERE_MESSAGE);

		//Click on Add Gadgets link
		waitForElementPresent(ADD_GADGETS_LINK);
		click(ADD_GADGETS_LINK);
		waitForElementPresent(GADGET_DIRECTORY_LIST);

		//Drag Bookmarks Gadget on list and Drop into Container
		waitForTextPresent("Tools");
		actions.dragAndDropBy(waitForAndGetElement(BOOKMARKS_GADGET_ON_LIST), 2, 2).build().perform();
		waitForTextPresent(GADGET_NAME);
		waitForElementPresent(CLOSE_WINDOW_BUTTON);
		click(CLOSE_WINDOW_BUTTON);

		//Edit Bookmarks Gadget content
		waitForElementPresent(EDIT_ICON);
		click(EDIT_ICON);
		waitForElementPresent(OPTION_5);
		select(MAXIMIZE_TO_DISPLAY_LIST, "10");
		waitForElementPresent(SAVE_BUTTON);
		click(SAVE_BUTTON);
		pause(1000);
		//Verify edit gadget results and reset data
		waitForElementPresent(EDIT_ICON);
		click(EDIT_ICON);
		waitForElementPresent(OPTION_10);
		select(MAXIMIZE_TO_DISPLAY_LIST, "5");
		click(SAVE_BUTTON);

		//Delete data
		deleteGadgetOnDashboard("Top voted rating topic");
	}

	@AfterMethod()
	public void afterTest() throws Exception
	{
		driver.quit();
	}
}
