//Devin Burnes, Ellen Folk
//CPSC 215 002
//Project 3: Email Client

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import javax.mail.internet.MimeMessage.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Properties;



public class EmailTransmissionDlg extends JDialog{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 8L;
    private static final int SMTP_PORT = 465;

	ArrayList<String> emailAddresses = new ArrayList<String>();

	DataStore DS = DataStore.getInstance();
	
	EmailTransmissionDlg(){
		setModal(true);
		setSize(new Dimension(500,400));
		setMaximumSize(new Dimension(500, 400));
		setBackground(Color.white);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		setTitle("New Message");

		String emailA;
		for(int i = 0; i < DS.getContacts().size(); i++){
			emailA = DS.getContactAt(i).getEmail();
			if(!emailAddresses.contains(emailA)){
				emailAddresses.add(emailA);
			}
		}

		JLabel to = new JLabel("To: ");
		JLabel from = new JLabel("From: ");
		JLabel sub = new JLabel("Subject: ");
		
		to.setForeground(new Color(3, 28, 120));
		from.setForeground(new Color(3, 28, 120));

		JTextField email = new JTextField(DS.getConfig().getEmail(), 15);
		final JTextField ject = new JTextField();
		final JComboBox adrLst = new JComboBox(emailAddresses.toArray()); 
		adrLst.setSelectedIndex(0);
		adrLst.setEditable(false);

		JPanel nfo = new JPanel();
      //  SpringLayout spng = new SpringLayout();
		nfo.setLayout(new GridLayout(3, 2));

		JPanel btns = new JPanel();
		btns.setLayout(new GridLayout(1, 2));

		final JTextArea msgCntnr = new JTextArea();

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
				try {
					Properties props = new Properties();
					props.put("mail.transport.protocol", "smtp");
			        props.put("mail.smtp.host", DS.getConfig().getSMTP());
			        props.put("mail.smtp.port", SMTP_PORT);
			        
			        props.put("mail.smtp.socketFactory.port", SMTP_PORT);
			        props.put("mail.smtp.socketFactory.class", 
			        		"javax.net.ssl.SSLSocketFactory");
			        props.put("mail.smtp.auth", "true");
			        
			        Authenticator auth = new Authenticator () {
						public PasswordAuthentication getPasswordAuthentication(){
							return new PasswordAuthentication(DS.getConfig().getEmail(), getPassword());
						}
					};

					Session session = Session.getDefaultInstance(props, auth);
					//session.setDebug(true);
					
					Message msg = new MimeMessage(session);
					msg.setRecipient(RecipientType.TO, new InternetAddress((String)adrLst.getSelectedItem()));
					msg.setFrom(new InternetAddress(DS.getConfig().getEmail()));
					msg.setSubject(ject.getText());
					msg.setText(msgCntnr.getText());
					
					Transport.send(msg);
				    System.out.println("Message sent.");
					
				} catch(Exception exc) {
					System.out.println("Exception: " + exc);
				}
			}
		});

		nfo.add(from);
		nfo.add(email);
		nfo.add(to);
		nfo.add(adrLst);
		nfo.add(sub);
		nfo.add(ject);
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
	
	public static String getPassword()
	{
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Enter a password:");
		JPasswordField pwd = new JPasswordField(30);
		panel.add(label);
		panel.add(pwd);
		String[] options = new String[]{"OK", "Cancel"};
		int option = JOptionPane.showOptionDialog(null, panel, "Enter your password",
		                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
		                         null, options, options[1]);
		if(option == 0) // pressing OK button
		{
		    System.out.println("Entered password... ");
		    return new String(pwd.getPassword());
		}
		return null;
	}

	EmailTransmissionDlg(Contact c){
		this();

		//I gotta change this somehow

		//make recipient field the email address of contact
	}
	

}