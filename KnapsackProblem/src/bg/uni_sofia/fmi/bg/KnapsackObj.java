package bg.uni_sofia.fmi.bg;

public class KnapsackObj {
	private long weight;
	private long cost;

	public KnapsackObj(long[] arr) {
		if (isValid(arr)) {
			this.weight = arr[0];
			this.cost = arr[1];
		}
	}
	
	public long getWeight() {
		return weight;
	}

	public long getCost() {
		return cost;
	}
	
	private boolean isValid(long[] arr) {
		if (arr.length != 2)
			return false;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < 0)
				return false;
		}
		return true;
	}
}
