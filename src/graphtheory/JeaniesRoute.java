package graphtheory;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Solution for https://www.hackerrank.com/challenges/jeanies-route
 * 
 * @author Christoph
 * 
 */

public class JeaniesRoute<V> {
	static Graph<Integer, Integer> graph;
	static long countCall = 0;
	static long countTwo = 0;
	static int N;
	static int K;
	static boolean[] nodesToVisit;

	public static void main(String[] args) {
		readInput();
		long solution = JeaniesRoute.solve();
		System.out.println(solution);
	}

	private static long solve() {
		removeLeafsThatDontNeedToBeVisited();
		long valueMST = calcMinimumSpanningTreeForTree();
		long valueEulerTour = 2 * valueMST;
		long maxDistance = longestPath(nodesToVisit);
		long solution = valueEulerTour - maxDistance;
		return solution;
	}

	private static void removeLeafsThatDontNeedToBeVisited() {
		int numOfRemoved = 0;
		do {
			numOfRemoved = 0;
			for (Node<Integer, Integer> node : graph.getNodes()) {
				if (node != null && !nodesToVisit[node.getNameOfNode()] && node.getEdges().size() == 1) {
					((DirectedGraph<Integer, Integer>) graph).removeNode(node);
					numOfRemoved = 1;
				}
			}
		} while (numOfRemoved > 0);
	}

	private static long calcMinimumSpanningTreeForTree() {
		long sum = 0;
		for (Node<Integer, Integer> node : graph.getNodes()) {
			for (Edge<Integer> edge : node.getEdges()) {
				sum += edge.getWeight();
			}
		}
		sum = sum / 2;
		return sum;
	}

	private static long longestPath(boolean[] nodesToVisit) {

		long longestPath = 0;

		int innerNode = findOuterNode(graph);
		int[] maxPathmaxDiameter = null;
		if (innerNode == -1) {
			longestPath = maxDistanceLessEqualTwoNodes(graph);
		} else {
			maxPathmaxDiameter = maxPathMaxDiameter(innerNode, innerNode);

			if (maxPathmaxDiameter[0] > maxPathmaxDiameter[1]) {
				longestPath = maxPathmaxDiameter[0];
			} else {
				longestPath = maxPathmaxDiameter[1];
			}
		}

		return longestPath;
	}

	private static int[] maxPathMaxDiameter(int currentNode, int lastNode) {

		if (graph.getNodes()[currentNode].getEdges().size() == 1 && lastNode != currentNode) {
			int sol[] = { 0, 0 };
			return sol;
		}

		int maxDiameter = 0;
		int maxPath1 = 0;
		int maxPath2 = 0;

		for (Edge<Integer> edge : graph.getNodes()[currentNode].getEdges()) {
			int currentTarget = edge.getTargetNode();
			int currentNodeCopy = currentNode;
			if (currentTarget != lastNode) {
				int edgeSum = 0;
				Edge<Integer> curEdge = edge;
				edgeSum += curEdge.getWeight();
				int sol[] = maxPathMaxDiameter(currentTarget, currentNodeCopy);
				if (sol[0] > maxDiameter) {
					maxDiameter = sol[0];
				}
				if (sol[1] + edgeSum > maxPath1) {
					maxPath2 = maxPath1;
					maxPath1 = sol[1] + edgeSum;
				} else if (sol[1] + edgeSum > maxPath2) {
					maxPath2 = sol[1] + edgeSum;
				}

			}

		}
		int maximalPath = maxPath1;
		int maxDiameterDummy = maxPath1 + maxPath2;

		if (maxDiameterDummy > maxDiameter) {
			maxDiameter = maxDiameterDummy;
		}
		int[] maxPathmaxDiameter = new int[2];
		maxPathmaxDiameter[0] = maxDiameter;
		maxPathmaxDiameter[1] = maximalPath;

		return maxPathmaxDiameter;
	}

	private static long maxDistanceLessEqualTwoNodes(Graph<Integer, Integer> g) {
		long maxDistance = 0;
		for (Node<Integer, Integer> node : g.getNodes()) {
			if (node.getEdges().size() == 1) {
				return node.getEdges().get(0).getWeight();
			}
		}
		return maxDistance;
	}

	private static int findOuterNode(Graph<Integer, Integer> g) {

		int innerNode = -1;
		for (Node<Integer, Integer> node : g.getNodes()) {
			if (node.getEdges().size() == 1) {
				return node.getNameOfNode();
			}
		}

		return innerNode;
	}

	private static void readInput() {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();
		graph = new DirectedGraph<Integer, Integer>(N);

		nodesToVisit = new boolean[N + 1];
		for (int i = 1; i <= K; i++) {
			nodesToVisit[sc.nextInt() - 1] = true;
		}

		while (sc.hasNext()) {
			int startNode = sc.nextInt() - 1;
			int endNode = sc.nextInt() - 1;
			int weight = sc.nextInt();
			EdgeImpl<Integer> e1 = new EdgeImpl<Integer>(startNode, endNode, weight);
			EdgeImpl<Integer> e2 = new EdgeImpl<Integer>(endNode, startNode, weight);
			if (graph.getNodes()[startNode] == null) {
				graph.getNodes()[startNode] = new NodeImpl<Integer, Integer>(startNode);
			}
			graph.getNodes()[startNode].addEdge(e1);

			if (graph.getNodes()[endNode] == null) {
				graph.getNodes()[endNode] = new NodeImpl<Integer, Integer>(endNode);
			}
			graph.getNodes()[endNode].addEdge(e2);
		}
		sc.close();
	}

	static abstract class Node<U, V> {
		protected int nameOfNode;
		protected LinkedList<Edge<V>> edges;

		public Node(int nameOfNode) {
			this.nameOfNode = nameOfNode;
			edges = new LinkedList<Edge<V>>();
		}

		public void addEdge(Edge<V> edge) {
			this.edges.add(edge);
		}

		public LinkedList<Edge<V>> getEdges() {
			return edges;
		}

		public void setEdges(LinkedList<Edge<V>> edges) {
			this.edges = edges;
		}

		public int getNameOfNode() {
			return this.nameOfNode;
		}

		public void removeEdge(Edge<V> edgeToRemove) {
			this.edges.remove(edgeToRemove);
		}

	}

	static abstract class Edge<V> {

		protected int sourceNode;
		protected int targetNode;
		protected V weight;

		public Edge(int sourceNode, int targetNode, V weight) {
			this.sourceNode = sourceNode;
			this.targetNode = targetNode;
			this.weight = weight;
		}

		public Edge(int sourceNode, int targetNode) {
			this.sourceNode = sourceNode;
			this.targetNode = targetNode;
		}

		public int getSourceNode() {
			return sourceNode;
		}

		public void setSourceNode(int sourceNode) {
			this.sourceNode = sourceNode;
		}

		public int getTargetNode() {
			return targetNode;
		}

		public void setTargetNode(int targetNode) {
			this.targetNode = targetNode;
		}

		public V getWeight() {
			return weight;
		}

		public void setWeight(V weight) {
			this.weight = weight;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + sourceNode;
			result = prime * result + targetNode;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Edge other = (Edge) obj;
			if (sourceNode != other.sourceNode)
				return false;
			if (targetNode != other.targetNode)
				return false;
			return true;
		}

	}

	static abstract class Graph<U, V> {

		protected int numOfNodes;
		protected Node<U, V>[] nodes;

		public Graph(int numOfNodes) {
			this.numOfNodes = numOfNodes;
		}

		public void setNode(Node<U, V> node) {
			int nameOfNode = node.getNameOfNode();
			nodes[nameOfNode] = node;
		}

		public int getNumOfNodes() {
			return numOfNodes;
		}

		public void setNumOfNodes(int numOfNodes) {
			this.numOfNodes = numOfNodes;
		}

		public Node<U, V>[] getNodes() {
			return nodes;
		}

	}

	static class DirectedGraph<U, V> extends Graph<U, V> {

		public DirectedGraph(int numOfNodes) {
			super(numOfNodes);
			super.nodes = new Node[numOfNodes];
		}

		public void removeNode(Node<U, V> node) {
			for (Edge<V> edge : node.getEdges()) {
				int targetNode = edge.getTargetNode();
				this.removeEdge(new EdgeImpl<V>(targetNode, node.getNameOfNode()), this.nodes[targetNode]);
			}
			node.setEdges(new LinkedList<Edge<V>>());
		}

		private void removeEdge(Edge<V> edge, Node<U, V> node) {
			node.removeEdge(edge);
		}

	}

	static class NodeImpl<U, V> extends Node<U, V> {

		public NodeImpl(int nameOfNode) {
			super(nameOfNode);
		}
	}

	static class EdgeImpl<V> extends Edge<V> {

		public EdgeImpl(int sourceNode, int targetNode, V weight) {
			super(sourceNode, targetNode, weight);
		}

		public EdgeImpl(int sourceNode, int targetNode) {
			super(sourceNode, targetNode);
		}
	}

}
