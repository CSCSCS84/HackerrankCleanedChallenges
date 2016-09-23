/**
 * https://www.hackerrank.com/challenges/chocolate-in-box
 * Solution: https://en.wikipedia.org/wiki/Nim
 */

package gametheory;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class ChocolateInBox {
	static int a[];

	static int N;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		a = new int[N];
		for (int i = 0; i < N; i++) {
			a[i] = sc.nextInt();
		}
		int solution = solve(sc);
		System.out.println(solution);
	}

	private static int solve(Scanner sc) {

		int xorSum = calcXORSum();
		if (xorSum == 0) {
			return 0;
		}

		int binaryLengthOfXORSum = Integer.toBinaryString(xorSum).length();
		int maxNumber = (int) Math.pow(2, binaryLengthOfXORSum - 1);

		int numberOfSolutions = 0;
		for (int i = 0; i < a.length; i++) {
			numberOfSolutions += checkNumber(binaryLengthOfXORSum, maxNumber, i);
		}
		return numberOfSolutions;
	}

	private static int checkNumber(int binaryLength, int maxNumber , int index) {
		int numOfSolution=0;
		String s = Integer.toBinaryString(a[index]);
		if (a[index] >= maxNumber) {
			char ch = s.charAt(s.length() - binaryLength);
			if (ch == '1') {
				numOfSolution++;
			}
		}
		return numOfSolution;
	}

	private static int calcXORSum() {
		int xorSum = 0;
		for (int i = 0; i < N; i++) {
			xorSum = xorSum ^ a[i];
		}
		return xorSum;
	}
}