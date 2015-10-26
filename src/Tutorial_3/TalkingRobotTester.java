package Tutorial_3;

public class TalkingRobotTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Robot2 r1 = new Robot2();
		String[] u1 = { "Exterminate, Exterminate!", "I obey!", 
						"You cannot escape.", "Robots do not feel fear.", 
						"The Robots must survive!" };
	
		r1.setSayings(u1); 
		
		System.out.println("\nRobot r1 says: ");
		for (int i = 0; i < 10; i++) { 
			r1.speak();
		}
		
		System.out.println("\nRobot r2 says: ");
		
		Robot2 r2 = new Robot2(); 
		
		String[] u2 = {"I obey!"};
		
		r2.setSayings(u2);
		
		for (int i = 0; i < 10; i++) { 
			r2.speak();
		} 
	
	}
	
}

