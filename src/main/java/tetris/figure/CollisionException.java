package tetris.figure;

/**
 * Created by highway on 17/11/15.
 */
public class CollisionException extends RuntimeException {
    public CollisionException() {
        System.err.println("Collision detected....");
    }
}
