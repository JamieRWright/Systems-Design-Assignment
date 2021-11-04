import javax.swing.*;
import java.awt.*;

public class GuestLogin {
	JFrame f = new JFrame();
	
	public GuestLogin(JFrame f) {
		this.f = f;
	}
	
	public JFrame getFrame() {
		return this.f;
	}
	
	public JPanel createGuestLoginPanel() {
		JPanel guestLogin = new JPanel();
		Container c = getFrame().getContentPane();
		
		final Font plain = new Font("Verdana", Font.PLAIN, 30);
		final Font bold = new Font("Verdana", Font.BOLD, 50);
		
		JLabel login, id, pw;
		JButton signUp, loginButton;
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
}