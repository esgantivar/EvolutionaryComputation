package Function;

public class Factory {
	public static int DIM = 1000;
	public static Function<Double> CEC2013_LSGO(String function){
		switch (function) {
		case "f1": return new ShiftedElliptic(DIM);
		case "f2": return new ShiftedRastrigins(DIM);
		case "f3": return new ShiftedAckleys(DIM);
		case "f4": return new ShiftedandRotatedEllipticVII(DIM);
		case "f5": return new ShiftedandRotatedRastriginsVII(DIM);
		case "f6": return new ShiftedandRotatedAckleysVII(DIM);
		case "f7": return new ShiftedSchwefelsVII(DIM);
		case "f8": return new ShiftedandRotatedEllipticXX(DIM);
		case "f9": return new ShiftedandRotatedRastriginsXX(DIM);
		case "f10": return new ShiftedandRotatedAckleysXX(DIM);
		case "f11": return new ShiftedSchwefelsXX(DIM);
		case "f12": return new ShiftedRosenbrocks(DIM);
		case "f13": return new ShiftedSchwefelsConformingOverlapping(DIM);
		case "f14": return new ShiftedSchwefelsConflictingOverlapping(DIM);
		case "f15": return new ShiftedSchwefels(DIM);
		default: return new ShiftedElliptic(DIM);
		}
	}
}
