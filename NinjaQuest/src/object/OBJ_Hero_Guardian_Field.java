package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Hero_Guardian_Field extends Entity{

	public static final String objName = "Guardian Field";
	
	public OBJ_Hero_Guardian_Field(GamePanel gp) {
		super(gp);
		
		type = type_special;
		name = objName;
		down1 = setup("/objects/hero_guardian_field", gp.tileSize, gp.tileSize);
		
		description = "[" + name + "]\nStronger protection.\nDisables attacking.";
	}
}
