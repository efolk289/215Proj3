//Ellen Folk, Devin Burnes
//CPSC 215 002
//Project 3: Email Client

import java.io.Serializable;


public class Contact implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	
	private String fName;
	private String lName;
	private String address;  //!!!!!!!!!!!!!!!!!!CHANGE TO ADDRESS TYPE!
	private String phone;
	private String email;
	
	Contact(String iFirst, String iLast, String iAddr, String iPh, String iEmail ) {
		fName = iFirst;
		lName = iLast;
		address = iAddr;
		phone = iPh;
		email = iEmail;
	}
	
	public Contact() {
		fName = new String();
		lName = new String();
		address = new String();
		phone = new String();
		email = new String();
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}
	
	public void setlName(String name) {
		this.lName = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
