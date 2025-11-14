import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
		sPage.add(home);
		
		home.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sPage.setVisible(false);
				UI.home(user, pass, admin, mod);
			}
		});
		
		// Displays the search bar and prompts user for a keyword to search
		JLabel searchLabel = new JLabel("Search keyword:");
		searchLabel.setBounds(300, 10, 100, 100);
		JTextField searchBox = new JTextField();
        searchBox.setBounds(420, 50, 250, 30);
        JButton enter = new JButton("Search");
        enter.setBounds(680, 50, 100, 30);
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
