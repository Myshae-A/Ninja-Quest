package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Eternal_Ring extends Entity{

	public static final String objName = "Eternal Ring";
	
	GamePanel gp;
	
	public OBJ_Eternal_Ring(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_consumable;
		name = objName;
		down1 = setup("/objects/EternalRing", gp.tileSize, gp.tileSize);
		description = "["+name+"]\nDeath is defeated.\nResets life & ammo.";
		price = 20; // 10 shekels of silver reference
		
		setDialogue();
	}
	public void setDialogue() {
		dialogues[0][0] = "As long as you hold this, you cannot die.";
	}
	public boolean use(Entity entity) {
		
		startDialogue(this,0);
		gp.player.life = gp.player.maxLife;
		gp.player.mana = gp.player.maxMana;
		return false;
	}
}
