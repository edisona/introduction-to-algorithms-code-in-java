package chp2.algo4.pb2_bubblesort;

import java.util.Arrays;

public class BubbleSort {
	public static void bubbleSort(int[] array) {
		for (int i = 0; i < array.length - 1; ++i) {
			for (int j = array.length - 1; j > i; --j) {
				if (array[j - 1] > array[j]) {
					int temp = array[j];
					array[j] = array[j - 1];
					array[j - 1] = temp;
				}
			}
		}
	}

	public static void main(String[] args) {
//		int[] a = {2, 3, 1, 5, 4, 6};
//		int[] a = {2};
		int[] a = {};
		System.out.println("Before sort: " + Arrays.toString(a));
		bubbleSort(a);
		System.out.println("After sort: " + Arrays.toString(a));
	}
}
