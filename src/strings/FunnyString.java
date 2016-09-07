/**
 * https://www.hackerrank.com/contests/master/challenges/funny-string
 */

package strings;

import java.util.Scanner;

public class FunnyString {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int numberOfTestcases = sc.nextInt();
		String str = sc.nextLine();
		
		for (int i = 1; i <= numberOfTestcases; i++) {
			str = sc.nextLine();
			if (FunnyString.isFunny(str)) {
				System.out.println("Funny");
			}
			else {
				System.out.println("Not Funny");
			}
		}
		
		sc.close();
	}

	private static boolean isFunny(String str) {

		for (int i = 1; i < str.length(); i++) {
			int s1 = Character.getNumericValue(str.charAt(i - 1));
			int s2 = Character.getNumericValue(str.charAt(i));
			int r1 = Character.getNumericValue(str.charAt(str.length() - i - 1));
			int r2 = Character.getNumericValue(str.charAt(str.length() - i));

			if (Math.abs(s1 - s2) != Math.abs(r1 - r2)) {
				return false;
			}
		}

		return true;
	}
}