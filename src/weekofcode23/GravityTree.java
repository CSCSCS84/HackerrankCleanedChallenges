/**
 * https://www.hackerrank.com/contests/w23/challenges/gravity-1
 * Solution: For every pair calc the distances and the forces
 */
package weekofcode23;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Stack;

public class GravityTree {
	static int numberOfNodes;
	static Node[] nodes;
	static double timeSum = 0;

	public static void main(String[] args) throws FileNotFoundException {
		String currentPath = System.getProperty("user.dir");
		String pathOfTestCaseFile = currentPath + "" + "/TestData/GravityTree/GravityTreeTestData19.txt";
		FasterScanner sc = readInput(pathOfTestCaseFile);

		GravityTree.calcLevelOfNodes();

		int q = sc.nextInt();
		StringBuffer solBuf = new StringBuffer();
		for (int i = 1; i <= q; i++) {
			int v = sc.nextInt();
			int u = sc.nextInt();
			long solution = solve(v, u);
			solBuf.append(solution);
			solBuf.append("\n");

		}
		System.out.println(solBuf);
	}

	private static long solve(int v, int u) {
		long solution = 0;
		if (!isChild(u, v)) {
			long distanceVU = distance(v, u);
			solution = sumOfSquaredDistancesForSubtree(u, distanceVU);
		} else {
			solution += sumOfSquaredDistancesForSubtree(v, 0);
			solution += sumOfSquaredDistancesForParent(v, 0, u);
		}
		return solution;
	}

	private static long sumOfSquaredDistancesForParent(int v, int depth, int u) {

		int parent = nodes[v].getParent();
		if (parent == 0) {
			return 0;
		}

		long solution = 0;
		solution += (depth + 1) * (depth + 1);
		if (parent != u) {
			solution += sumOfSquaredDistancesForParent(parent, depth + 1, u);
		}
		for (Integer child : nodes[(int) parent].getChilds()) {
			if (child != v) {
				solution += sumOfSquaredDistancesForSubtree(child, depth + 2);
			}
		}

		return solution;
	}

	private static long sumOfSquaredDistancesForSubtree(int u, long distanceVU) {
		long solution = 0;
		int levelOfU = nodes[u].getLevel();
		Stack<Integer> stackOfChilds = new Stack<>();
		stackOfChilds.add(u);
		while (!stackOfChilds.empty()) {
			int child = stackOfChilds.pop();
			long distance = nodes[child].getLevel() - levelOfU + distanceVU;
			solution += distance * distance;

			for (Integer childOfChild : nodes[child].getChilds()) {
				stackOfChilds.add(childOfChild);
			}
		}
		return solution;
	}

	private static long distance(int v, int u) {
		long distance = 0;
		if (nodes[v].getLevel() > nodes[u].getLevel()) {
			while (nodes[v].getLevel() > nodes[u].getLevel()) {
				distance++;
				v = nodes[v].getParent();
			}
		}
		if (nodes[v].getLevel() < nodes[u].getLevel()) {
			while (nodes[v].getLevel() < nodes[u].getLevel()) {
				distance++;
				u = nodes[u].getParent();
			}
		}
		while (v != u) {
			distance += 2;
			v = nodes[v].getParent();
			u = nodes[u].getParent();

		}
		return distance;
	}

	static boolean isChild(int v, int u) {
		if (nodes[u].getLevel() <= nodes[v].getLevel()) {
			return false;
		}
		int levelV = nodes[v].getLevel();

		while (nodes[u].getLevel() != levelV) {
			u = nodes[u].getParent();
		}
		if (u == v) {
			return true;
		} else {
			return false;
		}
	}

	private static void calcLevelOfNodes() {
		Stack<Integer> stackOfChild = new Stack<>();
		stackOfChild.add(1);
		while (!stackOfChild.empty()) {
			int child = stackOfChild.pop();
			if (child != 1) {
				int parent = nodes[child].getParent();
				nodes[child].setLevel(nodes[parent].getLevel() + 1);
			}
			for (Integer childOfChild : nodes[child].getChilds()) {
				stackOfChild.add(childOfChild);
			}
		}

	}

	private static FasterScanner readInput(String pathOfTestCaseFile) throws FileNotFoundException {
		File file = new File(pathOfTestCaseFile);
		InputStream is = new FileInputStream(file);
		FasterScanner sc = new FasterScanner(is);
		numberOfNodes = sc.nextInt();
		// leave 0 entry empty
		nodes = new Node[numberOfNodes + 1];
		for (int i = 1; i <= numberOfNodes; i++) {
			nodes[i] = new Node();
		}

		for (int i = 2; i <= numberOfNodes; i++) {
			int parentOfNode = sc.nextInt();
			nodes[i].setParent(parentOfNode);
			nodes[parentOfNode].childs.add(i);
		}
		return sc;
	}

	private static class Node {
		public LinkedList<Integer> childs = new LinkedList<>();
		public int parent;
		public int level;

		public LinkedList<Integer> getChilds() {
			return childs;
		}

		public int getParent() {
			return parent;
		}

		public void setParent(int parent) {
			this.parent = parent;
		}

		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
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
