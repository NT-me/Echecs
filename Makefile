run: Main
	java -jar chess.jar Main

Main: Chessboard.java Box.java Piece.java Main.java Pion.java Fou.java Tour.java Cheval.java Roi.java Reine.java Player.java
	javac $^ 
	jar cfe chess.jar $@ *.class

edit: 
	xfce4-terminal -x vim -p Piece.java Pion.java Fou.java Tour.java Cheval.java Roi.java Reine.java  &
	vim -p Makefile Player.java Ai.java Main.java Chessboard.java Box.java

clean:
	rm -f *.class
	rm -f chess.jar
	
	
