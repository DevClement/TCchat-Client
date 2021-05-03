package fr.tcchat.frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.tcchat.Main;
import fr.tcchat.config.Config;
import fr.tcchat.packet.EnumPacket;
import fr.tcchat.packet.TCPacket;
import fr.tcchat.ui.ConversationsRender;
import fr.tcchat.ui.ConversationsUI;
import fr.tcchat.ui.MessagesRender;
import fr.tcchat.ui.MessagesUI;
import fr.tcchat.ui.ScrollBarUI;

public class FrameHome extends FrameDefault {
	
	private JFrame frame;
	
	private JPanel conversationPanel;
	private JPanel conversationTitlePanel;
	private JPanel userPanel;
	private JPanel conversationsPanel;
	private JLabel signOutLabel;
	
	private DefaultListModel<ConversationsUI> conversationList;
	
	private JScrollPane conversations;
	
	private JList<ConversationsUI> contentConversationsList;
	
	/* Chat Panel */
	private JPanel chatPanel;
	private JPanel chatTitlePanel;
	
	private JLabel chatTitleLabel;
	
	private JPanel chatContentPanel;
	
	private DefaultListModel<MessagesUI> messageListModel;
	
	private JScrollPane messagesContentScroll;
	
	private JList<MessagesUI> messageList;
	
	private JPanel chatActionPanel;
	
	private JTextField messageTextField;
	
	private JPanel sendMessageButton;
	private JLabel sendMessageTitleButton;
	
	private JPanel leaveDiscutionButton;
	private JLabel leaveDiscutionMessageButton;
	
	private int currentGroupID;
	
	public FrameHome() {
		/* Start menu */
		frame = new JFrame();
		frame.setBounds(100, 100, 975, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setUndecorated(true);
		frame.setBackground(Color.decode("#FFFFFF"));
		
		frame.setResizable(false);
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	    frame.setIconImage(new ImageIcon(new ImageIcon(this.getClass().getResource("/res/logo.png")).getImage().getScaledInstance(256, 256, Image.SCALE_DEFAULT)).getImage());
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.decode("#DDDDDD"));
		panel.setBounds(0, 0, 975, 40);
		frame.add(panel);
		panel.setLayout(null);
		
		JLabel closeButton = new JLabel();
		closeButton.setBounds(945, 10, 20, 20);
		closeButton.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/res/close.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
		
		closeButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		System.exit(0);
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
		
		JLabel hideButton = new JLabel();
		hideButton.setBounds(910, 10, 20, 20);
		hideButton.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/res/hide.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
		
		hideButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		frame.setExtendedState(JFrame.ICONIFIED);
        		frame.setState(JFrame.ICONIFIED);
        	}
        	
        	@Override
        	public void mouseEntered(MouseEvent arg0) {
        		hideButton.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/res/hide_hover.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        	}
        	
        	@Override
        	public void mouseExited(MouseEvent arg0) {
        		hideButton.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/res/hide.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        	}
        });
		
		JLabel logo = new JLabel();
		logo.setBounds(10, 2, 36, 36);
		logo.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/res/logo.png")).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
		
		panel.add(logo);
		panel.add(hideButton);
		panel.add(closeButton);
		
        FrameMover frameDragListener = new FrameMover(frame);
        panel.addMouseListener(frameDragListener);
        panel.addMouseMotionListener(frameDragListener);
		/* End menu */
		
		
		
		this.conversationPanel = new JPanel();
		this.chatPanel = new JPanel();
		this.conversationTitlePanel = new JPanel();
		
		this.userPanel = new JPanel() {
			@Override
		    protected void paintComponent(Graphics g) {
		    	super.paintComponent(g);
		        Dimension arcs = new Dimension(40,40);
		        int width = getWidth();
		        int height = getHeight();
		        Graphics2D graphics = (Graphics2D) g;
		        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


		        //Draws the rounded opaque panel with borders.
		        graphics.setColor(Color.decode("#222831"));
		        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
		    }
		};
		
		JLabel test = new JLabel("+");
		test.setBounds(10, 10, 40, 40);
		test.setHorizontalAlignment(SwingConstants.CENTER);
		test.setVerticalAlignment(SwingConstants.CENTER);
		test.setFont(new Font("Calibri", Font.BOLD, 40));
		test.setForeground(Color.decode("#FFFFFF"));
		
		
		this.conversationsPanel = new JPanel();
		
		this.conversationPanel.setBackground(Color.decode("#FFFFFF"));
		this.conversationPanel.setBounds(10, 50, 260, 590);
		this.conversationPanel.setLayout(null);
		
		this.conversationsPanel.setBackground(Color.decode("#FFFFFF"));
		this.conversationsPanel.setBounds(0, 60, 260, 530);
		this.conversationsPanel.setLayout(null);
		
		this.conversationTitlePanel.setBackground(Color.decode("#393e46"));
		this.conversationTitlePanel.setBounds(0, 0, 260, 60);
		this.conversationTitlePanel.setLayout(null);
		
		this.userPanel.setBounds(10,10,40,40);
		this.userPanel.setOpaque(false);
		
		this.userPanel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		createPopupGroup();
        	}
        });
		
		
		this.signOutLabel = new JLabel();
		this.signOutLabel.setBounds(210, 15, 30, 30);
		this.signOutLabel.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/res/sign_out.png")).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
		
		this.signOutLabel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		signOut();
        	}
        });
		
		this.conversationList = new DefaultListModel<ConversationsUI>();
		
		this.contentConversationsList = new JList<ConversationsUI>(this.conversationList);

	    this.contentConversationsList.setCellRenderer(new ConversationsRender());
	    this.contentConversationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		this.conversations = new JScrollPane(this.contentConversationsList);
		
		this.conversations.getVerticalScrollBar().setUI(new ScrollBarUI());
		this.conversations.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
		this.conversations.getVerticalScrollBar().setBackground(Color.decode("#FFFFFF"));
		this.conversations.setBorder(BorderFactory.createEmptyBorder());
		this.conversations.setBounds(10, 10, 250, 510);
		
		this.contentConversationsList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting() && contentConversationsList.getSelectedValue() != null && currentGroupID != contentConversationsList.getSelectedValue().getId()) {
                	chatTitleLabel.setText(contentConversationsList.getSelectedValue().getName());
                	leaveDiscutionButton.setVisible(false);
                	if(contentConversationsList.getSelectedValue().getId() != 2) {
                		leaveDiscutionButton.setVisible(true);
                	}
                	displayMessageGroup(contentConversationsList.getSelectedValue().getId());
                }
            }
        });
		
		/* Chat Panel */
		this.chatPanel.setBackground(Color.decode("#FFFFFF"));
		this.chatPanel.setBounds(290, 50, 675, 590);
		this.chatPanel.setLayout(null);
		
		this.chatTitlePanel = new JPanel();
		this.chatTitlePanel.setBackground(Color.decode("#393e46"));
		this.chatTitlePanel.setBounds(0, 0, 675, 60);
		this.chatTitlePanel.setLayout(null);
		
		this.chatTitleLabel = new JLabel();
		this.chatTitleLabel.setBounds(20, 15, 635, 30);
		this.chatTitleLabel.setFont(new Font("Calibri", Font.BOLD, 24));
		this.chatTitleLabel.setForeground(Color.decode("#FFFFFF"));
		
		this.chatContentPanel = new JPanel();
		this.chatContentPanel.setBackground(Color.decode("#FFFFFF"));
		this.chatContentPanel.setBounds(10, 60, 665, 480);
		this.chatContentPanel.setLayout(null);
		
		this.messageListModel = new DefaultListModel<MessagesUI>();
		
		this.messageList = new JList<MessagesUI>(this.messageListModel);

	    this.messageList.setCellRenderer(new MessagesRender());
	    this.messageList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		this.messagesContentScroll = new JScrollPane(this.messageList);
		
		this.messagesContentScroll.getVerticalScrollBar().setUI(new ScrollBarUI());
		this.messagesContentScroll.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
		this.messagesContentScroll.getVerticalScrollBar().setBackground(Color.decode("#FFFFFF"));
		this.messagesContentScroll.setBorder(BorderFactory.createEmptyBorder());
		this.messagesContentScroll.setBounds(0, 0, 665, 480);
		
		this.chatActionPanel = new JPanel();
		this.chatActionPanel.setBounds(0, 540, 675, 50);
		this.chatActionPanel.setBackground(Color.decode("#393e46"));
		this.chatActionPanel.setLayout(null);
		
		this.messageTextField = new JTextField("Écrivez votre message..");
		this.messageTextField.setBounds(65, 10, 480, 30);
		this.messageTextField.setBackground(Color.decode("#FFFFFF"));
		this.messageTextField.setBorder(null);
		this.messageTextField.setBorder(BorderFactory.createMatteBorder(0, 10, 0, 10, Color.white));
		
		this.messageTextField.setFont(new Font("Calibri", Font.PLAIN, 16));
		this.messageTextField.setForeground(Color.decode("#393e46"));
		
		this.messageTextField.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (messageTextField.getText().equals("Écrivez votre message..")) {
		        	messageTextField.setText("");
		        }
		    }
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (messageTextField.getText().isEmpty()) {
		        	messageTextField.setText("Écrivez votre message..");
		        }
		    }
		});
		
		this.messageTextField.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	            if(e.getKeyCode() == KeyEvent.VK_ENTER){
	            	sendMessage(messageTextField.getText());
	            }
	        }

	    });
		
		this.sendMessageButton = new JPanel();
		this.sendMessageButton.setBackground(Color.decode("#393e46"));
		this.sendMessageButton.setBounds(555, 10, 100, 30);
		this.sendMessageButton.setLayout(null);
		
		this.sendMessageButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		sendMessage(messageTextField.getText());
        	}
        	
        	@Override
        	public void mouseEntered(MouseEvent arg0) {
        		sendMessageButton.setBackground(Color.decode("#222831"));
        	}
        	
        	@Override
        	public void mouseExited(MouseEvent arg0) {
        		sendMessageButton.setBackground(Color.decode("#393e46"));
        	}
        });
		
		this.sendMessageTitleButton = new JLabel();
		this.sendMessageTitleButton.setText("Envoyer");
		this.sendMessageTitleButton.setBounds(0, 0, 100, 30);
		this.sendMessageTitleButton.setHorizontalAlignment(SwingConstants.CENTER);
		this.sendMessageTitleButton.setVerticalAlignment(SwingConstants.CENTER);
		this.sendMessageTitleButton.setFont(new Font("Calibri", Font.BOLD, 22));
		this.sendMessageTitleButton.setForeground(Color.decode("#FFFFFF"));
		
		
		this.leaveDiscutionButton = new JPanel();
		this.leaveDiscutionButton.setBackground(Color.decode("#393e46"));
		this.leaveDiscutionButton.setBounds(555, 15, 100, 30);
		this.leaveDiscutionButton.setLayout(null);
		
		this.leaveDiscutionButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		leaveDiscution();
        	}
        	
        	@Override
        	public void mouseEntered(MouseEvent arg0) {
        		leaveDiscutionButton.setBackground(Color.decode("#222831"));
        	}
        	
        	@Override
        	public void mouseExited(MouseEvent arg0) {
        		leaveDiscutionButton.setBackground(Color.decode("#393e46"));
        	}
        });
		
		this.leaveDiscutionMessageButton = new JLabel();
		this.leaveDiscutionMessageButton.setText("Quitter");
		this.leaveDiscutionMessageButton.setBounds(0, 0, 100, 30);
		this.leaveDiscutionMessageButton.setHorizontalAlignment(SwingConstants.CENTER);
		this.leaveDiscutionMessageButton.setVerticalAlignment(SwingConstants.CENTER);
		this.leaveDiscutionMessageButton.setFont(new Font("Calibri", Font.BOLD, 22));
		this.leaveDiscutionMessageButton.setForeground(Color.decode("#FF0000"));
		
		
		this.leaveDiscutionButton.add(this.leaveDiscutionMessageButton);
				
		this.chatActionPanel.add(this.messageTextField);
		this.chatActionPanel.add(this.sendMessageButton);
		
		this.conversationsPanel.add(this.conversations);
		
		this.conversationTitlePanel.add(test);
		this.conversationTitlePanel.add(this.userPanel);
		
		this.conversationTitlePanel.add(this.signOutLabel);
		this.conversationPanel.add(this.conversationTitlePanel);
		this.conversationPanel.add(this.conversationsPanel);
		
		this.chatTitlePanel.add(this.chatTitleLabel);
		this.chatTitlePanel.add(this.leaveDiscutionButton);
		
		this.chatContentPanel.add(this.messagesContentScroll);
		
		this.chatPanel.add(this.chatTitlePanel);
		this.chatPanel.add(this.chatContentPanel);
		this.chatPanel.add(this.chatActionPanel);
		
		this.frame.add(this.conversationPanel);
		this.frame.add(this.chatPanel);
	}

	@Override
	public void displayError(EnumPacket type, String message) {
		// TODO Auto-generated method stub
	}
	
	public void sendMessage(String message) {
        if(messageTextField.getText().length() == 0 || messageTextField.getText().replaceAll(" ", "").length() == 0 || messageTextField.getText().equals("Écrivez votre message..")) {
        	return;
        }
        
        this.messageTextField.setText("");
        Main.getInstance().sendPacket(new TCPacket(EnumPacket.MESSAGE).write(message));
	}
	
	public void signOut() {
		Main.getInstance().getConfig().remove("username");
		Main.getInstance().getConfig().remove("password");
		
		Main.getInstance().getIHM().show(Main.getInstance().getIHM().frameConnection);
		Main.getInstance().sendPacket(new TCPacket(EnumPacket.DISCONNECTION).writeNull());
		
		this.messageListModel.clear();
		this.conversationList.clear();
	}
	
	public void displayMessageGroup(int groupID) {
		this.messageListModel.clear();
		Main.getInstance().sendPacket(new TCPacket(EnumPacket.GET_MESSAGES).write(groupID));
		this.currentGroupID = groupID;
	}
	
	public void leaveDiscution() {
		Main.getInstance().sendPacket(new TCPacket(EnumPacket.LEAVE_GROUP).writeNull());
	}

	@Override
	public void valid(EnumPacket type) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void valid(EnumPacket type, String data) {
		switch (type) {
		case INITIALIZE_GROUPS:
			this.contentConversationsList.setVisible(false);
			String[] groups = data.split(Config.SEPARATOR_DATA);
			for (String group : groups) {
				ConversationsUI myConv = new ConversationsUI(group);
				this.conversationList.addElement(myConv);
			}
			
			System.out.println("Groupe : " + groups.length);
			this.contentConversationsList.setSelectedIndex(0);
			this.contentConversationsList.setVisible(true);
			break;
		case INITIALIZE_MESSAGES:
			this.messageList.setVisible(false);
			System.out.println("messages ! ");
			String[] messages = data.split(Config.SEPARATOR_DATA);
			for (String message : messages) {
				MessagesUI myConv = new MessagesUI(message);
				this.messageListModel.addElement(myConv);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Message : " + messages.length);
			this.messageList.setVisible(true);
			this.scrollToBottom();
			break;
			
		case SEND_MESSAGE:
			
			String[] datas = data.split(Config.SEPARATOR_DATA);
			
			MessagesUI myMessage = new MessagesUI(datas[0]);
			ConversationsUI myGroup = new ConversationsUI(datas[1]);
			
			this.messageListModel.addElement(myMessage);
			
			this.scrollToBottom();
			
			this.updateConv(myGroup);
			
			break;
			
		case INITIALIZE_GROUP: 
			ConversationsUI newGroup = new ConversationsUI(data);
			this.updateConv(newGroup);
			
        	chatTitleLabel.setText(newGroup.getName());
        	displayMessageGroup(newGroup.getId());
        	this.contentConversationsList.setSelectedIndex(0);
			break;
			
		case UPDATE_GROUP:
			ConversationsUI group = new ConversationsUI(data);
			this.removeConv(group);
			this.contentConversationsList.setSelectedIndex(0);
			break;
			
		case LOAD_GROUP: 
			ConversationsUI theGroup = new ConversationsUI(data);
        	chatTitleLabel.setText(theGroup.getName());
        	displayMessageGroup(theGroup.getId());
        	
        	int index = 0;
        	
        	for(; index < this.contentConversationsList.getModel().getSize(); index++) {
        		if(this.contentConversationsList.getModel().getElementAt(index).getId() == theGroup.getId()) {
        			break;
        		}
        	}
        	this.contentConversationsList.setSelectedIndex(index);
			break;

		default:
			break;
		}
	}
	
	public void createPopupGroup() {
		FramePopup popupGroup = new FramePopup(this.frame);
		popupGroup.getFrame().setVisible(true);
	}

	private void updateConv(ConversationsUI myGroup) {
		
		int groupExist = -1;

	    for (int i = 0; i < this.conversationList.getSize(); i++) {
	    	if(this.conversationList.getElementAt(i).getId() == myGroup.getId())
	    		groupExist = i;
	    }
	    
	    if(groupExist != -1) {
	    	this.conversationList.remove(groupExist);
			this.conversationList.add(0, myGroup);
			
			if(this.currentGroupID == myGroup.getId()) {
				this.contentConversationsList.setSelectedIndex(0);
			}
	    } else {
	    	this.conversationList.add(0, myGroup);
	    }
	}
	
	private void removeConv(ConversationsUI myGroup) {
		
		int groupExist = -1;

	    for (int i = 0; i < this.conversationList.getSize(); i++) {
	    	if(this.conversationList.getElementAt(i).getId() == myGroup.getId())
	    		groupExist = i;
	    }
	    
	    if(groupExist != -1) {
	    	this.conversationList.remove(groupExist);
	    }
	}

	@Override
	public Window getFrame() {
		return this.frame;
	}
	
	private void scrollToBottom(){
	    int lastIndex = this.messageList.getModel().getSize()-1;
	    if(lastIndex >= 0){
	    	this.messageList.ensureIndexIsVisible(lastIndex);
	    }
	}
}

