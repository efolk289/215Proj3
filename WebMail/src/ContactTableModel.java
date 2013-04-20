import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import java.awt.*;
import java.util.Vector;

public class ContactTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private int col = 5;
	
	DataStore DS = DataStore.getInstance();
	
	Vector<Contact> cList = DS.getContacts();

	
	ContactTableModel(){	
	}
	
	public int getColumnCount() {
		return col;
	}


	public int getRowCount() {
		return cList.size();
	}

	
	//finish re-doing this class for contacts.
	public Object getValueAt(int arg0, int arg1) {
		Contact temp = cList.get(arg0);
		String error = "Please enter valid argument";
		if(arg1==0){
			return temp.getfName();
		}
		
		else if(arg1==1){
			return temp.getlName();
		}
		
		else if(arg1==2){
			return temp.getAddress();
		}
		
		else if(arg1==3){
			return temp.getPhone();
		}
		
		else if(arg1==4){
			return temp.getEmail();
		}
		
		else
		return error;
	}
	
	public void addContact(Contact iPer){
		cList.add(iPer);
	}
	
	public Contact getContact(int at){
		return cList.get(at);
	}

}
