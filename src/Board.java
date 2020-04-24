import java.util.Random;

public class Board implements Runnable{
    private int row;
    private int col;
    private Cell[][] cells;

    private Random rand = new Random();
    private final double initialAliveProbability = 0.5;
    private boolean stop;

    private int tick = 0;

    private Display display;

    private Thread thread;

    Board(int _row, int _col){
        row = _row;
        col = _col;
        cells = new Cell[row][col];
        display = new Display("New Life Game", _col * Display.CELL_DISPLAY_PIXEL_HEIGHT, _row * Display.CELL_DISPLAY_PIXEL_WIDTH);
    }

    @Override
    public void run() {
        init();
        while (!stop) {
            draw();
            moveToNextGeneration();
            tick ++;
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (terminate()) stop();
        }
    }

    /**
     * Initialize with random cells, alive or dead
     */
    private void init(){
        for(int i = 0; i < cells.length; i ++){
            for(int j = 0; j < cells[0].length; j ++){
                int lowerBound = (int)rand.nextDouble() * (6 - 2 + 1) + 2;
                cells[i][j] = new CustomizedCell(
                        rand.nextDouble() < initialAliveProbability,
                        i, j, lowerBound, lowerBound + 1
                );
            }
        }
    }

    /**
     * Start clock
     */
    synchronized void start(){
        stop = false;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Stop clock
     */
    synchronized void stop(){
        if (stop) return; // Since we are not using a lock on 'stop' here, always do this to prevent messy stuff
        stop = true;
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    boolean terminate(){
        return tick > 50;
    }

    void resume(){
        stop = false;
        start();
    }

    /**
     * Render the board on canvas
     */
    private void draw(){
        display.render(cells);
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

    private void print(){
        System.out.printf("----Tick number %d----\n", tick);
        for(int i = 0; i < cells.length; i ++){
            for(int j = 0; j < cells[0].length; j ++){
                if(cells[i][j].isAlive()){
                    System.out.print("0");
                } else {
                    System.out.print("X");
                }
            }
            System.out.println();
        }
        System.out.println("-------");
    }

    public static void main(String[] args) {
        Board b = new Board(100, 100);
        // b.init();
        b.start();
    }
}
