package object;

import java.awt.Color;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Hero_Marksman_Bow extends Projectile{
	
	public static final String objName = "Marksman Bow";
	
	GamePanel gp;

	public OBJ_Hero_Marksman_Bow(GamePanel gp) { // once again, took out strength parameters for projectiles now, too complicated for time given
		super(gp);
		this.gp = gp;
		
		image = setup("/objects/hero_marksman_bow", gp.tileSize, gp.tileSize);
		type = type_projectile;
		name = objName;
		speed = 20;
		maxLife = 180;
		life = maxLife;
		attack = 40 + strength;
		knockBackPower = 12;
		useCost = 1;
		alive = false;
		description = "[" + name + "]\nEye of the tiger.\nThe ultimate weapon\nthat can be used for range attack: "+attack+"\nKnockback: "+knockBackPower;
		durability = 1000000000;
		price = 0;
		getImage();
	}
	public void getImage() {
		up1 = setup("/projectile/marksman_ammo_up",gp.tileSize,gp.tileSize);
		up2 = setup("/projectile/marksman_ammo_up",gp.tileSize,gp.tileSize);
		down1 = setup("/projectile/marksman_ammo_down",gp.tileSize,gp.tileSize);
		down2 = setup("/projectile/marksman_ammo_down",gp.tileSize,gp.tileSize);
		left1 = setup("/projectile/marksman_ammo_left",gp.tileSize,gp.tileSize);
		left2 = setup("/projectile/marksman_ammo_left",gp.tileSize,gp.tileSize);
		right1 = setup("/projectile/marksman_ammo_right",gp.tileSize,gp.tileSize);
		right2 = setup("/projectile/marksman_ammo_right",gp.tileSize,gp.tileSize);
		
		up3 = setup("/projectile/marksman_ammo_up",gp.tileSize,gp.tileSize);
		up4 = setup("/projectile/marksman_ammo_up",gp.tileSize,gp.tileSize);
		down3 = setup("/projectile/marksman_ammo_down",gp.tileSize,gp.tileSize);
		down4 = setup("/projectile/marksman_ammo_down",gp.tileSize,gp.tileSize);
		left3 = setup("/projectile/marksman_ammo_left",gp.tileSize,gp.tileSize);
		left4 = setup("/projectile/marksman_ammo_left",gp.tileSize,gp.tileSize);
		right3 = setup("/projectile/marksman_ammo_right",gp.tileSize,gp.tileSize);
		right4 = setup("/projectile/marksman_ammo_right",gp.tileSize,gp.tileSize);
	}
	public boolean haveResource(Entity user) {
		
		boolean haveResource = false;
		if(user.mana >= useCost) {
			haveResource = true;
		}
		return haveResource;
	}
	public void subtractResource(Entity user) {
		user.mana -= useCost;
		gp.playSE(0);
	}
	public Color getParticleColor() {
		Color color = new Color(255, 191, 0);
		return color;
	}
	public int getParticleSize() {
		int size = 10; // means 6 pixels
		return size;
	}
	public int getParticleSpeed() {
		int speed = 1;
		return speed;
	}
	public int getParticleMaxLife() {
		int maxLife = 20;
		return maxLife;
	}

}
