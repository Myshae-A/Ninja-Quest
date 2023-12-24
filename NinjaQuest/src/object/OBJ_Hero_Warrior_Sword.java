package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Hero_Warrior_Sword extends Entity{
	
	public static final String objName = "Warrior Sword";
	
	public OBJ_Hero_Warrior_Sword(GamePanel gp) {
		super(gp);
		
		type = type_special;
		name = objName;
		down1 = setup("/objects/hero_warrior_sword", gp.tileSize, gp.tileSize);
		attackValue = 20;
		attackArea.width = 36;
		attackArea.height = 40; // Slightly longer attack height
		price = 0;
		knockBackPower = 6;
		motion1_duration = 3;
		motion2_duration = 15;
		durability = 1000000000; // should be infinite, but then what's the point of the Eternal Blade?
		
		description = "[" + name + "]\nFor warriors only.\nAttack: "+attackValue+"+"+
				"\nKnockback: "+knockBackPower;// + "\nDurability: "+durability;
	}
}
