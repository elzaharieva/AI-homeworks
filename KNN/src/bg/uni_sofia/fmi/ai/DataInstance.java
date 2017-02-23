package bg.uni_sofia.fmi.ai;

public class DataInstance {
	private double[] attributes;
	private String className;
	private double distance;
	
	public DataInstance(String line){
		String[] arr = line.split(",");
		this.className = arr[arr.length-1].trim();
		this.attributes = getAttributesFromLine(line);
		}
	
	public double[] getAttributes() {
		return attributes;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public String getClassName() {
		return className;
	}
	
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public boolean isValid() {
		return attributes.length == 4 && className!=null;
	}
	
	
	public static double[] getAttributesFromLine(String line) {
		String[] arr = line.split(",");
		double[] result = new double[arr.length-1];
		for(int i=0; i<arr.length-1; i++) {
			result[i] = Double.parseDouble(arr[i].trim());
		}
		return result;
	}
}


