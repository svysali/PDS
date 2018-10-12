package ca.mcgill.ecse.pds.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import ca.mcgill.ecse.pds.controller.InvalidInputException;
import ca.mcgill.ecse.pds.controller.PdsController;
import ca.mcgill.ecse.pds.model.Customer;
import ca.mcgill.ecse.pds.model.Ingredient;
import ca.mcgill.ecse.pds.model.MenuPizza;
import ca.mcgill.ecse.pds.model.Pizza;
import ca.mcgill.ecse.pds.utils.IngredientRenderer;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.CardLayout;
import javax.swing.SwingConstants;
import javax.swing.JList;
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

	// data elements
	private String error = null;

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

	private HashMap<Integer, Ingredient> ingredients;
	private Integer selectedRemoveIngredient = -1;
	private Integer selectedUpdateIngredient = -1;
	private String overviewIngredientColumnNames[] = {"Name", "Price"};

	//Menu Pizza Panel Elements
	private JPanel menuPizzaPanel;

	//Card 1 : Initial Data Elements
	private JTable menuPizzaTable;
	private JScrollPane menuPizzScrollPane;
	private String overviewMenuPizzaColumnNames[] = {"Name", "Price","Calorie Count","Ingredients"};
	private JLabel menuPizzaErrorMessage;

	//Card 2: Create New Menu Pizza Elements
	private JTextField fldNewMenuPizzaName;
	private JTextField fldNewMenuPizzaPrice;
	private JTextField fldNewMenuPizzaCalorieCount;
	private JLabel createMenuPizzaErrorMessage;
	private JList listAddedIngredients;
	private JList listAvailableIngredients;
	private JComboBox cBDeletePizza;
	private DefaultListModel<Ingredient> availableListModel;
	private DefaultListModel<Ingredient> addedListModel;

	private HashMap<Integer, MenuPizza> menuPizzas;
	private Integer selectedDeletePizzaIndex = -1;

	//Card 3: Edit Menu Pizza Elements
	private JComboBox cBEditPizzaSelect;
	private JTextField fldEditPizzaPrice;
	private JTextField fldEditPizzaCalorieCount;
	private JLabel editPizzaErrorMessage;
	private JList editPizzaAddedList;
	private JList editPizzaAvailableList;
	private DefaultListModel<Ingredient> editPizzaAvailableListModel;
	private DefaultListModel<Ingredient> editPizzaAddedListModel;

	private Integer selectedEditPizzaIndex = -1;

	//Customer Panel Elements

	private HashMap<Integer, Customer> customers;
	private Integer selectedRemoveCustomer = -1;
	private Integer selectedUpdateCustomer = -1;
	private JComboBox<String> selectCustToDelete;
	private JComboBox<String> selectCustToUpdate;
	private JTextField custName;
	private JTextField custPhone;
	private JTextField custEmail;
	private JTextField custAddress;
	private JTable existingCustomersTable;
	private JTextField updatePhone;
	private JTextField updateEmail;
	private JTextField updateAddress;
	private JLabel custErrorMessage;
	private JScrollPane custScrollPane;

	/**
	 * Create the frame.
	 */
	public PdsPage() {
		initComponents();
		refreshCustomerData();
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

		initIngredientTab();
		initMenuPizzaTab();

		//Customer panel 
		JPanel customersPanel = new JPanel();
		tabbedPanel.addTab("Customers", null, customersPanel, null);
		customersPanel.setLayout(null);

		JLabel lblExistingCustomers = new JLabel("Existing Customers");
		lblExistingCustomers.setBounds(16, 6, 147, 16);
		customersPanel.add(lblExistingCustomers);

		JLabel lblNewCustomer = new JLabel("New Customer");
		lblNewCustomer.setBounds(372, 6, 129, 16);
		customersPanel.add(lblNewCustomer);

		JScrollPane custScrollPane = new JScrollPane();
		custScrollPane.setBounds(16, 29, 255, 125);
		customersPanel.add(custScrollPane);

		existingCustomersTable = new JTable();
		custScrollPane.setViewportView(existingCustomersTable);

		custName = new JTextField();
		custName.setBounds(360, 25, 130, 26);
		customersPanel.add(custName);
		custName.setColumns(10);

		custPhone = new JTextField();
		custPhone.setBounds(360, 50, 130, 26);
		customersPanel.add(custPhone);
		custPhone.setColumns(10);

		custEmail = new JTextField();
		custEmail.setBounds(360, 77, 130, 26);
		customersPanel.add(custEmail);
		custEmail.setColumns(10);

		custAddress = new JTextField();
		custAddress.setBounds(360, 104, 130, 45);
		customersPanel.add(custAddress);
		custAddress.setColumns(10);

		JButton btnAddCust = new JButton("Add");
		btnAddCust.setBounds(372, 148, 117, 29);
		customersPanel.add(btnAddCust);
		btnAddCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCustomerButtonActionPerformed(e);
			}
		});

		JLabel lblUpdateCustomer = new JLabel("Update Customer");
		lblUpdateCustomer.setBounds(16, 166, 125, 16);
		customersPanel.add(lblUpdateCustomer);


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

		JLabel lblSelect_2 = new JLabel("Select");
		lblSelect_2.setBounds(320, 217, 61, 27);
		customersPanel.add(lblSelect_2);

		updatePhone = new JTextField();
		updatePhone.setColumns(10);
		updatePhone.setBounds(141, 190, 130, 26);
		customersPanel.add(updatePhone);

		updateEmail = new JTextField();
		updateEmail.setColumns(10);
		updateEmail.setBounds(141, 216, 130, 26);
		customersPanel.add(updateEmail);

		updateAddress = new JTextField();
		updateAddress.setColumns(10);
		updateAddress.setBounds(141, 243, 130, 42);
		customersPanel.add(updateAddress);

		JLabel label = new JLabel("Phone #");
		label.setBounds(91, 194, 61, 16);
		customersPanel.add(label);

		JLabel label_1 = new JLabel("Email");
		label_1.setBounds(91, 221, 61, 16);
		customersPanel.add(label_1);

		JLabel label_2 = new JLabel("Address");
		label_2.setBounds(80, 243, 61, 26);
		customersPanel.add(label_2);

		JLabel custErrorMessage = new JLabel(" ");
		custErrorMessage.setBounds(283, 292, 61, 16);
		customersPanel.add(custErrorMessage);

		selectCustToUpdate = new JComboBox<String>(new String[0]);
		selectCustToUpdate.setBounds(141, 162, 130, 27);
		customersPanel.add(selectCustToUpdate);
		selectCustToUpdate.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedUpdateCustomer = cb.getSelectedIndex();
			}
		});

		JButton btnUpdateCust = new JButton("Update");
		btnUpdateCust.setBounds(154, 287, 117, 29);
		customersPanel.add(btnUpdateCust);
		btnUpdateCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateCustomerButtonActionPerformed(e);
			}
		});


		selectCustToDelete = new JComboBox<String>(new String[0]);
		selectCustToDelete.setBounds(360, 217, 130, 27);
		customersPanel.add(selectCustToDelete);
		selectCustToDelete.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedRemoveCustomer = cb.getSelectedIndex();
			}
		});

		JButton btnDeleteCust = new JButton("Delete");
		btnDeleteCust.setBounds(372, 256, 117, 29);
		customersPanel.add(btnDeleteCust);
		btnDeleteCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeCustomerButtonActionPerformed(e);
			}
		});
		contentPanel.setLayout(gl_contentPanel);
	}

	private void initIngredientTab() {
		//Ingredient Tab
		ingredientsPanel = new JPanel();
		tabbedPanel.addTab("Ingredients", null, ingredientsPanel, null);

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
		//Menu Pizza Tab
		menuPizzaPanel = new JPanel();
		tabbedPanel.addTab("Menu Pizzas", null, menuPizzaPanel, null);

		//Set Card Layout
		CardLayout menuPizzaPanelLayout = new CardLayout(); 
		menuPizzaPanel.setLayout(menuPizzaPanelLayout);

		//Card 1
		JPanel menuPizzaInitialPanel = new JPanel();
		menuPizzaPanel.add(menuPizzaInitialPanel, "menuPizzaInitialPanel");

		JLabel lblAvailablePizzas = new JLabel("Available Pizzas");
		JLabel menuPizzaErrorMessage = new JLabel("this is an error label");
		JLabel lblOtherActions = new JLabel("Other Actions");
		JLabel lblDeletePizza = new JLabel("Delete Pizza");
		JLabel lblSelect_1 = new JLabel("Select");

		menuPizzScrollPane = new JScrollPane();

		cBDeletePizza = new JComboBox();
		cBDeletePizza.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedDeletePizzaIndex = cb.getSelectedIndex();
			}
		});

		JButton btnLaunchNewMenuPizzaPage = new JButton("New menu Pizza...");
		JButton btnLaunchEditPizzaPage = new JButton("Edit Menu Pizza ...");
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteMenuPizzaButtonActionPerformed(e);
			}
		});

		GroupLayout gl_menuPizzaInitialPanel = new GroupLayout(menuPizzaInitialPanel);
		gl_menuPizzaInitialPanel.setHorizontalGroup(
				gl_menuPizzaInitialPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuPizzaInitialPanel.createSequentialGroup()
						.addGap(15)
						.addComponent(menuPizzScrollPane, GroupLayout.PREFERRED_SIZE, 476, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(65, Short.MAX_VALUE))
				.addGroup(gl_menuPizzaInitialPanel.createSequentialGroup()
						.addGap(189)
						.addComponent(menuPizzaErrorMessage)
						.addContainerGap(241, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_menuPizzaInitialPanel.createSequentialGroup()
						.addGroup(gl_menuPizzaInitialPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_menuPizzaInitialPanel.createSequentialGroup()
										.addGap(59)
										.addComponent(lblOtherActions))
								.addGroup(gl_menuPizzaInitialPanel.createSequentialGroup()
										.addContainerGap()
										.addGroup(gl_menuPizzaInitialPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(btnLaunchEditPizzaPage)
												.addComponent(btnLaunchNewMenuPizzaPage))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_menuPizzaInitialPanel.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_menuPizzaInitialPanel.createSequentialGroup()
														.addGap(72)
														.addComponent(lblAvailablePizzas))
												.addGroup(gl_menuPizzaInitialPanel.createSequentialGroup()
														.addComponent(lblSelect_1)
														.addGap(6)))))
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_menuPizzaInitialPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDeletePizza)
								.addGroup(gl_menuPizzaInitialPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnDelete)
										.addComponent(cBDeletePizza, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)))
						.addGap(65))
				);
		gl_menuPizzaInitialPanel.setVerticalGroup(
				gl_menuPizzaInitialPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuPizzaInitialPanel.createSequentialGroup()
						.addGap(8)
						.addComponent(lblAvailablePizzas)
						.addGap(18)
						.addComponent(menuPizzScrollPane, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_menuPizzaInitialPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblOtherActions)
								.addComponent(lblDeletePizza))
						.addGroup(gl_menuPizzaInitialPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_menuPizzaInitialPanel.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_menuPizzaInitialPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(cBDeletePizza, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblSelect_1)))
								.addGroup(gl_menuPizzaInitialPanel.createSequentialGroup()
										.addGap(2)
										.addComponent(btnLaunchNewMenuPizzaPage)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_menuPizzaInitialPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_menuPizzaInitialPanel.createSequentialGroup()
										.addComponent(btnLaunchEditPizzaPage)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(menuPizzaErrorMessage))
								.addComponent(btnDelete))
						.addContainerGap())
				);

		menuPizzaTable = new JTable();
		menuPizzScrollPane.setViewportView(menuPizzaTable);
		menuPizzaInitialPanel.setLayout(gl_menuPizzaInitialPanel);

		//Card 2

		JPanel createNewMenuPizzaPanel = new JPanel();
		menuPizzaPanel.add(createNewMenuPizzaPanel, "newMenuPizzaPanel");

		JLabel lblCreateNewMenu = new JLabel("Create New Menu Pizza");
		JLabel lblName = new JLabel("Name");
		JLabel lblPrice = new JLabel("Price");
		JLabel lblCalorieCount = new JLabel("Calorie Count");
		JLabel lblIngredients = new JLabel("Ingredients");
		JLabel lblAvailable_1 = new JLabel("Available");
		JLabel lblAdded = new JLabel("Added");

		fldNewMenuPizzaName = new JTextField();
		fldNewMenuPizzaName.setColumns(10);

		fldNewMenuPizzaPrice = new JTextField();
		fldNewMenuPizzaPrice.setColumns(10);

		fldNewMenuPizzaCalorieCount = new JTextField();
		fldNewMenuPizzaCalorieCount.setColumns(10);

		JButton btnRemoveIngredientFromMenuPizza = new JButton("< Remove ");

		JButton btnAddIngredientToMenuPizza = new JButton("Add >");

		listAvailableIngredients = new JList();
		availableListModel = new DefaultListModel<Ingredient>();
		listAvailableIngredients.setModel(availableListModel);
		listAvailableIngredients.setCellRenderer(new IngredientRenderer());
		listAvailableIngredients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listAvailableIngredients.setVisibleRowCount(5);
		JScrollPane listAvailableScrollPane = new JScrollPane();
		listAvailableScrollPane.setViewportView(listAvailableIngredients);

		listAddedIngredients = new JList();
		addedListModel = new DefaultListModel<Ingredient>();
		listAddedIngredients.setModel(addedListModel);
		listAddedIngredients.setCellRenderer(new IngredientRenderer());
		listAddedIngredients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listAddedIngredients.setVisibleRowCount(5);
		JScrollPane listAddedScrollPane = new JScrollPane();
		listAddedScrollPane.setViewportView(listAddedIngredients);

		listAvailableIngredients.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {

					if (listAvailableIngredients.getSelectedIndex() == -1) {
						//No selection, disable button.
						btnAddIngredientToMenuPizza.setEnabled(false);
					} else {
						//Selection, enable the button.
						btnAddIngredientToMenuPizza.setEnabled(true);
					}
				}
			}
		});

		listAddedIngredients.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {

					if (listAddedIngredients.getSelectedIndex() == -1) {
						//No selection, disable button.
						btnRemoveIngredientFromMenuPizza.setEnabled(false);
					} else {
						//Selection, enable the button.
						btnRemoveIngredientFromMenuPizza.setEnabled(true);
					}
				}
			}
		});

		btnAddIngredientToMenuPizza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer selectedIngredientToAdd = listAvailableIngredients.getSelectedIndex();
				Ingredient selectedIngredient = (Ingredient) listAvailableIngredients.getModel().getElementAt(selectedIngredientToAdd);
				addedListModel.addElement(selectedIngredient);
				availableListModel.remove(selectedIngredientToAdd);
				listAddedIngredients.setModel(addedListModel);
				listAvailableIngredients.setModel(availableListModel);
			}	
		});

		btnRemoveIngredientFromMenuPizza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer selectedIngredientToRemove = listAddedIngredients.getSelectedIndex();
				Ingredient selectedIngredient = (Ingredient) listAddedIngredients.getModel().getElementAt(selectedIngredientToRemove);
				availableListModel.addElement(selectedIngredient);
				addedListModel.remove(selectedIngredientToRemove);
				listAddedIngredients.setModel(addedListModel);
				listAvailableIngredients.setModel(availableListModel);
			}	
		});

		JButton btnBack = new JButton("Back");
		JButton btnCreateMenuPizza = new JButton("create pizza");
		btnCreateMenuPizza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createMenuPizzaButtonActionPerformed(e);
			}	
		});

		createMenuPizzaErrorMessage = new JLabel("This is an error label");


		GroupLayout gl_createNewMenuPizzaPanel = new GroupLayout(createNewMenuPizzaPanel);
		gl_createNewMenuPizzaPanel.setHorizontalGroup(
				gl_createNewMenuPizzaPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_createNewMenuPizzaPanel.createSequentialGroup()
						.addGap(62)
						.addComponent(lblAvailable_1)
						.addGap(80)
						.addComponent(lblIngredients)
						.addGap(86)
						.addComponent(lblAdded)
						.addContainerGap(134, Short.MAX_VALUE))
				.addGroup(gl_createNewMenuPizzaPanel.createSequentialGroup()
						.addContainerGap()
						.addComponent(createMenuPizzaErrorMessage)
						.addGap(189)
						.addComponent(btnBack)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnCreateMenuPizza)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_createNewMenuPizzaPanel.createSequentialGroup()
						.addGroup(gl_createNewMenuPizzaPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_createNewMenuPizzaPanel.createSequentialGroup()
										.addGap(161)
										.addComponent(lblCreateNewMenu))
								.addGroup(gl_createNewMenuPizzaPanel.createSequentialGroup()
										.addGap(45)
										.addComponent(lblName)
										.addPreferredGap(ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
										.addComponent(lblPrice)
										.addGap(67))
								.addGroup(gl_createNewMenuPizzaPanel.createSequentialGroup()
										.addContainerGap()
										.addGroup(gl_createNewMenuPizzaPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(fldNewMenuPizzaName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(listAvailableScrollPane, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_createNewMenuPizzaPanel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_createNewMenuPizzaPanel.createSequentialGroup()
														.addGap(8)
														.addComponent(fldNewMenuPizzaPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_createNewMenuPizzaPanel.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(gl_createNewMenuPizzaPanel.createParallelGroup(Alignment.LEADING)
																.addComponent(btnRemoveIngredientFromMenuPizza, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
																.addComponent(btnAddIngredientToMenuPizza, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))))
										.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(gl_createNewMenuPizzaPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_createNewMenuPizzaPanel.createSequentialGroup()
										.addGap(46)
										.addComponent(lblCalorieCount))
								.addGroup(gl_createNewMenuPizzaPanel.createSequentialGroup()
										.addGap(31)
										.addGroup(gl_createNewMenuPizzaPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(listAddedScrollPane, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
												.addComponent(fldNewMenuPizzaCalorieCount, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))))
						.addGap(28))
				);
		gl_createNewMenuPizzaPanel.setVerticalGroup(
				gl_createNewMenuPizzaPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_createNewMenuPizzaPanel.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblCreateNewMenu)
						.addGap(8)
						.addGroup(gl_createNewMenuPizzaPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCalorieCount, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPrice)
								.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_createNewMenuPizzaPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_createNewMenuPizzaPanel.createSequentialGroup()
										.addGap(6)
										.addComponent(fldNewMenuPizzaCalorieCount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_createNewMenuPizzaPanel.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(fldNewMenuPizzaPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_createNewMenuPizzaPanel.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(fldNewMenuPizzaName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_createNewMenuPizzaPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_createNewMenuPizzaPanel.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(lblIngredients))
								.addGroup(gl_createNewMenuPizzaPanel.createSequentialGroup()
										.addGap(24)
										.addGroup(gl_createNewMenuPizzaPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblAvailable_1)
												.addComponent(lblAdded))))
						.addGroup(gl_createNewMenuPizzaPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_createNewMenuPizzaPanel.createSequentialGroup()
										.addGap(18)
										.addComponent(btnAddIngredientToMenuPizza)
										.addGap(18)
										.addComponent(btnRemoveIngredientFromMenuPizza))
								.addGroup(gl_createNewMenuPizzaPanel.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_createNewMenuPizzaPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(listAddedScrollPane, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
												.addComponent(listAvailableScrollPane, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_createNewMenuPizzaPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(createMenuPizzaErrorMessage)
								.addGroup(gl_createNewMenuPizzaPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnCreateMenuPizza)
										.addComponent(btnBack)))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);


		createNewMenuPizzaPanel.setLayout(gl_createNewMenuPizzaPanel);

		// Add action listeners to buttons to transition between panels
		btnLaunchNewMenuPizzaPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPizzaPanelLayout.show(menuPizzaPanel,"newMenuPizzaPanel");
				refreshCreateNewMenuPizzaPanel();
			}	
		});

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPizzaPanelLayout.show(menuPizzaPanel,"menuPizzaInitialPanel");
				refreshMenuPizzaInitialData();
			}	
		});

		menuPizzaPanelLayout.show(menuPizzaPanel, "menuPizzaInitialPanel");

		//Card 3 - Edit Pizza Panel

		JPanel editMenuPizzaPanel = new JPanel();
		menuPizzaPanel.add(editMenuPizzaPanel, "editMenuPizzaPanel");

		fldEditPizzaPrice = new JTextField();
		fldEditPizzaPrice.setColumns(10);

		fldEditPizzaCalorieCount = new JTextField();
		fldEditPizzaCalorieCount.setColumns(10);

		JLabel lblNewLabel = new JLabel("Select Pizza");
		JLabel lblNewLabel_1 = new JLabel("Price");
		JLabel lblNewLabel_2 = new JLabel("Calorie Count");
		JLabel lblNewLabel_4 = new JLabel("Ingredients");
		JLabel lblNewLabel_5 = new JLabel("Available");
		JLabel lblNewLabel_6 = new JLabel("Added");

		editPizzaErrorMessage = new JLabel("This is an error label");

		JScrollPane editPizzaAvailableScrollPane = new JScrollPane();
		JScrollPane editPizzaAddedScrollPane = new JScrollPane();

		cBEditPizzaSelect = new JComboBox();
		cBEditPizzaSelect.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedEditPizzaIndex = cb.getSelectedIndex();
				refreshEditPizzaPanelData(selectedEditPizzaIndex);
			}
		});

		editPizzaAvailableListModel = new DefaultListModel<Ingredient>();
		editPizzaAvailableList = new JList();
		editPizzaAvailableList.setModel(editPizzaAvailableListModel);
		editPizzaAvailableList.setCellRenderer(new IngredientRenderer());
		editPizzaAvailableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		editPizzaAvailableList.setVisibleRowCount(5);
		editPizzaAvailableScrollPane.setViewportView(editPizzaAvailableList);

		editPizzaAddedListModel = new DefaultListModel<Ingredient>();
		editPizzaAddedList = new JList();
		editPizzaAddedList.setModel(editPizzaAddedListModel);
		editPizzaAddedList.setCellRenderer(new IngredientRenderer());
		editPizzaAddedList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		editPizzaAddedList.setVisibleRowCount(5);
		editPizzaAddedScrollPane.setViewportView(editPizzaAddedList);

		JButton btnEditPizzaAddIngredient = new JButton("Add >");
		JButton btnEditPizzaRemoveIngredient = new JButton("< Remove");
		JButton btnEditPizzaBack = new JButton("Back");
		JButton btnEditPizzaSave = new JButton("Save");
		btnEditPizzaSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateMenuPizzaButtonActionPerformed(e);
			}	
		});

		editPizzaAvailableList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {

					if (editPizzaAvailableList.getSelectedIndex() == -1) {
						//No selection, disable button.
						btnEditPizzaAddIngredient.setEnabled(false);
					} else {
						//Selection, enable the button.
						btnEditPizzaAddIngredient.setEnabled(true);
					}
				}
			}
		});

		editPizzaAddedList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {

					if (editPizzaAddedList.getSelectedIndex() == -1) {
						//No selection, disable button.
						btnEditPizzaAddIngredient.setEnabled(false);
					} else {
						//Selection, enable the button.
						btnEditPizzaAddIngredient.setEnabled(true);
					}
				}
			}
		});

		btnEditPizzaAddIngredient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer selectedIngredientToAdd = editPizzaAvailableList.getSelectedIndex();
				Ingredient selectedIngredient = (Ingredient) editPizzaAvailableList.getModel().getElementAt(selectedIngredientToAdd);
				editPizzaAddedListModel.addElement(selectedIngredient);
				editPizzaAvailableListModel.remove(selectedIngredientToAdd);
				editPizzaAvailableList.setModel(editPizzaAvailableListModel);
				editPizzaAddedList.setModel(editPizzaAddedListModel);
			}	
		});

		btnEditPizzaRemoveIngredient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer selectedIngredientToRemove = editPizzaAddedList.getSelectedIndex();
				Ingredient selectedIngredient = (Ingredient) editPizzaAddedList.getModel().getElementAt(selectedIngredientToRemove);
				editPizzaAvailableListModel.addElement(selectedIngredient);
				editPizzaAddedListModel.remove(selectedIngredientToRemove);
				editPizzaAddedList.setModel(editPizzaAddedListModel);
				editPizzaAvailableList.setModel(editPizzaAvailableListModel);
			}	
		});

		GroupLayout gl_editMenuPizzaPanel = new GroupLayout(editMenuPizzaPanel);
		gl_editMenuPizzaPanel.setHorizontalGroup(
				gl_editMenuPizzaPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_editMenuPizzaPanel.createSequentialGroup()
						.addGap(57)
						.addComponent(lblNewLabel)
						.addGap(96)
						.addComponent(lblNewLabel_1)
						.addPreferredGap(ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2)
						.addGap(69))
				.addGroup(gl_editMenuPizzaPanel.createSequentialGroup()
						.addGap(230)
						.addComponent(lblNewLabel_4)
						.addContainerGap(239, Short.MAX_VALUE))
				.addGroup(gl_editMenuPizzaPanel.createSequentialGroup()
						.addGap(58)
						.addComponent(lblNewLabel_5)
						.addPreferredGap(ComponentPlacement.RELATED, 292, Short.MAX_VALUE)
						.addComponent(lblNewLabel_6)
						.addGap(93))
				.addGroup(gl_editMenuPizzaPanel.createSequentialGroup()
						.addGap(21)
						.addGroup(gl_editMenuPizzaPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(editPizzaAvailableScrollPane, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
								.addComponent(cBEditPizzaSelect, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
								.addComponent(editPizzaErrorMessage))
						.addGap(27)
						.addGroup(gl_editMenuPizzaPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_editMenuPizzaPanel.createSequentialGroup()
										.addComponent(fldEditPizzaPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
										.addComponent(fldEditPizzaCalorieCount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_editMenuPizzaPanel.createSequentialGroup()
										.addGroup(gl_editMenuPizzaPanel.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_editMenuPizzaPanel.createSequentialGroup()
														.addGroup(gl_editMenuPizzaPanel.createParallelGroup(Alignment.LEADING, false)
																.addComponent(btnEditPizzaAddIngredient, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(btnEditPizzaRemoveIngredient, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
														.addPreferredGap(ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
														.addComponent(editPizzaAddedScrollPane, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_editMenuPizzaPanel.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(btnEditPizzaBack)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(btnEditPizzaSave)))
										.addPreferredGap(ComponentPlacement.RELATED)))
						.addGap(36))
				);
		gl_editMenuPizzaPanel.setVerticalGroup(
				gl_editMenuPizzaPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_editMenuPizzaPanel.createSequentialGroup()
						.addGap(17)
						.addGroup(gl_editMenuPizzaPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_1))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_editMenuPizzaPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(fldEditPizzaPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(fldEditPizzaCalorieCount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(cBEditPizzaSelect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(lblNewLabel_4)
						.addGroup(gl_editMenuPizzaPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_editMenuPizzaPanel.createSequentialGroup()
										.addGap(9)
										.addGroup(gl_editMenuPizzaPanel.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNewLabel_5)
												.addComponent(lblNewLabel_6))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_editMenuPizzaPanel.createParallelGroup(Alignment.TRAILING)
												.addComponent(editPizzaAvailableScrollPane, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
												.addComponent(editPizzaAddedScrollPane, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_editMenuPizzaPanel.createSequentialGroup()
										.addGap(51)
										.addComponent(btnEditPizzaAddIngredient)
										.addGap(28)
										.addComponent(btnEditPizzaRemoveIngredient)))
						.addGap(18)
						.addGroup(gl_editMenuPizzaPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnEditPizzaBack)
								.addComponent(btnEditPizzaSave)
								.addComponent(editPizzaErrorMessage))
						.addContainerGap())
				);

		// Add action listeners to buttons to transition between panels
		btnLaunchEditPizzaPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPizzaPanelLayout.show(menuPizzaPanel,"editMenuPizzaPanel");
				initEditMenuPizzaPanel();
			}	
		});

		btnEditPizzaBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPizzaPanelLayout.show(menuPizzaPanel,"menuPizzaInitialPanel");
				refreshMenuPizzaInitialData();
			}	
		});

		editMenuPizzaPanel.setLayout(gl_editMenuPizzaPanel);

		//Refresh the First Panel Data
		refreshMenuPizzaInitialData();
	}

	private void refreshMenuPizzaInitialData() {
		menuPizzas = new HashMap<Integer, MenuPizza>();
		cBDeletePizza.removeAllItems();

		Integer index = 0;
		for (MenuPizza mpizza : PdsController.getMenuPizzas()) {
			menuPizzas.put(index, mpizza);
			cBDeletePizza.addItem(mpizza.getName());
			index++;
		}
		selectedDeletePizzaIndex = -1;
		cBDeletePizza.setSelectedIndex(selectedDeletePizzaIndex);

		// Menu Pizza Overview
		DefaultTableModel menuPizzaOverviewDtm = new DefaultTableModel(0, 0);
		menuPizzaOverviewDtm.setColumnIdentifiers(overviewMenuPizzaColumnNames);
		menuPizzaTable.setModel(menuPizzaOverviewDtm);
		for (MenuPizza menupizza : PdsController.getMenuPizzas()) {
			ArrayList<String> ingredientList = new ArrayList<String>();
			for(Ingredient ingredient : menupizza.getIngredients()) {
				ingredientList.add(ingredient.getName());
			}
			Object[] obj = {menupizza.getName(), menupizza.getPrice(), menupizza.getCalorieCount(),String.join(",", ingredientList)};
			menuPizzaOverviewDtm.addRow(obj);
		}
		Dimension d = menuPizzaTable.getPreferredSize();
		ingredientOverviewScrollPane.setPreferredSize(new Dimension(d.width, 100));

		pack();
	}

	private void refreshCreateNewMenuPizzaPanel() {
		fldNewMenuPizzaName.setText("");
		fldNewMenuPizzaPrice.setText("");
		fldNewMenuPizzaCalorieCount.setText("");

		availableListModel.removeAllElements();
		addedListModel.removeAllElements();
		Integer index = 0;
		for (Ingredient ingredient : PdsController.getIngredients()) {
			availableListModel.addElement(ingredient);
			index++;
		}
		listAvailableIngredients.setModel(availableListModel);
		listAddedIngredients.setModel(addedListModel);

	}

	private void initEditMenuPizzaPanel() {
		menuPizzas = new HashMap<Integer, MenuPizza>();
		cBEditPizzaSelect.removeAllItems();

		Integer index = 0;
		for (MenuPizza mpizza : PdsController.getMenuPizzas()) {
			menuPizzas.put(index, mpizza);
			cBEditPizzaSelect.addItem(mpizza.getName());
			index++;
		}
		selectedEditPizzaIndex = -1;
		cBEditPizzaSelect.setSelectedIndex(selectedEditPizzaIndex);

		fldEditPizzaPrice.setText("");
		fldEditPizzaCalorieCount.setText("");

	}

	private void refreshEditPizzaPanelData(int selectedPizzaIndex) {
		System.out.println("index changed to " + selectedPizzaIndex);
		editPizzaAvailableListModel.removeAllElements();
		editPizzaAddedListModel.removeAllElements();
		if(selectedPizzaIndex >= 0) {
			MenuPizza pizzaToEdit = menuPizzas.get(selectedPizzaIndex);
			ArrayList<String> existingIngredients = new ArrayList<String>();
			for(Ingredient ingredient: pizzaToEdit.getIngredients()) {
				existingIngredients.add(ingredient.getName());
			}
			fldEditPizzaPrice.setText(Float.toString(pizzaToEdit.getPrice()));
			fldEditPizzaCalorieCount.setText(Float.toString(pizzaToEdit.getCalorieCount()));

			Integer index = 0;
			for (Ingredient ingredient : PdsController.getIngredients()) {
				if(!existingIngredients.contains(ingredient.getName())) {
					editPizzaAvailableListModel.addElement(ingredient);
					index++;
				}
			}

			index = 0;
			for (Ingredient ingredient : pizzaToEdit.getIngredients() ) {
				editPizzaAddedListModel.addElement(ingredient);
				index++;
			}
		}
		editPizzaAvailableList.setModel(editPizzaAvailableListModel);
		editPizzaAddedList.setModel(editPizzaAddedListModel);
	}

	//  End of Panel Initialization and refresh methods 

	//  Begin action performed methods 

	//Ingredients
	private void addIngredientButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message
		error = "";
		if (error.length() == 0) {
			try {
				PdsController.createIngredient(fldNewIngredient.getText(), convertStringToFloat(fldNewIngredientPrice.getText()));
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
			float newPrice = convertStringToFloat(fldUpdatedPrice.getText());
			try {
				PdsController.updateIngredient(selectedIngredient.getName(),newPrice);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
		// update visuals
		refreshIngredientData();
	}

	//Menu Pizza
	private void createMenuPizzaButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		Float price = convertStringToFloat(fldNewMenuPizzaPrice.getText());
		Float calorieCount = convertStringToFloat(fldNewMenuPizzaCalorieCount.getText());
		Ingredient[] addedIngredientArray = new Ingredient[addedListModel.getSize()];
		Integer nrOfIngredients = 0;
		//Maybe we can check if price of pizza is greater than price off all Ingredients here
		if(price < 0) {
			error = "Price cannot be less than zero";
		}
		if(calorieCount < 0) {
			error = "Calorie Count cannot be less than Zero";
		}
		if(addedListModel.isEmpty()) {
			error = "Atleast one ingredient must be added";
		} else {
			for(int i=0;i<addedListModel.getSize();i++) {
				Ingredient ingredient = (Ingredient) addedListModel.getElementAt(i);
				addedIngredientArray[i] = ingredient;
			}
		}

		if (error.length() == 0) {
			try {
				PdsController.createMenuPizza(fldNewMenuPizzaName.getText(),calorieCount,price,addedIngredientArray);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		} 
	}

	private void deleteMenuPizzaButtonActionPerformed(java.awt.event.ActionEvent evt) {
		//delete the pizza
		error = "";
		if (selectedDeletePizzaIndex < 0)
			error = "A Pizza needs to be selected for deletion!";

		if (error.length() == 0) {
			// call the controller
			try {
				PdsController.deleteMenuPizza(menuPizzas.get(selectedDeletePizzaIndex));
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
		// update visuals
		refreshMenuPizzaInitialData();
	}

	private void updateMenuPizzaButtonActionPerformed(java.awt.event.ActionEvent evt) {
		//delete the pizza
		String error = "";
		MenuPizza pizzaToEdit = menuPizzas.get(selectedEditPizzaIndex);
		Float price = convertStringToFloat(fldEditPizzaPrice.getText());
		Float calorieCount = convertStringToFloat(fldEditPizzaCalorieCount.getText());
		Ingredient[] editedIngredientArray = new Ingredient[editPizzaAddedListModel.getSize()];
		
		if(selectedEditPizzaIndex<0) {
			error = "Select a pizza to update!";
		}
		if(price <= 0) {
			error = "Price cannot be less than zero";
		}
		if(calorieCount <= 0) {
			error = "Calorie Count cannot be less than Zero";
		}
		for(int i=0;i<editPizzaAddedListModel.getSize();i++) {
			Ingredient ingredient = (Ingredient) editPizzaAddedListModel.getElementAt(i);
			editedIngredientArray[i] = ingredient;
		}
		if (error.length() == 0) {
			try {
				PdsController.updateMenuPizza(pizzaToEdit.getName(),price,calorieCount,editedIngredientArray);
			} catch (InvalidInputException e) {
				error = e.getMessage();
				System.out.println("FINAL ERROR:" + error);
			}
		}
	}

	// End Action performed methods

	// Begin Additional utilities    
	private float convertStringToFloat(String price) {
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

	//Action Performed Methods for Customer

	private void addCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message
		error = "";
		if (custName.getText() == null || custAddress.getText()==null ||custName.getText() == "" || custAddress.getText()=="" ){
			error = error + "Customer info cannot be left blank \n";	
		}

		if (error.length() == 0) {
			try {
				PdsController.createCustomer(custName.getText(), custPhone.getText(), custEmail.getText(), custAddress.getText());
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}	
		// update visuals
		refreshCustomerData();
	}

	private void removeCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message and basic input validation
		error = "";
		if (selectedRemoveCustomer < 0)
			error = "A Customer needs to be selected for deletion!";

		if (error.length() == 0) {
			// call the controller
			try {
				PdsController.deleteCustomer(customers.get(selectedRemoveCustomer));
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
		// update visuals
		refreshCustomerData();
	}


	private void updateCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		if (selectedUpdateCustomer < 0)
			error = "An Customer needs to be selected for update!";
		if (error.length() == 0) {
			// call the controller
			Customer selectedCustomer = customers.get(selectedUpdateCustomer);
			try {
				PdsController.updateCustomer(selectedCustomer.getName(),updatePhone.getText(), updateEmail.getText(),updateAddress.getText());
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
		// update visuals
		refreshCustomerData();
	}

	private void refreshCustomerData() {
		// error
		//custErrorMessage.setText(error);
		if (error == null || error.length() == 0) {		
			// Update all text fields
			custName.setText("");
			custPhone.setText("");		
			custEmail.setText("");
			custAddress.setText("");

			updatePhone.setText("");
			updateEmail.setText("");
			updateAddress.setText("");
		}

		// Update all Combo box instance

		customers = new HashMap<Integer, Customer>();		
		selectCustToDelete.removeAllItems();
		selectCustToUpdate.removeAllItems();
		Integer index = 0;
		for (Customer customer : PdsController.getCustomers()) {
			customers.put(index, customer);
			selectCustToDelete.addItem(customer.getName());
			selectCustToUpdate.addItem(customer.getName());
			index++;
		}

		selectedRemoveCustomer = -1;
		selectedUpdateCustomer = -1;
		selectCustToDelete.setSelectedIndex(selectedRemoveCustomer);
		selectCustToUpdate.setSelectedIndex(selectedUpdateCustomer);

		//			
		//			// Customer Overview
		//			DefaultTableModel customerOverviewDtm = new DefaultTableModel(0, 0);
		//			customerOverviewDtm.setColumnIdentifiers(overviewIngredientColumnNames);
		//			existingCustomersTable.setModel(customerOverviewDtm);
		//			for (Customer customer : PdsController.getCustomers()) {
		//				Object[] obj = {customer.getName(), customer.getPhoneNumber(), customer.getEmailAddress(), customer.getDeliveryAddress()};
		//				customerOverviewDtm.addRow(obj);
		//			}
		//			Dimension d = existingCustomersTable.getPreferredSize();
		//			custScrollPane.setPreferredSize(new Dimension(d.width, 100));
		//			
		//			pack();
	}
}
