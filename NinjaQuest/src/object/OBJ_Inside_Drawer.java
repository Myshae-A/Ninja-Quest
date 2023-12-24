package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Inside_Drawer extends Entity{
	
	public static final String objName = "drawer";
	
	GamePanel gp;
	
	public OBJ_Inside_Drawer(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_obstacle;
		sub_type = sub_type_insideStuff;
		name = objName;
		image = setup("/objects/inside_drawer",gp.tileSize,gp.tileSize);
		down1 = image;
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
		
		dialogues[0][0] = "Drawers don't do anything.";
		
		dialogues[1][0] = "Um... How do you do?";
	}
	public void interact () {
		
		startDialogue(this,dialogueSet);
		
		dialogueSet++;
		
		if(dialogues[dialogueSet][0] == null) {
			
			dialogueSet = 0;
			// dialogueSet--; repeats the last set of dialogue
		}
	}
}
