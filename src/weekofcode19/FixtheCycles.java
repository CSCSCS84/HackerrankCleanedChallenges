package weekofcode19;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/contests/w19/challenges/fix-the-cycles
 * 
 * @author Christoph
 * 
 */
public class FixtheCycles {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int a = in.nextInt();
		int b = in.nextInt();
		int c = in.nextInt();
		int d = in.nextInt();
		int e = in.nextInt();
		int f = in.nextInt();

		int cycle1 = a + b + c + d;
		int cycle2 = a + e + d;
		int cycle3 = b + f + a;

		int numOfCycle = 0;
		int minCycleLength = Integer.MAX_VALUE;
		if (cycle1 < 0) {
			numOfCycle++;
			if (cycle1 < minCycleLength) {
				minCycleLength = cycle1;
			}
		}
		if (cycle2 < 0) {
			numOfCycle++;
			if (cycle2 < minCycleLength) {
				minCycleLength = cycle2;
			}
		}
		if (cycle3 < 0) {
			numOfCycle++;
			if (cycle3 < minCycleLength) {
				minCycleLength = cycle3;
			}
		}

		if (numOfCycle == 0) {
			System.out.println(0);
		} else {
			System.out.println(-1 * minCycleLength);
		}
		in.close();
	}
}