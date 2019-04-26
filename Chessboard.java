import java.util.EmptyStackException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class Chessboard
{
	private Box board[][];

	private Stack<Piece> dead;

	private Stack<int[]> pileUndo;

	public Chessboard()
	{
		this.dead = new Stack<Piece>();

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
		this.pileUndo = new Stack<>();
	}

	public Box[][] getBoard()
	{ return this.board; }
	
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
		System.out.print("\n\\  abcdefgh\n\nPieces Mortes :\n");

		for(Piece p : this.dead)
		{
			p.displayShell();
			System.out.print("\u001B[0m");
		}
		System.out.println();
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
				for(int i = deplacement[3] + 1; i < deplacement[1]; i++) {
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
				for(int i = deplacement[2] + 1;i < deplacement[0]; i++) {
					if(board[i][deplacement[1]].getPiece() != null)
						return false;
				}
				return true;
			}
		}
		return false;

	}
	
	public boolean traceDiag(int deplacement[]) {
		//verifie si il y a une case dans la diagonale d'un point a un autre
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
		else if(dx == -dy)
		{
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
		else return false;
	}

	public boolean movePossible(int color, int deplacement[],boolean ai){
		//errors
		if(this.board[deplacement[0]][deplacement[1]].getPiece() == null) {
			if(!ai) System.out.println("Pas de piece dans cette case");
			return false;
		}
		if(this.board[deplacement[0]][deplacement[1]].getColor() != color) {
			if(!ai) System.out.println("Cette piece ne vous appartient pas");
			return false;
		}
		if (this.board[deplacement[0]][deplacement[1]].getPiece().isMovePossible(deplacement[0], deplacement[1], deplacement[2], deplacement[3]) == false)
		{
			if(!ai) System.out.println("Mouvement incorrect pour cette piece");
			return false;
		}

		//ne pas manger ses cooequipiers
		if(this.board[deplacement[2]][deplacement[3]].getTypePiece() != null){
			if(this.board[deplacement[2]][deplacement[3]].getColor() == color)
			{
				if(!ai) System.out.println("Vous ne pouvez pas manger vos propres pièces");
				return false;
			}
		}

		//cas special de la tour
		if(this.board[deplacement[0]][deplacement[1]].getTypePiece().equals("Tour")
			&& !traceLigne(deplacement) ) {
			if(!ai) System.out.println("La tour passe au dessus d'une autre piece");
			return false;
		}
		
		//cas special du fou
		if(this.board[deplacement[0]][deplacement[1]].getTypePiece().equals("Fou")
			&& !traceDiag(deplacement) ) {
			if(!ai) System.out.println("Le Fou passe au dessus d'une autre piece");
			return false;
		}

		//cas spécial du pion
		//a retravailler
		if(this.board[deplacement[0]][deplacement[1]].getTypePiece().equals("Pion")){
			
			int dx = deplacement[2] - deplacement[0];
			int dy = deplacement[3] - deplacement[1];

			if ( (deplacement[0] == deplacement[2]) && board[deplacement[2]][deplacement[3]].getTypePiece() != null){
				if (!ai) System.out.print("Un pion ne peut pas prendre devant lui !\n");
				return false;
			}
			
			if( dx == dy ^ dx == -dy)
			{
				//alors mouvement en diagonale
				if(dy == 1 && color == 0)
				{
					if(this.board[deplacement[2]][deplacement[3]].getTypePiece() == null)
						return false;
					return true;
				}
				if(dy == -1 && color == 1)
				{
					if(this.board[deplacement[2]][deplacement[3]].getTypePiece() == null)
						return false;
					return true;
				}
				return false;
			}
		} //fin if pion

		//cas special de la reine
		if(this.board[deplacement[0]][deplacement[1]].getTypePiece().equals("Reine") ) {
			boolean test;
			//test si le mouvement est en ligne ou en diagonale
			if((deplacement[0] == deplacement[2]) ^ (deplacement[1] == deplacement[3])) {
				test = traceLigne(deplacement);
			} else {
				test = traceDiag(deplacement);
			}

			if(!test) { 
				if(!ai) System.out.println("La Reine passe au dessus d'une autre piece");
				return false;
			}
		} //fin if reine
		


		return true;
	}
	
	//boolean ai = true si c'est l'ia qui joue pour eviter ses messages d'erreurs
	public int mouvement(int color, int deplacement[],boolean ai){
		
		if(this.movePossible(color,deplacement,ai) == false)
			return -1;

		//main move
		Piece pi = this.board[deplacement[0]][deplacement[1]].getPiece();
		
		setUndo(deplacement);

		if (this.board[deplacement[2]][deplacement[3]].getPiece() == null) {

			this.board[deplacement[2]][deplacement[3]].changePiece(pi);
			this.board[deplacement[0]][deplacement[1]].changePiece(null);
		
		} else {
			//Si il y a une piece dans la case d'arrivée
			//si c'est une piece adverse
			//on la stocke dans une liste de pieces mortes 
			this.dead.push(this.board[deplacement[2]][deplacement[3]].getPiece());
			int[] d = new int[4];
			d[0] = -1;
			d[1] = -1;
			d[2] = 666;
			d[3] = -1;
			setUndo(d);
			this.board[deplacement[2]][deplacement[3]].changePiece(pi);
			this.board[deplacement[0]][deplacement[1]].changePiece(null);

		}

		return 0;
	}


	public void mutation() {
		for(int i = 0; i < 8; i++)
		{
			try{
			if(board[i][7].getTypePiece().equals("Pion"))
			{
				int c = board[i][7].getColor();
				board[i][7].changePiece(new Reine(c));
			}
		}catch (NullPointerException e){}
		}
		for(int i = 0; i < 8; i++)
		{
			try{
				if(board[i][0].getTypePiece().equals("Pion"))
				{
					int c = board[i][7].getColor();
					board[i][0].changePiece(new Reine(c));
				}
			}catch (NullPointerException e){}
		}

	}

	public void setUndo(int a[]){
		this.pileUndo.push(a);
	}

	public void doUndo(){
		int dep[]= {-1,-1,-1,-1};
		int tmp[]= {-1,-1,-1,-1};

		try {
			dep = this.pileUndo.pop();
		}catch (EmptyStackException e ){
			System.out.println("Vous ne pouvez pas retourner plus loin");
			return;
		}
		
		if(dep[0] != -1) {
			tmp[0] = dep[2];
			tmp[1] = dep[3];
			tmp[2] = dep[0];
			tmp[3] = dep[1];
		
			Piece p = this.board[tmp[0]][tmp[1]].getPiece();
		
			System.out.printf("-> %d%d%d%d\n", tmp[0], tmp[1], tmp[2], tmp[3]);
		
			this.board[tmp[0]][tmp[1]].changePiece(null);
			this.board[tmp[2]][tmp[3]].changePiece(p);
		}
		else
		{
			dep = this.pileUndo.pop();
			
			tmp[0] = dep[2];
			tmp[1] = dep[3];
			tmp[2] = dep[0];
			tmp[3] = dep[1];
		
			Piece p = this.board[tmp[0]][tmp[1]].getPiece();
		
			System.out.printf("-> %d%d%d%d\n", tmp[0], tmp[1], tmp[2], tmp[3]);
		
			this.board[tmp[0]][tmp[1]].changePiece(null);
			this.board[tmp[2]][tmp[3]].changePiece(p);


			try{
				p = this.dead.pop();
			} catch (EmptyStackException e ){
				System.out.println("Vous ne pouvez pas retourner plus loin");
				return;
			}
			
			this.board[tmp[0]][tmp[1]].changePiece(p);

		}
	}
	
	public int[] findKing(int c){
        //Trouve le roi
		Piece king;
        int[] tmp = new int[2];
        int i = 0, j = 0, flag = 0;

        for (i = 0; i < 8 && flag == 0; i = i+1){
            for (j = 0; j < 8 && flag == 0; j = j + 1){
                if (this.board[i][j].getTypePiece() == "Roi" && this.board[i][j].getColor() == c)
				{
                    king = this.board[i][j].getPiece();
                    flag = 1;
                    tmp[0] = i; tmp[1] = j;
                }
            }
        }
		
		return tmp;
	}

	public boolean areYouInEchec(int[] roi,int c){
		
    	int[] tmp = new int[4];
		tmp[2] = roi[0];
		tmp[3] = roi[1];
	
        for (int h = 0; h<8; ++h){
            for (int g = 0; g<8; ++g){
                if (this.board[g][h].getPiece() != null && this.board[g][h].getColor() != c){
                    tmp[0] = g; tmp[1] = h;
                    if (this.movePossible(1 - c, tmp, true) == true){
                        return true;
                    }
                }
            }
        }
        return false;
    }

	public boolean areYouInMat(int c) {
		
		int[] k = this.findKing(c);

		int Somme = 0;

		for(int i = -1; i < 2; i++)
		{
			for(int j = -1; j < 2; j++)
			{
				int x = k[0] + j;
				int y = k[1] + i;
				if( ((x < 0)||(x > 7)||(y < 0)||(y > 7)) == false )
				{
					//si dans le board
					if(this.board[x][y].getPiece() == null)
					{
						int[] tmpKing = new int[2];
						tmpKing[0] = x;
						tmpKing[1] = y;
						if(areYouInEchec(tmpKing,c) == true)
						{
							Somme++;
						}
					}
					else
					{
						Somme++;
					}
				}
				else
				{
					Somme++;
				}
			}
		}

		if(Somme == 9) return true && this.areYouInEchec(k,c);
		
		return false;
	}
	
}

