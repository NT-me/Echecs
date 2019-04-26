/**
 * Classe representant une case de jeu
 */
public class Box
{
	//backColor never to be touched
	private int backColor;

	private Piece piece;
	
	/**
	 * Constructeur
	 */
	public Box(int color)
	{
		this.backColor = color;
		this.piece = null;
	}

	/**
	 * Constructeur
	 * @param p Piece a mettre dans la case
	 */
	public void changePiece(Piece p)
	{
		this.piece = p;
	}

	/**
	 * Affiche la case
	 */
	public void displayShell()
	{
		if(backColor == 0) 
		{
			//background set to GREY
			System.out.print("\u001B[48;5;8m");
		}
		else
		{
			//Background set to BLACK
			System.out.print("\u001B[48;5;0m");
		}
		

		if(this.piece != null)
			this.piece.displayShell();
		else
			System.out.print(" ");

		System.out.print("\u001B[0m");
	}
	
	/** 
	 * Retourne le nom de la classe sous forme de string
	 * @return le nom de la classe
	 */
	public String getTypePiece() {
		try {
			return piece.getClass().getSimpleName();
		}catch (NullPointerException e){
			return null;
		}
	}
	
	/**
	 * Accesseur de piece
	 */
	public Piece getPiece(){
		return this.piece;
	}

	/**
	 * Accesseur de la color de la piece
	 */
	public int getColor(){
		return this.piece.getColor();
	}
}

