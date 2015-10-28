package tetris.gui;

import tetris.figure.Block;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JPanel;

/**
 * The class FieldPanel implements a panel that displays the field of the Tetris game.
 *
 * @author Stephan Fischli
 * @version 2.0
 */
public class FieldPanel extends JPanel {

	private static final int BORDER_SIZE = 5;
	private static final int BLOCK_SIZE = 20;
	private static final int BLOCK_SPACING = 1;

	private static final Color BORDER_COLOR = Color.GRAY;
	private static final Color[] COLOR_PALETTE = new Color[]{
		Color.RED, Color.YELLOW, Color.MAGENTA, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE
	};

	/**
	 * The width of the field.
	 */
	private final int width;

	/**
	 * The height of the field.
	 */
	private final int height;

	/**
	 * The blocks of the field.
	 */
	private final Set<Block> blocks;

	/**
	 * Constructs a field panel.
	 *
	 * @param width the with of the field
	 * @param height the height of the field
	 */
	public FieldPanel(int width, int height) {
		this.width = width;
		this.height = height;
		this.blocks = new HashSet<>();
		setPreferredSize(new Dimension(width * BLOCK_SIZE + 2 * BORDER_SIZE, height * BLOCK_SIZE + 2 * BORDER_SIZE));
	}

	/**
	 * Adds blocks to the field.
	 *
	 * @param blocks the blocks to be added
	 */
	public void addBlocks(List<Block> blocks) {
		this.blocks.addAll(blocks);
	}

	/**
	 * Adds blocks to the field.
	 *
	 * @param blocks the blocks to be added
	 */
	public void removeBlocks(List<Block> blocks) {
		this.blocks.removeAll(blocks);
	}

	/**
	 * Draws the border of the field and the blocks.
	 *
	 * @param g the graphics context
	 */
	@Override
	public void paintComponent(Graphics g) {

		// draw border
		g.setColor(BORDER_COLOR);
		g.fillRect(0, 0, BORDER_SIZE, height * BLOCK_SIZE + 2 * BORDER_SIZE);
		g.fillRect(width * BLOCK_SIZE + BORDER_SIZE, 0, BORDER_SIZE, height * BLOCK_SIZE + 2 * BORDER_SIZE);
		g.fillRect(0, height * BLOCK_SIZE + BORDER_SIZE, width * BLOCK_SIZE + 2 * BORDER_SIZE, BORDER_SIZE);

		// draw blocks
		for (Block block : blocks) {
			if (block.x >= 0 && block.x < width && block.y >= 0 && block.y < height) {
				g.setColor(COLOR_PALETTE[(block.color - 1) % 7]);
				int x = BORDER_SIZE + block.x * BLOCK_SIZE + BLOCK_SPACING;
				int y = BORDER_SIZE + (height - block.y - 1) * BLOCK_SIZE + BLOCK_SPACING;
				int w = BLOCK_SIZE - 2 * BLOCK_SPACING;
				g.fillRect(x, y, w, w);
			}
		}
	}
}
