package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lantern_Metal extends Entity{

	public static final String objName = "Metal Lantern";
	
	public OBJ_Lantern_Metal(GamePanel gp) {
		super(gp);
		
		type = type_light;
		name = objName;
		lightRadius = 300;
		down1 = setup("/objects/lantern_metal",gp.tileSize, gp.tileSize);
		description = "["+name+"]\nLanterns only\nincrease your light.\nLightRadius: "+lightRadius;
		price = 1600;
		
	}
}
