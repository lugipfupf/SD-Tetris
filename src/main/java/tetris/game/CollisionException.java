package tetris.game;

/**
 * Created by highway on 17/11/15.
 */
public class CollisionException extends RuntimeException {
    public CollisionException() {
        super();
        System.err.println("Collision detected....");
    }
}
