import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class BreathalyzerTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		File inputFile = new File(args[0]);
		Scanner input = new Scanner(inputFile);
		
		String drunkSentence = input.nextLine().trim();
		try {
			Breathalyzer myBreathalyzer = new Breathalyzer("./var/tmp/twl06.txt", 
					//"tihs sententcnes iss nout varrry goud");
					//"tihs");
					drunkSentence);
			System.out.println(myBreathalyzer.run());
		} catch (FileNotFoundException e) {
			System.out.println("File can't be found.");
		} catch (Exception e){
			System.out.println("Something random.");
			e.printStackTrace();
		}

	}

}
