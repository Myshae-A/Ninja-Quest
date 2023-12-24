package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Tent extends Entity{

	public static final String objName = "Tent";
	
	GamePanel gp;
	
	public OBJ_Tent(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_consumable;
		name = objName;
		down1 = setup("/objects/tent",gp.tileSize,gp.tileSize);
		description = "[Tent]\nYou can only sleep at\nnight.";
		price = 300;
		stackable = true;
	}
	public boolean use(Entity entity) {
//		int countTime = 0;
//		countTime+=gp.drawCount;
//		
//		if(gp.eManager.lighting.dayState != gp.eManager.lighting.night) {
//			//description = "You can only sleep\nat night!";
//			if(countTime >= 0) {
//				description = "[Tent]\nYou can sleep until\nnext morning";
//				return false;
//			}
//		}
		
		// attempted to create a feature where the description would change, then go back to normal after 2 seconds.
		
		if (gp.eManager.lighting.dayState != gp.eManager.lighting.night) {
			gp.ui.addMessage("You can only sleep at night!");
			return false;
		}
		else {
			gp.gameState = gp.sleepState;
			gp.playSE(26);
			gp.player.life = gp.player.maxLife;
			gp.player.mana = gp.player.maxMana;
			gp.player.getSleepingImage(down1);
			return true;
		}
	}
}
