package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Big_Red extends Entity{

	public static final String objName = "Big Red Potion";
	
	GamePanel gp;
	
	public OBJ_Potion_Big_Red(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_consumable;
		name = objName;
		stackable = true;
		value = 16;
		down1 = setup("/objects/potion_big_red", gp.tileSize, gp.tileSize);
		description = "["+name+"]\nHeals life by " + value + ".";
		price = 350;
		
		setDialogue();
	}
	public void setDialogue() {
		
		dialogues[0][0] = "You drank the " + name + "!\n"
				+ "Your life has been recovered by " + value + ".";
	}
	public boolean use(Entity entity) {
		
		startDialogue(this,0);
		entity.life += value;
		gp.playSE(20);
		return true;
	}
}
