package Induction_Tutorial_2;
import java.util.Scanner;

/**
*<dl>
 *	<dt> Purpose:
 *  <dd> Simple example program
 *  
 *  <dt> Description:
 *  <dd> This program asks for user input and prints it out as part of a statement.
 * </dl> 
 *
 * @author Aditya Mukherjee
 * @version 1 Oct 2015 14:12:56
 *
 */

public class Name {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("What is your first name?");
		String firstName = scanner.nextLine();
		
		System.out.println("What is your last name?");
		String lastName = scanner.nextLine();
		
		System.out.println("Hello! I thought that your name might be " + firstName + " " + lastName +"!");
	}

}
