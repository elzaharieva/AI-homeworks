package bg.uni_sofia.fmi.ai;

public class Point {
	private double x;
	private double y;
	private int clusterIndex = -1;

	public Point(String line) {
		line = makeValid(line);
		this.x = Double.parseDouble(line.split(" ")[0]);
		this.y = Double.parseDouble(line.split(" ")[1]);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public int getClusterIndex() {
		return clusterIndex;
	}
	
	public void setClusterIndex(int clusterIndex) {
		this.clusterIndex = clusterIndex;
	}
	
	private String makeValid(String line) {
		line.trim();
		char[] arr = line.toCharArray();
		line = "";
		for(int i = 0; i<arr.length; i++) {
			if(!(arr[i]>='0' && arr[i]<='9') && arr[i]!='.') {
				arr[i] = ' ';
			}
			line+=arr[i];
		}
		return line;
	}
}
