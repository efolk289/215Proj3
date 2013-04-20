import javax.swing.*;
import java.awt.*;

public class ConfigurationDlg extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5L;
	
	ConfigurationDlg(){
		
		setVisible(true);
		setModal(true);
		setSize(new Dimension(500,400));
		setBackground(Color.green);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

}
