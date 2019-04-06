public class Box
{
	private int backColor;
	private Piece piece;

	public Box(int color)
	{
		this.backColor = color;
		this.piece = null;
	}
	
	public void changePiece(Piece p)
	{
		this.piece = p;
	}

	public void displayShell()
	{
		if(backColor == 0) 
		{
			//background set to GREY
			System.out.print("\u001B[48;5;8m");
		}
		else
		{
			//Background set to WHITE
			System.out.print("\u001B[48;5;0m");
		}
		

		if(this.piece != null)
			this.piece.displayShell();
		else
			System.out.print(" ");

		System.out.print("\u001B[0m");
	}

	public String getPiece() {
		return piece.getClass().getSimpleName();
	}

	public int getColor(){
		return this.piece.getColor();
	}
}