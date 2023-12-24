package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Inside_Bed_Purple extends Entity{

	public static final String objName = "purple bed";
	
	GamePanel gp;
	
	public OBJ_Inside_Bed_Purple(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_obstacle;
		name = objName;
		image = setup("/objects/inside_bed_purple",gp.tileSize,gp.tileSize);
		down1 = image;
		collision = true;
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 48;
		solidArea.height = 48;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
	}
}
