package filesprocessing;

import java.io.File;
import java.util.ArrayList;

import filesprocessing.errors.typetwo.InvalidUsageException;
import filesprocessing.errors.typetwo.TypeTwoException;

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
		// DIR = 0
		// file = 1

		try {
			if(args.length != 2)
				throw new InvalidUsageException(TypeTwoException.ERROR_MESSAGE
						+ "only 2 arguemnts are valid");
			String dir =  args[0];
			String path =  args[1];
			CommandFileLoader loader = new CommandFileLoader(path);
			loader.readFile();
			ArrayList<File> lst = new ArrayList<File>(getFiles(dir));	
			SectionParser p = new SectionParser(loader.getSections(), lst);
			p.parse();
		} catch (TypeTwoException e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	

}
