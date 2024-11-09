package com.example.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class StateSelectionDialog extends JDialog {
    private JComboBox<String> stateDropdown;
    
    private boolean confirmed = false;

    public StateSelectionDialog(Frame parent,Map<String, Integer> states) {
        super(parent, "Select State", true);

        // Set a modern layout and size
        setSize(350, 200);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        // Create a JPanel for content and set a GridBagLayout for better control
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add padding around components

        // Title label
        JLabel label = new JLabel("Select State:");
        label.setFont(new Font("Arial", Font.BOLD, 14)); // Stylish title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(label, gbc);

        // State Dropdown (JComboBox)
        stateDropdown = new JComboBox<>();
        for (String state : states.keySet()) {
        	stateDropdown.addItem(state);
        }
        stateDropdown.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(stateDropdown, gbc);

        // Create a panel for the OK and Cancel buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Center buttons with space in between

        // OK Button
        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Arial", Font.PLAIN, 12));
        okButton.setPreferredSize(new Dimension(100, 30));
        okButton.setBackground(new Color(34, 139, 34)); // Green color
        okButton.setForeground(Color.WHITE);
        okButton.setFocusPainted(false);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmed = true;
                setVisible(false);
            }
        });
        buttonPanel.add(okButton);

        // Cancel Button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 12));
        cancelButton.setPreferredSize(new Dimension(100, 30));
        cancelButton.setBackground(new Color(192, 57, 43)); // Red color
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmed = false;
                setVisible(false);
            }
        });
        buttonPanel.add(cancelButton);

        // Add components to the dialog window
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String getSelectedState() {
        return (String) stateDropdown.getSelectedItem();
    }

    
}
