package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Hero_Special_Cloak extends Entity{

	public static final String objName = "Special Cloak";
	
	public OBJ_Hero_Special_Cloak(GamePanel gp) {
		super(gp);
		
		type = type_special;
		name = objName;
		down1 = setup("/objects/hero_special_cloak", gp.tileSize, gp.tileSize);
		
		description = "[" + name + "]\nCan turn invisible to\nenemies and NPCs.\nDisables attacking.";
	}
}
