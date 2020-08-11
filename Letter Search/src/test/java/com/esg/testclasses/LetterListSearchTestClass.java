package com.esg.testclasses;

import com.esg.utilities.HelperClass;

public class LetterListSearchTestClass extends HelperClass {
public static void login() {
	login("Helper","BaseURL", 1, 0, 1, 1);
	}
	public static void LetterListSearch() {
		ClickByActionClass("LetterListSearch", "MainMenu", "ESG Values", "Letter_List_Search", 1, 1);
		enterTextboxValue("LetterListSearch", "EnrollmentNo", "ESG Values", "Letter_List_Search", 2, 1);
		clickWebelement("LetterListSearch", "SearchButton");
		threadWait(5000);
		defineLogs("Letter List", "Letter List", "Letter List", "Enrollment Number validated Successfully", "Failed to validate Enrollment Number", "LetterListSearch", "VerifyEnrollmentNo");
		
		ClickByActionClass("LetterListSearch", "MainMenu", "ESG Values", "Letter_List_Search", 1, 1);
		enterTextboxValue("LetterListSearch", "AccountNo", "ESG Values", "Letter_List_Search", 3, 1);
		clickWebelement("LetterListSearch", "SearchButton");
		explicitWait(3000, "LetterListSearch", "VerifyAccountNumber");
		defineLogs("Letter List", "Letter List", "Letter List", "Account Number validated Successfully", "Failed to validate Account Number", "LetterListSearch", "VerifyAccountNumber");
		
		ClickByActionClass("LetterListSearch", "MainMenu", "ESG Values", "Letter_List_Search", 1, 1);
		selectDropdown("LetterListSearch", "LetterName","", "ESG Values", "Letter_List_Search", 4, 1);
		clickWebelement("LetterListSearch", "SearchButton");
		threadWait(5000);
		defineLogs("Letter List", "Letter List", "Letter List", "Letter Name validated Successfully", "Failed to validate Letter Name", "LetterListSearch", "VerifyLetterName");
	}
	
}