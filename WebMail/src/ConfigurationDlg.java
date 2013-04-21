//Devin Burnes, Ellen Folk
//CPSC 215 002
//Project 3: Email Client

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigurationDlg extends JDialog{

	private static final long serialVersionUID = 5L;
	
	ConfigurationDlg(final Configuration cfg){
		setTitle("Configuration");
		JButton sve = new JButton("Save");
		JButton cncl = new JButton("Cancel");
		
		JPanel btns = new JPanel();
		btns.setLayout(new GridLayout(1, 2));
		
		JLabel ea = new JLabel("Email Address:");
		JLabel smp = new JLabel("SMTP server:");
		
		JPanel txtT = new JPanel();
		txtT.setLayout(new GridLayout(4, 1));

		JPanel txtB = new JPanel();
		txtB.setLayout(new GridLayout(2, 1));


		final JTextArea eml = new JTextArea(cfg.getEmail());
		final JTextArea tp = new JTextArea(cfg.getSMTP());
		setModal(true);

		
		setSize(new Dimension(200,130));
		setMaximumSize(new Dimension(200, 130));

		setLayout(new BorderLayout());
		setBackground(Color.blue);  //doesn't work
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		btns.add(sve);
		btns.add(cncl);
	
		txtT.add(ea);
		txtT.add(eml);

		txtT.add(smp);
		txtT.add(tp);
		
		add(txtB, BorderLayout.CENTER);
		add(txtT, BorderLayout.NORTH);
		add(btns, BorderLayout.SOUTH);

		cncl.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
                dispose();
			}
		});
		
		sve.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if(!tp.getText().contains("SMTP")){
					JOptionPane.showMessageDialog(tp, "Please enter in a valid smtp server");
				}
				else if(!eml.getText().contains("@")){
					JOptionPane.showMessageDialog(eml, "Please enter a valid email address");
				}
				
				else{
					cfg.setEmail(eml.getText());
					cfg.setSMTP(tp.getText());
                	dispose();
				}
			}
		});
		setVisible(true);
	}
}
