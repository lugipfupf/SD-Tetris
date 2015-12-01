package tetris.game;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;

/**
 * Created by highway on 01/12/15.
 */
public class Scoring {
    private final static String HIGH_SCORE_FILE = ".highscore";

    int level = 1;
    int score = 0;
    int highScore = 0;

    File highScoreFile;

    public Scoring() {
        highScoreFile = new File(HIGH_SCORE_FILE);

        // attempt to create file if it does not exist
        if ( ! highScoreFile.exists()) {
            try {
                highScoreFile.createNewFile();
                update(0);
            } catch (IOException e) {
                System.err.println("Could not create or read high score file " + HIGH_SCORE_FILE);
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(HIGH_SCORE_FILE))) {
            highScore = reader.read();
        } catch (IOException e) {
            System.err.println("Error reading high score file");
        }
    }

    public void update(int count) {
        switch (count) {
            case 1:
                score += 1;
                break;
            case 2:
                score += 3;
                break;
            case 3:
                score += 7;
                break;
            case 4:
                score += 15;
                break;
            default:
                score += 0;
                break;
        }

        highScore = score > highScore ? score : highScore;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HIGH_SCORE_FILE))) {
            writer.write(highScore);
        } catch (IOException e) {
            System.err.println("Error writing high score file");
        }
    }

    public void reset() {
        level = 0;
        score = 0;
        highScore = 0;
    }

    public int getLevel() {
        return level;
    }

    public int getScore() {
        return score;
    }

    public int getHighScore() {
        return highScore;
    }
}
