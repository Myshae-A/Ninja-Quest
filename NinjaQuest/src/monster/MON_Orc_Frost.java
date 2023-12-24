package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Gold;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_ManaCrystal;
import object.OBJ_Mario_Star;
import object.OBJ_Monster_Ball;
import object.OBJ_Pink_Ghost;
import object.OBJ_Shield_Metal;

public class MON_Orc_Frost extends Entity{

	GamePanel gp;
	
	public MON_Orc_Frost(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_monster;
		sub_type = sub_type_enemy;
		name = "Frost Orc";
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 96;
		life = maxLife;
		attack = 4;
		defense = 1;
		exp = (int)(Math.random()*40)+100;
		knockBackPower = 6;
		
		solidArea.x = 1;
		solidArea.y = 1;
		solidArea.width = 47;
		solidArea.height = 47;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		attackArea.width = 48;
		attackArea.height = 48;
		motion1_duration = 40;
		motion2_duration = 85;
		
		getImage();
		getAttackImage();
	}
	public void getImage() {
		
		up1 = setup("/monster/frostOrc_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/frostOrc_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/monster/frostOrc_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/frostOrc_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/monster/frostOrc_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/frostOrc_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/monster/frostOrc_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/frostOrc_right_2", gp.tileSize, gp.tileSize);
		
		up3 = setup("/monster/frostOrc_up_1", gp.tileSize, gp.tileSize);
		up4 = setup("/monster/frostOrc_up_2", gp.tileSize, gp.tileSize);
		down3 = setup("/monster/frostOrc_down_1", gp.tileSize, gp.tileSize);
		down4 = setup("/monster/frostOrc_down_2", gp.tileSize, gp.tileSize);
		left3 = setup("/monster/frostOrc_left_1", gp.tileSize, gp.tileSize);
		left4 = setup("/monster/frostOrc_left_2", gp.tileSize, gp.tileSize);
		right3 = setup("/monster/frostOrc_right_1", gp.tileSize, gp.tileSize);
		right4 = setup("/monster/frostOrc_right_2", gp.tileSize, gp.tileSize);
	}
	public void getAttackImage() {
		
		attackUp1 = setup("/monster/frostOrc_attack_up_1", gp.tileSize, gp.tileSize*2);
		attackUp2 = setup("/monster/frostOrc_attack_up_2", gp.tileSize, gp.tileSize*2);
		attackDown1 = setup("/monster/frostOrc_attack_down_1", gp.tileSize, gp.tileSize*2);
		attackDown2 = setup("/monster/frostOrc_attack_down_2", gp.tileSize, gp.tileSize*2);
		attackLeft1 = setup("/monster/frostOrc_attack_left_1", gp.tileSize*2, gp.tileSize);
		attackLeft2 = setup("/monster/frostOrc_attack_left_2", gp.tileSize*2, gp.tileSize);
		attackRight1 = setup("/monster/frostOrc_attack_right_1", gp.tileSize*2, gp.tileSize);
		attackRight2 = setup("/monster/frostOrc_attack_right_2", gp.tileSize*2, gp.tileSize);
	}
	public void setAction() {
		
		if(onPath == true) {
			
			//check if it stops chasing
			checkStopChasingOrNot(gp.player, 12, 100);
			
			// search the direction to go
			searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
		}
		else {
			
			//check if it starts chasing
			checkStartChasingOrNot(gp.player,6,100);
			
			// get a random direction, if its not onPath
			getRandomDirection(120);	
		}
		
		// check if it attacks
		if(attacking == false) {
			checkAttackOrNot(30,gp.tileSize*4, gp.tileSize);
			// if u want monster to be very aggresive, pass a small rate # here, and vice versa
		}
	}
	public void damageReaction() {
		
		actionLockCounter = 0;
		
		if(!gp.player.invisible) {
			onPath = true;
		}
	}
	public void checkDrop() {
		
		gp.playSE(33);
		
		// CAST A DIE
		int i = new Random().nextInt(104);
		
		// SET THE MONSTER DROP
		if(i <= 4) {
			gp.playSE(21);
			if(gp.player.canObtainItem(new OBJ_Shield_Metal(gp)) == true) {
				gp.ui.addMessage("You got a Metal Shield!");
			}
			else {
				gp.ui.addMessage("How unlucky!!!");
			}
			
		}
		if(i > 4 && i <= 30) {
			gp.playSE(21);
			if(gp.player.canObtainItem(new OBJ_Key(gp)) == true) {
				gp.ui.addMessage("Lucky, you got a key!");
			}
			else {
				gp.ui.addMessage("How unlucky!!!");
			}
			
		}
		if(i > 30 && i <= 80) {
			dropItem(new OBJ_Coin_Gold(gp));
		}
		if(i > 80 & i <= 90) {
			dropItem(new OBJ_Heart(gp));
		}
		if(i > 90 && i <= 102) {
			dropItem(new OBJ_ManaCrystal(gp));
		}
		if(i == 103) {
			dropItem(new OBJ_Monster_Ball(gp));
		}
	}
}
