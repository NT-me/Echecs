public abstract class Piece {
	protected int color;

	public abstract void displayShell();

	/**
	 *Valide le mouvement en fonction des regles
	 *
	 *
	 */
	public abstract boolean isMovePossible(int fx, int fy, int tx, int ty);

	//public abstract void display();

	public int getColor(){
		return this.color;
	}
}
