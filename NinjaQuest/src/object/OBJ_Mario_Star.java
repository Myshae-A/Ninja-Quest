package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Mario_Star extends Entity{

	public static final String objName = "Lucky Star";
	
	GamePanel gp;
	
	public OBJ_Mario_Star(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_consumable;
		name = objName;
		stackable = false;
		value = 4;
		down1 = setup("/objects/rare_luckyStar", gp.tileSize, gp.tileSize);
		description = "["+name+"]\nDoes NOT make you\ninvincible.";
		price = 10000;
		
		setDialogue();
	}
	public void setDialogue() {
		dialogues[0][0] = "";
	}
	public boolean use(Entity entity) {
		
//		startDialogue(this,0);
//		entity.life += value;
//		gp.playSE(20);
		return false;
	}
}
