import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.LinkedBlockingDeque;

public class BFS {
	
	private LinkedBlockingDeque<Node> successors;

	public BFS(String theDrunkSentence){
		//first parse the sentence for the n words in it
		//each of those words will be the root of a tree
		//each will have children with the states coming from them
		//with the new possible words doing all of those variations.
		
		successors = new LinkedBlockingDeque<Node>();
		
		ArrayList<Node> words = parseSentence(theDrunkSentence);
		for (Node currentWordNode : words){
			generateSuccessors(currentWordNode);
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
	
	private void generateSuccessors(Node currentNode){
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
	}
	
//	public int getScore(){
//		
//	}
	
	public void printSuccessors(){
		System.out.println("Successors:");
		System.out.println("------------");
		while (!successors.isEmpty()){
			System.out.println(successors.poll().getWord());
		}
	}
}
