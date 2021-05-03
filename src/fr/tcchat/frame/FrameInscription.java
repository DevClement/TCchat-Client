package fr.tcchat.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
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

public class FrameInscription extends FrameDefault {
	
	private JFrame frame;
	
	private JPanel inscriptionPanel;
	private JPanel inscriptionTitlePanel;
	private JPanel inscriptionButtonPanel;
	
	private JLabel inscriptionTitleLabel;
	private JLabel usernameLabel;
	private JLabel emailLabel;
	private JLabel passwordLabel;
	private JLabel passwordRetryLabel;
	private JLabel inscriptionErrorLabel;
	private JLabel inscriptionButtonTitleLabel;
	private JLabel connexionLabel;
	
	private JTextField usernameTextField;
	private JTextField emailTextField;
	private JPasswordField passwordTextField;
	private JPasswordField passwordRetryTextField;
	
	public FrameInscription() {
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
		
		this.inscriptionPanel = new JPanel();
		this.inscriptionTitlePanel = new JPanel();
		this.inscriptionButtonPanel = new JPanel();

		this.inscriptionTitleLabel = new JLabel();
		this.usernameLabel = new JLabel();
		this.emailLabel = new JLabel();
		this.passwordLabel = new JLabel();
		this.passwordRetryLabel = new JLabel();
		this.inscriptionErrorLabel = new JLabel();
		this.inscriptionButtonTitleLabel = new JLabel();
		this.connexionLabel = new JLabel();
		
		this.usernameTextField = new JTextField();
		this.emailTextField = new JTextField();

		this.passwordTextField = new JPasswordField();
		this.passwordRetryTextField = new JPasswordField();
		
		
		this.inscriptionPanel.setBackground(Color.decode("#DDDDDD"));
		this.inscriptionPanel.setBounds(287, 70, 401, 540);
		this.inscriptionPanel.setLayout(null);
		
		this.inscriptionTitlePanel.setBackground(Color.decode("#393e46"));
		this.inscriptionTitlePanel.setBounds(0, 0, 401, 50);
		this.inscriptionTitlePanel.setLayout(null);
		
		this.inscriptionTitleLabel.setText("INSCRIPTION");
		this.inscriptionTitleLabel.setBounds(0, 0, 401, 54);
		this.inscriptionTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.inscriptionTitleLabel.setVerticalAlignment(SwingConstants.CENTER);
		this.inscriptionTitleLabel.setFont(new Font("Calibri", Font.BOLD, 28));
		this.inscriptionTitleLabel.setForeground(Color.decode("#ffd369"));
		
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
		
		this.emailLabel.setText("Adresse e-mail");
		this.emailLabel.setBounds(66, 174, 280, 20);
		this.emailLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
		this.emailLabel.setForeground(Color.decode("#393e46"));
		this.emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.emailTextField.setBounds(60, 200, 280, 34);
		this.emailTextField.setBorder(BorderFactory.createEmptyBorder());
		this.emailTextField.setFont(new Font("Calibri", Font.PLAIN, 22));
		this.emailTextField.setHorizontalAlignment(SwingConstants.CENTER);
		this.emailTextField.setForeground(Color.decode("#393e46"));
		
		this.passwordLabel.setText("Mot de passe");
		this.passwordLabel.setBounds(66, 254, 280, 20);
		this.passwordLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
		this.passwordLabel.setForeground(Color.decode("#393e46"));
		this.passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.passwordTextField.setBounds(60, 280, 280, 34);
		this.passwordTextField.setBorder(BorderFactory.createEmptyBorder());
		this.passwordTextField.setHorizontalAlignment(SwingConstants.CENTER);
		this.passwordTextField.setFont(new Font("Calibri", Font.PLAIN, 22));
		this.passwordTextField.setForeground(Color.decode("#393e46"));
		
		this.passwordRetryLabel.setText("Retapez votre mot de passe");
		this.passwordRetryLabel.setBounds(66, 334, 280, 20);
		this.passwordRetryLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
		this.passwordRetryLabel.setForeground(Color.decode("#393e46"));
		this.passwordRetryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.passwordRetryTextField.setBounds(60, 360, 280, 34);
		this.passwordRetryTextField.setBorder(BorderFactory.createEmptyBorder());
		this.passwordRetryTextField.setHorizontalAlignment(SwingConstants.CENTER);
		this.passwordRetryTextField.setFont(new Font("Calibri", Font.PLAIN, 22));
		this.passwordRetryTextField.setForeground(Color.decode("#393e46"));
		
		this.inscriptionErrorLabel.setText("");
		this.inscriptionErrorLabel.setBounds(50, 400, 301, 40);
		this.inscriptionErrorLabel.setFont(new Font("Calibri", Font.ITALIC, 16));
		this.inscriptionErrorLabel.setForeground(Color.decode("#91091e"));
		this.inscriptionErrorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.inscriptionErrorLabel.setVisible(false);
		
		
		this.inscriptionButtonPanel.setBackground(Color.decode("#393e46"));
		this.inscriptionButtonPanel.setBounds(110, 450, 180, 50);
		this.inscriptionButtonPanel.setLayout(null);
		
		this.inscriptionButtonPanel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		inscriptionErrorLabel.setVisible(false);
        		
        		if(usernameTextField.getText().length() == 0) {
        			inscriptionErrorLabel.setText("Veuillez mettre un nom d'utilisateur");
        			inscriptionErrorLabel.setVisible(true);
        			return;
        		} else if(emailTextField.getText().length() == 0) {
        			inscriptionErrorLabel.setText("Veuillez mettre une adresse email");
        			inscriptionErrorLabel.setVisible(true);
        			return;
        		} else if(String.valueOf(passwordTextField.getPassword()).length() == 0) {
        			inscriptionErrorLabel.setText("Veuillez mettre un mot de passe");
        			inscriptionErrorLabel.setVisible(true);
        			return;
        		} else if(String.valueOf(passwordRetryTextField.getPassword()).length() == 0) {
        			inscriptionErrorLabel.setText("Veuillez remettre le même mot de passe");
        			inscriptionErrorLabel.setVisible(true);
        			return;
        		} else if(!String.valueOf(passwordTextField.getPassword()).equals(String.valueOf(passwordRetryTextField.getPassword()))) {
        			inscriptionErrorLabel.setText("Les mots de passe ne correspondent pas");
        			inscriptionErrorLabel.setVisible(true);
        			return;
        		}
        		
				TCPacket myPacket = new TCPacket(EnumPacket.REGISTRATION);
				Main.getInstance().sendPacket(myPacket.write(emailTextField.getText(), usernameTextField.getText(), String.valueOf(passwordTextField.getPassword()), " ", " "));
				System.out.println(myPacket.write(emailTextField.getText(), usernameTextField.getText(), String.valueOf(passwordTextField.getPassword()), " ", " "));
        	}
        	
        	@Override
        	public void mouseEntered(MouseEvent arg0) {
        		inscriptionButtonPanel.setBackground(Color.decode("#222831"));
        	}
        	
        	@Override
        	public void mouseExited(MouseEvent arg0) {
        		inscriptionButtonPanel.setBackground(Color.decode("#393e46"));
        	}
        });
		
		this.inscriptionButtonTitleLabel.setText("INSCRIPTION");
		this.inscriptionButtonTitleLabel.setBounds(0, 0, 180, 54);
		this.inscriptionButtonTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.inscriptionButtonTitleLabel.setVerticalAlignment(SwingConstants.CENTER);
		this.inscriptionButtonTitleLabel.setFont(new Font("Calibri", Font.BOLD, 24));
		this.inscriptionButtonTitleLabel.setForeground(Color.decode("#ffd369"));
		
		this.connexionLabel.setText("Déjà un compte ?");
		this.connexionLabel.setBounds(10, 508, 381, 30);
		this.connexionLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		this.connexionLabel.setForeground(Color.decode("#393e46"));

		this.connexionLabel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		Main.getInstance().getIHM().show(Main.getInstance().getIHM().frameConnection);
        	}
        });
		
		this.inscriptionButtonPanel.add(this.inscriptionButtonTitleLabel);
		
		this.inscriptionTitlePanel.add(this.inscriptionTitleLabel);
		
		this.inscriptionPanel.add(this.inscriptionTitlePanel);
		this.inscriptionPanel.add(this.usernameLabel);
		this.inscriptionPanel.add(this.usernameTextField);
		this.inscriptionPanel.add(this.emailLabel);
		this.inscriptionPanel.add(this.emailTextField);
		this.inscriptionPanel.add(this.passwordLabel);
		this.inscriptionPanel.add(this.passwordTextField);
		this.inscriptionPanel.add(this.passwordRetryLabel);
		this.inscriptionPanel.add(this.passwordRetryTextField);
		this.inscriptionPanel.add(this.inscriptionErrorLabel);
		this.inscriptionPanel.add(this.inscriptionButtonPanel);
		this.inscriptionPanel.add(this.connexionLabel);
		
		this.frame.add(this.inscriptionPanel);
		
	}

	@Override
	public void displayError(EnumPacket type, String message) {
		this.inscriptionErrorLabel.setText("Une erreur s'est produite");
		this.inscriptionErrorLabel.setVisible(true);
	}

	@Override
	public void valid(EnumPacket type) {
		Main.getInstance().getIHM().show(Main.getInstance().getIHM().frameHome);
	}

	@Override
	public Window getFrame() {
		return this.frame;
	}

	@Override
	public void valid(EnumPacket type, String data) {
		// TODO Auto-generated method stub
		
	}

}
