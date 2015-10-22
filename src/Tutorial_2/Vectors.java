package Tutorial_2;

import java.util.Scanner;
import java.util.Vector;

public class Vectors {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter integers separated by spaces.");
		String[] input = scanner.nextLine().split(" ");
		Vector<Integer> integers = new Vector<Integer>();
		for (String el : input) {
			integers.add(Integer.parseInt(el));
		}
		
		integers = quickSort(integers);
		System.out.println(integers);
		scanner.close();
	}
	
	public static Vector<Integer> quickSort(Vector<Integer> array) {
		if (!array.isEmpty()) {
			int pivot = array.get(0);
			
			Vector<Integer> less = new Vector<Integer>();
			Vector<Integer> more = new Vector<Integer>();
			Vector<Integer> pivotList = new Vector<Integer>();
			
			for (Integer integer : array) {
				if(integer < pivot) {
					less.add(integer);
				} else if (integer > pivot) {
					more.add(integer);
				} else {
					pivotList.add(integer);
				}
			}
			
			less = quickSort(less);
			more = quickSort(more);
			
			less.addAll(pivotList);
			less.addAll(more);
			return less;
		}
		return array;
	}

}
