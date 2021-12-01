package main;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class HBPanels extends HomeBreaks {

	public static JPanel changeInfoPanel() {
		final Font plain = new Font("Verdana", Font.PLAIN, 25);
		
        JPanel ci = new JPanel();
		
		JPanel hp = new JPanel();
		hp.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		ci.setBorder(HomeBreaks.createTitledBorder("My info"));
		ci.setLayout(new GridBagLayout());
		
		JLabel fName, lName, phone;
		JTextField f, l, p;
		
		HomeBreaks.setConstraints(g, 0, 0, GridBagConstraints.EAST);
		fName = new JLabel("First Name: ");
		fName.setFont(plain);
		ci.add(fName, g);
		
		HomeBreaks.setConstraints(g, 1, 0, GridBagConstraints.WEST);
		f = new JTextField(20); //TODO add host's name as default text
		f.setFont(plain);
		ci.add(f, g);
		
		HomeBreaks.setConstraints(g, 0, 1, GridBagConstraints.EAST);
		lName = new JLabel("Last Name: ");
		lName.setFont(plain);
		ci.add(lName, g);
		
		HomeBreaks.setConstraints(g, 1, 1, GridBagConstraints.WEST);
		l = new JTextField(20);
		l.setFont(plain);
		ci.add(l, g);
		
		HomeBreaks.setConstraints(g, 0, 2, GridBagConstraints.EAST);
		phone = new JLabel("Phone Number: ");
		phone.setFont(plain);
		ci.add(phone, g);
		
		HomeBreaks.setConstraints(g, 1, 2, GridBagConstraints.WEST);
		p = new JTextField(20);
		p.setFont(plain);
		ci.add(p, g);
		
		HomeBreaks.setConstraints(g, 1, 3, GridBagConstraints.WEST);
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
					TDatabase.UpdateValue("Host", "firstName", "", fn);
					TDatabase.UpdateValue("Host", "lastName", "", ln);
					TDatabase.UpdateValue("Host", "Phone", "", pn);
				}
			}
		});
		ci.add(confirm, g);
		
		HomeBreaks.setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		hp.add(ci, g);
		
		return hp;
	}
	
	public static JPanel defaultSearchPanel() {
		final Font plain = new Font("Verdana", Font.PLAIN, 25);
		
		JPanel sr = new JPanel();
		JPanel hp = new JPanel();
		hp.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		sr.setBorder(HomeBreaks.createTitledBorder("Properties"));
		JPanel x = new JPanel();
		x.setBorder(new EmptyBorder(0, 70, 0, 70));
		
		// Default label
		JLabel dl = new JLabel("Start searching to see relevant properties...");
		dl.setFont(plain);
		x.add(dl);
		sr.add(x);
		
		HomeBreaks.setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		hp.add(sr, g);
		
		return hp;
	}
	
	public static JPanel reviewPanel() {
		final Font plain = new Font("Verdana", Font.PLAIN, 25);
		
		JPanel rp = new JPanel();
		JPanel hp = new JPanel();
		JPanel r = new JPanel();
		
		hp.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		rp.setLayout(new GridBagLayout());
		
		r.setLayout(new GridBagLayout());
		
		JLabel c1, c2, c3, c4, c5, c6, desc;
		ButtonGroup cleanliness, communication, checkin, accuracy, location, value;
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
		
		r.setBorder(HomeBreaks.createTitledBorder("Leave a review"));
		
		HomeBreaks.setConstraints(g, 0, 0, GridBagConstraints.EAST);
		c1 = new JLabel("Cleanliness: ");
		c1.setFont(plain);
		rp.add(c1, g);
		
		HomeBreaks.setConstraints(g, 1, 0, GridBagConstraints.WEST);
		cleanliness = new ButtonGroup();
		c1_1 = new JRadioButton("1");
		c1_2 = new JRadioButton("2");
		c1_3 = new JRadioButton("3");
		c1_4 = new JRadioButton("4");
		c1_5 = new JRadioButton("5");
		
		g2 = new JPanel();
		
		HomeBreaks.createRatingButtons(c1_1, c1_2, c1_3, c1_4, c1_5, g2, cleanliness);
		rp.add(g2, g);
		
		HomeBreaks.setConstraints(g, 0, 1, GridBagConstraints.EAST);
		c2 = new JLabel("Communication: ");
		c2.setFont(plain);
		rp.add(c2, g);
		
		HomeBreaks.setConstraints(g, 1, 1, GridBagConstraints.WEST);
		communication = new ButtonGroup();
		c2_1 = new JRadioButton("1");
		c2_2 = new JRadioButton("2");
		c2_3 = new JRadioButton("3");
		c2_4 = new JRadioButton("4");
		c2_5 = new JRadioButton("5");
		
		g3 = new JPanel();
		
		HomeBreaks.createRatingButtons(c2_1, c2_2, c2_3, c2_4, c2_5, g3, communication);
		rp.add(g3, g);
		
		HomeBreaks.setConstraints(g, 0, 2, GridBagConstraints.EAST);
		c3 = new JLabel("Check-in: ");
		c3.setFont(plain);
		rp.add(c3, g);
		
		HomeBreaks.setConstraints(g, 1, 2, GridBagConstraints.WEST);
		checkin = new ButtonGroup();
		c3_1 = new JRadioButton("1");
		c3_2 = new JRadioButton("2");
		c3_3 = new JRadioButton("3");
		c3_4 = new JRadioButton("4");
		c3_5 = new JRadioButton("5");
		
		g4 = new JPanel();
		
		HomeBreaks.createRatingButtons(c3_1, c3_2, c3_3, c3_4, c3_5, g4, checkin);
		rp.add(g4, g);
		
		HomeBreaks.setConstraints(g, 0, 3, GridBagConstraints.EAST);
		c4 = new JLabel("Accuracy: ");
		c4.setFont(plain);
		rp.add(c4, g);
		
		HomeBreaks.setConstraints(g, 1, 3, GridBagConstraints.WEST);
		accuracy = new ButtonGroup();
		c4_1 = new JRadioButton("1");
		c4_2 = new JRadioButton("2");
		c4_3 = new JRadioButton("3");
		c4_4 = new JRadioButton("4");
		c4_5 = new JRadioButton("5");
		
		g5 = new JPanel();
		
		HomeBreaks.createRatingButtons(c4_1, c4_2, c4_3, c4_4, c4_5, g5, accuracy);
		rp.add(g5, g);
		
		//
		HomeBreaks.setConstraints(g, 0, 4, GridBagConstraints.EAST);
		c5 = new JLabel("Location: ");
		c5.setFont(plain);
		rp.add(c5, g);
		
		HomeBreaks.setConstraints(g, 1, 4, GridBagConstraints.WEST);
		location = new ButtonGroup();
		c5_1 = new JRadioButton("1");
		c5_2 = new JRadioButton("2");
		c5_3 = new JRadioButton("3");
		c5_4 = new JRadioButton("4");
		c5_5 = new JRadioButton("5");
		
		g6 = new JPanel();
		
		HomeBreaks.createRatingButtons(c5_1, c5_2, c5_3, c5_4, c5_5, g6, location);
		rp.add(g6, g);
		
		HomeBreaks.setConstraints(g, 0, 5, GridBagConstraints.EAST);
		c6 = new JLabel("Value: ");
		c6.setFont(plain);
		rp.add(c6, g);
		
		HomeBreaks.setConstraints(g, 1, 5, GridBagConstraints.WEST);
		value = new ButtonGroup();
		c6_1 = new JRadioButton("1");
		c6_2 = new JRadioButton("2");
		c6_3 = new JRadioButton("3");
		c6_4 = new JRadioButton("4");
		c6_5 = new JRadioButton("5");
		
		g7 = new JPanel();
		
		HomeBreaks.createRatingButtons(c6_1, c6_2, c6_3, c6_4, c6_5, g7, value);
		rp.add(g7, g);
		HomeBreaks.setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		rp.setBorder(new EmptyBorder(0, 100, 0, 100));
		r.add(rp, g);
		
		HomeBreaks.setConstraints(g, 0, 1, GridBagConstraints.CENTER);
		desc = new JLabel("Describe your experience (optional): ");
		desc.setFont(plain);
		r.add(desc, g);
		
		HomeBreaks.setConstraints(g, 0, 2, GridBagConstraints.CENTER);
		description = new JTextArea(5, 20);
		JScrollPane scrollPane = new JScrollPane(description);
		description.setFont(plain);
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		JPanel space = new JPanel();
		space.setBorder(new EmptyBorder(10, 0, 0, 0));
		r.add(space, g);
		HomeBreaks.setConstraints(g, 0, 3, GridBagConstraints.CENTER);
		r.add(scrollPane, g);
		
		HomeBreaks.setConstraints(g, 0, 4, GridBagConstraints.CENTER);
		submit = new JButton("Submit Review");
		submit.setFont(plain);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean a = cleanliness.getSelection() == null || communication.getSelection() == null || checkin.getSelection() == null;
				boolean b = accuracy.getSelection() == null || location.getSelection() == null || value.getSelection() == null;
				if (a || b) {
					showMessageDialog(null, "Please rate all categories.");
				}
				else {
					int cl = 0;
					int com = 0;
					int chk = 0;
					int ac = 0;
					int loc = 0;
					int val = 0;
					
					cl = HomeBreaks.findSelectedValue(c1_1, c1_2, c1_3, c1_4, c1_5);
					com = HomeBreaks.findSelectedValue(c2_1, c2_2, c2_3, c2_4, c2_5);
					chk = HomeBreaks.findSelectedValue(c3_1, c3_2, c3_3, c3_4, c3_5);
					ac = HomeBreaks.findSelectedValue(c4_1, c4_2, c4_3, c4_4, c4_5);
					loc = HomeBreaks.findSelectedValue(c5_1, c5_2, c5_3, c5_4,c5_5);
					val = HomeBreaks.findSelectedValue(c6_1, c6_2, c6_3, c6_4, c6_5);
					TDatabase.AddReview(1, 1, 1, cl, com, chk, ac, loc, val, description.getText());
					//TODO add the propertyID, guestID, and hostID
				}
			}
		});
		JPanel space2 = new JPanel();
		space2.setBorder(new EmptyBorder(10, 0, 0, 0));
		r.add(space2, g);
		HomeBreaks.setConstraints(g, 0, 5, GridBagConstraints.CENTER);
		r.add(submit, g);
		
		HomeBreaks.setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		hp.add(r, g);
		return hp;
	}
	
	public static JPanel chargeBandPanel() {
		final Font plain = new Font("Verdana", Font.PLAIN, 25);
		
		JPanel acb = new JPanel();
		JPanel hp = new JPanel();
		
		acb.setLayout(new GridBagLayout());
		acb.setBorder(HomeBreaks.createTitledBorder("Add Charge Band"));
		hp.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		
		JLabel s, e, ppn, sc, cc;
		JTextField startDD, endDD, startMM, endMM, startYY, endYY, pricePerNight, serviceCharge, cleaningCharge;
		
		HomeBreaks.setConstraints(g, 0, 0, GridBagConstraints.EAST);
		s = new JLabel("Start Date: ");
		s.setFont(plain);
		acb.add(s, g);
		
		JPanel startDate = new JPanel();
		startDate.setLayout(new GridBagLayout());
		HomeBreaks.setConstraints(g, 0, 0, GridBagConstraints.WEST);
		startDD = new JTextField("DD", 2);
		startDD.setFont(plain);
		startDate.add(startDD, g);
		HomeBreaks.setConstraints(g, 1, 0, GridBagConstraints.WEST);
		JLabel d1 = new JLabel("-");
		d1.setFont(plain);
		startDate.add(d1, g);
		HomeBreaks.setConstraints(g, 2, 0, GridBagConstraints.WEST);
		startMM = new JTextField("MM", 2);
		startMM.setFont(plain);
		startDate.add(startMM, g);
		HomeBreaks.setConstraints(g, 3, 0, GridBagConstraints.WEST);
		JLabel d2 = new JLabel("-");
		d2.setFont(plain);
		startDate.add(d2, g);
		HomeBreaks.setConstraints(g, 4, 0, GridBagConstraints.WEST);
		startYY = new JTextField("YYYY", 4);
		startYY.setFont(plain);
		startDate.add(startYY, g);
		
		HomeBreaks.setConstraints(g, 1, 0, GridBagConstraints.WEST);
		acb.add(startDate, g);
		
		HomeBreaks.setConstraints(g, 0, 1, GridBagConstraints.EAST);
		e = new JLabel("End Date: ");
		e.setFont(plain);
		acb.add(e, g);
		
		JPanel endDate = new JPanel();
		endDate.setLayout(new GridBagLayout());
		
		HomeBreaks.setConstraints(g, 0, 0, GridBagConstraints.WEST);
		endDD = new JTextField("DD", 2);
		endDD.setFont(plain);
		endDate.add(endDD, g);
		HomeBreaks.setConstraints(g, 1, 0, GridBagConstraints.WEST);
		JLabel d3 = new JLabel("-");
		d3.setFont(plain);
		endDate.add(d3, g);
		HomeBreaks.setConstraints(g, 2, 0, GridBagConstraints.WEST);
		endMM = new JTextField("MM", 2);
		endMM.setFont(plain);
		endDate.add(endMM, g);
		HomeBreaks.setConstraints(g, 3, 0, GridBagConstraints.WEST);
		JLabel d4 = new JLabel("-");
		d4.setFont(plain);
		endDate.add(d4, g);
		HomeBreaks.setConstraints(g, 4, 0, GridBagConstraints.WEST);
		endYY = new JTextField("YYYY", 4);
		endYY.setFont(plain);
		endDate.add(endYY, g);
		
		HomeBreaks.setConstraints(g, 1, 1, GridBagConstraints.CENTER);
		acb.add(endDate, g);
		
		HomeBreaks.setConstraints(g, 0, 2, GridBagConstraints.EAST);
		ppn = new JLabel("Price per night (Ã‚Â£): ");
		ppn.setFont(plain);
		acb.add(ppn, g);
		
		HomeBreaks.setConstraints(g, 1, 2, GridBagConstraints.WEST);
		pricePerNight = new JTextField(5);
		pricePerNight.setFont(plain);
		acb.add(pricePerNight, g);
		
		HomeBreaks.setConstraints(g, 0, 3, GridBagConstraints.EAST);
		sc = new JLabel("Service charge (Ã‚Â£): ");
		sc.setFont(plain);
		acb.add(sc, g);
		
		HomeBreaks.setConstraints(g, 1, 3, GridBagConstraints.WEST);
		serviceCharge = new JTextField(5);
		serviceCharge.setFont(plain);
		acb.add(serviceCharge, g);
		
		HomeBreaks.setConstraints(g, 0, 4, GridBagConstraints.EAST);
		cc = new JLabel("Cleaning charge (Ã‚Â£): ");
		cc.setFont(plain);
		acb.add(cc, g);
		
		HomeBreaks.setConstraints(g, 1, 4, GridBagConstraints.WEST);
		cleaningCharge = new JTextField(5);
		cleaningCharge.setFont(plain);
		acb.add(cleaningCharge, g);
		
		HomeBreaks.setConstraints(g, 1, 5, GridBagConstraints.WEST);
		JButton add = new JButton("Add");
		add.setFont(plain);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sY = startYY.getText();
				String sM = startMM.getText();
				String sD = startDD.getText();
				boolean rightStart = HomeBreaks.isNumericDate(sY) || HomeBreaks.isNumericDate(sM) || HomeBreaks.isNumericDate(sD);
				boolean unfilledStart = sY.isEmpty() || sM.isEmpty() || sD.isEmpty();
				
				String eY = endYY.getText();
				String eM = endMM.getText();
				String eD = endDD.getText();
				boolean rightEnd = HomeBreaks.isNumericDate(eY) || HomeBreaks.isNumericDate(eM) || HomeBreaks.isNumericDate(eD);
				boolean unfilledEnd = eY.isEmpty() || eM.isEmpty() || eD.isEmpty();
				
				String price = pricePerNight.getText();
				String cleaning = cleaningCharge.getText();
				String service = serviceCharge.getText();
				boolean rightPrices = HomeBreaks.isNumericPrice(price) || HomeBreaks.isNumericPrice(cleaning) || HomeBreaks.isNumericPrice(service);
				boolean unfilledPrices = price.isEmpty() || cleaning.isEmpty() || service.isEmpty();
				
				if (unfilledStart || unfilledEnd || unfilledPrices) {
					showMessageDialog(null, "All fields must be filled.");
				}
				else if (rightStart && rightEnd && rightPrices) {
					String start = sY + "-" + sM + "-" + sD;
					String end = eY + "-" + eM + "-" + eD;
					
					TDatabase.AddChargeBand(start, end, 25, Double.parseDouble(price), Double.parseDouble(service), Double.parseDouble(cleaning)); // TODO get propertyID from current property
				}
				else {
					showMessageDialog(null, "Incorrect input");
				}
				
			}
		});
		acb.add(add, g);
		
		
		HomeBreaks.setConstraints(g, 0, 0, GridBagConstraints.CENTER);
		hp.add(acb);
		return hp;
	}
}

