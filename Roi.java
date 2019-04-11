public class Roi extends Piece
{
	public Roi(int color)
	{
		super();
		this.color = color;
	}

	@Override
	public void displayShell()
	{
		String s;
		s = (color == 1 ? "\u001B[38;5;10m" : "\u001B[38;5;15m");
		s += "K";
		System.out.print(s);
	}
	
	@Override
	public boolean isMovePossible(int fx,int fy,int tx,int ty)
	{
		int dx = tx - fx;
		int dy = ty - fy;
		
		if(dx == 0 && dy == 0) return false;
		return (dx <= 1) && (dx >= -1) && (dy <= 1) && (dy >= -1);
	}
}
