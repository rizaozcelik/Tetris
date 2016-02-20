import java.util.ArrayList;
import java.util.Collections;

/**
* Class that is responsible for the creation of a l shaped tile.
* It is a subclass of Tiles class.
* @see Tiles
* @author Riza Ozcelik
*
*/
public class L extends Tiles {
	
	private ArrayList<Unit> l = new ArrayList<Unit>();
	
	/**
	 * Constructor that creates L shaped unit array list.
	 * It also assigns tiles to l.
	 */
	public L(){
		super();
		uA = new Unit(4,20);
		uR = new Unit(5,20);
		uC = new Unit(6,20);
		uB = new Unit(4,21);		
		Collections.addAll(l,uA,uB,uC,uR);
		tile = l;
	}
}