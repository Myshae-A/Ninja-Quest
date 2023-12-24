package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_SaveStone extends Entity{

	public static final String objName = "SaveStone";
	
	GamePanel gp;
	
	public OBJ_SaveStone(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_obstacle;
		name = objName;
		image = setup("/objects/save_stone",gp.tileSize,gp.tileSize);
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
		
		dialogues[0][0] = "You saved your progress!\nMonsters now reset!";
	}
	public void interact() {
		
		startDialogue(this,0);
		gp.playSE(2);
		gp.aSetter.setMonster();
		gp.saveload.save();
	}
}
