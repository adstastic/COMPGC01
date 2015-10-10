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
            float number = bytes;
            System.out.println(bytes + UNITS[0]);
            for (int i = 1; i < UNITS.length; i++) {
                number /= 1024;
                System.out.println(number + UNITS[i]);
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
