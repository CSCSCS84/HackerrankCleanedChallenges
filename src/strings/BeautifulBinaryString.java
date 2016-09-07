/**
 * https://www.hackerrank.com/challenges/beautiful-binary-string/submissions/code/23724075
 * Challenge Description: Count the the minimum number of steps needed to make a string beautiful. A string is called 
 * beautiful if it does not contain the substring "010".
 */

package strings;

import java.util.Scanner;

public class BeautifulBinaryString {

	static final String notBeautiful = "010";

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc.nextInt();
		String input = sc.next();
		sc.close();
		int solution = numberOfStepsToMakeStringBeautiful(input);
		System.out.println(solution);
	}

	private static int numberOfStepsToMakeStringBeautiful(String input) {
		int count = 0;
		for (int i = 0; i < input.length() - 2; i++) {
			if (input.substring(i, i + 3).equals(notBeautiful)) {
				count++;
				if (i + 4 < input.length()) {
					if (input.substring(i + 2, i + 5).equals(notBeautiful)) {
						i = i + 3;
					}
				}
			}
		}
		return count;
	}
}
