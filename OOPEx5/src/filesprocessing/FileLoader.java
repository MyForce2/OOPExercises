package filesprocessing;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import filesprocessing.comparators.FileSorter;
import filesprocessing.comparators.SizeFileComparator;

public class FileLoader {
	
	/**
	 * The path of the directory
	 */
	private final String sourceDirPath;
	
	/**
	 * The path of the commands file
	 */
	private final String commandFilePath;
	
	/**
	 * The directory as a java file object
	 */
	private File directory;
	
	/**
	 * The commands file as a java file object
	 */
	private File commandsFile;
	
	/**
	 * All of the actual files in the folder
	 */
	private ArrayList<File> filesInDirectory;
	
	
	
	public FileLoader(String directory, String commandFilePath) {
		this.sourceDirPath = directory;
		this.commandFilePath = commandFilePath;
		filesInDirectory = new ArrayList<File>();
		init();
	}
	
	private void init() {
		directory = new File(sourceDirPath);
		File[] itemsInDirectory = directory.listFiles();
		for(File file : itemsInDirectory) {
			if(file.isFile())
				filesInDirectory.add(file);
		}
		filesInDirectory.forEach((x) -> { System.out.println(x.getName()); });;
		FileSorter sorter = new FileSorter(filesInDirectory, new SizeFileComparator());
		sorter.sort();
		System.out.println("---------------");
		filesInDirectory = new ArrayList<File>(Arrays.asList(sorter.getFiles()));
		filesInDirectory.forEach((x) -> { System.out.println(x.getName()); });;
	}

}
