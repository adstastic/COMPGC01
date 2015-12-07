package v1;

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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class UI extends JFrame {
	private final String LOGIN = "Login";
	private final String REGISTER = "Register";
	private final static Logger L = Logger.getLogger("L");
	private JPanel cards;
	private CardLayout c;
	private UserDataHandler userDataHandler = new UserDataHandler("./userData.json");
	
	public static void main(String[] args) {
		UI ui = new UI();
	}
	
	
	private UI() {
		setSize(500,500);
		setTitle("Patient Registry System");
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		setLayout(new BorderLayout());
		
		cards = new JPanel(new CardLayout());
		c = (CardLayout) cards.getLayout();
		
		JPanel loginPanel = loginPanel();
		JPanel regPanel = regPanel();
//		JPanel listPanel = listPanel();
        
//		JPanel container = loginPanel(lblUsername, lblPassword, fldUsername, fldPassword, btnLogin, btnRegister, btnExit);        
        cards.add(loginPanel, LOGIN);
        cards.add(regPanel, REGISTER);
        
        
        add(cards, BorderLayout.CENTER);
        c.show(cards, LOGIN);
		setVisible(true);		
		
	}
	
	
	private JPanel loginPanel() {
		JLabel lblUsername = new JLabel("Username ");
		JLabel lblPassword = new JLabel("Password ");
        JTextField fldUsername = new JTextField(20);
        JPasswordField fldPassword = new JPasswordField(20);
        JButton btnLogin = new JButton("Login");
        JButton btnExit = new JButton("Exit");
        JButton btnRegister = new JButton("Register");
		
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
        
        btnLogin.addActionListener(new ActionListener() {
			@Override 
            public void actionPerformed(ActionEvent e) {
				String username = fldUsername.getText();
				String password = new String(fldPassword.getPassword());
                if (userDataHandler.authenticate(username, password)) {
                    JOptionPane.showMessageDialog(container,
                            "Welcome " + username + "",
                            "Login",
                            JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(container,
                            "Invalid username or password",
                            "Login",
                            JOptionPane.ERROR_MESSAGE);
                    fldUsername.setText("");
                    fldPassword.setText("");
                }
            }
        });
		
		btnExit.addActionListener(new ActionListener() {
			@Override 
            public void actionPerformed(ActionEvent e) {
				int exitConfirm = JOptionPane.showConfirmDialog(container, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
				if (exitConfirm == JOptionPane.YES_OPTION) {
					System.exit(0);
				} else {
					// Remove Message Dialog
				}
			}
		});
		
		btnRegister.addActionListener(new ActionListener() {
			@Override 
            public void actionPerformed(ActionEvent e) {
				c.show(cards, REGISTER);				
			}
		});
		
        return container;
	}
	
	private JPanel regPanel() {
		JLabel lblUsername = new JLabel("Username");
		JLabel lblPassword = new JLabel("Password");
		JLabel lblAuthCode = new JLabel("Authentication Code");
		JTextField fldNewUsername = new JTextField(20);
        JPasswordField fldNewPassword = new JPasswordField(20);
        JPasswordField fldAuthCode = new JPasswordField(20);
		JButton btnSubmit = new JButton("Submit");
		JButton btnCancel = new JButton("Cancel");
		
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
		gbc1.gridx = 0;
		gbc1.gridy++;
		gbc1.gridwidth = 1;
		regPanel.add(lblAuthCode, gbc1);
		gbc1.gridx++;
		gbc1.gridwidth = 2;
		regPanel.add(fldAuthCode, gbc1);

	  	gbc1.gridx = 1;
      	gbc1.gridy++;
      	gbc1.gridwidth = 1;
      	regPanel.add(btnCancel, gbc1);
      	gbc1.gridx++;
        regPanel.add(btnSubmit, gbc1);
     
        btnSubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = fldNewUsername.getText();
				String password = new String(fldNewPassword.getPassword());
				String authCode = new String(fldAuthCode.getPassword());
				if (userDataHandler.addUser(username, password, authCode)) {
					JOptionPane.showMessageDialog(regPanel,
                            "User added!",
                            "User Registration",
                            JOptionPane.PLAIN_MESSAGE);
					fldNewUsername.setText("");
                    fldNewPassword.setText("");
					c.show(cards, LOGIN);
                } else {
                	JOptionPane.showMessageDialog(regPanel,
                            "Invalid Authentication Code!",
                            "User Registration",
                            JOptionPane.ERROR_MESSAGE);
                }
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.show(cards, LOGIN);
			}
		});
		
        return regPanel;
	}
	
	private JPanel listPanel() {
		JLabel lblPatients = new JLabel("Patients");
		
		JPanel listPanel = new JPanel(new BorderLayout());
        
		JTable patientTable = new JTable(new PatientTableModel(pdh.getPatientList()));
		JScrollPane tablePane = new JScrollPane(patientTable);
     
        
		
        return listPanel;
	}
}
