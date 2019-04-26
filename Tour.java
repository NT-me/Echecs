	/**
	 * 
	 *class représentant Tour d'un jeu d'échec
	 */
public class Tour extends Piece
{
	/**
	 * constructeur de la class Tour initialise la couleur de la Tour
	 *@param color couleur de la Tour
	 */
	public Tour(int color)
	{
		super();
		this.color = color;
	}
	
	/**
	 *affiche la piece Tour sur le plateau  
	 *
	 */
	@Override
	public void displayShell()
	{
		String s;
		s = (color == 1 ? "\u001B[38;5;10m" : "\u001B[38;5;15m");
		s += "T";
		System.out.print(s);
	}
	
	/**
	 * Retourne true si le mouvement est possible
	 * selon les regles de la Tour
	 * (independamment des autre pieces)
	 * @param fx (from x) la position d'origine en x de la Tour 
	 * @param fy (from y) la position d'origine en y de la Tour
	 * @param tx (to x) la future position  en x de la Tour 
	 * @param ty (to y) la future position  en y de la Tour
	 */
	@Override
	public boolean isMovePossible(int fx,int fy,int tx,int ty) {
		return (tx == fx) ^ (ty == fy);
	}
}
