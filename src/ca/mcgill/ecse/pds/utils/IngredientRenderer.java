package ca.mcgill.ecse.pds.utils;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import ca.mcgill.ecse.pds.model.Ingredient;

public class IngredientRenderer extends JLabel implements ListCellRenderer<Ingredient> { 

	//private static final long serialVersionUID = 1529112079821173014L;
	
	public IngredientRenderer() {
        setOpaque(true);
    }
	@Override
    public Component getListCellRendererComponent(JList<? extends Ingredient> list, Ingredient ingredient, int index, boolean isSelected, boolean cellHasFocus) {
        setText(ingredient.getName());     
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        return this;
    } 
}