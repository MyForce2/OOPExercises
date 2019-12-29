package filesprocessing;

import java.util.ArrayList;

import filesprocessing.errors.typetwo.TypeTwoException;

public class DirectoryProcessor {
	
	private static final String P_1 = "C:\\Users\\nadav\\eclipse-workspace\\OOPEx5\\src\\filesprocessing";
	private static final String COMMAND_P = "C:\\Users\\nadav\\eclipse-workspace\\OOPEx5\\src\\Command Files\\";
	
	public static void main(String[] args) {
//		String path = COMMAND_P + "filter002.flt";
//		try {
//			CommandFileLoader loader = new CommandFileLoader(path);
//			loader.readFile();
//			ArrayList<Section> r = loader.getSections();
//			r.forEach((x) -> {System.out.print(x.getFilterText() + "\t" + x.getOrderText() + '\n'); });
//		} catch (TypeTwoException e) {
//			System.out.println(e.getMessage());
//		}
		String k = "NAME#VALUE#v";
		System.out.println(k.split("#").length);
	}
	
	

}
