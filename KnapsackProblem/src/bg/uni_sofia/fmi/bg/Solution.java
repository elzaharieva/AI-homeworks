package bg.uni_sofia.fmi.bg;

import java.util.List;

public class Solution {
	private List<Boolean> solution;
	private long weightSum;
	private long costSum;
	
	public Solution(List<Boolean> solution, long weightSum, long costSum) {
		this.solution = solution;
		this.weightSum = weightSum;
		this.costSum = costSum;
	}
	
	public List<Boolean> getSolution() {
		return solution;
	}
	
	public long getCostSum() {
		return costSum;
	}
	
	 public boolean isValid(long weightCapacity) {
		 return this.weightSum <= weightCapacity;
	 }
}
