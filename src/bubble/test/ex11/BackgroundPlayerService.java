package bubble.test.ex11;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundPlayerService implements Runnable {
	
	private BufferedImage image;
	private Player player;
	
	public BackgroundPlayerService(Player player) {
		this.player = player;
		
		try {
			image = ImageIO.read(new File("image/backgroundMapService.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void run() {
		while(true) {
			Color leftColor = new Color(image.getRGB(player.getX() -10 , player.getY() + 25));
			Color rightColor = new Color(image.getRGB(player.getX() +50 + 15 , player.getY() + 25));
			
			if(leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
				player.setLeftWallCrash(true);
				player.setLeft(false);
			} else if(rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
				player.setRightWallCrash(true);
				player.setRight(false);
			} else {
				player.setRightWallCrash(false);
				player.setLeftWallCrash(false);
			}
			
			Color bottomColor = new Color(image.getRGB(player.getX() + 25, player.getY() + 50 + 5));
			Color bottomColor2 = new Color(image.getRGB(player.getX() + 50 -10, player.getY() + 50 + 5));
			
			if(bottomColor.getRed() != 255 || bottomColor.getGreen() != 255 || bottomColor.getBlue() != 255 
			|| bottomColor2.getRed() != 255 || bottomColor2.getGreen() != 255 || bottomColor2.getBlue() != 255) {
				player.setDown(false);
			} else {
				if(!player.isUp() && !player.isDown()) {
					player.down();
				}
			}
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	
	
	
}
