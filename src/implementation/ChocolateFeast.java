/**
 * https://www.hackerrank.com/challenges/chocolate-feast
 */

package implementation;

import java.util.Scanner;

public class ChocolateFeast {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		for (int a0 = 0; a0 < t; a0++) {
			int n = scanner.nextInt();
			int c = scanner.nextInt();
			int m = scanner.nextInt();
			int numberOfChocolates = solve(n, c, m);
			System.out.println(numberOfChocolates);
		}
		scanner.close();

	}

	private static int solve(int n, int c, int m) {
		int numberOfChocolates = n / c;
		int numberOfDiscountChocolates = numberOfChocolates / m;
		numberOfChocolates = numberOfChocolates + numberOfDiscountChocolates;

		int numberOfDiscountAfterAddingDiscount = numberOfChocolates / m;
		int diff = numberOfDiscountAfterAddingDiscount - numberOfDiscountChocolates;
		while (diff != 0) {
			numberOfChocolates += (diff);
			numberOfDiscountChocolates = numberOfDiscountAfterAddingDiscount;
			numberOfDiscountAfterAddingDiscount = numberOfChocolates / m;
			diff = numberOfDiscountAfterAddingDiscount - numberOfDiscountChocolates;
		}
		return numberOfChocolates;
	}
}
