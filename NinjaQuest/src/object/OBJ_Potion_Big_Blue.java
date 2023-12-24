package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Big_Blue extends Entity{

	public static final String objName = "Big Blue Potion";
	
	GamePanel gp;
	
	public OBJ_Potion_Big_Blue(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_consumable;
		name = objName;
		stackable = true;
		value = 7;
		down1 = setup("/objects/potion_big_blue", gp.tileSize, gp.tileSize);
		description = "["+name+"]\nFills ammo by " + value + ".";
		price = 350;
		
		setDialogue();
	}
	public void setDialogue() {
		
		dialogues[0][0] = "You drank the " + name + "!\n"
				+ "Your ammo has been recovered by " + value + ".";
	}
	public boolean use(Entity entity) {
		
		startDialogue(this,0);
		entity.mana += value;
		gp.playSE(20);
		return true;
	}
}
