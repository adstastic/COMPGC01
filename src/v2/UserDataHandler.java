package v2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;


public class UserDataHandler {
	private final static Logger L = Logger.getLogger("L");
	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private String AUTH_CODE = "H4X0R";
	private String FILEPATH;
	protected Map<String, String> userList = new HashMap<String, String>();
	
	protected UserDataHandler(String filepath) {
		this.FILEPATH = filepath;
		if (new File(this.FILEPATH).exists()) {
			read();
		}
	}
	
	protected boolean addUser(String username, String password, String AUTH_CODE) {
		String userRegistrationInfo = String.format("Username: %-10s\n"+
													"Password: %-10s\n"+
													"Auth Code: %-10s\n", username, password, AUTH_CODE);
		L.info(userRegistrationInfo);
		if (AUTH_CODE.equals(this.AUTH_CODE)) {
			userList.put(username, password);
			L.info("New user registered.");
			write();
			return true;
		} else {
			L.warning("Invalid Authentication Code.");
			L.info("User Registration Failed");
			return false;
		}
	}
	
	protected boolean authenticate(String username, String password) {
		if (userList.containsKey(username)) {
			L.info("User "+username+" logged in.");
			return true;
		} else {
			L.warning("Invalid login details");
			return false;
		}
	}
	
	protected void read() {
		try {
			JsonReader jsonReader = new JsonReader(new FileReader(this.FILEPATH));
			this.userList = gson.fromJson(jsonReader, new TypeToken<Map<String, String>>(){}.getType());
			L.info("Read data from "+this.FILEPATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	protected void write() {
		Writer w;
		try {
			w = new FileWriter(new File(FILEPATH));
			gson.toJson(userList, w);
			L.info("Wrote data to "+this.FILEPATH);
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
