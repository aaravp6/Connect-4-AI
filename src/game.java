import java.util.ArrayList;
import java.awt.Color;

public class game {
    private int[][] boardState;
    private player player1;
    private player player2;
    public game(player p1, player p2, int[][] bs) {
        //0 = empty; 1 = player 1; 2 = player 2
        //(0, 0) is at the top left of the board
        boardState = bs;

        player1 = p1;
        player2 = p2;

    }

    public int determineNextPlayer() {
        int count1 = 0; int count2 = 0;
        for (int[] row: boardState) {
            for (int piece: row) {
                if (piece==1) {
                    count1++;
                } else if (piece==2) {
                    count2++;
                }
            }
        }
        if (count1==count2) {
            return 1;
        } else {
            return 2;
        }
    }

    public int getPos(int row, int col) {
        int val;
        try {
            val = boardState[row][col];
            return val;
        } catch (Exception e) {
            return -1;
        }
    }

    public void setPos(int newPiece, int row, int col) {
        boardState[row][col] = newPiece;
    }

    public void setBoardState(int[][] newBoardState) {
        boardState = newBoardState;
    }

    public int[][] getBoardState() {
        return boardState;
    }

    public void addPiece(int newPiece, int col) {
        for (int i=0; i<6; i++) {
            if (getPos(5-i, col) == 0) {
                setPos(newPiece, 5-i, col);
                break;
            }
        }
    }

    public ArrayList<Integer> generateValidMoves() {
        ArrayList<Integer> validMoves = new ArrayList<Integer>();
        for (int c = 0; c<7; c++) {
            for (int r = 0; r<6; r++){
                if (getPos(r, c) == 0) {
                    validMoves.add(c);
                    break;
                }
            }
        }
        return validMoves;
    }

    public void takeTurn(int playerNum, gooey myGooey) {
        int selectedCol = -1;
        if (playerNum==1) {
            selectedCol = player1.makeMove(boardState, generateValidMoves(), playerNum, myGooey);;
        } else if (playerNum==2) {
            selectedCol = player2.makeMove(boardState, generateValidMoves(), playerNum, myGooey);;
        } else
            System.out.println("Player does not exist");
        addPiece(playerNum, selectedCol);
    }

    public void printBoard(gooey myGooey) {
        String[] mapToChar = {".", "X", "O"};

        System.out.println("\nCurrent Board:");
        for (int i=0;i<boardState.length;i++) {
            for (int j=0;j<boardState[i].length;j++) {
                System.out.print("|" + mapToChar[boardState[i][j]]);
                if(boardState[i][j]==0) {
                    myGooey.grid[i][j].setBackground(Color.WHITE);
                } else if(boardState[i][j]==1) {
                    myGooey.grid[i][j].setBackground(Color.RED);
                } else {
                    myGooey.grid[i][j].setBackground(Color.BLUE);
                }
            }
            System.out.println("|");
        }
    }

    public boolean terminalGameState() {
        return (drawGame() || wonGame(1) || wonGame(2));
    }

    public boolean drawGame() {
        return generateValidMoves().size() == 0;
    }

    public boolean wonGame(int playerNum) {
        //check horizontal
        for (int[] row: boardState) {
            for (int i = 0; i<4; i++) {
                if (row[i] == playerNum && (row[i] == row[i+1] && row[i+2]==row[i+3] && row[i]==row[i+3])) {
                    return true;
                }
            }
        }

        //check vertical
        for (int c = 0; c<7; c++) {
            for (int r = 0; r<3; r++) {
                if (getPos(r, c)== playerNum && (getPos(r, c)==getPos(r+1, c) && getPos(r+2, c)==getPos(r+3, c) && getPos(r, c)==getPos(r+3, c))) {
                    return true;
                }
            }
        }

        //check '\' diagonal
        for (int c = 0; c<4; c++) {
            for (int r = 0; r < 3; r++) {
                if (getPos(r, c)== playerNum && (getPos(r, c) == getPos(r+1, c+1) && getPos(r+2, c+2) == getPos(r+3, c+3) && getPos(r, c) == getPos(r+3, c+3))) {
                    return true;
                }
            }
        }

        //check '/' diagonal
        for (int c = 0; c<4; c++) {
            for (int r = 5; r >2; r--) {
                if (getPos(r, c)== playerNum && (getPos(r, c) == getPos(r-1, c+1) && getPos(r-2, c+2) == getPos(r-3, c+3) && getPos(r, c) == getPos(r-3, c+3))) {
                    return true;
                }
            }
        }

        return false;

    }

}
