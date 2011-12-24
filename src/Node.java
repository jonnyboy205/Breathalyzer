import java.util.Queue;

public class Node {

	private String word;
	private Node parent;
	
	public Node(String word, Node parent){
		this.word = word;
		this.parent = parent;
	}
	
	public String getWord(){
		return word;
	}
	
}
