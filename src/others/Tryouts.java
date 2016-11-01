package others;

import java.io.IOException;

public class Tryouts {
	public static void main (String[]args) throws IOException{
		String s = "";
		String a = "Hello \n";
		double b = 3.14;
		s = s + a + b;
		System.out.println(s);
		FileReaderWriter.writeFile("tryouts.txt", s);
	}
}
