import java.util.ArrayList;
import java.util.Stack;
import java.util.Scanner;

public class Player {

    private String input;
    private ArrayList<Integer> undo;

    public Player(){
        this.input = "";
    }

    private int[] setInput(String input){
        String temp = input;
        int res[]={-1,-1,-1,-1};
        int fy, ty;
        String fx, tx;

        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir undo, ou position (ToX ToY/FromX FromY :");
        temp = sc.nextLine();

        String verifUn = Character.toString(temp.charAt(0));
        String verifDeux = Character.toString(temp.charAt(1));
        String verifTrois = Character.toString(temp.charAt(2));
        String verifQuatre = Character.toString(temp.charAt(3));


        // Création des listes
        ArrayList<String> alphaB = new ArrayList<String>(8);
        ArrayList<String> chiffre = new ArrayList<String>(8);

        alphaB.add("a");alphaB.add("b");alphaB.add("c");alphaB.add("d");alphaB.add("e");alphaB.add("f");
        alphaB.add("g");alphaB.add("h");

        for (int i = 0; i<8; ++i){
            String j = Integer.toString(i);
            chiffre.add(j);
        }

        if (temp.equals("undo")){
            System.out.println("Vous avez fait undo");
        }

        if (temp.contains(verifUn) && temp.contains(verifDeux) && temp.contains(verifTrois) &&  temp.contains(verifQuatre)){
            fx=temp.substring(0, 1);
            fy=Integer.parseInt(temp.substring(1, 2));

            tx=temp.substring(2, 3);
            ty=Integer.parseInt(temp.substring(3, 4));


            res[0]=alphaB.indexOf(fx);
            res[1]=fy;
            res[2]=alphaB.indexOf(tx);
            res[3]=ty;

        }
        else{
            //error
        }

        return res;

    }

    public int[] getInput(){

        return setInput(input);
    }

}
