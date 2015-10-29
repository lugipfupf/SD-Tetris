package tetris.figure;

import tetris.gui.Tetris;

import java.util.Random;

/**
 * Created by highway on 28/10/15.
 */
public abstract class Figure {
    protected Block[] blocks;
    protected int color;
    protected int x, y;

    private static Random r = new Random();

    private FigureOrientation orientation = FigureOrientation.NORTH;

    public Figure(int x, int y) {
        blocks = new Block[Tetris.MAX_BLOCKS];

        this.x = x;
        this.y = y;
    }

    public static Figure getFigure(int x, int y) {
        Figure f;

        switch (r.nextInt(7)) {
            case 0:
                f = new FigT(x, y);
                break;
            case 1:
                f = new FigCube(x, y);
                break;
            case 2:
                f = new FigI(x, y);
                break;
            case 3:
                f = new FigLLeft(x, y);
                break;
            case 4:
                f = new FigLRight(x, y);
                break;
            case 5:
                f = new FigZLeft(x, y);
                break;
            default:
                f = new FigZRight(x, y);
                break;
        }

        return f;
    }

    public void move(int dx, int dy) {
        for (int i = 0; i < Tetris.MAX_BLOCKS; i++) {
            blocks[i].x += dx;
            blocks[i].y += dy;
        }
    }

    public Block[] getBlocks() {
        return blocks;
    }

    public int getColor() {
        return color;
    }

    public abstract void rotate();

}
