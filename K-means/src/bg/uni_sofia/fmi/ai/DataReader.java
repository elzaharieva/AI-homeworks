package bg.uni_sofia.fmi.ai;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class DataReader {
	private String filePath;
	
	public DataReader(String path) {
		filePath = path;
	}
	
	public Vector<Point> readData() {
		Vector<Point> points = new Vector<>();
		String line;
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			line = br.readLine();
			while(line!=null) {
				points.addElement(new Point(line));
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return points;
	}
}
