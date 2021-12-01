package main;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class HomeBreaks extends JFrame implements DocumentListener {
	CardLayout cards, myAccountCards, crd, myPropertiesCards, facilitiesCards;
	Container c = getContentPane();
	public static String current = "";
	Popup k;
	JPanel home, guestLogin, hostLogin, myAccount, searchResult, viewProperties, myProperties, myFacilities;
	JButton toHome, host, enquirer, guest, gsuBtn, hsuBtn, glBtn, hlBtn, toGSU, toHSU, search, homeBtn, viewMoreBtn, newPropertyBtn;
	JTextField fName_input_gsu, lName_input_gsu, add1_gsu, add2_gsu, add3_gsu, add4_gsu, phone_input_gsu, id_input_gsu, id_input_gl;
	JTextField fName_input_hsu, lName_input_hsu, add1_hsu, add2_hsu, add3_hsu, add4_hsu, phone_input_hsu, id_input_hsu, id_input_hl;
	JTextField searchProperty;
	JLabel warning_gsu = new JLabel("");
	JLabel warning_hsu = new JLabel("");
	JPasswordField pw_input_gsu, confirm_input_gsu, pw_input_gl;
	JPasswordField pw_input_hsu, confirm_input_hsu, pw_input_hl;
	JPanel p = new JPanel();
	JPanel pBookings = new JPanel();
	public static Dimension screen;
	public static Host currentHost;
	public static Guest currentGuest;
	
	Map<Integer, Property> properties;
	String cityFilter = "Sheffield";
	String hostFilter = "";
	String propertyNameFilter = "";
	Property chosenHouse;
	Address address;
	static Facilities facility;
	ParkType parking = null;
	BedType bed1 = null;
	BedType bed2 = null;
	Map<Integer, Property> filterProperties = new HashMap<Integer, Property>();
	
	// TODO if there's time: enhance the searching feature to search for matching results, not exact ones
	// check if price input is correct properly
	
	final Font plain = new Font("Verdana", Font.PLAIN, 25);
	final Font bold = new Font("Verdana", Font.BOLD, 50);
	
	public HomeBreaks() throws ParseException {
		System.out.println("Loading...");
		TDatabase.Initialise();
		properties = TDatabase.Properties;
		chosenHouse = properties.get(25);
		startGUI();
		System.out.println("Successfully loaded.");
	}
	
	public void startGUI() throws ParseException {
		Toolkit tk = Toolkit.getDefaultToolkit();
		screen = tk.getScreenSize();
				
		setSize(screen.width, screen.height);
		setTitle("HomeBreaks Plc");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//current = "GH";
		//currentGuest = TDatabase.Guests.get(7);
		chosenHouse = TDatabase.Properties.get(71);
		
		cards = new CardLayout();
		c.setLayout(cards);
		c.add("Home",  home());
		c.add("Guest Sign Up", guestSU());
		c.add("Host Sign Up", hostSU());
		c.add("Guest Login", guestLogin());
		c.add("Host Login", hostLogin());
		c.add("Inquiry", enquirer());
		c.add("Host Home", hostHome());
		//c.add("House View", houseView());
		c.add("Add Living", addLiving());
		c.add("Add Utility", addUtility());
		c.add("Add Bath", addBath());
		c.add("Add Outdoor", addOutdoor());
		c.add("Add Bedroom", addBedroom());
		c.add("Add Kitchen", addKitchen());
		// Scrollpane setting
		//c.add("View Properties", viewProperties(null, "City"));
		//c.add("Booking", bookingPage());
		
		setVisible(true);
	}
	
	public JPanel home() {
		JPanel home = new JPanel();
		JPanel hp = new JPanel();
		hp.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		home.setBorder(createTitledBorder("Welcome to HomeBreaks Plc"));
		home.setLayout(new GridBagLayout());
		
        JPanel buttons;
		
		JLabel opt;

		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		opt = new JLabel("To start, choose a role");
		opt.setFont(plain);
		home.add(opt, g);
		
		buttons = new JPanel();
		
		host = new JButton("Host");
		guest = new JButton("Guest");
		enquirer = new JButton("Enquirer");
		
		host.setFont(plain);
		guest.setFont(plain);
		enquirer.setFont(plain);
		
		host.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.show(c, "Host Login");
				current = "HL";
				setTitle("Host Login");
			}
		});
		
		guest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.show(c, "Guest Login");
				current = "GL";
				setTitle("Guest Login");
			}
		});
		
		enquirer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.show(c, "Inquiry");
				current = "Inquiry";
				setTitle("Inquiry");
			}
		});
		
		
		host.setPreferredSize(new Dimension(400, 500));
		guest.setPreferredSize(new Dimension(400, 500));
		enquirer.setPreferredSize(new Dimension(400, 500));
		
		buttons.add(host);
		buttons.add(guest);
		buttons.add(enquirer);
		buttons.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		setConstraints(g, 0, 1, GridBagConstraints.CENTER);
		home.add(buttons, g);
		
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		hp.add(home, g);
		return hp;
	}
	
	public JPanel guestSU() {
		JPanel gsu = new JPanel();
		JPanel hp = new JPanel();
		hp.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		gsu.setBorder(createTitledBorder("Sign Up as Guest"));
		gsu.setLayout(new GridBagLayout());
		
		JLabel fName, lName, addA, addB, addC, addD, phone, id, pw, confirm_pw;
		
		// Input for user's name
		setConstraints(g, 0, 0, GridBagConstraints.EAST);
		fName = new JLabel("First Name: ");
		fName.setFont(plain);
		gsu.add(fName, g);
		
		setConstraints(g, 1, 0, GridBagConstraints.WEST);
		fName_input_gsu = new JTextField(20);
		fName_input_gsu.setFont(plain);
		gsu.add(fName_input_gsu, g);
		
		setConstraints(g, 0, 1, GridBagConstraints.EAST);
		lName = new JLabel("Last Name: ");
		lName.setFont(plain);
		gsu.add(lName, g);
		
		setConstraints(g, 1, 1, GridBagConstraints.WEST);
		lName_input_gsu = new JTextField(20);
		lName_input_gsu.setFont(plain);
		gsu.add(lName_input_gsu, g);
		
		// Input for user's address
		setConstraints(g, 0, 2, GridBagConstraints.EAST);
		addA = new JLabel("House No.: ");
		addA.setFont(plain);
		gsu.add(addA, g);
		
		setConstraints(g, 1, 2, GridBagConstraints.WEST);
		add1_gsu = new JTextField(20);
		add1_gsu.setFont(plain);
		gsu.add(add1_gsu, g);
		
		setConstraints(g, 0, 3, GridBagConstraints.EAST);
		addB = new JLabel("Street Name: ");
		addB.setFont(plain);
		gsu.add(addB, g);
		
		setConstraints(g, 1, 3, GridBagConstraints.WEST);
		add2_gsu = new JTextField(20);
		add2_gsu.setFont(plain);
		gsu.add(add2_gsu, g);
		
		setConstraints(g, 0, 4, GridBagConstraints.EAST);
		addC = new JLabel("Postcode: ");
		addC.setFont(plain);
		gsu.add(addC, g);
		
		setConstraints(g, 1, 4, GridBagConstraints.WEST);
		add3_gsu = new JTextField(20);
		add3_gsu.setFont(plain);
		gsu.add(add3_gsu, g);
		
		setConstraints(g, 0, 5, GridBagConstraints.EAST);
		addD = new JLabel("Place Name: ");
		addD.setFont(plain);
		gsu.add(addD, g);
		
		setConstraints(g, 1, 5, GridBagConstraints.WEST);
		add4_gsu = new JTextField(20);
		add4_gsu.setFont(plain);
		gsu.add(add4_gsu, g);
		
		// Input for phone number
		setConstraints(g, 0, 6, GridBagConstraints.EAST);
		phone = new JLabel("Phone No.: ");
		phone.setFont(plain);
		gsu.add(phone, g);
		
		setConstraints(g, 1, 6, GridBagConstraints.WEST);
		phone_input_gsu = new JTextField(20);
		phone_input_gsu.setFont(plain);
		gsu.add(phone_input_gsu, g);
		
		// Input for email
		setConstraints(g, 0, 7, GridBagConstraints.EAST);
		id = new JLabel("Email: ");
		id.setFont(plain);
		gsu.add(id, g);
		
		setConstraints(g, 1, 7, GridBagConstraints.WEST);
		id_input_gsu = new JTextField(20);
		id_input_gsu.setFont(plain);
		gsu.add(id_input_gsu, g);
		
		// Input for password
		setConstraints(g, 0, 8, GridBagConstraints.EAST);
		pw = new JLabel("Password: ");
		pw.setFont(plain);
		gsu.add(pw, g);
		
		setConstraints(g, 1, 8, GridBagConstraints.WEST);
		pw_input_gsu = new JPasswordField(20);
		pw_input_gsu.setFont(plain);
		pw_input_gsu.setEchoChar('*');
		pw_input_gsu.getDocument().addDocumentListener(this);
		gsu.add(pw_input_gsu, g);
		
		// Input for confirm password
		setConstraints(g, 0, 9, GridBagConstraints.EAST);
		confirm_pw = new JLabel("Confirm Password: ");
		confirm_pw.setFont(plain);
		gsu.add(confirm_pw, g);
		
		setConstraints(g, 1, 9, GridBagConstraints.WEST);
		confirm_input_gsu = new JPasswordField(20);
		confirm_input_gsu.setFont(plain);
		confirm_input_gsu.setEchoChar('*');
		confirm_input_gsu.getDocument().addDocumentListener(this);
		gsu.add(confirm_input_gsu, g);
		
		setConstraints(g, 1, 10, GridBagConstraints.WEST);
		gsuBtn = new JButton("Sign up");
		gsuBtn.setFont(plain);
		gsuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current = "GSU";
				String fName = fName_input_gsu.getText();
				String lName = lName_input_gsu.getText();
				String houseName = add1_gsu.getText();
				String streetName = add2_gsu.getText();
				String city = add3_gsu.getText();
				String postcode = add4_gsu.getText();
				String phone = phone_input_gsu.getText();
				String email = id_input_gsu.getText();
				//Hash Password
				String password="";
				try {
					password = TDatabase.encryptThisString(String.valueOf(pw_input_gsu.getPassword()));
				} 
				catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (fName.isEmpty() || lName.isEmpty() || houseName.isEmpty() || streetName.isEmpty() || city.isEmpty() || fName.isEmpty() || postcode.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty()) {
					showMessageDialog(null, "Please fill in all blanks.");
				}
				else {
					Address add = new Address(houseName, streetName, postcode, city, true);
					Guest guest = new Guest(fName, lName, add, phone, email, password);
					TDatabase.Guests.put(Integer.parseInt(guest.userID), guest);
					
					if (!(guest.getSuccess())) {
						showMessageDialog(null, "Sign up failed.");
					}
					else
					{
						TDatabase.Guests.put(Integer.parseInt(guest.getID()), guest);
						showMessageDialog(null, "Successfully signed up!");
						cards.show(c, "Guest Login");
					}
				}
			}
		});
		gsu.add(gsuBtn, g);
		
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		hp.add(gsu, g);
		setConstraints(g, 0, 1, GridBagConstraints.CENTER);
		hp.add(createHomeBtnPanel(), g);
		setConstraints(g, 0, 2, GridBagConstraints.CENTER);
		hp.add(warning_gsu, g);
		
		return hp;
	}
	
	public JPanel hostSU() {
		JPanel hsu = new JPanel();
		JPanel hp = new JPanel();
		hp.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		hsu.setBorder(createTitledBorder("Sign Up as Host"));
		hsu.setLayout(new GridBagLayout());
		
		JLabel fName, lName, addA, addB, addC, addD, phone, id, pw, confirm_pw;
		
		// Input for user's name
		setConstraints(g, 0, 0, GridBagConstraints.EAST);
		fName = new JLabel("First Name: ");
		fName.setFont(plain);
		hsu.add(fName, g);
		
		setConstraints(g, 1, 0, GridBagConstraints.WEST);
		fName_input_hsu = new JTextField(20);
		fName_input_hsu.setFont(plain);
		hsu.add(fName_input_hsu);
		
		setConstraints(g, 0, 1, GridBagConstraints.EAST);
		lName = new JLabel("Last Name: ");
		lName.setFont(plain);
		hsu.add(lName, g);
		
		setConstraints(g, 1, 1, GridBagConstraints.WEST);
		lName_input_hsu = new JTextField(20);
		lName_input_hsu.setFont(plain);
		hsu.add(lName_input_hsu, g);
				
		// Input for user's address
		setConstraints(g, 0, 2, GridBagConstraints.EAST);
		addA = new JLabel("House No.: ");
		addA.setFont(plain);
		hsu.add(addA, g);
		
		setConstraints(g, 1, 2, GridBagConstraints.WEST);
		add1_hsu = new JTextField(20);
		add1_hsu.setFont(plain);
		hsu.add(add1_hsu, g);
		
		setConstraints(g, 0, 3, GridBagConstraints.EAST);
		addB = new JLabel("Street Name: ");
		addB.setFont(plain);
		hsu.add(addB, g);
		
		setConstraints(g, 1, 3, GridBagConstraints.WEST);
		add2_hsu = new JTextField(20);
		add2_hsu.setFont(plain);
		hsu.add(add2_hsu, g);
		
		setConstraints(g, 0, 4, GridBagConstraints.EAST);
		addC = new JLabel("Postcode: ");
		addC.setFont(plain);
		hsu.add(addC, g);
		
		setConstraints(g, 1, 4, GridBagConstraints.WEST);
		add3_hsu = new JTextField(20);
		add3_hsu.setFont(plain);
		hsu.add(add3_hsu, g);
		
		setConstraints(g, 0, 5, GridBagConstraints.EAST);
		addD = new JLabel("Place Name: ");
		addD.setFont(plain);
		hsu.add(addD, g);
		
		setConstraints(g, 1, 5, GridBagConstraints.WEST);
		add4_hsu = new JTextField(20);
		add4_hsu.setFont(plain);
		hsu.add(add4_hsu, g);
		
		// Input for phone number
		setConstraints(g, 0, 6, GridBagConstraints.EAST);
		phone = new JLabel("Phone No.: ");
		phone.setFont(plain);
		hsu.add(phone, g);
		
		setConstraints(g, 1, 6, GridBagConstraints.WEST);
		phone_input_hsu = new JTextField(20);
		phone_input_hsu.setFont(plain);
		hsu.add(phone_input_hsu, g);
		
		// Input for email
		setConstraints(g, 0, 7, GridBagConstraints.EAST);
		id = new JLabel("Email: ");
		id.setFont(plain);
		hsu.add(id, g);
		
		setConstraints(g, 1, 7, GridBagConstraints.WEST);
		id_input_hsu = new JTextField(20);
		id_input_hsu.setFont(plain);
		hsu.add(id_input_hsu, g);
			
		// Input for password
		setConstraints(g, 0, 8, GridBagConstraints.EAST);
		pw = new JLabel("Password: ");
		pw.setFont(plain);
		hsu.add(pw, g);
		
		setConstraints(g, 1, 8, GridBagConstraints.WEST);
		pw_input_hsu = new JPasswordField(20);
		pw_input_hsu.setFont(plain);
		pw_input_hsu.setEchoChar('*');
		pw_input_hsu.getDocument().addDocumentListener(this);
		hsu.add(pw_input_hsu, g);
			
		// Input for confirm password
		setConstraints(g, 0, 9, GridBagConstraints.EAST);
		confirm_pw = new JLabel("Confirm Password: ");
		confirm_pw.setFont(plain);
		hsu.add(confirm_pw, g);
		
		setConstraints(g, 1, 9, GridBagConstraints.WEST);
		confirm_input_hsu = new JPasswordField(20);
		confirm_input_hsu.setFont(plain);
		confirm_input_hsu.setEchoChar('*');
		confirm_input_hsu.getDocument().addDocumentListener(this);
		hsu.add(confirm_input_hsu, g);
		
		setConstraints(g, 1, 10, GridBagConstraints.WEST);
		hsuBtn = new JButton("Sign up");
		hsuBtn.setFont(plain);
		hsuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fName = fName_input_hsu.getText();
				String lName = lName_input_hsu.getText();
				String houseName = add1_hsu.getText();
				String streetName = add2_hsu.getText();
				String city = add3_hsu.getText();
				String postcode = add4_hsu.getText();
				String phone = phone_input_hsu.getText();
				String email = id_input_hsu.getText();
				//Hash Password
				String password="";
				try {
					password = TDatabase.encryptThisString(String.valueOf(pw_input_hsu.getPassword()));
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (fName.isEmpty() || lName.isEmpty() || houseName.isEmpty() || streetName.isEmpty() || city.isEmpty() || fName.isEmpty() || postcode.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty()) {
					showMessageDialog(null, "Please fill in all blanks.");
				}
				else {
					Address add = new Address(houseName, streetName, postcode, city, true);
					Host host = new Host(lName, fName, add, phone, email, password); // TODO change when authentication is available

					
					if (!(host.getSuccess())) {
						showMessageDialog(null, "Sign up failed.");
					}
					else
					{
						TDatabase.Hosts.put(Integer.parseInt(host.getID()), host);
						showMessageDialog(null, "Successfully signed up!");
						cards.show(c, "Host Login");
					}
				}
			}
		});
		hsu.add(hsuBtn, g);
		
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		hp.add(hsu, g);
		setConstraints(g, 0, 1, GridBagConstraints.CENTER);
		hp.add(createHomeBtnPanel(), g);
		setConstraints(g, 0, 2, GridBagConstraints.CENTER);
		hp.add(warning_hsu, g);
		
		return hp;
	}
	
	public JPanel guestLogin() {
		JPanel gl = new JPanel();
		JPanel hp = new JPanel();
		hp.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		gl.setBorder(createTitledBorder("Login as Guest"));
		gl.setLayout(new GridBagLayout());
		
		JLabel id, pw;
		
		setConstraints(g, 0, 0, GridBagConstraints.EAST);
		id = new JLabel("User ID: ");
		id.setFont(plain);
		gl.add(id, g);
		
		setConstraints(g, 1, 0, GridBagConstraints.WEST);
		id_input_gl = new JTextField(20);
		id_input_gl.setFont(plain);
		id_input_gl.getDocument().addDocumentListener(this);
		gl.add(id_input_gl, g);
		
		setConstraints(g, 0, 1, GridBagConstraints.EAST);
		pw = new JLabel("Password: ");
		pw.setFont(plain);
		gl.add(pw, g);
		
		setConstraints(g, 1, 1, GridBagConstraints.WEST);
		pw_input_gl = new JPasswordField(20);
		pw_input_gl.setFont(plain);
		pw_input_gl.setEchoChar('*');
		gl.add(pw_input_gl, g);
		
		setConstraints(g, 1, 2, GridBagConstraints.WEST);
		glBtn = new JButton("Log in");
		glBtn.setFont(plain);
		glBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = id_input_gl.getText();
				String pw="";
				try {
					pw = TDatabase.encryptThisString(String.valueOf(pw_input_gl.getPassword()));
				} 
				catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				String guestID;
				
				if (TDatabase.GuestLogin(email, pw)) {
					guestID = TDatabase.SearchUserID("Guest", email);
					setTitle("Guest Home");
					current = "GH";
					currentGuest = TDatabase.Guests.get(Integer.parseInt(guestID));
					c.add("Guest Home", guestHome());
					//c.add("Guest Home", guestHome(currentGuest));
					cards.show(c, "Guest Home");
				}
				else {
					showMessageDialog(null, "Wrong credentials!");
				}
			}
		}); 	
		gl.add(glBtn, g);
		
		setConstraints(g, 1, 3, GridBagConstraints.WEST);
		toGSU = new JButton("Don't have an account? Sign up!");
		toGSU.setContentAreaFilled(false);
		toGSU.setBorderPainted(false);
		toGSU.setFont(plain);
		toGSU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current = "GSU";
				setTitle("Guest Sign Up");
				cards.show(c, "Guest Sign Up");
				
			}
		});
		gl.add(toGSU, g);
		
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		hp.add(gl, g);
		setConstraints(g, 0, 1, GridBagConstraints.CENTER);
		hp.add(createHomeBtnPanel(), g);
		
		return hp;
	}
	
	public JPanel hostLogin() {
		JPanel hl = new JPanel();
		JPanel hp = new JPanel();
		hp.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		hl.setBorder(createTitledBorder("Login as Host"));
		hl.setLayout(new GridBagLayout());
		
		JLabel id, pw;
		
		setConstraints(g, 0, 0, GridBagConstraints.EAST);
		id = new JLabel("User ID: ");
		id.setFont(plain);
		hl.add(id, g);
		
		setConstraints(g, 1, 0, GridBagConstraints.WEST);
		id_input_hl = new JTextField(20);
		id_input_hl.setFont(plain);
		id_input_hl.getDocument().addDocumentListener(this);
		hl.add(id_input_hl, g);
		
		setConstraints(g, 0, 2, GridBagConstraints.EAST);
		pw = new JLabel("Password: ");
		pw.setFont(plain);
		hl.add(pw, g);
		
		setConstraints(g, 1, 2, GridBagConstraints.WEST);
		pw_input_hl = new JPasswordField(20);
		pw_input_hl.setFont(plain);
		pw_input_hl.setEchoChar('*');
		hl.add(pw_input_hl, g);
		
		setConstraints(g, 1, 3, GridBagConstraints.WEST);
		hlBtn = new JButton("Log in");
		hlBtn.setFont(plain);
		hlBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = id_input_hl.getText();
				String pw="";
				try {
					pw = TDatabase.encryptThisString(String.valueOf(pw_input_hl.getPassword()));
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String hostID;
				
				if (TDatabase.HostLogin(email, pw)) {
					hostID=TDatabase.SearchUserID("Host", email);
					HomeBreaks.currentHost = TDatabase.Hosts.get(Integer.parseInt(hostID));
					setTitle("Host Home");
					
					// Create panel that shows current host's properties
					addHostProperties();
					current = "HH";	
					c.add("Host Home", hostHome());
					cards.show(c, "Host Home");
				}
				else {
					showMessageDialog(null, "Wrong credentials!");
				}
			}
		});
		hl.add(hlBtn, g);
		
		setConstraints(g, 1, 4, GridBagConstraints.WEST);
		toHSU = new JButton("Don't have an account? Sign up!");
		toHSU.setContentAreaFilled(false);
		toHSU.setBorderPainted(false);
		toHSU.setFont(plain);
		toHSU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.show(c, "Host Sign Up");
				current = "HSU";
				setTitle("Host Sign Up");
			}
		});
		hl.add(toHSU, g);
		
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		hp.add(hl, g);
		setConstraints(g, 0, 1, GridBagConstraints.CENTER);
		hp.add(createHomeBtnPanel(), g);

		return hp;
	}
	
	public void addHostProperties() {
		JPanel p1 = new JPanel();
		p1.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		//g.weightx = 2;
		//g.weighty = 2;
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		JScrollPane mp = viewProperties(currentHost, "Host");
		p1.add(mp, g);
		setConstraints(g, 0, 1, GridBagConstraints.CENTER);
		//g.weightx = 0;
		//g.weighty = 0;
		newPropertyBtn = new JButton("Create New Property");
		newPropertyBtn.setFont(plain);
		newPropertyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myPropertiesCards.show(myProperties, "New");
			}
		});
		p1.add(newPropertyBtn, g);
		
		myProperties.add("All Properties", p1);
		myPropertiesCards.show(myProperties, "All Properties");
	}
	
	public JPanel newPropertyPanel() {		
		JPanel np = new JPanel();
		JPanel hp = new JPanel();
		hp.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		np.setBorder(HomeBreaks.createTitledBorder("New Property"));
		np.setLayout(new GridBagLayout());
		
		JLabel name, description, houseNo, stNo, postcode, place, bfast;
		JTextField shortName, add1, add2, add3, add4;
		JTextArea desc;
		JButton create;
		
		HomeBreaks.setConstraints(g, 0, 0, GridBagConstraints.EAST);
		name = new JLabel("Property name: ");
		name.setFont(plain);
		np.add(name, g);
		
		HomeBreaks.setConstraints(g, 1, 0, GridBagConstraints.WEST);
		shortName = new JTextField(20);
		shortName.setFont(plain);
		np.add(shortName, g);
		
		HomeBreaks.setConstraints(g, 0, 2, GridBagConstraints.EAST);
		description = new JLabel("Description: ");
		description.setFont(plain);
		np.add(description, g);
		
		HomeBreaks.setConstraints(g, 1, 2, GridBagConstraints.WEST);
		desc = new JTextArea(5, 20);
		JScrollPane scrollPane = new JScrollPane(desc);
		desc.setFont(plain);
		desc.setLineWrap(true);
		desc.setWrapStyleWord(true);
		np.add(scrollPane, g);
		
		HomeBreaks.setConstraints(g, 0, 3, GridBagConstraints.EAST);	
		houseNo = new JLabel("House Number: ");
		houseNo.setFont(plain);
		np.add(houseNo, g);
		
		HomeBreaks.setConstraints(g, 1, 3, GridBagConstraints.WEST);
		add1 = new JTextField(20);
		add1.setFont(plain);
		np.add(add1, g);
		
		HomeBreaks.setConstraints(g, 0, 4, GridBagConstraints.EAST);
		stNo = new JLabel("Street Name: ");
		stNo.setFont(plain);
		np.add(stNo, g);
		
		HomeBreaks.setConstraints(g, 1, 4, GridBagConstraints.WEST);
		add2 = new JTextField(20);
		add2.setFont(plain);
		np.add(add2, g);
		
		HomeBreaks.setConstraints(g, 0, 5, GridBagConstraints.EAST);
		postcode = new JLabel("Postcode: ");
		postcode.setFont(plain);
		np.add(postcode, g);
		
		HomeBreaks.setConstraints(g, 1, 5, GridBagConstraints.WEST);
		add3 = new JTextField(20);
		add3.setFont(plain);
		np.add(add3, g);
		
		HomeBreaks.setConstraints(g, 0, 6, GridBagConstraints.EAST);
		place = new JLabel("City: ");
		place.setFont(plain);
		np.add(place, g);
		
		HomeBreaks.setConstraints(g, 1, 6, GridBagConstraints.WEST);
		add4 = new JTextField(20);
		add4.setFont(plain);
		np.add(add4, g);
		
		HomeBreaks.setConstraints(g, 0, 7, GridBagConstraints.EAST);
		bfast = new JLabel("Is breakfast served?");	
		bfast.setFont(plain);
		np.add(bfast, g);
		
		HomeBreaks.setConstraints(g, 1, 7, GridBagConstraints.WEST);
		ButtonGroup grp = new ButtonGroup();
		JRadioButton yes_bfast = new JRadioButton("Yes");
		yes_bfast.setFont(plain);
		JRadioButton no_bfast = new JRadioButton("No");
		no_bfast.setFont(plain);
		grp.add(yes_bfast);
		grp.add(no_bfast);
		JPanel breakfast = new JPanel();
		breakfast.add(yes_bfast);
		breakfast.add(no_bfast);
		np.add(breakfast, g);		
		
		HomeBreaks.setConstraints(g, 1, 8, GridBagConstraints.WEST);
		create = new JButton("Create Property");
		create.setFont(plain);
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get inputs
				String sName = shortName.getText();
				String descr = desc.getText();
				String houseNo = add1.getText();
				String street = add2.getText();
				String postcode = add3.getText();
				String city = add4.getText();
				int bfast = 0;
				boolean notAllFilled = sName.isEmpty() || descr.isEmpty() || houseNo.isEmpty() || street.isEmpty() || postcode.isEmpty() || city.isEmpty();
				
				if (yes_bfast.isSelected()) {
					bfast = 1;
				}
				
				if (notAllFilled) {
					showMessageDialog(null, "All fields are mandatory.");
				}
				else {
					address = new Address(houseNo, street, postcode, city, true);
					chosenHouse = new Property(sName, descr, HomeBreaks.currentHost, address, bfast, null, true);
					cards.show(c, "Add Living");
					current = "AL";
					setTitle("New Living Space");
				}
			}
		});
		np.add(create, g);
		
		HomeBreaks.setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		hp.add(np, g);
		
		return hp;
	}
	public JPanel addLiving() {
		final Font plain = new Font("Verdana", Font.PLAIN, 25);
		JPanel al = new JPanel();
		JPanel buttons;
		
		al.setBorder(HomeBreaks.createTitledBorder("New Living Space"));
		al.setLayout(new GridBagLayout());
		
		JPanel hp = new JPanel();
		hp.setLayout(new GridBagLayout());
		
		JCheckBox checkWifi = new JCheckBox("Wifi", true);
		checkWifi.setBounds(100,100, 50,50);
		JCheckBox checkTV = new JCheckBox("TV", true);
		checkTV.setBounds(100,150, 50,50);
		JCheckBox checkSat = new JCheckBox("Satellite", true);
		checkSat.setBounds(100,200, 50,50);
		JCheckBox checkStream = new JCheckBox("Streaming", true);
		checkStream.setBounds(100,250, 50,50);
		JCheckBox checkDvd = new JCheckBox("DVD Player", true);
		checkDvd.setBounds(100,300, 50,50);
		JCheckBox checkBoard = new JCheckBox("Board Games", true);
		checkBoard.setBounds(100,350, 50,50);
		
		JButton create = new JButton("Create Living Space");
		create.setFont(plain);
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Living living = new Living(checkWifi.isSelected(), checkTV.isSelected(), checkSat.isSelected(),
						checkStream.isSelected(), checkDvd.isSelected(), checkBoard.isSelected());
				facility.setLivingFacility(living, chosenHouse.getID());
				cards.show(c, "Add Utility");
				current = "AU";
				setTitle("New Utilities");
			}
		});
		
		buttons = new JPanel();
		buttons.add(create);
		
		al.add(checkWifi);
		al.add(checkTV);
		al.add(checkSat);
		al.add(checkStream);
		al.add(checkDvd);
		al.add(checkBoard);
		al.add(buttons);
		
		return al;	        
	}
	
	public JPanel addUtility() {
		final Font plain = new Font("Verdana", Font.PLAIN, 25);
		JPanel au = new JPanel();
		JPanel buttons;
		
		au.setBorder(HomeBreaks.createTitledBorder("New Utilities"));
		au.setLayout(new GridBagLayout());
		
		JPanel hp = new JPanel();
		hp.setLayout(new GridBagLayout());
		
		JCheckBox check1 = new JCheckBox("Heating", true);
		check1.setBounds(100,100, 50,50);
		JCheckBox check2 = new JCheckBox("Washing Machine", true);
		check2.setBounds(100,150, 50,50);
		JCheckBox check3 = new JCheckBox("Drying Machine", true);
		check3.setBounds(100,200, 50,50);
		JCheckBox check4 = new JCheckBox("Fire Extinguisher", true);
		check4.setBounds(100,250, 50,50);
		JCheckBox check5 = new JCheckBox("Smoke Alarm", true);
		check5.setBounds(100,300, 50,50);
		JCheckBox check6 = new JCheckBox("First Aid Kit", true);
		check6.setBounds(100,350, 50,50);
		
		JButton create = new JButton("Create Utilities");
		create.setFont(plain);
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Utility utility = new Utility(check1.isSelected(), check2.isSelected(), check3.isSelected(),
						check4.isSelected(), check5.isSelected(), check6.isSelected());
				facility.setUtility(utility, chosenHouse.getID());
				cards.show(c, "Add Bath");
				current = "ABA";
				setTitle("New Bathroom");
			}
		});
		
		buttons = new JPanel();
		buttons.add(create);
		
		au.add(check1);
		au.add(check2);
		au.add(check3);
		au.add(check4);
		au.add(check5);
		au.add(check6);
		au.add(buttons);
		
		return au;	        
	}
    
	public JPanel addBath() {
		final Font plain = new Font("Verdana", Font.PLAIN, 25);
		JPanel aba = new JPanel();
		JPanel buttons;
		
		aba.setBorder(HomeBreaks.createTitledBorder("New Bathroom"));
		aba.setLayout(new GridBagLayout());
		
		JPanel hp = new JPanel();
		hp.setLayout(new GridBagLayout());
		
		JCheckBox check1 = new JCheckBox("Hair Drier", true);
		check1.setBounds(100,100, 50,50);
		JCheckBox check2 = new JCheckBox("Shampoo", true);
		check2.setBounds(100,150, 50,50);
		JCheckBox check3 = new JCheckBox("Toilet Paper", true);
		check3.setBounds(100,150, 50,50);
		JCheckBox check4 = new JCheckBox("Toilet", true);
		check4.setBounds(100,200, 50,50);
		JCheckBox check5 = new JCheckBox("Bath", true);
		check5.setBounds(100,250, 50,50);
		JCheckBox check6 = new JCheckBox("Shower", true);
		check6.setBounds(100,300, 50,50);
		JCheckBox check7 = new JCheckBox("Shared?", true);
		check7.setBounds(100,350, 50,50);
		
		JButton create = new JButton("Create Bathroom");
		create.setFont(plain);
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Bathroom bathroom = new Bathroom(check1.isSelected(), check2.isSelected(), check3.isSelected(),
						check4.isSelected(), check5.isSelected(), check6.isSelected(), check7.isSelected());
				facility.addBathroom(bathroom, chosenHouse.getID());
				cards.show(c, "Add Bedroom");
				current = "AB";
				setTitle("New Bedroom");
			}
		});
		
		buttons = new JPanel();
		buttons.add(create);
		
		aba.add(check1);
		aba.add(check2);
		aba.add(check3);
		aba.add(check4);
		aba.add(check5);
		aba.add(check6);
		aba.add(check7);
		aba.add(buttons);
		
		return aba;	        
	}
	
	public JPanel addBedroom() {
		final Font plain = new Font("Verdana", Font.PLAIN, 25);
		JPanel ab = new JPanel();
		JPanel buttons;
		
		ab.setBorder(HomeBreaks.createTitledBorder("New Bedroom"));
		ab.setLayout(new GridBagLayout());
		
		JPanel hp = new JPanel();
		hp.setLayout(new GridBagLayout());
		
		JCheckBox check1 = new JCheckBox("Bed Linen", true);
		check1.setBounds(100,100, 50,50);
		JCheckBox check2 = new JCheckBox("Towels", true);
		check2.setBounds(100,150, 50,50);
		JCheckBox check3a = new JCheckBox("Bed 1 = Single", true);
		check3a.setBounds(150,200, 50,50);
		JCheckBox check3b = new JCheckBox("Bed 1 = Double", true);
		check3b.setBounds(200,200, 50,50);
		JCheckBox check3c = new JCheckBox("Bed 1 = King", true);
		check3c.setBounds(150,200, 50,50);
		JCheckBox check3d = new JCheckBox("Bed 1 = Bunk", true);
		check3d.setBounds(300,200, 50,50);
		JCheckBox check4a = new JCheckBox("Bed 2 = Single", true);
		check4a.setBounds(150,200, 50,50);
		JCheckBox check4b = new JCheckBox("Bed 2 = Double", true);
		check4b.setBounds(200,200, 50,50);
		JCheckBox check4c = new JCheckBox("Bed 2 = King", true);
		check4c.setBounds(150,200, 50,50);
		JCheckBox check4d = new JCheckBox("Bed 2 = Bunk", true);
		check4d.setBounds(300,200, 50,50);
		
		if (check3a.isSelected()) {bed1 = BedType.Single;}
		if (check3b.isSelected()) {bed1 = BedType.Double;}
		if (check3c.isSelected()) {bed1 = BedType.King;}
		if (check3d.isSelected()) {bed1 = BedType.Bunk;}
		if (check4a.isSelected()) {bed2 = BedType.Single;}
		if (check4b.isSelected()) {bed2 = BedType.Double;}
		if (check4c.isSelected()) {bed2 = BedType.King;}
		if (check4d.isSelected()) {bed2 = BedType.Bunk;}
		
		JButton create = new JButton("Create Bedroom");
		create.setFont(plain);
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Bedroom bedroom = new Bedroom(check1.isSelected(), check2.isSelected(), bed1, bed2);
				facility.addBedroom(bedroom, chosenHouse.getID());
				cards.show(c, "Add Outdoor");
				current = "AO";
				setTitle("New Oudoor Facility");
			}
		});
		
		buttons = new JPanel();
		buttons.add(create);
		
		ab.add(check1);
		ab.add(check2);
		ab.add(check3a);
		ab.add(check3b);
		ab.add(check3c);
		ab.add(check3d);
		ab.add(check4a);
		ab.add(check4b);
		ab.add(check4c);
		ab.add(check4d);
		ab.add(buttons);
		
		return ab;	        
	}
    
	public JPanel addOutdoor() {
		final Font plain = new Font("Verdana", Font.PLAIN, 25);
		JPanel ao = new JPanel();
		JPanel buttons;
		
		ao.setBorder(HomeBreaks.createTitledBorder("New Outdoor Facility"));
		ao.setLayout(new GridBagLayout());
		
		JPanel hp = new JPanel();
		hp.setLayout(new GridBagLayout());
		
		JCheckBox check1 = new JCheckBox("Patio", true);
		check1.setBounds(100,100, 50,50);
		JCheckBox check2 = new JCheckBox("BBQ", true);
		check2.setBounds(100,150, 50,50);
		JCheckBox check3a = new JCheckBox("Parking = Free On-Site", true);
		check3a.setBounds(150,200, 50,50);
		JCheckBox check3b = new JCheckBox("Parking = On-Road", true);
		check3b.setBounds(200,200, 50,50);
		JCheckBox check3c = new JCheckBox("Parking = Paid", true);
		
		if (check3a.isSelected()) {parking = ParkType.free;}
		if (check3b.isSelected()) {parking = ParkType.onRoad;}
		if (check3c.isSelected()) {parking = ParkType.paid;}
		
		JButton create = new JButton("Create Outdoor Facilities");
		create.setFont(plain);
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Outdoor outdoor = new Outdoor(check1.isSelected(), check2.isSelected(), parking);
				facility.setOutdoor(outdoor, chosenHouse.getID());
				cards.show(c, "Add Kitchen");
				current = "AK";
				setTitle("New Kitchen");
			}
		});
		
		buttons = new JPanel();
		buttons.add(create);
		
		ao.add(check1);
		ao.add(check2);
		ao.add(check3a);
		ao.add(check3b);
		ao.add(check3c);
		ao.add(buttons);
		
		return ao;	        
	}
	
	public JPanel addKitchen() {
		final Font plain = new Font("Verdana", Font.PLAIN, 25);
		JPanel ak = new JPanel();
		JPanel buttons;
		
		ak.setBorder(HomeBreaks.createTitledBorder("New Kitchen"));
		ak.setLayout(new GridBagLayout());
		
		JPanel hp = new JPanel();
		hp.setLayout(new GridBagLayout());
		
		JCheckBox checkFridge = new JCheckBox("Fridge", true);
		checkFridge.setBounds(100,100, 50,50);
		JCheckBox checkMicro = new JCheckBox("Microwave", true);
		checkMicro.setBounds(100,150, 50,50);
		JCheckBox checkOven = new JCheckBox("Oven", true);
		checkOven.setBounds(100,200, 50,50);
		JCheckBox checkStore = new JCheckBox("Storage", true);
		checkStore.setBounds(100,250, 50,50);
		JCheckBox checkDish = new JCheckBox("Dishwasher", true);
		checkDish.setBounds(100,300, 50,50);
		JCheckBox checkTable = new JCheckBox("Tableware", true);
		checkTable.setBounds(100,350, 50,50);
		JCheckBox checkCook = new JCheckBox("Cookware", true);
		checkCook.setBounds(100,400, 50,50);
		JCheckBox checkBasic = new JCheckBox("Basic Provisions", true);
		checkBasic.setBounds(100,450, 50,50);
		
		JButton create = new JButton("Create Kitchen");
		create.setFont(plain);
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Kitchen kitchen = new Kitchen(checkFridge.isSelected(), checkMicro.isSelected(), checkOven.isSelected(),
						checkStore.isSelected(), checkDish.isSelected(), checkTable.isSelected(), checkCook.isSelected(),
						checkBasic.isSelected());
				facility.setKitchen(kitchen, chosenHouse.getID());
				facility.setPropertyID(chosenHouse.getID());
				chosenHouse.setFacilities(facility);
				TDatabase.Properties.put(chosenHouse.getID(), chosenHouse);
				showMessageDialog(null, "Property successfully added!");
				addHostProperties();
				current = "HH";
				myPropertiesCards.show(myProperties, "All Properties");
			}
		});
		
		buttons = new JPanel();
		buttons.add(create);
		
		ak.add(checkFridge);
		ak.add(checkMicro);
		ak.add(checkOven);
		ak.add(checkStore);
		ak.add(checkDish);
		ak.add(checkTable);
		ak.add(checkCook);
		ak.add(checkBasic);
		ak.add(buttons);
		
		return ak;	        
	}
	
	public JPanel inquiry() {
		// This panel is 
		JPanel i = new JPanel();
		JPanel hp = new JPanel();
		JPanel x = new JPanel();
		
		// Use GridBagLayout on hp to create a two-grid container (i and x panels will be on grid (0,0) and (0,1) respectively)
		hp.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		// x will be the panel which shows the input panel i
		x.setBorder(createTitledBorder("Find properties"));
		// Use GBL on i (input for inquiry) to create a container with 5 grids (just one column)
		i.setLayout(new GridBagLayout());
		
		JTextField startDD, endDD, startMM, endMM, startYY, endYY;
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);		
		searchProperty = new JTextField("Area (i.e. Sheffield)", 20);
		searchProperty.setFont(plain);
		i.add(searchProperty, g);
		
		// Start adding components to the i panel grids
		setConstraints(g, 0, 1, GridBagConstraints.CENTER);
		JLabel start = new JLabel("Start date: ");
		start.setFont(plain);
		i.add(start, g);
		
		// A new panel to arrange the date inputs in
		// Use GBL as well to create just one row with five columns
		JPanel startDate = new JPanel();
		startDate.setLayout(new GridBagLayout());
		
		// Start adding components to startDate panel grids
		setConstraints(g, 0, 0, GridBagConstraints.WEST);
		startDD = new JTextField("DD", 2);
		startDD.setFont(plain);
		startDate.add(startDD, g);
		setConstraints(g, 1, 0, GridBagConstraints.WEST);
		JLabel d1 = new JLabel("-");
		d1.setFont(plain);
		startDate.add(d1, g);
		setConstraints(g, 2, 0, GridBagConstraints.WEST);
		startMM = new JTextField("MM", 2);
		startMM.setFont(plain);
		startDate.add(startMM, g);
		setConstraints(g, 3, 0, GridBagConstraints.WEST);
		JLabel d2 = new JLabel("-");
		d2.setFont(plain);
		startDate.add(d2, g);
		setConstraints(g, 4, 0, GridBagConstraints.WEST);
		startYY = new JTextField("YYYY", 4);
		startYY.setFont(plain);
		startDate.add(startYY, g);
		// Add the startDate panel to the first row, column 2 of i
		setConstraints(g, 0, 2, GridBagConstraints.CENTER);
		i.add(startDate, g);
		// Add a label for the end date, same as for the start date
		setConstraints(g, 0, 3, GridBagConstraints.CENTER);
		JLabel end = new JLabel("End date: ");
		end.setFont(plain);
		i.add(end, g);
		
		// New panel for end date inputs (same as before with start date)
		JPanel endDate = new JPanel();
		endDate.setLayout(new GridBagLayout());
		
		// Add input for end date to panel startDate
		setConstraints(g, 0, 0, GridBagConstraints.WEST);
		endDD = new JTextField("DD", 2);
		endDD.setFont(plain);
		endDate.add(endDD, g);
		setConstraints(g, 1, 0, GridBagConstraints.WEST);
		JLabel d3 = new JLabel("-");
		d3.setFont(plain);
		endDate.add(d3, g);
		setConstraints(g, 2, 0, GridBagConstraints.WEST);
		endMM = new JTextField("MM", 2);
		endMM.setFont(plain);
		endDate.add(endMM, g);
		setConstraints(g, 3, 0, GridBagConstraints.WEST);
		JLabel d4 = new JLabel("-");
		d4.setFont(plain);
		endDate.add(d4, g);
		setConstraints(g, 4, 0, GridBagConstraints.WEST);
		endYY = new JTextField("YYYY", 4);
		endYY.setFont(plain);
		endDate.add(endYY, g);
		
		// add endDate to the fourth row of i
		setConstraints(g, 0, 4, GridBagConstraints.CENTER);
		i.add(endDate, g);
		
		// add the search button to the fifth row of i
		JPanel btn = new JPanel();
		setConstraints(g, 0, 5, GridBagConstraints.CENTER);
		btn.setBorder(new EmptyBorder(50, 0, 0, 0));
		search = new JButton("Seach Area");
		search.setFont(plain);
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cityFilter = searchProperty.getText();
				c.add("View Properties", viewProperties(null, "City"));
				cards.show(c, "View Properties");
				current = "VP";
				setTitle("View Properties");
			}
		});
		btn.add(search);
		i.add(btn, g);
		
		// TODO add search results into the searchResult panel
		// this is just to set some space to make sure both panels are about the same width
		i.setBorder(new EmptyBorder(0, 100, 0, 100));
		// add i to x
		x.add(i);
		hp.add(x, g);
		setConstraints(g, 0, 2, GridBagConstraints.CENTER);
		// this is where viewProperty should go, the panel under the input panel
		// I will turn it into a cardlayout so it can be switched with viewProperty() when search is clicked
		JPanel result = new JPanel();
		CardLayout resultCard = new CardLayout();
		result.setLayout(resultCard);
		
		result.add("Default", HBPanels.defaultSearchPanel());
		// should probably be something like "result.add("Property view, viewProperty()")"
		
		// add the panel with card layout to the return panel hp 
		//(g is already set to point to the third column of the first row)
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		hp.add(result, g);
		
		return hp;
	}

	public JPanel enquirer() {
		JPanel e = new JPanel();
		e.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		e.add(inquiry(), g);
		setConstraints(g, 0, 1, GridBagConstraints.CENTER);
		e.add(createHomeBtnPanel(), g);
		
		return e;
	}
	
	public JPanel hostHome() {
		JPanel hh = new JPanel();
		hh.setLayout(new BorderLayout());
		
		JTabbedPane hostTabs = new JTabbedPane();
		hostTabs.setFont(plain);
		
		myProperties = new JPanel();
		myPropertiesCards = new CardLayout();
		myProperties.setLayout(myPropertiesCards);
		myProperties.add("Add Kitchen", addKitchen());
		
		myAccount = new JPanel();
		myAccountCards = new CardLayout();
		myAccount.setLayout(myAccountCards);
		myAccount.add("My Account", myAccount());
		myAccount.add("Edit Info", HBPanels.changeInfoPanel());
		
		myProperties.add("New", newPropertyPanel());
		
		hostTabs.add("My Properties", myProperties); // shows list of properties, and a button to create a new property
		try {
			hostTabs.add("My Bookings", viewBookings());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hostTabs.add("My Account", myAccount);
		
		hh.add(hostTabs, BorderLayout.CENTER);
		
		return hh;
	}
	
	public JPanel guestHome() {
		JPanel gh = new JPanel();
		
		gh.setLayout(new BorderLayout());
		
		JTabbedPane guestTabs = new JTabbedPane();
		guestTabs.setFont(plain);
		
		JPanel myProperties = new JPanel();
		CardLayout c = new CardLayout();
		myProperties.setLayout(c);
		
		myAccount = new JPanel();
		myAccountCards = new CardLayout();
		myAccount.setLayout(myAccountCards);
		myAccount.add("My Account", myAccount());
		myAccount.add("Edit Info", HBPanels.changeInfoPanel());
				
		CardLayout card = new CardLayout();
		JPanel booking = new JPanel();
		booking.setLayout(card);
		
		try {
			current = "GH";
			guestTabs.add("My Bookings", viewBookings());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		guestTabs.add("My Bookings", pBookings);
		guestTabs.add("Book a property", guestBookingPanel());
		guestTabs.add("My Account", myAccount);
		
		gh.add(guestTabs, BorderLayout.CENTER);
		
		return gh;
	}
	
	public JPanel guestBookingPanel() {
		JPanel gb = new JPanel();
		JPanel searchPanel = new JPanel();
		JPanel resultPanel = new JPanel();
		CardLayout c = new CardLayout();
		resultPanel.setLayout(c);
		
		gb.setLayout(new GridBagLayout());
		searchPanel.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		// Search bar
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		JTextField searchBar = new JTextField(20);
		searchBar.setFont(plain);
		searchBar.setMinimumSize(searchBar.getPreferredSize());
		searchPanel.add(searchBar, g);
		
		setConstraints(g, 1, 0, GridBagConstraints.CENTER);
		JComboBox<String> searchSetting = new JComboBox<String>();
		searchSetting.setFont(plain);
		searchSetting.addItem("Property name");
		searchSetting.addItem("Host name");
		searchSetting.addItem("Area");
		searchPanel.add(searchSetting, g);
		
		setConstraints(g, 0, 1, GridBagConstraints.CENTER);
		JButton searchBtn = new JButton("Search");
		searchBtn.setFont(plain);
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String setting = searchSetting.getSelectedItem().toString();
				String search = searchBar.getText();
				
				if (search.isEmpty()) {
					showMessageDialog(null, "Type in the search bar to start searching!");
				}
				else if (setting == "Area") {
					JScrollPane s = viewProperties(null, "City");
					s.setPreferredSize(resultPanel.getPreferredSize());
					cityFilter = search;
					resultPanel.add("Properties in area", viewProperties(null, "City"));
					c.show(resultPanel, "Properties in area");
				}
				else if (setting == "Host name") {
					hostFilter = search;
					resultPanel.add("Matching hosts", searchHost());
					c.show(resultPanel, "Matching hosts");
				}
				else if (setting == "Property name") {
					propertyNameFilter = search;
					resultPanel.add("Matching properties", viewProperties(null, "Name"));
					c.show(resultPanel, "Matching properties");
				}
			}
		});
		searchPanel.add(searchBtn, g);
		
		// add cards for resultPanel
		resultPanel.add("Default", HBPanels.defaultSearchPanel());
		
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		gb.add(searchPanel, g);
		
		setConstraints(g, 0, 1, GridBagConstraints.CENTER);
		g.weightx = 1;
		g.weighty = 1;
		gb.add(resultPanel, g);
		
		return gb;
	}
	
	public JScrollPane searchHost() {		
		JPanel sh = new JPanel();
		CardLayout crd = new CardLayout();
		JPanel hp = new JPanel();
		hp.setLayout(crd);
		sh.setBorder(HomeBreaks.createTitledBorder(""));
		List<Host> searchResult = new ArrayList<Host>();
		
		Map<Integer, Host> hosts = TDatabase.Hosts;
		
		for (Host h : hosts.values()) {
			if (h.getName().equals(hostFilter)) {
				searchResult.add(h);
			}
		}
		
		JLabel hostName;
		JButton viewProfile;
		JPanel n, b, h;
		
		for (Host host : searchResult) {
			hostName = new JLabel(host.getName());
			hostName.setFont(plain);
			viewProfile = new JButton("View Profile");
			viewProfile.setFont(plain);
			viewProfile.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					currentHost = host;
					hp.add("Profile", hostProfile(host));
					crd.show(hp, "Profile");
				}
			});
			
			n = new JPanel();
			b = new JPanel();
			n.add(hostName);
			b.add(viewProfile);
			
			h = new JPanel();
			h.add(n);
			h.add(b);
			
			sh.add(h);
		}
		hp.add("Host result", sh);
		

		JScrollPane scrollPane = new JScrollPane(hp);
		return scrollPane;
		
	}

	
	public JPanel myAccount() {
		JPanel ma = new JPanel();
		ma.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
				
		JButton editInfo, logOut, changePassword, myRating; //TODO to be added
		
		editInfo = new JButton("Account Info");
		editInfo.setFont(plain);
		editInfo.setPreferredSize(new Dimension(400, 50));
		editInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myAccountCards.show(myAccount, "Edit Info");
			}
		});
		ma.add(editInfo, g);
		
		setConstraints(g, 0, 1, GridBagConstraints.CENTER);
		myRating = new JButton("My Ratings");
		myRating.setFont(plain);
		myRating.setPreferredSize(new Dimension(400, 50));
		ma.add(myRating, g);
		
		setConstraints(g, 0, 2, GridBagConstraints.CENTER);
		changePassword = new JButton("Change Password");
		changePassword.setFont(plain);
		changePassword.setPreferredSize(new Dimension(400, 50));
		ma.add(changePassword, g);
		
		setConstraints(g, 0, 3, GridBagConstraints.CENTER);
		logOut = new JButton("Log Out");
		logOut.setFont(plain);
		logOut.setPreferredSize(new Dimension(400, 50));
		logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.show(c, "Home");
				currentHost = null;
				currentGuest = null;
			}
		});
		ma.add(logOut, g);
		
		return ma;
	}
	

	
	public JScrollPane viewBookings() throws ParseException {
		JPanel b = new JPanel();
		b.setLayout(new GridLayout(1, 2));
		b.setBorder(HomeBreaks.createTitledBorder(""));
		Font plain = new Font("Verdana", Font.PLAIN, 18);
		
		Map<Integer, Booking> acceptedBookings = new HashMap<Integer, Booking>();
		Map<Integer, Booking> provisionalBookings = new HashMap<Integer, Booking>();
		// For guest page
		if (HomeBreaks.current == "GH") {
			for (Booking booking : TDatabase.Bookings.values()) {
				int guestID = booking.getGuestID();
				int currentGID = Integer.parseInt(HomeBreaks.currentGuest.getID());
				int bookingID = TDatabase.GetBookingID(booking.getPropertyID(), booking.getGuestID());
				boolean provisional = booking.getProvisional();
				boolean rejected = booking.getRejected();
				
				if (currentGID == guestID) {
					if (!(provisional) && !(rejected)) {
						acceptedBookings.put(bookingID, booking);
					}
					else {
						provisionalBookings.put(bookingID, booking);
					}
				}
			}
		}
		// For host page
		else if (HomeBreaks.current == "HH") {
			for (Booking booking : TDatabase.Bookings.values()) {
				int hostID = booking.getHostID();
				int currentHID = Integer.parseInt(HomeBreaks.currentHost.getID());
				int bookingID = TDatabase.GetBookingID(booking.getPropertyID(), booking.getGuestID());
				boolean provisional = booking.getProvisional();
				boolean rejected = booking.getRejected();
				
				if (currentHID == hostID) {
					if (!provisional && !rejected) {
						acceptedBookings.put(bookingID, booking);
					}
					else {
						provisionalBookings.put(bookingID, booking);
					}
				}
			}
		}
		
		JPanel accepted = new JPanel();
		BoxLayout b1 = new BoxLayout(accepted, BoxLayout.Y_AXIS);
		accepted.setLayout(b1);
		accepted.setBorder(HomeBreaks.createTitledBorder("Accepted bookings"));
		
		JPanel provisional = new JPanel();
		BoxLayout b2 = new BoxLayout(provisional, BoxLayout.Y_AXIS);
		provisional.setLayout(new GridBagLayout());
		provisional.setBorder(createTitledBorder("Other bookings"));
		
		// For guest's and hosts' bookings page
		int acceptedCount = 0;
		for (Booking acceptedBooking : acceptedBookings.values()) {
			Host host = TDatabase.Hosts.get(acceptedBooking.getHostID());
			Guest guest = TDatabase.Guests.get(acceptedBooking.getGuestID());
			System.out.println("Accepted" + host.getName() + guest.getName());
			JPanel vb = new JPanel();
			//vb.setBorder(createTitledBorder(""));
			JLabel pName, personName, sDate, eDate, contactN, contactE, status, numNights, pricePerNight, serviceCharge, cleaningCharge;
			personName = new JLabel();
			contactN = new JLabel();
			contactE = new JLabel();
			
			pName = new JLabel("Property name: " + TDatabase.Properties.get(acceptedBooking.getPropertyID()).getShortName());
			pName.setFont(plain);
			if (current == "GH") {
				// print host info
				personName.setText("Host name: " + host.getName());
				personName.setFont(plain);
				contactN.setText("Host phone number: " + host.getPhone());
				contactN.setFont(plain);
				contactE.setText("Host email: " + host.getEmail());
				contactE.setFont(plain);
			}
			else if (current == "HH") {
				// print guest info
				personName.setText("Guest name: " + guest.getName());
				personName.setFont(plain);
				contactN.setText("Guest mobile number: " + guest.getPhone());
				contactN.setFont(plain);
				contactE.setText("Guest email: " + guest.getEmail());
				contactE.setFont(plain);
			}
			sDate = new JLabel("Start date: " + acceptedBooking.getStartDate());
			sDate.setFont(plain);
			eDate = new JLabel("End date: " + acceptedBooking.getEndDate());
			eDate.setFont(plain);
			
			ChargeBand chargeBand = null;
			// Add Chargeband info
			for (ChargeBand c : TDatabase.ChargeBands.values()) {
				if (c.getPropertyID() == acceptedBooking.getPropertyID()) {
					if (Booking.overlap(c.getStartDate(), c.getEndDate(), acceptedBooking.getStartDate(), acceptedBooking.getEndDate())) {
						chargeBand = c;
					}
				}
			}
			numNights = new JLabel("Number of nights: " + Booking.getNightsNum(acceptedBooking.getStartDate(), acceptedBooking.getEndDate()));
			numNights.setFont(plain);
			pricePerNight = new JLabel("Price Per Night: " + chargeBand.getPPN());
			pricePerNight.setFont(plain);
			serviceCharge = new JLabel("Service Charge: " + chargeBand.getSC());
			serviceCharge.setFont(plain);
			cleaningCharge  = new JLabel("Cleaning Charge: " + chargeBand.getCC());
			cleaningCharge.setFont(plain);
			status = new JLabel("Status: Accepted");
			status.setFont(plain);
			
			
			BoxLayout bl = new BoxLayout(vb, BoxLayout.Y_AXIS);
			vb.setLayout(bl);
			
			JSeparator s = new JSeparator();
			
			vb.add(pName);
			vb.add(personName);
			vb.add(contactN);
			vb.add(contactE);
			vb.add(sDate);
			vb.add(eDate);
			vb.add(numNights);
			vb.add(pricePerNight);
			vb.add(serviceCharge);
			vb.add(cleaningCharge);
			vb.add(status);
			if (acceptedCount < acceptedBookings.size()) {vb.add(s);}
			
			accepted.add(vb);
			acceptedCount++;
		}
		
		// for guests' and hosts' provisional bookings
		int pCount = 0;
		for (Booking booking : provisionalBookings.values()) {
			Host host = TDatabase.Hosts.get(booking.getHostID());
			Guest guest = TDatabase.Guests.get(booking.getGuestID());
			
			System.out.println("Provisional: " + host.getName() + " " +  guest.getName());
			provisional.setBorder(createTitledBorder("Other bookings"));
			JPanel vb = new JPanel();
			JLabel pName, personName, sDate, eDate, status, numNights, pricePerNight, serviceCharge, cleaningCharge;
			String stat = "";
			personName = new JLabel();
			
			if (booking.getRejected()) {
				stat = "Rejected";
			}
			else {
				stat = "Provisional";
			}
			
			pName = new JLabel("Property name: " + properties.get(booking.getPropertyID()).getShortName());
			pName.setFont(plain);
			if (current == "GH") {
				personName.setText("Host name: " + host.getName());
				personName.setFont(plain);
			}
			else if (current == "HH") {
				personName.setText("Host name: " + guest.getName());
				personName.setFont(plain);
			}
			sDate = new JLabel("Start date: " + booking.getStartDate());
			sDate.setFont(plain);
			eDate = new JLabel("End date: " + booking.getEndDate());
			eDate.setFont(plain);
			status = new JLabel("Status: " + stat);
			status.setFont(plain);
			
			ChargeBand chargeBand = null;
			// Add Chargeband info
			for (ChargeBand c : TDatabase.ChargeBands.values()) {
				if (c.getPropertyID() == booking.getPropertyID()) {
					if (Booking.overlap(c.getStartDate(), c.getEndDate(), booking.getStartDate(), booking.getEndDate())) {
						chargeBand = c;
					}
				}
			}
			
			pricePerNight = new JLabel("Price Per Night: " + chargeBand.getPPN());
			pricePerNight.setFont(plain);
			serviceCharge = new JLabel("Service Charge: " + chargeBand.getSC());
			serviceCharge.setFont(plain);
			cleaningCharge  = new JLabel("Cleaning Charge: " + chargeBand.getCC());
			cleaningCharge.setFont(plain);
			
			BoxLayout bl = new BoxLayout(vb, BoxLayout.Y_AXIS);
			vb.setLayout(bl);
			
			JSeparator s = new JSeparator();
			
			vb.add(pName);
			vb.add(personName);
			vb.add(sDate);
			vb.add(eDate);
			vb.add(pricePerNight);
			vb.add(serviceCharge);
			vb.add(cleaningCharge);
			vb.add(status);
			if (pCount < acceptedBookings.size()) {vb.add(s);}
			
			provisional.add(vb);
			pCount++;
		}
		
		// Add accepted booking
		GridBagConstraints g = new GridBagConstraints();
		HomeBreaks.setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		g.weightx = 1;
		b.add(accepted);
		setConstraints(g, 0, 1, GridBagConstraints.CENTER);
		b.add(provisional);
		JScrollPane s = new JScrollPane(b);
		return s;
	}
	
	// Shows reviews related to a property
	public JScrollPane viewReviews() {
		// New map to store related reviews
		Property p = chosenHouse;
		Map<Integer, Review> pr = new HashMap<Integer, Review>();
		
		for (Review r : TDatabase.Reviews.values()) {
			if (p.getID() == r.getPropertyID()) {pr.put(r.getID(), r);}
		}
		
		// Elements needed to display the reviews
		JPanel reviews = new JPanel();
		BoxLayout b = new BoxLayout(reviews, BoxLayout.Y_AXIS);
		reviews.setLayout(b);
		reviews.setBorder(createTitledBorder("Reviews"));
		
		// Iterate through related reviews and print them out
		int count = 0;
		for (Review r : pr.values()) {
			JPanel singleReview = new JPanel();
			singleReview.setLayout(new GridLayout(0, 1));
			Guest guest = TDatabase.Guests.get(r.getGuestID());
			
			JLabel gName = new JLabel(guest.getName());
			gName.setFont(new Font("Verdana", Font.BOLD, 30));
			JLabel overall = new JLabel();
			overall.setFont(bold);
			
			// Get guest's overall rating for the property and print out number of stars
			double ovrll = r.overallRating();
			System.out.println(ovrll);
			
			if (ovrll >= 4.7) {overall.setText("* * * * *");}
			else if (ovrll > 4 && ovrll < 4.7) {overall.setText("* * * *");}
			else if (ovrll > 3 && ovrll <= 4) {overall.setText("* * *");}
			else if (ovrll > 2 && ovrll <=3) {overall.setText("* *");}
			else if (ovrll > 1 && ovrll <=2) {overall.setText("*");}
			
			JLabel description = new JLabel(r.getText());
			description.setFont(plain);
			
			JSeparator sep = new JSeparator();
			
			// Add elements to the review
			singleReview.add(gName);
			singleReview.add(overall);
			singleReview.add(description);
			if (count < pr.size()) {singleReview.add(sep);}
			reviews.add(singleReview);
			count++;
		}
		
		
		JScrollPane s = new JScrollPane(reviews);
		return s;
	}
	
	public JPanel bookingPage() {
		JPanel bp = new JPanel();
		bp.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		bp.setBorder(createTitledBorder("Book property"));
		
		JTextField startDD, startMM, startYY;
		JTextField endDD, endMM, endYY;
		
		setConstraints(g, 0, 1, GridBagConstraints.CENTER);
		JLabel start = new JLabel("Start date: ");
		start.setFont(plain);
		bp.add(start, g);
		
		JPanel startDate = new JPanel();
		startDate.setLayout(new GridBagLayout());
		
		setConstraints(g, 0, 0, GridBagConstraints.WEST);
		startDD = new JTextField("DD", 2);
		startDD.setFont(plain);
		startDate.add(startDD, g);
		setConstraints(g, 1, 0, GridBagConstraints.WEST);
		JLabel d1 = new JLabel("-");
		d1.setFont(plain);
		startDate.add(d1, g);
		setConstraints(g, 2, 0, GridBagConstraints.WEST);
		startMM = new JTextField("MM", 2);
		startMM.setFont(plain);
		startDate.add(startMM, g);
		setConstraints(g, 3, 0, GridBagConstraints.WEST);
		JLabel d2 = new JLabel("-");
		d2.setFont(plain);
		startDate.add(d2, g);
		setConstraints(g, 4, 0, GridBagConstraints.WEST);
		startYY = new JTextField("YYYY", 4);
		startYY.setFont(plain);
		startDate.add(startYY, g);
		
		setConstraints(g, 0, 2, GridBagConstraints.CENTER);
		bp.add(startDate, g);

		setConstraints(g, 0, 3, GridBagConstraints.CENTER);
		JLabel end = new JLabel("End date: ");
		end.setFont(plain);
		bp.add(end, g);
		
		JPanel endDate = new JPanel();
		endDate.setLayout(new GridBagLayout());
		
		setConstraints(g, 0, 0, GridBagConstraints.WEST);
		endDD = new JTextField("DD", 2);
		endDD.setFont(plain);
		endDate.add(endDD, g);
		setConstraints(g, 1, 0, GridBagConstraints.WEST);
		JLabel d3 = new JLabel("-");
		d3.setFont(plain);
		endDate.add(d3, g);
		setConstraints(g, 2, 0, GridBagConstraints.WEST);
		endMM = new JTextField("MM", 2);
		endMM.setFont(plain);
		endDate.add(endMM, g);
		setConstraints(g, 3, 0, GridBagConstraints.WEST);
		JLabel d4 = new JLabel("-");
		d4.setFont(plain);
		endDate.add(d4, g);
		setConstraints(g, 4, 0, GridBagConstraints.WEST);
		endYY = new JTextField("YYYY", 4);
		endYY.setFont(plain);
		endDate.add(endYY, g);
		
		setConstraints(g, 0, 4, GridBagConstraints.CENTER);
		bp.add(endDate, g);
		
		setConstraints(g, 0, 5, GridBagConstraints.CENTER);
		JPanel space = new JPanel();
		space.setBorder(new EmptyBorder(10, 0, 0, 0));
		bp.add(space, g);
		
		setConstraints(g, 0, 6, GridBagConstraints.CENTER);
		JButton request = new JButton("Request booking");
		request.setFont(plain);
		request.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean overlap = false;
				boolean passed = false;
				String sY = startYY.getText();
				String sM = startMM.getText();
				String sD = startDD.getText();
				boolean rightStart = HomeBreaks.isNumericDate(sY) || HomeBreaks.isNumericDate(sM) || HomeBreaks.isNumericDate(sD);
				boolean unfilledStart = sY.isEmpty() || sM.isEmpty() || sD.isEmpty();
				
				String eY = endYY.getText();
				String eM = endMM.getText();
				String eD = endDD.getText();
				boolean rightEnd = HomeBreaks.isNumericDate(eY) || HomeBreaks.isNumericDate(eM) || HomeBreaks.isNumericDate(eD);
				boolean unfilledEnd = eY.isEmpty() || eM.isEmpty() || eD.isEmpty();
				
				String start = sY + "-" + sM + "-" + sD;
				String end = eY + "-" + eM + "-" + eD;
				
				try {
					if (Booking.hasPassed(start) || Booking.hasPassed(end)) {
						showMessageDialog(null, "Cannot book for dates already passed!");
					}
				}
				catch (ParseException p) {
					p.printStackTrace();
				}
								
				// get list of all bookings, check if any overlaps with given date
				
				for (Booking b : TDatabase.Bookings.values()) {
					String sd = b.getStartDate();
					String ed = b.getEndDate();
					
					boolean accepted = !(b.getProvisional());
					
					try {
						if (!(overlap)) {
							if (Booking.overlap(sd, ed, start, end) && !(accepted)) {
								showMessageDialog(null, "Property is unavailable for that date.");
								overlap = true;
							}
						}
					}
					catch (ParseException p) {
						p.printStackTrace();
					}
					
				}
				
				boolean validDates = !overlap && !passed;
				
				if (unfilledStart || unfilledEnd) {showMessageDialog(null, "Please fill in all blanks.");}
				else if (rightStart && rightEnd && validDates) {
					int guestID = Integer.parseInt(currentGuest.getID());
					int propertyID = chosenHouse.getID();
					int hostID = Integer.parseInt(chosenHouse.getHost().getID());
					TDatabase.AddBooking(propertyID, hostID, guestID, start, end);
					showMessageDialog(null, "Booking successfull!");
				}
			}
		});
		bp.add(request, g);
		
		return bp;
	}
	
	// Filter by city
	public void filterCity(Map<Integer, Property> filteredList) {
		for (Property house : TDatabase.Properties.values())  {
			Address address = house.getFullAddress();
			String city = address.getCity();
			
			if (city.equals(cityFilter)){
				filteredList.put(house.getID(), house);
			}
		}
	}
	
	// Filter by host
	public void filterHost(Host host, Map<Integer, Property> filteredList) {
		for (Property house : TDatabase.Properties.values())  {
			String hostID = host.getID();
			if (hostID.equals(house.getHost().getID())){
				filteredList.put(house.getID(), house);
			}
		}
	}
	
	// Filter by property name
	public void filterPName(Map<Integer, Property> filteredList) {
		for (Property house : TDatabase.Properties.values()) {
			String houseName = house.getShortName();
			if (houseName.equals(propertyNameFilter)) {
				filteredList.put(house.getID(), house);
			}
		}
	}
	
	// Default host should be null
	public JScrollPane viewProperties(Host host, String filter) {
		JPanel vp = new JPanel();
		//current = "View Properties";
		
		JPanel home = new JPanel();
		vp.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		
		home.setBorder(createTitledBorder("View All Properties"));
		home.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel shortName, location, description, breakfast, maxsleep, rating,
		numbaths, numbeds;
		JPanel sn, l, d, b, ms, r, nbaths, nbeds, buttons;

		setConstraints(gbc, 0, 0, GridBagConstraints.CENTER);
		
		final Font plain = new Font("Verdana", Font.PLAIN, 20);
		final Font bold = new Font("Verdana", Font.BOLD, 50);
		
		if (filter == "City") {filterCity(filterProperties);}
		else if (filter == "Host") {filterHost(host, filterProperties);}
		else if (filter == "Name") {filterPName(filterProperties);}
		
		for (Property chosenHouse : filterProperties.values()) {
			facility = chosenHouse.getFacilities();
			//house information
			shortName = new JLabel("Name: "+ chosenHouse.getShortName());
			shortName.setFont(bold);
			location = new JLabel("Location: "+ chosenHouse.getPublicLocation());
			location.setFont(plain);
			description = new JLabel("Description: " + chosenHouse.getDescription());
			description.setFont(plain);
			breakfast = new JLabel("Breakfast: " + chosenHouse.getBreakfast());
			breakfast.setFont(plain);
			maxsleep = new JLabel("Maximum Sleep: " + chosenHouse.getMaxSleepers());
			maxsleep.setFont(plain);
			rating = new JLabel("Rating: " + chosenHouse.getPropertyRating());
			rating.setFont(plain);
			numbaths = new JLabel("Number of Bathrooms: " + facility.getBathroomNum());
			numbaths.setFont(plain);
			numbeds = new JLabel("Number of Bedrooms: " + facility.getBedroomNum());
			numbeds.setFont(plain);
			
			viewMoreBtn = new JButton("View More");
			viewMoreBtn.setFont(plain);
			viewMoreBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					c.add(chosenHouse.getShortName(), houseView(chosenHouse));
					cards.show(c, chosenHouse.getShortName());
					//current = "HV";
					setTitle("House View");
				}
			});
			
			sn = new JPanel();
			l = new JPanel();
			d = new JPanel();
			b = new JPanel();
			ms = new JPanel();
			r = new JPanel();
			nbaths = new JPanel();
			nbeds = new JPanel();
			buttons = new JPanel();

			BoxLayout bl = new BoxLayout(vp, BoxLayout.Y_AXIS);
			vp.setLayout(bl);
			
			sn.add(shortName);
			l.add(location);
			d.add(description);
			b.add(breakfast);
			ms.add(maxsleep);
			r.add(rating);
			nbaths.add(numbaths);
			nbeds.add(numbeds);	
			buttons.add(viewMoreBtn);
			
			vp.add(shortName);
			vp.add(location);
			vp.add(description);
			vp.add(breakfast);
			vp.add(maxsleep);
			vp.add(rating);
			vp.add(numbaths);
			vp.add(numbeds);
			vp.add(buttons);
		}
		JScrollPane scroll = new JScrollPane(vp);
		vp.add(createHomeBtnPanel(), gbc);
		
		return scroll;
	}
	
	
	public JPanel houseView(Property house) {
		chosenHouse = house;
		facility = house.getFacilities();
		JPanel hv = new JPanel();
		hv.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		
		hv.setBorder(createTitledBorder("what a lovely property"));
		hv.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		final Font plain = new Font("Verdana", Font.PLAIN, 20);
		final Font bold = new Font("Verdana", Font.BOLD, 50);
		
		JLabel shortName, description, host, location, address, breakfast,
		maxSleepers, rating, bedrooms, beds;
		JPanel sn, d, h, pl, a, bekfast, ms, r, rooms, b, buttons;
		
		//house information
		shortName = new JLabel("Name: "+ house.getShortName());
		shortName.setFont(bold);
		description = new JLabel("Description: " + house.getDescription());
		description.setFont(plain);
		location = new JLabel("Location: "+ house.getPublicLocation());
		location.setFont(plain);
		host = new JLabel("" + house.getHost().getName());
		host.setFont(plain);
		breakfast = new JLabel("breakfast: " + house.getBreakfast());
		breakfast.setFont(plain);
		
		JButton bedsBtn = new JButton("View Bedrooms");
		bedsBtn.setFont(plain);
		bedsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Bedroom bed = facility.getBedroom(1);
				showMessageDialog(null, "" + bed.toString());
			}
		});
		JButton bathBtn = new JButton("View Bathrooms");
		bathBtn.setFont(plain);
		bathBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Bathroom bath = facility.getBathroom(1);
				showMessageDialog(null, "" + bath.toString());
			}
		});
		JButton ktchnBtn = new JButton("View Kitchen");
		ktchnBtn.setFont(plain);
		ktchnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Kitchen kitchen = facility.getKitchen();
				showMessageDialog(null, "" + kitchen.toString());
			}
		});
		JButton livBtn = new JButton("View Living Space");
		livBtn.setFont(plain);
		livBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Living living = facility.getLivingFacility();
				showMessageDialog(null, "" + living.toString());
			}
		});
		JButton utilBtn = new JButton("View Utilities");
		utilBtn.setFont(plain);
		utilBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Utility utility = facility.getUtilityFacility();
				showMessageDialog(null, "" + utility.toString());
			}
		});
		JButton outBtn = new JButton("View Outdoor Facility");
		outBtn.setFont(plain);
		outBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Outdoor outdoor = facility.getOutdoorFacility();
				showMessageDialog(null, "" + outdoor.toString());
			}
		});
		
		JButton bookBtn = new JButton("Book");
		bookBtn.setFont(plain);
		bookBtn.setVisible(false);
		bookBtn.setEnabled(false);
		if (current == "GH") {
			bookBtn.setVisible(true);
			bookBtn.setEnabled(true);
		}
		bookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.show(c, "Booking");
				setTitle("Book " + chosenHouse.getShortName());
			}
		});
		
		sn = new JPanel();
		pl = new JPanel();
		d = new JPanel();
		h = new JPanel();
		bekfast = new JPanel();
		buttons = new JPanel();

		JButton back = new JButton("back");
		back.setFont(plain);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.add("View Properties", viewProperties(null, "City"));
				cards.show(c, "View Properties");
				current = "VP";
				setTitle("View Properties");
			}
		});

		BoxLayout bl = new BoxLayout(hv, BoxLayout.Y_AXIS);
		hv.setLayout(bl);
		
		sn.add(shortName);
		pl.add(location);
		d.add(description);
		h.add(host);
		bekfast.add(breakfast);
		buttons.add(bedsBtn);
		buttons.add(bathBtn);
		buttons.add(ktchnBtn);
		buttons.add(livBtn);
		buttons.add(utilBtn);
		buttons.add(outBtn);
		buttons.add(bookBtn);
		buttons.add(back);
		
		hv.add(shortName);
		hv.add(location);
		hv.add(description);
		hv.add(host);
		hv.add(breakfast);
		hv.add(buttons);
		hv.add(avgPropertyRating());
		hv.add(viewReviews());
		
		return hv;
	}
	
	// Show host's information
	public JPanel hostProfile(Host host) {
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		JLabel n, sh, r; // TODO get rating and superhost status
		JScrollPane hostProperties = viewProperties(host, "Host");
		
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		n = new JLabel("Name: " + host.getName());
		n.setFont(plain);
		p.add(n, g);
		
		// Get host's rating in a new map if available
		// if not, set to no ratings
		Map<Integer, Review> relatedRatings = new HashMap<Integer, Review>();
		for (Review rating : TDatabase.Reviews.values()) {
			// Get the property from its ID
			Property house = TDatabase.Properties.get(rating.getPropertyID());
			if (Integer.parseInt(currentHost.getID()) == house.getID()) {relatedRatings.put(rating.getID(), rating);}
		}
		
		// Show default rating and superhost status
		JLabel superHost = new JLabel("Status: Host");
		JLabel rating = new JLabel("Host rating: This host has no ratings yet");
		
		// Iterate over the values in map and calculate average ratings if there's at least a review
		if (!(relatedRatings.isEmpty())) {
			double averages = 0;
			int count = 0;
			for (Review rv : relatedRatings.values()) {
				averages += rv.overallRating();
				count++;
			}
			double hostAverage = averages / count;
			hostAverage = Math.round(hostAverage * 10.0) / 10.0;
			System.out.println("HA " + hostAverage);
			rating.setText("Host rating: " + hostAverage);
			if (hostAverage > 4.7) {superHost.setText("Status: Superhost!");}
		}
		
		rating.setFont(plain);
		superHost.setFont(plain);
		
		// Add everything to panel p
		setConstraints(g, 0, 1, GridBagConstraints.CENTER);
		p.add(rating, g);
		setConstraints(g, 0, 2, GridBagConstraints.CENTER);
		p.add(superHost, g);
		setConstraints(g, 0, 3, GridBagConstraints.CENTER);
		p.add(hostProperties, g);
		
		return p;
	}
	
	public JPanel avgPropertyRating() {
		JPanel categories = new JPanel();
		categories.setLayout(new GridLayout(1, 6));
		Property p = chosenHouse;
		
		// Get all reviews related to property p and store them in a map
		Map<Integer, Review> related = new HashMap<Integer, Review>();
		for (Review r : TDatabase.Reviews.values()) {
			if (p.getID() == r.getPropertyID()) {related.put(r.getID(), r);}
		}
		
		// Set default labels with no String in case the property has no reviews
		JLabel cl = new JLabel();
		JLabel com = new JLabel();
		JLabel ck = new JLabel();
		JLabel ac = new JLabel();
		JLabel lo = new JLabel();
		JLabel val = new JLabel();
		JLabel overall = new JLabel();
		
		JLabel[] labels = {cl, com, ck, ac, lo, val};
		
		// Set text if there are reviews
		if (!(related.isEmpty())) {
			// Calculate for each category
			RatingCategory[] category = {RatingCategory.Cleanliness, RatingCategory.Communication, RatingCategory.CheckIn, 
					RatingCategory.Accuracy, RatingCategory.Location,RatingCategory.Value};
			double[] values = new double[6];
			int numReviews = 0;
			
			for (int i = 0; i < labels.length; i++) {
				for (Review r : related.values()) {
					values[i] += r.getRatingMap().get(category[i]);
					numReviews++;
				}
				
				values[i] = values[i] / numReviews;
				labels[i].setText(category[i].getName() + ": " + values[i]);
				numReviews = 0;
			}
			
			// Calculate overall property rating
			int total = 0;
			for (int i = 0; i < values.length; i++) {total += values[i];}
			overall.setText("Overall: " + (total / values.length));
		}
		
		// Add everything, including the property's overall rating
		JPanel rating = new JPanel();
		BoxLayout b = new BoxLayout(rating, BoxLayout.Y_AXIS);
		rating.setLayout(b);
		rating.add(overall);
		
		for (int i = 0; i < labels.length; i++) {categories.add(labels[i]);}
		
		rating.add(categories);
		
		return rating;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		checkPasswords();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		checkPasswords();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		checkPasswords();
	}
	
	public void checkPasswordsGSU() {
		String password="";
		try {
			password = TDatabase.encryptThisString(String.valueOf(pw_input_gsu.getPassword()));
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String repassword="";
		try {
			repassword = TDatabase.encryptThisString(String.valueOf(confirm_input_gsu.getPassword()));
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	    if (!password.equals(repassword)) {
	    	warning_gsu.setFont(new Font("Verdana", Font.PLAIN, 20));
	    	warning_gsu.setForeground(Color.RED);
			warning_gsu.setText("Passwords do not match!");
			gsuBtn.setEnabled(false);
			
		}
	    else {
	    	warning_gsu.setText("");
	    	gsuBtn.setEnabled(true);
	    }
	}
	
	public void checkPasswordsHSU() {
		String password="";
		try {
			password = TDatabase.encryptThisString(String.valueOf(pw_input_hsu.getPassword()));
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String repassword="";
		try {
			repassword = TDatabase.encryptThisString(String.valueOf(confirm_input_hsu.getPassword()));
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	    if (!password.equals(repassword)) {
	    	warning_hsu.setFont(new Font("Verdana", Font.PLAIN, 20));
	    	warning_hsu.setForeground(Color.RED);
			warning_hsu.setText("Passwords do not match!");
			hsuBtn.setEnabled(false);
			
		}
	    else {
	    	warning_hsu.setText("");
	    	hsuBtn.setEnabled(true);
	    }
	}
	
	public void checkPasswords() {
		if (current == "HSU") {
			checkPasswordsHSU();
		}
		else if (current == "GSU") {
			checkPasswordsGSU();
		}
	}
	
	public JPanel createHomeBtnPanel() {
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout());
		
		homeBtn = new JButton("Home");
		homeBtn.setFont( new Font("Verdana", Font.PLAIN, 20));
		homeBtn.setContentAreaFilled(false);
		homeBtn.setBorderPainted(false);
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.show(c, "Home");
			}
		});
		
		p.add(homeBtn);
		return p;
	}
	
	public static void setConstraints(GridBagConstraints gbc, int x, int y, int anchor) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.anchor = anchor;
	}
	
	public static TitledBorder createTitledBorder(String title) {
		TitledBorder tb = new TitledBorder(title);
		tb.setTitleFont(new Font("Verdana", Font.BOLD, 50));
		
		return tb;
	}
	
	public static int findSelectedValue(JRadioButton a, JRadioButton b, JRadioButton c, JRadioButton d, JRadioButton e) {
		int value = 0;
		if (a.isSelected()) {value = 1;}
		else if (b.isSelected()) {value = 2;}
		else if (c.isSelected()) {value = 3;}
		else if (d.isSelected()) {value = 4;}
		else if (e.isSelected()) {value = 5;}
		return value;
	}
	
	public static void createRatingButtons(JRadioButton a, JRadioButton b, JRadioButton c, JRadioButton d, JRadioButton e, JPanel p, ButtonGroup g) {
		final Font plain = new Font("Verdana", Font.PLAIN, 25);
		a.setFont(plain);
		b.setFont(plain);
		c.setFont(plain);
		d.setFont(plain);
		e.setFont(plain);
		
		g.add(a);
		g.add(b);
		g.add(c);
		g.add(d);
		g.add(e);
		
		p.add(a);
		p.add(b);
		p.add(c);
		p.add(d);
		p.add(e);
	}
	
	public static boolean isNumericPrice(String str) {
		  return str.matches("\\d+(\\.\\d+)?");
	}
	
	public static boolean isNumericDate(String str) {
		  return str.matches("\\d+\\d");
	}
	
	
	public static void main (String [] args) throws ParseException {
		new HomeBreaks();
	}
}
