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
				nodeFunction.addChild(generateTerminal().getRoot());
			} else {
				nodeFunction.addChild(generateFunction(depth - 1).getRoot());
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
		String param = (Math.random() >= .5 ? "terminal" : "function");
		Equation equation = new Equation(" = ", Node.EQUATION, 2);
		Equation nodeFunction = generateFunction(0, depth - 1);
		equation.addChild(nodeFunction.getRoot());
		int index;
		if (param.equals("function") && depth > 0) {
			equation.addChild(generateFunction(nodeFunction.getChildren()).getRoot()); // function
		} else if (param.equals("terminal") && existBooleanTerminals() && (Math.random() > 0.5)) {
			while (true) {
				index = (int) (Math.random() * terminal.length);
				if (terminal[index] == "true" || terminal[index] == "false") {
					break;
				}
			}
			Equation terminal = generateTerminal(index);
			equation.addChild(terminal.getRoot());
		} else {
			int childIndex = (int) (Math.random() * nodeFunction.numChildren());
			Equation nodeTerminal = new Equation(nodeFunction.getChild(childIndex));
			equation.addChild(nodeTerminal.getRoot()); // terminal
		}
		return equation;
	}

	private boolean existBooleanTerminals() {
		boolean exist = false;
		for (String t : terminal) {
			if (t.equals("true") || t.equals("false")) {
				exist = true;
				break;
			}
		}
		return exist;
	}
}
