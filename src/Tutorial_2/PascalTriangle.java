package Tutorial_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PascalTriangle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		System.out.print("Enter number of rows ot Pascal's Triangle to print: ");
		int numRows = Integer.parseInt(s.nextLine());
		PrintPascalTriangle(numRows);
		s.close();
	}
	
	public static void PrintPascalTriangle(int numRows) {
		for (int i=0; i<numRows; i++) {
			ArrayList<Integer> pascalTriangleRow = new ArrayList<Integer>();
			pascalTriangleRow.add(1);
			for (int j=0; j < i; j++) {
				int nextEl = pascalTriangleRow.get(j) * (i - j) / (j + 1);
				pascalTriangleRow.add(nextEl);
			}
			System.out.println(pascalTriangleRow);
		}
	}

}
