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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
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


public class HomeBreaks extends JFrame implements ActionListener, DocumentListener {
	CardLayout cards, myAccountCards;
	Container c = getContentPane();
	String current = "";
	Popup k;
	JPanel home, guestLogin, hostLogin, myAccount, searchResult, viewProperties;
	JButton toHome, host, enquirer, guest, gsuBtn, hsuBtn, glBtn, hlBtn, toGSU, toHSU, search, homeBtn, viewMoreBtn;
	JTextField fName_input_gsu, lName_input_gsu, add1_gsu, add2_gsu, add3_gsu, add4_gsu, phone_input_gsu, id_input_gsu, id_input_gl;
	JTextField fName_input_hsu, lName_input_hsu, add1_hsu, add2_hsu, add3_hsu, add4_hsu, phone_input_hsu, id_input_hsu, id_input_hl;
	JTextField searchProperty;
	JLabel warning_gsu = new JLabel("");
	JLabel warning_hsu = new JLabel("");
	JPasswordField pw_input_gsu, confirm_input_gsu, pw_input_gl;
	JPasswordField pw_input_hsu, confirm_input_hsu, pw_input_hl;
	public static Host currentHost;
	public static Guest currentGuest;
	
	Map<Integer, Property> properties;
	String cityFilter = "Sheffield";
	Property chosenHouse;
	Kitchen kitchen;
	
	final Font plain = new Font("Verdana", Font.PLAIN, 25);
	//final Font smaller = new Font("Verdana", Font.PLAIN, 20);
	final Font bold = new Font("Verdana", Font.BOLD, 50);
	
	public HomeBreaks() {
		System.out.println("Loading...");
		TDatabase.Initialise();
		properties = TDatabase.Properties;
		chosenHouse = properties.get(25);
		kitchen = chosenHouse.getKitchen();
		startGUI();
		System.out.println("Successfully loaded.");
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
		c.add("Inquiry", enquirer());
		c.add("Host Home", hostHome());
		c.add("Guest Home", guestHome());
		c.add("House View", houseView());
		c.add("View Properties", viewProperties());
		
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
				String city = add3_gsu.getText();
				String postcode = add4_gsu.getText();
				String phone = phone_input_gsu.getText();
				String email = id_input_gsu.getText();
				String password = pw_input_gsu.getText();
				
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
				String city = add3_hsu.getText();
				String postcode = add4_hsu.getText();
				String phone = phone_input_hsu.getText();
				String email = id_input_hsu.getText();
				String password = pw_input_hsu.getText();
				
				if (fName.isEmpty() || lName.isEmpty() || houseName.isEmpty() || streetName.isEmpty() || city.isEmpty() || fName.isEmpty() || postcode.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty()) {
					showMessageDialog(null, "Please fill in all blanks.");
				}
				else {
					Address add = new Address(houseName, streetName, postcode, city, true);
					Host host = new Host(fName, lName, add, phone, email, password); // TODO change when authentication is available

					
					if (!(host.getSuccess())) {
						showMessageDialog(null, "Sign up failed.");
					}
					else
					{
						TDatabase.Hosts.put(Integer.parseInt(host.getID()), host);
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
		glBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = id_input_gl.getText();
				String pw = String.valueOf(pw_input_gl.getPassword());
				
				if (TDatabase.GuestLogin(email, pw)) {
					setTitle("Guest Home");
					cards.show(c, "Guest Home");
				}
				else {
					showMessageDialog(null, "Wrong credentials!");
				}
			}
		}); 	
		gl.add(glBtn, gbc);
		
		setConstraints(gbc, 1, 3, GridBagConstraints.WEST);
		toGSU = new JButton("Don't have an account? Sign up!");
		toGSU.setContentAreaFilled(false);
		toGSU.setBorderPainted(false);
		toGSU.setFont(plain);
		toGSU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.show(c, "Guest Sign Up");
				
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
				String email = id_input_hl.getText();
				String pw = String.valueOf(pw_input_hl.getPassword());
				String hostID;
				
				if (TDatabase.HostLogin(email, pw)) {
					hostID=TDatabase.SearchUserID("Host", email);
					HomeBreaks.currentHost = TDatabase.Hosts.get(Integer.parseInt(hostID));
					setTitle("Host Home");
					cards.show(c, "Host Home");
				}
				else {
					showMessageDialog(null, "Wrong credentials!");
				}
			}
		});
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
		// This panel is 
		JPanel i = new JPanel();
		JPanel hp = new JPanel();
		JPanel x = new JPanel();
		
		// Use GridBagLayout on hp to create a two-grid container (i and x panels will be on grid (0,0) and (0,1) respectively)
		hp.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		
		// x will be the panel which shows the input panel i
		x.setBorder(createTitledBorder("Find properties"));
		// Use GBL on i (input for inquiry) to create a container with 5 grids (just one column)
		i.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JTextField startDD, endDD, startMM, endMM, startYY, endYY;
		setConstraints(gbc, 0, 0, GridBagConstraints.CENTER);		
		searchProperty = new JTextField("Area (i.e. Sheffield)", 20);
		searchProperty.setFont(plain);
		i.add(searchProperty, gbc);
		
		// Start adding components to the i panel grids
		setConstraints(gbc, 0, 1, GridBagConstraints.CENTER);
		JLabel start = new JLabel("Start date: ");
		start.setFont(plain);
		i.add(start, gbc);
		
		// A new panel to arrange the date inputs in
		// Use GBL as well to create just one row with five columns
		JPanel startDate = new JPanel();
		startDate.setLayout(new GridBagLayout());
		GridBagConstraints g2 = new GridBagConstraints();
		
		// Start adding components to startDate panel grids
		setConstraints(g2, 0, 0, GridBagConstraints.WEST);
		startDD = new JTextField("DD", 2);
		startDD.setFont(plain);
		startDate.add(startDD, g2);
		setConstraints(g2, 1, 0, GridBagConstraints.WEST);
		JLabel d1 = new JLabel("-");
		d1.setFont(plain);
		startDate.add(d1, g2);
		setConstraints(g2, 2, 0, GridBagConstraints.WEST);
		startMM = new JTextField("MM", 2);
		startMM.setFont(plain);
		startDate.add(startMM, g2);
		setConstraints(g2, 3, 0, GridBagConstraints.WEST);
		JLabel d2 = new JLabel("-");
		d2.setFont(plain);
		startDate.add(d2, g2);
		setConstraints(g2, 4, 0, GridBagConstraints.WEST);
		startYY = new JTextField("YYYY", 4);
		startYY.setFont(plain);
		startDate.add(startYY, g2);
		// Add the startDate panel to the first row, column 2 of i
		setConstraints(gbc, 0, 2, GridBagConstraints.CENTER);
		i.add(startDate, gbc);
		// Add a label for the end date, same as for the start date
		setConstraints(gbc, 0, 3, GridBagConstraints.CENTER);
		JLabel end = new JLabel("End date: ");
		end.setFont(plain);
		i.add(end, gbc);
		
		// New panel for end date inputs (same as before with start date)
		JPanel endDate = new JPanel();
		endDate.setLayout(new GridBagLayout());
		GridBagConstraints g3 = new GridBagConstraints();
		
		// Add input for end date to panel startDate
		setConstraints(g3, 0, 0, GridBagConstraints.WEST);
		endDD = new JTextField("DD", 2);
		endDD.setFont(plain);
		endDate.add(endDD, g3);
		setConstraints(g3, 1, 0, GridBagConstraints.WEST);
		JLabel d3 = new JLabel("-");
		d3.setFont(plain);
		endDate.add(d3, g3);
		setConstraints(g3, 2, 0, GridBagConstraints.WEST);
		endMM = new JTextField("MM", 2);
		endMM.setFont(plain);
		endDate.add(endMM, g3);
		setConstraints(g3, 3, 0, GridBagConstraints.WEST);
		JLabel d4 = new JLabel("-");
		d4.setFont(plain);
		endDate.add(d4, g3);
		setConstraints(g3, 4, 0, GridBagConstraints.WEST);
		endYY = new JTextField("YYYY", 4);
		endYY.setFont(plain);
		endDate.add(endYY, g3);
		
		// add endDate to the fourth row of i
		setConstraints(gbc, 0, 4, GridBagConstraints.CENTER);
		i.add(endDate, gbc);
		
		// add the search button to the fifth row of i
		JPanel btn = new JPanel();
		setConstraints(gbc, 0, 5, GridBagConstraints.CENTER);
		btn.setBorder(new EmptyBorder(50, 0, 0, 0));
		search = new JButton("Seach Area");
		search.setFont(plain);
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.show(c, "View Properties");
				current = "VP";
				setTitle("View Properties");
			}
		});
		btn.add(search);
		i.add(btn, gbc);
		
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
		myAccount = new JPanel();
		myAccountCards = new CardLayout();
		myAccount.setLayout(myAccountCards);
		myAccount.add("My Account", myAccount());
		myAccount.add("Edit Info", HBPanels.changeInfoPanel());
		JPanel propertiesList = new JPanel();
		
		myProperties.add("All Properties", p1);
		myProperties.add("New", HBPanels.newPropertyPanel());
		
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
	
	public JPanel guestHome() {
		JPanel gh = new JPanel();
		JPanel p1;
		
		gh.setLayout(new BorderLayout());
		JButton newProperty;
		
		JTabbedPane guestTabs = new JTabbedPane();
		guestTabs.setFont(plain);
		
		JPanel myProperties = new JPanel();
		CardLayout c = new CardLayout();
		myProperties.setLayout(c);
		
		p1 = new JPanel();
		newProperty = new JButton("Create New Property");
		newProperty.setFont(plain);
		p1.add(newProperty);
		
		JPanel pBookings = new JPanel();
		pBookings.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		
		myAccount = new JPanel();
		myAccountCards = new CardLayout();
		myAccount.setLayout(myAccountCards);
		myAccount.add("My Account", myAccount());
		myAccount.add("Edit Info", HBPanels.changeInfoPanel());
		JPanel propertiesList = new JPanel();
		
		myProperties.add("All Properties", p1);
		myProperties.add("New", HBPanels.newPropertyPanel());
		
		newProperty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.show(myProperties, "New");
			}
		});
		
		guestTabs.add("Provisional Bookings", pBookings);
		guestTabs.add("Book a property", inquiry());
		guestTabs.add("My Account", myAccount);
		
		gh.add(guestTabs, BorderLayout.CENTER);
		
		return gh;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
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
	
	public JScrollPane viewProperties() {
		JPanel vp = new JPanel();
		current = "View Properties";
		
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
		
		Map<Integer, Property> filterProperties = new HashMap<Integer, Property>();
		
		for (Property house : properties.values())  {
			Address address = house.getFullAddress();
			String city = address.getCity();
			if (city == cityFilter){
				filterProperties.put(house.getID(), house);
			}
		}
		
		for (Property chosenHouse : properties.values()) {
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
			numbaths = new JLabel("Number of Bathrooms: " + chosenHouse.getBathroom());
			numbaths.setFont(plain);
			numbeds = new JLabel("Number of Bedrooms: " + chosenHouse.getBedroom());
			numbeds.setFont(plain);
			
			
			viewMoreBtn = new JButton("View More");
			viewMoreBtn.setFont(plain);
			viewMoreBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cards.show(c, "House View");
					current = "HV";
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
		
		return scroll;
	}
	
	public JPanel houseView() {
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
		shortName = new JLabel("Name: "+ chosenHouse.getShortName());
		shortName.setFont(bold);
		description = new JLabel("Description: " + chosenHouse.getDescription());
		description.setFont(plain);
		location = new JLabel("Location: "+ chosenHouse.getPublicLocation());
		location.setFont(plain);
		host = new JLabel("Host: " + chosenHouse.getHost());
		host.setFont(plain);
		breakfast = new JLabel("breakfast: " + chosenHouse.getBreakfast());
		breakfast.setFont(plain);
		
		JButton bedsBtn = new JButton("View Bedrooms");
		bedsBtn.setFont(plain);
		bedsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.show(c, "Beds");
				current = "BE";
				setTitle("Bedrooms");
			}
		});
		JButton bathBtn = new JButton("View Bathrooms");
		bathBtn.setFont(plain);
		bathBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.show(c, "Baths");
				current = "BA";
				setTitle("Bathrooms");
			}
		});
		JButton ktchnBtn = new JButton("View Kitchen");
		ktchnBtn.setFont(plain);
		ktchnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.show(c, "Kitchen");
				current = "KI";
				setTitle("Kitchen");
			}
		});
		JButton livBtn = new JButton("View Living Space");
		livBtn.setFont(plain);
		livBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.show(c, "Living");
				current = "LI";
				setTitle("Living Space");
			}
		});
		JButton utilBtn = new JButton("View Utilities");
		utilBtn.setFont(plain);
		utilBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.show(c, "Utility");
				current = "UT";
				setTitle("Utility");
			}
		});
		JButton outBtn = new JButton("View Outdoor Facility");
		outBtn.setFont(plain);
		outBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.show(c, "Outdoor");
				current = "OUT";
				setTitle("Outdoor Facility");
			}
		});
		
		sn = new JPanel();
		pl = new JPanel();
		d = new JPanel();
		h = new JPanel();
		bekfast = new JPanel();
		buttons = new JPanel();

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
		
		hv.add(shortName);
		hv.add(location);
		hv.add(description);
		hv.add(host);
		hv.add(breakfast);
		hv.add(buttons);
		
		return hv;
	}
	
	public JFrame viewKitchen(){
		String text = "";
		GridBagConstraints g = new GridBagConstraints();
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		
		GridBagConstraints gbc = new GridBagConstraints();
		final Font plain = new Font("Verdana", Font.PLAIN, 20);
		final Font bold = new Font("Verdana", Font.BOLD, 50);
		
		JFrame ki = new JFrame("pop");
		 
		// create a label
		JLabel l = new JLabel(kitchen.popUpText());

		ki.setSize(400, 400);

		PopupFactory pf = new PopupFactory();

		// create a panel
		JPanel p2 = new JPanel();

		p2.add(l);

		// create a pop-up
		k = pf.getPopup(ki, p2, 180, 100);

		// create a button
		JButton b = new JButton("close");

		// add action listener
		b.addActionListener(this);

		// create a panel
		JPanel p1 = new JPanel();

		p1.add(b);
		ki.add(p1);
		
		return ki;
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
		  return str.matches("\\d?");
	}
	
	public static void main (String [] args) {
		new HomeBreaks();
	}
}
