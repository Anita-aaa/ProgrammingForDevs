package q7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SocialMediaAppGUI extends JFrame {
    private List<User> users;
    private User currentUser;
    private JTextArea newsFeedTextArea;
    private JTextField postTextField;

    public SocialMediaAppGUI() {
        super("Social Media App");
        users = new ArrayList<>();
        currentUser = null;

        // Initialize GUI components
        JPanel topPanel = new JPanel();
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);
        JButton loginButton = new JButton("Login");
        JButton signUpButton = new JButton("Sign Up");

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        newsFeedTextArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(newsFeedTextArea);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        postTextField = new JTextField(20);
        JButton postButton = new JButton("Post");
        bottomPanel.add(postTextField, BorderLayout.CENTER);
        bottomPanel.add(postButton, BorderLayout.EAST);

        // Add action listeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                User user = findUser(username);
                if (user != null) {
                    currentUser = user;
                    updateNewsFeed();
                } else {
                    JOptionPane.showMessageDialog(SocialMediaAppGUI.this, "User not found. Please sign up.");
                }
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                if (!username.isEmpty()) {
                    User newUser = new User(username);
                    users.add(newUser);
                    currentUser = newUser;
                    updateNewsFeed();
                } else {
                    JOptionPane.showMessageDialog(SocialMediaAppGUI.this, "Please enter a username.");
                }
            }
        });

        postButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentUser != null) {
                    String postContent = postTextField.getText();
                    if (!postContent.isEmpty()) {
                        currentUser.addPost(postContent);
                        updateNewsFeed();
                        postTextField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(SocialMediaAppGUI.this, "Please enter something to post.");
                    }
                } else {
                    JOptionPane.showMessageDialog(SocialMediaAppGUI.this, "Please log in or sign up.");
                }
            }
        });

        // Add components to the frame
        topPanel.add(usernameLabel);
        topPanel.add(usernameField);
        topPanel.add(loginButton);
        topPanel.add(signUpButton);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private User findUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    private void updateNewsFeed() {
        newsFeedTextArea.setText("");
        if (currentUser != null) {
            List<Post> posts = currentUser.getPosts();
            for (Post post : posts) {
                newsFeedTextArea.append(post.getContent() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SocialMediaAppGUI();
            }
        });
    }
}
