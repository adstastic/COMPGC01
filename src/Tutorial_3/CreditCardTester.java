package Tutorial_3;

public class CreditCardTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CreditCard c1 = new CreditCard(04, 2016, "Bob", "Jones", "1234567890123456");
		CreditCard c2 = new CreditCard(10, 2015, "Bob", "Jones", "1234567890123456");
		
		System.out.println(c1.toString());
		System.out.println(c2.toString());
	}

}
