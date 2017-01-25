/**
 * https://www.hackerrank.com/challenges/icecream-parlor
 */
package search;

import java.util.Scanner;

public class IceCreamParlor {

	static Scanner sc = new Scanner(System.in);
	static short M;
	static int N;
	static short cost[];

	public static void main(String[] args) {

		int T = sc.nextInt();
		for (int i = 0; i < T; i++) {
			readInputForTestcase();
			solveForTestcase();
		}
	}

	private static void solveForTestcase() {
		// iterate over all solutions
		for (int k = 0; k < N - 1; k++) {
			for (int l = k + 1; l < N; l++) {
				short sum = (short) (cost[k] + cost[l]);
				if (sum == M) {
					System.out.println((k + 1) + " " + (l + 1));
					return;
				}
			}
		}
	}

	private static void readInputForTestcase() {
		M = sc.nextShort();
		N = sc.nextInt();
		cost = new short[N];
		for (int t = 0; t < N; t++) {
			cost[t] = sc.nextShort();
		}
	}
}
