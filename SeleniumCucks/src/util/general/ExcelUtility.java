package util.general;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	public static Workbook getWorkBook ( String fileName ) 
	{
		try {
			File file = new File ( fileName );
			FileInputStream fis = new FileInputStream( file );
			Workbook workbook = null;
			if (fileName.toLowerCase().endsWith("xlsx")) {
				workbook = new XSSFWorkbook( fis );
			}
			if (fileName.toLowerCase().endsWith("xls")) {
				workbook = new HSSFWorkbook( fis );
			}
			return workbook;
		} catch (Exception e) {
			return null;
		}
	}
	public static HSSFSheet getSheet( String fileName, String sheetName )
	{
		try {
	        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream( fileName ));
	      //  HSSFWorkbook workbook = getWorkBook( fileName );

	        return workbook.getSheet( sheetName );
		} catch (Exception e) {
			//K2 : Implement Proper Error Message , Handle THis Exception
			return null;
		}
	}
	public static String getValue (HSSFSheet sheet, String key )
	{
		int rowCount = sheet.getLastRowNum();
		for (int i = 0; i < rowCount ; i++) 
		{
			Cell cell = sheet.getRow(i).getCell(0);
			if (cell.toString().trim().equals(key.trim())) 
				return sheet.getRow(i).getCell(1).toString().trim();
		}
		return null;
	}
	public static String getValue (HSSFSheet sheet, String key ,int position )
	{
		int rowCount = sheet.getLastRowNum();
		for (int i = 0; i < rowCount ; i++) 
		{
			Cell cell = sheet.getRow(i).getCell(0);
			if (cell.toString().trim().equals(key.trim())) 
				return sheet.getRow(i).getCell(position).toString().trim();
		}
		return null;
	}

}
