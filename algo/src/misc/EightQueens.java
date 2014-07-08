package misc;

public class EightQueens
{
	private int[][] board;
	private int[][] attacked;
	private int numOfQueens;

	public static void main(String args[])
	{
		EightQueens eightQueens = new EightQueens(8);
		eightQueens.solve();
	}

	public EightQueens()
	{
		board = new int[8][8];
		attacked = new int[8][8];
		this.numOfQueens = 8; // default
	}

	public EightQueens(int numOfQueens)
	{
		this();
		this.numOfQueens = numOfQueens;
	}

	public void solve()
	{
		solve(0);
	}

	private void print()
	{
		System.out.println("\n---------------------------\n");
		for (int row = board.length - 1; row >= 0; row--)
		{
			System.out.print("Row[" + (row + 1) + "] :");
			for (int col = 0; col < board.length; col++)
			{
				System.out.print(board[row][col] + " |");
			}
			System.out.println();
		}
		System.out.println("\n---------------------------");
	}

	private void solve(int row)
	{
		if (row == numOfQueens)
		{
			print();
			return;
		}

		for (int col = 0; col < board.length; col++)
		{
			if (attacked[row][col] == 0)
			{
				put(row, col);
				solve(row + 1);
				remove(row, col);
			}
		}
	}

	private void remove(int row, int col)
	{
		computeMatrix(row, col, 0, false);
	}

	private void put(int row, int col)
	{
		computeMatrix(row, col, 1, true);
	}

	private void computeMatrix(int row, int col, int val, boolean isAdd)
	{
		board[row][col] = val;
		int matrix[][] = attacked;
		int size = board.length;
		for (int i = col + 1; i < size; i++)
		{ // right horizontal
			int curVal = matrix[row][i];
			matrix[row][i] = isAdd ? curVal + 1 : curVal - 1;
		}
		for (int i = col - 1; i >= 0; i--)
		{ // left horizontal
			int curVal = matrix[row][i];
			matrix[row][i] = isAdd ? curVal + 1 : curVal - 1;
		}

		for (int i = row + 1; i < size; i++)
		{ // up vertical
			int curVal = matrix[i][col];
			matrix[i][col] = isAdd ? curVal + 1 : curVal - 1;
		}

		for (int i = row - 1; i >= 0; i--)
		{ // down vertical
			int curVal = matrix[i][col];
			matrix[i][col] = isAdd ? curVal + 1 : curVal - 1;
		}

		// diagonals
		for (int i = row + 1, j = col + 1; i < size && j < size; i++, j++)
		{ // up right
			int curVal = matrix[i][j];
			matrix[i][j] = isAdd ? curVal + 1 : curVal - 1;
		}

		for (int i = row + 1, j = col - 1; i < size && j >= 0; i++, j--)
		{ // up left
			int curVal = matrix[i][j];
			matrix[i][j] = isAdd ? curVal + 1 : curVal - 1;
		}

		for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--)
		{ // down left
			int curVal = matrix[i][j];
			matrix[i][j] = isAdd ? curVal + 1 : curVal - 1;
		}

		for (int i = row - 1, j = col + 1; i >= 0 && j < size; i--, j++)
		{ // down right
			int curVal = matrix[i][j];
			matrix[i][j] = isAdd ? curVal + 1 : curVal - 1;
		}
	}
}
