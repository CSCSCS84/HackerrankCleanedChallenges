/**
 * https://www.hackerrank.com/challenges/pangrams
 */
package strings;

import java.util.HashSet;
import java.util.Scanner;

public class Pangrams {

	public static void main(String[] args) {
		String solution = solve();
		System.out.println(solution);
	}

	private static String solve() {
		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine().toLowerCase();

		HashSet<Character> charactersInString = new HashSet<Character>();
		for (int i = 0; i < str.length(); i++) {
			Character ch = str.charAt(i);
			if (!ch.equals(' ')) {
				charactersInString.add(ch);
			}
		}
		scanner.close();
		if (charactersInString.size() == 26) {
			return "pangram";
		} else {
			return "not pangram";
		}
	}
}