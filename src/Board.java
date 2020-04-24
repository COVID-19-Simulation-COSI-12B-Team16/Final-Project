import java.util.List;

public class Board {
    private int row;
    private int col;
    private Cell[][] cells;

    Board(int _row, int _col){
        row = _row;
        col = _col;
        cells = new Cell[row][col];
    }

    /**
     * Initialize with random cells, alive or dead
     */
    private void init(){
    }

    /**
     * Start clock
     */
    void start(){

    }

    /**
     * Stop clock
     */
    void stop(){

    }

    /**
     * Render the board on canvas
     */
    private void draw(){

    }

    /**
     * Update cells on the board to next generation
     */
    private void moveToNextGeneration(){

    }
}
