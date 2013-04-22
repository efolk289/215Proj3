//Ellen Folk, Devin Burnes
//CPSC 215 002
//Project 3: Email Client

import javax.swing.*;

import java.awt.*;

public class SystemInformationDlg extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6L;
	
	SystemInformationDlg(){
		
		setModal(true);
		setSize(new Dimension(600,400));
		setMaximumSize(new Dimension(600, 400));
		setBackground(new Color(3, 28, 120));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		//set icon
		ImageIcon img = new ImageIcon("imgs/mainIcon.jpg");
		setIconImage(img.getImage());
		
		JTextArea about = new JTextArea(
				"An email client designed by Ellen Folk and Devin Burnes for CPSC 215 002.\n" +
				"Designed in Eclipse Juno on OSX 10.8 and Windows 7\n" +
				"");
		about.setEditable(false);
		about.setBackground(new Color(3, 28, 120));
		about.setForeground(Color.white);
		about.setFont(new Font("SansSerif", Font.BOLD, 14));

		add(about);
		
		
		
		setVisible(true);
		
	}

}
