package bubble.test.ex16;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundEnemyService implements Runnable {
	
	private BufferedImage image;
	private Enemy enemy;
	
	public BackgroundEnemyService(Enemy enemy) {
		this.enemy = enemy;
		
		try {
			image = ImageIO.read(new File("image/backgroundMapService.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void run() {
		while(true) {
			Color leftColor = new Color(image.getRGB(enemy.getX() -10 , enemy.getY() + 25));
			Color rightColor = new Color(image.getRGB(enemy.getX() +50 + 15 , enemy.getY() + 25));
			
			if(leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
				enemy.setLeft(false);
			} else if(rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
				enemy.setRight(false);
			} else {
			}
			
			Color bottomColor = new Color(image.getRGB(enemy.getX() + 25, enemy.getY() + 50 + 5));
			Color bottomColor2 = new Color(image.getRGB(enemy.getX() + 50 -10, enemy.getY() + 50 + 5));
			
			if(bottomColor.getRed() != 255 || bottomColor.getGreen() != 255 || bottomColor.getBlue() != 255 
			|| bottomColor2.getRed() != 255 || bottomColor2.getGreen() != 255 || bottomColor2.getBlue() != 255) {
				enemy.setDown(false);
			} else {
				if(!enemy.isUp() && !enemy.isDown()) {
					enemy.down();
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
