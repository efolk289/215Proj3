import java.io.Serializable;


public class Address implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	private String m_street;
	private String m_city;
	private String m_state;
	private int m_zip;
	
	public Address(String street, 
					String city, String state, int zip)
	{
		m_street = street;
		m_city = city;
		m_state = state;
		m_zip = zip;
	}
	
	public String toString()
	{
		return m_street + ", "+m_city+", "+m_state+", "+m_zip;
	}

}
