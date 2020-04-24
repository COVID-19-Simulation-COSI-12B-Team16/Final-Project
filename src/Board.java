import java.util.Random;

public class Board {
    private int row;
    private int col;
    private Cell[][] cells;

    private Random rand = new Random();
    private final double initialAliveProbability = 0.3;
    private boolean stop;

    private int tick = 0;

    Board(int _row, int _col){
        row = _row;
        col = _col;
        cells = new Cell[row][col];
    }

    /**
     * Initialize with random cells, alive or dead
     */
    private void init(){
        for(int i = 0; i < cells.length; i ++){
            for(int j = 0; j < cells[0].length; j ++){
                cells[i][j] = new Cell(rand.nextDouble() < initialAliveProbability);
            }
        }
    }

    /**
     * Start clock
     */
    void start(){
        while(!stop){
            moveToNextGeneration();
            draw();
            tick ++;
        }
    }

    /**
     * Stop clock
     */
    void stop(){
        stop = true;
    }

    void resume(){
        stop = false;
        start();
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
        for(int i = 0; i < cells.length; i ++){
            for(int j = 0; j < cells[0].length; j ++){
                cells[i][j].updateNextAlive(cells);
            }
        }

        for(int i = 0; i < cells.length; i ++){
            for(int j = 0; j < cells[0].length; j ++){
                cells[i][j].nextGeneration();
            }
        }
    }
}
