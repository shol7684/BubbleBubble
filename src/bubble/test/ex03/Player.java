package bubble.test.ex03;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel implements Moveable {
	
	private int x;
	private int y;
	
	private ImageIcon playerR, playerL;
	
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	
	
	public Player() {
		System.out.println("player");
		initObject();
		initSetting();
	}
	
	private void initObject() {
		playerR = new ImageIcon("image/playerR.png");
		playerL = new ImageIcon("image/playerL.png");
	}
	
	private void initSetting() {
		x = 55;
		y = 535;
		
		left = false;
		right = false;
		
		setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y);
	}

	@Override
	public void left() {
		x = x - 10;
		setLocation(x,y);
		setIcon(playerL);
		
	}

	@Override
	public void right() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		x = x + 10;
		setLocation(x,y);
		setIcon(playerR);
		
	}

	@Override
	public void up() {
		System.out.println("위");
		
	}

	@Override
	public void down() {
		
	}
	

}
