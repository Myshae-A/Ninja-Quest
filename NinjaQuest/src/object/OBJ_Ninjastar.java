package object;

import java.awt.Color;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Ninjastar extends Projectile{
	
	public static final String objName = "Ninja star";
	
	GamePanel gp;

	public OBJ_Ninjastar(GamePanel gp) { // once again, took out strength parameters for projectiles now, too complicated for time given
		super(gp);
		this.gp = gp;
		
		image = setup("/projectile/ninja_star01", gp.tileSize, gp.tileSize);
		type = type_projectile;
		name = objName;
		speed = 12;
		maxLife = 40;
		life = maxLife;
		attack = 10;// + strength;
		knockBackPower = 4;
		useCost = 1;
		alive = false;
		description = "[" + name + "]\nLooks best with Ninja.\nRange attack: "+attack+
				"\nKnockback: "+knockBackPower;
		durability = 250;
		price = 2500;
		getImage();
	}
	public void getImage() {
		up1 = setup("/projectile/ninja_star01",gp.tileSize,gp.tileSize);
		up2 = setup("/projectile/ninja_star02",gp.tileSize,gp.tileSize);
		down1 = setup("/projectile/ninja_star01",gp.tileSize,gp.tileSize);
		down2 = setup("/projectile/ninja_star02",gp.tileSize,gp.tileSize);
		left1 = setup("/projectile/ninja_star01",gp.tileSize,gp.tileSize);
		left2 = setup("/projectile/ninja_star02",gp.tileSize,gp.tileSize);
		right1 = setup("/projectile/ninja_star01",gp.tileSize,gp.tileSize);
		right2 = setup("/projectile/ninja_star02",gp.tileSize,gp.tileSize);
		
		up3 = setup("/projectile/ninja_star01",gp.tileSize,gp.tileSize);
		up4 = setup("/projectile/ninja_star02",gp.tileSize,gp.tileSize);
		down3 = setup("/projectile/ninja_star01",gp.tileSize,gp.tileSize);
		down4 = setup("/projectile/ninja_star02",gp.tileSize,gp.tileSize);
		left3 = setup("/projectile/ninja_star01",gp.tileSize,gp.tileSize);
		left4 = setup("/projectile/ninja_star02",gp.tileSize,gp.tileSize);
		right3 = setup("/projectile/ninja_star01",gp.tileSize,gp.tileSize);
		right4 = setup("/projectile/ninja_star02",gp.tileSize,gp.tileSize);
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
		Color color = new Color(40, 40, 40);
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
