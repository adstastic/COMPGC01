package v2;

import org.json.JSONObject;

public class PatientDataHandlerTester {

	public static void main(String[] args) {
		PatientDataHandler pdh = addPatientsTest();
		System.out.println(pdh.toString());
	}
	
	public static PatientDataHandler addPatientsTest() {
		PatientDataHandler pdh = new PatientDataHandler("./data/patients.json");
		Patient p1 = PatientTester.ConstructWithInputFields();
		Patient p2 = PatientTester.ConstructWithMap();
		
		pdh.addPatient(p1);
		pdh.addPatient(p2);
		
		return pdh;
	}

}
