package bubble.test.ex15;

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
	
	private final int SPEED = 3;
	private final int JUMPSPEED = 2;
	
	private EnemyWay enemyWay;
	
	
	public Enemy(BubbleFrame mContext) {
		this.mContext = mContext;
		initObject();
		initSetting();
//		initBackgroundPlayerService();
	}
	
	@Override
	public void attack() {
		System.out.println(1);
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
		System.out.println("생성");
		enemyR = new ImageIcon("image/enemyR.png");
		enemyL = new ImageIcon("image/enemyL.png");
	}
	
	
	
	private void initSetting() {
		x = 480;
		y = 178;
		
		left = false;
		right = false;
		up = false;
		down = false;
		enemyWay = EnemyWay.RIGHT;
		setIcon(enemyR);
		setSize(50, 50);
		setLocation(x, y);
	}
	
	private void initBackgroundEnemyService() {
		new Thread(new BackgroundEnemyService(this)).start();
	}
	
	
	

	@Override
	public void left() {
		System.out.println("왼쪽");
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
		System.out.println("오른쪽");
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
		System.out.println("위");
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
		System.out.println("다운");
		down = true;
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(down) {
					System.out.println("착지중");
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
