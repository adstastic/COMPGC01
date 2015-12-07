package v2;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

/**
 * @author amukherj
 *
 */
public class PatientDataHandler {
	private final static Logger L = Logger.getLogger("L");
	protected List<Patient> patientList = new LinkedList<Patient>();
	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private String FILEPATH;
	
	public PatientDataHandler(String filepath) {
		this.FILEPATH = filepath;
		L.info("Patient Data Handler created.");
		if (new File(this.FILEPATH).exists()) {
			read();
		}
	}
	
	protected void add(Patient p) {
		Integer lastIndex = patientList.size();
		p.setID(lastIndex);
		patientList.add(p);
		L.info("Patient "+p.getFirstName()+" "+p.getLastName()+" added.");
		write();
	}
	
	protected void update(Patient p) {
		patientList.set(p.getID(), p);
		L.info("Patient "+p.getFirstName()+" "+p.getLastName()+" modified.");
		write();
	}
	
	protected void remove(Patient p) {
		patientList.remove(p);
		L.info("Patient "+p.getFirstName()+" "+p.getLastName()+" removed.");
		write();
	}
	
	protected void read() {
		try {
			JsonReader jsonReader = new JsonReader(new FileReader(this.FILEPATH));
			this.patientList = gson.fromJson(jsonReader, 
					new TypeToken<LinkedList<Patient>>(){}.getType());
			L.info("Read data from "+this.FILEPATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	protected void write() {
		Writer w;
		try {
			w = new FileWriter(new File(FILEPATH));
			gson.toJson(patientList, w);
			L.info("Wrote data to "+this.FILEPATH);
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected static BufferedImage getImage(String path) {
		BufferedImage bi;
		try {
			bi = ImageIO.read(new File(path));
			return bi;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected static BufferedImage getImage(File file) {
		BufferedImage bi;
		try {
			bi = ImageIO.read(file);
			return bi;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected static List<BufferedImage> getImage(List<String> paths) {
		List<BufferedImage> imgList = new ArrayList<BufferedImage>();
		for (String path : paths) {			
			try {
				System.out.println(path);
				BufferedImage bi = ImageIO.read(new File(path));
				imgList.add(bi);				
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		return imgList;
	}
	
	/* Getters and Setters */
	protected Patient getAtIndex(Integer index) {
		Patient p = this.patientList.get(index);
		L.info("Getting patient "+p.getFirstName()+
				" "+p.getLastName()+" at index "+index);
		return p;
	}
	
	/**
	 * Returns {@link List} of {@link Patient}s
	 * @return {@link List} of {@link Patient}s
	 */
	protected List<Patient> getPatientList() {
		return this.patientList;
	}
	
	/* Utility methods */ 
	@Override
	public String toString() {
		return gson.toJson(patientList);
	}
	
	
	/**
	 * {@link Patient} ID's are allocated as they are added to patientList. 
	 * When Patients are deleted from patientList, the remaining ID's do not 
	 * remain sequential. This remaps each Patient's ID to it's current index 
	 * in patientList.
	 */
	public void reMapIds() {
		for(int i=0; i<this.patientList.size(); i++) {
			Patient p = patientList.get(i);
			p.setID(i);
			update(p);
		}
	}
}
