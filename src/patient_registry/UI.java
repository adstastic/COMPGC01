package patient_registry;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class UI extends JFrame {
	private final String LOGIN = "Login";
	private final String REGISTER = "Register";
	private final static Logger L = Logger.getLogger(UI.class.getName());
	private JTextField fldUsername; 
	private JPasswordField fldPassword;
	private JTextField fldNewUsername; 
	private JPasswordField fldNewPassword;
	private boolean login;
	private JButton btnLogin;
	private JButton btnRegister;
	private JButton btnExit;
	private JButton btnSubmit;
	private JButton btnCancel;

	public static void main(String[] args) {
		UI ui = new UI();
		
	}
	
	private UI() {
		setSize(500,500);
		setTitle("Patient Registry System");
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		setLayout(new BorderLayout());
		
		JPanel cards = new JPanel(new CardLayout());
		CardLayout c = (CardLayout) cards.getLayout();
		
		JPanel loginPanel = loginPanel();
		JPanel regPanel = regPanel();
        
//		JPanel container = loginPanel(lblUsername, lblPassword, fldUsername, fldPassword, btnLogin, btnRegister, btnExit);        
        cards.add(loginPanel, LOGIN);
        cards.add(regPanel, REGISTER);
        
        
        add(cards, BorderLayout.CENTER);
        c.show(cards, LOGIN);
		setVisible(true);
		
		btnLogin.addActionListener(new ActionListener() {
			@Override 
            public void actionPerformed(ActionEvent e) {
                if (Utilities.authenticate(getUsername(), getPassword())) {
                    JOptionPane.showMessageDialog(loginPanel,
                            "Welcome " + getUsername() + "",
                            "Login",
                            JOptionPane.INFORMATION_MESSAGE);
                    login = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(loginPanel,
                            "Invalid username or password",
                            "Login",
                            JOptionPane.ERROR_MESSAGE);
                    fldUsername.setText("");
                    fldPassword.setText("");
                    login = false;
                }
            }
        });
		
		btnExit.addActionListener(new ActionListener() {
			@Override 
            public void actionPerformed(ActionEvent e) {
				int exitConfirm = JOptionPane.showConfirmDialog(loginPanel, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
				if (exitConfirm == JOptionPane.YES_OPTION) {
					System.exit(0);
				} else {
					// Remove Message Dialog
					Window w = SwingUtilities.getWindowAncestor(btnCancel);
					if (w != null) {
						w.setVisible(false);
					}
				}
			}
		});
		
		btnRegister.addActionListener(new ActionListener() {
			@Override 
            public void actionPerformed(ActionEvent e) {
				c.show(cards, REGISTER);				
			}
		});
		
		btnSubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = fldUsername.getText();
				String password = Arrays.toString(fldPassword.getPassword());
				if (Utilities.addUser(username, password)) {
					JOptionPane.showMessageDialog(loginPanel,
                            "User added!",
                            "User Registration",
                            JOptionPane.PLAIN_MESSAGE);
					c.show(cards, LOGIN);
                }
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.show(cards, LOGIN);
			}
		});
	}
	
	
	private JPanel loginPanel() {
		JLabel lblUsername = new JLabel("Username ");
		JLabel lblPassword = new JLabel("Password ");
        fldUsername = new JTextField(20);
        fldPassword = new JPasswordField(20);
        btnLogin = new JButton("Login");
		btnExit = new JButton("Exit");
		btnRegister = new JButton("Register");
		
		JPanel container = new JPanel(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;
		container.add(lblUsername, gbc);
		gbc.gridx++;
		gbc.gridwidth = 3;
		container.add(fldUsername, gbc);
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 1;
		container.add(lblPassword, gbc);
		gbc.gridx++;
		gbc.gridwidth = 3;
		container.add(fldPassword, gbc);

	  	gbc.gridx = 1;
      	gbc.gridy++;
      	gbc.gridwidth = 1;
      	container.add(btnExit, gbc);
      	gbc.gridx++;
        container.add(btnLogin, gbc);
        gbc.gridx++;
        container.add(btnRegister, gbc);
        
        return container;
	}
	
	private JPanel regPanel() {
		JLabel lblUsername = new JLabel("Username");
		JLabel lblPassword = new JLabel("Password");
		fldNewUsername = new JTextField(20);
        fldNewPassword = new JPasswordField(20);
		btnSubmit = new JButton("Submit");
		btnCancel = new JButton("Cancel");
		
		JPanel regPanel = new JPanel(new GridBagLayout());
        
		GridBagConstraints gbc1 = new GridBagConstraints();
		gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.anchor = GridBagConstraints.WEST;
        gbc1.gridwidth = 1;
		regPanel.add(lblUsername, gbc1);
		gbc1.gridx++;
		gbc1.gridwidth = 2;
		regPanel.add(fldNewUsername, gbc1);
		gbc1.gridx = 0;
		gbc1.gridy++;
		gbc1.gridwidth = 1;
		regPanel.add(lblPassword, gbc1);
		gbc1.gridx++;
		gbc1.gridwidth = 2;
		regPanel.add(fldNewPassword, gbc1);

	  	gbc1.gridx = 1;
      	gbc1.gridy++;
      	gbc1.gridwidth = 1;
      	regPanel.add(btnCancel, gbc1);
      	gbc1.gridx++;
        regPanel.add(btnSubmit, gbc1);
     
        
        return regPanel;
	}
	
	public String getUsername() {
		return fldUsername.getText();
	}
	
	public String getPassword() {
		return Arrays.toString(fldPassword.getPassword());
	}
	
}
