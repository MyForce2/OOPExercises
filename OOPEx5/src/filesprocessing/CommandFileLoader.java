package filesprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import filesprocessing.errors.typetwo.FormatException;
import filesprocessing.errors.typetwo.InvalidUsageException;
import filesprocessing.errors.typetwo.SubSectionException;
import filesprocessing.errors.typetwo.TypeTwoException;
import filesprocessing.errors.typetwo.TypeTwoIOException;

public class CommandFileLoader {
	
	private File file;
	
	private static final char END_OF_LINE = '\n';
	
	private String fileText;
	
	private static final String FILTER_SECTION = "FILTER";
	private static final String ORDER_SECTION = "ORDER";
	
	
	public CommandFileLoader(String path) throws InvalidUsageException {
		file = new File(path);
		fileText = "";
		String name = file.getName();
		String err = "File: " + name;
		if(!file.exists()) {
			System.err.println(TypeTwoException.PRINT_ERROR_MESSAGE + err + " , doesn't exist");
			throw new InvalidUsageException(TypeTwoException.ERROR_MESSAGE 
					+ err + " , doesn't exist\n");
		}
		if(!file.isFile()) {
			System.err.println(TypeTwoException.PRINT_ERROR_MESSAGE + err + " , is a directory (Not a file)");
			throw new InvalidUsageException(TypeTwoException.ERROR_MESSAGE + err + " , is a directory (Not a file)" + '\n');
		}
	}
	
	
	public void readFile() throws TypeTwoIOException {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while(line != null) {
				fileText += line + END_OF_LINE;
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException o) {
			System.err.println(TypeTwoException.PRINT_ERROR_MESSAGE + "Failed reading commands file : (" + file.getName() + ")"
							+ "IOException message: " + o.getMessage());
			throw new TypeTwoIOException(TypeTwoException.ERROR_MESSAGE + 
					"Failed reading commands file : (" + file.getName() + ")" +
					"IOException message: " + o.getMessage() + "\n");
		}
	}
	
	public ArrayList<Section> getSections() throws FormatException, SubSectionException {
		ArrayList<Section> sections = new ArrayList<Section>();
		String[] lines = fileText.split("\n");
		int i = 0;
		while(i < lines.length) {
			Section section = new Section();
			String filterSection = lines[i];
			if(filterSection.equals(ORDER_SECTION)) {
				String err = TypeTwoException.ERROR_MESSAGE + "Missing FILTER section";
				System.err.println(TypeTwoException.PRINT_ERROR_MESSAGE + err);
				throw new FormatException(TypeTwoException.ERROR_MESSAGE + err);
			}
			if(!filterSection.equals(FILTER_SECTION)) {
				String err = TypeTwoException.ERROR_MESSAGE + "Invalid section name at line : " + (i + 1);
				System.err.println(TypeTwoException.PRINT_ERROR_MESSAGE + err);
				throw new SubSectionException(TypeTwoException.ERROR_MESSAGE + err);
			}
			i++;
			if(i >= lines.length) {
				sections.add(section);
				break;
			}
			String filterContent = lines[i];
			section.setFilterText(filterContent);
			i++;
			if(i >= lines.length) {
				sections.add(section);
				break;
			}
			String orderSection = lines[i];
			if(orderSection.equals(FILTER_SECTION)) {
				String err = "Missing ORDER section";
				System.err.println(TypeTwoException.PRINT_ERROR_MESSAGE + err);
				throw new FormatException(TypeTwoException.ERROR_MESSAGE + err);
			}
			if(!orderSection.equals(ORDER_SECTION)) {
				String err = "Invalid section name at line : " + (i + 1);
				System.err.println(TypeTwoException.PRINT_ERROR_MESSAGE + err);
				throw new SubSectionException(TypeTwoException.ERROR_MESSAGE + err);
			}
			i++;
			if(i >= lines.length) {
				section.setOrderText("abs");
				sections.add(section);
				break;
			}
			section.setOrderText(lines[i]);
			sections.add(section);
			i++;
		}
		return sections;
	}
}
