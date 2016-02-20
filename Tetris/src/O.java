import java.util.ArrayList;
import java.util.Collections;

import drawing_framework.AnimationCanvas;

/**
 * Class that is responsible for the creation of a O shaped tile.
 * It is a subclass of Tiles class.
 * @see Tiles
 * @author Riza Ozcelik
 *
 */
public class O extends Tiles {
	
	private ArrayList<Unit> o = new ArrayList<Unit>();
	
	/**
	 * Constructor that creates o shaped unit array list.
	 * It also assigns tiles to o.
	 */
	public O(){
		super();
		uA = new Unit(4,20);
		uB = new Unit(5,20);
		uC = new Unit(4,21);
		uR = new Unit(5,21);		
		Collections.addAll(o,uA,uB,uC,uR);
		tile = o;
	}
	
	/*
	 * Method that basically does nothing since o shaped
	 * tiles does not have different rotation position.
	 * 
	 */
	@Override
	public void rotate(AnimationCanvas canvas){
//		do nothing
	}
}