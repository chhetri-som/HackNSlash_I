package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.OBJ_Key;

public class UI {
	
	GamePanel gp;
	Font comfort_20;
	Font comfort_30;
	BufferedImage keyImage;
	public boolean messageON = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		comfort_20 = new Font("Comfortaa", Font.ITALIC, 20);
		comfort_30 = new Font("Comfortaa", Font.BOLD, 30);
		OBJ_Key key = new OBJ_Key();
		keyImage = key.image;
	}
	

	
	public void showMessage(String text) {
		
		message = text;
		messageON = true;
	}
	
	public void draw(Graphics2D g2) {
		
		if(gameFinished == true) {
			
			g2.setFont(comfort_30);
			g2.setColor(Color.cyan);
			
			String text;
			int textLength;
			int x;
			int y;
			
			text = "ayo there aint a treasure yet soo... yeah";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 - (gp.tileSize * 3);
			g2.drawString(text, x, y);

			text = "Time Spent: " + dFormat.format(playTime) + " seconds bro.";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 + (gp.tileSize * 4);
			g2.drawString(text, x, y);
			
			text = "love yourself broo";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 + (gp.tileSize * 2);
			g2.drawString(text, x, y);
			
			gp.gameThread = null;
			
		}
		else {
			
			g2.setFont(comfort_20);
			g2.setColor(Color.white);
			g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
			g2.drawString("x " + gp.player.hasKey, 74, 42);
			
			//time
			playTime +=(double)1/30;
			
			// message
			if(messageON == true) {
				
				g2.setFont(g2.getFont().deriveFont(20F)); 
				g2.drawString(message, gp.tileSize + 370, gp.tileSize + 210);
				
				messageCounter++;
				
				if(messageCounter > 90) {
					messageCounter = 0;
					messageON = false;
				}
			}
		}
	}

}
