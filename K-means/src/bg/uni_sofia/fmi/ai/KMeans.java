package bg.uni_sofia.fmi.ai;

import java.util.Random;
import java.util.Vector;

public class KMeans {
	private int k;
	private Vector<Point> points;
	private Cluster[] clusters;
	
	public KMeans(int k, Vector<Point> p) {
		this.k = k;
		this.points = p;
		clusters = new Cluster[k];
	}
	
	public Cluster[] getClusters() {
		return clusters;
	}
	
	public void kMeans() {
		getRandomStart();
		boolean unstable = true;
		while(unstable) {
			unstable = cluster();
			calculateCenters();
		}
		
	}
	
	private void getRandomStart() {
		Random rn = new Random();
		for (int i=0; i<k; i++) {
			int index = rn.nextInt(points.size());
			clusters[i] = new Cluster(points.elementAt(index).getX(), points.elementAt(index).getY());
		}
	}
	
	private void calculateCenters() {
		for(int i=0; i<clusters.length; i++) {
			clusters[i].setNewCenter();
		}
	}
	
	
	private boolean cluster () {
		boolean flag = false;
		for (int i=0; i<points.size(); i++) {
			selectCluster(points.elementAt(i), flag);
		}
		return flag;
	}
	
	private void selectCluster (Point p, boolean flag) {
		int index = 0;
		for(int i=0; i<clusters.length; i++) {
			if(calculateDistance(p, clusters[i])<calculateDistance(p, clusters[index])) {
				index = i;
			}
		}
		if(p.getClusterIndex()!= index) {
			flag = true;
			if(p.getClusterIndex()!=-1) {
				clusters[p.getClusterIndex()].remove(p);
			}
			clusters[index].add(p);
			p.setClusterIndex(index);
		}
	}
	
	private double calculateDistance(Point p, Cluster c) {
		return Math.sqrt(Math.pow(c.getCenter()[1]-p.getY(), 2) + Math.pow(c.getCenter()[0]-p.getX(), 2));
	}
	
	public void print() {
		for(int i=0; i<clusters.length; i++) {
			System.out.println("Cluster number "+(i+1)+":");
			clusters[i].print();
		}
	}
}
