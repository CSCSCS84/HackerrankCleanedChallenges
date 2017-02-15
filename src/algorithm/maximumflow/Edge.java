package algorithm.maximumflow;

/**
 * Implementation of an edge for FordFulkerson Algorithm
 * 
 * @author christoph
 * 
 * @param <T>
 */
public class Edge {

	int source;
	int target;
	long capacity;

	public Edge(int source, int target, long capacity) {
		super();
		this.source = source;
		this.target = target;
		this.capacity = capacity;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public long getCapacity() {
		return capacity;
	}

	public void setCapacity(long capacity) {
		this.capacity = capacity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + source;
		result = prime * result + target;
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
		if (source != other.source)
			return false;
		if (target != other.target)
			return false;
		return true;
	}

}
