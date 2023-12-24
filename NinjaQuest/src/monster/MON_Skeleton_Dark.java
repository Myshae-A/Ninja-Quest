package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Emerald;
import object.OBJ_Coin_Gold;
import object.OBJ_Key;
import object.OBJ_Mario_Star;
import object.OBJ_Monster_Ball;
import object.OBJ_Pink_Ghost;

public class MON_Skeleton_Dark extends Entity{

	GamePanel gp;
	
	public MON_Skeleton_Dark(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_monster;
		sub_type = sub_type_enemy;
		name = "Elite Skeleton"; // meant for final island... Try to make it a different color than purple...
		defaultSpeed = 2;
		speed = defaultSpeed;
		maxLife = 96;
		life = maxLife;
		attack = 4;
		defense = 3;
		exp = (int)(Math.random()*75)+150;
		knockBackPower = 7;
		
		solidArea.x = 1;
		solidArea.y = 1;
		solidArea.width = 46;
		solidArea.height = 46;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		attackArea.width = 48;
		attackArea.height = 48;
		motion1_duration = 35;
		motion2_duration = 75;
		
		getImage();
		getAttackImage();
	}
	public void getImage() {
		
		up1 = setup("/monster/finalSkeleton_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/finalSkeleton_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/monster/finalSkeleton_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/finalSkeleton_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/monster/finalSkeleton_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/finalSkeleton_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/monster/finalSkeleton_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/finalSkeleton_right_2", gp.tileSize, gp.tileSize);
		
		up3 = setup("/monster/finalSkeleton_up_1", gp.tileSize, gp.tileSize);
		up4 = setup("/monster/finalSkeleton_up_2", gp.tileSize, gp.tileSize);
		down3 = setup("/monster/finalSkeleton_down_1", gp.tileSize, gp.tileSize);
		down4 = setup("/monster/finalSkeleton_down_2", gp.tileSize, gp.tileSize);
		left3 = setup("/monster/finalSkeleton_left_1", gp.tileSize, gp.tileSize);
		left4 = setup("/monster/finalSkeleton_left_2", gp.tileSize, gp.tileSize);
		right3 = setup("/monster/finalSkeleton_right_1", gp.tileSize, gp.tileSize);
		right4 = setup("/monster/finalSkeleton_right_2", gp.tileSize, gp.tileSize);
	}
	public void getAttackImage() {
		
		attackUp1 = setup("/monster/finalSkeleton_attack_up_1", gp.tileSize, gp.tileSize*2);
		attackUp2 = setup("/monster/finalSkeleton_attack_up_2", gp.tileSize, gp.tileSize*2);
		attackDown1 = setup("/monster/finalSkeleton_attack_down_1", gp.tileSize, gp.tileSize*2);
		attackDown2 = setup("/monster/finalSkeleton_attack_down_2", gp.tileSize, gp.tileSize*2);
		attackLeft1 = setup("/monster/finalSkeleton_attack_left_1", gp.tileSize*2, gp.tileSize);
		attackLeft2 = setup("/monster/finalSkeleton_attack_left_2", gp.tileSize*2, gp.tileSize);
		attackRight1 = setup("/monster/finalSkeleton_attack_right_1", gp.tileSize*2, gp.tileSize);
		attackRight2 = setup("/monster/finalSkeleton_attack_right_2", gp.tileSize*2, gp.tileSize);
	}
	public void setAction() {
		
		if(onPath == true) {
			
			// check if it stops chasing
			checkStopChasingOrNot(gp.player, 24, 100);
			
			// search the direction to go
			searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
			
		}
		else {
			
			//check if it starts chasing
			checkStartChasingOrNot(gp.player,10,100);
			
			// get a random direction, if its not onPath
			getRandomDirection(90);	
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
		
		gp.playSE(36);
		
		// CAST A DIE
		int i = new Random().nextInt(104);
		
		// SET THE MONSTER DROP
		if(i <= 4) {
			gp.playSE(21);
			dropItem(new OBJ_Coin_Emerald(gp));
			
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
		if(i > 30 && i <= 102) {
			dropItem(new OBJ_Coin_Gold(gp));
		}
		if(i == 103){
			dropItem(new OBJ_Monster_Ball(gp));
		}
	}
}
