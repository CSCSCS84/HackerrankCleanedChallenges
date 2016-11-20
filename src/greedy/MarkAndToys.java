/**
 * https://www.hackerrank.com/challenges/mark-and-toys
 */

package greedy;

import java.util.Arrays;
import java.util.Scanner;

public class MarkAndToys {

	static int k;
	static int n;
	static int[] prices;

	public static void main(String[] args) {
		readInput();
		int solution = solve();
		System.out.println(solution);
	}

	private static int solve() {
		Arrays.sort(prices);
		int sumOfPrices = 0;
		int solution = 0;
		for (int i = 0; i < prices.length && sumOfPrices + prices[i] < k; i++) {
			sumOfPrices += prices[i];
			solution++;
		}

		return solution;
	}

	private static void readInput() {
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		k = scanner.nextInt();
		prices = new int[n];
		for (int i = 0; i < n; i++) {
			prices[i] = scanner.nextInt();
		}
		scanner.close();
	}
}
