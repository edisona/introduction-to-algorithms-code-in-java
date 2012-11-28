package chp2.algo3_divide_conquer.mergeSort.exercise4;

import java.util.Arrays;

public class InsertionSortRecursive {

	public static void sort(int[] array) {
		insertionSort(array, 0, array.length - 1);
	}

	/**
	 * Sort an array using recursive insertion sort
	 * @param array array to sort
	 */
	private static void insertionSort(int[] array, int begin, int end) {
		int length = end - begin + 1;
		if (length > 1) {
			insertionSort(array, begin, end - 1);
			int key = array[end];
			int i = end - 1;
			while (i >= begin && key < array[i]) {
				array[i + 1] = array[i];
				--i;
			}
			array[i + 1] = key;
		}
	}



	public static void main(String[] args) {
		int[] array = {1, 3, 2, 8, 7, 5, 4, 6, 3, 8, 10, 9, 6};
//		int[] array = {1};
		System.out.println("Before merge sort: " + Arrays.toString(array));
		sort(array);
		System.out.println("After merge sort: " + Arrays.toString(array));
	}

}
