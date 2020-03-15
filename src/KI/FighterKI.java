package KI;

import Battle.Attack;
import Fight.Fighter;
import Fight.FighterData;



public abstract class FighterKI extends FighterKIMoves{

	public FighterKI()
	{
		
	}
		
 
	
	
	/*
	 * tactic:
	 * 0=aggressiv
	 * 1=defensiv
	 */
	
	
	final int NOTHING=0; // Nothing
	// Movement
	final int MOVE_RIGHT=1,MOVE_LEFT=2,MOVE_UP=3,MOVE_DOWN=4;
	final int FASTMOVE_RIGHT=5,FASTMOVE_LEFT=6,FASTMOVVE_UP=7,FASTMOVE_DOWN=8;
	final int BLOCK=17;
	// Attack
	final int ATTACK_STRAIGHT=9,ATTACK_UP=10,ATTACK_DOWN=11;
	// Energy
	final int LOAD_KI=15,LOAD_AURA=20;
	// Ki Attack
	final int ATTACK_KI=16,SPECIAL_ATTACK=19,ULTIMATE_ATTACK=18;
	
    public int getMove(Fighter f,  Fighter f2, Attack[] attacks, int spielerID)
    {    	
    	
    	//Set Powerrange
    	
    	int fid=f.getAttributes()[0];
    	this.specialrange=FighterData.getPowerRange(fid,false);
    	this.ultimaterange=FighterData.getPowerRange(fid,true);
    	
    	x1=f.getPos()[0];
    	y1=f.getPos()[1];
    	x2=f2.getPos()[0];
    	y2=f2.getPos()[1];   	
    	this.f=f;
    	this.f2=f;
    	this.attacks=attacks;    	
    	playerAktion=0; 
        useKI();//KI Aktionen berechnen
    	auto(attacks,spielerID);//Automatische Reaktionen  	
    	
    	
    	return playerAktion;
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
