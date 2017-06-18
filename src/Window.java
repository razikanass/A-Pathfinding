
import javax.swing.JFrame;

/*
	the window
*/
public class Window extends JFrame {
	
	public Window(String title, int width, int height, Test test){
		
		test.setSize(width, height);
		setTitle(title);
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		add(test);
		test.start();
		
	}

}
