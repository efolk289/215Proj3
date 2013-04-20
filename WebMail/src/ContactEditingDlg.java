import javax.swing.*;

import java.awt.*;

public class ContactEditingDlg extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7L;

	JButton CEDSave;
	JButton CEDCancel;
	
	
	ContactEditingDlg(){
		setVisible(true);
		setModal(true);
		setSize(new Dimension(500,400));
		setBackground(Color.blue);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel info = new JPanel();
		info.setForeground(Color.white);
		info.add(new Label("A 215 email client"));
		JPanel info2 = new JPanel();
		info2.add(new Label("asdf"));
		add(info);
		add(info2);
	}
	
	ContactEditingDlg(Contact c){
		this();
		
		//populate 
	}
}
