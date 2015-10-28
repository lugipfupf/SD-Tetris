package tetris.gui;

import java.awt.*;

/**
 * Created by highway on 27/10/15.
 */
public class Figure {
    public static final int MAX_BLOCKS = 4;

    private Block[] blocks;
    private int color;
    private int x, y;


    public Figure(int x, int y) {
        blocks = new Block[MAX_BLOCKS];

        this.x = x;
        this.y = y;
        color = 1;

        for (int i = 0; i <= 3; i++) {
            blocks[i] = new Block(5, Tetris.HEIGHT - i, color);
        }
    }

    public void move(int dx, int dy) {
        for (int i = 0; i < MAX_BLOCKS; i++) {
            blocks[i].x += dx;
            blocks[i].y += dy;
        }
    }

    public void rotate() {

    }

    public Block[] getBlocks() {
        return blocks;
    }

    public int getColor() {
        return color;
    }
}
