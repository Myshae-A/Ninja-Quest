package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin_Gold extends Entity{

	public static final String objName = "Gold coin";
	
	GamePanel gp;
	
	public OBJ_Coin_Gold(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_pickupOnly;
		sub_type = sub_type_coin;
		name = objName;
		value = 100;
		stackable = true;
		down1 = setup("/objects/coin_gold",gp.tileSize,gp.tileSize);
	}
	public boolean use(Entity entity) {
		
		gp.playSE(1);
		//gp.ui.addMessage("Coin +" + value);
		gp.player.coin += value;
		return true;
	}
}
