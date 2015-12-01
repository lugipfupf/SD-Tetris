package tetris.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import javax.swing.Box;
import javax.swing.JFrame;

/**
 * The class GUI implements the graphical user interface of the Tetris game.
 *
 * @author Stephan Fischli
 * @version 2.0
 */
public class GUI extends JFrame implements KeyListener {

	/**
	 * The panel that displays the field.
	 */
	private final FieldPanel fieldPanel;

	/**
	 * The panel that contains the control components.
	 */
	private final ControlPanel controlPanel;

	/**
	 * The queue of action events.
	 */
	private final BlockingQueue<ActionEvent> eventQueue = new ArrayBlockingQueue<>(10);

	/**
	 * The handler of the game actions.
	 */
	private ActionHandler actionHandler = new QueueActionHandler();

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);

		// add components
		fieldPanel = new FieldPanel(width, height);
		getContentPane().add(Box.createVerticalStrut(20), BorderLayout.NORTH);
		getContentPane().add(Box.createHorizontalStrut(20), BorderLayout.WEST);
		getContentPane().add(fieldPanel, BorderLayout.CENTER);
		getContentPane().add(Box.createHorizontalStrut(20), BorderLayout.EAST);
		controlPanel = new ControlPanel();
		getContentPane().add(controlPanel, BorderLayout.SOUTH);

		// set size and location
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Registers the handler that handles the game actions.
	 *
	 * @param handler the action handler
	 */
	public void setActionHandler(ActionHandler handler) {
		actionHandler = handler;
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
	 * Sets the level of the game to be displayed.
	 */
	public void setLevel(int level) {
		controlPanel.setLevel(level);
	}

	/**
	 * Sets the score of the game to be displayed.
	 */
	public void setScore(int score) {
		controlPanel.setScore(score);
	}

	/**
	 * Sets the high score of the game to be displayed.
	 */
	public void setHighScore(int highScore) {
		controlPanel.setHighScore(highScore);
	}

	/**
	 * Handles a key event.
	 *
	 * @param event the event to be handled
	 */
	@Override
	public void keyPressed(KeyEvent event) {
		try {
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
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			Toolkit.getDefaultToolkit().beep();
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
	}

	@Override
	public void keyTyped(KeyEvent event) {
	}

	/**
	 * The class QueueActionHandler implements an action handler that queues the action events.
	 */
	private class QueueActionHandler implements ActionHandler {

		@Override
		public void moveDown() {
			eventQueue.offer(ActionEvent.MOVE_DOWN);
		}

		@Override
		public void moveLeft() {
			eventQueue.offer(ActionEvent.MOVE_DOWN);
		}

		@Override
		public void moveRight() {
			eventQueue.offer(ActionEvent.MOVE_DOWN);
		}

		@Override
		public void rotateLeft() {
			eventQueue.offer(ActionEvent.MOVE_DOWN);
		}

		@Override
		public void rotateRight() {
			eventQueue.offer(ActionEvent.MOVE_DOWN);
		}

		@Override
		public void drop() {
			eventQueue.offer(ActionEvent.MOVE_DOWN);
		}
	}
}
