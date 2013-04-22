//Ellen Folk, Devin Burnes
//CPSC 215 002
//Project 3: Email Client

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//implements ActionListener
public class ContactEditingDlg extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7L;
	
	Contact contact = new Contact();
	
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
		
	}
	
	ContactEditingDlg(Contact c){
		contact = c;
		
		setModal(true);
		setSize(new Dimension(600,400));
		setTitle("Create or Edit Contacts");

		fName = new JTextField();
		lName = new JTextField();
		address = new JTextField();
		phone = new JTextField();
		email = new JTextField();
		
		
		makeFields();

	}
	
	void makeFields(){
		
		fName.setForeground(Color.black);

		Container pane = getContentPane();

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

		fName = new JTextField(contact.getfName());
		lName= new JTextField(contact.getlName());
		address= new JTextField(contact.getAddress());  //!!!!!!!!!!!!!!!!!!CHANGE TO ADDRESS TYPE!
		phone= new JTextField(contact.getPhone());
		email= new JTextField(contact.getEmail());

		right.add(fName);
		right.add(lName);
		right.add(address);
		right.add(phone);
		right.add(email);

		fNamePr.setLabelFor(fName);

		pane.add(right,BorderLayout.CENTER);

		makeButtons(pane, contact);
	}
	
	void makeButtons(Container pane, Contact c){
		CEDSave = new JButton("Save");
		CEDCancel = new JButton("Cancel");		
		final Contact toSave;
		if(c==null){
			toSave = new Contact();
		}
		else{
			toSave = c;
		}
		
		CEDSave.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				boolean validEmail = true;
				boolean validPhone = false;
				
				int temp = JOptionPane.showConfirmDialog(null, "Are you sure you want to save?", "Confirm save", 2);
				if(temp ==JOptionPane.YES_OPTION){
					
					//validate email
					try {
			            new InternetAddress(email.getText()).validate();	
			           // validEmail = true;
			        } 
					catch (AddressException ex) {
			            JOptionPane.showMessageDialog(email, "Please enter a valid email address", 
			            		"Email error", JOptionPane.ERROR_MESSAGE);
			            validEmail = false;
			        }
					
					 //validate phone number
					Pattern pattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
				    Matcher matcher = pattern.matcher(phone.getText());
					if(!matcher.matches()){
						JOptionPane.showMessageDialog(phone, "Please enter a valid phone number \nin the format xxx-xxx-xxxx", 
								"Phone error", JOptionPane.ERROR_MESSAGE);
					}
						else{
							validPhone = true;
						}

					if(validEmail==true && validPhone ==true){
						//Contact toSave = new Contact();
						toSave.setfName(fName.getText());
						toSave.setlName(lName.getText());
						toSave.setAddress(address.getText());
						toSave.setPhone(phone.getText());
						toSave.setEmail(email.getText());
						DataStore DS = DataStore.getInstance();
						DS.addContact(toSave);
						dispose();
					}
				}
				

				//cancel save
				else{ 
					dispose();
				}
			}
		});
				
		CEDCancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int temp = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel?", "Confirm Cancel", 2);
				if(temp ==JOptionPane.YES_OPTION){
					dispose();
				}
				
			}
			
		});
		
		JPanel inner = new JPanel();
	    inner.setLayout(new GridLayout(1,2));
	    inner.add(CEDSave);
	    inner.add(CEDCancel);
	    
	    pane.add(inner, BorderLayout.SOUTH);
	    
	    setVisible(true);
	    
	}
		
	}
//}
