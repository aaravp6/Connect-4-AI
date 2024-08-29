import java.util.ArrayList;
import java.util.Random;

public class player {
    //Generates random moves // Mainly just a filler
    public int makeMove(int[][] givenBoardState, ArrayList<Integer> validMoves, int playerNum, gooey myGooey) {
        Random r = new Random();
        return validMoves.get(r.nextInt(validMoves.size()));
    }
}
