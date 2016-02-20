import java.util.ArrayList;
import java.util.Collections;

import drawing_framework.AnimationCanvas;

/**
* Class that is responsible the creation of an S shaped tile4It is a subclass of Tiles class.
* It is a subclass of Tiles class.
* @see Tiles
* @author Riza Ozcelik
*
*/

public class S extends Tiles {
	
	private ArrayList<Unit> s = new ArrayList<Unit>();
	int count = 0;
	
	/**
	 * Constructor that creates S shaped unit array list.
	 * It also assigns tiles to s.
	 */
	public S(){
		super();
		uA = new Unit(4,20);
		uB = new Unit(5,20);
		uC = new Unit(6,21);
		uR = new Unit(5,21);		
		Collections.addAll(s,uA,uB,uC,uR);
		tile = s;
	}
	
	/**
	 * Method that checks if the piece is rotatable.
	 * It is overridden because S piece has only 2 rotations.
	 * So uses a different rotation algorithm.
	 * It basically rotates back to its former position if it can
	 * @return : If the piece is rotatable.
	 * @see Tiles.rotatable()
	 */
	@Override
	public boolean rotatable() {
		int orx = 0;
		int ory = 0;
		double alpha = -1;
		if(count %2 == 1){
			alpha = -1;
		}else alpha = 1;
		orx = tile.get(3).getX();
		ory = tile.get(3).getY();
		for (Unit unit : tile) {
			int tempx = unit.getX() - orx;
			int tempy = unit.getY() - ory;
			int a = (orx + (int) (-(tempy * alpha)));
			int b = (ory + (int) ((tempx * alpha)));
			if (!(a < canvas.getGridWidth() && a > -1
					&& b < canvas.getGridHeight() && b > -1)){
				return false;
			}if	(TetrisGame.getTable(b,a)) {
				return false;
			}
			
		}
		return true;
	}
	
	/**
	 * Method that rotates S if it is rotatable
	 * @see Tiles.rotate()
	 */
	@Override
	public void rotate(AnimationCanvas canvas) {
		if (rotatable()) {
			count++;
			double alpha;
			if(count %2 == 1){
				alpha = 1;
			}else alpha = -1;
			int orx = 0;
			int ory = 0;
			orx = tile.get(3).getX();
			ory = tile.get(3).getY();
			for (Unit unit : tile) {
				int tempx = unit.getX() - orx;
				int tempy = unit.getY() - ory;
				int a = (orx + (int) (-(tempy*alpha)));
				int b = (ory + (int) ((tempx*alpha)));
				unit.setX(a);
				unit.setY(b);
			}
		}
	}
}
