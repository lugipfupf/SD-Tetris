package tetris.game;

import tetris.Tetris;
import tetris.gui.Block;
import tetris.figure.Figure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by highway on 17/11/15.
 */
public class Field {
    int width, height, rowsRemoved;

    Figure currentFig;

    private Block[][] field;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;

        field = new Block[height][width];
    }

    public boolean isColliding(Figure fig) {
        return isCollidingWithBorder(fig) || isCollidingWithBlock(fig);
    }

    private boolean isCollidingWithBlock(Figure fig) {
        for (Block b : fig.getBlocks()) {
            if (b.y < height && field[b.y][b.x] != null) {
                return true;
            }
        }

        return false;
    }

    private boolean isCollidingWithBorder(Figure fig) {
        for (Block b : fig.getBlocks()) {
            if (b.x < 0 || b.y < 0) {
                return true;
            }

            if (b.x > Tetris.WIDTH - 1) {
                return true;
            }
        }

        return false;
    }

    public int getRowsRemoved() {
        return rowsRemoved;
    }

    public List<Block> removeFullRows() {
        ArrayList<Block> blocksToRemove = new ArrayList<>();

        boolean[] rowsToRemove = new boolean[height];
        rowsRemoved = 0;

        for (int i = height - 1; i >= 0; i--) {
            rowsToRemove[i] = isRowFull(field[i]);
        }

        for (int i = height - 1; i >= 0; i--) {
            if (rowsToRemove[i]) {
                rowsRemoved++;
                System.out.println("remove row " + i);

                blocksToRemove.addAll(Arrays.asList(field[i]));
                clearRow(field[i]);
            }
        }

        for (int i = height -1; i >= 0; i--) {
            if (rowsToRemove[i]) {
                reposition(i, rowsRemoved);
                break;
            }
        }

        return blocksToRemove;
    }

    private void reposition(int start, int howmany) {
        for (int i = start + 1; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (field[i][j] != null) {
                    Block b = field[i][j];
                    field[i][j] = null;
                    b.y = b.y - howmany;
                    field[i - howmany][j] = b;
                }
            }
        }
    }

    private void clearRow(Block[] row) {
        for (int i = 0; i < width; i++) {
            row[i] = null;
        }
    }

    private boolean isRowFull(Block[] row) {
        for (int i = 0; i < width; i++) {
            if (row[i] == null) {
                return false;
            }
        }

        return true;
    }

    public List<Block> getBlocks() {
        ArrayList<Block> list = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (field[i][j] != null) {
                    list.add(field[i][j]);
                }
            }
        }

        return list;
    }

    public Figure depositFigure(Figure figure) throws GameOverException {
        for (Block b : figure.getBlocks()) {
            field[b.y][b.x] = b;
        }

        return getNewFigure();
    }

    public Figure getNewFigure() throws GameOverException{
        currentFig = Figure.getFigure(width / 2, height - 1);
        if (isCollidingWithBlock(currentFig)) {
            throw new GameOverException();
        }
        return currentFig;
    }

    public void clear() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                field[i][j] = null;
            }
        }
    }
}
