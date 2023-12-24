package entity;

import java.awt.Rectangle;

import main.GamePanel;
import object.OBJ_Axe;
import object.OBJ_Fireball;
import object.OBJ_Hero_Crafting_Table;
import object.OBJ_Key;
import object.OBJ_Lantern;
import object.OBJ_Lantern_Blue;
import object.OBJ_Lantern_Emerald;
import object.OBJ_Lantern_Metal;
import object.OBJ_Map;
import object.OBJ_Ninjastar;
import object.OBJ_Potion_Big_Blue;
import object.OBJ_Potion_Big_Red;
import object.OBJ_Potion_Blue;
import object.OBJ_Potion_Max_Blue;
import object.OBJ_Potion_Max_Red;
import object.OBJ_Potion_Red;
import object.OBJ_Rock_forPlayer;
import object.OBJ_Shield_Blue;
import object.OBJ_Shield_Emerald;
import object.OBJ_Shield_Metal;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Blue;
import object.OBJ_Sword_Emerald;
import object.OBJ_Sword_Metal;
import object.OBJ_Sword_Normal;
import object.OBJ_Tent;

public class NPC_Merchant extends Entity{
	
	public NPC_Merchant(GamePanel gp) {
		super(gp);
		
		name = "Merchant";
		direction = "down";
		speed = 0;
		
		solidArea = new Rectangle();
		solidArea.x = 3;
		solidArea.y = 16;
		solidArea.width = 42;
		solidArea.height = 42;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
		setDialogue();
		setItems();
	}
	public void getImage() {
		
		if(gp.currentMap == 0) {
			up1 = setup("/npc/Merchant_Beginner'sIsle_down_1", gp.tileSize, gp.tileSize);
			up2 = setup("/npc/Merchant_Beginner'sIsle_down_2", gp.tileSize, gp.tileSize);
			down1 = setup("/npc/Merchant_Beginner'sIsle_down_1", gp.tileSize, gp.tileSize);
			down2 = setup("/npc/Merchant_Beginner'sIsle_down_2", gp.tileSize, gp.tileSize);
			left1 = setup("/npc/Merchant_Beginner'sIsle_down_1", gp.tileSize, gp.tileSize);
			left2 = setup("/npc/Merchant_Beginner'sIsle_down_2", gp.tileSize, gp.tileSize);
			right1 = setup("/npc/Merchant_Beginner'sIsle_down_1", gp.tileSize, gp.tileSize);
			right2 = setup("/npc/Merchant_Beginner'sIsle_down_2", gp.tileSize, gp.tileSize);
			
			up3 = setup("/npc/Merchant_Beginner'sIsle_down_1", gp.tileSize, gp.tileSize);
			up4 = setup("/npc/Merchant_Beginner'sIsle_down_2", gp.tileSize, gp.tileSize);
			down3 = setup("/npc/Merchant_Beginner'sIsle_down_1", gp.tileSize, gp.tileSize);
			down4 = setup("/npc/Merchant_Beginner'sIsle_down_2", gp.tileSize, gp.tileSize);
			left3 = setup("/npc/Merchant_Beginner'sIsle_down_1", gp.tileSize, gp.tileSize);
			left4 = setup("/npc/Merchant_Beginner'sIsle_down_2", gp.tileSize, gp.tileSize);
			right3 = setup("/npc/Merchant_Beginner'sIsle_down_1", gp.tileSize, gp.tileSize);
			right4 = setup("/npc/Merchant_Beginner'sIsle_down_2", gp.tileSize, gp.tileSize);
		}
		else if(gp.currentMap == 1) {
			up1 = setup("/npc/Merchant_MistyMeadows_down_1", gp.tileSize, gp.tileSize);
			up2 = setup("/npc/Merchant_MistyMeadows_down_2", gp.tileSize, gp.tileSize);
			down1 = setup("/npc/Merchant_MistyMeadows_down_1", gp.tileSize, gp.tileSize);
			down2 = setup("/npc/Merchant_MistyMeadows_down_2", gp.tileSize, gp.tileSize);
			left1 = setup("/npc/Merchant_MistyMeadows_down_1", gp.tileSize, gp.tileSize);
			left2 = setup("/npc/Merchant_MistyMeadows_down_2", gp.tileSize, gp.tileSize);
			right1 = setup("/npc/Merchant_MistyMeadows_down_1", gp.tileSize, gp.tileSize);
			right2 = setup("/npc/Merchant_MistyMeadows_down_2", gp.tileSize, gp.tileSize);
			
			up3 = setup("/npc/Merchant_MistyMeadows_down_1", gp.tileSize, gp.tileSize);
			up4 = setup("/npc/Merchant_MistyMeadows_down_2", gp.tileSize, gp.tileSize);
			down3 = setup("/npc/Merchant_MistyMeadows_down_1", gp.tileSize, gp.tileSize);
			down4 = setup("/npc/Merchant_MistyMeadows_down_2", gp.tileSize, gp.tileSize);
			left3 = setup("/npc/Merchant_MistyMeadows_down_1", gp.tileSize, gp.tileSize);
			left4 = setup("/npc/Merchant_MistyMeadows_down_2", gp.tileSize, gp.tileSize);
			right3 = setup("/npc/Merchant_MistyMeadows_down_1", gp.tileSize, gp.tileSize);
			right4 = setup("/npc/Merchant_MistyMeadows_down_2", gp.tileSize, gp.tileSize);
		}
		else if(gp.currentMap == 5) {
			up1 = setup("/npc/Merchant_FinalIsland_down_1", gp.tileSize, gp.tileSize);
			up2 = setup("/npc/Merchant_FinalIsland_down_2", gp.tileSize, gp.tileSize);
			down1 = setup("/npc/Merchant_FinalIsland_down_1", gp.tileSize, gp.tileSize);
			down2 = setup("/npc/Merchant_FinalIsland_down_2", gp.tileSize, gp.tileSize);
			left1 = setup("/npc/Merchant_FinalIsland_down_1", gp.tileSize, gp.tileSize);
			left2 = setup("/npc/Merchant_FinalIsland_down_2", gp.tileSize, gp.tileSize);
			right1 = setup("/npc/Merchant_FinalIsland_down_1", gp.tileSize, gp.tileSize);
			right2 = setup("/npc/Merchant_FinalIsland_down_2", gp.tileSize, gp.tileSize);
			
			up3 = setup("/npc/Merchant_FinalIsland_down_1", gp.tileSize, gp.tileSize);
			up4 = setup("/npc/Merchant_FinalIsland_down_2", gp.tileSize, gp.tileSize);
			down3 = setup("/npc/Merchant_FinalIsland_down_1", gp.tileSize, gp.tileSize);
			down4 = setup("/npc/Merchant_FinalIsland_down_2", gp.tileSize, gp.tileSize);
			left3 = setup("/npc/Merchant_FinalIsland_down_1", gp.tileSize, gp.tileSize);
			left4 = setup("/npc/Merchant_FinalIsland_down_2", gp.tileSize, gp.tileSize);
			right3 = setup("/npc/Merchant_FinalIsland_down_1", gp.tileSize, gp.tileSize);
			right4 = setup("/npc/Merchant_FinalIsland_down_2", gp.tileSize, gp.tileSize);
		}
		else if(gp.currentMap == 6) {
			up1 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
			up2 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
			down1 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
			down2 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
			left1 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
			left2 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
			right1 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
			right2 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
			
			up3 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
			up4 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
			down3 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
			down4 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
			left3 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
			left4 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
			right3 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
			right4 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
		}
	}
	public void setDialogue() {
		
		dialogues[0][0] = "Wanna trade?";
		
		dialogues[1][0] = "Come a-again, h-heehee!";
		
		dialogues[2][0] = "Y-you need m-more coins to buy that!";
		
		dialogues[3][0] = "You c-cannot c-carry any more!";
		
		dialogues[4][0] = "You cannot s-sell an equipped item!";
		
		dialogues[5][0] = "You can't sell t-that!";
	}
	public void setItems() {
		
		if(gp.currentMap == 0) {
			inventory.add(new OBJ_Potion_Red(gp));
			inventory.add(new OBJ_Potion_Blue(gp));
			inventory.add(new OBJ_Sword_Blue(gp));
			inventory.add(new OBJ_Shield_Blue(gp));
			inventory.add(new OBJ_Rock_forPlayer(gp));
			inventory.add(new OBJ_Lantern_Blue(gp));
			inventory.add(new OBJ_Tent(gp));
			inventory.add(new OBJ_Key(gp));
			inventory.add(new OBJ_Map(gp));
			inventory.add(new OBJ_Hero_Crafting_Table(gp));
		}
		else if(gp.currentMap == 1) {
			inventory.add(new OBJ_Potion_Big_Red(gp));
			inventory.add(new OBJ_Potion_Big_Blue(gp));
			inventory.add(new OBJ_Sword_Metal(gp));
			inventory.add(new OBJ_Shield_Metal(gp));
			inventory.add(new OBJ_Ninjastar(gp));
			inventory.add(new OBJ_Lantern_Metal(gp));
			inventory.add(new OBJ_Tent(gp));
			inventory.add(new OBJ_Key(gp));
			inventory.add(new OBJ_Map(gp));
			inventory.add(new OBJ_Hero_Crafting_Table(gp));
		}
		else if(gp.currentMap == 5) {
			inventory.add(new OBJ_Potion_Max_Red(gp));
			inventory.add(new OBJ_Potion_Max_Blue(gp));
			inventory.add(new OBJ_Sword_Emerald(gp));
			inventory.add(new OBJ_Shield_Emerald(gp));
			inventory.add(new OBJ_Fireball(gp));
			inventory.add(new OBJ_Lantern_Emerald(gp));
			inventory.add(new OBJ_Tent(gp));
			inventory.add(new OBJ_Key(gp));
			inventory.add(new OBJ_Map(gp));
			inventory.add(new OBJ_Hero_Crafting_Table(gp));
		}
		else if(gp.currentMap == 6) {
			inventory.add(new OBJ_Potion_Red(gp));
			inventory.add(new OBJ_Potion_Blue(gp));
			inventory.add(new OBJ_Axe(gp));
			inventory.add(new OBJ_Sword_Normal(gp));
			inventory.add(new OBJ_Shield_Wood(gp));
			inventory.add(new OBJ_Lantern(gp));
			inventory.add(new OBJ_Key(gp));
			inventory.add(new OBJ_Map(gp));
			inventory.add(new OBJ_Hero_Crafting_Table(gp));
		}
		
		
	}
	public void clearItems() {
		inventory.clear();
	}
	public void speak() {
		
		facePlayer(); // wont change much, but good practice for other npcs
		gp.gameState = gp.tradeState;
		gp.ui.npc = this;
	}
}
