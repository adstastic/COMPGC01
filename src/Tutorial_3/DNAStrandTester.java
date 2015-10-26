package Tutorial_3;

public class DNAStrandTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DNAStrand DNA1 = new DNAStrand("ATCG");
		DNAStrand DNA2 = new DNAStrand("ATCG ");
		DNAStrand notEvenTrying = new DNAStrand("HELLO WORLD");
		summarise(DNA1);
		summarise(DNA2);
		summarise(notEvenTrying);
	}
	
	public static void summarise(DNAStrand dna) {
		System.out.println("\n### DNA ###");
		System.out.println("Original DNA Sequence: \"" + dna+"\""); // Added "" so whitespace can be seen
		
		if (dna.isValidDNA()) {
			System.out.println("Is valid"); 
			System.out.println("Complement: " + dna.complementWC()); 
			System.out.println("WC Palindrome: " + dna.palindromeWC());
		} else {
			System.out.println("Not Valid DNA");
		}
	}

}
