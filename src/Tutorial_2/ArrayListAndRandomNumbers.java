package Tutorial_2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ArrayListAndRandomNumbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random random = new Random();
		ArrayList<Integer> randArrList= new ArrayList<Integer>();
		ArrayList<Integer> oddRandArrList= new ArrayList<Integer>();
		int sum = 0;
		for (int i=0; i<100; i++) {
			int currentRand = random.nextInt(100);
			randArrList.add(random.nextInt(100));
			if (ProgramFlowAndBooleans.isEven_noLocal(currentRand)) {
				sum += currentRand;
			} else {
				oddRandArrList.add(currentRand);
			}
		}
		System.out.println("Random ArrayList: "+randArrList.toString());
		System.out.println("Sum: "+sum);
		randArrList.removeAll(oddRandArrList);
		System.out.println("Random Arraylist of even: "+randArrList);
		System.out.println("Random Arraylist of odd: "+oddRandArrList);
		randArrList.addAll(oddRandArrList);
		System.out.println("Random ArrayList with even separated from odd: "+randArrList.toString());
		int [] randArr = new int[randArrList.size()];
		for (int i=0; i<randArrList.size(); i++) {
			randArr[i] = randArrList.get(i);
		}
		System.out.println("Random Array: "+Arrays.toString(randArr));
	}

}
