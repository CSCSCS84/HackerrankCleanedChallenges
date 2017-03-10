package graphtheory;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/primsmstsub
 * 
 * @author Christoph
 * 
 */
public class PrimeMinimumSpanningTree {

	static int N;
	static int M;
	static Node[] nodes;
	static int S;

	public static void main(String[] args) {

		buildInstance();
		int solution = solve();
		System.out.println(solution);

	}

	private static int solve() {
		nodes[S].setDistanceToS(0);
		boolean[] nodeIsInGraph = new boolean[N];
		nodeIsInGraph[S] = true;

		PriorityQueue<Node> pq = initialisePriorityQueue();
		int solution = 0;
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			LinkedList<Edge> edges = node.getEdges();
			int minDistanz = node.getDistanceToS();
			nodeIsInGraph[node.getNodeNumber()] = true;
			solution += minDistanz;
			for (Edge edge : edges) {
				int distanceOfEndNode = nodes[edge.getEndNode()].getDistanceToS();
				if (edge.weight < distanceOfEndNode && !nodeIsInGraph[edge.getEndNode()]) {
					pq.remove(nodes[edge.getEndNode()]);
					nodes[edge.getEndNode()].setDistanceToS(edge.weight);
					pq.add(nodes[edge.getEndNode()]);
				}

			}
		}
		return solution;
	}

	private static PriorityQueue<Node> initialisePriorityQueue() {
		PriorityQueue<Node> pq = new PriorityQueue<Node>(M, new NodeComperator());

		for (int i = 0; i < N; i++) {
			pq.add(nodes[i]);
		}
		return pq;
	}

	private static void buildInstance() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		buildGraph(sc);
		S = sc.nextInt() - 1;
		sc.close();
	}

	private static void buildGraph(Scanner sc) {
		nodes = new Node[N];
		for (int i = 0; i < N; i++) {
			Node node = new Node(i);
			node.setDistanceToS(Integer.MAX_VALUE);
			nodes[i] = node;
		}

		for (int i = 0; i < M; i++) {
			int start = sc.nextInt() - 1;
			int end = sc.nextInt() - 1;
			int weight = sc.nextInt();
			Edge edge1 = new Edge(start, end, weight);
			Edge edge2 = new Edge(end, start, weight);
			nodes[start].addEdge(edge1);
			nodes[end].addEdge(edge2);
		}
	}

	static class NodeComperator implements Comparator<PrimeMinimumSpanningTree.Node> {

		@Override
		public int compare(Node node1, Node node2) {
			if (node1.getDistanceToS() < node2.getDistanceToS()) {
				return -1;
			} else if (node1.getDistanceToS() > node2.getDistanceToS()) {
				return 1;

			}
			return 0;
		}

	}

	static class Node {
		LinkedList<Edge> edges;
		int distanceToS;
		int nodeNumber;

		public Node(int i) {
			super();
			this.edges = new LinkedList<Edge>();
			this.nodeNumber = i;
		}

		public int getNodeNumber() {
			return nodeNumber;
		}

		public void addEdge(Edge edge) {
			this.edges.add(edge);
		}

		public LinkedList<Edge> getEdges() {
			return edges;
		}

		public void setEdges(LinkedList<Edge> edges) {
			this.edges = edges;
		}

		public int getDistanceToS() {
			return distanceToS;
		}

		public void setDistanceToS(int distanceToS) {
			this.distanceToS = distanceToS;
		}

	}

	static class Edge {

		int startNode;
		int endNode;

		public Edge(int startNode, int endNode, int weight) {
			this.startNode = startNode;
			this.endNode = endNode;
			this.weight = weight;

		}

		public int getStartNode() {
			return startNode;
		}

		public void setStartNode(int startNode) {
			this.startNode = startNode;
		}

		public int getEndNode() {
			return endNode;
		}

		public void setEndNode(int endNode) {
			this.endNode = endNode;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

		int weight;

	}
}