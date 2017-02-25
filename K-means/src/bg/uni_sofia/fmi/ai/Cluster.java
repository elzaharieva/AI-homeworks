package bg.uni_sofia.fmi.ai;

import java.util.Vector;

public class Cluster {
	private double[] center;
	private Vector<Point> elements;
	
	public Cluster(double x, double y) {
		center = new double[2];
		center[0] = x;
		center[1] = y;
		elements = new Vector<Point>();
	}
	
	public void setCenter(double x, double y) {
		center = new double[2];
		center[0] = x;
		center[1] = y;
	}
	
	public double[] getCenter() {
		return center;
	}
	
	public Vector<Point> getElements() {
		return elements;
	}
	
	public void add(Point p) {
		elements.addElement(p);
	}
	
	public void remove(Point p) {
		if(elements.contains(p)) {
			elements.remove(p);	
		}
	}
	
	public void setNewCenter() {
		double x = 0.0;
		double y = 0.0;
		for(int i=0; i<elements.size(); i++) {
			x+=elements.elementAt(i).getX();
			y+=elements.elementAt(i).getY();
		}
		setCenter(x/elements.size(), y/elements.size());
	}
	
	public void print() {
		for(int i=0; i<elements.size(); i++) {
			System.out.println(elements.elementAt(i).getX()+" "+elements.elementAt(i).getY());
		}
	}
}