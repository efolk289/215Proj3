import javax.swing.*;

import java.awt.*;

public class ContactEditingDlg extends JDialog{

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
	
	
	ContactEditingDlg(){
		setVisible(true);
		setModal(true);
		setSize(new Dimension(600,400));
		setTitle("Create or Edit Contacts");
	/*	setBackground(Color.darkGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel info = new JPanel();
		info.setForeground(Color.white);
		info.add(new Label("A 215 email client"));
		JPanel info2 = new JPanel();
		info2.add(new Label("asdf"));
		add(info);
		add(info2);
	*/	
		//initialize
		fName = new JTextField();
		lName = new JTextField();
		address = new JTextField();
		phone = new JTextField();
		email = new JTextField();
		makeFields();
	}
	
	ContactEditingDlg(Contact c){
		this();
		
		//populate 
		fName = new JTextField(c.getfName());
		lName = new JTextField(c.getlName());
		address = new JTextField(c.getAddress());
		phone = new JTextField(c.getPhone());
		email = new JTextField(c.getEmail());
		
		makeFields();
	}
	
	void makeFields(){

		Container pane = getContentPane();
		setLayout(new BorderLayout());
		left = new JPanel();
		right = new JPanel();
		left.setLayout(new GridLayout(5,1));
		right.setLayout(new GridLayout(5,1));
		
		JLabel fNamePr = new JLabel("First Name:", JLabel.CENTER);
		JLabel lNamePr = new JLabel("Last Name:", JLabel.CENTER);
		JLabel addPr = new JLabel("Address:", JLabel.CENTER);
		JLabel phonePr = new JLabel("Phone \nnumber:", JLabel.CENTER);
		JLabel emailPr = new JLabel("Email address:", JLabel.CENTER);
		
		//fName.setHorizontal
		
		left.add(fNamePr);
		left.add(lNamePr);		
		left.add(addPr);
		left.add(phonePr);
		left.add(emailPr);
				
		pane.add(left, BorderLayout.WEST);
		
		JTextField fName = new JTextField();
		JTextField lName= new JTextField();
		JTextField address= new JTextField();  //!!!!!!!!!!!!!!!!!!CHANGE TO ADDRESS TYPE!
		JTextField phone= new JTextField();
		JTextField email= new JTextField();
		
		right.add(fName);
		right.add(lName);
		right.add(address);
		right.add(phone);
		right.add(email);
		
		fNamePr.setLabelFor(fName);
		
		pane.add(right,BorderLayout.CENTER);
		
	}
}
