package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Coin_Gold;
import object.OBJ_Coin_Silver;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_ManaCrystal;
import object.OBJ_Mario_Star;
import object.OBJ_Monster_Ball;
import object.OBJ_Pink_Ghost;

public class MON_Bat extends Entity{

	GamePanel gp;
	
	public MON_Bat(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_monster;
		sub_type = sub_type_enemy;
		name = "Bat";
		defaultSpeed = 4;
		speed = defaultSpeed;
		maxLife = 8;
		life = maxLife;
		attack = 3;
		defense = 0;
		exp = (int)(Math.random()*5)+10;
		
		
		solidArea.x = 3;
		solidArea.y = 15;
		solidArea.width = 42;
		solidArea.height = 21;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
	public void getImage() {
		
		up1 = setup("/monster/bat_down_1", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/bat_down_2", gp.tileSize, gp.tileSize);
		down1 = setup("/monster/bat_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/bat_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/monster/bat_down_1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/bat_down_2", gp.tileSize, gp.tileSize);
		right1 = setup("/monster/bat_down_1", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/bat_down_2", gp.tileSize, gp.tileSize);
		
		up3 = setup("/monster/bat_down_1", gp.tileSize, gp.tileSize);
		up4 = setup("/monster/bat_down_2", gp.tileSize, gp.tileSize);
		down3 = setup("/monster/bat_down_1", gp.tileSize, gp.tileSize);
		down4 = setup("/monster/bat_down_2", gp.tileSize, gp.tileSize);
		left3 = setup("/monster/bat_down_1", gp.tileSize, gp.tileSize);
		left4 = setup("/monster/bat_down_2", gp.tileSize, gp.tileSize);
		right3 = setup("/monster/bat_down_1", gp.tileSize, gp.tileSize);
		right4 = setup("/monster/bat_down_2", gp.tileSize, gp.tileSize);
	}
	public void setAction() {
		
		if(onPath == true) {
			
			// check if it stops chasing
			//checkStopChasingOrNot(gp.player, 12, 100);
			
			// search the direction to go
			//searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
			
		}
		else {
			
			//check if it starts chasing
			//checkStartChasingOrNot(gp.player,5,100);
			
			// get a random direction, if its not onPath
			getRandomDirection(10);	
		}
	}
	public void damageReaction() {
		
		actionLockCounter = 0;
		//onPath = true;
	}
	public void checkDrop() {
		
		// CAST A DIE
		int i = new Random().nextInt(104);
		
		
		// SET THE MONSTER DROP
		if(i <= 1) {
			gp.playSE(21);
			if(gp.player.canObtainItem(new OBJ_Key(gp)) == true) {
				gp.ui.addMessage("Lucky, you got a key!");
			}
			else {
				gp.ui.addMessage("How unlucky!!!");
			}
			
		}
		if(i > 1 && i <= 70) {
			dropItem(new OBJ_Coin_Bronze(gp));
		}
		if(i > 70 && i <= 80) {
			dropItem(new OBJ_Coin_Silver(gp));
		}
		if(i > 80 && i <= 85) {
			dropItem(new OBJ_Coin_Gold(gp));
		}
		if(i > 85 & i <= 93) {
			dropItem(new OBJ_Heart(gp));
		}
		if(i > 93 && i <= 102) {
			dropItem(new OBJ_ManaCrystal(gp));
		}
		if(i == 103){
			dropItem(new OBJ_Pink_Ghost(gp));
		}
	}
	
}
