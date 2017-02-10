package algorithm.maximumflow;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class EdmondsKarpAlgorithmArray {

	static int N;
	static int M;
	static int K;
	static long distanceBikerBike[][];
	static LinkedList<Integer>[] curEdges;
	static long[][] curDistance;
	static long[][] graph;

	public static void main(String[] args) throws FileNotFoundException {
		String currentPath = System.getProperty("user.dir");
		String pathOfTestCaseFile = currentPath + "" + "/TestData/BikeRacers/BikeRacersTestData06.txt";
		readInput(pathOfTestCaseFile);
		long solution = solveBikeRacers();
		System.out.println(solution);
	}

	private static long solveBikeRacers() {
		long maxDistance = maxDistance();
		long left = 0;
		long right = maxDistance;
		long t = 0;
		while (left < right) {
			t = (right + left) / 2;
			buildGraphAndDistance(t);
			// long sol = edmondsKarp(curEdges, curDistance, N + M, N + M + 1);
			long sol = fordFulkerson(graph, N + M, N + M + 1);
			if (sol >= K) {
				right = t;
			}
			if (sol < K) {
				left = t + 1;
			}

		}
		return t;
	}

	private static long maxDistance() {
		long maxDistance = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (distanceBikerBike[i][j] > maxDistance) {
					maxDistance = distanceBikerBike[i][j];
				}
			}
		}
		return maxDistance;
	}

	static void buildGraphAndDistance(long t) {
		// s is n+m, t is n+m+1
		graph = new long[N + M + 2][N + M + 2];
		curEdges = new LinkedList[N + M + 2];
		curDistance = new long[N + M + 2][N + M + 2];
		for (int i = 0; i < N; i++) {
			curEdges[i] = new LinkedList<Integer>();
			for (int j = 0; j < M; j++) {
				if (distanceBikerBike[i][j] <= t) {
					graph[i][j + N] = 1;
					int a = j + N;
					curEdges[i].add(a);
					curDistance[i][a] = 1;
					if (curEdges[a] == null) {
						curEdges[a] = new LinkedList<>();
					}
					curEdges[a].add(i);
					curDistance[a][i] = 0;

				}
			}
		}
		curEdges[N + M] = new LinkedList<Integer>();
		for (int i = 0; i < N; i++) {
			curEdges[N + M].add(i);
			curDistance[N + M][i] = 1;
			graph[N + M][i] = 1;

			if (curEdges[i] == null) {
				curEdges[i] = new LinkedList<>();
			}
			curEdges[i].add(N + M);
			curDistance[i][N + M] = 1;

		}
		for (int i = N; i < N + M; i++) {
			curEdges[i] = new LinkedList<>();
			curEdges[i].add(N + M + 1);
			curDistance[i][N + M + 1] = 1;

			graph[i][N + M + 1] = 1;

			if (curEdges[N + M + 1] == null) {
				curEdges[N + M + 1] = new LinkedList<>();
			}
			curEdges[N + M + 1].add(i);
			curDistance[N + M + 1][i] = 1;

		}

	}

	static boolean bfs(long rGraph[][], int s, int t, int parent[]) {
		int V = rGraph.length;
		// Create a visited array and mark all vertices as not
		// visited
		boolean visited[] = new boolean[V];
		for (int i = 0; i < V; ++i)
			visited[i] = false;

		// Create a queue, enqueue source vertex and mark
		// source vertex as visited
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(s);
		visited[s] = true;
		parent[s] = -1;

		// Standard BFS Loop
		while (queue.size() != 0) {
			int u = queue.poll();

			for (int v = 0; v < V; v++) {
				if (visited[v] == false && rGraph[u][v] > 0) {
					queue.add(v);
					parent[v] = u;
					visited[v] = true;
				}
			}
		}

		// If we reached sink in BFS starting from source, then
		// return true, else false
		return (visited[t] == true);
	}

	// Returns tne maximum flow from s to t in the given graph
	static int fordFulkerson(long graph[][], int s, int t) {
		int V = graph.length;
		int u, v;

		// Create a residual graph and fill the residual graph
		// with given capacities in the original graph as
		// residual capacities in residual graph

		// Residual graph where rGraph[i][j] indicates
		// residual capacity of edge from i to j (if there
		// is an edge. If rGraph[i][j] is 0, then there is
		// not)
		long rGraph[][] = new long[V][V];

		for (u = 0; u < V; u++)
			for (v = 0; v < V; v++)
				rGraph[u][v] = graph[u][v];

		// This array is filled by BFS and to store path
		int parent[] = new int[V];

		int max_flow = 0; // There is no flow initially

		// Augment the flow while tere is path from source
		// to sink
		while (bfs(rGraph, s, t, parent)) {
			// Find minimum residual capacity of the edhes
			// along the path filled by BFS. Or we can say
			// find the maximum flow through the path found.
			int path_flow = Integer.MAX_VALUE;
			for (v = t; v != s; v = parent[v]) {
				u = parent[v];
				path_flow = (int) Math.min(path_flow, rGraph[u][v]);
			}

			// update residual capacities of the edges and
			// reverse edges along the path
			for (v = t; v != s; v = parent[v]) {
				u = parent[v];
				rGraph[u][v] -= path_flow;
				rGraph[v][u] += path_flow;
			}

			// Add path flow to overall flow
			max_flow += path_flow;
		}

		// Return the overall flow
		return max_flow;
	}

	static void readInput(String pathOfTestCaseFile) {

		File file = new File(pathOfTestCaseFile);
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		LinkedList<int[]> positionBiker = new LinkedList<>();
		LinkedList<int[]> positionBikes = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int[] pos = { x, y };
			positionBiker.add(pos);
		}
		for (int i = 1; i <= M; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int[] pos = { x, y };
			positionBikes.add(pos);
		}
		// build distance matrix
		distanceBikerBike = new long[N][M];
		int biker = 0;
		for (int[] posBiker : positionBiker) {
			int bike = 0;
			for (int[] posBike : positionBikes) {
				double distanceD = Math.pow(posBiker[0] - posBike[0], 2) + Math.pow(posBiker[1] - posBike[1], 2);
				// distanceD = Math.sqrt(distanceD);

				long dist = (long) ((distanceD));
				distanceBikerBike[biker][bike] = dist;
				bike++;

			}
			biker++;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (distanceBikerBike[i][j] <= 9418)
					System.out.println(i + " " + j);
			}
		}

	}

	private static void testEdmondKarp() throws FileNotFoundException {
		String currentPath = System.getProperty("user.dir");
		String pathOfTestCaseFile = currentPath + "" + "/TestData/EdmondsKarp/EdmondsKarpsTestData01.txt";
		File file = new File(pathOfTestCaseFile);

		Scanner sc = new Scanner(file);
		int n = sc.nextInt();
		int E = sc.nextInt();
		int s = sc.nextInt() - 1;
		int t = sc.nextInt() - 1;

		LinkedList[] edges = new LinkedList[n];
		long[][] capacities = new long[n][n];
		GraphEdmondsKarp graph = new GraphEdmondsKarp(n);
		for (int i = 1; i <= E; i++) {
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			long capacity = sc.nextLong();
			if (edges[a] == null) {
				edges[a] = new LinkedList<Integer>();
			}
			edges[a].add(b);
			if (edges[b] == null) {
				edges[b] = new LinkedList<Integer>();
			}
			// edges[b].add(a);
			capacities[a][b] = capacity;
			// capacities[b][a] = -capacity;

		}
		long maxFlow = EdmondsKarpAlgorithmArray.edmondsKarp(edges, capacities, s, t);
		System.out.println(maxFlow);
		sc.close();
	}

	public static long edmondsKarp(LinkedList[] E, long[][] C, int s, int t) {
		int n = C.length;

		// Residual capacity from u to v is C[u][v] - F[u][v]
		int[][] F = new int[n][n];
		while (true) {
			int[] P = new int[n]; // Parent table
			Arrays.fill(P, -1);
			P[s] = s;
			long[] M = new long[n]; // Capacity of path to node
			M[s] = Long.MAX_VALUE;
			// BFS queue
			Queue<Integer> Q = new LinkedList<Integer>();
			Q.offer(s);
			LOOP: while (!Q.isEmpty()) {
				int u = Q.poll();
				for (int v : (LinkedList<Integer>) E[u]) {
					// There is available capacity,
					// and v is not seen before in search
					if (C[u][v] - F[u][v] > 0 && P[v] == -1) {
						P[v] = u;
						M[v] = (int) Math.min(M[u], C[u][v] - F[u][v]);
						if (v != t) {
							Q.offer(v);
						} else {
							// Backtrack search, and write flow
							while (P[v] != v) {
								u = P[v];
								F[u][v] += M[t];
								F[v][u] -= M[t];
								v = u;
							}
							break LOOP;
						}
					}
				}
			}
			if (P[t] == -1) { // We did not find a path to t
				long sum = 0;
				for (int x : F[s]) {
					sum += x;
				}
				return sum;
			}
		}
	}
}
