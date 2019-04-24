import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Chessboard cb = new Chessboard();
		cb.displayShell();
		Scanner sc = new Scanner(System.in);
		int color;

		System.out.println("Combien d'IA souhaitez vous ? 1 ou 2 ?");
		String in = sc.next();
		if(in.equals("1")){
			// Cas ou y'a un joueur
			System.out.println("Choisis une équipe (0 ou 1) / (noir ou vert)");

			in = sc.next();
			if(in.equals("noir") || in.equals("0"))
				color = 0;
			else if (in.equals("vert") || in.equals("1"))
				color = 1;
			else {
				System.out.println("Mauvais input, couleur par defaut = vert");
				color = 1;
			}

			Player p = new Player(cb,color);
			Ai a = new Ai(cb,1 - color);

			while(p.getAlive() == true && a.getAlive() == true)
			{

				if (color == 1){ // Cas ou le joueur est vert donc commence
					p.gameLoop();
					a.makeMove();
				}
				else {
					a.makeMove();
					p.gameLoop();
				}
				cb.mutation();
			}

			if(p.getAlive() == false)
			{
				System.out.println("You lost");
			} else if(a.getAlive() == false) {
				System.out.println("You won");
			}
		}
		else if (in.equals("2")){//Cas sans joueur
			Ai Kasparof = new Ai(cb, 1);
			Ai Mickey = new Ai(cb, 0);

			while (Kasparof.getAlive() == true && Mickey.getAlive() == true){
				Mickey.makeMove();
				try
				{
					Thread.sleep(1000);
				}
				catch(InterruptedException ex)
				{
					Thread.currentThread().interrupt();
				}
				Kasparof.makeMove();
				cb.mutation();
			}
			if (Kasparof.getAlive() == false){
				System.out.println("Les noirs ont gagné");
			}
			else if (Mickey.getAlive() == false){
				System.out.println("Les blancs ont gagné");
			}
		}

		else {
			System.out.println("Mauvais input, fin du programme");
		}

	}
}
