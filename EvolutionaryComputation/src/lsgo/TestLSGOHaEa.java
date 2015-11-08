package lsgo;



import FunctionLSGO.ShiftedEllipticFunction;
import unalcol.descriptors.Descriptors;
import unalcol.descriptors.WriteDescriptors;
import unalcol.evolution.haea.HAEA;
import unalcol.evolution.haea.HaeaOperators;
import unalcol.evolution.haea.HaeaStep;
import unalcol.evolution.haea.SimpleHaeaOperators;
import unalcol.evolution.haea.SimpleHaeaOperatorsDescriptor;
import unalcol.evolution.haea.WriteHaeaStep;
import unalcol.io.Write;
import unalcol.optimization.OptimizationFunction;
import unalcol.optimization.OptimizationGoal;
import unalcol.optimization.real.HyperCube;
import unalcol.optimization.real.mutation.AdaptMutationIntensity;
import unalcol.optimization.real.mutation.IntensityMutation;
import unalcol.optimization.real.mutation.OneFifthRule;
import unalcol.optimization.real.mutation.PermutationPick;
import unalcol.optimization.real.mutation.PickComponents;
import unalcol.optimization.real.testbed.Schwefel;
import unalcol.optimization.real.xover.LinearXOver;
import unalcol.random.real.DoubleGenerator;
import unalcol.random.real.SimplestSymmetricPowerLawGenerator;
import unalcol.search.Goal;
import unalcol.search.Solution;
import unalcol.search.population.PopulationSolution;
import unalcol.search.population.PopulationSolutionDescriptors;
import unalcol.search.population.variation.ArityTwo;
import unalcol.search.population.variation.Operator;
import unalcol.search.selection.Tournament;
import unalcol.search.space.Space;
import unalcol.tracer.ConsoleTracer;
import unalcol.tracer.Tracer;
import unalcol.types.real.array.DoubleArray;
import unalcol.types.real.array.DoubleArrayPlainWrite;


public class TestLSGOHaEa {

	public static void main(String[] args) {
		real();
	}

	
	public static void real(){
		// Search Space definition
		int DIM = 1;
		double[] min = DoubleArray.create(DIM, -100);
		double[] max = DoubleArray.create(DIM, 100);
    	Space<double[]> space = new HyperCube( min, max );    	
    	
    	// Optimization Function
    	OptimizationFunction<double[]> function = new ShiftedEllipticFunction(DIM);
    	//OptimizationFunction<double[]> function = new Schwefel();
        Goal<double[]> goal = new OptimizationGoal<double[]>(function); // minimizing, add the parameter false if maximizing   	
    	
    	// Variation definition
    	DoubleGenerator random = new SimplestSymmetricPowerLawGenerator(); // It can be set to Gaussian or other symmetric number generator (centered in zero)
    	PickComponents pick = new PermutationPick(DIM/2); // It can be set to null if the mutation operator is applied to every component of the solution array
    	AdaptMutationIntensity adapt = new OneFifthRule(500, 0.9); // It can be set to null if no mutation adaptation is required
    	IntensityMutation mutation = new IntensityMutation( 0.1, random, pick, adapt );
    	ArityTwo<double[]> xover = new LinearXOver();
    	
        // Search method
        int POPSIZE = 100;
        int MAXITERS = 10000;
		@SuppressWarnings("unchecked")
		Operator<double[]>[] opers = (Operator<double[]>[])new Operator[2];
    	opers[0] = mutation;
    	opers[1] = xover;
    	HaeaOperators<double[]> operators = new SimpleHaeaOperators<double[]>(opers);
        HAEA<double[]> search = new HAEA<double[]>(POPSIZE, operators, new Tournament<double[]>(4), MAXITERS );

        // Tracking the goal evaluations
        WriteDescriptors write_desc = new WriteDescriptors();
        Write.set(double[].class, new DoubleArrayPlainWrite(false));
        Write.set(HaeaStep.class, new WriteHaeaStep<double[]>());
        Descriptors.set(PopulationSolution.class, new PopulationSolutionDescriptors<double[]>());
        Descriptors.set(HaeaOperators.class, new SimpleHaeaOperatorsDescriptor<double[]>());
        Write.set(HaeaOperators.class, write_desc);

        ConsoleTracer tracer = new ConsoleTracer();       
        //Tracer.addTracer(goal, tracer);  // Uncomment if you want to trace the function evaluations
        Tracer.addTracer(search, tracer); // Uncomment if you want to trace the hill-climbing algorithm
        
        // Apply the search method
        Solution<double[]> solution = search.apply(space, goal);
        //System.out.println( solution.quality() + "=" + solution.value());
        System.out.println(solution.quality());		
	}

	

}
