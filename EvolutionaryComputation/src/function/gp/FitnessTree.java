package function.gp;

import java.util.List;

import evolution.individual.gp.Tree;
import fplearning.interpreter.Evaluator;
import fplearning.interpreter.GoalException;
import fplearning.interpreter.ProgramException;
import fplearning.language.LexicalException;
import fplearning.language.SyntacticalException;
import function.Function;

public class FitnessTree implements Function<Tree> {
	private String[][] examples;

	public FitnessTree(String[][] examples_) {
		examples = examples_;
	}

	@Override
	public Double apply(List<Tree> x) {
		double fitness = 0;
		try {
			StringBuilder str = new StringBuilder();
			for (Tree t : x) {
				str.append(t.toString()).append("\n");
			}
			str.deleteCharAt(str.lastIndexOf("\n"));

			for (String[] example : examples) {
				if ((Evaluator.evalue(str.toString(), example[0])).equals(example[1])) {
					fitness++;
				} else {
					fitness--;
				}
			}
		} catch (ProgramException | GoalException | LexicalException | SyntacticalException ex) {
			fitness -= 20;
		}
		return fitness;
	}

}
