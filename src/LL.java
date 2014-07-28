//change back to PieceCoord[]
public class LL {
	private Node first, last, current;
	
	public LL(){
		first = null;
		last = null;
	}
	
	public boolean isEmpty(){
		return first == null;
	}
	
	public void joinWith(LL newList){
		if(newList.isEmpty())
			return;
		if(this.isEmpty()){
			first = newList.getFirst();
			last = newList.getLast();
		}
		else{
			last.next = newList.getFirst();
			last = newList.getLast();
		}
	}
	
	public void insert(PieceCoord[] pc){
		Node n = new Node(pc);
		if(this.isEmpty()){
			first = n;
			last = n;
		}
		else{
			last.next = n;
			last = n;
		}
	}
	
	public Node getFirst(){
		return first;
	}
	
	public PieceCoord[] getFirstEle(){
		return first.data;
	}
	
	public Node getLast(){
		return last;
	}
	
	private class Node{
		public PieceCoord[] data;
		public Node next;
		
		public Node(PieceCoord[] board){
			data = board;
			next = null;
		}
	}
	
	public void initIterator(){
		current = first;
	}
	
	public PieceCoord[] getNextEle(){
		Node next = current.next;
		current = next;
		return next.data;
	}
	
	public boolean hasNext(){
		if(current.next != null)
			return true;
		else
			return false;
	}
	
}
