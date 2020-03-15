package Battle;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import DBZ.Form;
import DBZ.Main;


public abstract class Attack extends Form{

	public final static int BODYCHANGE=1;
	
	public final static int TYP_SMASH=0,TYP_KIATTACK=1,TYP_BEAM=2,TYP_AURA=3;
	public static float druckspeed=30;
	protected Dimension dim=new Dimension();
	protected Damage damage;
	protected int absender;
	protected boolean strongsmash=false;
	protected boolean kill=false;
	protected  int time=0,maxtime=39;
	protected  boolean onehit=false;
	protected  int art,x2;
	protected  int wait=0;
	protected  int blooddesign=0; 
	protected Explosion explosion;
	protected int power=0,startpower;
	protected int attacktyp;
	protected boolean special=false;
	protected  int rotate=0;
	protected int[] srcpos=new int[2];
	protected boolean absorbdamage=false;
	protected boolean truespecial=false,iststein=false;
	protected int smashtime=39;
	protected boolean bomb=false;
	protected int specialfunktion=0;
	protected int stuntime;
	protected int multihit=1;
    protected float bwinkel,bwinkelspeed;
    protected float speedplus=1.25f;
    protected boolean winkelbewegung=false;
	protected boolean stopmovement=false,charge=false;
	protected boolean candruck=true;
	protected float chargesize=0;
	
	protected int bradius=0;
	
	
	public void setStuntime(int t)
	{
		stuntime=t;
	}
	
	public void setStronSmash()
	{
		strongsmash=true;
	}
	
	public boolean canDruckDuell()
	{
		return candruck;
	}
	
	public void cantDruckDuell()
	{
		candruck=false;
	}
	
	public boolean isChargin()
	{
		return charge;
	}
	
	public void setCharge()
	{
		charge=true;
	}
	
	public void setStein()
	{
		iststein=true;
	}
	
	public void setMaxTime(int t)
	{
		maxtime=t;
	}
	
	public void setMulitHit(int h)
	{
		multihit=h;
	}
	
	public int getStunTime()
	{
		return stuntime;
	}
	
	public void setSpecialFunktion(int f)
	{
		specialfunktion=f;
	}
	
	public void killTheAttack()
	{
		kill=true;
		active=false;
	}
	
	public int getSpecialFunktion()
	{
		return specialfunktion;
	}
	
	public void setDimension(Dimension d)
	{
		dim=d;
	}
	
	public void setSmashTime(int t)
	{
		smashtime=t;
	}
	
	public int[] getSrcPos()
	{
		return srcpos;
	}
	
	public void setTrueSpecial()
	{
		truespecial=true;
	}
	
	public boolean isUltimateAttack()
	{
		return truespecial;
	}
	
	public void setSize(int breite, int hoehe)
	{
		dim.setSize(breite, hoehe);
	}
	
	public void setAbsorbDamage()
	{
		absorbdamage=true;
	}
	
	public boolean isAbsorbDamage()
	{
		return absorbdamage;
	}
	
	public void rotate(int r)
	{
		rotate=r;
	}
	
	public void setWait(int w)
	{
		wait=w;
	}
	
	public int getPower()
	{
		return power;
	}
	
	public void setDamage(Damage d)
	{
		damage=d;
	}
	
	public boolean killAttack()
	{
		return kill;
	}
	
	public void paint(Graphics g)
	{
		
	}
	
	public void setAbsender(int a)
	{
		absender=a;
	}
	
	public abstract Damage crash(int abs, int x, int y, int r2,int blood);
	
	protected boolean inRadius(int x1, int x2, int y1, int y2, int r)//Punkt im Radius des anderen Punkts?
	{
		boolean b=false;	
		int abstand=(int) Point.distance(x1,y1,x2,y2);
		if(abstand<=r)
		{
			b=true; 
		}
		return b;
	}
	
	protected double[] getMovement(double x1, double y1, double x2, double y2)
	{				
		double w=Math.atan2((y2-y1),(x2-x1));	
		double speed[]=new double[2];
		speed[0]=Math.cos(w);
		speed[1]=Math.sin(w);		
		return speed;
	}
	
	public void setExplosion(Explosion ex)
	{
		explosion=ex;
	}

	
	public int getAbsender()
	{
		return absender;
	}
	
	public void setSpecial()
	{
		special=true;
	}
	
	public boolean isSpecial()
	{
		return special;
	}
	
	public void setPower(int p)
	{
		power=p;
	}
	
	public boolean isBomb()
	{
		return bomb;
	}
	/*
	public void duell(Attack a)
	{
	
		if(Main.team.isEnemy(a.getAbsender(), absender))
		{
		Rectangle rec1=getSize();
		Rectangle rec2=a.getSize();
		if(gegenRichtung(a.getSpeed()[0],this.getSpeed()[0]))
		{
	   if(a.isBomb()==false&&bomb==false)
	   {
		if(rec1!=null&&rec2!=null)
		{
			
			
			if(rec1.intersects(rec2)||rec2.intersects(rec1)||rec1.contains(rec2)||rec2.contains(rec1))
			{
				//hit
				
			
					switch(attacktyp)
					{
					case 1: //ki attack
						if(power>1&&a.getPower()>1)							
						{
							
							if(a.getAttackTyp()!=3)
							{
						//	speed[0]=(speed[0]+a.getSpeed()[0]*-1)/2;
						//	speed[1]=(speed[1]+a.getSpeed()[1]*-1)/2;
							speed[0]=a.getSpeed()[0]*-1;
							speed[1]=a.getSpeed()[1]*-1;
							}
							else
							{
								power-=startpower/200;
							}
						//	pos[0]-=speed[0]*((Math.random()*1+1));
						//	pos[1]-=speed[1]*((Math.random()*1+1));
							pos[0]-=speed[0]+((Math.random()*5+1)-3);
							if(speed[1]!=0)
							{
							pos[1]-=speed[1]+((Math.random()*5+1)-3);
							}
							
							if((int)(Math.random()*5+1)==1&&a.getAttackTyp()!=3)
							{
							Explosion ex=new Explosion((pos[0]+a.getPos()[0])/2,(pos[1]+a.getPos()[1])/2,7);
						      Battle.newExplosion(ex);
						      Main.sound.playKiDuell();
							}
							if(power>1)
							{
							power-=startpower/200;
							}						
							else
							{
								Explosion exp=new Explosion(pos[0],pos[1],3);
								Battle.newExplosion(exp);
								kill=true;
								   Main.sound.stopKiDuell();
							}	
					
						}
					
						if(power<1)
						{							
							Explosion exp=new Explosion(pos[0],pos[1],1);
							Battle.newExplosion(exp);
							kill=true;
						}
						break;
					case 2: //ki beam
						if(a.getAttackTyp()!=3)
						{
					//	speed[0]=(speed[0]+a.getSpeed()[0]*-1)/2;
					//	speed[1]=(speed[1]+a.getSpeed()[1]*-1)/2;
						
						speed[0]=a.getSpeed()[0]*-1;
						speed[1]=a.getSpeed()[1]*-1;
						}
						else
						{
							power-=startpower/100;					
						}
						
						if((int)(Math.random()*5+1)==1)
						{
						Explosion ex=new Explosion((pos[0]+x2+a.getPos()[0])/2,(pos[1]+a.getPos()[1])/2,7);
					      Battle.newExplosion(ex);
					    Main.sound.playKiDuell();
						}
						
						x2-=speed[0]+((Math.random()*5+1)-3);
                       
						if(power>1)
						{
							power-=startpower/100;
						}
						else
						{
							kill=true;
							   Main.sound.stopKiDuell();
						}			
						break;
					case 3://aura
						  
						if((int)(Math.random()*5+1)==1)
						{
						Explosion ex=new Explosion((pos[0]+a.getPos()[0]+(int)(Math.random()*50+1)-25)/2,(pos[1]+a.getPos()[1]+(int)(Math.random()*50+1)-25)/2,7);
					      Battle.newExplosion(ex);
					      Main.sound.playKiDuell();
						}
							break;
					}
				
			}
		}
		}
		}
	   }
	}*/
	
	

	public abstract Rectangle getSize();
	

	
	public int getAttackTyp()
	{
		return attacktyp;
	}

	public void stopMovement(boolean b) {
		// TODO Auto-generated method stub
		stopmovement=b;
	}

	
	
	
	public abstract void moveDruckDuell(float speed);

	public void setSinusWinkel(float winkel, int r) {
		// TODO Auto-generated method stub
		winkelbewegung=true;
		bwinkelspeed=winkel;
		
		bradius=r;
	}
	
	
	
	
}
