import javax.swing.*;

import java.awt.*;

public class EmailTransmissionDlg extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8L;
	
	JButton ETSend;
	JButton ETCancel;
	
	EmailTransmissionDlg(){
		setVisible(true);
		setModal(true);
		setSize(new Dimension(500,400));
		setBackground(Color.black);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel info = new JPanel();
		info.setForeground(Color.white);
		info.add(new Label("A 215 email client"));
		JPanel info2 = new JPanel();
		info2.add(new Label("asdf"));
		add(info);
		add(info2);
	}
	
	EmailTransmissionDlg(Contact c){
		this();
		
		//make recipient field the email address of contact
	}

}
