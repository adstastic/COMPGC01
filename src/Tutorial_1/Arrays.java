package Tutorial_1;

public class Arrays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numbers[] = new int [20];
		numbers[0] = 1;
		StringBuilder numbersToString = new StringBuilder();
		
		for (int i = 1; i < numbers.length; i++) {
			numbers[i] = numbers[i-1]*2;
			numbersToString.append(numbers[i] + " ");
		}
		
		float numbersMean[] = new float [20];
		numbersMean[0] = numbers[0];
		numbersMean[19] = numbers[19];
		StringBuilder numbersMeanToString = new StringBuilder();
		
		for (int i = 1; i < numbers.length-1; i++) {
			numbersMean[i] = ( numbers[i-1] + numbers[i+1] ) / 2;
			numbersMeanToString.append(numbersMean[i] + " ");
		}
		
		System.out.println(numbersToString.toString());
		System.out.println(numbersMeanToString.toString());
	}

}
