package q6.codejava.swing.download;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import q6.codejava.JFilePicker;

/**
 * A Swing application that downloads file from an HTTP server.
 *
 *
 */
public class SwingFileDownloadHTTP extends JFrame implements
        PropertyChangeListener {
    private final JTextField fieldURL = new JTextField(30);

    private final JFilePicker filePicker = new JFilePicker("Save in directory: ",
            "Browse...");

    private final JTextField fieldFileName = new JTextField(20);

    private final JTextField fieldFileSize = new JTextField(20);

    private final JProgressBar progressBar = new JProgressBar(0, 100);

    public SwingFileDownloadHTTP() {
        super("Swing File Download from HTTP server");

        // set up layout
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);

        // set up components
        filePicker.setMode(JFilePicker.MODE_SAVE);
        filePicker.getFileChooser().setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        JButton buttonDownload = new JButton("Download");
        buttonDownload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                buttonDownloadActionPerformed(event);
            }
        });

        fieldFileName.setEditable(false);
        fieldFileSize.setEditable(false);

        progressBar.setPreferredSize(new Dimension(200, 30));
        progressBar.setStringPainted(true);

        // add components to the frame
        constraints.gridx = 0;
        constraints.gridy = 0;
        JLabel labelURL = new JLabel("Download URL: ");
        add(labelURL, constraints);

        constraints.gridx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        add(fieldURL, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 0.0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        add(filePicker, constraints);

        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(buttonDownload, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;
        JLabel labelFileName = new JLabel("File name: ");
        add(labelFileName, constraints);

        constraints.gridx = 1;
        add(fieldFileName, constraints);

        constraints.gridy = 4;
        constraints.gridx = 0;
        JLabel labelFileSize = new JLabel("File size (bytes): ");
        add(labelFileSize, constraints);

        constraints.gridx = 1;
        add(fieldFileSize, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;
        JLabel labelProgress = new JLabel("Progress:");
        add(labelProgress, constraints);

        constraints.gridx = 1;
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(progressBar, constraints);

        pack();
        setLocationRelativeTo(null);    // center on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * handle click event of the Upload button
     */
    private void buttonDownloadActionPerformed(ActionEvent event) {
        String downloadURL = fieldURL.getText();
        String saveDir = filePicker.getSelectedFilePath();

        // validate input first
        if (downloadURL.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter download URL!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            fieldURL.requestFocus();
            return;
        }

        if (saveDir.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please choose a directory save file!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            progressBar.setValue(0);

            DownloadTask task = new DownloadTask(this, downloadURL, saveDir);
            task.addPropertyChangeListener(this);
            task.execute();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error executing upload task: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    void setFileInfo(String name, int size) {
        fieldFileName.setText(name);
        fieldFileSize.setText(String.valueOf(size));
    }

    /**
     * Update the progress bar's state whenever the progress of download changes.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("progress")) {
            int progress = (Integer) evt.getNewValue();
            progressBar.setValue(progress);
        }
    }

    /**
     * Launch the application
     */
    public static void main(String[] args) {
        try {
            // set look and feel to system dependent
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SwingFileDownloadHTTP().setVisible(true);
            }
        });
    }
}
