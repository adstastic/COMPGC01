package v1;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class PatientTableModel extends AbstractTableModel{
	private static List<Patient> patients; 
	
	public PatientTableModel(List<Patient> patientList) {
		patients = patientList;
	}

	@Override
	public int getRowCount() {
		return patients.size();
	}

	@Override
	public int getColumnCount() {
		return patients.get(0).keys().size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Patient p = patients.get(rowIndex);
		Object o = p.keys().get(columnIndex);
		return o;
	}
	
}
