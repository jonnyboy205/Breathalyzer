import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BFS {
	
	private HashSet<String> dictionary;
	private ArrayList<Node> initialWords;
	private static final boolean DEBUG = false;

	public BFS(String theDrunkSentence, HashSet<String> dictHash){
		dictionary = dictHash;
		
		initialWords = parseSentence(theDrunkSentence);
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
		
		String copyWord = currentNode.getWord();
	
		//TODO extract those methods
		
		//remove any single letter
		char[] letters = copyWord.toCharArray();
		String temp = copyWord;
		for (int i=0; i<letters.length; i++){
			temp = copyWord;
			if (i==0)
				temp = temp.substring(i+1, letters.length);			
			else if (i>=1 || i<(letters.length-1))
				temp = temp.substring(0, i) + temp.substring(i+1, letters.length);
			else // i==letters.length-1
				temp = temp.substring(0, i);
			successors.add(new Node(temp, currentNode));
		}
		
		//replacing any single letter
		char letter = 'a';
		for (int i=0; i<letters.length; i++){
			temp = copyWord;
			if (i==0){
				letter = 'a';
				while (letter <= 'z'){
					temp = temp.substring(i+1, letters.length);
					String letterStr = String.valueOf(letter);
					temp = letterStr.concat(temp);
					successors.add(new Node(temp, currentNode));
					letter++;
				}
			}
			else if (i>=1 || i<(letters.length-1)){
				letter = 'a';
				while (letter <= 'z'){
					String letterStr = String.valueOf(letter);
					if (!letterStr.equals(temp.substring(i, i+1))){
						temp = temp.substring(0, i) + letterStr + temp.substring(i+1, letters.length);
						successors.add(new Node(temp, currentNode));
					}
					letter++;
				}
			}
			else{ // i==letters.length-1
				letter = 'a';
				while (letter <= 'z'){
					String letterStr = String.valueOf(letter);
					if (!letterStr.equals(temp.substring(i, i+1))){
						temp = temp.substring(0, i) + letterStr;
						successors.add(new Node(temp, currentNode));
					}
					letter++;
				}				
			}
		}
		
		//adding a letter in any position
		for (int i=-1; i<currentNode.getWord().length(); i++){
			if (i == -1){
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
	
	private ArrayList<Node> generateSuccessors(ArrayList<Node> previousSuccessors){
		ArrayList<Node> ret = new ArrayList<Node>();
		
		for (Node n: previousSuccessors){
			ret.addAll(generateSuccessors(n));
		}
		
		return ret;
	}

	/**
	 * 
	 * @param a, depth level in breadth-first search tree
	 * @return score
	 */
	private int recursiveCalcScore(int a, ArrayList<Node> successors){
		if (a==0)
			return 0;
		
		for (Node n: successors){
			if (dictionary.contains(n.getWord())){
				if (DEBUG)
					System.out.println(n.getWord());
				
				return a;
			}
		}
		
		return recursiveCalcScore(a++, generateSuccessors(successors));
		
	}

	public int run(){
		int score = 0;
		
		for (Node iWordNode: initialWords){
			score += recursiveCalcScore(1, generateSuccessors(iWordNode));
		}
		
		return score;
	}
	
}