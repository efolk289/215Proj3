//Ellen Folk, Devin Burnes
//CPSC 215 002
//Project 3: Email Client

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
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
		setMaximumSize(new Dimension(1000, 800));
		
		createMenus();
		
		ContactTableModel CTM = new ContactTableModel();
		createFrame(CTM);
		
	}
	
	//Save the contact data upon program exit
		public static void saveAndClose(){
			try {
				DataStore.saveContact();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				DataStore.saveConfiguration(DataStore.getConfig().getEmail(), 
											DataStore.getConfig().getSMTP());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	public void createMenus(){
		
		//create top menu
		menubar = new JMenuBar();
		menubar.setBackground(new Color(3,28,120));
		
		//File
		fileMenu = new JMenu("File");
		fileMenu.setBackground(new Color(3,28,120));
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
		editMenu.setBackground(new Color(3,28,120));
		editCopy = new JMenuItem("Copy");
		editCut = new JMenuItem("Cut");
		editPaste = new JMenuItem("Paste");
		editMenu.add(editCopy);
		editMenu.add(editCut);
		editMenu.add(editPaste);
		
		//Configure
		confiMenu = new JMenu("Configuration");
		confiMenu.setBackground(new Color(3,28,120));
		confiConfigure = new JMenuItem("Configure");
		confiConfigure.addActionListener(this);
		
		confiMenu.add(confiConfigure);
		
		//Help
		helpMenu = new JMenu("Help");
		helpMenu.setBackground(new Color(3,28,120));
		helpAbout = new JMenuItem("About");
		
		helpAbout.addActionListener(this);
		
		helpMenu.add(helpAbout);
		
		//add menus to menubar
		menubar.add(fileMenu);
		menubar.add(editMenu);
		menubar.add(confiMenu);
		menubar.add(helpMenu);
		menubar.setForeground(Color.white);
		
		//set jframe menubar, 
		setJMenuBar(menubar);
				
	}
	

	
	public void createFrame(TableModel CTM){
		//TableModel ContactModel = new TableModel();
		
		
		DataStore DS = DataStore.getInstance();
		/*//#$%@^@&@#%&^@#%^#%^@&@*@^%^#%^^@&@%*@$^&@$^@#$%!@#~#$~#%~^#^&@%^@^$!%#!~$%~@$%~@%$%^~
        DefaultTableModel hd = new DefaultTableModel();
        hd.setColumnIdentifiers(DS.ColNames);

        DS.addContact(new Contact("Devin", "Burnes", "Iniameni", "843-812-077", "dburnes@clemson.edu"));
		
		for(int i = 0; i < DS.getContacts().size(); i++){
			Vector<String> v = new Vector<String>();
			v.add(DS.getContactAt(i).getfName());
			v.add(DS.getContactAt(i).getlName());
			v.add(DS.getContactAt(i).getAddress());
			v.add(DS.getContactAt(i).getPhone());
			v.add(DS.getContactAt(i).getEmail());

			String [] s = v.toArray(new String[v.size()]);
			
			hd.insertRow(i, s);
		}
		
		 int width = 0;
		 for (int row = 0; row < table.getRowCount(); row++) {
		     TableCellRenderer renderer = table.getCellRenderer(row, myColumn);
		     Component comp = table.prepareRenderer(renderer, row, myColumn);
		     width = Math.max (comp.getPreferredSize().width, width);
		 }
		
		
		mainContacts = new JTable(hd);
		 //#$%@^@&@#%&^@#%^#%^@&@*@^%^#%^^@&@%*@$^&@$^@#$%!@#~#$~#%~^#^&@%^@^$!%#!~$%~@$%~@%$%^~
		 */
		
		 
		 //JTable mainContacts = new JTable(CV, DS.ColNames);
		mainContacts = new JTable(CTM);
		//((ContactTableModel) CTM).setColumnIdentifiers(DS.ColNames);
		mainContacts.setGridColor(new Color(3, 28, 120));
		mainContacts.setForeground(new Color(3, 28, 120));
		mainContacts.setRowSelectionAllowed(true);
		
		String[] hdr = {"First Name",
                "Last Name",
                "Address",
                "Phone #",
                "Email Address"};

		//((ContactTableModel) CTM).addContact(new Contact("Devin", "Burnes", "Iniameni", "843-812-077", "dburnes@clemson.edu"));
		/*
		mainContacts.getTableHeader().setFont( new Font( "SansSerif", Font.BOLD, 12));
		mainContacts.setFont(new Font("SansSerif", Font.PLAIN, 12));
		*/
		
		Container pane = getContentPane();
		pane.add(mainContacts);
		JScrollPane scrollPane = new JScrollPane(mainContacts);
		mainContacts.setFillsViewportHeight(true);
		//mainContacts.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setVerticalScrollBarPolicy  (JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy  (JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//scrollPane.setColumnHeaderView(mainContacts.getTableHeader());
		
		//add(scrollPane);
		mainContacts.setForeground(Color.black);
		scrollPane.setForeground(Color.black);
		
		mainAdd = new JButton("Add"); //can accept an icon
		mainEdit = new JButton("Edit");
		mainDel = new JButton("Delete");
		
		mainAdd.setForeground(new Color(3,28,120));
		mainEdit.setForeground(new Color(3,28,120));
		mainDel.setForeground(new Color(3,28,120));
		
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
			new ConfigurationDlg();
		}
		
	}
	
	public static void checkPaths(){
		File ContactPath = new File(DataStore.dir);
		File ConfigFile = new File(DataStore.cfgP);
		File ContactFile = new File(DataStore.Cpath);
		
		boolean exists = ContactPath.exists();
		if(!exists){
			try{
				exists = ContactPath.mkdir();
				
			}catch(SecurityException Se){
				System.out.println("Data directory creation failed");
			}
		}
		exists = ContactFile.exists();
		if(!exists){
			try{
				exists = ContactFile.createNewFile();
				
			}catch(SecurityException Se){
				System.out.println("Contact File creation failed");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		exists = ConfigFile.exists();
		if(!exists){
			try{
				exists = ConfigFile.createNewFile();
				
			}catch(SecurityException Se){
				System.out.println("Configuration File creation failed");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	public static void main(String[] args) {
		checkPaths();

		try {
			DataStore.loadContacts();
			DataStore.loadConfiguration();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		MainFrame myFrame = new MainFrame("TigerMail v1");
		myFrame.setVisible(true);
		
		/* save the data on program exit */
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
		    public void run() {
		    	saveAndClose();
		    }
		}));

	}

	
	
}
