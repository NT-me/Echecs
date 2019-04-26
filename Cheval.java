
/**
	 * 
	 *class représentant le Cavalier d'un jeu d'échec
	 */
public class Cheval extends Piece
{
	/**
	 * constructeur de la class Cavalier initialise la couleur du Cavalier
	 *@param color couleur du Cavalier
	 */
	public Cheval(int color)
	{
		super();
		this.color = color;
	}
	
	/**
	 *affiche la piece Cavalier sur le plateau  
	 *
	 */
	@Override
	public void displayShell()
	{
		String s;
		s = (color == 1 ? "\u001B[38;5;10m" : "\u001B[38;5;15m");
		s += "C";
		System.out.print(s);
	}
	
	/**
	 * Retourne true si le mouvement est possible
	 * selon les regles du Cavalier
	 * (independamment des autre pieces)
	 * @param fx (from x) la position d'origine en x du Cavalier
	 * @param fy (from y) la position d'origine en y du Cavalier
	 * @param tx (to x) la future position  en x du Cavalier
	 * @param ty (to y) la future position  en y du Cavalier
	 */
	@Override
	public boolean isMovePossible(int fx,int fy,int tx,int ty)
	{
		if ((tx == fx + 1) && (ty == fy + 2 )){
			return true;
		}

		if ((tx == fx + 2) && (ty == fy + 1 )){
			return true;
		}

		if ((tx == fx + 1) && (ty == fy - 2 )){
			return true;
		}

		if ((tx == fx + 2) && (ty == fy - 1 )){
			return true;
		}

		if ((tx == fx - 2) && (ty == fy - 1 )){
			return true;
		}

		if ((tx == fx - 2) && (ty == fy + 1 )){
			return true;
		}

		if ((tx == fx - 1) && (ty == fy + 2 )){
			return true;
		}

		if ((tx == fx - 1) && (ty == fy - 2 )){
			return true;
		}

		return false;
	}
}
