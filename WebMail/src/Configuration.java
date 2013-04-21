//Devin Burnes, Ellen Folk
//CPSC 215 002
//Project 3: Email Client

import java.io.Serializable;


public class Configuration implements Serializable {

	private static String eAddress;
    private static String smtp;
    
	private static final long serialVersionUID = 1L;

	public String getSMTP(){
		return smtp;
	}

	public String getEmail(){
		return eAddress;
	}
	
	public void setEmail(String E){
		eAddress = E;
	}
	
	public void setSMTP(String i){
		smtp = i;		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}