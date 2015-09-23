package AdbServer;


public class Address {

	private String street;
	private int zip;
	private String city;
	private int netID;
	private String cityNet;
	
	
	
	// Constructor
	public Address(String street, int zip, String city, int netID, String cityNet){
		
		setStreet(street);
		setZip(zip);
		setCity(city);
		setNetID(netID);	
		setCityNet(cityNet);
	}
	
	
	
	
	// Get & set
	public String getStreet() {return street;}
	public void setStreet(String street) {this.street = street;}
	
	public int getZip() {return zip;}
	public void setZip(int zip) {this.zip = zip;}
	
	public String getCity() {return city;}
	public void setCity(String city) {this.city = city;}
	
	public int getNetID() {return netID;}
	public void setNetID(int netID) {this.netID = netID;}
	
	public String getCityNet() {return cityNet;}
	public void setCityNet(String cityNet) {this.cityNet = cityNet;}
	
	
	// To string
	public String toString(){return street + " | " + zip + " | " + city + " | " + cityNet;}
	
	
	
}
