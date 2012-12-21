package chp2.algo4.pb4_inversioncount;

import java.util.Arrays;

public class InversionCounter {
	private int[] array;
		
	public int count(int[] array) {
		// On ne touche pas le parametre en entree
		this.array = array.clone();
		
		int totalInversion = countWithMergeSort(0, array.length - 1);

		return totalInversion;
	}
	
	private int countWithMergeSort(int from, int to) {
		int inversion1 = 0;
		int inversion2 = 0;
		int inversion3 = 0;
		if (from < to) {
			int middle = (from + to) / 2;
			inversion1 = countWithMergeSort(from, middle);
			inversion2 = countWithMergeSort(middle + 1, to);
			inversion3 = countWithMergeSort(array, from, middle, to);
		}
		return inversion1 + inversion2 + inversion3;
	}
	
	private int countWithMergeSort(int[] array, int p, int q, int r) {
		int lengthLeft = q - p + 1;
		int lengthRight = r - q;
		
		int[] left = new int[lengthLeft];
		int[] right = new int[lengthRight];
		
		// copy left and right
		for (int i = 0; i < lengthLeft; ++i) {
			left[i] = array[p + i];
		}
		
		for (int i = 0; i < lengthRight; ++i) {
			right[i] = array[q + 1 + i];
		}
		
		// Merge
		int countInversion = 0;
		
		int i = 0;
		int j = 0;
		int k = p;
		while (i < lengthLeft && j < lengthRight) {
			if (left[i] <= right[j]) {
				array[k] = left[i];
				++i;
				++k;
			}
			else {
				array[k] = right[j];
				++j;
				++k;
				countInversion += (lengthLeft - i);
			}
		}
		// i >= lengthLeft || j >= lengthRight
		// La partie gauche a encore des elements,
		while (i < lengthLeft) {
			array[k] = left[i];
			++k;
			++i;
		}
		
		// La partie droite a encore des elements
		while (j < lengthRight) {
			array[k] = right[j];
			++k;
			++j;
		}
		
		return countInversion;
	}
	
	private static void testCountWithMergeSort() {
		int[] array = {3,4,1,2,5};
		
		InversionCounter counter = new InversionCounter();
		int inversion = counter.countWithMergeSort(array, 0, 1, 4);
		System.out.println(Arrays.toString(array));
		System.out.println("inversion = " + inversion);
	}
	
	private static void testCounter() {
		int[] array = {6, 5, 4, 3, 2, 1};
		
		InversionCounter counter = new InversionCounter();
		
		System.out.println(Arrays.toString(array));
		int inversion = counter.count(array);
		System.out.println(inversion);
		System.out.println(Arrays.toString(counter.array));
	}
	
	public static void main(String[] args) {
		testCounter();
	}
}
