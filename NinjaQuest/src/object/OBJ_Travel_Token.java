package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Travel_Token extends Entity{

	public static final String objName = "Travel Token";
	
	GamePanel gp;
	
	public OBJ_Travel_Token(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_pickupOnly;
		name = objName;
		stackable = true;
		down1 = setup("/objects/travel_token",gp.tileSize,gp.tileSize);
		description = "[Travel Token]\nEnables map travel.\nHas unlimited use.\nTalk to T-Bot.";
		price = 0;
	}
	public boolean use(Entity entity) {
		
		gp.playSE(54);
		gp.player.canObtainItem(this);
		return true;
	}
}
