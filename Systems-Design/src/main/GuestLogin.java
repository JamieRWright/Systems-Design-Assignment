package main;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuestLogin implements DocumentListener, ActionListener {
	JFrame f;
	protected JButton signUp;
	protected Container c;
	protected GuestSignUp gsu;
	protected CardLayout card;
	JTextField id_input;
	JPasswordField pw_input;
	
	public GuestLogin(JFrame f) {
		this.f = f;
	}
	
	public JFrame getFrame() {
		return this.f;
	}
	
	public Container getContainer() {
		return this.c;
	}
	
	public JPanel createGuestLoginPanel() {
		JPanel guestLogin = new JPanel();
		
		c = f.getContentPane();
		card = new CardLayout();
		c.setLayout(card);
		gsu = new GuestSignUp(f);
		c.add("Guest Login", guestLogin);
		//c.add("Guest Sign Up", gsu.createGuestSignUpPanel());
		
		final Font plain = new Font("Verdana", Font.PLAIN, 30);
		final Font bold = new Font("Verdana", Font.BOLD, 50);
		
		JLabel login, id, pw;
		JButton loginButton;
		JPanel buttons, hp1, hp2, hp3, hp4;
		
		login = new JLabel("Login a Guest");
		login.setFont(bold);
		
		id = new JLabel("User ID: ");
		id.setFont(plain);
		
		id_input = new JTextField(20);
		id_input.setFont(plain);
		id_input.getDocument().addDocumentListener(this);
		
		pw = new JLabel("Password: ");
		pw.setFont(plain);
		
		pw_input = new JPasswordField(20);
		pw_input.setFont(plain);
		pw_input.setEchoChar('*');
		
		signUp = new JButton("Don't have an account? Sign up!");
		signUp.setContentAreaFilled(false);
		signUp.setBorderPainted(false);
		signUp.setFont(plain);
		signUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(c, "Guest Sign Up");
			}
		});
		
		loginButton = new JButton("Log in");
		loginButton.setFont(plain);
		loginButton.addActionListener(this);
		
		hp1 = new JPanel();
		hp2 = new JPanel();
		hp3 = new JPanel();
		hp4 = new JPanel();
		buttons = new JPanel();
		
		BoxLayout b = new BoxLayout(guestLogin, BoxLayout.Y_AXIS);
		guestLogin.setLayout(b);
		
		hp1.add(login);
		hp2.add(id);
		hp2.add(id_input);
		hp3.add(pw);
		hp3.add(pw_input);
		hp4.add(signUp);
		buttons.add(loginButton);
		
		guestLogin.add(hp1);
		guestLogin.add(hp2);
		guestLogin.add(hp3);
		guestLogin.add(buttons);
		guestLogin.add(hp4);
		
		return guestLogin;
	}
	
	public void actionPerformed(ActionEvent e) {
		check();
	}
	
	public static void main (String [] args) {
		JFrame f = new JFrame();
		GuestLogin g = new GuestLogin(f);
		Container c = f.getContentPane();
		c.add(g.createGuestLoginPanel());
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screen = tk.getScreenSize();
				
		f.setSize(screen.width, screen.height);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		//check();
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		//check();
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		//check();
	}
	
	public void check() {
		if (id_input.getText().isEmpty() || pw_input.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "All fields must not be blank");
		}
	}

	
}