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
import javax.swing.JComponent;
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
	CardLayout cards, myAccountCards, resultPanelCards, myPropertiesCards, facilitiesCards, myBookingCards;
	Container c = getContentPane();
	public static String current = "";
	Popup k;
	JPanel home, guestLogin, hostLogin, myAccount, searchResult, viewProperties, myProperties, myFacilities, myBooking, resultPanel;
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
	JTabbedPane guestTabs;
	
	Map<Integer, Property> properties;
	String cityFilter = "";
	String hostFilter = "";
	String propertyNameFilter = "";
	String startFilter = "";
	String endFilter = "";
	Property chosenHouse;
	Address address;
	static Facilities facility;
	ParkType parking = null;
	BedType bed1 = null;
	BedType bed2 = null;
	Map<Integer, Property> filterProperties = new HashMap<Integer, Property>();
	String lastPage;
	String lp; 
	List<Bathroom> baths = new ArrayList<Bathroom>();
	List<Bedroom> beds = new ArrayList<Bedroom>();
	
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
		
		cards = new CardLayout();
		c.setLayout(cards);
		c.add("Home", home());
		c.add("Guest Sign Up", guestSU());
		c.add("Host Sign Up", hostSU());
		c.add("Guest Login", guestLogin());
		c.add("Host Login", hostLogin());
		c.add("Inquiry", enquirer());
		
		setVisible(true);
	}
	
	// Create the home page with options for roles
	public JPanel home() {
		// Add main panel and helper panel to set the contents right at the center
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
		
		// Buttons for different roles the user can play
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
		
		// Set a bigger size for the buttons for a better look
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

	// Create page for guest sign up
	public JPanel guestSU() {
		// Create main panel gsu and helper panel hp to keep things center
		JPanel gsu = new JPanel();
		JPanel hp = new JPanel();
		hp.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		gsu.setBorder(createTitledBorder("Sign Up as Guest"));
		gsu.setLayout(new GridBagLayout());
		
		JLabel fName, lName, addA, addB, addC, addD, phone, id, pw, confirm_pw;
		
		// Input for user's first and last name
		fName = new JLabel("First Name: ");
		fName_input_gsu = new JTextField(20);
		lName = new JLabel("Last Name: ");
		lName_input_gsu = new JTextField(20);
		
		// Input for user's address
		addA = new JLabel("House No.: ");
		add1_gsu = new JTextField(20);
		addB = new JLabel("Street Name: ");
		add2_gsu = new JTextField(20);
		addC = new JLabel("Postcode: ");
		add3_gsu = new JTextField(20);
		addD = new JLabel("City: ");
		add4_gsu = new JTextField(20);
		
		// Input for phone number
		phone = new JLabel("Phone No.: ");
		phone_input_gsu = new JTextField(20);
		
		// Input for email
		id = new JLabel("Email: ");
		id_input_gsu = new JTextField(20);
		pw = new JLabel("Password: ");
		confirm_pw = new JLabel("Confirm Password: ");
		
		// Set labels and inputs up using loop
		JLabel[] labels = {fName, lName, addA, addB, addC, addD, phone, id, pw, confirm_pw};
		JTextField[] inputs = {fName_input_gsu, lName_input_gsu, add1_gsu, add2_gsu, add3_gsu, add4_gsu, phone_input_gsu, id_input_gsu};
		
		for (int i = 0; i < labels.length; i++) {
			labels[i].setFont(plain);
			setConstraints(g, 0, i, GridBagConstraints.EAST);
			gsu.add(labels[i], g);
		}
		
		for (int i = 0; i < inputs.length; i++) {
			inputs[i].setFont(plain);
			setConstraints(g, 1, i, GridBagConstraints.WEST);
			gsu.add(inputs[i], g);
		}
		
		// Input for password
		setConstraints(g, 1, 8, GridBagConstraints.WEST);
		pw_input_gsu = new JPasswordField(20);
		pw_input_gsu.setFont(plain);
		pw_input_gsu.setEchoChar('*');
		pw_input_gsu.getDocument().addDocumentListener(this);
		gsu.add(pw_input_gsu, g);
		
		// Input for confirm password		
		setConstraints(g, 1, 9, GridBagConstraints.WEST);
		confirm_input_gsu = new JPasswordField(20);
		confirm_input_gsu.setFont(plain);
		confirm_input_gsu.setEchoChar('*');
		confirm_input_gsu.getDocument().addDocumentListener(this);
		gsu.add(confirm_input_gsu, g);
		
		// Add sign up button and its action listener
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
				String postcode = add4_gsu.getText().toUpperCase();
				String phone = phone_input_gsu.getText();
				String email = id_input_gsu.getText().toLowerCase();
				//Hash Password
				String password="";
				try {
					password = TDatabase.encryptThisString(String.valueOf(pw_input_gsu.getPassword()));
					
				} 
				catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				// Show a message if any field is empty
				if (fName.isEmpty() || lName.isEmpty() || houseName.isEmpty() || streetName.isEmpty() || city.isEmpty() || fName.isEmpty() || postcode.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty()) {
					showMessageDialog(null, "Please fill in all blanks.");
				}
				
				else if (TDatabase.IsUser("Guest", email)) {
					showMessageDialog(null, "A user with this email already exists!");
				}
				
				// Create new guest if all inputs are correct
				else {
					Address add = new Address(houseName, streetName, postcode, city, true);
					Guest guest = new Guest(fName, lName, add, phone, email, password);
					TDatabase.Guests.put(Integer.parseInt(guest.userID), guest);
					
					if (!(guest.getSuccess())) {
						// Show message if sign up was unsuccessful
						showMessageDialog(null, "Sign up failed.");
					}
					else
					{
						// Add to the guests list if sign up was successful
						// Return to the login page
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
	
	// Create page for host sign up
	public JPanel hostSU() {
		// Create main panel hsu and helper panel hp to keep things centered
		JPanel hsu = new JPanel();
		JPanel hp = new JPanel();
		hp.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		hsu.setBorder(createTitledBorder("Sign Up as Host"));
		hsu.setLayout(new GridBagLayout());
		
		JLabel fName, lName, addA, addB, addC, addD, phone, id, pw, confirm_pw;
		
		// Input for user's first and last name
		fName = new JLabel("First Name: ");
		fName_input_hsu = new JTextField(20);
		lName = new JLabel("Last Name: ");
		lName_input_hsu = new JTextField(20);
				
		// Input for user's address
		addA = new JLabel("House No.: ");
		add1_hsu = new JTextField(20);
		addB = new JLabel("Street Name: ");
		add2_hsu = new JTextField(20);
		addC = new JLabel("Postcode: ");
		add3_hsu = new JTextField(20);
		addD = new JLabel("Place Name: ");
		add4_hsu = new JTextField(20);
		
		// Input for phone number
		phone = new JLabel("Phone No.: ");
		phone_input_hsu = new JTextField(20);
		
		// Input for email
		id = new JLabel("Email: ");
		id_input_hsu = new JTextField(20);
		pw = new JLabel("Password: ");
		confirm_pw = new JLabel("Confirm Password: ");
		
		JLabel[] labels = {fName, lName, addA, addB, addC, addD, phone, id, pw, confirm_pw};
		JTextField[] inputs = {fName_input_hsu, lName_input_hsu, add1_hsu, add2_hsu, add3_hsu, add4_hsu, phone_input_hsu, id_input_hsu};
		
		for (int i = 0; i < labels.length; i++) {
			labels[i].setFont(plain);
			setConstraints(g, 0, i, GridBagConstraints.EAST);
			hsu.add(labels[i], g);
		}
		
		for (int i = 0; i < inputs.length; i++) {
			inputs[i].setFont(plain);
			setConstraints(g, 1, i, GridBagConstraints.WEST);
			hsu.add(inputs[i], g);
		}
		
		// Input for password
		
		setConstraints(g, 1, 8, GridBagConstraints.WEST);
		pw_input_hsu = new JPasswordField(20);
		pw_input_hsu.setFont(plain);
		pw_input_hsu.setEchoChar('*');
		pw_input_hsu.getDocument().addDocumentListener(this);
		hsu.add(pw_input_hsu, g);
			
		// Input for confirm password
		
		setConstraints(g, 1, 9, GridBagConstraints.WEST);
		confirm_input_hsu = new JPasswordField(20);
		confirm_input_hsu.setFont(plain);
		confirm_input_hsu.setEchoChar('*');
		confirm_input_hsu.getDocument().addDocumentListener(this);
		hsu.add(confirm_input_hsu, g);
		
		// Add sign up button and its action listener
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
				String postcode = add4_hsu.getText().toUpperCase();
				String phone = phone_input_hsu.getText();
				String email = id_input_hsu.getText().toLowerCase();
				//Hash Password
				String password="";
				try {
					password = TDatabase.encryptThisString(String.valueOf(pw_input_hsu.getPassword()));
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
                // Show a message if any field is blank
				if (fName.isEmpty() || lName.isEmpty() || houseName.isEmpty() || streetName.isEmpty() || city.isEmpty() || fName.isEmpty() || postcode.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty()) {
					showMessageDialog(null, "Please fill in all blanks.");
				}
				else if (TDatabase.IsUser("Host", email)) {
					showMessageDialog(null, "A user with this email already exists!");
				}
				else {
					// Sign up host if inputs are correct
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
	
	// Create a page for guest login
	public JPanel guestLogin() {
		// Create main panel gl and helper panel hp to keep things centered
		JPanel gl = new JPanel();
		JPanel hp = new JPanel();
		hp.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		gl.setBorder(createTitledBorder("Login as Guest"));
		gl.setLayout(new GridBagLayout());
		
		JLabel id, pw;
		
		// Input for guest's email
		setConstraints(g, 0, 0, GridBagConstraints.EAST);
		id = new JLabel("User ID (email): ");
		gl.add(id, g);
		
		setConstraints(g, 1, 0, GridBagConstraints.WEST);
		id_input_gl = new JTextField(20);
		id_input_gl.getDocument().addDocumentListener(this);
		gl.add(id_input_gl, g);
		
		// Input for password, use JPasswordField and hide password
		setConstraints(g, 0, 1, GridBagConstraints.EAST);
		pw = new JLabel("Password: ");
		gl.add(pw, g);
		
		setConstraints(g, 1, 1, GridBagConstraints.WEST);
		pw_input_gl = new JPasswordField(20);
		pw_input_gl.setEchoChar('*');
		gl.add(pw_input_gl, g);
		
		// Login button
		setConstraints(g, 1, 2, GridBagConstraints.WEST);
		glBtn = new JButton("Log in");
		glBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = id_input_gl.getText().toLowerCase();
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
					// Upon successful login, show guest's home page and set guest as currentGuest
					guestID = TDatabase.SearchUserID("Guest", email);
					setTitle("Guest Home");
					current = "GH";
					lastPage = "Guest Home";
					lp = "GH";
					currentGuest = TDatabase.Guests.get(Integer.parseInt(guestID));
					c.add("Guest Home", guestHome());
					cards.show(c, "Guest Home");
				}
				else {
					showMessageDialog(null, "Wrong credentials!");
				}
			}
		}); 	
		gl.add(glBtn, g);
		
		// Button for sign up for those who have no account
		setConstraints(g, 1, 3, GridBagConstraints.WEST);
		toGSU = new JButton("Don't have an account? Sign up!");
		toGSU.setContentAreaFilled(false);
		toGSU.setBorderPainted(false);
		toGSU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current = "GSU";
				setTitle("Guest Sign Up");
				cards.show(c, "Guest Sign Up");
				
			}
		});
		gl.add(toGSU, g);
		
		JComponent[] comps = {id, id_input_gl, pw, pw_input_gl, glBtn, toGSU};
		for (int i = 0; i < comps.length; i++) {
			comps[i].setFont(plain);
		}
		
		// Home button
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		hp.add(gl, g);
		setConstraints(g, 0, 1, GridBagConstraints.CENTER);
		hp.add(createHomeBtnPanel(), g);
		
		return hp;
	}

	// Create a page for host login
	public JPanel hostLogin() {
		// Create main panel hl and helper panel hp to keep things centered
		JPanel hl = new JPanel();
		JPanel hp = new JPanel();
		hp.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		hl.setBorder(createTitledBorder("Login as Host"));
		hl.setLayout(new GridBagLayout());
		
		JLabel id, pw;
		
		// Input for email
		setConstraints(g, 0, 0, GridBagConstraints.EAST);
		id = new JLabel("User ID (email): ");
		id.setFont(plain);
		hl.add(id, g);
		
		setConstraints(g, 1, 0, GridBagConstraints.WEST);
		id_input_hl = new JTextField(20);
		id_input_hl.setFont(plain);
		id_input_hl.getDocument().addDocumentListener(this);
		hl.add(id_input_hl, g);
		
		// Input for password, use JPasswordField, and hide passwrod
		setConstraints(g, 0, 2, GridBagConstraints.EAST);
		pw = new JLabel("Password: ");
		pw.setFont(plain);
		hl.add(pw, g);
		
		setConstraints(g, 1, 2, GridBagConstraints.WEST);
		pw_input_hl = new JPasswordField(20);
		pw_input_hl.setFont(plain);
		pw_input_hl.setEchoChar('*');
		hl.add(pw_input_hl, g);
		
		// Login button
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
					current = "HH";	
					lastPage = "Host Home";
					lp = "HH";
					c.add("Host Home", hostHome());
					cards.show(c, "Host Home");
				}
				else {
					showMessageDialog(null, "Wrong credentials!");
				}
			}
		});
		hl.add(hlBtn, g);
		
		// Button to sign up for those who don't have a host account
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
	
	// Shows host's owned properties
	public void addHostProperties() {
		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		JScrollPane mp = viewProperties(currentHost, "Host");
		p1.add(mp, BorderLayout.CENTER);
		
		// Button for property creation
		newPropertyBtn = new JButton("Create New Property");
		newPropertyBtn.setFont(plain);
		newPropertyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myPropertiesCards.show(myProperties, "New");
			}
		});
		p1.add(newPropertyBtn, BorderLayout.SOUTH);
		
		myProperties.add("All Properties", p1);
		myPropertiesCards.show(myProperties, "All Properties");
	}

	// Creates page for property creation
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
		
		name = new JLabel("Property name: ");
		shortName = new JTextField(20);
		description = new JLabel("Description: ");
		desc = new JTextArea(5, 20);
		desc.setLineWrap(true);
		desc.setWrapStyleWord(true);

		houseNo = new JLabel("House Number: ");
		add1 = new JTextField(20);
		stNo = new JLabel("Street Name: ");
		stNo.setFont(plain);
		add2 = new JTextField(20);
		postcode = new JLabel("Postcode: ");
		add3 = new JTextField(20);
		place = new JLabel("City: ");
		add4 = new JTextField(20);
		add4.setFont(plain);
		np.add(add4, g);
		
		bfast = new JLabel("Is breakfast served?");	
		
		HomeBreaks.setConstraints(g, 1, 6, GridBagConstraints.WEST);
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
		
		JLabel[] labels = {name, description, houseNo, stNo, postcode, place, bfast};
		JComponent[] inputs = {shortName, desc, add1, add2, add3, add4};
		
		for (int i = 0; i < labels.length; i++) {
			labels[i].setFont(plain);
			setConstraints(g, 0, i, GridBagConstraints.EAST);
			np.add(labels[i], g);
		}
		
		for (int i = 0; i < inputs.length; i++) {
			inputs[i].setFont(plain);
			if (i < 1 || i > 1) {
				setConstraints(g, 1, i, GridBagConstraints.WEST);
				np.add(inputs[i], g);
			}
			else if (i == 1) {
				setConstraints(g, 1, i, GridBagConstraints.WEST);
				JScrollPane scrollPane = new JScrollPane(inputs[i]);
				np.add(scrollPane, g);
			} 
		}
		
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
				String postcode = add3.getText().toUpperCase();
				String city = add4.getText();
				int bfast = 0;
				boolean notAllFilled = sName.isEmpty() || descr.isEmpty() || houseNo.isEmpty() || street.isEmpty() || postcode.isEmpty() || city.isEmpty();
				
				if (yes_bfast.isSelected()) {
					bfast = 1;
				}
				
				if (notAllFilled) {
					showMessageDialog(null, "All fields are mandatory.");
				}
				else if (TDatabase.isProperty(houseNo, postcode))
				{
					showMessageDialog(null, "This property already exists!");
				}
				else {
					address = new Address(houseNo, street, postcode, city, true);
					
					chosenHouse = new Property(sName, descr, HomeBreaks.currentHost, address, bfast, null, true);
					
					try {
						TDatabase.Properties.put(chosenHouse.getID(), chosenHouse);
						facility = new Facilities(chosenHouse.getID(), new ArrayList<Bedroom>(), new ArrayList<Bathroom>(), null, null, null, null);
						chosenHouse.setFacilities(facility);
					}
					catch (NumberFormatException e1) {
						e1.printStackTrace();
						showMessageDialog(null, "Oops, something went wrong :( Please try again later.");
					}

					myProperties.add("Add Living", addLiving());
					myPropertiesCards.show(myProperties, "Add Living");
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

	// Creates page for adding living facilities
	public JPanel addLiving() {
		JPanel al = new JPanel();
		JPanel buttons;
		
		al.setBorder(HomeBreaks.createTitledBorder("New Living Space"));
		al.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		JCheckBox checkWifi = new JCheckBox("Wifi", true);
		JCheckBox checkTV = new JCheckBox("TV", true);
		JCheckBox checkSat = new JCheckBox("Satellite", true);
		JCheckBox checkStream = new JCheckBox("Streaming", true);
		JCheckBox checkDvd = new JCheckBox("DVD Player", true);
		JCheckBox checkBoard = new JCheckBox("Board Games", true);
		
		JButton create = new JButton("Create Living Space");
		create.setFont(plain);
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Living living = new Living(checkWifi.isSelected(), checkTV.isSelected(), checkSat.isSelected(),
						checkStream.isSelected(), checkDvd.isSelected(), checkBoard.isSelected());
				facility.setLivingFacility(living, chosenHouse.getID());
				//cards.show(c, "Add Utility");
				myProperties.add("Add Utility", addUtility());
				myPropertiesCards.show(myProperties, "Add Utility");
				current = "AU";
				setTitle("New Utilities");
			}
		});
		
		buttons = new JPanel();
		buttons.add(create);
		JPanel hp = new JPanel();
		hp.setLayout(new GridBagLayout());
		JCheckBox[] boxes = {checkWifi, checkTV, checkSat, checkStream, checkDvd, checkBoard};
		
		for (int i = 0; i < boxes.length; i++) {
			boxes[i].setFont(plain);
			setConstraints(g, 0, i, GridBagConstraints.WEST);
			hp.add(boxes[i], g);
		}
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		al.add(hp, g);
		setConstraints(g, 0, 1, GridBagConstraints.CENTER);
		al.add(buttons, g);
		
		return al;	        
	}
	
	// Creates page for adding utilities
	public JPanel addUtility() {
		final Font plain = new Font("Verdana", Font.PLAIN, 25);
		JPanel au = new JPanel();
		JPanel buttons;
		
		au.setBorder(HomeBreaks.createTitledBorder("New Utilities"));
		au.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		JCheckBox check1 = new JCheckBox("Heating", true);
		JCheckBox check2 = new JCheckBox("Washing Machine", true);
		JCheckBox check3 = new JCheckBox("Drying Machine", true);
		JCheckBox check4 = new JCheckBox("Fire Extinguisher", true);
		JCheckBox check5 = new JCheckBox("Smoke Alarm", true);
		JCheckBox check6 = new JCheckBox("First Aid Kit", true);
		
		JButton create = new JButton("Create Utilities");
		create.setFont(plain);
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Utility utility = new Utility(check1.isSelected(), check2.isSelected(), check3.isSelected(),
						check4.isSelected(), check5.isSelected(), check6.isSelected());
				facility.setUtility(utility, chosenHouse.getID());
				//cards.show(c, "Add Bath");
				myProperties.add("Add Bath", addBath());
				myPropertiesCards.show(myProperties, "Add Bath");
				current = "ABA";
				setTitle("New Bathroom");
			}
		});
		
		buttons = new JPanel();
		buttons.add(create);
		JPanel hp = new JPanel();
		hp.setLayout(new GridBagLayout());
		JCheckBox[] boxes = {check1, check2, check3, check4, check5, check6};
		for (int i = 0; i < boxes.length; i++) {
			boxes[i].setFont(plain);
			setConstraints(g, 0, i, GridBagConstraints.WEST);
			hp.add(boxes[i], g);
		}
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		au.add(hp, g);
		setConstraints(g, 0, 1, GridBagConstraints.WEST);
		au.add(buttons, g);
		
		return au;	        
	}
    
	// Creates page for adding bathrooms
	public JPanel addBath() {
		JPanel aba = new JPanel();
		JPanel buttons;
		
		aba.setBorder(HomeBreaks.createTitledBorder("New Bathroom"));
		aba.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		JCheckBox check1 = new JCheckBox("Hair Drier", true);
		JCheckBox check2 = new JCheckBox("Shampoo", true);
		JCheckBox check3 = new JCheckBox("Toilet Paper", true);
		JCheckBox check4 = new JCheckBox("Toilet", true);
		JCheckBox check5 = new JCheckBox("Bath", true);
		JCheckBox check6 = new JCheckBox("Shower", true);
		JCheckBox check7 = new JCheckBox("Shared?", true);
		
		JButton create = new JButton("Create Bathroom(s)");
		create.setFont(plain);
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Bathroom bathroom = new Bathroom(check1.isSelected(), check2.isSelected(), check3.isSelected(),
						check4.isSelected(), check5.isSelected(), check6.isSelected(), check7.isSelected());
				baths.add(bathroom);
				facility.addBathroom(baths, chosenHouse.getID());
				myProperties.add("Add Bedroom", addBedroom());
				myPropertiesCards.show(myProperties, "Add Bedroom");
				current = "AB";
				setTitle("New Bedroom");
			}
		});
		
		JButton another = new JButton("Add Another");
		another.setFont(plain);
		another.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Bathroom bathroom = new Bathroom(check1.isSelected(), check2.isSelected(), check3.isSelected(),
						check4.isSelected(), check5.isSelected(), check6.isSelected(), check7.isSelected());
				baths.add(bathroom);
				myProperties.add("Add Bath", addBath());
				myPropertiesCards.show(myProperties, "Add Bath");
				current = "ABA";
				setTitle("New Bathroom");
			}
		});
		
		buttons = new JPanel();
		buttons.add(create);
		buttons.add(another);
		
		JPanel hp = new JPanel();
		hp.setLayout(new GridBagLayout());
		JCheckBox[] boxes = {check1, check2, check3, check4, check5, check6, check7};
		
		for (int i = 0; i < boxes.length; i++) {
			boxes[i].setFont(plain);
			setConstraints(g, 0, i, GridBagConstraints.WEST);
			hp.add(boxes[i], g);
		}
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		aba.add(hp, g);
		setConstraints(g, 0, 1, GridBagConstraints.CENTER);
		aba.add(buttons, g);
		
		return aba;	        
	}
	
	// Creates page for adding bedrooms
	public JPanel addBedroom() {
		JPanel ab = new JPanel();
		JPanel buttons;
		
		ab.setBorder(HomeBreaks.createTitledBorder("New Bedroom"));
		ab.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		ButtonGroup grp = new ButtonGroup();
		JRadioButton single1 = new JRadioButton("Bed 1 = Single");
		JRadioButton double1 = new JRadioButton("Bed 1 = Double");
		JRadioButton king1 = new JRadioButton("Bed 1 = King");
		JRadioButton bunk1 = new JRadioButton("Bed 1 = Bunk");
		JPanel bedOne = new JPanel();

		ButtonGroup grp2 = new ButtonGroup();
		JRadioButton single2 = new JRadioButton("Bed 2 = Single");
		JRadioButton double2 = new JRadioButton("Bed 2 = Double");
		JRadioButton king2 = new JRadioButton("Bed 2 = King");
		JRadioButton bunk2 = new JRadioButton("Bed 2 = Bunk");
		JPanel bedTwo = new JPanel();
		
		JRadioButton[] r1 = {single1, double1, king1, bunk1};
		JRadioButton[] r2 = {single2, double2, king2, bunk2};
		
		for (int i = 0; i < r1.length; i++) {
			r1[i].setFont(plain);
			grp.add(r1[i]);
			bedOne.add(r1[i]);
			r2[i].setFont(plain);
			grp2.add(r2[i]);
			bedTwo.add(r2[i]);
		}
		
		JCheckBox check1 = new JCheckBox("Bed Linen", true);
		JCheckBox check2 = new JCheckBox("Towels", true);
		
		JButton create = new JButton("Create Bedroom(s)");
		create.setFont(plain);
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (single1.isSelected()) {bed1 = BedType.Single;}
				if (double1.isSelected()) {bed1 = BedType.Double;}
				if (king1.isSelected()) {bed1 = BedType.King;}
				if (bunk1.isSelected()) {bed1 = BedType.Bunk;}
				if (single2.isSelected()) {bed2 = BedType.Single;}
				if (double2.isSelected()) {bed2 = BedType.Double;}
				if (king1.isSelected()) {bed2 = BedType.King;}
				if (bunk1.isSelected()) {bed2 = BedType.Bunk;}
				Bedroom bedroom = new Bedroom(check1.isSelected(), check2.isSelected(), bed1, bed2);
				beds.add(bedroom);
				facility.addBedroom(beds, chosenHouse.getID());
				myProperties.add("Add Outdoor", addOutdoor());
				myPropertiesCards.show(myProperties, "Add Outdoor");
				current = "AO";
				setTitle("New Oudoor Facility");
			}
		});
		
		JButton another = new JButton("Add Another");
		another.setFont(plain);
		another.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (single1.isSelected()) {bed1 = BedType.Single;}
				if (double1.isSelected()) {bed1 = BedType.Double;}
				if (king1.isSelected()) {bed1 = BedType.King;}
				if (bunk1.isSelected()) {bed1 = BedType.Bunk;}
				if (single2.isSelected()) {bed2 = BedType.Single;}
				if (double2.isSelected()) {bed2 = BedType.Double;}
				if (king1.isSelected()) {bed2 = BedType.King;}
				if (bunk1.isSelected()) {bed2 = BedType.Bunk;}
				Bedroom bedroom = new Bedroom(check1.isSelected(), check2.isSelected(), bed1, bed2);
				beds.add(bedroom);
				myProperties.add("Add Bedroom", addBedroom());
				myPropertiesCards.show(myProperties, "Add Bedroom");
				current = "AB";
				setTitle("New Bedroom");
			}
		});
			
		buttons = new JPanel();
		buttons.add(create);
		buttons.add(another);
		
		JPanel hp = new JPanel();
		hp.setLayout(new GridBagLayout());
		JCheckBox[] boxes = {check1, check2};
		for (int i = 0; i < boxes.length; i++) {
			boxes[i].setFont(plain);
			setConstraints(g, 0, i, GridBagConstraints.WEST);
			hp.add(boxes[i], g);
		}
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		ab.add(hp, g);
		setConstraints(g, 0, 1, GridBagConstraints.CENTER);
		ab.add(bedOne, g);
		setConstraints(g, 0, 2, GridBagConstraints.CENTER);
		ab.add(bedTwo, g);
		setConstraints(g, 0, 3, GridBagConstraints.CENTER);
		ab.add(buttons, g);
		
		return ab;	        
	}
    
	// Create page for adding outdoor facilities
	public JPanel addOutdoor() {
		JPanel ao = new JPanel();
		JPanel buttons;
		
		ao.setBorder(HomeBreaks.createTitledBorder("New Outdoor Facility"));
		ao.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		JCheckBox check1 = new JCheckBox("Patio", true);
		check1.setFont(plain);
		JCheckBox check2 = new JCheckBox("BBQ", true);
		check2.setFont(plain);

		ButtonGroup grp = new ButtonGroup();
		JRadioButton free = new JRadioButton("Free Parking");
		free.setFont(plain);
		JRadioButton onRoad = new JRadioButton("On Road Parking");
		onRoad.setFont(plain);
		JRadioButton paid = new JRadioButton("Paid Parking");
		paid.setFont(plain);
		grp.add(free);
		grp.add(onRoad);
		grp.add(paid);
		JPanel park = new JPanel();
		park.add(free);
		park.add(onRoad);
		park.add(paid);
		
		JButton create = new JButton("Create Outdoor Facilities");
		create.setFont(plain);
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (free.isSelected()) {parking = ParkType.free;}
				if (onRoad.isSelected()) {parking = ParkType.onRoad;}
				if (paid.isSelected()) {parking = ParkType.paid;}
				Outdoor outdoor = new Outdoor(check1.isSelected(), check2.isSelected(), parking);
				facility.setOutdoor(outdoor, chosenHouse.getID());
				//cards.show(c, "Add Kitchen");
				myProperties.add("Add Kitchen", addKitchen());
				myPropertiesCards.show(myProperties, "Add Kitchen");
				current = "AK";
				setTitle("New Kitchen");
			}
		});
		
		buttons = new JPanel();
		buttons.add(create);
		JPanel hp = new JPanel();
		hp.setLayout(new GridBagLayout());
		JComponent[] components = {check1, check2};
		
		for (int i = 0; i < components.length; i++) {
			setConstraints(g, 0, i, GridBagConstraints.WEST);
			hp.add(components[i], g);
		}
		
		JPanel[] more = {hp, park, buttons};
		for (int i = 0; i < more.length; i++) {
			setConstraints(g, 0, i, GridBagConstraints.CENTER);
			ao.add(more[i], g);
		}
		
		return ao;	        
	}
	
	// Create page for adding kitchen facilities
	public JPanel addKitchen() {
		final Font plain = new Font("Verdana", Font.PLAIN, 25);
		JPanel ak = new JPanel();
		JPanel buttons;
		
		ak.setBorder(HomeBreaks.createTitledBorder("New Kitchen"));
		ak.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		JCheckBox checkFridge = new JCheckBox("Fridge", true);
		JCheckBox checkMicro = new JCheckBox("Microwave", true);
		JCheckBox checkOven = new JCheckBox("Oven", true);
		JCheckBox checkStore = new JCheckBox("Storage", true);
		JCheckBox checkDish = new JCheckBox("Dishwasher", true);
		JCheckBox checkTable = new JCheckBox("Tableware", true);
		JCheckBox checkCook = new JCheckBox("Cookware", true);
		JCheckBox checkBasic = new JCheckBox("Basic Provisions", true);
		
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
				myProperties.add("Add Charge Band", chargeBandPanel());
				myPropertiesCards.show(myProperties, "Add Charge Band");
			}
		});
		
		buttons = new JPanel();
		buttons.add(create);
		JPanel hp = new JPanel();
		hp.setLayout(new GridBagLayout());
		JCheckBox[] boxes = {checkFridge, checkMicro, checkOven, checkStore, checkDish, checkTable, checkCook, checkBasic};
		for (int i = 0; i < boxes.length; i++) {
			boxes[i].setFont(plain);
			setConstraints(g, 0, i, GridBagConstraints.WEST);
			hp.add(boxes[i], g);
		}
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		ak.add(hp, g);
		setConstraints(g, 0, 1, GridBagConstraints.CENTER);
		ak.add(buttons, g);
		
		return ak;	        
	}
	
	// Creates inquiry panel to be put on the enquirer's page
	public JPanel inquiry() {
		JPanel i = new JPanel();
		JPanel hp = new JPanel();
		JPanel x = new JPanel();
		
		hp.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		x.setBorder(createTitledBorder("Find properties"));
		i.setLayout(new GridBagLayout());
		
		JTextField startDD, endDD, startMM, endMM, startYY, endYY;
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);		
		JTextField searchProperty = new JTextField("Area (i.e. Sheffield)", 20);
		searchProperty.setFont(plain);
		i.add(searchProperty, g);
		
		setConstraints(g, 0, 1, GridBagConstraints.CENTER);
		JLabel start = new JLabel("Start date: ");
		start.setFont(plain);
		i.add(start, g);
		
		JPanel startDate = new JPanel();
		startDate.setLayout(new GridBagLayout());
		
		// Start date input
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
		i.add(startDate, g);

		setConstraints(g, 0, 3, GridBagConstraints.CENTER);
		JLabel end = new JLabel("End date: ");
		end.setFont(plain);
		i.add(end, g);

		JPanel endDate = new JPanel();
		endDate.setLayout(new GridBagLayout());
		
		// Input for end date
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
		i.add(endDate, g);
		
		JPanel btn = new JPanel();
		setConstraints(g, 0, 5, GridBagConstraints.CENTER);
		btn.setBorder(new EmptyBorder(50, 0, 0, 0));
		search = new JButton("Seach Area");
		search.setFont(plain);
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Check the validity of date inputs
				String sY = startYY.getText();
				String sM = startMM.getText();
				String sD = startDD.getText();
				boolean rightStart = HomeBreaks.isNumericDate(sY) && HomeBreaks.isNumericDate(sM) && HomeBreaks.isNumericDate(sD);
				rightStart = rightStart && (sY.length() == 4) && (sM.length() == 2) && (sD.length() == 2);
				boolean unfilledStart = sY.equals("YYYY") || sM.equals("MM") || sD.equals("DD");
				String eY = endYY.getText();
				String eM = endMM.getText();
				String eD = endDD.getText();
				boolean rightEnd = HomeBreaks.isNumericDate(eY) && HomeBreaks.isNumericDate(eM) && HomeBreaks.isNumericDate(eD);
				rightEnd = rightEnd && (eY.length() == 4) && (eM.length() == 2) && (eD.length() == 2);
				boolean unfilledEnd = eY.equals("YYYY") || eM.equals("MM") || eD.equals("DD");
				
				if (unfilledEnd || unfilledStart) {
					showMessageDialog(null, "No dates entered, showing all properties in area.");
					cityFilter = searchProperty.getText();
					c.add("View Properties", viewProperties(null, "City"));
					cards.show(c, "View Properties");
					current = "VP";
					setTitle("View Properties in " + cityFilter);
				}
				else if (rightStart && rightEnd) {
					String start = sY + "-" + sM + "-" + sD;
					String end = eY + "-" + eM + "-" + eD;
					try {
						if (!(Booking.before(start, end))) {
							showMessageDialog(null, "Invalid dates (please check that start date is before end date)");
						}
						else {
							showMessageDialog(null, "Showing properties available from " + start + " to " + end);
							cityFilter = searchProperty.getText();
							startFilter = start;
							endFilter = end;
							c.add("View Properties", viewProperties(null, "Bookmarks"));
							cards.show(c, "View Properties");
							setTitle("View Properties in " + cityFilter);
						}
					} catch (ParseException e1) {
  
						e1.printStackTrace();
					}
				}
			}
		});
		btn.add(search);
		i.add(btn, g);
		i.setBorder(new EmptyBorder(0, 100, 0, 100));
		x.add(i);
		hp.add(x, g);
		setConstraints(g, 0, 2, GridBagConstraints.CENTER);
		
		// Add panel to show results, show default first
		JPanel result = new JPanel();
		CardLayout resultCard = new CardLayout();
		result.setLayout(resultCard);
		result.add("Default", HBPanels.defaultSearchPanel());
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		hp.add(result, g);
		
		return hp;
	}

	// Creates page for enquirers
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
	
	
	// Creates page for hosts upon successful login
	public JPanel hostHome() {
		JPanel hh = new JPanel();
		hh.setLayout(new BorderLayout());
		
		JTabbedPane hostTabs = new JTabbedPane();
		hostTabs.setFont(plain);
		
		myBooking = new JPanel();
		myBookingCards = new CardLayout();
		myBooking.setLayout(myBookingCards);
		
		
		myProperties = new JPanel();
		myPropertiesCards = new CardLayout();
		myProperties.setLayout(myPropertiesCards);
		addHostProperties();
		
		myAccount = new JPanel();
		myAccountCards = new CardLayout();
		myAccount.setLayout(myAccountCards);
		myAccount.add("My Account", myAccount());
		myAccount.add("Edit Info", changeInfoPanel());
		
		myProperties.add("New", newPropertyPanel());
		
		hostTabs.add("My Properties", myProperties);
		try {
			current = "HH";
			myBooking.add("My Bookings", viewBookings());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		hostTabs.add("My Bookings", myBooking);
		hostTabs.add("My Account", myAccount);
		
		hh.add(hostTabs, BorderLayout.CENTER);
		
		return hh;
	}
	
	// Creates page for guests upon successful login
	public JPanel guestHome() {
		JPanel gh = new JPanel();
		
		gh.setLayout(new BorderLayout());
		
		guestTabs = new JTabbedPane();
		guestTabs.setFont(plain);
		
		myProperties = new JPanel();
		myPropertiesCards = new CardLayout();
		myProperties.setLayout(myPropertiesCards);
		
		myAccount = new JPanel();
		myAccountCards = new CardLayout();
		myAccount.setLayout(myAccountCards);
		myAccount.add("My Account", myAccount());
		myAccount.add("Edit Info", changeInfoPanel());
				
		CardLayout card = new CardLayout();
		JPanel booking = new JPanel();
		booking.setLayout(card);
		
		try {
			current = "GH";
			guestTabs.add("My Bookings", viewBookings());
		} catch (ParseException e1) {
			e1.printStackTrace();
			guestTabs.add("My Bookings", pBookings);
		}
		guestTabs.add("Book a property", guestBookingPanel());
		guestTabs.add("My Account", myAccount);
		
		gh.add(guestTabs, BorderLayout.CENTER);
		
		return gh;
	}
	
	// Creates panel for changing a person's info
	public JPanel changeInfoPanel() {
		// Create main panel ci and button panel
		JPanel ci = new JPanel();
        JPanel buttons = new JPanel();
		
        // Create a helper panel hp to keep things centered
		JPanel hp = new JPanel();
		hp.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		ci.setBorder(HomeBreaks.createTitledBorder("My info"));
		ci.setLayout(new GridBagLayout());
		
		JLabel fName, lName, phone;
		JTextField f, l, p;
		
		String fname = "";
		String lname = "";
		String phonenum = "";
		
		// Set default string for JTextField depending on the current page
		if (current == "GH") {
			String[] name = currentGuest.getName().split(" ");
			fname = name[0];
			lname = name[1];
			phonenum = currentGuest.getPhone();
			
		}
		else if (current == "HH") {
			String[] name = currentHost.getName().split(" ");
			fname = name[0];
			lname = name[1];
			phonenum = currentHost.getPhone();
		}
		
		// Input and label for changing first name
		HomeBreaks.setConstraints(g, 0, 0, GridBagConstraints.EAST);
		fName = new JLabel("First Name: ");
		ci.add(fName, g);
		
		HomeBreaks.setConstraints(g, 1, 0, GridBagConstraints.WEST);
		f = new JTextField(20);
		f.setText(fname);
		ci.add(f, g);
		
		// Input and label for changing last name
		HomeBreaks.setConstraints(g, 0, 1, GridBagConstraints.EAST);
		lName = new JLabel("Last Name: ");
		ci.add(lName, g);
		
		HomeBreaks.setConstraints(g, 1, 1, GridBagConstraints.WEST);
		l = new JTextField(20);
		l.setText(lname);
		ci.add(l, g);
		
		// Input and label for changing phone number
		HomeBreaks.setConstraints(g, 0, 2, GridBagConstraints.EAST);
		phone = new JLabel("Phone Number: ");
		ci.add(phone, g);
		
		HomeBreaks.setConstraints(g, 1, 2, GridBagConstraints.WEST);
		p = new JTextField(20);
		p.setText(phonenum);
		ci.add(p, g);
		
		HomeBreaks.setConstraints(g, 1, 3, GridBagConstraints.WEST);
		JButton confirm = new JButton("Confirm");
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fn = f.getText();
				String ln = f.getText();
				String pn = p.getText();
				
				if (fn.isEmpty() || ln.isEmpty() || pn.isEmpty()) {
					showMessageDialog(null, "All fields must not be blank.");
				}
				else {
					if (current == "HH") {
						TDatabase.UpdateValue("Host", "FirstName", currentHost.getID(), fn);
						TDatabase.UpdateValue("Host", "LastName", currentHost.getID(), ln);
						TDatabase.UpdateValue("Host", "PhoneNumber", currentHost.getID(), pn);
					}
					else if (current == "GH") {
						TDatabase.UpdateValue("Guest", "FirstName", currentGuest.getID(), fn);
						TDatabase.UpdateValue("Guest", "LastName", currentGuest.getID(), ln);
						TDatabase.UpdateValue("Guest", "PhoneNumber", currentGuest.getID(), pn);
					}
				}
			}
		});
		
		HomeBreaks.setConstraints(g, 1, 4, GridBagConstraints.WEST);
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myAccountCards.show(myAccount, "My Account");
				current = lp;
				setTitle(lastPage);
			}
		});
		
		JComponent[] comps = {fName, lName, phone, f, l, p, confirm, back};
		for (int i = 0; i < comps.length; i++) {comps[i].setFont(plain);}
		
		buttons.add(confirm);
		buttons.add(back);
		
		ci.add(buttons, g);	
		
		HomeBreaks.setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		hp.add(ci, g);
		
		return hp;
	}
	
	// Creates page for guests to choose properties they want to book
	// Guest already knows the name of property/host they want from enquirer's page
	// But they can still do a search in the area if they forget
	public JPanel guestBookingPanel() {
		JPanel gb = new JPanel();
		JPanel searchPanel = new JPanel();
		resultPanel = new JPanel();
		resultPanelCards = new CardLayout();
		resultPanel.setLayout(resultPanelCards);
		
		gb.setLayout(new BorderLayout());
		searchPanel.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		// Search bar
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		JTextField searchBar = new JTextField(20);
		searchBar.setFont(plain);
		searchBar.setMinimumSize(searchBar.getPreferredSize());
		searchPanel.add(searchBar, g);
		
		// Combo box for different search filters
		setConstraints(g, 1, 0, GridBagConstraints.CENTER);
		JComboBox<String> searchSetting = new JComboBox<String>();
		searchSetting.setFont(plain);
		searchSetting.addItem("Property name");
		searchSetting.addItem("Host name");
		searchSetting.addItem("Area");
		searchPanel.add(searchSetting, g);
		
		// Search Button
		setConstraints(g, 0, 1, GridBagConstraints.CENTER);
		JButton searchBtn = new JButton("Search");
		searchBtn.setFont(plain);
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lastPage = "Guest Home";
				lp = "GH";
				String setting = searchSetting.getSelectedItem().toString();
				String search = searchBar.getText();
				
				if (search.isEmpty()) {
					showMessageDialog(null, "Type in the search bar to start searching!");
				}
				else if (setting == "Area") {
					cityFilter = search;
					JScrollPane s = viewProperties(null, "City");
					s.setPreferredSize(resultPanel.getPreferredSize());
					resultPanel.add("Properties in area", viewProperties(null, "City"));
					resultPanelCards.show(resultPanel, "Properties in area");
				}
				else if (setting == "Host name") {
					hostFilter = search;
					resultPanel.add("Matching hosts", searchHost());
					resultPanelCards.show(resultPanel, "Matching hosts");
				}
				else if (setting == "Property name") {
					propertyNameFilter = search;
					resultPanel.add("Matching properties" , viewProperties(null, "Name"));
					resultPanelCards.show(resultPanel, "Matching properties");
				}
			}
		});
		searchPanel.add(searchBtn, g);
		
		// add cards for resultPanel
		resultPanel.add("Default", HBPanels.defaultSearchPanel());
		
		gb.add(searchPanel, BorderLayout.NORTH);

		gb.add(resultPanel, BorderLayout.CENTER);
		
		return gb;
	}
	
	// Shows result of host name searches
	public JScrollPane searchHost() {		
		JPanel sh = new JPanel();
		CardLayout crd = new CardLayout();
		JPanel hp = new JPanel();
		hp.setLayout(crd);
		sh.setBorder(HomeBreaks.createTitledBorder(""));
		// Create a list of relevant hosts
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
		// Show host's name only, and add button to view more
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

	// Creates panel for available account options for logged in guests and hosts
	public JPanel myAccount() {
		JPanel ma = new JPanel();
		ma.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
				
		JButton editInfo, logOut;
		// Button to change name and phone number
		editInfo = new JButton("Account Info");
		editInfo.setFont(plain);
		editInfo.setPreferredSize(new Dimension(400, 50));
		editInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myAccountCards.show(myAccount, "Edit Info");
			}
		});
		ma.add(editInfo, g);
		// logout button
		setConstraints(g, 0, 1, GridBagConstraints.CENTER);
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
	
	// Shows bookings related to a person
	public JScrollPane viewBookings() throws ParseException {
		JPanel b = new JPanel();
		b.setLayout(new GridLayout(1, 2));
		b.setBorder(HomeBreaks.createTitledBorder(""));
		Font plain = new Font("Verdana", Font.PLAIN, 18);
		
		Map<Integer, Booking> acceptedBookings = new HashMap<Integer, Booking>();
		Map<Integer, Booking> provisionalBookings = new HashMap<Integer, Booking>();
		// Filter bookings for guest page
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
						System.out.println("Goes into provisional: " + bookingID);
						provisionalBookings.put(bookingID, booking);
					}
				}
			}
		}
		// Filter bookings for host page
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
		provisional.setLayout(b2);
		provisional.setBorder(createTitledBorder("Other bookings"));
		
		// For guests and hosts accepted bookings
		int acceptedCount = 0;
		for (Booking acceptedBooking : acceptedBookings.values()) {
			Host host = TDatabase.Hosts.get(acceptedBooking.getHostID());
			Guest guest = TDatabase.Guests.get(acceptedBooking.getGuestID());
			JPanel vb = new JPanel();
			JLabel pName, personName, sDate, eDate, contactN, contactE, status, numNights, pricePerNight, serviceCharge, cleaningCharge, passed;
			// set the labels and buttons that vary depending on the situation to have no text
			personName = new JLabel();
			contactN = new JLabel();
			contactE = new JLabel();
			passed = new JLabel();
			JButton review = new JButton("Submit a review!");
			review.setFont(plain);
			review.setVisible(false);
			review.setEnabled(false);
			
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
				
				// If the booking dates has passed, show review option
				if (Booking.hasPassed(acceptedBooking.getStartDate()) && Booking.hasPassed(acceptedBooking.getEndDate())) {
					passed.setText("You have finished your stay! Submit a review?");
					passed.setFont(plain);
					review.setVisible(true);
					review.setEnabled(true);
					review.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							HBPanels panel;
							try {
								panel = new HBPanels();
								guestTabs.add("Review Page", panel.reviewPanel(TDatabase.Properties.get(acceptedBooking.getPropertyID()), currentGuest));
								review.setEnabled(false);
								passed.setText("Thank you for leaving a review!");
								
							} catch (ParseException e1) {
								showMessageDialog(null, "Oops! Something went wrong! Please try again later");
								e1.printStackTrace();
							}
						}
					});
				}
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
			numNights = new JLabel();
			pricePerNight = new JLabel();
			serviceCharge = new JLabel();
			cleaningCharge = new JLabel();
			status = new JLabel();
			
			ChargeBand chargeBand = null;
			// Add Chargeband info
			for (ChargeBand c : TDatabase.ChargeBands.values()) {
				if (c.getPropertyID() == acceptedBooking.getPropertyID()) {
					if (Booking.overlap(c.getStartDate(), c.getEndDate(), acceptedBooking.getStartDate(), acceptedBooking.getEndDate())) {
						chargeBand = c;
					}
				}
			}
			
			if (chargeBand != null) {
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
			}
			
			
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
			vb.add(passed);
			vb.add(review);
			if (acceptedCount <= acceptedBookings.size()) {vb.add(s);}
			
			accepted.add(vb);
			acceptedCount++;
		}
		
		// for guests' and hosts' provisional bookings
		int pCount = 0;
		for (Booking booking : provisionalBookings.values()) {
			Host host = TDatabase.Hosts.get(booking.getHostID());
			Guest guest = TDatabase.Guests.get(booking.getGuestID());
			provisional.setBorder(createTitledBorder("Other bookings"));
			JPanel vb = new JPanel();
			JLabel pName, personName, sDate, eDate, status, pricePerNight, serviceCharge, cleaningCharge;
			String stat = "";
			personName = new JLabel();
			pricePerNight = new JLabel();
			serviceCharge = new JLabel();
			cleaningCharge = new JLabel();
			
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
				personName.setText("Guest name: " + guest.getName());
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
			
			
			if (chargeBand != null) {
				pricePerNight.setText("Price Per Night: " + chargeBand.getPPN());
				pricePerNight.setFont(plain);
				serviceCharge.setText("Service Charge: " + chargeBand.getSC());
				serviceCharge.setFont(plain);
				cleaningCharge.setText("Cleaning Charge: " + chargeBand.getCC());
				cleaningCharge.setFont(plain);
			}
			
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
			
			// Add accept and reject button for hosts (provisional booking only)
			JPanel buttons = new JPanel();
			JButton accept = new JButton("Accept");
			JButton reject = new JButton("Reject");
			accept.setFont(plain);
			reject.setFont(plain);
			accept.setVisible(false);
			reject.setVisible(false);
			accept.setEnabled(false);
			reject.setEnabled(false);
			
			accept.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					TDatabase.UpdateBookingValue(booking.getID(), "Provisional", 0);
					TDatabase.UpdateBookingValue(booking.getID(), "Rejected", 0);
					TDatabase.Bookings.get(booking.getID()).setProvisional(false);
					TDatabase.Bookings.get(booking.getID()).setRejected(false);
					accept.setEnabled(false);
					reject.setEnabled(false);
					try {
						myBooking.add("Booking", viewBookings());
						myBookingCards.show(myBooking, "Booking");
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}				
				}
			});
			
			reject.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					TDatabase.UpdateBookingValue(booking.getID(), "Provisional", 0);
					TDatabase.UpdateBookingValue(booking.getID(), "Rejected", 1);
					TDatabase.Bookings.get(booking.getID()).setProvisional(false);
					TDatabase.Bookings.get(booking.getID()).setRejected(true);
					accept.setEnabled(false);
					reject.setEnabled(false);
					try {
						myBooking.add("Booking", viewBookings());
						myBookingCards.show(myBooking, "Booking");
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			
			if (current == "HH") {
				accept.setVisible(true);
				accept.setEnabled(true);
				reject.setVisible(true);
				reject.setEnabled(true);
			}
			
			buttons.add(accept);
			buttons.add(reject);
			vb.add(buttons);
			if (pCount <= acceptedBookings.size()) {vb.add(s);}
			
			provisional.add(vb);
			pCount++;
		}
		
		// Add accepted booking and provisional booking lists
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
		final Font plain = new Font("Verdana", Font.PLAIN, 20);
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
			overall.setForeground(Color.YELLOW);
			
			// Get guest's overall rating for the property and print out number of stars
			double ovrll = r.overallRating();
			
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
	
	// Creates a page for charge band addition
	public JPanel chargeBandPanel() {		
		JPanel acb = new JPanel();
		JPanel hp = new JPanel();
		
		acb.setLayout(new GridBagLayout());
		acb.setBorder(HomeBreaks.createTitledBorder("Add Charge Band"));
		hp.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		JLabel s, e, ppn, sc, cc;
		JTextField startDD, endDD, startMM, endMM, startYY, endYY, pricePerNight, serviceCharge, cleaningCharge;
		
		HomeBreaks.setConstraints(g, 0, 0, GridBagConstraints.EAST);
		s = new JLabel("Start Date: ");
		s.setFont(plain);
		acb.add(s, g);
		
		JPanel startDate = new JPanel();
		startDate.setLayout(new GridBagLayout());
		HomeBreaks.setConstraints(g, 0, 0, GridBagConstraints.WEST);
		startDD = new JTextField("DD", 2);
		startDD.setFont(plain);
		startDate.add(startDD, g);
		HomeBreaks.setConstraints(g, 1, 0, GridBagConstraints.WEST);
		JLabel d1 = new JLabel("-");
		d1.setFont(plain);
		startDate.add(d1, g);
		HomeBreaks.setConstraints(g, 2, 0, GridBagConstraints.WEST);
		startMM = new JTextField("MM", 2);
		startMM.setFont(plain);
		startDate.add(startMM, g);
		HomeBreaks.setConstraints(g, 3, 0, GridBagConstraints.WEST);
		JLabel d2 = new JLabel("-");
		d2.setFont(plain);
		startDate.add(d2, g);
		HomeBreaks.setConstraints(g, 4, 0, GridBagConstraints.WEST);
		startYY = new JTextField("YYYY", 4);
		startYY.setFont(plain);
		startDate.add(startYY, g);
		
		HomeBreaks.setConstraints(g, 1, 0, GridBagConstraints.WEST);
		acb.add(startDate, g);
		
		HomeBreaks.setConstraints(g, 0, 1, GridBagConstraints.EAST);
		e = new JLabel("End Date: ");
		e.setFont(plain);
		acb.add(e, g);
		
		JPanel endDate = new JPanel();
		endDate.setLayout(new GridBagLayout());
		
		HomeBreaks.setConstraints(g, 0, 0, GridBagConstraints.WEST);
		endDD = new JTextField("DD", 2);
		endDD.setFont(plain);
		endDate.add(endDD, g);
		HomeBreaks.setConstraints(g, 1, 0, GridBagConstraints.WEST);
		JLabel d3 = new JLabel("-");
		d3.setFont(plain);
		endDate.add(d3, g);
		HomeBreaks.setConstraints(g, 2, 0, GridBagConstraints.WEST);
		endMM = new JTextField("MM", 2);
		endMM.setFont(plain);
		endDate.add(endMM, g);
		HomeBreaks.setConstraints(g, 3, 0, GridBagConstraints.WEST);
		JLabel d4 = new JLabel("-");
		d4.setFont(plain);
		endDate.add(d4, g);
		HomeBreaks.setConstraints(g, 4, 0, GridBagConstraints.WEST);
		endYY = new JTextField("YYYY", 4);
		endYY.setFont(plain);
		endDate.add(endYY, g);
		
		HomeBreaks.setConstraints(g, 1, 1, GridBagConstraints.CENTER);
		acb.add(endDate, g);
		
		HomeBreaks.setConstraints(g, 0, 2, GridBagConstraints.EAST);
		ppn = new JLabel("Price per night (Â£): ");
		ppn.setFont(plain);
		acb.add(ppn, g);
		
		HomeBreaks.setConstraints(g, 1, 2, GridBagConstraints.WEST);
		pricePerNight = new JTextField(5);
		pricePerNight.setFont(plain);
		acb.add(pricePerNight, g);
		
		HomeBreaks.setConstraints(g, 0, 3, GridBagConstraints.EAST);
		sc = new JLabel("Service charge (Â£): ");
		sc.setFont(plain);
		acb.add(sc, g);
		
		HomeBreaks.setConstraints(g, 1, 3, GridBagConstraints.WEST);
		serviceCharge = new JTextField(5);
		serviceCharge.setFont(plain);
		acb.add(serviceCharge, g);
		
		HomeBreaks.setConstraints(g, 0, 4, GridBagConstraints.EAST);
		cc = new JLabel("Cleaning charge (Â£): ");
		cc.setFont(plain);
		acb.add(cc, g);
		
		HomeBreaks.setConstraints(g, 1, 4, GridBagConstraints.WEST);
		cleaningCharge = new JTextField(5);
		cleaningCharge.setFont(plain);
		acb.add(cleaningCharge, g);
		
		HomeBreaks.setConstraints(g, 1, 5, GridBagConstraints.WEST);
		JButton add = new JButton("Add");
		add.setFont(plain);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sY = startYY.getText();
				String sM = startMM.getText();
				String sD = startDD.getText();
				boolean rightStart = HomeBreaks.isNumericDate(sY) && HomeBreaks.isNumericDate(sM) && HomeBreaks.isNumericDate(sD);
				rightStart = rightStart && (sY.length() == 4) && (sM.length() == 2) && (sD.length() == 2);
				boolean unfilledStart = sY.isEmpty() || sM.isEmpty() || sD.isEmpty();
				
				String eY = endYY.getText();
				String eM = endMM.getText();
				String eD = endDD.getText();
				boolean rightEnd = HomeBreaks.isNumericDate(eY) && HomeBreaks.isNumericDate(eM) && HomeBreaks.isNumericDate(eD);
				rightEnd = rightEnd && (eY.length() == 4) && (eM.length() == 2) && (eD.length() == 2);
				boolean unfilledEnd = eY.isEmpty() || eM.isEmpty() || eD.isEmpty();
				
				String price = pricePerNight.getText();
				String cleaning = cleaningCharge.getText();
				String service = serviceCharge.getText();
				boolean rightPrices = HomeBreaks.isNumericPrice(price) || HomeBreaks.isNumericPrice(cleaning) || HomeBreaks.isNumericPrice(service);
				boolean unfilledPrices = price.isEmpty() || cleaning.isEmpty() || service.isEmpty();
				
				
				if (unfilledStart || unfilledEnd || unfilledPrices) {
					showMessageDialog(null, "All fields must be filled.");
				}
				else if (Integer.parseInt(sM) > 12 || Integer.parseInt(eM) > 12) {showMessageDialog(null, "Invalid months input.");}
				else if (rightStart && rightEnd && rightPrices) {
					String start = sY + "-" + sM + "-" + sD;
					String end = eY + "-" + eM + "-" + eD;
					
					try {
						if (Booking.before(start, end) && !(Booking.hasPassed(start)) && !(Booking.hasPassed(start))) {
							TDatabase.AddChargeBand(start, end, chosenHouse.getID(), Double.parseDouble(price), Double.parseDouble(service), Double.parseDouble(cleaning));
							showMessageDialog(null, "Charge Band added!");
							// Refresh host's property page
							addHostProperties();
						}
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
				else {
					showMessageDialog(null, "Incorrect input");
				}
				
			}
		});
		acb.add(add, g);
		
		
		HomeBreaks.setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		hp.add(acb);
		return hp;
	}
	
	// Creates a page for guest bookings
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
				boolean rightStart = HomeBreaks.isNumericDate(sY) && HomeBreaks.isNumericDate(sM) && HomeBreaks.isNumericDate(sD);
				boolean unfilledStart = sY.isEmpty() || sM.isEmpty() || sD.isEmpty();
				
				String eY = endYY.getText();
				String eM = endMM.getText();
				String eD = endDD.getText();
				boolean rightEnd = HomeBreaks.isNumericDate(eY) && HomeBreaks.isNumericDate(eM) && HomeBreaks.isNumericDate(eD);
				boolean unfilledEnd = eY.isEmpty() || eM.isEmpty() || eD.isEmpty();
				
				String start = sY + "-" + sM + "-" + sD;
				String end = eY + "-" + eM + "-" + eD;
				
				if (Integer.parseInt(sM) > 12 || Integer.parseInt(eM) > 12) {
					showMessageDialog(null, "Invalid month input");
				}
				else {
					try {
						if (Booking.hasPassed(start) || Booking.hasPassed(end)) {
							showMessageDialog(null, "Cannot book for dates already passed!");
						}
						else {
							// get list of all bookings, check if any overlaps with given date
							for (Booking b : TDatabase.Bookings.values()) {
								String sd = b.getStartDate();
								String ed = b.getEndDate();
								
								boolean accepted = !(b.getProvisional()) && !(b.getRejected());
								
								try {
									if (!(overlap)) {
										if (Booking.overlap(sd, ed, start, end) && !(accepted)) {
											showMessageDialog(null, "Property is already booked for that date.");
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
								Booking booking = new Booking(propertyID, hostID,guestID, start, end, true, false, true);
								TDatabase.Bookings.put(booking.getID(), booking);
								showMessageDialog(null, "Booking successfull!");
								resultPanelCards.show(resultPanel, "Default");
							}
						}
					}
					catch (ParseException p) {
						p.printStackTrace();
					}
				}
			}
		});
		bp.add(request, g);
		
		return bp;
	}
	
	// Filter for city/area search
	public void filterCity(Map<Integer, Property> filteredList) {
		for (Property house : TDatabase.Properties.values())  {
			Address address = house.getFullAddress();
			String city = address.getCity();
			if (city.equals(cityFilter)){
				filteredList.put(house.getID(), house);
			}
		}
	}
	
	// Filter for host name search
	public void filterHost(Host host, Map<Integer, Property> filteredList) {
		for (Property house : TDatabase.Properties.values())  {
			String hostID = host.getID();
			if (hostID.equals(house.getHost().getID())){
				filteredList.put(house.getID(), house);
			}
		}
	}
	
	// Filter for property name search
	public void filterPName(Map<Integer, Property> filteredList) {
		for (Property house : TDatabase.Properties.values()) {
			String houseName = house.getShortName();
			if (houseName.equals(propertyNameFilter)) {
				filteredList.put(house.getID(), house);
			}
		}
	}
	
	// Bookmarks filter
	public void filterBookmarks(Map<Integer, Property> filteredList) {
		Map<Integer, Property> inArea = new HashMap<Integer, Property>();
		filterCity(inArea);
		
		for (Property house : inArea.values()) {
			// Check for accepted bookings that overlaps with the given dates
			boolean overlap = false;
			for (Booking booking : TDatabase.Bookings.values()) {
				try {
					if (booking.getPropertyID() == house.getID()) {
						if (Booking.overlap(booking.getStartDate(), booking.getEndDate(), startFilter, endFilter) && !booking.getProvisional() && !booking.getRejected()) 
					    {overlap = true;}
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (!(overlap)) {filteredList.put(house.getID(), house);}
		}
	}
	
	// Default host should be null
	public JScrollPane viewProperties(Host host, String filter) {
		JPanel vp = new JPanel();
		String breakTxt = "";
		
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
		
		
		Map<Integer, Property> filterProperties = new HashMap<Integer, Property>();
		
		if (filter == "City") {filterCity(filterProperties);}
		else if (filter == "Host") {filterHost(host, filterProperties);}
		else if (filter == "Name") {filterPName(filterProperties);}
		else if (filter == "Bookmarks") {filterBookmarks(filterProperties);}
		
		for (Property chosenHouse : filterProperties.values()) {
			// Calculate Rating
			// Get all reviews related to property p and store them in a map
			Map<Integer, Review> related = new HashMap<Integer, Review>();
			for (Review review : TDatabase.Reviews.values()) {
				if (chosenHouse.getID() == review.getPropertyID()) {related.put(review.getID(), review);}
			}
			
			double rate = 0;
			RatingCategory[] category = {RatingCategory.Cleanliness, RatingCategory.Communication, RatingCategory.CheckIn, 
					RatingCategory.Accuracy, RatingCategory.Location,RatingCategory.Value};
			double[] values = new double[6];
			int numReviews = 0;
			
			for (int i = 0; i < category.length; i++) {
				for (Review rev : related.values()) {
					values[i] += rev.getRatingMap().get(category[i]);
					numReviews++;
				}
				
				values[i] = values[i] / numReviews;
				numReviews = 0;
			}
			// Calculate the overall rating for property
			for (int i = 0; i < category.length; i++) {rate += values[i];}
			rate = rate / category.length;
			
			
			facility = chosenHouse.getFacilities();
			//house information
			shortName = new JLabel("Name: "+ chosenHouse.getShortName());
			shortName.setFont(bold);
			location = new JLabel("Location: "+ chosenHouse.getPublicLocation());
			location.setFont(plain);
			description = new JLabel("Description: " + chosenHouse.getDescription());
			description.setFont(plain);
			if (chosenHouse.getBreakfast()) breakTxt = "Breakfast is served";
			else if (!chosenHouse.getBreakfast()) breakTxt = "Breakfast is not served";
			breakfast = new JLabel(breakTxt);
			breakfast.setFont(plain);
			maxsleep = new JLabel("Maximum Sleep: " + chosenHouse.getMaxSleepers());
			maxsleep.setFont(plain);
			rating = new JLabel("Rating: " + rate);
			rating.setFont(plain);
			numbaths = new JLabel("Number of Bathrooms: " + facility.getBathroomNum());
			numbaths.setFont(plain);
			numbeds = new JLabel("Number of Bedrooms: " + facility.getBedroomNum());
			numbeds.setFont(plain);
			
			viewMoreBtn = new JButton("View More");
			viewMoreBtn.setFont(plain);
			viewMoreBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (lastPage != "Guest Home" && lastPage != "Host Home") {
						lastPage = "View Properties";
						lp = "VP";
					}
					
					if (current == "GH") {
						resultPanel.add(chosenHouse.getShortName(), houseView(chosenHouse));
						resultPanelCards.show(resultPanel, chosenHouse.getShortName());
					}
					else {
						c.add(chosenHouse.getShortName(), houseView(chosenHouse));
						cards.show(c, chosenHouse.getShortName());
					}
					setTitle("House View");
				}
			});
			
			// Add charge band button for hosts
			JButton addCB = new JButton("Add charge band");
			addCB.setFont(plain);
			addCB.addActionListener(new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					myProperties.add("Add Charge Band", chargeBandPanel());
					myPropertiesCards.show(myProperties, "Add Charge Band");
				}
			});
			addCB.setVisible(false);
			addCB.setEnabled(false);
			if (current == "HH") {
				addCB.setVisible(true);
				addCB.setEnabled(true);
			}
			
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
			buttons.add(addCB);
			
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
		
		if (current != "HH" && current != "GH") {vp.add(createHomeBtnPanel(), gbc);}
		
		return scroll;
	}
	
	// Shows property details, including ratings and reviews
	public JPanel houseView(Property house) {
		chosenHouse = house;
		facility = house.getFacilities();
		String breakTxt = "";
		JPanel hv = new JPanel();
		hv.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		
		hv.setBorder(createTitledBorder("Name: "+ house.getShortName()));
		hv.setLayout(new GridBagLayout());
		final Font plain = new Font("Verdana", Font.PLAIN, 20);
		final Font bold = new Font("Verdana", Font.BOLD, 50);
		
		JLabel shortName, description, host, location, breakfast;
		JPanel sn, d, h, pl, bekfast, buttons;
		
		//house information
//		shortName = new JLabel("Name: "+ house.getShortName());
//		shortName.setFont(bold);
		description = new JLabel("Description: " + house.getDescription());
		description.setFont(plain);
		location = new JLabel("Location: "+ house.getPublicLocation());
		location.setFont(plain);
		host = new JLabel("" + house.getHost().getName());
		host.setFont(plain);
		if (chosenHouse.getBreakfast()) breakTxt = "Breakfast is served";
		else if (!chosenHouse.getBreakfast()) breakTxt = "Breakfast is not served";
		breakfast = new JLabel(breakTxt);
		breakfast.setFont(plain);
		
		JButton bedsBtn = new JButton("View Bedrooms");
		bedsBtn.setFont(plain);
		bedsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Bedroom> beds = facility.getBedroom();
				int i = 1;
				String str = "";
				for (Bedroom bed : beds) {
					str += "Bedroom " + i + "\n" + bed.toString();
					i++;
				}
				showMessageDialog(null, "" + str);
			}
		});
		JButton bathBtn = new JButton("View Bathrooms");
		bathBtn.setFont(plain);
		bathBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Bathroom> baths = facility.getBathroom();
				int i = 1;
				String str = "";
				for (Bathroom bath : baths) {
					str += "Bathroom " + i + "\n" + bath.toString();
					i++;
				}
				showMessageDialog(null, "" + str);
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
				resultPanel.add("Booking", bookingPage());
				resultPanelCards.show(resultPanel, "Booking");
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
				if (lastPage == "View Properties"){
					c.add("View Properties", viewProperties(null, "City"));
					cards.show(c, "View Properties");
					current = "VP";
					setTitle("View Properties");
				}
				else {
					cards.show(c, lastPage);
					current = lp;
					setTitle(lastPage);
				}
			}
		});

		BoxLayout bl = new BoxLayout(hv, BoxLayout.Y_AXIS);
		hv.setLayout(bl);
		
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
		
		hv.add(location);
		hv.add(description);
		hv.add(host);
		hv.add(breakfast);
		hv.add(buttons);
		// Add property's rating and reviews respectively
		hv.add(avgPropertyRating());
		hv.add(viewReviews());
		
		return hv;
	}
	
	// Shows a host's profile (non-confidential details and properties)
	public JPanel hostProfile(Host host) {
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		JLabel n;
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
			if (Integer.parseInt(currentHost.getID()) == Integer.parseInt(house.getHost().getID())) {relatedRatings.put(rating.getID(), rating);}
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
	
	// returns a panel with calculated average rating for each category of each property
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
		overall.setFont(plain);
		
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
	
	// Check if the password input and confirm password input match for guest sign up
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
	
	// Check if the password input and confirm password input match for host sign up
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
	
	// Decides which password checker to use depending on current page
	public void checkPasswords() {
		if (current == "HSU") {
			checkPasswordsHSU();
		}
		else if (current == "GSU") {
			checkPasswordsGSU();
		}
	}
	
	// Creates a home button
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
	
	// Sets gridx, gridy, and anchor for a given gridbag constraint
	public static void setConstraints(GridBagConstraints gbc, int x, int y, int anchor) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.anchor = anchor;
	}
	
	// Create a titled border
	public static TitledBorder createTitledBorder(String title) {
		TitledBorder tb = new TitledBorder(title);
		tb.setTitleFont(new Font("Verdana", Font.BOLD, 50));
		
		return tb;
	}
	
	// Find the selected value for JRadioButtons
	public static int findSelectedValue(JRadioButton a, JRadioButton b, JRadioButton c, JRadioButton d, JRadioButton e) {
		int value = 0;
		if (a.isSelected()) {value = 1;}
		else if (b.isSelected()) {value = 2;}
		else if (c.isSelected()) {value = 3;}
		else if (d.isSelected()) {value = 4;}
		else if (e.isSelected()) {value = 5;}
		return value;
	}
	
	// Creates radio buttons for ratings (values 1 to 5)
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
	
	// Checks if input for prices are correct
	public static boolean isNumericPrice(String str) {
		  return str.matches("\\d+(\\.\\d+)?");
	}
	
	// Checks if input for dates are correct
	public static boolean isNumericDate(String str) {
		  return str.matches("\\d+\\d");
	}
	
	
	public static void main (String [] args) throws ParseException {
		new HomeBreaks();
	}
}
