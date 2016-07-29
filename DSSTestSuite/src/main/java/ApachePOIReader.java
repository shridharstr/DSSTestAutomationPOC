import java.io.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ApachePOIReader {

	public static XSSFCell cell;
	public static XSSFCell Cell;
	public static XSSFWorkbook wbook;
	public static XSSFSheet Sheet;
	
	 public static String getCellData(XSSFCell cell)
		{
		 int type;
		 Object result;
		 type = cell.getCellType();
		 
		 switch(type)
		 { 
		 case 0:
			 result = cell.getNumericCellValue();
			 break;
			 
		 case 1:
			 result = cell.getStringCellValue();
			 break;
			 
		 default:
			 throw new RuntimeException("No such value exists!");
			
		 }
		 
		 return result.toString();
			

	    }
	 
	 public static String[][] getExcelDataforMarket(String fileName, String sheetName)
	 {
		 String[][] arrayData = null;
		 
		 try{
			  FileInputStream inputStream = new FileInputStream(new File(fileName));
			  wbook = new XSSFWorkbook(inputStream);
			  Sheet = wbook.getSheet("OS");
			  System.out.println("Data sheet read successfully!");
				
			  int rowNum = Sheet.getLastRowNum();
			  int colNum = Sheet.getRow(0).getLastCellNum();
			 
			  arrayData = new String[rowNum][colNum];
			  
			  for(int i=1; i< rowNum; i++)
			  {
				  XSSFRow row = Sheet.getRow(i);
				  for(int j=0; j< colNum; j++)
				  {
					  XSSFCell cell = row.getCell(j);
					  arrayData[i-1][j] = getCellData(cell);
					  System.out.println(arrayData[i-1][j]);
					  
				  }	  
			  }
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
	       	 
		 
		 return arrayData;
	 }
	
	
	public static void main(String[] args) throws Exception
	{
		String excelFile = "C:\\Users\\shridhars\\Documents\\DSSAutomation-POC\\DataProvider.xlsx";
		getExcelDataforMarket(excelFile, "OS");
		
	}

}
