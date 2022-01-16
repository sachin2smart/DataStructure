package in.sachinshinde.graph;

public class Node {
	private int u;
	private int v;
	private int weight;
	
	public Node(int u, int v, int weight) {
		super();
		this.u = u;
		this.v = v;
		this.weight = weight;
	}

	public int getU() {
		return u;
	}

	public int getV() {
		return v;
	}

	public int getWeight() {
		return weight;
	}
	
}
