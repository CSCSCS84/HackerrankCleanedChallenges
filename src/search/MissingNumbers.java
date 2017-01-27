/**
 * https://www.hackerrank.com/challenges/missing-numbers/copy-from/17390784
 */
package search;

import java.util.Scanner;

public class MissingNumbers {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int A[] = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = in.nextInt();

		}
		int m = in.nextInt();
		int B[] = new int[m];
		for (int i = 0; i < m; i++) {
			B[i] = in.nextInt();
		}

		solve(A, B);

		for (int i = 0; i < B.length; i++) {
			if (B[i] != -1) {
				if (i > 1 && B[i] != B[i - 1]) {
					System.out.print(B[i] + " ");
				}
			}
		}

	}

	private static void solve(int[] A, int[] B) {
		int lastB = 0;

		for (int i = 0; i < A.length; i++) {
			int a = A[i];
			while (lastB < B.length) {
				if (a == B[lastB]) {
					B[lastB] = -1;
					break;
				}
				lastB++;
			}
		}
	}
}