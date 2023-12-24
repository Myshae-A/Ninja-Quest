package tile;

import main.GamePanel;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Map extends TileManager{
	GamePanel gp;
	BufferedImage worldMap[];
	public boolean miniMapOn = false;
	
	public Map(GamePanel gp) {
		super(gp);
		this.gp = gp;
		createWorldMap();
	}
	public void createWorldMap() {
		
		worldMap = new BufferedImage[gp.maxMap];
		
//		 final int maxDimension = Math.max(gp.maxWorldCol, gp.maxWorldRow);
//		 final int scaledTileSize = gp.tileSize * 40 / maxDimension;
//		 final int worldMapWidth = scaledTileSize * gp.maxWorldCol;
//		 final int worldMapHeight = scaledTileSize * gp.maxWorldRow;
				
//		int worldMapWidth = gp.tileSize * gp.maxWorldCol;
//		int worldMapHeight = gp.tileSize * gp.maxWorldRow;
		//-Xmx12G;
		
		for(int i = 0; i < gp.maxMap; i++) {
			
			final int maxCol = mapTileNum[i][0].length;
			final int maxRow = mapTileNum[i].length;
			
//			System.out.println("maxCOl: "+maxCol);
//			System.out.println("maxRow: "+maxRow);
			
			final int maxDimension = Math.max(maxCol, maxRow);
			int scaledTileSize = gp.tileSize * 40 / maxDimension;
			final int worldMapWidth = scaledTileSize * maxCol;
			final int worldMapHeight = scaledTileSize * maxRow;
			
//			if(i != 5) {// noice, this fixed the problem!
//				scaledTileSize*=1; //for 250x250maps *= 2
//			}
			if(i != 1 && i != 5 && i != 6) {
				scaledTileSize*=2;
			}
			else {
				scaledTileSize*=1;
			}
			
			worldMap[i] = new BufferedImage(worldMapWidth, worldMapHeight, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = (Graphics2D)worldMap[i].createGraphics();
			
			int col = 0;
			int row = 0;
			
			
			while(col < maxCol && row < maxRow) {
				
				int tileNum = mapTileNum[i][col][row];
				
				final int x = scaledTileSize * col;
			    final int y = scaledTileSize * row;
			    g2.drawImage(tile[tileNum].image, x, y, scaledTileSize, scaledTileSize, null);
				
				col++;
				if(col == maxCol) {
					col = 0;
					row++;
				}
			}
			g2.setColor(Color.RED);
			//g2.fillRect(0,0,worldMapWidth,worldMapHeight);
			g2.dispose();
		}
	}
	public void drawFullMapScreen(Graphics2D g2) {
		
		// Background color
		g2.setColor(Color.black);
		g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
		
		// Draw map
		int width = 576;
		int height = 576;
		int x = gp.screenWidth/2 - width/2;
		int y = gp.screenHeight/2 - height/2;
		g2.drawImage(worldMap[gp.currentMap],x,y,width,height,null);
		
		// Draw Player 
		double scale = 0.0;
		if(gp.currentMap == 1 || gp.currentMap == 5 || gp.currentMap == 6) {
			scale = (double)(gp.tileSize*100)/width;
		}
		else {
			scale = (double)(gp.tileSize*50)/width;
		}
		int playerX = (int)(x+gp.player.worldX/scale-18);
		int playerY = (int)(y+gp.player.worldY/scale-24);
		int playerSize = (int)(gp.tileSize);
		g2.drawImage(gp.player.down1,playerX,playerY,playerSize,playerSize,null);
		
		// Hint message
		g2.setFont(gp.ui.maruMonica.deriveFont(32f));
		g2.setColor(Color.white);
		g2.drawString("[M] leave map",778,548);
		String text = "";
		int textX = -1;
		
		if(gp.currentMap == 7) {
			text = "Home";
			textX = gp.ui.getXForCenteredText(text);
			g2.drawString(text,textX,30);
		}
		else if(gp.currentMap == 6) {
			text = "Home";
			textX = gp.ui.getXForCenteredText(text);
			g2.drawString(text,textX,30);
		}
		else if(gp.currentMap == 5) {
			text = "Final Island";
			textX = gp.ui.getXForCenteredText(text);
			g2.drawString(text,textX,30);
		}
		else if(gp.currentMap == 4) {
			text = "Beginner's Isle - Dungeon 2";
			textX = gp.ui.getXForCenteredText(text);
			g2.drawString(text,textX,30);
		}
		else if(gp.currentMap == 3) {
			text = "Beginner's Isle - Dungeon 1";
			textX = gp.ui.getXForCenteredText(text);
			g2.drawString(text,textX,30);
		}
		else if(gp.currentMap == 2) {
			text = "Frosty Forest - Pirate Cave";
			textX = gp.ui.getXForCenteredText(text);
			g2.drawString(text,textX,30);
		}
		else if(gp.currentMap == 1) {
			text = "Frosty Forest";
			textX = gp.ui.getXForCenteredText(text);
			g2.drawString(text,textX,30);
		}
		else if(gp.currentMap == 0) {
			text = "Beginner's Isle";
			textX = gp.ui.getXForCenteredText(text);
			g2.drawString(text,textX,30);
		}
		
		
	}
	public void drawMiniMap(Graphics2D g2) {
		
		if(miniMapOn == true) {
			
			// Draw map
			int width = 200;
			int height = 200;
			int x = gp.screenWidth-width-50;
			int y = 50;
			
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.8f));
			g2.drawImage(worldMap[gp.currentMap],x,y,width,height,null);
			
			// Draw Player 
			double scale = (double)(gp.tileSize*gp.maxWorldCol)/width;
			int playerX = (int)(x+gp.player.worldX/scale);
			int playerY = (int)(y+gp.player.worldY/scale);
			int playerSize = (int)(gp.tileSize/3);
			g2.drawImage(gp.player.down1,playerX-5,playerY-5,playerSize,playerSize,null);
			
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
		}
	}
}
