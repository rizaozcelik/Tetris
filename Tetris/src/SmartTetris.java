import java.awt.event.KeyEvent;
import java.util.ArrayList;

import drawing_framework.Animatable;
import drawing_framework.AnimationCanvas;
import drawing_framework.GUI;
/**
 * Class that is responsible for the Artificial Intelligence.
 * AI follows a basic algorithm. It sets pieces as low as possible.
 * It removes lines when lucky :). The class is a subclass of TetrisGame
 * and implements Animatable interface.  
 * @author Riza Ozcelik
 *
 */
public class SmartTetris extends TetrisGame implements Animatable {
	
	/**
	 * Constructor that creates a smart tetris.
	 * @param gui : Gui to be used.
	 */
	public SmartTetris(GUI gui) {
		super(gui);
		TetrisGame.gameCanvas.addObject(this);
	}
	
	/**
	 * Method to move object to a certain destination.
	 * @param canvas
	 */
	@Override
	public void move(AnimationCanvas canvas) {
		gooesToo(findMinHeight());
	}
	/**
	 * Method that returns column with the lowest height.
	 * @return : Column with the lowest height.
	 */
	public int findMinHeight(){
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(int j = 0; j<10; j++){
			for(int i = 24; i>-1; i--){
				if(getTable(i,j)){
					arr.add(i);
					break;
				}
				if(i == 0){
					arr.add(0);
				}
			}
		}
		int min = 10;
		int index = 0;
		for(int i = 0; i<arr.size(); i++){
			if(arr.get(i) < min){
				min = arr.get(i);
				index =i;
			}
		}
		return index;
	}
	/**
	 * Method to move objects to a certain destination.
	 * @param dest : x  coordinate of destination.
	 */
	@SuppressWarnings("static-access")
	public void gooesToo(int dest) {
		int min = super.tile.getMinX();
		if (dest > min) {
			for (int i = 0; i < dest - min; i++) {
				super.tile.setDirection(Direction.RIGHT);
				super.tile.move(gameCanvas);
			}
		} else {
			for (int i = 0; i < min - dest; i++) {
				super.tile.setDirection(Direction.LEFT);
				super.tile.move(gameCanvas);
			}
		}
		if (min == dest) {
			while (super.tile.canMoveDown()) {
				super.tile.move(gameCanvas);
			}
		}
	}
	/**
	 * Method to collect user data. But since it is an AI it collects
	 * only the pause and restart inputs.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_R) {
			kill();
			GUI gui = new GUI();
			new SmartTetris(gui);
		}

		if (e.getKeyCode() == KeyEvent.VK_P) {
			stop = !stop;
			if (stop) {
				gameCanvas.stop();
			} else
				gameCanvas.start();
		}

	}
	/**
	 * Method that does nothing. Overridden just because it must be.
	 * @param canvas : ----
	 */
	@Override
	public void draw(AnimationCanvas canvas) {
		// TODO Auto-generated method stub

	}
}