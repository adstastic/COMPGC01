package Tutorial_3;
import java.util.Calendar;

public class CreditCard {
	
	private int expiryMonth;
	private int expiryYear;
	private String firstName;
	private String lastName;
	private String ccNumber;
	
	public CreditCard(int expiryMonth, int expiryYear, String firstName, String lastName, String ccNumber){
		this.expiryMonth = expiryMonth;
		this.expiryYear = expiryYear;
		this.firstName = firstName;
		this.lastName = lastName;
		this.ccNumber = ccNumber;
	}
	
	public String formatExpiryDate() {
		return expiryMonth+"/"+expiryYear;
	}
	
	public String formatFullName() {
		return firstName+" "+lastName;
	}
	
	public String formatCCNumber() {
		String[] ccSplit = ccNumber.split("^[0-9]{4}$");
		StringBuilder ccFormat = new StringBuilder();
		for (String block : ccSplit) {
			ccFormat.append(block+" ");
		}
		return ccFormat.toString();
	}
	
	public boolean isValid() {
		Calendar now = Calendar.getInstance();
		if (expiryYear > now.get(Calendar.YEAR)) {
			return true;
		} else if (expiryYear == now.get(Calendar.YEAR)) { 
			if (expiryMonth > now.get(Calendar.MONTH)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public String toString() {
		return "Number: "+ccNumber+"\nExpiry Date: "+formatExpiryDate()+"\nAccount Holder: "+formatFullName()+"\nIs valid: "+isValid();
	}
}
