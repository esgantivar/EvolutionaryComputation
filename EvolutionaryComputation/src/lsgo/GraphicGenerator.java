package lsgo;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import evolution.util.Solution;
import function.Function;
import function.lsgo.Factory;

public class GraphicGenerator {
	private static Writer out = null;
	private static String name;

	public static void main(String args[]) {

		operators();
	}

	public static void operators() {
		String names[] = { "M1", "M2", "M3", "M4" };
		String titles[] = { "Poblacion Inicial", "Generacion 40", "Generacion 80", "Generacion 120", "Generacion 160",
				"Generacion 200" };
		String comp[]={"_pop_ini","40","80","120","160","200"};
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < titles.length; j++) {

				name = names[i] + comp[j]+".plt";

				openFile();
				try {
					out.write("set terminal png transparent nocrop enhanced font \"arial,8\" \n");
					out.write(
							"set key inside right top vertical Right noreverse enhanced autotitle box lt black linewidth 1.000 dashtype solid\n");
					out.write("set style data lines\n");
					out.write("set title '" + titles[j] + "'\n");
					out.write("set ylabel 'Fitness'" + "\n"
							+ "set datafile separator \";\"" + "\n" + "set grid" + "\n" + "set term pdf" + "\n");
					out.write("set output 'D:\\" + names[i] + titles[j]+".pdf'\n");
					out.write("plot 'D:\\M.Objetivo\\function." + names[i]+"DC"
							+ ".txt'  using 1:2 with lines t \"Curva "+names[i]+"\",\\\n");
					out.write("'D:\\M.Objetivo\\function." + names[i] + "DC"+comp[j]+".txt'  using 1:2 with points t \"Individuos\"\n");
				} catch (IOException e) {
				}
				closeFile();
			}
		}
	}

	public static void functions() {
		for (int i = 1; i <= 15; i++) {
			System.out.println(i);
			Function<Double> f = Factory.CEC2013_LSGO("f" + i);
			name = f.getClass().getName() + ".plt";
			openFile();
			try {
				out.write("set terminal png transparent nocrop enhanced font \"arial,8\" \n");
				out.write(
						"set key inside right top vertical Right noreverse enhanced autotitle box lt black linewidth 1.000 dashtype solid\n");
				out.write("set style data lines\n");
				out.write(
						"set title '" + f.getClass().getName().substring(14, f.getClass().getName().length()) + "'\n");
				out.write("set ylabel 'Fitness'" + "\n" + "set xlabel 'Generaciones'" + "\n"
						+ "set datafile separator \";\"" + "\n" + "set grid" + "\n" + "set term pdf" + "\n");
				out.write("set output 'D:\\f" + (i) + "(1).pdf'\n");
				out.write("plot 'D:\\10000\\" + f.getClass().getName()
						+ ".txt'  using 1:2 with lines sm acs t \"Mejor\",\\\n");
				out.write(
						"'D:\\10000\\" + f.getClass().getName() + ".txt'  using 1:4 with lines sm acs t \"Peor\",\\\n");
				out.write("'D:\\10000\\" + f.getClass().getName()
						+ ".txt'  using 1:3 with lines sm acs t \"Mediana\",\\\n");
				out.write("'D:\\10000\\" + f.getClass().getName() + ".txt'  using 1:5 with lines sm acs t \"Media\"\n");
			} catch (IOException e) {
			}
			closeFile();
		}
	}

	private static void openFile() {
		out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(name), "UTF-8"));
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
		}
	}

	private static void closeFile() {
		try {
			out.close();
		} catch (IOException ex3) {
		}
	}
}
