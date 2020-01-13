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

/**
 * A Command file loader class, loads and validates a commands file
 * the parses the commands file into a list of section
 */
public class CommandFileLoader {
	
	/**
	 * Command file object
	 */
	private File file;
	
	/**
	 * End of line character
	 */
	private static final char END_OF_LINE = '\n';
	
	/**
	 * The text of the file
	 */
	private String fileText;
	
	
	/**
	 * The name of a valid FILTER section
	 */
	private static final String FILTER_SECTION = "FILTER";
	
	/**
	 * The name of a valid ORDER section
	 */
	private static final String ORDER_SECTION = "ORDER";
	
	
	/**
	 * A constructor for the CommandFileLoader class
	 * @param path The path of the commands file
	 * @throws InvalidUsageException in the case the file doesn't exists
	 * or the file isn't a file (e.g a directory) an InvalidUsageException is thrown
	 * specifiying the problem
	 */
	public CommandFileLoader(String path) throws InvalidUsageException {
		file = new File(path);
		fileText = "";
		String name = file.getName();
		String err = "File: " + name;
		if(!file.exists()) {
			throw new InvalidUsageException(TypeTwoException.PRINT_ERROR_MESSAGE 
					+ err + " , doesn't exist\n");
		}
		if(!file.isFile()) {
			throw new InvalidUsageException(TypeTwoException.PRINT_ERROR_MESSAGE 
					+ err + " , is a directory (Not a file)" + '\n');
		}
	}
	
	
	/**
	 * Reads the file into the fileText field
	 * @throws TypeTwoIOException In the case any IO exception occurs while
	 * reading the file a TypeTwoIOException is thrown
	 */
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
			throw new TypeTwoIOException(TypeTwoException.PRINT_ERROR_MESSAGE + 
					"Failed reading commands file : (" + file.getName() + ")" +
					"IOException message: " + o.getMessage() + "\n");
		}
	}
	
	/**
	 * @return An ArrayList of sections (see Section object)
	 * containing the valid data for each section, the list should be parsed
	 * using the SectionParser class
	 * @throws FormatException In the case there is a problem with the file Format (e.g
	 * missing FILTER section) a FormatException is thrown
	 * @throws SubSectionException In the case there is a problem with section name (e.g
	 * order instead of ORDER) a SubSectionException is thrown
	 */
	public ArrayList<Section> getSections() throws FormatException, SubSectionException {
		ArrayList<Section> sections = new ArrayList<Section>();
		String[] lines = fileText.split("\n");
		int i = 0;
		while(i < lines.length) {
			Section section = new Section(i + 1);
			String filterSection = lines[i];
			if(filterSection.equals(ORDER_SECTION)) {
				String err = TypeTwoException.PRINT_ERROR_MESSAGE 
						+ "Missing FILTER section";
				throw new FormatException(TypeTwoException.ERROR_MESSAGE + err);
			}
			if(!filterSection.equals(FILTER_SECTION)) {
				String err = "Invalid section name at line : " + (i + 1);
				throw new SubSectionException(TypeTwoException.ERROR_MESSAGE + err);
			}
			i++;
			if(i >= lines.length) {
				sections.add(section);
				throw new FormatException(TypeTwoException.ERROR_MESSAGE 
						+ "Missing ORDER section");
			}
			String filterContent = lines[i];
			section.setFilterText(filterContent);
			i++;
			if(i >= lines.length) {
				sections.add(section);
				throw new FormatException(TypeTwoException.ERROR_MESSAGE 
						+ "Missing ORDER section");
			}
			String orderSection = lines[i];
			if(orderSection.equals(FILTER_SECTION)) {
				String err = "Missing ORDER section";
				throw new FormatException(TypeTwoException.ERROR_MESSAGE + err);
			}
			if(!orderSection.equals(ORDER_SECTION)) {
				String err = "Invalid section name at line : " + (i + 1);
				throw new SubSectionException(TypeTwoException.ERROR_MESSAGE + err);
			}
			i++;
			if(i >= lines.length) {
				section.setOrderText("abs");
				sections.add(section);
				break;
			}
			if(lines[i].equals(FILTER_SECTION)) {
				// Missing order content
				section.setOrderText("abs");
				sections.add(section);
				continue;
			}
			section.setOrderText(lines[i]);
			sections.add(section);
			i++;
		}
		return sections;
	}
}
