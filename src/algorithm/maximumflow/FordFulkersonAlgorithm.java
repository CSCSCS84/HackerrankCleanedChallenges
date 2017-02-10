package algorithm.maximumflow;

import java.util.LinkedList;

public class FordFulkersonAlgorithm {
	static boolean bfs(int rGraph[][], int s, int t, int parent[]) {
		int V = rGraph.length;

		boolean visited[] = new boolean[V];
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(s);
		visited[s] = true;
		parent[s] = -1;

		// Standard BFS Loop
		while (queue.size() != 0) {
			int u = queue.poll();
			for (int v = 0; v < V; v++) {
				if (rGraph[u][v] > 0 && visited[v] == false) {
					queue.add(v);
					parent[v] = u;
					visited[v] = true;
				}
			}
		}
		return (visited[t] == true);
	}

	public static int fordFulkerson(LinkedList<Short> graph[], int s, int t) {
		int V = graph.length;
		int u, v;

		int capacity[][] = new int[V][V];

		for (u = 0; u < V; u++) {
			if (graph[u] != null) {
				for (Short i : graph[u]) {
					capacity[u][i] = 1;
				}
			}
		}
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
}
