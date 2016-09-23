/**
 * https://www.hackerrank.com/contests/projecteuler/challenges/euler018
 * Solution: Use recursion.
 */

package projecteuler;

import java.util.Scanner;

public class MaximumPathSum1 {
	static Scanner sc;
	static int[][] triangle;
	static int N;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		int numberOfTestcases = sc.nextInt();
		for (int t = 0; t < numberOfTestcases; t++) {
			int solution = solve();
			System.out.println(solution);
		}

	}

	private static int solve() {
		N = sc.nextInt();
		triangle = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= i; j++) {
				triangle[i][j] = sc.nextInt();
			}
		}
		int solution = findMaxPath(0, 0);
		return solution;
	}

	private static int findMaxPath(int row, int column) {
		if (row == N) {
			return 0;
		}

		int leftPathSum = triangle[row][column];
		leftPathSum += findMaxPath(row + 1, column);

		int rightPathSum = triangle[row][column];
		rightPathSum += findMaxPath(row + 1, column + 1);

		return max(leftPathSum, rightPathSum);

	}

	static int max(int a, int b) {
		if (a > b) {
			return a;
		} else {
			return b;
		}
	}
}