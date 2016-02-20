package drawing_framework;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

/**
 * Animated drawing canvas.
 * @author cantunca
 * @version 1.2
 */
public class AnimationCanvas extends Canvas {
	
	private static final long serialVersionUID = 3L;
	
	private Timer animationTimer;
	private ArrayList<Animatable> objects;
	private ArrayList<Animatable> objectsToBeAdded;
	private ArrayList<Animatable> objectsToBeRemoved;
	private final int frameRate;
	
	/**
	 * No argument constructor.
	 * The default frame rate is 20 frames per second.
	 * The default size of the canvas is 500 x 500 pixels.
	 * The canvas also contains an initially hidden grid. The default square size of the grid is 20 pixels.
	 */
	public AnimationCanvas() {
		super();
		frameRate = 20;
		objects = new ArrayList<Animatable>();
		objectsToBeAdded = new ArrayList<Animatable>();
		objectsToBeRemoved = new ArrayList<Animatable>();
		setAnimationTimer();
	}
	
	/**
	 * Constructor for explicitly setting the frame rate.
	 * The default size of the canvas is 500 x 500 pixels.
	 * The canvas also contains an initially hidden grid. The default square size of the grid is 20 pixels.
	 * @param frameRate Frame rate of the animation.
	 */
	public AnimationCanvas(int frameRate) {
		super();
		this.frameRate = frameRate;
		objects = new ArrayList<Animatable>();
		objectsToBeAdded = new ArrayList<Animatable>();
		objectsToBeRemoved = new ArrayList<Animatable>();
		setAnimationTimer();
	}
	
	/**
	 * Constructor for explicitly setting the size, grid square size and the frame rate of the canvas.
	 * @param width Width of the canvas in terms of pixels.
	 * @param height Height of the canvas in terms of pixels.
	 * @param gridSquareSize Size of the side of an individual grid square in terms of pixels.
	 * @param frameRate Frame rate of the animation.
	 */
	public AnimationCanvas(int width, int height, int gridSquareSize, int frameRate) {
		super(width, height, gridSquareSize);
		this.frameRate = frameRate;
		objects = new ArrayList<Animatable>();
		objectsToBeAdded = new ArrayList<Animatable>();
		objectsToBeRemoved = new ArrayList<Animatable>();
		setAnimationTimer();
	}
	
	/**
	 * Initializes and starts the animation timer with the specified frame rate.
	 * At each tick, the move() and draw() methods of the added animatable objects are called.
	 */
	private void setAnimationTimer() {
		animationTimer = new Timer(1000/frameRate, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearCanvas();
				drawGrid();
				for (Animatable obj:objects) {
					obj.move(AnimationCanvas.this);
					obj.draw(AnimationCanvas.this);
				}
				for (Animatable obj:objectsToBeAdded) {
					objects.add(obj);
				}
				objectsToBeAdded.clear();
				for (Animatable obj:objectsToBeRemoved) {
					objects.remove(obj);
				}
				objectsToBeRemoved.clear();
			}
		});
	}
	
	/**
	 * Starts the animation.
	 */
	public void start() {
		animationTimer.setInitialDelay(0);
		animationTimer.start();
	}
	
	/**
	 * Stops the animation.
	 */
	public void stop() {
		animationTimer.stop();
	}
	
	/**
	 * Adds an animatable object to the canvas.
	 * The operation is applied in the next animation tick.
	 * @param obj Object to be added to the canvas.
	 */
	public void addObject(Animatable obj) {
		objectsToBeAdded.add(obj);
	}
	
	/**
	 * Removes a previously added animatable object from the canvas.
	 * The operation is applied in the next animation tick.
	 * @param obj Object to be removed from the canvas.
	 */
	public void removeObject(Animatable obj) {
		objectsToBeRemoved.add(obj);
	}
	
	/**
	 * Returns the objects previously added to the canvas.
	 * @return ArrayList containing the animatable objects in the canvas.
	 */
	public ArrayList<Animatable> getObjects() {
		return objects;
	}

}
