package org.exoplatform.selenium.platform.exogtn.functional.dashboard;

import static org.exoplatform.selenium.platform.ManageAccount.*;
import static org.exoplatform.selenium.platform.NavigationToolbar.goToDashboard;

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

public class EXOGTN_Dashboard_DeleteGadget extends PlatformBase 
{
	//Define data
	public String GADGET_NAME = "Agenda Gadget";
	public String GADGET_TITLE_DISPLAY = "My Agenda";
	public By ADD_GADGETS_LINK = By.xpath("//a[text()='Add Gadgets']");
	public By GADGET_DIRECTORY_LIST = By.xpath("//div[@class='UIPopupWindow UIDragObject NormalStyle']");
	public By AGENDA_GADGET_ON_LIST = By.xpath("//div[@class='GadgetTitle' and @title='"+GADGET_NAME+"']");
	public By CLOSE_WINDOW_BUTTON = By.xpath("//a[@title='Close Window']");

	public String DRAG_GADGETS_HERE_MESSAGE = "Drag your gadgets here.";

	public void deleteGadgetOnDashboard(String gadgetTitleDisplay)
	{
		String action = "Delete Gadget";
		By deleteGadgetIcon = By.xpath("//span[text()='"+gadgetTitleDisplay+"']/preceding::span[@title='"+action+"']");
		waitForAndGetElement(deleteGadgetIcon);
		click(deleteGadgetIcon);
		waitForConfirmation("Are you sure to delete this gadget?");
		waitForTextNotPresent(gadgetTitleDisplay);
	}
	
	@BeforeMethod()
	public void beforeTest() throws Exception 
	{
		initSeleniumTest();
		actions = new Actions(driver);
		driver.get(baseUrl);
		driver.manage().window().maximize();
		signIn("john", "gtn");
	}
	
	//Delete gadget with  deleting confirmation
	@Test()
	public void test01_DeleteGadgetWithConfirmation()
	{		
		//Goto DashBoard
		goToDashboard();
		waitForTextPresent(DRAG_GADGETS_HERE_MESSAGE);

		//Click on Add Gadgets link
		waitForElementPresent(ADD_GADGETS_LINK);
		click(ADD_GADGETS_LINK);
		waitForElementPresent(GADGET_DIRECTORY_LIST);

		//Drag My Agenda Gadget on list and Drop into Container
		waitForTextPresent("Collaboration");
		actions.dragAndDropBy(waitForAndGetElement(AGENDA_GADGET_ON_LIST), 2, 2).build().perform();
		waitForTextPresent(GADGET_TITLE_DISPLAY);
		waitForElementPresent(CLOSE_WINDOW_BUTTON);
		click(CLOSE_WINDOW_BUTTON);
		
		//Delete data
		deleteGadgetOnDashboard(GADGET_TITLE_DISPLAY);
	}
	
	@AfterMethod()
	public void afterTest() throws Exception
	{
		signOut();
		driver.quit();
	}
}