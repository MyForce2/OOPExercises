package filesprocessing;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import filesprocessing.errors.typetwo.InvalidUsageException;
import filesprocessing.errors.typetwo.TypeTwoException;
import filesprocessing.errors.typetwo.TypeTwoIOException;

public class DirectoryProcessor {
	
	/**
	 * Loads all of the files in the given directory to list
	 * @param directory The given directory
	 * @return An arraylist containing only the files in the given directory
	 * @throws InvalidUsageException If this directory doesn't exist or this directory
	 * isn't a file an InvalidUsageExceptio is thrown
	 */
	public static ArrayList<File> getFiles(String directory) throws InvalidUsageException {
		File dir = new File(directory);
		ArrayList<File> files = new ArrayList<File>();
		File[] inDirectory = dir.listFiles();
		for(File file : inDirectory) {
			if(file.isFile())
				files.add(file);
		}
		return files;
	}
	
	
	public static void main(String[] args) {
		try {
			if(args.length != 2)
				throw new InvalidUsageException(TypeTwoException.ERROR_MESSAGE
						+ "only 2 arguemnts are valid");
			String path = args[0].startsWith("\"") ? args[0].substring
					(1, args[0].length() - 1) : args[0];
			String dir = args[1].startsWith("\"") ? args[1].substring
					(1, args[1].length() - 1) : args[1];
			CommandFileLoader loader = new CommandFileLoader(path);
			loader.readFile();
			ArrayList<File> lst = new ArrayList<File>(getFiles(dir));	
			SectionParser p = new SectionParser(loader.getSections(), lst);
			p.parse();
		} catch (TypeTwoException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	

}
