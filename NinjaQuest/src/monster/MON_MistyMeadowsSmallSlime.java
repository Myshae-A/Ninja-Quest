package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Gold;
import object.OBJ_Coin_Silver;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_ManaCrystal;
import object.OBJ_Mario_Star;
import object.OBJ_Monster_Ball;
import object.OBJ_Pink_Ghost;
import object.OBJ_Rock;

public class MON_MistyMeadowsSmallSlime extends Entity{

	GamePanel gp;
	
	public MON_MistyMeadowsSmallSlime(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_monster;
		sub_type = sub_type_enemy;
		name = "Misty Slime"; // small light blue
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 32;
		life = maxLife;
		attack = 3;
		defense = 1;
		exp = (int)(Math.random()*15)+20;
		
		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 45;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
	public void getImage() {
		
		up1 = setup("/monster/slime_misty_down_1", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/slime_misty_down_2", gp.tileSize, gp.tileSize);
		down1 = setup("/monster/slime_misty_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/slime_misty_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/monster/slime_misty_down_1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/slime_misty_down_2", gp.tileSize, gp.tileSize);
		right1 = setup("/monster/slime_misty_down_1", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/slime_misty_down_2", gp.tileSize, gp.tileSize);
		
		up3 = setup("/monster/slime_misty_down_1", gp.tileSize, gp.tileSize);
		up4 = setup("/monster/slime_misty_down_2", gp.tileSize, gp.tileSize);
		down3 = setup("/monster/slime_misty_down_1", gp.tileSize, gp.tileSize);
		down4 = setup("/monster/slime_misty_down_2", gp.tileSize, gp.tileSize);
		left3 = setup("/monster/slime_misty_down_1", gp.tileSize, gp.tileSize);
		left4 = setup("/monster/slime_misty_down_2", gp.tileSize, gp.tileSize);
		right3 = setup("/monster/slime_misty_down_1", gp.tileSize, gp.tileSize);
		right4 = setup("/monster/slime_misty_down_2", gp.tileSize, gp.tileSize);
	}
	public void update() {
		
		super.update();
		
		int xDistance = Math.abs(worldX - gp.player.worldX);
		int yDistance = Math.abs(worldY - gp.player.worldY);
		int tileDistance = (xDistance + yDistance)/gp.tileSize;
		if(onPath == false && tileDistance < 5) {
			
			int i = new Random().nextInt(100)+1;
			if(i > 50) {
				onPath = true;
			}
		}
		if(onPath == true && tileDistance >= 7) { // 2nd condition,
			// if player runs at least 10 blocks away, monsters stop chasing
			
			onPath = false; // works, might look a little weird when
			// looking at the path, but it works.
		}
	}
	public void setAction() {
		
		
		if(onPath == true) {
			
//			// check if it stops chasing
			checkStopChasingOrNot(gp.player, 12, 100);
			
			// search the direction to go
			searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
			
			//checkShootOrNot(200, 100);
		}
		else {
			actionLockCounter ++;
			if(actionLockCounter == 120) {
				
				Random random = new Random();
				int i = random.nextInt(100)+1; // picks up number from 1 to 100
				
				if(i <= 25) {
					direction = "up";
				}
				if(i > 25 && i <= 50) {
					direction = "down";
				}
				if(i > 50 && i <= 75) {
					direction = "left";
				}
				if(i > 75 && i <= 100) {
					direction = "right";
				}
				
				actionLockCounter = 0;
			}
		}
		
	}
	public void damageReaction() {
		
		if(!gp.player.invisible) {
			onPath = true;
		}
	}
	public void checkDrop() {
		
		// CAST A DIE
		int i = new Random().nextInt(104);
		
		// SET THE MONSTER DROP
		if(i < 60) {
			dropItem(new OBJ_Coin_Silver(gp));
		}
		if(i >= 60 && i < 87) {
			dropItem(new OBJ_Coin_Gold(gp));
		}
		if(i >= 87 & i < 92) {
			dropItem(new OBJ_Heart(gp));
		}
		if(i >= 92 && i < 97) {
			dropItem(new OBJ_ManaCrystal(gp));
		}
		if(i >= 97 && i <= 102) {
			gp.playSE(21);
			if(gp.player.canObtainItem(new OBJ_Key(gp)) == true) {
				gp.ui.addMessage("Lucky, you got a key!");
			}
			else {
				gp.ui.addMessage("How unlucky!!!");
			}
		}
		if(i == 103){
			dropItem(new OBJ_Pink_Ghost(gp));
		}
	}
}
