package bg.uni_sofia.fmi.bg;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class GeneticAlgorithm {
	private List<KnapsackObj> all;
	Vector<Solution> q;
	
	public GeneticAlgorithm(List<KnapsackObj> all, Vector<Solution> q) {
		this.all = all;
		this.q = q;
	}
	
	public void geneticAlgorithm() {
		for(int i=0; i<Main.ITERATIONS_COUNT; i++) {
			updateQueue();
//			System.out.println(getBestFitness());
		}
	}
	
/*	public long getBestFitness() {
		Solution best = q.get(0);
		for(int i=0; i<q.size(); i++) {
			if(q.get(i).getCostSum()>best.getCostSum()) {
				best = q.get(i);
			}
		}
		return best.getCostSum();
	}
*/	
	public Solution getBestSolution() {
		Solution best = q.get(0);
		for(Solution s:q) {
			if(s.getCostSum()>best.getCostSum()) best = s;
		}
		return best;
	}
	
	private void updateQueue() {
		List<Boolean> crossovered = crossover();
		mutate(crossovered);
		long weight = calculateWeightSum(crossovered);
		if(weight<=Main.KNAPSACK_WEIGHT_CAPACITY) {
			long cost = calculateCostSum(crossovered);
			Solution child = new Solution(crossovered, weight, cost);
			if(cost>getMinCostElement().getCostSum()) {
				q.remove(getMinCostElement());
				q.add(child);
			}
		}
	}
	
	private Solution getMinCostElement() {
		Solution min = q.get(0);
		for(Solution s:q) {
			if (s.getCostSum()<min.getCostSum()) min = s;
		}
		return min;
	}
	
	private List<Boolean> crossover() {
		Random rn = new Random();
		Solution first = q.get(rn.nextInt(q.size()));
		Solution second = q.get(rn.nextInt(q.size()));
		List<Boolean> newChromosome = new ArrayList<>();
		for (int i = 0; i<first.getSolution().size()/2; i++) {
			newChromosome.add(i, first.getSolution().get(i));
		}
		for (int i=first.getSolution().size()/2; i<second.getSolution().size(); i++) {
			newChromosome.add(i, second.getSolution().get(i));
		}
		return newChromosome;
	}
	
	private long calculateCostSum(List<Boolean> chromosome) {
		long sum = 0;
		for(int i=0; i<chromosome.size(); i++) {
			if(chromosome.get(i)) sum+=all.get(i).getCost();
		}
		return sum;
	}
	
	private long calculateWeightSum(List<Boolean> chromosome) {
		long sum = 0;
		for(int i=0; i<chromosome.size(); i++) {
			if (chromosome.get(i)) sum+=all.get(i).getWeight();
		}
		return sum;
	}
	
	private void mutate(List<Boolean> chromosome){
		Random rn = new Random();
		int index = rn.nextInt(chromosome.size());
		chromosome.add(index, !chromosome.get(index));
		chromosome.remove(index+1);
	}

}
