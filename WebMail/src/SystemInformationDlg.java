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
		setVisible(true);
		setModal(true);
		setSize(new Dimension(500,400));
		setBackground(Color.darkGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel info = new JPanel();
		info.add(new Label("A 215 email client"));
		JPanel info2 = new JPanel();
		info2.add(new Label("asdf"));
		add(info);
		add(info2);
		
	}

}
