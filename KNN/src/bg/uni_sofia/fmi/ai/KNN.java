package bg.uni_sofia.fmi.ai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class KNN {
	private final int K = 3;
	private List<DataInstance> data;
	private List<DataInstance> toClassificate;

	public KNN(List<DataInstance> data, List<DataInstance> toClassificate) {
		this.data = data;
		this.toClassificate = toClassificate;
	}

	public void kNearestNeighbours() {
		int countMistakes = 0;
		List<DataInstance> nearest = new ArrayList<>();
		for (DataInstance di : toClassificate) {
			nearest = classificate(di);
			if(!di.getClassName().equals(mostCommon(nearest))) {
				countMistakes++;
			}
		}
		
		System.out.println("Accuracy = "+(toClassificate.size()-countMistakes)*100/toClassificate.size()+"%");
	}

	private String mostCommon(List<DataInstance> nearest) {
		Map<String, Integer> map = new HashMap<>();
		for(DataInstance di:nearest) {
			String s = di.getClassName();
			if(!map.containsKey(s)) {
				map.put(s, 1);
			} else {
				map.put(s, map.get(s)+1);
			}
		}
		Entry<String, Integer> max = null;
		for(Entry<String, Integer> e:map.entrySet()) {
			if(max == null || max.getValue() < e.getValue()) {
				max = e;
			}
		}
		return max.getKey();
	}

	public List<DataInstance> classificate(DataInstance di) {
		List<DataInstance> nearest = new ArrayList<>();
		double maxDistance;
		for (DataInstance d : data) {
			double distance = calculateDistance(di, d);
			d.setDistance(distance);
			if (nearest.size() < K) {
				nearest.add(d);
			} 
			maxDistance = getFurthest(nearest).getDistance();
			if (distance < maxDistance && nearest.size()>=K) {
				nearest.remove(getFurthest(nearest));
				nearest.add(d);
			}
		}
		return nearest;
	}

	private double calculateDistance(DataInstance d1, DataInstance d2) {
		double distance = 0;
		for (int i = 0; i < d1.getAttributes().length; i++) {
			distance += Math.abs(d1.getAttributes()[i] - d2.getAttributes()[i]);
		}
		return distance;
	}

	private DataInstance getFurthest(List<DataInstance> nearest) {
		DataInstance furthest = nearest.get(0);
		for (int i = 0; i < nearest.size(); i++) {
			if (nearest.get(i).getDistance() > furthest.getDistance()) {
				furthest = nearest.get(i);
			}
		}
		return furthest;
	}
}
