import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
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
		creatorSubmission.setBounds(380, 40, 100, 100);
		JLabel yearSubmission = new JLabel();
		yearSubmission.setText("Year created: ");
		yearSubmission.setBounds(380, 70, 100, 100);
		JLabel worthSubmission = new JLabel();
		worthSubmission.setText("Worth: ");
		worthSubmission.setBounds(380, 100, 100, 100);
		JLabel museumSubmission = new JLabel();
		museumSubmission.setText("Museum located: ");
		museumSubmission.setBounds(380, 130, 100, 100);
		JLabel imageSubmission = new JLabel();
		imageSubmission.setText("Image URL: ");
		imageSubmission.setBounds(380, 160, 100, 100);
		JLabel descSubmission = new JLabel();
		descSubmission.setText("Museum located: ");
		descSubmission.setBounds(380, 190, 100, 100);
		
		JTextField name = new JTextField();
		name.setBounds(480, 40, 130, 30);
		JTextField creator = new JTextField();
		creator.setBounds(480, 70, 130, 30);
		JTextField year = new JTextField();
		year.setBounds(480, 100, 130, 30);
		JTextField worth = new JTextField();
		worth.setBounds(480, 130, 130, 30);
		JTextField museum = new JTextField();
		museum.setBounds(480, 160, 130, 30);
		JTextField image = new JTextField();
		image.setBounds(480, 190, 130, 30);
		JTextArea desc = new JTextArea(5, 25);
		desc.setLineWrap(true);
		desc.setWrapStyleWord(true);
		JScrollPane descScroll = new JScrollPane(desc);
		descScroll.setBounds(380, 250, 230, 100);

		// Adds the text to the form
		form.add(nameSubmission);	
		form.add(creatorSubmission);
		form.add(yearSubmission);
		form.add(worthSubmission);
		form.add(museumSubmission);
		form.add(imageSubmission);
		form.add(descSubmission);
		form.add(name);
		form.add(creator);
		form.add(year);
		form.add(worth);
		form.add(museum);
		form.add(image);
		form.add(descScroll);
		
		// Submit button to properly add all data into a holding file for confirmation
		JButton submit = new JButton("Submit");
		submit.setBounds(440, 350, 100, 25);
		form.add(submit);
		
		// Submission Successful Indicator Text
		JLabel submitSuccess = new JLabel("Form Successfully Submitted");
		submitSuccess.setBounds(410, 380, 230, 20);
		submitSuccess.setForeground(Color.BLUE);
		submitSuccess.setOpaque(true);
		form.add(submitSuccess);
		submitSuccess.setVisible(false);
		
		// Home Button
		JButton home = new JButton("Home");
		home.setBounds(100, 10, 100, 25);
		form.add(home);
		
		// Actions after pressing submit button
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Opens and write data into a file
				try {
					FileWriter submission = new FileWriter("DataFiles/Submissions.txt", true);
					RandomAccessFile file = new RandomAccessFile("DataFiles/Submissions.txt", "rw");
					file.seek(file.length());
					submission.write(creator.getText() + "\n");
					submission.write(year.getText() + "\n");
					submission.write(name.getText() + "\n");
					submission.write("Pictures/" + image.getText() + "\n");
					submission.write(museum.getText() + "\n");
					submission.write(worth.getText() + "\n");
					submission.write(desc.getText() + "\n");
					submission.close();
				} catch (IOException q) {
					System.out.println("An error occurred: " + q.getMessage());
				}
				// Displays text telling user form was submitted successfully
				submitSuccess.setVisible(true);
			}
		});
		
		home.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				form.setVisible(false);
				home();
			}
		});
	
	}
	
	
	public static void login() {
		// Creates the login screen
		JFrame f = new JFrame("GalleryWalk"); 
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize((int) screenSize.getHeight(), (int) screenSize.getWidth());
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


