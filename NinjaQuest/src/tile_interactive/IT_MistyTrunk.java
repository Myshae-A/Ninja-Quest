package tile_interactive;

import main.GamePanel;

public class IT_MistyTrunk extends InteractiveTile{

GamePanel gp;
	
	public IT_MistyTrunk(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		
		this.worldX = gp.tileSize * col;
		this.worldY = gp.tileSize * row;
		
		down1 = setup("/tiles_interactive/mistyTrunk",gp.tileSize,gp.tileSize);
		
		// Setting no solid area
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 0;
		solidArea.height = 0;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
}
