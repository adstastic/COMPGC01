package v1;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PatientTester {

	public static void main(String[] args) {
		Patient p1 = ConstructWithInputFields();
		Patient p2 = ConstructWithMap();
		System.out.println(p1.toString());
		System.out.println(p2.toString());
	}
	
	public static Patient ConstructWithInputFields() {
		Patient p = new Patient("John", "Smith", "01/01/1991","1 Road Place", "+44 1234 567 890", "Non-existence");
		p.addAppointment("06/12/15 09:00");
		Path image1 = Paths.get("./images/patent1.jpg");
		Path image2 = Paths.get("./images/scan1.jpg");
		Path image3 = Paths.get("./images/scan2.jpg");
		p.addImage(image1 , "profile");
		p.addImage(image2 , "medical");
		p.addImage(image3 , "medical");
		return p;
	}
	
	public static Patient ConstructWithMap() {
		Map<String, Object> patient = new LinkedHashMap<String, Object>();
		List<Date> appointments = new ArrayList<Date>();	
		List<Path> medical_images = new ArrayList<Path>();
		Path image1 = Paths.get("./images/patient1.jpg");
		Path image2 = Paths.get("./images/scan1.jpg");
		Path image3 = Paths.get("./images/scan2.jpg");
		
		medical_images.add(image2);
		medical_images.add(image3);
		
		appointments.add(Patient.parseDate("", "06/12/2016 12:43"));
//		appointments.add(Patient.parseDate("", "24/11/2018 24:22")); // Correctly throws unparseable error
//		appointments.add(Patient.parseDate("", "06/16/2016 23:57")); // Correctly throws unparseable error
		patient.put("first_name", "John");
		patient.put("last_name", "Smith");
		patient.put("address", "Somewhere");
		patient.put("emergency_number", "+44 0000 000 000");
		patient.put("date_of_birth", Patient.parseDate("date", "02/12/2011"));
//		patient.put("date_of_birth", Patient.parseDate("date", "02/16/2011")); // Correctly throws unparseable error
		patient.put("condition", "Not knowing what condition they have");
		patient.put("comments", "There is much to be said.");
		patient.put("appointments", appointments);
		patient.put("profile_image", image1);
		patient.put("medical_image", medical_images);
		
		Patient p = new Patient(patient);
		return p;
	}

}
