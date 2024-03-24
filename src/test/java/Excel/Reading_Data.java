package Excel;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Reading_Data {

	public static void main(String[] args) throws IOException {
		
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+ "\\testdata\\data.xlsx");
		
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("sheet1");
		
		int totalrows = sheet.getLastRowNum();
		int totalcells = sheet.getRow(1).getLastCellNum();
		
		System.out.println(totalrows);
		System.out.println(totalcells);
		
		for(int r=0;r<=totalrows;r++)
		{
			XSSFRow currentRow = sheet.getRow(r);
					
			for(int c=0;c<totalcells;c++)
			{
				//XSSFCell cell=currentRow.getCell(c);
				//String value=cell.toString();
				String value = currentRow.getCell(c).toString();
				System.out.print(value+"      ");
			}
			System.out.println();
		}
		
		workbook.close();
		file.close();
	}
	
}
