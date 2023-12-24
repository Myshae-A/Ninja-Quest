package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Craftable_Anvil extends Entity{
	
	public static final String objName = "Anvil";
	
	GamePanel gp;
	
	public OBJ_Craftable_Anvil(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_craftable;
		sub_type = sub_type_placeable;
		name = objName;
		image = setup("/objects/craftable_anvil",gp.tileSize,gp.tileSize);
		down1 = image;
		description = "[" + name + "]\nCan increase durability.";
		collision = true;
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 48;
		solidArea.height = 48;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		setDialogue();
	}
	public void setDialogue() {
		
		dialogues[0][0] = "You need at least 3 wood!";
		
		dialogues[1][0] = "You need at least 3 stone!";
		
		dialogues[2][0] = "Selected item durabilities increased by 10!";
		
		dialogues[3][0] = "Selected item durabilities increased by 30!";
	}
	public void interact() {
		
		//startDialogue(this,0);
		gp.ui.subState = 1;
		gp.ui.npc = this;
		gp.gameState = gp.anvilState;
		gp.playSE(61);
	}
	public boolean use() {
		return true;
	}
	public void speak() {
		
		gp.gameState = gp.anvilState;
		
	}
}
