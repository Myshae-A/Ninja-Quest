package main;

import entity.Entity;
import object.OBJ_Axe;
import object.OBJ_Boots;
import object.OBJ_Camp_Fire;
import object.OBJ_Chest;
import object.OBJ_Coin_Bronze;
import object.OBJ_Coin_Emerald;
import object.OBJ_Coin_Gold;
import object.OBJ_Coin_Silver;
import object.OBJ_Craftable_Anvil;
import object.OBJ_Craftable_Powder;
import object.OBJ_Craftable_Stone;
import object.OBJ_Craftable_Tnt;
import object.OBJ_Craftable_Wood;
import object.OBJ_Craftable_Woodenplank;
import object.OBJ_Door;
import object.OBJ_Door_Down;
import object.OBJ_Door_Iron;
import object.OBJ_Emblem_Craftsman;
import object.OBJ_Emblem_Guardian;
import object.OBJ_Emblem_Ranger;
import object.OBJ_Emblem_Specialist;
import object.OBJ_Emblem_Warrior;
import object.OBJ_Eternal_Ring;
import object.OBJ_Fireball;
import object.OBJ_Heart;
import object.OBJ_Hero_Crafting_Table;
import object.OBJ_Hero_Guardian_Field;
import object.OBJ_Hero_Marksman_Bow;
import object.OBJ_Hero_Special_Cloak;
import object.OBJ_Hero_Warrior_Sword;
import object.OBJ_Inside_Bed_Blue;
import object.OBJ_Inside_Bed_Purple;
import object.OBJ_Inside_Bed_Red;
import object.OBJ_Inside_Bookshelf;
import object.OBJ_Inside_Chair_Blue;
import object.OBJ_Inside_Chair_Purple;
import object.OBJ_Inside_Chair_Red;
import object.OBJ_Inside_Drawer;
import object.OBJ_Inside_Table;
import object.OBJ_Key;
import object.OBJ_Lantern;
import object.OBJ_Lantern_Blue;
import object.OBJ_Lantern_Emerald;
import object.OBJ_Lantern_Metal;
import object.OBJ_Lightning;
import object.OBJ_ManaCrystal;
import object.OBJ_Map;
import object.OBJ_Mario_Star;
import object.OBJ_Monster_Ball;
import object.OBJ_Monster_Spawner;
import object.OBJ_Ninjastar;
import object.OBJ_Pickaxe;
import object.OBJ_Pink_Ghost;
import object.OBJ_Placeholder;
import object.OBJ_Placeholder_Selected;
import object.OBJ_Potion_Big_Blue;
import object.OBJ_Potion_Big_Red;
import object.OBJ_Potion_Blue;
import object.OBJ_Potion_Max_Blue;
import object.OBJ_Potion_Max_Red;
import object.OBJ_Potion_Red;
import object.OBJ_Rock;
import object.OBJ_Rock_forPlayer;
import object.OBJ_SaveStone;
import object.OBJ_Shield_Blue;
import object.OBJ_Shield_Emerald;
import object.OBJ_Shield_Metal;
import object.OBJ_Shield_Wood;
import object.OBJ_Sign;
import object.OBJ_Skull;
import object.OBJ_Sword_Blue;
import object.OBJ_Sword_Emerald;
import object.OBJ_Sword_Metal;
import object.OBJ_Sword_Normal;
import object.OBJ_Tent;
import object.OBJ_Travel_Stone;
import object.OBJ_Travel_Token;

public class EntityGenerator {

	GamePanel gp;
	
	public EntityGenerator(GamePanel gp) {
		this.gp = gp;
	}
	public Entity getObject(String itemName) {
		
		Entity obj = null;
		
		// works but not a flexible solution, so this might change...
		switch(itemName) {
		case OBJ_Axe.objName: obj = new OBJ_Axe(gp); break;
		case OBJ_Boots.objName: obj = new OBJ_Boots(gp); break;
		case OBJ_Camp_Fire.objName: obj = new OBJ_Camp_Fire(gp); break;
		case OBJ_Chest.objName: obj = new OBJ_Chest(gp); break;
		case OBJ_Coin_Emerald.objName: obj = new OBJ_Coin_Emerald(gp); break;
		case OBJ_Coin_Bronze.objName: obj = new OBJ_Coin_Bronze(gp); break;
		case OBJ_Coin_Gold.objName: obj = new OBJ_Coin_Gold(gp); break;
		case OBJ_Coin_Silver.objName: obj = new OBJ_Coin_Silver(gp); break;
		
		//craftable objects
		case OBJ_Craftable_Anvil.objName: obj = new OBJ_Craftable_Anvil(gp); break;
		case OBJ_Craftable_Powder.objName: obj = new OBJ_Craftable_Powder(gp); break;
		case OBJ_Craftable_Stone.objName: obj = new OBJ_Craftable_Stone(gp); break;
		case OBJ_Craftable_Tnt.objName: obj = new OBJ_Craftable_Tnt(gp); break;
		case OBJ_Craftable_Wood.objName: obj = new OBJ_Craftable_Wood(gp); break;
		case OBJ_Craftable_Woodenplank.objName: obj = new OBJ_Craftable_Woodenplank(gp); break;
		
		case OBJ_Door_Down.objName: obj = new OBJ_Door_Down(gp,false); break;
		case OBJ_Door_Iron.objName: obj = new OBJ_Door_Iron(gp); break;
		case OBJ_Door.objName: obj = new OBJ_Door(gp); break;
		case OBJ_Emblem_Craftsman.objName: obj = new OBJ_Emblem_Craftsman(gp); break;
		case OBJ_Emblem_Guardian.objName: obj = new OBJ_Emblem_Guardian(gp); break;
		case OBJ_Emblem_Ranger.objName: obj = new OBJ_Emblem_Ranger(gp); break;
		case OBJ_Emblem_Specialist.objName: obj = new OBJ_Emblem_Specialist(gp); break;
		case OBJ_Emblem_Warrior.objName: obj = new OBJ_Emblem_Warrior(gp); break;
		case OBJ_Eternal_Ring.objName: obj = new OBJ_Eternal_Ring(gp); break;
		case OBJ_Fireball.objName: obj = new OBJ_Fireball(gp); break;
		case OBJ_Heart.objName: obj = new OBJ_Heart(gp); break;
		
		// HERO Special objects!!!
		case OBJ_Hero_Crafting_Table.objName: obj = new OBJ_Hero_Crafting_Table(gp); break;
		case OBJ_Hero_Guardian_Field.objName: obj = new OBJ_Hero_Guardian_Field(gp); break;
		case OBJ_Hero_Marksman_Bow.objName: obj = new OBJ_Hero_Marksman_Bow(gp); break;
		case OBJ_Hero_Special_Cloak.objName: obj = new OBJ_Hero_Special_Cloak(gp); break;
		case OBJ_Hero_Warrior_Sword.objName: obj = new OBJ_Hero_Warrior_Sword(gp); break;
		
		
		// Interior decorations & stuff
		case OBJ_Inside_Bed_Blue.objName: obj = new OBJ_Inside_Bed_Blue(gp); break;
		case OBJ_Inside_Bed_Purple.objName: obj = new OBJ_Inside_Bed_Purple(gp); break;
		case OBJ_Inside_Bed_Red.objName: obj = new OBJ_Inside_Bed_Red(gp); break;
		case OBJ_Inside_Bookshelf.objName: obj = new OBJ_Inside_Bookshelf(gp); break;
		case OBJ_Inside_Chair_Blue.objName: obj = new OBJ_Inside_Chair_Blue(gp); break;
		case OBJ_Inside_Chair_Purple.objName: obj = new OBJ_Inside_Chair_Purple(gp); break;
		case OBJ_Inside_Chair_Red.objName: obj = new OBJ_Inside_Chair_Red(gp); break;
		case OBJ_Inside_Drawer.objName: obj = new OBJ_Inside_Drawer(gp); break;
		case OBJ_Inside_Table.objName: obj = new OBJ_Inside_Table(gp); break;
		
		case OBJ_Key.objName: obj = new OBJ_Key(gp); break;
		case OBJ_Lantern_Blue.objName: obj = new OBJ_Lantern_Blue(gp); break;
		case OBJ_Lantern_Emerald.objName: obj = new OBJ_Lantern_Blue(gp); break;
		case OBJ_Lantern_Metal.objName: obj = new OBJ_Lantern_Blue(gp); break;
		case OBJ_Lantern.objName: obj = new OBJ_Lantern(gp); break;
		case OBJ_Lightning.objName: obj = new OBJ_Lightning(gp); break;
		case OBJ_ManaCrystal.objName: obj = new OBJ_ManaCrystal(gp); break;
		case OBJ_Map.objName: obj = new OBJ_Map(gp); break;
		case OBJ_Monster_Spawner.objName: obj = new OBJ_Monster_Spawner(gp); break;
		case OBJ_Ninjastar.objName: obj = new OBJ_Ninjastar(gp); break;
		case OBJ_Pickaxe.objName: obj = new OBJ_Pickaxe(gp); break;
		case OBJ_Placeholder.objName: obj = new OBJ_Placeholder(gp); break;
		case OBJ_Placeholder_Selected.objName: obj = new OBJ_Placeholder_Selected(gp); break;
		case OBJ_Potion_Big_Blue.objName: obj = new OBJ_Potion_Big_Blue(gp); break;
		case OBJ_Potion_Big_Red.objName: obj = new OBJ_Potion_Big_Red(gp); break;
		case OBJ_Potion_Blue.objName: obj = new OBJ_Potion_Blue(gp); break;
		case OBJ_Potion_Max_Blue.objName: obj = new OBJ_Potion_Max_Blue(gp); break;
		case OBJ_Potion_Max_Red.objName: obj = new OBJ_Potion_Max_Red(gp); break;
		case OBJ_Potion_Red.objName: obj = new OBJ_Potion_Red(gp); break;
		case OBJ_Rock_forPlayer.objName: obj = new OBJ_Rock_forPlayer(gp); break;
		case OBJ_Rock.objName: obj = new OBJ_Rock(gp); break;
		case OBJ_SaveStone.objName: obj = new OBJ_SaveStone(gp); break;
		case OBJ_Shield_Blue.objName: obj = new OBJ_Shield_Blue(gp); break;
		case OBJ_Shield_Emerald.objName: obj = new OBJ_Shield_Emerald(gp); break;
		case OBJ_Shield_Metal.objName: obj = new OBJ_Shield_Metal(gp); break;
		case OBJ_Shield_Wood.objName: obj = new OBJ_Shield_Wood(gp); break;
		case OBJ_Sign.objName: obj = new OBJ_Sign(gp); break;
		case OBJ_Skull.objName: obj = new OBJ_Skull(gp); break;
		case OBJ_Sword_Blue.objName: obj = new OBJ_Sword_Blue(gp); break;
		case OBJ_Sword_Emerald.objName: obj = new OBJ_Sword_Emerald(gp); break;
		case OBJ_Sword_Metal.objName: obj = new OBJ_Sword_Metal(gp); break;
		case OBJ_Sword_Normal.objName: obj = new OBJ_Sword_Normal(gp); break;
		case OBJ_Tent.objName: obj = new OBJ_Tent(gp); break;
		case OBJ_Travel_Stone.objName: obj = new OBJ_Travel_Stone(gp); break;
		case OBJ_Travel_Token.objName: obj = new OBJ_Travel_Token(gp); break;
		
		// RARES
		case OBJ_Mario_Star.objName: obj = new OBJ_Mario_Star(gp); break;
		case OBJ_Pink_Ghost.objName: obj = new OBJ_Pink_Ghost(gp); break;
		case OBJ_Monster_Ball.objName: obj = new OBJ_Monster_Ball(gp); break;
		
		// Add EVERYTHING! Otherwise there'll be errors!!!
		}
		return obj;
		
	}
}
