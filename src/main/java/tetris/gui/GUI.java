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
	 * The handler of the status change events.
	 */
	private StatusHandler statusHandler;

	/**
	 * The handler of the figure action events.
	 */
	private ActionHandler actionHandler = new QueueActionHandler();

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
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);

		// add components
		fieldPanel = new FieldPanel(width, height);
		fieldPanel.setBackground(Color.yellow);
		getContentPane().add(Box.createVerticalStrut(25), BorderLayout.NORTH);
		getContentPane().add(Box.createHorizontalStrut(35), BorderLayout.WEST);
		getContentPane().add(fieldPanel, BorderLayout.CENTER);
		getContentPane().add(Box.createHorizontalStrut(25), BorderLayout.EAST);
		controlPanel = new ControlPanel();
		getContentPane().add(controlPanel, BorderLayout.SOUTH);

		// set size and location
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Registers the handler of the status change events.
	 *
	 * @param handler the status handler
	 */
	public void setStatusHandler(StatusHandler handler) {
		statusHandler = handler;
	}

	/**
	 * Registers the handler of the figure action events.
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
	}

	/**
	 * Draws blocks.
	 *
	 * @param blocks the blocks to be drawn
	 */
	public void drawBlocks(Block[] blocks) {
		fieldPanel.addBlocks(Arrays.asList(blocks));
	}

	/**
	 * Clears blocks.
	 *
	 * @param blocks the blocks to be cleared
	 */
	public void clearBlocks(List<Block> blocks) {
		fieldPanel.removeBlocks(blocks);
	}

	/**
	 * Clears blocks.
	 *
	 * @param blocks the blocks to be cleared
	 */
	public void clearBlocks(Block[] blocks) {
		fieldPanel.removeBlocks(Arrays.asList(blocks));
	}

	/**
	 * Clears all blocks.
	 */
	public void clearAllBlocks() {
		fieldPanel.removeAllBlocks();
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
	 * Sets the status of the game to be displayed.
	 */
	public void setStatus(Status status) {
		controlPanel.setStatus(status);
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
				case KeyEvent.VK_ENTER:
					if (statusHandler != null) {
						statusHandler.changeStatus();
					}
					break;
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
						actionHandler.rotateRight();
					} else {
						actionHandler.rotateLeft();
					}
					break;
				case KeyEvent.VK_SPACE:
					actionHandler.drop();
					break;
			}
		} catch (RuntimeException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			System.err.println(ex);
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
