

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class ReadFaultMatrix {
	public static void main(String[] args) throws Exception {
		Properties props = new Properties();// 使用Properties类来加载属性文件
		FileInputStream iFile = new FileInputStream(new File("version.properties"));
		props.load(iFile);
		String faultMatrixname = props.getProperty("错误矩阵名");
		Integer testcasenumber = Integer.valueOf(props.getProperty("测试用例数"));
		Integer versionsnumber = Integer.valueOf(props.getProperty("版本数目"));

		String[][] executeresult = new String[testcasenumber][versionsnumber];
		File f = new File(faultMatrixname);
		Scanner input = new Scanner(f);
		while (input.hasNext()) {
			for (int count1 = 0; count1 < testcasenumber; count1++) {
				for (int count2 = 0; count2 < versionsnumber; count2++) {
					executeresult[count1][count2] = input.next();
				}
			}
		}
		input.close();
		for (int count1 = 0; count1 < testcasenumber; count1++) {
			for (int count2 = 0; count2 < versionsnumber; count2++) {
				System.out.print(executeresult[count1][count2] + "   ");
			}
			System.out.println();
		}
	}
}
