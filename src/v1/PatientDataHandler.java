package v1;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Logger;
import org.json.JSONObject;

public class PatientDataHandler extends DataHandler {
	private final static Logger L = Logger.getLogger("L");
	JSONObject patientDataStore = super.dataStore;
	
	protected PatientDataHandler(String filepath) {
		super(filepath);
	}
	
	protected boolean addPatient(Patient p) {
		try {
			super.put(nextKey(), p.getData());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private static Comparator<String> INTEGER_VALUE = new Comparator<String>() {
		public int compare (String key1, String key2) {
			return Integer.parseInt(key1) - Integer.parseInt(key2);
		}
	};
	
	protected String nextKey() {
		if (keys().isEmpty()) {
			return "0";
		} else {
			Collections.sort(keys(), INTEGER_VALUE);
			String maxKey = keys().get(0);
			Integer nextKey = Integer.parseInt(maxKey) +1;
			return nextKey.toString();
		}
	}

}
