package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lantern extends Entity{

	public static final String objName = "Normal Lantern";
	
	public OBJ_Lantern(GamePanel gp) {
		super(gp);
		
		type = type_light;
		name = objName;
		lightRadius = 100;
		down1 = setup("/objects/lantern",gp.tileSize, gp.tileSize);
		description = "[Lantern]\nIlluminates your\nsurroundings.\nLight Radius: "+lightRadius;
		price = 400;
		
	}
}
