package entity;

import main.GamePanel;

public class PlayerDummy extends Entity{

	public static final String npcName = "Dummy";
	
	public PlayerDummy(GamePanel gp) {
		super(gp);
		
		image = setup("/player/ninjaQUEST_TITLE", gp.tileSize*20, gp.tileSize*12);
		
		name = npcName;
		getImage();
	}
	public void getImage() {
		up1 = setup("/player/ninja_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/player/ninja_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/player/ninja_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/player/ninja_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/player/ninja_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/player/ninja_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/player/ninja_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/player/ninja_right_2", gp.tileSize, gp.tileSize);
		
		up3 = setup("/player/ninja_up_1", gp.tileSize, gp.tileSize);
		up4 = setup("/player/ninja_up_2", gp.tileSize, gp.tileSize);
		down3 = setup("/player/ninja_down_1", gp.tileSize, gp.tileSize);
		down4 = setup("/player/ninja_down_2", gp.tileSize, gp.tileSize);
		left3 = setup("/player/ninja_left_1", gp.tileSize, gp.tileSize);
		left4 = setup("/player/ninja_left_2", gp.tileSize, gp.tileSize);
		right3 = setup("/player/ninja_right_1", gp.tileSize, gp.tileSize);
		right4 = setup("/player/ninja_right_2", gp.tileSize, gp.tileSize);
	}
}
