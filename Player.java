import java.util.*;

public class Player {
	
	//ref sur le plateau
	private Chessboard cb;
	private int color;
	private boolean alive;



	
	//Constructeurs 
    public Player(Chessboard cb){
		this.cb = cb;
		this.color = 0;
		this.alive = true;

    }

	public Player(Chessboard cb,int color){
		this.cb = cb;
		this.color = color;
		this.alive = true;
	}
	
	//autres Methodes
    private int[] setInput(String input){
        
		int res[]={-1,-1,-1,-1};
        int fy, ty;
        String fx, tx;
        
		if(input.length() != 4) 
		{
			System.out.println("Input trop long");
			return null;
		}

		if (input.equals("undo")){
            res[0]=999;
			return res;
        }
		else
		{	
			//conversions (- 1 a la fin car coordonées indexées 1
			//donne la position dans l'alphapet de la lettre (grace a la table ASCII 'a' = 97)
			res[0] = ((int)Character.toLowerCase(input.charAt(0)) - 96) - 1;
			//equivalent de atoi();
			res[1] = (char)(input.charAt(1) - '0') - 1;
			res[2] = ((int)Character.toLowerCase(input.charAt(2)) - 96) - 1;
			res[3] = (char)(input.charAt(3) - '0') - 1;
		}
		
		for(int i = 0; i < 4; i++)
		{
			if(res[i] > 7 || res[i] < 0)
			{
				System.out.println("Mauvais format de coordonées");
				return null;
			}
		}

        return res;

    }

    public int[] getInput(){

		System.out.println("Veuillez choisir undo ou position \n (sous le format #x#x ou # est une lettre entre a et h et x un nombre entre 1 et 8)");
		Scanner sc = new Scanner(System.in);
		
		String input;
		try{
			input = sc.next();
			System.out.printf("#%s#\n",input);
		} 
		catch (NoSuchElementException e)
		{
			System.out.println("Vous n'avez rien tapez, Veuillez reessayer");
			return null;
		}
        return setInput(input);
    }
	
	public boolean getAlive()
	{ return this.alive; }
	
	private void testAlive()
	{
		this.alive = false;
		Box[][] board = cb.getBoard();
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				if(board[j][i].getPiece() != null) {
					if(board[j][i].getTypePiece().equals("Roi") 
						&& board[j][i].getColor() == this.color)
						this.alive = true;

				}
			}
		}
	}

	public int gameLoop()
	{
		this.testAlive();
		if(this.alive == false) return 0;
		int input[] = null;
		int rv = -1;
		if( this.cb.areYouInEchec( this.cb.findKing(this.color), this.color) == true)
		{
            System.out.printf("%s vous etes en échec, vous devriez bouger !\n",(this.color == 0 ? "Blanc" : "Vert"));
		}
		if( this.cb.areYouInEchecEtMat(this.color) == true )
		{
            System.out.printf("%s vous etes en échec et mat\n",(this.color == 0 ? "Blanc" : "Vert"));
			this.alive = false;
			return 0;
		}
		do{
			do{
				input = this.getInput();
			} while (input == null);

			if (input[0]==999){
					System.out.println("Vous avez fait undo");
					cb.doUndo();
					cb.doUndo();
					return 1;
			}
			rv = cb.mouvement(this.color,input,false);

		} while (rv == -1);

		//affichage du tour
		cb.displayShell();
		
		this.testAlive();

		return 0;
	}

}
