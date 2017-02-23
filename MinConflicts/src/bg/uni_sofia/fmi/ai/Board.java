package bg.uni_sofia.fmi.ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Board {
	private int[] board;

	public Board(int n) {
		this.board = createBoard(n);
	}

	public int[] getBoard() {
		return board;
	}

	public void setBoard(int[] board) {
		this.board = board;
	}

	public int countConflicts(int x, int y) {
		int count = 0;
		for (int i = 0; i < board.length; i++) {
			if (i != x) {
				if ((board[i]) == y || Math.abs(i - x) == Math.abs(board[i] - y))
					count++;
			}
		}
		return count;
	}

	public void nextMove() {
		int moves = 0;
		List<Integer> options = new ArrayList<>();
		List<Integer> nextMove = new ArrayList<>();
		while (true) {
			options.clear();
			int maxConflicts = 0;
			for (int i = 0; i < board.length; i++) {
				int count = countConflicts(i, board[i]);
				if (count == maxConflicts) {
					options.add(i);
				}
				if (count > maxConflicts) {
					options.clear();
					options.add(i);
					maxConflicts = count;
				}
			}
			if (maxConflicts == 0) {
				return;
			}
			Random rn = new Random();
			nextMove.clear();
			if (!options.isEmpty()) {
				int next = options.get(rn.nextInt(options.size()));
				int minConflicts = maxConflicts;
				for (int j = 0; j < board.length; j++) {
					int conflictsCount = countConflicts(next, j);
					if(conflictsCount == minConflicts) {
						nextMove.add(j);
					}
					if (conflictsCount < minConflicts) {
						nextMove.clear();
						nextMove.add(j);
						minConflicts = conflictsCount;
					}
				}
				board[next] = nextMove.get(rn.nextInt(nextMove.size()));
				
			}
			moves++;
			if (moves == board.length*3) {
				setBoard(createBoard(board.length));
				moves = 0;
			}
		}
	}

	private static int[] createBoard(int n) {
		int[] board = new int[n];
		Random rn = new Random();
		for (int i = 0; i < n; i++) {
			int k = rn.nextInt(n);
			board[i] = k;
		}
		return board;
	}

}
