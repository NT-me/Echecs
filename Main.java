import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Chessboard cb = new Chessboard();
		cb.displayShell();
	
		System.out.println("Choisis une équipe (0 ou 1) / (blanc ou vert)");
		Scanner sc = new Scanner(System.in);
		int color;
		String in = sc.next();
		if(in.equals("blanc") || in.equals("0"))
			color = 0;
		else if (in.equals("vert") || in.equals("1"))
			color = 1;
		else {
			System.out.println("Mauvais input, couleur par defaut = blanc");
			color = 0;
		}

		Player p = new Player(cb,color);
		Ai a = new Ai(cb,1 - color);

		while(p.getAlive() == true && a.getAlive() == true)
		{
			p.gameLoop();
			a.makeMove();
		}

		if(p.getAlive() == false)
		{
			System.out.println("You lost");
		} else if(a.getAlive() == false) {
			System.out.println("You won");
		}
	}
}
