package fr.tcchat.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import fr.tcchat.Main;
import fr.tcchat.packet.EnumPacket;
import fr.tcchat.packet.TCPacket;

public class FramePopup {
	private JDialog frame;
	
	private static FramePopup instance;
	
	private JComboBox cbInput;
	private DefaultComboBoxModel model;
	private JTextField txtInput;
	
	public FramePopup(JFrame owner) {
		/* Start menu */
		frame = new JDialog(owner);
		frame.setBounds(0, 0, 500, 325);
		frame.setLayout(null);
		frame.setUndecorated(true);
		frame.setBackground(Color.decode("#FFFFFF"));
		
		instance = this;
		
		frame.setTitle("Création d'une conversation");
		frame.setModal(true);
		
		frame.setResizable(false);
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	    frame.setIconImage(new ImageIcon(new ImageIcon(this.getClass().getResource("/res/logo.png")).getImage().getScaledInstance(256, 256, Image.SCALE_DEFAULT)).getImage());
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.decode("#DDDDDD"));
		panel.setBounds(0, 0, 500, 40);
		frame.add(panel);
		panel.setLayout(null);
		
		JLabel closeButton = new JLabel();
		closeButton.setBounds(470, 10, 20, 20);
		closeButton.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/res/close.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
		
		closeButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		frame.dispose();
        	}
        	
        	@Override
        	public void mouseEntered(MouseEvent arg0) {
        		closeButton.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/res/close_hover.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        	}
        	
        	@Override
        	public void mouseExited(MouseEvent arg0) {
        		closeButton.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/res/close.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        	}
        });
		
		
		JLabel logo = new JLabel();
		logo.setBounds(10, 2, 36, 36);
		logo.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/res/logo.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
		
		panel.add(logo);
		panel.add(closeButton);
		/* End menu */
		
        txtInput = new JTextField();
        txtInput.setBounds(150, 80, 200, 34);
        txtInput.setBorder(BorderFactory.createEmptyBorder());
        txtInput.setFont(new Font("Calibri", Font.PLAIN, 18));
        txtInput.setForeground(Color.decode("#393e46"));
        setupAutoComplete(txtInput);
        frame.add(txtInput);
        
	}
	
	public Window getFrame() {
		return this.frame;
	}
	
	public static FramePopup getInstance() {
		return instance;
	}
	
	private boolean isAdjusting(JComboBox cbInput) {
        if (cbInput.getClientProperty("is_adjusting") instanceof Boolean) {
            return (Boolean) cbInput.getClientProperty("is_adjusting");
        }
        return false;
    }

    private void setAdjusting(JComboBox cbInput, boolean adjusting) {
        this.cbInput.putClientProperty("is_adjusting", adjusting);
    }

    public void setupAutoComplete(final JTextField txtInput) {
        this.model = new DefaultComboBoxModel();
        this.cbInput = new JComboBox(this.model) {
            public Dimension getPreferredSize() {
                return new Dimension(super.getPreferredSize().width, 0);
            }
        };
        setAdjusting(this.cbInput, false);
        
        JScrollPane test = new JScrollPane(this.cbInput);
        
        test.setBackground(Color.decode("#FFFFFF"));
        test.setBorder(BorderFactory.createEmptyBorder());
        
        this.cbInput.setSelectedItem(null);
        this.cbInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isAdjusting(cbInput)) {
                    if (cbInput.getSelectedItem() != null) {
                    	selectedName();
                    }
                }
            }
        });

        txtInput.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                setAdjusting(cbInput, true);
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (cbInput.isPopupVisible()) {
                        e.setKeyCode(KeyEvent.VK_ENTER);
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    e.setSource(cbInput);
                    cbInput.dispatchEvent(e);
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    	selectedName();
                        //cbInput.setPopupVisible(false);
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    //cbInput.setPopupVisible(false);
                }
                setAdjusting(cbInput, false);
            }
        });
        txtInput.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                updateList();
            }

            public void removeUpdate(DocumentEvent e) {
                updateList();
            }

            public void changedUpdate(DocumentEvent e) {
                updateList();
            }

            private void updateList() {
                String input = txtInput.getText();
                if(!input.equals("")) {
                    Main.getInstance().sendPacket(new TCPacket(EnumPacket.GET_USERS).write(txtInput.getText()));
                }
                cbInput.setPopupVisible(false);
            }
        });
        txtInput.setLayout(new BorderLayout());
        txtInput.add(test, BorderLayout.SOUTH);
    }
    
    public void selectedName() {
    	txtInput.setText(cbInput.getSelectedItem().toString());
    	Main.getInstance().sendPacket(new TCPacket(EnumPacket.CREATE_GROUP).write(cbInput.getSelectedItem().toString()));
    	this.getFrame().dispose();
    }
    
    public void updateSelector(HashMap<Integer, String> users) {
    	if(this.getFrame().isActive()) {
        	cbInput.setPopupVisible(false);
        	setAdjusting(this.cbInput, true);
        	model.removeAllElements();
        	if(users.size() > 0) {
        		for (int i : users.keySet()) {
        		  	this.model.addElement(users.get(i));
        		}
        	}
        	this.cbInput.setPopupVisible(this.model.getSize() > 0);
        	setAdjusting(this.cbInput, false);
    	}
    }
}
