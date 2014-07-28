
public class LLTest {
	private Node first, last, current;

	public LLTest(){
		first = null;
		last = null;
	}

	public boolean isEmpty(){
		return first == null;
	}

	public void joinWith(LLTest newList){
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

	public void insert(int pc){
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

	public int getFirstEle(){
		return first.data;
	}

	public Node getLast(){
		return last;
	}

	private class Node{
		public int data;
		public Node next;

		public Node(int i){
			data = i;
			next = null;
		}
	}

	public void initIterator(){
		current = first;
	}

	public int getNextEle(){
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
