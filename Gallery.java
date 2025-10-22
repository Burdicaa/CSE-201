import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Gallery {
	
    private ArrayList<ArtPiece> pieces;

    public Gallery(String fileName) {
        this.pieces = loadPieces(fileName);
    }

    public void disp() {
        JFrame frame = new JFrame("Art Gallery Viewer");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        for (ArtPiece art : pieces) {
            mainPanel.add(createArtPanel(art));
            mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);
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

    private JPanel createArtPanel(ArtPiece art) {
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
        panel.add(info, BorderLayout.CENTER);

        return panel;
    }

    
    private ArrayList<ArtPiece> loadPieces(String fileName) {
        ArrayList<ArtPiece> list = new ArrayList<>();

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

                list.add(new ArtPiece(artist, year, title, imageFile, museum, value, tags, description));

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

                    list.add(new ArtPiece(artist, year, title, imageFile, museum, value, tags, description));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    
    private class ArtPiece {
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
