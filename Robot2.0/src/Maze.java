import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class Maze which reads from a textfile, puts the characters into a arraylist of strings. One line in the textfile
 * is considered as one string.
 * Course: Objektorienterad programmeringsmetodik
 * File: Maze.java
 * @author Edvin Hellquist (id20eht)
 * @version 2.0. Moved some verifying responsibilities to the constructor, deleted print method.
 *
 */

public class Maze {
    private final int maxRows;
    private final int maxColumns;
    private final ArrayList<String> labyrinth;

    /**
     * Constructor for the class maze. Creates a maze as a arraylist of strings. The strings are constructed with '*' as
     * walls, ' ' as movable positions, 'S' as a startpoint and 'G' as goal. Also sets maxRows and maxColumns.
     * MaxRows is equal to the number of rows in the file and maxColumns is equal to the amount of characters on the
     * longest row.
     * @param reader An object of class reader to use when reading from the textfile.
     * @throws IOException if the scanner finds an invalid character, no characters at all, anything else than one
     * startpoint or no goal.
     */

    public Maze(java.io.Reader reader) throws IOException {
        int startpoint = 0;
        int goal = 0;

        this.labyrinth = new ArrayList<>();
        Scanner scanner = new Scanner(reader);
        int countCharacters = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            labyrinth.add(line);
            for (int j = 0; j < line.length(); j++){
                if(line.charAt(j) == ' '|| line.charAt(j) == '*') {
                    countCharacters++;
                }
                else if (line.charAt(j) == 'S') {
                    startpoint++;
                    countCharacters++;
                }
                else if (line.charAt(j) == 'G') {
                    goal++;
                    countCharacters++;
                }
                else {
                    throw new IOException();
                }
            }
        }
        if(goal < 1 | startpoint != 1 |countCharacters < 1) {
            throw new IOException();
        }
        maxColumns = getNumColumns();
        maxRows = getNumRows();
    }

    /**
     * A method which verifies whether the position p contains a movable character.
     * @param p An object of the class position to examine whether it holds character which the robot is allowed to
     *          "walk" to.
     * @throws IndexOutOfBoundsException if the position which the robot is trying to access is not within the array.
     * @return True if the position is movable (contains a whitespace) otherwise false.
     */

    public boolean isMovable(Position p) {
        int y = p.getY();
        int x = p.getX();
        String temp = labyrinth.get(y);
        if(!(y >= 0 && y <= maxRows && x >= 0 && x <= maxColumns)) {
            throw new IndexOutOfBoundsException();
        }
        return temp.charAt(x) == ' ' | temp.charAt(x) == 'G' | temp.charAt(x) == 'S';
    }

    /**
     * A method which verifies whether a position is the goal.
     * @param p An object of the class position to be examined.
     * @return True if the array-position contains a 'G' otherwise false.
     */

    public boolean isGoal(Position p) {
        int y = p.getY();
        int x = p.getX();
        String temp = this.labyrinth.get(y);
        return temp.charAt(x) == 'G';
    }

    /**
     * A method that searches the arraylist for the startpoint ('S').
     * @return The position where the startpoint is.
     */
    public Position getStart() {
        Position p = null;
        //Double for-loops to search every row and every column.
        for (int i = 0; i < labyrinth.size(); i++) {
            String x = labyrinth.get(i);
            for (int j = 0; j < x.length(); j++) {
                // If a S or a G is found, increase respective counter
                if (x.charAt(j) == 'S') {
                    p = new Position(j, i);
                }
            }
        } // Return the startpoint
        return p;
    }

    /**
     * Method that retrieves the longest string stored in the arraylist, representing the columns of the maze.
     * @return Integer representing the number of characters of the longest string.
     */

    public int getNumColumns() {
        int cols = 0;
        for (String temp : labyrinth) {
            int maxCols = temp.length();
            if (maxCols > cols) {
                cols = maxCols;
            }
        }
        return cols;
    }

    /**
     * Method similar to the previous with the only difference being that this one retrieves the size of the arraylist.
     * representing the rows in the maze.
     * @return Integer representing the number of rows in the maze.
     */

    public int getNumRows() {
        int rows = 0;
        for (int i = 0; i < labyrinth.size(); i++) {
            rows = rows + 1;
        }
        return rows;
    }
}