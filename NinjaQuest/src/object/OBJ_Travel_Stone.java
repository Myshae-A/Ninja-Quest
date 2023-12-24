package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Travel_Stone extends Entity{

	public static final String objName = "Ladder";
	
	GamePanel gp;
	int pressed = 0;
	
	public OBJ_Travel_Stone(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_obstacle;
		name = objName;
		image = setup("/tiles/076",gp.tileSize,gp.tileSize);
		down1 = image;
		collision = true;
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 40;
		solidArea.height = 40;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		setDialogue();
	}
	public void setDialogue() {
		
		dialogues[0][0] = "If you want to travel to Frosty Forest, enter this\n"+objName+" again.";
		
		dialogues[1][0] = "If you want to travel to Treasure Island, press this\n"+objName+" again.";
		
		dialogues[2][0] = "Great fight! Make sure to collect from the chest!";
		
		dialogues[3][0] = "If you want to travel back to Misty Meadows, press\nthis "+objName+" again.";
		
		//dialogues[3][0] = "Be level 10 or higher to use the "+objName+"!";
		
		dialogues[4][0] = "Something is disrupting the teleportation. Wait and\nuse the "+objName+" again!";
	}
	public void interact() {
		
			if(gp.currentMap == 2) {
				if(pressed == 2) {
					
					gp.obj[2][0] = null;
					gp.obj[2][1] = null;
					
					gp.currentMap = 1;
					gp.npc[1][0].clearItems();
					gp.npc[1][0].setItems();
					
					gp.eHandler.teleport(1, 49, 49,gp.outside);
					pressed = 0;
				}
				else if(pressed == 1) {
					
					startDialogue(this,3);
					pressed = 2;
					
				}
				else if(pressed == 0) {
					
					startDialogue(this,2);
					pressed = 1;
					
				}
			}
	}
}
