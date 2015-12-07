package v2;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Patient {
	protected final static Logger L = Logger.getLogger("L");
	protected Integer ID;
	protected String FIRST_NAME;
	protected String LAST_NAME;
	protected String DOB;
	protected String ADDRESS;
	protected String EMERGENCY_NUMBER;
	protected String CONDITION;
	protected String COMMENTS;
	protected List<String> APPOINTMENTS = new ArrayList<String>();
	protected String PROFILE_PICTURE;
	protected List<String> MEDICAL_IMAGES = new ArrayList<String>();
		
	public Patient(String fname, String lname, String dob, String addr, String epnumber, String condition) {
		this.FIRST_NAME = fname;
		this.LAST_NAME = lname;
		this.DOB = dob;
		this.ADDRESS = addr;
		this.EMERGENCY_NUMBER = epnumber;
		this.CONDITION = condition;
		L.info("Patient "+this.FIRST_NAME+" "+this.LAST_NAME+" created.");
	}
				
	protected void addImage(String imagePath, String destinationField) {
		if (destinationField.equalsIgnoreCase("profile")) {
			this.PROFILE_PICTURE = imagePath;
		} else if (destinationField.equalsIgnoreCase("medical")) {
			this.MEDICAL_IMAGES.add(imagePath);
		}
	}
	
	protected void addComment(String comments) {
		this.COMMENTS = comments;
	}
	
	protected void addAppointment(String appointment) {
		this.APPOINTMENTS.add(appointment);
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
	
	protected Integer getID(Integer id) {
		return this.ID;
	}

	protected List<String> getAppointments() {
		return this.APPOINTMENTS;
	}
	
	protected String getProfilePicturePath() {
		return this.PROFILE_PICTURE;
	}
	
	protected List<String> getMedicalImagesPath() {
		return this.MEDICAL_IMAGES;
	}
	
	protected void setID(Integer id) {
		this.ID = id;
	}
	
	
	
}
