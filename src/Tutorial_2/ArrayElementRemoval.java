package Tutorial_2;
import java.util.Scanner;

public class ArrayElementRemoval {
	
	static String[] arr = {"The ", "quick ", "brown ", "fox ", "jumps ", 
					"over ", "the ", "lazy ", "dog."};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		System.out.println(String.join(" ", arr));
		System.out.print("Enter an element position to remove from the above sentence: ");
		int position = Integer.parseInt(s.nextLine());
		arr = remElement(arr, position);
		System.out.println(String.join(" ", arr));
	}
	
	public static String[] remElement(String[] array, int position) {
		String[] newArray = new String[array.length-1];
		for (int i=0; i<array.length; i++) {
			if (i < position) {
				newArray[i] = array [i];
			} else if (i == position) {}
			else if (i > position) {
				newArray[i-1] = array[i];
			}
		}
		return newArray;
	}
}
