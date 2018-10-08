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

public class PdsPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -169243617689486922L;
	private JPanel contentPanel;
	private JTabbedPane tabbedPanel;
	private GroupLayout gl_contentPanel;
	
	//Ingredient Panel Elements
	private JPanel IngredientsPanel;
	private JScrollPane ingredientOverviewScrollPane;
	private JTextField fldNewIngredient;
	private JTextField fldNewIngredientPrice;
	private JTextField fldUpdatedPrice;
	private JComboBox<String> cBRemoveIngredient;
	private JComboBox<String> cBUpdateIngredient;
	private JTable IngredientTable;
	
	//Menu Pizza Panel Elements
	private JPanel MenuPizzaPanel;
	
	// data elements
	private String error = null;
	
	private HashMap<Integer, Ingredient> ingredients;
	private Integer selectedRemoveIngredient = -1;
	private Integer selectedUpdateIngredient = -1;
	private String overviewIngredientColumnNames[] = {"Name", "Price"};
	
	//UI elements
	private JLabel errorMessage;
	
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
		IngredientsPanel = new JPanel();
		tabbedPanel.addTab("Ingredients", null, IngredientsPanel, null);
		
		//Menu Pizza Tab
		MenuPizzaPanel = new JPanel();
    	tabbedPanel.addTab("Menu Pizzas", null, MenuPizzaPanel, null);
		
    	//Should we dynamically init on tab click ?
    	errorMessage = new JLabel();
    	initIngredientTab();
    	
		contentPanel.setLayout(gl_contentPanel);
	}
	private void initIngredientTab() {
		JLabel lblAvailable = new JLabel("Available ");
		
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
			
			}
		});
		
		JLabel lblUpdatePrice = new JLabel("Update Price");
		lblUpdatePrice.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		
		JLabel lblSelect = new JLabel("Select");
		lblSelect.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		
		cBUpdateIngredient = new JComboBox();
		
		JButton btnUpdate = new JButton("Update");
		
		JLabel lblNewPrice = new JLabel("Price");
		
		fldUpdatedPrice = new JTextField();
		fldUpdatedPrice.setColumns(10);
		
		JLabel lblRemoveIngredient = new JLabel("Remove Ingredient");
		
		JLabel lblRemoveSelect = new JLabel("Select");
		
		cBRemoveIngredient = new JComboBox();
		
		JButton btnRemoveIngredient = new JButton("Remove");
		
		ingredientOverviewScrollPane = new JScrollPane();
		GroupLayout gl_IngredientsPanel = new GroupLayout(IngredientsPanel);
		gl_IngredientsPanel.setHorizontalGroup(
			gl_IngredientsPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_IngredientsPanel.createSequentialGroup()
					.addGroup(gl_IngredientsPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_IngredientsPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnRemoveIngredient))
						.addGroup(gl_IngredientsPanel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_IngredientsPanel.createSequentialGroup()
								.addGap(44)
								.addComponent(lblAvailable))
							.addGroup(gl_IngredientsPanel.createSequentialGroup()
								.addGap(41)
								.addComponent(lblRemoveIngredient))
							.addGroup(gl_IngredientsPanel.createSequentialGroup()
								.addGap(17)
								.addGroup(gl_IngredientsPanel.createParallelGroup(Alignment.LEADING)
									.addComponent(ingredientOverviewScrollPane, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_IngredientsPanel.createSequentialGroup()
										.addComponent(lblRemoveSelect)
										.addGap(18)
										.addComponent(cBRemoveIngredient, 0, 96, Short.MAX_VALUE))))))
					.addGap(58)
					.addGroup(gl_IngredientsPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_IngredientsPanel.createSequentialGroup()
							.addGroup(gl_IngredientsPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_IngredientsPanel.createSequentialGroup()
									.addComponent(lblNewIngredient)
									.addGap(35))
								.addGroup(gl_IngredientsPanel.createSequentialGroup()
									.addGroup(gl_IngredientsPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_IngredientsPanel.createSequentialGroup()
											.addComponent(lblNewIngredientLabel)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(fldNewIngredient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_IngredientsPanel.createParallelGroup(Alignment.TRAILING)
											.addComponent(btnAddIngredient)
											.addGroup(gl_IngredientsPanel.createSequentialGroup()
												.addComponent(lblNewIngredientPrice)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(fldNewIngredientPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
										.addGroup(gl_IngredientsPanel.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_IngredientsPanel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_IngredientsPanel.createSequentialGroup()
													.addComponent(lblNewPrice)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(fldUpdatedPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_IngredientsPanel.createSequentialGroup()
													.addComponent(lblSelect)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(cBUpdateIngredient, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)))))
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGap(9))
						.addGroup(gl_IngredientsPanel.createSequentialGroup()
							.addComponent(lblUpdatePrice)
							.addGap(40))
						.addGroup(gl_IngredientsPanel.createSequentialGroup()
							.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_IngredientsPanel.setVerticalGroup(
			gl_IngredientsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_IngredientsPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_IngredientsPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAvailable)
						.addComponent(lblNewIngredient))
					.addGap(6)
					.addGroup(gl_IngredientsPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_IngredientsPanel.createSequentialGroup()
							.addGroup(gl_IngredientsPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewIngredientLabel)
								.addComponent(fldNewIngredient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_IngredientsPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewIngredientPrice)
								.addComponent(fldNewIngredientPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAddIngredient)
							.addPreferredGap(ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
							.addComponent(lblUpdatePrice)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_IngredientsPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSelect)
								.addComponent(cBUpdateIngredient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_IngredientsPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewPrice)
								.addComponent(fldUpdatedPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnUpdate))
						.addGroup(gl_IngredientsPanel.createSequentialGroup()
							.addComponent(ingredientOverviewScrollPane, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblRemoveIngredient)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_IngredientsPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRemoveSelect)
								.addComponent(cBRemoveIngredient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRemoveIngredient)))
					.addContainerGap())
		);
		
		IngredientTable = new JTable();
		ingredientOverviewScrollPane.setViewportView(IngredientTable);
		IngredientsPanel.setLayout(gl_IngredientsPanel);
		refreshIngredientData();
	}
	
	private void refreshIngredientData() {
		// error
		errorMessage.setText(error);
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
			IngredientTable.setModel(ingredientOverviewDtm);
			for (Ingredient ingredient : PdsController.getIngredients()) {
				Object[] obj = {ingredient.getName(), ingredient.getPrice()};
				ingredientOverviewDtm.addRow(obj);
			}
			Dimension d = IngredientTable.getPreferredSize();
			ingredientOverviewScrollPane.setPreferredSize(new Dimension(d.width, 100));
		}
	}	
    private void initMenuPizzaTab() {
    	
    }

    private void addIngredientButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message
		error = null;
		// call the controller
		try {
			PdsController.createIngredient(fldNewIngredient.getText(), Float.parseFloat(fldNewIngredientPrice.getText()));
		} catch (InvalidInputException e) {
			error = e.getMessage();
			System.out.println("Error: " + error);
		}
		// update visuals
		refreshIngredientData();
	}
}
