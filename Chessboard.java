public class Chessboard
{
	private Box board[][];

	public Chessboard()
	{
		//ref sur tab 2D de Box
		Box[][] tmp = new Box[8][8];
		
		//allocation / creation de chaque Box
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				int c = ( (j+i) % 2 == 0 ? 1 : 0 );
				tmp[i][j] = new Box(c);
			}
		}
		for(int i = 0; i < 8; i++)
			tmp[i][1].changePiece(new Pion(0));

		for(int i = 0; i < 8; i++)
			tmp[i][6].changePiece(new Pion(1));

		tmp[2][0].changePiece(new Fou(0));
		tmp[5][0].changePiece(new Fou(0));
		tmp[2][7].changePiece(new Fou(1));
		tmp[5][7].changePiece(new Fou(1));

		tmp[0][0].changePiece(new Tour(0));
		tmp[7][0].changePiece(new Tour(0));
		tmp[0][7].changePiece(new Tour(1));
		tmp[7][7].changePiece(new Tour(1));

		tmp[1][0].changePiece(new Cheval(0));
		tmp[6][0].changePiece(new Cheval(0));
		tmp[1][7].changePiece(new Cheval(1));
		tmp[6][7].changePiece(new Cheval(1));

		tmp[3][0].changePiece(new Roi(0));
		tmp[4][0].changePiece(new Reine(0));
		tmp[3][7].changePiece(new Roi(1));
		tmp[4][7].changePiece(new Reine(1));

		this.board = tmp;
	}
	
	public void displayShell()
	{
		for(int i = 7; i >= 0; i--)
		{	
			System.out.printf("%d  ",i+1);

			for(int j = 0; j < 8; j++)
			{
				board[j][i].displayShell();
			}
			System.out.println();
		}
		System.out.print("\n\\  abcdefgh\n");
	}
	
	public boolean traceLigne(int deplacement[]) {
		if(deplacement[0] == deplacement[2]) {
			if(deplacement[1] < deplacement[3]) {
				for(int i = deplacement[1] + 1; i < deplacement[3]; i++) {
					if(board[deplacement[0]][i].getPiece() != null)
						return false;
				}
				return true;

			} else {
				for(int i = deplacement[3]; i < deplacement[1] - 1; i++) {
					if(board[deplacement[0]][i].getPiece() != null)
						return false;
				}
				return true;
			}
		}
		if(deplacement[1] == deplacement[3]) {
			if(deplacement[0] < deplacement[2]) {
				for(int i = deplacement[0] + 1; i < deplacement[2]; i++) {
					if(board[i][deplacement[1]].getPiece() != null)
						return false;
				}
				return true;

			} else {
				for(int i = deplacement[2];i < deplacement[0] - 1; i++) {
					if(board[i][deplacement[1]].getPiece() != null)
						return false;
				}
				return true;
			}
		}
		return false;

	}
	
	public boolean traceDiag(int deplacement[]) {
		//ne marche toujours pas !!!!
		int dx = deplacement[2] - deplacement[0];
		int dy = deplacement[3] - deplacement[1];
		if(dx == dy) {
			if(dx > 0) {
				for(int i = 1; i < dx; i++) {
					if(board[deplacement[0] + i][deplacement[1] + i].getPiece() != null)
						return false;
				}
				return true;
			} else {
				for(int i = 1; i < -dx; i++) {
					if(board[deplacement[0] - i][deplacement[1] - i].getPiece() != null)
						return false;
				}
				return true;
			}
		}
		if(dx == -dy) {
			if(dx > 0) {
				for(int i = 1; i < dx; i++) {
					if(board[deplacement[0] + i][deplacement[1] - i].getPiece() != null)
						return false;
				}
				return true;
			} else {
				for(int i = 1; i < -dx; i++) {
					if(board[deplacement[0] - i][deplacement[1] + i].getPiece() != null)
						return false;
				}
				return true;
			}
		}
		return false;
	}

	public int mouvement(int color, int deplacement[]){
		//errors
		if(this.board[deplacement[0]][deplacement[1]].getPiece() == null) {
			System.out.println("Pas de piece dans cette case");
			return -1;
		}
		if(this.board[deplacement[0]][deplacement[1]].getColor() != color) {
			System.out.println("Cette piece ne vous appartient pas");
			return -1;
		}
		if (this.board[deplacement[0]][deplacement[1]].getPiece().isMovePossible(deplacement[0], deplacement[1], deplacement[2], deplacement[3]) == false)
		{
			System.out.println("Mouvement incorrect pour cette piece");
			return -1;
		}
		
		//main move
		if (this.board[deplacement[2]][deplacement[3]].getPiece() == null) {
			Piece pi = this.board[deplacement[0]][deplacement[1]].getPiece();

			if(board[deplacement[0]][deplacement[1]].getTypePiece().equals("Tour")
				&& !traceLigne(deplacement) ) {
				System.out.println("La tour passe au dessus d'une autre piece");
				return -1;
			}

			this.board[deplacement[2]][deplacement[3]].changePiece(pi);
			this.board[deplacement[0]][deplacement[1]].changePiece(null);
		} else {
			if(this.board[deplacement[2]][deplacement[3]].getColor() == color)
			{
				System.out.println("Vous ne pouvez pas manger vos propres pièces");
				return -1;
			}
			//tuer la piece selon les regles 
			//ptet la stocker dans une liste de pieces mortes
		}
		return 0;
	}

	public int mouvementAi(int color, int deplacement[]){
		//errors
		if(this.board[deplacement[0]][deplacement[1]].getPiece() == null) 
			return -1;
		if(this.board[deplacement[0]][deplacement[1]].getColor() != color) 
			return -1;
		if (this.board[deplacement[0]][deplacement[1]].getPiece().isMovePossible(deplacement[0], deplacement[1], deplacement[2], deplacement[3]) == false)
			return -1;
		
		//main move
		if (this.board[deplacement[2]][deplacement[3]].getPiece() == null) {
			Piece pi = this.board[deplacement[0]][deplacement[1]].getPiece();

			if(board[deplacement[0]][deplacement[1]].getTypePiece().equals("Tour")
				&& !traceLigne(deplacement) ) 
				return -1;

			this.board[deplacement[2]][deplacement[3]].changePiece(pi);
			this.board[deplacement[0]][deplacement[1]].changePiece(null);
		} else {
			if(this.board[deplacement[2]][deplacement[3]].getColor() == color)
				return -1;
			//tuer la piece selon les regles 
			//ptet la stocker dans une liste de pieces mortes
		}
		return 0;
	}

	public void mutation() {
		for(int i = 0; i < 8; i++)
		{
			if(board[i][7].getTypePiece().equals("Pion"))
			{
				int c = board[i][7].getColor();
				board[i][7].changePiece(new Reine(c));
			}
		}
		for(int i = 0; i < 8; i++)
		{
			if(board[i][0].getTypePiece().equals("Pion"))
			{
				int c = board[i][7].getColor();
				board[i][0].changePiece(new Reine(c));
			}
		}

	}
}
