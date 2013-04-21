//Ellen Folk, Devin Burnes
//CPSC 215 002
//Project 3: Email Client

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactEditingDlg extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7L;

	JLabel lNamePr;
	JLabel fNamePr;
	JLabel addPr;
	JLabel phonePr;
	JLabel emailPr;
	
	JTextField fName;
	JTextField lName;
	JTextField address;  //!!!!!!!!!!!!!!!!!!CHANGE TO ADDRESS TYPE!
	JTextField phone;
	JTextField email;
	
	JPanel left;
	JPanel right;
	
	JButton CEDSave;
	JButton CEDCancel;
	
	String tf;
	String tl;
	String ta;
	String tp;
	String te;
	
	
	ContactEditingDlg(){
		setVisible(true);
		setModal(true);
		setSize(new Dimension(600,400));
		setTitle("Create or Edit Contacts");
		
		//initialize
		fName = new JTextField();
		lName = new JTextField();
		address = new JTextField();
		phone = new JTextField();
		email = new JTextField();
		makeFields();
		setModal(true);
	}
	
	ContactEditingDlg(Contact c){
		this();
		setModal(true);
		//populate 
		fName = new JTextField(c.getfName());
		lName = new JTextField(c.getlName());
		address = new JTextField(c.getAddress());
		phone = new JTextField(c.getPhone());
		email = new JTextField(c.getEmail());
		
		fName.setText(c.getfName());
		
		makeFields();
	}
	
	void makeFields(){
		
		fName.setForeground(Color.black);

		Container pane = getContentPane();
		setLayout(new BorderLayout());
		JLabel top = new JLabel("Please press enter after entering text in each field",  JLabel.CENTER);

		pane.add(top, BorderLayout.NORTH);
		left = new JPanel();
		right = new JPanel();
		left.setLayout(new GridLayout(5,1));
		right.setLayout(new GridLayout(5,1));
		
		fNamePr = new JLabel("First Name:", JLabel.CENTER);
		lNamePr = new JLabel("Last Name:", JLabel.CENTER);
		addPr = new JLabel("Address:", JLabel.CENTER);
		phonePr = new JLabel("Phone number:", JLabel.CENTER);
		emailPr = new JLabel("Email address:", JLabel.CENTER);
		
		//fName.setHorizontal
		
		left.add(fNamePr);
		left.add(lNamePr);		
		left.add(addPr);
		left.add(phonePr);
		left.add(emailPr);
				
		pane.add(left, BorderLayout.WEST);
		
		fName = new JTextField();
		lName= new JTextField();
		address= new JTextField();  //!!!!!!!!!!!!!!!!!!CHANGE TO ADDRESS TYPE!
		phone= new JTextField();
		email= new JTextField();
		
		fName.addActionListener(this);
		lName.addActionListener(this);
		address.addActionListener(this);
		phone.addActionListener(this);
		email.addActionListener(this);
		
		
		right.add(fName);
		right.add(lName);
		right.add(address);
		right.add(phone);
		right.add(email);
		
		fNamePr.setLabelFor(fName);
		
		pane.add(right,BorderLayout.CENTER);
		
		makeButtons(pane);
		
	}
	
	void makeButtons(Container pane){
		CEDSave = new JButton("Save");
		CEDCancel = new JButton("Cancel");		
		
		CEDSave.addActionListener(this);
		CEDCancel.addActionListener(this);
		
		JPanel inner = new JPanel();
	    inner.setLayout(new GridLayout(1,2));
	    inner.add(CEDSave);
	    inner.add(CEDCancel);
	    
	    pane.add(inner, BorderLayout.SOUTH);
	    
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource() == CEDSave){
			int temp = JOptionPane.showConfirmDialog(null, "Are you sure you want to save?", "Confirm save", 2);
			if(temp ==JOptionPane.YES_OPTION){
				Contact toSave = new Contact(tf, tl, ta, tp, te);
				System.out.println(fName.getText());
				DataStore DS = DataStore.getInstance();
				DS.addContact(toSave);
				dispose();
			}
			else if(temp ==JOptionPane.NO_OPTION){
				//dispose();
				setVisible(false);
			}
			
			
		}
		
		else if (arg0.getSource() == CEDCancel){
			int temp = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Confirm Exit", 2);
			if(temp ==JOptionPane.YES_OPTION){
				dispose();
			}
		}
		
		else if (arg0.getSource() == fName){
			tf = new String(fName.getText());
		}
		
		else if (arg0.getSource() == lName){
			tl = new String(lName.getText());
		}
		
		else if (arg0.getSource() == address){
			ta = new String(address.getText());
		}
		
		else if (arg0.getSource() == phone){
			tp = new String(phone.getText());
		}
		
		else if (arg0.getSource() == email){
			te = new String(email.getText());
		}
		

		
	}
}
