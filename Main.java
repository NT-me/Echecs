public class Main
{
	public static void main(String[] args)
	{
		Chessboard cb = new Chessboard();
		Player p = new Player();
		//Pick a team
		System.out.println("Vous etes l'équipe blanche");
		cb.displayShell();
		
			//Un tour de jeu Humain
			int input[] = null;
			int rv = -1;
			do{
				do{
					input = p.getInput();
				} while (input == null);

				rv = cb.mouvement(0,input);
			} while (rv == -1);

			//affichage du tour
			cb.displayShell();

	}
}
