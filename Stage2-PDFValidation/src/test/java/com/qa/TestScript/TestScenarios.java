package com.qa.TestScript;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;
import org.testng.annotations.Test;


public class TestScenarios extends TestBase
{

	@Test(priority=1)
	public void VerifyFileFormat()
	{
		String FileExtension = getFileExtension(filepath);
		if (FileExtension.equalsIgnoreCase("pdf")) {
            System.out.println("The file is a PDF.");
        } else {
            System.out.println("The file is not a PDF.");
        }
	}
	public String getFileExtension(String filepath)
	{
		String extension = "";
        int lastDotIndex = filepath.lastIndexOf(".");
        if (lastDotIndex > 0) {
            extension = filepath.substring(lastDotIndex + 1);
        }
        return extension;
		
	}
	
	@Test(priority=2)
	
	public void ValidateVersionofPDF()
	{
		float version = pdfdocument.getVersion();
		System.out.println("PDF Version: " + version);
         // pdfdocument.close();
	}
  
	@Test(priority=3)
	
	public void CheckPasswordProtectionofPDF()
	{
		if (isPasswordProtected(pdfdocument)) {
            System.out.println("The PDF is password-protected.");
        } else {
            System.out.println("The PDF is not password-protected.");
        }
	}
	
	public static boolean isPasswordProtected(PDDocument document) {
        AccessPermission accessPermission = document.getCurrentAccessPermission();
        return accessPermission.isOwnerPermission();
    }

	@Test(priority=4)
	public void ContentValidation() throws IOException
	{
		int pagecount = pdfdocument.getNumberOfPages();
		System.out.println(pagecount);
		Assert.assertEquals(pagecount,4);
		PDFTextStripper pdftextstriper = new PDFTextStripper();
		String pdfText = pdftextstriper.getText(pdfdocument);
		//System.out.println(pdfText);
		Assert.assertTrue(pdfText.contains("PDF BOOKMARK SAMPLE"));
		Assert.assertTrue(pdfText.contains("Sample Data File"));
		//set the page number and get text
		pdftextstriper.setStartPage(3);
		String thirdpagetext = pdftextstriper.getText(pdfdocument);
		System.out.println(thirdpagetext);
	}
	
	@Test(priority=5)
	public void ValidatingLinksandBookmarks() throws IOException
	{
		boolean found = false;
		for (PDPage page : pdfdocument.getPages()) {
            for (PDAnnotation link : page.getAnnotations()) {
                System.out.println("Link found on page " + (pdfdocument.getPages().indexOf(page) + 1));
                found = true;
                // Extract additional link properties, such as the URL, by accessing link.getAction()
            }
        }
		if(found) System.out.println("There are links in PDF");
		else System.out.println("No Links Available");
		
		
        PDDocumentOutline root = pdfdocument.getDocumentCatalog().getDocumentOutline();

        if (root != null) {
            System.out.println("The PDF contains bookmarks.");
            // You can also further process or inspect the bookmark structure if needed
        } else {
            System.out.println("The PDF does not contain bookmarks.");
        }

	}
	
}
