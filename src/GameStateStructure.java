import java.util.LinkedList;
import java.util.Queue;
//add last pointer?
public class GameStateStructure {
	private ViewTree vt = new ViewTree();
	private Queue<Node> queue = new LinkedList<Node>();
    private Node root;
    private Node curState;
    
    private class Node{
        private PieceCoord[] coords;
        private Node parent;
        private char name;
        private LinkedList<Node> children;
        
        public Node(PieceCoord[] pc){
        	coords = pc;
        	parent = null;
        	children = new LinkedList<Node>();
        }
    }
    
    //update curState somehow
    public void addChild(PieceCoord[] pc, boolean noMoreMoves){
    	Node n = new Node(pc);
    	if(root == null){
    		root = n;
    		root.name = 'r';
    		curState = root;
    	}
    	else{
    		n.parent = curState;
    		curState.children.add(n);
    	}
    	if(noMoreMoves){
    		queue.add(n);
    		curState = queue.remove();
    	}
    	else{
    		queue.add(n);
    	}
    }
    
    /*
     * if(no moves left for curState){
     * 	curState = curState.parent;
     *	
     */
}