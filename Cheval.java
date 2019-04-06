public class Cheval extends Piece

	//PETIT PONEY TU ES TOUT PETIT ET TOUT GRIS PETIT PONEY
{
	public Cheval(int color)
	{
		super();
		this.color = color;
	}

	@Override
	public void displayShell()
	{
		String s;
		s = (color == 0 ? "\u001B[38;5;10m" : "\u001B[38;5;15m");
		s += "C";
		System.out.print(s);
	}
	
	@Override
	public boolean isMovePossible(int fx,int fy,int tx,int ty)
	{
		if ((tx == fx + 1) && (ty == fy + 2 )){
			return true;
		}

		if ((tx == fx + 2) && (ty == fy + 1 )){
			return true;
		}

		if ((tx == fx + 1) && (ty == fy - 2 )){
			return true;
		}

		if ((tx == fx + 2) && (ty == fy - 1 )){
			return true;
		}

		if ((tx == fx - 2) && (ty == fy - 1 )){
			return true;
		}

		if ((tx == fx - 2) && (ty == fy + 1 )){
			return true;
		}

		if ((tx == fx - 1) && (ty == fy + 2 )){
			return true;
		}

		if ((tx == fx - 1) && (ty == fy - 2 )){
			return true;
		}

		else {
			return false;
		}
	}
}
