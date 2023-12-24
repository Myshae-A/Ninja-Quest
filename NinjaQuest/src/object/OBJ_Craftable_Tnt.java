package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Craftable_Tnt extends Entity{
	
	public static final String objName = "TNT";
	
	GamePanel gp;
	
	public OBJ_Craftable_Tnt(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_craftable;
		sub_type = sub_type_placeable;
		stackable = true;
		name = objName;
		image = setup("/objects/craftable_tnt",gp.tileSize,gp.tileSize);
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
