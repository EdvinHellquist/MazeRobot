import java.util.Objects;

/**
 * Class Position which contains two integers representing coordinates.
 * Course: Objektorienterad programmeringsmetodik
 * File: Position.java
 * @author Edvin Hellquist (id20eht)
 *
 */

public class Position {
    private final int x;
    private final int y;

    /**
     * Construct a position with x and y coordinates
     * @param x Integer representing a x-coordinate which in turn is representing the column
     * @param y Integer representing a y-coordinate which in turn is representing the row
     */
    public Position (int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets a positions x-value.
     * @return the positions current x-value.
     */
    public int getX() {
        return x;
    }
    /**
     * Gets a positions y-value.
     * @return the positions current y-value.
     */
    public int getY() {
        return y;
    }
    /**
     * Creates a new position one step east (next character in the string) of the current.
     * @return the position one step to the right.
     */
    public Position getPosToSouth() {
        return new Position(x, y + 1);
    }
    /**
     * Creates a new position one step north (up in the array) of the current.
     * @return the position one step up from the current.
     */
    public Position getPosToNorth() {
        return new Position(x, y - 1);
    }
    /**
     * Creates a new position one step west (previous character in the string) of the current.
     * @return the position one step to the left.
     */
    public Position getPosToWest() {
        return new Position(x - 1, y);
    }
    /**
     * Creates a new position one step south (down in the array) of the current.
     * @return the position one step to the right.
     */
    public Position getPosToEast() {
        return new Position(x + 1, y);
    }

    /**
     * Function that compares two positions and determines if they are equal.
     * @param o An object to compare with.
     * @return True if the current position is equal to the object used as a parameter otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    /**
     * A function that turns a position into a hashvalue.
     * @return A positions hashvalue.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}