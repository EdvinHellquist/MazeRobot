import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class RandomRobot. A robot which moves randomly through a maze object.
 * Course: Objektorienterad programmeringsmetodik
 * File: RandomRobot.java
 * @author Edvin Hellquist (id20eht)
 * @version 2.0
 */

public class RandomRobot extends Robot {

    /**
     * Constructor which creates the RandomRobot. Only uses and initializes the abstract robots attributes.
     * @param m Object of the class maze, read from a textfile, which the robot "moves" through.
    */
    public RandomRobot(Maze m) {
        super(m);
    }

    /**
     * The method which simulates the robot moving. Implementing the abstract class move-method.
     * Every move is nearly random, though it will not move to its previous position except for the case where it
     * cannot move anywhere else. If it cannot move from its start point a system.exit will be executed. If there is
     * no way to the goal the robot will just move randomly until stopped by the user.
     */
    @Override
    public void move() {
        // Declaring variables used to randomize the order in which way the robot tries to move.
        ArrayList<Integer> order = new ArrayList<>();
        int next = 0;
        int upperBound = 4;
        int added = 0;
        Random rand = new Random();
        // Randomizes integers between 1 and 4 until it has added all four digits to the arraylist.
        while (added < 4) {
            int randomInt = rand.nextInt(upperBound) + 1;
            if (!order.contains(randomInt)) {
                order.add(randomInt);
                added++;
            }
        }
        //Adds a zero at the end. This is used to access the default case which moves the robot to its previous position
        order.add(order.size(), 0);
        // Initiates a variable which keeps track of whether the robot has moved.
        boolean moved = false;
        // As long as the robot has not moved we loop, the try/catch statement is used to catch when the robot tries to
        // access a position outside of the maze.
            while (!moved) {
                try {
                    // Each case 1-4 represent a move in some direction. If the move goes through the "moved"-variable
                    // is set to true. If it does not go through the "next"-variable to increment by one making the
                    // switch-statement try the next case determined by the randomized order.
                switch (order.get(next)) {
                    case 1:
                        Position northPos = position.getPosToNorth();
                        if (maze.isMovable(northPos)) {
                            if (northPos.equals(position) | northPos.equals(previousPosition)) {
                                next++;
                                break;
                            } else {
                                previousPosition = getPosition();
                                setPosition(northPos);
                                moved = true;
                            }
                        }
                        next++;
                        break;
                    case 2:
                        Position westPos = position.getPosToWest();
                        if (maze.isMovable(westPos)) {
                            if (westPos.equals(position) | westPos.equals(previousPosition)) {
                                next++;
                                break;
                            } else {
                                previousPosition = getPosition();
                                setPosition(westPos);
                                moved = true;
                            }
                        }
                        next++;
                        break;
                    case 3:
                        Position eastPos = position.getPosToEast();
                        if (maze.isMovable(eastPos)) {
                            if (eastPos.equals(position) | eastPos.equals(previousPosition)) {
                                next++;
                                break;
                            } else {
                                previousPosition = getPosition();
                                setPosition(eastPos);
                                moved = true;
                            }
                        }
                        next++;
                        break;
                    case 4:
                        Position southPos = position.getPosToSouth();
                        if (maze.isMovable(southPos)) {
                            if (southPos.equals(position) | southPos.equals(previousPosition)) {
                                next++;
                                break;
                            } else {
                                previousPosition = getPosition();
                                setPosition(southPos);
                                moved = true;
                            }
                        }
                        next++;
                        break;
                        // The default case is to move back to the previous position, although if the robots current
                        // and previous position is still equal it cannot move anywhere and we throw an exception which
                        // indicates that the maze-file is faulty.
                    default:
                        if(position.equals(previousPosition)) {
                            throw new IOException();
                        }
                        Position temp = getPosition();
                        position = previousPosition;
                        previousPosition = temp;
                        moved = true;
                }
            } catch(IndexOutOfBoundsException e) {
                next++;
            } catch (IOException e) {
                    System.out.println("Cannot move from startpoint. Exiting.");
                    System.exit(-1);
                }
        }
    }
}
