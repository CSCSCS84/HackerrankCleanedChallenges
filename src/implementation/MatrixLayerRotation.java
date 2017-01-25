/**
 * https://www.hackerrank.com/challenges/matrix-rotation-algo
 * Challenge Description: Rotate a given matrix R times in anti-clockwise direction.
 */

package implementation;

import java.util.Scanner;

public class MatrixLayerRotation {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int M = in.nextInt();
		int N = in.nextInt();
		int R = in.nextInt();

		int sol[][] = new int[M][N];

		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= N; j++) {
				int[] pos = MatrixLayerRotation.calcPosition(i, j, M, N, R);
				sol[pos[0] - 1][pos[1] - 1] = in.nextInt();
			}
		}

		printMatrix(M, N, sol);

	}

	private static int[] calcPosition(int i, int j, int M, int N, int R) {

		int distance = MatrixLayerRotation.calcDistanceToOuterRing(i, j, M, N);

		R = R % (2 * (M - 2 * distance + 2 - 1) + 2 * (N - 2 * distance + 2 - 1));

		while (R > 0) {
			// obere position
			boolean casefound = false;
			if (i == distance && j != distance) {
				int rot = 0;
				if (j - R < distance) {
					rot = j - distance;
				} else {
					rot = R;
				}
				j = j - rot;
				R = R - rot;
				casefound = true;
			}

			// linke Position
			if (j == distance && i != M - distance + 1 && !casefound) {
				int rot = 0;
				if (i + R > M - distance + 1) {
					rot = M - distance + 1 - i;

				} else {
					rot = R;
				}
				i = i + rot;
				R = R - rot;
				casefound = true;

			}
			// untere Position
			if (i == M - distance + 1 && j != N - distance + 1 && !casefound) {
				int rot = 0;
				if (j + R > N - distance + 1) {
					rot = (N - distance + 1) - j;
				} else {
					rot = R;
				}
				j = j + rot;
				R = R - rot;
				casefound = true;
			}
			// rechte position
			if (j == N - distance + 1 && i != distance && !casefound) {
				int rot = 0;
				if (i - R < distance) {
					rot = i - distance;
				} else {
					rot = R;
				}
				i = i - rot;
				R = R - rot;
				casefound = true;
			}
		}

		int position[] = { i, j };
		return position;
	}

	private static int calcDistanceToOuterRing(int i, int j, int M, int N) {

		int c = M - i + 1;
		int d = N - j + 1;
		int distanceHoricontal = Math.min(c, d);
		int distanceVertical = Math.min(i, j);
		int distance = Math.min(distanceHoricontal, distanceVertical);
		return distance;
	}

	private static void printMatrix(int M, int N, int[][] sol) {
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(sol[i][j] + " ");
			}
			System.out.println();
		}
	}
}