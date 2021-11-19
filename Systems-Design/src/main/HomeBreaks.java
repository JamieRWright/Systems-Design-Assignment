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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class HomeBreaks extends JFrame implements ActionListener, DocumentListener {
	CardLayout cards;
	Container c = getContentPane();
	String current = "";
	JPanel home, guestLogin, hostLogin;
	JButton toHome, host, enquirer, guest, gsuBtn, hsuBtn, glBtn, hlBtn, toGSU, toHSU, search, homeBtn;
	JTextField fName_input_gsu, lName_input_gsu, add1_gsu, add2_gsu, add3_gsu, add4_gsu, phone_input_gsu, id_input_gsu, id_input_gl;
	JTextField fName_input_hsu, lName_input_hsu, add1_hsu, add2_hsu, add3_hsu, add4_hsu, phone_input_hsu, id_input_hsu, id_input_hl;
	JTextField searchProperty;
	JLabel warning_gsu = new JLabel("");
	JLabel warning_hsu = new JLabel("");
	JPasswordField pw_input_gsu, confirm_input_gsu, pw_input_gl;
	JPasswordField pw_input_hsu, confirm_input_hsu, pw_input_hl;
	
	final Font plain = new Font("Verdana", Font.PLAIN, 25);
	//final Font smaller = new Font("Verdana", Font.PLAIN, 20);
	final Font bold = new Font("Verdana", Font.BOLD, 50);
	
	public HomeBreaks() {
		startGUI();
	}
	
	public void startGUI() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screen = tk.getScreenSize();
				
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
		c.add("Inquiry", inquiry());
		c.add("Host Home", hostHome());
		
		pack();
		setVisible(true);
	}
	
	public JPanel home() {
		JPanel home = new JPanel();
		JPanel hp = new JPanel();
		hp.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		
		home.setBorder(createTitledBorder("Welcome to HomeBreaks Plc"));
		home.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
        JPanel buttons, labels;
		
		JLabel welc, opt;

		setConstraints(gbc, 0, 0, GridBagConstraints.CENTER);
		opt = new JLabel("To start, choose a role");
		opt.setFont(plain);
		home.add(opt, gbc);
		
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
		
		setConstraints(gbc, 0, 1, GridBagConstraints.CENTER);
		home.add(buttons, gbc);
		
		hp.add(home);
		return hp;
	}
	
	public JPanel guestSU() {
		JPanel gsu = new JPanel();
		JPanel hp = new JPanel();
		hp.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		
		gsu.setBorder(createTitledBorder("Sign Up as Guest"));
		gsu.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel fName, lName, addA, addB, addC, addD, phone, id, pw, confirm_pw;
		
		// Input for user's name
		setConstraints(gbc, 0, 0, GridBagConstraints.EAST);
		fName = new JLabel("First Name: ");
		fName.setFont(plain);
		gsu.add(fName, gbc);
		
		setConstraints(gbc, 1, 0, GridBagConstraints.WEST);
		fName_input_gsu = new JTextField(20);
		fName_input_gsu.setFont(plain);
		gsu.add(fName_input_gsu, gbc);
		
		setConstraints(gbc, 0, 1, GridBagConstraints.EAST);
		lName = new JLabel("Last Name: ");
		lName.setFont(plain);
		gsu.add(lName, gbc);
		
		setConstraints(gbc, 1, 1, GridBagConstraints.WEST);
		lName_input_gsu = new JTextField(20);
		lName_input_gsu.setFont(plain);
		gsu.add(lName_input_gsu, gbc);
		
		// Input for user's address
		setConstraints(gbc, 0, 2, GridBagConstraints.EAST);
		addA = new JLabel("House No.: ");
		addA.setFont(plain);
		gsu.add(addA, gbc);
		
		setConstraints(gbc, 1, 2, GridBagConstraints.WEST);
		add1_gsu = new JTextField(20);
		add1_gsu.setFont(plain);
		gsu.add(add1_gsu, gbc);
		
		setConstraints(gbc, 0, 3, GridBagConstraints.EAST);
		addB = new JLabel("Street Name: ");
		addB.setFont(plain);
		gsu.add(addB, gbc);
		
		setConstraints(gbc, 1, 3, GridBagConstraints.WEST);
		add2_gsu = new JTextField(20);
		add2_gsu.setFont(plain);
		gsu.add(add2_gsu, gbc);
		
		setConstraints(gbc, 0, 4, GridBagConstraints.EAST);
		addC = new JLabel("Postcode: ");
		addC.setFont(plain);
		gsu.add(addC, gbc);
		
		setConstraints(gbc, 1, 4, GridBagConstraints.WEST);
		add3_gsu = new JTextField(20);
		add3_gsu.setFont(plain);
		gsu.add(add3_gsu, gbc);
		
		setConstraints(gbc, 0, 5, GridBagConstraints.EAST);
		addD = new JLabel("Place Name: ");
		addD.setFont(plain);
		gsu.add(addD, gbc);
		
		setConstraints(gbc, 1, 5, GridBagConstraints.WEST);
		add4_gsu = new JTextField(20);
		add4_gsu.setFont(plain);
		gsu.add(add4_gsu, gbc);
		
		// Input for phone number
		setConstraints(gbc, 0, 6, GridBagConstraints.EAST);
		phone = new JLabel("Phone No.: ");
		phone.setFont(plain);
		gsu.add(phone, gbc);
		
		setConstraints(gbc, 1, 6, GridBagConstraints.WEST);
		phone_input_gsu = new JTextField(20);
		phone_input_gsu.setFont(plain);
		gsu.add(phone_input_gsu, gbc);
		
		// Input for email
		setConstraints(gbc, 0, 7, GridBagConstraints.EAST);
		id = new JLabel("Email: ");
		id.setFont(plain);
		gsu.add(id, gbc);
		
		setConstraints(gbc, 1, 7, GridBagConstraints.WEST);
		id_input_gsu = new JTextField(20);
		id_input_gsu.setFont(plain);
		gsu.add(id_input_gsu, gbc);
		
		// Input for password
		setConstraints(gbc, 0, 8, GridBagConstraints.EAST);
		pw = new JLabel("Password: ");
		pw.setFont(plain);
		gsu.add(pw, gbc);
		
		setConstraints(gbc, 1, 8, GridBagConstraints.WEST);
		pw_input_gsu = new JPasswordField(20);
		pw_input_gsu.setFont(plain);
		pw_input_gsu.setEchoChar('*');
		pw_input_gsu.getDocument().addDocumentListener(this);
		gsu.add(pw_input_gsu, gbc);
		
		// Input for confirm password
		setConstraints(gbc, 0, 9, GridBagConstraints.EAST);
		confirm_pw = new JLabel("Confirm Password: ");
		confirm_pw.setFont(plain);
		gsu.add(confirm_pw, gbc);
		
		setConstraints(gbc, 1, 9, GridBagConstraints.WEST);
		confirm_input_gsu = new JPasswordField(20);
		confirm_input_gsu.setFont(plain);
		confirm_input_gsu.setEchoChar('*');
		confirm_input_gsu.getDocument().addDocumentListener(this);
		gsu.add(confirm_input_gsu, gbc);
		
		setConstraints(gbc, 1, 10, GridBagConstraints.WEST);
		gsuBtn = new JButton("Sign up");
		gsuBtn.setFont(plain);
		gsuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fName = fName_input_gsu.getText();
				String lName = lName_input_gsu.getText();
				String houseName = add1_gsu.getText();
				String streetName = add2_gsu.getText();
				String placeName = add3_gsu.getText();
				String postcode = add4_gsu.getText();
				String phone = phone_input_gsu.getText();
				String userID = id_input_gsu.getText();
				String password = pw_input_gsu.getText();
				
				if (fName.isEmpty() || lName.isEmpty() || houseName.isEmpty() || streetName.isEmpty() || placeName.isEmpty() || fName.isEmpty() || postcode.isEmpty() || phone.isEmpty() || userID.isEmpty() || password.isEmpty()) {
					showMessageDialog(null, "Please fill in all blanks.");
				}
				else {
					Address add = new Address(houseName, streetName, placeName, postcode);
					Guest guest = new Guest(fName, lName, add, phone, userID, password);
					
					System.out.println(guest);
					
					if (!(guest.getSuccess())) {
						showMessageDialog(null, "Sign up failed.");
					}
				}
			}
		});
		gsu.add(gsuBtn, gbc);
		
		hp.add(gsu, g);
		setConstraints(g, 0, 1, GridBagConstraints.CENTER);
		hp.add(createHomeBtnPanel(), g);
		
		return hp;
	}
	
	public JPanel hostSU() {
		JPanel hsu = new JPanel();
		JPanel hp = new JPanel();
		hp.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		
		hsu.setBorder(createTitledBorder("Sign Up as Host"));
		hsu.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel fName, lName, addA, addB, addC, addD, phone, id, pw, confirm_pw;
		
		// Input for user's name
		setConstraints(gbc, 0, 0, GridBagConstraints.EAST);
		fName = new JLabel("First Name: ");
		fName.setFont(plain);
		hsu.add(fName, gbc);
		
		setConstraints(gbc, 1, 0, GridBagConstraints.WEST);
		fName_input_hsu = new JTextField(20);
		fName_input_hsu.setFont(plain);
		hsu.add(fName_input_hsu);
		
		setConstraints(gbc, 0, 1, GridBagConstraints.EAST);
		lName = new JLabel("Last Name: ");
		lName.setFont(plain);
		hsu.add(lName, gbc);
		
		setConstraints(gbc, 1, 1, GridBagConstraints.WEST);
		lName_input_hsu = new JTextField(20);
		lName_input_hsu.setFont(plain);
		hsu.add(lName_input_hsu, gbc);
				
		// Input for user's address
		setConstraints(gbc, 0, 2, GridBagConstraints.EAST);
		addA = new JLabel("House No.: ");
		addA.setFont(plain);
		hsu.add(addA, gbc);
		
		setConstraints(gbc, 1, 2, GridBagConstraints.WEST);
		add1_hsu = new JTextField(20);
		add1_hsu.setFont(plain);
		hsu.add(add1_hsu, gbc);
		
		setConstraints(gbc, 0, 3, GridBagConstraints.EAST);
		addB = new JLabel("Street Name: ");
		addB.setFont(plain);
		hsu.add(addB, gbc);
		
		setConstraints(gbc, 1, 3, GridBagConstraints.WEST);
		add2_hsu = new JTextField(20);
		add2_hsu.setFont(plain);
		hsu.add(add2_hsu, gbc);
		
		setConstraints(gbc, 0, 4, GridBagConstraints.EAST);
		addC = new JLabel("Postcode: ");
		addC.setFont(plain);
		hsu.add(addC, gbc);
		
		setConstraints(gbc, 1, 4, GridBagConstraints.WEST);
		add3_hsu = new JTextField(20);
		add3_hsu.setFont(plain);
		hsu.add(add3_hsu, gbc);
		
		setConstraints(gbc, 0, 5, GridBagConstraints.EAST);
		addD = new JLabel("Place Name: ");
		addD.setFont(plain);
		hsu.add(addD, gbc);
		
		setConstraints(gbc, 1, 5, GridBagConstraints.WEST);
		add4_hsu = new JTextField(20);
		add4_hsu.setFont(plain);
		hsu.add(add4_hsu, gbc);
		
		// Input for phone number
		setConstraints(gbc, 0, 6, GridBagConstraints.EAST);
		phone = new JLabel("Phone No.: ");
		phone.setFont(plain);
		hsu.add(phone, gbc);
		
		setConstraints(gbc, 1, 6, GridBagConstraints.WEST);
		phone_input_hsu = new JTextField(20);
		phone_input_hsu.setFont(plain);
		hsu.add(phone_input_hsu, gbc);
		
		// Input for email
		setConstraints(gbc, 0, 7, GridBagConstraints.EAST);
		id = new JLabel("Email: ");
		id.setFont(plain);
		hsu.add(id, gbc);
		
		setConstraints(gbc, 1, 7, GridBagConstraints.WEST);
		id_input_hsu = new JTextField(20);
		id_input_hsu.setFont(plain);
		hsu.add(id_input_hsu, gbc);
			
		// Input for password
		setConstraints(gbc, 0, 8, GridBagConstraints.EAST);
		pw = new JLabel("Password: ");
		pw.setFont(plain);
		hsu.add(pw, gbc);
		
		setConstraints(gbc, 1, 8, GridBagConstraints.WEST);
		pw_input_hsu = new JPasswordField(20);
		pw_input_hsu.setFont(plain);
		pw_input_hsu.setEchoChar('*');
		pw_input_hsu.getDocument().addDocumentListener(this);
		hsu.add(pw_input_hsu, gbc);
			
		// Input for confirm password
		setConstraints(gbc, 0, 9, GridBagConstraints.EAST);
		confirm_pw = new JLabel("Confirm Password: ");
		confirm_pw.setFont(plain);
		hsu.add(confirm_pw, gbc);
		
		setConstraints(gbc, 1, 9, GridBagConstraints.WEST);
		confirm_input_hsu = new JPasswordField(20);
		confirm_input_hsu.setFont(plain);
		confirm_input_hsu.setEchoChar('*');
		confirm_input_hsu.getDocument().addDocumentListener(this);
		hsu.add(confirm_input_hsu, gbc);
		
		setConstraints(gbc, 1, 10, GridBagConstraints.WEST);
		hsuBtn = new JButton("Sign up");
		hsuBtn.setFont(plain);
		hsuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fName = fName_input_hsu.getText();
				String lName = lName_input_hsu.getText();
				String houseName = add1_hsu.getText();
				String streetName = add2_hsu.getText();
				String placeName = add3_hsu.getText();
				String postcode = add4_hsu.getText();
				String phone = phone_input_hsu.getText();
				String userID = id_input_hsu.getText();
				String password = pw_input_hsu.getText();
				
				if (fName.isEmpty() || lName.isEmpty() || houseName.isEmpty() || streetName.isEmpty() || placeName.isEmpty() || fName.isEmpty() || postcode.isEmpty() || phone.isEmpty() || userID.isEmpty() || password.isEmpty()) {
					showMessageDialog(null, "Please fill in all blanks.");
				}
				else {
					Address add = new Address(houseName, streetName, placeName, postcode);
					Host host = new Host(fName, lName, add, phone, userID, password); // TODO change when authentication is available
					
					System.out.println(host);
					
					if (!(host.getSuccess())) {
						showMessageDialog(null, "Sign up failed.");
					}
				}
			}
		});
		hsu.add(hsuBtn, gbc);
		
		
		hp.add(hsu, g);
		setConstraints(g, 0, 1, GridBagConstraints.CENTER);
		hp.add(createHomeBtnPanel(), g);
		
		return hp;
	}
	
	public JPanel guestLogin() {
		JPanel gl = new JPanel();
		JPanel hp = new JPanel();
		hp.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		
		gl.setBorder(createTitledBorder("Login as Guest"));
		gl.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel id, pw;
		JPanel buttons, hp1, hp2, hp3, hp4;
		
		setConstraints(gbc, 0, 0, GridBagConstraints.EAST);
		id = new JLabel("User ID: ");
		id.setFont(plain);
		gl.add(id, gbc);
		
		setConstraints(gbc, 1, 0, GridBagConstraints.WEST);
		id_input_gl = new JTextField(20);
		id_input_gl.setFont(plain);
		id_input_gl.getDocument().addDocumentListener(this);
		gl.add(id_input_gl);
		
		setConstraints(gbc, 0, 1, GridBagConstraints.EAST);
		pw = new JLabel("Password: ");
		pw.setFont(plain);
		gl.add(pw, gbc);
		
		setConstraints(gbc, 1, 1, GridBagConstraints.WEST);
		pw_input_gl = new JPasswordField(20);
		pw_input_gl.setFont(plain);
		pw_input_gl.setEchoChar('*');
		gl.add(pw_input_gl, gbc);
		
		setConstraints(gbc, 1, 2, GridBagConstraints.WEST);
		glBtn = new JButton("Log in");
		glBtn.setFont(plain);
		glBtn.addActionListener(this); 	
		gl.add(glBtn, gbc);
		
		setConstraints(gbc, 1, 3, GridBagConstraints.WEST);
		toGSU = new JButton("Don't have an account? Sign up!");
		toGSU.setContentAreaFilled(false);
		toGSU.setBorderPainted(false);
		toGSU.setFont(plain);
		toGSU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.show(c, "Guest Sign Up");
				current = "GSU";
				setTitle("Guest Sign Up");
			}
		});
		gl.add(toGSU, gbc);
		
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
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		
		hl.setBorder(createTitledBorder("Login as Host"));
		hl.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		
		JLabel login, id, pw;
		JPanel buttons, hp1, hp2, hp3, hp4;
		
		setConstraints(gbc, 0, 0, GridBagConstraints.EAST);
		id = new JLabel("User ID: ");
		id.setFont(plain);
		hl.add(id, gbc);
		
		setConstraints(gbc, 1, 0, GridBagConstraints.WEST);
		id_input_hl = new JTextField(20);
		id_input_hl.setFont(plain);
		id_input_hl.getDocument().addDocumentListener(this);
		hl.add(id_input_hl, gbc);
		
		setConstraints(gbc, 0, 2, GridBagConstraints.EAST);
		pw = new JLabel("Password: ");
		pw.setFont(plain);
		hl.add(pw, gbc);
		
		setConstraints(gbc, 1, 2, GridBagConstraints.WEST);
		pw_input_hl = new JPasswordField(20);
		pw_input_hl.setFont(plain);
		pw_input_hl.setEchoChar('*');
		hl.add(pw_input_hl, gbc);
		
		setConstraints(gbc, 1, 3, GridBagConstraints.WEST);
		hlBtn = new JButton("Log in");
		hlBtn.setFont(plain);
		hlBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.show(c, "Host Home");
			}
		}); // TODO change
		hl.add(hlBtn, gbc);
		
		setConstraints(gbc, 1, 4, GridBagConstraints.WEST);
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
		hl.add(toHSU, gbc);
		
		hp.add(hl, g);
		setConstraints(g, 0, 1, GridBagConstraints.CENTER);
		hp.add(createHomeBtnPanel(), g);

		return hp;
	}
	
	public JPanel inquiry() {
		JPanel i = new JPanel();
		JPanel searchBar = new JPanel();
		JPanel searchResult = new JPanel();
		
		searchProperty = new JTextField(20);
		searchProperty.setFont(plain);
		search = new JButton("Seach Area");
		search.setFont(plain);
		
		searchBar.add(searchProperty);
		searchBar.add(search);
		//searchBar.add(createHomeBtn());
		
		// TODO add search results into the searchResult panel
		
		i.setLayout(new BorderLayout());
		i.add(searchBar, BorderLayout.CENTER);
		i.add(createHomeBtnPanel(), BorderLayout.SOUTH);
		
		return i;
	}
	
	public JPanel hostHome() {
		JPanel hh = new JPanel();
		JPanel hp1, hp2, properties, createProperty, p1;
		JLabel title;
		hh.setLayout(new BorderLayout());
		JButton newProperty;
		
		JTabbedPane hostTabs = new JTabbedPane();
		hostTabs.setFont(plain);
		
		JPanel myProperties = new JPanel();
		CardLayout c = new CardLayout();
		myProperties.setLayout(c);
		
		p1 = new JPanel();
		newProperty = new JButton("Create New Property");
		newProperty.setFont(plain);
		p1.add(newProperty);
		
		JPanel pBookings = new JPanel();
		JPanel myAccount = myAccount();
		JPanel propertiesList = new JPanel();
		//propertiesList.setBorder(BorderFactory.createLineBorder(Color.black));
		
		myProperties.add("All Properties", p1);
		myProperties.add("New", newProperty());
		
		newProperty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.show(myProperties, "New");
			}
		});
		
		hp1 = new JPanel();
		hp2 = new JPanel();
		properties = new JPanel();
		createProperty = new JPanel();
		
		hostTabs.add("My Properties", myProperties); // shows list of properties, and a button to create a new property
		hostTabs.add("Provisional Bookings", pBookings);
		hostTabs.add("My Account", myAccount);
		
		hh.add(hostTabs, BorderLayout.CENTER);
		
		return hh;
	}

	public JPanel newProperty() {
		JPanel np = new JPanel();
		JLabel title, name, description, houseNo, stNo, postcode, place, bfast;
		JTextField shortName, add1, add2, add3, add4;
		JTextArea desc;
		JButton create;
		
		title = new JLabel("Create New Property");
		title.setFont(plain);
		JPanel hp = new JPanel();
		hp.add(title);
		
		name = new JLabel("Property name: ");
		name.setFont(plain);
		shortName = new JTextField(20);
		shortName.setFont(plain);
		JPanel hp1 = new JPanel();
		hp1.add(name);
		hp1.add(shortName);
		
		description = new JLabel("Description: ");
		description.setFont(plain);
		desc = new JTextArea(5, 20);
		desc.setFont(plain);
		JPanel hp2 = new JPanel();
		hp2.add(description);
		hp2.add(desc);
		
		houseNo = new JLabel("House Number: ");
		houseNo.setFont(plain);
		add1 = new JTextField(20);
		add1.setFont(plain);
		JPanel hp3 = new JPanel();
		hp3.add(houseNo);
		hp3.add(add1);
		stNo = new JLabel("Street Name: ");
		stNo.setFont(plain);
		add2 = new JTextField(20);
		add2.setFont(plain);
		JPanel hp4 = new JPanel();
		hp4.add(stNo);
		hp4.add(add2);
		postcode = new JLabel("Postcode: ");
		postcode.setFont(plain);
		add3 = new JTextField(20);
		add3.setFont(plain);
		JPanel hp5 = new JPanel();
		hp5.add(postcode);
		hp5.add(add3);
		place = new JLabel("Area: ");
		place.setFont(plain);
		add4 = new JTextField(20);
		add4.setFont(plain);
		JPanel hp6 = new JPanel();
		hp6.add(place);
		hp6.add(add4);
		
		bfast = new JLabel("Is breakfast served?");	
		bfast.setFont(plain);
		ButtonGroup grp = new ButtonGroup();
		JRadioButton yes_bfast = new JRadioButton("Yes");
		yes_bfast.setActionCommand("Yes");
		yes_bfast.setFont(plain);
		JRadioButton no_bfast = new JRadioButton("No");
		no_bfast.setActionCommand("No");
		no_bfast.setFont(plain);
		grp.add(yes_bfast);
		grp.add(no_bfast);
		JPanel hp7 = new JPanel();
		hp7.add(bfast);
		hp7.add(yes_bfast);
		hp7.add(no_bfast);
		
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
				String place = add4.getText();
				boolean breakfast = false;
				
				if (grp.getSelection().getActionCommand() == "Yes") {
					breakfast = true;
				}
				else if (grp.getSelection().getActionCommand() == "No") {
					breakfast = false;
				}
				
				boolean notAllFilled = sName.isEmpty() || descr.isEmpty() || houseNo.isEmpty() || street.isEmpty() || postcode.isEmpty() || place.isEmpty();
				
				if (notAllFilled) {
					showMessageDialog(null, "All fields are mandatory.");
				}
				else {
					Address propAddress = new Address(houseNo, street, postcode, place);
					Host testHost = new Host("May", "Brian", propAddress, "000", "redspecial@gmail.com", "password123");
					Property property = new Property(sName, descr, testHost, place, propAddress, breakfast);
					System.out.println(property);
				}
			}
		});
		JPanel hp8 = new JPanel();
		hp8.add(create);
		
		BoxLayout b = new BoxLayout(np, BoxLayout.Y_AXIS);
		np.setLayout(b);
		
		np.add(hp);
		np.add(hp1);
		np.add(hp2);
		np.add(hp3);
		np.add(hp4);
		np.add(hp5);
		np.add(hp6);
		np.add(hp7);
		np.add(hp8);
		
		return np;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public JPanel myAccount() {
		JPanel ma = new JPanel();
				
		JButton editInfo, logOut, changePassword, myRating; //TODO to be added
		JPanel buttons = new JPanel();
		BoxLayout b = new BoxLayout(buttons, BoxLayout.Y_AXIS);
		buttons.setLayout(b);
		
		editInfo = new JButton("Account Info");
		editInfo.setFont(plain);
		
		myRating = new JButton("My Ratings");
		myRating.setFont(plain);
		
		changePassword = new JButton("Change Password");
		changePassword.setFont(plain);
		
		logOut = new JButton("Log Out");
		logOut.setFont(plain);
		
		buttons.add(editInfo, BorderLayout.NORTH);
		buttons.add(myRating, BorderLayout.WEST);
		buttons.add(changePassword, BorderLayout.EAST);
		buttons.add(logOut, BorderLayout.SOUTH);
		
		ma.add(buttons);
		
		return ma;
	}
	
	public static void main (String [] args) {
		new HomeBreaks();
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
		String password = pw_input_gsu.getText();
		String repassword = confirm_input_gsu.getText();
		
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
		String password = pw_input_hsu.getText();
		String repassword = confirm_input_hsu.getText();
		
	    if (!password.equals(repassword)) {
	    	warning_hsu.setFont(new Font("Verdana", Font.PLAIN, 20));
	    	warning_hsu.setForeground(Color.RED);
			warning_hsu.setText("Passwords do not match!");
			hsuBtn.setEnabled(false);
			
		}
	    else {
	    	warning_gsu.setText("");
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
}
