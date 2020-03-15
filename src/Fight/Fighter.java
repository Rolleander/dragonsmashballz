package Fight;




import java.awt.Dimension;
import java.awt.Graphics;

import Battle.Attack;
import Battle.Battle;
import Battle.Blood;
import Battle.Damage;
import Battle.Explosion;
import Battle.KiAttack;
import Battle.Smash;
import DBZ.Animation;
import DBZ.Form;
import DBZ.Main;
import DBZ.PlayerControl;

import FighterBuild.Effect;
import FighterBuild.Item;
import FighterBuild.UseItem;

import java.util.ArrayList;
import Images.GameImages;
import KI.FighterKI;
import KI.HeavyKI;
import Menu.HUD;



public class Fighter extends Form{

	
	public final static int ATT_ID=0,ATT_HEALTH=1,ATT_MAXHEALTH=2,ATT_KI=3,ATT_DEF=4,ATT_SPEED=5,ATT_KILOAD=6,ATT_ATKSPEED=7;
	
	private int[] atts;
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
	private int[] damage;
	/*Damages:
	 *0. Normal Attack
	 *1. Heavy Attack
	 *2. Ki Attack
	 *3. Spezial
	 *4.second spezial
	 */
	private int[] kiwastage;

	private double specialplus=1;
	private int gepumpt=0;
	private int spielerid=0;
	private Dimension dim=new Dimension(200,200);
	private boolean immortal=false,transformpumpen=false;
	
	private FighterKI comp;
	private int computerziel;
	private int stuntime=0;
	private int canteleport=0;
	private int schlagPause=0;
	//Status
	private ArrayList<Item> items;
	private boolean alife=true;	
	private boolean flying=false;
    private boolean blocking=false;
    private boolean isCyborg=false;
    private boolean doneMove=false;
   private boolean secondspecial=false;
   private boolean invisible=false;
    private boolean sieg=false;
    private boolean cantransform=true;
    private boolean einfliegen=false;
    private boolean bewegung=false;
    private int angriffz=0,angrifft=0,dragontele=0;
    
    private float winkel=0;
    
	//Attacked
	private boolean controllable=true; //Can controll Fighter?


	//Move
	
	private FighterSpecials specials=new FighterSpecials();
	private int pid=0; //picture-id
	private boolean left=false; //Direction
	private boolean specialattack=false; //
	private boolean usespecial=false;
	private Animation animation=new Animation();//Animations Timer
	private FighterData data=new FighterData();
	private float heal=0;
	private boolean floorMap=true;
	
	
	
	private float shielddamage=0;
	
	//Design
	private int kiattackdesign=31;

	private Dimension mapborders;
	
	private int specialfightertime=0;
	
	private boolean useItems=false;
	
	public Fighter()
	{
		
	}
	
	public void sieg()
	{
		sieg=true;
	}
	
	public void setMapBorder(Dimension d)
	{
		mapborders=d;
	}
	
	public boolean hatGesiegt()
	{
		return sieg;
	}
	
	public void init(int id, int spid, boolean l)
	{
		spielerid=spid;
	left=l;
	    changeFighterId(id);
		active=true;
		
		alife=true;
		
	}
	
	
	

	public void setSpielerID(int id, int aid)
	{
		spielerid=id;
		PlayerControl c=Main.team.getSteuerung(spielerid);
		if(c.isComputer())
		{
			comp=Main.getFighterKI();
			computerziel=aid;
		}
		else
		{
			comp=null;
		}
	}
	
	public void changeFighterId(int id)
	{		
		atts=FighterData.getAttributes(id);
		damage=FighterData.getDamages(id);
		kiwastage=FighterData.getKiWastage(id);
		isCyborg=data.isCyborg(id);
		kiattackdesign=data.getAttackDesign(id);
		
		if(useItems)
		{
		items=Main.getFighterItems(id);
		useStatsItems();
	    atts[1]=atts[2]; 
		}
	}
	
	

	private void useStatsItems() {
		// TODO Auto-generated method stub
		if(useItems)
		{
		UseItem itemuser=new UseItem();		
		atts=itemuser.useAttributeItems(items, atts);		
		damage=itemuser.useDamageItems(items, damage);
		for(Item item: items)
		{
			for(Effect effect: item.getEffects())
			{
				if(effect.getType()==Effect.EFFECT_STATUS)
				{
					switch((int)effect.getPower())
					{
					case Effect.STATUS_BLITZ: gepumpt++; break;
					}										
				}
			}
		}
		}
	}

	private void changeFighterAttribute(int id)
	{
		int altid=atts[0];
		int leben=atts[1];
		atts=FighterData.getAttributes(id);
		atts[0]=altid;
		atts[1]=leben;
		damage=FighterData.getDamages(id);
		kiwastage=FighterData.getKiWastage(id);
		isCyborg=data.isCyborg(id);
		kiattackdesign=data.getAttackDesign(id);
	}
	
	public void setAttribut(int att, int value)
	{
		if(att==0&&value!=atts[0])
		{
			Main.sound.stopLoadingSound(atts[0]);
		    changeFighterId(value);
			Main.sound.playSound(16, true);
			Main.sound.playSpecialSound(value);
			Explosion exp=new Explosion(pos[0],pos[1],9);
			Battle.newExplosion(exp);
		}
		atts[att]=value;
		
	}
	
	public int[] getAttributes()
	{
		return atts;
	}
	
	public void hit(Damage d, int x, int y)
	{
		if(invisible==false)
		{
		//dir == Richtung des Angriffs
		//damage= Schaden am Spieler				
		//move
		
		controllable=false;
		if(atts[0]==FighterData.DRGERO&&secondspecial&&d.physical==false)
		{
			atts[1]+=d.damage/2;
			if(atts[1]>atts[2])
			{
				atts[1]=atts[2];
			}
		}
		else
		{
			canteleport=10;
		if(canBlock(x,y))
		{
			speed[0]=d.dirx/10;
			speed[1]=d.diry/10;
			
			damage(d,true);
			
		}
		else
		{
			
			Main.sound.playHurtSound(atts[0]);
			if(specialattack==false)
			{
			speed[0]=d.dirx/5;
			speed[1]=d.diry/5;
			animation.killAnimation();
			int da=d.dirx;
			if(da<0)
			{
				da=da*-1;
			}
			if(da>20)
			{
				da=20;
			}
			animation.newAnimation(da, 23, 24);	
			}
			
			damage(d,false);
		}
		}
		}
	}
	
	private boolean canBlock(int x, int y)
	{
		boolean b=false;
		if(blocking)
		{
			if(left==true&&x<pos[0])
			{
				b=true;
			}
			else if(left==false&&x>pos[0])
			{
				b=true;
			}
		}
		return b;
	}
	
	
	
	private  void damage(Damage d, boolean blocked)
	{
		int schaden;	
	
		if(blocked)
		{
		   
			schaden=(int) (d.damage/((double)atts[4]/6+1));
			
			if(schaden<1){
				schaden=1;
			}
		}
		else
		{
			double durch=((double)atts[4]/10);
			if(durch<1)
			{
				durch=1;
			}
			
			schaden=(int) (d.damage/durch);
		
		}		
	    if(shielddamage>=0)
	    {
	    	if(d.physical)
	    	{
		shielddamage+=schaden;
	    	}
	    	else
	    	{
	    		shielddamage+=schaden/3;
	    	}
		if(shielddamage>FighterData.SHIELD)
		{
			
			shielddamage=(shielddamage-FighterData.SHIELD-FighterData.KOTIME);
			
		}
	    }
	    
		boolean b=false;
		if(immortal==false)
		{
		atts[1]-=schaden;
		   
		}
		if(atts[1]<1)
		{//dead
			if(alife)
			{
				b=true;
			}
			die();
			
			
		}
		
		HUD.damageCounter(d.absender,spielerid, schaden,b);
		if(atts[1]<-200&&invisible==false)
		{
			invisible=true;
			
		  
			Main.sound.playSound(8, true);
			for(int i=0; i<30; i++)
			{
			Blood.newBlood((int)pos[0],(int)pos[1],Math.toRadians((int)(Math.random()*360+1)),data.getBloodArt(atts[0]));
			}
			Battle.newExplosion(new Explosion(pos[0],pos[1],3));
			
			pos[0]=-500;
			pos[1]=-500;
		}
		
		if(invisible==false){
			
			int anz=schaden/10+1;
			for(int i=0; i<anz; i++)
			{
			Blood.newBlood((int)pos[0],(int)pos[1],Math.toRadians((int)(Math.random()*360+1)),data.getBloodArt(atts[0]));
			}
			
		}
	}
	
	public void die()
	{
		controllable=false;
		animation.killAnimation();	
		animation.newAnimation(1000000, 25, 25);
	
	
		if(alife==true)
		{
			speed[1]=3;	
			Main.sound.stopLoadingSound(atts[0]);
		Main.sound.playDefeatSound(atts[0]);
		
		}
		alife=false;
		specialplus=1;
		gepumpt=0;
	}
	
	private  void noMovement()
	{//no button pressed
		if(controllable)
		{
			speed[0]=speed[0]*0.95f;
			speed[1]=speed[1]*0.95f;
			if(speed[0]<0.5)
			{
				speed[0]=0;
			}
			if(speed[1]<0.5)
			{
				speed[1]=0;
			}
		if(flying)
		{
			fly();
		}
		else
		{
			animation.newAnimation(1, 0, 0);
		}
		}
	
	}
	
	
	private int flytime;
	private boolean upfly=true;
	private  void fly()
	{
		animation.newAnimation(1, 4, 4);
		if(upfly){
			flytime++;
			pos[1]-=0.5;
			if(flytime>20)
			{
				upfly=false;
				flytime=0;
			}
		}
		else
		{
			flytime++;
			pos[1]+=0.5;
			if(flytime>20)
			{
				upfly=true;
				flytime=0;
			}
		}
	}
	
	public void makeNoMove() {
	if(doneMove==false)
	{
		noMovement();
		Main.sound.stopLoadingSound(atts[0]);
		
	}
	}
	
	public void makeMove(int move)
	{
       doneMove=true;
		if(alife)
		{
		if(pumpen==0&&stuntime==0&&shielddamage>=0)
		{
	
		switch(move)
		{
		case 0: noMovement();   break; //Do Nothing
		case 1: move(0); break;//Move Right
		case 2: move(1); break;//Move Left
		case 3: move(2); break;//Move Up
		case 4: move(3); break;//Move Down
		case 5: moveFast(0); break;//Move Right /Speed
		case 6: moveFast(1); break;//Move Left/Speed
		case 7: moveFast(2); break;//Move Up/Speed
		case 8: moveFast(3); break;//Move Down/Speed
		case 9:  attack(0); break;//Attack horizontaly
		case 10: attack(1); break;//Attack Up
		case 11: attack(2); break;//Attack Down
		case 12: attack(3); break; //Speed Attack
		case 13: attack(4); break; //Heavy Attack Up
		case 14: attack(5); break; //Heavy Attack Down
		case 15: loadKi(); 	 break; //Ki laden
		case 16: kiAttack(); break; //kiAttack
		case 17: block(); break; //def
		case 18: spezialAttack(true); break; //Special Attack
		case 19: spezialAttack(false); break; //Second Special Attack
		case 20: pumpen(); break;
		case 21: if(canTransform()) {transform();}else{loadKi(); move=15;}; break; 
	//	case 22: powerup();  break;
		case 22: noMovement(); break;
		}
		}
	
		if(move!=15)
		{
			Main.sound.stopLoadingSound(atts[0]);
			 lifeload=0;
		}	
		
		}
	}
	/*
	private void powerup() {
		// TODO Auto-generated method stub
		if(powerup!=null)
		{
		powerup.activate();
		}
		else
		{
			
			block();
		}
		if(atts[0]==FighterData.GOKU)
		{
			this.changeFighterAttribute(FighterData.GOKUKAIOKEN);
			this.specialfightertime=500;
		}
	}*/

	public boolean canTransform()
	{
		boolean t=false;
		if(cantransform)
		{
		if(data.canTransform(atts[0])) 
		{
		if(controllable&&pumpen==0)
		{
			if(atts[3]>=kiwastage[1])
			{
				t=true;
			}
		}
	}
		}
		return t;
	}
	
	public boolean haveTransformation()
	{
		return data.canTransform(atts[0]);
	}
	
   private void transform() {
		
	   if(canTransform())
	   {
			 animation.killAnimation();
			 animation.newAnimation(5, 12, 13);
				
					speed[0]=0;
					speed[1]=0;
					Main.sound.loadSpecialSound();
					Battle.newExplosion(new Explosion(pos[0],pos[1],9));
					
				
				    pumpen=200;
				    transformpumpen=true;
				    
	   }
	}

   private void evolve(){
		Main.sound.loadSpecialSound();
		Battle.newExplosion(new Explosion(pos[0],pos[1],9));
		
		controllable=false;
		Main.sound.stopLoadingSound(atts[0]);
		int leben=atts[1];
	    changeFighterId(data.getTransformForm(atts[0]));
	    atts[3]=100;
	    leben=(int) (leben*1.7);
	    atts[1]=leben;
	    if(atts[1]>atts[2])
	    {
	    	atts[1]=atts[2];
	    }
	   useStatsItems();
   }

private int pumpen;
	private void pumpen() {

		if(controllable&&pumpen==0)
		{
			if(specialplus<3)
			{
		  if(atts[3]>=kiwastage[3])
		  {
			  animation.killAnimation();
			animation.newAnimation(100, 20, 20);
			speed[0]=0;
			speed[1]=0;
			atts[3]-=kiwastage[3];
			Main.sound.loadSpecialSound();
			
			Battle.newExplosion(new Explosion(pos[0],pos[1],9));
			
			controllable=false;
			pumpen=100;
		  }
		  else
		  {
			  loadKi();
		  }
			}
		}
	}


	private int specialbild=0;
	private void spezialAttack(boolean original)
	{
		if(controllable)
		{	
			if(animation.isRunning()==false)
			   {
			
		
				int kiv;
				if(original)
				{
				kiv=kiwastage[1];
				}
				else
				{
				 kiv=kiwastage[2];
				 secondspecial=true;
				}
				if(atts[3]>=kiv)
				{
					
					int d1=damage[3];
					atts[3]-=kiv;
					damage[3]=(int) (damage[3]*specialplus);
			
				specialbild=21;
			speed[0]=0;
			speed[1]=0;
			if(original)
			{
				if(atts[0]==FighterData.SSJ2GOKU)
				{
					if(left)
					{
						pos[0]=50;
					}
					else
					{
						pos[0]=950;
					}
					left=!left;
				}
				
			}
		
			specialbild=specials.spezialAttack(original,atts[0],damage,specialplus,pos,left,spielerid);
			
			animation.killAnimation();
			animation.newAnimation(100, specialbild, specialbild);
			controllable=false;
		
		
		if(original)
		{
			damage[3]=d1;
		specialplus=1;
		gepumpt=0;
		
		
		}
		else
		{
			if(atts[0]==FighterData.HERCULE)
			{
				if(left)
				{
				this.pos[0]-=300;
				}
				else
				{
					pos[0]+=300;
				}
			}
			else if(atts[0]==FighterData.JANEMBA||atts[0]==FighterData.TRUNKSFUTURE)
			{
				if(left)
				{
				this.pos[0]-=250;
				}
				else
				{
					pos[0]+=250;
				}
				
			}
			
		}
				}
				else
				{
					
					attack(0);
				}
			
			
			   }
			
			
		}
	}
	
	private  void block()
	{
		if(dragontele>0)
		{
			dragonteleport();
		
		}
		else
		{
		if(controllable)
		{
			
			
		   speed[0]=0;
		    speed[1]=0;
			animation.newAnimation(1, 1, 1);
		    blocking=true;
			
		}
		}
	}
	
	private void dragonteleport() {
		// TODO Auto-generated method stub
		dragontele=0;
		int xplus=300;
		if(left)
		{
			pos[0]-=xplus;
		}
		else
		{
			pos[0]+=xplus;
		}
		left=!left;
		animation.killAnimation();
		animation.newAnimation(5, 22, 22);
		Main.sound.teleportSound();
	}

	private  void kiAttack()
	{
		if(controllable)
		{
				
			
		   if(animation.isRunning()==false)
		   {
			   
			  
		
			   if(atts[3]>=kiwastage[0])
				{
					
				   speed[0]=0;
					speed[1]=0;
				   atts[3]-=kiwastage[0];
			animation.newAnimation(atts[7], 14, 15);
			int lplus=0;
			int hplus=(int)(Math.random()*20+1)-10;
			if(left)
			{
				lplus=-50;
			}
			else
			{
				lplus=50;
			}
			/*if(attack==1||attack==4)
			{
				hplus=-70;
				lplus=(int) (lplus/1.3);
			}
			if(attack==2||attack==5)
			{
				hplus=70;
				lplus=(int) (lplus/1.3);
			}*/
			Attack a;
			a=new KiAttack((int)pos[0]+lplus,(int)pos[1]+hplus,new Damage(damage[2],lplus/10,0),lplus/5,0,kiattackdesign,50);
			if(atts[0]==FighterData.HERCULE)
			{
				a=new KiAttack((int)pos[0]+lplus,(int)pos[1]+hplus,new Damage(damage[2],lplus/10,0),lplus/5,0,74,35);
				a.rotate(15);
				a.setStein();
				
			}
			a.cantDruckDuell();
			a.setAbsender(spielerid);
			a.setExplosion(new Explosion(0,0,0));
			Battle.newAttack(a);
			Battle.newExplosion(new Explosion(pos[0]+lplus,pos[1]+hplus,8));
			Main.sound.playHitSound(atts[0]);
				}
			   else
			   {
				   attack(0);
			   }
		   }
		}
	}
	private int lifeload=0;
	private int kiloadani;
	private boolean duellpress;
	private  void loadKi()
	{
		
		if(controllable)
		{
			speed[0]=0;
			speed[1]=0;
		   if(isCyborg==false)
		   {
			   
		
				kiloadani++;
			if(kiloadani<=5)
			{
			animation.newAnimation(1, 12, 12);
			}
			else
			{
				animation.newAnimation(1, 13, 13);
				if(kiloadani>9)
				{
					kiloadani=0;
				}
			}
			
			atts[3]+=atts[6];
			if(atts[3]>FighterData.getMaxKi())
			{
				atts[3]=FighterData.getMaxKi();
			}
			Main.sound.playLoadingSound(atts[0]);
		   }
		   else
		   {
			   if(atts[3]>=50)
				{		 
		           lifeload++;
		        if(lifeload>50)
		        {
		        	   atts[3]-=50;
		           if(atts[1]<atts[2])
				   {
				   atts[1]+=2;
				   }
		           if(atts[3]<0)
		           {
		        	   atts[3]=0;
		           }
		           if(lifeload%5==0)
		           {
		        	 
		        	  
		        	   Explosion exp=new Explosion(pos[0],pos[1],7);
		        	   Battle.newExplosion(exp);
		           }
		        }
			   noMovement();
				}
		   }
		}
	
	}
	
	private  void move(int direction)
	{
		if(controllable)
		{			
			animation.newAnimation(1, 4, 4);//Move Bild
			double spd=atts[5]/2;
			double besch=0.3;
			switch(direction)
			{
			case 0:
				if(speed[0]<spd)
				{
				speed[0]+=besch;
				left=false;
				}
				break;
			case 1: 
				if(speed[0]>-spd)
				{
				speed[0]-=besch; 
				left=true;
				}
				break;
			case 2: 
				if(speed[1]>-spd)
				{
				speed[1]-=besch; 
				}
				break;
			case 3:
				if(speed[1]<spd)
				{
				speed[1]+=besch; 
				}
				break;
			}			
		}
	}
	
	private  void moveFast(int direction)
	{	
		if(dragontele>0)
		{
			dragonteleport();
		
		}
		else
		{
		if(controllable)
		{
			if(atts[3]>2)
			{
				atts[3]-=2;
			animation.newAnimation(1, 2, 2);
			double spd=atts[5];
			double besch=1;
			switch(direction)
			{
			case 0:
				if(speed[0]<spd)
				{
				speed[0]+=besch;
				left=false;
				}
				break;
			case 1: 
				if(speed[0]>-spd)
				{
				speed[0]-=besch; 
				left=true;
				}
				break;
			case 2: 
				if(speed[1]>-spd)
				{
				speed[1]-=besch; 
				}
				break;
			case 3:
				if(speed[1]<spd)
				{
				speed[1]+=besch; 
				}
				break;
			}			
			}
			else
			{
				block();
			}
		}
		else
		{//Sometimes Teleport there
			
			if(alife&&usespecial==false)
			{
				int xp=0,yp=0;
				switch(direction)
				{
				case 0:  xp=1; break;
				case 1:  xp=-1; break;
				case 2:  yp=-1; break;
				case 3:  yp=1; break;
				}
				
			if((int)(Math.random()*3+1)==1&&canteleport>0)
    		{
				//if(atts[3]>data.getMaxKi()/15)
				{
					canteleport=0;
					//atts[3]-=data.getMaxKi()/15;
				animation.killAnimation();
			animation.newAnimation(5, 22, 22);
			Main.sound.teleportSound();
			int plus=20;
			pos[0]+=(int)((Math.random()*plus+1)+plus)*xp;
			pos[1]+=(int)((Math.random()*plus+1)+plus)*yp;
		
				}
    		}
			
			}
		}
		}
	}
	
	private  void attack(int attack)
	{
		if(controllable)
		{
			
			if(speed[0]>=atts[5]-1)
			{
				attack=3;
			
			}
			
			speed[0]=0;
			speed[1]=0;
		
			int pid2 = 0;
				switch(attack)
				{
				case 0 : pid2=(int)(Math.random()*2+1)+4;			break;
				case 1 : pid2=7; break;
				case 2 : pid2=8; break;
				case 3 : pid2=11; break;
				case 4 : pid2=10; break;
				case 5 : pid2=11; break;				
				}
		
			  if(animation.isRunning()==false)
			   {
				  if(schlagPause>0)
					{
						schlagPause--;
						animation.killAnimation();
						animation.newAnimation(1, 4, 4);
					}
					else
					{
				  schlagPause=5;
				  if(attack==0)
					{
						if(angriffz>=5&&(int)(Math.random()*2+1)==1)
						{
							pid2=(int)(Math.random()*2+1)+8;
							angriffz=0;
							attack=3;
						}			
					}
					angriffz++;
					angrifft=30;
					
					animation.newAnimation(atts[7], pid2, pid2);
				  //new Attack
					int lplus=0;
					int hplus=0;
					if(left)
					{
						lplus=-70;
					}
					else
					{
						lplus=70;
					}
					if(attack==1||attack==4)
					{
						hplus=-70;
						lplus=(int) (lplus/1.3);
					}
					if(attack==2||attack==5)
					{
						hplus=70;
						lplus=(int) (lplus/1.3);
					}
					Attack a;
					
					
					
					if(attack<3)
					{//Normal Attack
						Damage d=new Damage(damage[0],lplus/10,hplus/3);
						d.physical=true;
					  a=new Smash((int)pos[0]+lplus,(int)pos[1]+hplus,d,0);
					}
					else if(attack==3)
					{
						Damage d=new Damage(damage[1],lplus,hplus);
						d.physical=true;
						  a=new Smash((int)pos[0]+lplus,(int)pos[1]+hplus,d,1);
						  a.setStronSmash();
							dragontele=30;
					}
					else
					{//Heavy Attack
						Damage d=new Damage(damage[1],lplus/3,hplus/3);
						d.physical=true;
					  a=new Smash((int)pos[0]+lplus,(int)pos[1]+hplus,d,1);
					  a.setStronSmash();
					}				
					
					a.setAbsender(spielerid);
					Battle.newAttack(a);
					Main.sound.playHitSound(atts[0]);
			   }
			}
		}
		else
		{
			duellpress=true;
		}
		
	}
	
	public boolean druecktDruckDuell()
	{
		boolean b=duellpress;
		duellpress=false;
		return b;
	}
	
	private int aura,auraz;

	private int amboden;
	public void paint(Graphics g)
	{
		
		if(alife)
		{
		/*	powerup.use(atts,pos,damage,left);
			atts=powerup.getAtts();
			pos=powerup.getPos();
			damage=powerup.getDamage();*/
			
			
			
			if(specialfightertime>0)
			{
				specialfightertime--;
				this.changeFighterAttribute(atts[0]);
			}
			
		if(shielddamage>0)
		{
			float sp=0.085f;
				shielddamage-=sp;
				if(shielddamage<sp)
				{
					shielddamage=0;
				}
		}
		else if(shielddamage<0)
		{
			shielddamage++;
			if(this.usespecial==false)
			{
				speed[0]=0;
				speed[1]=0;
				animation.killAnimation();
				animation.newAnimation(1, 23,23);
			}
		}
		}
		else
		{
			if(amboden<101)
			{
			amboden++;
			}
		}
		if(canteleport>0)
		{
			canteleport--;
		}
		if(dragontele>0)
		{
			dragontele--;
		}
		if(angrifft>0)
		{
			angrifft--;
			if(angrifft==0)
			{
				angriffz=0;
			}
		}
		bewegung=false;
		if(einfliegen==false)
		{
		if(stuntime>0&&alife)
		{
			stuntime--;
		
			if(stuntime%20==0)
			{
				  Explosion exp=new Explosion(pos[0],pos[1]-30,10);
		    	   Battle.newExplosion(exp);
			}
		}
	       doneMove=false;
		//Draw Player
		if(animation.isRunning())
		{			
			pid=animation.nextAnimation();		
			
		}
		else
		{			
			controllable=true;
			blocking=false;
			specialattack=false;
			secondspecial=false;
		
		}
		
		if(pid>29||pid<0)
		{
			pid=1;
		}
	
			usespecial=false;
			
			if(invisible==false)

			{
				if(pos[0]<50)
		{
			if(alife)
			{
			pos[0]=50;
			}
			else
			{
				pos[0]=50;
				speed[0]*=-2+(Math.random()*3+1)-3;
				if(speed[0]>2)
				{
					speed[0]=2;
				}
				else if(speed[0]<-2)
				{
					speed[0]=-2;
				}
			}
				
		}
		
		if(pos[0]>mapborders.width-50)
		{
			pos[0]=mapborders.width-50;
			if(alife)
			{
			
			}
			else
			{
				
				speed[0]*=-2+(Math.random()*3+1)-3;
				if(speed[0]>2)
				{
					speed[0]=2;
				}
				else if(speed[0]<-2)
				{
					speed[0]=-2;
				}
			}
				
		}
		
			
			
		if(pos[1]<50)
		{
			pos[1]=50;
		}
		
		if(pos[1]<mapborders.height-150)
		{
			flying=true;
		}
		
		
		if(alife==true)
		{
			
		if(pos[1]>=mapborders.height-150)
		{
			
			flying=false;
			pos[1]=mapborders.height-150;
			
	
		
			
		}
		
		
		
		if(floorMap ==false)
		{
			flying=true;
		}
	}
	else
	{
			if(floorMap)
			{
		if(pos[1]>mapborders.height-120)
		{
			pos[1]=mapborders.height-120;
			speed[1]*=-0.7;
		
			
			if(speed[1]>-1)
			{
				speed[1]=0;
				
			
			}
		
			Main.sound.fallGroundSound();
		}
		else if(pos[1]<mapborders.height-120)
		{
			speed[1]+=0.2;
		}
	
		if(pos[1]==mapborders.height-120&&speed[1]==0)
		{
			bewegung=false;
		}
		else
		{
			bewegung=true;
		}
			}
			else
			{
					speed[1]+=0.2;
					if(pos[1]<mapborders.height)
					{
						bewegung=true;
						
					}
			

			}
			
		speed[0]/=1.01;
	}
			}
	
		
			if(!invisible)
			{
				if(this.specialfightertime>0)
				{
					if(atts[0]==FighterData.GOKU)
					if(left)
					{
						
						g.drawImage(GameImages.specialfighter[0][pid],(int)((pos[0]-dim.width/2+dim.width)),(int)((pos[1]-dim.height/2)),(int)(-dim.width),(int)(dim.height),null);	    			 
					}
					else
					{
						g.drawImage(GameImages.specialfighter[0][pid],(int)((pos[0]-dim.width/2)),(int)((pos[1]-dim.height/2)),(int)(dim.width),(int)(dim.height),null);	    			 
					}
				}
				else
				{
		if(left)
		{
			
			g.drawImage(GameImages.fighter[atts[0]][pid],(int)((pos[0]-dim.width/2+dim.width)),(int)((pos[1]-dim.height/2)),(int)(-dim.width),(int)(dim.height),null);	    			 
		}
		else
		{
			g.drawImage(GameImages.fighter[atts[0]][pid],(int)((pos[0]-dim.width/2)),(int)((pos[1]-dim.height/2)),(int)(dim.width),(int)(dim.height),null);	    			 
		}
				}
			}
		
		if(specialplus>1)
		{
			//Aura
			if(auraz>0)
			{
				
					g.drawImage(GameImages.aura[aura],(int)((pos[0]-dim.width/2)),(int)((pos[1]-dim.height/2)),(int)(dim.width),(int)(dim.height),null);	    			 
					auraz--;
				
			}
			else
			{
			if((int)(Math.random()*(31-specialplus*10)+1)==1)
			{
				aura=(int)(Math.random()*10+1)-1;
				auraz=4;
			}
			}
		}
		
	//	g.setColor(new Color(0,0,0));
	 //  g.fillRect((int)(pos[0]*xf),(int)(pos[1]*yf),5,5);
	       pos[0]+=speed[0];
           pos[1]+=speed[1];
        
        if(pumpen>0&&alife)
		{
			pumpen--;
			if(transformpumpen){
				animation.newAnimation(5, 12, 13);
				
				Main.sound.playLoadingSound(atts[0]);
					if(pumpen==150)
				{
					Battle.newExplosion(new Explosion(pos[0],pos[1],9));
					
				}
			}
			if(pumpen==0)
			{
				if(this.transformpumpen)
				{
					transformpumpen=false;
					evolve();
				}
				else
				{
				//Special Verstärken
				specialplus+=0.2;
				gepumpt++;
				}
			}
				
		}
        else
        {
        	if(stuntime==0)
        	{
        		//regenerate
        		
        		if(usespecial==false)
        		{
        			
        		
        		if(alife){
        			
        			if(isCyborg)
        			{
        				if(atts[3]<FighterData.getMaxKi())
        				{
        				atts[3]+=5;
        				}
        				else
        				{
        					atts[3]=FighterData.getMaxKi();
        				}
        			}
        			
        			if(atts[1]<atts[2])
        			{
        				heal+=this.data.getRegeneration(atts[0]);
        				if(heal>=1)
        				{
        			atts[1]++;
        			  heal=0;
        				}
        			}
        		}
        		}
        	}
        }
		}
		else
		{
			paintFlug(g);
		}
        	
	}
	


	public boolean isLeft()
	{
		return left;
	}
	
	public boolean enoughKIfor(int nr)
	{
		if(atts[3]>=kiwastage[nr])
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	
	public void usesSpecial()
	{
		if(alife==true)
		{
		speed[0]=0;
		speed[1]=0;
		controllable=false;
		animation.killAnimation();
		usespecial=true;
		
		animation.newAnimation(10, specialbild, specialbild);
		}
		else
		{
			die();
		}
	}
	
	public void setImmortal()
	{
		immortal=true;
	}
	
	public void maximazeLife()
	{
		atts[2]=atts[2]*5;
		atts[1]=atts[2];
	}
	
	public int getSpecialPlus(){
	
		return gepumpt;
	}
	
	public void punchHit()
	{
		if(isCyborg)
		{
			if(atts[3]<FighterData.getMaxKi())
			{
			atts[3]+=30;
			}
			else
			{
				atts[3]=FighterData.getMaxKi();
			}
		}
	}
	
	public void setMapFloor(boolean floor)
	{
		floorMap=floor;
		
	}
	
	public void heal(int heal)
	{
		atts[1]+=heal;
		if(atts[1]>atts[2]){
			
			atts[1]=atts[2];
		}
	}
	
	public boolean isCyborg()
	{
		return isCyborg;
	}

	
	public boolean isDead()
	{
		return !alife;
	}
	
	public boolean isComputer()
	{
		if(comp!=null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void makeComputerMove(Fighter f,Attack[] a)
	{
		if(comp!=null)
		{
		int move=comp.getMove(this, f, a,spielerid);
		makeMove(move);
		}
	}
	
	public void setRichting(boolean l)
	{
		left=l;
	}
	
	public void setComputerZiel(int ziel)
	{
		computerziel=ziel;
	}
	
	public int getComputerZiel()
	{
		return computerziel;
	}
	
	
	public void setComputer(){
		
		
		comp=Main.getFighterKI();
	    
	}

	public void reborn() {
	alife=true;
	controllable=true;
	atts[1]=atts[2];
	animation.killAnimation();	
	animation.newAnimation(5, 0, 0);
	}

	public void setBugFighter() {
	
        if((int)(Math.random()*2+1)==1)
        {
        	atts[1]=150;
    		atts[2]=150;
    	
        }
        else
        {
        	die();
        }
	
	}
	
	public void stun(int time)
	{
	  if(stuntime<=0)
	  {
		stuntime=time;
		speed[0]=0;
		speed[1]=0;
		animation.killAnimation();	
		animation.newAnimation(stuntime, 1, 1);
		 
		
		 Explosion exp=new Explosion(pos[0],pos[1]-30,10);
  	   Battle.newExplosion(exp);
	  }
	}

	public void setLeben(int i) {
		// TODO Auto-generated method stub
		atts[1]=i;
		atts[2]=i;
	}

	public void cantTransform() {
		// TODO Auto-generated method stub
		cantransform=false;
	}

	public void setVollesKi() {
		// TODO Auto-generated method stub
		atts[3]=FighterData.getMaxKi();
	}

	public void einfliegen()
	{
	einfliegen=true;
	invisible=false;
	if(left)
	{
		winkel=0;
	}
	else
	{
		winkel=180;
	}
		
	}
	
	private void paintFlug(Graphics g) {
		// TODO Auto-generated method stub
		int x=(int)((pos[0]-dim.width/2));
		int y=(int)((pos[1]-dim.height/2));
		int r=y+200;
		if(left)
		{
			winkel++;
			if(winkel==90)
			{
				einfliegen=false;
			}
		}
		else
		{
			winkel--;
			if(winkel==90)
			{
				einfliegen=false;
			}
		}
		
		
		x+=(int)(Math.cos(Math.toRadians(winkel))*r);
		y=-200+(int)(Math.sin(Math.toRadians(winkel))*r);
		
		if(left)
		{
			
			g.drawImage(GameImages.fighter[atts[0]][2],x+dim.width,y,(int)(-dim.width),(int)(dim.height),null);	    			 
		}
		else
		{
			g.drawImage(GameImages.fighter[atts[0]][2],x,y,(int)(dim.width),(int)(dim.height),null);	    			 
		}
		bewegung=true;
		
	}
	
	public boolean inBewegung()
	{
		return bewegung;
	}
	
	public boolean fliegtEin()
	{
		return einfliegen;
	}
	
	public boolean inDerLuft()
	{
		return flying;
	}
	
	public void unsichtbar(){
		invisible=true;
	}

	public void pumpAnimation() {
		// TODO Auto-generated method stub
		Battle.newExplosion(new Explosion(pos[0],pos[1],9));
		Main.sound.loadSpecialSound();
		Main.sound.playHitSound(atts[0]);
	}

	public int getShield() {
		// TODO Auto-generated method stub
		return (int)shielddamage;
	}
	
	public boolean amBoden()
	{
		if(amboden>100)
		{
		return true;
		}
		else
		{
			return false;
		}
	}



}
