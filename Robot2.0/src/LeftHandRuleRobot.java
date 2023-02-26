/**
 * Class LeftHandRuleRobot. A robot which inherits from the abstract class Robot, traverses the maze by moving
 * with its "left" hand against a wall at all times.
 * Course: Objektorienterad programmeringsmetodik
 * File: LeftHandRuleRobot.java
 * @author Edvin Hellquist (id20eht)
 * @version 1.0. first public version.
 */

public class LeftHandRuleRobot extends Robot {
    private Position posInFront;
    private Position posToLeft;

    /**
     * Constructor for the LeftHandRuleRobot, initializes the superclass attributes as well as its own attributes
     * posInFront and posToLeft. The super class attributes are initialized as in the abstract class. PosInFront is
     * set to the north position of the start position and posToLeft as the position to west.
     * @param m object of the class maze, read from a textfile.
     */
    public LeftHandRuleRobot(Maze m) {
        super(m);
        posInFront = position.getPosToNorth();
        posToLeft = position.getPosToWest();
    }

    /**
     * Implements the abstract robot class move-method. Simulates the robot moving through the maze, always having its
     * posToLeft on a wall. If the robot cannot move from its startpoint a system.exit will be executed. If there is no
     * way to the goal, the robot will move in circles.
     */
    @Override
    public void move() {
        boolean moved = false;
        int rotated = 0;
        // As long as it has not taken a step keep trying.
        while (!moved) {
            try {
                if(rotated >= 4) {
                    System.out.println("Cannot move from startpoint, exiting");
                    System.exit(-1);
                }
                // If it can move to the left, take a step and set front and left positions with the rotate method
                if(maze.isMovable(posToLeft)) {
                    this.rotate90CounterClockwise();
                    this.step();
                    moved = true;
                }
                // Otherwise if we can move forward, do that
                else if (maze.isMovable(posInFront)) {
                    this.step();
                    moved = true;
                }
                // Otherwise we rotate and try again
                else {
                    this.rotate90Clockwise();
                    rotated++;
                }
            // If the front position are out of bounds, rotate
            } catch (IndexOutOfBoundsException e) {
                this.rotate90Clockwise();
            }
        }
    }

    /**
     * Help method for the class move-method. Rotates the robot 90 degrees clockwise by changing its front and left
     * position depending on which its current front position is.
     */
    public void rotate90Clockwise() {
        if (posInFront.equals(position.getPosToNorth())) {
            posInFront = position.getPosToEast();
            posToLeft = position.getPosToNorth();

        } else if (posInFront.equals(position.getPosToSouth())) {
            posInFront = position.getPosToWest();
            posToLeft = position.getPosToSouth();

        } else if (posInFront.equals(position.getPosToWest())) {
            posInFront = position.getPosToNorth();
            posToLeft = position.getPosToWest();

        } else if (posInFront.equals(position.getPosToEast())) {
            posInFront = position.getPosToSouth();
            posToLeft = position.getPosToEast();
        }
    }
    /**
     * Help method for the class move-method. Rotates the robot 90 degrees counterclockwise by changing its front and
     * left position depending on which its current front position is.
     */
    public void rotate90CounterClockwise() {

        if (posInFront.equals(position.getPosToNorth())) {
            posInFront = position.getPosToWest();
            posToLeft = position.getPosToNorth();

        } else if (posInFront.equals(position.getPosToSouth())) {
            posInFront = position.getPosToEast();
            posToLeft = position.getPosToSouth();

        } else if (posInFront.equals(position.getPosToWest())) {
            posInFront = position.getPosToSouth();
            posToLeft = position.getPosToWest();

        } else if (posInFront.equals(position.getPosToEast())) {
            posInFront = position.getPosToNorth();
            posToLeft = position.getPosToEast();
        }
    }
    /**
     * Help method for the class move-method. Simulates a step in the direction of the robots current front position by
     * changing the front, left and current positions.
     *
     */
    public void step() {
        Position p = position;
        position = posInFront;

        if (posInFront.equals(p.getPosToNorth())) {
            posToLeft = posInFront.getPosToWest();
            posInFront = posInFront.getPosToNorth();

        } else if (posInFront.equals(p.getPosToSouth())) {
            posToLeft = posInFront.getPosToEast();
            posInFront = posInFront.getPosToSouth();

        } else if (posInFront.equals(p.getPosToWest())) {
            posToLeft = posInFront.getPosToSouth();
            posInFront = posInFront.getPosToWest();

        } else if (posInFront.equals(p.getPosToEast())) {
            posToLeft = posInFront.getPosToNorth();
            posInFront = posInFront.getPosToEast();
        }
    }

    /**
     * Help method used for testing. Gets the current left position.
     * @return current left position.
     */
    public Position getPosToLeft() {
        return posToLeft;
    }
    /**
     * Help method used for testing. Gets the current front position.
     * @return current front position.
     */
    public Position getPosInFront() {
        return posInFront;
    }
}
