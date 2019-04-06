public class Tour extends Piece
{
	public Tour(int color)
	{
		super();
		this.color = color;
	}

	@Override
	public void displayShell()
	{
		String s;
		s = (color == 0 ? "\u001B[38;5;10m" : "\u001B[38;5;15m");
		s += "T";
		System.out.print(s);
	}
	
	@Override
	public boolean isMovePossible(int fx,int fy,int tx,int ty) {
		return (tx == fx) ^ (ty == fy);
	}
}