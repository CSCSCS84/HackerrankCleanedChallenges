/**
 * hhttps://www.hackerrank.com/challenges/sherlock-and-the-beast
 */

package greedy;

import java.util.Scanner;

public class SherlockAndTheBeast {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numberOfTestcases = scanner.nextInt();
		for (int a = 0; a < numberOfTestcases; a++) {
			int n = scanner.nextInt();
			System.out.println(SherlockAndTheBeast.largestDecentNumber(n));

		}
		scanner.close();
	}

	private static String largestDecentNumber(int n) {

		int numberOfFive = 0;
		int rest = n % 3;

		numberOfFive = n - rest;
		int numberOfThree = (n - numberOfFive);

		while (numberOfThree % 5 != 0 && numberOfFive >= 0) {
			numberOfFive = numberOfFive - 3;
			numberOfThree = (n - numberOfFive);
		}
		if (numberOfFive < 0) {
			return "-1";
		}
		String largestDecentNumber = SherlockAndTheBeast.buildResult(numberOfFive, numberOfThree);
		return largestDecentNumber;

	}

	private static String buildResult(int numberOfFive, int numberOfThree) {
		StringBuilder largestDecentNumber = new StringBuilder();
		for (int i = 1; i <= numberOfFive; i++) {
			largestDecentNumber.append("5");
		}
		for (int i = 1; i <= numberOfThree; i++) {
			largestDecentNumber.append("3");
		}
		return largestDecentNumber.toString();
	}
}
