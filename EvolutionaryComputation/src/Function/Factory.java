package Function;

public class Factory {
	public static int DIM = 1000;
	public static Function<Double> CEC2013_LSGO(String function){
		switch (function) {
		case "f1": return new ShiftedElliptic(DIM);
		case "f2": return new ShiftedRastrigins(DIM);
		case "f3": return new ShiftedAckleys(DIM);
		case "f4": return new ShiftedandRotatedElliptic(DIM);
		case "f5": return new ShiftedandRotatedRastrigins(DIM);
		default: return new ShiftedElliptic(DIM);
		}
	}

}
