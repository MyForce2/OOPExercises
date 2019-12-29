package filesprocessing;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class DirectoryProcessor {
	
	public static void main(String[] args) {
		ArrayList<File> f = new ArrayList<File>();
		File[] arr = f.toArray(new File[]{});
	}
	
	public static void sort(int[] arr) {
		mergeSort(arr, 0, arr.length - 1);
	}
	
	public static void mergeSort(int[] arr, int leftIndexLimit, int rightIndexLimit) {
		if(leftIndexLimit < rightIndexLimit) {
			int midIndex = (leftIndexLimit + rightIndexLimit) / 2;
			mergeSort(arr, leftIndexLimit, midIndex);
			mergeSort(arr, midIndex + 1, rightIndexLimit);
			mergeArrays(arr, leftIndexLimit, midIndex, rightIndexLimit);
		}
	}
	
	public static void mergeArrays(int[] arr, int leftIndexLimit, int midIndex, int rightIndexLimit) {
		int sizeSubArrayLeft = midIndex - leftIndexLimit + 1;
		int sizeSubArrayRight = rightIndexLimit - midIndex;
		int[] leftSubArray = Arrays.copyOfRange(arr, leftIndexLimit, midIndex + 1);
		int[] rightSubArray = Arrays.copyOfRange(arr, midIndex + 1, rightIndexLimit + 1);
		int index = leftIndexLimit;
		int leftSubArrayIndex = 0, rightSubArrayIndex = 0;
		while(leftSubArrayIndex < sizeSubArrayLeft && rightSubArrayIndex < sizeSubArrayRight) {
			if(leftSubArray[leftSubArrayIndex] <= rightSubArray[rightSubArrayIndex]) {
				arr[index] = leftSubArray[leftSubArrayIndex];
				leftSubArrayIndex++;
			} else {
				arr[index] = rightSubArray[rightSubArrayIndex];
				rightSubArrayIndex++;
			}
			index++;
		}
		
		for(int i = leftSubArrayIndex; i < sizeSubArrayLeft; i++) {
			arr[index] = leftSubArray[i];
			index++;
		}
		
		for(int i = rightSubArrayIndex; i < sizeSubArrayRight; i++) {
			arr[index] = rightSubArray[i];
			index++;
		}
		
	}

}
