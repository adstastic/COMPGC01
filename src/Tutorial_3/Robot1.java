package Tutorial_3;

public class Robot1 {
	
	private double batteryCharge = 5.0;
	
	public void batteryReCharge(double c) {
		batteryCharge += c;
		System.out.println("Battery charge is: "+batteryCharge);
	}
	
	public void move(int distance) {
		for (int i = 1; i<=distance; i++) {
			if (batteryCharge >= 0.5) {
				batteryCharge -= 0.5;
				System.out.print("["+i+"] ");
			} else {
				System.out.println("No power!");
				break;
			}
		}
	}
	
}
