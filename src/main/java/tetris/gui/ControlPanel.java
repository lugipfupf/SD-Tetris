package tetris.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * The class ControlPanel implements a panel that contains the controls of the Tetris game.
 *
 * @author Stephan Fischli
 * @version 2.0
 */
public class ControlPanel extends JPanel {

	/**
	 * The level label.
	 */
	private final JLabel levelLabel;

	/**
	 * The score label.
	 */
	private final JLabel scoreLabel;

	/**
	 * The high score label.
	 */
	private JLabel highScoreLabel;

	/**
	 * Constructs a control panel using the specified graphical user interface.
	 */
	public ControlPanel() {

		// initialize
		setBackground(Color.black);
		setLayout(new BorderLayout(10, 10));
		setFocusable(true);

		// add labels
		Font valueFont = UIManager.getFont("Label.font").deriveFont(Font.BOLD);
		JPanel labelPanel = new JPanel();
		this.add(labelPanel, BorderLayout.CENTER);
		labelPanel.add(new JLabel("Level:"));
		levelLabel = new JLabel(" ");
		levelLabel.setFont(valueFont);
		labelPanel.add(levelLabel);
		labelPanel.add(Box.createHorizontalStrut(10));
		labelPanel.add(new JLabel("Score:"));
		scoreLabel = new JLabel(" ");
		scoreLabel.setFont(valueFont);
		labelPanel.add(scoreLabel);
		labelPanel.add(Box.createHorizontalStrut(10));
		labelPanel.add(new JLabel("High Score:"));
		highScoreLabel = new JLabel(" ");
		highScoreLabel.setFont(valueFont);
		labelPanel.add(highScoreLabel);
	}

	/**
	 * Sets the level of the game to be displayed.
	 */
	public void setLevel(int level) {
		levelLabel.setText(String.valueOf(level));
	}

	/**
	 * Sets the score of the game to be displayed.
	 */
	public void setScore(int score) {
		scoreLabel.setText(String.valueOf(score));
	}

	/**
	 * Sets the high score of the game to be displayed.
	 */
	public void setHighScore(int highScore) {
		highScoreLabel.setText(String.valueOf(highScore));
	}
}
