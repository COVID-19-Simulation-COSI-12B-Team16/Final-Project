public class Cell {
    private boolean isAlive;
    private boolean nextAlive;

    private int x;
    private int y;

    private int lowerBound = 3;
    private int higherBound = 3;

    Cell(boolean _isAlive){
        isAlive = _isAlive;
    }

    /**
     * Update nextAlive attribute according to surrounding cells' conditions
     */
    void updateNextAlive(Cell[][] cells){
        int aliveCount = 0;
        for(int i = x - 1; i <= x + 1; i ++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i < 0 || i >= cells.length || j < 0 || j >= cells[0].length) continue;
                if (cells[i][j].isAlive()) aliveCount++;
            }
        }

        nextAlive = aliveCount <= higherBound && aliveCount >= lowerBound;

    }

    /**
     * Update the cell to next generation according to nextAlive attribute derived from above method
     */
    void nextGeneration(){
        isAlive = nextAlive;
    }

    boolean isAlive(){
        return isAlive;
    }
}
