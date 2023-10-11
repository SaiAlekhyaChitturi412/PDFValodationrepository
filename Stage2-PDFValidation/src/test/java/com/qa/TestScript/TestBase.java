package com.qa.TestScript;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Driver;
import java.util.concurrent.TimeUnit;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.Pages.FileWebpage;
import com.qa.Resources.Configuration.ConfigProperties;

import io.github.bonigarcia.wdm.WebDriverManager;
public class TestBase 
{
	public WebDriver driver;
	public PDDocument pdfdocument;
	public String filepath;	
	
	@BeforeClass
	public void Setup() throws IOException, InterruptedException
	{
		ConfigProperties.initializePropertyFile();	
		String sourceOfFile =  ConfigProperties.property.getProperty("SourceFile");
		if(sourceOfFile.equals("local"))
		{
			File file = new File("C:\\Users\\CHITTURI\\Downloads\\sample.pdf");
			FileInputStream inputFile = new FileInputStream(file);
			pdfdocument = PDDocument.load(inputFile);
			filepath = "C:\\\\Users\\\\CHITTURI\\\\Downloads\\\\sample.pdf";
//			System.out.println("No of pages inside the document is "+pdfdocument.getPages().getCount());
//			PDFTextStripper pdftextstriper = new PDFTextStripper();
//			String documentname = pdftextstriper.getText(pdfdocument);
////			System.out.println(documentname);
////			Assert.assertTrue(documentname.startsWith("DHARSHINY S"));
//			pdfdocument.close();
//			inputFile.close();
		}
		else
		{
			String path = ConfigProperties.property.getProperty("ChromeDriverPath");			
			System.setProperty("webdriver.chrome.driver",path);
			driver = new ChromeDriver();
			String URL = ConfigProperties.property.getProperty("URL");
			driver.get(URL);
			filepath = URL;
			URL pdfurl = new URL(URL);
			InputStream ip = pdfurl.openStream();
			BufferedInputStream bf = new BufferedInputStream(ip);
			pdfdocument = PDDocument.load(bf);
			driver.manage().window().maximize();			
			Thread.sleep(10000);	
			
		}	
		
	}
	
	
	
	
	
	
	@AfterClass
	public void afterClass()
	{
		driver.close();
	}
	
	
	
}
