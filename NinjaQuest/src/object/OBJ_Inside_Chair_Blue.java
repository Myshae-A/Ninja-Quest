package object;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class OBJ_Inside_Chair_Blue extends Entity{

	public static final String objName = "blue chair";
	
	GamePanel gp;
	
	public OBJ_Inside_Chair_Blue(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_obstacle;
		sub_type = sub_type_insideStuff;
		name = objName;
		image = setup("/objects/inside_chair_blue",gp.tileSize,gp.tileSize);
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
		
		dialogues[0][0] = "Sorry, chairs don't work in this game. Quite\ncomfy-looking though.";
		
		dialogues[1][0] = "Shouldn't you be adventuring-off somewhere?";
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
