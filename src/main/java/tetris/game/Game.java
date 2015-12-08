package tetris.game;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import tetris.figure.Figure;
import tetris.gui.ActionHandler;
import tetris.gui.GUI;

/**
 * Created by highway on 27/10/15.
 */
public class Game {
    private GUI gui;
    private Figure currentFig;
    private Field field;
    private Scoring scoring;

    public Game(GUI gui, int width, int height) {
        this.gui = gui;
        this.field = new Field(width, height);
        this.scoring = new Scoring();

        updateScore();

        FigureController controller = new FigureController();

        this.gui.setActionHandler(controller);

        gui.setVisible(true);

        try {
            currentFig = field.getNewFigure();
        } catch (GameOverException e) {
            e.printStackTrace();
        }
        gui.drawBlocks(currentFig.getBlocks());

        Timer t = new Timer();
        t.setActionHandler(controller);
        t.run();
    }

    private void figureLanded() {
        try {
            currentFig = field.depositFigure(currentFig);
        } catch (GameOverException e) {
            System.out.println("G A M E   O V E R");
            System.out.println("Score: " + scoring.getScore() + " Level: " + scoring.getLevel() + " Highscore: " + scoring.getHighScore());
            System.exit(0);
        }

        gui.clearBlocks(currentFig.getBlocks());
        gui.clearBlocks(field.removeFullRows());

        gui.drawBlocks(field.getBlocks());
        gui.drawBlocks(currentFig.getBlocks());

        updateScore();
    }

    private void updateScore() {
        scoring.update(field.getRowsRemoved());

        gui.setLevel(scoring.getLevel());
        gui.setScore(scoring.getScore());
        gui.setHighScore(scoring.getHighScore());
    }

    public class FigureController implements ActionHandler {
        @Override
        public void moveDown() {
            currentFig.translate(0, -1);
            gui.repaint();

            if (field.isColliding(currentFig)) {
                currentFig.translate(0, 1);
                figureLanded();
            }
        }

        @Override
        public void moveLeft() {
            currentFig.translate(-1, 0);
            gui.repaint();
            if (field.isColliding(currentFig)) {
                moveRight();
                throw new CollisionException();
            }
        }

        @Override
        public void moveRight() {
            currentFig.translate(1, 0);
            gui.repaint();

            if (field.isColliding(currentFig)) {
                moveLeft();
                throw new CollisionException();
            }
        }

        @Override
        public void rotateLeft() {
            currentFig.rotate(-1);
            gui.repaint();

            if (field.isColliding(currentFig)) {
                rotateRight();
                throw new CollisionException();
            }
        }

        @Override
        public void rotateRight() {
            currentFig.rotate(1);
            gui.repaint();

            if (field.isColliding(currentFig)) {
                rotateLeft();
                throw new CollisionException();
            }
        }

        @Override
        public void drop() {
            while (!field.isColliding(currentFig)) {
                currentFig.translate(0, -1);
            }
            currentFig.translate(0, 1);
            figureLanded();
        }
    }
}
