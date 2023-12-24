package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;
import object.OBJ_Axe;
import object.OBJ_Key;
import object.OBJ_Lantern;
import object.OBJ_Potion_Big_Blue;
import object.OBJ_Potion_Big_Red;
import object.OBJ_Potion_Blue;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;
import object.OBJ_Shield_Metal;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Blue;
import object.OBJ_Sword_Metal;
import object.OBJ_Sword_Normal;
import object.OBJ_Tent;

public class Entity {
	
	GamePanel gp;
	// BUFFERED IMAGES MOVEMENT
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2,
	up3, up4, down3, down4, left3, left4, right3, right4; // USE THIS WHEN NEEDED
	public BufferedImage guardUp, guardDown, guardLeft, guardRight;
	// BUFFERED IMAGES ATTACKING
	public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2,
	attackUp3, attackUp4, attackDown3, attackDown4, attackLeft3, attackLeft4, attackRight3, attackRight4;
	public BufferedImage image, image2, image3;
	public BufferedImage guardianField;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public Rectangle interactableArea = new Rectangle(0, 0, 48, 48);
	public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collision = false;
	public String dialogues[][] = new String[30][30]; // Change this as need be, mainly used for Bookshelves
	public Entity attacker;
	public Entity linkedEntity;
	public boolean temp = false; // for bookshelf...

	public boolean invis;
	
	// String I am just testing out new variables for  B-roll video lol
	public String valueOfAttacks =  "YOY";
	public Integer testingNYOY = 129;
	public boolean testing = false;
	// NO More necessary for the state, just writing comments
	
	// STATE
	public int worldX, worldY;
	public String direction = "down";
	public Entity user;
	public int spriteNum = 1;
	public int dialogueSet = 0;
	public int dialogueIndex = 0;
	public boolean collisionOn = false; // Start here, 4:10 on video
	public boolean invincible = false;
	public boolean attacking = false;
	public boolean alive = true;
	public boolean dying = false;
	public boolean hpBarOn = false;
	public boolean onPath = false;
	public boolean knockBack = false;
	public String knockBackDirection;
	public boolean guarding = false;
	public boolean transparent = false;
	public boolean offBalance = false;
	public Entity loot;
	public boolean opened = false;
	public boolean sleep = false;
	public boolean drawing = true;
	
	public boolean bossMusicOn = false;
	public boolean bossHalfHealth = false; // instead of inRage state
	public boolean bossLastHealth = false; // at 10% max health
	public boolean lightUpdated = false;
	
	// COUNTERS
	public int spriteCounter = 0;
	public int actionLockCounter = 0;
	public int invincibleCounter = 0;
	public int shotAvailableCounter = 0;
	int dyingCounter = 0;
	public int hpBarCounter = 0;
	int knockBackCounter = 0;
	public int guardCounter;
	int offBalanceCounter;
	public boolean myshaeBool = false;
	public int myshaeCounter = 0;
	
	// CHARACTER ATTRIBUTES
	public int lifeMaxCap;
	public int manaMaxCap;
	
	public String name;
	public int defaultSpeed;
	public int speed;
	public int maxLife;
	public int life;
	public int maxMana;
	public int mana;
	public int ammo;
	public int level;
	public int strength;
	public int dexterity;
	public int rangeAttack;
	public int attack;
	public int defense;
	public int exp;
	public int nextLevelExp;
	public int coin;
	public int motion1_duration;
	public int motion2_duration;
	public Entity currentWeapon;
	public Entity currentShield;
	public Entity currentLight;
	//public Entity currentKey;
	public Entity currentFootwear;
	public Entity currentProjectile;
	public Entity currentSpecial; // special items for classes
	public Entity currentAbility; // Abilities for classes
	public Projectile projectile;
	//public boolean boss;
	
	// ITEM ATTRIBUTES
	public ArrayList<Entity> inventory = new ArrayList<>();
	public final int maxInventorySize = 20;
	public int value;
	public int attackValue;
	public int defenseValue;
	public String description = "";
	public int useCost;
	public int price;
	public int knockBackPower = 0;
	public boolean stackable = false;
	public int amount = 1;
	public int lightRadius;
	public int durability = 100;
	
	// TYPE
	public int type; // 0 = player, 1 = npc, 2 = monster
	public final int type_player = 0;
	public final int type_npc = 1;
	public final int type_monster = 2;
	public final int type_sword = 3;
	public final int type_axe = 4;
	public final int type_shield = 5;
	public final int type_consumable = 6;
	public final int type_pickupOnly = 7;
	//public final int type_key = 8;
	//public final int type_door = 8;
	public final int type_footwear = 9;
	public final int type_boss = 10;
	public final int type_obstacle = 12;
	public final int type_light = 13;
	public final int type_projectile = 14;
	public final int type_emblem = 15;
	public final int type_special = 16;
	public final int type_ability = 17;
	public final int type_craftable = 18;
	public final int type_pickaxe = 19;
	
	// SUB-TYPE
	public int sub_type;
	public final int sub_type_enemy = 0;
	public final int sub_type_boss = 1;
	public final int sub_type_placeable = 3; // for type_craftable
	public final int sub_type_unplaceable = 4; // for type_craftable
	public final int sub_type_coin = 5;
	public final int sub_type_book = 6;
	public final int sub_type_insideStuff = 6;
	
	// class
	public int emblem;
	public final int emblem_craftsman = 0;
	public final int emblem_guardian = 1;
	public final int emblem_specialist = 2;
	public final int emblem_warrior = 3;
	public boolean canEnterDoor = false;
	
	public boolean isSelected = false; // for individual items in inventory, for placing normal items.
	public boolean alreadyHasStatItem1 = false; // for bosses, mainly pirate boss for now...
	public boolean alreadyHasStatItem2 = false; // for bosses, mainly pirate boss for now...
	public boolean dormant = false;
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	public int getScreenX() {
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		return screenX;
	}
	public int getScreenY() {
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		return screenY;
	}
	public int getLeftX() {
		return worldX + solidArea.x;
	}
	public int getRightX() {
		return worldX + solidArea.x + solidArea.width;
	}
	public int getTopY() {
		return worldY + solidArea.y;
	}
	public int getBottomY() {
		return worldY + solidArea.y + solidArea.height;
	}
	public int getCol() {
		return (worldX + solidArea.x)/gp.tileSize;
	}
	public int getRow() {
		return (worldY + solidArea.y)/gp.tileSize;
	}
	public int getCenterX() {
		int centerX = worldX + left1.getWidth()/2;
		return centerX;
	}
	public int getCenterY() {
		int centerY = worldY + up1.getHeight()/2;
		return centerY;
	}
	public int getXdistance(Entity target) {
		int xDistance = Math.abs(getCenterX() - target.getCenterX());
		return xDistance;
	}
	public int getYdistance(Entity target) {
		int yDistance = Math.abs(getCenterY() - target.getCenterY());
		return yDistance; // Stopped at 5:01 in #48 video
	}
	public int getTileDistance(Entity target) {
		int tileDistance = (getXdistance(target) + getYdistance(target))/gp.tileSize;
		return tileDistance;
	}
	public int getGoalCol(Entity target) {
		int goalCol = (target.worldX + target.solidArea.x)/gp.tileSize;
		return goalCol;
	}
	public int getGoalRow(Entity target) {
		int goalRow = (target.worldY + target.solidArea.y)/gp.tileSize;
		return goalRow;
	}
	public int getMonIndex(Entity target) {
		
		if(target.type == type_monster) {
			for(int i = 0; i < gp.monster.length; i++) {
				if(gp.monster[gp.currentMap][i] == target) {
					return i;
				}
				//System.out.println(gp.monster[gp.currentMap][i].name);
			}
		}
		
		return -1;
		
	}
	public void resetCounter() {
		
		spriteCounter = 0;
		actionLockCounter = 0;
		invincibleCounter = 0;
		shotAvailableCounter = 0;
		dyingCounter = 0;
		hpBarCounter = 0;
		knockBackCounter = 0;
		guardCounter = 0;
		offBalanceCounter = 0;
	}
	public void setLoot(Entity loot) {
		// leave blank
	}
	public void setAction() {}
	public void move(String direction) {}
	public void damageReaction() {}
	public void speak() {}
	public void facePlayer() {
		
		switch(gp.player.direction) {
		case "up":direction = "down";break;
		case "down":direction = "up";break;
		case "left":direction = "right";break;
		case "right":direction = "left";break;
		}
	}
	public void startDialogue(Entity entity, int setNum) {
		
		gp.gameState = gp.dialogueState;
		gp.ui.npc = entity;
		dialogueSet = setNum;
//		if(entity == gp.player && setNum == 1) {
//			
//		}
	}
	public void interact() {
		
	}
	public boolean use(Entity entity) {return false;}
	public void checkDrop() {}
	public void dropItem(Entity droppedItem) {
		
		for(int i = 0; i < gp.obj[1].length; i++) {
			if(gp.obj[gp.currentMap][i] == null) {
				gp.obj[gp.currentMap][i] = droppedItem;
				if(this.sub_type == sub_type_boss) {
					gp.obj[gp.currentMap][i].worldX = worldX + (this.solidArea.width/2); // rewards spawn in middle -
					gp.obj[gp.currentMap][i].worldY = worldY + (this.solidArea.height/2); // of giant bosses, insstead top left
				}
				else {
					gp.obj[gp.currentMap][i].worldX = worldX; // the dead monster's worldX & Y
					gp.obj[gp.currentMap][i].worldY = worldY;
				}
				break; // IMPORTANT
			}
		}
	}
	public Color getParticleColor() {
		Color color = null;
		return color;
	}
	public int getParticleSize() {
		int size = 0; // means 6 pixels
		return size;
	}
	public int getParticleSpeed() {
		int speed = 0;
		return speed;
	}
	public int getParticleMaxLife() {
		int maxLife = 0;
		return maxLife;
	}
	public void generateParticle(Entity generator, Entity target) {
		
		Color color = generator.getParticleColor();
		int size = generator.getParticleSize();
		int speed = generator.getParticleSpeed();
		int maxLife = generator.getParticleMaxLife();
		
		Particle p1 = new Particle(gp, target, color, size, speed, maxLife, -2, -1); // top left particle
		Particle p2 = new Particle(gp, target, color, size, speed, maxLife, 2, -1); // top right
		Particle p3 = new Particle(gp, target, color, size, speed, maxLife, -2, 1); // down left
		Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 2, 1); // down right

		gp.particleList.add(p1);
		gp.particleList.add(p2);
		gp.particleList.add(p3);
		gp.particleList.add(p4);
	}
	public void checkCollision() {
		
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
		gp.cChecker.checkEntity(this, gp.iTile);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		
		if(this.type == type_monster && contactPlayer == true) {
			
			if(gp.player.fieldOn) {
				damagePlayer(attack/2);
				
				int index = getMonIndex(this);
				gp.player.damageMonster(index,gp.player,gp.player.strength*2,8);
				
				//setKnockBack(gp.monster[gp.currentMap][index], gp.player, 8);
				//System.out.println("entity.checkCollision");
			}
			else {
				damagePlayer(attack);
			}
		}
	}
	public void update() {
		
		if(sleep == false) {
			if(knockBack == true) { // Stoped at 14:00 in video #49
				
				checkCollision();
				
				if(collisionOn == true) {
					knockBackCounter = 0;
					knockBack = false;
					speed = defaultSpeed;
				}
				else if(collisionOn == false) {
					switch(knockBackDirection) { 
					case "up":worldY -= speed;break;
					case "down":worldY += speed;break;
					case "left":worldX -= speed;break;
					case "right":worldX += speed;break;
					}
				}
				
				knockBackCounter++;
				if(knockBackCounter == 10) { // bigger #, bigger knockback
					knockBackCounter = 0;
					knockBack = false;
					speed = defaultSpeed;
				}
			}
			else if(attacking == true) {
				attacking();
			}
			else {
				setAction();
				checkCollision();
				
				// 	IF COLLISION IS FALSE, PLAYER CAN MOVE
				if(collisionOn == false) {
					switch(direction) {
					case "up":worldY -= speed;break;
					case "down":worldY += speed;break;
					case "left":worldX -= speed;break;
					case "right":worldX += speed;break;
					}
				}
				
				spriteCounter++;
				if(spriteCounter > 24) {
					if(spriteNum == 1) {
						spriteNum = 2;
					}
					else if(spriteNum == 2) {
						spriteNum = 3;
					}
					else if(spriteNum == 3) {
						spriteNum = 4;
					}
					else if(spriteNum == 4) {
						spriteNum = 1;
					}
					spriteCounter = 0;
					
				}
			}
			
			if(invincible == true) {
				invincibleCounter++;
				if(invincibleCounter > 40) {
					invincible = false;
					invincibleCounter = 0;
				}
			}
			if(shotAvailableCounter < 60) {
				shotAvailableCounter++;
			}
			if(offBalance == true) {
				offBalanceCounter++;
				if(offBalanceCounter > 60) {
					offBalance = false;
					offBalanceCounter = 0;
				}
			}
		}

	}
	public void checkAttackOrNot(int rate, int straight, int horizontal) {
		
		boolean targetInRange = false;
		int xDis = getXdistance(gp.player);
		int yDis = getYdistance(gp.player);
		
		if(gp.player.invisible == true) {
			
		}
		else {
			switch(direction) {
			case "up":
				if(gp.player.getCenterY() < getCenterY() && yDis < straight && xDis < horizontal) {
					targetInRange = true;
				}
				break;
			case "down":
				if(gp.player.getCenterY() > getCenterY() && yDis < straight && xDis < horizontal) {
					targetInRange = true;
				}
				break;
			case "left":
				if(gp.player.getCenterX() < getCenterX() && xDis < straight && yDis < horizontal) {
					targetInRange = true;
				}
				break;
			case "right":
				if(gp.player.getCenterX() > getCenterX() && xDis < straight && yDis < horizontal) {
					targetInRange = true;
				}
				break;
			}
			
			if(targetInRange == true) {
				// check if it initiates an attack
				int i = new Random().nextInt(rate);
				if(i == 0) {
					attacking = true;
					spriteNum = 1;
					spriteCounter = 0;
					shotAvailableCounter = 0;
					
//						if(this.name.equals("Skeleton")) {
//							gp.playSE(38);
//						}
//						if(this.name.equals("Orc")) {
//							gp.playSE(35);
//						}
					
				}
			}
		}
	}
	public void checkShootOrNot(int rate, int shotInterval) {
		
		int i = new Random().nextInt(rate);
		if(i == 0 && projectile.alive == false && shotAvailableCounter == shotInterval) {
			
			
			projectile.set(worldX, worldY, direction,  true,  this);
			
			// CHECK VACANCY
			for(int j = 0; j < gp.projectile[1].length;j++) {
				if(gp.projectile[gp.currentMap][j] == null) {
					gp.projectile[gp.currentMap][j] = projectile;
					break;
				}
			}
			
			shotAvailableCounter = 0;
		}
	}
	public void checkStartChasingOrNot(Entity target, int distance, int rate) {
		
		if(gp.player.invisible == true) {
			
		}
		else {
			if(getTileDistance(target) < distance) {
				int i = new Random().nextInt(rate);
				
				if(gp.player.invisible == true) { // so if the player turns the cloak on and then off
					onPath = false;
				}
				else if(i==0) {
					onPath = true;
				}
			}
		}
		
	}
	public void checkStopChasingOrNot(Entity target, int distance, int rate) {
		
		if(gp.player.invisible == true) {
			onPath = false;
		}
		else {
			if(getTileDistance(target) > distance) {
				int i = new Random().nextInt(rate);
				if(i==0) {
					onPath = false; // the rate, ex is 100, would be checked 60 times a second,
					//then when 0 would tell the enemy to stop chasing
				}
			}
			if(gp.player.invisible == true) { // so if the player turns the cloak on and then off
				onPath = false;
			}
		}
	}
	public void getRandomDirection(int interval) {
		
		actionLockCounter ++;
		
		if(actionLockCounter > interval) {
			
			Random random = new Random();
			int i = random.nextInt(100)+1; // picks up number from 1 to 100
			
			if(i <= 25) {direction = "up";}
			if(i > 25 && i <= 50) {direction = "down";}
			if(i > 50 && i <= 75) {direction = "left";}
			if(i > 75 && i <= 100) {direction = "right";}
			actionLockCounter = 0;
		}
	}
	public String getOppositeDirection(String direction) {
		
		String oppositeDirection = "";
		
		switch(direction) {
		case "up": oppositeDirection = "down";break;
		case "down": oppositeDirection = "up";break;
		case "left": oppositeDirection = "right";break;
		case "right": oppositeDirection = "left";break;
		}
		return oppositeDirection;
	}
	public void moveTowardPlayer(int interval) {
		
		actionLockCounter++;
		
		if(actionLockCounter > interval) {
			if(getXdistance(gp.player) > getYdistance(gp.player)) {
				if(gp.player.getCenterX() < getCenterX()) { // player on left side
					direction = "left";
				}
				else {
					direction = "right";
				}
			}
			else if(getXdistance(gp.player) < getYdistance(gp.player)){
				if(gp.player.getCenterY() < getCenterY()) {
					direction = "up";
				}
				else {
					direction = "down";
				}
			}
			actionLockCounter = 0;
		}
	}
	public void attacking() {
		
		spriteCounter++;
		
		if(spriteCounter <= motion1_duration) {
			spriteNum = 1;
		}
		if(spriteCounter > motion1_duration && spriteCounter <= motion2_duration) {
			spriteNum = 2;
			
			// Save the current worldX, worldY, solidArea
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			
			
			//set world y and x to top-left corner of entity
//			worldY -= solidArea.y;
//			worldX -= solidArea.x;
			
			// Adjust player's worldX/Y for the attackArea
			switch(direction) {
			case "up": worldY -= attackArea.height; break;
			case "down": worldY += attackArea.height; break; // new, might not work for bigger entities though
			case "left": worldX -= attackArea.width; break;
			case "right": worldX += attackArea.width; break;
			}
			//attackArea becomes solidArea
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			
			
			if(type == type_monster || type == type_boss) {
				if(gp.cChecker.checkPlayer(this) == true) {
					
					if(gp.player.fieldOn) {
//						gp.monster[gp.currentMap][monsterIndex].life -= gp.monster[gp.currentMap][monsterIndex].attackValue;
//						gp.monster[gp.currentMap][monsterIndex].hpBarOn = true;
						damagePlayer(attack/2);
						
						int index = getMonIndex(this);
						gp.player.damageMonster(index,gp.player,gp.player.strength*2,8);
						
						//setKnockBack(gp.monster[gp.currentMap][index], gp.player, 8);
						//System.out.println("entity.attacking");
					}
					else {
						damagePlayer(attack);
					}
				}
//				if(this.name.equals("Skeleton") && spriteCounter == motion2_duration) {
//					gp.playSE(37);
//				}
//				if(this.name.equals("Orc") && spriteCounter == motion2_duration) {
//					gp.playSE(34);
//				}
			}
			else { // Player
				// Check monster collision with the update worldX, worldY, and solidArea
				int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
				if(currentSpecial != null) {
					if(currentSpecial.name.equals("Warrior Sword")) {
						gp.player.damageMonster(monsterIndex, this, gp.player.getAttack(), currentSpecial.knockBackPower);
						
						int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
						gp.player.damageInteractiveTile(iTileIndex);
						
						int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
						gp.player.damageProjectile(projectileIndex);
					}
				}
				else if(currentWeapon != null) {
					gp.player.damageMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);
					
					int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
					gp.player.damageInteractiveTile(iTileIndex);
					
					int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
					gp.player.damageProjectile(projectileIndex);
				}
				else {
					gp.player.damageMonster(monsterIndex, this, strength, knockBackPower);
					// TOOK OUT DAMAGE I-TILES and DAMAGE MONSTERS! (So axe is useful, & ur weak w/out weapon)
				}

			}
			
//			if(currentWeapon != null) { // fast travel gimmick I discovered, lol!!!
//				worldX = currentWorldX;
//				worldY = currentWorldY;
//			}
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
		}
		if(spriteCounter > motion2_duration) {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
		
	}
	public void damagePlayer(int attack) {
		
		if(gp.player.invincible == false) {
			
			int damage = attack - gp.player.defense;
			
			//get an opposite direction of the attacker
			String canGuardDirection = getOppositeDirection(direction);
			
			if(gp.player.guarding == true && gp.player.direction.equals(canGuardDirection)) {
				
				// Parry
				if(gp.player.currentShield != null) { // will have to check if shield is null or not before trying to guard or parry
					
					if(gp.player.guardCounter < 15) { // can + this # to make Parry easier or vice versa, from 10-->15 to be easier
						damage = 0;
						gp.playSE(28);
						gp.ui.addMessage("Parry!");
						setKnockBack(this,gp.player,gp.player.getShieldValue()+1);
						offBalance = true;
						spriteCounter =- 75; // so attacking sprite returns to motion 1+, appearing frozen for a second, creating a stun effect
						invis = true;
						// Decrease durability
						gp.player.currentShield.durability -= 2;
						
								if(gp.player.currentShield.durability <= 0) {
									gp.ui.addMessage("Your "+gp.player.currentShield.name+" broke!");
									gp.player.inventory.remove(gp.player.getCurrentShieldSlot());
									
//									for(int j = 0; j < inventory.size(); j++) {
//										if(inventory.get(j).type == type_shield) {
//											gp.player.currentShield = inventory.get(j); break;
//										}
//									}
									
									//System.out.println("Got to null 1");
									gp.player.currentShield = null;
								}
								
						
					}
					else {
						
						if(gp.player.currentShield != null) {
							damage -= gp.player.getShieldValue();
							if(damage < 1) {
								damage = 1;
							}
						}
						
						if(gp.player.life <= 0) {
							setKnockBack(gp.player,this,0);
						}
						gp.playSE(27);
						
						// Decrease durability
						gp.player.currentShield.durability--;
						
						if(gp.player.currentShield.durability <= 0) {
							gp.ui.addMessage("Your "+gp.player.currentShield.name+" broke!");
							gp.player.inventory.remove(gp.player.getCurrentShieldSlot());
							
//									for(int j = 0; j < inventory.size(); j++) {
//										if(inventory.get(j).type == type_shield) {
//											gp.player.currentShield = inventory.get(j); break;
//										}
//									}
							
							//System.out.println("Got to null 2");
							gp.player.currentShield = null;
						}
					}
				}
				
			}
			else {
				//Not guarding
				gp.playSE(6);
				if(damage < 1) {
					
					if(gp.player.fieldOn) {
						damage = 0;
						//setSpecialKnockBack(this,8); // so no damage to monster here?
					}
					else {
						damage = 1;
					}
				}
			}
			
			if(damage != 0) {
				gp.player.transparent = true;
				if(gp.player.fieldOn) {
					// no knockback if u have guardian field
					//setSpecialKnockBack(this,8);
				}
				else {
					setKnockBack(gp.player, this, knockBackPower);
				}
				
			}
			
			gp.player.life -= damage;
			if(!gp.player.invisible) {
				gp.player.invincible = true;
			}
			
		}
	}
	public void setKnockBack(Entity target, Entity attacker, int knockBackPower) {
		
		this.attacker = attacker;
		target.knockBackDirection = attacker.direction;
		//target.knockBackDirection = getOppositeDirection(target.direction);
		target.speed += knockBackPower;
		target.knockBack = true;
	}
	public void setSpecialKnockBack(Entity target, int knockBackPower) {
		
		target.knockBackDirection = getOppositeDirection(target.direction);
		target.speed += knockBackPower;
		target.knockBack = true;
	}
	public boolean inCamera() {
		boolean inCamera = false;
		if (worldX + gp.tileSize*10 > gp.player.worldX - gp.player.screenX && // CHANGE THESE TO CHANGE RENDERING FOR GIANT BOSS SIZES!!!
				worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
				worldY + gp.tileSize*10 > gp.player.worldY - gp.player.screenY && // HERE TOO
				worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			inCamera = true;
		}
		return inCamera;
	}
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		// These new variables are my experimental
//		int rightOffset = gp.screenWidth - gp.player.screenX;
//		if(rightOffset > gp.worldWidth - gp.player.worldX) {
//			screenX = gp.screenWidth - (gp.worldWidth - worldX);
//		}
//		int bottomOffset = gp.screenHeight - gp.player.screenY;
//		if(bottomOffset > gp.worldHeight - gp.player.worldY) {
//			screenY = gp.screenHeight - (gp.worldHeight - worldY);
//		}
		// Up to here
		
		if (inCamera()) {
			
			int tempScreenX = getScreenX();
			int tempScreenY = getScreenY();
			
			switch(direction) {
			case "up":
				if(attacking == false) {
//					if(this.name.equals("Slime Boss")) {
//						tempScreenY = screenY - up1.getHeight();
//					}
					//tempScreenY = screenY - up1.getHeight();
					if (spriteNum == 1) {image = up1;}
					if (spriteNum == 2) {image = up2;}
					if (spriteNum == 3) {image = up3;}
					if (spriteNum == 4) {image = up4;}
				}
				if(attacking == true) {
					tempScreenY = getScreenY() - up1.getHeight(); //Ohh, noice
					if (spriteNum == 1) {image = attackUp1;}
					if (spriteNum == 2) {image = attackUp2;}
					if (spriteNum == 3) {image = attackUp3;}
					if (spriteNum == 4) {image = attackUp4;}
				}
				break;
			case "down":
				if (attacking == false) {
					if (spriteNum == 1) {image = down1;}
					if (spriteNum == 2) {image = down2;}
					if (spriteNum == 3) {image = down3;}
					if (spriteNum == 4) {image = down4;}
				}
				if (attacking == true) {
					if (spriteNum == 1) {image = attackDown1;}
					if (spriteNum == 2) {image = attackDown2;}
					if (spriteNum == 3) {image = attackDown3;}
					if (spriteNum == 4) {image = attackDown4;}
				}
				break;
			case "left":
				if(attacking == false) {
//					if(this.name.equals("Slime Boss")) {
//						tempScreenX = screenX - left1.getWidth();
//					}
					//tempScreenX = screenX - left1.getWidth();
					if (spriteNum == 1) {image = left1;}
					if (spriteNum == 2) {image = left2;}
					if (spriteNum == 3) {image = left3;}
					if (spriteNum == 4) {image = left4;}
				}
				if(attacking == true) {
					tempScreenX = getScreenX() - left1.getWidth();// *noice*
					if (spriteNum == 1) {image = attackLeft1;}
					if (spriteNum == 2) {image = attackLeft2;}
					if (spriteNum == 3) {image = attackLeft3;}
					if (spriteNum == 4) {image = attackLeft4;}
				}
				break;
			case "right":
				if(attacking == false) {
					if (spriteNum == 1) {image = right1;}
					if (spriteNum == 2) {image = right2;}
					if (spriteNum == 3) {image = right3;}
					if (spriteNum == 4) {image = right4;}
				}
				if(attacking == true) {
					if (spriteNum == 1) {image = attackRight1;}
					if (spriteNum == 2) {image = attackRight2;}
					if (spriteNum == 3) {image = attackRight3;}
					if (spriteNum == 4) {image = attackRight4;}
				}
				break;
			}
			
			// Boss HP bar
//			if(type == type_boss && hpBarOn == true) { 
//				
//				double oneScale = (double)gp.tileSize/maxLife;
//				double hpBarValue = oneScale*life;
//				
//				//System.out.println("normal if"); // Testing printed normal if, so it's running here
//				g2.setColor(new Color(35,35,35));
//				g2.fillRect(gp.tileSize, 11*gp.tileSize+12, 18*gp.tileSize + 2, 22);
//				
//				g2.setColor(new Color(255, 0, 30));
//				g2.fillRect(gp.tileSize, 11*gp.tileSize+12, 18*(int)hpBarValue, 20);
//				//System.out.println("Drawing Boss HP");
//				
//				// Okay, so the reason that the Boss HP Bar is disapearing has to do with,
//				// player distance from the boss enemy
//				if(bossHalfHealth) {
//					g2.setColor(new Color(35,35,35));
//					g2.fillRect(gp.tileSize, 11*gp.tileSize+12, 18*gp.tileSize + 2, 22);
//					
//					g2.setColor(new Color(249, 166, 2)); // orange healthbar rgb
//					g2.fillRect(gp.tileSize, 11*gp.tileSize+12, 18*(int)hpBarValue, 20);
//				}
//				if(bossLastHealth) {
//					g2.setColor(new Color(35,35,35));
//					g2.fillRect(gp.tileSize, 11*gp.tileSize+12, 18*gp.tileSize + 2, 22);
//					
//					g2.setColor(new Color(160, 32, 240)); // purple healthbar rgb
//					g2.fillRect(gp.tileSize, 11*gp.tileSize+12, 18*(int)hpBarValue, 20);
//				}
//			}
			
			if(invis) { 
				changeAlpha(g2, 0.4f);
				if(spriteCounter > 0) {
					invis = false;
				}
			}
			
			if(invincible == true) {
				hpBarOn = true;
				hpBarCounter = 0;
				changeAlpha(g2, 0.4f);
			}
			if(dying == true) {
				dyingAnimation(g2);
			}
			
			g2.drawImage(image, tempScreenX, tempScreenY, null);
			changeAlpha(g2, 1f);

		}
	}
	public void dyingAnimation(Graphics2D g2) {
		
		dyingCounter++;
		
		int i = 5;
		
		if(dyingCounter <= i) {changeAlpha(g2, 0f);}
		if(dyingCounter > i && dyingCounter <= i*2) {changeAlpha(g2, 1f);}
		if(dyingCounter > i*2 && dyingCounter <= i*3) {changeAlpha(g2, 0f);}
		if(dyingCounter > i*3 && dyingCounter <= i*4) {changeAlpha(g2, 1f);}
		if(dyingCounter > i*4 && dyingCounter <= i*5) {changeAlpha(g2, 0f);}
		if(dyingCounter > i*5 && dyingCounter <= i*6) {changeAlpha(g2, 1f);}
		if(dyingCounter > i*6 && dyingCounter <= i*7) {changeAlpha(g2, 0f);}
		if(dyingCounter > i*7 && dyingCounter <= i*8) {changeAlpha(g2, 1f);}
		if(dyingCounter > i*8) {
			alive = false;
		}
	}
	public void changeAlpha(Graphics2D g2, float alphaValue) {
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
	}
	public BufferedImage setup(String imagePath, int width, int height) {
		
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, width, height);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	public void searchPath(int goalCol, int goalRow) {
		
		// So we can get Col & row #'s of the entity
		int startCol = (worldX + solidArea.x)/gp.tileSize;
		int startRow = (worldY + solidArea.y)/gp.tileSize;
		
		gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);
		
		if(gp.pFinder.search() == true) {
			
			// Next worldX & worldY
			int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
			int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;
			
			// Entity's soildArea position
			int enLeftX = worldX + solidArea.x;
			int enRightX = worldX + solidArea.x + solidArea.width;
			int enTopY = worldY + solidArea.y;
			int enBottomY = worldY + solidArea.y + solidArea.height;
			
			if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
				direction = "up";
			}
			else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
				direction = "down";
			}
			else if(enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
				// Entity can go either left OR right
				if(enLeftX > nextX) {
					direction = "left";
				}
				if(enLeftX < nextX) {
					direction = "right";
				}
			}
			else if(enTopY > nextY && enLeftX > nextX) {
				// up or left
				direction = "up";
				checkCollision();
				if(collisionOn == true) {
					direction = "left";
				}
			}
			else if(enTopY > nextY && enLeftX < nextX) {
				// up or right
				direction = "up";
				checkCollision();
				if(collisionOn == true) {
					direction = "right";
				}		
			}
			else if(enTopY < nextY && enLeftX > nextX) {
				// down or left
				direction = "down";
				checkCollision();
				if(collisionOn == true) {
					direction = "left";
				}
			}
			else if(enTopY < nextY && enLeftX < nextX) {
				// down or right
				direction = "down";
				checkCollision();
				if(collisionOn == true) {
					direction = "right";
				}
			}
			
			// If reached the goal, stop the search
			
			// Disabled when following player
			
//			int nextCol = gp.pFinder.pathList.get(0).col;
//			int nextRow = gp.pFinder.pathList.get(0).row;
//			if(nextCol == goalCol && nextRow == goalRow) {
//				onPath = false;
//			}
		}
	}
	public int getDetected(Entity user, Entity target[][], String targetName) {
		
		int index = 999;
		
		// check the surrounding object
		int nextWorldX = user.getLeftX();
		int nextWorldY = user.getTopY();
		
		switch(user.direction) {
		case "up": nextWorldY = user.getTopY()-gp.player.speed; break;
		case "down": nextWorldY = user.getBottomY()+gp.player.speed; break;
		case "left": nextWorldX = user.getLeftX()-gp.player.speed; break;
		case "right": nextWorldX = user.getRightX()+gp.player.speed; break;
		}
		int col = nextWorldX/gp.tileSize;
		int row = nextWorldY/gp.tileSize;
		
		for(int i = 0; i < target[1].length; i++ ) {
			if(target[gp.currentMap][i] != null) {
				if(target[gp.currentMap][i].getCol() == col &&
						target[gp.currentMap][i].getRow() == row &&
						target[gp.currentMap][i].name.equals(targetName)) {
					
					index = i;
					break;
					
				}
			}
		}
		return index;
	}
	public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
		
		this.worldX = worldX;
		this.worldY = worldY;
		this.direction = direction;
		this.alive = alive;
		this.user = user;
		this.life = this.maxLife;
	}
	public boolean haveResource(Entity user) {
		boolean haveResource = false;
		return haveResource;
	}
	public void subtractResource(Entity user) {}
	public void setItems() {
		
		if(gp.currentMap == 0) {
			inventory.add(new OBJ_Potion_Red(gp));
			inventory.add(new OBJ_Potion_Blue(gp));
			inventory.add(new OBJ_Key(gp));
			inventory.add(new OBJ_Axe(gp));
			inventory.add(new OBJ_Sword_Normal(gp));
			inventory.add(new OBJ_Sword_Blue(gp));
			inventory.add(new OBJ_Shield_Wood(gp));
			inventory.add(new OBJ_Shield_Blue(gp));
			inventory.add(new OBJ_Lantern(gp));
			inventory.add(new OBJ_Tent(gp));
		}
		else if(gp.currentMap == 1) {
			inventory.add(new OBJ_Potion_Big_Red(gp));
			inventory.add(new OBJ_Potion_Big_Blue(gp));
			inventory.add(new OBJ_Key(gp));
			inventory.add(new OBJ_Axe(gp));
			inventory.add(new OBJ_Sword_Blue(gp));
			inventory.add(new OBJ_Sword_Metal(gp));
			inventory.add(new OBJ_Shield_Blue(gp));
			inventory.add(new OBJ_Shield_Metal(gp));
			inventory.add(new OBJ_Lantern(gp));
			inventory.add(new OBJ_Tent(gp));
		}
		
		
	}
	public void clearItems() {
		inventory.clear();
	}
	public void damageProjectile(int i) {
		if(i != 999) {
			Entity projectile = gp.projectile[gp.currentMap][i];
			projectile.alive = false;
			generateParticle(projectile,projectile);
		}
	}
}
