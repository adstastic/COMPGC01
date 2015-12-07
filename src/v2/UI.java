package v2;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.FileFilter;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.ListModel;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.omg.CORBA.IMP_LIMIT;

import com.toedter.calendar.JCalendar;

public class UI extends JFrame {
	private final String LOGIN = "Login";
	private final String REGISTER = "Register";
	private final String PATIENT_LIST = "Patient_List";
	private final String CURRENT_PATIENT = "Current_Patient";
	private final static Logger L = Logger.getLogger("L");
	private JPanel cards;
	private CardLayout c;
	private JPanel currentPatient = new JPanel();
	private UserDataHandler UDH = new UserDataHandler("./data/user_data.json");
	private PatientDataHandler PDH = new PatientDataHandler("./data/patient_test.json");
	
	public static void main(String[] args) {
		UI ui = new UI();
		ui.start();
	}
	
	
	protected UI() {}
	
	protected void start() {
		setSize(570,770);
		setTitle("Patient Registry System");
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		setLayout(new BorderLayout());
		
		cards = new JPanel(new CardLayout());
		c = (CardLayout) cards.getLayout();
		
		JPanel loginPanel = loginPanel();
		JPanel regPanel = regPanel();
		JPanel listPanel = listPanel();
        
        cards.add(loginPanel, LOGIN);
        cards.add(regPanel, REGISTER);
        cards.add(listPanel, PATIENT_LIST);
        cards.add(currentPatient, CURRENT_PATIENT);
        
        add(cards, BorderLayout.CENTER);
        c.show(cards, LOGIN);
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
                    c.show(cards, PATIENT_LIST);
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
				if (UDH.addUser(username, password, authCode)) {
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
		JLabel lblPatients = new JLabel("Patients (double click to open)");
		
		JPanel listPanel = new JPanel(new BorderLayout());
        
		JTable patientTable = new JTable(new PatientTableModel(PDH.getPatientList()));
		
		patientTable.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent me) {
		        JTable table =(JTable) me.getSource();
		        Point p = me.getPoint();
		        int row = table.rowAtPoint(p);
		        PatientTableModel ptm = (PatientTableModel) table.getModel();
		        Patient selectedPatient = ptm.getRow(row);
		        L.info("Patient "+selectedPatient.FIRST_NAME+" "+selectedPatient.LAST_NAME+" selected.");
		        if (me.getClickCount() == 2) {
		        	JPanel patientPanel = patientPanel(selectedPatient);
		        	cards.remove(currentPatient);
		            currentPatient = patientPanel;
		            cards.add(currentPatient, CURRENT_PATIENT);
		            c.show(cards, CURRENT_PATIENT);
		        }
		    }
		});
		JScrollPane tablePane = new JScrollPane(patientTable);
		
		JPanel pnlButtons = new JPanel();
		
		JButton btnAdd = new JButton("Add Patient");
		JButton btnDelete = new JButton("Delete Patient");
		JButton btnLogout = new JButton("Log out");
		
		listPanel.add(lblPatients, BorderLayout.NORTH);
		listPanel.add(tablePane, BorderLayout.CENTER);
		
        
        return listPanel;
	}
	
	protected JPanel patientPanel(Patient p) {
		int txtWidth = 20;
		JTextField fldFirstName = new JTextField(txtWidth);
		fldFirstName.setText(p.FIRST_NAME);
		JTextField fldLastName = new JTextField(txtWidth);
		fldLastName.setText(p.LAST_NAME);
		JTextField fldDob = new JTextField(txtWidth);
		fldDob.setText(p.DOB);
		JTextField fldAddress = new JTextField(txtWidth);
		fldAddress.setText(p.ADDRESS);
		JTextField fldEmergencyPhone = new JTextField(txtWidth);
		fldEmergencyPhone.setText(p.EMERGENCY_NUMBER);
		JTextField fldCondition = new JTextField(txtWidth);
		fldCondition.setText(p.CONDITION);
		JTextArea txtComments = new JTextArea(5, txtWidth);
		txtComments.setText(p.COMMENTS);
		JScrollPane spnComments = new JScrollPane(txtComments);
		
		String[] appointments = p.getAppointments().toArray(new String[p.getAppointments().size()]);
		JList<String> lstAppointments = new JList<String>(appointments);
		JScrollPane spnAppointments = new JScrollPane(lstAppointments);
		
		ImagePanel pnlProfilePicture = new ImagePanel(p.getProfilePicturePath());
		
		JScrollPane spnMedicalImages = new JScrollPane(imageList(p));
		
		JPanel txtPanel = new JPanel();
		txtPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
		txtPanel.add(new JLabel("First Name"), gbc);
		
		gbc.gridx = 0;
        gbc.gridy = 1;
		txtPanel.add(fldFirstName, gbc);
		
		gbc.gridx = 0;
        gbc.gridy = 2;
		txtPanel.add(new JLabel("Last Name"), gbc);
		
		gbc.gridx = 0;
        gbc.gridy = 3;
		txtPanel.add(fldLastName, gbc);
		
		gbc.gridx = 0;
        gbc.gridy = 4;
		txtPanel.add(new JLabel("Date of Birth"), gbc);
		
		gbc.gridx = 0;
        gbc.gridy = 5;
		txtPanel.add(fldDob, gbc);
		
		gbc.gridx = 0;
        gbc.gridy = 6;
		txtPanel.add(new JLabel("Address"), gbc);
		
		gbc.gridx = 0;
        gbc.gridy = 7;
		txtPanel.add(fldAddress, gbc);
		
		gbc.gridx = 0;
        gbc.gridy = 8;
		txtPanel.add(new JLabel("Emergency Phone"), gbc);
		
		gbc.gridx = 0;
        gbc.gridy = 9;
		txtPanel.add(fldEmergencyPhone, gbc);
		
		gbc.gridx = 0;
        gbc.gridy = 10;
		txtPanel.add(new JLabel("Condition"), gbc);
		
		gbc.gridx = 0;
        gbc.gridy = 11;
		txtPanel.add(fldCondition, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 12;
		txtPanel.add(new JLabel("Comments"), gbc);
		
		gbc.gridx = 0;
		gbc.weighty = 1;
		gbc.gridy = 13;
		gbc.fill = GridBagConstraints.BOTH;
		txtPanel.add(spnComments, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 14;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 0;
		txtPanel.add(new JLabel("Appointments"), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 15;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		txtPanel.add(spnAppointments, gbc);
		
		JPanel fldPanel = new JPanel(new GridBagLayout());
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
		fldPanel.add(new JLabel("Profile Picture"), gbc);
		
		gbc.gridy = 1;
		fldPanel.add(new JLabel("Double-click to edit"), gbc);
		
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 1;
		fldPanel.add(pnlProfilePicture, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 3;
		gbc.weighty = 0;
		gbc.insets = new Insets(10, 0, 0, 0);
		fldPanel.add(new JLabel("Medical Images"), gbc);
		
		JButton btnAdd = new JButton("Add Image");
		JPanel pnlControl = new JPanel (new GridLayout(1, 2));
		pnlControl.add(new JLabel("Double-click to view/edit"));
		pnlControl.add(btnAdd);
		
		gbc.gridy = 4;
		fldPanel.add(pnlControl, gbc);
		
		gbc.gridy = 5;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 0.5;
		fldPanel.add(spnMedicalImages, gbc);
		
		
		JPanel patientPanel = new JPanel(new GridBagLayout());
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridy = 0;
		gbc.gridx = 0;
        patientPanel.add(txtPanel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        patientPanel.add(fldPanel, gbc);
        
        gbc.gridwidth = 2;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 1;
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnBack = new JButton("Back");
        JPanel pnlButton = new JPanel(new GridLayout(1, 3));
        pnlButton.add(btnBack);
        pnlButton.add(btnUpdate);
        pnlButton.add(btnDelete);
        patientPanel.add(pnlButton, gbc);
        
        btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				c.show(cards, PATIENT_LIST);
			}
		});
        
        btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int answer = JOptionPane.showConfirmDialog(patientPanel, 
											"Are you sure you want to delete "+p.FIRST_NAME+" "+p.LAST_NAME+"?",
											"Confirm Delete",
											JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(patientPanel, "Patient deleted.");
					PDH.remove(p);
					c.show(cards, PATIENT_LIST);
					cards.remove(currentPatient);
				} else if (answer == JOptionPane.NO_OPTION) {
					dispose();
				}
			}
		});
        
		return patientPanel;
	}
	
	protected JPanel patientPanel() {
		int txtWidth = 20;
		JTextField fldFirstName = new JTextField(txtWidth);
		JTextField fldLastName = new JTextField(txtWidth);
		JTextField fldDob = new JTextField(txtWidth);
		JTextField fldAddress = new JTextField(txtWidth);
		JTextField fldEmergencyPhone = new JTextField(txtWidth);
		JTextField fldCondition = new JTextField(txtWidth);
		JTextArea txtComments = new JTextArea(5, txtWidth);
		JScrollPane spnComments = new JScrollPane(txtComments);
		JList<String> lstAppointments = new JList<String>();
		JScrollPane spnAppointments = new JScrollPane(lstAppointments);
		
		JScrollPane spnMedicalImages = new JScrollPane();
		
		JPanel txtPanel = new JPanel();
		txtPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
		txtPanel.add(new JLabel("First Name"), gbc);
		
		gbc.gridx = 0;
        gbc.gridy = 1;
		txtPanel.add(fldFirstName, gbc);
		
		gbc.gridx = 0;
        gbc.gridy = 2;
		txtPanel.add(new JLabel("Last Name"), gbc);
		
		gbc.gridx = 0;
        gbc.gridy = 3;
		txtPanel.add(fldLastName, gbc);
		
		gbc.gridx = 0;
        gbc.gridy = 4;
		txtPanel.add(new JLabel("Date of Birth"), gbc);
		
		gbc.gridx = 0;
        gbc.gridy = 5;
		txtPanel.add(fldDob, gbc);
		
		gbc.gridx = 0;
        gbc.gridy = 6;
		txtPanel.add(new JLabel("Address"), gbc);
		
		gbc.gridx = 0;
        gbc.gridy = 7;
		txtPanel.add(fldAddress, gbc);
		
		gbc.gridx = 0;
        gbc.gridy = 8;
		txtPanel.add(new JLabel("Emergency Phone"), gbc);
		
		gbc.gridx = 0;
        gbc.gridy = 9;
		txtPanel.add(fldEmergencyPhone, gbc);
		
		gbc.gridx = 0;
        gbc.gridy = 10;
		txtPanel.add(new JLabel("Condition"), gbc);
		
		gbc.gridx = 0;
        gbc.gridy = 11;
		txtPanel.add(fldCondition, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 12;
		txtPanel.add(new JLabel("Comments"), gbc);
		
		gbc.gridx = 0;
		gbc.weighty = 1;
		gbc.gridy = 13;
		gbc.fill = GridBagConstraints.BOTH;
		txtPanel.add(spnComments, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 14;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 0;
		txtPanel.add(new JLabel("Appointments"), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 15;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		txtPanel.add(spnAppointments, gbc);
		
		JPanel fldPanel = new JPanel(new GridBagLayout());
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
		fldPanel.add(new JLabel("Profile Picture"), gbc);
		
		gbc.gridy = 1;
		fldPanel.add(new JLabel("Double-click to edit"), gbc);
		
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 1;
		fldPanel.add(pnlProfilePicture, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy = 3;
		gbc.weighty = 0;
		gbc.insets = new Insets(10, 0, 0, 0);
		fldPanel.add(new JLabel("Medical Images"), gbc);
		
		JButton btnAdd = new JButton("Add Image");
		JPanel pnlControl = new JPanel (new GridLayout(1, 2));
		pnlControl.add(new JLabel("Double-click to view/edit"));
		pnlControl.add(btnAdd);
		
		gbc.gridy = 4;
		fldPanel.add(pnlControl, gbc);
		
		gbc.gridy = 5;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = 0.5;
		fldPanel.add(spnMedicalImages, gbc);
		
		
		JPanel patientPanel = new JPanel(new GridBagLayout());
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridy = 0;
		gbc.gridx = 0;
        patientPanel.add(txtPanel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        patientPanel.add(fldPanel, gbc);
        
        gbc.gridwidth = 2;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 1;
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnBack = new JButton("Back");
        JPanel pnlButton = new JPanel(new GridLayout(1, 3));
        pnlButton.add(btnBack);
        pnlButton.add(btnUpdate);
        pnlButton.add(btnDelete);
        patientPanel.add(pnlButton, gbc);
        
        btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				c.show(cards, PATIENT_LIST);
			}
		});
        
        btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int answer = JOptionPane.showConfirmDialog(patientPanel, 
											"Are you sure you want to delete "+p.FIRST_NAME+" "+p.LAST_NAME+"?",
											"Confirm Delete",
											JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(patientPanel, "Patient deleted.");
					PDH.remove(p);
					c.show(cards, PATIENT_LIST);
					cards.remove(currentPatient);
				} else if (answer == JOptionPane.NO_OPTION) {
					dispose();
				}
			}
		});
        
		return patientPanel;
	}
	
	protected void paintComponent(Graphics g, BufferedImage img) {
		super.paintComponents(g);
		g.drawImage(img, 0,0, null);
	}
	
	protected JList imageList(Patient p) {
		List<String> medicalImagePaths = p.getMedicalImagesPath();
		
		DefaultListModel<JLabel> lm = new DefaultListModel<JLabel>();
		for (String path : medicalImagePaths) {
			JLabel imgLabel = iconLabel(path);
			lm.addElement(imgLabel);
		}
		JList<JLabel> lstMedicalImages = new JList<JLabel>(lm);
		lstMedicalImages.setCellRenderer(new ImageListCellRenderer());
				
		lstMedicalImages.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JList<JLabel> list = (JList<JLabel>) e.getSource();
				int clicks = e.getClickCount();
				if (clicks == 2) {
					int index = list.locationToIndex(e.getPoint());
					String imagePath = medicalImagePaths.get(index);
					ImagePanel pnlImg = new ImagePanel(imagePath);
					JPanel pnlImgView = imageView(pnlImg, p, list, index);
					JFrame imgWindow = new JFrame();
					imgWindow.add(pnlImgView);
					imgWindow.setSize(400, 500);
					imgWindow.setVisible(true);
				}
			}
		});
		return lstMedicalImages;
	}
	
	protected JPanel imageView(ImagePanel pnlImg, Patient p, JList<JLabel> list, int index) {
		
		JButton btnEdit = new JButton("Edit");
		JButton btnRemove = new JButton("Remove");
		JButton btnBack = new JButton("Back");
		JPanel pnlButton = new JPanel(new GridLayout());
		pnlButton.add(btnBack);
		pnlButton.add(btnEdit);
		pnlButton.add(btnRemove);
		
		btnRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
				
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser("./");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Images: .jpg, .jpeg, .png, .gif", "jpg", "jpeg", "png", "gif");
				fc.setFileFilter(filter);
				int returnVal = fc.showOpenDialog(pnlImg);
				
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					Path relPath = absoluteWorkingPath().relativize(Paths.get(file.getPath()));
					String path = relPath.toString();
					pnlImg.setImage(path);
					L.info("Set Medical ImagePanel to file "+path);
					DefaultListModel<JLabel> dlm = (DefaultListModel<JLabel>) list.getModel();
					JLabel newLabel = iconLabel(path);
					dlm.setElementAt(newLabel, index);
					L.info("Set Medical Image JList item "+index+" to image at "+path);
					list.repaint();
					System.out.println(p.MEDICAL_IMAGES);
					p.MEDICAL_IMAGES.set(index, path);
					L.info("Patient "+p.FIRST_NAME+" "+p.LAST_NAME+" medical image "+index+" changed to "+path);
					System.out.println(p.MEDICAL_IMAGES);
					fc.setVisible(false);
				} else if (returnVal == JFileChooser.CANCEL_OPTION) {
					fc.setVisible(false);
				}				
			
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Close JOptionPane that this is button is displayed in
				Window w = SwingUtilities.getWindowAncestor(btnBack);

			    if (w != null) {
			      w.setVisible(false);
			    }
			}
		});
		
		
		JPanel container = new JPanel(new GridBagLayout());
		container.setMinimumSize(new Dimension(100, 150));
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(5,5,5,5);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		container.add(pnlImg, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridx = 0;
		gbc.gridy = 1;
		container.add(pnlButton, gbc);
		
		return container;
	}
	
	protected JPanel imageView(ImagePanel pnlImg, Patient p) {
		
		JButton btnEdit = new JButton("Edit");
		JButton btnRemove = new JButton("Remove");
		JButton btnBack = new JButton("Back");
		JPanel pnlButton = new JPanel(new GridLayout());
		pnlButton.add(btnBack);
		pnlButton.add(btnEdit);
		pnlButton.add(btnRemove);
		
		btnRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
				
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser("./");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Images: .jpg, .jpeg, .png, .gif", "jpg", "jpeg", "png", "gif");
				fc.setFileFilter(filter);
				int returnVal = fc.showOpenDialog(pnlImg);
				
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					Path relPath = absoluteWorkingPath().relativize(Paths.get(file.getPath()));
					String path = relPath.toString();
					pnlImg.setImage(path);
					L.info("Set Medical ImagePanel to file "+path);
					System.out.println(p.MEDICAL_IMAGES);
					p.PROFILE_PICTURE = path;
					L.info("Patient "+p.FIRST_NAME+" "+p.LAST_NAME+" profile picture changed to "+path);
					System.out.println(p.MEDICAL_IMAGES);
					fc.setVisible(false);
				} else if (returnVal == JFileChooser.CANCEL_OPTION) {
					fc.setVisible(false);
				}				
			
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Close JOptionPane that this is button is displayed in
				Window w = SwingUtilities.getWindowAncestor(btnBack);

			    if (w != null) {
			      w.setVisible(false);
			    }
			}
		});
		
		
		JPanel container = new JPanel(new GridBagLayout());
		container.setMinimumSize(new Dimension(100, 150));
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(5,5,5,5);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		container.add(pnlImg, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridx = 0;
		gbc.gridy = 1;
		container.add(pnlButton, gbc);
		
		return container;
	}
	
	protected JLabel iconLabel(String path) {
		Path filepath = Paths.get(path);
		String filename = filepath.getFileName().toString();
		
		BufferedImage bi = PatientDataHandler.getImage(path);
		Image resizedImg = bi.getScaledInstance(100, 100, 0);
		ImageIcon icon = new ImageIcon(resizedImg);
		
		JLabel imgLabel = new JLabel(filename, icon, JLabel.LEFT);
		
		return imgLabel;
	}
	
	protected Path absoluteWorkingPath() {
		Path currentRelativePath = Paths.get("");
		return currentRelativePath.toAbsolutePath();
	}
}
