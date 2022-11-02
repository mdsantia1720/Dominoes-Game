import java.util.ArrayList;
import java.util.Random;

/**
 * A class to create a table object
 *
 * @author Micky Santiago-Zayas
 * @version July 9, 2021
 */

public class Table {
    private ArrayList<Domino> available;
    private int numPlayers;
    private ArrayList<ArrayList> hands;

    // Constructor of a new table
    public Table(int numPlayers) {
        this.numPlayers = numPlayers;
        // creating 'deck'/all domino tokens
        newTable();
    }

    public void newTable() {
        available = new ArrayList<>();
        hands = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            for (int j = i; j < 7; j++) {
                available.add(new Domino(i, j));
            }
        }

        for (int i = 0; i < numPlayers; i++) { // generating random dominoes to each player
            Random r = new Random();
            ArrayList<Domino> hand = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                int pos = r.nextInt(available.size());
                hand.add(available.get(pos));
                available.remove(pos);
            }
            hands.add(hand);
        }
    }

    public ArrayList<ArrayList> getHands() {
        return hands;
    }

    public ArrayList<Domino> getAvailable() {
        return available;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void addDomino(int index) {
        Random random = new Random();
        Domino domino = available.get(random.nextInt(available.size()));
        available.remove(domino);
        ArrayList<Domino> hand = hands.get(index);
        hand.add(domino);
        hands.remove(index);
        hands.add(index, hand);
    }

    public void removeDomino(int index, Domino domino) {
        ArrayList<Domino> hand = hands.get(index);
        hand.remove(domino);
        hands.remove(index);
        hands.add(index, hand);
    }
}
