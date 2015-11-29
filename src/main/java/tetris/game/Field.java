package tetris.game;

import tetris.Tetris;
import tetris.figure.Block;
import tetris.figure.Figure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by highway on 17/11/15.
 */
public class Field {
    int width, height;

    Figure currentFig;

    private Block[][] field;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;

        field = new Block[height][width];
        System.out.println(field.length + " x " + field[0].length);
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

    public List<Block> removeFullRows() {
        ArrayList<Block> blocksToRemove = new ArrayList<>();

        boolean[] rowsToRemove = new boolean[height];
        int count = 0;

        for (int i = height - 1; i >= 0; i--) {
            rowsToRemove[i] = isRowFull(field[i]);
        }

        for (int i = height - 1; i >= 0; i--) {
            if (rowsToRemove[i]) {
                count++;
                System.out.println("remove row " + i);

                blocksToRemove.addAll(Arrays.asList(field[i]));
                clearRow(field[i]);
            }
        }

        for (int i = height -1; i >= 0; i--) {
            if (rowsToRemove[i]) {
                reposition(i, count);
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

    public Figure depositFigure(Figure figure) {
        for (Block b : figure.getBlocks()) {
            field[b.y][b.x] = b;
        }

        return getNewFigure();
    }

    public Figure getNewFigure() {
        currentFig = Figure.getFigure(width / 2, height - 1);
        return currentFig;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
