package tetris.gui;

/**
 * The interface ActionHandler defines the methods to move a Tetris figure.
 *
 * @author Stephan Fischli
 * @version 2.0
 */
public interface ActionHandler {

	/**
	 * Moves the figure downwards.
	 */
	void moveDown() throws Exception;

	/**
	 * Moves the figure to the left.
	 */
	void moveLeft() throws Exception;

	/**
	 * Moves the figure to the right.
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
