package AdbServer;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
 
@WebServlet("/Servlet")
@MultipartConfig(fileSizeThreshold=1024*1024*2)  





public class Servlet extends HttpServlet {
 
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR = "uploadFiles";
	
	


    
    
     

    
    // Method for handling GET requests.
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
       	
    		SQLHelper helper = new SQLHelper();	
    		ArrayList<CityNet> cityNets = new ArrayList<CityNet>();
    		
        	
        	//Provides active citynets for the list
        	try {cityNets = helper.getCityNets();} 
    		catch (SQLException e) {e.printStackTrace();} 
        	
   
        	
        	//Sends list and forwards browser to index.jsp
    		request.setAttribute("cityNets", cityNets); 
    	    request.getRequestDispatcher("/index.jsp").forward(request, response);
        	
    }

    
   
    
    
    
    // Method for handling POST requests.
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
       
    	
    	Parser parser = new Parser();
    	SQLHelper helper = new SQLHelper();
    	int rowsAffected = 0;
    	int format = 0;
    	

    	
        // Creates the save path for the uploaded file.
    	String appPath;appPath = request.getServletContext().getRealPath("");
    	String savePath = appPath + File.separator + SAVE_DIR; 
    	File fileSaveDir = new File(savePath);
    	String fileName = "temp.xls";
    	String completeLocation = savePath + File.separator + fileName;
        
    	
    	
    	// Creates a directory if it doesn't already exist and the writes the file to disk.
    	if (!fileSaveDir.exists()) {fileSaveDir.mkdir();}
    	for (Part part : request.getParts()) {part.write(completeLocation);} 
 
       
     
    	
    	// Gets the chosen city Net from the POST sent from the users browser.
    	String[] temp = request.getParameterValues("cityNets");
        
    	
    	// If the post contains no chosen city Net the addresses in the file gets deleted from the database.
        if (temp == null){
        	
        	format = Integer.parseInt(request.getParameter("formatRemove"));   	
            
        	try {rowsAffected = helper.deleteFromDB(parser.parseFile(completeLocation, 0, format));} 
            catch (SQLException e) {e.printStackTrace();}     
        }
        
        
        // Otherwise the method below sends the path and the id of the chosen city net to the parser. 
        // The method returns the number of rows affected.
        else {
        	
        	ArrayList<Address> addresses = new ArrayList<Address>();
        	format = Integer.parseInt(request.getParameter("formatAdd"));    			
        	        	
        	try {rowsAffected = helper.writeToDB(parser.parseFile(completeLocation, Integer.parseInt(temp[0]), format));} 
        	catch (SQLException e) {e.printStackTrace();}
        }
       
               
       
       // Sets up a string with code to be sent to the browser opening a pop up with the number of rows affected,
       // as well as forwarding the browser to the servlet to reset the page and program. 
       String message;message = "alert('" + rowsAffected + " adresser behandlade!'); window.location = '/AdbServer/Servlet';";
       PrintWriter out = response.getWriter();  
       response.setContentType("text/html");  
       
       out.println("<script type=\"text/javascript\">");  
       out.println(message);  
       out.println("</script>");

    }
  
    
   
    
    

  
}
