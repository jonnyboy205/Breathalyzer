import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class Breathalyzer {
	
	private HashSet<String> dictHash;
	private String theWordListFilename;
	private String theDrunkSentence;
	private static final boolean DEBUG = false;
	
	public Breathalyzer(String theWordListFilename, String theDrunkSentence) throws FileNotFoundException{
		this.theWordListFilename = theWordListFilename;
		this.theDrunkSentence = theDrunkSentence;
		File theWordList = new File(theWordListFilename);
		try {
			Scanner dictionary = new Scanner(theWordList);
			createDictHashSet(dictionary);
			
			if (DEBUG){
				printDictHash();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}

	private void createDictHashSet(Scanner s) {
		dictHash = new HashSet<String>();
		while(s.hasNextLine()){
			dictHash.add(s.nextLine().trim().toLowerCase());
		}
	}
	
	/**
	 * Main run method. Calculates drunk score of sentence.
	 */
	public int run(){
		//I'm thinking a breadth-first search to check all possible variations of words with 1 change, 2 changes, etc.
		//TODO
		
		BFS myBFS = new BFS(theDrunkSentence, dictHash);
		return myBFS.run();
		
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
