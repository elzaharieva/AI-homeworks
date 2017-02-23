package bg.uni_sofia.fmi.ai;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
	
	private static File file = new File("resources/iris.data");

	
	public static void main(String[] args) {
		DataReader dr = new DataReader(file);
		List<DataInstance> dataToClassificate = dr.readDataFromFile();
		List<DataInstance> myData = splitToEntities(dataToClassificate);
		KNN knn = new KNN(myData, dataToClassificate);
		knn.kNearestNeighbours();
	}
	
	public static List<DataInstance> splitToEntities(List<DataInstance> all) {
		List<DataInstance> myData = new ArrayList<>();
		Random rn = new Random();
		DataInstance di;
		for(int i=0; i<30; i++) {
			int k = rn.nextInt(all.size());
			di = all.get(k);
			all.remove(k);
			myData.add(di);
		}
		return myData;
	}
}
