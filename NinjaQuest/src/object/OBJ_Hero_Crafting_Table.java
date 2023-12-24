package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Hero_Crafting_Table extends Entity{

	public static final String objName = "Crafting Table";
	
	GamePanel gp;
	
	public OBJ_Hero_Crafting_Table(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_special;
		name = objName;
		down1 = setup("/objects/hero_crafting_table", gp.tileSize, gp.tileSize);
		description = "[" + name + "]\nCan craft useful items.\nMust use stone or\nwood.";
		collision = true;
		price = 10000;
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 48;
		solidArea.height = 48;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		setDialogue();
	}
	public void setDialogue() {
		
		dialogues[0][0] = "You clicked the Crafting Table!";
	}
	public void interact() {
		
		startDialogue(this,0);
		gp.ui.subState = 2;
		gp.gameState = gp.craftsmanState;
		gp.playSE(61);
	}
	public boolean use() {
		return true;
	}
}
