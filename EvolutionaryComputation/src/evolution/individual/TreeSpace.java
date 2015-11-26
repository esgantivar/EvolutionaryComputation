package evolution.individual;

import java.util.List;

import evolution.individual.gp.Node;
import evolution.individual.gp.Tree;
import fplearning.interpreter.Evaluator;
import fplearning.interpreter.GoalException;
import fplearning.interpreter.ProgramException;
import fplearning.language.LexicalException;
import fplearning.language.SyntacticalException;
import function.gp.FitnessTree;

public class TreeSpace extends Space<Tree> {

	private String[] functor;
	private int[] arityFun;
	private String[] terminal;
	@SuppressWarnings("unused")
	private int limitEquation;
	@SuppressWarnings("unused")
	private int limitTerms;
	private String target;

	public TreeSpace(String[] functor_, int[] arity_, String[] terminal_, int num_lines_, int num_terms_, String target_) {
		target = target_;
		functor = functor_;
		arityFun = arity_;
		terminal = terminal_;
		limitEquation = num_lines_;
		limitTerms = num_terms_;
	}

	@Override
	public Tree[] getBoundaries() {
		return null;
	}

	@Override
	public Tree limitLow() {
		return null;
	}

	@Override
	public Tree limitHigh() {
		return null;
	}

	@Override
	public boolean isFeasible(Tree ind) {
		return false;
	}

	@Override
	public Tree next() {
		Tree t;
		while (true) {
			t = generateEquation(2);
			try {
				Evaluator.evalue(t.toString(), target);
				break;
			} catch (LexicalException | SyntacticalException | ProgramException | GoalException e) {
			}
		}
		return t;
	}

	public Tree generateTerminal() {
		int index = (int) (Math.random() * terminal.length);
		return generateTerminal(index);
	}

	public Tree generateTerminal(int index) {
		return new Tree(terminal[index], Node.TERMINAL);
	}

	public Tree generateFunction(int depth) {
		int index = (int) (Math.random() * functor.length);
		return generateFunction(index, depth);
	}

	public Tree generateFunction(int index, int depth) {
		Tree nodeFunction = new Tree(functor[index], Node.FUNCTION);
		int option;
		for (int i = 0; i < arityFun[index]; i++) {
			option = Math.random() >= .5 ? 0 : 1;
			if (option == 0 || depth < 1) {
				nodeFunction.getRoot().getChildren().add(generateTerminal().getRoot());
			} else {
				nodeFunction.getRoot().getChildren().add(generateFunction(depth - 1).getRoot());
			}
		}
		return nodeFunction;
	}

	public Tree generateFunction(List<Node> param) {
		int index = (int) (Math.random() * functor.length);
		Tree nodeFunction = new Tree(functor[index], Node.FUNCTION);
		int option;
		for (int i = 0; i < arityFun[index]; i++) {
			option = (int) (Math.random() * param.size());
			nodeFunction.addChild(param.get(option));
		}
		return nodeFunction;
	}

	public Tree generateEquation(int depth) {
		int option = (Math.random() >= .5 ? 0 : 1);
		Tree nodeEquation = new Tree(" = ", Node.EQUATION);
		Tree nodeFunction = generateFunction(0, depth - 1);
		nodeEquation.addChild(nodeFunction.getRoot());
		if (option == 0 && depth > 0) {
			nodeEquation.addChild(generateFunction(nodeFunction.getChildren()).getRoot()); // function
		} else {
			int childIndex = (int) (Math.random() * nodeFunction.numChildren());
			Tree nodeTerminal = new Tree(nodeFunction.getChild(childIndex));
			nodeEquation.addChild(nodeTerminal.getRoot()); // terminal
		}
		return nodeEquation;
	}

	public static void main(String args[]) {
		String[][] examples = { { "geq(0,1)", "false" }, { "geq(0,0)", "true" }, { "geq(1,0)", "true" },
				{ "geq(1,1)", "true" }, { "geq(1,2)", "false" }, { "geq(2,1)", "true" }, { "geq(2,5)", "false" },
				{ "geq(5,2)", "true" }, { "geq(3,3)", "true" } };
		String[] functor = { "geq", "s" };
		int[] arityFun = { 2, 1 };
		String[] terminal = { "0", "X", "Y", "true", "false" };
		int limitEquation = 3;
		int limitTerms = 2;

		Space<Tree> g = new TreeSpace(functor, arityFun, terminal, limitEquation, limitTerms,"geq(0,1)");
		for (int i = 0; i < 100; i++) {
			Individual<Tree> ind = new Individual<>(3, new FitnessTree(examples), g, 3);
			System.out.println(ind.computeFitness());
		}		
	}
}
