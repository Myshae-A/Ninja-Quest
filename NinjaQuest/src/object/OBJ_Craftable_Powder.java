package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Craftable_Powder extends Entity{
	
	public static final String objName = "Powder";
	
	GamePanel gp;
	
	public OBJ_Craftable_Powder(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_craftable;
		sub_type = sub_type_unplaceable;
		stackable = true;
		name = objName;
		image = setup("/objects/craftable_powder",gp.tileSize,gp.tileSize);
		down1 = image;
		collision = true;
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 48;
		solidArea.height = 48;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
}
