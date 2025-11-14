import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;


public class Comments {

    public Comments(String pieceTitle, String usr, String pass, String admin, String mod) {
        JFrame frame = new JFrame("Comments for: " + pieceTitle);
        frame.setSize(1000, 800);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.decode("#E9DAC4"));
        frame.setVisible(true);

        // Home button
        JButton homeBtn = new JButton("Gallery");
        homeBtn.setBounds(10, 10, 100, 25);
        frame.add(homeBtn);
        homeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                // Go back to gallery with same user/admin info
                Gallery g = new Gallery(usr, pass, admin, mod, "DataFiles/AcceptedPieces.txt");
                g.disp();
            }
        });

        // Create comments folder if it doesn't exist
        File folder = new File("Comments");
        if (!folder.exists()) folder.mkdir();

        File commentFile = new File(folder, pieceTitle + ".txt");
        try {
            if (!commentFile.exists()) commentFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Display existing comments
        JTextArea commentArea = new JTextArea();
        commentArea.setEditable(false);
        commentArea.setLineWrap(true);
        commentArea.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(commentArea);
        scroll.setBounds(50, 50, 900, 500);
        frame.add(scroll);

        try (BufferedReader reader = new BufferedReader(new FileReader(commentFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                commentArea.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Input field for new comment
        JTextField newComment = new JTextField();
        newComment.setBounds(50, 570, 700, 30);
        frame.add(newComment);

        JButton addBtn = new JButton("Add Comment");
        addBtn.setBounds(770, 570, 150, 30);
        frame.add(addBtn);

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String comment = newComment.getText().trim();
                if (!comment.isEmpty()) {
                    try (FileWriter writer = new FileWriter(commentFile, true)) {
                    	if (usr == null) {
                            writer.write( "Guest: " + comment + "\n");
                            commentArea.append("Guest: " + comment + "\n");
                    	}
                    	else {
                    		writer.write(usr + ": " + comment + "\n");
                    		commentArea.append(usr + ": " + comment + "\n");
                    	}
                        newComment.setText("");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        // Delete comment button (only visible to admin/mod)
        if ("Admin: true".equals(admin) || "Mod: true".equals(mod)) {
            JButton deleteBtn = new JButton("Delete Last Comment");
            deleteBtn.setBounds(50, 610, 200, 30);
            frame.add(deleteBtn);

            deleteBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        List<String> lines = new ArrayList<>();
                        BufferedReader reader = new BufferedReader(new FileReader(commentFile));
                        String line;
                        while ((line = reader.readLine()) != null) lines.add(line);
                        reader.close();

                        if (!lines.isEmpty()) {
                            lines.remove(lines.size() - 1); // remove last comment
                            BufferedWriter writer = new BufferedWriter(new FileWriter(commentFile));
                            for (String l : lines) writer.write(l + "\n");
                            writer.close();

                            // Refresh comment area
                            commentArea.setText("");
                            for (String l : lines) commentArea.append(l + "\n");
                        }

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
    }
}


