package AdbServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;







// Class made to handle mySQL interaction.
public class SQLHelper {
	
	private String street, city;
	private Integer zip, netID;
	
	private MysqlDataSource dataSource = new MysqlDataSource();
	private Connection conn;
	private ResultSet rs;
	private ArrayList<CityNet> cityNets;
	private ArrayList<Address> addresses;
	private int rowsAffected;
	private PreparedStatement stmt;
	private String tempName;
	private int tempID;
	


	
	// Querys database for provided address and returns associated citynet as an object.
	public ArrayList<CityNet> getCityNet(Address address) throws SQLException{
		
		
		

		cityNets = new ArrayList<CityNet>();
	
		openConnection(); 
		
		
		

	
		stmt = conn.prepareStatement("SELECT netid FROM ch_adresses WHERE street LIKE ? AND zip LIKE ?");
		stmt.setString(1, "%" + address.getStreet() + "%");
		stmt.setString(2, "%" + address.getZip() + "%");
				
		rs = stmt.executeQuery();
				
	
			while (rs.next()) {netID = rs.getInt("netid");}
			rs = stmt.executeQuery("SELECT id, name FROM skycom_products WHERE id='" + netID + "'");
			while (rs.next()) {cityNets.add(new CityNet(rs.getInt("id"), rs.getString("name")));}
	
			
	
	
	
		closeConnection();
		cityNets.add(new CityNet(0, "not found"));
		return cityNets;
		
	}



	
	// Querys database for provided data and returns associated addresses and citynets as an ArrayList.
	public ArrayList<Address> getSearchResults(Address address) throws SQLException{
		
		addresses = new ArrayList<Address>();
		tempID = 0;
		tempName ="";
		
		openConnection(); 
		

		
		if (address.getZip() == -1){
			
			stmt = conn.prepareStatement("SELECT street, zip, city, netid FROM ch_adresses WHERE street LIKE ? AND city LIKE ? ORDER BY street");
			stmt.setString(1, "%" + address.getStreet() + "%");
			stmt.setString(2, "%" + address.getCity() + "%");
			rs = stmt.executeQuery();
		
		}
		
		else {
	
		
		stmt = conn.prepareStatement("SELECT street, zip, city, netid FROM ch_adresses WHERE street LIKE ? AND zip LIKE ? AND city LIKE ? ORDER BY street");
		stmt.setString(1, "%" + address.getStreet() + "%");
		stmt.setString(2, "%" + address.getZip() + "%");
		stmt.setString(3, "%" + address.getCity() + "%");
		rs = stmt.executeQuery();
		
		}
	
	

			
			
			
			while (rs.next()) {addresses.add(new Address(rs.getString("street"), rs.getInt("zip"), rs.getString("city"), rs.getInt("netid"), ""));}
			
			 for (int i = 0; i < addresses.size(); i++){
				
				 	if (tempID == addresses.get(i).getNetID()){addresses.get(i).setCityNet(tempName);}
					
				 	else {
					stmt = conn.prepareStatement("SELECT name FROM skycom_products WHERE id=?");
					stmt.setString(1, Integer.toString(addresses.get(i).getNetID()));
					rs = stmt.executeQuery();
					
					
					while (rs.next()) {
						
						tempName = rs.getString("name");
						tempID = addresses.get(i).getNetID();
						addresses.get(i).setCityNet(tempName);
					
					}
				}
				
				
			
		}
		
		
		closeConnection();
		return addresses;
		}


	

	//Returns an arraylist containing all citynets with 'orderability' and 'active' set to 1.
	public ArrayList<CityNet> getCityNets() throws SQLException{
		
		cityNets = new ArrayList<CityNet>();
		
				
		
		openConnection(); 
		
		
		stmt = conn.prepareStatement("SELECT id, name FROM skycom_products "
				+ "WHERE type='citynet' AND orderability='1' AND active='1' AND class='municipal' AND is_legacy='0' AND name NOT LIKE '%(Telia Ö%' ORDER BY name");
		
		rs = stmt.executeQuery();
		
		while (rs.next()) {cityNets.add(new CityNet(rs.getInt("id"), rs.getString("name")));}
		
			
	
		closeConnection();
		return cityNets;
		
	}


	
	
	
	public ArrayList<CityNet> getCityNets2() throws SQLException{
		
		ArrayList temp = new ArrayList<String>();
		
				
		
		openConnection(); 
		
		
		stmt = conn.prepareStatement("SELECT id, name, parent_id FROM skycom_products "
				+ "WHERE type='citynet' AND orderability='1' AND active='1' AND class='municipal' AND is_legacy='0' AND parent_id NOT LIKE '774721' AND name NOT LIKE '%adsl%' AND name NOT LIKE '%bredband2direkt%' AND name NOT LIKE '%wimax%' AND name NOT LIKE '%(Telia Ö%' ORDER BY name");
		
		rs = stmt.executeQuery();
		
		System.out.println("id;name");
		
		while (rs.next()) {
			
			temp.add(rs.getString("name"));
			
		}
		
			
	
		closeConnection();
		return cityNets;
		
	}



	
	
	
	
	
	
	
	// Writes or updates provided addresses to the database and returns the number of rows affected as an integer. 
	public int writeToDB(ArrayList<Address> addresses) throws SQLException{
		  
		openConnection();
			
		
		// Loops through the provided list of address objects and runs a INSERT/UPDATE for each one.
		// If there already exists an entry matching the primary key 'street' + 'zip', the field 'netid' is updated with a new value.
		for (int i = 0; i < addresses.size(); i++) {
			
			street = addresses.get(i).getStreet();
			zip = addresses.get(i).getZip();
			city = addresses.get(i).getCity();
			netID = addresses.get(i).getNetID();
		
			stmt = conn.prepareStatement("INSERT INTO `ch_adresses` (`id`, `street`, `zip`, `city`, `netid`) VALUES(NULL, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE netid = ?");
			
			stmt.setString(1, street);
			stmt.setString(2, zip.toString());
			stmt.setString(3, city);
			stmt.setString(4, netID.toString());
			stmt.setString(5, netID.toString());
			
			rowsAffected = rowsAffected + stmt.executeUpdate();
			
			
		}
				

        
		closeConnection();
        return rowsAffected;
        
	}
	  
	  
	  
	
	
	
	// Loops through the provided list of address objects and runs a DELETE on each row matching the primary key 'street' + 'zip'.		
	public int deleteFromDB(ArrayList<Address> addresses) throws SQLException{
		  
		openConnection();
			
		
		
		for (int i = 0; i < addresses.size(); i++) {
			
			street = addresses.get(i).getStreet();
			zip = addresses.get(i).getZip();
			
			stmt = conn.prepareStatement("DELETE FROM `ch_adresses` WHERE street=? AND zip=?");
			
			stmt.setString(1, street);
			stmt.setString(2, zip.toString());

			
			rowsAffected = rowsAffected + stmt.executeUpdate();		

		}
				

       
		closeConnection();
		return rowsAffected; 
		
   }
	  
	
	  
	 
	
	
	
	
	// Sets the username, password and URL and opens a connection to the mySQL database.
	private void openConnection() throws SQLException{
		  
		dataSource.setUser("XXXXX");
		dataSource.setPassword("XXXXX");
		dataSource.setURL("jdbc:mysql://89.160.XXX.XXX/XXXXX"
		  		+ "?useUnicode=true&amp;connectionCollation=utf8_general_ci&amp;characterSetResults=utf8&amp;characterEncoding=utf8");
		  

		conn = dataSource.getConnection();
		
		
	}
	  
	 
	  	  
	
	
	
	// Closes the connection.
	private void closeConnection() throws SQLException{
		  		  
		if (stmt != null){stmt.close();}
		if (conn != null){conn.close();}
		     
	}
	







}

