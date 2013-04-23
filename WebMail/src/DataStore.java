//Ellen Folk, Devin Burnes
//CPSC 215 002
//Project 3: Email Client

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class DataStore {
//singleton

	private static Configuration config = new Configuration();
	private static Vector<Contact> contacts = new Vector<Contact>();
	private static DataStore instance = null;

	//#$%@^@&@#%&^@#%^#%^@&@*@^%^#%^^@&@%*@$^&@$^@#$%!@#~#$~#%~^#^&@%^@^$!%#!~$%~@$%~@%$%^~
	static String Cpath = "src/data/contacts.txt";
	static String cfgP =  "src/data/config.txt";
	static String dir =   "src/data/";
	//#$%@^@&@#%&^@#%^#%^@&@*@^%^#%^^@&@%*@$^&@$^@#$%!@#~#$~#%~^#^&@%^@^$!%#!~$%~@$%~@%$%^~

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

	}

	public static DataStore getInstance(){
		if(instance == null) {
	         instance = new DataStore();
	      }
	      return instance;
	}

	public static Configuration getConfig() {
		return config;
	}

	public Contact getContactAt(int at){
		return contacts.elementAt(at);
	}

	public void rmContactAt(int at){
		contacts.remove(at);
	}

	public static void addContact(Contact toSave){
		contacts.add(toSave);
	}

	public static void setConfig(String email, String smtp) {
		config.setEmail(email);
		config.setSMTP(smtp);
	}

	public Vector<Contact> getContacts() {
		return contacts;
	}
	
	//#$%@^@&@#%&^@#%^#%^@&@*@^%^#%^^@&@%*@$^&@$^@#$%!@#~#$~#%~^#^&@%^@^$!%#!~$%~@$%~@%$%^~
	public void setContacts(Vector<Contact> contacts) {
		DataStore.contacts = contacts;
	}

	public static void saveConfiguration(String em, String sm) 
			throws IOException
	{
        if(instance != null){
            BufferedWriter out = new BufferedWriter(new FileWriter(cfgP));

            /* If there is something to save */
            if(em != null && sm != null){
            	out.write(em);
            	out.newLine();
            	out.write(sm);
            }
            out.close();
        }
		
	}

	public static void loadConfiguration() 
			throws Exception
	{
		
		BufferedReader reader = new BufferedReader(new FileReader(cfgP));
		setConfig(reader.readLine(), reader.readLine());
		
		reader.close();
	}

	public static void saveContact() 
			throws IOException	
	{
        if(instance != null){
            BufferedWriter out = new BufferedWriter(new FileWriter(Cpath));
            Contact c;
            int i;
            
            for(i = 0; i < instance.getContacts().size(); i++){
            	/* To prevent an extra line added at the end of the list */
            	if(i != 0)
            		out.newLine();
            	
            	c = instance.getContactAt(i);
            	String contact = (c.getfName() + " " + c.getlName() + ", " +
            					  c.getAddress() + ", " + c.getPhone() + ", " +
            					  c.getEmail());

            	out.write(contact);
            }
            out.close();
       	}
	}

	public static void loadContacts() 
			throws Exception
	{
		BufferedReader reader = new BufferedReader(new FileReader(Cpath));
		String line = null;
		String fName = "", lName = "", email = "", addr = "", phne = "";
		while ((line = reader.readLine()) != null) {
		
			int i = 0, j;
			Contact c = new Contact();
			line = line.replaceAll(",", "");
			
			j = i;
			i = line.indexOf(" ", j);
			fName = line.substring(j, i);
			
			j = i + 1;
			i = line.indexOf(" ", j);
			lName = line.substring(j, i);
			
			j = i + 1;
			i = line.indexOf(" ", j);
			addr = line.substring(j, i);
			
			j = i + 1;
			i = line.indexOf(" ", j);
			phne = line.substring(j, i);
			
			j = i + 1;
			i = line.indexOf(" ", j);
			email = line.substring(j, line.length());

			c.setfName(fName);
			c.setlName(lName);
			c.setAddress(addr);
			c.setPhone(phne);
			c.setEmail(email);

			addContact(c);
		}
		reader.close();
	}
}
//#$%@^@&@#%&^@#%^#%^@&@*@^%^#%^^@&@%*@$^&@$^@#$%!@#~#$~#%~^#^&@%^@^$!%#!~$%~@$%~@%$%^~
