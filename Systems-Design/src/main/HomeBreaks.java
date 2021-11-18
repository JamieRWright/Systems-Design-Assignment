package main;


import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
		
		setVisible(true);
	}
	
	public JPanel home() {
		JPanel home = new JPanel();
		current = "Home";
		
        JPanel buttons, labels, hp;
		
		JLabel welc, opt;

		welc = new JLabel("Welcome");
		opt = new JLabel("To start, choose a role");
		welc.setFont(bold);
		opt.setFont(plain);
		EmptyBorder border = new EmptyBorder(30, 0, 0, 0);
		opt.setBorder(border);
		EmptyBorder border2 = new EmptyBorder(20, 40, 0, 0);
		welc.setBorder(border2);

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
		
		labels = new JPanel();
		buttons = new JPanel();
		hp = new JPanel();
		
		
		BoxLayout b2 = new BoxLayout(labels, BoxLayout.Y_AXIS);
		labels.setLayout(b2);
		
		host.setPreferredSize(new Dimension(400, 500));
		guest.setPreferredSize(new Dimension(400, 500));
		enquirer.setPreferredSize(new Dimension(400, 500));
		
		labels.add(welc);
		labels.add(opt);
		buttons.add(host);
		buttons.add(guest);
		buttons.add(enquirer);
		hp.add(labels);
		home.add(hp);
		home.add(buttons);
		
		return home;
	}
	
	public JPanel guestSU() {
		JPanel gsu = new JPanel();
		
		JLabel signUp, fName, lName, addA, addB, addC, addD, phone, id, pw, confirm_pw;
		JPanel buttons, hp1, hp2, hp3, hp4, hp5, hp6, hp7, hp8, hp9, hp10, hp11, hp12, hp13;
		
		signUp = new JLabel("Sign up as Guest");
		signUp.setFont(bold);
		
		// Input for user's name
		fName = new JLabel("First Name: ");
		fName.setFont(plain);
		
		fName_input_gsu = new JTextField(20);
		fName_input_gsu.setFont(plain);
		
		lName = new JLabel("Last Name: ");
		lName.setFont(plain);
		
		lName_input_gsu = new JTextField(20);
		lName_input_gsu.setFont(plain);
		
		// Input for user's address
		addA = new JLabel("House No.: ");
		addA.setFont(plain);
		add1_gsu = new JTextField(20);
		add1_gsu.setFont(plain);
		addB = new JLabel("Street Name: ");
		addB.setFont(plain);
		add2_gsu = new JTextField(20);
		add2_gsu.setFont(plain);
		addC = new JLabel("Postcode: ");
		addC.setFont(plain);
		add3_gsu = new JTextField(20);
		add3_gsu.setFont(plain);
		addD = new JLabel("Place Name: ");
		addD.setFont(plain);
		add4_gsu = new JTextField(20);
		add4_gsu.setFont(plain);
		
		// Input for phone number
		phone = new JLabel("Phone No.: ");
		phone.setFont(plain);
		phone_input_gsu = new JTextField(20);
		phone_input_gsu.setFont(plain);
		
		// Input for email
		id = new JLabel("Email: ");
		id.setFont(plain);
		id_input_gsu = new JTextField(20);
		id_input_gsu.setFont(plain);
		
		// Input for password
		pw = new JLabel("Password: ");
		pw.setFont(plain);
		pw_input_gsu = new JPasswordField(20);
		pw_input_gsu.setFont(plain);
		pw_input_gsu.setEchoChar('*');
		pw_input_gsu.getDocument().addDocumentListener(this);
		
		// Input for confirm password
		confirm_pw = new JLabel("Confirm Password: ");
		confirm_pw.setFont(plain);
		confirm_input_gsu = new JPasswordField(20);
		confirm_input_gsu.setFont(plain);
		confirm_input_gsu.setEchoChar('*');
		confirm_input_gsu.getDocument().addDocumentListener(this);
		
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
			
			
		hp1 = new JPanel();
		hp2 = new JPanel();
		hp3 = new JPanel();
		hp4 = new JPanel();
		hp5 = new JPanel();
		hp6 = new JPanel();
		hp7 = new JPanel();
		hp8 = new JPanel();
		hp9 = new JPanel();
		hp10 = new JPanel();
		hp11 = new JPanel();
		hp12 = new JPanel();
		hp13 = new JPanel();
		buttons = new JPanel();
		
		BoxLayout b = new BoxLayout(gsu, BoxLayout.Y_AXIS);
		gsu.setLayout(b);
		
		hp1.add(signUp);
		hp2.add(fName);
		hp2.add(fName_input_gsu);
		hp3.add(lName);
		hp3.add(lName_input_gsu);
		hp5.add(addA);
		hp5.add(add1_gsu);
		hp6.add(addB);
		hp6.add(add2_gsu);
		hp7.add(addC);
		hp7.add(add3_gsu);
		hp8.add(addD);
		hp8.add(add4_gsu);
		hp9.add(phone);
		hp9.add(phone_input_gsu);
		hp10.add(id);
		hp10.add(id_input_gsu);
		hp11.add(pw);
		hp11.add(pw_input_gsu);
		hp12.add(confirm_pw);
		hp12.add(confirm_input_gsu);
		hp13.add(warning_gsu);
		buttons.add(gsuBtn);
		
		gsu.add(hp1);
		gsu.add(hp2);
		gsu.add(hp3);
		gsu.add(hp5);
		gsu.add(hp6);
		gsu.add(hp7);
		gsu.add(hp8);
		gsu.add(hp9);
		gsu.add(hp10);
		gsu.add(hp11);
		gsu.add(hp12);
		gsu.add(hp13);
		gsu.add(buttons);
		gsu.add(hp4);
		gsu.add(createHomeBtnPanel());
		
		return gsu;
	}
	
	public JPanel hostSU() {
		JPanel hsu = new JPanel();
		
		JLabel signUp, fName, lName, addA, addB, addC, addD, phone, id, pw, confirm_pw;
		JPanel buttons, hp1, hp2, hp3, hp4, hp5, hp6, hp7, hp8, hp9, hp10, hp11, hp12, hp13;
		
		signUp = new JLabel("Sign up as Host");
		signUp.setFont(bold);
		
		// Input for user's name
		fName = new JLabel("First Name: ");
		fName.setFont(plain);
		
		fName_input_hsu = new JTextField(20);
		fName_input_hsu.setFont(plain);
			
		lName = new JLabel("Last Name: ");
		lName.setFont(plain);
				
		lName_input_hsu = new JTextField(20);
		lName_input_hsu.setFont(plain);
				
				// Input for user's address
		addA = new JLabel("House No.: ");
		addA.setFont(plain);
		add1_hsu = new JTextField(20);
		add1_hsu.setFont(plain);
		addB = new JLabel("Street Name: ");
		addB.setFont(plain);
		add2_hsu = new JTextField(20);
		add2_hsu.setFont(plain);
		addC = new JLabel("Postcode: ");
		addC.setFont(plain);
		add3_hsu = new JTextField(20);
		add3_hsu.setFont(plain);
		addD = new JLabel("Place Name: ");
		addD.setFont(plain);
		add4_hsu = new JTextField(20);
		add4_hsu.setFont(plain);
		
		// Input for phone number
		phone = new JLabel("Phone No.: ");
		phone.setFont(plain);
		phone_input_hsu = new JTextField(20);
		phone_input_hsu.setFont(plain);
		
		// Input for email
		id = new JLabel("Email: ");
		id.setFont(plain);
		id_input_hsu = new JTextField(20);
		id_input_hsu.setFont(plain);
			
		// Input for password
		pw = new JLabel("Password: ");
		pw.setFont(plain);
		pw_input_hsu = new JPasswordField(20);
		pw_input_hsu.setFont(plain);
		pw_input_hsu.setEchoChar('*');
		pw_input_hsu.getDocument().addDocumentListener(this);
			
		// Input for confirm password
		confirm_pw = new JLabel("Confirm Password: ");
		confirm_pw.setFont(plain);
		confirm_input_hsu = new JPasswordField(20);
		confirm_input_hsu.setFont(plain);
		confirm_input_hsu.setEchoChar('*');
		confirm_input_hsu.getDocument().addDocumentListener(this);
		
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
					Host host = new Host(fName, lName, add, phone, userID, password, new Rating());
					
					System.out.println(host);
					
					if (!(host.getSuccess())) {
						showMessageDialog(null, "Sign up failed.");
					}
				}
			}
		});
				
		hp1 = new JPanel();
		hp2 = new JPanel();
		hp3 = new JPanel();
		hp4 = new JPanel();
		hp5 = new JPanel();
		hp6 = new JPanel();
		hp7 = new JPanel();
		hp8 = new JPanel();
		hp9 = new JPanel();
		hp10 = new JPanel();
		hp11 = new JPanel();
		hp12 = new JPanel();
		hp13 = new JPanel();
		buttons = new JPanel();
			
		BoxLayout b = new BoxLayout(hsu, BoxLayout.Y_AXIS);
		hsu.setLayout(b);
			
		hp1.add(signUp);
		hp2.add(fName);
		hp2.add(fName_input_hsu);
		hp3.add(lName);
		hp3.add(lName_input_hsu);
		hp5.add(addA);
		hp5.add(add1_hsu);
		hp6.add(addB);
		hp6.add(add2_hsu);
		hp7.add(addC);
		hp7.add(add3_hsu);
		hp8.add(addD);
		hp8.add(add4_hsu);
		hp9.add(phone);
		hp9.add(phone_input_hsu);
		hp10.add(id);
		hp10.add(id_input_hsu);
		hp11.add(pw);
		hp11.add(pw_input_hsu);
		hp12.add(confirm_pw);
		hp12.add(confirm_input_hsu);
		hp13.add(warning_hsu);
		buttons.add(hsuBtn);
			
		
		hsu.add(hp1);
		hsu.add(hp2);
		hsu.add(hp3);
		hsu.add(hp5);
		hsu.add(hp6);
		hsu.add(hp7);
		hsu.add(hp8);
		hsu.add(hp9);
		hsu.add(hp10);
		hsu.add(hp11);
		hsu.add(hp12);
		hsu.add(hp13);
		hsu.add(buttons);
		hsu.add(hp4);
		hsu.add(createHomeBtnPanel());
		
		return hsu;
	}
	
	public JPanel guestLogin() {
		JPanel gl = new JPanel();
	
		JLabel login, id, pw;
		JPanel buttons, hp1, hp2, hp3, hp4;
		
		login = new JLabel("Login as Guest");
		login.setFont(bold);
		
		id = new JLabel("User ID: ");
		id.setFont(plain);
		
		id_input_gl = new JTextField(20);
		id_input_gl.setFont(plain);
		id_input_gl.getDocument().addDocumentListener(this);
		
		pw = new JLabel("Password: ");
		pw.setFont(plain);
		
		pw_input_gl = new JPasswordField(20);
		pw_input_gl.setFont(plain);
		pw_input_gl.setEchoChar('*');
		
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
		
		glBtn = new JButton("Log in");
		glBtn.setFont(plain);
		glBtn.addActionListener(this); 
		
		hp1 = new JPanel();
		hp2 = new JPanel();
		hp3 = new JPanel();
		hp4 = new JPanel();
		buttons = new JPanel();
		
		BoxLayout b = new BoxLayout(gl, BoxLayout.Y_AXIS);
		gl.setLayout(b);
		
		hp1.add(login);
		hp2.add(id);
		hp2.add(id_input_gl);
		hp3.add(pw);
		hp3.add(pw_input_gl);
		hp4.add(toGSU);
		buttons.add(glBtn);
		
		gl.add(hp1);
		gl.add(hp2);
		gl.add(hp3);
		gl.add(buttons);
		gl.add(hp4);
		gl.add(createHomeBtnPanel());
		
		return gl;
	}
	
	public JPanel hostLogin() {
		JPanel hl = new JPanel();
		
		JLabel login, id, pw;
		JPanel buttons, hp1, hp2, hp3, hp4;
		
		login = new JLabel("Login as Host");
		login.setFont(bold);
		
		id = new JLabel("User ID: ");
		id.setFont(plain);
		
		id_input_hl = new JTextField(20);
		id_input_hl.setFont(plain);
		id_input_hl.getDocument().addDocumentListener(this);
		
		pw = new JLabel("Password: ");
		pw.setFont(plain);
		
		pw_input_hl = new JPasswordField(20);
		pw_input_hl.setFont(plain);
		pw_input_hl.setEchoChar('*');
		
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
		
		hlBtn = new JButton("Log in");
		hlBtn.setFont(plain);
		hlBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.show(c, "Host Home");
			}
		}); // TODO change
		
		hp1 = new JPanel();
		hp2 = new JPanel();
		hp3 = new JPanel();
		hp4 = new JPanel();
		buttons = new JPanel();
		
		BoxLayout b = new BoxLayout(hl, BoxLayout.Y_AXIS);
		hl.setLayout(b);
		
		hp1.add(login);
		hp2.add(id);
		hp2.add(id_input_hl);
		hp3.add(pw);
		hp3.add(pw_input_hl);
		hp4.add(toHSU);
		buttons.add(hlBtn);
		
		hl.add(hp1);
		hl.add(hp2);
		hl.add(hp3);
		hl.add(buttons);
		hl.add(hp4);
		hl.add(createHomeBtnPanel());
		
		return hl;
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
		
		JPanel myProperties = new JPanel();
		CardLayout c = new CardLayout();
		myProperties.setLayout(c);
		
		p1 = new JPanel();
		newProperty = new JButton("Create New Property");
		p1.add(newProperty);
		
		JPanel pBookings = new JPanel();
		JPanel myAccount = new JPanel();
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
				boolean breakfast;
				String isSelected = "";
				
				if (grp.getSelection().getActionCommand() == "Yes") {
					breakfast = true;
				}
				else if (grp.getSelection().getActionCommand() == "No") {
					breakfast = false;
				}
				else {
					isSelected = "No";
				}
				
				boolean notAllFilled = sName.isEmpty() || descr.isEmpty() || houseNo.isEmpty() || street.isEmpty() || postcode.isEmpty() || place.isEmpty();
				boolean notSelected = isSelected == "No";
				
				if (notAllFilled && notSelected) {
					showMessageDialog(null, "All fields are mandatory.");
				}
				else {
					//create property
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
}