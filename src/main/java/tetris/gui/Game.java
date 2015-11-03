package tetris.gui;

import tetris.figure.Figure;

/**
 * Created by highway on 27/10/15.
 */
public class Game {
    private GUI gui;
    private Figure currentFig;

    public Game(GUI gui) {
        this.gui = gui;
        gui.setVisible(true);
    }

    public void run() {
        // get initial figure
        currentFig = Figure.getFigure(0, 0);
        gui.drawBlocks(currentFig.getBlocks());


        while (true) {
            ActionEvent event = gui.waitEvent();

            switch (event) {
                case DROP:
                    break;
                case MOVE_DOWN:
                    currentFig.translate(0, -1);
                    break;
                case MOVE_LEFT:
                    currentFig.translate(-1, 0);
                    break;
                case MOVE_RIGHT:
                    currentFig.translate(1, 0);
                    break;
                case ROTATE:
                    currentFig.rotate();
                    break;
            }

            gui.drawBlocks(currentFig.getBlocks());
        }
    }
}
