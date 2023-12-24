package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Monster_Spawner extends Entity{

	public static final String objName = "Monster Spawner";
	
	GamePanel gp;
	
	public OBJ_Monster_Spawner(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_obstacle;
		name = objName;
		image = setup("/objects/monster_spawner",gp.tileSize,gp.tileSize);
		down1 = image;
		collision = true;
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 48;
		solidArea.height = 48;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		setDialogue();
	}
	public void setDialogue() {
		
		dialogues[0][0] = "Monsters have now reset!";
	}
	public void interact() {
		
		gp.eHandler.spawnerPressed = true;
		gp.gameState = gp.transitionState;
		
//		gp.aSetter.setMonster();
//		startDialogue(this,0);
//		gp.playSE(30);
//		gp.eHandler.teleport(gp.currentMap, gp.player.getCol(), gp.player.getRow(), gp.outside); // will always be spawner OUTSIDE
	}
}
