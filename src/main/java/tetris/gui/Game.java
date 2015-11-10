package tetris.gui;

import tetris.figure.Block;
import tetris.figure.Figure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by highway on 27/10/15.
 */
public class Game implements ActionHandler {
    private GUI gui;
    private Figure currentFig;
    private ArrayList<Block> blocks = new ArrayList<Block>(Tetris.WIDTH * Tetris.HEIGHT);
    private int width;
    private int height;

    private HashMap<int[], Boolean> fieldStatus = new HashMap<>();

    public Game(GUI gui, int width, int height) {
        this.gui = gui;
        this.width = width;
        this.height = height;
        this.gui.setActionHandler(this);

        gui.setVisible(true);

        currentFig = Figure.getFigure(width / 2, height - 1);
        gui.drawBlocks(currentFig.getBlocks());
    }

    private boolean isColliding(Figure fig) {
        for (Block drawn : blocks) {
            for (Block checker : fig.getBlocks()) {
                if (checker.x == drawn.x && checker.y == drawn.y) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isBorder(Figure fig) {
        for (Block b : fig.getBlocks()) {
            if (b.x < 0 || b.y < 0) {
                return true;
            }

            if (b.x > Tetris.WIDTH -1) {
                return true;
            }
        }

        return false;
    }


    @Override
    public void moveDown() {
        currentFig.translate(0, -1);

        if (isColliding(currentFig) || isBorder(currentFig)) {
            currentFig.translate(0, 1);
        }
    }

    @Override
    public void moveLeft() {
        currentFig.translate(-1, 0);
        if (isColliding(currentFig) || isBorder(currentFig)) {
            currentFig.translate(1, 0);
        }
    }

    @Override
    public void moveRight() {
        currentFig.translate(1, 0);

        if (isColliding(currentFig) || isBorder(currentFig)) {
            currentFig.translate(-1, 0);
        }
    }

    @Override
    public void rotateLeft() {
        currentFig.rotate(-1);

        if (isColliding(currentFig) || isBorder(currentFig)) {
            currentFig.rotate(1);
        }
    }

    @Override
    public void rotateRight() {
        currentFig.rotate(1);
        if (isColliding(currentFig) || isBorder(currentFig)) {
            currentFig.rotate(-1);
        }
    }

    @Override
    public void drop() {
        while ( ! isColliding(currentFig) && ! isBorder(currentFig)) {
            currentFig.translate(0, -1);
        }
        currentFig.translate(0, 1);


        blocks.addAll(Arrays.asList(currentFig.getBlocks()));

        gui.clearBlocks(currentFig.getBlocks());
        currentFig = Figure.getFigure(width / 2, height - 1);
        gui.drawBlocks(currentFig.getBlocks());
        gui.drawBlocks(blocks);
    }
}
