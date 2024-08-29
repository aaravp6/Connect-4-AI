import java.util.ArrayList;

public class ai extends player{

    public int makeMove(int[][] givenBoardState, ArrayList<Integer> validMoves, int playerNum, gooey myGooey) {

        node rootNode = new node(-1, null);//negative 1 represents null
        int oppPlayerNum = 3-playerNum;

        //Creating a copy of the board state so it does not override the passed value
        int[][] copyGivenBoardState = new int[6][7];
        for (int i=0; i<6; i++) {
            for (int j = 0; j<7; j++) {
                copyGivenBoardState[i][j] = givenBoardState[i][j];
            }
        }
        game tempGame = new game(new player(), new player(), copyGivenBoardState);

        //Blocks if the opponent has an instant win available
        for (int colSelection = 0; colSelection<7; colSelection++) {

            for (int i=0; i<6; i++) {
                for (int j = 0; j<7; j++) {
                    copyGivenBoardState[i][j] = givenBoardState[i][j];
                }
            }
            tempGame.setBoardState(copyGivenBoardState);

            tempGame.addPiece(oppPlayerNum, colSelection);

            if (tempGame.wonGame(oppPlayerNum)) {
                System.out.println("Opponent has immediate win at " + colSelection);
                return colSelection;//TODO: check for errors if it does not add the piece correctly
            }
        }

        //Monte Carlo Tree Simulation (MCTS) AI. Runs for 100000 iterations.
        for (int iter = 1; iter<100000; iter++) {
            node n = rootNode;

            //update the copy
            for (int i=0; i<6; i++) {
                for (int j = 0; j<7; j++) {
                    copyGivenBoardState[i][j] = givenBoardState[i][j];
                }
            }

            tempGame.setBoardState(copyGivenBoardState);

            // Selection Phase //System.out.println("selecting...");
            while (!n.isLeaf()) {//selecting leaf
                //tree policy child selection
                n = treePolicyChild(n, iter);
                //add move to board
                tempGame.addPiece(tempGame.determineNextPlayer(), n.getMove());
            }

            double result;
            if (!tempGame.terminalGameState()) {

                // Expansion Phase //System.out.println("expanding...");
                n.nodeExpansion(tempGame);//adds children
                n = treePolicyChild(n, iter);//TODO is this right????

                // Simulation Phase //System.out.println("simulating...");
                result = simulate(tempGame.getBoardState(), tempGame.determineNextPlayer(), playerNum);
            } else {
                //Code if tree went to a terminal node // System.out.println("Decision tree went to a terminal state");
                result = (tempGame.wonGame(playerNum))? 1.0: 0.0;//TODO see if this is the right plan of action
            }

            //Backpropagation Phase //System.out.println("backpropagation...");
            while (n.hasParent()) {
                n.updateNode(result);
                n = n.getParent();
            }

        }

        //Finds the optimal move for direct children
        System.out.println("getting optimal move...");
        ArrayList<node> children = rootNode.getChildren();
        double mostVisits = 0; node bestNode = rootNode.getChildren().get(0);
        boolean updateBest;
        for (node curNode: children) {
            System.out.println("Starting Node # " + curNode.getMove() + "(" + curNode.getWins() + ",  " + curNode.getVisits() + ")");
            if (curNode.getVisits()>mostVisits) {

                updateBest = true;

                //checks if move leads to an instant win for the opponent
                for (int i=0; i<6; i++) {
                    for (int j = 0; j<7; j++) {
                        copyGivenBoardState[i][j] = givenBoardState[i][j];
                    }
                }

                tempGame.setBoardState(copyGivenBoardState);
                tempGame.addPiece(playerNum, curNode.getMove());
                if (tempGame.generateValidMoves().contains(curNode.getMove())) {
                    tempGame.addPiece(oppPlayerNum, curNode.getMove());
                    if (tempGame.wonGame(oppPlayerNum)) {
                        updateBest = false;
                    }
                }

                //updates the best move as normal
                if (updateBest) {
                    mostVisits = curNode.getVisits();
                    bestNode = curNode;
                }
            }
        }
        return bestNode.getMove();
    }

    public node treePolicyChild(node n, int iter) {

        ArrayList<node> childrenNodes = n.getChildren();
        node bestNode = null; double bestScore = -1;
        for (node curNode: childrenNodes) {
            if (curNode.priorityFunction(iter) >= bestScore) {

                bestScore = curNode.priorityFunction(iter);
                bestNode = curNode;
            }
        }
        return bestNode;
    }

    //simulates 5 games every iteration
    public double simulate(int[][] givenBoardState, int startingPlayer, int playerNum) {

        int[][] copyGivenBoardState = new int[6][7];; //so it does not override the givenBoardState I pass
        for (int i=0; i<6; i++) {
            for (int j = 0; j<7; j++) {
                copyGivenBoardState[i][j] = givenBoardState[i][j];
            }
        }

        player p1 = new player();
        player p2 = new player();
        game simulateGame = new game(p1, p2, copyGivenBoardState);
        double w = 0; double v = 0;

        for (int gameIter = 0; gameIter<5; gameIter++) {
            v++;
            int curPlayer = startingPlayer;

            for (int i=0; i<6; i++) {
                for (int j = 0; j<7; j++) {
                    copyGivenBoardState[i][j] = givenBoardState[i][j];
                }
            }
            simulateGame.setBoardState(copyGivenBoardState);

            while (true) {
                if (curPlayer==1){
                    simulateGame.takeTurn(1,null);
                    if (simulateGame.wonGame(playerNum)) {
                        w+= (playerNum==1) ? 1:0; break;
                    }
                    if (simulateGame.drawGame()) {
                        break;
                    }
                } else if (curPlayer==2) {
                    simulateGame.takeTurn(2,null);
                    if (simulateGame.wonGame(2)) {
                        w+= (playerNum==2) ? 1:0; break;
                    }
                    if (simulateGame.drawGame()) {
                        break;
                    }
                }

                curPlayer = 3-curPlayer;

            }
        }
        return w/v;
    }
}
