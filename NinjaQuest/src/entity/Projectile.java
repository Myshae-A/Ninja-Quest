package entity;

import main.GamePanel;

public class Projectile extends Entity{

	Entity user;
	
	public Projectile(GamePanel gp) {
		super(gp);
	}
	public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
		
		this.worldX = worldX;
		this.worldY = worldY;
		this.direction = direction;
		this.alive = alive;
		this.user = user;
		this.life = this.maxLife;
	}
	public void update() {
		
		if(user == gp.player) {
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			if(monsterIndex != 999) {
				gp.player.damageMonster(monsterIndex, this, attack+(gp.player.rangeAttack), knockBackPower); // rangeattack increases damage
				if(gp.monster[gp.currentMap][monsterIndex].sub_type != sub_type_boss) {
					generateParticle(user.currentProjectile, gp.monster[gp.currentMap][monsterIndex]);
				}
				alive = false;
			}
		}
		if(user != gp.player) {
			boolean contactPlayer = gp.cChecker.checkPlayer(this);
			if(gp.player.invincible == false && contactPlayer == true) {
				if(gp.player.fieldOn) {
					// no damage from projectiles if you have field on. Really useful against some bosses or enemies
				}
				else {
					damagePlayer(attack);
				}
				if(gp.player.life>0) { // so particles don't generate when player dies
					generateParticle(user.projectile, user.projectile);
				}
				alive = false;
			}
		}
		// Turns out that this does NOT work as intended. Will have to work on later for now
//		if(gp.cChecker.checkProjectile(this)) {
//			alive = false;
//		}
		
		switch(direction) {
		case "up": worldY -= speed; break;
		case "down": worldY += speed; break;
		case "left": worldX -= speed; break;
		case "right": worldX += speed; break;
		}
		
		life--; //since fireball life is 80, it disappears after 80 loops,
		//which is a little more than 1 second
		if(life <= 0) {
			alive = false;
		}
		
		spriteCounter++;
		if(spriteCounter > 12) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if(spriteNum == 2) {
				spriteNum = 2;
			}
			spriteCounter = 0;
		}
	}
	public boolean haveResource(Entity user) {
		boolean haveResource = false;
		return haveResource;
	}
	public void subtractResource(Entity user) {}
}
