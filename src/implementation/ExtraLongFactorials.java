/**
 * https://www.hackerrank.com/challenges/extra-long-factorials
 */

package implementation;

import java.math.BigInteger;
import java.util.Scanner;

public class ExtraLongFactorials {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.close();
		BigInteger solution = solve(n);
		System.out.println(solution);
	}

	private static BigInteger solve(int n) {
		BigInteger solution = new BigInteger(1 + "");
		if (n == 1) {
			return new BigInteger("1");
		}
		for (int i = 2; i <= n; i++) {
			solution = solution.multiply(new BigInteger("" + i));
		}
		return solution;
	}
}
