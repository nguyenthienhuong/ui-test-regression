package org.exoplatform.selenium.platform.ecms.functional.dms.siteexplorer.basicaction;

import org.exoplatform.selenium.platform.ecms.EcmsBase;

import static org.exoplatform.selenium.platform.ManageAccount.*;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.*;
import static org.exoplatform.selenium.TestLogger.*;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *@author NhungVT, ThuNTN
 *@date: 02/10/2012
 */

public class ECMS_DMS_SE_BasicAction_DragAndDrop extends EcmsBase 
{
	@BeforeMethod()
	public void beforeTest() throws Exception
	{
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		driver.manage().window().maximize();
		signIn("john", "gtn");
	}

	//Drag and drop in the left  pane
	@Test()
	public void test01_DragAndDropInLeftPane()
	{
		String DATA_CONTENT_FOLDER_1 = "FEX_ACTION_03_CONTENT_01_1";
		String DATA_CONTENT_FOLDER_2 = "FEX_ACTION_03_CONTENT_01_2";
		String CONTENT_FOLDER_XPATH_1_RIGHT = "//div[@title='"+DATA_CONTENT_FOLDER_1+"']";
		String CONTENT_FOLDER_XPATH_1_LEFT = "//div[@id='UITreeExplorer']//div[contains(@mousedown,'collaboration:/sites content/live/"+DATA_CONTENT_FOLDER_1+"')]";
		String CONTENT_FOLDER_XPATH_2_LEFT = "//div[@id='UITreeExplorer']//div[contains(@mousedown,'collaboration:/sites content/live/"+DATA_CONTENT_FOLDER_2+"')]";

		By CONTENT_FOLDER_1_RIGHT = By.xpath(CONTENT_FOLDER_XPATH_1_RIGHT);
		By CONTENT_FOLDER_1_LEFT = By.xpath(CONTENT_FOLDER_XPATH_1_LEFT);
		By CONTENT_FOLDER_2_LEFT = By.xpath(CONTENT_FOLDER_XPATH_2_LEFT);

		info("Drag and drop a node on the left to a node on the left pane!");
		//goto Site Explorer
		goToSiteExplorer();

		//Create Content Folder 1
		createNewContentFolder(DATA_CONTENT_FOLDER_1, DATA_CONTENT_FOLDER_1);
		waitForElementPresent(CONTENT_FOLDER_1_LEFT);

		//Create Content Folder 2
		createNewContentFolder(DATA_CONTENT_FOLDER_2, DATA_CONTENT_FOLDER_2);
		waitForElementPresent(CONTENT_FOLDER_2_LEFT);

		//Drag Folder 2 and Drop into Folder 1
		dragAndDropToObject(CONTENT_FOLDER_1_LEFT,CONTENT_FOLDER_2_LEFT);
		acceptAlert();

		//Verify Folder 2 into Folder1
		waitForElementNotPresent(CONTENT_FOLDER_1_LEFT);
		goToNode(CONTENT_FOLDER_2_LEFT);
		waitForElementPresent(CONTENT_FOLDER_1_RIGHT);
		deleteDocument(CONTENT_FOLDER_2_LEFT);
	}

	//Drag and drop from left to right pane
	@Test()
	public void test02_DragAndDropLeftToRightPane()
	{
		String DATA_CONTENT_FOLDER_1 = "FEX_ACTION_03_CONTENT_02_1";
		String DATA_CONTENT_FOLDER_2 = "FEX_ACTION_03_CONTENT_02_2";
		String CONTENT_FOLDER_XPATH_1_RIGHT = "//div[@title='"+DATA_CONTENT_FOLDER_1+"']";
		String CONTENT_FOLDER_XPATH_2_RIGHT = "//div[@title='"+DATA_CONTENT_FOLDER_2+"']";
		String CONTENT_FOLDER_XPATH_1_LEFT = "//div[@id='UITreeExplorer']//div[contains(@mousedown,'collaboration:/sites content/live/"+DATA_CONTENT_FOLDER_1+"')]";
		String CONTENT_FOLDER_XPATH_2_LEFT = "//div[@id='UITreeExplorer']//div[contains(@mousedown,'collaboration:/sites content/live/"+DATA_CONTENT_FOLDER_2+"')]";

		By CONTENT_FOLDER_1_RIGHT = By.xpath(CONTENT_FOLDER_XPATH_1_RIGHT);
		By CONTENT_FOLDER_2_RIGHT = By.xpath(CONTENT_FOLDER_XPATH_2_RIGHT);
		By CONTENT_FOLDER_1_LEFT = By.xpath(CONTENT_FOLDER_XPATH_1_LEFT);
		By CONTENT_FOLDER_2_LEFT = By.xpath(CONTENT_FOLDER_XPATH_2_LEFT);

		info("Drag and drop a node on the left to a node on the right pane!");
		//goto Site Explorer
		goToSiteExplorer();

		//Create Content Folder 1
		createNewContentFolder(DATA_CONTENT_FOLDER_1, DATA_CONTENT_FOLDER_1);
		waitForElementPresent(CONTENT_FOLDER_1_RIGHT);

		//Create Content Folder 2
		createNewContentFolder(DATA_CONTENT_FOLDER_2, DATA_CONTENT_FOLDER_2);
		waitForElementPresent(CONTENT_FOLDER_2_RIGHT);

		//Drag Folder 2 and Drop into Folder 1
		dragAndDropToObject(CONTENT_FOLDER_2_LEFT, CONTENT_FOLDER_1_RIGHT);
		acceptAlert();

		//Verify Folder 2 into Folder1
		waitForElementNotPresent(CONTENT_FOLDER_2_LEFT);
		goToNode(CONTENT_FOLDER_1_LEFT);
		waitForElementPresent(CONTENT_FOLDER_2_RIGHT);

		deleteDocument(CONTENT_FOLDER_1_LEFT);
	}

	//Drag and drop on the right  pane
	@Test()
	public void test03_DragAndDropInRight()
	{
		String DATA_CONTENT_FOLDER_1 = "FEX_ACTION_03_CONTENT_03_1";
		String DATA_CONTENT_FOLDER_2 = "FEX_ACTION_03_CONTENT_03_2";
		String CONTENT_FOLDER_XPATH_1_RIGHT = "//div[@title='"+DATA_CONTENT_FOLDER_1+"']";
		String CONTENT_FOLDER_XPATH_2_RIGHT = "//div[@title='"+DATA_CONTENT_FOLDER_2+"']";
		String CONTENT_FOLDER_XPATH_1_LEFT = "//div[@id='UITreeExplorer']//div[contains(@mousedown,'collaboration:/sites content/live/"+DATA_CONTENT_FOLDER_1+"')]";
		String CONTENT_FOLDER_XPATH_2_LEFT = "//div[@id='UITreeExplorer']//div[contains(@mousedown,'collaboration:/sites content/live/"+DATA_CONTENT_FOLDER_2+"')]";
		By CONTENT_FOLDER_1_RIGHT = By.xpath(CONTENT_FOLDER_XPATH_1_RIGHT);
		By CONTENT_FOLDER_2_RIGHT = By.xpath(CONTENT_FOLDER_XPATH_2_RIGHT);
		By CONTENT_FOLDER_1_LEFT = By.xpath(CONTENT_FOLDER_XPATH_1_LEFT);
		By CONTENT_FOLDER_2_LEFT = By.xpath(CONTENT_FOLDER_XPATH_2_LEFT);

		info("Drag and drop a node on the right to a node on the right pane!");
		//goto Site Explorer
		goToSiteExplorer();

		//Create Content Folder 1
		createNewContentFolder(DATA_CONTENT_FOLDER_1, DATA_CONTENT_FOLDER_1);
		waitForElementPresent(CONTENT_FOLDER_1_RIGHT);

		//Create Content Folder 2
		createNewContentFolder(DATA_CONTENT_FOLDER_2, DATA_CONTENT_FOLDER_2);
		waitForElementPresent(CONTENT_FOLDER_2_RIGHT);

		//Drag Folder 2 and Drop into Folder 1
		dragAndDropToObject(CONTENT_FOLDER_2_RIGHT, CONTENT_FOLDER_1_RIGHT);
		acceptAlert();

		//Verify Folder 2 into Folder1
		waitForElementNotPresent(CONTENT_FOLDER_2_LEFT);
		goToNode(CONTENT_FOLDER_1_LEFT);
		waitForElementPresent(CONTENT_FOLDER_2_RIGHT);

		deleteDocument(CONTENT_FOLDER_1_LEFT);
	}

	//Drag and drop from left to right pane
	@Test()
	public void test04_DragAndDropRightToLeftPane()
	{
		String DATA_CONTENT_FOLDER_1 = "FEX_ACTION_03_CONTENT_04_1";
		String DATA_CONTENT_FOLDER_2 = "FEX_ACTION_03_CONTENT_04_2";
		String CONTENT_FOLDER_XPATH_1_RIGHT = "//div[@title='"+DATA_CONTENT_FOLDER_1+"']";
		String CONTENT_FOLDER_XPATH_2_RIGHT = "//div[@title='"+DATA_CONTENT_FOLDER_2+"']";
		String CONTENT_FOLDER_XPATH_1_LEFT = "//div[@id='UITreeExplorer']//div[contains(@mousedown,'collaboration:/sites content/live/"+DATA_CONTENT_FOLDER_1+"')]";
		String CONTENT_FOLDER_XPATH_2_LEFT = "//div[@id='UITreeExplorer']//div[contains(@mousedown,'collaboration:/sites content/live/"+DATA_CONTENT_FOLDER_2+"')]";
		By CONTENT_FOLDER_1_RIGHT = By.xpath(CONTENT_FOLDER_XPATH_1_RIGHT);
		By CONTENT_FOLDER_2_RIGHT = By.xpath(CONTENT_FOLDER_XPATH_2_RIGHT);
		By CONTENT_FOLDER_1_LEFT = By.xpath(CONTENT_FOLDER_XPATH_1_LEFT);
		By CONTENT_FOLDER_2_LEFT = By.xpath(CONTENT_FOLDER_XPATH_2_LEFT);

		info("Drag and drop a node on the right to a node on the left pane!");
		//goto Site Explorer
		goToSiteExplorer();

		//Create Content Folder 1
		createNewContentFolder(DATA_CONTENT_FOLDER_1, DATA_CONTENT_FOLDER_1);
		waitForElementPresent(CONTENT_FOLDER_1_RIGHT);

		//Create Content Folder 2
		createNewContentFolder(DATA_CONTENT_FOLDER_2, DATA_CONTENT_FOLDER_2);
		waitForElementPresent(CONTENT_FOLDER_2_RIGHT);

		//Drag Folder 2 and Drop into Folder 1
		dragAndDropToObject(CONTENT_FOLDER_2_RIGHT, CONTENT_FOLDER_1_LEFT);
		acceptAlert();

		//Verify Folder 2 into Folder1
		waitForElementNotPresent(CONTENT_FOLDER_2_LEFT);
		goToNode(CONTENT_FOLDER_1_LEFT);
		waitForElementPresent(CONTENT_FOLDER_2_RIGHT);

		deleteDocument(CONTENT_FOLDER_1_LEFT);
	}

	@AfterMethod()
	public void afterTest() throws Exception
	{
		driver.quit();
	}
}
