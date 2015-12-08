package tetris.game;

import tetris.figure.Figure;
import tetris.figure.Movement;
import tetris.gui.ActionHandler;
import tetris.gui.GUI;
import tetris.gui.Status;
import tetris.gui.StatusHandler;

/**
 * Created by highway on 27/10/15.
 */
public class Game {
    private GUI gui;
    private Figure currentFig;
    private Field field;
    private Scoring scoring;
    private Timer t = new Timer();
    private Status status = Status.READY;
    private FigureController figureController = new FigureController();
    private GameController gameController = new GameController();

    public Game(GUI gui, int width, int height) {
        this.gui = gui;
        this.field = new Field(width, height);
        this.scoring = new Scoring();

        updateScore();

        this.gui.setActionHandler(figureController);
        this.gui.setStatusHandler(gameController);
        this.gui.setStatus(status);

        gui.setVisible(true);


        try {
            currentFig = field.getNewFigure();
        } catch (GameOverException e) {
            e.printStackTrace();
        }
        gui.drawBlocks(currentFig.getBlocks());

    }

    private void figureLanded() {
        try {
            currentFig = field.depositFigure(currentFig);
        } catch (GameOverException e) {
            System.out.println("G A M E   O V E R");
            System.out.println("Score: " + scoring.getScore() + " Level: " + scoring.getLevel() + " Highscore: " + scoring.getHighScore());

            status = Status.OVER;
            gui.setStatus(status);
            gui.clearBlocks(currentFig.getBlocks());
            gui.clearBlocks(field.getBlocks());
            field.clear();
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
        public void moveLeft() {
            perform(f -> f.translate(-1, 0), f -> f.translate(1, 0));
        }

        @Override
        public void moveRight() {
            perform(f -> f.translate(1, 0), f -> f.translate(-1, 0));
        }

        @Override
        public void rotateLeft() {
            perform(f -> f.rotate(-1), f -> f.rotate(1));
        }

        @Override
        public void rotateRight() {
            perform(f -> f.rotate(1), f -> f.rotate(-1));
        }

        @Override
        public void moveDown() {
            perform(
                    f -> f.translate(0, -1),
                    f -> {
                        f.translate(0, 1);
                        figureLanded();
                    }
            );
        }

        @Override
        public void drop() {
            boolean landed = false;
            while (!landed) {
                try {
                    moveDown();
                } catch (CollisionException e) {
                    landed = true;
                }
            }
        }

        public void perform(Movement M, Movement N) throws CollisionException {
            M.make(currentFig);

            if (field.isColliding(currentFig)) {
                N.make(currentFig);
                throw new CollisionException();
            }

            gui.repaint();
        }
    }

    public class GameController implements StatusHandler {
        @Override
        public void changeStatus() throws Exception {
            switch (status) {
                case READY:
                    status = Status.RUNNING;
                    break;
                case RUNNING:
                    t = null;
                    status = Status.PAUSED;
                    break;
                case OVER:
                    status = Status.READY;
                    break;
                case PAUSED:
                    status = Status.RUNNING;
                    break;
            }
            gui.setStatus(status);
            System.out.println(status);
        }
    }
}
