package KI;

import Battle.Attack;
import Fight.Fighter;


public class HeavyKI extends FighterKI {

	public HeavyKI()
	{
		
	}
	

	
    protected void useKI()
    {
    	stellenwert[SW_ATTACK]=300;
     	stellenwert[SW_LOAD]=50;
    	stellenwert[SW_BLOCK]=50;
    	stellenwert[SW_ESCAPE]=150;
    	stellenwert[SW_SPECIAL]=200;
    	stellenwert[SW_ULTIMATE]=75;
    	stellenwert[SW_POWERUP]=50;
    	stellenwert[SW_KIATTACK]=100;
    	stellenwert[SW_EVOLVE]=20;
    	stellenwert[SW_NOTHING]=0;
    }
	
}
