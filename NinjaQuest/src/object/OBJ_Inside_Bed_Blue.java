package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Inside_Bed_Blue extends Entity{

	public static final String objName = "blue bed";
	
	GamePanel gp;
	
	public OBJ_Inside_Bed_Blue(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_obstacle;
		//type = type_consumable;
		name = objName;
		image = setup("/player/ninja_up_1",gp.tileSize,gp.tileSize);
		down1 = setup("/objects/inside_bed_blue",gp.tileSize,gp.tileSize);
		collision = true;
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 48;
		solidArea.height = 48;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
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
			gp.player.getSleepingImage(image);
			return true;
		}
	}
}
