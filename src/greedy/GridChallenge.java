/**
 * https://www.hackerrank.com/challenges/grid-challenge
 * Solution: Sort each row and then check if the second condition is valid
 */

package greedy;

import java.util.Arrays;
import java.util.Scanner;

public class GridChallenge {
	static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		int numberOfTestCases = sc.nextInt();

		for (int t = 0; t < numberOfTestCases; t++) {
			String solution = solve();
			System.out.println(solution);

		}
	}

	private static String solve() {
		int N = sc.nextInt();
		byte[][] grid = readInputAndSortRows(N);

		if (conditionTwoIsValid(grid)) {
			return "YES";
		} else {
			return "NO";
		}

	}

	private static boolean conditionTwoIsValid(byte[][] grid) {
		int N = grid.length;
		for (int j = 0; j < N; j++) {
			for (int i = 0; i < N - 1; i++) {
				if (grid[i][j] > grid[i + 1][j]) {
					return false;
				}
			}
		}
		return true;
	}

	private static byte[][] readInputAndSortRows(int N) {
		byte grid[][] = new byte[N][N];
		String line = sc.nextLine();
		for (int i = 0; i < N; i++) {
			line = sc.nextLine();
			byte byteLine[] = line.getBytes();
			Arrays.sort(byteLine);
			grid[i] = byteLine;

		}
		return grid;
	}
}