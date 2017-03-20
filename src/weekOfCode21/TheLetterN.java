package weekOfCode21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class TheLetterN {
	static short N;

	static boolean[][] grid;
	static short dimension = 2 * 10 * 10 * 10 * 10;
	static short dimensionAdd = 10 * 10 * 10 * 10;

	static short[][] numberLeftOfPoint;

	static Point[] points;
	static double epsilon = 0.00000000001;

//	static HashMap<Bruch, Long> leftPoints;

	public static void main(String[] args) throws FileNotFoundException {
		//String[] testCases = { "06", "06b", "08", "08b", "08c", "08d" };
		String[] testCases = { "50","100","300","600" ,"1000","2000","2318"};
		for (int t = 0; t < testCases.length; t++) {

			String testCase = testCases[t];

			String pathOfTestCaseFile = "/home/christoph/Development2/HackerRank2/TestData/TheLetterN/TheLetterNTest"
					+ testCase + ".txt";
			File file = new File(pathOfTestCaseFile);
			Scanner sc = new Scanner(file);
			N = sc.nextShort();
			points = new Point[N];

			grid = new boolean[dimension + 1][dimension + 1];
			for (int i = 0; i < N; i++) {
				short x = (short) (sc.nextShort() + dimensionAdd);

				short y = (short) (sc.nextShort() + dimensionAdd);
				Point p = new Point(x, y);
				points[i] = p;
				grid[x][y] = true;
			}

			double time1 = System.currentTimeMillis();
			long solution = TheLetterN.solve();
			System.out.println(testCase+ " "  + solution);
			double time2 = System.currentTimeMillis();
			System.out.println((time2 - time1) / 1000);
		}

	}

	static void numberLeftOfPoint() {
		numberLeftOfPoint = new short[dimension + 1][dimension + 1];
		for (int j = 0; j < grid.length; j++) {
			short count = 0;
			for (int i = 0; i < grid.length; i++) {
				if (grid[i][j]) {
					count++;
				}
				numberLeftOfPoint[i][j] = count;
			}
		}
	}

	private static long solve() {
		long sum = 0;
		//leftPoints = new HashMap<>();
		// numberLeftOfPoint();
		for (int i = 0; i < points.length; i++) {
			// System.out.println(i);
			for (int j = i + 1; j < points.length; j++) {
				// System.out.println("punkt " + i + " " + j);
				long countLeft = 0;
				long countRight = 0;
				if (points[i].getY() >= points[j].getY()) {
					// System.out.println("left points");
					countLeft = countLeftValidPoints(points[i], points[j]);
					// System.out.println("right points");
					countRight = countRightValidPoints(points[i], points[j]);
				} else {
					// System.out.println("left points");
					countLeft = countLeftValidPoints(points[j], points[i]);
					// System.out.println("right points");
					countRight = countRightValidPoints(points[j], points[i]);
				}
				// System.out.println("countLeft countRight "+countLeft + " " +
				// countRight);
				sum += (countLeft * countRight);
			}
		}
		return sum;
	}

	// B ist weiter oben als c
	private static long countRightValidPoints(Point B, Point C) {
		long sum = 0;

		// TODO eventuell muss hier präziser gearbeitet werden
		double steigung = Point.steigung(B, C);
		if (B.getY() == C.getY()) {
			steigung = 0;
		}

		if (steigung == Double.MAX_VALUE) {
			for (int j = C.getY(); j < dimension; j++) {

				sum += countPointsInIntervall(j, B.getX() + 1, dimension - 1);
			}
		}

		else if (steigung > 0) {
			sum += countPointsInIntervall(C.getY(), C.getX() + 1, dimension - 1);
			// punkte oberhalb zählen
			double leftBorderDouble = C.getX() + 1 / steigung;
			int leftBorder = (int) Math.ceil(leftBorderDouble);
			if (leftBorderDouble - epsilon < leftBorder) {
				leftBorder++;
			}
			int j = C.getY() + 1;
			while (leftBorder < dimension && j < dimension) {
				sum += countPointsInIntervall(j, leftBorder, dimension - 1);
				leftBorderDouble += 1 / steigung;
				leftBorder = (int) Math.ceil(leftBorderDouble);
				if (leftBorderDouble - epsilon < leftBorder) {
					leftBorder++;
				}
				j++;
			}
			// punkte unterhalb zählen
			leftBorderDouble = C.getX() + steigung;
			leftBorder = (int) Math.ceil(leftBorderDouble);
			// if (leftBorderDouble - epsilon < leftBorder) {
			// leftBorder++;
			// }
			j = C.getY() - 1;
			while (leftBorder < dimension && j >= 0) {
				sum += countPointsInIntervall(j, leftBorder, 0);
				leftBorderDouble += steigung;
				leftBorder = (int) Math.ceil(leftBorderDouble);

				j--;
			}

		} else if (steigung < 0) {
			double steigungBetrag = -1 * steigung;
			// sum += countPointsInIntervall(B.getY(), 0, B.getX() - 1);
			// punkte oberhalb zählen

			double rightBorderDouble = C.getX() + steigungBetrag;
			int rightBorder = (int) Math.floor(rightBorderDouble);

			double leftBorderDouble = C.getX() - 1 / steigungBetrag;
			int leftBorder = (int) Math.ceil(leftBorderDouble);
			if (leftBorder - epsilon < leftBorderDouble) {
				leftBorder++;
			}
			int j = C.getY() + 1;
			while (j < dimension) {
				if (leftBorder < 0) {
					leftBorder = 0;
				}
				if (rightBorder >= dimension) {
					rightBorder = dimension - 1;
				}

				sum += countPointsInIntervall(j, leftBorder, rightBorder);
				rightBorderDouble += steigungBetrag;
				rightBorder = (int) Math.floor(rightBorderDouble);
				leftBorderDouble -= 1 / steigungBetrag;
				// TODO richtig so??
				leftBorder = (int) Math.ceil(leftBorderDouble);

				if (leftBorder - epsilon < leftBorderDouble) {
					leftBorder++;
				}
				j++;
			}

		} else if (steigung == 0) {

			int rightBorder = dimension - 1;
			int leftBorder = 0;
			if (B.getX() < C.getX()) {
				leftBorder = B.getX();
			} else {
				leftBorder = C.getX();
			}
			for (int j = B.getY() - 1; j >= 0; j--) {

				sum += countPointsInIntervall(j, leftBorder, rightBorder - 1);
			}
		}

		return sum;
	}

	private static long countLeftValidPoints(Point B, Point C) {
		long sum = 0;

		// TODO eventuell muss hier präziser gearbeitet werden

		int zaehler = (C.getY() - B.getY());
		int nenner = (C.getX() - B.getX());
		Bruch bruch=new Bruch(zaehler, nenner);
//		if(leftPoints.containsKey(bruch)){
//			return leftPoints.get(bruch);
//		}
		
		double steigung = Point.steigung(B, C);
		if (B.getY() == C.getY()) {
			steigung = 0;
		}

		if (steigung == Double.MAX_VALUE) {
			for (int j = B.getY(); j >= 0; j--) {

				sum += countPointsInIntervall(j, 0, B.getX() - 1);
			}
		}

		else if (steigung > 0) {
			sum += countPointsInIntervall(B.getY(), 0, B.getX() - 1);
			// punkte oberhalb zählen
			double rightBorderDouble = B.getX() - steigung;
			int rightBorder = (int) Math.floor(rightBorderDouble);
			int j = B.getY() + 1;
			while (rightBorder >= 0 && j < dimension) {
				sum += countPointsInIntervall(j, 0, rightBorder);
				rightBorderDouble -= steigung;
				rightBorder = (int) Math.floor(rightBorderDouble);
				j++;
			}
			// punkte unterhalb zählen
			rightBorderDouble = B.getX() - 1 / steigung;
			rightBorder = (int) Math.floor(rightBorderDouble);
			if (rightBorderDouble - epsilon < rightBorder) {
				rightBorder--;
			}
			j = B.getY() - 1;
			while (rightBorder >= 0 && j >= 0) {
				sum += countPointsInIntervall(j, 0, rightBorder);
				rightBorderDouble -= 1 / steigung;
				rightBorder = (int) Math.floor(rightBorderDouble);
				if (rightBorderDouble - epsilon < rightBorder) {
					rightBorder--;
				}
				j--;
			}

		} else if (steigung < 0) {
			double steigungBetrag = -1 * steigung;
			// sum += countPointsInIntervall(B.getY(), 0, B.getX() - 1);
			// punkte oberhalb zählen

			// punkte unterhalb zählen
			double rightBorderDouble = B.getX() + 1 / steigungBetrag;
			int rightBorder = (int) Math.floor(rightBorderDouble);
			double leftBorderDouble = B.getX() - steigungBetrag;
			int leftBorder = (int) Math.floor(leftBorderDouble);
			if (rightBorderDouble - epsilon < rightBorder) {
				rightBorder--;
			}
			int j = B.getY() - 1;
			while (j >= 0) {
				if (leftBorder < 0) {
					leftBorder = 0;
				}
				if (rightBorder >= dimension) {
					rightBorder = dimension - 1;
				}
				sum += countPointsInIntervall(j, leftBorder, rightBorder);
				rightBorderDouble += 1 / steigungBetrag;
				rightBorder = (int) Math.floor(rightBorderDouble);
				leftBorderDouble -= steigungBetrag;

				leftBorder = (int) Math.floor(leftBorderDouble);
				if (rightBorderDouble - epsilon < rightBorder) {
					rightBorder--;
				}
				j--;
			}

		} else if (steigung == 0) {
			int rightBorder = 0;
			if (B.getX() > C.getX()) {
				rightBorder = B.getX();
			} else {
				rightBorder = C.getX();
			}
			for (int j = B.getY() + 1; j < dimension; j++) {

				sum += countPointsInIntervall(j, 0, rightBorder);
			}
		}
		//leftPoints.put(bruch, sum);
		return sum;
	}

	private static int countPointsInIntervall(int j, int a, int b) {
		int count = 0;
		

//		for (int i = a; i <= b; i++) {
//
//			if (grid[i][j]) {
//				// System.out.println((i-dimensionAdd) +" "+ (j-dimensionAdd));
//				count++;
//			}
//		}
		return count;
	}

	static class Point {
		short x;
		short y;

		public Point(short x, short y) {
			super();
			this.x = x;
			this.y = y;
		}

		public short getX() {
			return x;
		}

		public void setX(short x) {
			this.x = x;
		}

		public short getY() {
			return y;
		}

		public void setY(short y) {
			this.y = y;
		}

		public static double distance(Point a, Point b) {
			double distance = Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
			return distance;
		}

		public static double steigung(Point a, Point b) {
			if (b.getX() == a.getX()) {
				return Double.MAX_VALUE;
			}
			double steigung = (b.getY() - a.getY() + 0.0) / (b.getX() - a.getX());
			return steigung;
		}

	}

	static class Bruch {
		int zaehler;
		int nenner;

		public int getZaehler() {
			return zaehler;
		}

		public void setZaehler(int zaehler) {
			this.zaehler = zaehler;
		}

		public int getNenner() {
			return nenner;
		}

		public void setNenner(int nenner) {
			this.nenner = nenner;
		}

		public Bruch(int zaehler, int nenner) {
			super();
			this.zaehler = zaehler;
			this.nenner = nenner;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			//result = prime * result + getOuterType().hashCode();
			result = prime * result + nenner;
			result = prime * result + zaehler;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Bruch other = (Bruch) obj;
			
			if (nenner != other.nenner)
				return false;
			if (zaehler != other.zaehler)
				return false;
			return true;
		}

		

	}

}
