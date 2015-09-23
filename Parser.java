package AdbServer;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;



// Xls file parser
public class Parser{

	private String street;
	private String streetOnly;
	private String streetNumber;
	private String streetPort;
	private String zip;
	private String city;
	
	private ArrayList<Address> addresses = new ArrayList<Address>();
	private FileInputStream file;
	private HSSFWorkbook workbook;
	private HSSFSheet sheet;
	
	private Iterator<Row> rowIterator;
	private Iterator<Cell> cellIterator;
	private Row row;
	private Cell cell;
	
	
	
	
	
	
	
	//  Parses .xls files and returns an arraylist containing Adress objects.
	public ArrayList<Address> parseFile(String fileLocation, int netID, int format){
			
	try{
    
        // Creates a stream to the file and gets the first sheet.
		
		file = new FileInputStream(fileLocation);
		workbook = new HSSFWorkbook(file);
        sheet = workbook.getSheetAt(0);

                
        
        //Iterates through the document
        rowIterator = sheet.iterator();
        
        while (rowIterator.hasNext()){
        	
        	row = rowIterator.next();
        	cellIterator = row.cellIterator();
             
            
        	
        	while (cellIterator.hasNext()){
                
        		cell = cellIterator.next();
                cell.setCellType(1);
                       
                 if (format == 1){format1();}              
                 if (format == 2){format2();}  
                 if (format == 3){format3(); }  
                
              
        	}
            if (streetOnly.equals(streetNumber)){
                streetNumber = "";
                streetPort = "";
            }
            
            if (streetNumber.equals(streetPort)){
                
                streetPort = "";
            }
        	
        	street = streetOnly + " " + streetNumber + streetPort;
        	
        	
        	// Ads every address as an object in the arraylist.
            addresses.add(new Address(street.toLowerCase(), Integer.parseInt(zip), city.toLowerCase(), netID, ""));
                   
            streetNumber = "";
            streetPort = "";
        }
       
            file.close();

	}
    
	
	catch (Exception e){e.printStackTrace();}
	return addresses;
	
	}
	
	
	public void format1(){
		
        switch (cell.getColumnIndex()){
        
        case 0: street = cell.getStringCellValue();
        case 1: zip = cell.getStringCellValue();
        case 2: city = cell.getStringCellValue();
	
        }
  
        
	}
	
	
	public void format2(){
		
        switch (cell.getColumnIndex()){
        
        case 0: streetOnly = cell.getStringCellValue();
        case 1: streetNumber = cell.getStringCellValue();
        case 2: zip = cell.getStringCellValue();
        case 3: city = cell.getStringCellValue();
	
        }
	}
	
	public void format3(){
		
        switch (cell.getColumnIndex()){
        
        case 0: streetOnly = cell.getStringCellValue();
        case 1: streetNumber = cell.getStringCellValue();
        case 2: streetPort = cell.getStringCellValue();
        case 3: zip = cell.getStringCellValue();
        case 4: city = cell.getStringCellValue();
	
        }
	}

}
