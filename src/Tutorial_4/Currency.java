package Tutorial_4;

public class Currency {
	
	String code;
	Double rate;
	
	public Currency(String code, Double rate) {
		this.code = code;
		this.rate = rate;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public Double getRate() {
		return this.rate;
	}

}
