package org.exoplatform.selenium.platform.ecms.functional.dms.siteexplorer.basicaction;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import static org.exoplatform.selenium.TestLogger.*;
import static org.exoplatform.selenium.platform.ecms.ActionBar.*;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.*;
import static org.exoplatform.selenium.platform.ecms.ContextMenu.*;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.*;
import static org.exoplatform.selenium.platform.ecms.WcmAdmin.*;
/*
 * @author: Thuntn
 * @date: 08/10/2012
 */
public class ECMS_DMS_SE_BasicAction_CutPaste extends EcmsBase {
	public static String USER = "john";
	public static String PASS = "gtn";
	By ELEMENT_ACME_FOLDER= By.xpath("//a[@title='acme ']");
	By ELEMENT_ROOT=By.xpath("//div[@class='Title' and contains(text(),'Sites Management')]");
	By ELEMENT_DRIVE_MANAGE_SITE=By.xpath("//a[contains(text(),'Sites Management')]");

	//cut a content folder and paste it to another node
	@Test(groups={"ecms"})
	public void test01_CutContentFolderPasteInOtherNode() {
		String title="ECMS_DMS_SE_BasicAction_CutPaste_01";
		By bCont=By.xpath("//a[@title='"+title+" ']");
		By bContDiv=By.xpath("//div[@title='"+title+"']");

		info("Cut a content folder and paste it to another node!");
		//create a content folder 
		createNewContentFolder(title, title);
		waitForElementPresent(bCont);
		//cut and paste content folder to acme folder
		cutAndPasteNode(bCont,ELEMENT_ACME_FOLDER);

		//verify if paste the content folder successfully in acme folder 
		waitForElementNotPresent(bCont);
		goToNode("acme");
		waitForElementPresent(bContDiv);

		//delete data
		deleteDocument(bCont);
	}
	//cut a document folder and paste it to Document folder
	@Test
	public void test02_CutDocumentFolderPasteInDocumentFolder() {
		String title="ECMS_DMS_SE_BasicAction_CutPaste_02";
		By bDoc=By.xpath("//a[@title='"+title+" ']");
		By bDocDiv=By.xpath("//div[@title='"+title+"']");
		String titleDes="ECMS_DMS_SE_BasicAction_CutPaste_02_des";
		By bDocDes=By.xpath("//a[@title='"+titleDes+" ']");

		info("Cut a document folder and paste it to another node!");
		//create a document folder - source
		createNewDocumentFolder(title, title);
		waitForElementPresent(bDoc);

		//create a document folder -destination
		createNewDocumentFolder(titleDes, titleDes);
		waitForElementPresent(bDocDes);

		//cut and paste the document folder to acme folder
		cutAndPasteNode(bDoc, bDocDes);

		//verify if paste document folder successfully in destination folder 
		goToNode(bDocDes);
		waitForElementPresent(bDocDiv);

		//delete data
		deleteDocument(bDocDes);
	}
	//cut a document folder and paste it to another node
	@Test
	public void test02_CutDocumentFolderPasteInContentFolder() {
		String title="ECMS_DMS_SE_BasicAction_CutPaste_02";
		By bDoc=By.xpath("//a[@title='"+title+" ']");
		By bDocDiv=By.xpath("//div[@title='"+title+"']");
		String titleDes="ECMS_DMS_SE_BasicAction_CutPaste_02_des";
		By bDocDes=By.xpath("//a[@title='"+titleDes+" ']");

		info("Cut a document folder and paste it to another node!");
		//create a document folder - source
		createNewDocumentFolder(title, title);
		waitForElementPresent(bDoc);

		//create a document folder -destination
		createNewContentFolder(titleDes, titleDes);
		waitForElementPresent(bDocDes);

		//cut and paste the document folder to acme folder
		cutAndPasteNode(bDoc, bDocDes);

		//verify if paste document folder successfully in destination folder 
		goToNode(bDocDes);
		waitForElementPresent(bDocDiv);

		//delete data
		deleteDocument(bDocDes);
	}
	//cut a document folder and paste it to an article
	@Test
	public void test03_CutDocumentFolderPasteIntoArticle() {
		String title="ECMS_DMS_SE_BasicAction_CutPaste_03";
		By bDoc=By.xpath("//a[@title='"+title+" ']");
		String titleDes="ECMS_DMS_SE_BasicAction_CutPaste_03_art";
		By bDocDes=By.xpath("//a[@title='"+ titleDes +" ']");

		info("Cut a document folder and paste it to another node!");
		//create a document folder - source
		createNewDocumentFolder(title, title);
		waitForElementPresent(bDoc);

		//create an article -destination
		goToAddNewContent();
		createNewArticle(titleDes, titleDes, titleDes, titleDes);
		waitForElementPresent(bDocDes);

		//cut and paste the document folder to an article
		cutAndPasteNode(bDoc, bDocDes);
		//verify cannot paste the document folder in an article
		waitForTextPresent("Cannot paste the copied node type on the current node.");
		click(ELEMENT_OK_BUTTON); 
		//delete data
		deleteDocument(bDocDes);
		deleteDocument(bDoc);
	}
	//cut a document folder and paste it to a sample node
	@Test
	public void test03_CutDocumentFolderPasteIntoSampleNode() {
		String title="ECMS_DMS_SE_BasicAction_CutPaste_03";
		By bDoc=By.xpath("//a[@title='"+title+" ']");
		By.xpath("//div[@title='"+title+"']");
		String titleDes="ECMS_DMS_SE_BasicAction_CutPaste_03_des";
		By bDocDes=By.xpath("//a[@title='"+ titleDes +" ']");
		String img="TestData/ECMS_DMS_SE_BasicAction_CutPaste_03_des.png";

		info("Cut a document folder and paste it to a sample node!");
		//create a document folder - source
		createNewDocumentFolder(title, title);
		waitForElementPresent(bDoc);

		//create a sample node -destination
		goToAddNewContent();
		createNewSampleNode(titleDes, titleDes, img);
		waitForElementPresent(bDocDes);

		//cut and paste the document folder to a sample node
		cutAndPasteNode(bDoc, bDocDes);
		//verify cannot paste the document folder in a sample node
		waitForTextPresent("Cannot paste the copied node type on the current node.");
		click(ELEMENT_OK_BUTTON); 
		//delete data
		deleteDocument(bDocDes);
		deleteDocument(bDoc);
	}
	//Cut a document folder and paste it to file document!
	@Test
	public void test04_CutDocumentFolderPasteIntoFileDocument() {
		String title="ECMS_DMS_SE_BasicAction_CutPaste_04";
		By bDoc=By.xpath("//a[@title='"+title+" ']");
		By.xpath("//div[@title='"+title+"']");
		String titleDes="ECMS_DMS_SE_BasicAction_CutPaste_04_des";
		By bDocDes=By.xpath("//a[@title='"+ titleDes +" ']");

		info("Cut a document folder and paste it to file document!");
		//create a document folder - source
		createNewDocumentFolder(title, title);
		waitForElementPresent(bDoc);

		//create a file document -destination
		goToAddNewContent();
		createNewFile(titleDes, titleDes, titleDes);
		waitForElementPresent(bDocDes);

		//cut and paste the document folder to file document
		cutAndPasteNode(bDoc, bDocDes);
		//verify if paste document folder successfully in a file document
		waitForElementNotPresent(bDoc);
		checkPreferenceOption(ELEMENT_ENABLE_DMS_STRUCTURE);
		goToNode(bDocDes);
		goToNode(bDoc);
		waitForElementPresent(By.xpath("//input[contains(@value,'"+titleDes+ "/" + title + "')]"));
		//delete data
		deleteDocument(bDocDes);
	}
	//Cut a document folder and paste it to podcast
	@Test
	public void test04_CutDocumentFolderPasteIntoPodcast() {
		String title="ECMS_DMS_SE_BasicAction_CutPaste_04";
		By bDoc=By.xpath("//a[@title='"+title+" ']");
		By.xpath("//div[@title='"+title+"']");
		String titleDes="ECMS_DMS_SE_BasicAction_CutPaste_04_des";
		By bDocDes=By.xpath("//a[@title='"+ titleDes +" ']");

		info("Cut a document folder and paste it to podcast!");
		//create a document folder - source
		createNewDocumentFolder(title, title);
		waitForElementPresent(bDoc);

		//create a podcast -destination
		goToAddNewContent();
		createNewPodcast(titleDes, titleDes, titleDes);
		waitForElementPresent(bDocDes);

		//cut and paste the document folder to podcast
		cutAndPasteNode(bDoc, bDocDes);
		//verify if paste document folder successfully in a podcast
		waitForElementNotPresent(bDoc);
		checkPreferenceOption(ELEMENT_ENABLE_DMS_STRUCTURE);
		goToNode(bDocDes);
		goToNode(bDoc);
		waitForElementPresent(By.xpath("//input[contains(@value,'"+titleDes+ "/" + title + "')]"));
		//delete data
		deleteDocument(bDocDes);
	}
	//Cut a document folder and paste it to File plan!
	@Test
	public void test04_CutDocumentFolderPasteIntoFilePlan() {
		String title="ECMS_DMS_SE_BasicAction_CutPaste_04";
		By bDoc=By.xpath("//a[@title='"+title+" ']");
		By.xpath("//div[@title='"+title+"']");
		String titleDes="ECMS_DMS_SE_BasicAction_CutPaste_04_des";
		By bDocDes=By.xpath("//a[@title='"+ titleDes +" ']");

		info("Cut a document folder and paste it to file plan!");
		//create a document folder - source
		createNewDocumentFolder(title, title);
		waitForElementPresent(bDoc);

		//create a file plan -destination
		goToAddNewContent();
		createNewFilePlan(titleDes, titleDes, titleDes, titleDes, titleDes);
		waitForElementPresent(bDocDes);

		//cut and paste the document folder to podcast
		cutAndPasteNode(bDoc, bDocDes);

		//verify if paste document folder successfully in a podcast
		waitForElementNotPresent(bDoc);
		checkPreferenceOption(ELEMENT_ENABLE_DMS_STRUCTURE);
		goToNode(bDocDes);
		goToNode(bDoc);
		waitForElementPresent(By.xpath("//input[contains(@value,'"+titleDes+ "/" + title + "')]"));

		//delete data
		deleteDocument(bDocDes);
	}
	//Cut a document folder and paste it to kofax document!
	@Test
	public void test04_CutDocumentFolderPasteToKofaxDocument() {
		String title="ECMS_DMS_SE_BasicAction_CutPaste_04";
		By bDoc=By.xpath("//a[@title='"+title+" ']");
		By.xpath("//div[@title='"+title+"']");
		String titleDes="ECMS_DMS_SE_BasicAction_CutPaste_04_des";
		By bDocDes=By.xpath("//a[@title='"+ titleDes +" ']");

		info("Cut a document folder and paste it to kofax document!");
		//create a document folder - source
		createNewDocumentFolder(title, title);
		waitForElementPresent(bDoc);

		//create a kofax -destination
		goToAddNewContent();
		createNewKofax(titleDes);
		waitForElementPresent(bDocDes);

		//cut and paste the document folder to kofax
		cutAndPasteNode(bDoc, bDocDes);

		//verify if paste document folder successfully in a kofax
		waitForElementNotPresent(bDoc);
		checkPreferenceOption(ELEMENT_ENABLE_DMS_STRUCTURE);
		goToNode(bDocDes);
		goToNode(bDoc);
		waitForElementPresent(By.xpath("//input[contains(@value,'"+titleDes+ "/" + title + "')]"));

		//delete data
		deleteDocument(bDocDes);
	}

	//Cut a document folder and paste it to uploaded file!
	@Test
	public void test04_CutDocumentFolderPasteToUploadedFile() {
		String title="ECMS_DMS_SE_BasicAction_CutPaste_04";
		By bDoc=By.xpath("//a[@title='"+title+" ']");
		By.xpath("//div[@title='"+title+"']");
		String titleDes="ECMS_DMS_SE_BasicAction_CutPaste_04_des";
		String img="TestData/ECMS_DMS_SE_BasicAction_CutPaste_04_des.zip";
		By bDocDes=By.xpath("//a[@title='"+ titleDes +".zip ']");

		info("Cut a document folder and paste it to uploaded file!");
		//create a document folder - source
		createNewDocumentFolder(title, title);
		waitForElementPresent(bDoc);

		//create an uploaded file -destination
		uploadFile(titleDes, img);
		waitForElementPresent(bDocDes);

		//cut and paste the document folder to uploaded file
		cutAndPasteNode(bDoc,bDocDes);

		//verify if paste document folder successfully in an uploaded file
		waitForElementNotPresent(bDoc);
		checkPreferenceOption(ELEMENT_ENABLE_DMS_STRUCTURE);
		goToNode(bDocDes);
		goToNode(bDoc);
		waitForElementPresent(By.xpath("//input[contains(@value,'"+titleDes+ ".zip/" + title + "')]"));

		//delete data
		deleteDocument(bDocDes);
	}

	//Cut an article and paste it to a content folder!
	@Test
	public void test05_CutArticlePasteToContentFolder() {
		String title="ECMS_DMS_SE_BasicAction_CutPaste_05";
		By bDoc=By.xpath("//a[@title='"+title+" ']");
		By.xpath("//div[@title='"+title+"']");
		String titleDes="ECMS_DMS_SE_BasicAction_CutPaste_05_des";
		By bDocDes=By.xpath("//a[@title='"+ titleDes +" ']");

		info("Cut an article and paste it to a content folder!");
		//create a content folder -destination
		createNewContentFolder(titleDes, titleDes);
		waitForElementPresent(bDocDes);

		//create an article - source
		goToAddNewContent();
		createNewArticle(title, title, title, title);
		waitForElementPresent(bDoc);

		//cut and paste the article to content folder
		cutAndPasteNode(bDoc, bDocDes);

		//verify if paste article successfully in a content folder
		waitForElementNotPresent(bDoc);
		goToNode(bDocDes);
		goToNode(bDoc);
		waitForElementPresent(By.xpath("//input[contains(@value,'"+titleDes+ "/" + title + "')]"));

		//delete data
		deleteDocument(bDocDes);
	}
	//Cut an article and paste it to a document folder!
	@Test
	public void test06_CutArticlePasteToDocumentFolder() {
		String title="ECMS_DMS_SE_BasicAction_CutPaste_06";
		By bDoc=By.xpath("//a[@title='"+title+" ']");
		By.xpath("//div[@title='"+title+"']");
		String titleDes="ECMS_DMS_SE_BasicAction_CutPaste_06_des";
		By bDocDes=By.xpath("//a[@title='"+ titleDes +" ']");

		info("Cut an article and paste it to a document folder!");
		//create a document folder -destination
		createNewDocumentFolder(titleDes, titleDes);
		waitForElementPresent(bDocDes);

		//create an article - source
		goToAddNewContent();
		createNewArticle(title, title, title, title);
		waitForElementPresent(bDoc);

		//cut and paste the article to document folder
		cutAndPasteNode(bDoc, bDocDes);

		//verify cannot paste article in a document folder
		waitForTextPresent("Cannot paste the copied node type on the current node.");
		click(ELEMENT_OK_BUTTON);

		//delete data
		deleteDocument(bDocDes);
		deleteDocument(bDoc);
	}
	//Cut an article and paste it to an article!
	@Test
	public void test07_CutArticlePasteToArticle() {
		String title="ECMS_DMS_SE_BasicAction_CutPaste_07";
		By bDoc=By.xpath("//a[@title='"+title+" ']");
		By.xpath("//div[@title='"+title+"']");
		String titleDes="ECMS_DMS_SE_BasicAction_CutPaste_07_des";
		By bDocDes=By.xpath("//a[@title='"+ titleDes +" ']");

		info("Cut an article and paste it to an article!");
		//create an article -destination
		goToAddNewContent();
		createNewArticle(titleDes, titleDes, titleDes, titleDes);
		waitForElementPresent(bDocDes);

		click(ELEMENT_ROOT);
		waitForElementPresent(By.xpath("//input[@value='/']"));

		//create an article - source
		goToAddNewContent();
		createNewArticle(title, title, title, title);
		waitForElementPresent(bDoc);

		//cut and paste the article to article
		cutAndPasteNode(bDoc, bDocDes);

		//verify cannot paste article in an article
		waitForTextPresent("Cannot paste the copied node type on the current node.");
		click(ELEMENT_OK_BUTTON);

		//delete data
		deleteDocument(bDocDes);
		deleteDocument(bDoc);
	}
	//Cut an article and paste it to a sample node!
	@Test
	public void test07_CutArticlePasteToSampleNode() {
		String title="ECMS_DMS_SE_BasicAction_CutPaste_07";
		By bDoc=By.xpath("//a[@title='"+title+" ']");
		By.xpath("//div[@title='"+title+"']");
		String titleDes="ECMS_DMS_SE_BasicAction_CutPaste_07_des";
		By bDocDes=By.xpath("//a[@title='"+ titleDes +" ']");
		String img="TestData/ECMS_DMS_SE_BasicAction_CutPaste.png";

		info("Cut an article and paste it to a sample node!");
		//create a sample node -destination
		goToAddNewContent();
		createNewSampleNode(titleDes, titleDes, img);
		waitForElementPresent(bDocDes);

		click(ELEMENT_ROOT);
		waitForElementPresent(By.xpath("//input[@value='/']"));

		//create an article - source
		goToAddNewContent();
		createNewArticle(title, title, title, title);
		waitForElementPresent(bDoc);

		//cut and paste the article to a sample node
		cutAndPasteNode(bDoc, bDocDes);

		//verify cannot paste the article in a sample node
		waitForTextPresent("Cannot paste the copied node type on the current node.");
		click(ELEMENT_OK_BUTTON);

		//delete data
		deleteDocument(bDocDes);
		deleteDocument(bDoc);
	}
	//Cut an article and paste it to a file plan!
	@Test
	public void test07_CutArticlePasteToFilePlan() {
		String title="ECMS_DMS_SE_BasicAction_CutPaste_07";
		By bDoc=By.xpath("//a[@title='"+title+" ']");
		By.xpath("//div[@title='"+title+"']");
		String titleDes="ECMS_DMS_SE_BasicAction_CutPaste_07_des";
		By bDocDes=By.xpath("//a[@title='"+ titleDes +" ']");
		info("Cut an article and paste it to a file plan!");
		//create a file plan -destination
		goToAddNewContent();
		createNewFilePlan(titleDes, titleDes, titleDes, titleDes, titleDes);
		waitForElementPresent(bDocDes);

		click(ELEMENT_ROOT);
		waitForElementPresent(By.xpath("//input[@value='/']"));

		//create an article - source
		goToAddNewContent();
		createNewArticle(title, title, title, title);
		waitForElementPresent(bDoc);

		//cut and paste the article to a file plan
		cutAndPasteNode(bDoc, bDocDes);

		//verify cannot paste article in a file plan
		waitForTextPresent("Cannot paste the copied node type on the current node.");
		click(ELEMENT_OK_BUTTON);

		//delete data
		deleteDocument(bDocDes);
		deleteDocument(bDoc);
	}

	//Cut an article and paste it to a file document!
	@Test
	public void test08_CutArticlePasteToFileDocument() {
		String title="ECMS_DMS_SE_BasicAction_CutPaste_08";
		By bDoc=By.xpath("//a[@title='"+title+" ']");
		By.xpath("//div[@title='"+title+"']");
		String titleDes="ECMS_DMS_SE_BasicAction_CutPaste_08_des";
		By bDocDes=By.xpath("//a[@title='"+ titleDes +" ']");

		info("Cut an article and paste it to a file document!");
		//create a file document -destination
		goToAddNewContent();
		createNewFile(titleDes, titleDes, titleDes);
		waitForElementPresent(bDocDes);

		click(ELEMENT_ROOT);
		pause(500);
		waitForElementPresent(By.xpath("//input[@value='/']"));

		//create an article - source
		goToAddNewContent();
		createNewArticle(title, title, title, title);
		waitForElementPresent(bDoc);

		//cut and paste the article to a file document
		cutAndPasteNode(bDoc, bDocDes);

		//verify if paste article in a file document
		waitForElementNotPresent(bDoc);
		checkPreferenceOption(ELEMENT_ENABLE_DMS_STRUCTURE);
		goToNode(bDocDes);
		goToNode(bDoc);
		waitForElementPresent(By.xpath("//input[contains(@value,'"+titleDes+ "/" + title + "')]"));

		//delete data
		deleteDocument(bDocDes);
	}
	//Cut an article and paste it to a podcast!
	@Test
	public void test08_CutArticlePasteToPodcast() {
		String title="ECMS_DMS_SE_BasicAction_CutPaste_08";
		By bDoc=By.xpath("//a[@title='"+title+" ']");
		By.xpath("//div[@title='"+title+"']");
		String titleDes="ECMS_DMS_SE_BasicAction_CutPaste_08_des";
		By bDocDes=By.xpath("//a[@title='"+ titleDes +" ']");

		info("Cut an article and paste it to a podcast!");
		//create a podcast -destination
		goToAddNewContent();
		createNewPodcast(titleDes, titleDes, titleDes);
		waitForElementPresent(bDocDes);

		click(ELEMENT_ROOT);
		pause(500);
		waitForElementPresent(By.xpath("//input[@value='/']"));

		//create an article - source
		goToAddNewContent();
		createNewArticle(title, title, title, title);
		waitForElementPresent(bDoc);

		//cut and paste the article to a podcast
		cutAndPasteNode(bDoc, bDocDes);

		//verify if paste article in a podcast
		waitForElementNotPresent(bDoc);
		checkPreferenceOption(ELEMENT_ENABLE_DMS_STRUCTURE);
		goToNode(bDocDes);
		goToNode(bDoc);
		waitForElementPresent(By.xpath("//input[contains(@value,'"+titleDes+ "/" + title + "')]"));

		//delete data
		deleteDocument(bDocDes);
	}
	//Cut an article and paste it to a kofax!
	@Test
	public void test08_CutArticlePasteToKofax() {
		String title="ECMS_DMS_SE_BasicAction_CutPaste_08";
		By bDoc=By.xpath("//a[@title='"+title+" ']");
		By.xpath("//div[@title='"+title+"']");
		String titleDes="ECMS_DMS_SE_BasicAction_CutPaste_08_des";
		By bDocDes=By.xpath("//a[@title='"+ titleDes +" ']");

		info("Cut an article and paste it to a kofax!");
		//create a kofax -destination
		goToAddNewContent();
		createNewKofax(titleDes);
		waitForElementPresent(bDocDes);

		click(ELEMENT_ROOT);
		pause(500);
		waitForElementPresent(By.xpath("//input[@value='/']"));

		//create an article - source
		goToAddNewContent();
		createNewArticle(title, title, title, title);
		waitForElementPresent(bDoc);

		//cut and paste the article to a kofax
		cutAndPasteNode(bDoc, bDocDes);

		//verify if paste article in a kofax
		waitForElementNotPresent(bDoc);
		checkPreferenceOption(ELEMENT_ENABLE_DMS_STRUCTURE);
		goToNode(bDocDes);
		goToNode(bDoc);
		waitForElementPresent(By.xpath("//input[contains(@value,'"+titleDes+ "/" + title + "')]"));

		//delete data
		deleteDocument(bDocDes);
	}
	//Cut an article and paste it to an uploaded file!
	@Test
	public void test08_CutArticlePasteToUploadedFile() {
		String title="ECMS_DMS_SE_BasicAction_CutPaste_08";
		By bDoc=By.xpath("//a[@title='"+title+" ']");
		By.xpath("//div[@title='"+title+"']");
		String titleDes="ECMS_DMS_SE_BasicAction_CutPaste_08_des";
		By bDocDes=By.xpath("//a[@title='"+ titleDes +".png ']");
		String img="TestData/ECMS_DMS_SE_BasicAction_CutPaste.png";

		info("Cut an article and paste it to an uploaded file!");
		//create an uploaded file -destination
		uploadFile(titleDes, img);
		waitForElementPresent(bDocDes);

		click(ELEMENT_ROOT);
		pause(500);
		waitForElementPresent(By.xpath("//input[@value='/']"));

		//create an article - source
		goToAddNewContent();
		createNewArticle(title, title, title, title);
		waitForElementPresent(bDoc);

		//cut and paste the article to an uploaded file
		cutAndPasteNode(bDoc, bDocDes);

		//verify if paste article in a uploaded file
		waitForElementNotPresent(bDoc);
		checkPreferenceOption(ELEMENT_ENABLE_DMS_STRUCTURE);
		goToNode(bDocDes);
		goToNode(bDoc);
		waitForElementPresent(By.xpath("//input[contains(@value,'"+titleDes+ ".png/" + title + "')]"));

		//delete data
		deleteDocument(bDocDes);
	}

	//Cut a file document and paste it to a node!
	@Test
	public void test09_CutFilePasteToANode() {
		String title="ECMS_DMS_SE_BasicAction_CutPaste_09";
		By bDoc=By.xpath("//a[@title='"+title+" ']");
		By.xpath("//div[@title='"+title+"']");
		String titleDes="ECMS_DMS_SE_BasicAction_CutPaste_09_des";
		By bDocDes=By.xpath("//a[@title='"+ titleDes +" ']");

		info("Cut a file document and paste it to a node!");
		//create a content folder -destination
		createNewContentFolder(titleDes, titleDes);
		waitForElementPresent(bDocDes);

		//create a file document - source
		goToAddNewContent();
		createNewFile(title, title, title);
		waitForElementPresent(bDoc);

		//cut and paste the file document to a node
		cutAndPasteNode(bDoc, bDocDes);

		//verify if paste file document in a node
		waitForElementNotPresent(bDoc);
		goToNode(bDocDes);
		goToNode(bDoc);
		waitForElementPresent(By.xpath("//input[contains(@value,'"+titleDes+ "/" + title + "')]"));

		//delete data
		deleteDocument(bDocDes);
	}

	//Cut a podcast and paste it to a node!
	@Test
	public void test10_CutPodcastPasteToANode() {
		String title="ECMS_DMS_SE_BasicAction_CutPaste_10";
		By bDoc=By.xpath("//a[@title='"+title+" ']");
		By.xpath("//div[@title='"+title+"']");
		String titleDes="ECMS_DMS_SE_BasicAction_CutPaste_10_des";
		By bDocDes=By.xpath("//a[@title='"+ titleDes +" ']");

		info("Cut a podcast and paste it to a node!");
		//create a file - destination
		goToAddNewContent();
		createNewFile(titleDes, titleDes, titleDes);
		waitForElementPresent(bDocDes);

		//pause(500);
		click(ELEMENT_ROOT);
		pause(500);
		waitForElementPresent(By.xpath("//input[@value='/']"));

		//create a podcast - source
		goToAddNewContent();
		createNewPodcast(title, title, title);
		waitForElementPresent(bDoc);

		//cut and paste the podcast to a node
		cutAndPasteNode(bDoc, bDocDes);

		//verify if paste the podcast in a node
		waitForElementNotPresent(bDoc);
		checkPreferenceOption(ELEMENT_ENABLE_DMS_STRUCTURE);
		goToNode(bDocDes);
		goToNode(bDoc);
		waitForElementPresent(By.xpath("//input[contains(@value,'"+titleDes+ "/" + title + "')]"));

		//delete data
		deleteDocument(bDocDes);
	}
	//Cut a sample node and paste it to a content folder!
	@Test
	public void test11_CutSampleNodePasteToContentFolder() {
		String title="ECMS_DMS_SE_BasicAction_CutPaste_11";
		By bDoc=By.xpath("//a[@title='"+title+" ']");
		By.xpath("//div[@title='"+title+"']");
		String titleDes="ECMS_DMS_SE_BasicAction_CutPaste_11_des";
		By bDocDes=By.xpath("//a[@title='"+ titleDes +" ']");
		String img="TestData/ECMS_DMS_SE_BasicAction_CutPaste.png";

		info("Cut a sample node and paste it to a node!");
		//create a content folder -destination
		createNewContentFolder(titleDes, titleDes);
		waitForElementPresent(bDocDes);

		//create a sample node - source
		goToAddNewContent();
		createNewSampleNode(title, title, img);
		waitForElementPresent(bDoc);

		//cut and paste the sample node to a content folder
		cutAndPasteNode(bDoc, bDocDes);

		//verify if paste sample node in a content folder
		waitForElementNotPresent(bDoc);
		goToNode(bDocDes);
		goToNode(bDoc);
		waitForElementPresent(By.xpath("//input[contains(@value,'"+titleDes+ "/" + title + "')]"));

		//delete data
		deleteDocument(bDocDes);
	}
	//Cut a sample node and paste it to a document folder!
	@Test
	public void test12_CutSampleNodePasteToDocumentFolder() {
		String title="ECMS_DMS_SE_BasicAction_CutPaste_12";
		By bDoc=By.xpath("//a[@title='"+title+" ']");
		By.xpath("//div[@title='"+title+"']");
		String titleDes="ECMS_DMS_SE_BasicAction_CutPaste_12_des";
		By bDocDes=By.xpath("//a[@title='"+ titleDes +" ']");
		String img="TestData/ECMS_DMS_SE_BasicAction_CutPaste.png";

		info("Cut a sample node and paste it to a document folder!");
		//create a document folder -destination
		createNewDocumentFolder(titleDes, titleDes);
		waitForElementPresent(bDocDes);

		//create a sample node - source
		goToAddNewContent();
		createNewSampleNode(title, title, img);
		waitForElementPresent(bDoc);

		//cut and paste the sample node to a document folder
		cutAndPasteNode(bDoc, bDocDes);

		//verify cannot paste sample node in a document folder
		waitForTextPresent("Cannot paste the copied node type on the current node.");
		click(ELEMENT_OK_BUTTON);

		//delete data
		deleteDocument(bDocDes);
		deleteDocument(bDoc);
	}
	//Cut a sample node and paste it to a file plan!
	@Test
	public void test13_CutSampleNodePasteToFilePlan() {
		String title="ECMS_DMS_SE_BasicAction_CutPaste_13";
		By bDoc=By.xpath("//a[@title='"+title+" ']");
		By.xpath("//div[@title='"+title+"']");
		String titleDes="ECMS_DMS_SE_BasicAction_CutPaste_13_des";
		By bDocDes=By.xpath("//a[@title='"+ titleDes +" ']");
		String img="TestData/ECMS_DMS_SE_BasicAction_CutPaste.png";

		info("Cut a sample node and paste it to a file plan!");
		//create a file plan -destination
		goToAddNewContent();
		createNewFilePlan(titleDes, titleDes, titleDes, titleDes, titleDes);
		waitForElementPresent(bDocDes);

		click(ELEMENT_ROOT);
		pause(500);
		waitForElementPresent(By.xpath("//input[@value='/']"));

		//create a sample node - source
		goToAddNewContent();
		createNewSampleNode(title, title, img);
		waitForElementPresent(bDoc);

		//cut and paste the sample node to a file plan
		cutAndPasteNode(bDoc, bDocDes);

		//verify cannot paste sample node in a file plan
		waitForTextPresent("Cannot paste the copied node type on the current node.");
		click(ELEMENT_OK_BUTTON);

		//delete data
		deleteDocument(bDocDes);
		deleteDocument(bDoc);
	}

	//Cut a sample node and paste it to a kofax document!
	@Test
	public void test14_CutSampleNodePasteToKofax() {
		String title="ECMS_DMS_SE_BasicAction_CutPaste_14";
		By bDoc=By.xpath("//a[@title='"+title+" ']");
		By.xpath("//div[@title='"+title+"']");
		String titleDes="ECMS_DMS_SE_BasicAction_CutPaste_14_des";
		By bDocDes=By.xpath("//a[@title='"+ titleDes +" ']");
		String img="TestData/ECMS_DMS_SE_BasicAction_CutPaste.png";

		info("Cut a sample node and paste it to a kofax document!");

		//create a sample node - source
		goToAddNewContent();
		createNewSampleNode(title, title, img);
		waitForElementPresent(bDoc);

		click(ELEMENT_ROOT);
		pause(500);
		waitForElementPresent(By.xpath("//input[@value='/']"));

		//create a kofax document -destination
		goToAddNewContent();
		createNewKofax(titleDes);
		waitForElementPresent(bDocDes);

		//cut and paste the sample node to a kofax document
		cutAndPasteNode(bDoc, bDocDes);

		//verify cannot paste sample node in a kofax document
		waitForElementNotPresent(bDoc);
		goToNode(titleDes);
		goToNode(title);
		waitForElementPresent(By.xpath("//input[contains(@value,'"+titleDes+ "/" + title + "')]"));

		//delete data
		deleteDocument(bDocDes);
	}
	//Cut a file plan and paste it to a folder!
	@Test
	public void test15_CutFilePlanPasteToFolder() {
		String title="ECMS_DMS_SE_BasicAction_CutPaste_15";
		By bDoc=By.xpath("//a[@title='"+title+" ']");
		By.xpath("//div[@title='"+title+"']");
		String titleDes="ECMS_DMS_SE_BasicAction_CutPaste_15_des";
		By bDocDes=By.xpath("//a[@title='"+ titleDes +" ']");

		info("Cut a file plan and paste it to a folder document!");

		//create a file plan - source
		goToAddNewContent();
		createNewFilePlan(title, title, title,title,title);
		waitForElementPresent(bDoc);

		click(ELEMENT_ROOT);
		pause(500);
		waitForElementPresent(By.xpath("//input[@value='/']"));

		//create a content folder -destination
		createNewContentFolder(titleDes, titleDes);
		waitForElementPresent(bDocDes);

		//cut and paste the file plan to a file plan document
		cutAndPasteNode(bDoc, bDocDes);

		//verify if paste file plan document in a content folder
		waitForElementNotPresent(bDoc);
		goToNode(titleDes);
		waitForElementPresent("//div[@title='"+title+"']");

		//delete data
		deleteDocument(bDocDes);
	}
	//Cut a file plan and paste it to an article!
	@Test
	public void test16_CutFilePlanPasteToArticle() {
		String title="ECMS_DMS_SE_BasicAction_CutPaste_16";
		By bDoc=By.xpath("//a[@title='"+title+" ']");
		By.xpath("//div[@title='"+title+"']");
		String titleDes="ECMS_DMS_SE_BasicAction_CutPaste_16_des";
		By bDocDes=By.xpath("//a[@title='"+ titleDes +" ']");

		info("Cut a file plan and paste it to an article document!");

		//create a file plan - source
		goToAddNewContent();
		createNewFilePlan(title, title, title,title,title);
		waitForElementPresent(bDoc);

		click(ELEMENT_ROOT);
		pause(500);
		waitForElementPresent(By.xpath("//input[@value='/']"));

		//create an article document -destination
		goToAddNewContent();
		createNewArticle(titleDes, titleDes,titleDes,titleDes);
		waitForElementPresent(bDocDes);

		//cut and paste the file plan to an article document
		cutAndPasteNode(bDoc, bDocDes);

		//verify cannot paste file plan document in an article
		waitForTextPresent("Cannot paste the copied node type on the current node.");
		click(ELEMENT_OK_BUTTON);

		//delete data
		deleteDocument(bDocDes);
		deleteDocument(bDoc);
	}
	//Cut a file plan and paste it to a free layout webcontent!
	@Test
	public void test17_CutFilePlanPasteToFreeLayoutContent() {
		String title="ECMS_DMS_SE_BasicAction_CutPaste_17";
		By bDoc=By.xpath("//a[@title='"+title+" ']");
		By.xpath("//div[@title='"+title+"']");
		String titleDes="ECMS_DMS_SE_BasicAction_CutPaste_17_des";
		By bDocDes=By.xpath("//a[@title='"+ titleDes +" ']");
		String img = "TestData/ECMS_DMS_SE_BasicAction_CutPaste.png";

		info("Cut a file plan and paste it to a free layout webcontent document!");

		//create a file plan - source
		goToAddNewContent();
		createNewFilePlan(title, title, title,title,title);
		waitForElementPresent(bDoc);

		click(ELEMENT_ROOT);
		pause(500);
		waitForElementPresent(By.xpath("//input[@value='/']"));

		//create a free layout web content -destination
		goToAddNewContent();
		createNewFreeLayoutWebContent(titleDes, titleDes, titleDes,img, titleDes,titleDes, titleDes);
		waitForElementPresent(bDocDes);

		//cut and paste the file plan to a free layout webcontent document
		cutAndPasteNode(bDoc, bDocDes);

		//verify if paste file plan document in a free layout webcontent
		waitForElementNotPresent(bDoc);
		checkPreferenceOption(ELEMENT_ENABLE_DMS_STRUCTURE);
		goToNode(titleDes);
		waitForElementPresent("//div[@title='"+title+"']");

		//delete data
		deleteDocument(bDocDes);
	}
	//Cut a kofax document and paste it to a document folder!
	@Test
	public void test18_CutKofaxPasteToDocumentFolder() {
		String title="ECMS_DMS_SE_BasicAction_CutPaste_18";
		By bDoc=By.xpath("//a[@title='"+title+" ']");
		By.xpath("//div[@title='"+title+"']");
		String titleDes="ECMS_DMS_SE_BasicAction_CutPaste_18_des";
		By bDocDes=By.xpath("//a[@title='"+ titleDes +" ']");

		info("Cut a kofax document and paste it to a document folder!");

		//create a kofax document - source
		goToAddNewContent();
		createNewKofax(title);
		waitForElementPresent(bDoc);

		click(ELEMENT_ROOT);
		pause(500);
		waitForElementPresent(By.xpath("//input[@value='/']"));

		//create a document folder -destination
		createNewDocumentFolder(titleDes, titleDes);
		waitForElementPresent(bDocDes);

		//cut and paste the kofax document to a document folder
		cutAndPasteNode(bDoc, bDocDes);

		//verify cannot paste the kofax document in a document folder
		waitForTextPresent("Cannot paste the copied node type on the current node.");
		click(ELEMENT_OK_BUTTON);

		//delete data
		deleteDocument(bDocDes);
		deleteDocument(bDoc);
	}
	//Cut a kofax document and paste it to a sample node!
	@Test
	public void test19_CutKofaxPasteToSampleNode() {
		String title="ECMS_DMS_SE_BasicAction_CutPaste_19";
		By bDoc=By.xpath("//a[@title='"+title+" ']");
		By.xpath("//div[@title='"+title+"']");
		String titleDes="ECMS_DMS_SE_BasicAction_CutPaste_19_des";
		By bDocDes=By.xpath("//a[@title='"+ titleDes +" ']");
		String img = "TestData/ECMS_DMS_SE_BasicAction_CutPaste.png";

		info("Cut a kofax document and paste it to a sample node!");

		//create a kofax document - source
		goToAddNewContent();
		createNewKofax(title);
		waitForElementPresent(bDoc);

		click(ELEMENT_ROOT);
		pause(500);
		waitForElementPresent(By.xpath("//input[@value='/']"));

		//create a sample node -destination
		goToAddNewContent();
		createNewSampleNode(titleDes, titleDes,img);
		waitForElementPresent(bDocDes);

		//cut and paste the kofax document to a sample node
		cutAndPasteNode(bDoc, bDocDes);

		//verify if paste kofax document in a sample
		waitForElementNotPresent(bDoc);
		checkPreferenceOption(ELEMENT_ENABLE_DMS_STRUCTURE);
		goToNode(titleDes);
		waitForElementPresent("//div[@title='"+title+"']");

		//delete data
		deleteDocument(bDocDes);
	}
	//Cut an uploaded file and paste it to an article!
	@Test
	public void test20_CutUploadedFilePasteToArticle() {
		String title="ECMS_DMS_SE_BasicAction_CutPaste_20";
		By bDoc=By.xpath("//a[@title='"+title+".png ']");
		By.xpath("//div[@title='"+title+"']");
		String titleDes="ECMS_DMS_SE_BasicAction_CutPaste_20_des";
		By bDocDes=By.xpath("//a[@title='"+ titleDes +" ']");
		String img = "TestData/ECMS_DMS_SE_BasicAction_CutPaste.png";

		info("Cut an uploaded file and paste it to an article!");

		//upload an uploaded file - source
		uploadFile(title,img);
		waitForElementPresent(bDoc);

		click(ELEMENT_ROOT);
		pause(500);
		waitForElementPresent(By.xpath("//input[@value='/']"));

		//create an article -destination
		goToAddNewContent();
		createNewArticle(titleDes, titleDes,titleDes,titleDes);
		waitForElementPresent(bDocDes);

		//cut and paste the uploaded file to an article
		cutAndPasteNode(bDoc, bDocDes);

		//verify if paste uploaded file in an article
		waitForElementNotPresent(bDoc);
		checkPreferenceOption(ELEMENT_ENABLE_DMS_STRUCTURE);
		goToNode(titleDes);
		waitForElementPresent("//div[@title='"+title+".png']");

		//delete data
		deleteDocument(bDocDes);
	}

	//Cut a checked in node and paste it to a content folder!
	@Test
	public void test21_CutCheckInNodePasteToContentFolder() {
		String title="ECMS_DMS_SE_BasicAction_CutPaste_21";
		By bDoc=By.xpath("//a[@title='"+title+" ']");
		By.xpath("//div[@title='"+title+"']");
		String titleDes="ECMS_DMS_SE_BasicAction_CutPaste_21_des";
		By bDocDes=By.xpath("//a[@title='"+ titleDes +" ']");

		info("Cut a checkin node and paste it to a content folder!");

		//create a file document
		goToAddNewContent();
		createNewFile(title, title, title);
		waitForElementPresent(bDoc);
		checkInNode(bDoc);

		//add icon Version to action bar
		addFunctionToActionBar(ELEMENT_CHECKBOX_VERSION);
		goToSiteExplorer();
		chooseDrive(ELEMENT_DRIVE_MANAGE_SITE);
		addVersionForNode(bDoc,"version file");

		click(ELEMENT_ROOT);
		pause(500);
		waitForElementPresent(By.xpath("//input[@value='/']"));

		//create a content folder -destination
		createNewContentFolder(titleDes, titleDes);
		waitForElementPresent(bDocDes);

		//cut and paste the uploaded file to an article
		cutAndPasteNode(bDoc, bDocDes);

		//verify if paste the uploaded file in an article
		waitForElementNotPresent(bDoc);
		goToNode(titleDes);
		waitForElementPresent("//div[@title='"+title+"']");

		//delete data
		deleteDocument(bDocDes);
	}	

	@BeforeMethod
	public void beforeMethod() {
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		loginEcms(USER, PASS);
		goToSiteExplorer();
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}
