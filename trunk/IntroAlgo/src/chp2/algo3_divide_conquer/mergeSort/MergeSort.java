package chp2.algo3_divide_conquer.mergeSort;

import java.util.Arrays;

public class MergeSort {

	/**
	 * Sort <code>array</code> using merge sort.
	 * @param array array to sort
	 * @param from from where we sort
	 * @param to to where we sort
	 */
	public static void mergeSortSentinel(int[] array, int from, int to) {
		if (from < to) {
			int middle = (from + to) / 2;
			mergeSortSentinel(array, from, middle);
			mergeSortSentinel(array, middle + 1, to);
			mergeSentinel(array, from, middle, to);
		}
	}

	/**
	 * Merge array[p..q] and array[q+1..r] into array itself, providing that array[p..q] and array[q+1..r] are ordered.
	 * @param array
	 * @param p
	 * @param q
	 * @param r
	 */
	private static void mergeSentinel(int array[], int p, int q, int r) {
		// length of array[p..q]
		int length1 = q - p + 1;
		// length of array[q+1..r]
		int length2 = r - q;

		// one more place for sentinel
		int[] left = new int[length1 + 1];
		int[] right = new int[length2 + 1];

		// copy array[p..q] to left
		for (int i = 0; i < length1; ++i) {
			left[i] = array[i + p];
		}
		// put the sentinel at the end of left
		left[length1] = Integer.MAX_VALUE;

		// copy array[q+1..r] to right
		for (int i = 0; i < length2; ++i) {
			right[i] = array[i + q + 1];
		}
		//  put the sentinel at the end of right
		right[length2] = Integer.MAX_VALUE;

		// Merge the arrays left and right into array[p..r]
		for (int i = 0, j = 0, k = p; k <= r; ++k) {
			// left[i] is the smallest element
			if (left[i] <= right[j]) {
				array[k] = left[i];
				// move i to the next index
				++i;
			}
			// right[j] is the smallest element
			else {
				array[k] = right[j];
				// move j to the next index
				++j;
			}
		}
	}

	/**
	 * Sort <code>array</code> using merge sort.
	 * @param array array to sort
	 * @param from from where we sort
	 * @param to to where we sort
	 */
	public static void mergeSort(int[] array, int from, int to) {
		if (from < to) {
			int middle = (from + to) / 2;
			mergeSort(array, from, middle);
			mergeSort(array, middle + 1, to);
			merge(array, from, middle, to);
		}
	}



	/**
	 * Exercise 2.3-2
	 * @param array
	 * @param p
	 * @param q
	 * @param r
	 */
	public static void merge(int[] array, int p, int q, int r) {
		// length of array[p..q]
		int length1 = q - p + 1;
		// length of array[q+1..r]
		int length2 = r - q;

		int[] left = new int[length1];
		int[] right = new int[length2];

		// copy array[p..q] to left
		for (int i = 0; i < length1; ++i) {
			left[i] = array[i + p];
		}

		// copy array[q+1..r] to right
		for (int i = 0; i < length2; ++i) {
			right[i] = array[i + q + 1];
		}

		// Merge the arrays left and right into array[p..r]
		int i = 0;
		int j = 0;
		int k = p;
		while (i < length1 && j < length2) {
			// left[i] is the smallest element
			if (left[i] <= right[j]) {
				array[k] = left[i];
				// move i to the next index
				++i;
			}
			// right[j] is the smallest element
			else {
				array[k] = right[j];
				// move j to the next index
				++j;
			}
			++k;
		}

		// i >= length1 or j >= length2

		// while array left still has elements
		while (i < length1) {
			array[k] = left[i];
			++k;
			++i;
		}

		// while array right still has elements
		while (j < length2) {
			array[k] = right[j];
			++k;
			++j;
		}

	}

	private static void testMerge() {
//		int[] array = {1, 3, 8, 9, 2, 5, 9, 18};
		int[] array = {1, 3, 8, 9, 2, 5, 9};
		System.out.println("Before merge: " + Arrays.toString(array));
//		mergeSentinel(array, 0, 3, 7);
		mergeSentinel(array, 0, 3, 6);
		System.out.println("After merge: " + Arrays.toString(array));
	}

	private static void testMergeSortSentinel() {
		int[] array = {1, 3, 2, 8, 7, 5, 4, 6, 3, 8, 10, 9, 6};
		System.out.println("Before merge sort: " + Arrays.toString(array));
		mergeSortSentinel(array, 0, array.length - 1);
		System.out.println("After merge sort: " + Arrays.toString(array));
	}

	private static void testMergeSort() {
		int[] array = {1, 3, 2, 8, 7, 5, 4, 6, 3, 8, 10, 9, 6};
		System.out.println("Before merge sort: " + Arrays.toString(array));
		mergeSort(array, 0, array.length - 1);
		System.out.println("After merge sort: " + Arrays.toString(array));
	}

	public static void main(String[] args) {
//		testMerge();
//		testMergeSortSentinel();
		testMergeSort();
	}
}
