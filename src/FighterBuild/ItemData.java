package FighterBuild;

import java.util.ArrayList;

public class ItemData {

	
	
	private ArrayList <Item> items=new ArrayList<Item>();
	
	public ItemData()
	{
		
		for(int i=0; i<100; i++)
		{
			
			Item item=null;
			
			switch(i)
			{
			
			case 0: item=new Item("Healthdrink","Increase Health by 50",8000,0);  item.addEffect(50, Effect.EFFECT_HEALTH, Effect.TYP_ADD);   break;	
			case 1: item=new Item("Magic Water","Increase Strength and Defence by 15%",15000,0);item.addEffect(1.15f, Effect.EFFECT_DEFENCE, Effect.TYP_PROZ);  item.addEffect(1.15f, Effect.EFFECT_STRENGTH, Effect.TYP_PROZ);   break;	
			case 2: item=new Item("Mental Training","Sped up Ki-loading by 3",7000,2);  item.addEffect(3, Effect.EFFECT_KILOAD, Effect.TYP_ADD);   break;	
			case 3: item=new Item("Ki Power","Sped up Power loading by 15%",10000,7);  item.addEffect(1.15f, Effect.EFFECT_POWERUP, Effect.TYP_PROZ);   break;	
			case 4: item=new Item("Broly Style","Fires two Ki Attack",100000,3);  item.addEffect(Effect.STATUS_KI2, Effect.EFFECT_STATUS, 0);   break;	
			case 5: item=new Item("Hercules Strength","Strength +1",5000,1);  item.addEffect(1, Effect.EFFECT_STRENGTH, Effect.TYP_ADD);   break;	
			case 6: item=new Item("Mutens Training","Defence +1",5000,1);  item.addEffect(1, Effect.EFFECT_DEFENCE, Effect.TYP_ADD);   break;	
			case 7: item=new Item("Earth Spirit","Ki Attack +1",5000,1);  item.addEffect(1, Effect.EFFECT_KIPOWER, Effect.TYP_ADD);   break;	
			case 8: item=new Item("Birds Speed","Speed +1",5000,1);  item.addEffect(1, Effect.EFFECT_SPEED, Effect.TYP_ADD);   break;	
			case 9: item=new Item("Flawless Ki","Ki +1",5000,1);  item.addEffect(1, Effect.EFFECT_KILOAD, Effect.TYP_ADD);   break;	
			case 10: item=new Item("Hawk Eye","Attack Speed +1",5000,1);  item.addEffect(1, Effect.EFFECT_ATTACKSPEED, Effect.TYP_ADD);   break;				
			case 11: item=new Item("Gods Blessing","Slowly regenerate Health",25000,7);  item.addEffect(0.1f, Effect.EFFECT_REGENERATEHEALTH, Effect.TYP_ADD);   break;	
			case 12: item=new Item("Cyborg Energy","Slowly regenerate Ki",25000,7);  item.addEffect(1, Effect.EFFECT_REGENERATEKI, Effect.TYP_ADD);   break;	
			case 13: item=new Item("Final Showdown","Start with 1 Power Lightning",75000,6);  item.addEffect(Effect.STATUS_BLITZ, Effect.EFFECT_STATUS, 0);   break;	
			case 14: item=new Item("Saiyan Lust","Normal Attacks heal 5 Health",60000,4);  item.addEffect(Effect.STATUS_NORMALHEAL, Effect.EFFECT_STATUS, 0);   break;	
			case 15: item=new Item("Full Tank","Defence +8 Strength -5",30000,5);  item.addEffect(8, Effect.EFFECT_DEFENCE, Effect.TYP_ADD);  item.addEffect(-5, Effect.EFFECT_STRENGTH, Effect.TYP_ADD);  break;	
			case 16: item=new Item("Full Power","Strength +8 Defence -5",30000,3);  item.addEffect(-5, Effect.EFFECT_DEFENCE, Effect.TYP_ADD);  item.addEffect(8, Effect.EFFECT_STRENGTH, Effect.TYP_ADD);  break;	
			case 17: item=new Item("Giants Health","Increase Health by 15%",40000,0);  item.addEffect(1.15f, Effect.EFFECT_HEALTH, Effect.TYP_PROZ);   break;	
			case 18: item=new Item("Giant Ape Strength","Increase Strength by 20%",35000,3);  item.addEffect(1.20f, Effect.EFFECT_STRENGTH, Effect.TYP_PROZ);   break;	
			case 19: item=new Item("Lightnings Speed","Increase Speed by 15%",28000,6);  item.addEffect(1.15f, Effect.EFFECT_SPEED, Effect.TYP_PROZ);   break;	
			case 20: item=new Item("Tank Armor","Increase Defence by 20%",50000,5);  item.addEffect(1.20f, Effect.EFFECT_DEFENCE, Effect.TYP_PROZ);   break;	
			case 21: item=new Item("Ki Master","Increase Ki-Loading by 20%",55000,7);  item.addEffect(1.20f, Effect.EFFECT_KILOAD, Effect.TYP_PROZ);   break;	
			case 22: item=new Item("Sayajin Power","In full-Ki mode every hit is critical",48000,3);  item.addEffect(Effect.STATUS_FULLKICRIT, Effect.EFFECT_STATUS, 0);   break;			
			case 23: item=new Item("Cybrog Strength","Strength +3",12500,1);  item.addEffect(3, Effect.EFFECT_STRENGTH, Effect.TYP_ADD);   break;	
			case 24: item=new Item("Kaios Training","Defence +3",12500,1);  item.addEffect(3, Effect.EFFECT_DEFENCE, Effect.TYP_ADD);   break;	
			case 25: item=new Item("Galaxy Spirit","Ki Attack +3",12500,1);  item.addEffect(3, Effect.EFFECT_KIPOWER, Effect.TYP_ADD);   break;	
			case 26: item=new Item("Dragons Speed","Speed +3",12500,1);  item.addEffect(3, Effect.EFFECT_SPEED, Effect.TYP_ADD);   break;	
			case 27: item=new Item("Perfect Ki","Ki +3",12500,1);  item.addEffect(3, Effect.EFFECT_KILOAD, Effect.TYP_ADD);   break;	
			case 28: item=new Item("Swordsman","Attack Speed +3",12500,1);  item.addEffect(3, Effect.EFFECT_ATTACKSPEED, Effect.TYP_ADD);   break;	
			case 29: item=new Item("Golden Winner","You get 10% more Prize money",99999,9);  item.addEffect(Effect.STATUS_PRIZEMONEY, Effect.EFFECT_STATUS, 0);   break;	
			
			
			
			}
			
			
			
			items.add(item);
			
		}
		
		
		
	}
	
	public ArrayList<Item> getItems(){
		
		return items;
	}
	
	
	
	
}
