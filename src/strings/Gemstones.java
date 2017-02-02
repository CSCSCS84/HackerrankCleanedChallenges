/**
 * https://www.hackerrank.com/challenges/gem-stones
 */
package strings;

import java.util.Scanner;

public class Gemstones {

	public static void main(String[] args) {
		int countletters = solve();
		System.out.println(countletters);
	}

	private static int solve() {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.nextLine();
		int[] letterCount = countOccurenceOfLetters(sc, N);
		int numberOfGemstones = countGemstones(N, letterCount);
		return numberOfGemstones;
	}

	private static int countGemstones(int N, int[] letterCount) {
		int numberOfGemstones = 0;
		for (int i = 0; i < 26; i++) {
			if (letterCount[i] == N) {
				numberOfGemstones++;
			}
		}
		return numberOfGemstones;
	}

	private static int[] countOccurenceOfLetters(Scanner sc, int N) {
		int[] letterCount = new int[26];
		for (int t = 0; t < N; t++) {
			boolean[] alreadyCounted = new boolean[26];
			String rock = sc.nextLine();
			for (int i = 0; i < rock.length(); i++) {
				int valueOfCharacter = Character.getNumericValue(rock.charAt(i)) - 10;
				if (!alreadyCounted[valueOfCharacter]) {
					letterCount[valueOfCharacter]++;
					alreadyCounted[valueOfCharacter] = true;
				}
			}
		}
		return letterCount;
	}
}