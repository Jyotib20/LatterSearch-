package com.esg.utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class WriteExcel extends BaseClass{
	 public static FileInputStream fi;
	    public static XSSFWorkbook wb;
	    public static XSSFSheet sh;
	    public static XSSFCell cell;
	    public static XSSFRow row;
		public static String exceldata=null;
		public static void Writefile(String file)
		{
			try {		
				File f= new File("./DataFiles/ExcelFiles/"+file+".xlsx");
				fi= new FileInputStream(f);
				wb= new XSSFWorkbook(fi);
				
			} catch (Exception e) 		{
				System.out.println("File not available");
			}	
		}
		

public static CharSequence[] WriteData(String file, String Sheet, String Input, int Row, int Cell) throws IOException{
 FileInputStream fis = new FileInputStream("./DataFiles/ExcelFiles/"+file+".xlsx");
 wb = new XSSFWorkbook(fis);
 sh = wb.getSheet(Sheet);
                row = sh.createRow(Row);
 cell = row.createCell(Cell);
 cell.setCellValue(Input);
 FileOutputStream fos = new FileOutputStream("./DataFiles/ExcelFiles/"+file+".xlsx");
 wb.write(fos);
 fos.close();
return null;
 }
public static CharSequence[] WriteCopiedData(String filename, String Xpath, String file, String Sheet, int Row, int Cell) throws IOException{
	
	String Store = Xpath(filename, Xpath).getText();
	System.out.println("Value is" + Store);
 FileInputStream fis = new FileInputStream("./DataFiles/ExcelFiles/"+file+".xlsx");
 wb = new XSSFWorkbook(fis);
 sh = wb.getSheet(Sheet);
                row = sh.createRow(Row);
 cell = row.createCell(Cell);
 cell.setCellValue(Store);
 FileOutputStream fos = new FileOutputStream("./DataFiles/ExcelFiles/"+file+".xlsx");
 wb.write(fos);
 fos.close();
return null;
 }

}