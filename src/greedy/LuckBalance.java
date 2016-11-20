/**
 * https://www.hackerrank.com/challenges/luck-balance
 */

package greedy;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class LuckBalance {
	static LinkedList<Integer> importantChallenges;
	static int N;
	static int K;

	public static void main(String[] args) {

		int amountOfLuck = readInputAndCalculateAmountOfLuckUnimportantChallenges();
		amountOfLuck += amountOfLuckImportantChallenges(K, importantChallenges);
		System.out.println(amountOfLuck);

	}

	private static int readInputAndCalculateAmountOfLuckUnimportantChallenges() {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		K = scanner.nextInt();

		importantChallenges = new LinkedList<Integer>();
		int amountOfLuck = 0;
		for (int i = 0; i < N; i++) {
			int val = scanner.nextInt();
			int important = scanner.nextInt();
			if (important == 1) {
				importantChallenges.add(val);
			} else {
				amountOfLuck += val;
			}
		}
		scanner.close();
		return amountOfLuck;
	}

	private static int amountOfLuckImportantChallenges(int K, LinkedList<Integer> importantChallenges) {
		int amountOfLuck = 0;
		Collections.sort(importantChallenges);
		Iterator<Integer> iterator = importantChallenges.descendingIterator();
		int count = 0;
		while (iterator.hasNext()) {
			if (count < K) {
				amountOfLuck += iterator.next();
			} else {
				amountOfLuck -= iterator.next();
			}
			count++;
		}
		return amountOfLuck;
	}
}