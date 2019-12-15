
public class SimpleSetPerformanceAnalyzer {
	
	public static void main(String[] args) {
		OHSTest();
		CHSTest();
	}
	
	public static void CHSTest() {
		ClosedHashSet h = new ClosedHashSet();
		int cap = 1000;
		for(int i = 0; i < cap; i++) {
			h.add(Integer.toString(i));
		}
		for(int i = 0; i < cap; i++) {
			if(!h.contains(Integer.toString(i)))
				System.out.println("F: " + i);
		}
		
	}
	
	public static void OHSTest() {
		OpenHashSet h = new OpenHashSet();
		int cap = 10000;
		for(int i = 0; i < cap; i++) {
			h.add(Integer.toString(i));
			if(h.size() - 1 != i) {
				System.out.println("I : " + i + "\tSize: " + h.size());
				System.out.println("o");
			}
		}
		for(int i = 0; i < cap; i++) {
			if(h.add(Integer.toString(i)))
				System.out.println("F");
		}
		for(int i = 0; i < cap; i++) {
			if(!h.contains(Integer.toString(i)))
				System.out.println("FAIL");
		}
		for(int i = 0; i < cap; i++) {
			h.delete(Integer.toString(i));
			if(h.size() != 9999 - i)
				System.out.println("s");
		}
	}
}
