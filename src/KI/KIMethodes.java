package KI;

import Battle.Attack;
import Fight.Fighter;
import Fight.FighterData;

public abstract class KIMethodes {


	protected int x1,x2,y1,y2;
	   protected int playerAktion=0;

	    protected Fighter f,f2;
		protected Attack[] attacks;
		protected int doAktion=0;
		
		protected int ultimaterange=10000,specialrange=10000;
	
		
     protected boolean sprint=false;
		
     
     
 	/**
 	 *  MoveID für Blitzaufladung Aktion
 	 */
		protected void einsetzenPowerLoad() {
			playerAktion=20;
		}
		
	

		
		/**
	 	 * Prüft ob genug Ki für die entsprechende Aktion 
	 	 * zur Verfügung steht
	 	 */
		  protected boolean genugKI(int fuer)
		  {
			  boolean ok=false;
				  if(f.getAttributes()[3]-50>=FighterData.getKiWastage(f.getAttributes()[0])[fuer]){				  
					  ok=true;
				  }
			  return ok;
		  }
		  /**
		 	 * MoveID zum Bewegen zu Gegner
		 	 */
		 protected void bewegeZuGegner()
		    {
		        int move;   	
		        int mp=0;
		        if(sprint)
		        {
		        	mp=4;
		        }
		    	if((int)(Math.random()*2+1)==1)
				{			
				if(x1<x2)
				{
					move=1+mp;
				}
				else
				{
					move=2+mp;
				}
				}	
				else
				{
				if(y1<y2)
				{
					move=4+mp;
				}
				else
				{
					move=3+mp;
				}		
				}
		    	playerAktion=move;
		    }
		    
		 /**
		 	 *  MoveID zum Entfernen von Gegner
		 	 */
		    protected void fliehenVorGegner()
		    {
		    	int move;   	
		        int mp=0;
		        if(sprint)
		        {
		        	mp=4;
		        }
		    	if((int)(Math.random()*2+1)==1)
				{			
				if(x1<x2)
				{
					move=2+mp;
				}
				else
				{
					move=1+mp;
				}
				}	
				else
				{
				if(y1<y2)
				{
					move=3+mp;
				}
				else
				{
					move=4+mp;
				}		
				}
		    	playerAktion=move;
		    }
		    
		    
		    /**
		 	 *  Gibt den Abstand zwischen Kämpfer und Ziel
		 	 */
		    protected int gegnerAbstand()
		    {
		    	return  (int) Math.sqrt(Math.pow((y2-y1), 2)+Math.pow((x2-x1), 2));
		    }
		    
		    /**
		 	 * Gibt den X Abstand zum Gegner
		 	 */
			private int gegnerXAbstand() {
				// TODO Auto-generated method stub
				return  (int) Math.sqrt(Math.pow((x2-x1), 2));
			}

			/**
		 	 *  MoveID für Ki Angriff
		 	 *   Dreht Spieler automatisch in die richtige Richtung zum Ziel
		 	 *   und prüft gleiche Höhe zum abfeuern
		 	 */
			protected void einsetzenKIAttack() {
				if(stehtVerkehrt())
		    	{
		    		umdrehen();
		    	}
				else
				{
					if(gleicheHoehe()){
						playerAktion=16;
					}
					else
					{
						bewegenAufGleicheHoehe();
					}
				}
			}
		    
			/**
		 	 *  MoveID für SpecialAngriff
		 	 *  .true = Ultimativer Angriff
		 	 *  .false = Special Angriff
		 	 *  
		 	 *  Prüft auf richtige Richtung und gleiche Höhe mit Gegner
		 	 */
			protected void einsetzenSpecial(boolean b) {
				
				if(stehtVerkehrt())
		    	{
		    		umdrehen();
		    	}
				else
				{
				
					if(gleicheHoehe()){
								
						if(this.inPowerRange(b))
						{
						if(b)
						{
							playerAktion=18;
						}
						else
						{
							playerAktion=19;
						}
						}
						else{
							
							this.bewegeZuGegner();
						}
					}
					else
					{
						bewegenAufGleicheHoehe();
					}
				}
			}
			
			
			/**
		 	 *  Prüft ob die X-Entfernung zum gegner innerhalb
		 	 *  der Specialfähigkeitsreichweite liegt.
		 	 *  .true = Ultimativer Angriff
		 	 *  .false = Special Angriff
		 	 */
			protected boolean inPowerRange(boolean b)
			{
			
				int a=0;
				if(b)
				{
					a=this.ultimaterange;
				}
				else
				{
					a=this.specialrange;
				}
				
				if(this.gegnerXAbstand()<=a)
				{
				return true;	
				}
				else
				{
					return false;
				}
			
			}
		    
		
			/**
		 	 *  MoveID für Entwicklung
		 	 */
            protected void evolve()
            {
            	playerAktion=21;
            }
            
            /**
         	 *  MoveID für Blocken, dreht Spieler automatisch in die richtige Richtung
         	 */
			protected void blocken() {
				
				if(stehtVerkehrt())
		    	{
		    		umdrehen();
		    	}
		    	else
		    	{
		    		playerAktion=17;
		    	}
			}
		    
			/**
		 	 *  MoveID für Angriff, prüft auf richtige Richtung und 
		 	 *  auf Oben, Mitte, Unten Schlag
		 	 */
		    protected void angreifen()
		    {
		    	if(stehtVerkehrt())
		    	{
		    		umdrehen();
		    	}
		    	else
		    	{
		    	int differenz=y1-y2;   	
		    	if(differenz>50)
		    	{
		    		playerAktion=10;//Nach oben schlagen
		    	}
		    	else if(differenz<-50)
		    	{
		    		playerAktion=11;//Nach unten schlagen
		    	}
		    	else
		    	{
		    		playerAktion=9;//Gerade schlagen
		    	}    		   	
		    	}
		    }
		    
		    /**
		 	 *  Befehl zum bewegen auf die gleiche Höhe zum Gegner
		 	 */
		    protected void bewegenAufGleicheHoehe()
		    {
		    	int mp=0;
		    	if(sprint)
		    	{
		    		mp=4;
		    	}
		    	if(y1<y2)
		    	{
		    		playerAktion=4+mp;
		    	}
		    	else
		    	{
		    		playerAktion=3+mp;	    	
		    	}    	
		    }
		    
		   
		 
		    /**
		 	 *  Prüft ob auf gleicher Höhe mit Gegner ist
		 	 */
		 protected boolean gleicheHoehe()
		    {
		    	boolean b=false;
		    	int differenz=y1-y2;  
		    	int toleranz=30;
		    	if(differenz<toleranz&&differenz>-toleranz)
		    	{
		    		b=true;
		    	}
		    	return b;
		    }
		    
		 /**
		 	 *  Prüft ob Spieler verkehrt zum Gegner steht
		 	 */
    protected boolean stehtVerkehrt()
    {
    	boolean verkehrt=true;
    	
    	if(x1<x2){
    		if(f.isLeft()==false)
    		{
    			verkehrt=false;
    		}
    	}
    	else
    	{
    		if(f.isLeft())
    		{
    			verkehrt=false;
    		}
    	}   	
    	return verkehrt;
    }
    
    /**
 	 * Dreht den Spieler um (horizontaler Richtungswechsel)
 	 */
    protected void umdrehen()
    {
    	if(f.isLeft())
    	{
    		playerAktion=1;
    	}
    	else
    	{
    		playerAktion=2;
    	}
    }
    

	
}
