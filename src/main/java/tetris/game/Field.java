package tetris.game;

import tetris.Tetris;
import tetris.figure.Block;
import tetris.figure.Figure;

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

    public void removeFullRows() {
        for (int i = 0; i < height; i++) {
            if (isRowFull(field[i])) {
                System.out.println("Line " + i + " is full");
            }
        }
    }

    private boolean isRowFull(Block[] line) {
        for (int i = 0; i < width; i++) {
            if (line[i] == null) {
                return false;
            }
        }

        return true;
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
