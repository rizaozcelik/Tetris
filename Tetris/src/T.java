import java.util.ArrayList;
import java.util.Collections;

/**
* Class that is responsible for the creation of a T shaped tile.
* It is a subclass of Tiles class.
* @see Tiles
* @author Riza Ozcelik
*
*/
public class T extends Tiles {
	
	private ArrayList<Unit> t = new ArrayList<Unit>();
	
	/**
	 * Constructor that creates T shaped unit array list.
	 * It also assigns tiles to t.
	 */
	public T(){
		super();
		uA = new Unit(4,20);
		uR = new Unit(5,20);
		uC = new Unit(6,20);
		uB = new Unit(5,21);		
		Collections.addAll(t,uA,uB,uC,uR);
		tile = t;
	}
}