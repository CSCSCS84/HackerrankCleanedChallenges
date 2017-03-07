package dynammicprogramming;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Algorithm for the DortmundDilemma Problem:
 * https://www.hackerrank.com/challenges/dortmund-dilemma Short description of
 * the Algorithm: Sol(n,k) denotes the Solution for n,k. G(n,k) is the number of
 * Subsets of lenght n, the contains every of the k letters
 * Difference(n,k)=G(n,k)-Sol(n,k) G(n,k)= StearlingNumberSecondKind(n,k)*k!
 * Formula for Stearling Number second kind can be found on wikipedia New for
 * algorithm is the calculation of Difference(n,k): for n=0 or k>n:
 * Difference(n,k)=0 for n=1, k=1: Difference(n,k)=1 For all other cases: for n
 * odd: Difference(n,k)=Difference(n-1,k)*k + Difference(n-1,k-1)*k for n even:
 * Difference(n,k)=Difference(n-1,k)*k + Difference(n-1,k-1)*k-
 * Difference(n/2,k)
 * 
 * @author Christoph
 * 
 */
public class DortmundDilemma {

	public long modulo;
	public String filename;
	public long factorials[];
	private long stirlingNumberSecondArt[][];
	private long[][] differenz;

	public DortmundDilemma(String filename, long modulo) {
		this.filename = filename;
		this.modulo = modulo;
	}

	public static void main(String[] args) throws FileNotFoundException {
		if (args.length == 0) {
			System.out.println("Enter a filename as input");
			return;
		}
		String filename = args[0];
		long modulo = 1000000009;
		DortmundDilemma d = new DortmundDilemma(filename, modulo);
		d.solve();

	}

	public void solve() throws FileNotFoundException {
		int[] maxNmaxK = calcMaxNandMaxK(this.filename);
		int maxN = maxNmaxK[0];
		int maxK = maxNmaxK[1];
		calcFactorials(maxN);
		calcStearlingNumbersSecondKind(maxN, maxK);
		calcDifferences(maxN, maxK);

		// alle Lösungen ausgeben
		this.printSolutions();
	}

	public void printSolutions() throws FileNotFoundException {
		File f = new File(this.filename);

		Scanner sc = new Scanner(f);
		int testCases = sc.nextInt();
		for (int t = 0; t < testCases; t++) {
			int n = sc.nextInt();
			int k = sc.nextInt();
			System.out.println((calcNumOfSubsetsKombiAlgoImproved(n, k) * calcBio26k(k)) % modulo);
		}
		sc.close();
	}

	public void calcDifferences(int maxN, int maxK) {
		this.differenz = new long[maxN + 1][maxK + 1];
		for (int i = 1; i <= maxN; i++) {
			for (int j = 1; j <= i && j <= maxK; j++) {
				this.differenz[i][j] = this.differenz(i, j) % modulo;

			}
		}
	}

	public void calcStearlingNumbersSecondKind(int maxN, int maxK) {
		stirlingNumberSecondArt = new long[maxN + 1][maxK + 1];
		stirlingNumberSecondArt[0][0] = 1;
		for (int i = 1; i < maxN + 1; i++) {
			for (int j = 1; j < maxK + 1; j++) {
				stirlingNumberSecondArt[i][j] = ((j * stirlingNumberSecondArt[i - 1][j]) % modulo + (stirlingNumberSecondArt[i - 1][j - 1])
						% modulo)
						% modulo;
			}
		}
	}

	private int[] calcMaxNandMaxK(String testfile) throws FileNotFoundException {
		File f = new File(testfile);
		Scanner sc = new Scanner(f);

		int numberOfTestCases = sc.nextInt();
		int maxN = 0;
		int maxK = 0;
		for (int i = 0; i < numberOfTestCases; i++) {
			int nextN = sc.nextInt();
			int nextK = sc.nextInt();
			if (nextN > maxN) {
				maxN = nextN;
			}
			if (nextK > maxK) {
				maxK = nextK;
			}
		}
		int[] maxNmaxK = { maxN, maxK };
		sc.close();
		return maxNmaxK;

	}

	private long calcNumOfSubsetsKombiAlgoImproved(int n, int k) {
		if (k > n) {
			return 0;
		}
		long numOfSol = 0;
		try {
			numOfSol = numberOfSolutionsWithEveryLetter(n, k);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long diff = differenz(n, k);
		if (numOfSol < diff) {
			numOfSol += modulo;
		}
		long solution = (numOfSol - diff) % modulo;

		return solution;
	}

	private long differenz(int n, int k) {
		if (differenz[n][k] != 0) {
			return differenz[n][k];
		}

		if (n == 0 || k == 0) {
			return 0;
		}
		if (n == 1 && k == 1) {
			return 1;
		}
		if (n == 1 && k > 1) {
			return 0;
		}

		if (k > n) {
			return 0;
		}
		long diff = ((k * differenz[n - 1][k]) % modulo + (k * differenz[n - 1][k - 1]) % modulo) % modulo;
		if (n % 2 == 0) {
			diff = (diff % modulo - differenz[n / 2][k] % modulo) % modulo;
		}
		diff = diff % modulo;
		differenz[n][k] = diff;
		return diff;
	}

	/**
	 * Calculates the Number Of Solutions that contain every of the k letter at
	 * least once; the Stearling Numbers Second Art for n,k has to be calculated
	 * before
	 * 
	 * @param n
	 * @param k
	 * @return
	 * @throws Exception
	 */
	private long numberOfSolutionsWithEveryLetter(int n, int k) throws Exception {

		if (!(k == 0 && n != 1) || k > n) {
			if (stirlingNumberSecondArt[n][k] == 0) {
				throw new Exception("Stearling Number Second Art Array might not have been calculated yet.");
			}
		}

		long ster = (stirlingNumberSecondArt[n][k] % modulo * factorials[k] % modulo) % modulo;
		return ster;
	}

	/**
	 * Calcs all factorials from 1 to n and stores it in factorials[]
	 */
	private void calcFactorials(int n) {
		factorials = new long[n + 1];
		factorials[0] = 1;

		for (int i = 1; i <= n; i++) {
			factorials[i] = (factorials[i - 1] * i) % modulo;
		}
	}

	/**
	 * Calcs Bionomialcoefficient n over 26
	 * 
	 * @param n
	 * @return
	 */
	private long calcBio26k(int n) {
		// uses formula Bio(n,k) = Bio(n,n-k)
		if (n < 13) {
			n = 26 - n;
		}
		long biocoefficient = 1;
		for (int i = n + 1; i <= 26; i++) {
			biocoefficient = biocoefficient * i;
		}
		for (int i = 2; i <= 26 - n; i++) {
			biocoefficient = biocoefficient / i;
		}
		return (biocoefficient) % modulo;
	}

	public String getFilename() {
		return filename;
	}
}
