
/**
	 * 
	 *class abtsraite représentant la piece d'un jeu d'échec
	 */
public abstract class Piece {
	
	protected int color;
	/**
	 * Pour afficher la piece sur le terminal
	 */
	public abstract void displayShell();

	/**
	 * Retourne true si le mouvement est possible
	 * selon les regles de la piece
	 * (independamment des autre pieces)
	 * @param fx (from x) la position d'origine en x d'une piece
	 * @param fy (from y) la position d'origine en y d'une piece
	 * @param tx (to x) la future position  en x d'une piece
	 * @param ty (to y) la future position  en y d'une piece
	 */
	public abstract boolean isMovePossible(int fx, int fy, int tx, int ty);

	
	
	/**
	 * 
	 *renvoie la couleur de la piece 
	 */
	public int getColor(){
		return this.color;
	}
}
