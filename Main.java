public class Main
{
	public static void main(String[] args)
	{
		Chessboard cb = new Chessboard();
		cb.displayShell();

		Player p = new Player();
		cb.mouvement(1,p.getInput());
		cb.displayShell();

	}
}
