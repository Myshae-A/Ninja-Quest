package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Map extends Entity{

	public static final String objName = "Map";
	
	GamePanel gp;
	
	public OBJ_Map(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_special;
		name = objName;
		down1 = setup("/objects/map_item", gp.tileSize, gp.tileSize);
		description = "[" + name + "]\nPress [M] to use.";
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
		
		dialogues[0][0] = "You clicked the Map!"; // this won't/shouldn't be used...
	}
	public void interact() {
		
		//startDialogue(this,0);
		gp.ui.subState = 2;
		gp.gameState = gp.mapState;
		gp.playSE(61);
	}
	public boolean use() {
		return false;
	}
}
