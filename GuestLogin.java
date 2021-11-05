import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuestLogin implements ActionListener {
	JFrame f;
	protected JButton signUp;
	protected Container c;
	protected GuestSignUp gsu;
	protected CardLayout card;
	
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
		c.add("Guest Sign Up", gsu.createGuestSignUpPanel());
		
		final Font plain = new Font("Verdana", Font.PLAIN, 30);
		final Font bold = new Font("Verdana", Font.BOLD, 50);
		
		JLabel login, id, pw;
		JButton loginButton;
		JPanel buttons, hp1, hp2, hp3, hp4;
		JTextField id_input, pw_input;
		
		login = new JLabel("Login a Guest");
		login.setFont(bold);
		
		id = new JLabel("User ID: ");
		id.setFont(plain);
		
		id_input = new JTextField(20);
		id_input.setFont(plain);
		
		pw = new JLabel("Password: ");
		pw.setFont(plain);
		
		pw_input = new JTextField(20);
		pw_input.setFont(plain);
		
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
		
	}
	

	
}