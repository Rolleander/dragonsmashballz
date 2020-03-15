package Battle;


import java.awt.Graphics;
import java.awt.Rectangle;

import DBZ.Main;
import Images.GameImages;


public class Smash extends Attack{
/*
 *  Normal Attack
 * 
 */
	public Smash(int x, int y, Damage d, int design)
	{
		damage=d;
		pos[0]=x;
		pos[1]=y;
		
		srcpos[0]=(int)pos[0];
		srcpos[1]=(int)pos[1];
		art=design;
		
		
		attacktyp=0;
		dim.setSize(70,70);
	}	
	
	public Damage crash(int abs,int x, int y, int r2,int blood)
	{
		if(inRadius((int)pos[0],x,(int)pos[1],y,dim.width/2+r2)&&onehit==false&&Main.team.isEnemy(abs, absender))
		{
			onehit=true;//only 1 hit chance
			
			Main.sound.playDamageSound(strongsmash);
			blooddesign=blood;
		    return damage;
		}
		else
		{
			return null;
		}
	}
	
	public void paint(Graphics g)
	{		
		   if(onehit&&art<2)
		   {
			   if(Main.PAINT)
				{
				g.drawImage(GameImages.effects[time/(smashtime/4)+art],(int)((pos[0]-dim.width/2)),(int)((pos[1]-dim.height/2)),(int)(dim.width),(int)(dim.height),null);
				}
		   }
		 
				time++;
			if(time>=smashtime)
			{
				
			kill=true;
			}	
	}

	@Override
	public Rectangle getSize() {
		Rectangle rec=new Rectangle((int)((pos[0]-dim.width/2)),(int)((pos[1]-dim.height/2)),(int)(dim.width),(int)(dim.height));
		return rec;
	}

	@Override
	public void moveDruckDuell(float forward) {
		// TODO Auto-generated method stub
		
	}

	
	
}

