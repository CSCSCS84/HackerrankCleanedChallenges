/**
 * https://www.hackerrank.com/challenges/sherlock-and-array
 */
package search;

import java.util.Scanner;

public class SherlockAndArray {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int numOfTestCases = in.nextInt();

		for (int i = 0; i < numOfTestCases; i++) {
			int A[] = new int[in.nextInt()];
			for (int j = 0; j < A.length; j++) {
				A[j] = in.nextInt();
			}
			if (SherlockAndArray.SherlockArray(A)) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}

	}

	private static boolean SherlockArray(int a[]) {
		int rightSumOfArray = SherlockAndArray.sumOfArray(a) - a[0];
		int leftSumOfArray = 0;
		if (rightSumOfArray == leftSumOfArray) {
			return true;
		}
		for (int i = 1; i < a.length; i++) {
			leftSumOfArray += a[i - 1];
			rightSumOfArray -= a[i];
			if (rightSumOfArray == leftSumOfArray) {
				return true;
			}
		}
		return false;
	}

	private static int sumOfArray(int a[]) {
		int sumOfArray = 0;
		for (int i = 0; i < a.length; i++) {
			sumOfArray = sumOfArray + a[i];
		}
		return sumOfArray;
	}
}