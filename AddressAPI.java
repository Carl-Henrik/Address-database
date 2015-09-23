package AdbServer;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

	


//RESTfull service - API.
@Path("/api")
public class AddressAPI {
	
	private ArrayList<CityNet> cityNets;
   
        
	// Method for getting zip and street from a GET. Returns correct CityNet as JSON
	@GET @Path("{street}/{zip}")
    @Produces({ MediaType.APPLICATION_JSON})
    public CityNet findCityNet(@PathParam("zip") String zip, @PathParam("street") String street) throws SQLException {
        
		cityNets = new SQLHelper().getCityNet(new Address(street, Integer.parseInt(zip), null, 0, ""));
		
		return cityNets.get(0);      	
    	 
    }
    
        
}
