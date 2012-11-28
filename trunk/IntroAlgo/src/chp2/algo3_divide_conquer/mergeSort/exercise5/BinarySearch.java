package chp2.algo3_divide_conquer.mergeSort.exercise5;


public class BinarySearch {

	public static int binarySearch(int[] array, int value) {
		int left = 0;
		int right = array.length - 1;
		while (left <= right) {
			int middle = (left + right) / 2;
			if (value == array[middle]) {
				return middle;
			}
			else if (value < array[middle]) {
				right = middle - 1;
			}
			else {
				left = middle + 1;
			}
		}

		return -1;
	}

	public static int binarySearchRecursive(int[] array, int value) {
		return binarySearchRecursive(array, value, 0, array.length - 1);
	}

	private static int binarySearchRecursive(int[] array, int value, int left, int right) {
		if (left > right) {
			return -1;
		}
		else {
			int middle = (left + right) / 2;
			if (value == array[middle]) {
				return middle;
			}
			else if (value < array[middle]) {
				return binarySearchRecursive(array, value, left, middle - 1);
			}
			else {
				return binarySearchRecursive(array, value, middle + 1, right);
			}
		}
	}

	public static void testBinarySearch() {
//		int[] array = {1, 2, 3, 4, 5};
		int[] array = {1, 2, 3, 4, 5, 6};
		int i = binarySearch(array, 1);
		System.out.println(i);
		i = binarySearch(array, 2);
		System.out.println(i);
		i = binarySearch(array, 3);
		System.out.println(i);
		i = binarySearch(array, 7);
		System.out.println(i);
		i = binarySearch(array, 0);
		System.out.println(i);
	}

	/**
	 * use iteratif binary search
	 * @param array array to search
	 * @param value value to find
	 */
	public static void testBinarySearchRecursive() {
//		int[] array = {1, 2, 3, 4, 5};
		int[] array = {1, 2, 3, 4, 5, 6};

		int i = binarySearchRecursive(array, 1);
		System.out.println(i);
		i = binarySearchRecursive(array, 2);
		System.out.println(i);
		i = binarySearchRecursive(array, 3);
		System.out.println(i);
		i = binarySearchRecursive(array, 7);
		System.out.println(i);
		i = binarySearchRecursive(array, 0);
		System.out.println(i);
	}

	public static void main(String[] args) {
//		testBinarySearch();
		testBinarySearchRecursive();

	}
}
