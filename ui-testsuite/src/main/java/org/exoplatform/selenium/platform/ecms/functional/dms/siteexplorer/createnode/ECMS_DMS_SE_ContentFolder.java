package org.exoplatform.selenium.platform.ecms.functional.dms.siteexplorer.createnode;

import java.util.List;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static  org.exoplatform.selenium.TestLogger.*;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.*;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;

public class ECMS_DMS_SE_ContentFolder extends EcmsBase{
	public String DATA_CONT_NAME_01 = "FNC_ECMS_FEX_CREATE_01_001";
	public String DATA_CONT_TITLE_01 = "FNC_ECMS_FEX_CREATE_01_001";
	public String DATA_SUB_NAME_01 = "FNC_ECMS_FEX_CREATE_01_001_SUB";
	public String DATA_SUB_TITLE_01 = "FNC_ECMS_FEX_CREATE_01_001_SUB";
	public String DATA_DOC_NAME_02 = "FNC_ECMS_FEX_CREATE_01_002";
	public String DATA_DOC_TITLE_02 = "FNC_ECMS_FEX_CREATE_01_002";	

	public String DATA_DOC_NAME_11 = "FNC_ECMS_FEX_CREATE_01_11";
	public String DATA_DOC_TITLE_11 = "FNC_ECMS_FEX_CREATE_01_11";
	public String DATA_FPLAN_NAME_11 = "FNC_ECMS_FEX_CREATE_01_11";
	public String DATA_FPLAN_CAT_11 = "file plan";
	public String DATA_FPLAN_ORI_11 = "file plan";
	public String DATA_FPLAN_DIS_11 = "file plan";
	public String DATA_FPLAN_EVENT_11 = "file plan";
	public static final String USER= "john";
	public static final String PASSWORD= "gtn";
	public static final By ELEMENT_NEW_FOLDER_LINK = By.linkText("New Folder");
	public static final By ELEMENT_NEW_CONTENT_TYPE = By.name("type");
	public static final int PAUSE_1= 6000;
	public static final int PAUSE_2= 4000;

	//create a content folder inside another content folder

	@Test
	public void test01_CreateContentFolderInsideContentFolder() {

		info("Create a node at root path");
		//create a node at root path
		createNewContentFolder(DATA_CONT_TITLE_01, DATA_CONT_NAME_01);
		// check for creating document folder successfully
		waitForElementPresent(By.linkText(DATA_CONT_TITLE_01));

		//create a sub-node
		goToNode(By.xpath("//a[@title='"+ DATA_CONT_TITLE_01 + " ']"));
		createNewContentFolder(DATA_SUB_TITLE_01, DATA_SUB_NAME_01);
		// check creating document folder successfully
		waitForElementPresent(By.linkText(DATA_SUB_TITLE_01));

		//pause(1000);
		deleteDocument(By.xpath("//a[@title='"+ DATA_SUB_TITLE_01+" ']"));

		//delete parent node
		deleteDocument(By.xpath("//a[@title='"+ DATA_CONT_TITLE_01+" ']"));
	}

	//create a Content folder in a Document folder 
	@Test
	public void test02_CreateContentFolderInsideDocumentFolder()
	{
		info("Create a node at root path");
		
		//create a node at root path
		createNewDocumentFolder(DATA_DOC_TITLE_02, DATA_DOC_NAME_02);
		// check for creating document folder successfully
		waitForElementPresent(By.linkText(DATA_DOC_TITLE_02));
		
		//create a sub-node
		goToNode(By.xpath("//a[@title='" + DATA_DOC_TITLE_02+ " ']"));
		waitForAndGetElement(ELEMENT_NEW_FOLDER_LINK).click();
		Select typeFolder= new Select(waitForAndGetElement(ELEMENT_NEW_CONTENT_TYPE));
		List<WebElement> allOption= typeFolder.getOptions();
		for (WebElement option: allOption)
		{
			assert !(option.getText().equals("Content Folder")): "Fail! Still can add a content folder in a document folder!";
		}
		
		//delete parent node
		deleteDocument(By.xpath("//a[@title='" + DATA_DOC_TITLE_02 + " ']"));
		//pause(4000);
	} 

	//create a content folder inside Locked Document Folder, File Plan
	@Test
	public void test11_CreateContentFolderInsideLockedDocument()
	{

		info("Create a node at root path");
		//create a node at root path

		createNewDocumentFolder(DATA_DOC_TITLE_11, DATA_DOC_NAME_11);
		// check for creating document folder successfully
		waitForElementPresent(By.linkText(DATA_DOC_TITLE_11));
		
		lockNode(By.xpath("//a[@title='" + DATA_DOC_TITLE_11 + " ']"));
		//check if the document is locked 
		assert checkLockNode("//a[@title='" + DATA_DOC_TITLE_11 + " (Locked by "+ USER +")']"): "Fail to lock the document";

		//create a sub-node
		goToNode(By.xpath("//a[@title='" + DATA_DOC_TITLE_11 + " (Locked by "+ USER +")']"));

		waitForAndGetElement(ELEMENT_NEW_FOLDER_LINK).click();
		Select typeFolder= new Select(waitForAndGetElement(ELEMENT_NEW_CONTENT_TYPE));
		List<WebElement> allOption= typeFolder.getOptions();
		for (WebElement option: allOption)
		{
			assert !(option.getText().equals("Content Folder")): "Fail! Still can add a content folder in a locked document folder!";
		}

		//delete parent node
		deleteDocument(By.xpath("//a[@title='"+ DATA_DOC_TITLE_11 + " (Locked by "+ USER +")']"));
	}

	// create a content folder inside a locked file plan
	@Test
	public void test11_CreateContentFolderinsideLockedFilePlan()
	{
		info("Create a File Plan");
		// create a File Plan
		goToAddNewContent();
		createNewFilePlan(DATA_FPLAN_NAME_11, DATA_FPLAN_CAT_11, DATA_FPLAN_DIS_11, DATA_FPLAN_ORI_11,DATA_FPLAN_EVENT_11);

		// check for creating file plan successfully
		waitForElementPresent(By.linkText(DATA_FPLAN_NAME_11));

		//lock the file plan
		lockNode(By.xpath("//a[@title='" + DATA_FPLAN_NAME_11 + " ']"));

		//check node is being locked
		assert checkLockNode("//a[@title='" + DATA_FPLAN_NAME_11 + " (Locked by "+ USER +")']"): "Fail to lock the document";

		//open New Folder form in a file plan
		goToNode(By.xpath("//a[@title='" + DATA_FPLAN_NAME_11 + " (Locked by "+ USER +")']"));
		waitForAndGetElement(ELEMENT_NEW_FOLDER_LINK).click();

		// check the "type" field if any Content Folder value 
		Select typeFolder= new Select(waitForAndGetElement(ELEMENT_NEW_CONTENT_TYPE));
		List<WebElement> allOption= typeFolder.getOptions();
		for (WebElement option: allOption)
		{

			assert !(option.getText().equals("Content Folder")): "Fail! Still can add a content folder in a locked document folder!";
		}   

		//delete parent node
		deleteDocument(By.xpath("//a[@title='" + DATA_FPLAN_NAME_11 + " (Locked by "+ USER +")']"));
	}

	@BeforeMethod
	public void beforeMethod() {
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		loginEcms(USER, PASSWORD);
		goToSiteExplorer();
	}

	@AfterMethod
	public void afterMethod() {
		logoutEcms();
		driver.close();
	}
}