package tetris.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
	private final JLabel highScoreLabel;

	/**
	 * The status label.
	 */
	private final JLabel statusLabel;

	/**
	 * The sound clip.
	 */
	private Clip soundClip;

	/**
	 * Constructs a control panel using the specified graphical user interface.
	 */
	public ControlPanel() {

		// initialize
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);
		setFocusable(true);

		// set defaults
		UIManager.put("Label.foreground", Color.WHITE);
		UIManager.put("Label.font", new Font(Font.DIALOG, Font.PLAIN, 12));
		Font valueFont = new Font(Font.DIALOG, Font.BOLD, 12);

		// add score labels
		JPanel labelPanel = new JPanel();
		labelPanel.setBackground(Color.BLACK);
		this.add(labelPanel, BorderLayout.NORTH);
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
		this.add(Box.createVerticalStrut(10), BorderLayout.CENTER);

		// add status line
		statusLabel = new JLabel(" ");
		statusLabel.setForeground(Color.GRAY);
		this.add(statusLabel, BorderLayout.SOUTH);

		// open sound clip
		try {
			URL url = new URL("http://sws.bfh.ch/fischli/tetris.wav");
			soundClip = AudioSystem.getClip();
			soundClip.open(AudioSystem.getAudioInputStream(url));
		} catch (Exception ex) {
			System.err.println("Could not open sound clip");
		}
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

	/**
	 * Sets the status of the game to be displayed.
	 */
	public void setStatus(Status status) {
		switch (status) {
			case READY:
				statusLabel.setText(" ");
				break;
			case RUNNING:
				statusLabel.setText(" ");
				soundClip.loop(Clip.LOOP_CONTINUOUSLY);
				break;
			case PAUSED:
				statusLabel.setText("Game paused");
				soundClip.stop();
				break;
			case OVER:
				statusLabel.setText("Game over");
				soundClip.stop();
				soundClip.setFramePosition(0);
		}
	}
}
