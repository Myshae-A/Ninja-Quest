package tile_interactive;

import java.awt.Graphics2D;

import entity.Entity;
import main.GamePanel;

public class InteractiveTile extends Entity{

	GamePanel gp;
	public boolean destructible = false;
	
	public InteractiveTile(GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
	}
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;
		return isCorrectItem;
	}
	public void playSE() {
		gp.playSE(11);
	}
	public InteractiveTile getDestroyedForm() {
		InteractiveTile tile = new IT_Trunk(gp, worldX/gp.tileSize, worldY/gp.tileSize);
		return tile;
	}
	public void update() {
		
			if(invincible == true) {
				invincibleCounter++;
				if(invincibleCounter > 20) {
					invincible = false;
					invincibleCounter = 0;
				}
			}
		}
	public void draw(Graphics2D g2) {
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		int rightOffset = gp.screenWidth - gp.player.screenX;
		if(rightOffset > gp.worldWidth - gp.player.worldX) {
			screenX = gp.screenWidth - (gp.worldWidth - worldX);
		}
		int bottomOffset = gp.screenHeight - gp.player.screenY;
		if(bottomOffset > gp.worldHeight - gp.player.worldY) {
			screenY = gp.screenHeight - (gp.worldHeight - worldY);
		}
		
		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
			worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
			worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
			worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			
			g2.drawImage(down1, screenX, screenY, null);
		}
		
		// OKAY!!, so this helped to fix the interactive tile problem
		// Next, I need to do this for entities,
		// Then, I will need to make the player move at the world corners
		else if(gp.player.screenX > gp.player.worldX ||
				gp.player.screenY > gp.player.worldY ||
				rightOffset > gp.worldWidth - gp.player.worldX ||
				bottomOffset > gp.worldHeight - gp.player.worldY) {
			
			g2.drawImage(down1, screenX, screenY, null);
		}
				
	}
	
	
}
