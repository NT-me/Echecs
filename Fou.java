public class Fou extends Piece
{
	public Fou(int color)
	{
		super();
		this.color = color;
	}

	@Override
	public void displayShell()
	{
		String s;
		s = (color == 1 ? "\u001B[38;5;10m" : "\u001B[38;5;15m");
		s += "F";
		System.out.print(s);
	}
	
	@Override
	public boolean isMovePossible(int fx,int fy,int tx,int ty)
	{
		int dx = tx - fx;
		int dy = ty - fy;
		
		//si a = b et a = -b alors a = 0 et b = 0
		//d'ou le Xor 
		return (dx == dy) ^ (dx == -dy) ;
	}
}
