package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lantern_Emerald extends Entity{

	public static final String objName = "Green Lantern";
	
	public OBJ_Lantern_Emerald(GamePanel gp) {
		super(gp);
		
		type = type_light;
		name = objName;
		lightRadius = 400;
		down1 = setup("/objects/lantern_emerald",gp.tileSize, gp.tileSize);
		description = "["+name+"]\nMade from emerald.\nLight Radius: "+lightRadius;
		price = 3200;
		
	}
}
