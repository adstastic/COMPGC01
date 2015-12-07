package v1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.json.*;

public abstract class DataHandler {
	private final static Logger L = Logger.getLogger("L");
	protected JSONObject dataStore;
	protected String filepath;
	protected File JSONfile;
	
	protected DataHandler(String filepath) {
		JSONfile = new File(filepath);
		if (JSONfile.exists()) {
			L.info("Reading JSON file...");
			JSONObject dataStore = read(filepath);
			this.dataStore = dataStore;
			this.filepath = filepath;
		} else {
			L.info("Creating JSON file...");
			try {
				JSONfile.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
			JSONObject dataStore = new JSONObject();
			this.dataStore = dataStore;
			this.filepath = filepath;
		}
		
	}
	
	protected Object getValue(String key) {
		return this.dataStore.get(key);
	}
	
	protected void put(String key, Object value) {
		this.dataStore.put(key, value);
		write();
	}
	
	protected boolean contains(String key) {
		if (this.dataStore.has(key)) {
			return true;
		} else {
			return false;
		}
	}
	
	protected List<String> keys() {
		return new ArrayList<String>(this.dataStore.keySet());
	}

	
	protected JSONObject read(String filepath) {
		try {
			JSONTokener jt = new JSONTokener(new BufferedReader(new FileReader(filepath)));
			return new JSONObject(jt);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected JSONObject read() {
		try {
			JSONTokener jt = new JSONTokener(new BufferedReader(new FileReader(this.filepath)));
			return new JSONObject(jt);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected void write(String filepath) {
		try {
			this.dataStore.write(new BufferedWriter(new FileWriter(filepath)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void write() {
		try {
			L.info("Datastore: "+this.dataStore.toString());
			String JSONString = this.dataStore.toString();
			FileWriter fw = new FileWriter(JSONfile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(JSONString);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String toString() {
		return this.dataStore.toString();
	}
	
}
