package com.esg.utilities;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReport extends BaseClass {
	public static String element;

	public static void initialization() {
		Report = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReport.html", true);
		// test = Report.startTest(Test);
	}

	/*public static void ExecutingAgainst(String Fname, String Version) {
		test.log(LogStatus.INFO, "Test is running against the version = "+ReadConfig.ReadFile(Fname, "BaseURL")
				+ ReadConfig.ReadFile(Fname, Version) + "/");
	}*/
	
	public static void ExecutingAgainst(String Fname, String Version) {
		test.log(LogStatus.INFO, "Test is running against the version = "+ReadConfig.ReadFile(Fname, "BaseURL"));
	}

	/*
	  public static void getStatusWithScreenshot(ITestResult result) throws
	  IOException { if(result.getStatus() == ITestResult.FAILURE) { String
	  screenShotPath = GetScreenShot.capture(driver); // test.log(LogStatus.FAIL,
	  result.getThrowable()); test.log(LogStatus.FAIL, "Snapshot below: " +
	  test.addScreenCapture(screenShotPath)); } Report.endTest(test); }
	 */
	public static void TextBoxvalue(String PageSourceContent, String ActualComment, String ExpectedComment,
			String PassLogs, String FailLogs, String filename, String webelement) {
		if (driver.getPageSource().contains(PageSourceContent)) {
			WebElement textBox = Xpath(filename, webelement);
			String value = textBox.getAttribute("value");
			test.log(LogStatus.PASS, PassLogs + ".     " + "The Validated text is  --->   " + value);
		} else {
			test.log(LogStatus.FAIL, FailLogs);
			Assert.assertEquals(ExpectedComment, ActualComment);
		}
	}

	public static void defineLogs(String PageSourceContent, String ActualComment, String ExpectedComment,
			String PassLogs, String FailLogs, String filename, String webelement) {
		
		element = Xpath(filename, webelement).getText();
		
		if (driver.getPageSource().contains(PageSourceContent)) {
			test.log(LogStatus.PASS, PassLogs + ".     " + "The Validated element is  --->   " + element);
		} else {
			test.log(LogStatus.FAIL,FailLogs+".     "+"The Actual Value of the Validated element is  --->   "+element);
			Assert.assertEquals(ExpectedComment, ActualComment);
		}
	}
	
	public static void defineLogswith_Negetive_Scenario(String PageSourceContent, String ActualComment, String ExpectedComment,
			String PassLogs, String FailLogs, String filename, String webelement) {
		
		element = Xpath(filename, webelement).getText();
		
		if (driver.getPageSource().contains(PageSourceContent)) {
			test.log(LogStatus.PASS, PassLogs + ".     " + "The Validated element is  --->   " + element);
		} else {
			test.log(LogStatus.WARNING,FailLogs+".     "+"The Actual Value of the Validated element is  --->   "+element);
			Assert.assertEquals(ExpectedComment, ActualComment);
		}
	}
	
	public static void VerifyElementForExistingValueWithText(String filename, String webelement, String ExcelFname, String Sheetname, int Rowno, int Colno) {
		 
		 String Value = Xpath(filename, webelement).getText();
		 String Givenelement = ReadExcel.readData(ExcelFname, Sheetname, Rowno, Colno);
		 if(Value.toString().contains(Givenelement)) {
			 test.log(LogStatus.PASS, ".     "+"The Given value   :-  "+ Givenelement+",  is match with the reflected Value   :-  "+ Value); 
		 }
		 else
			{				
				test.log(LogStatus.FAIL, ".     "+"The Given value   :-  "+Givenelement+", does not match with the reflected Value   :-  "+ Value);
			}
		}
	public static void VerifyElementForExistingValueWithAttribute(String filename, String webelement, String ExcelFname, String Sheetname, int Rowno, int Colno) {
		 
		 String Value = Xpath(filename, webelement).getAttribute("value");
		 String Givenelement = ReadExcel.readData(ExcelFname, Sheetname, Rowno, Colno);
		 if(Givenelement.equalsIgnoreCase(Value)) {
			 test.log(LogStatus.PASS, ".     "+"The Given value   :-  "+ Givenelement+",  is match with the reflected Value   :-  "+ Value); 
		 }
		 else
			{				
				test.log(LogStatus.FAIL, ".     "+"The Given value   :-  "+Givenelement+", does not match with the reflected Value   :-  "+ Value);
			}
		}
	public static void VerifyElementForExistingValueWithCSSValue(String filename, String webelement, String ExcelFname, String Sheetname, int Rowno, int Colno) {
		 
		 String Value = Xpath(filename, webelement).getCssValue("value");
		 String Givenelement = ReadExcel.readData(ExcelFname, Sheetname, Rowno, Colno);
		 if(Givenelement.equalsIgnoreCase(Value)) {
			 test.log(LogStatus.PASS, ".     "+"The Given value   :-  "+ Givenelement+",  is match with the reflected Value   :-  "+ Value); 
		 }
		 else
			{				
				test.log(LogStatus.FAIL, ".     "+"The Given value   :-  "+Givenelement+", does not match with the reflected Value   :-  "+ Value);
			}
		}
	
	public static void ValidateWithElement(String filename, String Path, String Text, String ExpectedComment, String ActualComment,  String PassLogs, String FailLogs) {
		 
		 String Value = driver.findElement(By.xpath(ReadConfig.ReadFile(filename, Path))).getText();
		 element = Xpath(filename, Path).getText();
		 if(Value.toString().contains(Text)) {
			 test.log(LogStatus.PASS,PassLogs+".     "+"The Actual Value of the Validated element is  --->   "+element); 
		 }
		 else
			{				
				test.log(LogStatus.FAIL,FailLogs+".     "+"The Actual Value of the Validated element is  --->   "+element);
				Assert.assertEquals(ExpectedComment, ActualComment);
			}
		}
	public static void validationWithelementVisibility(String filename,String webelement, String FailLogs, String PassLogs) 
	{
		boolean element=Xpath(filename, webelement).isEnabled();
		if( element= true)
		{
			test.log(LogStatus.PASS,PassLogs+".     "+"The Actual Value of the Validated element is  --->   "+element);

		}
		else if(element=false) {
			test.log(LogStatus.INFO,PassLogs+".     "+"The Actual Value of the Validated element is  --->   "+element);
		}
		else
		{				
			test.log(LogStatus.FAIL,FailLogs+".     "+"The Actual Value of the Validated element is  --->   "+element);
	 
		}
	}
	public static void validationWithWebelement(String filename,String webelement,String PassLogs, String FailLogs) 
	{
		String element=Xpath(filename, webelement).getText();
		if( element!= null)
		{
			
			test.log(LogStatus.PASS,PassLogs+".     "+"The Actual Value of the Validated element is  --->   "+element);
		}
		else
		{				
				
			test.log(LogStatus.FAIL,FailLogs+".     "+"The Actual Value of the Validated element is  --->   "+element);			 
		}
	}

	public static void ReadPopUp() {

		Alert alt = driver.switchTo().alert();
		String PopUp = alt.getText();
		System.out.println(PopUp);
		if (PopUp.equalsIgnoreCase(PopUp)) {
			test.log(LogStatus.PASS, PopUp);
			alt.accept();
		} else {
			test.log(LogStatus.FAIL, PopUp);
			Assert.assertEquals(PopUp, PopUp);
			alt.accept();

		}
	}
		public static void Pass_Message(String PassComment) {
				test.log(LogStatus.INFO, PassComment);
				
			} 
		 

		public static void Verify_Button_Enabled(String filename, String Xpath, String PassLog, String FailLog) {
			WebElement Element = Xpath(filename, Xpath);
			if (Element.isEnabled()) {
				
				test.log(LogStatus.PASS, PassLog);
				
			} else {
				test.log(LogStatus.FAIL, FailLog);
			}
		}
		
		public static void Verify_Button_Disabled(String filename, String Xpath, String PassLog, String FailLog) {
			WebElement Element = Xpath(filename, Xpath);
			if (Element.isEnabled()) {
				
				test.log(LogStatus.FAIL, FailLog);
				
			} else {
				test.log(LogStatus.PASS, PassLog);
			}
		}
		
		public static void VerifyDataFromExcelWithAttribute(String filename, String webelement, String ExcelFname, String Sheetname, int Rowno, int Colno) {
			 
			 String Value = Xpath(filename, webelement).getAttribute("value");
			 String Givenelement = ReadExcel.CanvassingData(ExcelFname, Sheetname, Rowno, Colno);
			 if(Givenelement.equalsIgnoreCase(Value)) {
				 test.log(LogStatus.PASS, ".     "+"The Given value   :-  "+ Givenelement+",  is match with the reflected Value   :-  "+ Value); 
			 }
			 else
				{				
					test.log(LogStatus.FAIL, ".     "+"The Given value   :-  "+Givenelement+", does not match with the reflected Value   :-  "+ Value);
				}
			}
		
		public static void VerifyDataFromExcel(String filename, String webelement, String ExcelFname, String Sheetname, int Rowno, int Colno) {
			
			 String Value = Xpath(filename, webelement).getText();
			 String Givenelement = ReadExcel.CanvassingData(ExcelFname, Sheetname, Rowno, Colno);
			 if(Value.toString().contains(Givenelement)) {
				 test.log(LogStatus.PASS, ".     "+"The Given value   :-  "+ Givenelement+",  is match with the reflected Value   :-  "+ Value); 
			 }
			 else
				{				
					test.log(LogStatus.FAIL, ".     "+"The Given value   :-  "+Givenelement+", does not match with the reflected Value   :-  "+ Value);
				}
			}
		
	public static void generateReport() {
		// driver.close();
		Report.flush();
		// Report.close();

	}
}
