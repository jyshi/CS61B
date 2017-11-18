import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Nuke2 {

	public static void main(String[] args) throws Exception {
	    BufferedReader keyboard;
	    String inputLine;

	    keyboard = new BufferedReader(new InputStreamReader(System.in));
	    inputLine = keyboard.readLine();
	    String outputLine = inputLine.charAt(0) + inputLine.substring(2);
	    System.out.print(outputLine);
	}
}
