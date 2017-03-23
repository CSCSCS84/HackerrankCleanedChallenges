package weekOfCode21;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/contests/w21/challenges/kangaroo
 * 
 * @author Christoph
 * 
 */

public class Kangaroo {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int x1 = in.nextInt();
		int v1 = in.nextInt();
		int x2 = in.nextInt();
		int v2 = in.nextInt();

		if (v2 >= v1) {
			System.out.println("NO");
			return;
		}

		int startDiff = x2 - x1;
		int jumpDiff = v1 - v2;

		if (startDiff % jumpDiff == 0) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
		in.close();

	}
}
