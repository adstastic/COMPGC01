package v2;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PatientPanel extends JPanel {
	private final static Logger L = Logger.getLogger("L");
	private static JList<JLabel> imageList;
	private static ImagePanel pnlProfilePicture;
	private static final String NO_IMAGE_PATH = "./src/v2/lib/no_image.png";
	private final static int PROFILE = -1;
	private static boolean isNewPatient = false;
	
	public PatientPanel(UI ui, Patient p) {
		JPanel cards = ui.getCards();
		CardLayout c = ui.getCardLayout();
		PatientDataHandler PDH = ui.getPatientDataHandler();
		
		int txtWidth = 20;
		
		JTextField fldFirstName = new JTextField(txtWidth);
		fldFirstName.setText(p.getFirstName());
		JTextField fldLastName = new JTextField(txtWidth);
		fldLastName.setText(p.getLastName());
		JTextField fldDob = new JTextField(txtWidth);
		fldDob.setText(p.getDOB());
		JTextField fldAddress = new JTextField(txtWidth);
		fldAddress.setText(p.getAddress());
		JTextField fldEmergencyPhone = new JTextField(txtWidth);
		fldEmergencyPhone.setText(p.getEmergencyNumber());
		JTextField fldCondition = new JTextField(txtWidth);
		fldCondition.setText(p.getCondition());
		JTextArea txtComments = new JTextArea(5, txtWidth);
		txtComments.setText(p.getComments());
		JScrollPane spnComments = new JScrollPane(txtComments);
		
		String[] appointments = p.getAppointments().toArray(new String[p.getAppointments().size()]);
		JList<String> lstAppointments = new JList<String>(appointments);
		JScrollPane spnAppointments = new JScrollPane(lstAppointments);

		if (p.getProfilePicturePath() != null) {
			System.out.println(p.getProfilePicturePath());
			pnlProfilePicture = new ImagePanel(p.getProfilePicturePath());
		} else {
			pnlProfilePicture = new ImagePanel(NO_IMAGE_PATH);
		}
		
		pnlProfilePicture.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int clicks = e.getClickCount();
				if (clicks == 2) {
					ImagePanel profileView = new ImagePanel(pnlProfilePicture.getPath());
					JPanel pnlImgView = imageView(profileView, p, PROFILE);
					JFrame imgWindow = new JFrame();
					imgWindow.add(pnlImgView);
					imgWindow.setSize(400, 500);
					imgWindow.setVisible(true);
				}
			}
		});
		
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
		
		
		setLayout(new GridBagLayout());
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridy = 0;
		gbc.gridx = 0;
        add(txtPanel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        add(fldPanel, gbc);
        
        gbc.gridwidth = 2;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 2;
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnBack = new JButton("Back");
        JPanel pnlButton = new JPanel(new GridLayout(1, 3));
        
        pnlButton.add(btnBack);
        pnlButton.add(btnUpdate);
        pnlButton.add(btnDelete);
        add(pnlButton, gbc);
        
        btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				c.show(cards, "PATIENTLIST");
			}
		});
        
        btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int answer = JOptionPane.showConfirmDialog(PatientPanel.this, 
											"Are you sure you want to delete "+p.getFirstName()+" "+p.getLastName()+"?",
											"Confirm Delete",
											JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(PatientPanel.this, "Patient deleted.");
					PDH.remove(p);
					c.show(cards, "PATIENTLIST");
					cards.remove(PatientPanel.this);
				} else if (answer == JOptionPane.NO_OPTION) {
					
				}
			}
		});
        
        btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				p.setFirstName(fldFirstName.getText());
				p.setLastName(fldLastName.getText());
				p.setDOB(fldDob.getText());
				p.setAddress(fldAddress.getText());
				p.setEmergencyNumber(fldEmergencyPhone.getText());
				p.setCondition(fldCondition.getText());
				p.setComments(txtComments.getText());
				if (isNewPatient) {
					PDH.add(p);
				} else {
					PDH.update(p);
				}
			}
		});
       
	}
	
	public PatientPanel(UI ui) {
		this(ui, new Patient("","","","","",""));
		this.isNewPatient = true;
	}
	
	protected static JList imageList(Patient p) {
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
					JPanel pnlImgView = imageView(pnlImg, p, index);
					JFrame imgWindow = new JFrame();
					imgWindow.add(pnlImgView);
					imgWindow.setSize(400, 500);
					imgWindow.setVisible(true);
				}
			}
		});
		imageList = lstMedicalImages;
		return lstMedicalImages;
	}
	
	
	protected static JPanel imageView(ImagePanel pnlImg, Patient p, int index) {
		
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
					if (index == PROFILE) {
						L.info("Set profile imageView ImagePanel to file "+pnlImg.getPath());
						L.info("Set Profile Picture to "+path);
						p.setProfilePicturePath(path);
						pnlImg.setImage(path);;
						pnlImg.repaint();
						pnlProfilePicture.setImage(path);
						pnlProfilePicture.repaint();
						L.info("Set patientPanel ImagePanel to file "+pnlProfilePicture.getPath());
					} else {
						L.info("Set medical imageView ImagePanel to file "+path);
						DefaultListModel<JLabel> dlm = (DefaultListModel<JLabel>) imageList.getModel();
						JLabel newLabel = iconLabel(path);
						dlm.setElementAt(newLabel, index);
						L.info("Set Medical Image JList item "+index+" to image at "+path);
						imageList.repaint();
						List<String> medicalImagesPath = p.getMedicalImagesPath();
						System.out.println();
						medicalImagesPath.set(index, path);
						L.info("Patient "+p.getFirstName()+" "+p.getLastName()+" medical image "+index+" changed to "+path);
						L.info("New medical images: "+medicalImagesPath);
					}
				}
				fc.setVisible(false);
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
	
	protected static JLabel iconLabel(String path) {
		Path filepath = Paths.get(path);
		String filename = filepath.getFileName().toString();
		
		BufferedImage bi = PatientDataHandler.getImage(path);
		Image resizedImg = bi.getScaledInstance(100, 100, 0);
		ImageIcon icon = new ImageIcon(resizedImg);
		
		JLabel imgLabel = new JLabel(filename, icon, JLabel.LEFT);
		
		return imgLabel;
	}
	
	protected static Path absoluteWorkingPath() {
		Path currentRelativePath = Paths.get("");
		return currentRelativePath.toAbsolutePath();
	}
}
