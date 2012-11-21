package org.exoplatform.selenium.platform.exogtn.functional.groupnavigation;

import static org.exoplatform.selenium.platform.ManageAccount.*;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;
import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author NhungVT
 * @date: 20/09/2012	
 */
public class EXOGTN_GroupNavigation_Node_CopyPaste extends PlatformBase
{
	//Define data
	public By CONTENT_EDIT_NAVI_LINK = By.xpath("//td/div[text()='Content Management']/ancestor::tr/td/a[text()='Edit Navigation']");
	public By ADMIN_EDIT_PROPERTIES_LINK = By.xpath("//td/div[text()='Administration']/ancestor::tr/td/a[text()='Edit Navigation']");
	public By UP_LEVEL = By.xpath("//a[contains(@title,'Up Level')]");
	public By CONTENT_EXPLORER = By.xpath("//a[@title='Content Explorer']");
	public By CONTENT_ADMINISTRATION = By.xpath("//a[@title='Content Administration']");
	public By PORTAL_ADMINISTRATION = By.xpath("//a[@title='Portal Administration']");
	public String CHILD_NODE = "//div[@class='ChildrenContainer']//a[@class = 'NodeIcon DefaultPageIcon' and @title='Content Explorer']";
	public By SAVE_BUTTON = By.xpath("//a[text()='Save']");
	public By OK_BUTTON = By.xpath("//a[contains(text(),'OK')]");
	public By CLOSE_NAVIGATION = By.xpath("//a[contains(@title,'Close Window')]");
	public String SAME_PLACE_MESSAGE = "This node name already exists.";
	public String DELETE_CONFIRMATION_MESSAGE = "Are you sure to delete this node?";
	
	@BeforeMethod()
	public void beforeTest() throws Exception
	{
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		driver.manage().window().maximize();
		signIn("john", "gtn");
	}
	
	//Copy/Paste a node into another node in the same navigation
	@Test()
	public void test01_CopyPasteNodeInSameNavigation()
	{
		//Goto Group Sites
		goToGroupSites();
		
		//Select Edit Navigation of Content Management
		waitForElementPresent(CONTENT_EDIT_NAVI_LINK);
		click(CONTENT_EDIT_NAVI_LINK);
		
		//Copy Node on Content Explorer
		waitForElementPresent(CONTENT_EXPLORER);
		copyNode(CONTENT_EXPLORER);
		
		//Paste Node on Content Administration
		waitForElementPresent(CONTENT_ADMINISTRATION);
		pasteNode(CONTENT_ADMINISTRATION);
		
		//Save
		save();
		waitForElementNotPresent(CONTENT_EXPLORER);
		
		//Select Edit Navigation of Content Management
		waitForElementPresent(CONTENT_EDIT_NAVI_LINK);
		click(CONTENT_EDIT_NAVI_LINK);
		click(CONTENT_ADMINISTRATION);
		
		//Verify Copy/Paste result and Reset data
		waitForElementPresent(CHILD_NODE);
		rightClickOnElement(CHILD_NODE);
		click(ELEMENT_NODE_DELETE);
		waitForConfirmation(DELETE_CONFIRMATION_MESSAGE);
		waitForElementNotPresent(CHILD_NODE);
		save();
		waitForElementNotPresent(CONTENT_EXPLORER);
	}
	
	//Copy/Paste a node into another node in different navigation
	@Test()
	public void test02_CopyPasteNodeInDiffirentNavigation()
	{
		//Goto Group Sites
		goToGroupSites();
		
		//Select Edit Navigation of Content Management
		waitForElementPresent(CONTENT_EDIT_NAVI_LINK);
		click(CONTENT_EDIT_NAVI_LINK);
		
		//Right click and select Copy Node on Content Explorer
		waitForElementPresent(CONTENT_EXPLORER);
		copyNode(CONTENT_EXPLORER);
		
		//Close Content Management Navigation
		waitForElementPresent(CLOSE_NAVIGATION);
		click(CLOSE_NAVIGATION);
		
		//Select Edit Navigation of Administration
		waitForElementPresent(ADMIN_EDIT_PROPERTIES_LINK);
		click(ADMIN_EDIT_PROPERTIES_LINK);
		
		//Right click and confirm Paste Node not exist on Node Portal Administration
		waitForElementPresent(PORTAL_ADMINISTRATION);
		rightClickOnElement(PORTAL_ADMINISTRATION);
		waitForElementNotPresent(ELEMENT_PASTE_NODE);
		
		//Close Content Management Navigation
		waitForElementPresent(CLOSE_NAVIGATION);
		click(CLOSE_NAVIGATION);
	}
	
	//Copy/Paste a node into the same place
	@Test()
	public void test03_CopyPasteNodesInSamePlace()
	{
		//Goto Group Sites
		goToGroupSites();
		
		//Select Edit Navigation of Content Management
		waitForElementPresent(CONTENT_EDIT_NAVI_LINK);
		click(CONTENT_EDIT_NAVI_LINK);
		
		//Copy Node on Content Explorer
		waitForElementPresent(CONTENT_EXPLORER);
		copyNode(CONTENT_EXPLORER);
		
		//Paste Node on Content Administration
		waitForElementPresent(CONTENT_ADMINISTRATION);
		pasteNode(CONTENT_ADMINISTRATION);
		
		//Save
		save();
		waitForElementNotPresent(CONTENT_EXPLORER);
		
		//Select Edit Navigation of Content Management
		waitForElementPresent(CONTENT_EDIT_NAVI_LINK);
		click(CONTENT_EDIT_NAVI_LINK);
		
		click(CONTENT_ADMINISTRATION);
		copyNode(By.xpath(CHILD_NODE));
		pasteNode(CONTENT_ADMINISTRATION);
		
		//Verify display message to notice that Node already existed
		waitForTextPresent(SAME_PLACE_MESSAGE);
		click(OK_BUTTON);
		
		rightClickOnElement(CHILD_NODE);
		click(ELEMENT_NODE_DELETE);
		waitForConfirmation(DELETE_CONFIRMATION_MESSAGE);
		waitForElementNotPresent(CHILD_NODE);
		save();
		waitForElementNotPresent(CONTENT_EXPLORER);
	}
	
	@AfterMethod
	public void afterTest() throws Exception {
		signOut();
		driver.quit();
	}
}