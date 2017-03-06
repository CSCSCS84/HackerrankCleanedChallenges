package bitmanipulation;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/flipping-bits
 */

public class FlippingBits {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int numberOfTestcases = sc.nextInt();
		for (int i = 0; i < numberOfTestcases; i++) {
			long toFlip = sc.nextLong();
			System.out.println(FlippingBits.flipInteger(toFlip));
		}
		sc.close();
	}

	private static long flipInteger(long number) {
		long solution = (long) (Math.pow(2, 32)) - number - 1;
		return solution;
	}
}
