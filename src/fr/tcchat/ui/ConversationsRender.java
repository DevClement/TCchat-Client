package fr.tcchat.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

public class ConversationsRender implements ListCellRenderer<ConversationsUI> {
	 
    @Override
    public Component getListCellRendererComponent(JList<? extends ConversationsUI> list, ConversationsUI conversation, int index,
        boolean isSelected, boolean cellHasFocus) {
    	
    	int borderTop = (index == 0) ? 0 : 10;
    	int borderBottom = (index == list.getModel().getSize() -1) ? 0 : 10;
    	
    	int height = Math.abs(borderTop + borderBottom - 20);
    	
    	JPanel myPanel = new JPanel();
    	myPanel.setOpaque(true);
    	myPanel.setLayout(null);
    	myPanel.setPreferredSize(new Dimension(200 , 80 - height));
    	
    	myPanel.setBorder(BorderFactory.createMatteBorder(borderTop, 0, borderBottom, 10, Color.white));
        
        myPanel.setBackground(Color.decode("#eeeeee"));

		JLabel name = new JLabel(conversation.getName());
		name.setBounds(20, 14 + (borderTop - 10), 200, 30);
		name.setFont(new Font("Calibri", Font.BOLD, 16));
		name.setForeground(Color.decode("#393e46"));
		
		JLabel message = new JLabel(conversation.getLastMessage());
		message.setBounds(20, 40 + (borderTop - 10), 210, 30);
		message.setFont(new Font("Calibri", Font.PLAIN, 12));
		message.setForeground(Color.decode("#393e46"));

		myPanel.add(name);
		myPanel.add(message);
		
		if(isSelected) {
			myPanel.setBackground(Color.decode("#dddddd"));
		} else {
			myPanel.setBackground(Color.decode("#eeeeee"));
		}

        return myPanel;
    }
     
}