package tetris.game;

import tetris.Tetris;
import tetris.figure.Block;
import tetris.figure.CollisionException;
import tetris.figure.Figure;
import tetris.gui.GUI;

import java.util.ArrayList;

/**
 * Created by highway on 27/10/15.
 */
public class Game {
    private GUI gui;
    private Figure currentFig;
    private Field field;
    private ArrayList<Block> blocks = new ArrayList<Block>(Tetris.WIDTH * Tetris.HEIGHT);

    public Game(GUI gui, int width, int height) {
        this.gui = gui;
        this.field = new Field(width, height);
        FigureController controller = new FigureController();

        this.gui.setActionHandler(controller);

        gui.setVisible(true);

        currentFig = field.getNewFigure();
        gui.drawBlocks(currentFig.getBlocks());

        Timer t = new Timer();
        t.setActionHandler(controller);
        t.run();
    }

    public class FigureController implements ActionHandler {
        @Override
        public void moveDown() {
            currentFig.translate(0, -1);
            gui.repaint();

            if (field.isColliding(currentFig)) {
                currentFig.translate(0, 1);
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

            currentFig = field.depositFigure(currentFig);

            gui.clearBlocks(currentFig.getBlocks());
            gui.drawBlocks(currentFig.getBlocks());
            gui.drawBlocks(blocks);
            gui.repaint();
        }
    }
}