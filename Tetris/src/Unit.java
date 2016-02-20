import java.awt.Color;

import drawing_framework.AnimationCanvas;
/**
 * Class that creates units which constitute tiles.
 * 
 * @author Riza Ozcelik
 *
 */

public class Unit {
	private int x,y;
	
	/**
	 * Constructor that creates a unit with certain x and y values.
	 * @param x : x coordinate of unit.
	 * @param y : y coordinate of unit.
	 */
	public Unit(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	/**
	 * method that returns x coordinate of unit
	 * @return : x coordinate as integer.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Method that set x coordinate of unit
	 * @param x : x value to be set.
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * method that returns y coordinate of unit
	 * @return : y coordinate as integer.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Method that set x coordinate of unit
	 * @param x : x value to be set.
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 *Method that move unit 1 unit rightwards.
	 *@param canvas : Canvas to be drawn on. 
	 */
	public void moveRight(AnimationCanvas canvas) {
		setX(x+1);
	}
	
	/**
	 * Method that move unit 1 unit leftwards.
	 * @param canvas : Canvas to be drawn on.
	 */
	public void moveLeft(AnimationCanvas canvas) {
		setX(x-1);
	}
	
	/**
	 * Method that move unit 1 unit downwards.
	 * @param canvas : Canvas to be drawn on.
	 */
	public void moveDown(AnimationCanvas canvas) {
		setY(y-1);
	}
	
	/**
	 * Method that draws unit on the canvas.
	 * @param canvas : Canvas to be drawn on.
	 */
	public void draw(AnimationCanvas canvas){
		canvas.fillGridSquare(x,y,Color.CYAN.darker());
	}

}

