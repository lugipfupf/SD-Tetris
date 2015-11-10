package tetris.gui;

import tetris.figure.Block;
import tetris.figure.Figure;

import java.util.ArrayList;

/**
 * Created by highway on 27/10/15.
 */
public class Game implements ActionHandler {
    private GUI gui;
    private Figure currentFig;
    private ArrayList<Block> blocks = new ArrayList<Block>(Tetris.WIDTH * Tetris.HEIGHT);
    private int width;
    private int height;

    public Game(GUI gui, int width, int height) {
        this.gui = gui;
        this.width = width;
        this.height = height;
        this.gui.setActionHandler(this);

        gui.setVisible(true);

        currentFig = Figure.getFigure(width / 2, height - 1);
        gui.drawBlocks(currentFig.getBlocks());
    }

    private boolean checkMovement(int xFrom, int xTo, int yFrom, int yTo) {
        boolean ret = true;
        for (Block b : blocks) {

        }

        return ret;
    }


    @Override
    public void moveDown() {
        currentFig.translate(0, -1);
    }

    @Override
    public void moveLeft() {
        currentFig.translate(-1, 0);
    }

    @Override
    public void moveRight() {
        currentFig.translate(1, 0);
    }

    @Override
    public void rotateLeft() {
        currentFig.rotate(-1);
    }

    @Override
    public void rotateRight() {
        currentFig.rotate(1);
    }

    @Override
    public void drop() {
        for (Block block : currentFig.getBlocks()) {
            blocks.add(block);
        }

        gui.clearBlocks(currentFig.getBlocks());
        currentFig = Figure.getFigure(width / 2, height - 1);
        gui.drawBlocks(currentFig.getBlocks());
        gui.drawBlocks(blocks);
    }
}
