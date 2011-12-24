import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class Breathalyzer {
	
	private HashSet<String> dictHash;
	private String theWordListFilename;
	private static final boolean DEBUG = true;
	
	public Breathalyzer(String theWordListFilename) throws Exception{
		this.theWordListFilename = theWordListFilename;
		File theWordList = new File(theWordListFilename);
		try {
			Scanner dictionary = new Scanner(theWordList);
			createDictHashSet(dictionary);
			
			if (DEBUG)
				printDictHash();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	private void createDictHashSet(Scanner s) {
		dictHash = new HashSet<String>();
		while(s.hasNextLine()){
			dictHash.add(s.nextLine().trim());
		}
	}
	
	private void printDictHash(){
		System.out.println("Dictionary according to " + theWordListFilename + ":");
		System.out.println("----------------------------------------------------");
		
		Iterator<String> dictHashIterator = dictHash.iterator();
		while (dictHashIterator.hasNext()){
			System.out.println(dictHashIterator.next());
		}
		
		
	}
}
