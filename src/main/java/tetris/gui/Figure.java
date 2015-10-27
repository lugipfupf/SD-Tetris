package tetris.gui;

/**
 * Created by highway on 27/10/15.
 */
public class Figure {
    private Block[] blocks;
    private int color;
    private int x, y;


    public Figure(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int dx, int dy) {

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
