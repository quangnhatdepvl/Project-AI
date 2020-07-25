package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

public class test {
	public static void main(String[] args) throws Exception {
		File file = new File("D:\\test\\test.txt");
		if (!file.exists()) {
			FileWriter FW = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(FW);
			String header = "MSSV" + "\t";
			header += "NAME" + "\t";
			header += "CLASS" + "\t";
			header += "THOI GIAN";
			bw.write(header);
			bw.write("\n");
			bw.write("1");
			bw.write("\t");
			bw.write("2");
			bw.write("\t");
			bw.write("3");
			bw.write("\t");
			bw.write("4");
			bw.write("\n");
			bw.flush();
		} else {
			FileWriter FW = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(FW);
			bw.write("5");
			bw.write("\t");
			bw.write("6");
			bw.write("\t");
			bw.write("7");
			bw.write("\t");
			bw.write("8");
			bw.write("\n");
			bw.flush();
		}
		System.out.println(file.exists());

	}

	private static void abcsa() throws Exception {
		File file = new File("D:\\test.txt");
		FileWriter FW = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(FW);
		bw.write("1");
		bw.write("\t");
		bw.write("2");
		bw.write("\t");
		bw.write("3");
		bw.write("\t");
		bw.write("4");
		bw.write("\n");
		bw.flush();
	}
}
