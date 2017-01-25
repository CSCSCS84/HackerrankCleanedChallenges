/**
 * https://www.hackerrank.com/challenges/service-lane
 */
package implementation;

import java.util.Scanner;

public class ServiceLane {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int testcases = in.nextInt();
		int width[] = new int[n];
		for (int width_i = 0; width_i < n; width_i++) {
			width[width_i] = in.nextInt();
		}

		for (int a = 0; a < testcases; a++) {
			int i = in.nextInt();
			int j = in.nextInt();
			System.out.println(calcMaxMin(width, i, j));
		}
	}

	private static int calcMaxMin(int[] width, int i, int j) {
		int min = 3;
		for (int k = i; k <= j; k++) {
			if (width[k] < min) {
				min = width[k];
			}
		}
		return min;
	}
}
