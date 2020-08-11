package com.esg.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcel 
{
    public static FileInputStream fi;
    public static FileOutputStream fos;
    public static XSSFWorkbook wb;
    public static XSSFSheet sh;
    public static XSSFCell cell;
    public static XSSFRow row;
	//public static String exceldata=null;
	public static String value;
	public static void readFile(String file)
	{
		try {		
			File f= new File("./DataFiles/ExcelFiles/"+file+".xlsx");
			fi= new FileInputStream(f);
			wb= new XSSFWorkbook(fi);
			
		} catch (Exception e) 		{
			System.out.println("File not available");
		}	
	}
	
	public static void ReadCanvassingFile(String file)
	{
		try {		
			File f= new File("C:\\Users\\Admin\\Downloads\\"+file+".xlsx");
			fi= new FileInputStream(f);
			wb= new XSSFWorkbook(fi);
			
		} catch (Exception e) 		{
			System.out.println("File not available");
		}	
	}
	
	public static String readData(String fname,String Sheetname,int row,int column) 
	{
		readFile(fname);		
		sh= wb.getSheet(Sheetname);
		cell=sh.getRow(row).getCell(column);		
		String exceldata;
		try 
		{
			DataFormatter formatter = new DataFormatter();
            String cellData = formatter.formatCellValue(cell);
            return cellData;
		}
		catch (Exception e) 
		{
			exceldata="";
		}
		/*wb.close();
		fi.close();*/
		return exceldata;
	}
	public static void UpdateData(String f_name,String Sheet_name,int Row,int Column,String value) {
		readFile(f_name);		
		sh= wb.getSheet(Sheet_name);
		cell=sh.getRow(Row).getCell(Column);
		try 
		{	
			cell.setCellValue(value);
            fos = new FileOutputStream("./DataFiles/ExcelFiles/"+f_name+".xlsx");
            wb.write(fos);
            fos.close();
           
		}
		catch (Exception e) 
		{
			System.out.println("Value was not updated");
		
		}
		
	}
	public static String CanvassingData(String fname,String Sheetname,int row,int column) 
	{
		ReadCanvassingFile(fname);		
		sh= wb.getSheet(Sheetname);
		cell=sh.getRow(row).getCell(column);		
		String exceldata;
		try 
		{
			DataFormatter formatter = new DataFormatter();
            String cellData = formatter.formatCellValue(cell);
            return cellData;
		}
		catch (Exception e) 
		{
			exceldata="";
		}
		/*wb.close();
		fi.close();*/
		return exceldata;
	}
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*public static String readData(String fname,String Sheetname,int row,int column)	
	{
		readFile(fname);
		sh= wb.getSheet(Sheetname);
		cell=sh.getRow(row).getCell(column);
		String exceldata=sh.getRow(row).getCell(column).getStringCellValue();		
		if (cell.getCellType()==Cell.CELL_TYPE_NUMERIC) {
			int data=(int)cell.getNumericCellValue();
			exceldata=String.valueOf(data);
		} else {
			exceldata=cell.getStringCellValue();

		}
		return exceldata;
			*/
		

