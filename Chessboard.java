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

			this.board[deplacement[2]][deplacement[3]].changePiece(pi);
			this.board[deplacement[0]][deplacement[1]].changePiece(null);
		} else {
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
