package KI;

import Battle.Attack;
import Fight.Fighter;


public class EasyKI extends FighterKI {

	

	
	public EasyKI()
	{
		this.checkDanger=5;
	}
	

	
    protected void useKI()
    {
    	stellenwert[SW_ATTACK]=100;
     	stellenwert[SW_LOAD]=120;
    	stellenwert[SW_BLOCK]=200;
    	stellenwert[SW_ESCAPE]=20;
    	stellenwert[SW_SPECIAL]=50;
    	stellenwert[SW_ULTIMATE]=25;
    	stellenwert[SW_POWERUP]=10;
    	stellenwert[SW_KIATTACK]=90;
    	stellenwert[SW_EVOLVE]=5;
    	stellenwert[SW_NOTHING]=150;
    }
	
}
