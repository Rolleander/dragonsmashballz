package Battle;


import java.awt.Dimension;
import java.awt.Graphics;

import DBZ.Form;
import DBZ.Main;
import Images.GameImages;


public class Explosion extends Form{

	
	private int bild,steps,time,steptime;
   Dimension dim=new Dimension();
   private boolean shockwave=false;
   private boolean pumpen=false;
   
	public Explosion(float x, float y, int art)
	{
		pos[0]=x;
		pos[1]=y;
		vorbereiten(art);
		time=0;
		active=true;
	}
	
	
	
	private void vorbereiten (int art)
	{
		
		switch(art){
		
		case 0: //exp 1
			bild=0; steps=5; steptime=7;
			dim.setSize(35, 35);
			break;
		case 1: //hit1
			bild=6; steps=4; steptime=9;
			dim.setSize(45, 45);
			break;
		case 2: //exp2
			bild=10; steps=6; steptime=6;
			dim.setSize(70, 70);
			break;
		case 3: //exp2
			bild=16; steps=4; steptime=7;
			dim.setSize(130, 130);
			break;
		case 4: 
			bild=20; steps=4; steptime=7;
			dim.setSize(150, 150);
			break;
		case 5: //exp2
			bild=24; steps=6; steptime=8;
			dim.setSize(160, 160);
			break;
		case 6: 
			bild=30; steps=7; steptime=7;
			dim.setSize(70, 70);
			break;
		case 7:
			bild=37; steps=3; steptime=5;
			int r=(int)(Math.random()*50+1);
			dim.setSize(110+r, 110+r);
			break;
			
		case 10:
			bild=40; steps=4; steptime=5;
			dim.setSize(100,100);
			break;
			
		case 11:
			bild=45; steps=5; steptime=7;

			break;
			
		}
		if(art==8)
		{
			shockwave=true;
			bild=45;
			dim.setSize(5,5);
			steptime=20;
		}
		if(art==9)
		{
			pumpen=true;
			dim.setSize(150,150);
		}
	}
	
	public void setSize(int b, int h)
	{
		dim.setSize(b,h);
	}
	
	private int size=0;
	private int dauer=0;
	public void paint(Graphics g)
	{
		time++;
		if(pumpen)
		{
			size+=10;
			if(size>=200)
			{
				size=0;
				dauer++;
				if(dauer>5)
				{
					active=false;
				}
			}
			if(Main.PAINT)
			{
			g.drawImage(GameImages.effects[54],(int)pos[0]-size/2,(int)pos[1]-size/2,size,size,null);
			g.drawImage(GameImages.aura[size/20],(int)pos[0]-dim.width/2,(int)pos[1]-dim.height/2,dim.width,dim.height,null);
			}
		}
		else if(shockwave)
		{
			if(Main.PAINT)
			{
			g.drawImage(GameImages.effects[54],(int)pos[0]-dim.width/2,(int)pos[1]-dim.height/2,dim.width,dim.height,null);
			}
			dim.width+=5;
			dim.height+=5;
			if(time>steptime)
			{
				active=false;
			}
		}
		else
		{
			if(Main.PAINT)
			{
		g.drawImage(GameImages.explosions[bild+time/steptime],(int)pos[0]-dim.width/2,(int)pos[1]-dim.height/2,dim.width,dim.height,null);
			}
		if(time>=steptime*steps-1)
		{		
			active=false;
		}
		}
	}
	
	public boolean killExplosion()
	{
		return !active;
	}
}
