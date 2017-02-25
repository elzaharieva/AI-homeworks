package bg.uni_sofia.fmi.ai;

import java.util.Scanner;
import java.util.Vector;

public class Main {
	private static String PATH;
	private static int K;
	//57 or 58 for unbalance.txt
	//14 or 15 for normal.txt
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the name of the file");
		PATH = "./resources/" + scan.nextLine();
		scan = new Scanner(System.in);
		System.out.println("Enter K");
		K = scan.nextInt();
		DataReader dr = new DataReader(PATH);
		Vector<Point> points = dr.readData();
		KMeans km = new KMeans(K, points);
		km.kMeans();
		km.print();
	}
}
