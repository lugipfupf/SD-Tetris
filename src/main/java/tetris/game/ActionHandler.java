package tetris.game;


/**
 * Created by highway on 10/11/15.
 */
public interface ActionHandler {
    void moveDown() throws CollisionException;
    void moveLeft() throws CollisionException;
    void moveRight() throws CollisionException;
    void rotateLeft() throws CollisionException;
    void rotateRight() throws CollisionException;
    void drop() throws CollisionException;
}
