	package filesprocessing.comparators;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

/**
 * A class used to sort a collection / array of files using a files comparator
 * The files are sorted using a merge sort, should be used for all utilities needed
 * during the order section
 */
public class FileSorter {
	
	/**
	 * The underlying files which will be sorted
	 */
	private File[] files;
	
	/**
	 * The comparator used when sorting the underlying files array
	 */
	private Comparator<File> cmp;
	
	
	/**
	 * An empty array of the File type, used when converting a 
	 * collection to an array
	 */
	private static final File[] FILE = new File[]{};
	
	/**
	 * A FileSorter constructor
	 * @param files The files to be sorted
	 * @param cmp The comparator used when sorting the files
	 */
	public FileSorter(File[] files, Comparator<File> cmp) {
		this.files = files;
		this.cmp = cmp;
	}
	
	/**
	 * A FileSorter constructor
	 * @param files A collections transformed to a file array
	 * @param cmp The comparator used when sorting the files
	 */
	public FileSorter(Collection<File> files, Comparator<File> cmp) {
		this.files = files.toArray(FILE);
		this.cmp = cmp;
	}
	
	/**
	 * Sorts the underlying files array using the comparator specified in
	 * the constructor 
	 */
	public void sort() {
		if(files == null)
			return;
		if(cmp == null)
			return;
		if(files.length < 2)
			return;
		mergeSort(0, files.length - 1);
	}
	
	/**
	 * A merge sort function, sorts the array from smallest to 
	 * largest according to the comparator
	 * specified in the constructor
	 * @param leftIndexLimit The left index boundary of the files 
	 * array (sub array index 0)
	 * @param rightIndexLimit The right index boundary of the files 
	 * array (sub array index length - 1)
	 */
	private void mergeSort(int leftIndexLimit, int rightIndexLimit) {
		if(leftIndexLimit >= rightIndexLimit)
			return;
		int midIndex = (leftIndexLimit + rightIndexLimit) / 2;
		mergeSort(leftIndexLimit, midIndex);
		mergeSort(midIndex + 1, rightIndexLimit);
		mergeArrays(leftIndexLimit, midIndex, rightIndexLimit);
	}
	
	/**
	 * Merges two sorted array into the files array
	 * @param leftIndexLimit The left index boundary of the files array 
	 * (sub array left index 0)
	 * @param midIndex Middle of the current sub array 
	 * (sub array left right index, sub array right left index)
	 * @param rightIndexLimit The right index boundary of the files array
	 * (sub array right right index)
	 */
	private void mergeArrays(int leftIndexLimit, int midIndex, 
			int rightIndexLimit) {
		int sizeSubArrayLeft = midIndex - leftIndexLimit + 1;
		int sizeSubArrayRight = rightIndexLimit - midIndex;
		
		File[] leftSubArray = Arrays.copyOfRange(files, leftIndexLimit, 
				midIndex + 1);
		File[] rightSubArray = Arrays.copyOfRange(files, midIndex + 1, 
				rightIndexLimit + 1);
		int index = leftIndexLimit;
		int leftSubArrayIndex = 0, rightSubArrayIndex = 0;
		
		// Actual merging of the arrays, sorted merge
		while(leftSubArrayIndex < sizeSubArrayLeft && 
				rightSubArrayIndex < sizeSubArrayRight) {
			File leftVal = leftSubArray[leftSubArrayIndex];
			File rightVal = rightSubArray[rightSubArrayIndex];
			int result = cmp.compare(leftVal, rightVal);
			if(result <= 0) {
				files[index] = leftVal;
				leftSubArrayIndex++;
			} else {
				files[index] = rightVal;
				rightSubArrayIndex++;
			}
			index++;
		}
		
		for(int i = leftSubArrayIndex; i < sizeSubArrayLeft; i++) { 
			files[index] = leftSubArray[i];
			index++;
		}
		
		for(int i = rightSubArrayIndex; i < sizeSubArrayRight; i++) {
			files[index] = rightSubArray[i];
			index++;
		}
	}
	
	/**
	 * @return A reversed shallow copy of the array stored in the file sorter
	 */
	public File[] reverse() {
		File[] arr = new File[files.length];
		for(int i = files.length - 1; i > -1; i--) 
			arr[i] = files[files.length - 1 - i];
		return arr;
	}
	
	/**
	 * @param cmp sets this comparator to the given comparator
	 */
	public void setComparator(Comparator<File> cmp) {
		this.cmp = cmp;
	}
	
	/**
	 * @return The comparator used when sorting the underlying given array
	 */
	public Comparator<File> getComparator() {
		return cmp;
	}
	
	/**
	 * @return The underlying file array contained in this sorter
	 */
	public File[] getFiles() {
		return files;
	}
	
}
