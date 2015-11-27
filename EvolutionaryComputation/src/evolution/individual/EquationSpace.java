package evolution.individual;

import java.util.List;

import evolution.individual.gp.Node;
import evolution.individual.gp.Equation;
import fplearning.interpreter.Evaluator;
import fplearning.interpreter.GoalException;
import fplearning.interpreter.ProgramException;
import fplearning.language.LexicalException;
import fplearning.language.SyntacticalException;

public class EquationSpace extends Space<Equation> {

	private String[] functor;
	private int[] arityFun;
	private String[] terminal;
	private int limitEquation;
	@SuppressWarnings("unused")
	private int limitTerms;
	private String target;

	public EquationSpace(String[] functor_, int[] arity_, String[] terminal_, int num_lines_, int num_terms_,
			String target_) {
		target = target_;
		functor = functor_;
		arityFun = arity_;
		terminal = terminal_;
		limitEquation = num_lines_;
		limitTerms = num_terms_;
	}
	
	@Override
	public int getDimension() {
		return limitEquation;
	}

	@Override
	public Equation[] getBoundaries() {
		return null;
	}

	@Override
	public Equation limitLow() {
		return null;
	}

	@Override
	public Equation limitHigh() {
		return null;
	}

	@Override
	public boolean isFeasible(Equation ind) {
		return false;
	}

	@Override
	public Equation next() {
		Equation t;
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

	public Equation generateTerminal() {
		int index = (int) (Math.random() * terminal.length);
		return generateTerminal(index);
	}

	public Equation generateTerminal(int index) {
		return new Equation(terminal[index], Node.TERMINAL, 0);
	}

	public Equation generateFunction(int depth) {
		int index = (int) (Math.random() * functor.length);
		return generateFunction(index, depth);
	}

	public Equation generateFunction(int index, int depth) {
		Equation nodeFunction = new Equation(functor[index], Node.FUNCTION, arityFun[index]);
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

	public Equation generateFunction(List<Node> param) {
		int index = (int) (Math.random() * functor.length);
		Equation nodeFunction = new Equation(functor[index], Node.FUNCTION, arityFun[index]);
		int option;
		for (int i = 0; i < arityFun[index]; i++) {
			option = (int) (Math.random() * param.size());
			nodeFunction.addChild(param.get(option));
		}
		return nodeFunction;
	}

	public Equation generateEquation(int depth) {
		int option = (Math.random() >= .5 ? 0 : 1);
		Equation nodeEquation = new Equation(" = ", Node.EQUATION, 2);
		Equation nodeFunction = generateFunction(0, depth - 1);
		nodeEquation.addChild(nodeFunction.getRoot());
		int index;
		int p = Math.random() >= .5 ? 0 : 1;
		if (p == 0) {
			while (true) {
				index = (int) (Math.random() * terminal.length);
				if (terminal[index] == "true" || terminal[index] == "false") {
					break;
				}
			}
			Equation terminal = generateTerminal(index);
			nodeEquation.getRoot().getChildren().add(terminal.getRoot()); // funct
		} else if (option == 0 && depth > 0) {
			nodeEquation.addChild(generateFunction(nodeFunction.getChildren()).getRoot()); // function
		} else {
			int childIndex = (int) (Math.random() * nodeFunction.numChildren());
			Equation nodeTerminal = new Equation(nodeFunction.getChild(childIndex));
			nodeEquation.addChild(nodeTerminal.getRoot()); // terminal
		}
		return nodeEquation;
	}
}
