package bubble.test.ex18;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bubble extends JLabel implements Moveable {

	
	private BubbleFrame mContext;
	private Player player;
	private List<Enemy> enemy; 
	private BackgroundBubbleService backgroundBubbleService;
	
	private int x;
	private int y;
	
	private boolean left;
	private boolean right;
	private boolean up;

	private int state; // 0 물방울, 1 적을 가둔 물방울
	private ImageIcon bubble;
	private ImageIcon bubbled;
	private ImageIcon bomb;
	
	private final int SPEED = 3;

	public Bubble(BubbleFrame mContext) {
		this.mContext = mContext;
		this.player = mContext.getPlayer();
		this.enemy = mContext.getEnemy();
		initObject();
		initSetting();
	}
	
	private void initObject() {
		bubble = new ImageIcon("image/bubble.png");
		bubbled = new ImageIcon("image/bubbled.png");
		bomb = new ImageIcon("image/bomb.png");
		
		backgroundBubbleService = new BackgroundBubbleService(this);
	}
	
	private void initSetting() {
		left = false;
		right = false;
		up = false;
		
		x = player.getX();
		y = player.getY();
	
		setIcon(bubble);
		setSize(50, 50);
		state = 0;
	}

	@Override
	public void left() {
		left = true;
		for(int i=0;i<400;i++) {
			x--;
			setLocation(x,y);
			
			if(backgroundBubbleService.leftWall()) {
				left = false;
				break;
			}
			for(int j=0;j<enemy.size();j++) {
				if((Math.abs(x - enemy.get(j).getX()) > 40 && Math.abs(x - enemy.get(j).getX()) < 60) && 
						(Math.abs(y - enemy.get(j).getY()) > 0 && Math.abs(y - enemy.get(j).getY()) < 50)) {
					if(enemy.get(j).getState() == 0) {
						attack();
						break;
					}
				}
				
			}
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		up();
	}

	@Override
	public void right() {
		right = true;
		
		for(int i=0;i<400;i++) {
			x++;
			setLocation(x,y);
			
			if(backgroundBubbleService.rightWall()) {
				right = false;
				break;
			}
			for(int j=0;j<enemy.size();j++) {
				if((Math.abs(x - enemy.get(j).getX()) < 10) && 
						(Math.abs(y - enemy.get(j).getY()) > 0 && Math.abs(y - enemy.get(j).getY()) < 50)) {
					if(enemy.get(j).getState() == 0) {
						attack();
						break;
					}
				}
				
			}
			
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		up();
		
	}

	@Override
	public void up() {
		up = true;
		
		while(up) {
			y--;
			setLocation(x,y);
			
			if(backgroundBubbleService.topWall()) {
				up = false;
				break;
			}
			
			try {
				if(state == 0) {
					Thread.sleep(1);
				} else {
					Thread.sleep(10);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		if(state == 0 ) {
			clearBubble();
		}
	}
	
	@Override
	public void attack() {
		state = 1;
		for(int j=0;j<enemy.size();j++) {
			enemy.get(j).setState(1);
			mContext.remove(enemy.get(j));
		}
		setIcon(bubbled);
		mContext.repaint();
	}
	
	public void clearBubble() {
		try {
			Thread.sleep(3000);
			setIcon(bomb);
			Thread.sleep(500);
			mContext.remove(this);
			mContext.repaint();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


