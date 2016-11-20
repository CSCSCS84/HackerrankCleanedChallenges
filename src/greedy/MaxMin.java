/**
 * https://www.hackerrank.com/challenges/angry-children/submissions/code/20741479
 */

package greedy;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class MaxMin {

	static int N;
	static int K;
	static int[] numbers;

	public static void main(String[] args) throws NumberFormatException, IOException {
		readInput();
		int minValueUnfairness = solve();
		System.out.println(minValueUnfairness);
	}

	private static int solve() {

		int minValueUnfairness = Integer.MAX_VALUE;
		Arrays.sort(numbers);

		for (int i = 0; i < N - K + 1; i++) {
			int difference = numbers[i + K - 1] - numbers[i];
			if (difference < minValueUnfairness) {
				minValueUnfairness = difference;
			}
		}
		return minValueUnfairness;
	}

	private static void readInput() {
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		N = scanner.nextInt();
		K = scanner.nextInt();
		numbers = new int[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = scanner.nextInt();
		}
		scanner.close();
	}
}
