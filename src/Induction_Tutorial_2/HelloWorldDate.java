package Induction_Tutorial_2;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
*<dl>
 *	<dt> Purpose:
 *  <dd> Simple example program
 *  
 *  <dt> Description:
 *  <dd> This program prints out the message Hello World and the current date and time formatted.
 * </dl> 
 *
 * @author Aditya Mukherjee
 * @version 1 Oct 2015 14:05:30
 *
 */

public class HelloWorldDate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Date date = new Date( );
	    SimpleDateFormat formattedDate = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");

	    System.out.println("Hello World!" + '\n' + "Current Date: " + formattedDate.format(date));
	}

}
