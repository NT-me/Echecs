import java.util.concurrent.ThreadLocalRandom;

public class Ai
{
	private Chessboard cb;
	private int color;


	public Ai(Chessboard cb)
	{
		this.cb = cb;
	}
	
	public Ai(Chessboard cb,int color)
	{
		this.cb = cb;
		this.color = color;
	}

	public void makeMove() {
		
		int move[] = {-1,-1,-1,-1};
		int rv;
		do{
			move[0] = ThreadLocalRandom.current().nextInt(0, 7 + 1);
			move[1] = ThreadLocalRandom.current().nextInt(0, 7 + 1);
			move[2] = ThreadLocalRandom.current().nextInt(0, 7 + 1);
			move[3] = ThreadLocalRandom.current().nextInt(0, 7 + 1);
			rv = cb.mouvementAi(this.color,move);
		} while(rv == -1);
		System.out.printf("#%d%d%d%d#\n",move[0],move[1],move[2],move[3]);
		cb.displayShell();
	}
}
