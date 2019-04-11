public class Reine extends Piece
{
	public Reine(int color)
	{
		super();
		this.color = color;
	}

	@Override
	public void displayShell()
	{
		String s;
		s = (color == 1 ? "\u001B[38;5;10m" : "\u001B[38;5;15m");
		s += "Q";
		System.out.print(s);
	}
	
	@Override
	public boolean isMovePossible(int fx,int fy,int tx,int ty)
	{
		int dx = tx - fx;
		int dy = ty - fy;

		boolean fou = (dx == dy) ^ (dx == -dy);
		boolean tour = (tx == fx) ^ (ty == fy);

		return fou ^ tour;
	}
}
