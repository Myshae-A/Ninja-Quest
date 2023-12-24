package object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.image.BufferedImage;

import entity.Entity;
import main.GamePanel;

public class OBJ_Camp_Fire extends Entity {

	public static final String objName = "Camp Fire";
	
	GamePanel gp;
	BufferedImage darknessFilter;
	public float filterAlpha = 0f;
	
	public OBJ_Camp_Fire(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_obstacle;
		name = objName;
		image = setup("/objects/camp_fire_1",gp.tileSize,gp.tileSize);
		down1 = image;
		lightRadius = 200;
		collision = true;
		lightUpdated = true;
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 48;
		solidArea.height = 48;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		//getImage();
		//setLight(gp);
	}
//	public OBJ_Camp_Fire(GamePanel gp, int circleSize) {
//		super(gp);
//		this.gp = gp;
//		
//		type = type_obstacle;
//		name = objName;
//		image = setup("/objects/camp_fire_1",gp.tileSize,gp.tileSize);
//		down1 = image;
//		lightRadius = 200;
//		collision = true;
//		lightUpdated = true;
//		
//		solidArea.x = 0;
//		solidArea.y = 0;
//		solidArea.width = 48;
//		solidArea.height = 48;
//		solidAreaDefaultX = solidArea.x;
//		solidAreaDefaultY = solidArea.y;
//		
//		//Create a buffered image
//		darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
//		Graphics2D g2 = (Graphics2D)darknessFilter.getGraphics();
//		
//		//System.out.println(gp.screenWidth +" "+ gp.screenHeight);
//		// create a screen-size rectangle area?
//		Area screenArea = new Area(new Rectangle2D.Double(0,0,100,50));
//		
//		// get the center x and y of the light circle
//		int centerX = 450;
//		int centerY = 250;
//		
//		// get the top left x and y of the circle
//		double x = centerX = (circleSize/2);
//		double y = centerY = (circleSize/2);
//		
//		// create a light circle shape
//		Shape circleShape = new Ellipse2D.Double(x,y,circleSize,circleSize);
//		
//		// Create a light circle Area
//		Area lightArea = new Area(circleShape);
//		
//		// subtract the light circle from the screen rectangle
//		screenArea.subtract(lightArea);
//		
//		// set a color (black) to draw the rectangle
//		g2.setColor(new Color(0,0,0,0.95f));
//		
//		// Draw the screen rectangle without the light circle area
//		g2.fill(screenArea);
//		g2.dispose();
//	}
	public void getImage() {
		down1 = setup("/objects/camp_fire_1",gp.tileSize,gp.tileSize);
		down2 = setup("/objects/camp_fire_2",gp.tileSize,gp.tileSize);
		up1 = setup("/objects/camp_fire_3",gp.tileSize,gp.tileSize);
		up2 = setup("/objects/camp_fire_4",gp.tileSize,gp.tileSize);
	}
	public void setLight(GamePanel gp) {
		this.gp = gp;
		
		darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D)darknessFilter.getGraphics();
		
		// get the center x and y of the light circle
//		int centerX = gp.player.screenX + (gp.tileSize)/2;
//		int centerY = gp.player.screenY + (gp.tileSize)/2;
		
		// Create a graduation effect within the light circle
		Color color[] = new Color[12];
		float fraction[] = new float[12];
		
		color[0] = new Color(0,0,0.1f,0.1f); // 0f is completely transparent
		color[1] = new Color(0,0,0.1f,0.42f);
		color[2] = new Color(0,0,0.1f,0.52f);
		color[3] = new Color(0,0,0.1f,0.61f);
		color[4] = new Color(0,0,0.1f,0.69f); 
		color[5] = new Color(0,0,0.1f,0.76f);
		color[6] = new Color(0,0,0.1f,0.82f);
		color[7] = new Color(0,0,0.1f,0.87f);
		color[8] = new Color(0,0,0.1f,0.91f);
		color[9] = new Color(0,0,0.1f,0.94f);
		color[10] = new Color(0,0,0.1f,0.96f);
		color[11] = new Color(0,0,0.1f,0.98f); // 1f is completely dark
		
		fraction[0] = 0.1f;
		fraction[1] = 0.4f;
		fraction[2] = 0.5f;
		fraction[3] = 0.6f;
		fraction[4] = 0.65f;
		fraction[5] = 0.7f;
		fraction[6] = 0.75f;
		fraction[7] = 0.8f;
		fraction[8] = 0.85f;
		fraction[9] = 0.9f;
		fraction[10] = 0.95f;
		fraction[11] = 1f;
		
		// crate a garduation paint settings for the light circle
		RadialGradientPaint gPaint = new RadialGradientPaint(460,280, this.lightRadius,fraction,color);
		
		// set the gradient on g2
		g2.setPaint(gPaint);
		
		
		g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);
		
		g2.dispose();
	}
//	public void update() {
//		//setLight(gp);
//	}
//	public void draw(Graphics2D g2) {
//		
//		//g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha));
//		g2.drawImage(darknessFilter,0,0,null);
//		//g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
//		
//	}
	
}
