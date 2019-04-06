public abstract class Piece {
	protected int color;

	public abstract void displayShell();

	/*
	 * Retourne true si le mouvement est possible
	 * selon les regles de la piece
	 * (independamment des autre pieces)
	 */
	public abstract boolean isMovePossible(int fx, int fy, int tx, int ty);

	//public abstract void display();

	public int getColor(){
		return this.color;
	}
}