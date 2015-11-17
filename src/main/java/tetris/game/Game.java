package tetris.game;

import tetris.Tetris;
import tetris.figure.Block;
import tetris.figure.CollisionException;
import tetris.figure.Figure;
import tetris.gui.GUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by highway on 27/10/15.
 */
public class Game {
    private GUI gui;
    private Figure currentFig;
    private Field field;
    private ArrayList<Block> blocks = new ArrayList<Block>(Tetris.WIDTH * Tetris.HEIGHT);
    private int width;
    private int height;


    public Game(GUI gui, int width, int height) {
        this.gui = gui;
        this.field = new Field(width, height);
        this.width = width;
        this.height = height;
        this.gui.setActionHandler(new FigureController());

        gui.setVisible(true);

        currentFig = field.getNewFigure();
        gui.drawBlocks(currentFig.getBlocks());
    }


    public class FigureController implements ActionHandler {
        @Override
        public void moveDown() {
            currentFig.translate(0, -1);

            if (field.isColliding(currentFig) || field.isBorder(currentFig)) {
                currentFig.translate(0, 1);
            }
        }

        @Override
        public void moveUp() {
            currentFig.translate(0, 1);

            if (field.isColliding(currentFig) || field.isBorder(currentFig)) {
                throw new CollisionException();
            }
        }

        @Override
        public void moveLeft() {
            currentFig.translate(-1, 0);
            if (field.isColliding(currentFig) || field.isBorder(currentFig)) {
                throw new CollisionException();
            }
        }

        @Override
        public void moveRight() {
            currentFig.translate(1, 0);

            if (field.isColliding(currentFig) || field.isBorder(currentFig)) {
                throw new CollisionException();
            }
        }

        @Override
        public void rotateLeft() {
            currentFig.rotate(-1);

            if (field.isColliding(currentFig) || field.isBorder(currentFig)) {
                throw new CollisionException();
            }
        }

        @Override
        public void rotateRight() {
            currentFig.rotate(1);
            if (field.isColliding(currentFig) || field.isBorder(currentFig)) {
                throw new CollisionException();
            }
        }

        @Override
        public void drop() {
            while (!field.isColliding(currentFig) && !field.isBorder(currentFig)) {
                currentFig.translate(0, -1);
            }
            currentFig.translate(0, 1);

            currentFig = field.depositFigure(currentFig);

            gui.clearBlocks(currentFig.getBlocks());
            gui.drawBlocks(currentFig.getBlocks());
            gui.drawBlocks(blocks);
        }
    }
}
