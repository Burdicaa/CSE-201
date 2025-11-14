import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class Gallery {
	
    public ArrayList<ArtPiece> pieces;
    public static String usr;
    public static String pass;
    public static String admin;
    public static String mod;
    public String fileName;

    public Gallery(String usr, String pass, String admin, String mod, String fileName) {
    	this.usr = usr;
    	this.pass = pass;
    	this.admin = admin;
    	this.mod = mod;
    	this.fileName = fileName;
        this.pieces = loadPieces(fileName);
    }
    
    public Gallery() {
    	
    }

    public void disp() {
        JFrame frame = new JFrame("Art Gallery Viewer");
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.decode("#E9DAC4"));
    
        JButton home = new JButton("Home");
        home.setAlignmentX(JButton.CENTER_ALIGNMENT);
        mainPanel.add(home);
        
        home.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				UI.home(usr, pass, admin, mod);
			}
		});

        for (ArtPiece art : pieces) {
            mainPanel.add(createArtPanel(art, frame));
            mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        // Sets it so the scrollpane starts at the top rather than at position of
        // last added piece
        SwingUtilities.invokeLater(() ->
        scrollPane.getVerticalScrollBar().setValue(0));

        frame.add(scrollPane);
        frame.setVisible(true);
    }
    
    // Creates a Gallery Display for specified Searched Items
    public void searchDisp(ArrayList<Gallery.ArtPiece> pieces) {
        JFrame frame = new JFrame("Art Gallery Viewer");
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.decode("#E9DAC4"));
    
        JButton search = new JButton("Search");
        search.setAlignmentX(JButton.CENTER_ALIGNMENT);
        mainPanel.add(search);
        
        search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
        		Search.searchPage(usr, pass, admin, mod);			}
		});

        for (Gallery.ArtPiece art : pieces) {
            mainPanel.add(createArtPanel(art, frame));
            mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        // Sets it so the scrollpane starts at the top rather than at position of
        // last added piece
        SwingUtilities.invokeLater(() ->
        scrollPane.getVerticalScrollBar().setValue(0));

        frame.add(scrollPane);
        frame.setVisible(true);
    }

    // Used to create the listing for PieceReview page
    public void reviewDisp() {
        JFrame frame = new JFrame("Art Gallery Viewer");
        frame.setSize(1000, 800);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        mainPanel.setBackground(Color.decode("#E9DAC4"));
        
        JButton home = new JButton("Home");
        home.setAlignmentX(JButton.CENTER_ALIGNMENT);
        mainPanel.add(home);
        
        home.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				UI.home(usr, pass, admin, mod);
			}
		});

        for (ArtPiece art : pieces) {
            mainPanel.add(createReviewPanel(art));
            mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        // Sets it so the scrollpane starts at the top rather than at position of
        // last added piece
        SwingUtilities.invokeLater(() ->
        scrollPane.getVerticalScrollBar().setValue(0));
        
        frame.add(scrollPane);
        frame.setVisible(true);
    }
    
    public ArrayList<String> dispKey() {
        ArrayList<String> keywords = new ArrayList<>();
        for (ArtPiece art : pieces) {
            for (String tag : art.tags) {
                keywords.add(tag.toLowerCase());
            }
        }
        
        return new ArrayList<>(keywords);
    }

    private static JPanel createArtPanel(ArtPiece art, JFrame frame) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        panel.setLayout(new BorderLayout(10, 10));
        panel.setMaximumSize(new Dimension(750, 300));

        JLabel imageLabel;
        try {
            ImageIcon icon = new ImageIcon("Pictures/" + art.imageFileName);
            Image scaled = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            imageLabel = new JLabel(new ImageIcon(scaled));
        } catch (Exception e) {
            imageLabel = new JLabel("Image not found");
        }
        panel.add(imageLabel, BorderLayout.WEST);

        JTextArea info = new JTextArea();
        info.setEditable(false);
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        info.setText(String.format(
                "%s (%d)\nTitle: %s\nMuseum: %s\nValue: %s\nTags: %s\n\n%s",
                art.artist, art.year, art.title, art.museum,
                (art.value > 0 ? "$" + String.format("%,d", art.value) : "Unknown"),
                String.join(", ", art.tags), art.description
        ));
        
        JButton comments = new JButton("Comments");
        panel.add(comments, BorderLayout.SOUTH);
        
        comments.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		frame.setVisible(false);
        		Comments c = new Comments(art.title, usr, pass, admin, mod);
        		
        	}
        });
        
        panel.add(info, BorderLayout.CENTER);

        return panel;
    }
    
    // Helps the reviewDisp
    private JPanel createReviewPanel(ArtPiece art) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        panel.setLayout(new BorderLayout(10, 10));
        panel.setMaximumSize(new Dimension(750, 300));

        JLabel imageLabel;
        try {
            ImageIcon icon = new ImageIcon("Pictures/" + art.imageFileName);
            Image scaled = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            imageLabel = new JLabel(new ImageIcon(scaled));
        } catch (Exception e) {
            imageLabel = new JLabel("Image not found");
        }
        panel.add(imageLabel, BorderLayout.WEST);

        JTextArea info = new JTextArea();
        info.setEditable(false);
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        info.setText(String.format(
                "%s (%d)\nTitle: %s\nMuseum: %s\nValue: %s\nTags: %s\n\n%s",
                art.artist, art.year, art.title, art.museum,
                (art.value > 0 ? "$" + String.format("%,d", art.value) : "Unknown"),
                String.join(", ", art.tags), art.description
        ));
        
        JPanel buttons = new JPanel();
        JButton accept = new JButton("Accept");
        accept.setSize(100, 50);  
        JButton deny = new JButton("Deny");
        deny.setSize(100, 50);
        buttons.add(accept);
        buttons.add(deny);
        panel.add(buttons, BorderLayout.SOUTH);
        
        accept.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		try {
        			// moves items to AcceptedPieces.txt
					FileWriter Accepted = new FileWriter("DataFiles/AcceptedPieces.txt", true);
					RandomAccessFile Acceptedfile = new RandomAccessFile("DataFiles/AcceptedPieces.txt", "rw");
					Acceptedfile.seek(Acceptedfile.length());
					Accepted.write(art.artist + "\n");
					Accepted.write(art.year + "\n");
					Accepted.write(art.title + "\n");
					Accepted.write(art.imageFileName + "\n");
					Accepted.write(art.museum + "\n");
					Accepted.write(art.value + "\n");
					Accepted.write(String.join(", ", art.tags) + "\n");
					Accepted.write("+" + art.description + "+" + "\n");
					Accepted.close();
					Acceptedfile.close();
        			
        			// Modifies the Submissions file to no longer contain that piece
					List<String> lines = Files.readAllLines(Paths.get("DataFiles/Submissions.txt"));
					FileWriter submission = new FileWriter("DataFiles/Submissions.txt", false);
					for (int i = 0; i < lines.size();i++) {
						if (lines.get(i).equals(art.artist) || lines.get(i).equals("" + art.year) || lines.get(i).equals(art.title) || lines.get(i).equals(art.museum) || lines.get(i).equals("" + art.value) ||
								lines.get(i).equals(String.join(", ", art.tags)) || lines.get(i).equals("+" + art.description + "+") || lines.get(i).equals(art.imageFileName)) {
							continue;							
						}
						else {
							submission.write(lines.get(i) + "\n");
						}
					}
					submission.close();
					panel.setVisible(false);
					panel.repaint();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        
        deny.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		try {
        			
        			// Modifies the Submissions file to no longer contain that piece
					List<String> lines = Files.readAllLines(Paths.get("DataFiles/Submissions.txt"));
					FileWriter submission = new FileWriter("DataFiles/Submissions.txt", false);
					for (int i = 0; i < lines.size();i++) {
						if (lines.get(i).equals(art.artist) || lines.get(i).equals("" + art.year) || lines.get(i).equals(art.title) || lines.get(i).equals(art.museum) || lines.get(i).equals("" + art.value) ||
								lines.get(i).equals(String.join(", ", art.tags)) || lines.get(i).equals("+" + art.description + "+") || lines.get(i).equals(art.imageFileName)) {
							continue;							
						}
						else {
							submission.write(lines.get(i) + "\n");
						}
					}
					submission.close();
					panel.setVisible(false);
					panel.repaint();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        
        panel.add(info, BorderLayout.CENTER);

        return panel;
    }
  
    static ArrayList<ArtPiece> loadPieces(String fileName) {
        ArrayList<Gallery.ArtPiece> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String artist = line.trim();
                int year = Integer.parseInt(reader.readLine().trim());
                String title = reader.readLine().trim();
                String imageFile = reader.readLine().trim();
                String museum = reader.readLine().trim();

                String possibleValueOrTags = reader.readLine().trim();
                int value = 0;
                String tagsLine;

                if (possibleValueOrTags.matches("\\d+")) {
                    value = Integer.parseInt(possibleValueOrTags);
                    tagsLine = reader.readLine().trim();
                } else {
                    tagsLine = possibleValueOrTags;
                }

                String[] tags = tagsLine.split(",\\s*");

                StringBuilder descBuilder = new StringBuilder();
                String descLine = reader.readLine();
                if (descLine != null && descLine.startsWith("+")) {
                    descLine = descLine.substring(1);

                    while (descLine != null && !descLine.endsWith("+")) {
                        descBuilder.append(descLine).append(" ");
                        descLine = reader.readLine();
                    }

                    if (descLine != null && descLine.endsWith("+")) {
                        descBuilder.append(descLine, 0, descLine.length() - 1);
                    }
                }

                String description = descBuilder.toString().trim();
                
                list.add(new Gallery().new ArtPiece(artist, year, title, imageFile, museum, value, tags, description));

                while ((line = reader.readLine()) != null && line.trim().isEmpty()) {
                }

                if (line != null) {
                    artist = line.trim();
                    year = Integer.parseInt(reader.readLine().trim());
                    title = reader.readLine().trim();
                    imageFile = reader.readLine().trim();
                    museum = reader.readLine().trim();

                    possibleValueOrTags = reader.readLine().trim();
                    value = 0;

                    if (possibleValueOrTags.matches("\\d+")) {
                        value = Integer.parseInt(possibleValueOrTags);
                        tagsLine = reader.readLine().trim();
                    } else {
                        tagsLine = possibleValueOrTags;
                    }

                    tags = tagsLine.split(",\\s*");

                    descBuilder = new StringBuilder();
                    descLine = reader.readLine();
                    if (descLine != null && descLine.startsWith("+")) {
                        descLine = descLine.substring(1);

                        while (descLine != null && !descLine.endsWith("+")) {
                            descBuilder.append(descLine).append(" ");
                            descLine = reader.readLine();
                        }

                        if (descLine != null && descLine.endsWith("+")) {
                            descBuilder.append(descLine, 0, descLine.length() - 1);
                        }
                    }

                    description = descBuilder.toString().trim();

                    list.add(new Gallery().new ArtPiece(artist, year, title, imageFile, museum, value, tags, description));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    class ArtPiece {
        String artist;
        int year;
        String title;
        String imageFileName;
        String museum;
        int value;
        String[] tags;
        String description;

        private ArtPiece(String artist, int year, String title, String imageFileName,
                        String museum, int value, String[] tags, String description) {
            this.artist = artist;
            this.year = year;
            this.title = title;
            this.imageFileName = imageFileName;
            this.museum = museum;
            this.value = value;
            this.tags = tags;
            this.description = description;
        }
    }
}

