package bubble.test.ex18;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player extends JLabel implements Moveable {
	private BubbleFrame mContext;
	
	private int x;
	private int y;
	
	private ImageIcon playerR, playerL;
	
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	private final int SPEED = 3;
	private final int JUMPSPEED = 2;
	
	private boolean leftWallCrash;
	private boolean rightWallCrash;
	
	private PlayerWay playerWay;
	
	
	public Player(BubbleFrame mContext) {
		this.mContext = mContext;
		initObject();
		initSetting();
		initBackgroundPlayerService();
	}
	
	@Override
	public void attack() {
		System.out.println(1);
		new Thread(new Runnable() {
			@Override
			public void run() {
				Bubble bubble = new Bubble(mContext);
				mContext.add(bubble);
				
				if(playerWay == PlayerWay.LEFT) {
					bubble.left();
				} else {
					bubble.right();
				}
			}
		}).start();
	}
	
	
	private void initObject() {
		playerR = new ImageIcon("image/playerR.png");
		playerL = new ImageIcon("image/playerL.png");
	}
	
	
	
	private void initSetting() {
		x = 80;
		y = 535;
		
		left = false;
		right = false;
		up = false;
		down = false;
		leftWallCrash = false;
		rightWallCrash = false;
		playerWay = PlayerWay.RIGHT;
		setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y);
		
	}
	
	private void initBackgroundPlayerService() {
		new Thread(new BackgroundPlayerService(this)).start();
	}
	
	
	

	@Override
	public void left() {
		left = true;
		playerWay = PlayerWay.LEFT;
		
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
		right = true;
		playerWay = PlayerWay.RIGHT;
		
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
		up = true;
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=0;i<130 / JUMPSPEED;i++) {
					y = y -JUMPSPEED;
					setLocation(x, y);
					
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				up = false;
				down();
			}
		}).start();
		
		
	}

	@Override
	public void down() {
		down = true;
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(down) {
					y = y + JUMPSPEED;
					setLocation(x, y);
					
					try {
						Thread.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				down = false;
			}
		}).start();
		
		
		
	}
	

}
