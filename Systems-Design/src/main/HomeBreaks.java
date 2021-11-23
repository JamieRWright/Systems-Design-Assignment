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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class HomeBreaks extends JFrame implements ActionListener, DocumentListener {
	CardLayout cards, myAccountCards;
	Container c = getContentPane();
	String current = "";
	JPanel home, guestLogin, hostLogin, myAccount, searchResult, viewProperties;
	JButton toHome, host, enquirer, guest, gsuBtn, hsuBtn, glBtn, hlBtn, toGSU, toHSU, search, homeBtn, viewMoreBtn;
	JTextField fName_input_gsu, lName_input_gsu, add1_gsu, add2_gsu, add3_gsu, add4_gsu, phone_input_gsu, id_input_gsu, id_input_gl;
	JTextField fName_input_hsu, lName_input_hsu, add1_hsu, add2_hsu, add3_hsu, add4_hsu, phone_input_hsu, id_input_hsu, id_input_hl;
	JTextField searchProperty;
	JLabel warning_gsu = new JLabel("");
	JLabel warning_hsu = new JLabel("");
	JPasswordField pw_input_gsu, confirm_input_gsu, pw_input_gl;
	JPasswordField pw_input_hsu, confirm_input_hsu, pw_input_hl;
	Host currentHost;
	Guest currentGuest;
	
	final Font plain = new Font("Verdana", Font.PLAIN, 25);
	//final Font smaller = new Font("Verdana", Font.PLAIN, 20);
	final Font bold = new Font("Verdana", Font.BOLD, 50);
	
	public HomeBreaks() {
		TDatabase.Initialise();
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
		c.add("Inquiry", enquirer());
		c.add("Host Home", hostHome());
		c.add("Guest Home", guestHome());
		c.add("House View", houseView());
		
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
					TDatabase.Guests.add(guest);
					
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
					TDatabase.Hosts.add(host);
					
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
		glBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.show(c, "Guest Home");
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
		JPanel hp = new JPanel();
		JPanel x = new JPanel();
		
		hp.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		
		x.setBorder(createTitledBorder("Find properties"));
		i.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JTextField startDD, endDD, startMM, endMM, startYY, endYY;
		setConstraints(gbc, 0, 0, GridBagConstraints.CENTER);		
		searchProperty = new JTextField("Area (i.e. Sheffield)", 20);
		searchProperty.setFont(plain);
		i.add(searchProperty, gbc);
		
		setConstraints(gbc, 0, 1, GridBagConstraints.CENTER);
		JLabel start = new JLabel("Start date: ");
		start.setFont(plain);
		i.add(start, gbc);
		
		JPanel startDate = new JPanel();
		startDate.setLayout(new GridBagLayout());
		GridBagConstraints g2 = new GridBagConstraints();
		
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
		
		setConstraints(gbc, 0, 2, GridBagConstraints.CENTER);
		i.add(startDate, gbc);
		
		setConstraints(gbc, 0, 3, GridBagConstraints.CENTER);
		JLabel end = new JLabel("End date: ");
		end.setFont(plain);
		i.add(end, gbc);
		
		JPanel endDate = new JPanel();
		endDate.setLayout(new GridBagLayout());
		GridBagConstraints g3 = new GridBagConstraints();
		
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
		
		setConstraints(gbc, 0, 4, GridBagConstraints.CENTER);
		i.add(endDate, gbc);
		
		JPanel btn = new JPanel();
		setConstraints(gbc, 0, 5, GridBagConstraints.CENTER);
		btn.setBorder(new EmptyBorder(50, 0, 0, 0));
		search = new JButton("Seach Area");
		search.setFont(plain);
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn.add(search);
		i.add(btn, gbc);
		
		// TODO add search results into the searchResult panel
		i.setBorder(new EmptyBorder(0, 100, 0, 100));
		x.add(i);
		hp.add(x, g);
		setConstraints(g, 0, 2, GridBagConstraints.CENTER);
		hp.add(searchResultPanel(), g);
		
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
		myAccount.add("Edit Info", changeInfo());
		JPanel propertiesList = new JPanel();
		
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
		JPanel hp = new JPanel();
		hp.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		
		np.setBorder(createTitledBorder("New Property"));
		np.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel name, description, houseNo, stNo, postcode, place, bfast;
		JTextField shortName, add1, add2, add3, add4;
		JTextArea desc;
		JButton create;
		
		setConstraints(gbc, 0, 0, GridBagConstraints.EAST);
		name = new JLabel("Property name: ");
		name.setFont(plain);
		np.add(name, gbc);
		
		setConstraints(gbc, 1, 0, GridBagConstraints.WEST);
		shortName = new JTextField(20);
		shortName.setFont(plain);
		np.add(shortName, gbc);
		
		setConstraints(gbc, 0, 2, GridBagConstraints.EAST);
		description = new JLabel("Description: ");
		description.setFont(plain);
		np.add(description, gbc);
		
		setConstraints(gbc, 1, 2, GridBagConstraints.WEST);
		desc = new JTextArea(5, 20);
		JScrollPane scrollPane = new JScrollPane(desc);
		desc.setFont(plain);
		desc.setLineWrap(true);
		desc.setWrapStyleWord(true);
		np.add(scrollPane, gbc);
		
		setConstraints(gbc, 0, 3, GridBagConstraints.EAST);	
		houseNo = new JLabel("House Number: ");
		houseNo.setFont(plain);
		np.add(houseNo, gbc);
		
		setConstraints(gbc, 1, 3, GridBagConstraints.WEST);
		add1 = new JTextField(20);
		add1.setFont(plain);
		np.add(add1, gbc);
		
		setConstraints(gbc, 0, 4, GridBagConstraints.EAST);
		stNo = new JLabel("Street Name: ");
		stNo.setFont(plain);
		np.add(stNo, gbc);
		
		setConstraints(gbc, 1, 4, GridBagConstraints.WEST);
		add2 = new JTextField(20);
		add2.setFont(plain);
		np.add(add2, gbc);
		
		setConstraints(gbc, 0, 5, GridBagConstraints.EAST);
		postcode = new JLabel("Postcode: ");
		postcode.setFont(plain);
		np.add(postcode, gbc);
		
		setConstraints(gbc, 1, 5, GridBagConstraints.WEST);
		add3 = new JTextField(20);
		add3.setFont(plain);
		np.add(add3, gbc);
		
		setConstraints(gbc, 0, 6, GridBagConstraints.EAST);
		place = new JLabel("Area: ");
		place.setFont(plain);
		np.add(place, gbc);
		
		setConstraints(gbc, 1, 6, GridBagConstraints.WEST);
		add4 = new JTextField(20);
		add4.setFont(plain);
		np.add(add4, gbc);
		
		setConstraints(gbc, 0, 7, GridBagConstraints.EAST);
		bfast = new JLabel("Is breakfast served?");	
		bfast.setFont(plain);
		np.add(bfast, gbc);
		
		setConstraints(gbc, 1, 7, GridBagConstraints.WEST);
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
		np.add(breakfast, gbc);		
		
		setConstraints(gbc, 1, 8, GridBagConstraints.WEST);
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
				boolean bfast = false;
				boolean notAllFilled = sName.isEmpty() || descr.isEmpty() || houseNo.isEmpty() || street.isEmpty() || postcode.isEmpty() || place.isEmpty();
				
				if (yes_bfast.isSelected()) {
					bfast = true;
				}
				
				if (notAllFilled) {
					showMessageDialog(null, "All fields are mandatory.");
				}
				else {
					//Address propAddress = new Address(houseNo, street, postcode, place);
					//Host testHost = new Host("May", "Brian", propAddress, "000", "redspecial@gmail.com", "password123");
					TDatabase.Properties.add(new Property(1, houseNo, street, postcode, place, "England", sName, descr, true));
				}
			}
		});
		np.add(create, gbc);
		
		hp.add(np, g);
		
		return hp;
	}
	
	public JPanel changeInfo() {
		JPanel ci = new JPanel();
		
		JPanel hp = new JPanel();
		hp.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		
		ci.setBorder(createTitledBorder("My info"));
		ci.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel fName, lName, phone;
		JTextField f, l, p;
		
		setConstraints(gbc, 0, 0, GridBagConstraints.EAST);
		fName = new JLabel("First Name: ");
		fName.setFont(plain);
		ci.add(fName, gbc);
		
		setConstraints(gbc, 1, 0, GridBagConstraints.WEST);
		f = new JTextField(20); //TODO add host's name as default text
		f.setFont(plain);
		ci.add(f, gbc);
		
		setConstraints(gbc, 0, 1, GridBagConstraints.EAST);
		lName = new JLabel("Last Name: ");
		lName.setFont(plain);
		ci.add(lName, gbc);
		
		setConstraints(gbc, 1, 1, GridBagConstraints.WEST);
		l = new JTextField(20);
		l.setFont(plain);
		ci.add(l, gbc);
		
		setConstraints(gbc, 0, 2, GridBagConstraints.EAST);
		phone = new JLabel("Phone Number: ");
		phone.setFont(plain);
		ci.add(phone, gbc);
		
		setConstraints(gbc, 1, 2, GridBagConstraints.WEST);
		p = new JTextField(20);
		p.setFont(plain);
		ci.add(p, gbc);
		
		setConstraints(gbc, 1, 3, GridBagConstraints.WEST);
		JButton confirm = new JButton("Confirm");
		confirm.setFont(plain);
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fn = f.getText();
				String ln = f.getText();
				String pn = p.getText();
				
				if (fn.isEmpty() || ln.isEmpty() || pn.isEmpty()) {
					showMessageDialog(null, "All fields must not be blank.");
				}
				else {
					TDatabase.UpdateValue("Host", "firstName", "userID", fn);
					TDatabase.UpdateValue("Host", "lastName", "userID", ln);
					TDatabase.UpdateValue("Host", "Phone", "userID", pn);
				}
			}
		});
		ci.add(confirm, gbc);
		
		hp.add(ci, g);
		
		return hp;
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
		myAccount.add("Edit Info", changeInfo());
		JPanel propertiesList = new JPanel();
		
		myProperties.add("All Properties", p1);
		myProperties.add("New", newProperty());
		
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
	
	public JPanel searchResultPanel() {
		JPanel sr = new JPanel();
		JPanel hp = new JPanel();
		hp.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		sr.setBorder(createTitledBorder("Properties"));
		JPanel x = new JPanel();
		x.setBorder(new EmptyBorder(0, 70, 0, 70));
		
		// Default label
		JLabel dl = new JLabel("Start searching to see relevant properties...");
		dl.setFont(plain);
		x.add(dl);
		sr.add(x);
		
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		hp.add(sr, g);
		
		return hp;
	}
	
	public JPanel reviewPanel() {
		JPanel rp = new JPanel();
		JPanel hp = new JPanel();
		JPanel r = new JPanel();
		
		hp.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		
		rp.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		setConstraints(gbc, 0, 0, GridBagConstraints.EAST);
		
		r.setLayout(new GridBagLayout());
		GridBagConstraints gb = new GridBagConstraints();
		setConstraints(gb, 0, 0, GridBagConstraints.CENTER);
		
		JLabel c, c1, c2, c3, c4, c5, c6, desc;
		ButtonGroup overall, cleanliness, communication, checkin, accuracy, location, value;
		JRadioButton c_1, c_2, c_3, c_4, c_5;
		JRadioButton c1_1, c1_2, c1_3, c1_4, c1_5;
		JRadioButton c2_1, c2_2, c2_3, c2_4, c2_5;
		JRadioButton c3_1, c3_2, c3_3, c3_4, c3_5;
		JRadioButton c4_1, c4_2, c4_3, c4_4, c4_5;
		JRadioButton c5_1, c5_2, c5_3, c5_4, c5_5;
		JRadioButton c6_1, c6_2, c6_3, c6_4, c6_5;
		JPanel g1, g2, g3, g4, g5, g6, g7;
		JTextArea description;
		JButton submit;
		
		r.setBorder(createTitledBorder("Leave a review"));
		
		c = new JLabel("Overall: ");
		c.setFont(plain);
		rp.add(c, gbc);
		
		setConstraints(gbc, 1, 0, GridBagConstraints.WEST);
		overall = new ButtonGroup();
		c_1 = new JRadioButton("1");
		c_2 = new JRadioButton("2");
		c_3 = new JRadioButton("3");
		c_4 = new JRadioButton("4");
		c_5 = new JRadioButton("5");

		g1 = new JPanel();
		
		createRatingButtons(c_1, c_2, c_3, c_4, c_5, g1, overall);
		
		rp.add(g1, gbc);
		
		setConstraints(gbc, 0, 1, GridBagConstraints.EAST);
		c1 = new JLabel("Cleanliness: ");
		c1.setFont(plain);
		rp.add(c1, gbc);
		
		setConstraints(gbc, 1, 1, GridBagConstraints.WEST);
		cleanliness = new ButtonGroup();
		c1_1 = new JRadioButton("1");
		c1_2 = new JRadioButton("2");
		c1_3 = new JRadioButton("3");
		c1_4 = new JRadioButton("4");
		c1_5 = new JRadioButton("5");
		
		g2 = new JPanel();
		
		createRatingButtons(c1_1, c1_2, c1_3, c1_4, c1_5, g2, cleanliness);
		rp.add(g2, gbc);
		
		setConstraints(gbc, 0, 2, GridBagConstraints.EAST);
		c2 = new JLabel("Communication: ");
		c2.setFont(plain);
		rp.add(c2, gbc);
		
		setConstraints(gbc, 1, 2, GridBagConstraints.WEST);
		communication = new ButtonGroup();
		c2_1 = new JRadioButton("1");
		c2_2 = new JRadioButton("2");
		c2_3 = new JRadioButton("3");
		c2_4 = new JRadioButton("4");
		c2_5 = new JRadioButton("5");
		
		g3 = new JPanel();
		
		createRatingButtons(c2_1, c2_2, c2_3, c2_4, c2_5, g3, communication);
		rp.add(g3, gbc);
		
		setConstraints(gbc, 0, 3, GridBagConstraints.EAST);
		c3 = new JLabel("Check-in: ");
		c3.setFont(plain);
		rp.add(c3, gbc);
		
		setConstraints(gbc, 1, 3, GridBagConstraints.WEST);
		checkin = new ButtonGroup();
		c3_1 = new JRadioButton("1");
		c3_2 = new JRadioButton("2");
		c3_3 = new JRadioButton("3");
		c3_4 = new JRadioButton("4");
		c3_5 = new JRadioButton("5");
		
		g4 = new JPanel();
		
		createRatingButtons(c3_1, c3_2, c3_3, c3_4, c3_5, g4, checkin);
		rp.add(g4, gbc);
		
		setConstraints(gbc, 0, 4, GridBagConstraints.EAST);
		c4 = new JLabel("Accuracy: ");
		c4.setFont(plain);
		rp.add(c4, gbc);
		
		setConstraints(gbc, 1, 4, GridBagConstraints.WEST);
		accuracy = new ButtonGroup();
		c4_1 = new JRadioButton("1");
		c4_2 = new JRadioButton("2");
		c4_3 = new JRadioButton("3");
		c4_4 = new JRadioButton("4");
		c4_5 = new JRadioButton("5");
		
		g5 = new JPanel();
		
		createRatingButtons(c4_1, c4_2, c4_3, c4_4, c4_5, g5, accuracy);
		rp.add(g5, gbc);
		
		//
		setConstraints(gbc, 0, 5, GridBagConstraints.EAST);
		c5 = new JLabel("Location: ");
		c5.setFont(plain);
		rp.add(c5, gbc);
		
		setConstraints(gbc, 1, 5, GridBagConstraints.WEST);
		location = new ButtonGroup();
		c5_1 = new JRadioButton("1");
		c5_2 = new JRadioButton("2");
		c5_3 = new JRadioButton("3");
		c5_4 = new JRadioButton("4");
		c5_5 = new JRadioButton("5");
		
		g6 = new JPanel();
		
		createRatingButtons(c5_1, c5_2, c5_3, c5_4, c5_5, g6, location);
		rp.add(g6, gbc);
		
		//
		setConstraints(gbc, 0, 6, GridBagConstraints.EAST);
		c6 = new JLabel("Value: ");
		c6.setFont(plain);
		rp.add(c6, gbc);
		
		setConstraints(gbc, 1, 6, GridBagConstraints.WEST);
		value = new ButtonGroup();
		c6_1 = new JRadioButton("1");
		c6_2 = new JRadioButton("2");
		c6_3 = new JRadioButton("3");
		c6_4 = new JRadioButton("4");
		c6_5 = new JRadioButton("5");
		
		g7 = new JPanel();
		
		createRatingButtons(c6_1, c6_2, c6_3, c6_4, c6_5, g7, value);
		rp.add(g7, gbc);
		rp.setBorder(new EmptyBorder(0, 100, 0, 100));
		r.add(rp, gb);
		
		setConstraints(gb, 0, 1, GridBagConstraints.CENTER);
		desc = new JLabel("Describe your experience (optional): ");
		desc.setFont(plain);
		r.add(desc, gb);
		
		setConstraints(gb, 0, 2, GridBagConstraints.CENTER);
		description = new JTextArea(5, 20);
		JScrollPane scrollPane = new JScrollPane(description);
		description.setFont(plain);
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		JPanel space = new JPanel();
		space.setBorder(new EmptyBorder(10, 0, 0, 0));
		r.add(space, gb);
		setConstraints(gb, 0, 3, GridBagConstraints.CENTER);
		r.add(scrollPane, gb);
		
		setConstraints(gb, 0, 4, GridBagConstraints.CENTER);
		submit = new JButton("Submit Review");
		submit.setFont(plain);
		JPanel space2 = new JPanel();
		space2.setBorder(new EmptyBorder(10, 0, 0, 0));
		r.add(space2, gb);
		setConstraints(gb, 0, 5, GridBagConstraints.CENTER);
		r.add(submit, gb);
		
		hp.add(r, g);
		return hp;
	}
	
		public JPanel viewProperties() {
		JPanel vp = new JPanel();
		current = "viewProperties";
		
		vp.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		
		home.setBorder(createTitledBorder("View All Properties"));
		home.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel shortName, location, description;
		JPanel sn, l, d, buttons;

		setConstraints(gbc, 0, 0, GridBagConstraints.CENTER);
		
		final Font plain = new Font("Verdana", Font.PLAIN, 20);
		final Font bold = new Font("Verdana", Font.BOLD, 50);
		
		List<Property> properties = TDatabase.LoadProperties();
		
		for (int i = 0; i < properties.size(); i++) {
			Property house = properties.get(i);
			//house information
			shortName = new JLabel("Name: "+ house.getShortName());
			shortName.setFont(bold);
			location = new JLabel("Location: "+ house.getPublicLocation());
			location.setFont(plain);
			description = new JLabel("Description: ");
			description.setFont(plain);
			
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
			buttons = new JPanel();

			BoxLayout b = new BoxLayout(vp, BoxLayout.Y_AXIS);
			vp.setLayout(b);
			
			sn.add(shortName);
			l.add(location);
			d.add(description);
			buttons.add(viewMoreBtn);
			
			vp.add(shortName);
			vp.add(location);
			vp.add(description);
			vp.add(buttons);
		}
		
		return vp;
	}
	
	public JPanel houseView() {
		JPanel hv = new JPanel();
		hv.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		
		hv.setBorder(createTitledBorder("what a lovely property"));
		hv.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		return hv;
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
	
	public void createRatingButtons(JRadioButton a, JRadioButton b, JRadioButton c, JRadioButton d, JRadioButton e, JPanel p, ButtonGroup g) {
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
	
}
