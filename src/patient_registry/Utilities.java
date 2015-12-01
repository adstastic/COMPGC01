package patient_registry;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Utilities {
	private static HashMap<String, String> userMap = new HashMap<String, String>();
	
	public static boolean authenticate(String username, String password) {
		boolean login = false;
		Iterator<Entry<String, String>> users = userMap.entrySet().iterator();
		while(users.hasNext()) {
			Entry<String, String> entry = (Entry<String, String>) users.next(); 
			if (entry.getKey().equals(username) && entry.getValue().equals(password)) {
				login = true;
			} 
		} 
		return login;
	}
	
	public static boolean addUser(String username, String password) {
		userMap.put(username, password);
		try {
			XMLencode(userMap, "users.xml");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}g 
	}
	
	public void readObject(String filepath) throws Exception {
		File userMapFile = new File("./user_map.ser");
		if (userMapFile.exists()) { 
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user_map.ser"));
			userMap = (HashMap<String, String>) ois.readObject();
			ois.close();
		}
	}
	
	public static void writeObject() throws Exception {
		File userMapObj = new File("./user_map.ser");
		if (userMapObj.exists()) { 
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user_map.ser"));
			oos.writeObject(userMap);
			oos.close();
		}
	}
	
	public static void XMLencode(HashMap<String, String> userMap, String filename) throws Exception {
		XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)));
		e.writeObject(userMap);
		e.close();
	}
	
	public static HashMap<String, String> XMLdecode(String filename) throws Exception {
		XMLDecoder d = new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)));
//			if (d.readObject().getClass() == HashMap.class) {				
			HashMap<String, String> userMap = (HashMap<String, String>) d.readObject();
//			}
		d.close();
		return userMap;
	}
	
}
