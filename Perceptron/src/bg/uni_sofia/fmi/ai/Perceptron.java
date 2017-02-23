package bg.uni_sofia.fmi.ai;

import java.util.Random;

public class Perceptron {
	private boolean[][] input;
	private double sigma;
	private int[] output;
	private double learningRate = 0.1;
	
	public Perceptron(boolean[][] in, double s) {
		this.input = new boolean[in.length][in[0].length];
		this.input = in;
		this.sigma = s;
		this.output = new int[in.length];
		for (int i = 0; i < in.length; i++) {
			output[i] = booleanToInt(logicalOperation(in[i]));
		}
	}

	public void train() {
		Random rn = new Random();
		double[] weight = new double[input[0].length];
		int error;
		for (int i = 0; i < input[0].length; i++) {
			weight[i] = rn.nextDouble();
		}
		while (true) {
			boolean errFlag = false;
			for (int i = 0; i < input.length; i++) {
				double sum = 0.0;
				for (int j = 0; j < input[i].length; j++) {
					sum += booleanToInt(input[i][j]) * weight[j];
				}
				error = output[i]-booleanToInt(sum>sigma);
				if (error != 0) {
					errFlag = true;
					for (int k=0; k<weight.length; k++) {
						weight[k]+=error*booleanToInt(input[i][k])*learningRate;
					}
				}
			}
			if (!errFlag) {
				for (int i = 0; i < weight.length; i++) {
					System.out.println(weight[i]);
				}
				break;
			}

		}
	}

	private boolean logicalOperation(boolean[] arr) {
		boolean res = arr[0];
		for (int i = 0; i < arr.length; i++) {
			res = res && arr[i];
		}
		return res;
	}
	private int booleanToInt(boolean a) {
		return a?1:0;
	}
	
	public static void main(String[] args) {
		boolean[][] input = {{false, false}, {false, true}, {true, false}, {true, true}};
		double sigma = 1.0;
		Perceptron p = new Perceptron(input, sigma);
		p.train();
	}
}
