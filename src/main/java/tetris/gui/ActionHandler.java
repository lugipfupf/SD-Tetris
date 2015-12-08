package tetris.gui;

/**
 * The interface ActionHandler defines the methods to move a Tetris figure.
 *
 * @author Stephan Fischli
 * @version 2.0
 */
public interface ActionHandler {

	/**
	 * Moves the figure down.
	 */
	void moveDown() throws Exception;

	/**
	 * Moves the figure left.
	 */
	void moveLeft() throws Exception;

	/**
	 * Moves the figure right.
	 */
	void moveRight() throws Exception;

	/**
	 * Rotates the figure to the left.
	 */
	void rotateLeft() throws Exception;

	/**
	 * Rotates the figure to the left.
	 */
	void rotateRight() throws Exception;

	/**
	 * Drops the figure.
	 */
	void drop() throws Exception;
}
