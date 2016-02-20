import java.util.ArrayList;
import java.util.Collections;

/**
 * Class that is responsible for the creation of a J shaped tile.
 * It is a subclass of Tiles class.
 * @see Tiles
 * @author Riza Ozcelik
 *
 */
public class J extends Tiles {
	
	private ArrayList<Unit> j = new ArrayList<Unit>();
	
	/**
	 * Constructor that creates J shaped unit array list.
	 * It also assigns tiles to j.
	 */
	public J(){
		super();
		uA = new Unit(4,20);
		uR = new Unit(5,20);
		uC = new Unit(6,20);
		uB = new Unit(6,21);		
		Collections.addAll(j,uA,uB,uC,uR);
		tile = j;
	}
}