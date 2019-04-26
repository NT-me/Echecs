	/**
	 * 
	 *class représentant la Reine d'un jeu d'échec
	 */
public class Reine extends Piece
{
	/**
	 * constructeur de la class Reine initialise la couleur de la Reine
	 *@param color couleur de la Reine
	 */
	public Reine(int color)
	{
		super();
		this.color = color;
	}
	
	/**
	 *affiche la piece Reine sur le plateau  
	 *
	 */
	@Override
	public void displayShell()
	{
		String s;
		s = (color == 1 ? "\u001B[38;5;10m" : "\u001B[38;5;15m");
		s += "Q";
		System.out.print(s);
	}
	/**
	 * Retourne true si le mouvement est possible
	 * selon les regles de la Reine 
	 * (independamment des autre pieces)
	 * @param fx (from x) la position d'origine en x de la Reine 
	 * @param fy (from y) la position d'origine en y de la Reine 
	 * @param tx (to x) la future position  en x de la Reine 
	 * @param ty (to y) la future position  en y de la Reine 
	 */
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
