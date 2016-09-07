/**
 * https://www.hackerrank.com/challenges/anagram/submissions/code/24495331
 * Challenge Description: A Strings is given, find the the number of Characters you have to change so that the first 
 * part of the string is an anagram of the second part of the string
 */

package strings;

import java.util.Scanner;

public class Anagram {

	static final int numericValueOfChar_a = 97;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int testCases = sc.nextInt();
		sc.nextLine();

		for (int t = 1; t <= testCases; t++) {
			String str = sc.nextLine();
			int solution = 0;
			solution = numberOfChangesToMakeAnagram(str);
			System.out.println(solution);
		}

		sc.close();
	}

	private static int numberOfChangesToMakeAnagram(String input) {
		int lengthOfInput = input.length();
		int solution;
		if (lengthOfInput % 2 != 0) {
			solution = -1;
		} else {
			String firstPart = input.substring(0, lengthOfInput / 2);
			String secondPart = input.substring(lengthOfInput / 2, lengthOfInput);

			int numberOfCharsFirstPart[] = countNumberOfChars(firstPart);
			int numberOfCharsSecondPart[] = countNumberOfChars(secondPart);

			solution = sumOfDifferentChars(numberOfCharsFirstPart, numberOfCharsSecondPart) / 2;
		}
		return solution;
	}

	private static int[] countNumberOfChars(String input) {
		int numberOfChars[] = new int[26];

		for (int i = 0; i < input.length(); i++) {
			int val = input.charAt(i) - numericValueOfChar_a;
			numberOfChars[val]++;
		}

		return numberOfChars;
	}

	private static int sumOfDifferentChars(int[] numberOfCharsFirstPart, int[] numberOfCharsSecondPart) {
		int sum = 0;

		for (int j = 0; j < numberOfCharsFirstPart.length; j++) {
			if (numberOfCharsFirstPart[j] > numberOfCharsSecondPart[j]) {
				sum = sum + numberOfCharsFirstPart[j] - numberOfCharsSecondPart[j];
			} else {
				sum = sum + numberOfCharsSecondPart[j] - numberOfCharsFirstPart[j];
			}
		}

		return sum;
	}
}