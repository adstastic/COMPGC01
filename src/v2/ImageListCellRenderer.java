package v2;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ImageListCellRenderer implements ListCellRenderer{
	
	@Override
	public Component getListCellRendererComponent(JList list, 
												  Object value, 
												  int index,
												  boolean isSelected, 
												  boolean cellHasFocus) {
		if (value instanceof JLabel) {	
			JLabel item = (JLabel) value;
			if (isSelected) {
				item.setBackground(Color.BLUE);
				item.setForeground(Color.WHITE);
				item.setOpaque(true);
			} else {
				item.setBackground(Color.WHITE);
				item.setForeground(Color.BLACK);
				item.setOpaque(false);
			}
			return item;
		} else {
			return new JLabel("Image not found");
		}
		
	}
	
}

