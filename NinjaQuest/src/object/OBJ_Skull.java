package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Skull extends Entity{

	public static final String objName = "Skull";
	
	GamePanel gp;
	boolean pressed = false;
	
	public OBJ_Skull(GamePanel gp) {
		super(gp);
		this.gp = gp;
			
			type = type_obstacle;
			collision = true;
			name = objName;
			value = 1000;
			down1 = setup("/objects/skull",gp.tileSize,gp.tileSize);
			
			setDialogue();
	}
	public void setDialogue() {
		
		dialogues[0][0] = "Are you ready to fight the boss?\nIf so, press again.";
		dialogues[1][0] = "Be level 15 or higher to fight the boss!";
	}
	public void interact() {
		if(gp.player.level >= 15) {
			if(pressed == false) {
				
				startDialogue(this,0);
				pressed = true;
			}
			else {
				//System.out.println("here");
				gp.stopMusic();
				gp.currentMap = 2;
				gp.eHandler.teleport(2, 25, 43, gp.indoor);
				
				pressed = false;
			}
		}
		else {
			
			startDialogue(this,1);
		}
		
	}

}
