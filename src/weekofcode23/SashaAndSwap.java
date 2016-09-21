/**
 * https://www.hackerrank.com/contests/w23/challenges/sasha-and-swaps-ii
 * Challenge Description: There are n distinct numbers given in a certain order. Count the number of different
 * permutations you can reach after 1,..,n-1 swap 
 * Solution: Let S_nm be the solution for n given numbers after m swaps. Then S_nm= S_(n-1)m + (n-1)*S_(n-1)(m-1)
 */

package weekofcode23;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class SashaAndSwap {

	static int n;
	static int numbers[];
	static long modulo = 1000000007;
	static long solution = 0;

	static int solutionFast[][];

	public static void main(String[] args) throws FileNotFoundException {
		String currentPath = System.getProperty("user.dir");
		String pathOfTestCaseFile = currentPath + "" + "/TestData/SashaAndSwap/SashaAndSwapTestDataOwn" + 25000 + ".txt";
		readInput(pathOfTestCaseFile);
		String solution = SashaAndSwap.solveFaster();
		System.out.println(solution);
	}

	private static String solveFaster() {
		StringBuffer sol = new StringBuffer();

		long[] solution = solutionForOneSwap();
		sol.append(solution[n] + " ");

		for (int t = 2; t < n - 1; t++) {
			solutionForTSwaps(solution, t);
			sol.append(solution[n] + " ");
		}

		long lastTerm = solutionForNSwaps();
		sol.append(lastTerm);
		return sol.toString();

	}

	private static long solutionForNSwaps() {
		long lastTerm = 1;
		for (int i = 3; i <= n; i++) {
			lastTerm = (lastTerm * (i)) % modulo;
		}
		return lastTerm;
	}

	private static long solutionForTSwaps(long[] solution, int t) {
		long startValue = (t + 1) % modulo;
		long lastValue = solution[t + 1];
		solution[t + 1] = startValue;
		long nextValue = 0;
		for (int i = t + 2; i <= n; i++) {
			nextValue = ((lastValue * (i - 1)) % modulo + solution[i - 1]) % modulo;
			lastValue = solution[i];
			solution[i] = nextValue;
		}
		solution[n] = nextValue;
		return startValue;
	}

	private static long[] solutionForOneSwap() {
		long[] solution = new long[n + 1];
		long sum = 0;
		for (int i = 2; i <= n; i++) {
			sum = (sum + (i - 1));
			if (sum > modulo) {
				sum = sum % modulo;
			}
			solution[i] = sum;
		}
		return solution;
	}

	private static void readInput(String pathOfTestCaseFile) throws FileNotFoundException {
		File file = new File(pathOfTestCaseFile);
		InputStream is = new FileInputStream(file);
		FasterScanner sc = new FasterScanner(is);
		n = sc.nextInt();
		numbers = new int[n];
		for (int i = 0; i < n; i++) {
			numbers[i] = sc.nextInt();
		}
	}

	public static class FasterScanner {
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
