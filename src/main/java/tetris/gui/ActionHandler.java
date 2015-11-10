package tetris.gui;

/**
 * Created by highway on 10/11/15.
 */
public interface ActionHandler {
    void moveDown();
    void moveLeft();
    void moveRight();
    void rotateLeft();
    void rotateRight();
    void drop();
}
