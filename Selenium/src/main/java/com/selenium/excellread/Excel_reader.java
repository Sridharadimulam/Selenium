package com.selenium.excellread;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.log4testng.Logger;
public class Excel_reader {
	public static final Logger logger=Logger.getLogger(Excel_reader.class.getName());
	public String[][]getexcelldata(String excellocation,String sheetname) throws IOException{
	try{	
		String datasets[][]=null;
		FileInputStream file=new FileInputStream(new File(excellocation));
		XSSFWorkbook workbook=new XSSFWorkbook(file);
		XSSFSheet sheet=workbook.getSheet(sheetname);
		//count number of active cells
		int totalrow=sheet.getLastRowNum()+1;
		//count number of active columns in row
		int totalcolumns=sheet.getRow(0).getLastCellNum();
		//create arrays of rows and columns
		datasets=new  String[totalrow-1][totalcolumns];
		Iterator<Row> rowiterator = sheet.iterator();
		int i=0;
		int t=0;
		while(rowiterator.hasNext()){
			Row row=rowiterator.next();
			if(i++ !=0){
				int k=t;
				t++;
				//for each row, iterate all columns
			Iterator<Cell> celliterator = row.cellIterator();
			int j=0;
			while (celliterator.hasNext()){
				Cell cell=celliterator.next();
			//check the cell type and farmat accordingly	
				switch (cell.getCellType()){
				case Cell.CELL_TYPE_NUMERIC:
					datasets[k][j++]=cell.getStringCellValue();
					System.out.print(cell.getNumericCellValue());
					break;
				case Cell.CELL_TYPE_STRING:
					datasets[k][j++]=cell.getStringCellValue();
					System.out.print(cell.getStringCellValue());
					break;
				case Cell.CELL_TYPE_FORMULA:
					datasets[k][j++]=cell.getStringCellValue();
					System.out.print(cell.getStringCellValue());
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					datasets[k][j++]=cell.getStringCellValue();
					System.out.print(cell.getStringCellValue());
					break;
				}
			}
			System.out.println("");
			}
		}
		file.close();
		return datasets;
	}catch(Exception e){
		e.printStackTrace();
	}return null;

}
}