package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Craftable_Stone extends Entity{
	
	public static final String objName = "Stone";
	
	GamePanel gp;
	
	public OBJ_Craftable_Stone(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_craftable;
		sub_type = sub_type_unplaceable;
		stackable = true;
		name = objName;
		image = setup("/objects/craftable_stone",gp.tileSize,gp.tileSize);
		down1 = image;
		description = "["+name+"]\nCraftable with Crafting\nTable.";
		collision = true;
		price = 60;
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 48;
		solidArea.height = 48;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
}
