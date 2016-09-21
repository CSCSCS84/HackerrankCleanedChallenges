/**
 * Not cleaned and with at least one bug.
 * https://www.hackerrank.com/contests/w23/challenges/enclosure
 * Description: n length are given. Form a polygon with maximal area
 * Solution: Every point of a max area polygon must lie on a circle. So solution is to find the radius so that every
 * point lies on this radius. R is the radius if and only if sum(arcsin(L_k/2R))=PI where L_k is the length of side k. 
 * R is found by using a root finding algorithm. We use the bisection methode here.	
 */

package weekofcode23;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Enclosure {
	static long numberOfPoints;
	static long[] lengthOfSides = null;
	static double epsilon = 0.00000000001;
	static long maxR = 0;
	static private long maxIteration = 10000000;
	static double maxTime=3.5;

	public static void main(String[] args) throws FileNotFoundException {
		String currentPath = System.getProperty("user.dir");
		String pathOfTestCaseFile = currentPath+ ""+"/TestData/Enclosure/EnclosureTestData32.txt";
		File file = new File(pathOfTestCaseFile);

		Scanner sc = new Scanner(file);
		numberOfPoints = sc.nextInt();
		lengthOfSides = new long[(int) numberOfPoints];
		for (int i = 0; i < numberOfPoints; i++) {
			lengthOfSides[i] = sc.nextLong();
			if (lengthOfSides[i] > maxR) {
				maxR = lengthOfSides[i];
			}
		}
		StringBuffer solution = solve();
		System.out.println(solution);
		sc.close();

	}

	private static StringBuffer solve() {
		StringBuffer solution1 = new StringBuffer();
		StringBuffer solution2 = new StringBuffer();
		double radius = Enclosure.findRadiusByBisection();

		double distToStartPointSolution1 = 0;
		double distToStartPointSolution2 = 0;

		distToStartPointSolution1 = solution1(solution1, radius, distToStartPointSolution1);
		distToStartPointSolution2 = solution2(solution2, radius, distToStartPointSolution2);

		if (distToStartPointSolution1 < distToStartPointSolution2) {
			return solution1;
		} else {
			return solution2;
		}

	}

	private static double solution2(StringBuffer solution2, double radius, double distToZeroSol2) {
		double winkelSum = 0;
		solution2.append("0");
		solution2.append("\n");
		solution2.append("0");
		solution2.append("\n");
		solution2.append("\n");
		solution2.append("0");
		solution2.append("\n");
		solution2.append(lengthOfSides[0]);
		solution2.append("\n");
		solution2.append("\n");

		double mitteX = radius * radius - lengthOfSides[0] / 2.0 * lengthOfSides[0] / 2.0;
		mitteX = -Math.sqrt(mitteX);
		double mitteY = lengthOfSides[0] / 2.0;
		double winkelStart = Math.asin(mitteY / (radius));

		winkelStart = Math.PI / 2 - winkelStart;
		winkelSum = winkelStart;
		for (int i = 1; i < numberOfPoints; i++) {
			double winkel = 2 * Math.asin(lengthOfSides[i] / (2 * radius));

			winkelSum += winkel;
			if (winkelSum >= 2 * Math.PI) {
				winkelSum -= 2 * Math.PI;
			}
			double x = mitteX + radius * Math.sin(winkelSum);
			double y = mitteY + radius * Math.cos(winkelSum);
			if (i + 1 == numberOfPoints) {
				distToZeroSol2 += (x * x + y * y);
			}
			if (i + 1 != numberOfPoints) {
				solution2.append(x);
				solution2.append("\n");
				solution2.append(y);
				solution2.append('\n');
				solution2.append('\n');
			}
		}
		return distToZeroSol2;
	}

	private static double solution1(StringBuffer solution1, double radius, double distToZeroSol1) {
		double winkelSum = 0;
		solution1.append("0");
		solution1.append("\n");
		solution1.append("0");
		solution1.append("\n");
		solution1.append("\n");
		solution1.append("0");
		solution1.append("\n");
		solution1.append(lengthOfSides[0]);
		solution1.append("\n");
		solution1.append("\n");

		double mitteX = radius * radius - lengthOfSides[0] / 2.0 * lengthOfSides[0] / 2.0;
		mitteX = Math.sqrt(mitteX);
		double mitteY = lengthOfSides[0] / 2.0;
		winkelSum = 3 / 2.0 * Math.PI;
		winkelSum += Math.asin(lengthOfSides[0] / (2 * radius));
		for (int i = 1; i < numberOfPoints; i++) {
			double winkel = 2 * Math.asin(lengthOfSides[i] / (2 * radius));

			winkelSum += winkel;
			if (winkelSum >= 2 * Math.PI) {
				winkelSum -= 2 * Math.PI;
			}
			double x = mitteX + radius * Math.sin(winkelSum);
			double y = mitteY + radius * Math.cos(winkelSum);
			if (i + 1 == numberOfPoints) {
				distToZeroSol1 += (x * x + y * y);
			}
			if (i + 1 != numberOfPoints) {
				solution1.append(x);
				solution1.append("\n");
				solution1.append(y);
				solution1.append('\n');
				solution1.append('\n');
			}
		}
		return distToZeroSol1;
	}

	private static double findRadiusByBisection() {
		double time1 = System.currentTimeMillis();
		double time2 = System.currentTimeMillis();

		double radius = 0;

		double leftBound = maxR / 2.0;
		double rightBound = 0;
		double sumR = 0;
		for (int i = 0; i < numberOfPoints; i++) {
			sumR += lengthOfSides[i];
		}
		rightBound = sumR;
		long count = 0;
		System.out.println(calcSum(leftBound) + " " + calcSum(rightBound));

		while (count <= maxIteration && (time2 - time1) / 1000 < maxTime) {

			radius = leftBound + (rightBound - leftBound) / 2.0;
			double calcSum = calcSum(radius);
			if (Math.abs(calcSum) < epsilon) {
				return radius;
			}
			if (Math.signum(calcSum(radius)) == Math.signum(calcSum(leftBound))) {
				leftBound = radius;
			} else {
				rightBound = radius;
			}
			count++;
		}
		return radius;

	}

	private static double calcSum(double rnplus1) {
		double sum = 0;
		for (int i = 0; i < numberOfPoints; i++) {
			double dummy = lengthOfSides[i] / (2.0 * rnplus1);
			if (Math.abs(dummy) > 1) {
				return 1;
			}
			sum += Math.asin(dummy);
		}
		sum -= Math.PI;
		return sum;
	}

}