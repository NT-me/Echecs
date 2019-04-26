# Projet de JAVA  
## Manuel utilisateur [fr]

Pour lancer le programme l'utilisateur peut soit utiliser la commande `make` s'il souhaite recompiler le projet, soit `java -jar chess.jar` pour le lancer plus classiquement.  

Ensuite l'utilisateur devra choisir s'il veut jouer contre une IA ou deux IA.  

Ensuite il va devoir choisir sa couleur, s'il choisit 0 il sera blanc (en bas et il commencera), s'il choisit 1 il sera vert, assimilé à noir (en haut il est le second à jouer).  

Pour jouer le joueur fournira un string de forme fxfy txty (avec fx une lettre en a et h, fy un chiffre en 1 et 8 puis la destination sous le même format).  

**Exemple** :

```
a2a3 (Si le joueur est blanc)
```

Une fois que l'un des joueurs a gagné le jeu se ferme automatiquement.

## User manuel [en]

For run the soft you need to use the command `make` if you want to compile again or just `java -jar chess.jar` for run this more classicaly.

He gonna ask you (in french) how many AIs you want, if you answer "one" he gonna launche the game Ai (We name her Mickey) against you. If you answer "two" two AIs (Mickey and Kasparof) gonne play together without you.

For play you need to enter things with the format :
`#x#x` with '#' letter a->h and 'x' 1->8. The first couple is origin pos, and the second is destination.


**Example** :

```
a2a3 (If the player is white)
```

## Manuel technique [only in french]

### Les pièces 

La gestion des pièces se fait par le biais de différente classes :

- Piece.java
- Pion.java
- Tour.java
- Fou.java
- Cheval.java
- Reine.java
- Roi.java

La classe `Piece` est une classe abstraite dont chacune des autres pièces hérite.  

La partie importante des classes de chacune des pièces est la méthode `isMoveIsPossible` qui est un booléen et qui permet de vérifier que les coordonnées donnée en entrée par l'utilisateur sont valide et respecte les règles des échecs.

Les pièces ont directement dans leur constructeur un int ( 0 ou 1 ) qui permet de connaître leur couleur et ceci dès leur initialisation.  

Dans la classe mère (Piece.java) il y a trois assesseurs:  

- `getColor` : Qui permet de récupérer le int de la couleur.  
- `getPiece` : Qui permet de tester la pièce et retourne une `Piece`.  
- `getTypePiece` : Qui permet d'avoir le nom de la classe, elle retourne un String.  

### Le plateau

Les classes en rapport avec le plateau de jeu sont dans :  

- Box.java
- Chessboard.java

La classe Box permet de créer les cases du plateau, elles stockent soit null, soit une pièce.  
La plupart des méthodes dans cette classe sont liée à l'affichage néanmoins la fonction `changePiece` permet, comme son nom l'indique de changer la pièce dans la box concernée.  
Cette méthode est utilisée beaucoup dans l'initialisation et dans les méthodes de mouvement.

Dans la classe Chessboard on peut observer de nombreuses méthodes.
Les méthodes `traceDiag` et `traceLigne` permettent de vérifier s'il y'a des pièces dans des cases entre deux positions. Ce sont des méthodes de vérification tout comme la méthode `movePossible` qui permet de vérifier si un mouvement est possible en fonction de son environnement (pièces alliées sur la route par exemple) et c'est là sa différence majeur avec `isMoveIsPossible`.  

La méthode `mouvement` bouge les pièces en vérifiant que c'est possible (en utilisant les fonctions `movePossible` & `isMoveIsPossible`), elle prend un booléen "AI" pour ne pas afficher les nombreux messages d'erreur lorsque l'IA test ses coups.

La fonction `mutation` test la présence de pion de l'autre couleur sur la dernière ligne de chaque camps (8e ligne pour les verts et 1ere ligne pour les blancs) et remplace ce dernier par une reine.

La méthode `setUndo` va juste empiler les Strings entré par l'utilisateur.

La méthode `doUndo` elle va dépiler, inverser le String pour bouger la pièce à sa dernière position. Un flag a été intégré pour quand une pièce en mange une autre et ainsi permettre à la fonction de remettre ladite pièce mangée en jeu.

La méthode `findKing` va trouver le roi, pour cela elle va parcourir l'ensemble des cases du plateau en cherchant le roi de la couleur spécifié. Elle renvoie ses positions dans un tableau de int.

La méthode `areYouInEchec` va vérifier si le roi d'une couleur est mis en échec. Pour cela il va vérifier que le roi n'est pas attaquable par une des pièces adverses.

La méthode `areYouInMat` va vérifier si le roi de la couleur spécifiée peut encore bougé en situation d'échec, elle ne prend pas en compte la situation où une pièce le protège en se déplaçant.

La méthode `areYouInEchecEtMat` va lancer la précédente fonction en bougeant chacune des pièces de la couleur pour voir si l'une d'elle peut intercepter le régicide.

### Les joueurs (Humain et IA)

Les classes en rapport avec les joueurs et les IA sont :

- Player.java
- Ai.java

Dans la class player il y a les fonctions chargées de récupérer l'Input user et transforme les lettres de ce dernier en chiffre manipulable par le reste du programme.
Et il y'a la méthode `GameLoop` qui va déterminer précisément la routine de jeu pour un joueur humain. 

Dans la class Ai il y'a les méthodes chargées de choisir aléatoirement une pièce ayant un coup valide et de l'effectuer.
