

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Properties;

public class Readcoverage {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// begin 配置信息
		Properties props = new Properties();// 使用Properties类来加载属性文件
		FileInputStream iFile = new FileInputStream(new File("version.properties"));
		props.load(iFile);
		String experimentpath = props.getProperty("实验路径");
		String coveragematrixname = props.getProperty("覆盖矩阵名");
		Integer testcasenumber = Integer.valueOf(props.getProperty("测试用例数"));
		// end
		File folder = new File(experimentpath);
		PrintStream ps = new PrintStream(coveragematrixname);
		File[] files = folder.listFiles();
		int[] arrayfilename = new int[testcasenumber];

		for (int count1 = 0; count1 < testcasenumber; count1++) {
			arrayfilename[count1] = Integer.parseInt(files[count1].getName());
		}

		Arrays.sort(arrayfilename);
		for (int count2 = 0; count2 < testcasenumber; count2++) {
			File f = new File(experimentpath + "\\" + Integer.toString(arrayfilename[count2]));
			BufferedReader br = new BufferedReader(new FileReader(f));
			String s = "";
			String s1 = "";
			String cover = "1";
			String uncover = "0";
			while ((s = br.readLine()) != null) {
				String[] sourceStrArrays = s.split(":");
				if (sourceStrArrays[1].replace(" ", "").equals("0")) {
					continue;
				}
				if (sourceStrArrays[0].replace(" ", "").equals("#####")
						|| sourceStrArrays[0].replace(" ", "").equals("-")) {
					System.out.print(uncover + "\t");
					ps.print(uncover + "\t");
				} else {
					System.out.print(cover + "\t");
					ps.print(cover + "\t");
				}
			}
			System.out.print("\n");
			ps.print("\n");
		}
		ps.close();
	}
}
