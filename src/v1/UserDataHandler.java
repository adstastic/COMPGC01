package v1;

import java.util.logging.Logger;

import org.json.JSONObject;

public class UserDataHandler extends DataHandler {
	private final static Logger L = Logger.getLogger("L");
	String AUTH_CODE = "H4X0R";
	JSONObject userDataStore = super.dataStore;
	
	protected UserDataHandler(String filepath) {
		super(filepath);
	}
	
	protected boolean addUser(String username, String password, String AUTH_CODE) {
		String userRegistrationInfo = String.format("Username: %-10s\n"+
													"Password: %-10s\n"+
													"Auth Code: %-10s\n", username, password, AUTH_CODE);
		L.info(userRegistrationInfo);
		if (AUTH_CODE.equals(this.AUTH_CODE)) {
			super.put(username, password);
			L.info("New user registered.");
			return true;
		} else {
			L.warning("Invalid Authentication Code.");
			L.info("User Registration Failed");
			return false;
		}
	}
	
	protected boolean authenticate(String username, String password) {
		if (super.contains(username)) {
			L.info("User "+username+" logged in.");
			return true;
		} else {
			L.warning("Invalid login details");
			return false;
		}
	}
	
	

}
