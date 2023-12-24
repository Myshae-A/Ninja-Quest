package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Craftable_Woodenplank extends Entity{
	
	public static final String objName = "Wooden Plank";
	
	GamePanel gp;
	
	public OBJ_Craftable_Woodenplank(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_craftable;
		sub_type = sub_type_placeable;
		name = objName;
		image = setup("/objects/craftable_woodenplank",gp.tileSize,gp.tileSize);
		down1 = image;
		description = "[" + name + "]\nUse as barrier or wall.\nCan only place one\nat a time.";
		collision = true;
		stackable = false;
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 48;
		solidArea.height = 48;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		setDialogue();
	}
	public void setDialogue() {
		
		dialogues[0][0] = "You clicked the Wooden Plank!";
	}
	public void interact() {
		
		//startDialogue(this,0);
		gp.playSE(57);
		gp.player.inventory.add(new OBJ_Craftable_Woodenplank(gp));
		
		gp.obj[gp.currentMap][199].worldX = gp.tileSize*-10; //I  don't know how to null or rid item, so it gets transported off 
		gp.obj[gp.currentMap][199].worldY = gp.tileSize*-10; //the map (player can't see) giving illusion of picking and placing blocks
		gp.gameState = gp.characterState;
		gp.player.tableOn = false;
		gp.ui.subState = 0;
		//gp.playSE(61);
	}
	public boolean use() {
		return true;
	}
}
