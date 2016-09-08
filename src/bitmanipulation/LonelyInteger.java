/**
 * https://www.hackerrank.com/challenges/lonely-integer
 * Challenge Description: In a given list of numbers there is one number unique the other one appear twice. Find the 
 * unique number
 */

package bitmanipulation;

import java.util.HashMap;
import java.util.Scanner;

public class LonelyInteger {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		byte[] numbers = new byte[n];
		for (int i = 0; i < n; i++) {
			numbers[i] = sc.nextByte();
		}
		sc.close();

		byte solution = solve(numbers);
		System.out.println(solution);
	}

	private static byte solve(byte[] numbers) {
		byte solution = 0;
		HashMap<Byte, Integer> countNumbers = countNumbers(numbers);
		solution = findUniqueNumber(countNumbers);
		return solution;
	}

	private static HashMap<Byte, Integer> countNumbers(byte[] numbers) {
		HashMap<Byte, Integer> countNumbers = new HashMap<>();
		for (Byte number : numbers) {
			if (countNumbers.containsKey(number)) {
				countNumbers.remove(number);
				countNumbers.put(number, 2);
			} else {
				countNumbers.put(number, 1);
			}
		}
		return countNumbers;
	}

	private static byte findUniqueNumber(HashMap<Byte, Integer> countNumbers) {
		for (Byte number : countNumbers.keySet()) {
			if (countNumbers.get(number) == 1) {
				return number;
			}
		}
		return -1;
	}
}
