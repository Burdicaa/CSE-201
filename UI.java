import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI {
	public static void home(String user, String pass, String admin, String mod) {

		// Creates the home page
		JFrame home = new JFrame("GalleryWalk");
		home.setSize(1000,800);
		home.setLayout(null);
        home.getContentPane().setBackground(Color.decode("#E9DAC4"));
		home.setVisible(true);
		
		// Creates a panel for all button in home
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setBounds(300,50,400,500);
		p.setBackground(Color.decode("#E9DAC4"));
		home.add(p, BorderLayout.CENTER);

		JButton search = new JButton("Search");
		search.setSize(50, 25);
		search.setAlignmentX(Component.CENTER_ALIGNMENT);
		p.add(search);
		
		// Creates a button that allows user to change to piece submission form
		JButton submitPiece = new JButton("Create Piece Submission Form");
		submitPiece.setSize(50, 50);
		submitPiece.setAlignmentX(Component.CENTER_ALIGNMENT);
		p.add(submitPiece);
		
		JButton gallery = new JButton("Gallery");
		gallery.setSize(50,50);
		gallery.setAlignmentX(Component.CENTER_ALIGNMENT);
		p.add(gallery);
		
		
		if (admin != null && admin.equals("Admin: true")) {
			JButton pieceReview = new JButton("Review Piece Applications");
			pieceReview.setSize(50,50);
			pieceReview.setAlignmentX(Component.CENTER_ALIGNMENT);
			p.add(pieceReview);
			
			JButton modUser = new JButton("Change User to Moderator");
			modUser.setSize(50, 50);
			modUser.setAlignmentX(Component.CENTER_ALIGNMENT);
			p.add(modUser);
			
	        pieceReview.addActionListener(new ActionListener() {
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		home.setVisible(false);
	        		pieceReview(user, pass, admin, mod);
	        	}
	        });
	        
	        modUser.addActionListener(new ActionListener() {
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		home.setVisible(false);
	        		modUser(user, pass, admin, mod);
	        	}
	        });
		}
		
		JButton logout = new JButton("Logout");
		logout.setSize(50,25);
		logout.setAlignmentX(Component.CENTER_ALIGNMENT);
		p.add(logout);
		
		
		// Checks for button press and changes screen
        search.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		home.setVisible(false);
        		searchPage(user, pass, admin, mod);
        	}
        });
		
		
		// Checks for button press and changes screen
        submitPiece.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		home.setVisible(false);
        		pieceForm(user, pass, admin, mod);
        	}
        });

        gallery.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		home.setVisible(false);
        		Gallery f = new Gallery(user, pass, admin, mod, "Datafiles/AcceptedPieces.txt");
        		f.disp();
        	}
        });
        
        logout.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		home.setVisible(false);
        		try {
					login();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        
	}


	public static void searchPage(String user, String pass, String admin, String mod) {
		JFrame sPage = new JFrame("Search");
		sPage.setSize(1000, 800);
		sPage.setLayout(null);
        sPage.getContentPane().setBackground(Color.decode("#E9DAC4"));
		sPage.setVisible(true);
		
		JButton home = new JButton("Home");
		home.setBounds(10, 10, 100, 25);
		sPage.add(home);
		
		home.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sPage.setVisible(false);
				home(user, pass, admin, mod);
			}
		});
		
		// Displays the search bar and prompts user for a keyword to search
		JLabel searchLabel = new JLabel("Search keyword:");
		searchLabel.setBounds(300, 10, 100, 100);
		JTextField searchBox = new JTextField();
        searchBox.setBounds(420, 50, 300, 30);
        sPage.add(searchLabel);
        sPage.add(searchBox);
	}
	
	public static void pieceForm(String user, String pass, String admin, String mod) {
		JFrame form = new JFrame("Piece Submission");
		form.setSize(1000, 800);
		form.setLayout(null);
        form.getContentPane().setBackground(Color.decode("#E9DAC4"));
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
		descSubmission.setText("Description: ");
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
		home.setBounds(10, 10, 100, 25);
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
					submission.write(image.getText() + "\n");
					submission.write(museum.getText() + "\n");
					submission.write(worth.getText() + "\n");
					submission.write(desc.getText() + "\n");
					submission.close();
					file.close();
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
				home(user, pass, admin, mod);
			}
		});
	
	}
	
	public static void pieceReview(String user, String pass, String admin, String mod) {
		JFrame review = new JFrame();
		review.setSize(1000, 800);
		review.setLayout(null);
        review.getContentPane().setBackground(Color.decode("#E9DAC4"));
		review.setVisible(true);
		
		JButton home = new JButton("Home");
		home.setBounds(10, 10, 100, 25);
		review.add(home);
		
		home.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				review.setVisible(false);
				home(user, pass, admin, mod);
			}
		});
		
		
	}

	public static void createUser() {
		JFrame createU = new JFrame("Create User");
	    createU.setSize(1000, 800);
	    createU.setLayout(null);
        createU.getContentPane().setBackground(Color.decode("#E9DAC4"));
	    createU.setVisible(true);

	    // Title
	    JLabel title = new JLabel("Create Your Account");
	    title.setBounds(420, 30, 300, 30);
	    title.setFont(new Font("Arial", Font.BOLD, 16));
	    createU.add(title);

	    // Username field
	    JLabel usernameLabel = new JLabel("Username:");
	    usernameLabel.setBounds(370, 80, 100, 30);
	    createU.add(usernameLabel);
	    JTextField usernameBox = new JTextField();
	    usernameBox.setBounds(460, 80, 160, 25);
	    createU.add(usernameBox);

	    // Password field
	    JLabel passwordLabel = new JLabel("Password:");
	    passwordLabel.setBounds(370, 120, 100, 30);
	    createU.add(passwordLabel);
	    JPasswordField passwordBox = new JPasswordField();
	    passwordBox.setBounds(460, 120, 160, 25);
	    createU.add(passwordBox);

	    // Confirm Password field
	    JLabel confirmLabel = new JLabel("Confirm Password:");
	    confirmLabel.setBounds(340, 160, 150, 30);
	    createU.add(confirmLabel);
	    JPasswordField confirmBox = new JPasswordField();
	    confirmBox.setBounds(460, 160, 160, 25);
	    createU.add(confirmBox);

	    // Message label
	    JLabel messageLabel = new JLabel("");
	    messageLabel.setBounds(400, 200, 400, 30);
	    messageLabel.setForeground(Color.RED);
	    createU.add(messageLabel);

	    // Submit button
	    JButton submitButton = new JButton("Submit");
	    submitButton.setBounds(450, 240, 100, 25);
	    createU.add(submitButton);

	    // Back button
	    JButton backButton = new JButton("Back to Login");
	    backButton.setBounds(440, 280, 120, 25);
	    createU.add(backButton);

	    // When user clicks Submit
	    submitButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String username = usernameBox.getText().trim();
	            String password = new String(passwordBox.getPassword());
	            String confirm = new String(confirmBox.getPassword());

	            if (username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
	            	messageLabel.setForeground(Color.RED);
	                messageLabel.setText("Please fill out all fields.");
	                return;
	            }

	            if (!password.equals(confirm)) {
	            	messageLabel.setForeground(Color.RED);
	                messageLabel.setText("Passwords do not match.");
	                return;
	            }

	          
	            // Save to Accounts.txt
	            try {
	                FileWriter writer = new FileWriter("DataFiles/Accounts.txt", true);
	                RandomAccessFile file = new RandomAccessFile("DataFiles/Accounts.txt", "rw");
	                file.seek(file.length());
	                writer.write(username + "\n");
	                writer.write(password + "\n");
	                writer.write("Admin: false\n" );
	                writer.write("Mod: false\n" );
	                writer.close();
	                file.close();

	                messageLabel.setForeground(Color.BLUE);
	                messageLabel.setText("Account created successfully!");
	            } catch (IOException ex) {
	            	messageLabel.setForeground(Color.RED);
	                messageLabel.setText("Error creating account.");
	            }
	        }
	    });

	    // Go back to login screen
	    backButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            createU.setVisible(false);
	            try {
	                login();
	            } catch (FileNotFoundException ex) {
	            	messageLabel.setForeground(Color.RED);
	            	messageLabel.setText("Error creating account.");
	            }
	        }
	    });

	}
	
	public static void modUser(String user, String pass, String admin, String mod) {
		JFrame modUser = new JFrame();
		modUser.setSize(1000, 800);
		modUser.setLayout(null);
        modUser.getContentPane().setBackground(Color.decode("#E9DAC4"));
		modUser.setVisible(true);
		
		JButton home = new JButton("Home");
		home.setBounds(10, 10, 100, 25);
		modUser.add(home);
		
		home.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modUser.setVisible(false);
				home(user, pass, admin, mod);
			}
		});
	}
	
	public static void login() throws FileNotFoundException {
		// Creates the login screen
		JFrame loginf = new JFrame("GalleryWalk"); 
		loginf.setSize(1000, 800);
        loginf.setLayout(null);
        loginf.getContentPane().setBackground(Color.decode("#E9DAC4"));
        loginf.setVisible(true);
        
        ImageIcon headerIcon = new ImageIcon("Pictures/Screenshot 2025-11-03 235341.png");

        // Scale image to fit the top of the window
        Image scaledHeader = headerIcon.getImage().getScaledInstance(500, 200, Image.SCALE_SMOOTH);
        JLabel headerLabel = new JLabel(new ImageIcon(scaledHeader));
        headerLabel.setBounds(250, 50, 500, 200); // X, Y, Width, Height
        loginf.add(headerLabel);
        
        // Creates the Login TextFields
        JLabel username = new JLabel("Username: ");
        username.setBounds(360, 210, 100, 100);
        username.setFont(new Font("Arial", Font.BOLD, 16));
        username.setForeground(Color.decode("#4C474A"));
        JLabel password = new JLabel("Password: ");
        password.setBounds(360, 255, 100, 100);
        password.setFont(new Font("Arial", Font.BOLD, 16));
        password.setForeground(Color.decode("#4C474A"));
        JTextField usernameBox = new JTextField();
        usernameBox.setBounds(450, 245, 200, 30);
        JPasswordField passwordBox = new JPasswordField();
        passwordBox.setBounds(450, 290, 200, 30);
        loginf.add(username);
        loginf.add(usernameBox);
        loginf.add(password);
        loginf.add(passwordBox);
        
        // Create login submission button
        JButton login = new JButton("Login");
        login.setBounds(455, 340, 100, 30);
        login.setFont(new Font("Arial", Font.BOLD, 16));
        login.setForeground(Color.decode("#4C474A"));
        loginf.add(login);
        
        // Create User button
        JButton createU = new JButton("Create User");
        createU.setBounds(430, 400, 150, 25);
        createU.setOpaque(false);
        createU.setContentAreaFilled(false);
        createU.setBorderPainted(false);
        createU.setFont(new Font("Arial", Font.BOLD, 16));
        createU.setForeground(Color.decode("#4C474A"));
        loginf.add(createU);
        
        
        // Creates the skip login submission button and adds it to the panel
        JButton skipLogin = new JButton("Skip Login");
        skipLogin.setOpaque(false);
        skipLogin.setContentAreaFilled(false);
        skipLogin.setBorderPainted(false);
        skipLogin.setBounds(430, 450, 150, 25);
        skipLogin.setFont(new Font("Arial", Font.BOLD, 16));
        skipLogin.setForeground(Color.decode("#4C474A"));
        loginf.add(skipLogin);
        loginf.repaint();
        
        // Fail to login Text
        JLabel failedLogin = new JLabel("Failed to Log in, Try Again");
        failedLogin.setBounds(420, 160, 200, 100);
        failedLogin.setForeground(Color.RED);
        failedLogin.setVisible(false);
        loginf.add(failedLogin);
                
        
        // Add an if statement encompassing this to check if account is real
        login.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
            	File file = new File("DataFiles/Accounts.txt");
            	Scanner scanner = null;
				try {
					scanner = new Scanner(file);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	while (scanner.hasNextLine()) {
            		if (scanner.nextLine().equals(usernameBox.getText())) {
            			if (scanner.nextLine().equals(passwordBox.getText())) {
            				String admin = scanner.nextLine();
            				String mod = scanner.nextLine();
            				loginf.setVisible(false);
            				home(usernameBox.getText(), passwordBox.getText(), admin, mod);
            			}
            		}
            	}
            	failedLogin.setVisible(true);
            	loginf.repaint();
            	scanner.close();
        	}
        });
        
        createU.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		loginf.setVisible(false);
        		createUser();
        	}
        });
        
        // Checks for button press and changes screen if check returns true
        skipLogin.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		loginf.setVisible(false);
        		home(null, null, null, null);
        	}
        });
        
        }

}






