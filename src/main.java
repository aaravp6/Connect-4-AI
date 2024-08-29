import java.awt.Color;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        gooey myGooey=new gooey();
        for (int i = 0; i<100000; i++) {
            System.out.println("a");
            if(myGooey.ready) {
                break;
            }
        }
        //Can decide if player 1 is a "human" that manually selects moves or "ai" which uses MCTS to generate moves
        player p1;
        player p2;
        p1 = new ai();
        p2 = new human();
        /*if(myGooey.p1Human) {
            p1=new human();
        } else {
            p1=new ai();
        }
        if(myGooey.p2Human) {
            p2=new human();
        } else {
            p2=new ai();
        }*/
        int score1=0;
        int score2=0;
        int[][] board = new int[6][7];
        game myGame = new game(p1, p2, board);
        Scanner s = new Scanner(System.in);

        //Alternates between P1 and P2 moves
        while (true) {

            System.out.println("\n\n\n----------------------\nPlayer 1's turn (X):");
            myGooey.text.setText("Red's Turn");
            myGooey.text.setBackground(Color.RED);
            myGame.printBoard(myGooey);
            myGame.takeTurn(1, myGooey);
            if (myGame.wonGame(1)) {
                myGooey.text.setText("RED WINS!");
                myGooey.text.setBackground(Color.RED);
                System.out.println("Player 1 won!!!");
                score1++;
                myGooey.score1Label.setText(Integer.toString(score1));
                break;
            } else if (myGame.drawGame()) {
                System.out.println("Draw game!");
                myGooey.text.setText("DRAW!");
                myGooey.text.setBackground(Color.WHITE);
                break;
            }

            System.out.println("\n\n\n----------------------\nPlayer 2's turn (O):");
            myGooey.text.setText("Blue's Turn");
            myGooey.text.setBackground(Color.BLUE);
            myGame.printBoard(myGooey);
            myGame.takeTurn(2, myGooey);
            if (myGame.wonGame(2)) {
                myGooey.text.setText("BLUE WINS!");
                myGooey.text.setBackground(Color.BLUE);
                System.out.println("Player 2 won!!!");
                score2++;
                myGooey.score2Label.setText(Integer.toString(score2));
                break;
            } else if (myGame.drawGame()) {
                System.out.println("Draw game!");
                myGooey.text.setText("DRAW!");
                myGooey.text.setBackground(Color.WHITE);
                break;
            }

        }
    }
}

