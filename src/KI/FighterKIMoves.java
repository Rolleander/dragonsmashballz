package KI;

import java.awt.Point;

import Battle.Attack;
import DBZ.ErrorLogger;
import DBZ.Main;
import Fight.Fighter;
import Fight.FighterData;


public abstract class FighterKIMoves extends KIMethodes{

	
	
	final int KIATTACK=0,SPECIAL=2,ULTIMATE=1,PUMPEN=3;
	protected int aktiontimer=0;
	protected int[] stellenwert=new int[10];
	
	public int[] aktionlength={200,100,50,50,200,200,100,75,150,10};
    public int checkDanger=0; 
	
	//protected int kiload;

	final int SW_ATTACK=0,SW_LOAD=1,SW_ESCAPE=2,SW_BLOCK=3,SW_SPECIAL=4,SW_ULTIMATE=5,SW_POWERUP=6,SW_KIATTACK=7,SW_EVOLVE=8,SW_NOTHING=9;

	protected int speedt=0;

	
	/**
	 * Der KI Taktgebervorgang. 
	 * Steuert alle Abläufe, verwaltet Stellenwerte und ändert aktuelle
	 * Aktionen aus der Aktionenkette
	 */
	protected void auto(Attack[] attacks, int id)
	{
		if(!f.haveTransformation())
		{
			stellenwert[SW_EVOLVE]=0;
		
		}
		if((int)(Math.random()*40+1)==1)
		{
			speedt=(int)(Math.random()*30+1);
		}
		
		if(speedt>0){
			speedt--;
			sprint=true;
		}
		else
		{
			sprint=false;
		}
		
		if(aktiontimer>0)
		{
		
       
		}
		else
		{
			
			//Stellenwerte
			doAktion=getAktion();
	        aktiontimer=(int)(Math.random()*aktionlength[doAktion]+1)+aktionlength[doAktion]/3;  	
	       
		}
		
		 doAktion();
		 
		 
		

	if(f2.isDead())
	{
		playerAktion=0;
	}
	
	if((Math.random()*checkDanger+1)==1)
	{
	 cheackDangerSituation(attacks,id);
	}
	}
	
	/**
	 * Überprüft ob feindliche Angriffe in der Nähe sind.
	 * Führt automatisch zu einem MoveAway() von der Angriffsquelle 
	 */
	protected void cheackDangerSituation(Attack[] att, int id) {
		// TODO Auto-generated method stub
		//Special angriffen ausweichen
		for(int i=0; i<att.length; i++)
		{
			if(att[i]!=null)
			{
				if(Main.team.isEnemy(id, att[i].getAbsender()))
				{
					int ax=att[i].getPos()[0];
					int ay=att[i].getPos()[1];
			if(att[i].isSpecial())
			{
				if(att[i].getAttackTyp()==Attack.TYP_BEAM)
				{
					if(Point.distance(0, y1, 0, ay)<att[i].getSize().height+50)
					{
			          moveAway(ax,ay,att[i].getSpeed());
					}
				}
				else
				{
					if(Point.distance(x1, y1, ax, ay)<att[i].getSize().height+75)
					{
			          moveAway(ax,ay,att[i].getSpeed());
					}
				}								
			}
			else if(att[i].getAttackTyp()!=Attack.TYP_SMASH)
			{
				if(Point.distance(x1, y1, ax, ay)<att[i].getSize().height+50)
				{
		          moveAway(ax,ay,att[i].getSpeed());
				}
			}
				}
			}
		}
		
		
	}

	/**
	 * Kämpfer von einer Angriffsquelle wegbewegen.
	 * float[] speed wird als Bewegungsgeschwindigkeit
	 * der Attacke übergeben, um ein genaueres Ausweichen
	 * zu ermöglichen
	 * 
	 * Achtung: Überprüft nicht ob genug KI zum Sprinten
	 * übrig ist. Führt automatisch zum Verteidigen wenn
	 * kein KI mehr übrig ist (Selbststeuerung des Fighters)
	 */
	protected  void moveAway(int ax, int ay, float[] speed ) {
		// TODO Auto-generated method stub
		
		 int move;   	
	        int mp=4;
	       
	        boolean rechts=false,hoch=false;
	        
	        if(speed[0]>0)
	        {
	        rechts=true;	
	        }
	        if(ay>200)
	        {
	        	hoch=true;
	        }
	        
	        if(rechts)
	        {
	        	if((int)(Math.random()*3+1)==1)
	        	{
	        		move=1+mp;//Rechts
	        	}
	        	else
	        	{
	        	if(hoch)
	        	{
	        		move=3+mp;//Hoch
	        	}
	        	else
	        	{
	        		move=4+mp;//Runter
	        	}
	        	}
	        }
	        else
	        {
	        	if((int)(Math.random()*3+1)==1)
	        	{
	        		move=2+mp;//Links
	        	}
	        	else
	        	{
	        	if(hoch)
	        	{
	        		move=3+mp;//Hoch
	        	}
	        	else
	        	{
	        		move=4+mp;//Runter
	        	}
	        	}
	        }
	    	playerAktion=move;
		
	}

	/**
	 * Führt die aktuelle Aktion aus der Aktionskette aus
	 */
	protected  void doAktion()
	{
		switch(doAktion)
		{
		case SW_ATTACK: wantAttack(); break;
		case SW_LOAD: wantLoad(); break;	
		case SW_ESCAPE: wantEscape(); break;	
		case SW_BLOCK: wantBlock(); break;	
		case SW_SPECIAL: wantSpecial(); break;	
		case SW_ULTIMATE: wantUltimate(); break;	
		case SW_POWERUP: wantPowerUp(); break;	
		case SW_KIATTACK: wantKIattack(); break;	
		case SW_EVOLVE: wantEvolve(); break;
		case SW_NOTHING:  playerAktion=0; break;
		}
       aktiontimer--;
	}
	
	/**
	 * Befehl zum Weiterentwickeln.
	 * Wenn genug KI zum weiterentwickeln vorhanden ist,
	 * entwickelt sich der Kämpfer.
	 * 
	 * Autoablauf:
	 * Wenn ein Gegner zu Nahe kommt, wird die
	 * Autoreaktion attackEscapeOrBlock() aufgerufen
	 */
	protected void wantEvolve() {
		// TODO Auto-generated method stub
		
		if(genugKI(this.ULTIMATE))
		{
			
			this.evolve();
		}
		else
		{
			playerAktion=15;// Ki laden
			if(gegnerAbstand()<100)
			{
				attackEscapeOrBlock();
			}
		}
		}
		

	/**
	 * Befehl zum Abfeuern eines Ki-Angriffs.
	 * Wenn zu wenig Ki, wird Ki Laden ausgeführt.
	 * 
	 * Autoablauf:
	 * Wenn ein Gegner zu Nahe kommt, wird die
	 * Autoreaktion attackEscapeOrBlock() aufgerufen
	 */
	protected void wantKIattack() {
		if(genugKI(KIATTACK)){			
           
			
			einsetzenKIAttack();
		}
		else
		{
			playerAktion=15;// Ki laden
			if(gegnerAbstand()<100)
			{
				attackEscapeOrBlock();
			}
		}	
	}

	/**
	 * Befehl zum PowerUP. Wenn nicht genug Ki vorhanden ist,
	 * wird der Ki-Load Befehl ausgeführt.
	 * 
	 * Autoablauf:
	 * Wenn ein Gegner zu Nahe kommt, wird die
	 * Autoreaktion attackEscapeOrBlock() aufgerufen
	 */
	protected  void wantPowerUp() {
		if(genugKI(PUMPEN)){			
			einsetzenPowerLoad();
		}
		else
		{
			playerAktion=15;// Ki laden
			if(gegnerAbstand()<100)
			{
				attackEscapeOrBlock();
			}
		}	
	}



	/**
	 * Befehl zum Einsetzen des Ultimativen Angriff.
	 * Bei zu wenig KI wird Ki-Load Befehl ausgeführt
	 * 
	 * 
	 * Autoablauf:
	 * Wenn ein Gegner zu Nahe kommt, wird die
	 * Autoreaktion attackEscapeOrBlock() aufgerufen
	 */
	protected  void wantUltimate() {
		if(genugKI(ULTIMATE)){			
			einsetzenSpecial(true);
		}
		else
		{
			playerAktion=15;// Ki laden
			if(gegnerAbstand()<100)
			{
				attackEscapeOrBlock();
			}
		}	
	}
	
	/**
	 * Befehl zum Einsetzen des Special Angriff.
	 * Bei zu wenig KI wird Ki-Load Befehl ausgeführt
	 * 
	 * 
	 * Autoablauf:
	 * Wenn ein Gegner zu Nahe kommt, wird die
	 * Autoreaktion attackEscapeOrBlock() aufgerufen
	 */
	protected void wantSpecial() {
		
		if(genugKI(SPECIAL)){			
			einsetzenSpecial(false);
			
		}
		else
		{
			playerAktion=15;// Ki laden
			if(gegnerAbstand()<100)
			{
				attackEscapeOrBlock();
			}
		}		
	}


	/**
	 * Befehl zum Verteidigen 
	 * 
	 * 
	 * Autoablauf:
	 * Wenn kein Gegner in der Nähe ist,
	 * wird newRandomMove() aufgerufen
	 */
	protected  void wantBlock() {
		if(gegnerAbstand()<100)
		{
			blocken();
		}
		else
		{
			newRandomMove();
		}
	
	}



	/**
	 * Befehl zum Flüchten
	 * 
	 * 
	 * Autoablauf:
	 * Wenn kein Gegner in der Nähe ist,
	 * wird newRandomMove() aufgerufen
	 */
	protected void wantEscape()
	{
		if(gegnerAbstand()<200)
		{
			fliehenVorGegner();
		}
		else
		{
			newRandomMove();
		}
	}
	
	
	/**
	 * Befehl zum Ki-Laden
	 * 
	 * 
	 * Autoablauf:
	 * Wenn die Ki voll ist, wird newRandomMove() aufgerufen
	 * Wenn Gegner zu nahe kommen, wird attackEscapeOrBlock()
	 * aufgerufen.
	 */
	protected void wantLoad(){				
		if(f.getAttributes()[3]<FighterData.getMaxKi()){
			playerAktion=15; // ki laden
			if(gegnerAbstand()<70)
			{
				attackEscapeOrBlock();
			}
		} 
		else
		{
			newRandomMove(); //voll aufgeladen
		}			
	}
	
	/**
	 * Befehl zum Angreifen
	 * 
	 * Autoablauf:
	 * Wenn Gegner zu weit entfernt ist,
	 * wird bewegeZuGegner() augerufen
	 * Wenn in Gegner steht, wird eine
	 * FLuchtaktion ausgeführt
	 * 
	 */
	protected void wantAttack()
	{
		if(gegnerAbstand()<90)
		{
			if(gegnerAbstand()<70)
			{
				
				doAktion=SW_ESCAPE;
				aktiontimer=10;
			}
			else
			{
				angreifen();
			}
		
		}
		else
		{
			bewegeZuGegner();
		}
	}
	
	/**
	 * Neue zufällige Aktion
	 */
	protected  void newRandomMove() {
		doAktion=getAktion();
        aktiontimer=(int)(Math.random()*aktionlength[doAktion]+1)+aktionlength[doAktion]/3;  		
        
	}
	
	/**
	 * Meist wenn Gegner zu Nahe sind,
	 * führt zufällig nach Stellenwert zu
	 * folgenden 3 verschiedenen Reaktionen:
	 * 
	 * -Angreifen
	 * -Verteidigen
	 * -Flüchten
	 */
	protected void attackEscapeOrBlock()
	{
		
		int[] at={stellenwert[SW_ATTACK],stellenwert[SW_BLOCK],stellenwert[SW_ESCAPE]};
		int max=at[0]+at[1]+at[2];
		int rand=(int)(Math.random()*max+1)-1;
		for(int i=0; i<3; i++)
		{
			if(rand<=at[i]){
				switch(i)
				{
				case 0: doAktion=SW_ATTACK; break;
				case 1: doAktion=SW_BLOCK; break;
				case 2: doAktion=SW_ESCAPE; break;
				}
				 aktiontimer=(int)(Math.random()*aktionlength[doAktion]+1)+aktionlength[doAktion]/3;  	
				break;
			}
		}
		
	}
	
	
	/**
	 * Gibt nächste Aktion-ID zurück,
	 * abhängig vom Stellenwert
	 */
	protected int getAktion()
	{
		int aktion=0;
		int maxsw=0;
		for(int i=0; i<stellenwert.length; i++)
		{
			maxsw+=stellenwert[i];
		}
		int dosw=(int)(Math.random()*maxsw+1)-1;
		int nowsw=0;
		for(int i=0; i<stellenwert.length; i++)
		{
			
			nowsw+=stellenwert[i];
			if(dosw<=nowsw)
			{
				aktion=i;
				break;
			}
		}
		return aktion;
	}
	

	
}
