package object;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class OBJ_Inside_Bookshelf extends Entity{

	public static final String objName = "bookshelf";
	
	GamePanel gp;
	
	public OBJ_Inside_Bookshelf(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_obstacle;
		sub_type = sub_type_insideStuff;
		name = objName;
		image = setup("/objects/inside_bookshelf",gp.tileSize,gp.tileSize);
		down1 = image;
		collision = true;
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 48;
		solidArea.height = 48;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		setDialogue();
	}
	public void setDialogue() {
		
		dialogues[0][0] = "It's year 3003.";
		
		dialogues[1][0] = "The teleporter was invented 3 centuries ago.";
		
		dialogues[2][0] = "'The Cure'. A journal entry from Dr. Maxwell.";
		dialogues[2][1] = "To my assistant Dean...";
		dialogues[2][2] = "Our planet has been infested by monsters from\nanother dimension. It all began when the teleporter\nwas invented...";
		dialogues[2][3] = "They appeared to 'teleport' out of thin air. Quite\nfascinating is it not?";
		dialogues[2][4] = "I have been studying this phenomenon, and\ndiscovered that there is a cure to rid all the\nmonsters.";
		dialogues[2][5] = "All you need is... *page is torn*.";
		
		dialogues[3][0] = "To whom it may concern...";
		dialogues[3][1] = "Monster-hunting is a renowned profession in this\nday and age.";
		dialogues[3][2] = "Those who excel in their skills can earn the title\nof a 'Hero'.";
		dialogues[3][3] = "However, earning this title requires much efforts\nand the candidates must face trials.";
		dialogues[3][4] = "If the trials are surpassed, then the candidates\nmay recieve a Class Badge. This would activate\ntheir new status.";
		
		dialogues[4][0] = "There are 4 Class badges. One for the Craftsman,\nGuardian, Warrior, and the Specialist.";
		
		dialogues[5][0] = "'The War'. By Anonymous.";
		dialogues[5][1] = "There has been a war between the monster and\nhumans. It has been ongoing for over 300 years...";
		dialogues[5][2] = "Many of the great Monster Generals have already\nbeen defeated by the Heros.";
		dialogues[5][3] = "However, one Monster General remains, and no one\nhas been able to defeat him.";
		dialogues[5][4] = "He has many Monster Bosses who serve under him.\nHe has many names, such as The Creature.";
		dialogues[5][5] = "He calls himself... Samurai!";
		
		dialogues[6][0] = "The land you are standing on has been envaded by\nthe Monster Empire!";
		dialogues[6][1] = "Scary, isn't it?";
		
		dialogues[7][0] = "What class would you want to join?";
		
		dialogues[8][0] = "You found a page of Esther's Journal!";
		dialogues[8][1] = "Dec, 31, 2999.\nI hate the injustice in this world Why must they\ntake you?!";
		dialogues[8][2] = "I promise myself today that I will find you,\nJacob!";
		dialogues[8][3] = "I'm working to become a Guardian. Please don't die\ncousin...";
		dialogues[8][4] = "I can't afford to lose another person in my life.\nI don't know if I'll be able to take it anymore...";
		dialogues[8][5] = "Note to self: Don't forget to throw this note in the\ntrash...";
		
		dialogues[9][0] = "You found a page of Daniel's Journal!";
		dialogues[9][1] = "Sep 11, 3001.\nI feel like a failure. I've been a Warrior for 1 week\nand have yet to accomplish anything.";
		dialogues[9][2] = "How will I be able to return home with pride?";
		dialogues[9][3] = "... I must defeat the Samurai. No one better take\nhim from me.";
		dialogues[9][4] = "Maybe I'll go for the Eternal Ring next. I wonder if\nit still even exists...";
		dialogues[9][5] = "I've also heard people talking about an event that\nhappened exactly 1,000 years ago, wonder what it\nwas.";
		
		dialogues[10][0] = "You found a page of Joy's Journal!";
		dialogues[10][1] = "Sep 6, 3001.\nI finished my flute performance at the city concert!\nIt was fun--";
		dialogues[10][2] = "-- but as I walked outside, monsters were\nattacking the city!";
		dialogues[10][3] = "I had to run but I wasn't fast enough. Right as a\nslime was about to get me, a shadowy figure came\nto the rescue!";
		dialogues[10][4] = "All I saw was that he had gray hair... he beat the\nmonsters and disappeared.";
		dialogues[10][5] = "So here I am, back home in my room. I've decided\nthat I want to be a Specialist--to outrun monsters!";
		
		dialogues[11][0] = "You found a page of Alden's Journal!";
		dialogues[11][1] = "July 4, 2998.\nWhy do mom and dad always argue? Is it because\nof me?";
		dialogues[11][2] = "No I will not believe that. It has to be something\nelse.";
		dialogues[11][3] = "Maybe we're too poor and they can't provide for\nme. Yes... that has to be it.";
		dialogues[11][4] = "I must become a Craftsman and craft items and\ntools for the whole world to enjoy!";
		dialogues[11][5] = "Then I'll become rich from selling my products.\nThen I'll be able to provide for my family!";
		
		dialogues[12][0] = "Many of the towns today have come to ruins or\nlost technology due to the monsters' destruction.";
		
		dialogues[13][0] = "'Auto City' is the largest city in the world!";
		dialogues[13][1] = "With a population of 50 million people, it is home\nto the greatest technological wonders of our world\ntoday.";
		dialogues[13][2] = "It is quite the popular tourist attraction as well, with\nbright lights and flying cars.";
		dialogues[13][3] = "The monsters have not been able to attack the city\neither, making it very safe as well--";
		dialogues[13][4] = "--at least from monsters, er, literal monsters...";
		
		dialogues[14][0] = "There's a mysterious chest that sits on an island.";
		dialogues[14][1] = "No one has opened it...";
		
		dialogues[15][0] = "Did you know that Frosty Forest used to be called\nMisty Meadows?";
		
		dialogues[16][0] = "The Samurai is 3003 years old.";
		
		dialogues[17][0] = "Have you gotten the trumpet yet?";
		
		dialogues[18][0] = "The King of the Pirates, Gol D. Roger!!!";
		
		dialogues[19][0] = "The Skeleton Boss's nickname is 'Dry Bones'.";
		
		dialogues[20][0] = "Have you met the plumber? He calls himself 'Mario'.";
		
		dialogues[21][0] = "Try beating all the bosses more than once...";
	}
	public void interact () {
		
		// This loops makes it so the same dialogue can't repeat twice in a row...
		int num = -1;
		while(true) {
			Random random = new Random();
			int randNum = random.nextInt(22); // should be 0 to #
			if(randNum != gp.prevDialogue) {
				num = randNum;
				gp.prevDialogue = num;
				break;
			}
		}
		
		if(num == 0) {
			startDialogue(this,0);
		}
		else if(num == 1) {
			startDialogue(this,1);
		}
		else if(num == 2) {
			startDialogue(this,2);
		}
		else if(num == 3) {
			startDialogue(this,3);
		}
		else if(num == 4) {
			startDialogue(this,4);
		}
		else if(num == 5) {
			startDialogue(this,5);
		}
		else if(num == 6) {
			startDialogue(this,6);
		}
		else if(num == 7) {
			startDialogue(this,7);
		}
		else if(num == 8) {
			startDialogue(this,8);
		}
		else if(num == 9) {
			startDialogue(this,9);
		}
		else if(num == 10) {
			startDialogue(this,10);
		}
		else if(num == 11) {
			startDialogue(this,11);
		}
		else if(num == 12) {
			startDialogue(this,12);
		}
		else if(num == 13) {
			startDialogue(this,13);
		}
		else if(num == 14) {
			startDialogue(this,14);
		}
		else if(num == 15) {
			startDialogue(this,15);
		}
		else if(num == 16) {
			startDialogue(this,16);
		}
		else if(num == 17) {
			startDialogue(this,17);
		}
		else if(num == 18) {
			startDialogue(this,18);
		}
		else if(num == 19) {
			startDialogue(this,19);
		}
		else if(num == 20) {
			startDialogue(this,20);
		}
		else if(num == 21) {
			startDialogue(this,21);
		}
		
	}
}
