package com.example.ui;

import javax.swing.border.EmptyBorder;
import javax.swing.*; 
import javax.swing.table.DefaultTableModel;

import Controllers.CityController; 
import Controllers.StatesController;
import Models.City; 

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CityFrame extends JFrame {

	private static final long serialVersionUID = 1L;
 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CityFrame frame = new CityFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	private JPanel contentPane;
    private JTextField cityIdField;
    private JTextField cityNameField;
    private JTextField pincodeField;
    private JComboBox<String> stateComboBox; 
    private JTable cityTable;
    private DefaultTableModel tableModel;

	private Map<Integer, City> stateCityMap;
	
	public CityFrame() {
		 // Set up frame
        setTitle("City Information");
        setBounds(100, 100, 800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Main content panel
        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);

        // Form panel for input fields with two columns
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Setting a preferred width for all text fields
        Dimension fieldSize = new Dimension(250, 30);

        // City ID (disabled field)
        JLabel cityIdLabel = new JLabel("City ID:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(cityIdLabel, gbc);

        cityIdField = new JTextField();
        cityIdField.setPreferredSize(fieldSize);
        cityIdField.setEnabled(false);
        cityIdField.setForeground(Color.BLACK);
        cityIdField.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 1;
        formPanel.add(cityIdField, gbc);

        // City Name
        JLabel cityNameLabel = new JLabel("City Name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(cityNameLabel, gbc);

        cityNameField = new JTextField();
        cityNameField.setPreferredSize(fieldSize);
        gbc.gridx = 1;
        formPanel.add(cityNameField, gbc);

        // Pincode
        JLabel pincodeLabel = new JLabel("Pincode:");
        gbc.gridx = 2;
        gbc.gridy = 0;
        formPanel.add(pincodeLabel, gbc);

        pincodeField = new JTextField();
        pincodeField.setPreferredSize(fieldSize);
        gbc.gridx = 3;
        formPanel.add(pincodeField, gbc);

        // State ComboBox
        JLabel stateLabel = new JLabel("State:");
        gbc.gridx = 2;
        gbc.gridy = 1;
        formPanel.add(stateLabel, gbc);

        stateComboBox = new JComboBox<>();
        
        stateComboBox.setPreferredSize(fieldSize);
        gbc.gridx = 3;
        formPanel.add(stateComboBox, gbc);

        contentPane.add(formPanel, BorderLayout.NORTH);

        // Table for displaying city data
        tableModel = new DefaultTableModel();
        cityTable = new JTable(tableModel);
        tableModel.addColumn("City ID");
        tableModel.addColumn("City Name");
        tableModel.addColumn("Pincode");
        tableModel.addColumn("State");

        JScrollPane scrollPane = new JScrollPane(cityTable);
        scrollPane.setPreferredSize(new Dimension(700, 250));
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton previousButton = new JButton("Previous");
        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton loadButton = new JButton("Load");
        JButton ClearButton = new JButton("Clear");
        
        buttonPanel.add(previousButton);
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(ClearButton);
        
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        // Action for the previous button to close this frame and go back
        previousButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();  // Close this window to go back to the previous one
            }
        });
        
        
        StatesController statesObject = new StatesController();
        CityController cityObject = new CityController(); 
        
        
        // Load states into the combo box
        Map<String, Integer> states = statesObject.readStates() ;

        // Add state names to the combo box
        for (String state : states.keySet()) {
            stateComboBox.addItem(state);
        }
       
        
        addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean validate=validateFields();
				if(validate) {
					
					String selectedState = (String) stateComboBox.getSelectedItem();
	                int stateId = states.get(selectedState); 
					String pincode = pincodeField.getText().toString();
					String selectedCityName= cityNameField.getText().toString();
					
			        
					int row= cityObject.insertCity(selectedCityName, pincode,stateId);
					
					if(row>0) { 
						LoadData(cityObject, stateId);
						JOptionPane.showMessageDialog(CityFrame.this, "Data Added Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(CityFrame.this, "Something Went wrong", "Error", JOptionPane.ERROR_MESSAGE);
					}
						
						
				}
				
			}
		});
        
        // Button action listener for "Clear"
        ClearButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cityIdField.setText(""); 
				cityNameField.setText("");
				pincodeField.setText(""); 
				stateComboBox.setSelectedIndex(-1);
				
			}
		});
        
        
        
        updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean validate=validateFields();
				if(validate) {
					 if (cityIdField.getText().isEmpty()) {
				            JOptionPane.showMessageDialog(CityFrame.this, "Please Select Data First");      
				      }
					 else {
						 	int cityId =  Integer.parseInt(cityIdField.getText().toString());
						 	String selectedState = (String) stateComboBox.getSelectedItem();
			                int stateId = states.get(selectedState); 
							String pincode = pincodeField.getText().toString();
							String selectedCityName= cityNameField.getText().toString();
							
					        
							int row= cityObject.updateCity(cityId,selectedCityName, pincode,stateId);
							
							if(row>0) { 
								LoadData(cityObject, stateId);
								JOptionPane.showMessageDialog(CityFrame.this, "Data Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
							}
							else {
								JOptionPane.showMessageDialog(CityFrame.this, "Something Went wrong", "Error", JOptionPane.ERROR_MESSAGE);
							}
					 }
					
						
						
				}
				
			}
		});
        // Add action listener for table row selection to prefill form fields
        
        cityTable.getSelectionModel().addListSelectionListener(e -> {
        	 int selectedRow = cityTable.getSelectedRow();
        	 if (selectedRow != -1) {
        		 cityIdField.setText(tableModel.getValueAt(selectedRow, 0).toString());
        		 cityNameField.setText(tableModel.getValueAt(selectedRow, 1).toString());
        		 pincodeField.setText(tableModel.getValueAt(selectedRow, 2).toString());
        		  
        		 int stateId = Integer.parseInt(tableModel.getValueAt(selectedRow, 3).toString());
        		 for (String state : states.keySet()) {
        			    if (states.get(state).equals(stateId)) {
        			        stateComboBox.setSelectedItem(state); 
        			        break;
        			   }
        		}
     
        	 }
        });
        
        
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StateSelectionDialog dialog = new StateSelectionDialog(CityFrame.this,states);
                dialog.setVisible(true);
                if (dialog.isConfirmed()) {
                	String selectedState = dialog.getSelectedState();
                    if (selectedState != null) {
                    	LoadData(cityObject,states.get(selectedState));
                    }
                }
                
            }
        });
        
        
        //Button action listener for "Delete"
        deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = cityTable.getSelectedRow();
				if(selectedRow == -1)
					JOptionPane.showMessageDialog(CityFrame.this, "Please Select Row first", "Error", JOptionPane.ERROR_MESSAGE);
				else {
					int city_id= Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
					int row = cityObject.deleteCity(city_id);
					
					if(row>0) {
						LoadData(cityObject, Integer.parseInt(tableModel.getValueAt(selectedRow, 3).toString()));
						JOptionPane.showMessageDialog(CityFrame.this, "Data Deleted Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(CityFrame.this, "Something Went wrong", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});

 
	}
	
	
    public void LoadData(CityController cities,int state_id) {
    	// Get the migrant data from the database
    	List<City> cityList = cities.readCities(state_id);

        // Clear any existing rows in the table
        tableModel.setRowCount(0);

        // Add rows to the table model based on the fetched migrant data
                       
        for(City city: cityList) {
        	tableModel.addRow(new Object[] {
        		city.getCity_id(),
        		city.getName(),
        		city.getPincode(),
        		city.getState_id()
        	});
        }
    }
	
  
    
    private boolean validateFields() {
    
        // Validate City Name
        String cityName = cityNameField.getText().trim();
        if (cityName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "City Name cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate Pincode
        String pincode = pincodeField.getText().trim();
        if (pincode.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pincode cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!pincode.matches("\\d{6}")) {  // Adjust if your format differs
            JOptionPane.showMessageDialog(this, "Pincode must be a 6-digit number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        

        // Validate State Selection
        if (stateComboBox.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Please select a State.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;   
    }


}


