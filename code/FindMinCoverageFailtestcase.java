// The class is used to find a failed test case having the minimum coverage
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.Scanner;

public class FindMinCoverageFailtestcase {
	// 找出最小覆盖的失败测试用例
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
			// list.clear();
		}
		// 3.寻找最小覆盖list
		int mincoverIndex = Integer.MAX_VALUE;
		int mincoverLength = Integer.MAX_VALUE;
		List<Integer> maxcoverlist = new ArrayList<Integer>();
		// 存放最小覆盖的list
		// 1.只有一个最小覆盖
		// 2.如果有多个最小覆盖
		for (Entry<Integer, Integer> es : hm2.entrySet()) {
			if (es.getValue() < mincoverLength) {
				mincoverLength = es.getValue();
				mincoverIndex = es.getKey();
			}
		}
		// 输出所有的最小覆盖的失败测试用例的Index
		for (Entry<Integer, Integer> es : hm2.entrySet()) {
			if (es.getValue() == mincoverLength) {
				System.out.println(es.getKey());
			}
		}
	}
}
