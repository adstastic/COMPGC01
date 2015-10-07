package Induction_Tutorial_2;
/**
*<dl>
 *	<dt> Purpose:
 *  <dd> Simple example program
 *  
 *  <dt> Description:
 *  <dd> This program asks for user input and prints it out as part of a statement and validate if input is empty.
 * </dl> 
 *
 * @author Aditya Mukherjee
 * 1 Oct 2015 14:31:41
 */

public class Loops {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int counter = 0; counter < 10; counter++) {
			System.out.println("message " + counter);
		}
		
		for (int counter = 10; counter > 0; counter--) {
			System.out.println("message " + counter);
		}
		
		int counter = 0;
		
		while (counter < 10) {
			System.out.println("message "+ counter);
			counter ++;
		}
	}

}
