package MaximumFlowAlgorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class EdmondsKarpAlgorithm {

	public static void main(String[] args) throws FileNotFoundException {
		String currentPath = System.getProperty("user.dir");
		String pathOfTestCaseFile = currentPath + "" + "/TestData/EdmondsKarp/EdmondsKarpsTestData01.txt";
		File file = new File(pathOfTestCaseFile);

		Scanner sc = new Scanner(file);
		int n = sc.nextInt();
		int E = sc.nextInt();
		int s = sc.nextInt() - 1;
		int t = sc.nextInt() - 1;

		GraphEdmondsKarp graph = new GraphEdmondsKarp(n);
		for (int i = 1; i <= E; i++) {
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			int capacity = sc.nextInt();
			graph.addEdge(a, b, capacity, 0);
			graph.addEdge(b, a, 0, 0);
		}
		EdmondsKarpAlgorithm algo = new EdmondsKarpAlgorithm();
		int maxFlow = algo.maxFlow(graph, s, t);
		System.out.println(maxFlow);
		sc.close();
	}

	private int maxFlow(GraphEdmondsKarp graph, int source, int target) {
		initiliazeResidual(graph);

		return augmentGraphDFS(graph, source, target);

	}

	private int augmentGraphDFS(GraphEdmondsKarp graph, int source, int target) {
		int maxFlowValue = 0;
		int[] parent = new int[graph.getNumOfNodes()];
		Arrays.fill(parent, -1);
		parent[source] = source;
		int[] M = new int[graph.getNumOfNodes()]; // Capacity of path to node
		M[source] = Integer.MAX_VALUE;
		Queue<Integer> Q = new LinkedList<Integer>();
		Q.offer(source);
		LOOP: while (!Q.isEmpty()) {
			int u = Q.poll();
			for (EdgeEdmondKarp edge : graph.getNodes()[u].getEdges()) {
				int v = edge.getTargetNode();
				// There is available capacity,
				// and v is not seen before in search
				if (edge.weight - edge.getWeightResidual() > 0 && parent[v] == -1) {
					parent[v] = u;
					M[v] = Math.min(M[u], edge.weight - edge.getWeightResidual());
					if (v != target) {
						Q.offer(v);
					} else {
						// Backtrack search, and write flow
						while (parent[v] != v) {
							u = parent[v];
							edge.addWeightToResidual((M[target]));
							// maxFlowValue += M[target];
							// System.out.println(maxFlowValue);
							EdgeEdmondKarp edge2 = graph.getEdge(target, source);
							// F[v][u] -= M[target];
							if (edge2 == null) {
								graph.addEdge(target, source, edge.getWeight(), -M[target]);
							} else {
								edge2.addWeightToResidual(-M[target]);
							}
							v = u;
						}
						break LOOP;
					}
				}
			}
		}
		if (parent[target] != -1) { // We did not find a path to t
			int sum = 0;
			for (EdgeEdmondKarp x : graph.getNodes()[source].getEdges()) {
				sum += x.getWeight();
				// System.out.println(sum);
			}

			return sum;
		}
		return 0;
		// return maxFlowValue;

	}

	private void initiliazeResidual(GraphEdmondsKarp graph) {
		for (NodeEdmondsKarp node : graph.getNodes()) {
			for (EdgeEdmondKarp edge : node.getEdges()) {
				edge.setWeightResidual(0);
			}
		}
	}
}
