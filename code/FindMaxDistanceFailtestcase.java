// The class is used to find a failed test case having the maximum distance

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class FindMaxDistanceFailtestcase {
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

		// 1.找出所有失败了的测试用例,找个list存起来
		File f1 = new File(faultMatrixname);
		Scanner sc = new Scanner(f1);
		String[][] faultmatrix = new String[testcasenumber][versionnumbers];
		while (sc.hasNext()) {
			for (int count1 = 0; count1 < testcasenumber; count1++) {
				for (int count2 = 0; count2 < versionnumbers; count2++) {
					faultmatrix[count1][count2] = sc.next();
				}
			}
		}
		sc.close();
		List<Integer> faillist = new ArrayList<Integer>();
		for (int count1 = 0; count1 < testcasenumber; count1++) {
			if (faultmatrix[count1][faultmatrixindex].equals("1")) {
				faillist.add(count1 + 1);
			}
		}
		// System.out.println(faillist);
		// 2.找出对应的失败list中的测试用例的覆盖，找个map存起来
		HashMap<Integer, List<Integer>> hm1 = new HashMap<Integer, List<Integer>>();
		HashMap<Integer, Integer> hm2 = new HashMap<Integer, Integer>();
		int[][] covermatrix = new int[testcasenumber][LOC];
		File f2 = new File(coveragematrixname);
		Scanner sc2 = new Scanner(f2);
		while (sc2.hasNext()) {
			for (int count1 = 0; count1 < testcasenumber; count1++) {
				for (int count2 = 0; count2 < LOC; count2++) {
					covermatrix[count1][count2] = Integer.valueOf(sc2.next());
				}
			}
		}
		sc2.close();

		for (int count = 0; count < testcasenumber; count++) {
			List<Integer> list = new ArrayList<Integer>();
			if (faultmatrix[count][faultmatrixindex].equals("1")) {
				for (int count2 = 0; count2 < LOC; count2++) {
					if (covermatrix[count][count2] == 1) {
						list.add(count2);
					}
				}
				hm1.put(count + 1, list);
				hm2.put(count + 1, list.size());
			}
		}
		// 3.生成失败测试用例的顺序,上面已经生成
		double[] distance = new double[testcasenumber];
		int sum = 0;
		// 多重循环
		for (int count1 = 0; count1 < LOC; count1++) {
			for (int count2 = 0; count2 < testcasenumber; count2++) {
				for (int count3 = 0; count3 < testcasenumber; count3++) {
					distance[count3] += covermatrix[count3][count1] ^ covermatrix[count2][count1];
				}
			}
		}
		for (int count = 0; count < testcasenumber; count++) {
			distance[count] = distance[count] / testcasenumber;
		}
		Map<Integer, Double> distancemap = new HashMap<Integer, Double>();
		Map<Integer, Double> failtestcasedistancemap = new HashMap<Integer, Double>();

		Iterator<Integer> failtestcaseindex = faillist.iterator();
		while (failtestcaseindex.hasNext()) {
			int failtestcaseindexflag = failtestcaseindex.next();
			failtestcasedistancemap.put(failtestcaseindexflag, distance[failtestcaseindexflag - 1]);
		}
		int maxdistanceindex = 0;
		double maxdistance = Double.MIN_VALUE;

		List<Integer> maxdistancelist = new ArrayList<Integer>();
		Set<Entry<Integer, Double>> set = failtestcasedistancemap.entrySet();
		for (Entry entry : set) {
			// System.out.println(entry.getKey()+":"+entry.getValue());
			double entryvalue = (Double) entry.getValue();
			if (entryvalue > maxdistance) {
				maxdistanceindex = (Integer) entry.getKey();
				maxdistance = entryvalue;
			}

		}

		for (Entry entry : set) {
			double entryvalue = (Double) entry.getValue();
			if (entryvalue == maxdistance) {
				maxdistancelist.add((Integer) entry.getKey());
			}
		}
		System.out.println(maxdistanceindex + ":" + maxdistance);
		System.out.println(maxdistancelist);
	}
}
