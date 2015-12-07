package v1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class Patient {
	private final static Logger L = Logger.getLogger("L");
	private Map<String, Object> patient = new LinkedHashMap<String, Object>();
	
	public Patient(String fname, String lname, String dob, String addr, String epnumber, String condition) {
		this.patient.put("first_name", fname);
		this.patient.put("last_name", lname);
		this.patient.put("date_of_birth", parseDate("date", dob));
		this.patient.put("address", addr);
		this.patient.put("emergency_number", epnumber);
		this.patient.put("condition", condition);
		this.patient.put("comments", "none");
		this.patient.put("appointments", new ArrayList<Date>());
		this.patient.put("profile_image", "none");
		this.patient.put("medical_image", new ArrayList<Path>());
	}
	
	public Patient(Map<String, Object> patient) {
		this.patient = patient;
	}
			
	protected void addImage(Path imagePath, String destinationField) {
		if (destinationField.equalsIgnoreCase("profile")) {
			this.patient.put("profiile_image", imagePath);
		} else if (destinationField.equalsIgnoreCase("medical")) {
			ArrayList<Path> medicalImages = (ArrayList<Path>) this.patient.get("medical_image");
			medicalImages.add(imagePath);
			this.patient.put("medical_image", medicalImages);
		}
	}
	
	protected void addComment(String comments) {
		this.patient.put("comments", comments);
	}
	
	protected void addAppointment(String appointment) {
		List<Date> appointments = (ArrayList<Date>) this.patient.get("appointments");
		appointments.add(parseDate("appointment", appointment));
		this.patient.put("appointments", appointments);
	}
	
	protected Map<String, Object> getData() {
		return patient;
	}
	
	protected List<String> keys() {
		List<String> keyList = new ArrayList<String>(keys());
		return keyList;
	}
	
	protected Object getValue(int index) {
		return keys().get(index);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Entry<String, Object> e : this.patient.entrySet()) {
			String format = String.format("%-17s : %s", e.getKey(), e.getValue().toString());
			sb.append(format).append('\n');
		}
		return sb.toString();
	}
	
	protected BufferedImage getImage(Path p) {
		BufferedImage bi;
		try {
			bi = ImageIO.read(new File("./images/einstein.jpeg"));
			return bi;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	protected static Date parseDate(String type, String dateStr) {
		DateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm", Locale.ENGLISH);
		format.setLenient(false); // Only accepts valid dates
		if (type.equals("date")) {
			format = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);
			format.setLenient(false); // Only accepts valid dates
		}
		Date date;
		try {
			date = format.parse(dateStr);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}
