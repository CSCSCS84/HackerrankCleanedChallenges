package bitmanipulation;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/string-transmission
 * 
 * @author Christoph
 * 
 */

/*
 * Not cleaned, because I dont know what I did here.
 */
public class StringTransmission {

	public static void main(String[] args) {
		int modulo = 1000000007;

		Scanner sc = new Scanner(System.in);

		int numOfTestCases = sc.nextInt();

		for (int t = 1; t <= numOfTestCases; t++) {
			int N = sc.nextInt();
			int K = sc.nextInt();
			sc.nextLine();
			String input = sc.nextLine();

			long sol = StringTransmission.solveFast(input, N, K, modulo);
			System.out.println(sol);
		}

	}

	private static int solveFast(String input, int N, int K, int modulo) {
		if (N == 1 && K > 0) {
			return 2;
		}
		if (N == 1 && K == 0) {
			return 1;
		}
		int countMaxFlip = 0;
		for (int i = (int) K; i >= 0; i--) {
			countMaxFlip = StringTransmission.mod(
					countMaxFlip + StringTransmission.binomialCoefficientModulo(N, i, modulo), modulo);
		}
		int notValid = countNotValidFlips(input, N, K, modulo);
		int validFlips = mod(countMaxFlip - notValid, modulo);
		return validFlips;
	}

	private static int countNotValidFlips(String input, int N, int K, int modulo) {
		char[] in = input.toCharArray();
		LinkedList<Integer> divisors = StringTransmission.allDivider(N);

		int countNotValid = 0;

		int dp[][] = new int[divisors.size()][(int) (N + K + 1)];
		boolean flag = false;
		int divIndex = 0;
		for (Integer div : divisors) {
			// System.out.println(div);
			dp[divIndex][0] = 1;

			for (int j = 0; j < div; j++) {
				// count zeros
				int zeros = 0;
				for (int index = j; index < N; index += div) {
					if (input.charAt(index) == '0') {
						zeros++;
					}
				}
				int pre[] = dp[divIndex];
				dp[divIndex] = new int[1000];
				for (int k = 0; k <= K; k++) {
					// auf 1 setzen
					if (pre[k] > 0) {
						dp[divIndex][k + zeros] = (dp[divIndex][k + zeros] + pre[k]) % modulo;
						dp[divIndex][(int) (k + N / div - zeros)] = (dp[divIndex][(int) (k + N / div - zeros)] + pre[k])
								% modulo;
					}

				}

			}
			if (dp[divIndex][0] > 0) {
				flag = true;
			}
			int index2 = 0;
			for (Integer div2 : divisors) {
				if (div > div2) {
					if (div % div2 == 0) {
						for (int k = 0; k <= K; k++) {
							dp[divIndex][k] = mod(dp[divIndex][k] - dp[index2][k], modulo);
						}
					}
				}
				index2++;
			}

			for (int k = 1; k <= K; k++) {
				countNotValid = (countNotValid + dp[divIndex][k]) % modulo;
			}
			divIndex++;
		}
		if (flag) {
			countNotValid = (countNotValid + 1) % modulo;
		}
		return countNotValid;
	}

	private static LinkedList<Integer> allDivider(long N) {
		LinkedList<Integer> allDiv = new LinkedList<>();

		Integer div = (int) (1);
		while (div <= N - 1) {
			if (N % div == 0) {
				allDiv.add(div);
			}
			div++;
		}

		return allDiv;
	}

	private static int mod(long l, int m) {
		return (int) ((l % m + m) % m);
	}

	private static long binomialCoefficientModulo(long n, long k, long modulo) {

		if (k == 0) {
			return 1;
		}
		if (k == 1) {
			return n;
		}
		if (n == 0 || k > n) {
			return 0;
		}

		long numerator = factorialNdividedByFactorialKModulo(n, k, modulo);
		long denominator = factorialModulo(k, modulo);
		long moduloInverse = moduloInverse(denominator, modulo);

		return (numerator * moduloInverse) % modulo;
	}

	private static long factorialNdividedByFactorialKModulo(long n, long k, long modulo) {
		long factorial = 1;
		for (int i = 0; i < k; i++) {
			factorial = (factorial * (n - i)) % modulo;
		}
		return factorial;
	}

	private static long factorialModulo(long k, long modulo) {
		long factorial = 1;
		for (int i = 2; i <= k; i++) {
			factorial = (factorial * i) % modulo;
		}
		return factorial;
	}

	private static long moduloInverse(long number, long modulo) {
		return moduloPow(number, modulo - 2, modulo);
	}

	public static long moduloPow(long x, long n, long modulo) {
		long result = 1;

		while (n > 0) {
			if (n % 2 != 0) {
				result = (result * x) % modulo;
			}

			x = (x * x) % modulo;
			n = n / 2;
		}

		return result;
	}

}
