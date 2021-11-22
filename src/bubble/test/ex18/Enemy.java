package bubble.test.ex18;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Enemy extends JLabel implements Moveable {
	private BubbleFrame mContext;
	
	private int x;
	private int y;
	
	private ImageIcon enemyR, enemyL;
	
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	private int state;
	
	private final int SPEED = 3;
	private final int JUMPSPEED = 2;
	
	private EnemyWay enemyWay;
	
	private boolean wallCrash;
	
	private int random;
	
	
	public Enemy(BubbleFrame mContext) {
		this.mContext = mContext;
		initObject();
		initSetting();
		initBackgroundEnemyService();
		
		System.out.println("적생성");
	}
	
	@Override
	public void attack() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Bubble bubble = new Bubble(mContext);
				mContext.add(bubble);
				
				if(enemyWay == EnemyWay.LEFT) {
					bubble.left();
				} else {
					bubble.right();
				}
			}
		}).start();
	}
	
	
	private void initObject() {
		enemyR = new ImageIcon("image/enemyR.png");
		enemyL = new ImageIcon("image/enemyL.png");
	}
	
	
	
	private void initSetting() {
		x = 480;
		y = 178;
		state = 0;
		
		left = false;
		right = false;
		up = false;
		down = false;
		wallCrash = false;
		enemyWay = EnemyWay.RIGHT;
		setIcon(enemyR);
		setSize(50, 50);
		setLocation(x, y);

		left();
	}
	
	private void initBackgroundEnemyService() {
		new Thread(new BackgroundEnemyService(this)).start();
	}
	
	
	

	@Override
	public void left() {
		left = true;
		enemyWay = EnemyWay.LEFT;
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				
				while(left) {
					x = x - SPEED;
					setLocation(x,y);
					setIcon(enemyL);
					
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
		enemyWay = EnemyWay.RIGHT;
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(right) {
					x = x + SPEED;
					setLocation(x,y);
					setIcon(enemyR);
					
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
	
	
	
	public void move() {
		if(!right && !left) {
			
			System.out.println(left + " "  + right + " " + wallCrash ) ;
			System.out.println(enemyWay);
			if(!wallCrash ) {
				if(enemyWay == EnemyWay.LEFT) {
					right();
				} else{
					left();
				}
				
			} else if(wallCrash&& y > 180) {
				if(!isUp()) {
					up();
				}
				
			} else if(wallCrash) {
				wallCrash = false;
				System.out.println("벽에 부딪힘");
				if(enemyWay == EnemyWay.LEFT) {
					right();
				} else{
					left();
				}
				
			}
			return;
		} 
		
	}
	

}
