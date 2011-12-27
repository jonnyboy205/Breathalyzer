import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.concurrent.LinkedBlockingDeque;

public class BFS {
	
	private int currentLevel;
	private HashSet<String> dictionary;
	private ArrayList<String> initialWords;
	private HashMap<String, ArrayList<Node>> sentenceTree;

	public BFS(String theDrunkSentence, HashSet<String> dictHash){
		dictionary = dictHash;
		
		sentenceTree = new HashMap<String, ArrayList<Node>>();
		initialWords = new ArrayList<String>();
		
		ArrayList<Node> words = parseSentence(theDrunkSentence);
			
		String currentWord = "";
		for (Node currentWordNode : words){
			currentWord = currentWordNode.getWord();
			initialWords.add(currentWord);
			sentenceTree.put(currentWord, generateSuccessors(currentWordNode));
		}
	}
	
	private ArrayList<Node> parseSentence(String sentence){
		ArrayList<Node> ret = new ArrayList<Node>();
		
		StringTokenizer st = new StringTokenizer(sentence, " ");
		while (st.hasMoreTokens()){
			String tempWord = st.nextToken();
			ret.add(new Node(tempWord, null));
		}
		return ret;
	}
	
	private ArrayList<Node> generateSuccessors(Node currentNode){
		ArrayList<Node> successors = new ArrayList<Node>();
		
		char letter = 'a';
		for (int i=-1; i<currentNode.getWord().length(); i++){
			if (i==-1){
				letter = 'a';
				while (letter <= 'z'){
					successors.add(new Node(letter + currentNode.getWord().substring(i+1, currentNode.getWord().length()), currentNode));
					letter++;
				}
			}
			else if (i>=0 || i<(currentNode.getWord().length()-1)){
				letter = 'a';
				while (letter <= 'z'){
					successors.add(new Node(currentNode.getWord().substring(0,i+1) + letter + currentNode.getWord().substring(i+1, currentNode.getWord().length()), currentNode));
					letter++;
				}
			}
			else{
				letter = 'a';
				while (letter <= 'z'){
					successors.add(new Node(currentNode.getWord().substring(0,i+1) + letter, currentNode));
					letter++;
				}
			}
		}
		
		return successors;
	}
	
//	public int run() {
//		int score = checkLevel();
//		while (score != -1) {
//			// check next successors
//			getNewSuccessors();
//			score = checkLevel();
//		}
//		return score;
//
//	}

//	private void getNewSuccessors(){
//		//iterate through the hash table
//		//get the arraylists
//		//generate successors for each of those array lists
//		//combine those successor array lists
//		//look through each of those and hash them
//	}

//	private int checkLevel() {
//		int score = 0;
//		
//		ArrayList<String> wordsToRemove = new ArrayList<String>();
//		for (String iWord: initialWords){
//			if (someSuccessorMatchesDict(iWord)){
//				wordsToRemove.add(iWord);
//				System.out.println("Some word matches.");
//			}
//		}
//		for (String rWord: wordsToRemove){
//			initialWords.remove(rWord);
//			sentenceTree.remove(rWord);
//		}
//		
//		if (initialWords.isEmpty())
//			score = 0;
//		else
//			score = -1;
//		
//		return score;
//	}
	
//	private boolean someSuccessorMatchesDict(String word){
//		boolean b = false;
//		
//		for (Node n: sentenceTree.get(word)){
//			if (dictionary.contains(n.getWord())){
//				initialWords.remove(word);
//				b = true;
//			}
//		}
//		
//		return b;
//	}
	
}
