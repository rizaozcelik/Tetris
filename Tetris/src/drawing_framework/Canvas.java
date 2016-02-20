package drawing_framework;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * Drawing canvas (usable as a panel).
 * @author cantunca
 * @version 1.1
 */
public class Canvas extends JPanel {

	private static final long serialVersionUID = 2L;

	private final int gamePanelWidth;
	private final int gamePanelHeight;
	private final int gridSquareSize;

	private BufferedImage canvasImage;

	/**
	 * No argument constructor.
	 * The default size of the canvas is 500 x 500 pixels.
	 * The canvas also contains an initially hidden grid. The default square size of the grid is 20 pixels.
	 */
	public Canvas() {
		super();
		gamePanelWidth = 200;
		gamePanelHeight = 400;
		gridSquareSize = 20;
		canvasImage = new BufferedImage(gamePanelWidth, gamePanelHeight, BufferedImage.TYPE_INT_ARGB);
	}
	
	/**
	 * Constructor for explicitly setting the size and the grid specification of the canvas.
	 * @param width Width of the canvas in terms of pixels.
	 * @param height Height of the canvas in terms of pixels.
	 * @param gridSquareSize Size of the side of an individual grid square in terms of pixels.
	 */
	public Canvas(int width, int height, int gridSquareSize) {
		super();
		gamePanelWidth = width;
		gamePanelHeight = height;
		this.gridSquareSize = gridSquareSize;
		canvasImage = new BufferedImage(gamePanelWidth, gamePanelHeight, BufferedImage.TYPE_INT_ARGB);
	}

	/**
	 * Returns the preferred size of the canvas as a Dimension object.
	 */
	public Dimension getPreferredSize() {
		return new Dimension(gamePanelWidth, gamePanelHeight);
	}

	/**
	 * Paints the contents on the canvas.
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (canvasImage != null) {
			g.drawImage(canvasImage, 0, 0, null);
		}
	}
	
	/**
	 * Returns the width of the canvas in terms of number of grid squares.
	 * @return Total horizontal number of grid squares.
	 */
	public int getGridWidth() {
		return gamePanelWidth/gridSquareSize;
	}
	
	/**
	 * Returns the width of the canvas in terms of number of grid squares.
	 * @return Total vertical number of grid squares.
	 */
	public int getGridHeight() {
		return gamePanelHeight/gridSquareSize;
	}
	
	/**
	 * Converts a number in terms of grid coordinate system to the corresponding pixel (in the horizontal axis).
	 * @param val Number in grid coordinate system.
	 * @return Pixel value associated with the given number
	 */
	private int getPixelX(double val) {
		return (int)(val*gridSquareSize);
	}
	
	/**
	 * Converts a number in terms of grid coordinate system to the corresponding pixel (in the vertical axis).
	 * @param val Number in grid coordinate system.
	 * @return Pixel value associated with the given number
	 */
	private int getPixelY(double val) {
		return (int)(gamePanelHeight - val*gridSquareSize);
	}
	
	/**
	 * Clears the canvas (painting it white). 
	 */
	public void clearCanvas() {
		Graphics2D g = canvasImage.createGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, canvasImage.getWidth(), canvasImage.getHeight());
		g.dispose();
		repaint();
	}
	
	/**
	 * Draws the grid structure on the canvas.
	 */
	public void drawGrid() {
		Graphics2D g = canvasImage.createGraphics();
		g.setColor(Color.LIGHT_GRAY);

		// vertical grid
		for (int i=0; i<getGridWidth(); i++) {
			int lineX = getPixelX(i);
			g.drawLine(lineX, 0, lineX, gamePanelHeight);
		}
		// horizontal grid
		for (int i=0; i<getGridHeight(); i++) {
			int lineY = getPixelY(i);
			g.drawLine(0, lineY, gamePanelWidth, lineY);
		}
		g.dispose();
		repaint();
	}
	
	/**
	 * Draws a line segment on the canvas. The input values are in terms of the grid coordinate system.
	 * @param startX X coordinate of the line start point.
	 * @param startY Y coordinate of the line start point.
	 * @param endX X coordinate of the line end point.
	 * @param endY Y coordinate of the line end point.
	 * @param color Color of the line segment.
	 */
	public void drawLine(double startX, double startY, double endX, double endY, Color color) {
		Graphics2D g = canvasImage.createGraphics();
		g.setColor(color);
		g.drawLine(getPixelX(startX), getPixelY(startY), getPixelX(endX), getPixelY(endY));
		g.dispose();
		repaint();
	}
	
	/**
	 * Fills the given grid square with the given color.
	 * Useful for implementing square block-based visuals.
	 * @param gridX The X coordinate of the grid square (index starts at 0)
	 * @param gridY The Y coordinate of the grid square (index starts at 0)
	 * @param color The color of the drawn square
	 */
	public void fillGridSquare(int gridX, int gridY, Color color) {
		if (gridX < 0 || gridY < 0 || 
				gridX >= gamePanelWidth/gridSquareSize || gridY >= gamePanelHeight/gridSquareSize) {
			return;
		}
		Graphics2D g = canvasImage.createGraphics();
		g.setColor(color);
		int x = getPixelX(gridX)+1;
		int y = getPixelY(gridY)+1-gridSquareSize;
		g.fillRect(x, y, gridSquareSize-1, gridSquareSize-1);
		g.dispose();
		repaint();
	}
	
	/**
	 * Draws a filled rectangle. The input values are in terms of the grid coordinate system.
	 * @param southWestX X coordinate of south west corner
	 * @param southWestY Y coordinate of south west corner
	 * @param northEastX X coordinate of north east corner
	 * @param northEastY Y coordinate of north east corner
	 * @param color Color of the drawn rectangle
	 */
	public void drawRectangle(double southWestX, double southWestY, double northEastX, double northEastY, Color color) {
		Graphics2D g = canvasImage.createGraphics();
		g.setColor(color);
		int x = getPixelX(southWestX);
		int y = getPixelY(northEastY);
		int width = getPixelX(northEastX) - x;
		int height = getPixelY(southWestY) - y;
		g.fillRect(x, y, width, height);
		g.dispose();
		repaint();
	}

	/**
	 * Writes the given text onto the canvas. The input values are in terms of the grid coordinate system.
	 * @param text The string to be written.
	 * @param x X coordinate of the text.
	 * @param y Y coordinate of the text.
	 * @param color Color of the text.
	 */
	public void drawText(String text, double x, double y, Color color) {
		Graphics2D g = canvasImage.createGraphics();
		g.setColor(color);
		g.drawString(text, getPixelX(x), getPixelY(y));
		g.dispose();
		repaint();
	}
}
