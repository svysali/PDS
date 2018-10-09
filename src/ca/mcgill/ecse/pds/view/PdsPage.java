package ca.mcgill.ecse.pds.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ca.mcgill.ecse.pds.controller.InvalidInputException;
import ca.mcgill.ecse.pds.controller.PdsController;
import ca.mcgill.ecse.pds.model.Ingredient;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.JTextPane;

public class PdsPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -169243617689486922L;
	private JPanel contentPanel;
	private JTabbedPane tabbedPanel;
	private GroupLayout gl_contentPanel;
	
	//Ingredient Panel Elements
	private JPanel ingredientsPanel;
	private JScrollPane ingredientOverviewScrollPane;
	private JTextField fldNewIngredient;
	private JTextField fldNewIngredientPrice;
	private JTextField fldUpdatedPrice;
	private JComboBox<String> cBRemoveIngredient;
	private JComboBox<String> cBUpdateIngredient;
	private JTable ingredientTable;
	private JLabel ingredientErrorMessage;
	
	//Menu Pizza Panel Elements
	private JPanel menuPizzaPanel;
	
	// data elements
	private String error = null;
	
	private HashMap<Integer, Ingredient> ingredients;
	private Integer selectedRemoveIngredient = -1;
	private Integer selectedUpdateIngredient = -1;
	private String overviewIngredientColumnNames[] = {"Name", "Price"};
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table_1;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
		
	/**
	 * Create the frame.
	 */
	public PdsPage() {
		initComponents();
	}
	private void initComponents() {
		setTitle("Mamma Mia Pizza delivery");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 550, 400);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		tabbedPanel = new JTabbedPane(JTabbedPane.TOP);
		gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPanel, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPanel, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
		);
		//Ingredient Tab
		ingredientsPanel = new JPanel();
		tabbedPanel.addTab("Ingredients", null, ingredientsPanel, null);
		
		//Menu Pizza Tab

		menuPizzaPanel = new JPanel();
    	tabbedPanel.addTab("Menu Pizzas", null, menuPizzaPanel, null);
    	menuPizzaPanel.setLayout(new CardLayout(0, 0));
    	
    	JPanel menuPizzaInitialPanel = new JPanel();
    	menuPizzaPanel.add(menuPizzaInitialPanel, "name_6903914557869");
    	
    	JLabel lblAvailablePizzas = new JLabel("Available Pizzas");
    	
    	JScrollPane scrollPane = new JScrollPane();
    	
    	JLabel menuPizzaErrorMessage = new JLabel("");
    	
    	JButton btnLaunchNewMenuPizzaPage = new JButton("New menu Pizza...");
    	
    	JButton btnLaunchEditPizzaPage = new JButton("Edit Menu Pizza ...");
    	
    	JLabel lblOtherActions = new JLabel("Other Actions");
    	
    	JLabel lblDeletePizza = new JLabel("Delete Pizza");
    	
    	JComboBox comboBox = new JComboBox();
    	
    	JLabel lblSelect_1 = new JLabel("Select");
    	
    	JButton btnDelete = new JButton("Delete");
    	GroupLayout gl_menuPizzaInitialPanel = new GroupLayout(menuPizzaInitialPanel);
    	gl_menuPizzaInitialPanel.setHorizontalGroup(
    		gl_menuPizzaInitialPanel.createParallelGroup(Alignment.LEADING)
    			.addGroup(gl_menuPizzaInitialPanel.createSequentialGroup()
    				.addGroup(gl_menuPizzaInitialPanel.createParallelGroup(Alignment.LEADING)
    					.addGroup(gl_menuPizzaInitialPanel.createSequentialGroup()
    						.addGap(72)
    						.addComponent(lblAvailablePizzas)
    						.addGap(194)
    						.addComponent(lblOtherActions))
    					.addGroup(gl_menuPizzaInitialPanel.createSequentialGroup()
    						.addGap(15)
    						.addGroup(gl_menuPizzaInitialPanel.createParallelGroup(Alignment.LEADING)
    							.addGroup(gl_menuPizzaInitialPanel.createSequentialGroup()
    								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
    								.addGroup(gl_menuPizzaInitialPanel.createParallelGroup(Alignment.LEADING, false)
    									.addGroup(Alignment.TRAILING, gl_menuPizzaInitialPanel.createSequentialGroup()
    										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    										.addGroup(gl_menuPizzaInitialPanel.createParallelGroup(Alignment.LEADING)
    											.addComponent(btnLaunchEditPizzaPage)
    											.addComponent(btnLaunchNewMenuPizzaPage))
    										.addGap(32))
    									.addGroup(Alignment.TRAILING, gl_menuPizzaInitialPanel.createSequentialGroup()
    										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    										.addComponent(lblDeletePizza)
    										.addGap(68))
    									.addGroup(gl_menuPizzaInitialPanel.createSequentialGroup()
    										.addGap(18)
    										.addGroup(gl_menuPizzaInitialPanel.createParallelGroup(Alignment.TRAILING)
    											.addComponent(btnDelete)
    											.addGroup(gl_menuPizzaInitialPanel.createSequentialGroup()
    												.addComponent(lblSelect_1)
    												.addPreferredGap(ComponentPlacement.RELATED)
    												.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)))
    										.addGap(41))))
    							.addComponent(menuPizzaErrorMessage))))
    				.addContainerGap(206, Short.MAX_VALUE))
    	);
    	gl_menuPizzaInitialPanel.setVerticalGroup(
    		gl_menuPizzaInitialPanel.createParallelGroup(Alignment.LEADING)
    			.addGroup(gl_menuPizzaInitialPanel.createSequentialGroup()
    				.addGap(14)
    				.addGroup(gl_menuPizzaInitialPanel.createParallelGroup(Alignment.TRAILING)
    					.addComponent(lblAvailablePizzas)
    					.addComponent(lblOtherActions))
    				.addPreferredGap(ComponentPlacement.RELATED)
    				.addGroup(gl_menuPizzaInitialPanel.createParallelGroup(Alignment.LEADING)
    					.addGroup(gl_menuPizzaInitialPanel.createParallelGroup(Alignment.TRAILING)
    						.addComponent(menuPizzaErrorMessage)
    						.addGroup(Alignment.LEADING, gl_menuPizzaInitialPanel.createSequentialGroup()
    							.addGap(6)
    							.addComponent(btnLaunchNewMenuPizzaPage)
    							.addPreferredGap(ComponentPlacement.RELATED)
    							.addComponent(btnLaunchEditPizzaPage)
    							.addGap(28)
    							.addComponent(lblDeletePizza)
    							.addGap(18)
    							.addGroup(gl_menuPizzaInitialPanel.createParallelGroup(Alignment.BASELINE)
    								.addComponent(lblSelect_1)
    								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
    							.addPreferredGap(ComponentPlacement.RELATED)
    							.addComponent(btnDelete)))
    					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE))
    				.addContainerGap(65, Short.MAX_VALUE))
    	);
    	
    	table = new JTable();
    	scrollPane.setViewportView(table);
    	menuPizzaInitialPanel.setLayout(gl_menuPizzaInitialPanel);
    	
    	JPanel ordersPanel = new JPanel();
    	tabbedPanel.addTab("Orders", null, ordersPanel, null);
    	
    	JPanel customersPanel = new JPanel();
    	tabbedPanel.addTab("Customers", null, customersPanel, null);
    	customersPanel.setLayout(null);
    	
    	JLabel lblExistingCustomers = new JLabel("Existing Customers");
    	lblExistingCustomers.setBounds(16, 6, 147, 16);
    	customersPanel.add(lblExistingCustomers);
    	
    	JLabel lblNewCustomer = new JLabel("New Customer");
    	lblNewCustomer.setBounds(372, 6, 129, 16);
    	customersPanel.add(lblNewCustomer);
    	
    	textField = new JTextField();
    	textField.setBounds(360, 25, 130, 26);
    	customersPanel.add(textField);
    	textField.setColumns(10);
    	
    	textField_1 = new JTextField();
    	textField_1.setBounds(360, 50, 130, 26);
    	customersPanel.add(textField_1);
    	textField_1.setColumns(10);
    	
    	textField_2 = new JTextField();
    	textField_2.setBounds(360, 77, 130, 26);
    	customersPanel.add(textField_2);
    	textField_2.setColumns(10);
    	
    	textField_3 = new JTextField();
    	textField_3.setBounds(360, 104, 130, 45);
    	customersPanel.add(textField_3);
    	textField_3.setColumns(10);
    	
    	JButton btnAdd = new JButton("Add");
    	btnAdd.setBounds(372, 148, 117, 29);
    	customersPanel.add(btnAdd);
    	
    	JLabel lblUpdateCustomer = new JLabel("Update Customer");
    	lblUpdateCustomer.setBounds(16, 166, 125, 16);
    	customersPanel.add(lblUpdateCustomer);
    	
    	JScrollPane scrollPane_1 = new JScrollPane();
    	scrollPane_1.setBounds(16, 29, 255, 125);
    	customersPanel.add(scrollPane_1);
    	
    	table_1 = new JTable();
    	scrollPane_1.setViewportView(table_1);
    	
    	JLabel lblDeleteCustomer = new JLabel("Delete Customer");
    	lblDeleteCustomer.setBounds(372, 189, 118, 16);
    	customersPanel.add(lblDeleteCustomer);
    	
    	JLabel lblName = new JLabel("Name");
    	lblName.setBounds(307, 30, 61, 16);
    	customersPanel.add(lblName);
    	
    	JLabel lblPhone = new JLabel("Phone #");
    	lblPhone.setBounds(307, 55, 61, 16);
    	customersPanel.add(lblPhone);
    	
    	JLabel lblEmail = new JLabel("Email");
    	lblEmail.setBounds(307, 82, 61, 16);
    	customersPanel.add(lblEmail);
    	
    	JLabel lblAddress = new JLabel("Address");
    	lblAddress.setBounds(307, 106, 61, 16);
    	customersPanel.add(lblAddress);
    	
    	JButton btnUpdate = new JButton("Update");
    	btnUpdate.setBounds(154, 287, 117, 29);
    	customersPanel.add(btnUpdate);
    	
    	JButton btnDelete_1 = new JButton("Delete");
    	btnDelete_1.setBounds(372, 256, 117, 29);
    	customersPanel.add(btnDelete_1);
    	
    	JComboBox comboBox_1 = new JComboBox();
    	comboBox_1.setBounds(360, 217, 130, 27);
    	customersPanel.add(comboBox_1);
    	
    	JLabel lblSelect_2 = new JLabel("Select");
    	lblSelect_2.setBounds(320, 217, 61, 27);
    	customersPanel.add(lblSelect_2);
    	
    	JComboBox comboBox_2 = new JComboBox();
    	comboBox_2.setBounds(141, 162, 130, 27);
    	customersPanel.add(comboBox_2);
    	
    	textField_4 = new JTextField();
    	textField_4.setColumns(10);
    	textField_4.setBounds(141, 190, 130, 26);
    	customersPanel.add(textField_4);
    	
    	textField_5 = new JTextField();
    	textField_5.setColumns(10);
    	textField_5.setBounds(141, 216, 130, 26);
    	customersPanel.add(textField_5);
    	
    	textField_6 = new JTextField();
    	textField_6.setColumns(10);
    	textField_6.setBounds(141, 243, 130, 42);
    	customersPanel.add(textField_6);
    	
    	JLabel label = new JLabel("Phone #");
    	label.setBounds(91, 194, 61, 16);
    	customersPanel.add(label);
    	
    	JLabel label_1 = new JLabel("Email");
    	label_1.setBounds(91, 221, 61, 16);
    	customersPanel.add(label_1);
    	
    	JLabel label_2 = new JLabel("Address");
    	label_2.setBounds(80, 243, 61, 26);
    	customersPanel.add(label_2);

		
    	//Should we dynamically init on tab click ?
    	initIngredientTab();
    	
		contentPanel.setLayout(gl_contentPanel);
	}
	private void initIngredientTab() {
		
		JLabel lblNewIngredient = new JLabel("New Ingredient");
		lblNewIngredient.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		JLabel lblNewIngredientLabel = new JLabel("Name");
		lblNewIngredientLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		JLabel lblNewIngredientPrice = new JLabel("Price");
		lblNewIngredientPrice.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		fldNewIngredient = new JTextField();
		fldNewIngredient.setColumns(10);
		fldNewIngredientPrice = new JTextField();
		fldNewIngredientPrice.setColumns(10);
		
		JButton btnAddIngredient = new JButton("Add ");
		btnAddIngredient.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		btnAddIngredient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addIngredientButtonActionPerformed(e);
			}
		});
		
		JLabel lblUpdatePrice = new JLabel("Update Price");
		lblUpdatePrice.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		JLabel lblSelect = new JLabel("Select");
		lblSelect.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		cBUpdateIngredient = new JComboBox();
		cBUpdateIngredient.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
		        JComboBox<String> cb = (JComboBox<String>) evt.getSource();
		        selectedUpdateIngredient = cb.getSelectedIndex();
			}
		});
		JLabel lblNewPrice = new JLabel("Price");
		fldUpdatedPrice = new JTextField();
		fldUpdatedPrice.setColumns(10);
		JButton btnUpdateIngredient = new JButton("Update");
		btnUpdateIngredient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateIngredientButtonActionPerformed(e);
			}
		});
		
		JLabel lblRemoveIngredient = new JLabel("Remove Ingredient");
		JLabel lblRemoveSelect = new JLabel("Select");
		cBRemoveIngredient = new JComboBox();
		cBRemoveIngredient.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
		        JComboBox<String> cb = (JComboBox<String>) evt.getSource();
		        selectedRemoveIngredient = cb.getSelectedIndex();
			}
		});
		JButton btnRemoveIngredient = new JButton("Remove");
		btnRemoveIngredient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeIngredientButtonActionPerformed(e);
			}
		});
		
		JLabel lblAvailable = new JLabel("Available Ingredients ");
		ingredientOverviewScrollPane = new JScrollPane();
		ingredientErrorMessage = new JLabel("");
		
		GroupLayout gl_ingredientsPanel = new GroupLayout(ingredientsPanel);
		gl_ingredientsPanel.setHorizontalGroup(
			gl_ingredientsPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_ingredientsPanel.createSequentialGroup()
					.addGroup(gl_ingredientsPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_ingredientsPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnRemoveIngredient))
						.addGroup(gl_ingredientsPanel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_ingredientsPanel.createSequentialGroup()
								.addGap(44)
								.addComponent(lblAvailable))
							.addGroup(gl_ingredientsPanel.createSequentialGroup()
								.addGap(41)
								.addComponent(lblRemoveIngredient))
							.addGroup(gl_ingredientsPanel.createSequentialGroup()
								.addGap(17)
								.addGroup(gl_ingredientsPanel.createParallelGroup(Alignment.LEADING)
									.addComponent(ingredientOverviewScrollPane, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_ingredientsPanel.createSequentialGroup()
										.addComponent(lblRemoveSelect)
										.addGap(18)
										.addComponent(cBRemoveIngredient, 0, 200, Short.MAX_VALUE))
									.addComponent(ingredientErrorMessage)))))
					.addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
					.addGroup(gl_ingredientsPanel.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_ingredientsPanel.createSequentialGroup()
							.addGroup(gl_ingredientsPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_ingredientsPanel.createSequentialGroup()
									.addComponent(lblNewIngredient)
									.addGap(35))
								.addGroup(gl_ingredientsPanel.createSequentialGroup()
									.addGroup(gl_ingredientsPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_ingredientsPanel.createSequentialGroup()
											.addComponent(lblNewIngredientLabel)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(fldNewIngredient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_ingredientsPanel.createParallelGroup(Alignment.TRAILING)
											.addComponent(btnAddIngredient)
											.addGroup(gl_ingredientsPanel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_ingredientsPanel.createSequentialGroup()
													.addComponent(lblSelect)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(cBUpdateIngredient, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_ingredientsPanel.createSequentialGroup()
													.addComponent(lblNewIngredientPrice)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(fldNewIngredientPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_ingredientsPanel.createParallelGroup(Alignment.TRAILING)
													.addComponent(btnUpdateIngredient, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
													.addGroup(gl_ingredientsPanel.createSequentialGroup()
														.addComponent(lblNewPrice)
														.addGap(18)
														.addComponent(fldUpdatedPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))))
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGap(9))
						.addGroup(gl_ingredientsPanel.createSequentialGroup()
							.addComponent(lblUpdatePrice)
							.addGap(39))))
		);
		gl_ingredientsPanel.setVerticalGroup(
			gl_ingredientsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ingredientsPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_ingredientsPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAvailable)
						.addComponent(lblNewIngredient))
					.addGap(6)
					.addGroup(gl_ingredientsPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_ingredientsPanel.createSequentialGroup()
							.addGroup(gl_ingredientsPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewIngredientLabel)
								.addComponent(fldNewIngredient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_ingredientsPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewIngredientPrice)
								.addComponent(fldNewIngredientPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_ingredientsPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_ingredientsPanel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAddIngredient)
									.addGap(18)
									.addComponent(lblUpdatePrice))
								.addGroup(gl_ingredientsPanel.createSequentialGroup()
									.addGap(71)
									.addGroup(gl_ingredientsPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblSelect)
										.addComponent(cBUpdateIngredient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_ingredientsPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewPrice)
										.addComponent(fldUpdatedPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnUpdateIngredient)
							.addContainerGap())
						.addGroup(gl_ingredientsPanel.createSequentialGroup()
							.addComponent(ingredientOverviewScrollPane, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblRemoveIngredient)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_ingredientsPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRemoveSelect)
								.addComponent(cBRemoveIngredient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRemoveIngredient)
							.addPreferredGap(ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
							.addComponent(ingredientErrorMessage)
							.addGap(31))))
		);
		
		ingredientTable = new JTable();
		ingredientOverviewScrollPane.setViewportView(ingredientTable);
		ingredientsPanel.setLayout(gl_ingredientsPanel);
		refreshIngredientData();
	}
	
	private void refreshIngredientData() {
		// error
		ingredientErrorMessage.setText(error);
		if (error == null || error.length() == 0) {
			
			// Update all text fields
			fldNewIngredient.setText("");
			fldNewIngredientPrice.setText("");		
			fldUpdatedPrice.setText("");
			
			// Update all Combo box instance
			ingredients = new HashMap<Integer, Ingredient>();
			cBRemoveIngredient.removeAllItems();
			cBUpdateIngredient.removeAllItems();
			Integer index = 0;
			for (Ingredient ingredient : PdsController.getIngredients()) {
				ingredients.put(index, ingredient);
				cBRemoveIngredient.addItem(ingredient.getName());
				cBUpdateIngredient.addItem(ingredient.getName());
				index++;
			}
			selectedRemoveIngredient = -1;
			selectedUpdateIngredient = -1;
			cBRemoveIngredient.setSelectedIndex(selectedRemoveIngredient);
			cBUpdateIngredient.setSelectedIndex(selectedUpdateIngredient);
			
			// Ingredient Overview
			DefaultTableModel ingredientOverviewDtm = new DefaultTableModel(0, 0);
			ingredientOverviewDtm.setColumnIdentifiers(overviewIngredientColumnNames);
			ingredientTable.setModel(ingredientOverviewDtm);
			for (Ingredient ingredient : PdsController.getIngredients()) {
				Object[] obj = {ingredient.getName(), ingredient.getPrice()};
				ingredientOverviewDtm.addRow(obj);
			}
			Dimension d = ingredientTable.getPreferredSize();
			ingredientOverviewScrollPane.setPreferredSize(new Dimension(d.width, 100));
			
			pack();
		}
	}	
    private void initMenuPizzaTab() {
    	
    }

    //Action Performed methods for ingredients
    private void addIngredientButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message
		error = "";
		if (error.length() == 0) {
			try {
				PdsController.createIngredient(fldNewIngredient.getText(), convertPriceToFloat(fldNewIngredientPrice.getText()));
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}	
		// update visuals
		refreshIngredientData();
	}
    
    private void removeIngredientButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message and basic input validation
		error = "";
		if (selectedRemoveIngredient < 0)
			error = "An Ingredient needs to be selected for deletion!";
		
		if (error.length() == 0) {
			// call the controller
			try {
				PdsController.deleteIngredient(ingredients.get(selectedRemoveIngredient));
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
		// update visuals
		refreshIngredientData();
	}
    
    private void updateIngredientButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	error = "";
    	if (selectedUpdateIngredient < 0)
			error = "An Ingredient needs to be selected for update!";
    	if (error.length() == 0) {
			// call the controller
			Ingredient selectedIngredient = ingredients.get(selectedUpdateIngredient);
    		float newPrice = convertPriceToFloat(fldUpdatedPrice.getText());
			try {
				PdsController.updateIngredient(selectedIngredient.getName(),newPrice);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
		// update visuals
		refreshIngredientData();
    }
    
    private float convertPriceToFloat(String price) {
    	float returnVal = 0;
		// call the controller
		try {
			returnVal = Float.parseFloat(price);
		}
		catch (NumberFormatException e) {
			returnVal = 0;
		}
		return returnVal;
    }
}
