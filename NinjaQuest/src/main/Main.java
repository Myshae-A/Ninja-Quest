package main;

import javax.swing.JFrame;

public class Main {
	
	public static JFrame window;
	
	public static void main(String[] args) {
		
		// Game Name Ideas: "Ninja Hunter", "Treasure Hunter", "Ninja Game"
		
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("2D Adventure");
		// disable the setUndecorated here,
		// and the setFullScreen call in setUpGame
		// thats in GamePanel
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		gamePanel.config.loadConfig();
		if(gamePanel.fullScreenOn == true) {
			window.setUndecorated(true);
		}
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		gamePanel.setUpGame();
		gamePanel.startGameThread();
//		GamePanel gp = new GamePanel();
//		while(gp.gameState == gp.playState) {
//			gp.MyshaeTime += .1;
//			System.out.println(gp.MyshaeTime);
//		}
	}
}