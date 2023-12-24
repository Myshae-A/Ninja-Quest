package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Pink_Ghost extends Entity{

	public static final String objName = "Pink Ghost";
	
	GamePanel gp;
	
	public OBJ_Pink_Ghost(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_consumable;
		name = objName;
		stackable = false;
		value = 4;
		down1 = setup("/objects/rare_pinkGhost", gp.tileSize, gp.tileSize);
		description = "["+name+"]\nBoo!";
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
