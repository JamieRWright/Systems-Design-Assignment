package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class CreateProperty implements ActionListener {
	JFrame f;
	protected JTextField shortName, add1, add2, add3, add4;
	protected JTextArea desc;
	protected JComboBox<String> breakfast;
	
	public CreateProperty(JFrame f) {
		this.f = f;
	}
	
	public JPanel createPropertyPanel() {
		JPanel panel = new JPanel();
		
		final Font plain = new Font("Verdana", Font.PLAIN, 25);
		final Font bold = new Font("Verdana", Font.BOLD, 50);
		
		JLabel title, shortName_lbl, desc_lbl, add_lbl, breakfast_lbl;
		JButton createBtn;
		JPanel hp1, hp2, hp3, hp4, adds, hp5, hp6;
		
		title = new JLabel("Create a new property");
		title.setFont(bold);
		
		// input for short name
		shortName_lbl = new JLabel("Property Name: ");
		shortName_lbl.setFont(plain);
		shortName = new JTextField(20);
		shortName.setFont(plain);
		
		// input for longer description
		desc_lbl = new JLabel("Description: ");
		desc_lbl.setFont(plain);
		desc = new JTextArea(5, 20);
		desc.setFont(plain);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		desc.setBorder(border);
		
		// input for address
		add_lbl = new JLabel("Address: ");
		add_lbl.setFont(plain);
		add1 = new JTextField(20);
		add2 = new JTextField(20);
		add3 = new JTextField(20);
		add4 = new JTextField(20);
		add1.setFont(plain);
		add2.setFont(plain);
		add3.setFont(plain);
		add4.setFont(plain);
		
		// input for breakfast
		breakfast_lbl = new JLabel("Breakfast: ");
		breakfast_lbl.setFont(plain);
		breakfast = new JComboBox();
		breakfast.setFont(plain);
		breakfast.addItem("Yes");
		breakfast.addItem("No");
		
		createBtn = new JButton("Create Property");
		createBtn.setFont(plain);
		createBtn.addActionListener(this);
		
		BoxLayout b = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(b);
		
		hp1 = new JPanel();
		hp2 = new JPanel();
		hp3 = new JPanel();
		hp4 = new JPanel();
		adds = new JPanel();
		hp5 = new JPanel();
		hp6 = new JPanel();
		
		adds.setLayout(new GridLayout(0, 1));
		adds.add(add1);
		adds.add(add2);
		adds.add(add3);
		adds.add(add4);
		
		hp1.add(title);
		hp2.add(shortName_lbl);
		hp2.add(shortName);
		hp3.add(desc_lbl);
		hp3.add(desc);
		hp4.add(add_lbl);
		hp4.add(adds);
		hp5.add(breakfast_lbl);
		hp5.add(breakfast);
		hp6.add(createBtn);
		
		panel.add(hp1);
		panel.add(hp2);
		panel.add(hp3);
		panel.add(hp4);
		panel.add(hp5);
		panel.add(hp6);
		
		return panel;
	}
	
	public void actionPerformed(ActionEvent e) {
		String houseNo = add1.getText();
		String street = add2.getText();
		String postcode = add3.getText();
		String city = add4.getText();
		String s_name = shortName.getText();
		String description = desc.getText();
		
		Property property = new Property(1, houseNo, street, postcode, city, city, s_name, description);
	}
	
	public static void main (String [] args) {
		JFrame f = new JFrame();
		CreateProperty g = new CreateProperty(f);
		Container c = f.getContentPane();
		c.add(g.createPropertyPanel());
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screen = tk.getScreenSize();
				
		f.setSize(screen.width, screen.height);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}