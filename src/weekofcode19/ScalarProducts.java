package weekofcode19;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * https://www.hackerrank.com/contests/w19/challenges/scalar-products
 * 
 * @author christoph
 * 
 */
/*
 * Not cleaned because I do not know what I did here.
 */
public class ScalarProducts {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int C = sc.nextInt();
		int M = sc.nextInt();
		int N = sc.nextInt();
		ScalarProducts scalar = new ScalarProducts();
		scalar.calcScalarProd(C, M, N);
		sc.close();
	}

	private void calcScalarProd(int C, int M, int N) {
		Set<Long> residues = new HashSet<Long>();
		Set<Folge> folge = new HashSet<Folge>();

		if (C % M == 0) {
			System.out.println(1);
			return;
		}

		long v0 = 0;
		long v1 = C % M;
		long preprev = v0;
		long prev = v1;
		int maxN = N;
		boolean maxNset = false;
		for (int i = 2; i < N + 2 && i < maxN + 2; i++) {
			long vi = (preprev + prev) % M;
			long vi1 = (vi + prev) % M;
			preprev = vi;
			prev = vi1;

			Folge f = new Folge(vi, vi1);

			if (!folge.contains(f)) {
				folge.add(f);
			} else {

				long scalarProduct = (vi * vi + vi1 * vi1) % M;
				if (!maxNset) {
					maxNset = true;
					maxN = 2 * i + 2;
				}
				residues.add(scalarProduct);

			}

		}

		Iterator<Folge> it1 = folge.iterator();
		Iterator<Folge> it2 = folge.iterator();

		while (it1.hasNext()) {
			Folge f1 = it1.next();
			it2.next();
			while (it2.hasNext()) {
				Folge f2 = it2.next();
				long scalarProduct = (f1.getV1() * f2.getV1() + f1.getV2() * f2.getV2()) % M;
				residues.add(scalarProduct);
				if (residues.size() == M) {
					System.out.println(0);
					return;
				}
			}
			folge.remove(f1);
			it1 = folge.iterator();
			it2 = folge.iterator();
		}

		System.out.println(residues.size() % M);

	}

	private class Folge {
		long v1;
		long v2;

		public Folge(long v1, long v2) {
			this.v1 = v1;
			this.v2 = v2;
		}

		public long getV1() {
			return v1;
		}

		public void setV1(long v1) {
			this.v1 = v1;
		}

		public long getV2() {
			return v2;
		}

		public void setV2(long v2) {
			this.v2 = v2;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + (int) (v1 ^ (v1 >>> 32));
			result = prime * result + (int) (v2 ^ (v2 >>> 32));
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
			Folge other = (Folge) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (v1 != other.v1)
				return false;
			if (v2 != other.v2)
				return false;
			return true;
		}

		private ScalarProducts getOuterType() {
			return ScalarProducts.this;
		}

	}
}