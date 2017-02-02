/**
 * https://www.hackerrank.com/challenges/making-anagrams
 */

package strings;

import java.util.Scanner;

public class MakingAnagrams {

	public static void main(String[] args) {
		int numberOfDeletions = solve();
		System.out.println(numberOfDeletions);
	}

	private static int solve() {
		Scanner sc = new Scanner(System.in);
		String a = sc.nextLine();
		String b = sc.nextLine();

		int[] countChars1 = countChars1(a);
		int[] countChars2 = countChars1(b);
		sc.close();

		int numOfDeletion = numOfDeletion(countChars1, countChars2);
		return numOfDeletion;
	}

	private static int numOfDeletion(int[] countChars1, int[] countChars2) {
		int numberOfDeletions = 0;
		for (int i = 0; i < 26; i++) {
			int difference = countChars2[i] - countChars1[i];
			if (difference > 0) {
				numberOfDeletions += difference;
			}
			if (difference < 0) {
				numberOfDeletions += (-1 * difference);
			}
		}
		return numberOfDeletions;
	}

	private static int[] countChars1(String str) {
		int countChars[] = new int[26];
		for (int i = 0; i < str.length(); i++) {
			Character ch = str.charAt(i);
			int valueOfCharacter = Character.getNumericValue(ch) - 10;
			countChars[valueOfCharacter]++;
		}
		return countChars;
	}
}