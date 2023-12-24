package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity{

	public static final String objName = "Axe";
	
	public OBJ_Axe(GamePanel gp) {
		super(gp);
		
		type = type_axe;
		name = objName;
		down1 = setup("/objects/axe", gp.tileSize, gp.tileSize);
		attackValue = 100000000; //default=1
		attackArea.width = 24;
		attackArea.height = 24;
		price = 20;
		knockBackPower = 1;
		motion1_duration = 15;
		motion2_duration = 30;
		durability = 1000000;
		
//		description = "[" + name + "]\nCan only cut DIFFERENT\nCOLORED trees!\nAttack: "+attackValue+
//				"\nKnockback: "+knockBackPower;// + "\nDurability: "+durability;
		
		description = "[" + name + "]\nCut suspicious-looking\ntrees.\nAttack: "+attackValue+
				"\nKnockback: "+knockBackPower;// + "\nDurability: "+durability;
	
	}

}
