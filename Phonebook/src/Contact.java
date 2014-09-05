import java.io.Serializable;
//import java.util.Date;


public class Contact implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private String telNumber;
	private String email;
	//private Date birthDate;
	
	
	
	public Contact(String name, String telNumber, String email){
		
		this.name = name;
		this.telNumber = telNumber;
		this.email = email;
		
	}
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
