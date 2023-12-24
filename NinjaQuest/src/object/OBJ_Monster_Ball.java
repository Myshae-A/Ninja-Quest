package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Monster_Ball extends Entity{

	public static final String objName = "Monster Ball";
	
	GamePanel gp;
	
	public OBJ_Monster_Ball(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_consumable;
		name = objName;
		stackable = false;
		value = 4;
		down1 = setup("/objects/rare_monsterBall", gp.tileSize, gp.tileSize);
		description = "["+name+"]\nGotta catch 'em all.";
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
