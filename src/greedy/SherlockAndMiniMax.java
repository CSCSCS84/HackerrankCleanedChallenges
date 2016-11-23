/**
 * https://www.hackerrank.com/challenges/sherlock-and-minimax
 */
package greedy;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class SherlockAndMiniMax {

	static int N;
	static int P;
	static int Q;
	static int[] numbers;

	public static void main(String[] args) throws FileNotFoundException {

		readInput();

		int solution = solve();

		System.out.println(solution);
	}

	private static int solve() {
		Arrays.sort(numbers);
		int maxMin = 0;
		int sol = -1;

		if (P <= numbers[0]) {
			maxMin = numbers[0] - P;
			sol = P;
		}

		for (int i = 0; i < N - 1; i++) {
			if (P > numbers[i + 1] || Q < numbers[i]) {
				continue;
			}

			int difference = numbers[i + 1] - numbers[i];

			int middle = numbers[i] + difference / 2;

			if (P > middle) {
				difference = numbers[i + 1] - P;
				middle = P;

			} else if (Q < middle) {
				difference = Q - numbers[i];
				middle = Q;
			}
			if (difference / 2 > maxMin) {
				maxMin = difference / 2;
				sol = middle;
			}
		}

		if (Q > numbers[N - 1]) {
			int difference = Q - numbers[N - 1];
			if (difference > maxMin) {
				maxMin = difference;
				sol = Q;
			}
		}
		return sol;
	}

	private static void readInput() {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		numbers = new int[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = scanner.nextInt();
		}
		P = scanner.nextInt();
		Q = scanner.nextInt();
		scanner.close();
	}
}
