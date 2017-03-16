package weekofcode19;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * https://www.hackerrank.com/contests/w19/challenges/coolguy-and-two-
 * subsequences
 * 
 * @author Christoph
 * 
 */

/*
 * Solution for this challenge. It is not cleaned because I have no idea what i
 * did here.
 */
public class CoolGuyCombinatoricAlgo {

	static long countMin[];
	static long A[];
	static long ASorted[];
	static long APos1Validabcd[];
	static long modulo = 1000000007;
	public static Elements[] elements;
	static int n;

	public static void main(String[] args) {
		readInput();

		CoolGuyCombinatoricAlgo coolguy = new CoolGuyCombinatoricAlgo();
		long sum = coolguy.solve(n);
		System.out.println(sum);
	}

	// berechnetn für position 1 alle häufigkeiten wie oft sie in lösung [a,b]
	// [c,d] vorkommt
	private static void calcAPos1(long n) {
		// TODO Auto-generated method stub
		long sum = 0;
		APos1Validabcd[0] = 0;
		for (int i = 1; i < n; i++) {
			sum += i;
			APos1Validabcd[i] = sum + APos1Validabcd[i - 1];
		}
	}

	private long solve(int n) {
		Arrays.sort(elements, new ElementsComparator());
		countMin = new long[n];
		long sumOfMins = 0;
		TreeSet<Interval> intervals = new TreeSet<Interval>(new IntervallComparator());
		Interval i1 = new Interval(0, n - 1);
		long sumM2 = 0;
		intervals.add(i1);
		sumM2 += numOfSubIntervalls(i1.getB() - i1.getA() + 1);
		for (int i = 0; i < n; i++) {

			// bestimmte position von a in ASorted[i]
			long a = elements[i].getA();
			int posa = elements[i].getIndex();

			// bestimme das intervall in dem a bisher liegt
			long leftBorder = 0;
			long rightBorder = n - 1;
			Interval idummy = null;

			if (intervals.size() <= n + 1) {
				idummy = intervals.floor(new Interval(posa, 0));
			}

			leftBorder = idummy.getA();
			rightBorder = idummy.getB();
			long lengthIntervall = rightBorder - leftBorder + 1;
			long countamin = 0;
			countamin += (numOfSolutions(posa - leftBorder + 1, lengthIntervall)) % modulo;
			long multiplikator = numOfSubSets(posa - leftBorder + 1, lengthIntervall);

			sumM2 -= numOfSubIntervalls(idummy.getB() - idummy.getA() + 1);
			intervals.remove(idummy);
			countamin = (countamin + sumM2 * multiplikator) % modulo;
			sumOfMins = (sumOfMins + (countamin * a) % modulo) % modulo;

			if (posa != leftBorder) {
				Interval inew = new Interval(leftBorder, posa - 1);
				intervals.add(inew);
				sumM2 += numOfSubIntervalls(inew.getB() - inew.getA() + 1);
			}
			if (posa != rightBorder) {
				Interval inew = new Interval(posa + 1, rightBorder);
				intervals.add(inew);
				sumM2 += numOfSubIntervalls(inew.getB() - inew.getA() + 1);
			}

		}

		return sumOfMins;
	}

	// berechnet die Anzahl an Teilintervallen, die aus einem intervall der
	// Länge length bilden können
	private long numOfSubIntervalls(long length) {
		return (length * length + length) / 2l;
	}

	private static void readInput() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		A = new long[n];
		APos1Validabcd = new long[n];
		calcAPos1(n);
		ASorted = new long[n];
		elements = new Elements[n];
		for (int i = 0; i < n; i++) {
			long a = sc.nextLong();
			A[i] = a;
			ASorted[i] = a;
			Elements e = new Elements(a, i);
			elements[i] = e;
		}
		sc.close();
	}

	private static class Interval implements Comparable<Object> {
		long a;
		long b;

		public Interval(long a, long b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (a ^ (a >>> 32));
			return result;
		}

		@Override
		public boolean equals(Object obj) {

			Interval other = (Interval) obj;

			if (a != other.a)
				return false;
			return true;
		}

		public long getA() {
			return a;
		}

		public long getB() {
			return b;
		}

		public String toString() {
			return "[" + a + "," + b + "]";
		}

		@Override
		public int compareTo(Object o) {
			if (this.a < ((Interval) o).getA()) {
				return -1;
			} else if (this.a > ((Interval) o).getA()) {
				return 1;
			} else {
				return 0;
			}
		}

	}

	// anzahl an gültigen [][] intervallen, die das array auf pos enthalten und
	// aus einem array der länge n sind
	private static long numOfSolutions(long l, long lengthIntervall) {

		long sum = 0;
		long min1 = l - 1;
		long min2 = lengthIntervall - l;
		long min = Math.min(min1, min2);

		sum = APos1Validabcd[(int) lengthIntervall - 1];
		for (int i = 1; i <= min; i++) {
			sum = (sum + APos1Validabcd[(int) (lengthIntervall - 2 * i - 1)]) % modulo;
		}

		return sum;

	}

	// anzahl der teilintervalle eines intervalls mit der lenge lengthIntervall,
	// die l enthalten ;
	private static long numOfSubSets(long l, long lengthIntervall) {

		long sum = 0;
		long min1 = l - 1;
		long min2 = lengthIntervall - l;
		long min = Math.min(min1, min2);
		sum = lengthIntervall;
		for (int i = 1; i <= min; i++) {
			sum = (sum + lengthIntervall - 2l * (long) i);
		}
		return sum;
	}

	static class Elements {

		public long a;
		public int index;

		public Elements(long a, int index) {
			this.a = a;
			this.index = index;
		}

		public long getA() {
			return a;
		}

		public void setA(long a) {
			this.a = a;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	}

	static class IntervallComparator implements Comparator<Interval> {

		@Override
		public int compare(Interval e1, Interval e2) {
			if (e1.getA() < e2.getA()) {
				return -1;
			} else if (e1.getA() > e2.getA()) {
				return 1;
			} else {
				return 0;
			}
		}

	}

	static class ElementsComparator implements Comparator<Elements> {

		@Override
		public int compare(Elements e1, Elements e2) {
			if (e1.getA() < e2.getA()) {
				return -1;
			} else if (e1.getA() > e2.getA()) {
				return 1;
			} else {
				return 0;
			}
		}
	}

}
