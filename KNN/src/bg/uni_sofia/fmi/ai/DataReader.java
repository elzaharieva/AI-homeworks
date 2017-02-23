package bg.uni_sofia.fmi.ai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
	private File file;
	
	public DataReader(File file) {
		this.file = file;
	}
	
	public List<DataInstance> readDataFromFile() {
		List<DataInstance> list = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = br.readLine();
			while(line!=null){
				DataInstance di = new DataInstance(line);
				if(di.isValid()) {
					list.add(di);
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
