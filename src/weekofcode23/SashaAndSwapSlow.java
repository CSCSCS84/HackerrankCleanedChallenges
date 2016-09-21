/**
 * Not cleaned.
 * https://www.hackerrank.com/contests/w23/challenges/sasha-and-swaps-ii
 * Challenge Description: There are n distinct numbers given in a certain order. Count the number of different
 * permutations you can reach after 1,..,n-1 swap 
 * Solution: Recursive and very slow
 */
package weekofcode23;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;

public class SashaAndSwapSlow {
	static int n;
	static int numbers[];
	static long modulo = 1000000007;
	static long solution = 0;
	static HashMap<BigInteger, Byte> permutationsKey;
	static HashMap<BigInteger, Long> memory[] = null;
	static long solutionMemory[][];

	public static void main(String[] args) throws FileNotFoundException {
		
			String currentPath = System.getProperty("user.dir");
			String pathOfTestCaseFile = currentPath + "" + "/TestData/SashaAndSwap/SashaAndSwapTestDataOwn" + 100
					+ ".txt";
			File file = new File(pathOfTestCaseFile);

			Scanner sc = new Scanner(file);
			n = sc.nextInt();
			numbers = new int[n];
			for (int i = 0; i < n; i++) {
				numbers[i] = sc.nextInt();
			}
			sc.close();
			SashaAndSwapSlow.solve();

		

	}

	private static void solve() {
		memory=new HashMap[n];
		for (int i = 1;  i < n; i++) {
			long solution = solveForI(i);
			System.out.print((solution) + " ");
		}
	}

	private static long solveForI(int k) {
		solution = 0;
		permutationsKey = new HashMap<>();
		for (int i = 0; i < n; i++) {
			memory[i] = new HashMap<>();
		}
		BigInteger keyValue = keyValueForPermutation(numbers);
		long solution = countPermuation(k, numbers, keyValue);
		return solution;

	}

	private static long countPermuation(int numOfSwaps, int[] permutation, BigInteger keyValue) {
		if (numOfSwaps == 0) {
			if (permutationsKey.containsKey(keyValue)) {
				return 0;
			} else {
				permutationsKey.put(keyValue, (byte) 1);
				return 1;
			}
		}
		long sum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				int[] copy = copyArrayDeep(permutation);
				copy[i] = permutation[j];
				copy[j] = permutation[i];
				long a = 0;
				BigInteger updatedKeyValue = updateKeyValue(keyValue, i, j, permutation);
				if (memory[numOfSwaps - 1].containsKey(updatedKeyValue)) {
					a = memory[numOfSwaps - 1].get(updatedKeyValue);
				} else {

					a = countPermuation(numOfSwaps - 1, copy, updatedKeyValue);
					memory[numOfSwaps - 1].put(updatedKeyValue, a);
					sum += a;
				}
			}
		}
		return sum;
	}

	private static BigInteger updateKeyValue(BigInteger keyValue, int i, int j, int permutation[]) {
		BigInteger updatedKeyValue = keyValue.add(new BigInteger("0"));
		int diff1 = permutation[j] - permutation[i];
		BigInteger nplus1 = new BigInteger((n + 1) + "");
		updatedKeyValue = updatedKeyValue.add(nplus1.pow(i).multiply(new BigInteger("" + diff1)));

		int diff2 = permutation[i] - permutation[j];
		updatedKeyValue = updatedKeyValue.add(nplus1.pow(j).multiply(new BigInteger("" + diff2)));

		return updatedKeyValue;
	}

	private static int[] copyArrayDeep(int[] permutation) {
		int[] copy = new int[n];
		for (int i = 0; i < n; i++) {
			copy[i] = permutation[i];
		}
		return copy;
	}

	private static BigInteger keyValueForPermutation(int[] permutation) {

		BigInteger keyValue = new BigInteger("0");
		for (int i = 0; i < n; i++) {
			BigInteger dummy = new BigInteger((n + 1) + "");
			dummy = dummy.pow(i).multiply(new BigInteger(permutation[i] + ""));
			keyValue = keyValue.add(dummy);
		}

		return keyValue;
	}

}
