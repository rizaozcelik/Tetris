import java.util.ArrayList;
import java.util.Collections;

import drawing_framework.AnimationCanvas;

/**
 * Class that is responsible for the creation of a I shaped tile.
 * It is a subclass of Tiles class.
 * @see Tiles
 * @author Riza Ozcelik
 *
 */
public class I extends Tiles {

	private ArrayList<Unit> i = new ArrayList<Unit>();
	int count =0;
	
	/**
	 * Constructor that creates I shaped unit array list.
	 * It also assigns tiles to i.
	 */
	public I() {
		super();
		uA = new Unit(4, 20);
		uB = new Unit(4, 21);
		uR = new Unit(4, 22);
		uC = new Unit(4, 23);
		Collections.addAll(i, uA, uB, uC, uR);
		tile = i;
	}
	/**
	 * Method that checks if the piece is rotatable.
	 * It is overridden because I piece has only 2 rotations.
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
		orx = tile.get(1).getX();
		ory = tile.get(1).getY();
		if(orx == 1){
			orx = uR.getX();
			ory = uR.getY();
		}
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
	 * Method that rotates I if it is rotatable
	 * @see Tiles.rotate()
	 */
	@Override
	public void rotate(AnimationCanvas canvas) {
		if (rotatable()) {
			count++;
			double alpha = -1;
			if(count %2 == 1){
				alpha = 1;
			}else alpha = -1;
			int orx = 0;
			int ory = 0;
			orx = tile.get(1).getX();
			ory = tile.get(1).getY();
			if(orx == 1){
				orx = uR.getX();
				ory = uR.getY();
			}
			for (Unit unit : tile) {
				int tempx = unit.getX() - orx;
				int tempy = unit.getY() - ory;
				int a = (orx + (int) (-(tempy * alpha)));
				int b = (ory + (int) ((tempx * alpha)));
				unit.setX(a);
				unit.setY(b);
			}
		}
	}
}

