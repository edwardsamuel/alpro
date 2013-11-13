package maximumFlow;

import java.util.HashMap;
import java.util.LinkedList;

public class DirectedGraph {
	private HashMap<Object, Node> nodes = new HashMap<>();
	private LinkedList<Edge> edges = new LinkedList<>();

	void addEdge(Object startNodeID, Object endNodeID, int capacity) {
		Node startNode;
		Node endNode;
		if (!this.nodes.containsKey(startNodeID)) {
			startNode = new Node();
			this.nodes.put(startNodeID, startNode);
		} else {
			startNode = this.nodes.get(startNodeID);
		}
		if (!this.nodes.containsKey(endNodeID)) {
			endNode = new Node();
			this.nodes.put(endNodeID, endNode);
		} else {
			endNode = this.nodes.get(endNodeID);
		}
		Edge edge = new Edge(startNodeID, endNodeID, capacity);
		startNode.addEdge(edge);
		endNode.addEdge(edge);
		this.edges.add(edge);
	}

	Node getNode(Object nodeID) {
		return this.nodes.get(nodeID);
	}

	LinkedList<Edge> getEdges() {
		return this.edges;
	}
}
