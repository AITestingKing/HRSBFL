

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.Map.Entry;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Readaefanfaepanp {
	// begin
	// 1.Naish公式
	public static double Naish(double aef, double F, double P, double aep) {
		if (aef < F) {
			return -1;
		} else {
			return P - aep;
		}
	}

	// 2.Naish2公式
	public static double Naish2(double aef, double aep, double anp) {
		return aef - (aep / (aep + anp + 1));
	}

	// 3.Jaccard公式
	public static double Jaccard(double aef, double anf, double aep) {
		return aef / (aef + anf + aep);
	}

	// 4.Anderberg公式
	public static double Anderberg(double aef, double anf, double aep) {
		return aef / (aef + 2 * (anf + aep));
	}

	// 5.Srensen-Dice公式
	public static double Srensen_Dice(double anf, double aef, double aep) {
		return 2 * aef / (2 * aef + anf + aep);
	}

	// 6.Dice公式
	public static double Dice(double aef, double anf, double aep) {
		return 2 * aef / (aef + anf + aep);
	}

	// 7.Goodman公式
	public static double Goodman(double aef, double anf, double aep) {
		return (2 * aef - anf - aep) / (2 * aef + anf + aep);
	}

	// 8.Tarantula公式
	public static double Tarantula(double aef, double anf, double aep, double anp) {
		return (aef / (aef + anf)) / ((aef / (aef + anf)) + (aep / (aep + anp)));
	}

	// 9.qe公式
	public static double qe(double aef, double aep) {
		return aef / (aef + aep);
	}

	// 10.CBI公式
	public static double CBI(double aef, double aep, double anf, double anp) {
		if (aef == 0) {
			return -anf / (anf + aef + anp + aef);
		} else {
			return (aef / (aef + aep)) - ((aef + anf) / (aef + anf + aep + anp));
		}
	}

	// 11.Wong2公式
	public static double Wong2(double aef, double aep) {
		return aef - aep;
	}

	// 12.Hamann公式
	public static double Hamann(double aef, double anp, double anf, double aep) {
		return (aef + anp - anf - aep) / (aef + anf + aep + anp);
	}

	// 13.Simple Matching
	public static double SimpleMatching(double aef, double anp, double anf, double aep) {
		return (aef + anp) / (aef + anf + aep + anp);
	}

	// 14.Sokal
	public static double Sokal(double aef, double anp, double anf,double aep) {
		return (2 * (aef + anp)) / (2 * (aef + anp) + anf + aep);
	}

	// 15.Rogers&Tanimoto
	public static double RogersTanimoto(double aef, double anp, double anf, double aep) {
		return (aef + anp) / (aef + anp + 2 * (anf + aep));
	}

	// 16.Hamming etc
	public static double Hamming(double aef, double anp) {
		return aef + anp;
	}

	// 17.Euclid
	public static double Euclid(double aef, double anp) {
		return Math.sqrt(aef + anp);
	}

	// 18.Wong1
	public static double Wong1(double aef) {
		return aef;
	}

	// 19.RusselRao
	public static double RusselRao(double aef, double anf, double aep, double anp) {
		return aef / (aef + anf + aep + anp);
	}

	// 20.Binary
	public static double Binary(double aef, double F) {
		if (aef < F) {
			return 0;
		} else {
			return 1;
		}
	}

	// 21.Scott
	public static double Scott(double aef, double anp, double aep, double anf) {
		return (4 * aef * anp - 4 * anf * aep - (anf - aep) * (anf - aep))
				/ ((2 * aef + anf + aep) * (2 * anp + anf + aep));
	}

	// 22.Rogot1
	public static double Rogot1(double aef, double anp, double anf, double aep) {
		return 0.5 * (aef / (2 * aef + anf + aep) + anp / (2 * anp + anf + aep));
	}

	// 23.kylczynski2
	public static double kylczynski2(double aef, double anf, double aep) {
		return 0.5 * (aef / (aef + anf) + aef / (aef + aep));
	}

	// 24.Ochiai
	public static double Ochiai(double aef, double anf, double aep) {
		return aef / Math.sqrt((aef + anf) * (aef + aep));
	}

	// 25.M2
	public static double M2(double aef, double anp, double anf, double aep) {
		return aef / (aef + anp + 2 * (anf + aep));
	}

	// 26.AMPLE2
	public static double AMPLE2(double aef, double anf, double aep, double anp) {
		if (aef == 0 & (aep + anp) != 0) {
			return -aep / (aep + anp);
		} else if (aep == 0 & (aef + anf) != 0) {
			return aef / (aef + anf);
		}
		return aef / (aef + anf) - aep / (aep + anp);
	}

	// 27.Wong3
	public static double Wong3(double aef, double h, double aep) {
		if (aep <= 2) {
			h = aep;
		} else if (2 < aep && aep <= 10) {
			h = 2 + 0.1 * (aep - 2);
		} else {
			h = 2.8 + 0.001 * (aep - 10);
		}
		return aef - h;
	}

	// 28.ArithemeticMean
	public static double ArithemeticMean(double aef, double anp, double anf, double aep) {
		if ((2 * aef * anp - 2 * anf * aep) == 0) {
			return 0;
		} else if ((aef + aep) * (anp + anf) + (aef + anf) * (aep + anp) == 0) {
			return Double.MAX_VALUE;
		} else {
			return (2 * aef * anp - 2 * anf * aep) / ((aef + aep) * (anp + anf) + (aef + anf) * (aep + anp));
		}
	}

	// 29.Cohen
	public static double Cohen(double aef, double anp, double anf, double aep) {
		return (2 * aef * anp - 2 * anf * aep) / ((aef + aep) * (anp + aep) + (aef + anf) * (anf + anp));
	}

	// 30.Fleiss
	public static double Fleiss(double aef, double anp, double aep, double anf) {
		return (4 * aef * anp - 4 * anf * aep - (anf - aep) * (anf - aep))
				/ ((2 * aef + anf + aep) + (2 * anp + anf + aep));
	}

	// 31.AMPLE
	public static double AMPLE(double aef, double F, double aep, double P) {
		return Math.abs((aef / F) - (aep / P));
	}

	// 32.kylczynski1
	public static double kylczynski1(double aef, double F, double aep) {
		return aef / (F - aef + aep);
	}

	// 33.M1
	public static double M1(double aef, double anp, double aep, double F) {
		return (aef + anp) / (F - aef + aep);
	}

	// 34.zk1
	public static double zk1(double aef, double anf, double aep, double anp, double F, double P) {
		return (aef - anf) / F - (aep - anp) / P;
	}

	// 35.op2
	public static double op2(double aef, double aep, double P) {
		return aef - (aep / (P + 1));
	}
	// end

	// 矩阵转置函数
	public static void Traverse(String[][] a, String[][] b) {
		for (int count1 = 0; count1 < a.length; count1++) {
			for (int count2 = 0; count2 < a[count1].length; count2++) {
				b[count2][count1] = a[count1][count2];
			}
		}
	}

	public static void main() throws Exception {
		Properties props = new Properties();// 使用Properties类来加载属性文件
		FileInputStream iFile = new FileInputStream(new File("version.properties"));
		props.load(iFile);
		String coveragematrixname = props.getProperty("覆盖矩阵名");
		String faultMatrixname = props.getProperty("错误矩阵名");
		Integer testcasenumber = Integer.valueOf(props.getProperty("测试用例数"));
		Integer LOC = Integer.valueOf(props.getProperty("LOC"));
		Integer versionnumbers = Integer.valueOf(props.getProperty("版本数目"));
		Integer faultmatrixindex = Integer.valueOf(props.getProperty("faultmatrixindex"));
		File f1 = new File(coveragematrixname);
		// 执行失败的测试用例
		double F = 0;
		// 执行成功的测试用例
		double P = 0;
		String[][] covermatrix = new String[testcasenumber][LOC];
		String[][] covermatrix2 = new String[LOC][testcasenumber];
		double[] anp = new double[LOC];
		double[] aep = new double[LOC];
		double[] anf = new double[LOC];
		double[] aef = new double[LOC];
		Scanner sc1 = new Scanner(f1);
		while (sc1.hasNext()) {
			for (int count1 = 0; count1 < testcasenumber; count1++) {
				for (int count2 = 0; count2 < LOC; count2++) {
					covermatrix[count1][count2] = (String) sc1.next();
				}
			}
		}
		sc1.close();
		Traverse(covermatrix, covermatrix2);
		String[][] faultmatrix = new String[testcasenumber][versionnumbers];
		File f2 = new File(faultMatrixname);
		Scanner sc2 = new Scanner(f2);
		while (sc2.hasNext()) {
			for (int count1 = 0; count1 < testcasenumber; count1++) {
				for (int count2 = 0; count2 < versionnumbers; count2++) {
					faultmatrix[count1][count2] = sc2.next();
				}
			}
		}
		sc2.close();
		// 分别统计失败和成功的测试用例的个数F、P
		for (int count = 0; count < testcasenumber; count++) {
			if (faultmatrix[count][faultmatrixindex].equals("1")) {
				F++;
			} else {
				P++;
			}
		}
		// System.out.println(P);
		// System.out.println(F);
		/*
		 * for(double count1=0;count1<LOC;count1++) { for(double
		 * count2=0;count2<testcasenumber;count2++) {
		 * System.out.print(covermatrix2[count1][count2]+"\t"); }
		 * System.out.println(); }
		 */
		for (int count1 = 0; count1 < LOC; count1++) {
			for (int count2 = 0; count2 < testcasenumber; count2++) {
				if (covermatrix2[count1][count2].equals("1") & faultmatrix[count2][faultmatrixindex].equals("0")) {
					aep[count1]++;
				} else if (covermatrix2[count1][count2].equals("1")
						& faultmatrix[count2][faultmatrixindex].equals("1")) {
					aef[count1]++;
				} else if (covermatrix2[count1][count2].equals("0")
						& faultmatrix[count2][faultmatrixindex].equals("0")) {
					anp[count1]++;
				} else {
					anf[count1]++;
				}
			}
		}

		double[] Jaccard = new double[LOC];
		double[] Naish2 = new double[LOC];
		double[] Anderberg = new double[LOC];
		double[] Srensen_Dice = new double[LOC];
		double[] Dice = new double[LOC];
		double[] Goodman = new double[LOC];
		double[] Tarantula = new double[LOC];
		double[] qe = new double[LOC];
		double[] CBI = new double[LOC];
		double[] Wong2 = new double[LOC];
		double[] Hamann = new double[LOC];
		double[] SimpleMatching = new double[LOC];
		double[] Sokal = new double[LOC];
		double[] RogersTanimoto = new double[LOC];
		double[] Hamming = new double[LOC];
		double[] Euclid = new double[LOC];
		double[] Wong1 = new double[LOC];
		double[] RusselRao = new double[LOC];
		double[] Binary = new double[LOC];
		double[] Scott = new double[LOC];
		double[] Rogot1 = new double[LOC];
		double[] kylczynski2 = new double[LOC];
		double[] Ochiai = new double[LOC];
		double[] M2 = new double[LOC];
		double[] AMPLE2 = new double[LOC];
		double[] Wong3 = new double[LOC];
		double[] ArithemeticMean = new double[LOC];
		double[] Cohen = new double[LOC];
		double[] Fleiss = new double[LOC];
		double[] AMPLE = new double[LOC];
		double[] M1 = new double[LOC];
		double[] kylczynski1 = new double[LOC];
		double[] Naish = new double[LOC];
		double[] zk1 = new double[LOC];
		double[] op2 = new double[LOC];
		// 计算33个怀疑度公式的每条语句的怀疑度
		for (int count2 = 0; count2 < LOC; count2++) {
			// 计算33个怀疑度公式的每条语句的怀疑度
			Naish[count2] = Naish(aef[count2], F, P, aep[count2]);
			Naish2[count2] = Naish2(aef[count2], aep[count2], anp[count2]);
			Jaccard[count2] = Jaccard(aef[count2], anf[count2], aep[count2]);
			Anderberg[count2] = Anderberg(aef[count2], anf[count2], aep[count2]);
			Srensen_Dice[count2] = Srensen_Dice(anf[count2], aef[count2], aep[count2]);
			Dice[count2] = Dice(aef[count2], anf[count2], aep[count2]);
			Goodman[count2] = Goodman(aef[count2], anf[count2], aep[count2]);
			Tarantula[count2] = Tarantula(aef[count2], anf[count2], aep[count2], anp[count2]);
			try {
				qe[count2] = qe(aef[count2], aep[count2]);
			} catch (Exception e) {
				qe[count2] = -1;
			}
			CBI[count2] = CBI(aef[count2], aep[count2], anf[count2], anp[count2]);
			Wong2[count2] = Wong2(aef[count2], aep[count2]);
			Hamann[count2] = Hamann(aef[count2], anp[count2], anf[count2], aep[count2]);
			SimpleMatching[count2] = SimpleMatching(aef[count2], anp[count2], anf[count2], aep[count2]);
			Sokal[count2] = Sokal(aef[count2], anp[count2], anf[count2],aep[count2]);
			RogersTanimoto[count2] = RogersTanimoto(aef[count2], anp[count2], anf[count2], aep[count2]);
			Hamming[count2] = Hamming(aef[count2], anp[count2]);
			Euclid[count2] = Euclid(aef[count2], anp[count2]);
			Wong1[count2] = Wong1(aef[count2]);
			RusselRao[count2] = RusselRao(aef[count2], anf[count2], aep[count2], anp[count2]);
			Binary[count2] = Binary(aef[count2], F);
			Scott[count2] = Scott(aef[count2], anp[count2], aep[count2], anf[count2]);
			Rogot1[count2] = Rogot1(aef[count2], anp[count2], anf[count2], aep[count2]);
			try {
				kylczynski2[count2] = kylczynski2(aef[count2], anf[count2], aep[count2]);
			} catch (Exception e) {
				kylczynski2[count2] = -1;
			}
			Ochiai[count2] = Ochiai(aef[count2], anf[count2], aep[count2]);
			M2[count2] = M2(aef[count2], anp[count2], anf[count2], aep[count2]);
			AMPLE2[count2] = AMPLE2(aef[count2], anf[count2], aep[count2], anp[count2]);
			double h = 0;// wong3公式中的h值
			Wong3[count2] = Wong3(aef[count2], h, aep[count2]);
			ArithemeticMean[count2] = ArithemeticMean(aef[count2], anp[count2], anf[count2], aep[count2]);
			Cohen[count2] = Cohen(aef[count2], anp[count2], anf[count2], aep[count2]);
			Fleiss[count2] = Fleiss(aef[count2], anp[count2], aep[count2], anf[count2]);
			AMPLE[count2] = AMPLE(aef[count2], F, aep[count2], P);
			kylczynski1[count2] = kylczynski1(aef[count2], F, aep[count2]);
			M1[count2] = M1(aef[count2], anp[count2], aep[count2], F);
			zk1[count2] = zk1(aef[count2], anf[count2], aep[count2], anp[count2], F, P);
			op2[count2] = op2(aef[count2], aep[count2], P);
		}
		for (int count = 0; count < LOC; count++) {
			if (Double.isNaN(Ochiai[count])) {
				Ochiai[count] = -1;
			}
			if (Double.isNaN(Tarantula[count])) {
				Tarantula[count] = -1;
			}
			if (Double.isNaN(qe[count])) {
				qe[count] = -1;
			}

			if (Double.isNaN(kylczynski2[count])) {
				kylczynski2[count] = -1;
			}
			// System.out.println(Ochiai[count]);
		}
		/*
		 * 构造33个map映射,Naish Naish2 Jaccard Anderberg Srensen_Dice Dice Goodman
		 * Tarantula qe CBI Wong2 Hamann SimpleMatching Sokal RogersTanimoto
		 * Hamming Euclid ArithemeticMean Wong1 RusselRao Binary Scott Rogot1
		 * kylczynski2 Ochiai M2 AMPLE2 Wong3 Cohen Fleiss AMPLE kylczynskil M1
		 **/
		HashMap<Integer, Double> NaishMap = new HashMap<Integer, Double>();
		HashMap<Integer, Double> Naish2Map = new HashMap<Integer, Double>();
		HashMap<Integer, Double> JaccardMap = new HashMap<Integer, Double>();
		HashMap<Integer, Double> AnderbergMap = new HashMap<Integer, Double>();
		HashMap<Integer, Double> Srensen_DiceMap = new HashMap<Integer, Double>();
		HashMap<Integer, Double> DiceMap = new HashMap<Integer, Double>();
		HashMap<Integer, Double> GoodmanMap = new HashMap<Integer, Double>();
		HashMap<Integer, Double> TarantulaMap = new HashMap<Integer, Double>();
		HashMap<Integer, Double> qeMap = new HashMap<Integer, Double>();
		HashMap<Integer, Double> CBIMap = new HashMap<Integer, Double>();
		HashMap<Integer, Double> Wong2Map = new HashMap<Integer, Double>();
		HashMap<Integer, Double> HamannMap = new HashMap<Integer, Double>();
		HashMap<Integer, Double> SimpleMatchingMap = new HashMap<Integer, Double>();
		HashMap<Integer, Double> SokalMap = new HashMap<Integer, Double>();
		HashMap<Integer, Double> RogersTanimotoMap = new HashMap<Integer, Double>();
		HashMap<Integer, Double> HammingMap = new HashMap<Integer, Double>();
		HashMap<Integer, Double> EuclidMap = new HashMap<Integer, Double>();
		HashMap<Integer, Double> ArithemeticMeanMap = new HashMap<Integer, Double>();
		HashMap<Integer, Double> Wong1Map = new HashMap<Integer, Double>();
		HashMap<Integer, Double> RusselRaoMap = new HashMap<Integer, Double>();
		HashMap<Integer, Double> BinaryMap = new HashMap<Integer, Double>();
		HashMap<Integer, Double> ScottMap = new HashMap<Integer, Double>();
		HashMap<Integer, Double> Rogot1Map = new HashMap<Integer, Double>();
		HashMap<Integer, Double> kylczynski2Map = new HashMap<Integer, Double>();
		HashMap<Integer, Double> OchiaiMap = new HashMap<Integer, Double>();
		HashMap<Integer, Double> M2Map = new HashMap<Integer, Double>();
		HashMap<Integer, Double> AMPLE2Map = new HashMap<Integer, Double>();
		HashMap<Integer, Double> Wong3Map = new HashMap<Integer, Double>();
		HashMap<Integer, Double> CohenMap = new HashMap<Integer, Double>();
		HashMap<Integer, Double> FleissMap = new HashMap<Integer, Double>();
		HashMap<Integer, Double> AMPLEMap = new HashMap<Integer, Double>();
		HashMap<Integer, Double> kylczynski1Map = new HashMap<Integer, Double>();
		HashMap<Integer, Double> M1Map = new HashMap<Integer, Double>();
		HashMap<Integer, Double> zk1Map = new HashMap<Integer, Double>();
		HashMap<Integer, Double> op2Map = new HashMap<Integer, Double>();
		// 构建map映射
		for (int count = 0; count < LOC; count++) {
			NaishMap.put(count + 1, Naish[count]);
			Naish2Map.put(count + 1, Naish2[count]);
			JaccardMap.put(count + 1, Jaccard[count]);
			AnderbergMap.put(count + 1, Anderberg[count]);
			Srensen_DiceMap.put(count + 1, Srensen_Dice[count]);
			DiceMap.put(count + 1, Dice[count]);
			GoodmanMap.put(count + 1, Goodman[count]);
			TarantulaMap.put(count + 1, Tarantula[count]);
			qeMap.put(count + 1, qe[count]);
			CBIMap.put(count + 1, CBI[count]);
			Wong2Map.put(count + 1, Wong2[count]);
			HamannMap.put(count + 1, Hamann[count]);
			SimpleMatchingMap.put(count + 1, SimpleMatching[count]);
			SokalMap.put(count + 1, Sokal[count]);
			RogersTanimotoMap.put(count + 1, RogersTanimoto[count]);
			HammingMap.put(count + 1, Hamming[count]);
			EuclidMap.put(count + 1, Euclid[count]);
			ArithemeticMeanMap.put(count + 1, ArithemeticMean[count]);
			Wong1Map.put(count + 1, Wong1[count]);
			RusselRaoMap.put(count + 1, RusselRao[count]);
			kylczynski2Map.put(count + 1, kylczynski2[count]);
			OchiaiMap.put(count + 1, Ochiai[count]);
			M2Map.put(count + 1, M2[count]);
			AMPLE2Map.put(count + 1, AMPLE2[count]);
			Wong3Map.put(count + 1, Wong3[count]);
			CohenMap.put(count + 1, Cohen[count]);
			FleissMap.put(count + 1, Fleiss[count]);
			AMPLEMap.put(count + 1, AMPLE[count]);
			kylczynski1Map.put(count + 1, kylczynski1[count]);
			M1Map.put(count + 1, M1[count]);
			BinaryMap.put(count + 1, Binary[count]);
			ScottMap.put(count + 1, Scott[count]);
			Rogot1Map.put(count + 1, Rogot1[count]);
			zk1Map.put(count + 1, zk1[count]);
			op2Map.put(count + 1, op2[count]);
		}
		// 重写comparable比较器
		List<Entry<Integer, Double>> Naishlist = new ArrayList<Map.Entry<Integer, Double>>(NaishMap.entrySet());
		List<Entry<Integer, Double>> Naish2list = new ArrayList<Map.Entry<Integer, Double>>(Naish2Map.entrySet());
		List<Entry<Integer, Double>> Jaccardlist = new ArrayList<Map.Entry<Integer, Double>>(JaccardMap.entrySet());
		List<Entry<Integer, Double>> Anderberglist = new ArrayList<Map.Entry<Integer, Double>>(AnderbergMap.entrySet());
		List<Entry<Integer, Double>> Srensen_Dicelist = new ArrayList<Map.Entry<Integer, Double>>(
				Srensen_DiceMap.entrySet());
		List<Entry<Integer, Double>> Dicelist = new ArrayList<Map.Entry<Integer, Double>>(DiceMap.entrySet());
		List<Entry<Integer, Double>> Goodmanlist = new ArrayList<Map.Entry<Integer, Double>>(GoodmanMap.entrySet());
		List<Entry<Integer, Double>> Tarantulalist = new ArrayList<Map.Entry<Integer, Double>>(TarantulaMap.entrySet());
		List<Entry<Integer, Double>> qelist = new ArrayList<Map.Entry<Integer, Double>>(qeMap.entrySet());
		List<Entry<Integer, Double>> CBIlist = new ArrayList<Map.Entry<Integer, Double>>(CBIMap.entrySet());
		List<Entry<Integer, Double>> Wong2list = new ArrayList<Map.Entry<Integer, Double>>(Wong2Map.entrySet());
		List<Entry<Integer, Double>> Hamannlist = new ArrayList<Map.Entry<Integer, Double>>(HamannMap.entrySet());
		List<Entry<Integer, Double>> SimpleMatchinglist = new ArrayList<Map.Entry<Integer, Double>>(
				SimpleMatchingMap.entrySet());
		List<Entry<Integer, Double>> Sokallist = new ArrayList<Map.Entry<Integer, Double>>(SokalMap.entrySet());
		List<Entry<Integer, Double>> RogersTanimotolist = new ArrayList<Map.Entry<Integer, Double>>(
				RogersTanimotoMap.entrySet());
		List<Entry<Integer, Double>> Hamminglist = new ArrayList<Map.Entry<Integer, Double>>(HammingMap.entrySet());
		List<Entry<Integer, Double>> Euclidlist = new ArrayList<Map.Entry<Integer, Double>>(EuclidMap.entrySet());
		List<Entry<Integer, Double>> ArithemeticMeanlist = new ArrayList<Map.Entry<Integer, Double>>(
				ArithemeticMeanMap.entrySet());
		List<Entry<Integer, Double>> Wong1list = new ArrayList<Map.Entry<Integer, Double>>(Wong1Map.entrySet());
		List<Entry<Integer, Double>> RusselRaolist = new ArrayList<Map.Entry<Integer, Double>>(RusselRaoMap.entrySet());
		List<Entry<Integer, Double>> Binarylist = new ArrayList<Map.Entry<Integer, Double>>(BinaryMap.entrySet());
		List<Entry<Integer, Double>> Scottlist = new ArrayList<Map.Entry<Integer, Double>>(ScottMap.entrySet());
		List<Entry<Integer, Double>> Rogot1list = new ArrayList<Map.Entry<Integer, Double>>(Rogot1Map.entrySet());
		List<Entry<Integer, Double>> kylczynski2list = new ArrayList<Map.Entry<Integer, Double>>(
				kylczynski2Map.entrySet());
		List<Entry<Integer, Double>> Ochiailist = new ArrayList<Map.Entry<Integer, Double>>(OchiaiMap.entrySet());
		List<Entry<Integer, Double>> M2list = new ArrayList<Map.Entry<Integer, Double>>(M2Map.entrySet());
		List<Entry<Integer, Double>> AMPLE2list = new ArrayList<Map.Entry<Integer, Double>>(AMPLE2Map.entrySet());
		List<Entry<Integer, Double>> Wong3list = new ArrayList<Map.Entry<Integer, Double>>(Wong3Map.entrySet());
		List<Entry<Integer, Double>> Cohenlist = new ArrayList<Map.Entry<Integer, Double>>(CohenMap.entrySet());
		List<Entry<Integer, Double>> Fleisslist = new ArrayList<Map.Entry<Integer, Double>>(FleissMap.entrySet());
		List<Entry<Integer, Double>> AMPLElist = new ArrayList<Map.Entry<Integer, Double>>(AMPLEMap.entrySet());
		List<Entry<Integer, Double>> kylczynski1list = new ArrayList<Map.Entry<Integer, Double>>(
				kylczynski1Map.entrySet());
		List<Entry<Integer, Double>> M1list = new ArrayList<Map.Entry<Integer, Double>>(M1Map.entrySet());
		List<Entry<Integer, Double>> zk1list = new ArrayList<Map.Entry<Integer, Double>>(zk1Map.entrySet());
		List<Entry<Integer, Double>> op2list = new ArrayList<Map.Entry<Integer, Double>>(op2Map.entrySet());

		/*
		 * 构造33个map映射,Naish Naish2 Jaccard Anderberg Srensen_Dice Dice Goodman
		 * Tarantula qe CBI Wong2 Hamann SimpleMatching Sokal RogersTanimoto
		 * Hamming Euclid ArithemeticMean Wong1 RusselRao Binary Scott Rogot1
		 * kylczynski2 Ochiai M2 AMPLE2 Wong3 Cohen Fleiss AMPLE kylczynski1 M1
		 **/
		// 1.Naish
		Collections.sort(Naishlist, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}

		});
		// 2.Naish2
		Collections.sort(Naish2list, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}

		});
		// 3.Jaccard
		Collections.sort(Jaccardlist, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}

		});
		// 4.Anderberg
		Collections.sort(Anderberglist, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}

		});
		// 5.Srensen_Dice
		Collections.sort(Srensen_Dicelist, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}

		});
		// 6.Dice
		Collections.sort(Dicelist, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}

		});
		// 7.Goodman
		Collections.sort(Goodmanlist, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}

		});
		// 8.Tarantula
		Collections.sort(Tarantulalist, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}

		});
		// 9.qe
		Collections.sort(qelist, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}

		});
		// 10.CBI
		Collections.sort(CBIlist, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}
		});
		// 11.Wong2
		Collections.sort(Wong2list, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}
		});
		// 12.Hamann
		Collections.sort(Hamannlist, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}
		});
		// 13.SimpleMatching
		Collections.sort(SimpleMatchinglist, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}
		});
		// 14.Sokal
		Collections.sort(Sokallist, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}
		});
		// 15.RogersTanimoto
		Collections.sort(RogersTanimotolist, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}
		});
		// 16.Hamming
		Collections.sort(Hamminglist, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}
		});
		// 17.Euclid
		Collections.sort(Euclidlist, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}
		});
		// 18.ArithemeticMean
		Collections.sort(ArithemeticMeanlist, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}
		});
		// 19.Wong1
		Collections.sort(Wong1list, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}
		});
		// 20.RusselRao
		Collections.sort(RusselRaolist, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}
		});
		// 21.Binary
		Collections.sort(Binarylist, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}
		});
		// 22.Scott
		Collections.sort(Scottlist, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}
		});
		// 23.Rogot1
		Collections.sort(Rogot1list, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}
		});
		// 24.kylczynski2
		Collections.sort(kylczynski2list, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}
		});
		// 25.Ochiai
		Collections.sort(Ochiailist, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}
		});
		// 26.M2
		Collections.sort(M2list, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}
		});
		// 27.AMPLE2
		Collections.sort(AMPLE2list, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}
		});
		// 28.Wong3
		Collections.sort(Wong3list, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}
		});
		// 29.Cohen
		Collections.sort(Cohenlist, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}
		});
		// 30.Fleiss
		Collections.sort(Fleisslist, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}
		});
		// 31.AMPLE
		Collections.sort(AMPLElist, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}
		});
		// 32.kylczynski1
		Collections.sort(kylczynski1list, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}
		});
		// 33.M1
		Collections.sort(M1list, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}
		});

		// 34.zk1
		Collections.sort(zk1list, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}
		});
		// 34.op2
		Collections.sort(op2list, new Comparator<Map.Entry<Integer, Double>>() {
			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				// TODO Auto-generated method stub
				if (o1.getValue() > o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else if (o1.getValue() < o2.getValue())
					return o2.getValue().compareTo(o1.getValue());
				else
					return o1.getKey().compareTo(o2.getKey());
			}
		});
		/*
		 * Naish Naish2 Jaccard Anderberg Srensen_Dice Dice Goodman Tarantula qe
		 * CBI Wong2 Hamann SimpleMatching Sokal RogersTanimoto Hamming Euclid
		 * Wong1 RusselRao Binary Scott Rogot1 kylczynski2 Ochiai M2 AMPLE2
		 * Wong3 ArithemeticMean Cohen Fleiss AMPLE kylczynskil M1
		 */
		try {
			// 将数据打印到Excel表中
			String excelsuspiciousname = props.getProperty("怀疑度表名");
			String sheetsuspiciousname = props.getProperty("怀疑度表中sheet名");
			String versionname = props.getProperty("版本名");
			WritableWorkbook book = Workbook.createWorkbook(new File(excelsuspiciousname));
			WritableSheet sheet1 = book.createSheet(sheetsuspiciousname, 0);
			// Workbook book2=Workbook.getWorkbook(new File("suspicious.xls"));
			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0),单元格内容为string
			// 1.Naish
			Label Naish_lineNumber = new Label(0, LOC, "Naish行数");
			Label Naish_suspicious = new Label(1, LOC, "Naish怀疑度");

			// 2.Naish2
			Label Naish2_lineNumber = new Label(2, LOC, "Naish2行数");
			Label Naish2_suspicious = new Label(3, LOC, "Naish2怀疑度");

			// 3.Jaccard
			Label Jaccard_lineNumber = new Label(4, LOC, "Jaccard行数");
			Label Jaccard_suspicious = new Label(5, LOC, "Jaccard怀疑度");

			// 4.Anderberg
			Label Anderberg_lineNumber = new Label(6, LOC, "Anderberg行数");
			Label Anderberg_suspicious = new Label(7, LOC, "Anderberg怀疑度");

			// 5.Srensen_Dice
			Label Srensen_Dice_lineNumber = new Label(8, LOC, "Srensen_Dice行数");
			Label Srensen_Dice_suspicious = new Label(9, LOC, "Srensen_Dice怀疑度");

			// 6.Dice
			Label Dice_lineNumber = new Label(10, LOC, "Dice行数");
			Label Dice_suspicious = new Label(11, LOC, "Dice怀疑度");

			// 7.Goodman
			Label Goodman_lineNumber = new Label(12, LOC, "Goodman行数");
			Label Goodman_suspicious = new Label(13, LOC, "Goodman怀疑度");

			// 8.Tarantula
			Label Tarantula_lineNumber = new Label(14, LOC, "Tarantula行数");
			Label Tarantula_suspicious = new Label(15, LOC, "Tarantula怀疑度");

			// 9.qe
			Label qe_lineNumber = new Label(16, LOC, "qe行数");
			Label qe_suspicious = new Label(17, LOC, "qe怀疑度");

			// 10.CBI
			Label CBI_lineNumber = new Label(18, LOC, "CBI行数");
			Label CBI_suspicious = new Label(19, LOC, "CBI怀疑度");

			// 11.Wong2
			Label Wong2_lineNumber = new Label(20, LOC, "Wong2行数");
			Label Wong2_suspicious = new Label(21, LOC, "Wong2怀疑度");

			// 12.Hamann
			Label Hamann_lineNumber = new Label(22, LOC, "Hamann行数");
			Label Hamann_suspicious = new Label(23, LOC, "Hamann怀疑度");

			// 13.SimpleMatching
			Label SimpleMatching_lineNumber = new Label(24, LOC, "SimpleMatching行数");
			Label SimpleMatching_suspicious = new Label(25, LOC, "SimpleMatching怀疑度");

			// 14.Sokal
			Label Sokal_lineNumber = new Label(26, LOC, "Sokal行数");
			Label Sokal_suspicious = new Label(27, LOC, "Sokal怀疑度");

			// 15.RogersTanimoto
			Label RogersTanimoto_lineNumber = new Label(28, LOC, "RogersTanimoto行数");
			Label RogersTanimoto_suspicious = new Label(29, LOC, "RogersTanimoto怀疑度");

			// 16.Hamming
			Label Hamming_lineNumber = new Label(30, LOC, "Hamming行数");
			Label Hamming_suspicious = new Label(31, LOC, "Hamming怀疑度");

			// 17.Euclid
			Label Euclid_lineNumber = new Label(32, LOC, "Euclid行数");
			Label Euclid_suspicious = new Label(33, LOC, "Euclid怀疑度");

			// 18.ArithemeticMean
			Label ArithemeticMean_lineNumber = new Label(34, LOC, "ArithemeticMean行数");
			Label ArithemeticMean_suspicious = new Label(35, LOC, "ArithemeticMean怀疑度");

			// 19.Wong1
			Label Wong1_lineNumber = new Label(36, LOC, "Wong1行数");
			Label Wong1_suspicious = new Label(37, LOC, "Wong1怀疑度");

			// 20.RusselRao
			Label RusselRao_lineNumber = new Label(38, LOC, "RusselRao行数");
			Label RusselRao_suspicious = new Label(39, LOC, "RusselRao怀疑度");

			// 21.Binary
			Label Binary_lineNumber = new Label(40, LOC, "Binary行数");
			Label Binary_suspicious = new Label(41, LOC, "Binary怀疑度");

			// 22.Scott
			Label Scott_lineNumber = new Label(42, LOC, "Scott行数");
			Label Scott_suspicious = new Label(43, LOC, "Scott怀疑度");

			// 23.Rogot1
			Label Rogot1_lineNumber = new Label(44, LOC, "Rogot1行数");
			Label Rogot1_suspicious = new Label(45, LOC, "Rogot1怀疑度");

			// 24.kylczynski2
			Label kylczynski2_lineNumber = new Label(46, LOC, "kylczynski2行数");
			Label kylczynski2_suspicious = new Label(47, LOC, "kylczynski2怀疑度");

			// 25.Ochiai
			Label Ochiai_lineNumber = new Label(48, LOC, "Ochiai行数");
			Label Ochiai_suspicious = new Label(49, LOC, "Ochiai怀疑度");

			// 26.M2
			Label M2_lineNumber = new Label(50, LOC, "M2行数");
			Label M2_suspicious = new Label(51, LOC, "M2怀疑度");

			// 27.AMPLE2
			Label AMPLE2_lineNumber = new Label(52, LOC, "AMPLE2行数");
			Label AMPLE2_suspicious = new Label(53, LOC, "AMPLE2怀疑度");

			// 28.Wong3
			Label Wong3_lineNumber = new Label(54, LOC, "Wong3行数");
			Label Wong3_suspicious = new Label(55, LOC, "Wong3怀疑度");

			// 29.Cohen
			Label Cohen_lineNumber = new Label(56, LOC, "Cohen行数");
			Label Cohen_suspicious = new Label(57, LOC, "Cohen怀疑度");

			// 30.Fleiss
			Label Fleiss_lineNumber = new Label(58, LOC, "Fleiss行数");
			Label Fleiss_suspicious = new Label(59, LOC, "Fleiss怀疑度");

			// 31.AMPLE
			Label AMPLE_lineNumber = new Label(60, LOC, "AMPLE行数");
			Label AMPLE_suspicious = new Label(61, LOC, "AMPLE怀疑度");

			// 32.kylczynski1
			Label kylczynski1_lineNumber = new Label(62, LOC, "kylczynski1行数");
			Label kylczynski1_suspicious = new Label(63, LOC, "kylczynski1怀疑度");

			// 33.M1
			Label M1_lineNumber = new Label(64, LOC, "M1行数");
			Label M1_suspicious = new Label(65, LOC, "M1怀疑度");

			// 34.zk1
			Label zk1_lineNumber = new Label(66, LOC, "zk1行数");
			Label zk1_suspicious = new Label(67, LOC, "zk1怀疑度");

			// 35. op2
			Label op2_lineNumber = new Label(68, LOC, "op2行数");
			Label op2_suspicious = new Label(69, LOC, "op2怀疑度");

			Label tablelabel = new Label(0, LOC + 1, versionname);
			// 将定义好的单元格添加到工作表中
			/*
			 * Naish Naish2 Jaccard Anderberg Srensen_Dice Dice Goodman
			 * Tarantula qe CBI Wong2 Hamann SimpleMatching Sokal RogersTanimoto
			 * Hamming Euclid ArithemeticMean Wong1 RusselRao Binary Scott
			 * Rogot1 kylczynski2 Ochiai M2 AMPLE2 Wong3 Cohen Fleiss AMPLE
			 * kylczynski1 M1
			 */
			sheet1.addCell(Naish_lineNumber);
			sheet1.addCell(Naish_suspicious);
			sheet1.addCell(Naish2_lineNumber);
			sheet1.addCell(Naish2_suspicious);
			sheet1.addCell(Jaccard_lineNumber);
			sheet1.addCell(Jaccard_suspicious);
			sheet1.addCell(Anderberg_lineNumber);
			sheet1.addCell(Anderberg_suspicious);
			sheet1.addCell(Srensen_Dice_lineNumber);
			sheet1.addCell(Srensen_Dice_suspicious);
			sheet1.addCell(Dice_lineNumber);
			sheet1.addCell(Dice_suspicious);
			sheet1.addCell(Goodman_lineNumber);
			sheet1.addCell(Goodman_suspicious);
			sheet1.addCell(Tarantula_lineNumber);
			sheet1.addCell(Tarantula_suspicious);
			sheet1.addCell(qe_lineNumber);
			sheet1.addCell(qe_suspicious);
			sheet1.addCell(CBI_lineNumber);
			sheet1.addCell(CBI_suspicious);
			sheet1.addCell(Wong2_lineNumber);
			sheet1.addCell(Wong2_suspicious);
			sheet1.addCell(Hamann_lineNumber);
			sheet1.addCell(Hamann_suspicious);
			sheet1.addCell(SimpleMatching_lineNumber);
			sheet1.addCell(SimpleMatching_suspicious);
			sheet1.addCell(Sokal_lineNumber);
			sheet1.addCell(Sokal_suspicious);
			sheet1.addCell(RogersTanimoto_lineNumber);
			sheet1.addCell(RogersTanimoto_suspicious);
			sheet1.addCell(Hamming_lineNumber);
			sheet1.addCell(Hamming_suspicious);
			sheet1.addCell(Euclid_lineNumber);
			sheet1.addCell(Euclid_suspicious);
			sheet1.addCell(ArithemeticMean_lineNumber);
			sheet1.addCell(ArithemeticMean_suspicious);
			sheet1.addCell(Wong1_lineNumber);
			sheet1.addCell(Wong1_suspicious);
			sheet1.addCell(RusselRao_lineNumber);
			sheet1.addCell(RusselRao_suspicious);
			sheet1.addCell(Binary_lineNumber);
			sheet1.addCell(Binary_suspicious);
			sheet1.addCell(Scott_lineNumber);
			sheet1.addCell(Scott_suspicious);
			sheet1.addCell(Rogot1_lineNumber);
			sheet1.addCell(Rogot1_suspicious);
			sheet1.addCell(kylczynski2_lineNumber);
			sheet1.addCell(kylczynski2_suspicious);
			sheet1.addCell(Ochiai_lineNumber);
			sheet1.addCell(Ochiai_suspicious);
			sheet1.addCell(M2_lineNumber);
			sheet1.addCell(M2_suspicious);
			sheet1.addCell(AMPLE2_lineNumber);
			sheet1.addCell(AMPLE2_suspicious);
			sheet1.addCell(Wong3_lineNumber);
			sheet1.addCell(Wong3_suspicious);
			sheet1.addCell(Cohen_lineNumber);
			sheet1.addCell(Cohen_suspicious);
			sheet1.addCell(Fleiss_lineNumber);
			sheet1.addCell(Fleiss_suspicious);
			sheet1.addCell(AMPLE_lineNumber);
			sheet1.addCell(AMPLE_suspicious);
			sheet1.addCell(kylczynski1_lineNumber);
			sheet1.addCell(kylczynski1_suspicious);
			sheet1.addCell(M1_lineNumber);
			sheet1.addCell(M1_suspicious);
			sheet1.addCell(zk1_lineNumber);
			sheet1.addCell(zk1_suspicious);
			sheet1.addCell(op2_lineNumber);
			sheet1.addCell(op2_suspicious);

			sheet1.addCell(tablelabel);
			/*
			 * for (Entry<Integer, Double> mapping : Naishlist) {
			 * System.out.println(mapping.getKey() + ":" + mapping.getValue());
			 * }
			 */

			// 将定义好的单元格添加到工作表中

			// 1.Naish
			int count = 0;
			for (Entry<Integer, Double> Naishmapping : Naishlist) {
				Number number1 = new Number(0, count, Naishmapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(1, count, Naishmapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 2.Naish2
			for (Entry<Integer, Double> Naish2mapping : Naish2list) {
				Number number1 = new Number(2, count, Naish2mapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(3, count, Naish2mapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 3.Jaccard
			for (Entry<Integer, Double> Jaccardmapping : Jaccardlist) {
				Number number1 = new Number(4, count, Jaccardmapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(5, count, Jaccardmapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 4.Anderberg
			for (Entry<Integer, Double> Anderbergmapping : Anderberglist) {
				Number number1 = new Number(6, count, Anderbergmapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(7, count, Anderbergmapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 5.Srensen_Dice
			for (Entry<Integer, Double> Srensen_Dicemapping : Srensen_Dicelist) {
				Number number1 = new Number(8, count, Srensen_Dicemapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(9, count, Srensen_Dicemapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 6.Dice
			for (Entry<Integer, Double> Dicemapping : Dicelist) {
				Number number1 = new Number(10, count, Dicemapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(11, count, Dicemapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 7.Goodman
			for (Entry<Integer, Double> Goodmanmapping : Goodmanlist) {
				Number number1 = new Number(12, count, Goodmanmapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(13, count, Goodmanmapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 8.Tarantula
			for (Entry<Integer, Double> Tarantulamapping : Tarantulalist) {
				Number number1 = new Number(14, count, Tarantulamapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(15, count, Tarantulamapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 9.qe
			for (Entry<Integer, Double> qemapping : qelist) {
				Number number1 = new Number(16, count, qemapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(17, count, qemapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 10.CBI
			for (Entry<Integer, Double> CBImapping : CBIlist) {
				Number number1 = new Number(18, count, CBImapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(19, count, CBImapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 11.Wong2
			for (Entry<Integer, Double> Wong2mapping : Wong2list) {
				Number number1 = new Number(20, count, Wong2mapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(21, count, Wong2mapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 12.Hamann
			for (Entry<Integer, Double> Hamannmapping : Hamannlist) {
				Number number1 = new Number(22, count, Hamannmapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(23, count, Hamannmapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 13.SimpleMatching
			for (Entry<Integer, Double> SimpleMatchingmapping : SimpleMatchinglist) {
				Number number1 = new Number(24, count, SimpleMatchingmapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(25, count, SimpleMatchingmapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 14.Sokal
			for (Entry<Integer, Double> Sokalmapping : Sokallist) {
				Number number1 = new Number(26, count, Sokalmapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(27, count, Sokalmapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 15.RogersTanimoto
			for (Entry<Integer, Double> RogersTanimotomapping : RogersTanimotolist) {
				Number number1 = new Number(28, count, RogersTanimotomapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(29, count, RogersTanimotomapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 16.Hamming
			for (Entry<Integer, Double> Hammingmapping : Hamminglist) {
				Number number1 = new Number(30, count, Hammingmapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(31, count, Hammingmapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 17.Euclid
			for (Entry<Integer, Double> Euclidmapping : Euclidlist) {
				Number number1 = new Number(32, count, Euclidmapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(33, count, Euclidmapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 18.ArithemeticMean
			for (Entry<Integer, Double> ArithemeticMeanmapping : ArithemeticMeanlist) {
				Number number1 = new Number(34, count, ArithemeticMeanmapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(35, count, ArithemeticMeanmapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 19.Wong1
			for (Entry<Integer, Double> Wong1mapping : Wong1list) {
				Number number1 = new Number(36, count, Wong1mapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(37, count, Wong1mapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 20.RusselRao
			for (Entry<Integer, Double> RusselRaomapping : RusselRaolist) {
				Number number1 = new Number(38, count, RusselRaomapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(39, count, RusselRaomapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 21.Binary
			for (Entry<Integer, Double> Binarymapping : Binarylist) {
				Number number1 = new Number(40, count, Binarymapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(41, count, Binarymapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 22.Scott
			for (Entry<Integer, Double> Scottmapping : Scottlist) {
				Number number1 = new Number(42, count, Scottmapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(43, count, Scottmapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 23.Rogot1
			for (Entry<Integer, Double> Rogot1mapping : Rogot1list) {
				Number number1 = new Number(44, count, Rogot1mapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(45, count, Rogot1mapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 24.kylczynski2
			for (Entry<Integer, Double> kylczynski2mapping : kylczynski2list) {
				Number number1 = new Number(46, count, kylczynski2mapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(47, count, kylczynski2mapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 25.Ochiai
			for (Entry<Integer, Double> Ochiaimapping : Ochiailist) {
				Number number1 = new Number(48, count, Ochiaimapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(49, count, Ochiaimapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 26.M2
			for (Entry<Integer, Double> M2mapping : M2list) {
				Number number1 = new Number(50, count, M2mapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(51, count, M2mapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 27.AMPLE2
			for (Entry<Integer, Double> AMPLE2mapping : AMPLE2list) {
				Number number1 = new Number(52, count, AMPLE2mapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(53, count, AMPLE2mapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 28.Wong3
			for (Entry<Integer, Double> Wong3mapping : Wong3list) {
				Number number1 = new Number(54, count, Wong3mapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(55, count, Wong3mapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 29.Cohen
			for (Entry<Integer, Double> Cohenmapping : Cohenlist) {
				Number number1 = new Number(56, count, Cohenmapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(57, count, Cohenmapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 30.Fleiss
			for (Entry<Integer, Double> Fleissmapping : Fleisslist) {
				Number number1 = new Number(58, count, Fleissmapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(59, count, Fleissmapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 31.AMPLE
			for (Entry<Integer, Double> AMPLEmapping : AMPLElist) {
				Number number1 = new Number(60, count, AMPLEmapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(61, count, AMPLEmapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 32.kylczynski1
			for (Entry<Integer, Double> kylczynski1mapping : kylczynski1list) {
				Number number1 = new Number(62, count, kylczynski1mapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(63, count, kylczynski1mapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 33.M1
			for (Entry<Integer, Double> M1mapping : M1list) {
				Number number1 = new Number(64, count, M1mapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(65, count, M1mapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 34.zk1
			for (Entry<Integer, Double> zk1mapping : zk1list) {
				Number number1 = new Number(66, count, zk1mapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(67, count, zk1mapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			count = 0;
			// 35.op2
			for (Entry<Integer, Double> op2mapping : op2list) {
				Number number1 = new Number(68, count, op2mapping.getKey());
				sheet1.addCell(number1);
				Number number2 = new Number(69, count, op2mapping.getValue());
				sheet1.addCell(number2);
				count++;
			}
			/*
			 * for(int count=0;count<LOC;count++){
			 * //生成一个保存数字的单元格,单元格位置是第1列，第count+1行，单元格的内容为怀疑度 /*Naish Naish2
			 * Jaccard Anderberg Srensen_Dice Dice Goodman Tarantula qe CBI
			 * Wong2 Hamann SimpleMatching Sokal RogersTanimoto Hamming Euclid
			 * ArithemeticMean Wong1 RusselRao Binary Scott Rogot1 kylczynski2
			 * Ochiai M2 AMPLE2 Wong3 Cohen Fleiss AMPLE kylczynski1 M1
			 */
			/*
			 * Number number1 = new Number(0, count, Naish[count]);
			 * sheet.addCell(number1); Number number2 = new Number(1, count,
			 * Naish2[count]); sheet.addCell(number2); Number number3 = new
			 * Number(2, count, Jaccard[count]); sheet.addCell(number3); Number
			 * number4 = new Number(3, count, Anderberg[count]);
			 * sheet.addCell(number4); Number number5 = new Number(4, count,
			 * Srensen_Dice[count]); sheet.addCell(number5); Number number6 =
			 * new Number(5, count, Dice[count]); sheet.addCell(number6); Number
			 * number7 = new Number(6, count, Goodman[count]);
			 * sheet.addCell(number7); Number number8 = new Number(7, count,
			 * Tarantula[count]); sheet.addCell(number8); Number number9 = new
			 * Number(8, count, qe[count]); sheet.addCell(number9); Number
			 * number10 = new Number(9, count, CBI[count]);
			 * sheet.addCell(number10); Number number11 = new Number(10, count,
			 * Wong2[count]); sheet.addCell(number11); Number number12 = new
			 * Number(11, count, Hamann[count]); sheet.addCell(number12); Number
			 * number13 = new Number(12, count, SimpleMatching[count]);
			 * sheet.addCell(number13); Number number14 = new Number(13, count,
			 * Sokal[count]); sheet.addCell(number14); Number number15 = new
			 * Number(14, count, RogersTanimoto[count]);
			 * sheet.addCell(number15); Number number16 = new Number(15, count,
			 * Hamming[count]); sheet.addCell(number16); Number number17 = new
			 * Number(16, count, Euclid[count]); sheet.addCell(number17); Number
			 * number18 = new Number(17, count, ArithemeticMean[count]);
			 * sheet.addCell(number18); Number number19 = new Number(18, count,
			 * Wong1[count]); sheet.addCell(number19); Number number20 = new
			 * Number(19, count, RusselRao[count]); sheet.addCell(number20);
			 * Number number21 = new Number(20, count, Binary[count]);
			 * sheet.addCell(number21); Number number22 = new Number(21, count,
			 * Scott[count]); sheet.addCell(number22); Number number23 = new
			 * Number(22, count, Rogot1[count]); sheet.addCell(number23); Number
			 * number24 = new Number(23, count, kylczynski2[count]);
			 * sheet.addCell(number24); Number number25 = new Number(24, count,
			 * Ochiai[count]); sheet.addCell(number25); Number number26 = new
			 * Number(25, count, M2[count]); sheet.addCell(number26); Number
			 * number27 = new Number(26, count, AMPLE2[count]);
			 * sheet.addCell(number27); Number number28 = new Number(27, count,
			 * Wong3[count]); sheet.addCell(number28); Number number29 = new
			 * Number(28, count, Cohen[count]); sheet.addCell(number29); Number
			 * number30 = new Number(29, count, Fleiss[count]);
			 * sheet.addCell(number30); Number number31 = new Number(30, count,
			 * AMPLE[count]); sheet.addCell(number31); Number number32 = new
			 * Number(31, count, kylczynski1[count]); sheet.addCell(number32);
			 * Number number33 = new Number(32, count, M1[count]);
			 * sheet.addCell(number33); }
			 */
			// 写入数据并关闭文件
			book.write();
			book.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
