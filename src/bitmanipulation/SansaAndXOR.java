/**
 * https://www.hackerrank.com/challenges/sansa-and-xor
 * Challenge Description: an array with n numbers is given. Find the XORSum over the XORSum of each contiguous subset
 * Solution: Calculat the number of subsets which contain a_i. If the number is even, add a_i^a_i, else a_i.
 * 
 */

package bitmanipulation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class SansaAndXOR {
	public static FasterScanner mFScanner;

	public static void main(String[] args) throws FileNotFoundException {

		mFScanner = new FasterScanner();
		int numberOfTestCases = mFScanner.nextInt();
		for (int t = 1; t <= numberOfTestCases; t++) {
			int solution=solve();
			System.out.println(solution);
		}
	}

	private static int solve() {
		int n = mFScanner.nextInt();
		int XORSum = 0;
		int numOfSubSets = n;
		int differenceOfSubsets = n - 2;
		for (int i = 1; i <= n; i++) {
			int a = mFScanner.nextInt();
			int xorForA = calcXORForNumber(a, numOfSubSets);
			numOfSubSets += differenceOfSubsets;
			differenceOfSubsets = differenceOfSubsets - 2;

			XORSum = XORSum ^ xorForA;
		}
		return XORSum;
	}

	private static int calcXORForNumber(int a, int numOfSub) {
		if (numOfSub % 2 == 0) {
			return a ^ a;
		} else {
			return a;
		}
	}

	static class FasterScanner {
		private InputStream mIs;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;

		public FasterScanner() {
			this(System.in);
		}

		public FasterScanner(InputStream is) {
			mIs = is;
		}

		public int read() {
			if (numChars == -1)
				throw new InputMismatchException();
			if (curChar >= numChars) {
				curChar = 0;
				try {
					numChars = mIs.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (numChars <= 0)
					return -1;
			}
			return buf[curChar++];
		}

		public String nextLine() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isEndOfLine(c));
			return res.toString();
		}

		public String nextString() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isSpaceChar(c));
			return res.toString();
		}

		public long nextLong() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public int nextInt() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public boolean isSpaceChar(int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		public boolean isEndOfLine(int c) {
			return c == '\n' || c == '\r' || c == -1;
		}

	}
}