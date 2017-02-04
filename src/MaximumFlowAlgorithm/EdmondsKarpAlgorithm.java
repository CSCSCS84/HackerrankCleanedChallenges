package MaximumFlowAlgorithm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class EdmondsKarpAlgorithm {
	private int maxFlow(GraphEdmondsKarp graph, int source, int target) {
		initiliazeResidual(graph);

		return 0;

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
					if (v != target)
						Q.offer(v);
					else {
						// Backtrack search, and write flow
						while (parent[v] != v) {
							u = parent[v];
							edge.addWeightToResidual((M[target]));
							maxFlowValue += M[target];
							// F[v][u] -= M[target];
							v = u;
						}
						break LOOP;
					}
				}
			}
		}
		return maxFlowValue;

	}

	private void initiliazeResidual(GraphEdmondsKarp graph) {
		for (NodeEdmondsKarp node : graph.getNodes()) {
			for (EdgeEdmondKarp edge : node.getEdges()) {
				edge.setWeightResidual(0);
			}
		}
	}
}
