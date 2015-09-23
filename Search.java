package AdbServer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/Search")


public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private SQLHelper helper;
    private ArrayList<Address> addresses;
    private int zip;
	
     
    
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		addresses = new ArrayList<Address>();
		helper = new SQLHelper();
		
		String street = request.getParameter("street");
		String zipTemp = request.getParameter("zip");
		String city = request.getParameter("city");
		
		if (zipTemp == ""){zip = -1;}
		else {zip = Integer.parseInt(zipTemp);}
		
		
				
		try {addresses = helper.getSearchResults(new Address(street, zip, city, 0, ""));} 
		catch (SQLException e) {e.printStackTrace();}
		

	
		
		request.setAttribute("addresses", addresses); 
	    request.getRequestDispatcher("/search.jsp").forward(request, response);

}
	
}
