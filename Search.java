import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class Search {

	public static String keyword;
	public static ArrayList<Gallery.ArtPiece> piecesKey;
	public static Gallery newGallery;

	
	
	public static void searchPage(String user, String pass, String admin, String mod) {
		JFrame sPage = new JFrame("Search");
		sPage.setSize(1000, 800);
		sPage.setLayout(null);
        sPage.getContentPane().setBackground(Color.decode("#E9DAC4"));
		sPage.setVisible(true);
		
		JButton home = new JButton("Home");
		home.setBounds(10, 10, 100, 25);
		home.setForeground(Color.decode("#4C474A"));
		home.setFont(new Font("Arial", Font.BOLD, 16));
		sPage.add(home);
		
		home.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sPage.setVisible(false);
				UI.home(user, pass, admin, mod);
			}
		});
		
        ImageIcon headerIcon = new ImageIcon("Pictures/Screenshot 2025-11-14 211713.png");

        // Scale image to fit the top of the window
        Image scaledHeader = headerIcon.getImage().getScaledInstance(500, 200, Image.SCALE_SMOOTH);
        JLabel headerLabel = new JLabel(new ImageIcon(scaledHeader));
        headerLabel.setBounds(300, 80, 450, 150); // X, Y, Width, Height
        sPage.add(headerLabel);
		
		// Displays the search bar and prompts user for a keyword to search
		JLabel searchLabel = new JLabel("Search keyword:");
		searchLabel.setBounds(225, 262, 190, 100);
		searchLabel.setForeground(Color.decode("#4C474A"));
		searchLabel.setFont(new Font("Arial", Font.BOLD, 20));
		JTextField searchBox = new JTextField();
        searchBox.setBounds(420, 300, 250, 30);
        JButton enter = new JButton("Search");
        enter.setBounds(695, 300, 100, 30);
        enter.setForeground(Color.decode("#4C474A"));
        enter.setFont(new Font("Arial", Font.BOLD, 16));
        sPage.add(searchLabel);
        sPage.add(searchBox);
        sPage.add(enter);
        
        enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (searchBox.getText().toLowerCase().equals("")) {
					System.out.println("No key entered.");
				} else {
					ArrayList<Gallery.ArtPiece> temp = searchKey(searchBox.getText());
					sPage.setVisible(false);
					Gallery searchG = new Gallery(user,pass,admin,mod,"DataFiles/AcceptedPieces.txt");
					searchG.searchDisp(temp);
					
				}
			}
		});
        
	}
	
	public static ArrayList<Gallery.ArtPiece> searchKey(String key) throws NumberFormatException {
		piecesKey = new ArrayList<>();
		
		if (key == null) {
			System.out.println("No keyword selected");
			return piecesKey;
		}
		
		keyword = key;
		key = key.toLowerCase();
		ArrayList<Gallery.ArtPiece> temp = null;
		try {
			temp = Gallery.loadPieces("Datafiles/AcceptedPieces.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).artist.toLowerCase().contains(key)) {
				if (!piecesKey.contains(temp.get(i))) {
					piecesKey.add(temp.get(i));
				}
			} else if (temp.get(i).description.toLowerCase().contains(key)) {
				if (!piecesKey.contains(temp.get(i))) {
					piecesKey.add(temp.get(i));
				}			
			} else if (temp.get(i).museum.toLowerCase().contains(key)) {
				if (!piecesKey.contains(temp.get(i))) {
					piecesKey.add(temp.get(i));
				}
			} else if (temp.get(i).title.toLowerCase().contains(key)){
				if (!piecesKey.contains(temp.get(i))) {
					piecesKey.add(temp.get(i));
				}
			} else if (Integer.toString(temp.get(i).value).toLowerCase().contains(key)) {
				if (!piecesKey.contains(temp.get(i))) {
					piecesKey.add(temp.get(i));
				}

			} else if (Integer.toString(temp.get(i).year).toLowerCase().contains(key)) {
				if (!piecesKey.contains(temp.get(i))) {
					piecesKey.add(temp.get(i));
				}
			}
			
			for (int j = 0; j < temp.get(i).tags.length; j++) {
				if (temp.get(i).tags[j].toLowerCase().contains(key)) {
					if (!piecesKey.contains(temp.get(i))) {
						piecesKey.add(temp.get(i));
					}
					break;
				}
			}
			
		}
		
		return piecesKey;
	}
	
	public String getKeyword() {
		return keyword;
	}
}
