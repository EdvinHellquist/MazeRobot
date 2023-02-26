public interface GenericRobot {
    /**
     * Interface for a generic robot.
     * Course: Objektorienterad programmeringsmetodik
     * File: GenericRobot.java
     * @author Edvin Hellquist (id20eht)
     * @version 1.0. first public version.
     */
    void move();
    Position getPosition();
    void setPosition(Position p);
    boolean hasReachedGoal();

}
