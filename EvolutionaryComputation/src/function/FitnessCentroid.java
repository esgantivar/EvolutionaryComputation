package function;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FitnessCentroid implements Function<Double> {
	private Double[][] dataset = null;
	private Double[] distances = null;
	private double sigma;
	private double threshold;
	private Double w[];

	public FitnessCentroid(String dataPath) {
		sigma = 0.1;
		threshold = 0.1;
		String file_path = "cdatasets/" + dataPath + ".txt";
		FileReader fr;
		try {
			fr = new FileReader(file_path);
			BufferedReader textReader = new BufferedReader(fr);
			String sCurrentLine;
			ArrayList<String> ss = new ArrayList<>();
			int dim[];
			try {
				while ((sCurrentLine = textReader.readLine()) != null) {
					ss.add(sCurrentLine);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (textReader != null)
						textReader.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}

			dim = new int[ss.get(0).split(" ").length];
			int itera = 0;
			for (String string : ss.get(0).split(" ")) {
				dim[itera++] = Integer.valueOf(string);
			}
			dataset = new Double[dim[0]][dim[1]];
			distances = new Double[dim[0]];
			w = new Double[dim[0]];
			for (int i = 1; i < ss.size(); i++) {
				for (int j = 0; j < ss.get(i).split(" ").length; j++) {
					dataset[i - 1][j] = Double.valueOf(ss.get(i).split(" ")[j]);
				}
			}
		} catch (FileNotFoundException e1) {
		}

	}

	@Override
	public Double apply(List<Double> x) {
		Double coord[] = { x.get(0), x.get(1) };
		euclidean(coord);
		double f = 0.0;
		for (int i = 0; i < distances.length; i++) {
			if(distances[i]<threshold){
				f += 1.0;
			}
		}
		/*weights();
		double f = 0.0;
		double wp = 0.0;
		for (Double wi : w) {
			wp += wi;
		}
		f = wp / Math.pow(sigma, 2.0);
		double ws = 0.0;
		for (int i = 0; i < w.length; i++) {
			ws += (w[i] + Math.pow(distances[i], 1.0));
		}
		sigma = ws / wp;*/
		return -f;
	}

	private void euclidean(Double a[]) {
		for (int i = 0; i < dataset.length; i++) {
			distances[i] = Math.sqrt(Math.pow(a[0] - dataset[i][0], 2.0) + Math.pow(a[1] - dataset[i][1], 2.0));
		}
	}

	@SuppressWarnings("unused")
	private void weights() {
		int i = 0;
		for (Double dis : distances) {
			//w[i++] = (Math.exp(-((Math.pow(dis, 1.0)) / (2 * Math.pow(sigma, 2.0)))) < threshold ? 0.0 : 1.0);
			w[i++] = (Math.exp(-((Math.pow(dis, 1.0)) / (2 * Math.pow(sigma, 2.0)))));
		}
	}

}
