import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GuestSignUp implements ActionListener {
    
	JFrame f = new JFrame();
	
	JTextField fName_input, lName_input, add1, add2, add3, add4, phone_input, id_input, pw_input, confirm_input;
	JLabel warning = new JLabel("");
	
	public GuestSignUp(JFrame f) {
		this.f = f;
	}
	
	public JFrame getFrame() {
		return this.f;
	}
	
	public JPanel createGuestSignUpPanel() {
		JPanel guestSignUp = new JPanel();
		
		final Font plain = new Font("Verdana", Font.PLAIN, 20);
		final Font bold = new Font("Verdana", Font.BOLD, 50);
		
		JLabel signUp, fName, lName, addA, addB, addC, addD, phone, id, pw, confirm_pw;
		JButton loginButton;
		JPanel buttons, hp1, hp2, hp3, hp4, hp5, hp6, hp7, hp8, hp9, hp10, hp11, hp12, hp13;
		
		signUp = new JLabel("Login a Guest");
		signUp.setFont(bold);
		
		// Input for user's name
		fName = new JLabel("First Name: ");
		fName.setFont(plain);
		
		fName_input = new JTextField(20);
		fName_input.setFont(plain);
		
		lName = new JLabel("Last Name: ");
		lName.setFont(plain);
		
		lName_input = new JTextField(20);
		lName_input.setFont(plain);
		
		// Input for user's address
		addA = new JLabel("House No.: ");
		addA.setFont(plain);
		add1 = new JTextField(20);
		add1.setFont(plain);
		addB = new JLabel("Street Name: ");
		addB.setFont(plain);
		add2 = new JTextField(20);
		add2.setFont(plain);
		addC = new JLabel("Place Name: ");
		addC.setFont(plain);
		add3 = new JTextField(20);
		add3.setFont(plain);
		addD = new JLabel("Place Name: ");
		addD.setFont(plain);
		add4 = new JTextField(20);
		add4.setFont(plain);
		
		// Input for phone number
		phone = new JLabel("Phone No.: ");
		phone.setFont(plain);
		phone_input = new JTextField(20);
		phone_input.setFont(plain);
		
		// Input for email
		id = new JLabel("Email: ");
		id.setFont(plain);
		id_input = new JTextField(20);
		id_input.setFont(plain);
		
		// Input for password
		pw = new JLabel("Password: ");
		pw.setFont(plain);
		pw_input = new JTextField(20);
		pw_input.setFont(plain);
		
		// Input for confirm password
		confirm_pw = new JLabel("Confirm Password: ");
		confirm_pw.setFont(plain);
		confirm_input = new JTextField(20);
		confirm_input.setFont(plain);
		
		loginButton = new JButton("Sign up");
		loginButton.setFont(plain);
		loginButton.addActionListener(this);
		
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
		
		BoxLayout b = new BoxLayout(guestSignUp, BoxLayout.Y_AXIS);
		guestSignUp.setLayout(b);
		
		hp1.add(signUp);
		hp2.add(fName);
		hp2.add(fName_input);
		hp3.add(lName);
		hp3.add(lName_input);
		hp5.add(addA);
		hp5.add(add1);
		hp6.add(addB);
		hp6.add(add2);
		hp7.add(addC);
		hp7.add(add3);
		hp8.add(addD);
		hp8.add(add4);
		hp9.add(phone);
		hp9.add(phone_input);
		hp10.add(id);
		hp10.add(id_input);
		hp11.add(pw);
		hp11.add(pw_input);
		hp12.add(confirm_pw);
		hp12.add(confirm_input);
		hp13.add(warning);
		buttons.add(loginButton);
		
		guestSignUp.add(hp1);
		guestSignUp.add(hp2);
		guestSignUp.add(hp3);
		guestSignUp.add(hp5);
		guestSignUp.add(hp6);
		guestSignUp.add(hp7);
		guestSignUp.add(hp8);
		guestSignUp.add(hp9);
		guestSignUp.add(hp10);
		guestSignUp.add(hp11);
		guestSignUp.add(hp12);
		guestSignUp.add(buttons);
		guestSignUp.add(hp13);
		guestSignUp.add(hp4);
		
		return guestSignUp;
	}
	
	public void actionPerformed(ActionEvent e) {
		String fName = fName_input.getText();
		String lName = lName_input.getText();
		String houseName = add1.getText();
		String streetName = add2.getText();
		String placeName = add3.getText();
		String postcode = add4.getText();
		String phone = phone_input.getText();
		String userID = id_input.getText();
		String password = pw_input.getText();
		
		if (fName != null && lName != null && add1 != null && add2 != null && add3 != null && add4 != null && phone_input != null && id_input != null && pw_input != null) {
			Address add = new Address(houseName, streetName, placeName, postcode);
			Guest guest = new Guest(fName, lName, add, phone, userID, password);
			
			System.out.println(guest);
		}
		else {
			warning.setText("Please fill all blanks!");
			warning.setFont(new Font("Verdana", Font.PLAIN, 20));
			warning.setForeground(Color.RED);
		}
	}
	
	public static void main (String [] args) {
		JFrame f = new JFrame();
		GuestSignUp g = new GuestSignUp(f);
		Container c = f.getContentPane();
		c.add(g.createGuestSignUpPanel());
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screen = tk.getScreenSize();
				
		f.setSize(screen.width, screen.height);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}