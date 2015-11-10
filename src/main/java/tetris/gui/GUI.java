package tetris.gui;

import tetris.figure.Block;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import javax.swing.*;

/**
 * The class GUI implements the graphical user interface of the Tetris game.
 *
 * @author Stephan Fischli
 * @version 2.0
 */
public class GUI extends JFrame implements KeyListener {


	private ActionHandler actionHandler;

	/**
	 * The panel that displays the field.
	 */
	private final FieldPanel fieldPanel;

	/**
	 * The queue of action events.
	 */
	private final BlockingQueue<ActionEvent> eventQueue = new ArrayBlockingQueue<>(10);

	/**
	 * Constructs a graphical user interface.
	 *
	 * @param width the with of the field
	 * @param height the height of the field
	 */
	public GUI(int width, int height) {

		// initialize
		super("Tetris");
		setLayout(new BorderLayout(20, 20));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.BLACK);
		addKeyListener(this);

		// add components
		fieldPanel = new FieldPanel(width, height);
		getContentPane().add(Box.createVerticalStrut(20), BorderLayout.NORTH);
		getContentPane().add(Box.createHorizontalStrut(20), BorderLayout.WEST);
		getContentPane().add(fieldPanel, BorderLayout.CENTER);
		getContentPane().add(Box.createHorizontalStrut(20), BorderLayout.EAST);
		getContentPane().add(Box.createVerticalStrut(20), BorderLayout.SOUTH);

		// set size and location
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Draws blocks.
	 *
	 * @param blocks the blocks to be drawn
	 */
	public void drawBlocks(List<Block> blocks) {
		fieldPanel.addBlocks(blocks);
		repaint();
	}

	/**
	 * Draws blocks.
	 *
	 * @param blocks the blocks to be drawn
	 */
	public void drawBlocks(Block[] blocks) {
		fieldPanel.addBlocks(Arrays.asList(blocks));
		repaint();
	}

	/**
	 * Clears blocks.
	 *
	 * @param blocks the blocks to be cleared
	 */
	public void clearBlocks(List<Block> blocks) {
		fieldPanel.removeBlocks(blocks);
		repaint();
	}

	/**
	 * Clears blocks.
	 *
	 * @param blocks the blocks to be cleared
	 */
	public void clearBlocks(Block[] blocks) {
		fieldPanel.removeBlocks(Arrays.asList(blocks));
		repaint();
	}

	/**
	 * Waits for an action event.
	 *
	 * @return the event that has arrived.
	 */
	public ActionEvent waitEvent() {
		try {
			return eventQueue.take();
		} catch (InterruptedException ex) {
			return null;
		}
	}

	/**
	 * Handles a key event.
	 *
	 * @param event the event to be handled
	 */
	@Override
	public void keyPressed(KeyEvent event) {
		switch (event.getKeyCode()) {
			case KeyEvent.VK_DOWN:
				actionHandler.moveDown();
				break;
			case KeyEvent.VK_LEFT:
				actionHandler.moveLeft();
				break;
			case KeyEvent.VK_RIGHT:
				actionHandler.moveRight();
				break;
			case KeyEvent.VK_UP:
                if (event.isShiftDown()) {
				    actionHandler.rotateLeft();
                } else {
				    actionHandler.rotateRight();
                }
				break;
			case KeyEvent.VK_SPACE:
				actionHandler.drop();
				break;
		}
        repaint();
	}

	@Override
	public void keyReleased(KeyEvent event) {
	}

	@Override
	public void keyTyped(KeyEvent event) {
	}

	public void setActionHandler(ActionHandler actionHandler) {
		this.actionHandler = actionHandler;
	}
}
