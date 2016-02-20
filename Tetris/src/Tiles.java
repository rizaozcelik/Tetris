import java.awt.Color;
import java.util.ArrayList;

import drawing_framework.Animatable;
import drawing_framework.AnimationCanvas;
/**
 * 
 * Class responsible for the movement of tiles,
 * game level, and track of the score.
 * 
 * @author Riza Ozcelik
 *
 */
public class Tiles implements Animatable {

	private static int deletedLine = 0;
	private static int level = 1;
	private static int tileSpeed = 10;
	private static int point;
	private boolean landed;
	protected int counter;
	AnimationCanvas canvas = new AnimationCanvas();
	Unit uA, uB, uC, uR;
	Direction dir;
	ArrayList<Unit> tile = new ArrayList<Unit>();
	/**
	 * Tile constructor with no parameter. 
	 * It is shaped by subclasses.
	 */
	public Tiles() {
		dir = Direction.DOWN;

	}
	
	/**
	 * Method that returns tileSpeed.
	 * @return tileSpeed as integer.
	 */
	public static int getTileSpeed() {
		return tileSpeed;
	}
	/**
	 * Method that sets tileSpeed.
	 * @param tileSpeed : tileSpeed to be set.
	 */
	public static void setTileSpeed(int tileSpeed) {
		Tiles.tileSpeed = tileSpeed;
	}
	
	/**
	 * Method to set level of the game.
	 * @param level = level to be set.
	 */
	public static void setLevel(int level) {
		Tiles.level = level;
	}
	
	/**
	 * Method to set point
	 * @param point : point to be set.
	 */
	public static void setPoint(int point) {
		Tiles.point = point;
	}
	/**
	 * Method that returns point.
	 * @return point as integer.
	 */
	public static int getPoint() {
		return point;
	}
	/**
	 * Method that return Direction of the tile.
	 * @return direction as enumeration.
	 */
	public Direction getDirection() {
		return dir;
	}
	/**
	 * Method to set direction of the tile.
	 * @param dir : Direction to be set.
	 */
	public void setDirection(Direction dir) {
		this.dir = dir;
	}
	/**
	 * Method that is responsible for the drawing of falling tile.
	 * It draws the tile bu traversing it unit by unit.
	 * @param canvas : Canvas to be drawn.
	 */
	@Override
	public void draw(AnimationCanvas canvas) {
		for (Unit unit : tile) {
			unit.draw(canvas);
		}
	}
	/**
	 * Method that is responsible for the drawing of the piece that fell before.
	 * It draws the by traversing the boolean matrix (table) of TetrisGame
	 * @param canvas : Canvas to be drawn.
	 */
	public void drawAll(AnimationCanvas canvas) {
		for (int i = 0; i < canvas.getGridHeight(); i++) {
			for (int j = 0; j < canvas.getGridWidth(); j++) {
				if (TetrisGame.getTable(i, j)) {
					TetrisGame.gameCanvas.fillGridSquare(j, i, Color.RED);
				}
			}
		}
	}
	/**
	 * Method that returns biggest x value of the units.
	 * @return : Biggest x value of the units of the tile.
	 */
	public int getMaxX() {
		int max = 0;
		for (Unit unit : tile) {
			if (unit.getX() > max)
				max = unit.getX();
		}
		return max;
	}
	/**
	 * Method that returns smallest x value of the units.
	 * @return : Smallest x value of the units of the tile.
	 */
	public int getMinX() {
		int min = canvas.getGridWidth();
		for (Unit unit : tile) {
			if (unit.getX() < min)
				min = unit.getX();
		}
		return min;
	}
	/**
	 * Method that returns smallest y value of the units.
	 * @return : Smallest y value of the units of the tile.
	 */
	public int getMinY() {
		int min = canvas.getGridWidth();
		for (Unit unit : tile) {
			if (unit.getY() < min)
				min = unit.getY();
		}
		return min;
	}
	/**
	 * Method that checks if tile can move in the right direction.
	 * @return : If tile can go to the right.
	 */
	public boolean canMoveRight() {
		if ((getMaxX() < canvas.getGridWidth() - 1) && !landed
				&& !control(1, 0)) {
			setDirection(Direction.DOWN);
			return true;
		}
		return false;
	}
	/**
	 * Method that checks if tile can move in the left direction.
	 * @return : If tile can go to the left.
	 */
	public boolean canMoveLeft() {
		if ((getMinX() > 0 && !landed && !control(-1, 0))) {
			setDirection(Direction.DOWN);
			return true;
		}
		return false;
	}
	/**
	 * Method that checks if tile can move downwards.
	 * If tile cannot go down anymore, it calls landOp method.
	 * @return : If tile can fall.
	 */
	public boolean canMoveDown() {
		if ((getMinY() > 0)) {
			setDirection(Direction.DOWN);
			if (control(0, -1)) {
				landOp();
				return false;
			}
			return true;
		}
		if (getMinY() == 0) {
			landOp();
			return false;
		}
		return false;
	}
	/**
	 * Method that checks if another tile occupies the destination.
	 * It does it by traversing the TetrisGame's matrix(table).
	 * The direction is set by parameters.
	 * @param i : Downwards movement.
	 * @param j : Rightward movement.
	 * @return : If another unit occupies destination of the tile.
	 */
	public boolean control(int i, int j) {
		for (Unit unit : tile) {
			if (TetrisGame.getTable(unit.getY() + j, unit.getX() + i)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Method that is responsible for the operations after the tile fell. 
	 * It sets the the position of the tile true in the matrix.
	 * Creates a new piece and add it to the game canvas.
	 */
	public void landOp() {
		for (Unit unit : tile) {
			TetrisGame.setTable(unit.getY(), unit.getX(), true);
		}
		Tiles temp = TetrisGame.createTile();
		tile = new ArrayList<Unit>();
		TetrisGame.tile = temp;
		TetrisGame.gameCanvas.addObject(temp);
	}
	/**
	 * Method that is mainly responsible for the movement of a tile.
	 * It checks if the tile can move in the given direction and 
	 * moves is if it can. 
	 * It also prints score and level on canvas. Calls checkTable and drawAll methods.
	 */
	@Override
	public void move(AnimationCanvas canvas) {
		TetrisGame.gameCanvas.drawText("Score : " + point, 0, 19,
				Color.MAGENTA.darker());
		TetrisGame.gameCanvas.drawText("Level : " + level, 0, 18,
				Color.MAGENTA.darker());
		checkTable();
		drawAll(canvas);
		TetrisGame.checkGameOver();
		counter++;
		if (counter % tileSpeed == 0) {
			if (getDirection() == Direction.RIGHT) {
				if (canMoveRight()) {
					for (Unit unit : tile) {
						unit.moveRight(canvas);
					}
				}
			} else if (getDirection() == Direction.LEFT) {
				if (canMoveLeft()) {
					for (Unit unit : tile) {
						unit.moveLeft(canvas);
					}
				}

			}
			if (canMoveDown()) {
				for (Unit unit : tile) {
					unit.moveDown(canvas);
				}
			}

		}
	}
	/**
	 * Method that updates matrix by starting from the given parameter.
	 * It copies the value of upper line to the current.
	 * It also increments deletedLine which helps the calculation of score.
	 * @param x : Line that updating starts.
	 */
	public void updateTable(int x) {
		point += level * 100;
		deletedLine++;
		if (deletedLine % 10 == 0 && tileSpeed > 1) {
			tileSpeed--;
			level++;
		}
		for (int i = x; i < canvas.getGridHeight() - 1; i++) {
			for (int j = 0; j < canvas.getGridWidth(); j++) {
				TetrisGame.setTable(i, j, TetrisGame.getTable(i + 1, j));
			}
		}
	}
	/**
	 * Method that checks if there is any line to be removed.
	 * It matrix has line with full of trues. It removes it.
	 * And calls updateMatrix method.
	 */
	public void checkTable() {
		for (int i = 0; i < canvas.getGridHeight(); i++) {
			for (int j = 0; j < canvas.getGridWidth(); j++) {
				if (TetrisGame.getTable(i, j) == false) {
					break;
				}
				if (j == canvas.getGridWidth() - 1) {
					updateTable(i);
					break;
				}
			}
		}
	}
	/**
	 * Method that checks if the tile is rotatable.
	 * If the destination is filled by another unit or out of limits it returns false.
	 * @return : If the tile is rotatable.
	 */
	
	public boolean rotatable() {
		int orx = 0;
		int ory = 0;
		orx = tile.get(3).getX();
		ory = tile.get(3).getY();
		for (Unit unit : tile) {
			int tempx = unit.getX() - orx;
			int tempy = unit.getY() - ory;
			int a = (orx + (int) (-(tempy)));
			int b = (ory + (int) ((tempx)));
			if (!(a < canvas.getGridWidth() && a > -1
					&& b < canvas.getGridHeight() && b > -1)) {
				return false;
			}
			if (TetrisGame.getTable(b, a)) {
				return false;
			}

		}
		return true;
	}
	/**
	 * Method that rotates the tile if it is rotatable.
	 * @param canvas : Canvas to be drawn.
	 */
	public void rotate(AnimationCanvas canvas) {
		if (rotatable()) {
			int orx = 0;
			int ory = 0;
			orx = tile.get(3).getX();
			ory = tile.get(3).getY();
			for (Unit unit : tile) {
				int tempx = unit.getX() - orx;
				int tempy = unit.getY() - ory;
				int a = (orx + (int) (-(tempy)));
				int b = (ory + (int) ((tempx)));
				unit.setX(a);
				unit.setY(b);
			}
		}
	}
}
