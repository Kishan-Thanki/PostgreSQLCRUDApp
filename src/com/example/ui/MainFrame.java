package com.example.ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.swing.border.EmptyBorder;
import javax.swing.*; 
import com.toedter.calendar.JCalendar;

import Controllers.CityController;
import Controllers.MigrantController;
import Controllers.StatesController;
import Models.City;
import Models.Migrants;

import java.util.*; 
import java.awt.Color;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JLabel lblFirstName;
    private JTextField textField_1;
    private JLabel lblMiddleName;
    private JTextField textField_2;
    private JLabel lblLastName;
    private JTextField textField_3;
    private JLabel lblBirthdate;
    private JLabel lblGender;
    private JLabel lblState;
    private JComboBox<String> stateComboBox;
    private JComboBox<String> cityComboBox;
    private JLabel lblCity;
    private JLabel lblMartialStatus;
    private JRadioButton rdbtnSingle;
    private JRadioButton rdbtnMarried;
    private JLabel lblNewLabel_1;
    private JTextField textField_4;
    private JLabel lblNewLabel_2;
    private JTextField textField_5;

    private DefaultTableModel tableModel;
    private JTable migrantTable;
	private Map<Integer, City> stateCityMap;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
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
    public MainFrame() {
        // Set default close operation and window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(10, 10, 1000, 800);  // Window size (width x height)

        // Initialize content panel with empty border
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));  // Padding
        setContentPane(contentPane);
        
        // Set Absolute Layout for manual positioning
        contentPane.setLayout(null);
        
        //Create a heading label with custom font and styling
        JLabel headingLabel = new JLabel("Migrant Information", SwingConstants.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 28));
        headingLabel.setForeground(new Color(0, 102, 204));

        // Manually set the bounds for the heading label to center it at the top
        headingLabel.setBounds(300, 20, 400, 40); // Adjust x, width as needed for centering

        // Add heading label to the content pane
        contentPane.add(headingLabel);
        
        // Create and set bounds for Migrant_ID label
        JLabel lblNewLabel = new JLabel("Migrant_ID");
        lblNewLabel.setBounds(10, 64, 80, 20);  // x, y, width, height
        contentPane.add(lblNewLabel);

        // Create and set bounds for the Migrant ID text field
        textField = new JTextField();
        textField.setEnabled(false);
        textField.setBounds(100, 64, 100, 25);  // x, y, width, height
        contentPane.add(textField);
        textField.setColumns(10);
        textField.setForeground(Color.BLACK);  
        textField.setFont(new Font("Arial", Font.BOLD, 12));
        
        lblFirstName = new JLabel("First Name");
        lblFirstName.setBounds(10, 119, 80, 20);
        contentPane.add(lblFirstName);
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(100, 119, 200, 25);
        contentPane.add(textField_1);
        
        lblMiddleName = new JLabel("Middle Name");
        lblMiddleName.setBounds(342, 119, 80, 20);
        contentPane.add(lblMiddleName);
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(432, 119, 200, 25);
        contentPane.add(textField_2);
        
        lblLastName = new JLabel("Last Name");
        lblLastName.setBounds(671, 119, 80, 20);
        contentPane.add(lblLastName);
        
        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(761, 119, 200, 25);
        contentPane.add(textField_3);
        
        lblBirthdate = new JLabel("BirthDate");
        lblBirthdate.setBounds(10, 178, 60, 20);
        contentPane.add(lblBirthdate);
        
        JCalendar calendar = new JCalendar();
        calendar.setBounds(100, 178, 250, 152);
        contentPane.add(calendar);
        
        lblGender = new JLabel("Gender");
        lblGender.setBounds(392, 178, 60, 20);
        contentPane.add(lblGender);
        
        // Create a ButtonGroup for the radio buttons
        ButtonGroup genderGroup = new ButtonGroup();

        // Create and add Male radio button
        JRadioButton rdbtnNewRadioButton = new JRadioButton("Male");
        rdbtnNewRadioButton.setBounds(482, 178, 60, 21);
        contentPane.add(rdbtnNewRadioButton);

        // Create and add Female radio button
        JRadioButton rdbtnFemale = new JRadioButton("Female");
        rdbtnFemale.setBounds(562, 178, 70, 21);
        contentPane.add(rdbtnFemale);

        // Add the radio buttons to the ButtonGroup
        genderGroup.add(rdbtnNewRadioButton);
        genderGroup.add(rdbtnFemale);
        
        lblState = new JLabel("State");
        lblState.setBounds(10, 371, 30, 20);
        contentPane.add(lblState);

        // Initialize ComboBox for States
        stateComboBox = new JComboBox<>();
        stateComboBox.setBounds(100, 370, 200, 25);
        contentPane.add(stateComboBox);

        // Initialize ComboBox for Cities
        cityComboBox = new JComboBox<>();
        cityComboBox.setBounds(432, 370, 200, 25);
        contentPane.add(cityComboBox);
        
        lblCity = new JLabel("City");
        lblCity.setBounds(392, 371, 30, 20);
        contentPane.add(lblCity);
        
        // Update Martial Status section
        lblMartialStatus = new JLabel("Martial Status");
        lblMartialStatus.setBounds(392, 229, 80, 20);
        contentPane.add(lblMartialStatus);
        
        // Create a ButtonGroup for the marital status radio buttons
        ButtonGroup maritalStatusGroup = new ButtonGroup();

        // Create and add "Single" radio button
        rdbtnSingle = new JRadioButton("Single");
        rdbtnSingle.setBounds(565, 229, 70, 21);
        contentPane.add(rdbtnSingle);

        // Create and add "Married" radio button
        rdbtnMarried = new JRadioButton("Married");
        rdbtnMarried.setBounds(482, 229, 80, 21);
        contentPane.add(rdbtnMarried);

        // Add both radio buttons to the ButtonGroup
        maritalStatusGroup.add(rdbtnSingle);
        maritalStatusGroup.add(rdbtnMarried);
        
        lblNewLabel_1 = new JLabel("Contact No");
        lblNewLabel_1.setBounds(10, 433, 80, 20);
        contentPane.add(lblNewLabel_1);
        
        textField_4 = new JTextField();
        textField_4.setColumns(10);
        textField_4.setBounds(100, 431, 100, 25);
        contentPane.add(textField_4);
        
        lblNewLabel_2 = new JLabel("Email");
        lblNewLabel_2.setBounds(10, 481, 80, 20);
        contentPane.add(lblNewLabel_2);
        
        textField_5 = new JTextField();
        textField_5.setColumns(10);
        textField_5.setBounds(100, 479, 200, 25);
        contentPane.add(textField_5);
        
        JButton btnNewButton = new JButton("Add");
        btnNewButton.setForeground(Color.BLACK);
        btnNewButton.setBackground(Color.WHITE);
        btnNewButton.setBounds(100, 547, 85, 21);
        contentPane.add(btnNewButton);
        
        JButton btnUpdate = new JButton("Update");
        btnUpdate.setForeground(Color.BLACK);
        btnUpdate.setBackground(Color.WHITE);
        btnUpdate.setBounds(250, 547, 85, 21);
        contentPane.add(btnUpdate);
        
        // Other existing buttons like Add, Update, etc.

        JButton btnNext = new JButton("Next");
        btnNext.setForeground(Color.BLACK);
        btnNext.setBackground(Color.WHITE);
        btnNext.setBounds(833, 547, 85, 21);  // Positioning it after the "Clear" button
        contentPane.add(btnNext);
        
        JButton btnDelete = new JButton("Delete");
        btnDelete.setForeground(Color.BLACK);
        btnDelete.setBackground(Color.WHITE);
        btnDelete.setBounds(403, 547, 85, 21);
        contentPane.add(btnDelete);
        
        JButton btnLoad = new JButton("Load");
        btnLoad.setForeground(Color.BLACK);
        btnLoad.setBackground(Color.WHITE);
        btnLoad.setBounds(547, 547, 85, 21);
        contentPane.add(btnLoad);
        
        JButton btnClear = new JButton("Clear");
        btnClear.setForeground(Color.BLACK);
        btnClear.setBackground(Color.WHITE);
        btnClear.setBounds(690, 547, 85, 21);  
        contentPane.add(btnClear);

 
        StatesController statesObject = new StatesController();
        CityController cityObject = new CityController();
        MigrantController migrants = new MigrantController();
        
        
        // Load states into the combo box
        Map<String, Integer> states = statesObject.readStates() ;

        // Add state names to the combo box
        for (String state : states.keySet()) {
            stateComboBox.addItem(state);
        }
        
        
        // Add an action listener to the state combo box to update cities based on selected state
        stateComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedState = (String) stateComboBox.getSelectedItem();
                int stateId = states.get(selectedState); // Get state_id for selected state
                
                
                populateCityComboBox(stateId,cityObject);
            }
        });

     // Create a table model for the migrant data
        tableModel = new DefaultTableModel();
        migrantTable = new JTable(tableModel);

        // Define columns for the migrant table
        tableModel.addColumn("Migrant ID");
        tableModel.addColumn("First Name");
        tableModel.addColumn("Middle Name");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("Birthdate");
        tableModel.addColumn("Gender");
        tableModel.addColumn("City");
        tableModel.addColumn("Marital Status");
        tableModel.addColumn("Contact No");
        tableModel.addColumn("Email");

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(migrantTable);
        scrollPane.setBounds(10, 580, 950, 180);
        contentPane.add(scrollPane);
        
        
        //button action listener for "Add"
        
        btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean validate=validateForm(textField_1, textField_3, rdbtnNewRadioButton, rdbtnFemale, rdbtnMarried, rdbtnSingle,stateComboBox,
						cityComboBox);
				if(validate) {
					String selectedCityName = (String) cityComboBox.getSelectedItem();
					int selectedCityId=0;
					for (Map.Entry<Integer, City> entry : stateCityMap.entrySet()) {
					    if (entry.getValue().getName().equals(selectedCityName)) {
					        selectedCityId = entry.getKey();
					        // Use the selectedCityId for data insertion
					        break;
					    }
					}
				
					
			        Date selectedDate = calendar.getDate(); 
			        java.sql.Date sqlDate = new java.sql.Date(selectedDate.getTime());
			        String gen="";
			        gen = rdbtnNewRadioButton.isSelected() ?  "M" : "F";
			        
			        Boolean is_married;
			        is_married = rdbtnMarried.isSelected() ? true : false;
			        
			        String middle_name = textField_2.getText().toString();
			        middle_name = middle_name.isEmpty() ? null : middle_name;
			        
			        String contact_no = textField_4.getText().toString();
			        contact_no = contact_no.isEmpty() ? null : contact_no;
			        
			        String email = textField_5.getText().toString();
			        email = email.isEmpty() ? null : email;
			        	
			        
					int row=migrants.insertMigrant(selectedCityId,textField_1.getText(),middle_name, textField_3.getText(),
							sqlDate, contact_no,email, gen,is_married);
					
					if(row>0) {
						LoadData(migrants);
						JOptionPane.showMessageDialog(MainFrame.this, "Data Inserted Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(MainFrame.this, "Something Went wrong", "Error", JOptionPane.ERROR_MESSAGE);
					}
						
						
				}
			}
		});
        
        // Add an action listener to open the new GUI frame
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the new GUI
                CityFrame cityFrame = new CityFrame(); 
                cityFrame.setVisible(true);
            }
        });
        
        // Button action listener for "Clear"
        btnClear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText(""); 
				textField_1.setText("");
				textField_2.setText(""); 
				textField_3.setText(""); 
				textField_4.setText(""); 
				textField_5.setText(""); 
				Date today = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
				calendar.setDate(today); 
				cityComboBox.setSelectedIndex(-1);
				genderGroup.clearSelection();
				maritalStatusGroup.clearSelection();
			}
		});

        // Button action listener for "Load"
        btnLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoadData(migrants);
            }
        });
        
        //Button action listener for "Delete"
        btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = migrantTable.getSelectedRow();
				if(selectedRow == -1)
					JOptionPane.showMessageDialog(MainFrame.this, "Please Select Row first", "Error", JOptionPane.ERROR_MESSAGE);
				else {
					int migrant_id= Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
					int row = migrants.deleteMigrant(migrant_id);
					
					if(row>0) {
						LoadData(migrants);
						JOptionPane.showMessageDialog(MainFrame.this, "Data Deleted Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(MainFrame.this, "Can't Delete this Migrant", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
        
        
        // Add action listener for table row selection to prefill form fields
        
        migrantTable.getSelectionModel().addListSelectionListener(e -> {
        	 int selectedRow = migrantTable.getSelectedRow();
        	 if (selectedRow != -1) {
        		 textField.setText(tableModel.getValueAt(selectedRow, 0).toString());
        		 textField_1.setText(tableModel.getValueAt(selectedRow, 1).toString());
        		 Object middle_name= tableModel.getValueAt(selectedRow, 2);
        		 textField_2.setText(middle_name!=null ? middle_name.toString() : "");
        		 textField_3.setText(tableModel.getValueAt(selectedRow, 3).toString());
        		 
        		 String dateString = tableModel.getValueAt(selectedRow, 4).toString();
        		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        		 try {
					Date date = sdf.parse(dateString);
					calendar.setDate(date);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		 
        		 String gender = tableModel.getValueAt(selectedRow, 5).toString();
        		 if(gender.equalsIgnoreCase("m"))
        			 rdbtnNewRadioButton.setSelected(true);
        		 else
        			 rdbtnFemale.setSelected(true);
        		 
        		 int cityId = Integer.parseInt(tableModel.getValueAt(selectedRow, 6).toString());
        		 City cityRow = cityObject.getCityById(cityId);
        		 int stateId = cityRow.getState_id(); 
        		 for (String state : states.keySet()) {
        			    if (states.get(state).equals(stateId)) {
        			        stateComboBox.setSelectedItem(state); 
        			        break;
        			   }
        		}
        		 
        		List<City> cities = cityObject.readCities(stateId); 
        		cityComboBox.removeAllItems();
        		if(stateCityMap!=null)
        	        stateCityMap.clear();
        		
        		for (City city : cities) {
                    stateCityMap.put(city.getCity_id(), city);
                    cityComboBox.addItem(city.getName());
                    if (city.getCity_id() == cityId) {  // Compare city_id
        		        cityComboBox.setSelectedItem(city.getName());  // Set the pre-selected city
        		    }
                }
        	        		
        		 Boolean ma_status = Boolean.parseBoolean(tableModel.getValueAt(selectedRow, 7).toString());
        		 if(ma_status)
        			 rdbtnMarried.setSelected(true);
        		 else
        			 rdbtnSingle.setSelected(true);
        		 
        		 Object contact_no = tableModel.getValueAt(selectedRow, 8);
        		 textField_4.setText(contact_no!= null ? contact_no.toString() : "");
        		 
        		 Object email = tableModel.getValueAt(selectedRow, 9);
        		 textField_5.setText(email !=null ? email.toString() : "");
        	 }
        });
        
        
        // Button action listener for "Update"
        
        btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				boolean validate=validateForm(textField_1, textField_3, rdbtnNewRadioButton, rdbtnFemale, rdbtnMarried, rdbtnSingle,stateComboBox,
						cityComboBox);
				if(validate) {
					String migrant_id= textField.getText().toString();
					if(migrant_id == "")
						JOptionPane.showMessageDialog(MainFrame.this, "Please Select Data", "Error", JOptionPane.ERROR_MESSAGE);
					else {
						String selectedCityName = (String) cityComboBox.getSelectedItem();
						int selectedCityId=0;
						for (Map.Entry<Integer, City> entry : stateCityMap.entrySet()) {
						    if (entry.getValue().getName().equals(selectedCityName)) {
						        selectedCityId = entry.getKey();
						        // Use the selectedCityId for data insertion
						        break;
						    }
						}
					
						
				        Date selectedDate = calendar.getDate(); 
				        java.sql.Date sqlDate = new java.sql.Date(selectedDate.getTime());
				        String gen="";
				        gen = rdbtnNewRadioButton.isSelected() ?  "M" : "F";
				        
				        Boolean is_married;
				        is_married = rdbtnMarried.isSelected() ? true : false;
				        	
				        String middle_name = textField_2.getText().toString();
				        middle_name = middle_name.isEmpty() ? null : middle_name;
				        
				        String contact_no = textField_4.getText().toString();
				        contact_no = contact_no.isEmpty() ? null : contact_no;
				        
				        String email = textField_5.getText().toString();
				        email = email.isEmpty() ? null : email;
				        
						int row=migrants.updateMigrant(Integer.parseInt(migrant_id),selectedCityId,textField_1.getText(),middle_name, textField_3.getText(),
								sqlDate, contact_no, email, gen,is_married);
						
						if(row>0) {
							LoadData(migrants);
							JOptionPane.showMessageDialog(MainFrame.this, "Data Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(MainFrame.this, "Something Went wrong", "Error", JOptionPane.ERROR_MESSAGE);
						}
							

					}
											
				}
			
			}
		});
        
    }
    
    
    

    
    
    public void LoadData(MigrantController migrants) {
    	// Get the migrant data from the database
    	List<Migrants> migrantsList = migrants.readMigrants();

        // Clear any existing rows in the table
        tableModel.setRowCount(0);

        // Add rows to the table model based on the fetched migrant data
                       
        for(Migrants migrant: migrantsList) {
        	tableModel.addRow(new Object[] {
        		migrant.getMigrants_id(),
        		migrant.getFirst_name(),
        		migrant.getMiddle_name(),
        		migrant.getLast_name(),
        		migrant.getBirthdate(),
        		migrant.getGender(),
        		migrant.getBirthplace_id(),
        		migrant.isIs_married(),
        		migrant.getContact_no(),
        		migrant.getEmail()
        	});
        }
    }
    
    private void populateCityComboBox(int stateId,CityController cityObject) {
        List<City> cities = cityObject.readCities(stateId);
        cityComboBox.removeAllItems();
        if(stateCityMap!=null)
        stateCityMap.clear();

        // Create a local cityMap for this state
        Map<Integer, City> stateCityMap = new HashMap<>();
        for (City city : cities) {
            stateCityMap.put(city.getCity_id(), city);
            cityComboBox.addItem(city.getName());
        }

        // Store the stateCityMap for later use (e.g., in a class variable)
        // You might need to consider synchronization if multiple threads access it.
        this.stateCityMap = stateCityMap; // Assuming a class variable
    }

    public boolean validateForm(JTextField firstNameField, JTextField lastNameField, 
                               JRadioButton maleRadioButton,JRadioButton femaleRadioButton, JRadioButton marriedRadioButton, 
                                JRadioButton unmarriedRadioButton,JComboBox<String> stateComboBox, JComboBox<String> cityComboBox) {
        // Check if first name or last name is empty
        if (firstNameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "First Name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (lastNameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Last Name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
 

        // Check if gender is selected (either male or female)
        if (!maleRadioButton.isSelected() && !femaleRadioButton.isSelected()) {
            JOptionPane.showMessageDialog(this, "Please select a gender", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Check if marital status is selected (either married or unmarried)
        if (!marriedRadioButton.isSelected() && !unmarriedRadioButton.isSelected()) {
            JOptionPane.showMessageDialog(this, "Please select a marital status", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Check if a city is selected from the combo box
        if (stateComboBox.getSelectedItem() == null || stateComboBox.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a State", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Check if a city is selected from the combo box
        if (cityComboBox.getSelectedItem() == null || cityComboBox.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a city", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // If all fields are valid, return true
        return true;
    }

}
