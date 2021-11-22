package bubble.test.ex10;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bubble extends JLabel{

	private Player player;
	
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

	public Bubble(Player player) {
		this.player = player;
		initObject();
		initSetting();
	}
	
	private void initObject() {
		bubble = new ImageIcon("image/bubble.png");
		bubbled = new ImageIcon("image/bubbled.png");
		bomb = new ImageIcon("image/bomb.png");
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
}


