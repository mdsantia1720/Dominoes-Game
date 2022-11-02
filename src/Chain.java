import java.util.ArrayList;

/**
 * A class to keep track of played domino tokens
 *
 * @author Micky Santiago-Zayas
 * @version July 9, 2021
 */

public class Chain {
    private Node head;
    private Node tail;
    private int size;
    private int headInt;
    private int tailInt;
    private Domino middle;
    private ArrayList<Integer> positionsX;
    private ArrayList<Integer> getPositionsY;

    // Constructor of an empty table with no played dominoes
    public Chain() {
        size = 0;
        headInt = -1;
        tailInt = -1;
    }

    // Add a token to the table
    // int side-To determine which side the player selected
    // if side = 0 then tail is replaced
    // if side = 1 then head is replaced
    public void addToken(Domino domino, int side) {
        Node n;
        if (head == null && side == -1) { // it is a new table
            n = new Node(domino);
            head = n;
            tail = n;
            headInt = domino.getSide1();
            tailInt = domino.getSide2();
            middle = domino;
        }
        else {
            if (side == 0) { // if side = 0 then tail is replaced
                if (domino.getSide1() == tailInt) {
                    tailInt = domino.getSide2();
                    n = new Node(domino);
                } else {
                    tailInt = domino.getSide1();
                    Domino redirected = new Domino(domino.getSide2(), domino.getSide1());
                    n = new Node(redirected);
                }
                tail.link = n;
                tail = n;
            } else { //if side = 1 then head is replaced
                if (domino.getSide1() == headInt) {
                    headInt = domino.getSide2();
                    Domino redirected = new Domino(domino.getSide2(), domino.getSide1());
                    n = new Node(redirected);
                } else {
                    headInt = domino.getSide1();
                    n = new Node(domino);
                }
                n.link = head;
                head = n;
            }
        }
        System.out.printf("Token %s was added to the table on side %d\n", domino, side);
    }

    public ArrayList<Domino> readChain() {
        ArrayList<Domino> read = new ArrayList<>();
        Domino domino;
        Node temporary = head;
        try {
            do {
                domino = temporary.domino;
                read.add(domino);
                temporary = temporary.link;
            } while (temporary != null);
            return read;
        } catch (Exception e) {
            return null;
        }
    }

    public int getSize() {
        return size;
    }

    public int getHeadInt() {
        return headInt;
    }

    public int getTailInt() {
        return tailInt;
    }

    @Override
    public String toString() {
        return "Chain{" +
                head +
                '}';
    }

    public void updatePositions() {
        ArrayList<Domino> chain = readChain();
        int middlePos = chain.indexOf(middle);
        int index = chain.indexOf(middle);
        do {
            // TODO while in the same row
            int side1 = -1; // position relative to middle position
            int side2 = -1;
            if (chain.get(index).getSide1() == chain.get(index).getSide2())

            index -= 1;
        } while(index > -1);
    }

    /**public int getPosition(Domino domino) {

        return;
    }*/

    /**
     * A class to keep track of chain of domino tokens
     *
     * @author Micky Santiago-Zayas
     * @version July 9, 2021
     */


    public class Node {
        private Domino domino;
        private Node link;


        // Constructor of a node between dominoes
        public Node(Domino domino) {
            this.domino = domino;
            size++;
        }

        @Override
        public String toString() {
            return "Node{" + domino + link +
                    "}";
        }
    }
}
