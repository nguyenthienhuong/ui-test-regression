package org.exoplatform.selenium.platform.exogtn.functional.portalnavigation.edit;

import java.util.HashMap;
import java.util.Map;
import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.exoplatform.selenium.TestLogger.*;
import static org.exoplatform.selenium.platform.ManageAccount.*;
import static org.exoplatform.selenium.platform.NavigationToolbar.*;
import static org.exoplatform.selenium.platform.NavigationManagement.*;
import static org.exoplatform.selenium.platform.PageManagement.*;


/**
 *@author HangNTT
 * @date: 18/09/2012
 */
public class EXOGTN_PortalNavigation_EditNavigation_Node_Cut extends PlatformBase {
	/**
	 * @param args
	 */
	String CURRENT_NAV = "intranet";
	String CURRTENT_NODE = ELEMENT_NODE_LINK.replace("${nodeLabel}", "Home");
	String PAGE_NAME  = null; 
	String PAGE_TITLE = null; 
	By UP_LEVEL = By.xpath("//a[@title='Up Level']");

	public WebElement element = null;
	public String THE_SOURCE_DESTINATION_DIFFERENT ="The source and the destination must be different.";
	public String NODE_NAME_EXISTS ="This node name already exists.";
	
	public void cutNode(String nodename) {
		WebElement cutNode = waitForAndGetElement(By.linkText(nodename));
		Actions action = new Actions(driver);
		action.contextClick(cutNode).perform();
		waitForAndGetElement(By.partialLinkText("Cut Node")).click();
	}

	public void pasteNode(String nodename) {
		WebElement pasteNode = waitForAndGetElement(By.linkText(nodename));
		Actions action = new Actions(driver);
		action.contextClick(pasteNode).perform();
		waitForAndGetElement(By.partialLinkText("Paste Node")).click();
	}

	@BeforeMethod()
	public void beforeTest() throws Exception {
		initSeleniumTest();
		actions = new Actions(driver);
		driver.get(baseUrl);
		driver.manage().window().maximize();
	}

	// Cut Paste Node To Same Place
	@Test
	public void test05_CutPasteNodeToSamePlace () {
		signIn("john", "gtn");
		
		//Go to Portal navigation
		info("Go to Poral naviagtion");
		goToPortalSites();
		
		//Click Edit Navigation of intranet's portal
		info("Go to Edit navigation of intranet");
		editNavigation(CURRENT_NAV);
		
		// Cut a node
		info("Right click on a node");
		rightClickOnElement(CURRTENT_NODE);
		info("Cut a node");
		cutNode("Home");
		
		//Paste to same place
		info("Paste to same place");
		pasteNode("Home");
		//Show message
		waitForTextPresent(THE_SOURCE_DESTINATION_DIFFERENT);
		closeMessageDialog();
		info("Close navigation form");
		save();
		waitForTextPresent(CURRENT_NAV);
	}
	
	// Cut Paste Node To New Place In Same Navigation
	@Test
	public void test02_CutPasteNodeToNewPlaceInSameNavigation(){

		Map<String, String> languages = new HashMap<String, String>();
		languages.put("English", "");

		info("main program");	  
		signIn("john", "gtn");
		
		//Go to Portal navigation
		info("Go to Poral naviagtion");
		goToPortalSites();
		
		//Click Edit Navigation of intranet's portal
		info("Go to Edit navigation of intranet");
		editNavigation(CURRENT_NAV);
		
		//Add new node
		info("add new node");
		addNodeForPortal("intranet", "Home", false, "PORNAV_14_04_002", true, languages, "PORNAV_14_04_002", PAGE_NAME, PAGE_TITLE, true, true);
		editNavigation("intranet");
		
		//Cut new node
		info("Right click on new node");
		rightClickOnElement(By.linkText("PORNAV_14_04_002"));
		info("Cut node");
		cutNode("PORNAV_14_04_002");
		click(UP_LEVEL);   
		
		//Paste new node to new place
		info("Paste to new place");
		rightClickOnElement(By.linkText("Welcome"));
		pasteNode("Welcome");
		save();
		
		//Delete new node
		info("Delete node");
		editNavigation("intranet");
		deleteNode("intranet","Welcome","PORNAV_14_04_002",false);
		
		info("Verify node is deleted");
		editNavigation("intranet");
		waitForTextNotPresent("PORNAV_14_04_002");
		save();
		waitForTextPresent(CURRENT_NAV);
	}
	
	//Cut Paste Node To Same Place
	@Test
	public void test01_CutPasteNodeToSamePlace(){

		Map<String, String> languages = new HashMap<String, String>();
		languages.put("English", "");
		info("main program");	  
		signIn("john", "gtn");
		
		//Go to Portal navigation
		info("Go to Poral naviagtion");
		goToPortalSites();
		
		//Click Edit Navigation of intranet's portal
		info("Go to Edit navigation of intranet");
		editNavigation(CURRENT_NAV);
		
		//Add new node
		info("add new node");
		addNodeForPortal("intranet", "Home", false, "PORNAV_14_04_001", true, languages, "PORNAV_14_04_001", PAGE_NAME, PAGE_TITLE, true, true);
		editNavigation("intranet");
		
		info("Right click on new node");
		// Cut new node
		rightClickOnElement(By.linkText("PORNAV_14_04_001"));
		
		info("Cut node");
		cutNode("PORNAV_14_04_001");
		
		// Paste node to same place
		info("Paste node");
		rightClickOnElement(By.linkText("Home"));
		
		info("Paste to same place");
		pasteNode("Home");
		waitForTextPresent(NODE_NAME_EXISTS);
		closeMessageDialog();
		
		info("Close navigation form");
		info("Delete node");
		//Delete node
		deleteNode("intranet", "Home", "PORNAV_14_04_001", true);
		editNavigation("intranet");
		waitForTextNotPresent("PORNAV_14_04_001");
		save();
		waitForTextPresent(CURRENT_NAV);
	}
	
	//Cut Paste Node Created Automatically By Add Page
	@Test
	public void test04_CutPasteANodeCreatedAutomaticallyByAddPage () {
		String NODE_NAME = "PORNAV_14_04_004"; 
		String DISPLAY_NAME = "PORNAV_14_04_004";		
		String LANGUAGE = "English";	
		Map<String, String> PORTLET_IDS = new HashMap<String, String>();
		PORTLET_IDS.put("Content/ContentListViewerPortlet","");
		String CATEGORY_TITLE = "Content";

		info("main program");	  
		signIn("john", "gtn");
		
		//Add new page by wizard
		goToAddPageEditor();
		addNewPageEditor(NODE_NAME, DISPLAY_NAME, LANGUAGE, CATEGORY_TITLE, PORTLET_IDS, true);
		
		// Go to Portal Navigation
		goToPortalSites();
		info("Go to Edit navigation");
		
		// Click Edit navigation of intranet's portal
		editNavigation("intranet");
		
		//Cut new node
		info("Right click on new node");
		rightClickOnElement(By.linkText("PORNAV_14_04_004"));
		
		info("Cut node");
		cutNode("PORNAV_14_04_004");
		
		// Paste new node
		info("Paste node");
		rightClickOnElement(By.linkText("Welcome"));
		
		info("Paste new node ");
		pasteNode("Welcome");
		
		// Delete new node
		info("Delete node");
		deleteNode("intranet", "Home", "PORNAV_14_04_004", true);
		editNavigation("intranet");
		waitForTextNotPresent("PORNAV_14_04_004");
		save();
		waitForTextPresent(CURRENT_NAV);
	}
	
	@AfterMethod()
	public void afterTest() throws Exception {
		signOut();
		driver.quit();
	}
}