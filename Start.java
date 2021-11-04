import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Start {
	
	final Font plain = new Font("Verdana", Font.PLAIN, 30);
	final Font bold = new Font("Verdana", Font.BOLD, 50);
	final Color bg = new Color(31, 40, 51);
	final Color font1 = new Color(102, 252, 242);
	final Color font2 = new Color(197, 198, 199);
	JFrame frame = new JFrame();
	Container contentPane = frame.getContentPane();
	JPanel panel = new JPanel();
	JButton host, guest, enquirer;
	
	public Start() {
		startGUI();
		mainOptions();
	}
	
	public Start(String page) {
		startGUI();
	}
	
	public void startGUI() {
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screen = tk.getScreenSize();
				
		frame.setSize(screen.width, screen.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BoxLayout b = new BoxLayout(contentPane, BoxLayout.Y_AXIS);
		contentPane.setLayout(b);
		
	    //mainOptions();
		//guestLogin();
		
		frame.setVisible(true);
		
	}
	
	public void mainOptions() {
		JLabel welc, opt;
		
		welc = new JLabel("Welcome");
		opt = new JLabel("To start, choose a role");
		welc.setFont(bold);
		opt.setFont(plain);
		EmptyBorder border = new EmptyBorder(30, 0, 0, 0);
		opt.setBorder(border);
		EmptyBorder border2 = new EmptyBorder(20, 40, 0, 0);
		welc.setBorder(border2);
		welc.setForeground(font1);
		opt.setForeground(font1);
		
		host = new JButton("Host");
		guest = new JButton("Guest");
		enquirer = new JButton("Enquirer");
		
		host.setFont(plain);
		host.setForeground(font2);
		guest.setFont(plain);
		guest.setForeground(font2);
		enquirer.setFont(plain);
		enquirer.setForeground(font2);
		host.setContentAreaFilled(false);
		guest.setContentAreaFilled(false);
		enquirer.setContentAreaFilled(false);
		host.setBorder(BorderFactory.createLineBorder(font2, 4));
		guest.setBorder(BorderFactory.createLineBorder(font2, 4));
		enquirer.setBorder(BorderFactory.createLineBorder(font2, 4));
		
		JPanel labels = new JPanel();
		JPanel options = new JPanel();
		JPanel hp = new JPanel();
		
		labels.setBackground(bg);
		options.setBackground(bg);
		hp.setBackground(bg);
		
		BoxLayout b2 = new BoxLayout(labels, BoxLayout.Y_AXIS);
		labels.setLayout(b2);
		
		host.setPreferredSize(new Dimension(400, 500));
		guest.setPreferredSize(new Dimension(400, 500));
		enquirer.setPreferredSize(new Dimension(400, 500));
		
		labels.add(welc);
		labels.add(opt);
		options.add(host);
		options.add(guest);
		options.add(enquirer);
		hp.add(labels);
		contentPane.add(hp);
		contentPane.add(options);
	}
	
	public void guestLogin() {
		JLabel login, id, pw;
		JButton signUp, loginButton;
		JPanel buttons, hp1, hp2, hp3, hp4;
		JTextField id_input, pw_input;
		
		login = new JLabel("Login a Guest");
		login.setFont(bold);
		login.setForeground(font1);
		
		id = new JLabel("User ID: ");
		id.setFont(plain);
		id.setForeground(font2);
		
		id_input = new JTextField(20);
		id_input.setFont(plain);
		
		pw = new JLabel("Password: ");
		pw.setFont(plain);
		pw.setForeground(font2);
		
		pw_input = new JTextField(20);
		pw_input.setFont(plain);
		
		signUp = new JButton("Don't have an account? Sign up!");
		signUp.setContentAreaFilled(false);
		signUp.setBorderPainted(false);
		signUp.setFont(plain);
		signUp.setForeground(font2);
		
		loginButton = new JButton("Log in");
		loginButton.setFont(plain);
		loginButton.setForeground(font2);
		loginButton.setContentAreaFilled(false);
		loginButton.setBorder(BorderFactory.createLineBorder(font2, 2));
		
		hp1 = new JPanel();
		hp2 = new JPanel();
		hp3 = new JPanel();
		hp4 = new JPanel();
		buttons = new JPanel();
		hp1.setBackground(bg);
		hp2.setBackground(bg);
		hp3.setBackground(bg);
		hp4.setBackground(bg);
		buttons.setBackground(bg);
		
		BoxLayout b = new BoxLayout(contentPane, BoxLayout.Y_AXIS);
		contentPane.setLayout(b);
		
		hp1.add(login);
		hp2.add(id);
		hp2.add(id_input);
		hp3.add(pw);
		hp3.add(pw_input);
		hp4.add(signUp);
		buttons.add(loginButton);
		
		contentPane.add(hp1);
		contentPane.add(hp2);
		contentPane.add(hp3);
		contentPane.add(buttons);
		contentPane.add(hp4);
	}
	
	public static void main (String [] args) {
		new Start();
	}
}