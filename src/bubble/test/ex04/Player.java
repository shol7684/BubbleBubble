package bubble.test.ex04;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player extends JLabel implements Moveable {
	
	private int x;
	private int y;
	
	private ImageIcon playerR, playerL;
	
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	private final int SPEED = 3;
	
	
	
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
		System.out.println("왼쪽");
		left = true;
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				
				while(left) {
					x = x - SPEED;
					setLocation(x,y);
					setIcon(playerL);
					
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		
		
		
	}

	@Override
	public void right() {
		System.out.println("오른쪽");
		right = true;
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(right) {
					x = x + SPEED;
					setLocation(x,y);
					setIcon(playerR);
					
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		
		
	}

	@Override
	public void up() {
		System.out.println("위");
		
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while(right) {
//					x = x + 1;
//					setLocation(x,y);
//					setIcon(playerR);
//					
//					try {
//						Thread.sleep(10);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}).start();
//		
		
		
		
	}

	@Override
	public void down() {
		
	}
	

}