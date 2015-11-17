package tetris.figure;

/**
 * Created by highway on 28/10/15.
 */
public class Z extends Figure {
    public Z(int x, int y) {
        super(x, y);

        color = 6;

        blocks[0] = new Block(x -1, y, color);
        blocks[1] = new Block(x, y, color);
        blocks[2] = new Block(x, y - 1, color);
        blocks[3] = new Block(x + 1, y - 1, color);
    }
}
