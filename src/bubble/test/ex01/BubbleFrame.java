package bubble.test.ex01;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;

public class BubbleFrame extends JFrame {
	
	public BubbleFrame() {
		setSize(1000, 640);
		getContentPane().setLayout(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new BubbleFrame();
	}

}
