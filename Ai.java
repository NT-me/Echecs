import java.util.concurrent.ThreadLocalRandom;

public class Ai
{
	private Chessboard cb;
	private int color;
	private boolean alive;

	public Ai(Chessboard cb)
	{
		this.cb = cb;
		this.alive = true;
	}
	
	public Ai(Chessboard cb,int color)
	{
		this.cb = cb;
		this.color = color;
		this.alive = true;
	}

	public boolean getAlive()
	{ return this.alive; }

	public void makeMove() {
		
		int move[] = {-1,-1,-1,-1};
		int rv;
		do{
			move[0] = ThreadLocalRandom.current().nextInt(0, 7 + 1);
			move[1] = ThreadLocalRandom.current().nextInt(0, 7 + 1);
			move[2] = ThreadLocalRandom.current().nextInt(0, 7 + 1);
			move[3] = ThreadLocalRandom.current().nextInt(0, 7 + 1);
			rv = cb.mouvement(this.color,move,true);
		} while(rv == -1);
		System.out.printf("#%d%d%d%d#\n",move[0],move[1],move[2],move[3]);
		cb.displayShell();
		
		this.alive = false;
		Box[][] board = cb.getBoard();
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				if(board[j][i].getPiece() != null) {
					if(board[j][i].getTypePiece().equals("Roi") 
						&& board[j][i].getColor() == this.color)
						this.alive = true;

				}
			}
		}
	}
}
