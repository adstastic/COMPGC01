package v2;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Patient {
	protected final static Logger L = Logger.getLogger("L");
	static AtomicInteger nextId = new AtomicInteger();
	private Integer ID;
	private String FIRST_NAME;
	private String LAST_NAME;
	private String DOB;
	private String ADDRESS;
	private String EMERGENCY_NUMBER;
	private String CONDITION;
	private String COMMENTS;
	private List<String> APPOINTMENTS = new ArrayList<String>();
	private String PROFILE_PICTURE;
	private List<String> MEDICAL_IMAGES = new ArrayList<String>();
		
	public Patient(String fname, String lname, String dob, String addr, String epnumber, String condition) {
		this.FIRST_NAME = fname;
		this.LAST_NAME = lname;
		this.DOB = dob;
		this.ADDRESS = addr;
		this.EMERGENCY_NUMBER = epnumber;
		this.CONDITION = condition;
		L.info("Patient "+this.FIRST_NAME+" "+this.LAST_NAME+" created.");
	}
	
	@Override
	public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(this);
		return json;
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
	
	protected List<Object> toList() {
		List<Object> fieldList = new LinkedList<Object>();
		fieldList.add(ID);
		fieldList.add(FIRST_NAME);
		fieldList.add(LAST_NAME);
		fieldList.add(DOB);
		fieldList.add(ADDRESS);
		fieldList.add(EMERGENCY_NUMBER);
		fieldList.add(CONDITION);
		fieldList.add(COMMENTS);
		fieldList.add(APPOINTMENTS);
		fieldList.add(PROFILE_PICTURE);
		fieldList.add(MEDICAL_IMAGES);
		return fieldList;
	}
	
	/* Getters and Setters */
	
	public Integer getID() {
		return this.ID;
	}
	
	public void setID(Integer id) {
		this.ID = id;
	}
	
	public String getFirstName() {
		return FIRST_NAME;
	}

	public void setFirstName(String firstName) {
		FIRST_NAME = firstName;
	}

	public String getLastName() {
		return LAST_NAME;
	}

	public void setLastName(String lastName) {
		LAST_NAME = lastName;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public String getAddress() {
		return ADDRESS;
	}

	public void setAddress(String address) {
		ADDRESS = address;
	}

	public String getEmergencyNumber() {
		return EMERGENCY_NUMBER;
	}

	public void setEmergencyNumber(String emergencyNumber) {
		EMERGENCY_NUMBER = emergencyNumber;
	}

	public String getCondition() {
		return CONDITION;
	}

	public void setCondition(String condition) {
		CONDITION = condition;
	}

	public String getComments() {
		return COMMENTS;
	}

	public void setComments(String comments) {
		COMMENTS = comments;
	}

	public List<String> getAppointments() {
		return this.APPOINTMENTS;
	}
	
	public void setAppointments(List<String> appointments) {
		this.APPOINTMENTS = appointments;
	}
	
	public String getProfilePicturePath() {
		return this.PROFILE_PICTURE;
	}
	
	public void setProfilePicturePath(String path) {
		this.PROFILE_PICTURE = path;
	}
	
	public List<String> getMedicalImagesPath() {
		return this.MEDICAL_IMAGES;
	}
	
	public void setMedicalImagesPath(List<String> pathList) {
		this.MEDICAL_IMAGES = pathList;
	}
	
	
}
