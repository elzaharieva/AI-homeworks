package bg.uni_sofia.fmi.bg;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Main {
	public final static long KNAPSACK_WEIGHT_CAPACITY = 400;
	public final static int QUEUE_CAPACITY = 28;
	public final static int ITERATIONS_COUNT = 500;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter N: ");
		int n = scan.nextInt();
		String string;
		long[] arr;
		List<KnapsackObj> all = new ArrayList<>();
		scan = new Scanner(System.in);
		for (int i = 0; i < n; i++) {
			System.out.println("Enter a value: ");
			string = scan.nextLine();
			arr = new long[2];
			for(int j=0; j<arr.length; j++) {
				arr[j] = Long.parseLong(string.split(" ")[j]);
			}
			all.add(new KnapsackObj(arr));
		}
		Vector<Solution> queue = new Vector<>();
		for(int i=0; i<QUEUE_CAPACITY; i++) {
			queue.add(generateSolution(all, KNAPSACK_WEIGHT_CAPACITY));
		}
		GeneticAlgorithm ga = new GeneticAlgorithm(all, queue);
		ga.geneticAlgorithm();
		Solution solution = ga.getBestSolution();
		System.out.println(solution.getCostSum());
	}
	
	public static Solution generateSolution(List<KnapsackObj> all, long weightCapacity) {
		List<Boolean> list = new ArrayList<>();
		int weightSum = 0;
		int costSum = 0;
		Random rn = new Random();
		for(int i=0; i<all.size(); i++) {
			if(rn.nextInt(2)==1 && weightSum+all.get(i).getWeight()<weightCapacity){
				list.add(i, true);
				weightSum += all.get(i).getWeight();
				costSum += all.get(i).getCost();
			}
			else list.add(i, false);			
		}
		Solution solution = new Solution(list, weightSum, costSum);
		return solution;
	}
	
}
