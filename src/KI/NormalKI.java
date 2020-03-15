package KI;

import Battle.Attack;
import Fight.Fighter;


public class NormalKI extends FighterKI {

	

	
	public NormalKI()
	{
		this.checkDanger=2;
	}
	

	
    protected void useKI()
    {
    	stellenwert[SW_ATTACK]=140;
     	stellenwert[SW_LOAD]=120;
    	stellenwert[SW_BLOCK]=110;
    	stellenwert[SW_ESCAPE]=20;
    	stellenwert[SW_SPECIAL]=65;
    	stellenwert[SW_ULTIMATE]=35;
    	stellenwert[SW_POWERUP]=20;
    	stellenwert[SW_KIATTACK]=90;
    	stellenwert[SW_EVOLVE]=15;
    	stellenwert[SW_NOTHING]=50;
    }
	
}
