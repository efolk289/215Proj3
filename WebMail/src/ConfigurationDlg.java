//Devin Burnes, Ellen Folk
//CPSC 215 002
//Project 3: Email Client

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ConfigurationDlg extends JDialog{

	private static final long serialVersionUID = 5L;


	ConfigurationDlg(){
		final DataStore DS = DataStore.getInstance();

		setTitle("Configuration");
		JButton sve = new JButton("Save");
		JButton cncl = new JButton("Cancel");

		sve.setForeground(new Color(3, 28, 120));
		cncl.setForeground(new Color(3, 28, 120));

		JPanel btns = new JPanel();
		btns.setLayout(new GridLayout(1, 2));

		JLabel ea = new JLabel("Email Address:");
		JLabel smp = new JLabel("SMTP server:");

		ea.setForeground(Color.white);
		smp.setForeground(Color.white);

		ea.setFont(new Font("SansSerif", Font.BOLD, 12));
		smp.setFont(new Font("SansSerif", Font.BOLD, 12));
		JPanel txtT = new JPanel();
		txtT.setLayout(new GridLayout(4, 1));

		JPanel txtB = new JPanel();
		txtB.setLayout(new GridLayout(2, 1));

		//#$%@^@&@#%&^@#%^#%^@&@*@^%^#%^^@&@%*@$^&@$^@#$%!@#~#$~#%~^#^&@%^@^$!%#!~$%~@$%~@%$%^~
		final JTextArea eml = new JTextArea(DataStore.getConfig().getEmail());
		final JTextArea tp = new JTextArea(DataStore.getConfig().getSMTP());
		//#$%@^@&@#%&^@#%^#%^@&@*@^%^#%^^@&@%*@$^&@$^@#$%!@#~#$~#%~^#^&@%^@^$!%#!~$%~@$%~@%$%^~
		
		eml.setForeground(new Color(3, 28, 120));
		tp.setForeground(new Color(3, 28, 120));

		setModal(true);

		setSize(new Dimension(600,400));
		setMaximumSize(new Dimension(600, 400));

		setLayout(new BorderLayout());
		setBackground(new Color(3, 28, 120));
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
				//#$%@^@&@#%&^@#%^#%^@&@*@^%^#%^^@&@%*@$^&@$^@#$%!@#~#$~#%~^#^&@%^@^$!%#!~$%~@$%~@%$%^~
				int temp = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel?", "Confirm cancel", 2);
				//#$%@^@&@#%&^@#%^#%^@&@*@^%^#%^^@&@%*@$^&@$^@#$%!@#~#$~#%~^#^&@%^@^$!%#!~$%~@$%~@%$%^~
				if(temp ==JOptionPane.YES_OPTION){
					dispose();
				}
			}
		});

		sve.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if(!tp.getText().contains("smtp")){
					JOptionPane.showMessageDialog(tp, "Please enter in a valid smtp server");
				}
				else if(!eml.getText().contains("@") && !eml.getText().endsWith(".***")){
					JOptionPane.showMessageDialog(eml, "Please enter a valid email address");
				}

				else{
					int temp = JOptionPane.showConfirmDialog(null, "Are you sure you want to save?", "Confirm Save", 2);
					if(temp ==JOptionPane.YES_OPTION){

						DataStore.setConfig(eml.getText(), tp.getText());
						dispose();
					}
				}
			}
		});
		setVisible(true);
	}
}