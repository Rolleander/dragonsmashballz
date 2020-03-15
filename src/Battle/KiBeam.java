package Battle;


import java.awt.Graphics;
import java.awt.Rectangle;

import DBZ.Main;
import Images.GameImages;


public class KiBeam extends Attack {
	
	
	public final static int YELLOW_BEAM=0,BLUE_BEAM=1,CIRCLE_BEAM=2,ROSA_BEAM=3,CYAN_BEAM=4,RED_BEAM=5,DRAGON_FIST=6,BLACK_BEAM=7;
	public final static int GREEN_BEAM=8;
	public KiBeam(int x, int y, Damage d,float s0, int a, int b)
	{
		damage=d;
		pos[0]=x;
		pos[1]=y;
		srcpos[0]=(int)pos[0];
		srcpos[1]=(int)pos[1];
		
		art=a;
		speed[0]=s0*speedplus;
		attacktyp=2;
		dim.setSize(1,b);
		Main.sound.fireKiBeamSound();
		
		power=((d.damage*10)*b)/10;
		
		startpower=power;
	}	
	
	
	
	public Damage crash(int abs,int x, int y, int r2,int blood)
	{
		if(Main.team.isEnemy(abs, absender)&&wait<=0)
		{
			r2=r2+dim.height/2;
			if(speed[0]>0)
			{
			if(x+r2>pos[0]&&x-r2<pos[0]+x2&&y-r2<pos[1]&&y+r2>pos[1])
			{
			onehit=true;//   attack hit		
			Main.sound.hitKiBeamSound();
			
			blooddesign=blood;
			
               if(x2%3==0)
               {
            	   
            	   Battle.newExplosion(new Explosion((int)x+(int)(Math.random()*40+1)-20,(int)y+(int)(Math.random()*dim.height+1)-dim.height/2,1));
		    return damage;
               }
               else
               {
            	   return null;
               }
			}
			else
			{
				return null;
			}
			}
			else
			{
				if(x-r2<pos[0]&&x+r2>pos[0]+x2&&y-r2<pos[1]&&y+r2>pos[1])
				{
				onehit=true;//   attack hit		
				  if(x2%3==0)
	               {
			      	   Battle.newExplosion(new Explosion((int)x+(int)(Math.random()*40+1)-20,(int)y+(int)(Math.random()*dim.height+1)-dim.height/2,1));
			      		
			    return damage;
	               }
	               else
	               {
	            	   return null;
	               }
				}
				else
				{
					return null;
				}
				
			}
		}
		else
		{
			return null;
		}
	}
	
	private void move(boolean full)
	{
		if(full)
		{
	     x2+=speed[0];
		}
		else
		{
			 x2+=speed[0]/2;
		}
	     dim.width=(int) (x2);
	 	if(dim.width<-1500||dim.width>1500)
	 	{
	 		kill=true;
	 	}
	}
	
	public void paint(Graphics g)
	{		
		int apl=60+art;
		if(art==RED_BEAM)
		{
			apl=70;
		}
		if(art==DRAGON_FIST)
		{
			apl=62;
		}
		if(art==BLACK_BEAM)
		{
			apl=78;
		}
		if(art==GREEN_BEAM)
		{
			apl=81;
		}
		if(wait<=0)
		{
			if(Main.PAINT)
			{
		g.drawImage(GameImages.effects[apl],(int)(pos[0]),(int)((pos[1]-dim.height/2)),(int)(dim.width),(int)(dim.height),null);
			}
		int beamart=0,beamart2=0;
		switch(art)
		{
		case 0: beamart=50; break;
		case 1: beamart=52; break;
		case 2: beamart=50; break;
		case 3: beamart=55; break;
		case 4: beamart=57; break;
		case 5: beamart=68; break;
		case 6: beamart=50; break;
		case 7: beamart=57; break;
		case 8: beamart=80; break;
		}
		beamart2=beamart+1;
		if(art==DRAGON_FIST){
			
			beamart2=71;
		}
        if(art==GREEN_BEAM){
			
			beamart2=82;
		}
        if(Main.PAINT)
		{
		if(speed[0]>0)
		{
		g.drawImage(GameImages.effects[beamart],(int)((pos[0]-30)),(int)((pos[1]-dim.height/2)),(int)(70),(int)(dim.height),null);
		g.drawImage(GameImages.effects[beamart2],(int)((pos[0]+x2-30)),(int)((pos[1]-dim.height/2)),(int)(70),(int)(dim.height),null);
		}
		else
		{
			g.drawImage(GameImages.effects[beamart],(int)((pos[0]+30)),(int)((pos[1]-dim.height/2)),(int)(-70),(int)(dim.height),null);
			g.drawImage(GameImages.effects[beamart2],(int)((pos[0]+x2+30)),(int)((pos[1]-dim.height/2)),(int)(-70),(int)(dim.height),null);		
		}
		}
		
		if(onehit==true)
		{
			if(specialfunktion==Attack.BODYCHANGE)
			{
				kill=true;
				time=40;
			}
			if(stopmovement==false)
			{
			move(false);
			}
			if(Main.PAINT)
			{
			for(int i=0; i<5; i++)
			{
			g.drawImage(GameImages.effects[time/10],(int)((pos[0]+x2-30+(int)(Math.random()*50+1)-25)),(int)((pos[1]-30+(int)(Math.random()*50+1)-25)),(int)(60),(int)(60),null);			
			}
			}
			time++;
			if(time>=maxtime)
			{				
			kill=true;
			}	
		}
		else
		{
			if(stopmovement==false)
			{
			move(true);
			}
		}
		}
		else
		{
			wait--;
			if(wait==0)
			{
				Main.sound.fireKiAttackSound();
			}
		}
	}
	
	public int[] getPos()
	{
		int[] i=new int[2];
		
	
		if(speed[0]>0)
		{
			i[0]=(int) (pos[0]+x2-30);
		}
		else			
		{
			i[0]=(int) (pos[0]+x2+30);
		}
		i[1]=(int)pos[1];
		return i;
	}



	@Override
	public Rectangle getSize() {
		// TODO Auto-generated method stub
		Rectangle rec=null;
	    if(dim.width<0)
	    {
	    	rec=new Rectangle((int)(pos[0]+dim.width),(int)((pos[1]-dim.height/2)),(int)(dim.width*-1),(int)(dim.height));
	    	
	    }
	    else
	    {
	    	rec=new Rectangle((int)(pos[0]),(int)((pos[1]-dim.height/2)),(int)(dim.width),(int)(dim.height));
	    	
	    }
		
		return rec;
	}



	@Override
	public void moveDruckDuell(float spd) {
		// TODO Auto-generated method stub
		float s=0;
		
		
		if(speed[0]>0)
		{
			s=spd;
		}
		else if(speed[0]<0)
		{
			s=-spd;
		}
	
		x2+=s;	
		 dim.width=(int) (x2);
	}




	
}

