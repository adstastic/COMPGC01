import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandling {

	public static void main(String[] args) {
		for (String filepath : args) {
			try {
//				BufferedReader br = new BufferedReader(new FileReader(new File(filepath)));
				Path p = Paths.get(filepath);
				String fileName = p.getFileName().toString();
				System.out.println(fileName);
				LineNumberReader  lnr = new LineNumberReader(new FileReader(new File(filepath)));
				lnr.skip(Long.MAX_VALUE);
				System.out.println("Lines: "+lnr.getLineNumber()+1);
				lnr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

}
