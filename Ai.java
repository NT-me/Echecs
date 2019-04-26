import java.util.concurrent.ThreadLocalRandom;

/**
 * Classe de l'IA
 */
public class Ai
{
	private Chessboard cb;
	private int color;
	private boolean alive;
	
	/**
	 * Constructeur
	 */
	public Ai(Chessboard cb)
	{
		this.cb = cb;
		this.alive = true;
	}
	
	/**
	 * Constructeur
	 */
	public Ai(Chessboard cb,int color)
	{
		this.cb = cb;
		this.color = color;
		this.alive = true;
	}

	/**
	 * Accesseur de alive
	 * @return alive
	 */
	public boolean getAlive()
	{ return this.alive; }
	
	/**
	 * test si l'ia est en vie
	 */
	private void testAlive()
	{
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
	
	/**
	 * Tour de jeu de l'IA
	 */
	public void makeMove() {
		this.testAlive();
		if(this.alive == false) return;

		int move[] = {-1,-1,-1,-1};
		int rv;
		if( this.cb.areYouInEchec( this.cb.findKing(this.color), this.color) == true)
		{
            System.out.printf("%s vous etes en échec, vous devriez bouger !\n",(this.color == 0 ? "Blanc" : "Vert"));
		}
		if( this.cb.areYouInEchecEtMat(this.color) == true )
		{
            System.out.printf("%s vous etes en échec et mat\n",(this.color == 0 ? "Blanc" : "Vert"));
			this.alive = false;
			return;
		}
		do{
			move[0] = ThreadLocalRandom.current().nextInt(0, 7 + 1);
			move[1] = ThreadLocalRandom.current().nextInt(0, 7 + 1);
			move[2] = ThreadLocalRandom.current().nextInt(0, 7 + 1);
			move[3] = ThreadLocalRandom.current().nextInt(0, 7 + 1);
			rv = cb.mouvement(this.color,move,true);
		} while(rv == -1);
		System.out.printf("#%d%d%d%d#\n",move[0],move[1],move[2],move[3]);
		cb.displayShell();
		
		this.testAlive();
		
	}
}
