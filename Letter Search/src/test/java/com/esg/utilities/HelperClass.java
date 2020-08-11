package com.esg.utilities;

import java.awt.event.KeyEvent;
import java.io.IOException;

import com.relevantcodes.extentreports.LogStatus;

public class HelperClass extends ExtentReport
{	
	
	public static void OpenBrowser(String Filename,String URL) 
	{
		  //setProperty("helper", "ie", "IE_Path");
		  setProperty("helper", "chrome", "Chrome_Path");
		  //openInternetExploer();	
		  openChrome();
		  maximizeWindow();
	  	  implicitlywait(30);
		  openURL(Filename,URL);
		  
		}   	
	public static void login(String Relevant_Filename,String Provided_URL,int UsernameRow,int UsernameColumn,int PasswordRow,int PasswordColumn) 
	{
		 ExecutingAgainst("helper", "Version");
		  OpenBrowser(Relevant_Filename, Provided_URL);
		  enterTextboxValue("helper", "Username_Xpath", "LoginData", "Login", UsernameRow, UsernameColumn);
		  enterTextboxValue("helper", "Password_Xpath", "LoginData", "Login", PasswordRow, PasswordColumn);
		  clickWebelement("helper","Login_Xpath");
		  defineLogs("Logout", "Logout", "Logout", "User was Loggedin Successfully", "User is not able to Login","helper", "Logout_Xpath");
		 // Report.endTest(test);
	}   	
	
	
public static void login2( int UsernameRow,int UsernameColumn,int PasswordRow,int PasswordColumn) {
	//	Setup(Browsername);
		openURL("helper", "BaseURL");
		try {
			enterTextboxValue("helper", "Username_Xpath", "LoginData", "Login", UsernameRow, UsernameColumn);
			  enterTextboxValue("helper", "Password_Xpath", "LoginData", "Login", PasswordRow, PasswordColumn);
			  clickWebelement("helper","Login_Xpath");
		} catch (Exception e) {
			if (Xpath("helper", "Logout_Xpath").isDisplayed()) {
				 maximizeWindow();	
				clickWebelement("helper", "Logout_Xpath");
				 enterTextboxValue("helper", "Username_Xpath", "LoginData", "Login", UsernameRow, UsernameColumn);
				  enterTextboxValue("helper", "Password_Xpath", "LoginData", "Login", PasswordRow, PasswordColumn);
				  clickWebelement("helper","Login_Xpath");
				 
			}
			else
				//WaitForElement(300,"helper","Username_Xpath");
				maximizeWindow();	
			  enterTextboxValue("helper", "Username_Xpath", "LoginData", "Login", UsernameRow, UsernameColumn);
			  enterTextboxValue("helper", "Password_Xpath", "LoginData", "Login", PasswordRow, PasswordColumn);
			  clickWebelement("helper","Login_Xpath");
		}
		  
		/*driver.getPageSource().contains("Logout")*/
		
		  
	}
	public static void createEnrollment(int programRow,int programCoulmn,int accountRow,int accountCoulmn )
	{
		linkText("Main Menu").click();
		linkText("Enrollments").click();
		//threadWait(3000);
		clickWebelement("helper", "NewButton");
		selectDropdown("helper", "SelectProgram_Dropdown","SelectProgram_Dropdown", "CreateEnrollments", "Enrollments", programRow,programCoulmn);
		WaitForElement(30, "helper", "NextButton");
		clickWebelement("helper", "NextButton");
		clickWebelement("helper", "GeneralSearch_Dropdown");		
		selectDropdown("helper", "GeneralSearch_Dropdown","GeneralSearch_Dropdown", "CreateEnrollments", "Enrollments", 3, 2);
		enterTextboxValue("helper", "EnterAccountNumber","CreateEnrollments", "Enrollments", accountRow, accountCoulmn);
		clickWebelement("helper", "ClickOn_GoButton");
	    clickWebelement("helper", "ClickOn_CustomerName");	
	    WaitForElement(30, "helper", "NextButton");
	    clickWebelement("helper", "NextButton");	   
	    //enterlogs("Enrollment was Created Successfully");
	    defineLogs("Application - Applicant Information - New", "Application - Applicant Information - New", "Application - Applicant Information - New", "Enrollment was Created Successfully", "Enrollment was not Created", "helper", "Varify_Enrollment");
	
	}
	public static void clickonProcess()
	{
		try {
			  Thread.sleep(2000);
			  scrollToElement("helper", "ProcessButton");
			  clickWebelement("helper", "ProcessButton");
		    } catch (InterruptedException e)
		{
			System.out.println("Application Processed successfully");
		}
		
	
	}
	public static void clickonSave()
	{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		scrollToElement("helper", "SaveButton");
		clickWebelement("helper", "SaveButton");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
	}	
	public static void selectMeasures()
	{
		scrollToElement("helper", "MeasureSub_Section");
		clickWebelement("helper", "MeasureSub_Section");
	}
	
	public static void selectApplicantInformation()
	{
		scrollToElement("helper", "ApplicantInformationSub_Section");
		clickWebelement("helper", "ApplicantInformationSub_Section");
	}
	public static void selectActualReviewDate()
	{
		WaitForElement(2000,"helper", "ActualReviewDateImage");
		clickWebelement("helper", "ActualReviewDateImage");		
		
		clickWebelement("helper", "SelectDate");
		clickonSave();
	}
	public static void WaitForEmployeeDropdown()
	{
		WaitForElement(2000,"helper", "EnableEmployee_Dropdown");
	}
	
	public static void selectActualVisitDate()
	{
		clickWebelement("helper", "ActualVisitDateImage");	
		Enter(KeyEvent.VK_ENTER);
		//clickWebelement("helper", "SelectDate");
		clickonSave();
	}
	public static void selectLOIApprovedDate()
	{
		clickWebelement("helper", "LOIApprovedDateImage");				
		clickWebelement("helper", "SelectDate");
		clickonSave();
	}
	public static void selectDueDate()
	{
		clickWebelement("helper", "DueDateImage");				
		clickWebelement("helper", "SelectDate");
		clickonSave();
	}
	public static void SelectIncentiveAgreementTargetDate()
	{
		clickWebelement("helper", "IncentiveAgreementTargetDateImage");				
		clickWebelement("helper", "SelectDate");
		clickonSave();
	}
	public static void SelectEstimatedInstallationCompletionDate()
	{
		clickWebelement("helper", "EstimatedInstallationCompletionDateImage");				
		clickWebelement("helper", "SelectDate");
		clickonSave();
	}
	public static void selectReadyForPayment()
	{	
		scrollToElement("helper", "ReadyForPayment_YES");
		clickWebelement("helper", "ReadyForPayment_YES");
		clickonSave();
	}
	public static void InstallationDate() {
		clickWebelement("helper", "InstallationDate");
		//WaitForElement(30,"helper","SelectDate");
		threadWait(3000);
		//clickWebelement("helper", "SelectDate");
		Enter(KeyEvent.VK_ENTER);
	}
	public static void CustomerSignDate() {
		clickWebelement("helper", "InstallationDate");
		
		//WaitForElement(30,"helper","CustomerSignDate");
		threadWait(2000);
		clickWebelement("helper", "CustomerSignDate");
	}
	public static void ApplicationInstallationDate() {
		clickWebelement("helper", "ApplicationInstallationDate");
		WaitForElement(30,"helper","ApplicationDate");
		clickWebelement("helper", "ApplicationDate");
		clickonSave();
	}  
	public static void EICompletedDate() {
		clickWebelement("helper", "EICompleteDate");
		WaitForElement(30,"helper","SelectDate");
		clickWebelement("helper", "SelectDate");
		
	}
	public static void EIConstructionStartDate() {
		clickWebelement("helper", "ECStartDate");
		WaitForElement(30,"helper","SelectDate");
		clickWebelement("helper", "SelectDate");
	}
	public static void AgreementSignDateApplicant() {
		clickWebelement("helper", "AgreementSignDateApplicant");
		WaitForElement(30,"helper","SelectDate");
		clickWebelement("helper", "SelectDate");
}	
	public static void AgreementSignDateManagement() {
		clickWebelement("helper", "AgreementSignDateManagement");
		WaitForElement(30,"helper","SelectDate");
		clickWebelement("helper", "SelectDate");
		
	}
	
	public static void AppointmentDate() {
		clickWebelement("helper", "AppointmentDate");
	//	threadWait(3500);
		Enter(KeyEvent.VK_ENTER);
		//clickWebelement("helper", "SelectDate");
		
	}
	public static void ContactDate() {
		clickWebelement("helper", "ContactDate");
		//threadWait(2500);
		//clickWebelement("helper", "SelectDate");
		Enter(KeyEvent.VK_ENTER);
	}
	
	public static void SchedulingDate() {
		clickWebelement("helper", "SchedulingDate");
		//threadWait(2500);
		//clickWebelement("helper", "SelectDate");
		Enter(KeyEvent.VK_ENTER);
	}
	
	public static void clickonclose()
	{
		WaitForElement(300,"helper","CloseButton");
		scrollToElement("helper", "CloseButton");
		clickWebelement("helper", "CloseButton");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
	}

		public static void PurchaseDate() {
		clickWebelement("helper", "PurchaseDate");
		WaitForElement(30,"helper","SelectPurchaseDate");
		clickWebelement("helper", "SelectPurchaseDate");
	}
		public static void InstallationDate2() {
			clickWebelement("helper", "InstallationDate2");
			WaitForElement(30,"helper","SelectDate");
			clickWebelement("helper", "SelectDate");
		}
		
		public static void Setup(String Browsername) {
			
			if (Browsername.equalsIgnoreCase("chrome")) {
				
				setProperty("helper", "chrome", "Chrome_Path");
				openChrome();
			}
			else if (Browsername.equalsIgnoreCase("IE")) {
				setProperty("helper", "ie", "IE_Path");
				openInternetExploer();
				
			}
			
		}
		public static  void TrackEnrollmentNumber(String FileName, String Webelement, String ExcelFileName, String ExcelSheetName, int Row, int Col  ){
			String input= Xpath(FileName, Webelement).getText();
			     String EnrollmentNo=input.replaceAll("[^0-9]", "");
			      System.out.println("\nEnrollment Numbers is: " + EnrollmentNo);
			     // threadWait(30);
			      try {
					WriteExcel.WriteData(ExcelFileName, ExcelSheetName, EnrollmentNo, Row, Col);
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Enrollment Number is not updated");
				}
		}
		public static void CloseBrowserWindow() {
			driver.quit();
	} 
		
			public static void createEnrollment(String FileName,String SheetName,int programRow,int programCoulmn,int accountRow,int accountCoulmn )
				{
					linkText("Main Menu").click();
					linkText("Enrollments").click();
					clickWebelement("helper", "NewButton");
					selectDropdown("helper", "SelectProgram_Dropdown","SelectProgram_Dropdown", FileName, SheetName, programRow,programCoulmn);
					clickWebelement("helper", "NextButton");
					//clickWebelement("helper", "GeneralSearch_Dropdown");		
					//selectDropdown("helper", "GeneralSearch_Dropdown", FileName, SheetName, 3, 0);
					enterTextboxValue("Certified_Product_List_CPL", "Certified_Product_List_CPL", FileName, SheetName, accountRow, accountCoulmn);
					clickWebelement("helper", "ClickOn_GoButton");
					doubleClickWebelement("helper", "ClickOn_CustomerName");
				    defineLogs("Application - Applicant Information - New", "Application - Applicant Information - New", "Application - Applicant Information - New", "Enrollment was Created Successfully", "Enrollment was not Created", "helper", "Varify_Enrollment");
				
				} 
			
			public static void ESA_Alert_Window() {
					
				if (driver.switchTo().alert().getText().contains("There is currently another lead open and duplicates are not allowed for this program and account.")) {
					//threadWait(2500);
					handlePopup();
					WaitForElement(3000, "helper", "CancelButton");
					clickWebelement("helper", "CancelButton");
					WaitForElement(3000, "helper", "AccountNumber");
					enterTextboxValue("helper", "AccountNumber", "ESG Values", "ESA_Data", 4, 1);
					selectDropdown("helper", "AssignTradeAlly","AssignTradeAlly", "ESG Values", "ESA_Data", 8, 1);
					selectDropdown("helper", "LeadStatus","LeadStatus", "ESG Values", "ESA_Data", 8, 2);
					selectDropdown("helper", "EmployeeName", "EmployeeName", "ESG Values", "ESA_Data", 8, 1);
					scrollToElement("helper", "SearchButton");
					clickWebelement("helper", "SearchButton");
					WaitForElement(3000, "helper", "LeadSearchWait");
					/*doubleClickWebelement("helper", "AssignedDateSort");
					threadWait(10000);
					doubleClickWebelement("helper", "AssignedDateSort");
					threadWait(10000);*/
					//TO delete the UnAssigned Lead
					String Text = "Unassigned";
					String Match = Xpath("helper", "Unassigned").getText();
					System.out.println(Match);
					if (Match.contains(Text)) {
						clickWebelement("helper", "EditOption");
						WaitForElement(3000, "helper", "DeleteButton");
						clickWebelement("helper", "DeleteButton");
						handlePopup();	
					} else {	
						System.out.println("Unassigned leads are not displayed");

					}

				}
			
			}
						
				public static void Effective_Date()
				{
					clickWebelement("helper", "Effective_Date");	
					threadWait(3000);
					Enter(KeyEvent.VK_ENTER);
					/*clickonSave();*/
				}
				
				
				public static void CanvassingListFromDate() {
					clickWebelement("helper", "CanvassingListFromDate");
					threadWait(2500);
					Enter(KeyEvent.VK_ENTER);
					
				}
				
				public static void ActiveDate() {
					clickWebelement("helper", "ActiveDate");
					threadWait(2500);
					Enter(KeyEvent.VK_ENTER);
					
				}
				public static void ExpirationDate() {
					clickWebelement("helper", "ExpirationDate");
					threadWait(2500);
					Enter(KeyEvent.VK_ENTER);
					
				}
						
						public static void SubStringData(String filename, String webelement, int Start, int End, String ExcelFname, String Sheetname, int Rowno, int Colno) throws IOException {
							String LeadSummary = Xpath("Canvassing", "LeadSummaryProgram").getText();
							//System.out.println(LeadSummary);
							String Summary = LeadSummary.substring(Start,End);
							System.out.println(Summary);
							 String Givenelement = ReadExcel.readData(ExcelFname, Sheetname, Rowno, Colno);
							if (Summary.equals(Givenelement)) {
								test.log(LogStatus.PASS, ".     "+"The Given value   :-  "+ Givenelement+",  is match with the reflected Value   :-  "+ Summary);
							} else {
								test.log(LogStatus.FAIL, ".     "+"The Given value   :-  "+ Givenelement+",  is does not match with the reflected Value   :-  "+ Summary);
							}
							
						}

}	
