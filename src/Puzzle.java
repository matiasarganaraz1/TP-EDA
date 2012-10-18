import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;


public class Puzzle {

	private static final int SIZE = 3;
	private static final int[] EXPECTED = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 0};
	
	public static void main(String[] args) {
		int[] board = new int[] {
				2, 8, 1,
				6, 0, 5,
				7, 3, 4
		};
		
		solve(board, 1, 1);
	}
	
	private static void solve(int[] board, int emptyRow, int emptyCol) {
		Set<PuzzleState> visited = new HashSet<PuzzleState>();
		Queue<PuzzleState> queue = new LinkedList<PuzzleState>();

		PuzzleState state = new PuzzleState(board, emptyRow, emptyCol, null);
		
		visited.add(state);
		queue.add(state);
		
		while (!queue.isEmpty()) {
			
			state = queue.remove();

			if (state.isSolution()) {
				while (state != null) {
					System.out.println(state);
					state = state.previous;
				}
				return;
			}
			for (PuzzleState neighbor : state.moves()){
				if (!visited.contains(neighbor)) {
					visited.add(neighbor);
					queue.add(neighbor);
				}
			}
		}
	}
	
	
	private static class PuzzleState {
		int[] numbers;
		int emptyRow, emptyCol;
		PuzzleState previous;
		
		PuzzleState(int[] numbers, int emptyRow, int emptyCol, PuzzleState previous) {
			this.numbers = numbers;
			this.emptyCol = emptyCol;
			this.emptyRow = emptyRow;
			this.previous = previous;
		}
		
		public boolean isSolution() {
			return Arrays.equals(numbers, EXPECTED);
		}
		
		public List<PuzzleState> moves() {
			List<PuzzleState> result = new ArrayList<PuzzleState>(4);
			int[][] moves = new int[][] { {0, 1}, {0, -1}, {1, 0}, {-1, 0}};

			for (int k=0; k<moves.length; k++) {
				int newRow = emptyRow + moves[k][0];
				int newCol = emptyCol + moves[k][1];
				
				if (newRow < 0 || newRow >= 3 || newCol < 0 || newCol >= 3) {
					continue;
				}
				PuzzleState newState = new PuzzleState(numbers.clone(), newRow, newCol, this);
				newState.numbers[emptyRow * SIZE + emptyCol] = newState.numbers[newRow * SIZE + newCol];
				newState.numbers[newRow * SIZE + newCol] = 0;
				result.add(newState);
			}
			return result;
		}

		
		@Override
		public String toString() {
			String result = "";
			for (int row = 0; row < SIZE; row++) {
				for (int col = 0; col < SIZE; col++) {
					result += numbers[row * SIZE + col] + " ";
				}
				result += "\n";
			}
			return result;
		}
		
		@Override
		public boolean equals(Object obj) {
			PuzzleState other = (PuzzleState)obj;
			return Arrays.equals(numbers, other.numbers);
		}

		@Override
		public int hashCode() {
			return emptyRow + emptyCol;
		}
		
		
//		static final int[] primes = new int[] {2081, 2857, 3019, 3547, 2297, 3169, 919, 3571, 3491, 2447};
//		
//		@Override
//		public int hashCode() {
//			int result = 0;
//			for (int i=0; i < SIZE * SIZE; i++) {
//				result += numbers[i] * primes[i];
//			}
//			return result;
//		}
		
		
	}
}
