package chp2.algo1_InsertionSort;

import java.util.Arrays;

public class InsertionSortTest {

	/**
	 *
	 * @param array
	 *
	 * Peusocode: (Array index begins with 1)
	 * INSERTION-SORT(A)
	 *	1 for j = 2 to A.length
	 *	2    key = A[j]
	 *	3    //Insert A[j] into the sorted sequence A[1..j - 1].
	 *	4    i = j - 1
	 *	5    while i > 0 and A[i] > key
	 *	6        A[i + 1] = A[i]
	 *	7        i = i - 1
	 *	8    A[i + 1] = key
	 */
	public static void insertSort(int[] array) {
		for (int j = 1; j < array.length; ++j) {
			int key = array[j];
			//Insert A[j] into the sorted sequence A[0..j - 1].
			int i = j - 1;
			while (i >= 0 && key < array[i]) {
				array[i + 1] = array[i];
				--i;
			}
			array[i + 1] = key;
			System.out.println("Step " + j + ": " + Arrays.toString(array));
		}
	}

	public static void main(String[] args) {
		int[] a = {5, 3, 4, 2, 3, 8, 1, 2};
		System.out.println("Before sort: " + Arrays.toString(a));
		insertSort(a);
		System.out.println("After sort: " + Arrays.toString(a));
	}


}
