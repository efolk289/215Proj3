import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;


public class DataStore {
//singleton
	
	private Configuration config = new Configuration();
	private Vector<Contact> contacts = new Vector<Contact>();
	private static DataStore instance = null;
	
	Vector<String> ColNames = new Vector<String>();
	
	
	
	String[] columnNames = {
			"First Name",
            "Last Name",
            "Address",
            "Phone Number",
            "Email"};
	
	protected DataStore() {
		ColNames.add("First Name");
		ColNames.add("Last Name");
		ColNames.add("Address");
		ColNames.add("Phone Number");
		ColNames.add("Email");
		
		//Contact con = new Contact("fir1", "las1", "addr1", "phone1", "em1");
		//contacts.add(con);
	}
	
	public static DataStore getInstance(){
		if(instance == null) {
	         instance = new DataStore();
	      }
	      return instance;
	}
	
	public Configuration getConfig() {
		return config;
	}
	
	public Contact getContactAt(int at){
		return contacts.elementAt(at);
	}
	
	public void addContact(Contact toSave){
		contacts.add(toSave);
	}

	public void setConfig(Configuration config) {
		this.config = config;
	}

	public Vector<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Vector<Contact> contacts) {
		this.contacts = contacts;
	}

	public static void saveConfiguration(Configuration c, String filename) 
			throws IOException
	{
	FileOutputStream fileOut = new FileOutputStream(filename);
    ObjectOutputStream out = new ObjectOutputStream(fileOut);
    
    out.writeObject(c);
    out.close();
    fileOut.close();
	}
	
	public static Configuration loadConfiguration(String filename) 
			throws Exception
	{
		FileInputStream fileIn = new FileInputStream(filename);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		
		Configuration c = (Configuration) in.readObject();
		in.close();
		fileIn.close();
		return c;
	}
	
	public static void saveContact(Contact c, String filename) 
			throws IOException
	{
	FileOutputStream fileOut = new FileOutputStream(filename);
    ObjectOutputStream out = new ObjectOutputStream(fileOut);
    
    out.writeObject(c);
    out.close();
    fileOut.close();
	}
	
	public static Contact loadContact(String filename) 
			throws Exception
	{
		FileInputStream fileIn = new FileInputStream(filename);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		
		Contact c = (Contact) in.readObject();
		in.close();
		fileIn.close();
		return c;
	}
	
	
}
