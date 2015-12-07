package v2;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class UI extends JFrame {
	private final static Logger L = Logger.getLogger("L");
	private static JPanel cards;
	private static CardLayout c;
	private static JPanel currentPatient = new JPanel();
	private UserDataHandler UDH = new UserDataHandler("./data/user_data.json");
	private PatientDataHandler PDH = new PatientDataHandler("./data/patient_test.json");

	protected UI() {}
	
	protected JPanel getCards() {
		return this.cards;
	}
	
	protected CardLayout getCardLayout() {
		return this.c;
	}
	
	protected PatientDataHandler getPatientDataHandler() {
		return this.PDH;
	}
	
	protected void start() {
		setSize(570,770);
		setTitle("Patient Registry System");
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		setLayout(new BorderLayout());
		
		cards = new JPanel(new CardLayout());
		c = (CardLayout) cards.getLayout();
		
		JPanel loginPanel = loginPanel();
		JPanel regPanel = regPanel();
		JPanel listPanel = new PatientTablePanel(UI.this, PDH);
        
        cards.add(loginPanel, "LOGIN");
        cards.add(regPanel, "REGISTER");
        cards.add(listPanel, "PATIENTLIST");
        cards.add(currentPatient, "CURRENTPATIENT");
        
        add(cards, BorderLayout.CENTER);
        c.show(cards, "LOGIN");
		setVisible(true);	
	}
	
	
	private JPanel loginPanel() {
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
		container.add(new JLabel("Username "), gbc);
		gbc.gridx++;
		gbc.gridwidth = 3;
		container.add(fldUsername, gbc);
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 1;
		container.add(new JLabel("Password "), gbc);
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
                if (UDH.authenticate(username, password)) {
                    JOptionPane.showMessageDialog(container,
                            "Welcome " + username + "",
                            "Login",
                            JOptionPane.INFORMATION_MESSAGE);
                    c.show(cards, "PATIENTLIST");
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
					dispose();
				}
			}
		});
		
		btnRegister.addActionListener(new ActionListener() {
			@Override 
            public void actionPerformed(ActionEvent e) {
				c.show(cards, "REGISTER");				
			}
		});
		
		container.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				fldUsername.setText("");
				fldPassword.setText("");
			}
			
			@Override
			public void componentResized(ComponentEvent e) {}
			
			@Override
			public void componentMoved(ComponentEvent e) {}
			
			@Override
			public void componentHidden(ComponentEvent e) {}
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
				if (UDH.addUser(username, password, authCode)) {
					JOptionPane.showMessageDialog(regPanel,
                            "User added!",
                            "User Registration",
                            JOptionPane.PLAIN_MESSAGE);
					fldNewUsername.setText("");
                    fldNewPassword.setText("");
					c.show(cards, "LOGIN");
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
				c.show(cards, "LOGIN");
			}
		});
		
        return regPanel;
	}
	
	protected static void refreshPatientPanel(PatientPanel pnlPatient) {
		cards.remove(currentPatient);
        currentPatient = pnlPatient;
        cards.add(currentPatient, "CURRENTPATIENT");
        c.show(cards, "CURRENTPATIENT");
	}

}
