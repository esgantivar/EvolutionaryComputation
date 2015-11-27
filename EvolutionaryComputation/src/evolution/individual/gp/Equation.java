package evolution.individual.gp;

import java.util.List;

public class Equation {
	private Node root;
	private Node left;
	private Node right;
	int countChild = 0;

	public Equation(String rootData, int type, int arity) {
		root = new Node(rootData, type, arity, null);
	}

	public Equation(Node n) {
		root = new Node(n);
	}

	public Equation(Equation t) {
		root = new Node(t.root);
	}

	public int depth() {
		return root.depth();
	}

	public Node getRoot() {
		return root;
	}

	public void addChild(Node node) {
		countChild += 1;
		root.addChild(node);
	}

	public Node getChild(int index) {
		return root.getChild(index);
	}

	public List<Node> getChildren() {
		return root.getChildren();
	}

	public int getType() {
		return root.type;
	}

	public int numChildren() {
		return root.children.size();
	}

	public int numNodes() {
		return root.numChildren() - 1;
	}

	public Node getRandomNode() {
		return getRandomNode(root, Double.valueOf(1.0 / (double)this.depth()));
	}

	private Node getRandomNode(Node node, double probability) {
		if (node.getChildren().isEmpty() || node.getType() == Node.TERMINAL) {
			return node;
		} else if (Math.random() < probability && node.getType() != Node.EQUATION) {
			return node;
		} else {
			int sele = (int) (Math.random() * node.children.size());
			
			return getRandomNode(node.children.get(sele), Double.valueOf(1.0 / (double)depth()));
		}
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(root.toString());
		return str.toString();
	}

}
