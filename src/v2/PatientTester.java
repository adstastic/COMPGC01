package v2;

import java.util.ArrayList;
import java.util.List;

public class PatientTester {
	private List<Patient> patients = new ArrayList<Patient>();

	public static void main(String[] args) {
		String filepath = "./data/patient_test.json";
		// Create patients
		Patient p1 = examplePatient();
		Patient p2 = examplePatient();
		
		// Create patient data handler
		PatientDataHandler pdh = new PatientDataHandler(filepath);
		
		// Add patients to data handler to test writing of data file
		System.out.println("ADD PATIENT, WRITE TEST");
		pdh.add(p1);
		pdh.add(p2);
		System.out.println(pdh);
		
		// Remove patients from data handler
		System.out.println("REMOVE PATIENT, WRITE TEST");
		pdh.remove(p2);
		System.out.println(pdh);
		
		// remove reference to current data handler (thereby disposing it)
		System.out.println("REMOVING DATA HANDLER");
		pdh = null;
		
		// Instantiate new data handler to test reading of existing data file
		System.out.println("READ TEST");
		PatientDataHandler pdh1 = new PatientDataHandler(filepath);
		System.out.println(pdh1);
		
		System.out.println(pdh1.getAtIndex(0));
	}
	
	public static Patient examplePatient() {
		Patient p = new Patient("John", "Smith", "01/01/1991","1 Road Place", "+44 1234 567 890", "Non-existence");
		p.addAppointment("06/12/15 09:00");
		String image1 = "./images/patient1.jpg";
		String image2 = "./images/scan1.jpg";
		String image3 = "./images/scan2.jpg";
		p.addImage(image1 , "profile");
		p.addImage(image2 , "medical");
		p.addImage(image3 , "medical");
		return p;
	}
	
	
}
