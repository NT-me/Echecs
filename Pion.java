/**
	 * 
	 *class représentant le Pion d'un jeu d'échec
	 */
public class Pion extends Piece
{
	/**
	 * constructeur de la class Pion initialise la couleur du Pion
	 *@param color couleur du pion
	 */
	public Pion(int color)
	{
		super();
		this.color = color;
	}
	
	/**
	 *affiche la piece "Pion" sur le plateau  
	 *
	 */
	@Override
	public void displayShell()
	{
		String s;
		s = (color == 1 ? "\u001B[38;5;10m" : "\u001B[38;5;15m");
		s += "P";
		System.out.print(s);
	}
	
	/**
	 * Retourne true si le mouvement est possible
	 * selon les regles de du Pion
	 * (independamment des autre pieces)
	 * @param fx (from x) la position d'origine en x du Pion
	 * @param fy (from y) la position d'origine en y du Pion
	 * @param tx (to x) la future position  en x du Pion
	 * @param ty (to y) la future position  en y du Pion
	 */
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
