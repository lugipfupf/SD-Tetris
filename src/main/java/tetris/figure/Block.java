package tetris.figure;

/**
 * The class Block implements a block of the Tetris game.
 *
 * @author Stephan Fischli
 * @version 2.0
 */
public class Block {

	/**
	 * The x coordinate of the block.
	 */
	public int x;

	/**
	 * The y coordinate of the block.
	 */
	public int y;

	/**
	 * The color of the block.
	 */
	public int color;

	/**
	 * Constructs a block with the specified coordinates and color.
	 *
	 * @param x the x coordinate of the block
	 * @param y the y coordinate of the block
	 * @param color the color of the block
	 */
	public Block(int x, int y, int color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
}
