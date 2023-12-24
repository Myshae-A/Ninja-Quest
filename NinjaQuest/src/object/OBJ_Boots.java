package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Boots extends Entity{
	
	public static final String objName = "Boots";
	
	GamePanel gp;
	
	public OBJ_Boots(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_pickupOnly;
		name = objName;
		down1 = setup("/objects/boots", gp.tileSize, gp.tileSize);
		description = "[Speed Boots]\nIncreases speed by 1.";
		speed = 1;
		price = 1250;
	}
	public boolean use(Entity entity) {
		OBJ_Boots s = new OBJ_Boots(gp);
		//dropItem(s);
		gp.player.inventory.add(s);
		gp.player.speed += speed;
		gp.playSE(21);
		return true;
	}
}
