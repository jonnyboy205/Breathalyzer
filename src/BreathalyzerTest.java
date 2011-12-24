import java.io.FileNotFoundException;


public class BreathalyzerTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		try {
			/*Breathalyzer myBreathalyzer = */new Breathalyzer("./var/tmp/twl06.txt", 
					"tihs sententcnes iss nout varrry goud");
		} catch (FileNotFoundException e) {
			System.out.println("File can't be found.");
		} catch (Exception e){
			System.out.println("Something random.");
			e.printStackTrace();
		}

	}

}
