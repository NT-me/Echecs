	/**
	 * 
	 *class représentant le Roi d'un jeu d'échec
	 */
public class Roi extends Piece
{
	/**
	 * constructeur de la class Roi initialise la couleur du Roi
	 *@param color couleur du Roi
	 */
	public Roi(int color)
	{
		super();
		this.color = color;
	}
	
	/**
	 *affiche la piece Roi sur le plateau  
	 *
	 */
	@Override
	public void displayShell()
	{
		String s;
		s = (color == 1 ? "\u001B[38;5;10m" : "\u001B[38;5;15m");
		s += "K";
		System.out.print(s);
	}
	
	/**
	 * Retourne true si le mouvement est possible
	 * selon les regles du Roi 
	 * (independamment des autre pieces)
	 * @param fx (from x) la position d'origine en x du Roi
	 * @param fy (from y) la position d'origine en y du Roi 
	 * @param tx (to x) la future position  en x du Roi 
	 * @param ty (to y) la future position  en y du Roi 
	 */
	@Override
	public boolean isMovePossible(int fx,int fy,int tx,int ty)
	{
		int dx = tx - fx;
		int dy = ty - fy;
		
		if(dx == 0 && dy == 0) return false;
		return (dx <= 1) && (dx >= -1) && (dy <= 1) && (dy >= -1);
	}
}
