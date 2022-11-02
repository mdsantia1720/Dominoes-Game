import java.util.*;

/**
 * A game class create a game and keeps track of the rules
 * players and actions in the game
 *
 * @author Micky Santiago-Zayas
 * @version July 10, 2021
 */

public class Game extends Table {
    private int pointsToWin;
    private Table table;
    private ArrayList<Player> players;
    private Chain chain = new Chain();
    private int turn;
    private int team1Points;
    private int team2Points;
    private int team3Points;
    private boolean newRound;

    // Constructor to create a new game
    public Game(ArrayList<Player> players, int pointsToWin) {
        super(players.size());
        this.players = players;
        this.pointsToWin = pointsToWin;
        newRound = true;
        turn = 0;
    }

    public void newRound() {
        newTable();
    }

    public int getTurn() {
        if (turn == 0) {
            Domino doubleSix = new Domino(6,6);
            for (ArrayList<Domino> hand: getHands()) {
                for (Domino test: hand) {
                    if (test.equals(doubleSix)) {
                        turn = getHands().indexOf(hand);
                        break;
                    }
                }
            }
        }
        return turn;
    }

    public ArrayList<Domino> getHand(Player player) {
        int index = players.indexOf(player);
        return getHands().get(index);
    }

    public void removeDomino(Player player, Domino domino) {
        int index = players.indexOf(player);
        super.removeDomino(index, domino);
    }

    public void skip(Player player) {
        if (getAvailable().size() > 0) {
            addDomino(player);
        }
        else
            turn++;
    }

    private void addDomino(Player player) {
        int index = players.indexOf(player);
        super.addDomino(index);
    }

    public boolean validMove(Domino domino, int side) {
        if (newRound) {
            if (turn > 3)
                return true;
            else {
                if (domino.equals(new Domino(6, 6)))
                    return true;
            }
        }
        if (side == 0) {
            int value = chain.getTailInt();
            if (domino.getSide1() == value)
                return true;
            if (domino.getSide2() == value)
                return true;
        }
        if (side == 1) {
            int value = chain.getHeadInt();
            if (domino.getSide1() == value)
                return true;
            if (domino.getSide2() == value)
                return true;
        }
        System.out.println("That domino was not a valid move!");
        return false;
    }

    public void addToChain(Player player, Domino domino, int side) {
        if (newRound)
            newRound = false;
        if (chain.getSize() == 0)
            chain.addToken(domino, -1);
        else
            chain.addToken(domino, side);
        removeDomino(players.indexOf(player), domino);
        turn++;
    }

    public int getTeam1Points() {
        return team1Points;
    }

    public int getTeam2Points() {
        return team2Points;
    }

    public int getTeam3Points() {
        return team3Points;
    }

    public boolean endGame() {
        if (team1Points >= pointsToWin || team2Points >= pointsToWin || team3Points >= pointsToWin) {
            getWinners();
            return true;
        }
        return false;
    }

    public String getWinners() {
        if (players.size() == 4) {
            if (team1Points >= pointsToWin)
                return String.format("%s and %s are the winners!", players.get(0), players.get(2));
            return String.format("%s and %s are the winners!", players.get(1), players.get(3));
        }
        if (players.size() == 3 || players.size() == 2) {
            String winner;
            if (team1Points >= pointsToWin)
                winner = players.get(0).toString();
            else if (team2Points >= pointsToWin)
                winner = players.get(1).toString();
            else if (team3Points >= pointsToWin)
                winner = players.get(2).toString();
            else
                return null;
            return String.format("%s is the winner!", winner);
        }
        return null;
    }
    
    public boolean endRound() {
        for (ArrayList<Domino> hand: getHands()) {
            if (hand.size() == 0) {
                addPoints();
                return true;
            }
        }
        return false;
    }
    
    public int getRoundWinners() {
        int winners = 0;
        if (players.size() == 4) {
            for (ArrayList<Domino> hand: getHands()) {
                if (hand.size() == 0) {
                    if (getHands().indexOf(hand) == 0 || getHands().indexOf(hand) == 2)
                        winners = 1;
                    else
                        winners = 2;
                    break;
                }
            }
        } else if (players.size() == 2 || players.size() == 3) {
            for (ArrayList<Domino> hand: getHands()) {
                if (hand.size() == 0) {
                    if (getHands().indexOf(hand) == 0)
                        winners = 1;
                    else if (getHands().indexOf(hand) == 1)
                        winners = 2;
                    else
                        winners = 3;
                    break;
                }
            }
        }
        newRound = true;
        return winners;
    }

    public int getRoundPoints() {
        int points = 0;
        for (ArrayList<Domino> hand: getHands()) {
            for (Domino domino: hand)
                points += domino.getSide1() + domino.getSide2();
        }
        return points;
    }

    public void addPoints() {
        int winner = getRoundWinners();
        int points = getRoundPoints();
        if (winner == 1)
            team1Points += points;
        else if (winner == 2)
            team2Points += points;
        else
            team3Points += points;
    }

    public void gameTerminal() throws Exception {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Domino> hand = this.getHands().get(this.getTurn() % 4);
        System.out.println(hand);
        int index = scanner.nextInt();
        int side = -1;
        if (!this.newRound)
            side = scanner.nextInt();
        if (index != -1) {
            if (this.validMove(hand.get(index), side)) {
                this.addToChain(this.players.get(this.getTurn() % 4), hand.get(index), side);
                System.out.println(this.chain.readChain());
            } else
                throw new Exception();
        } else
            this.turn++;
    }

    public Chain getChain() {
        return chain;
    }

    public static void main(String[] args) {
        new GUI();
    }
}
