/**
 * https://www.hackerrank.com/challenges/game-of-thrones
 */
package strings;

import java.util.Scanner;

public class GameOfThrones1 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String inputString = scanner.nextLine();

		int[] numberOfChars = numberOfChars(inputString);
		String solution = solve(inputString, numberOfChars);
		System.out.println(solution);
		scanner.close();
	}

	private static String solve(String inputString, int[] numberOfChars) {
		int countOdd = 0;
		int numOfOddValid = 0;
		if (inputString.length() % 2 != 0) {
			numOfOddValid = 1;
		}
		for (int i = 0; i < 26; i++) {
			if (numberOfChars[i] % 2 != 0) {
				countOdd++;
			}
			if (countOdd > numOfOddValid) {
				return "NO";
			}
		}
		return "YES";
	}

	private static int[] numberOfChars(String inputString) {
		int numberOfChars[] = new int[26];
		for (int i = 0; i < inputString.length(); i++) {
			Character ch = inputString.charAt(i);
			int charValue = ch.charValue() - 97;
			numberOfChars[charValue]++;
		}
		return numberOfChars;
	}
}
