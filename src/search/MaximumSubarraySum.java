/**
 * https://www.hackerrank.com/challenges/maxsubarray
 */

package search;

import java.util.Scanner;

public class MaximumSubarraySum {

	private static Scanner scanner;
	private static int N;
	private static int[] A;

	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		int numberOfTestCases = scanner.nextInt();
		solve(numberOfTestCases);
		scanner.close();
	}

	private static void solve(int numberOfTestCases) {
		for (int t = 0; t < numberOfTestCases; t++) {
			MaximumSubarraySum.readInputForTestcase();
			int solution[] = calcMaxContiguousAndMaxNontContiguous();
			System.out.println(solution[0] + " " + solution[1]);
		}
	}

	private static int[] calcMaxContiguousAndMaxNontContiguous() {

		int maxNonPositiveNumber = maxNonPositivNumber(A);
		int sumOfPositiveNumbers = sumOfPositiveNumbers(A);

		int maxSumNonContiguous = 0;
		int maxSumContiguous = maxSumContiguous();

		if (sumOfPositiveNumbers == 0 && maxSumNonContiguous == 0) {
			maxSumNonContiguous = maxNonPositiveNumber;
			maxSumContiguous = maxNonPositiveNumber;
		} else {
			maxSumNonContiguous = sumOfPositiveNumbers;
		}

		int[] solution = { maxSumContiguous, maxSumNonContiguous };
		return solution;
	}

	private static int maxSumContiguous() {
		int maxSumContiguous = 0;
		int maxLastIndex = 0;
		for (int i = 0; i < A.length; i++) {

			int maxCurrentIndex = Math.max(maxLastIndex + A[i], 0);

			if (maxCurrentIndex > maxSumContiguous) {
				maxSumContiguous = maxCurrentIndex;
			}

			maxLastIndex = maxCurrentIndex;
		}
		return maxSumContiguous;
	}

	private static int maxNonPositivNumber(int A[]) {
		int maxNonPositiveNumber = Integer.MIN_VALUE;
		for (int i = 0; i < A.length; i++) {
			if (A[i] <= 0 && A[i] > maxNonPositiveNumber) {
				maxNonPositiveNumber = A[i];
			}
		}
		return maxNonPositiveNumber;
	}

	private static int sumOfPositiveNumbers(int A[]) {
		int sumOfPositiveNumbers = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] > 0) {
				sumOfPositiveNumbers += A[i];
			}
		}
		return sumOfPositiveNumbers;
	}

	private static void readInputForTestcase() {
		N = scanner.nextInt();
		A = new int[N];

		for (int i = 0; i < N; i++) {
			A[i] = scanner.nextInt();
		}

	}
}