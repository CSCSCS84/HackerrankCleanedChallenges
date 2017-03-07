package greedy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/board-cutting
 * 
 * @author Christoph
 * 
 */
public class CuttingBoard {

	static long x[];
	static long y[];
	static long modulo = 1000000007;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int numberOfTestcases = in.nextInt();
		for (int t = 0; t < numberOfTestcases; t++) {
			readTestcase(in);
			long solution = solve();
			System.out.println(solution);
		}
	}

	private static long solve() {
		Arrays.sort(y);
		Arrays.sort(x);
		long numOfHoricontalPieces = 1;
		long numOfVerticalPieces = 1;

		int index1 = y.length - 1;
		int index2 = x.length - 1;
		// calculate NextCut
		long cost = 0;
		for (int k = 0; k < y.length + x.length; k++) {

			if (index2 == -1 || (index1 != -1 && y[index1] > x[index2])) {
				cost = (cost + (y[index1] * numOfVerticalPieces) % modulo) % modulo;
				numOfHoricontalPieces++;
				y[index1] = 0;
				index1--;
			} else {
				cost = (cost + (x[index2] * numOfHoricontalPieces) % modulo) % modulo;
				numOfVerticalPieces++;
				x[index2] = 0;
				index2--;
			}
		}
		return cost;
	}

	private static void readTestcase(Scanner in) {
		int M = in.nextInt();
		int N = in.nextInt();
		y = new long[M - 1];
		x = new long[N - 1];

		for (int i = 0; i < M - 1; i++) {
			y[i] = in.nextLong();
		}
		for (int i = 0; i < N - 1; i++) {
			x[i] = in.nextLong();
		}
	}
}