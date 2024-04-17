import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {

		InputStreamReader input = new InputStreamReader(System.in); 
        BufferedReader reader = new BufferedReader(input); 
        System.out.println("Please enter the input:");
        String name = reader.readLine();
        System.out.println("You entered: ");
        System.out.println(name);       
		
	}

}
