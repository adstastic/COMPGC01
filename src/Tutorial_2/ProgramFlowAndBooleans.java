package Tutorial_2;
import java.util.Scanner;

public class ProgramFlowAndBooleans {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		System.out.println("Enter a number.");
		String input = s.nextLine();
		int number = Integer.parseInt(input);
		boolean isEven = isEven_noLocal(number);
		if (isEven) {
			System.out.println(number+" is even.");
		} else {
			System.out.println(number+" is odd.");
		}
		s.close();
 	}
	
	public static boolean isEven_noLocal(int number) {
		return (number % 2 == 0) ? true : false;
	}
	
	public static boolean isEven_singleReturn(int number) {
		boolean isEven = (number % 2 == 0) ? true : false;
		return isEven;
	}
	
	public static boolean isEven_original(int number) {
		if (number % 2 == 0) {
			return true;
		} else {
			return false;
		}
	}
}
