package v2;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class PatientTablePanel extends JPanel {
	private final static Logger L = Logger.getLogger("L");
	JTextField filterText;
	TableRowSorter<TableModel> sorter;
	
	PatientTablePanel(UI ui, PatientDataHandler PDH) {
		JLabel lblPatients = new JLabel("Patients (double click to open)");
		CardLayout c = ui.getCardLayout();
		JPanel cards = ui.getCards();
		
		setLayout(new BorderLayout());
        
		/* START CITATION 
		 * REFERENCE: https://docs.oracle.com/javase/tutorial/uiswing/examples/components/TableFilterDemoProject/src/components/TableFilterDemo.java */
		
		TableModel patientTableModel = new PatientTableModel(PDH.getPatientList());
		JTable tblPatientList = new JTable(patientTableModel);
		sorter = new TableRowSorter<TableModel>(tblPatientList.getModel());
		tblPatientList.setRowSorter(sorter);
		tblPatientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
		tblPatientList.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
                    public void valueChanged(ListSelectionEvent event) {
                        int viewRow = tblPatientList.getSelectedRow();
                        if (viewRow < 0) {
                            //Selection got filtered away.
                        } else {
                            int modelRow = 
                                tblPatientList.convertRowIndexToModel(viewRow);
                            L.info(
                                String.format("Selected Row in view: %d. " +
                                    "Selected Row in model: %d.", 
                                    viewRow, modelRow));
                        }
                    }
                }
        );
		
		JPanel form = new JPanel(new SpringLayout());
        JLabel l1 = new JLabel("Filter Text:", SwingConstants.TRAILING);
        form.add(l1);
        filterText = new JTextField();
        //Whenever filterText changes, invoke newFilter.
        filterText.getDocument().addDocumentListener(
                new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void insertUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void removeUpdate(DocumentEvent e) {
                        newFilter();
                    }
                });
        l1.setLabelFor(filterText);
        form.add(filterText);

		/* END CITATION */
		
		tblPatientList.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent me) {
		        JTable table =(JTable) me.getSource();
		        Point p = me.getPoint();
		        int row = table.rowAtPoint(p);
		        PatientTableModel ptm = (PatientTableModel) table.getModel();
		        Patient selectedPatient = ptm.getRow(row);
		        L.info("Patient "+selectedPatient.getFirstName()+" "+selectedPatient.getLastName()+" selected.");
		        if (me.getClickCount() == 2) {
		        	PatientPanel pnlPatient = new PatientPanel(ui, selectedPatient);
		        	UI.refreshPatientPanel(pnlPatient);
		        }
		    }
		});
		
		JPanel tablePane = new JPanel(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane(tblPatientList);
		tablePane.add(scrollPane, BorderLayout.CENTER);
		tablePane.add(form, BorderLayout.SOUTH);
		
		JPanel pnlButtons = new JPanel(new GridLayout(1,3));
		
		JButton btnAdd = new JButton("Add Patient");
		JButton btnDelete = new JButton("Delete Patient");
		JButton btnLogout = new JButton("Log out");
		
		pnlButtons.add(btnAdd);
		pnlButtons.add(btnDelete);
		pnlButtons.add(btnLogout);
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PatientPanel pnlPatient = new PatientPanel(ui);
				UI.refreshPatientPanel(pnlPatient);
			}
		});
		
		btnLogout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				c.show(cards, "LOGIN");
			}
		});
		
		add(lblPatients, BorderLayout.NORTH);
		add(tablePane, BorderLayout.CENTER);
		add(pnlButtons, BorderLayout.SOUTH);

	}
	
	 private void newFilter() {
	        RowFilter<TableModel, Object> rf = null;
	        //If current expression doesn't parse, don't update.
	        try {
	            rf = RowFilter.regexFilter(filterText.getText(), 0);
	        } catch (java.util.regex.PatternSyntaxException e) {
	            return;
	        }
	        sorter.setRowFilter(rf);
	    }
}
