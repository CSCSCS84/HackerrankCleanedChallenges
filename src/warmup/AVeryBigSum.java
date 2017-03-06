package warmup;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/a-very-big-sum
 * 
 * @author Christoph
 * 
 */

public class AVeryBigSum {

	public static void main(String[] args) {

		long inputNumbers[] = AVeryBigSum.readInput();
		long sum = AVeryBigSum.sum(inputNumbers);

		System.out.println(sum);
	}

	private static long sum(long numbers[]) {
		long sum = 0;
		for (int i = 0; i < numbers.length; i++) {
			sum += numbers[i];
		}
		return sum;
	}

	private static long[] readInput() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long numbers[] = new long[n];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = sc.nextLong();
		}
		sc.close();
		return numbers;
	}
}
