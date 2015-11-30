package patient_registry;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.border.EmptyBorder;

public class UI extends JFrame {
	private final static Logger L = Logger.getLogger(UI.class.getName());

	public static void main(String[] args) {
		UI ui = new UI();
		
	}
	
	private UI() {
		setSize(500,500);
		setTitle("Patient Registry System");
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		setLayout(new GridBagLayout());
		
		JPanel container = new JPanel(new GridBagLayout());
		
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        container.add(new JLabel("Username:"), gbc);
        gbc.gridy++;
        container.add(new JLabel("Password:"), gbc);

        gbc.gridx++;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        JTextField fld_user = new JTextField(20);
        JPasswordField fld_pass = new JPasswordField();
        container.add(fld_user, gbc);
        gbc.gridy++;
        container.add(fld_pass, gbc);
        
        gbc.gridx = 1;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        JButton btn_login = new JButton("Login");
        JButton btn_register = new JButton("Register");
        JButton btn_exit = new JButton("Exit");
        container.add(btn_login, gbc);
        gbc.gridx++;
        container.add(btn_register, gbc);
        gbc.gridx++;
        container.add(btn_exit, gbc);
        
        add(container);
		setVisible(true);
		
		btn_login.addActionListener(new ActionListener() {
			@Override 
            public void actionPerformed(ActionEvent e) {
                if (Utilities.authenticate(fld_user.getText(), fld_pass.getPassword())) {
                    JOptionPane.showMessageDialog(LoginDialog.this,
                            "Hi " + getUsername() + "! You have successfully logged in.",
                            "Login",
                            JOptionPane.INFORMATION_MESSAGE);
                    succeeded = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginDialog.this,
                            "Invalid username or password",
                            "Login",
                            JOptionPane.ERROR_MESSAGE);
                    // reset username and password
                    tfUsername.setText("");
                    pfPassword.setText("");
                    succeeded = false;
 
                }
            }

			
        });
		
			
		}
		
	}
}
