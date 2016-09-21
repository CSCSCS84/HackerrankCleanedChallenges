/**
 * https://www.hackerrank.com/contests/w23/challenges/lighthouse
 * Solution: Calc for every point the minmimal euclidian distance to a rock. Solution is the maximum of these minimums
 */
package weekofcode23;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Lighthouse {

	static byte[][] grid;
	static int n;
	static LinkedList<Point> rocks = new LinkedList<Point>();

	public static void main(String[] args) throws FileNotFoundException {
		String currentPath = System.getProperty("user.dir");
		String pathOfTestCaseFile = currentPath + ""
				+ "/HackerrankNotCleanedChallenges/TestData/Lighthouse/LighthouseTestData50c.txt";
		File file = new File(pathOfTestCaseFile);
		readInput(file);
		int solution = Lighthouse.solve();
		System.out.println(solution);
	}

	private static int solve() {
		int maxRadius = 0;
		if (rocks.size() > 0) {
			maxRadius = maxRadiusWithRocks(maxRadius);
		} else {
			maxRadius = maxRadiusWithNoRocks();
		}
		return maxRadius;
	}

	private static int maxRadiusWithRocks(int maxRadius) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 0) {
					int radius = calcMinRadiusForPoint(i, j);
					if (radius > maxRadius) {
						maxRadius = radius;
					}
				}
			}
		}
		return maxRadius;
	}

	private static int maxRadiusWithNoRocks() {
		int maxRadius;
		if (n % 2 != 0) {
			maxRadius = (n - 1) / 2;
		} else {
			maxRadius = (n - 2) / 2;
		}
		return maxRadius;
	}

	private static int calcMinRadiusForPoint(int i, int j) {
		int minRadius = Integer.MAX_VALUE;
		minRadius = minDistanceToBorder(i, j);

		for (Point p : rocks) {
			int distance = calcDistance(i, j, p);
			if (distance < minRadius) {
				minRadius = distance;
			}
		}

		return calcMaximalSmallerInteger(minRadius);
	}

	static int minDistanceToBorder(int i, int j) {
		int distLeft = (i + 1) * (i + 1);
		int distRight = (n - i) * (n - i);

		int distAbove = (j + 1) * (j + 1);
		int distBelow = (n - j) * (n - j);

		int min = Math.min(Math.min(distLeft, distRight), Math.min(distAbove, distBelow));

		return min;
	}

	static int calcMaximalSmallerInteger(int number) {
		int maxSmallerInteger = 0;
		while ((maxSmallerInteger + 1) * (maxSmallerInteger + 1) < number) {
			maxSmallerInteger++;
		}
		return maxSmallerInteger;

	}

	static int calcDistance(int i, int j, Point p) {
		int distance = (i - p.getX()) * (i - p.getX()) + (j - p.getY()) * (j - p.getY());
		return distance;
	}

	private static void readInput(File file) throws FileNotFoundException {
		Scanner sc = new Scanner(file);
		n = sc.nextInt();
		sc.nextLine();
		grid = new byte[n][n];

		// read input
		for (byte i = 0; i < n; i++) {
			String line = sc.nextLine();
			for (byte j = 0; j < n; j++) {
				char s = line.charAt(j);
				if (s == '*') {
					grid[i][j] = 1;
					Point p = new Point(i, j);
					rocks.add(p);
				} else {
					grid[i][j] = 0;
				}

			}
		}
		sc.close();
	}

	static private class Point {
		byte x;
		byte y;

		public Point(byte i, byte j) {
			this.x = i;
			this.y = j;
		}

		public byte getX() {
			return x;
		}

		public byte getY() {
			return y;
		}

	}

}
