//Devin Burnes, Ellen Folk
//CPSC 215 002
//Project 3: Email Client

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EmailTransmissionDlg extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8L;

//	String[] emailAddresses = {"DBURNES@Clemson.edu", "askdjahsd@sfkuhfk.asd", "asidh@asdkasudh.com"};
	ArrayList<String> emailAddresses = new ArrayList<String>();
	
	String e1 = "DBURNES@Clemson.edu";
	String e2 = "askdjahsd@sfkuhfk.asd";
	
	EmailTransmissionDlg(){
		setModal(true);
		setSize(new Dimension(500,400));
		setMaximumSize(new Dimension(500, 400));
		setBackground(Color.white);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		setTitle("New Message");

		emailAddresses.add(e1);
		emailAddresses.add(e2);
		
		JLabel to = new JLabel("To: ");
		Component from = new JLabel("From: ");
		to.setForeground(new Color(3, 28, 120));
		from.setForeground(new Color(3, 28, 120));
		
		Component email = new JTextField("email goes here", 15);
		JComboBox adrLst = new JComboBox(emailAddresses.toArray()); 
		adrLst.setSelectedIndex(0);
		adrLst.setEditable(false);
		
		JPanel nfo = new JPanel();
      //  SpringLayout spng = new SpringLayout();
		nfo.setLayout(new GridLayout(2, 2));

		JPanel btns = new JPanel();
		btns.setLayout(new GridLayout(1, 2));
		
		JTextArea msgCntnr = new JTextArea();
		
		
		JButton snd = new JButton("Send Message");
		JButton cncl = new JButton("Cancel");
		cncl.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
                dispose();
			}
		});
		
		snd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				
			}
		});

		nfo.add(from);
		nfo.add(email);
		nfo.add(to);
		nfo.add(adrLst);
	/*	
		spng.putConstraint(SpringLayout.WEST, from, 10, SpringLayout.WEST, nfo);
		spng.putConstraint(SpringLayout.NORTH, from, 25, SpringLayout.NORTH, nfo);
		spng.putConstraint(SpringLayout.NORTH, email, 25, SpringLayout.NORTH, nfo);
	    spng.putConstraint(SpringLayout.WEST, email, 20, SpringLayout.EAST, from);
		//nfo.add(to);
		//nfo.add(adrLst);
		*/
	    JFrame stuff = new JFrame();
	    stuff.setLayout(new GridLayout(1, 1));
		
		btns.add(snd);
		btns.add(cncl);
		
		add(nfo, BorderLayout.NORTH);
		add(msgCntnr, BorderLayout.CENTER);
		add(btns, BorderLayout.SOUTH);
		
		setVisible(true);
	}

	EmailTransmissionDlg(Contact c){
		this();

		//I gotta change this somehow
		emailAddresses.add(c.getEmail());
		

		//make recipient field the email address of contact
	}

}