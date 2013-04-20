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
		setSize(new Dimension(500,400));
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
		left.setLayout(new GridLayout(3,1));
		right.setLayout(new GridLayout(3,1));
		
		JLabel fNamePr = new JLabel("First Name:");
		JLabel lNamePr = new JLabel("Last Name:");
		JLabel addPr = new JLabel("Address:");
		JLabel phonePr = new JLabel("Phone number:");
		JLabel emailPr = new JLabel("Email address:");
		
		left.add(emailPr);
		left.add(phonePr);
		left.add(addPr);
		left.add(lNamePr);
		left.add(fNamePr);
		
		pane.add(left, BorderLayout.CENTER);
	}
}
