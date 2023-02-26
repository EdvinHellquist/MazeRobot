
/**
 * Abstract class for a robot, implements the GenericRobot interface. Implements shared methods and initializes
 * mutual attributes.
 * Course: Objektorienterad programmeringsmetodik
 * File: Robot.java
 * @author Edvin Hellquist (id20eht)
 * @version 1.0. First Public version.
 */

public abstract class Robot implements GenericRobot {
    protected Position position;
    protected Position previousPosition;
    protected Maze maze;

    /**
     * General constructor for a robot. Sets both current and previous position to the start position as well as the
     * maze to the parameter m.
     * @param m object of the class maze, read from a textfile.
     */
    public Robot(Maze m) {
        position = m.getStart();
        previousPosition = m.getStart();
        maze = m;
    }
    /**
     * The method which simulates the robot moving.
     * Is implemented in the non-abstract classes.
     */
    public abstract void move();

    /**
     * Method which gets the robots current position.
     * @return The current position.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Sets the position p to the robots current position.
     * @param p Object of class position to set the robots current position to.
     */
    public void setPosition(Position p) {
        position = p;
    }

    /**
     * Method which determines if the robots current position is the goal.
     * @return True if the current position is the goal otherwise false.
     */
    public boolean hasReachedGoal() {
        return maze.isGoal(position);
    }
}

