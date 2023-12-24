package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Emblem_Craftsman extends Entity{
	
	public static final String objName = "Craftsman Emblem";
	GamePanel gp;
	
	public OBJ_Emblem_Craftsman(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_emblem;
		name = objName;
		down1 = setup("/objects/emblem_craftsman", gp.tileSize, gp.tileSize);
		image = setup("/objects/emblem_craftsman", gp.tileSize*2, gp.tileSize*2);
		defenseValue = 1;
		price = 1000000;
		//durability = 75;
		
		description = "[" + name + "]\nThe chosen class.";
	}
	public void interact() {
		gp.ui.classPicked = "Craftsman";
		gp.ui.subState = 0;
		gp.gameState = gp.pickClassState;
		
	}
}
