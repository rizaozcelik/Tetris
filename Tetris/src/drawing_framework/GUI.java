package drawing_framework;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

/**
 * The main application window
 * @author cantunca
 * @version 1.1
 */
public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Canvas> canvases;

	/**
	 * No argument constructor.
	 * Creates and shows the application window (frame).
	 */
	public GUI() {
		initialize();
		start();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setResizable(false);
		setBounds(100, 100, 50, 52);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		canvases = new ArrayList<Canvas>();
	}
	
	/**
	 * Run and show the frame.
	 */
	private void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Adds a canvas to the application window.
	 * The canvas is immediately shown and made functional.
	 * Multiple canvases are shown side by side.
	 * @param canvas
	 */
	public void addCanvas(Canvas canvas) {
		canvases.add(canvas);
		canvas.setBackground(Color.WHITE);
		add(canvas);
		pack();
	}
	
	/**
	 * Returns the canvases previously added to the GUI.
	 * @return ArrayList containing the canvases.
	 */
	public ArrayList<Canvas> getCanvases() {
		return canvases;
	}

}
