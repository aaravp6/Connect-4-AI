import java.util.ArrayList;
import java.util.Objects;

public class node {

    private int move;
    private node parent;
    private ArrayList<node> children;
    private double wins; private double visits;

    public node(int m, node p) {
        move = m;
        parent = p;
        children = new ArrayList<node>();

        wins = 0; visits = 0;
    }

    public node getParent() {
        return parent;
    }

    public double getVisits() {
        return visits;
    }

    public double getWins() {
        return wins;
    }

    public int getMove() {
        return move;
    }

    public ArrayList<node> getChildren() {
        return children;
    }

    //finds and creates node children (Expansion Phase)
    public void nodeExpansion(game tempGame) {
        if (!tempGame.terminalGameState()) {//checks if game is over
            for (int move: tempGame.generateValidMoves()) {
                node childNode = new node(move, this);//the parent is the current node
                children.add(childNode);
            }
        }
    }

    //updates during the backpropagation
    public void updateNode(double winPer) {
        visits++;
        wins+=winPer;
    }

    //checks if this is an ending Node (a leaf on the tree)
    public boolean isLeaf() {
        return children.size()==0;
    }

    //use during backpropagation to see when we reach the root node (or the starting node)
    public boolean hasParent() {
        return !Objects.isNull(parent); //!parent.equals(null);
    }

    //decides which node to visit. Balances the exploration/exploitation tradeoff (slight favor to exploration)
    public double priorityFunction(int iterations) {
        return wins/(visits+1) + Math.pow(3, .5)*Math.pow(Math.log(iterations)/(visits+1), .5);//(wins+iterations)/(visits+2*iterations);//
    }


}
