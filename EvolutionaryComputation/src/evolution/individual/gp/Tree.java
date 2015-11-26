package evolution.individual.gp;

import java.util.List;

public class Tree {
	private Node root;
	int countChild = 0;

	public Tree(String rootData, int type) {
		root = new Node(rootData, type);
	}

	public Tree(Node n) {
		root = new Node(n);
	}

	public Tree(Tree t) {
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
	
	public Node getChild(int index){
		return root.getChild(index);
	}
	
	public List<Node> getChildren(){
		return root.getChildren();
	}
	
	public int getType(){
		return root.type;
	}
	
	public int numChildren(){
		return countChild;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(root.toString());
		return str.toString();
	}

	
	
}
