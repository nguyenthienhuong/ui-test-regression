package org.exoplatform.selenium.platform.exogtn.functional.dashboard;

import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.exoplatform.selenium.platform.ManageAccount.*;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;

/**
 *@author NhungVT
 *@date: 28/09/2012
 */

public class EXOGTN_Dashboard_AddGadget extends PlatformBase
{
	//Define data
	public String GADGET_NAME = "Hangman";
	public By ADD_GADGETS_LINK = By.xpath("//a[text()='Add Gadgets']");
	public By GADGET_URI_INPUT = By.xpath("//input[@id='url']");
	public By ADD_GADGET_BUTTON = By.xpath("//img[@title='Add Gadget']");
	public By CLOSE_WINDOW_BUTTON = By.xpath("//a[@title='Close Window']");
	public By MAXIMIZE_ICON = By.xpath("//span[text()='"+GADGET_NAME+"']/preceding::span[@title='Maximize']");
	
	public String GADGET_URI = "http://www.labpixies.com/campaigns/hangman/hangman.xml";
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
	
	public void deleteGadgetOnDashboard(String gadgetTitleDisplay)
	{
		String action = "Delete Gadget";
		By deleteGadgetIcon = By.xpath("//span[text()='"+gadgetTitleDisplay+"']/preceding::span[@title='"+action+"']");
		waitForAndGetElement(deleteGadgetIcon);
		click(deleteGadgetIcon);
		waitForConfirmation("Are you sure to delete this gadget?");
		waitForTextNotPresent(gadgetTitleDisplay);
	}
	
	//Add new gadget into dashboard with valid value
	@Test()
	public void test01_AddValidGadgetIntoDashboard()
	{
		//Goto DashBoard
		goToDashboard();
		waitForTextPresent(DRAG_GADGETS_HERE_MESSAGE);
		
		//Click on Add Gadgets link
		waitForElementPresent(ADD_GADGETS_LINK);
		click(ADD_GADGETS_LINK);
		
		//Add "http://www.labpixies.com/campaigns/hangman/hangman.xml" into Gadget list
		type(GADGET_URI_INPUT, GADGET_URI, true);
		click(ADD_GADGET_BUTTON);
		
		waitForElementPresent(CLOSE_WINDOW_BUTTON);
		click(CLOSE_WINDOW_BUTTON);
		
		//Verify Gadget is added into Dashboard
		waitForTextPresent(GADGET_NAME);
		waitForElementPresent(MAXIMIZE_ICON);
		
		//Open Hangman Gadget
		click(MAXIMIZE_ICON);
		
		/*--- After adding a gadget on Dashboard, not display icon edit, minimize, delete gadget
		 *--- Must refresh browser or open this Gadget --> These icon are displayed */ 
		
		//Delete Gadget
		deleteGadgetOnDashboard(GADGET_NAME);
	}
	
	@AfterMethod()
	public void afterTest() throws Exception
	{
		driver.quit();
	}
}
