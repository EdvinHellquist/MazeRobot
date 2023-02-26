import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Class MemoryRobot. A robot which inherits from the abstract class Robot, traverses the maze by moving
 * through it while remembering the positions it has already visited, only moving back to one of those if there is no
 * other choice.
 * Course: Objektorienterad programmeringsmetodik
 * File: MemoryRobot.java
 * @author Edvin Hellquist (id20eht)
 * @version 1.0. first public version.
 */

public class MemoryRobot extends Robot {
    Stack<Position> visited;
    ArrayList<Position> path;

    /**
     * Constructor for the MemoryRobot initializes both the abstract class attributes as well as its own ArrayList and
     * stack. Both the stack and the list are used to remember visited positions.
     * @param m object of the class maze, read from a textfile.
     */
    public MemoryRobot(Maze m) {
        super(m);
        visited = new Stack<>();
        path = new ArrayList<>();
    }

    /**
     * Implements the abstract robot class move-method. Simulates the robot moving through the maze, remembering its
     * visited positions only moving back to these if no other choice is available.
     * If the robot cannot move from its startpoint or cannot find goal a system.exit will be executed.
     */

    @Override
    public void move() {
        boolean moved = false;
        int i = 0;
        // Sets a order for which position to try first
        int[] order = {1,2,3,4,5};
        do {
            try {
                //Every case is equal apart from which position is tried
                if(order[i] == 1) {
                    //If the position, in this case, to the north are movable and not travelled before use the step
                    // method (described below)
                    if (maze.isMovable(position.getPosToNorth())) {
                        if(!path.contains(position.getPosToNorth())) {
                            moved = this.step(position.getPosToNorth());
                        }
                    }
                    i++;
                }
                else if(order[i] == 2) {
                    if (maze.isMovable(position.getPosToSouth())) {
                        if(!path.contains(position.getPosToSouth())) {
                            moved = this.step(position.getPosToSouth());
                        }
                    }
                    i++;
                }
                else if(order[i] == 3) {
                    if (maze.isMovable(position.getPosToEast())) {
                        if(!path.contains(position.getPosToEast())) {
                            moved = this.step(position.getPosToEast());
                        }
                    }
                    i++;
                }
                else if(order[i] == 4) {
                    if (maze.isMovable(position.getPosToWest())) {
                        if(!path.contains(position.getPosToWest())) {
                            moved = this.step(position.getPosToWest());
                        }
                    }
                    i++;
                }
                //If none of above cases caused a move and the positions are unchanged, exit because the robot can´t
                // move from its start position.
                else {
                    if (position.equals(previousPosition)) {
                        System.out.println("Cannot move from startpoint, exiting");
                        System.exit(-1);
                    }
                    //Otherwise backtrack one position by moving to the top-most position stored in the stack
                    Position pos = visited.peek();
                    visited.pop();
                    previousPosition = position;
                    position = pos;
                    moved = true;
                }
            } catch (IndexOutOfBoundsException e) {
                i++;
            } catch (EmptyStackException e) {
                System.out.println("No way found to goal, exiting");
                System.exit(-1);
            }
        } while (!moved);
    }

    /**
     * Help method used in the move method. Simulates the robot taking a step if it has not already moved to that spot.
     * @param p The position the robot should try to move to.
     * @return True if the step was taken otherwise false.
     */
    public boolean step(Position p) {
        if(visited.search(p) == -1) {
            visited.push(position);
            previousPosition = position;
            position = p;
            if(!path.contains(p)) {
                path.add(position);
            }
            return true;
        }
        return false;
    }
}