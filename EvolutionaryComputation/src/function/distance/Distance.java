package function.distance;

import java.util.List;

import evolution.individual.*;
import function.Function;

public abstract class Distance<T> implements Function<Individual<T>>{
	public abstract Double apply(List<Individual<T>> x);
}
