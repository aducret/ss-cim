package input_generator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class InputGenerator {
	
	public static void generateInput(int N, int L) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter dynamicWriter = new PrintWriter("doc/examples/Dynamic" + N + ".txt", "UTF-8");
		PrintWriter staticWriter = new PrintWriter("doc/examples/Static" + N + ".txt", "UTF-8");
		
		dynamicWriter.println("0");
		staticWriter.println(N);
		staticWriter.println(L);
		
		for (int i = 0; i < N; i++) {
			staticWriter.println("0.3700 1.0000");
			dynamicWriter.println(PointGenerator.randomPointBetween(0, L));
		}
		
		staticWriter.close();
		dynamicWriter.close();
	}
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		generateInput(25000, 500);
	}
}
