import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import drawing_framework.AnimationCanvas;
import drawing_framework.GUI;

enum Direction {
	DOWN, RIGHT, LEFT
};
/**
 * Class which game goes on. This class collects user input via KeyListener.
 * And it leads the tiles according to this knowledge. It is also responsible for
 * another processes about game like creating piece,restart and pause.
 * @author Riza Ozcelik
 *
 */
public class TetrisGame implements KeyListener {

	protected boolean stop;
	static Tiles tile;
	static Tiles nextTile;
	static AnimationCanvas gameCanvas;
	static AnimationCanvas nextOne;
	private static boolean[][] table = new boolean[25][10];
	private static final int FRAME_RATE = 50;
	
	/**
	 * Constructor that creates a new game.
	 * @param gui : Gui to be used during gameplay.
 	 */
	public TetrisGame(GUI gui) {
//		GUI gui2 = new GUI();
		gameCanvas = new AnimationCanvas(200, 400, 20, FRAME_RATE);
//		nextOne = new AnimationCanvas(200,400,20,50);
//		nextOne.drawGrid();
//		Tiles asd = createTile();
//		nextOne.addObject(asd);
//		gameCanvas.drawGrid();
		gui.addCanvas(gameCanvas);
		gameCanvas.addKeyListener(this);
		gameCanvas.setFocusable(true);
//		gui.addCanvas(nextOne);
//		nextOne.start();
		tile = createTile();
		gameCanvas.addObject(tile);
		gameCanvas.start();
	}
	
	/**
	 * Method that return frame rate of the game.
	 * @return : Frame rate as integer.
	 */
	public static int getFrameRate() {
		return FRAME_RATE;
	}
	
	/**
	 * Method that returns the value of the given coordinates in the table.
	 * @param row : Targeted row of matrix.
	 * @param col : Targeted column of matrix.
	 * @return : boolean value of the cell.
	 */
	public static boolean getTable(int row, int col) {
		return table[row][col];
	}
	
	/**
	 * Method that sets the value of the given coordinates in the table.
	 * @param row : Targeted row of matrix.
	 * @param col : Targeted column of matrix.
	 * @param bool : boolean value to be set.
	 */
	public static void setTable(int row, int col, boolean bool) {
		table[row][col] = bool;
	}
//	
//	public void addAI(SmartTetris AI){
//		this.AI = AI;
//		gameCanvas.removeKeyListener(this);
////		tile = (Tiles)tile;
////		tile = (SmartTetris)tile;
//	}
	/**
	 * Method that checks if game is over. If so it stops the game.
	 */
	public static void checkGameOver() {
		for (int i = 0; i < gameCanvas.getGridWidth(); i++) {
			if (getTable(20, i) == true) {
				gameCanvas.stop();
				gameCanvas.drawText("Game Over!", 3, 10, Color.BLACK.darker());
				gameCanvas.drawText("Press 'R' to restart", 2, 9,
						Color.BLACK.darker());
				gameCanvas.drawText("Your score is :" + Tiles.getPoint(), 2, 8,
						Color.BLACK.darker());
			}
		}
	}
	
	/**
	 * Method that finishes the game and resetting the old data.
	 */
	public static void kill() {
		gameCanvas.stop();
		resetTable();
		Tiles.setPoint(0);
		Tiles.setLevel(1);
		Tiles.setTileSpeed(10);
	}
	/**
	 * Method that resets boolean matrix.
	 */
	public static void resetTable() {
		for (int i = 0; i < 25; i++) {
			for (int j = 0; j < 10; j++) {
				setTable(i, j, false);
			}
		}
	}
	/**
	 * Method that creates a new tile randomly.
	 * @return : new Tile
	 */
	public static Tiles createTile() {
		int x = (int) Math.ceil(Math.random() * 7);
//		x  = 2;
		if (x == 1) {
			return new O();
		} else if (x == 2) {
			return new I();
		} else if (x == 3) {
			return new T();
		} else if (x == 4) {
			return new L();
		} else if (x == 5) {
			return new J();
		} else if (x == 6) {
			return new S();
		} else {
			return new Z();
		}
	}
	/**
	 * Method that gets the keyboard inputs from the user,
	 * and directs the game according to this data.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (!stop) {
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				tile.setDirection(Direction.DOWN);
				for (int i = 0; i < 10; i++) {
					tile.move(gameCanvas);
				}
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				if (tile.canMoveLeft()){
					if(tile.getDirection() == Direction.LEFT){
						for (int i = 0; i < 500; i++) {
							tile.move(gameCanvas);
						}
					}
					tile.setDirection(Direction.LEFT);
				}
					
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if (tile.canMoveRight()){
					if(tile.getDirection() == Direction.RIGHT){
						for (int i = 0; i < 500; i++) {
							tile.move(gameCanvas);
						}
					}
					tile.setDirection(Direction.RIGHT);
				}
					
			} else if (e.getKeyCode() == KeyEvent.VK_UP) {
				tile.rotate(gameCanvas);
			} else if (e.getKeyCode() == KeyEvent.VK_R) {
				kill();
				GUI gui = new GUI();
				new TetrisGame(gui);
			} else if(e.getKeyCode() == KeyEvent.VK_ENTER){
				while(tile.canMoveDown()){
					tile.move(gameCanvas);
				}
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_P) {
			stop = !stop;
			if (stop) {
				gameCanvas.stop();
			} else
				gameCanvas.start();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	
}
