package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door_Iron extends Entity{

	public static final String objName = "Iron Door";
	
	GamePanel gp;
	
	public OBJ_Door_Iron(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_obstacle;
		//type = type_door;
		name = objName;
		down1 = setup("/objects/door_iron", gp.tileSize, gp.tileSize);
		collision = true;
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 48;
		solidArea.height = 48;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		dialogueSet = -1;
		
		setDialogue();
	}
	public void setDialogue() {
		
		dialogues[0][0] = "It won't budge.";
		
		//dialogues[1][0] = "Leaving the dungeon resets the rocks.";
	}
	public void interact () {
		
		startDialogue(this,dialogueSet);
		
		dialogueSet++;
		
		if(dialogues[dialogueSet][0] == null) {
			dialogueSet = 0;
		}
		
	}
}
