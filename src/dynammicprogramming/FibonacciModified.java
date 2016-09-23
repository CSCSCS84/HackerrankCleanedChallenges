/**
 * https://www.hackerrank.com/challenges/fibonacci-modified/submissions/code/17705217
 * Challenge Description:Given t_1 and t_2, compute t_i+2=t_i+ t_(i+1)*t_(i+1)
 * Solution: Just compute t_i+2 using a loop and BigIntegers
 */

package dynammicprogramming;

import java.math.BigInteger;
import java.util.Scanner;

public class FibonacciModified {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BigInteger t1 = sc.nextBigInteger();
		BigInteger t2 = sc.nextBigInteger();
		int n = sc.nextInt();
		BigInteger sol = solve(t1, t2, n);
		System.out.println(sol);
		sc.close();
	}

	private static BigInteger solve(BigInteger t1, BigInteger t2, int n) {
		BigInteger Tn = t1;
		BigInteger Tnplus1 = t2;
		BigInteger sol = new BigInteger("0");

		for (int i = 2; i <= n - 1; i++) {
			sol = Tnplus1;
			sol = sol.multiply(Tnplus1);
			sol = sol.add(Tn);
			Tn = Tnplus1;
			Tnplus1 = sol;
		}
		return sol;
	}
}