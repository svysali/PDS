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
import ca.mcgill.ecse.pds.model.Ingredient;
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
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.CardLayout;
import javax.swing.SwingConstants;
import javax.swing.JList;

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
	private JLabel menuPizzaErrorMessage;
	
	//Card 2: Create New Menu Pizza Elements
	private JTextField fldNewMenuPizzaName;
	private JTextField fldNewMenuPizzaPrice;
	private JTextField fldNewMenuPizzaCalorieCount;
	private JLabel createMenuPizzaErrorMessage;
	private JList listAddedIngredients;
	private JList listAvailableIngredients;
	private DefaultListModel<Ingredient> availableListModel;
	private DefaultListModel<Ingredient> addedListModel;


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

		initIngredientTab();
		initMenuPizzaTab();
		

		JPanel customersPanel = new JPanel();
		tabbedPanel.addTab("Customers", null, customersPanel, null);

		JPanel ordersPanel = new JPanel();
		tabbedPanel.addTab("Orders", null, ordersPanel, null);
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		
		JComboBox cBDeletePizza = new JComboBox();
		
		JButton btnLaunchNewMenuPizzaPage = new JButton("New menu Pizza...");
		JButton btnLaunchEditPizzaPage = new JButton("Edit Menu Pizza ...");
		JButton btnDelete = new JButton("Delete");
		
		GroupLayout gl_menuPizzaInitialPanel = new GroupLayout(menuPizzaInitialPanel);
		gl_menuPizzaInitialPanel.setHorizontalGroup(
				gl_menuPizzaInitialPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuPizzaInitialPanel.createSequentialGroup()
						.addGroup(gl_menuPizzaInitialPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_menuPizzaInitialPanel.createSequentialGroup()
										.addGap(72)
										.addComponent(lblAvailablePizzas))
								.addGroup(gl_menuPizzaInitialPanel.createSequentialGroup()
										.addGap(28)
										.addGroup(gl_menuPizzaInitialPanel.createParallelGroup(Alignment.TRAILING)
												.addComponent(btnLaunchNewMenuPizzaPage)
												.addComponent(btnLaunchEditPizzaPage))
										.addPreferredGap(ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
										.addGroup(gl_menuPizzaInitialPanel.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_menuPizzaInitialPanel.createSequentialGroup()
														.addComponent(lblSelect_1)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(cBDeletePizza, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
												.addComponent(btnDelete))))
						.addContainerGap(76, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_menuPizzaInitialPanel.createSequentialGroup()
						.addGap(59)
						.addComponent(lblOtherActions)
						.addPreferredGap(ComponentPlacement.RELATED, 175, Short.MAX_VALUE)
						.addComponent(lblDeletePizza)
						.addGap(122))
				.addGroup(gl_menuPizzaInitialPanel.createSequentialGroup()
						.addGap(15)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 476, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(28, Short.MAX_VALUE))
				.addGroup(gl_menuPizzaInitialPanel.createSequentialGroup()
						.addGap(189)
						.addComponent(menuPizzaErrorMessage)
						.addContainerGap(204, Short.MAX_VALUE))
				);
		gl_menuPizzaInitialPanel.setVerticalGroup(
				gl_menuPizzaInitialPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuPizzaInitialPanel.createSequentialGroup()
						.addGap(8)
						.addComponent(lblAvailablePizzas)
						.addGap(18)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
						.addGap(18)
						.addGroup(gl_menuPizzaInitialPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblOtherActions)
								.addComponent(lblDeletePizza))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_menuPizzaInitialPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnLaunchNewMenuPizzaPage)
								.addComponent(cBDeletePizza, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSelect_1))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_menuPizzaInitialPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_menuPizzaInitialPanel.createSequentialGroup()
										.addGroup(gl_menuPizzaInitialPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(btnLaunchEditPizzaPage)
												.addComponent(btnDelete))
										.addGap(25))
								.addGroup(gl_menuPizzaInitialPanel.createSequentialGroup()
										.addComponent(menuPizzaErrorMessage)
										.addContainerGap())))
				);

		menuPizzaTable = new JTable();
		scrollPane.setViewportView(menuPizzaTable);
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
		}
				);
		
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
										.addComponent(btnRemoveIngredientFromMenuPizza, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
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
		refreshMenuPizzaInitialData();
	}

	private void refreshMenuPizzaInitialData() {
		
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

	//  End of Panel Initialization and refresh methods 

	//  Beging action performed methods 

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
		ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
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
			for (int i = 0; i < addedListModel.getSize() ; i++) {
	            ingredientList.add(addedListModel.getElementAt(i));
	        }
		}
		
		if (error.length() == 0) {
			try {
				PdsController.createMenuPizza(fldNewMenuPizzaName.getText(),calorieCount,price,ingredientList.toArray(new Ingredient[ingredientList.size()]));
			} catch (InvalidInputException e) {
				error = e.getMessage();
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
	//End additional utilities
}
