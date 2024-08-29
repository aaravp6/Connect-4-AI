import java.util.ArrayList;
import java.util.Scanner;

public class human extends player{
    public void human() {
        int a = 0;
    }

    // Asks for user input to decide which column to make a move in
    public int makeMove(int[][] givenBoardState, ArrayList<Integer> validMoves, int playerNum, gooey myGooey) {
        myGooey.b=0;
        myGooey.button1.setVisible(true);
        myGooey.button2.setVisible(true);
        myGooey.button3.setVisible(true);
        myGooey.button4.setVisible(true);
        myGooey.button5.setVisible(true);
        myGooey.button6.setVisible(true);
        myGooey.button7.setVisible(true);
        while(true) {
            System.out.println("a");
            if(myGooey.b!=0) {
                break;
            }
        }
        System.out.println(myGooey.b);
        return myGooey.b-1;
        
        /*Scanner s = new Scanner(System.in);
        int colInp;
        
        
        while (true) {
            try {
                System.out.print("Please enter which col (1-7) you want to enter your piece into: ");
                colInp = Integer.parseInt(s.nextLine());
                if (1>colInp || colInp>7 || !validMoves.contains(colInp-1)) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println("Bad Input. Please try again.");
            }
        }
        

        return colInp-1;
        */
    }
}
