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
    protected Orientation orientation = Orientation.NORTH;

    private static Random r = new Random();

    public Figure(int x, int y) {
        blocks = new Block[Tetris.MAX_BLOCKS];

        this.x = x;
        this.y = y;
    }

    public static Figure getFigure(int x, int y) {
        Figure f;

        switch (r.nextInt(7)) {
            case 0:
                f = new T(x, y);
                break;
            case 1:
                f = new O(x, y);
                break;
            case 2:
                f = new I(x, y);
                break;
            case 3:
                f = new J(x, y);
                break;
            case 4:
                f = new L(x, y);
                break;
            case 5:
                f = new Z(x, y);
                break;
            default:
                f = new S(x, y);
                break;
        }

        return f;
    }

    public void move(int dx, int dy) {
        // collision detection

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
