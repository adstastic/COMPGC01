package v2;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PatientTableModel extends AbstractTableModel {
	private static List<Patient> patients; 
	private String[] columnNames = {"ID", 
									"First Name", 
									"Last Name", 
									"Date of Birth", 
									"Address",
									"Emergency Number",
									"Condition",
									"Comments",
									"Appointments", 
									"Profile Picture",
									"Medical Images"};
	
	public PatientTableModel(List<Patient> patientList) {
		patients = patientList;
	}

	@Override
	public int getRowCount() {
		return patients.size();
	}

	@Override
	public int getColumnCount() {
		return 11;
	}  
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	public Patient getRow(int rowIndex) {
		Patient p = patients.get(rowIndex);
		return p;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Patient p = patients.get(rowIndex);
		Object o = p.toList().get(columnIndex);
		return o;
	}
	
}
