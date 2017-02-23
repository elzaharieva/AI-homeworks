package bg.uni_sofia.fmi.ai;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter N:");
		int n = scan.nextInt();
		Board board = new Board(n);
		board.nextMove();

		for (int i = 0; i < board.getBoard().length; i++) {
			char[] arr = new char[board.getBoard().length];
			Arrays.fill(arr, '_');
			arr[board.getBoard()[i]] = '*';
			System.out.println(Arrays.toString(arr));

		}

	}
}
