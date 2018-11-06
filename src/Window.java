import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;

/*
 the window
 */
public class Window extends JFrame{

	public Window(String title, int width, int height, Test test) {

		test.setSize(width, height);
		setTitle(title);
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);
		add(test);

		WindowStateListener listener = new WindowAdapter() {
			public void windowStateChanged(WindowEvent evt) {
				int oldState = evt.getOldState();
				int newState = evt.getNewState();

				if ((oldState & Frame.ICONIFIED) == 0
						&& (newState & Frame.ICONIFIED) != 0) {
					System.out.println("Frame was iconized");
				} else if ((oldState & Frame.ICONIFIED) != 0
						&& (newState & Frame.ICONIFIED) == 0) {
					System.out.println("Frame was deiconized");
				}

				if ((oldState & Frame.MAXIMIZED_BOTH) == 0
						&& (newState & Frame.MAXIMIZED_BOTH) != 0) {
					System.out.println("Frame was maximized");
				} else if ((oldState & Frame.MAXIMIZED_BOTH) != 0
						&& (newState & Frame.MAXIMIZED_BOTH) == 0) {
					System.out.println("Frame was minimized");
				}
			}
		};

		this.addWindowStateListener(listener);
		this.setVisible(true);
		
		test.start();

	}

}
