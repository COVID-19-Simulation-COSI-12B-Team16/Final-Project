import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;

public class Display{
    public static final int CELL_DISPLAY_PIXEL_WIDTH = 10;
    public static final int CELL_DISPLAY_PIXEL_HEIGHT = 10;

    private String title;
    private int height;
    private int width;

    private JFrame frame;
    private Canvas canvas;

    public Display(String _title, int _height, int _width) {
        this.title = _title;
        this.height = _height;
        this.width = _width;
        createDisplay();
    }

    /**
     * Given all cells on the board, render to the canvas
     */
    public void render(Cell[][] cells) {
        BufferStrategy bs = canvas.getBufferStrategy();
        Graphics graphcis = bs.getDrawGraphics();
        // Draw the background in white
        graphcis.setColor(Color.WHITE);
        graphcis.fillRect(0, 0, width, height);
        // Draw each cell to the canvas
        graphcis.setColor(Color.BLACK);
        for (Cell[] row : cells) {
            for (Cell cell: row) {
                if (!cell.isAlive()) continue;
                // Convert logical_x/y into pixel_x/y
                int pixel_x = cell.x * CELL_DISPLAY_PIXEL_WIDTH;
                int pixel_y = cell.y * CELL_DISPLAY_PIXEL_HEIGHT;
                graphcis.fillRect(pixel_x, pixel_y, CELL_DISPLAY_PIXEL_WIDTH, CELL_DISPLAY_PIXEL_HEIGHT);
            }
        }
        bs.show();
        graphcis.dispose();
    }

    /**
     * Create frame and canvas, using width and height
     */
    private void createDisplay() {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        frame.add(canvas);
        frame.pack();
        canvas.createBufferStrategy(3);
        
    }
}