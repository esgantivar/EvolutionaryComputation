package lsgo;

import java.util.ArrayList;
import java.util.List;

import evolution.AlgorithmSPEA;
import evolution.individual.RealSpace;
import evolution.individual.Space;
import evolution.operators.AdaptMutationIntensity;
import evolution.operators.GaussianMutation;
import evolution.operators.LinearXOver;
import evolution.operators.OneFifthRule;
import evolution.operators.Operator;
import evolution.selectors.Roulette;
import evolution.selectors.Selector;
import function.F1;
import function.F2;
import function.Function;
import function.MultiObjetiveFunction;

public class TestM {
	public static void main(String args[]) {
		int nPop_ = 100;
		int nPareto_ = 1000;
		int maxIteration_ = 100;
		AdaptMutationIntensity adapt = new OneFifthRule(100, 0.5);
		double min_ = -6;
		double max_ = 6;
		int DIM_ = 1;
		Space<Double> space_ = new RealSpace(min_, max_, DIM_);
		List<Operator<Double>> operators_ = new ArrayList<>();
		operators_.add(new LinearXOver());
		operators_.add(new GaussianMutation(0.5, adapt,space_));
		List<Function<Double>> funs = new ArrayList<>();
		funs.add(new F1());
		funs.add(new F2());
		MultiObjetiveFunction mFunction_ = new MultiObjetiveFunction(funs);
		List<List<Double>> list = new ArrayList<>();
	
		List<Double> b0 = new ArrayList<>();	
		List<Double> b1 = new ArrayList<>();
		List<Double> b2= new ArrayList<>();
		List<Double> b3 = new ArrayList<>();
		List<Double> b4 = new ArrayList<>();
		List<Double> b5 = new ArrayList<>();
		List<Double> b6 = new ArrayList<>();
		List<Double> b7 = new ArrayList<>();
		List<Double> b8 = new ArrayList<>();
		List<Double> b9 = new ArrayList<>();
		b0.add(-3.82023815609064);
		b1.add(-2.3174433560791647);
		b2.add(-4.934911035949218);
		b3.add(0.6768238565260063);
		b4.add(5.517645278872594);
		b5.add(3.5664942376636724);
		b6.add(-0.18199661172601544);
		b7.add(2.4266328062996685);
		b8.add(-5.046742249441055);
		b9.add(-4.976897472116424);

		list.add(b0);
		list.add(b1);
		list.add(b2);
		list.add(b3);
		list.add(b4);
		list.add(b5);
		list.add(b6);
		list.add(b7);
		list.add(b8);
		list.add(b9);
		
		
		
	
		

		AlgorithmSPEA<Double> search = new AlgorithmSPEA<>(nPop_, nPareto_, maxIteration_, operators_,
		space_, mFunction_);
		
		search.iterate();
		
	}

}
