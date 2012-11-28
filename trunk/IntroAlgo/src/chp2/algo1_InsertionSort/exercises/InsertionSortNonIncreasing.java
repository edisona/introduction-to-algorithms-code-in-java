package chp2.algo1_InsertionSort.exercises;

import java.util.Arrays;

public class InsertionSortNonIncreasing {

	public static void insertionSortNonIncreasing(int[] array) {
		for (int i = 1; i < array.length; ++i) {
			int key = array[i];
			int j;
			for (j = i - 1; j >= 0 && array[j] < key; --j) {
				array[j + 1] = array[j];
			}
			array[j + 1] = key;
		}
	}

	public static void main(String[] args) {
		int[] a = {5, 3, 4, 2, 3, 8, 1, 2};
		System.out.println("Before sort: " + Arrays.toString(a));
		insertionSortNonIncreasing(a);
		System.out.println("After sort: " + Arrays.toString(a));
	}

}
