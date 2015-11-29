package evolution.individual.gp;

import java.util.ArrayList;
import java.util.List;

public class Node{
	public static final int FUNCTION = 0;
	public static final int EQUATION = 1;
	public static final int TERMINAL = 2;

	protected String value;
	protected Node parent = null;
	protected int type;
	protected int arity;
	protected List<Node> children = null;

	public Node() {
		children = new ArrayList<>();
	}

	public Node(String value_, int type_, int arity_, Node parent_) {
		value = value_;
		type = type_;
		children = new ArrayList<>();
		arity = arity_;
		if (parent != null)
			parent = new Node(parent_);
	}

	public Node(final Node n) {
		value = n.value;
		type = n.type;
		arity = n.arity;
		children = new ArrayList<>();
		for (Node child : n.children) {
			children.add(child.clone());
		}
		if (n.parent != null)
			parent = n.parent;
	}

	public Node(String value_, Node parent_, int type_, List<Node> children_, int arity_) {
		value = value_;
		type = type_;
		arity = arity_;
		children = new ArrayList<>();
		for (Node node : children_) {
			children.add(node.clone());
		}
		if(parent != null){
		parent = new Node(parent_);}
	}

	public String getValue() {
		return value;
	}

	public Node getParent() {
		return parent;
	}

	public int getArity() {
		return arity;
	}

	public int getType() {
		return type;
	}

	public List<Node> getChildren() {
		return children;
	}

	public Node getChild(int index) {
		if (children == null)
			return null;
		return children.get(index);
	}

	public void setType(int type_) {
		type = type_;
	}

	public void setParent(Node parent_) {
		parent = new Node(parent_);
	}

	public void setValue(String value_) {
		value = value_;
	}

	public void addChild(Node child) {
		children.add(child.clone());
	}

	public void addChildren(List<Node> children_) {
		children = new ArrayList<>();
		for (Node node : children_) {
			children.add(node.clone());
		}
	}

	public void replace(Node node) {
		value = node.value;
		type = node.type;
		children = new ArrayList<>();
		for (Node child : node.children) {
			children.add(child.clone());
		}
		if (node.parent != null)
			parent = node.parent;
	}

	public int depth() {
		int dephs[] = new int[children.size()];
		int max = 0;

		if (children == null) {
			return 0;
		}

		for (int i = 0; i < children.size(); i++) {
			dephs[i] = children.get(i).depth();
		}

		for (int i = 0; i < dephs.length; i++) {
			if (dephs[i] > max) {
				max = dephs[i];
			}
		}

		return 1 + max;
	}

	public int numTerms() {
		int num = 0;
		if (children == null) {
			return 1;
		}
		for (int i = 0; i < children.size(); i++) {
			num += children.get(i).numTerms();
		}
		return 1 + num;
	}
	@Override
	public Node clone(){
		return new Node(this.value,null,this.type,this.children,this.arity);
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		if (type == FUNCTION) {
			str.append(value);
			str.append("(");
			for (Node child : children) {
				str.append(child.toString()).append(",");
			}
			str.deleteCharAt(str.lastIndexOf(","));
			str.append(")");
		} else if (type == EQUATION) {
			str.append(children.get(0).toString());
			str.append(value);
			str.append(children.get(1).toString());
			str.append(";");
		} else if (type == TERMINAL) {
			str.append(value);
		}
		return str.toString();
	}
}
