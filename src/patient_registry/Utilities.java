package patient_registry;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Utilities {
	private static HashMap<String, String> user_map = new HashMap<String, String>();
	
	public static boolean authenticate(String username, char[] password) {
		boolean login = false;
		String password_str = Arrays.toString(password);
		Iterator<Entry<String, String>> users = user_map.entrySet().iterator();
		while(users.hasNext()) {
			Entry<String, String> entry = (Entry<String, String>) users.next(); 
			if (entry.getKey().equals(username) && entry.getValue().equals(password_str)) {
				login = true;
			} 
		} 
		return login;
	}
	
	public void add_user(String username, String password) {
		user_map.put(username, password);
	}
	
	public void read(String filepath) {
		try {
			File user_map_obj = new File("./user_map.ser");
			if (user_map_obj.exists()) { 
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user_map.ser"));
				user_map = (HashMap<String, String>) ois.readObject();
				ois.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void write() {
		try {
			File user_map_obj = new File("./user_map.ser");
			if (user_map_obj.exists()) { 
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user_map.ser"));
				oos.writeObject(user_map);
				oos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
