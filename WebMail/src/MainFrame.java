//Ellen Folk, Devin Burnes
//CPSC 215 002
//Project 3: Email Client

import javax.swing.*;
import javax.swing.event.*;
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
	JMenuItem msg;
	JMenuItem con;
	JMenuItem fileExit;
	JMenu editMenu;
	JMenuItem editCopy;
	JMenuItem editCut;
	JMenuItem editPaste;
	JMenu confiMenu;
	JMenuItem confiConfigure;
	JMenu helpMenu;
	JMenuItem helpAbout;
	
	ListSelectionModel LSM;
	
	JTable mainContacts;
	
	JButton mainAdd;
	JButton mainEdit;
	JButton mainDel;
	
	Configuration CNF;
	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	
	public MainFrame(String caption){
		//set caption
		super(caption);
		
		//set icon
		ImageIcon img = new ImageIcon("imgs/mainIcon.jpg");
		setIconImage(img.getImage());
		
		//make size and exit on close
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(1000,800));
		//setSize(new Dimension(100,100));
		
		createMenus();
		
		createConfig();
		
		ContactTableModel CTM = new ContactTableModel();
		createFrame(CTM);
		
	}

	public void createMenus(){

		UIManager.put("menubar.selectionBackground", Color.black);
		UIManager.put("Menu.selectionForeground",Color.white);
		
		//create top menu
		menubar = new JMenuBar();
		menubar.setBackground(Color.GREEN);
		
		//File
		fileMenu = new JMenu("File");
		fileMenu.setBackground(Color.GREEN);
		fileNew = new JMenu("New");
		msg = new JMenuItem("Message");
		con = new JMenuItem("Contact");
		fileNew.add(msg);
		fileNew.add(con);
		fileMenu.add(fileNew);		
		fileExit = new JMenuItem("Exit");
		fileMenu.add(fileExit);
		
		msg.addActionListener(this);
		con.addActionListener(this);
		fileExit.addActionListener(this);
		
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
				
	}
	
	public void createConfig(){
		DataStore DS = DataStore.getInstance();
		CNF = DS.getConfig();
		CNF.setEmail("blah@user.net");
		CNF.setSMTP("SMTP.gmail.com");
	}
	
	public void createFrame(TableModel CTM){
		//TableModel ContactModel = new TableModel();
		
		
		//JTable mainContacts = new JTable(CV, DS.ColNames);
		mainContacts = new JTable(CTM);
		mainContacts.setGridColor(Color.black);
		mainContacts.setRowSelectionAllowed(true);
		
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
		mainEdit.setEnabled(false);
		mainDel.setEnabled(false);
		pane.setLayout(new BorderLayout());
		
		mainContacts.addMouseListener(new MouseAdapter() {
			   public void mouseClicked(MouseEvent e) {
			      if (e.getClickCount() == 2) {
			         JTable target = (JTable)e.getSource();
			         int row = target.getSelectedRow();
			         
			         DataStore DS = DataStore.getInstance();
				     new EmailTransmissionDlg(DS.getContactAt(row));
			         }
			   }
			});
		
		
		LSM = mainContacts.getSelectionModel();
		LSM.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		LSM.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                //Ignore extra messages.                
            	if (e.getValueIsAdjusting()) {
            		return;
            	}
            	mainEdit.setEnabled(true);
            	mainDel.setEnabled(true);
            final ListSelectionModel event = (ListSelectionModel)e.getSource();
            	
            	mainEdit.addActionListener(new ActionListener(){
            		
					public void actionPerformed(ActionEvent e2) {
						int row = event.getMinSelectionIndex();
				           
			            DataStore DS = DataStore.getInstance();				            				            
					    new ContactEditingDlg(DS.getContactAt(row), row);
					}            		
            	});
            	
            	mainDel.addActionListener(new ActionListener(){
            		
            		public void actionPerformed(ActionEvent e3) {
            			
            			if(e3.getSource() == mainDel){
            			int row = event.getMinSelectionIndex();
            			
            			DataStore DS = DataStore.getInstance();
            			
						int temp = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the contact?", "Confirm Delete", 2);
						if(temp ==JOptionPane.YES_OPTION){
							DS.rmContactAt(row);
						}
						else if (temp == JOptionPane.NO_OPTION){
						}
            			}
            		}
            	});           	
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
	
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == helpAbout)
		{
			
			//JOptionPane.showMessageDialog(null, "A button was pressed");
			new SystemInformationDlg();
	
		}
		
		else if (arg0.getSource() == msg){
			new EmailTransmissionDlg();
		}
		
		else if (arg0.getSource() == con){
			new ContactEditingDlg();
		}
		
		else if (arg0.getSource() == mainAdd){
			new ContactEditingDlg();
		}

		else if (arg0.getSource() == fileExit){
			int temp = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Confirm Exit", 2);
			if(temp ==JOptionPane.YES_OPTION){
				System.exit(EXIT_ON_CLOSE);
			}
		}
		
		else if (arg0.getSource() == confiConfigure){
			new ConfigurationDlg(CNF);
		}
		
	}
	public static void main(String[] args) {
		MainFrame myFrame = new MainFrame("TigerMail v1");
		myFrame.setVisible(true);

	}

	
	
}
