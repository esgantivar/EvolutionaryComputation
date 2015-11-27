package evolution.individual;

import java.util.ArrayList;
import java.util.List;

import evolution.individual.gp.Node;
import evolution.individual.gp.Equation;
import evolution.operators.gp.InternalSwap;
import evolution.operators.gp.MutationEquation;
import evolution.operators.gp.MutationInternal;
import evolution.operators.gp.Swap;
import evolution.operators.gp.XOverEquation;
import fplearning.interpreter.Evaluator;
import fplearning.interpreter.GoalException;
import fplearning.interpreter.ProgramException;
import fplearning.language.LexicalException;
import fplearning.language.SyntacticalException;
import function.gp.FitnessTree;

public class EquationSpace extends Space<Equation> {

	private String[] functor;
	private int[] arityFun;
	private String[] terminal;
	@SuppressWarnings("unused")
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

	public static void main(String args[]) {
		String[][] examples = { { "geq(0,1)", "false" }, { "geq(0,0)", "true" }, { "geq(1,0)", "true" },
				{ "geq(1,1)", "true" }, { "geq(1,2)", "false" }, { "geq(2,1)", "true" }, { "geq(2,5)", "false" },
				{ "geq(5,2)", "true" }, { "geq(3,3)", "true" } };
		String[] functor = { "geq", "s" };
		int[] arityFun = { 2, 1 };
		String[] terminal = { "0", "X", "Y", "true", "false" };
		int limitEquation = 3;
		int limitTerms = 2;

		Space<Equation> g = new EquationSpace(functor, arityFun, terminal, limitEquation, limitTerms, "geq(0,1)");
		List<Individual<Equation>> parents = new ArrayList<>();
/*		for (int i = 0; i < 2; i++) {
			parents.add(new Individual<>(3, new FitnessTree(examples), g, 3));
		}

		List<Individual<Equation>> off = new XOverEquation().getIndividuals(parents);
		System.out.println("Parents: ");
		for (Individual<Equation> ind : parents) {
			System.out.println(ind.toString());
			System.out.println(ind.getFitness());
		}
		System.out.println("offsprings: ");
		for (Individual<Equation> ind : off) {
			System.out.println(ind.toString());
			System.out.println(ind.getFitness());
		}*/
		
		Individual<Equation> ind = new Individual<>(3, new FitnessTree(examples), g, 3);
		System.out.println(ind.toString());
		System.out.println(new MutationInternal((EquationSpace)g).getIndividual(ind));
		
		
	}
}
