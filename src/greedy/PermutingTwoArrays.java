/**
 * https://www.hackerrank.com/challenges/two-arrays
 */

package greedy;

import java.util.Arrays;
import java.util.Scanner;

public class PermutingTwoArrays {
	static int N;
	static int K;
	static Scanner scanner = new Scanner(System.in);
	static int[] A;
	static int[] B;

	public static void main(String[] args) {
		int numberOfQueries = scanner.nextInt();
		for (int t = 0; t < numberOfQueries; t++) {
			String solution = solveForQuery();
			System.out.println(solution);
		}
	}

	private static String solveForQuery() {
		readInputForQuery(scanner);
		Arrays.sort(A);
		Arrays.sort(B);

		for (int i = 0; i < A.length; i++) {
			int sum = A[i] + B[N - i - 1];
			if (sum < K) {
				return "NO";
			}
		}

		return "YES";
	}

	private static void readInputForQuery(Scanner sc) {
		N = sc.nextInt();
		K = sc.nextInt();
		A = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = sc.nextInt();
		}
		B = new int[N];
		for (int i = 0; i < N; i++) {
			B[i] = sc.nextInt();
		}
	}
}