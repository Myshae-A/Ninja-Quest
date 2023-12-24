package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Placeholder extends Entity{

	public static final String objName = "Placeholder";
	
	GamePanel gp;
	
	public OBJ_Placeholder(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_obstacle;
		name = objName;
		image = setup("/objects/skillTreePlaceHolder",gp.tileSize,gp.tileSize);
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
