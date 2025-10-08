import java.awt.*;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.text.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI {
	public static void home() {
		// Creates the home page
		JFrame h = new JFrame("GalleryWalk");
		h.setSize(1000,800);
		h.setLayout(new BorderLayout());
		h.setVisible(true);
		
		// Creates a panel for all button in home
		JPanel p = new JPanel();
		h.add(p);
		
		// Creates a button that allows user to change to piece submission form
		JButton submitPiece = new JButton("Create Piece Submission Form");
		submitPiece.setSize(50, 25);
		p.add(submitPiece);
		
		// Checks for button press and changes screen
        submitPiece.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		h.setVisible(false);
        		pieceForm();
        	}
        });
	}
	
	public static void pieceForm() {
		JFrame form = new JFrame("Piece Submission");
		form.setSize(1000, 800);
		form.setLayout(null);
		form.setVisible(true);
		
		// Allows user to enter text for name of piece
		JLabel nameSubmission = new JLabel();
		nameSubmission.setText("Name of piece: ");
		nameSubmission.setBounds(380, 10, 100, 100);
		JLabel creatorSubmission = new JLabel();
		creatorSubmission.setText("Creator of piece: ");
		creatorSubmission.setBounds(380, 50, 100, 100);
		
		JTextField name = new JTextField();
		name.setBounds(480, 50, 130, 30);
		JTextField creator = new JTextField();
		creator.setBounds(480, 90, 130, 30);
		
		// Adds the text to the form
		form.add(nameSubmission);	
		form.add(creatorSubmission);
		form.add(name);
		form.add(creator);
		
	}
	
	
	public static void login() {
		// Creates the login screen
		JFrame f = new JFrame("GalleryWalk");  
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1000, 800);
        f.setLayout(new BorderLayout());
        f.setVisible(true);
        
        
        // Put login UI here (The JPanel is where we put all the buttons and input things
        JPanel p = new JPanel();
        f.add(p);
        
        // Creates the login submission button and adds it to the panel
        JButton skipLogin = new JButton("Skip Login");
        skipLogin.setSize(50, 25);
        skipLogin.setOpaque(false);
        skipLogin.setContentAreaFilled(false);
        skipLogin.setBorderPainted(false);
        p.add(skipLogin);
        
        // Add an if statement encompassing this to check if account is real
        // Checks for button press and changes screen if check returns true
        skipLogin.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		f.setVisible(false);
        		home();
        	}
        });
        
        }

}


