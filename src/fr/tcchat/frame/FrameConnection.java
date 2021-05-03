package fr.tcchat.frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import fr.tcchat.Main;
import fr.tcchat.packet.EnumPacket;
import fr.tcchat.packet.TCPacket;

public class FrameConnection extends FrameDefault {
	
	private JFrame frame;
	
	private JPanel connectionPanel;
	private JPanel connectionTitlePanel;
	private JPanel connectionButtonPanel;
	
	private JLabel connectionTitleLabel;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JLabel connectionErrorLabel;
	private JLabel connectionButtonTitleLabel;
	private JLabel inscriptionLabel;
	
	private JTextField usernameTextField;
	private JPasswordField passwordTextField;

	public FrameConnection() {
		
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
		
		this.connectionPanel = new JPanel();
		this.connectionTitlePanel = new JPanel();
		this.connectionButtonPanel = new JPanel();
		
		this.connectionTitleLabel = new JLabel();
		this.usernameLabel = new JLabel();
		this.passwordLabel = new JLabel();
		this.connectionErrorLabel = new JLabel();
		this.connectionButtonTitleLabel = new JLabel();
		this.inscriptionLabel = new JLabel();
		
		this.usernameTextField = new JTextField();
		this.passwordTextField = new JPasswordField();
		
		
		this.connectionPanel.setBackground(Color.decode("#DDDDDD"));
		this.connectionPanel.setBounds(287, 120, 401, 400);
		this.connectionPanel.setLayout(null);
		
		this.connectionTitlePanel.setBackground(Color.decode("#393e46"));
		this.connectionTitlePanel.setBounds(0, 0, 401, 50);
		this.connectionTitlePanel.setLayout(null);
		
		this.connectionTitleLabel.setText("CONNEXION");
		this.connectionTitleLabel.setBounds(0, 0, 401, 54);
		this.connectionTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.connectionTitleLabel.setVerticalAlignment(SwingConstants.CENTER);
		this.connectionTitleLabel.setFont(new Font("Calibri", Font.BOLD, 28));
		this.connectionTitleLabel.setForeground(Color.decode("#ffd369"));
		
		this.usernameLabel.setText("Nom d'utilisateur");
		this.usernameLabel.setBounds(66, 94, 280, 20);
		this.usernameLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
		this.usernameLabel.setForeground(Color.decode("#393e46"));
		this.usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.usernameTextField.setBounds(60, 120, 280, 34);
		this.usernameTextField.setBorder(BorderFactory.createEmptyBorder());
		this.usernameTextField.setFont(new Font("Calibri", Font.PLAIN, 22));
		this.usernameTextField.setHorizontalAlignment(SwingConstants.CENTER);
		this.usernameTextField.setForeground(Color.decode("#393e46"));
		
		this.passwordLabel.setText("Mot de passe");
		this.passwordLabel.setBounds(66, 194, 280, 20);
		this.passwordLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
		this.passwordLabel.setForeground(Color.decode("#393e46"));
		this.passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.passwordTextField.setBounds(60, 220, 280, 34);
		this.passwordTextField.setBorder(BorderFactory.createEmptyBorder());
		this.passwordTextField.setHorizontalAlignment(SwingConstants.CENTER);
		this.passwordTextField.setFont(new Font("Calibri", Font.PLAIN, 22));
		this.passwordTextField.setForeground(Color.decode("#393e46"));
		
		this.connectionErrorLabel.setText("");
		this.connectionErrorLabel.setBounds(50, 260, 301, 40);
		this.connectionErrorLabel.setFont(new Font("Calibri", Font.ITALIC, 16));
		this.connectionErrorLabel.setForeground(Color.decode("#91091e"));
		this.connectionErrorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.connectionErrorLabel.setVisible(false);
		
		
		this.connectionButtonPanel.setBackground(Color.decode("#393e46"));
		this.connectionButtonPanel.setBounds(110, 310, 180, 50);
		this.connectionButtonPanel.setLayout(null);
		
		this.connectionButtonPanel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		execLogin(usernameTextField.getText(), String.valueOf(passwordTextField.getPassword()));
        	}
        	
        	@Override
        	public void mouseEntered(MouseEvent arg0) {
        		connectionButtonPanel.setBackground(Color.decode("#222831"));
        	}
        	
        	@Override
        	public void mouseExited(MouseEvent arg0) {
        		connectionButtonPanel.setBackground(Color.decode("#393e46"));
        	}
        });
		
		this.connectionButtonTitleLabel.setText("CONNEXION");
		this.connectionButtonTitleLabel.setBounds(0, 0, 180, 54);
		this.connectionButtonTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.connectionButtonTitleLabel.setVerticalAlignment(SwingConstants.CENTER);
		this.connectionButtonTitleLabel.setFont(new Font("Calibri", Font.BOLD, 24));
		this.connectionButtonTitleLabel.setForeground(Color.decode("#ffd369"));
		
		this.inscriptionLabel.setText("Pas encore inscrit ?");
		this.inscriptionLabel.setBounds(10, 368, 381, 30);
		this.inscriptionLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		this.inscriptionLabel.setForeground(Color.decode("#393e46"));
		

		this.inscriptionLabel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		Main.getInstance().getIHM().show(Main.getInstance().getIHM().frameInscription);
        	}
        });
		
		this.connectionButtonPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		this.connectionTitlePanel.add(this.connectionTitleLabel);
		
		this.connectionButtonPanel.add(this.connectionButtonTitleLabel);
		
		this.connectionPanel.add(this.usernameLabel);
		this.connectionPanel.add(this.usernameTextField);
		this.connectionPanel.add(this.passwordLabel);
		this.connectionPanel.add(this.passwordTextField);
		this.connectionPanel.add(this.connectionErrorLabel);
		this.connectionPanel.add(this.connectionButtonPanel);
		this.connectionPanel.add(this.connectionTitlePanel);
		this.connectionPanel.add(this.inscriptionLabel);

		this.frame.add(this.connectionPanel);

		this.execLogin(Main.getInstance().getConfig().get("username"), Main.getInstance().getConfig().get("password"));
	}

	public JFrame getFrame() {
		return this.frame;
	}
	
	public void execLogin(String username, String password) {
		connectionErrorLabel.setVisible(false);
		
		if(username == null || password == null) {
			return;
		} else {
			usernameTextField.setText(username);
			passwordTextField.setText(password);
		}
		
		if(username.length() == 0) {
			connectionErrorLabel.setText("Veuillez mettre un nom d'utilisateur");
			connectionErrorLabel.setVisible(true);
			return;
		} else if(password.length() == 0) {
			connectionErrorLabel.setText("Veuillez mettre un mot de passe");
			connectionErrorLabel.setVisible(true);
			return;
		}
		
		TCPacket myPacket = new TCPacket(EnumPacket.CONNECTION);
		Main.getInstance().sendPacket(myPacket.write(username, password));
	}
	
	@Override
	public void displayError(EnumPacket type, String message) {
		this.connectionErrorLabel.setText("Nom d'utilisateur ou mot de passe invalide");
		this.connectionErrorLabel.setVisible(true);
	}

	@Override
	public void valid(EnumPacket type) {
		Main.getInstance().getConfig().set("username", usernameTextField.getText());
		Main.getInstance().getConfig().set("password", String.valueOf(passwordTextField.getPassword()));
		Main.getInstance().getIHM().show(Main.getInstance().getIHM().frameHome);
		
		usernameTextField.setText("");
		passwordTextField.setText("");
	}

	@Override
	public void valid(EnumPacket type, String data) {
		// TODO Auto-generated method stub
		
	}
}