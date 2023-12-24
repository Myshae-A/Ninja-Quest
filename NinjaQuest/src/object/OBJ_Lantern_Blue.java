package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lantern_Blue extends Entity{

	public static final String objName = "Blue Lantern";
	
	public OBJ_Lantern_Blue(GamePanel gp) {
		super(gp);
		
		type = type_light;
		name = objName;
		lightRadius = 200;
		down1 = setup("/objects/lantern_blue",gp.tileSize, gp.tileSize);
		description = "["+name+"]\nEmits stronger light.\nLight Radius: "+lightRadius;
		price = 800;
		
	}
}
