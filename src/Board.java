import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class Board{
    private int row;
    private int col;
    private Display display;

    private Random rand = new Random();
    private final double initialAliveProbability = 0.05;
    private int tick = 0;
    private Cell[][] cells;

    private static final int DEFAULT_SIZE = 100;

    public static Board parseFromFile(String path){
        Board b = null;
        try {
            Scanner sc = new Scanner(new File(path));
            int rowMax = sc.nextInt();
            int colMax = sc.nextInt();
            b = new Board(rowMax, colMax);
            while(sc.hasNextInt()) {
                int rowCor = sc.nextInt();
                int colCor = sc.nextInt();
                b.cells[rowCor][colCor] = new CustomizedCell(true, rowCor, colCor, 2, 3);
            }

            for(int i = 0; i < b.cells.length; i ++){
                for(int j = 0; j < b.cells[0].length; j ++){
                    if(b.cells[i][j] != null) continue;
                    int lowerBound = (int)b.rand.nextDouble() * (6 - 2 + 1) + 2;
                    b.cells[i][j] = new CustomizedCell(
                            false, i, j, 2, 3
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    public static Board randomInitalize(){
        Board b = new Board(DEFAULT_SIZE, DEFAULT_SIZE);
        for(int i = 0; i < b.cells.length; i ++){
            for(int j = 0; j < b.cells[0].length; j ++){
                int lowerBound = (int)b.rand.nextDouble() * (6 - 2 + 1) + 2;
                b.cells[i][j] = new CustomizedCell(
                        b.rand.nextDouble() < b.initialAliveProbability,
                        i, j, lowerBound, lowerBound + 1
                );
            }
        }
        return b;
    }

    public void display(){
        display.render(cells);
    }

    private Board(int _row, int _col){
        row = _row;
        col = _col;
        cells = new Cell[row][col];
        display = new Display("New Life Game", row * Display.CELL_DISPLAY_PIXEL_HEIGHT, col * Display.CELL_DISPLAY_PIXEL_WIDTH);
    }


    /**
     * Update cells on the board to next generation
     */
    void moveToNextGeneration(){
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

}
