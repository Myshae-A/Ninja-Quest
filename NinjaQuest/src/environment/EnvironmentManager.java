package environment;

import main.GamePanel;

import java.awt.Graphics2D;

import entity.Entity;

public class EnvironmentManager {

	GamePanel gp;
	public Lighting lighting;
	public Entity e;
	
	public EnvironmentManager(GamePanel gp) {
		this.gp = gp;
	}
	public void setup() {
		
		lighting = new Lighting(gp);
		//e = new OBJ_Camp_Fire(gp,200);
	}
	public void update() {
		
		lighting.update();
	}
	public void draw(Graphics2D g2) {
		
		lighting.draw(g2);
		//e.draw(g2);
	}
}
