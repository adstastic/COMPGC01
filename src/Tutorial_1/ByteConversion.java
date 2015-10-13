package Tutorial_1;
import java.util.Scanner;
/**
 * @author amukherj
 *
 */
public class ByteConversion {
	static final String[] UNITS = {"B", "KB", "MB", "GB"};
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("Please enter a number of bytes to be converted:");
            float bytes = Float.parseFloat(s.nextLine());
            float[] number = new float[UNITS.length];
            number[0] = bytes;
            for (int i = 1; i < UNITS.length; i++) {
                number[i] = number[i-1]/1024;
            }
            for (int i = 0; i < UNITS.length; i++) {
            	System.out.println(number[i]+" "+UNITS[i]);
            }
            System.out.println("Again? (y/n)");
            String decision = s.nextLine();
            if (decision.equals("n")) {
                System.out.println("Goodbye!");
                break;
            }
        }
    }
}
