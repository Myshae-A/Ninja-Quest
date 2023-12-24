package environment;

import java.awt.AlphaComposite;
import java.awt.RadialGradientPaint;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.text.AttributeSet.ColorAttribute;

import entity.Entity;
import main.GamePanel;

public class Lighting {

	GamePanel gp;
	BufferedImage darknessFilter;
	public int dayCounter;
	public float filterAlpha = 0f;
	
	public final int day = 0;
	public final int dusk = 1;
	public final int night = 2;
	public final int dawn = 3;
	public int dayState = day;
	
	public double col = 0;
	public double row = 0;
	
	public Lighting(GamePanel gp) {
		this.gp = gp;
		setLightSource();
	}
	public void setLightSource() {
		
		// Create a buffered image
				darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2 = (Graphics2D)darknessFilter.getGraphics();
				
				if(gp.player.currentLight == null) {
					g2.setColor(new Color(0,0,0.1f,0.97f)); // can adjust the rgb colors of the day/night cycle
				}
				else {
					
						// get the center x and y of the light circle
						int centerX = gp.player.screenX + (gp.tileSize)/2;
						int centerY = gp.player.screenY + (gp.tileSize)/2;
						
						// Create a graduation effect within the light circle
						Color color[] = new Color[12];
						float fraction[] = new float[12];
						
						color[0] = new Color(0,0,0.1f,0.1f); // 0f is completely transparent
						color[1] = new Color(0,0,0.1f,0.42f);
						color[2] = new Color(0,0,0.1f,0.52f);
						color[3] = new Color(0,0,0.1f,0.61f);
						color[4] = new Color(0,0,0.1f,0.69f); 
						color[5] = new Color(0,0,0.1f,0.76f);
						color[6] = new Color(0,0,0.1f,0.82f);
						color[7] = new Color(0,0,0.1f,0.87f);
						color[8] = new Color(0,0,0.1f,0.91f);
						color[9] = new Color(0,0,0.1f,0.92f);
						color[10] = new Color(0,0,0.1f,0.93f);
						color[11] = new Color(0,0,0.1f,0.94f); // 1f is completely dark
						
						fraction[0] = 0.1f;
						fraction[1] = 0.4f;
						fraction[2] = 0.5f;
						fraction[3] = 0.6f;
						fraction[4] = 0.65f;
						fraction[5] = 0.7f;
						fraction[6] = 0.75f;
						fraction[7] = 0.8f;
						fraction[8] = 0.85f;
						fraction[9] = 0.9f;
						fraction[10] = 0.95f;
						fraction[11] = 1f;
						
						//System.out.println(centerX);
						//System.out.println(centerY);
						// crate a garduation paint settings for the light circle
						RadialGradientPaint gPaint = new RadialGradientPaint(centerX,centerY, gp.player.currentLight.lightRadius,fraction,color);
						
						// set the gradient on g2
						g2.setPaint(gPaint);
					
				}
				
				g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);
				
				g2.dispose();
	}
	public void setLightSourceNOTPLAYER(int map, double col, double row, Entity light) {
		
		// Create a buffered image
//		if(gp.currentMap == map) {
//			
//			darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
//			Graphics2D g2 = (Graphics2D)darknessFilter.getGraphics();
//			
//				// get the center x and y of the light circle
//				int centerX = gp.tileSize*col;
//				int centerY = gp.tileSize*row;
//				
//				// Create a graduation effect within the light circle
//				Color color[] = new Color[12];
//				float fraction[] = new float[12];
//				
//				color[0] = new Color(0,0,0.1f,0.1f); // 0f is completely transparent
//				color[1] = new Color(0,0,0.1f,0.42f);
//				color[2] = new Color(0,0,0.1f,0.52f);
//				color[3] = new Color(0,0,0.1f,0.61f);
//				color[4] = new Color(0,0,0.1f,0.69f); 
//				color[5] = new Color(0,0,0.1f,0.76f);
//				color[6] = new Color(0,0,0.1f,0.82f);
//				color[7] = new Color(0,0,0.1f,0.87f);
//				color[8] = new Color(0,0,0.1f,0.91f);
//				color[9] = new Color(0,0,0.1f,0.92f);
//				color[10] = new Color(0,0,0.1f,0.93f);
//				color[11] = new Color(0,0,0.1f,0.94f); // 1f is completely dark
//				
//				fraction[0] = 0.1f;
//				fraction[1] = 0.4f;
//				fraction[2] = 0.5f;
//				fraction[3] = 0.6f;
//				fraction[4] = 0.65f;
//				fraction[5] = 0.7f;
//				fraction[6] = 0.75f;
//				fraction[7] = 0.8f;
//				fraction[8] = 0.85f;
//				fraction[9] = 0.9f;
//				fraction[10] = 0.95f;
//				fraction[11] = 1f;
//				
//				//System.out.println(centerX);
//				//System.out.println(centerY);
//				// crate a garduation paint settings for the light circle
//				RadialGradientPaint gPaint = new RadialGradientPaint(centerX,centerY, light.lightRadius,fraction,color);
//				
//				// set the gradient on g2
//				g2.setPaint(gPaint);
//			
//			g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);
//			
//			g2.dispose();
//		}
		
		darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D)darknessFilter.getGraphics();
		
		if(gp.player.currentLight == null) {
			g2.setColor(new Color(0,0,0.1f,0.97f)); // can adjust the rgb colors of the day/night cycle
		}
		else {
			
				// get the center x and y of the light circle
//				int centerX = gp.player.screenX + (gp.tileSize)/2;
//				int centerY = gp.player.screenY + (gp.tileSize)/2;
			
				//int centerX = gp.tileSize*col; 
				//int centerY = gp.tileSize*row;
				
				// Create a graduation effect within the light circle
				Color color[] = new Color[12];
				float fraction[] = new float[12];
				
				color[0] = new Color(0,0,0.1f,0.1f); // 0f is completely transparent
				color[1] = new Color(0,0,0.1f,0.42f);
				color[2] = new Color(0,0,0.1f,0.52f);
				color[3] = new Color(0,0,0.1f,0.61f);
				color[4] = new Color(0,0,0.1f,0.69f); 
				color[5] = new Color(0,0,0.1f,0.76f);
				color[6] = new Color(0,0,0.1f,0.82f);
				color[7] = new Color(0,0,0.1f,0.87f);
				color[8] = new Color(0,0,0.1f,0.91f);
				color[9] = new Color(0,0,0.1f,0.92f);
				color[10] = new Color(0,0,0.1f,0.93f);
				color[11] = new Color(0,0,0.1f,0.94f); // 1f is completely dark
				
				fraction[0] = 0.1f;
				fraction[1] = 0.4f;
				fraction[2] = 0.5f;
				fraction[3] = 0.6f;
				fraction[4] = 0.65f;
				fraction[5] = 0.7f;
				fraction[6] = 0.75f;
				fraction[7] = 0.8f;
				fraction[8] = 0.85f;
				fraction[9] = 0.9f;
				fraction[10] = 0.95f;
				fraction[11] = 1f;
				
				//System.out.println(centerX);
				//System.out.println(centerY);
				// crate a garduation paint settings for the light circle
				
				//RadialGradientPaint gPaint = new RadialGradientPaint(centerX,centerY, 200,fraction,color);
				//__________________________________________________________________________________________________________
				// THE PROBLEM IS THAT I CANNOT CREATE RadialGradientPaint at a DOUBLE x and y.
				// It apparently needs to be an INTEGER at point x,y. This means even if I figured out a way to update it
				// so the ligh appears inside the screen, it would be choppy because it would update by col and row
				// INTEGERS as the player moves around the world...
				// SO basically, it's still too advanced for me to make.
				//__________________________________________________________________________________________________________
				// set the gradient on g2
				//g2.setPaint(gPaint);
			
		}
		
		g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);
		
		g2.dispose();
		
	}
	public void resetDay() {
		dayState = day;
		filterAlpha = 0f;
	}
	public void update() {
		
//		if(gp.player.lightUpdated == true) {
//			setLightSource();
//			gp.player.lightUpdated = false;
//		}
		setLightSource(); // updates according to player lightradius so, this one-line works best
//		setLightSourceNOTPLAYER(0,col,row, gp.obj[0][6]);
//		col+=.01;
//		row+=.01;
		
		// check the state of the day
		if(dayState == day) {
			
			dayCounter++;
			
			if(dayCounter == 1) {
				gp.dayNumber++;
				//System.out.println("day #: "+gp.dayNumber);
			}
			
			if(dayCounter > 18000) { // here 600 = 10 seconds, 36,000 = 10 mins, and so on
				dayState = dusk;
				gp.dayState = gp.dusk;
				dayCounter = 0;
			}
		}
		if(dayState == dusk) {
			
			filterAlpha += 0.001f; // can use smaller numbers here to reduce transitioning speed
			
			if(filterAlpha > 0.95f) { // so night isnt completely pitch black, reduced from 1f to .95f, seemed just about right
				filterAlpha = 0.95f;
				dayState = night;
				gp.dayState = gp.night;
				if((gp.currentMap == 0) ||(gp.currentMap == 1) || (gp.currentMap == 5) || (gp.currentMap == 6)) {
					if(gp.bossFightOn) {
						
					}
					else {
						gp.stopMusic();
						gp.playMusic(49);
					}
				}
			}
		}
		if(dayState == night) {
			
			dayCounter++;
			
			if(dayCounter > 12000) {
				dayState = dawn;
				gp.dayState = gp.dawn;
			}
		}
		if(dayState == dawn) {
			
			filterAlpha -= 0.001f;
			
			if(filterAlpha < 0f) {
				filterAlpha = 0f;
				dayState = day;
				gp.dayState = gp.day;
				dayCounter = 0;
				if((gp.currentMap == 0)) {
					gp.stopMusic();
					gp.playMusic(46);
				}
				if((gp.currentMap == 1)) {
					gp.stopMusic();
					gp.playMusic(29);
				}
				if((gp.currentMap == 5)) {
					gp.stopMusic();
					gp.playMusic(40);
				}
				if((gp.currentMap == 6)) {
					gp.stopMusic();
					gp.playMusic(43);
				}
				if((gp.currentMap == 7)) {
					gp.stopMusic();
					gp.playMusic(63);
				}
			}
		}
	}
	public void draw(Graphics2D g2) {
		
		// fixing all the filters for outside, indoor, and dungeon (is always dark in dungeon)
		if(gp.currentArea == gp.outside) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha));
		}
		if(gp.currentArea == gp.outside || gp.currentArea == gp.dungeon) {
			g2.drawImage(darknessFilter,0,0,null);
		}
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
		//debug
		
		// Turned off daytime indicators, bc it was a bit in the way
		String situation = "";
		
		switch(dayState) {
		case day:situation = "Day";break;
		case dusk:situation = "Dusk";break;
		case night:situation = "Night";break;
		case dawn:situation = "Dawn";break;
		}
	
		//doesnt draw day-time in dungeons or pirate boss map
		if(gp.currentMap == 4) {}
		else if(gp.currentMap == 3) {}
		else if(gp.currentMap == 2) {}
		else {
			if(gp.gameState == gp.playState) {
				g2.setColor(Color.black);
				g2.setFont(g2.getFont().deriveFont(50f));
				g2.drawString(situation, 842, 532);
				
				g2.setColor(Color.white);
				g2.drawString(situation, 840, 530);
			}
		}
		
//		if(gp.bossFightOn == false || gp.skillTreeOn == false) { // skillTreeOn doesnt work, so skip for now
//			g2.setColor(Color.white);
//			g2.setFont(g2.getFont().deriveFont(50f));
//			//g2.drawString(situation, 432, 550);
//			g2.drawString(situation, 840, 550);
//		}
		
		
	}
}
