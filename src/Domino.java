import java.util.Objects;

/**
 * A class to individual domino chip objects
 *
 * @author Micky Santiago-Zayas
 * @version July 9, 2021
 */

public class Domino {
    private int side1;
    private int side2;

    // Constructor of a single domino token
    public Domino(int side1, int side2) {
        this.side1 = side1;
        this.side2 = side2;
    }

    public int getSide1() {
        return side1;
    }

    public int getSide2() {
        return side2;
    }

    @Override
    public String toString() {
        return "<" + side1 + ", " +side2 + ">";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Domino domino = (Domino) o;
        return side1 == domino.side1 && side2 == domino.side2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(side1, side2);
    }
}
