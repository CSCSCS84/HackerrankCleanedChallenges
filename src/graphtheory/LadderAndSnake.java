package graphtheory;

/**
 * https://www.hackerrank.com/challenges/the-quickest-way-up
 */
import java.util.Scanner;

public class LadderAndSnake {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int numTestCases = scanner.nextInt();

		for (int t = 0; t < numTestCases; t++) {
			int ladderOrSnake[] = readInput(scanner);
			int solution = solve(ladderOrSnake);
			System.out.println(solution);
		}
	}

	private static int solve(int[] ladderOrSnake) {
		int[] minSteps = initialiseMinSteps();
		int currentPos = 1;
		for (int i = 1; i <= 1500; i++) {
			int position = currentPos;
			boolean moveback = false;
			for (int j = 1; j <= 6; j++) {
				int pos = currentPos + j;
				if (pos <= 100) {
					if (ladderOrSnake[pos] != 0) {
						pos = ladderOrSnake[pos];

					}
					if (minSteps[currentPos] + 1 < minSteps[pos]) {
						minSteps[pos] = minSteps[currentPos] + 1;
						if (pos < position) {
							position = pos;
							moveback = true;
						}
					}
				}
			}
			if (moveback) {
				currentPos = position;
			} else {
				currentPos = position + 1;
			}

		}

		if (minSteps[100] != 101) {
			return minSteps[100];
		} else {
			return -1;
		}
	}

	private static int[] initialiseMinSteps() {
		int minSteps[] = new int[100 + 1];
		minSteps[1] = 0;
		for (int a = 2; a <= 100; a++) {
			minSteps[a] = 101;
		}
		return minSteps;
	}

	private static int[] readInput(Scanner sc) {
		int ladderOrSnake[] = new int[100 + 1];
		int N = sc.nextInt();
		for (int k = 0; k < N; k++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			ladderOrSnake[start] = end;
		}

		int M = sc.nextInt();
		for (int k = 0; k < M; k++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			ladderOrSnake[start] = end;
		}
		return ladderOrSnake;
	}
}