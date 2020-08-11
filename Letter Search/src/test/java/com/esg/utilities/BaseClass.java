package com.esg.utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseClass 
{
	public static ExtentHtmlReporter Extent;
	public static ExtentReports Report;
    public static ExtentTest test;
   
  	public static WebDriver driver;
  	
  	//static Actions act=new Actions(driver);
  	
  	static JavascriptExecutor js = (JavascriptExecutor)driver;
  	
	public static WebElement Xpath(String filename, String Xpath)	
	{
		WebElement xpath= driver.findElement(By.xpath(ReadConfig.ReadFile(filename, Xpath)));	
		return xpath;	
	}
	public static WebElement ID(String filename, String ID)	
	{
		WebElement id= driver.findElement(By.id(ReadConfig.ReadFile(filename,ID)));	
		return id;	
	}
	public static WebElement linkText(String link)	
	{
		WebElement links= driver.findElement(By.linkText(link));	
		return links;	
	}
	public static String Paths(String filename,String path)
	{
		String pat=ReadConfig.ReadFile(filename,path);		
		return pat;		
	}
	public static void openURL(String filename,String url)	
	{
		driver.get(ReadConfig.ReadFile(filename,url));				
	}
	/*public static void enterTextboxValue(String filename,String webelement,String excelfname,String sheet,int row,int column) 
	{
		Xpath(filename,webelement).sendKeys(ReadExcel.readData(excelfname, sheet, row, column));	
	}*/
	
	public static void enterTextboxValue(String filename,String webelement,String fname,String sheet,int row,int column) 
	{
		threadWait(2000);
		try {
			scrollToElement(filename, webelement);
			Xpath(filename,webelement).sendKeys(ReadExcel.readData(fname, sheet, row, column));
			String TextValue = GetText(filename, webelement);
			String AttributeValue =GetAttribute(filename, webelement);
			String CssValue =GetCssValue(filename, webelement);
			
			String Value = TextValue+AttributeValue+CssValue;
			System.out.println(Value);
			if (Value.equalsIgnoreCase("")) {
					 WebElement complexelement1=Xpath(filename, webelement);		
						Actions act1=new Actions(driver);
						act1.moveToElement(complexelement1);
						act1.sendKeys(ReadExcel.readData(fname, sheet, row, column)).build().perform();
				
			} else {
				
					System.out.println("Text is successfully entered in the textbox using first method");
			}
			
		} catch (Exception e) {
			try {
				scrollToElement(filename, webelement);
				 WebElement complexelement1=Xpath(filename, webelement);		
					Actions act1=new Actions(driver);
					act1.moveToElement(complexelement1);
					act1.sendKeys(ReadExcel.readData(fname, sheet, row, column)).build().perform();	
			} catch (Exception e1) {

				if (driver.getPageSource().contains("Error Code")) {
					
					Enter(KeyEvent.VK_F5);
					 WebElement complexelement=Xpath(filename, webelement);		
						Actions act=new Actions(driver);
						act.moveToElement(complexelement);
						act.sendKeys(ReadExcel.readData(fname, sheet, row, column)).build().perform();	
				}
			}
		}
	}
	
	public static String GetText(String filename,String webelement) {
		threadWait(3000);
		return Xpath(filename,webelement).getText();
		
	}
	public static String GetAttribute(String filename,String webelement) {
		threadWait(3000);
		return Xpath(filename,webelement).getAttribute("value");
		
	}

public static String GetCssValue(String filename,String webelement) {
	threadWait(3000);
	return Xpath(filename,webelement).getCssValue("value");
}
	




	/*public static void selectDropdownValue(String filename,String webelement,String excelfname,String sheet,int row,int column) 
	{
		Select select=new Select(Xpath(filename,webelement));
		select.selectByValue(ReadExcel.readData(excelfname, sheet, row, column));	      		
	}
	public static void selectDropdownByVisibleText(String filename,String webelement,String excelfname,String sheet,int row,int column) 
	{
		Select select=new Select(Xpath(filename,webelement));
		select.selectByVisibleText(ReadExcel.readData(excelfname, sheet, row, column));	      		
	}*/
	public static void selectDropdownByIndex(String filename,String webelement,int indexNumber) 
	{
		Select select=new Select(Xpath(filename,webelement));
		select.selectByIndex(indexNumber);      		
	}
	
	
	public static void clickWebelement(String filename,String webelement)
	{
		//threadWait(2000);
		try {
			Xpath(filename,webelement).click();	
		} catch (Exception e) {
			try {
				Xpath(filename,webelement).sendKeys(Keys.ENTER);
			} catch (Exception e2) {
				try {
					WebElement complexelement=Xpath(filename, webelement);
					Actions act=new Actions(driver);
					act.click(complexelement).build().perform();
				} catch (Exception e3) {
					try {
						WebElement complexelement=Xpath(filename, webelement);
						js.executeScript("arguments[0].click();", complexelement);
						} finally {
							if (Xpath(filename,webelement).isDisplayed() && Xpath(filename,webelement).isEnabled()) {
								WebElement complexelement=Xpath(filename, webelement);
								Actions act=new Actions(driver);
								System.out.println("Clicked successfully still the web-element exists in the webpage");
								test.log(LogStatus.WARNING, "Clicked successfully still the web-element exists in the webpage");
								act.click(complexelement).build().perform();
							}else {
								System.out.println("Referenced Webelement is not found in the Webpage");	
							}
													
						}		
					}
				}
			}
		}
	
	public static void ClickWebelementByActionClass(String filename,String webelement) {
		WebElement complexelement=Xpath(filename, webelement);
		Actions act=new Actions(driver);
		act.moveToElement(complexelement).build().perform();
		act.click(complexelement).build().perform();
	}
	/*public static void clickWebelement(String filename,String webelement)
	{
		Xpath(filename,webelement).click();	
	}
	
	public static void clickKeyboardValue(String filename,String webelement,Keys value) 
	{
		Xpath(filename,webelement).sendKeys(value);		
	}*/
	
	public static void ClickByActionClass(String filename,String webelement,String fname,String sheet,int row,int column)
	{				
		WebElement complexelement=Xpath(filename, webelement);		
		Actions act=new Actions(driver);
		act.moveToElement(complexelement).build().perform();
		driver.findElement(By.linkText(ReadExcel.readData(fname, sheet, row, column))).click();	
	}
	
	public static void clearWebelement(String filename,String webelement)
	{
		Xpath(filename,webelement).clear();
	}
	public static void doubleClickWebelement(String filename,String webelement)
	{
		Actions action=new Actions(driver);
		action.moveToElement(Xpath(filename,webelement)).doubleClick().build().perform();	
	}
	public static void setProperty(String filename, String Browser, String browserPath)
	{		
	 System.setProperty("webdriver."+Browser+".driver",Paths(filename,browserPath)); 	 
	}
	public static void openChrome()
	{		
	 driver=new ChromeDriver();	
	}
	public static void openFirefox()
	{		
	 driver=new FirefoxDriver();	
	}
	public static void openInternetExploer()
	{		
	 driver=new InternetExplorerDriver();	
	}
	public static void maximizeWindow()
	{
		 driver.manage().window().maximize();		
	} 
	public static void implicitlywait(int time)
	{
		driver.manage().timeouts().implicitlyWait(time,TimeUnit.SECONDS);	
	}	 		
	public static void movetoElement(String filename,String webelement)
	{		 
		Actions action=new Actions(driver);	
		action.moveToElement(Xpath(filename,webelement)).build().perform();	
	}
	public static void scrollToElement(String filename,String Xpath)
	{			
	  JavascriptExecutor js = (JavascriptExecutor) driver;
	  WebElement Element=Xpath(filename, Xpath);
	  js.executeScript("arguments[0].scrollIntoView();",Element);
	 }
	
	
	public static void handlePopup()
	{	
		threadWait(2000);
		 Alert alert = driver.switchTo().alert();
         alert.accept();
        try {
        	 Enter(KeyEvent.VK_ENTER);
            System.out.println("alert was present and accepted");
        }
        catch(Exception e) {
             alert.accept();
        	 System.out.println("alert was present and accepted using Catch block");
        }
	}
	
	public static void explicitWait(int time,String filename,String weblement)
	{
		new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ReadConfig.ReadFile(filename, weblement))));	
	}
	public static void explicitWaitForClickable(int time,String filename,String weblement)
	{
		new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(By.xpath(ReadConfig.ReadFile(filename, weblement))));	
	}
	
	public static void threadWait(int time)
	{
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
		}
	}
	
	
	public static void WaitForElement(int time,String filename,String weblement) {
		try {
			//Check Presence of Element
			new WebDriverWait(driver, time).until(ExpectedConditions.presenceOfElementLocated(By.xpath(ReadConfig.ReadFile(filename, weblement))));	
			System.out.println("Element Present");
		} catch (Exception e) {
			try {
				//Check Visibility
				new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ReadConfig.ReadFile(filename, weblement))));	
				System.out.println("Element Visible");
			} catch (Exception e1) {
				try {
					//Check Clickable or not
					new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(By.xpath(ReadConfig.ReadFile(filename, weblement))));	
					System.out.println("Element Clickable");
				} finally {
							test.log(LogStatus.WARNING, "Wait for the web-element more than the expected time");
							System.out.println("Takes longer time");
							threadWait(5000);
					
				          }
			   }
		    }
		
		
	    }

	public static void passValue(String filename,String webelement,CharSequence[] value)
	{		        
        Xpath(filename, webelement).sendKeys(value);
	}
	public static void enterValueByActionClass(String filename,String webelement,String fname,String sheet,int row,int column)
	{				
		WebElement complexelement=Xpath(filename, webelement);		
		Actions act=new Actions(driver);
		act.moveToElement(complexelement);
		act.sendKeys(ReadExcel.readData(fname, sheet, row, column)).build().perform();		
	}
	
	
public static void selectDropdown(String filename,String webelement,String actionLocator, String excelfname, String sheet, int row, int column) {
		
	threadWait(2000);
		
		try {
			System.out.println("Enter First Try  Block");
				Select select=new Select(Xpath(filename,webelement));
				select.selectByVisibleText(ReadExcel.readData(excelfname, sheet, row, column));	
				
		} catch (Exception e) {
			try {
				Select select=new Select(Xpath(filename,webelement));
				select.selectByValue(ReadExcel.readData(excelfname, sheet, row, column));
			} catch (Exception e2) {
				System.out.println("Enter Second Catch  Block");
		    	clickWebelement(filename, webelement);
		    	threadWait(2000);
				enterTextboxValue(filename, actionLocator, excelfname, sheet, row, column);
				threadWait(1000);
				Enter(KeyEvent.VK_ENTER);
			}
				
			   
			}
			}


















	public static void handleMultipleWindow(String filename,String webelement)
	{	
		driver.getWindowHandle();
		clickWebelement(filename, webelement);
		/*String parentWindowHandle = driver.getWindowHandle();
		System.out.println("Parent window's handle -> " + parentWindowHandle);*/
		//clickWebelement(filename, webelement);
	}
	
	
	
	public static void Close() {
	
	driver.close();
}
public static void SwitchToFrame(String FileName, String xpath ) {
	
	WebElement frm0 = Xpath(FileName, xpath);
	driver.switchTo().frame(frm0);
}
public static void Enter(int KeyEventVKKey)  {
	
	Robot Keys;
	try {
		Keys = new Robot();
		Keys.keyPress(KeyEventVKKey);
	} catch (AWTException e) {
		System.out.println("Keyword value was not entered");
	}
}
	 public static void FileDownloaded(String downloadPath, String fileName) {
		  File dir = new File(downloadPath);
		  File[] dirContents = dir.listFiles();

		      if (dirContents.equals(fileName)) {
		    	  
		    	  System.out.println("Verified that file is downloaded");
		          //return true;
		      }
		      else {
		    	  System.out.println("Verified that file is not downloaded");
		      }
		          }
	public static void SwitchTab(String filename, String Xpath) {
		
		String parentWindowHandle = driver.getWindowHandle();
		System.out.println("parentWindowHandle"+parentWindowHandle);
		clickWebelement(filename, Xpath);
		//WaitForElement(2500);
		System.out.println(driver.getTitle());
		for(String childTab:driver.getWindowHandles()) {
		 driver.switchTo().window(childTab);
		}
		System.out.println(driver.getTitle());
	}
public static void Clear(String Xpath, String FileName) {
	
	driver.findElement(By.xpath(ReadConfig.ReadFile(Xpath, FileName))).clear();
}
public static void enterLogs(String message)
{
	Logger logger= Logger.getLogger("BaseClass");
    PropertyConfigurator.configure("log4j.properties");   
     logger.info(message);
}	
public static void Navigate(String Filename,String ProvidedURL)
{
	driver.navigate().to(ProvidedURL);
}	

public static void captureScreen(WebDriver driver, String tname) 
{
TakesScreenshot ts = (TakesScreenshot) driver;
File source = ts.getScreenshotAs(OutputType.FILE);
File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
try {
	FileUtils.copyFile(source, target);
} catch (IOException e) {
	e.printStackTrace();
}
System.out.println("Screenshot is taken");
}
public static void validationWithTitle(String test,String title) 
{
	if(driver.getTitle().equals(title))
	{
		Assert.assertTrue(true);
	    System.out.println("Method was passed successfully-->   "+title);
	}
	else
	{				
		captureScreen(driver,test); 	
		Assert.assertTrue(false);	
		System.out.println("Method was failed");
	}
}
public static void validationWithWebelement(String filename,String webelement) 
{
	String element=Xpath(filename, webelement).getText();
	if( element!= null)
	{
		test.log(LogStatus.PASS, "Row for Gilbert Collins is shown is validated = "+ element);
		System.out.println("Validated Successfully for the element-->   "+element);
	}
	else
	{				
		//captureScreen(driver,test); 	
		System.out.println("Does not find Referenced Object");			 
	}
}

public static void Validation(String Text) {
if(driver.getPageSource().contains(Text))
{
    System.out.println("Validation is passed:" + Text);
}

else
{
	System.out.println("Validation is Failed:" + Text);
}
}


public static void UploadFile(String Filename,String Path) throws AWTException {
	threadWait(5000);
	StringSelection s = new StringSelection(ReadConfig.ReadFile(Filename, Path));
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
    Robot robot = new Robot();
   // threadWait(2500);
    robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
   // threadWait(2500);
    robot.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
  //  threadWait(2500);
    robot.keyPress(java.awt.event.KeyEvent.VK_CONTROL);
   // threadWait(2500);
    robot.keyPress(java.awt.event.KeyEvent.VK_V);
   // threadWait(2500);
    robot.keyRelease(java.awt.event.KeyEvent.VK_CONTROL);
   // threadWait(2500);
    robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
}
public static void SwitchToChild() {
	for(String childTab:driver.getWindowHandles()) {
		 driver.switchTo().window(childTab);
		}
		System.out.println(driver.getTitle());
}
public static void HandleErrorCodeOnSave(String filename, String Webelement, String FrameWebElement) {
	
	try {
		
		try {
			Thread.sleep(3000);
			scrollToElement(filename, Webelement);
			clickWebelement(filename, Webelement);
		} catch (InterruptedException e) {
			System.out.println("First block");
		}
		
	} catch (Exception e) {
		System.out.println("Waiting for Alert Message");
		
		try {
		driver.switchTo().alert().accept();
		} catch (NoAlertPresentException e1) {
		try {
			WebDriverWait wait1 = new WebDriverWait(driver, 10);
			wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(ReadConfig.ReadFile(filename, FrameWebElement)));
			driver.switchTo().frame(ReadConfig.ReadFile(filename, FrameWebElement));
			driver.switchTo().alert().accept();
		} catch (WebDriverException e2) {
			if (driver.getPageSource().contains("Error Code")) {
				Enter(KeyEvent.VK_F5);
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e3) {
				}
				scrollToElement("helper", "SaveButton");
				clickWebelement("helper", "SaveButton");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e4) {
				}
				
		System.out.println("An exceptional case");
		
		
	}
}
		}
	}
}
		public static void HandleErrorCodeOnProcess(String filename, String Webelement, String FrameWebElement) {
			
			try {
				
				try {
					Thread.sleep(3000);
					scrollToElement(filename, Webelement);
					clickWebelement(filename, Webelement);
				} catch (InterruptedException e) {
					System.out.println("First block");
				}
				
			} catch (Exception e) {
				System.out.println("Waiting for Alert Message");
				
				try {
				driver.switchTo().alert().accept();
				} catch (NoAlertPresentException e1) {
				try {
					WebDriverWait wait1 = new WebDriverWait(driver, 10);
					wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(ReadConfig.ReadFile(filename, FrameWebElement)));
					driver.switchTo().frame(ReadConfig.ReadFile(filename, FrameWebElement));
					driver.switchTo().alert().accept();
				} catch (WebDriverException e2) {
					if (driver.getPageSource().contains("Error Code")) {
						Enter(KeyEvent.VK_F5);
						try {
							Thread.sleep(4000);
						} catch (InterruptedException e3) {
						}
						scrollToElement("helper", "SaveButton");
						clickWebelement("helper", "SaveButton");
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e4) {
						}
						
				System.out.println("An exceptional case");
				
				
			}
		}
		}
		
	}

}
		
		public static void DeleteFile() {
			
			  File file = new File("C:\\Users\\Admin\\Downloads\\Canvassing List.xlsx"); 
			   if(file.delete())
			   System.out.println("file deleted");
		}

}