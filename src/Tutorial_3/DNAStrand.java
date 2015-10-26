package Tutorial_3;

public class DNAStrand {
	
	private String dna;
	
	public DNAStrand(String dna){
//		if (isValidDNA()) {			
			this.dna = dna;
//		} else {
//			System.out.println("Error: Input must comtain only characters 'A', 'T', 'G', 'C'.");
//		}
	}
	
	public boolean isValidDNA(){
		// RegEx Explanation
		// ^: start of string, (capture group), [character set], *: 0 or more of preceding token, [^c]: not c, \\s: escaped whitespace, $ end of string
		if (dna.matches("^([ACGT]*[^\\s])$")) { 
			return true;
		} else {
			return false;
		}
	}
	
	public String complementWC() {
		// Another way to do this would be to use StringBuilder instead of converting to char arrays
		char[] dnaArray = dna.toCharArray();
		char[] complementWCArray = new char[dnaArray.length];
		for (int i=0; i<dnaArray.length; i++) {
			switch(dnaArray[i]) {
			case 'A': complementWCArray[i] = 'T'; break;
			case 'T': complementWCArray[i] = 'A'; break;
			case 'C': complementWCArray[i] = 'G'; break;
			case 'G': complementWCArray[i] = 'C'; break;
			}
		}
		return new String(complementWCArray);
	}
	
	public String palindromeWC() {
		String complementWC = complementWC();
		return new StringBuilder(complementWC).reverse().toString();
		
		// Manual method
//		int i, len = complementWC.length();
//		StringBuilder sb = new StringBuilder(len);
//		for (i = (len-1); i>=0; i--) {
//			sb.append(complementWC.charAt(i));
//		}
//		
//		return sb.toString();
	}
	
	public boolean containsSequence(String seq) {
		if (dna.contains(seq)) {
			return true;
		} else {
			return false;
		}
	}
	
	public String toString() {
		return dna;
	}
	
}
