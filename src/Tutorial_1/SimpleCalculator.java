package Tutorial_1;
import java.util.Scanner;

public class SimpleCalculator {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while (true) {
			float choice = selector(s);
			
			if (choice > 0 && choice < 5) {
				System.out.println("Please input two numbers with a space between.");
				String numberInput = s.nextLine();
				String[] numbers = numberInput.split(" ");
				float a = Float.parseFloat(numbers[0]);
				float b = Float.parseFloat(numbers[1]);
				float result = calculator(choice, a, b);
				System.out.println(result);
			} else if (choice == 5) {
				System.out.println("Goodbye!");
				break;
			} else {
				System.out.println("Invalid selection");
			}
		}
	}
	
	public static float selector(Scanner s) {
		System.out.println("Simple calculator. Please choose an operation:"
				+'\n'+"1. Add"
				+'\n'+"2. Subtract"
				+'\n'+"3. Multiply"
				+'\n'+"4. Divide"
				+'\n'+"5. Exit");
		String input = s.nextLine(); 
		float choice = Float.parseFloat(input);
		return choice;		
	}
	
	public static float calculator(float choice, float a, float b) {
		float result = 0;
		int selection = (int) choice; 
		switch(selection) {
			case 1: result = add(a,b);
					return result;
			case 2: result = subtract(a,b);
					return result;
			case 3: result = multiply(a,b);
					return result;
			case 4: result = divide(a,b);
					return result;
			default: System.out.println("Invalid selection");
					return result;
		}
	}
	
	public static float add(float a, float b) {
		float result = a + b;
		return result;
	}
	
	public static float subtract(float a, float b) {
		float result = a - b;
		return result;
	}
	
	public static float multiply(float a, float b) {
		float result = a * b;
		return result;
	}
	
	public static float divide(float a, float b) {
		float result = a / b;
		return result;
	}

}
