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
    private Status status = Status.READY;
    private FigureController figureController;

    public Game(GUI gui, int width, int height) {
        this.gui = gui;
        this.field = new Field(width, height);
        this.scoring = new Scoring();

        updateScore();

        GameController gameController = new GameController();
        this.gui.setStatusHandler(gameController);
        this.gui.setStatus(status);

        gui.setVisible(true);
    }

    private void figureLanded() {
        try {
            currentFig = field.depositFigure(currentFig);
            field.removeFullRows();
            scoring.update(field.getRowsRemoved());
        } catch (GameOverException e) {
            System.out.println("G A M E   O V E R");
            System.out.println("Score: " + scoring.getScore() + " Level: " + scoring.getLevel() + " Highscore: " + scoring.getHighScore());

            status = Status.OVER;
            gui.setStatus(status);
        }

        updateGui();

    }

    private void updateScore() {


        gui.setLevel(scoring.getLevel());
        gui.setScore(scoring.getScore());
        gui.setHighScore(scoring.getHighScore());
    }

    private void updateGui() {
        updateScore();

        gui.clearAllBlocks();
        gui.drawBlocks(currentFig.getBlocks());
        gui.drawBlocks(field.getBlocks());
        gui.repaint();
    }

    public class FigureController extends Thread implements ActionHandler {
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
            if (status == Status.RUNNING) {
                M.make(currentFig);

                if (field.isColliding(currentFig)) {
                    N.make(currentFig);
                    throw new CollisionException();
                }
                updateGui();
            }
        }

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                if (status == Status.RUNNING) {
                    try {
                        Thread.sleep(1500);
                        try {
                            moveDown();
                        } catch (Exception e) {
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public class GameController implements StatusHandler {
        @Override
        public void changeStatus() throws Exception {
            System.out.println("i am " + status);
            switch (status) {
                case OVER:
                    currentFig = null;
                    field.clear();

                case READY:
                    if (currentFig == null) {
                        try {
                            currentFig = field.getNewFigure();
                        } catch (GameOverException e) {
                            e.printStackTrace();
                        }
                    }
                    figureController = new FigureController();
                    gui.setActionHandler(figureController);
                    figureController.start();
                    updateGui();

                    status = Status.RUNNING;
                    break;
                case RUNNING:
                    status = Status.PAUSED;
                    break;
                case PAUSED:

                    status = Status.RUNNING;
                    break;
            }
            System.out.println("change to " + status);
            gui.setStatus(status);
        }
    }
}
