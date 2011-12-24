import java.util.AbstractQueue;
import java.util.StringTokenizer;

public class BFS {
	
	Node root;
	AbstractQueue<Node> successors;

	public BFS(String theDrunkSentence){
		//first parse the sentence for the n words in it
		//each of those words will be the root of a tree
		//each will have children with the states coming from them
		//with the new possible words doing all of those variations.
		
		//successors = new AbstractQueue<Node>();
		
		parseSentence(theDrunkSentence);
		expandRoot();
	}
	
	private void parseSentence(String sentence){
		//TODO
		//let's go for just the first word for now
		StringTokenizer st = new StringTokenizer(sentence, " ");
		String firstWord = st.nextToken();
		
		root = new Node(firstWord, null);
	}
	
	private void expandRoot(){
		char letter = 'a';
		while (letter <= 'z'){
			successors.add(new Node(letter + root.getWord(), root));
			letter++;
		}
	}
	
	public void printSuccessors(){
		System.out.println("Successors:");
		System.out.println("------------");
		while (!successors.isEmpty()){
			System.out.println(successors.poll());
		}
	}
}
