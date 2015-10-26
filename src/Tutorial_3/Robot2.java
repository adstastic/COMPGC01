package Tutorial_3;
import java.util.Random;

public class Robot2 extends Robot1 {
	
	private String[] sayings = {}; 
	
	public void setSayings(String[] sayings) {
		this.sayings = sayings;
	}
	
	public void speak() {
		Random r = new Random();
		int choice = r.nextInt(sayings.length);
		System.out.println(sayings[choice]);
	}
}
