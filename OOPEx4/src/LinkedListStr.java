import java.util.LinkedList;

public class LinkedListStr {
	
	private LinkedList<String> list;
	
	public LinkedListStr() {
		list = null;
	}
	
	public LinkedListStr(LinkedList<String> list) {
		this.list = list;
	}
	
	public LinkedList<String> get() {
		return list;
	}
	
	public void set(LinkedList<String> list) {
		this.list = list;
	}

}
