
/**
	 * 
	 *class représentant le Fou d'un jeu d'échec
	 */
public class Fou extends Piece
{
	/**
	 * constructeur de la class Fou initialise la couleur du Fou
	 *@param color couleur du Fou
	 */
	public Fou(int color)
	{
		super();
		this.color = color;
	}
	
	/**
	 *affiche la piece Fou sur le plateau  
	 *
	 */
	@Override
	public void displayShell()
	{
		String s;
		s = (color == 1 ? "\u001B[38;5;10m" : "\u001B[38;5;15m");
		s += "F";
		System.out.print(s);
	}
	
	/**
	 * Retourne true si le mouvement est possible
	 * selon les regles du Fou
	 * (independamment des autre pieces)
	 * @param fx (from x) la position d'origine en x du Fou
	 * @param fy (from y) la position d'origine en y du Fou
	 * @param tx (to x) la future position  en x du Fou
	 * @param ty (to y) la future position  en y du Fou
	 */
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
