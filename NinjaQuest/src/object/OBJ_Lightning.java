package object;

import java.awt.Color;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Lightning extends Projectile{

	public static final String objName = "Lightning";
	
	GamePanel gp;

	public OBJ_Lightning(GamePanel gp) { // took out strength variable, which added onto the fireball attackpower
		super(gp);
		this.gp = gp;
		
		image = setup("/projectile/lightning_up_1", gp.tileSize, gp.tileSize);
		type = type_projectile;
		name = objName;
		speed = 25;
		maxLife = 30;// + strength*5;
		life = maxLife;
		attack = 20;// + strength;
		knockBackPower = 8;
		useCost = 1;
		alive = false;
		description = "[" + name + "]\nThe power of lightning.\nRange Attack: "+attack+
				"\nKnockback: "+knockBackPower;
		durability = 1000;
		price = 10000;
		getImage();
	}
	public void getImage() {
		
		up1 = setup("/projectile/lightning_up_1",gp.tileSize,gp.tileSize);
		up2 = setup("/projectile/lightning_up_2",gp.tileSize,gp.tileSize);
		down1 = setup("/projectile/lightning_down_1",gp.tileSize,gp.tileSize);
		down2 = setup("/projectile/lightning_down_2",gp.tileSize,gp.tileSize);
		left1 = setup("/projectile/lightning_left_1",gp.tileSize,gp.tileSize);
		left2 = setup("/projectile/lightning_left_2",gp.tileSize,gp.tileSize);
		right1 = setup("/projectile/lightning_right_1",gp.tileSize,gp.tileSize);
		right2 = setup("/projectile/lightning_right_2",gp.tileSize,gp.tileSize);
		
		up3 = setup("/projectile/lightning_up_1",gp.tileSize,gp.tileSize);
		up4 = setup("/projectile/lightning_up_2",gp.tileSize,gp.tileSize);
		down3 = setup("/projectile/lightning_down_1",gp.tileSize,gp.tileSize);
		down4 = setup("/projectile/lightning_down_2",gp.tileSize,gp.tileSize);
		left3 = setup("/projectile/lightning_left_1",gp.tileSize,gp.tileSize);
		left4 = setup("/projectile/lightning_left_2",gp.tileSize,gp.tileSize);
		right3 = setup("/projectile/lightning_right_1",gp.tileSize,gp.tileSize);
		right4 = setup("/projectile/lightning_right_2",gp.tileSize,gp.tileSize);
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
		Color color = new Color(240,251,62); //purple 90, 53, 127
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
