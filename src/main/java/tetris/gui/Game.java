package tetris.gui;

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
        currentFig = getNewBlock();
        gui.drawBlocks(currentFig.getBlocks());


        while (true) {
            ActionEvent event = gui.waitEvent();

            switch (event) {
                case DROP:
                    System.out.println("DROP");
                    break;
                case MOVE_DOWN:
                    System.out.println("move DOWN");
                    currentFig.move(0, -1);
                    break;
                case MOVE_LEFT:
                    System.out.println("move LEFT");
                    currentFig.move(-1, 0);
                    break;
                case MOVE_RIGHT:
                    System.out.println("move RIGHT");
                    currentFig.move(1, 0);
                    break;
                case ROTATE:
                    System.out.println("ROTATE");
                    break;
            }

            gui.drawBlocks(currentFig.getBlocks());
        }
    }

    private Figure getNewBlock() {
        return new Figure(0, 0);
    }
}
