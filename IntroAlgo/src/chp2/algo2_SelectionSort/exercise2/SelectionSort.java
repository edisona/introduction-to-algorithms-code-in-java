package chp2.algo2_SelectionSort.exercise2;

import java.util.Arrays;

/**
 * 这是第二节analysing algorithm的课后练习题
 * 我写的伪码为：(数组从1开始)
 * SELECTION-SORT(A)
 * 1. for i = 1 to A.length - 1
 * 2.     minIndex = i
 * 3.     for j = i + 1 to A.length
 * 4.          if A[j] < A[minIndex]
 * 5.               minIndex = j
 * 6.     temp = A[i]
 * 7.     A[i] = A[minIndex]
 * 8.     A[minIndex] = temp

 * @author Bo LI
 *
 */
public class SelectionSort {
	public static void selectionSort(int[] array) {
		for (int i = 0; i < array.length - 1; ++i) {
			int minIndex = i;
			// look for the index of the smallest element from i to length - 1
			for (int j = i + 1; j < array.length; ++j) {
				if (array[j] < array[minIndex]) {
					minIndex = j;
				}
			}
			// Now minIndex is the index of the smallest element, we exchange
			// the smallest element and array[i]
			int temp = array[i];
			array[i] = array[minIndex];
			array[minIndex] = temp;
		}
	}

	public static void main(String[] args) {
		int a[] = {3, 1, 2, 3, 8, 5, 4 ,6};
//		int a[] = {3};
//		int a[] = {};
		System.out.println("Before sorting: " + Arrays.toString(a));
		selectionSort(a);
		System.out.println("After sorting: " + Arrays.toString(a));
	}
}
