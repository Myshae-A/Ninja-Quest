package main;

import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.xml.crypto.Data;


import ai.PathFinder;
import data.SaveLoad;
import data.Progress;
import entity.Entity;
import entity.NPC_OldMan;
import entity.Player;
import environment.EnvironmentManager;
import object.OBJ_Door_Down;
import object.OBJ_Door_Iron;
import tile.Map;
import tile.TileManager;
import tile_interactive.InteractiveTile;

public class GamePanel extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;
	
	// SCREEN SETTINGS
	final int originalTileSize = 16; //16x16 tiles
	final int scale = 3;
	
	public int tileSize = originalTileSize * scale; // 48x48 tile
	public int maxScreenCol = 20;
	public int maxScreenRow = 12;
	public int screenWidth = tileSize * maxScreenCol; // 960 pixels
	public int screenHeight = tileSize * maxScreenRow; // 576 pixels
	
	// FOR FULL SCREEN
	public int screenWidth2 = screenWidth;
	public int screenHeight2 = screenHeight;
	BufferedImage tempScreen;
	Graphics2D g2;
	public boolean fullScreenOn = false;
	
	// WORLD SETTINGS
	public int maxWorldCol;
	public int maxWorldRow;
	public int worldWidth = tileSize * maxWorldCol;
	public int worldHeight = tileSize * maxWorldRow;
	public int maxMap = 10;
	public int currentMap = 0;
	public int nextMap = 0;
	
	// FPS
	int FPS = 60;
	
	
	// TIME (I edited the "run" method and brought these out, to set timers, EX. for the dialogue screen
	public double drawInterval = 1000000000/FPS;
	public double delta = 0;
	public long lastTime = System.nanoTime();
	public long currentTime;
	public long timer = 0;
	public int drawCount = 0;
	// public double MyshaeTime = 0;
	
	public String[] mapNames = new String[maxMap];
	
	// SYSTEM
	public TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	Sound music = new Sound();
	Sound se = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public QuestHandler qH = new QuestHandler(this);
	public UI ui = new UI(this,qH);
	public EventHandler eHandler = new EventHandler(this);
	Config config = new Config(this);
	public PathFinder pFinder = new PathFinder(this);
	public EnvironmentManager eManager = new EnvironmentManager(this);
	Map map = new Map(this);
	public SaveLoad saveload = new SaveLoad(this);
	public EntityGenerator eGenerator = new EntityGenerator(this);
	public CutsceneManager csManager = new CutsceneManager(this);
	Thread gameThread;
	
	// ENITITY AND OBJECT
	public Player player = new Player(this,keyH);
	public Entity obj[][] = new Entity[maxMap][200];// 20 was too little, so increased
	public Entity npc[][] = new Entity[maxMap][25];
	public Entity monster[][] = new Entity[maxMap][50]; // used to be 20...
	public InteractiveTile iTile[][] = new InteractiveTile[maxMap][60]; // used to be 50...
	public Entity projectile[][] = new Entity[maxMap][20];
	//public ArrayList<Entity> projectileList = new ArrayList<>();
	public ArrayList<Entity> particleList = new ArrayList<>();
	ArrayList<Entity> entityList = new ArrayList<>();
	
		
	// GAME STATE
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	public final int characterState = 4; // Basically inventoryState,
	// but I don't want to change the name... for consusion's sake
	public final int optionsState = 5;
	public final int gameOverState = 6;
	public final int levelUpState = 7; // I made this one
	public final int transitionState = 8;
	public final int tradeState = 9;
	public final int sleepState = 10;
	public final int mapState = 11;
	public final int treeState = 12;
	public final int travelState = 13;
	public final int cinemaState = 14;
	public final int questState = 15;
	//for craftsman
	public final int craftsmanState = 16;
	public final int choosingState = 17; // for craft Hints
	public final int anvilState = 18;
	
	public final int pickClassState = 19;
	public final int gameCompleteState = 20;
	public final int creditState = 21;
	public final int cutsceneState = 22;
	//public final int statState = 20;
	
	Random rand = new Random(); //instance of random class
    public int upperbound = 4;
    //generate random values from 0-3
    public int randInt = rand.nextInt(upperbound);
    public boolean haveLoadedGameBefore = false;
	
	//OTHERS
	public boolean bossBattleOn = false; // use this from now on!
	public boolean skillTreeOn = false;
	public boolean bossFightOn = false;
	public boolean beatBoss = false;
	public boolean beatEntireGame = false;
	
	public int currentArea;
	public int nextArea;
	public final int outside = 50;
	public final int indoor = 51;
	public final int dungeon = 52;
	
	public int dayState;
	public final int day = 0;
	public final int dusk = 1;
	public final int night = 2;
	public final int dawn = 3;
	
	public int dayNumber;
	
	public int prevDialogue = -1; // for bookshelves
	
	
	public NPC_OldMan reference;
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setUpGame() {
		
		player.update();
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
		aSetter.setInteractiveTile();
		eManager.setup();
		
		playMusic(44);
		gameState = titleState;
		currentArea = outside;
		saveload.load();
		
		tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
		// just created a blank buffered image as long as our screen
		// meaning our game window, not our monitor
		g2 = (Graphics2D)tempScreen.getGraphics();
		
		
		
		if(fullScreenOn == true) {
			setFullScreen();
		}
		
		
	}
	public void resetGame(boolean restart) {
		
		currentArea = outside;
		removeTempEntity();
		bossBattleOn = false;
		player.setDefaultPositions();
		player.restoreStatus();
		player.resetCounter();
		aSetter.setNPC();
		aSetter.setMonster();
	
		if(restart == true) {
			player.setDefaultValues();
			aSetter.setObject();
			aSetter.setInteractiveTile();
			eManager.lighting.resetDay();
		}
		
	}
	public void setFullScreen() {
		
		// GET LOCAL SCREEN DEVICE
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice(); // getting our monitor, or laptop, local device screen size
		gd.setFullScreenWindow(Main.window);
		
		// GET THE FULL SCREEN WIDTH AND HEIGHT
		// These bottom 2 lines determine ur monitor windows screen width & height
		screenWidth2 = Main.window.getWidth();
		screenHeight2 = Main.window.getHeight();
		
	}
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null) {
			
			// ORIGINAL
//			currentTime = System.nanoTime();
//			
//			delta += (currentTime - lastTime) / drawInterval;
//			timer += (currentTime - lastTime);
//			lastTime = currentTime;
//			
//			if(delta >= 1) {
//			update();
//			drawToTempScreen(); // draws everything to the buffered image
//			drawToScreen(); // draws the buffered image to the screen
//			delta--;
//			drawCount++;
//			}
//			
//			if(timer >= 1000000000) {
//				//System.out.println("FPS:" + drawCount);
//				drawCount = 0;
//				//System.out.println(player.worldX);
//				//System.out.println(player.screenX);
//				timer = 0;
//			}
			
			//--------------------------------------------------------------
			// Miguel's Idea:
			if(nextDrawTime - System.nanoTime() <= 0) {
				update();
				drawToTempScreen(); // draws everything to the buffered image
				drawToScreen(); // draws the buffered image to the screen
				nextDrawTime += drawInterval;
			}
			
			
//			update();
//			drawToTempScreen(); // draws everything to the buffered image
//			drawToScreen();
//			
//			try {
//				double remainingTime = nextDrawTime - System.nanoTime();
//				remainingTime = remainingTime/1000000;
//				
//				if(remainingTime < 0) {
//					remainingTime = 0;
//				}
//				
////				Thread.sleep((long) remainingTime);
//				Thread.sleep(10);
//				nextDrawTime += drawInterval;
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
	}
	public void update() {
		
		if(gameState == playState) {
			// PLAYER
			player.update();
			
			// NPC
			for(int i = 0; i < npc[1].length; i++) {
				if(npc[currentMap][i] != null) {
					npc[currentMap][i].update();
				}
			}
			for(int i = 0; i < monster[1].length; i++) {
				if(monster[currentMap][i] != null) {
					if(monster[currentMap][i].alive == true && monster[currentMap][i].dying == false) {
						monster[currentMap][i].update();
					}
					if(monster[currentMap][i].alive == false) {
						monster[currentMap][i].checkDrop();
						monster[currentMap][i] = null;
					}
				}
			}
			for(int i = 0; i < projectile[1].length; i++) {
				if(projectile[currentMap][i] != null) {
					if(projectile[currentMap][i].alive == true) {
						projectile[currentMap][i].update();
					}
					if(projectile[currentMap][i].alive == false) {
						projectile[currentMap][i] = null;
					}
				}
			}
			for(int i = 0; i < particleList.size(); i++) {
				if(particleList.get(i) != null) {
					if(particleList.get(i).alive == true) {
						particleList.get(i).update();
					}
					if(particleList.get(i).alive == false) {
						particleList.remove(i);
					}
				}
			}
			for(int i = 0; i < iTile[1].length; i++) {
				if(iTile[currentMap][i] != null) {
					iTile[currentMap][i].update();
				}
			}
			eManager.update();
		}
		if(gameState == pauseState) {
			// nothing
			stopMusic();
		}
		
		
	}
	public void drawToTempScreen() {
		
		// DEBUG
				long drawStart = 0;
				if(keyH.showDebugText == true) {
					drawStart = System.nanoTime();
				}
				
				// TITLE SCREEN
				if(gameState == titleState) {
					ui.draw(g2);
				}
				//Map Screen
				else if(gameState == mapState) {
					map.drawFullMapScreen(g2);
				}
				// OTHERS
				else {
					
					// TILE
					tileM.draw(g2);
					
					// INTERACTIVE TILE
					for(int i = 0; i < iTile[1].length; i++) {
						if(iTile[currentMap][i] != null) {
							iTile[currentMap][i].draw(g2);
						}
					}
					
					// ADDS ENTITIES TO THE LIST
					
					
					for(int i = 0; i < npc[1].length; i++) {
						if (npc[currentMap][i] != null) {
							entityList.add(npc[currentMap][i]);
						}
					}
					
					for(int i = 0; i < obj[1].length; i ++) {
						if(obj[currentMap][i] != null) {
							entityList.add(obj[currentMap][i]);
						}
					}
					
					for(int i = 0; i < monster[1].length; i ++) {
						if(monster[currentMap][i] != null) {
							entityList.add(monster[currentMap][i]);
						}
					}
					for(int i = 0; i < projectile[1].length; i ++) {
						if(projectile[currentMap][i] != null) {
							entityList.add(projectile[currentMap][i]);
						}
					}
					for(int i = 0; i < particleList.size(); i ++) {
						if(particleList.get(i) != null) {
							entityList.add(particleList.get(i));
						}
					}
					
					entityList.add(player); // draws player over top all those objects and entities (Useful for when using doors)??
					
					// SORT
					Collections.sort(entityList, new Comparator<Entity>() {
						
						@Override
						public int compare(Entity e1, Entity e2) {

							int result = Integer.compare(e1.worldY,  e2.worldY);
							return result;
						}
						
					});
					
					// DRAW ENTITIES
					for(int i = 0; i <entityList.size(); i++) {
						entityList.get(i).draw(g2);
					}
					
					// EMPTY ENTITY LIST
					entityList.clear();
					
					// environment
					eManager.draw(g2);
					
					// Mini map
					map.drawMiniMap(g2);
					
					// CUTSCENE
					csManager.draw(g2);
					
					// UI
					ui.draw(g2);
					
					//Graphics gbob = ((Graphics)g2);
					//gbob.fillRect(100,100,200,200);
				}
				
				
				// DEBUG
				if(keyH.showDebugText == true) {
					long drawEnd = System.nanoTime();
					long passed = drawEnd - drawStart;
					
					g2.setFont(new Font("Arial", Font.PLAIN, 20));
					g2.setColor(Color.white);
					int x = 10;
					int y = 400;
					int lineHeight = 20;
					
					g2.drawString("WorldX" + player.worldX, x, y); y += lineHeight;
					g2.drawString("WorldY" + player.worldY, x, y); y += lineHeight;
					g2.drawString("Col" + (player.worldX + player.solidArea.x) / tileSize, x, y); y += lineHeight;
					g2.drawString("Row" + (player.worldY + player.solidArea.y) / tileSize, x, y); y += lineHeight;
					
					g2.drawString("Draw Time: " + passed, x, y); y += lineHeight;
					
//					public double drawInterval = 1000000000/FPS;
//					public double delta = 0;
//					public long lastTime = System.nanoTime();
//					public long currentTime;
//					public long timer = 0;
//					public int drawCount = 0;
					
					//g2.drawString("drawCount: " + drawCount, x, y);
					//System.out.println("Draw Time: "+passed);
				}
	}
	public void drawToScreen() {
		
		Graphics g = getGraphics();
		g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
		g.dispose();
	}
	public void playMusic(int i) {
		
		music.setFile(i);
		music.play();
		music.loop();
	}
	public void stopMusic() {
		
		music.stop();
	}
	public void decreaseMusic() {
		
		if(music.volumeScale > 0) { // decreases music to zero 5 times...
			music.volumeScale--;
		}
		
		music.checkVolume();
	}
	public void increaseMusic() { // actually, now that I think of it, we won't need this
		// im just gonna play the musics right away as how it is currently, but maybe this can be saved for later...
		
		if(music.volumeScale < 3) { // so you can keep increasing till its less than 3
			music.volumeScale++;
		}
		
		music.checkVolume();
	}
	public void setMusicVolume(int vol) { // actually, now that I think of it, we won't need this
		// im just gonna play the musics right away as how it is currently, but maybe this can be saved for later...
		
		music.volumeScale = vol;
		
		music.checkVolume();
	}
	public void stopSE() {
		se.stop();
	}
	public void playSE(int i) {
		se.setFile(i);
		se.play();
	}
	public void changeArea() { // for Teleportation
		
		stopMusic();
		
		checkAllTheMusic();
		
		aSetter.setNPC(); // mainly so rock resets back in dungeon01
		
		currentArea = nextArea;
		currentMap = nextMap;
		aSetter.setMonster();
	}
	public void checkAllTheMusic() {
		
		if(currentMap == 0) {
			if(dayState == night || dayState == dawn) {
				stopMusic();
				playMusic(49); // nighttime music
			}
			else {
				stopMusic();
				playMusic(46);
			}
		}
		else if(currentMap == 1) { // no 2,3,4 bc u can't respawn in dungeon or boss fight
			if(dayState == night || dayState == dawn) {
				stopMusic();
				playMusic(49);
			}
			else {
				stopMusic();
				playMusic(29);
			}
		}
		else if(currentMap == 2) { // no 2,3,4 bc u can't respawn in dungeon or boss fight
			if(bossBattleOn) {
				stopMusic();
				playMusic(22); // Boss song night or day
			}
		}
		else if(currentMap == 3) { // no 2,3,4 bc u can't respawn in dungeon or boss fight
			stopMusic();
			playMusic(31);
		}
		else if(currentMap == 4) { // no 2,3,4 bc u can't respawn in dungeon or boss fight
			if(bossBattleOn) {
				stopMusic();
				playMusic(68); // Boss song night or day
			}
		}
		else if(currentMap == 5) {
			if(bossBattleOn) {
				stopMusic();
				playMusic(64); // Boss song night or day
			}
			else {
				if(dayState == night || dayState == dawn) {
					stopMusic();
					playMusic(49);
				}
				else {
					stopMusic();
					playMusic(65);
				}
			}
		}
		else if(currentMap == 6) {
			if(dayState == night || dayState == dawn) {
				stopMusic();
				playMusic(49);
			}
			else {
				stopMusic();
				playMusic(43);
			}
		}
		else if(currentMap == 7) {
			if(bossBattleOn) {
				stopMusic();
				playMusic(63); // Boss song night or day
			}
		}
	}
	public void removeTempEntity() {
		
		for(int mapNum = 0; mapNum < maxMap; mapNum++) {
			
			for(int i = 0; i < obj[1].length; i++) {
				if(obj[mapNum][i] != null && obj[mapNum][i].temp == true) {
					obj[mapNum][i] = null;
				}
			}
		}
	}
	public void setRespawn() {
		
		if(currentMap == 7) {
			eHandler.teleport(6, 22, 8, outside);
		}
		else if(currentMap == 6) {
			if(player.getCol() > 49 || player.getRow() > 49) {
				eHandler.teleport(6, 49, 49, outside);
			}
			else {
				eHandler.teleport(6, 22, 8, outside);
			}
		}
		else if(currentMap == 5) {
			if(bossBattleOn == true) { // for Samurai boss
				bossBattleOn = false;
				
				stopMusic();
				checkAllTheMusic();
				
				for(int i = 0; i < obj[1].length; i++) {
					if(obj[currentMap][i] != null && obj[currentMap][i].name.equals(OBJ_Door_Iron.objName)) {
						playSE(60);
						obj[currentMap][i] = null;
					}
				}
				
				obj[5][5] = new OBJ_Door_Down(this,true);
				obj[5][5].worldX = tileSize*50;
				obj[5][5].worldY = tileSize*71;
				
				eHandler.teleport(5, 50, 72, outside);
			}
			else {
				eHandler.teleport(5, 21, 85, outside);
			}
		}
		else if(currentMap == 4) {
			eHandler.teleport(0, 12, 10, outside);
		}
		else if(currentMap == 3) {
			eHandler.teleport(0, 12, 10, outside);
		}
		else if(currentMap == 2) {
			eHandler.teleport(1, 83, 68, outside);
		}
		else if(currentMap == 1) {
			eHandler.teleport(1, 49, 49, outside);
		}
		else if(currentMap == 0) {
			eHandler.teleport(0, 23, 21, outside);
		}
	}
}
