package object;

import java.awt.Color;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Fireball extends Projectile{
	
	public static final String objName = "Fireball";
	
	GamePanel gp;

	public OBJ_Fireball(GamePanel gp) { // took out strength variable, which added onto the fireball attackpower
		super(gp);
		this.gp = gp;
		
		image = setup("/projectile/fireball_down_1", gp.tileSize, gp.tileSize);
		type = type_projectile;
		name = objName;
		speed = 10;
		maxLife = 50;// + strength*5;
		life = maxLife;
		attack = 15;// + strength;
		knockBackPower = 6;
		useCost = 1;
		alive = false;
		description = "[" + name + "]\nThe power of fire.\nRange attack: "+attack+
				"\nKnockback: "+knockBackPower;
		durability = 500;
		price = 5000;
		if(strength == 69) { // specifically for the Pirate Boss
			speed = 12;
			maxLife = 120;
			attack = 10;
		}
		getImage();
	}
	public void getImage() {
		up1 = setup("/projectile/fireball_up_1",gp.tileSize,gp.tileSize);
		up2 = setup("/projectile/fireball_up_2",gp.tileSize,gp.tileSize);
		down1 = setup("/projectile/fireball_down_1",gp.tileSize,gp.tileSize);
		down2 = setup("/projectile/fireball_down_2",gp.tileSize,gp.tileSize);
		left1 = setup("/projectile/fireball_left_1",gp.tileSize,gp.tileSize);
		left2 = setup("/projectile/fireball_left_2",gp.tileSize,gp.tileSize);
		right1 = setup("/projectile/fireball_right_1",gp.tileSize,gp.tileSize);
		right2 = setup("/projectile/fireball_right_2",gp.tileSize,gp.tileSize);
		
		up3 = setup("/projectile/fireball_up_1",gp.tileSize,gp.tileSize);
		up4 = setup("/projectile/fireball_up_2",gp.tileSize,gp.tileSize);
		down3 = setup("/projectile/fireball_down_1",gp.tileSize,gp.tileSize);
		down4 = setup("/projectile/fireball_down_2",gp.tileSize,gp.tileSize);
		left3 = setup("/projectile/fireball_left_1",gp.tileSize,gp.tileSize);
		left4 = setup("/projectile/fireball_left_2",gp.tileSize,gp.tileSize);
		right3 = setup("/projectile/fireball_right_1",gp.tileSize,gp.tileSize);
		right4 = setup("/projectile/fireball_right_2",gp.tileSize,gp.tileSize);
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
	}
	public Color getParticleColor() {
		Color color = new Color(240, 50, 0);
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
