package fr.tcchat.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import fr.tcchat.utils.Utils;

public class MessagesRender implements ListCellRenderer<MessagesUI> {
	
	private final int MAX_WIDTH = 500;
	private final int PANEL_WIDTH = 665;
	//665
	
    @Override
    public Component getListCellRendererComponent(JList<? extends MessagesUI> list, MessagesUI myMessage, int index,
        boolean isSelected, boolean cellHasFocus) {
    	
    	Dimension senderDim = Utils.getSizeOfText(myMessage.getSender().getUsername(), new Font("Calibri", Font.BOLD, 14));
    	Dimension messageDim = Utils.getSizeOfText(myMessage.getMessage(), new Font("Calibri", Font.PLAIN, 12));
    	
    	int marginLeft = 0;
    	int marginRight = (int) (this.PANEL_WIDTH - messageDim.getWidth() - 38);
    	int marginTop = 24;
    	int topMessage = 22;
    	int heightPlus = 35;
    	
    	if(myMessage.isMyMessage()) {
    		marginLeft = (int) (this.PANEL_WIDTH - messageDim.getWidth() - 56);
    		marginRight = 14;
    		marginTop = 10;
    		topMessage = 10;
    		heightPlus = 35;

    		if(marginLeft < this.PANEL_WIDTH - this.MAX_WIDTH) {
    			marginLeft = this.PANEL_WIDTH - this.MAX_WIDTH;
    		}
    	} else {
    		if(marginRight < this.PANEL_WIDTH - this.MAX_WIDTH) {
    			marginRight = this.PANEL_WIDTH - this.MAX_WIDTH;
    		}
    	}
    	
    	HashMap<Integer, String> line = new HashMap<Integer, String>(); 
    	
		String[] testMessage = myMessage.getMessage().split("\n");
		int idx = -1;
		for (String string : testMessage) {
			String[] myLine = string.split(" ");
			line.put(++idx, "");
			for (String word : myLine) {
				if((int) (Utils.getSizeOfText(line.get(idx), new Font("Calibri", Font.PLAIN, 12)).getWidth() + Utils.getSizeOfText(word, new Font("Calibri", Font.PLAIN, 12)).getWidth()) + 20 < this.MAX_WIDTH - 80) {
					line.replace(idx, line.get(idx) + word + " ");
				} else {
					line.put(++idx, word);
				}
			}
		}

    	JPanel myPanel = new JPanel();
    	myPanel.setOpaque(true);
    	myPanel.setLayout(null);
    	myPanel.setPreferredSize(new Dimension(0 , (int) (heightPlus + messageDim.getHeight())));
    	
    	myPanel.setBorder(BorderFactory.createMatteBorder(marginTop, marginLeft, 10, marginRight, Color.white));
        
        myPanel.setBackground(Color.decode("#eeeeee"));
		
		if(!myMessage.isMyMessage()) {
			JLabel name = new JLabel(myMessage.getSender().getUsername());
			name.setBounds(24, 4, 200, 20);
			name.setFont(new Font("Calibri", Font.BOLD, 14));
			name.setForeground(Color.decode("#393e46"));
			
			myPanel.add(name);
		}
		
		int total = (int) (messageDim.getHeight());
		
		JLabel message = new JLabel(line.get(0));
		message.setBounds(12 + marginLeft, topMessage, (int) messageDim.getWidth() + 26, 30);
		message.setFont(new Font("Calibri", Font.PLAIN, 12));
		message.setForeground(Color.decode("#393e46"));
		myPanel.add(message);
		
		for(int i = 1; i < line.size(); i++) {
			message = new JLabel(line.get(i));
			message.setBounds(12 + marginLeft, topMessage + i*14, (int) messageDim.getWidth() + 26, 30);
			message.setFont(new Font("Calibri", Font.PLAIN, 12));
			message.setForeground(Color.decode("#393e46"));
			myPanel.add(message);
			
			total = topMessage + 4 + i*14;
		}
		
		if(line.size() == 1 && !myMessage.isMyMessage()) {
			total += 10;
		}
		myPanel.setPreferredSize(new Dimension(0 , (int) (heightPlus + total)));

	
		
		if(isSelected) {
			myPanel.setBackground(Color.decode("#dddddd"));
		} else {
			myPanel.setBackground(Color.decode("#eeeeee"));
		}
        return myPanel;
    }
}