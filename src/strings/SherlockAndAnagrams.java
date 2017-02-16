/**
 * https://www.hackerrank.com/challenges/sherlock-and-anagrams
 */

package strings;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;

public class SherlockAndAnagrams {

	static final int numericalValueOfChar_a = 10;
	static BigInteger maxStringLength = new BigInteger("1000");

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int numOfTestCases = sc.nextInt();
		sc.nextLine();
		for (int t = 1; t <= numOfTestCases; t++) {
			String input = sc.nextLine();
			long solution = SherlockAndAnagrams.countUnorderedPairs(input);
			System.out.println(solution);
		}
		sc.close();
	}

	private static long countUnorderedPairs(String input) {

		HashMap<BigInteger, Long> countSubstrings = SherlockAndAnagrams.countSubstring(input);
		long countUnorderedPairs = 0;
		for (Long count : countSubstrings.values()) {
			countUnorderedPairs += (count * (count - 1) / 2);
		}
		return countUnorderedPairs;
	}

	private static HashMap<BigInteger, Long> countSubstring(String input) {
		HashMap<BigInteger, Long> countSubstrings = new HashMap<>();
		for (int i = 0; i < input.length(); i++) {
			for (int j = i; j < input.length(); j++) {

				String substring = input.substring(i, j + 1);
				BigInteger powOfString = SherlockAndAnagrams.powOfString(substring);

				if (countSubstrings.containsKey(powOfString)) {
					long count = countSubstrings.remove(powOfString);
					count++;
					countSubstrings.put(powOfString, count);
				} else {
					countSubstrings.put(powOfString, 1L);
				}
			}
		}
		return countSubstrings;
	}

	private static BigInteger powOfString(String input) {
		BigInteger pow = new BigInteger("0");
		for (int i = 0; i < input.length(); i++) {
			int charValue = Character.getNumericValue(input.charAt(i));
			charValue -= numericalValueOfChar_a;
			BigInteger dummy = maxStringLength.pow(charValue);
			pow = pow.add(dummy);
		}
		return pow;
	}

}
