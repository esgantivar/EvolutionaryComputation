package function.gp;

import java.util.List;

import evolution.individual.gp.Equation;
import fplearning.interpreter.Evaluator;
import fplearning.interpreter.GoalException;
import fplearning.interpreter.ProgramException;
import fplearning.language.LexicalException;
import fplearning.language.SyntacticalException;
import function.Function;

public class FitnessTree implements Function<Equation> {
	private String[][] examples;

	public FitnessTree(String[][] examples_) {
		examples = examples_;
	}

	@Override
	public Double apply(List<Equation> x) {
		double fitness = 0;
		try {
			StringBuilder str = new StringBuilder();
			for (Equation t : x) {
				str.append(t.toString()).append("\n");
			}
			str.deleteCharAt(str.lastIndexOf("\n"));

			for (String[] example : examples) {
				if ((Evaluator.evalue(str.toString(), example[0])).equals(example[1])) {
					fitness++;
				}
			}
		} catch (ProgramException | GoalException | LexicalException | SyntacticalException ex) {
			fitness = Double.NEGATIVE_INFINITY;
			return -1*Double.NEGATIVE_INFINITY;
		}
		return (-1*fitness)/((double)examples.length);
	}

}
