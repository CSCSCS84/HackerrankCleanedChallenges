package weekOfCode21;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/contests/w21/challenges/luck-balance
 * 
 * @author Christoph
 * 
 */
public class LuckBalance {

	static int N;
	static int K;
	static int luck[];
	static int importance[];
	static int lu[];

	public static void main(String[] args) {

		readInput();
		int solution = solve();
		System.out.println(solution);

	}

	private static int solve() {
		int luckbalance = 0;
		int numberOfUnimportant = 0;
		for (int i = 0; i < N; i++) {
			if (importance[i] == 0) {
				luckbalance += lu[i];
				luck[i] = 0;
				numberOfUnimportant++;
			} else {
				luck[i] = lu[i];
			}
		}
		Arrays.sort(luck);

		for (int i = N - 1; i > N - K - 1; i--) {
			luckbalance += luck[i];
		}
		for (int i = N - K - 1; i >= numberOfUnimportant; i--) {
			luckbalance -= luck[i];
		}
		return luckbalance;
	}

	private static void readInput() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		luck = new int[N];
		importance = new int[N];
		lu = new int[N];

		for (int i = 0; i < N; i++) {
			lu[i] = sc.nextInt();
			importance[i] = sc.nextInt();
		}
		sc.close();
	}
}