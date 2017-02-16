/**
 * https://www.hackerrank.com/challenges/insertionsort1
 */

package sorting;

import java.util.Scanner;

public class InsertionSortPart1 {

	public static void main(String[] args) {
		int[] ar = readInput();
		insertIntoSorted(ar);
	}

	private static int[] readInput() {
		Scanner sc = new Scanner(System.in);
		int s = sc.nextInt();
		int[] ar = new int[s];
		for (int i = 0; i < s; i++) {
			ar[i] = sc.nextInt();
		}
		sc.close();
		return ar;
	}

	public static void insertIntoSorted(int[] ar) {

		int add = ar[ar.length - 1];
		for (int i = ar.length - 2; i >= 0; i--) {

			if (ar[i] > add) {
				ar[i + 1] = ar[i];
				InsertionSortPart1.printArray(ar);
				if (i == 0) {
					ar[i] = add;
					InsertionSortPart1.printArray(ar);
					return;
				}
			} else {
				ar[i + 1] = add;
				InsertionSortPart1.printArray(ar);
				return;
			}
		}
	}

	private static void printArray(int[] ar) {
		for (int n : ar) {
			System.out.print(n + " ");
		}
		System.out.println("");
	}

}