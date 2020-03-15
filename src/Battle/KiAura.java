package Battle;


import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import DBZ.Main;
import Images.GameImages;


public class KiAura extends Attack {

	private int hit,hitx,hity;
	public KiAura(int x, int y, Damage d, int design, int size)
	{
		damage=d;
		pos[0]=x;
		art=design;
		pos[1]=y;
		time=0;
		
		srcpos[0]=(int)pos[0];
		srcpos[1]=(int)pos[1];
		
		attacktyp=3;
		dim.setSize(size,size);
		Main.sound.fireKiBeamSound();
		power=999999;
		startpower=power;
	}	
	
	public Damage crash(int abs,int x, int y, int r2,int blood)
	{
		if(inRadius((int)pos[0],x,(int)pos[1],y,dim.width/2+r2)&&Main.team.isEnemy(abs, absender))
		{
			Main.sound.hitKiBeamSound();
			if(hit==0)
			{
			hit=1;
			}
			blooddesign=blood;
		hitx=x;
			hity=y;
			return damage;
		}
		else
		{
			return null;
		}
	}
	
	private void move()
	{
	   dim.width++;
	   dim.height++;
	   time++;
	}
	
	public void paint(Graphics g)
	{		
		if(Main.PAINT)
		{
		Graphics2D g2d = (Graphics2D) g;
		  g2d.setComposite(AlphaComposite. getInstance(AlphaComposite.SRC_OVER, 0.5f));
				g2d.drawImage(GameImages.effects[art],(int)((pos[0]-dim.width/2)),(int)((pos[1]-dim.height/2)),(int)(dim.width),(int)(dim.height),null);
				  g2d.setComposite(AlphaComposite. getInstance(AlphaComposite.SRC_OVER, 1f));	
		}
			if(hit>0)
			{
				if(Main.PAINT)
				{
				for(int i=0; i<5; i++)
				{
				g.drawImage(GameImages.effects[3+(int)(Math.random()*4+1)],(int)((hitx+(int)(Math.random()*60+1)-60)),(int)((hity+(int)(Math.random()*60+1)-60)),(int)(60),(int)(60),null);
				}
				}
				hit++;
				if(hit>=39)
				{
					hit=0;
				}
			}
			if(stopmovement==false)
			{
				 move();	
			}
			if(time>=100)
			{
				
			kill=true;
			}	
	}

	@Override
	public Rectangle getSize() {
		// TODO Auto-generated method stub
		Rectangle rec=new Rectangle((int)((pos[0]-dim.width/2)),(int)((pos[1]-dim.height/2)),(int)(dim.width),(int)(dim.height));
		return rec;
	}

	@Override
	public void moveDruckDuell(float forward) {
		// TODO Auto-generated method stub
		
	}

	
	
}
