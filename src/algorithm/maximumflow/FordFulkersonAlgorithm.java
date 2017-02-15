/**
 * Implementation of FordFulkerson Algorithm using BFS
 */
package algorithm.maximumflow;

import java.util.LinkedList;
import java.util.Queue;

public class FordFulkersonAlgorithm {

	public int fordFulkerson(LinkedList<Edge> graph[], int s, int t) {
		int V = graph.length;
		int u, v;

		long[][] capacity = calculateCapacity(graph, V);
		int parent[] = new int[V];

		int max_flow = 0;

		while (bfs(capacity, s, t, parent)) {
			int path_flow = Integer.MAX_VALUE;
			for (v = t; v != s; v = parent[v]) {
				u = parent[v];
				path_flow = (int) Math.min(path_flow, capacity[u][v]);
			}

			for (v = t; v != s; v = parent[v]) {
				u = parent[v];
				capacity[u][v] -= path_flow;
				capacity[v][u] += path_flow;
			}

			max_flow += path_flow;
		}

		return max_flow;
	}

	private long[][] calculateCapacity(LinkedList<Edge>[] graph, int V) {
		int u;
		long capacity[][] = new long[V][V];

		for (u = 0; u < V; u++) {
			if (graph[u] != null) {
				for (Edge edge : graph[u]) {
					capacity[u][edge.getTarget()] = (int) edge.getCapacity();
				}
			}
		}
		return capacity;
	}

	private boolean bfs(long capacity[][], int source, int target, int parent[]) {
		int V = capacity.length;

		boolean visited[] = new boolean[V];
		visited[source] = true;

		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(source);
		parent[source] = -1;

		// Standard BFS Loop
		while (queue.size() != 0) {
			int current = queue.poll();
			for (int v = 0; v < V; v++) {
				if (capacity[current][v] > 0 && visited[v] == false) {
					queue.add(v);
					parent[v] = current;
					visited[v] = true;
				}
			}
		}
		return (visited[target] == true);
	}
}
