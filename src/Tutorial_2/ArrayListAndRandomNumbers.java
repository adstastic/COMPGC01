package Tutorial_2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ArrayListAndRandomNumbers {

	public static void main(String[] args) {
		Random random = new Random();
		ArrayList<Integer> aList= new ArrayList<Integer>();
		ArrayList<Integer> oddaList= new ArrayList<Integer>();
		int sum = 0;
		
		/* populate ArrayList with random numbers */
		for (int i=0; i<100; i++) {
			int currentRand = random.nextInt(100);
			aList.add(random.nextInt(100));
			if (ProgramFlowAndBooleans.isEven_noLocal(currentRand)) {
				sum += currentRand;
			/* separate out odd numbers as they are added into randArrList for use later */
			} else {
				oddaList.add(currentRand);
			}
		}
		System.out.println("Random ArrayList: "+aList.toString());
		System.out.println("Sum: "+sum);
		
		/* Remove odd elements from randArrList */
//		aList.removeAll(oddaList); // Not working for some reason
		
		for (int i=0; i<aList.size(); i++) {
			int randomNumber = aList.get(i);
			if (!ProgramFlowAndBooleans.isEven_noLocal(randomNumber)) {
				aList.remove(i);
				i--;
			}
		}
		
		System.out.println("Random Arraylist of even: "+aList);
		System.out.println("Random Arraylist of odd: "+oddaList);
		aList.addAll(oddaList); // not adding elements back in at end
		
		System.out.println("Random ArrayList with even separated from odd: "+aList.toString());
		
		/* convert to int Array */
		int [] randArr = new int[aList.size()];
		for (int i=0; i<aList.size(); i++) {
			randArr[i] = aList.get(i);
		}
		
		System.out.println("Random Array: "+Arrays.toString(randArr));
	}

}
