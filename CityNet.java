package AdbServer;



public class CityNet {
	
	private int id;
	private String name;
	
	
		
	
	
	
	// Constructor
	public CityNet(int id, String name){
		
		setId(id);
		setName(name);	
	}
	
	
	

	// Get & set
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	
	// To string
	public String toString(){return id + " | " + name;}
	
}
