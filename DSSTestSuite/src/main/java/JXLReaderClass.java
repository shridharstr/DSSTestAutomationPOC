import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class JXLReaderClass 
{
	public static String[][] getExcelDataForMarket(String fileName, String sheetName){
        String[][] arrayMarketData = null;
        try {
            FileInputStream fs = new FileInputStream(fileName);
            Workbook wb = Workbook.getWorkbook(fs);
            Sheet sheet = wb.getSheet(sheetName);

            int totalNoOfCols = sheet.getColumns();
            int totalNoOfRows = sheet.getRows();
            
            arrayMarketData = new String[totalNoOfRows-1][totalNoOfCols];
            
             for (int i= 1 ; i < totalNoOfRows; i++) {

                 for (int j=2; j < totalNoOfCols; j++) {
                      arrayMarketData[i-1][j] = sheet.getCell(j, i).getContents();
                      System.out.println(arrayMarketData[i-1][j] +"\t\t");
                 }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayMarketData;
   }

	public static void main(String[] args) 
	{
		String fileName = System.getProperty("user.dir") + File.separator + "src/main/java" + File.separator + "com/dss/test/dataproviders" + File.separator + "DataProvider.xls";
		JXLReaderClass.getExcelDataForMarket(fileName, "OS");
		// TODO Auto-generated method stub

	}

}
