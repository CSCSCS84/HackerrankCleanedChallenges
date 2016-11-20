/**
 * https://www.hackerrank.com/challenges/largest-permutation
 * Challenge Description:You are given an array of  N integers which is a permutation of the first N natural numbers. 
 * You can swap any two elements of the array. You can make at most K swaps. What is the largest permutation, in 
 * numerical order, you can make? 
 */

package greedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class LargestPermutation {
	static int n;
	static int k;
	static int numbers[];
	static int largesPermutation[];
	static HashMap<Integer, Integer> indexOfNumbers;

	public static void main(String[] args) {
		readInput();
		solve();
		outputSolution();
	}

	private static void solve() {
		Arrays.sort(numbers);
		int countOfSwappedNumbers = 1;
		for (int i = numbers.length - 1; i >= 0 && countOfSwappedNumbers <= k; i--) {
			int currentLargestNumber = numbers[i];
			int swapIndex = indexOfNumbers.get(currentLargestNumber);
			int numberCurrentIndex = largesPermutation[numbers.length - i - 1];
			if (numberCurrentIndex != currentLargestNumber) {
				countOfSwappedNumbers++;
				largesPermutation[numbers.length - i - 1] = currentLargestNumber;
				largesPermutation[swapIndex] = numberCurrentIndex;
				indexOfNumbers.remove(currentLargestNumber);
				indexOfNumbers.put(numberCurrentIndex, swapIndex);
			}
		}
	}

	private static void readInput() {
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		k = scanner.nextInt();
		numbers = new int[n];
		largesPermutation = new int[n];
		indexOfNumbers = new HashMap<>();

		for (int i = 0; i < n; i++) {
			int a = scanner.nextInt();
			numbers[i] = a;
			largesPermutation[i] = a;
			indexOfNumbers.put(a, i);
		}

		scanner.close();
	}

	private static void outputSolution() {
		for (Integer number : largesPermutation) {
			System.out.print(number + " ");
		}
	}

}
