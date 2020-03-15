package FighterBuild;

import java.util.ArrayList;


public class UseItem {

	
	public UseItem()
	{
		
	}

	public int[] useAttributeItems(ArrayList<Item> items, int[] attribute )
	{
		for(Item item: items)
		{
		ArrayList<Effect> eff=item.getEffects();
		for(int h=0; h<eff.size(); h++)
		{
			Effect e=eff.get(h);
		
				float power=e.getPower();
				int typeid=getAttributeID(e.getType());
				if(typeid>-1)
				{
				if(e.getCalculate()==Effect.TYP_ADD)
				{
					attribute[typeid]+=(int)power;
				}
				if(e.getCalculate()==Effect.TYP_PROZ)
				{
					attribute[typeid]=(int)((float)attribute[typeid]*power);
				}	
				}
		}
		}
		return attribute;
	}


	public int[] useDamageItems(ArrayList<Item> items, int[] damage) {
		
		for(Item item: items)
		{
			ArrayList<Effect> eff=item.getEffects();
			for(Effect e: eff)
			{
				float power=e.getPower();
				int typeid=getDamageID(e.getType());
				if(typeid>-1)
				{
				if(e.getCalculate()==Effect.TYP_ADD)
				{
					damage[typeid]+=(int)power;
				}
				if(e.getCalculate()==Effect.TYP_PROZ)
				{
					damage[typeid]=(int)((float)damage[typeid]*power);
				}	
				}			
			}
		}
		return damage;
	}
	
	/*Attribute
	 * 0.id
	 * 1.leben 
	 * 2.maxleben
	 * 3.ki
	 * 4.def
	 * 5.speed
	 * 6.ki load
	 * 7.attack speed
	 */
	private int getAttributeID(int type) {
		// TODO Auto-generated method stub
		int id=-1;
	    switch(type)
	    {
	    case Effect.EFFECT_HEALTH: id=2; break;
	    case Effect.EFFECT_KILOAD: id=6; break;
	    case Effect.EFFECT_ATTACKSPEED: id=7; break;
	    case Effect.EFFECT_DEFENCE: id=4; break;
	    case Effect.EFFECT_SPEED: id=5;
	    }
		return id;
	}
	
	/*Damages:
	 *0. Normal Attack
	 *1. Heavy Attack
	 *2. Ki Attack
	 *3. Spezial
	 *4. second spezial
	 */
	private int getDamageID(int type) {
		// TODO Auto-generated method stub
		int id=-1;
	    switch(type)
	    {
	    case Effect.EFFECT_STRENGTH: id=0; break;
	    case Effect.EFFECT_KIPOWER: id=2; break;
	    case Effect.EFFECT_SPECIAL: id=3; break;
	    }
		return id;
	}

	
}
