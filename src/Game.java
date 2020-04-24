public class Game implements Runnable{
    private Board board;
    private boolean running;
    private Thread thread;
    private int tick;

    public Game(String text) {
        if(text != null) board = Board.parseFromFile(text);
        else board = Board.randomInitalize();
        tick = 0;
    }

    @Override
    public void run() {
        while (running) {
            board.display();
            board.moveToNextGeneration();
            tick ++;
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (terminate()) stop();
        }
    }

    /**
     * Start clock
     */
    synchronized void start(){
        running = true;
        thread = new Thread(this);
        thread.start();
    }


    /**
     * Stop clock
     */
    synchronized void stop(){
        if (!running) return; // Since we are not using a lock on 'stop' here, always do this to prevent messy stuff
        running = false;
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    boolean terminate(){
        return tick > 5000;
    }


    public static void main(String[] args) {
        Game g = new Game(null);
        g.start();
    }

}
