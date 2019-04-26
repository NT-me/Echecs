public class Pion extends Piece
{
	/**
	 *
	 *
	 */
	public Pion(int color)
	{
		super();
		this.color = color;
	}
	
	@Override
	public void displayShell()
	{
		String s;
		s = (color == 1 ? "\u001B[38;5;10m" : "\u001B[38;5;15m");
		s += "P";
		System.out.print(s);
	}
	
	@Override
	public boolean isMovePossible(int fx,int fy,int tx,int ty)
	{
		if(this.color == 0)
		{
			if(fy == 1)
			{
				boolean diag = ( (tx - fx == 1) || (tx - fx == -1) ) && (ty - fy == 1);
				return  ( (tx == fx) && (ty - fy <= 2) && (ty - fy >= 1) ) || diag;
			}
			return ( ( (tx == fx) && (ty - fy == 1) ) || ((tx - fx == 1) || (tx - fx == -1) ) && (ty - fy == 1) );

		} else {
			if(fy == 6)
			{
				boolean diag = ((tx - fx == 1) || (tx - fx == -1) ) && (ty - fy == -1);
				return  ( (tx == fx) && (ty - fy >= -2) && (ty - fy <= -1) ) || diag;
			}
			return ( ( (tx == fx) && (ty - fy == -1) )|| ((tx - fx == 1) || (tx - fx == -1) ) && (ty - fy == -1));
		}
	}


}
