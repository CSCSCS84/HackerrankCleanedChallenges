package implementation;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/almost-sorted
 * 
 * @author Christoph
 * 
 */

/*
 * Code is not cleaned because I do not know what I did here.
 */
public class AlmostSorted {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int[] A = new int[n];
		int countReverse = 0;
		int countSwap = 0;
		int lastA = sc.nextInt();
		A[0] = lastA;
		int reverseStart = -1;
		int reverseEnd = -1;

		int[] indexToSwap = new int[4];

		boolean reverseMode = false;
		for (int i = 1; i < n; i++) {
			int currA = sc.nextInt();
			A[i] = currA;
			if (currA < lastA) {
				// something to do
				countSwap++;
				if (countSwap <= 2) {

					indexToSwap[(countSwap - 1) * 2] = i - 1;
					indexToSwap[(countSwap - 1) * 2 + 1] = i;

				}
				if (!reverseMode) {
					countReverse++;
					reverseStart = i - 1;
					reverseMode = true;
				}
			} else if (reverseMode) {
				reverseEnd = i - 1;
				reverseMode = false;

			}
			lastA = currA;
			if (countReverse > 1 && countSwap > 2) {
				System.out.println("no");
				return;
			}
		}

		if (countReverse == 0 && countSwap == 0) {
			System.out.println("yes");
		}

		if (countSwap == 1) {

			int index = indexToSwap[0];
			int compareSmaller = AlmostSorted.compare(index + 1, index - 1, A);
			int compareBigger = AlmostSorted.compare(index, index + 2, A);
			;
			if (compareSmaller != -1 && compareBigger != 1) {
				System.out.println("yes");
				System.out.println("swap " + (index + 1) + " " + (index + 2));
				return;
			}

		}
		if (countSwap == 2) {

			int index1 = indexToSwap[0];
			int index2 = indexToSwap[2];
			int compareSmaller = AlmostSorted.compare(index2 + 1, index1 - 1, A);
			int compareBigger = AlmostSorted.compare(index1, index2 + 2, A);
			;
			if (compareSmaller != -1 && compareBigger != 1) {
				System.out.println("yes");
				System.out.println("swap " + (index1 + 1) + " " + (index2 + 1 + 1));
				return;
			}

		}
		if (countReverse == 1) {
			if (reverseEnd == -1) {
				reverseEnd = n - 1;
			}
			int compareSmaller = AlmostSorted.compare(reverseEnd, reverseStart - 1, A);
			int compareBigger = AlmostSorted.compare(reverseStart, reverseEnd + 1, A);
			if (compareSmaller != -1 && compareBigger != 1) {
				System.out.println("yes");

				System.out.println("reverse " + (reverseStart + 1) + " " + (reverseEnd + 1));
				return;
			}

		}

		System.out.println("no");

	}

	static int compare(int index1, int index2, int[] A) {
		if (index2 < 0 || index2 >= A.length) {
			return 0;
		}
		if (A[index1] == A[index2]) {
			return 2;
		} else if (A[index1] < A[index2]) {
			return -1;
		} else if (A[index1] > A[index2]) {
			return 1;
		}
		return 0;
	}
}