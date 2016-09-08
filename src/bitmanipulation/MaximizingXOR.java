/**
 * https://www.hackerrank.com/challenges/maximizing-xor/submissions/code/18472533
 * Challenge Description: Given to integers L, find A and B with L<=A<=B<=R so that A xor B is maximal.
 */

package bitmanipulation;

import java.util.Scanner;

public class MaximizingXOR {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int L = Integer.parseInt(sc.nextLine());
		int R = Integer.parseInt(sc.nextLine());
		sc.close();

		int maxXor = calcMaxXOR(L, R);
		System.out.println(maxXor);
	}

	private static int calcMaxXOR(int L, int R) {
		int maxXor = 0;
		for (int i = L; i <= R; i++) {
			for (int j = L; j <= R; j++) {
				int xor = i ^ j;
				if (xor > maxXor) {
					maxXor = xor;
				}
			}
		}
		return maxXor;
	}
}
