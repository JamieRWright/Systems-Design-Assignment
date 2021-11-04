import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class StartProgramme implements ActionListener {
	JFrame frame = new JFrame();
	JButton host, guest, enquirer;
	
	public StartProgramme(JFrame frame) {
		this.frame = frame;
		startGUI(frame);
	}
	
	public void startGUI(JFrame f) {
		Container contentPane = f.getContentPane();
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screen = tk.getScreenSize();
				
		f.setSize(screen.width, screen.height);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane.setLayout(new CardLayout());
		
		GuestLogin gl = new GuestLogin(f);
		GuestSignUp gsu = new GuestSignUp(f);
		contentPane.add("Home", createHomePanel(contentPane));
		contentPane.add("Guest Login", gl.createGuestLoginPanel());
		contentPane.add("Guest Sign Up", gsu.createGuestSignUpPanel());
		
		frame.setVisible(true);
	}
	
	public JPanel createHomePanel(Container c) {
		JPanel home = new JPanel();
		JPanel buttons, labels, hp;
		
		Font plain = new Font("Verdana", Font.PLAIN, 30);
		Font bold = new Font("Verdana", Font.BOLD, 50);
		
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
		
		guest.addActionListener(this);
		
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
	
	public void actionPerformed(ActionEvent e) {
		((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "Guest Login");
	}
	
	public static void main (String [] args) {
		new StartProgramme(new JFrame());
	}
}