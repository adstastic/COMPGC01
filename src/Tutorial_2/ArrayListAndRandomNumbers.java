package Tutorial_2;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;

public class ArrayListAndRandomNumbers {

	public static void main(String[] args) {
		Random random = new Random();
		List<Integer> randArrList= new ArrayList<Integer>();
		List<Integer> oddRandArrList= new ArrayList<Integer>();
		int sum = 0;
		
		/* populate ArrayList with random numbers */
		for (int i=0; i<100; i++) {
			int currentRand = random.nextInt(100);
			randArrList.add(random.nextInt(100));
			if (ProgramFlowAndBooleans.isEven_noLocal(currentRand)) {
				sum += currentRand;
			/* separate out odd numbers as they are added into randArrList for use later */
			} else {
				oddRandArrList.add(currentRand);
			}
		}
		System.out.println("Random ArrayList: "+randArrList.toString());
		System.out.println("Sum: "+sum);
		
		/* Remove odd elements from randArrList */
//		randArrList.removeAll(oddRandArrList); // Not working for some reason
		
		/* throws ConcurrentModificationException */
//		for (Integer randomNumber : randArrList) {
//			if (oddRandArrList.contains(randomNumber)) {
//				randArrList.remove(randomNumber);
//			}
//		}
		
		for (int i=0; i<randArrList.size(); i++) {
			if (ProgramFlowAndBooleans.isEven_noLocal(randomNumber)) {
				randArrList.remove(randomNumber);
			}
		}
		
		System.out.println("Random Arraylist of even: "+randArrList);
		System.out.println("Random Arraylist of odd: "+oddRandArrList);
		randArrList.addAll(oddRandArrList);
		System.out.println("Random ArrayList with even separated from odd: "+randArrList.toString());
		
		/* convert to int Array */
		int [] randArr = new int[randArrList.size()];
		for (int i=0; i<randArrList.size(); i++) {
			randArr[i] = randArrList.get(i);
		}
		
		System.out.println("Random Array: "+Arrays.toString(randArr));
	}

}
