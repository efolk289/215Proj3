

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseListener;
//import java.util.Vector;
import java.util.Vector;

public class MainFrame extends JFrame implements ActionListener {

	JMenuBar menubar;
	JMenu fileMenu;
	JMenu fileNew;
	JMenuItem new1;
	JMenuItem new2;
	JMenuItem fileExit;
	JMenu editMenu;
	JMenuItem editCopy;
	JMenuItem editCut;
	JMenuItem editPaste;
	JMenu confiMenu;
	JMenuItem confiConfigure;
	JMenu helpMenu;
	JMenuItem helpAbout;
	
	JButton mainAdd;
	JButton mainEdit;
	JButton mainDel;
	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	
	public MainFrame(String caption){
		//set caption
		super(caption);
		
		//set icon
		//ImageIcon img = new ImageIcon(pathToFileOnDisk);
		//mYFrame.setIconImage(img.getImage());
		
		//JFrame.setIconImage(Image image);
		
		//make size and exit on close
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(1000,800));
		//setSize(new Dimension(100,100));
		
		createMenus();
		
		ContactTableModel CTM = new ContactTableModel();
		createFrame(CTM);
		
	}

	public void createMenus(){
		//create top menu
		menubar = new JMenuBar();
		menubar.setBackground(Color.GREEN);
		
		//File
		fileMenu = new JMenu("File");
		fileMenu.setBackground(Color.GREEN);
		fileNew = new JMenu("New");
		new1 = new JMenuItem("Message");
		new2 = new JMenuItem("Contact");
		fileNew.add(new1);
		fileNew.add(new2);
		fileMenu.add(fileNew);		
		fileExit = new JMenuItem("Exit");
		fileExit.addActionListener(this);
		fileMenu.add(fileExit);
		
		//Edit
		editMenu = new JMenu("Edit");
		editMenu.setBackground(Color.GREEN);
		editCopy = new JMenuItem("Copy");
		editCut = new JMenuItem("Cut");
		editPaste = new JMenuItem("Paste");
		editMenu.add(editCopy);
		editMenu.add(editCut);
		editMenu.add(editPaste);
		
		//Configure
		confiMenu = new JMenu("Configuration");
		confiMenu.setBackground(Color.GREEN);
		confiConfigure = new JMenuItem("Configure");
		confiConfigure.addActionListener(this);
		
		confiMenu.add(confiConfigure);
		
		//Help
		helpMenu = new JMenu("Help");
		helpMenu.setBackground(Color.GREEN);
		helpAbout = new JMenuItem("About");
		
		helpAbout.addActionListener(this);
		
		helpMenu.add(helpAbout);
		
		//add menus to menubar
		menubar.add(fileMenu);
		menubar.add(editMenu);
		menubar.add(confiMenu);
		menubar.add(helpMenu);
		
		//set jframe menubar, 
		setJMenuBar(menubar);
		
		//filemenu.add(new JSeparator());
		
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == helpAbout)
		{
			System.out.println("Wow, a button got pressed!");
			
			//JOptionPane.showMessageDialog(null, "A button was pressed");
			new SystemInformationDlg();
			
			System.out.println("Help Dialog Closed.");			
		}
		
		else if (arg0.getSource() == mainAdd){
			new ContactEditingDlg();
		}
		
		else if (arg0.getSource() == mainEdit){
			new ContactEditingDlg();
		}
		
		else if (arg0.getSource() == fileExit){
			int temp = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Confirm Exit", 2);
			if(temp ==JOptionPane.YES_OPTION){
				System.exit(EXIT_ON_CLOSE);
			}
		}
		
		else if (arg0.getSource() == confiConfigure){
			new ConfigurationDlg();
		}
		
	}

	
	public void createFrame(TableModel CTM){
		//TableModel ContactModel = new TableModel();
		
		
		//JTable mainContacts = new JTable(CV, DS.ColNames);
		JTable mainContacts = new JTable(CTM);
		mainContacts.setGridColor(Color.black);
		
		//TableColumnModel cols = mainContacts.getColumnModel();
		//JTableHeader JTH = new JTableHeader(cols);
	
		
		((ContactTableModel) CTM).addContact(new Contact("fir1", "las1", "addr1", "phone1", "em1"));
		
		Container pane = getContentPane();
		pane.add(mainContacts);
		JScrollPane scrollPane = new JScrollPane(mainContacts);
		mainContacts.setFillsViewportHeight(true);
		mainContacts.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setVerticalScrollBarPolicy  (JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy  (JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//scrollPane.setColumnHeaderView(mainContacts.getTableHeader());
		
		//add(scrollPane);
		mainContacts.setForeground(Color.black);
		scrollPane.setForeground(Color.black);
		
		mainAdd = new JButton("Add"); //can accept an icon
		mainEdit = new JButton("Edit");
		mainDel = new JButton("Delete");
		
		mainAdd.addActionListener(this);
		mainEdit.addActionListener(this);
		mainDel.addActionListener(this);
		
		pane.setLayout(new BorderLayout());
		
		mainContacts.addMouseListener(new MouseAdapter() {
			   public void mouseClicked(MouseEvent e) {
			      if (e.getClickCount() == 2) {
			         JTable target = (JTable)e.getSource();
			         int row = target.getSelectedRow();
			         
			         DataStore DS = DataStore.getInstance();
			         Vector<Contact> temp = DS.getContacts();
			         new EmailTransmissionDlg(temp.elementAt(row));
			         }
			   }
			});
		
		
		
		
		JPanel inner = new JPanel();
	    inner.setLayout(new GridLayout(1, 3));
	    inner.add(mainAdd);
	    inner.add(mainEdit);
	    inner.add(mainDel);
	    pane.add(inner, BorderLayout.SOUTH);
		
		pane.add(scrollPane, BorderLayout.CENTER);
		

	}
	public static void main(String[] args) {
		MainFrame myFrame = new MainFrame("TigerMail v1");
		myFrame.setVisible(true);

	}

	
	
}
