/**
 * https://www.hackerrank.com/challenges/bike-racers
 * Solution: For a given T build a graph as follows: For i biker and j bike, add an edge to the graph if distance[i][j]
 * <=T. Than add node s and t to the graph. For each biker i add an edge from s to i to the graph. For each bike j add 
 * an edge from j to t graph. All edges have capacity of 1. Use Ford Fulkerson to find the maximum flow in the graph. 
 * Now use  binary search to find the minimum T so that the maximum flow of the graph is K. 
 * 
 */

package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import algorithm.maximumflow.FordFulkersonAlgorithm;


public class BikeRacers {

	static int N;
	static int M;
	static int K;
	// squared euclidean distance
	static long distanceBikerToBike[][];

	public static void main(String[] args) throws FileNotFoundException {
		String currentPath = System.getProperty("user.dir");
		String pathOfTestCaseFile = currentPath + "" + "/TestData/BikeRacers/BikeRacersTestData14.txt";
		readInput(pathOfTestCaseFile);
		long solution = solveBikeRacers();
		System.out.println(solution);

	}

	private static long solveBikeRacers() {
		long maxDistance = maxDistance();
		long low = 0;
		long right = maxDistance;
		// binarySearch
		while (low < right) {
			long middle = (right + low) / 2;
			// edges[i] stores List of edges to nodes
			LinkedList<Short>[] edges = buildGraphForDistance(middle);
			long sol = FordFulkersonAlgorithm.fordFulkerson(edges, N + M, N + M + 1);

			if (sol >= K) {
				right = middle;
			}
			if (sol < K) {
				low = middle + 1;
			}

		}
		return low;
	}

	private static long maxDistance() {
		long maxDistance = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (distanceBikerToBike[i][j] > maxDistance) {
					maxDistance = distanceBikerToBike[i][j];
				}
			}
		}
		return maxDistance;
	}

	static LinkedList<Short>[] buildGraphForDistance(long distance) {
		// s is n+m, t is n+m+1
		LinkedList<Short>[] edges = new LinkedList[N + M + 2];
		addEdgeBikerToBikes(distance, edges);
		addEdgesSourceToBiker(edges);
		addEdgesBikesToTarget(edges);
		return edges;
	}

	private static void addEdgesBikesToTarget(LinkedList<Short>[] edges) {
		for (int i = N; i < N + M; i++) {
			if (edges[i] == null) {
				edges[i] = new LinkedList<>();
			}
			edges[i].add((short) (N + M + 1));
		}
	}

	private static void addEdgesSourceToBiker(LinkedList<Short>[] edges) {
		edges[N + M] = new LinkedList<Short>();
		for (int i = 0; i < N; i++) {
			edges[N + M].add((short) (i));
		}
	}

	private static void addEdgeBikerToBikes(long distance, LinkedList<Short>[] edges) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (distanceBikerToBike[i][j] <= distance) {
					if (edges[i] == null) {
						edges[i] = new LinkedList<Short>();
					}
					edges[i].add((short) (j + N));
				}
			}
		}
	}

	static void readInput(String pathOfTestCaseFile) {

		File file = new File(pathOfTestCaseFile);
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		LinkedList<int[]> positionBikers = readPositionBikers(sc);
		LinkedList<int[]> positionBikes = readPositionBikes(sc);
		// build distance matrix
		computeDistanceBikerToBike(positionBikes, positionBikers);

	}

	private static void computeDistanceBikerToBike(LinkedList<int[]> positionBiker, LinkedList<int[]> positionBikes) {
		distanceBikerToBike = new long[N][M];
		int biker = 0;
		for (int[] posBiker : positionBiker) {
			int bike = 0;
			for (int[] posBike : positionBikes) {
				double distance = Math.pow(posBiker[0] - posBike[0], 2) + Math.pow(posBiker[1] - posBike[1], 2);
				distanceBikerToBike[biker][bike] = (long) distance;
				bike++;
			}
			biker++;
		}
	}

	private static LinkedList<int[]> readPositionBikers(Scanner sc) {
		LinkedList<int[]> positionBikers = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int[] pos = { x, y };
			positionBikers.add(pos);
		}
		return positionBikers;
	}

	private static LinkedList<int[]> readPositionBikes(Scanner sc) {
		LinkedList<int[]> positionBikes = new LinkedList<>();
		for (int i = 1; i <= M; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int[] pos = { x, y };
			positionBikes.add(pos);
		}
		return positionBikes;
	}

}
