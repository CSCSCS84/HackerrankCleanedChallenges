/**
 * https://www.hackerrank.com/challenges/the-love-letter-mystery
 */

package strings;

import java.util.Scanner;

public class TheLoveLetterMystery {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		if (sc.hasNextLine()) {
			sc.nextLine();
		}
		for (int j = 0; j < t; j++) {
			String line = sc.nextLine();
			int count = solveForTestcase(line);
			System.out.println(count);
		}
		sc.close();
	}

	private static int solveForTestcase(String line) {

		int count = 0;
		int size = line.length();
		for (int i = 0; i < size / 2; i++) {
			Character chA = line.charAt(i);
			Character chB = line.charAt(size - i - 1);
			int a = Character.getNumericValue(chA);
			int b = Character.getNumericValue(chB);
			if (a > b) {
				count += (a - b);
			} else {
				count += (b - a);
			}
		}
		return count;
	}
}