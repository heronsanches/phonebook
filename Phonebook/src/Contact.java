import java.io.Serializable;


public class Contact implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private String telNumber;
	
	
	
	public Contact(String name, String telNumber){
		
		this.name = name;
		this.telNumber = telNumber;
		
	}
	

	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}
	

	
}
