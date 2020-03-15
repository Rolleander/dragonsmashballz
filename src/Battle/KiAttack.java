package Battle;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import DBZ.Main;
import Images.GameImages;


public class KiAttack extends Attack {
	
   private int fullsize;
	
	public KiAttack(int x, int y, Damage d, float s0, float s1, int design, int size)
	{
		damage=d;
		pos[0]=x;
		art=design;
		pos[1]=y;
		speed[0]=s0*speedplus;
		speed[1]=s1*speedplus;
		
		srcpos[0]=(int)pos[0];
		srcpos[1]=(int)pos[1];
		
		
		
		if(speed[0]==0&&speed[1]==0)
		{
			
			bomb=true;
		}
		
		attacktyp=1;
		dim.setSize(size,size);
		fullsize=size;
		if(charge==false&&wait<=0)
		{
		Main.sound.fireKiAttackSound();
		}
		if(charge&&wait<=0)
		{
			Main.sound.playSound(13, true);
		}
		
		if(size>50)
		{
		
			power=((d.damage/20)*size)/10;
			power=size*d.damage;
			
		}
		else
		{
			power=0;
		}
		
		
		
		startpower=power;
	}	
	
	
 
	
    private int lastx,lasty;
	public Damage crash(int abs,int x, int y, int r2,int blood)
	{
		
		if(inRadius((int)pos[0],x,(int)pos[1],y,dim.width/2+r2)&&onehit==false&&wait<=0&&Main.team.isEnemy(abs, absender))
		{
			if(bomb==false)
			{
				if(multihit>1)
				{
				multihit--;
				}
				else
				{
			onehit=true;//   attack hit
				}
			}
			blooddesign=blood;
			lastx=x;
			lasty=y;
			
		
			if(bomb==false)
			{
			if(dim.width>50)
			{
				if(explosion==null)
				{
					
				Battle.newExplosion(new Explosion(pos[0],pos[1],2));
				}
				else
				{
					explosion.setPos((int)pos[0], (int)pos[1]);
					Battle.newExplosion(explosion);
				}
				
				
			}
			else
			{
				if(explosion==null)
				{
				}
				else
				{
					explosion.setPos((int)pos[0], (int)pos[1]);
					Battle.newExplosion(explosion);
				}
			}
			}
			Main.sound.hitKiAttackSound();
			
			if(charge&&chargesize<fullsize)
			{
				damage.damage=(int)((double)damage.damage*((double)chargesize/(double)fullsize));
			}
			
			
		    return damage;
		}
		else
		{
			return null;
		}
	}
	
	
	private void move()
	{
		pos[0]+=speed[0];
		pos[1]+=speed[1];
		
		if(this.winkelbewegung)
		{
			pos[1]=(float) (Math.sin(Math.toRadians(this.bwinkel))*this.bradius)+this.srcpos[1];
			this.bwinkel+=this.bwinkelspeed;
		}
		
		
		if(pos[0]<-500||pos[0]>1500||pos[1]<-200||pos[1]>750)
		{
			kill=true;
		}
		
		
	}

	private int dreh;
	private float fally=0;
	public void paint(Graphics g)
	{		
		
		if(wait<=0)
		{
			
			
			if(charge&&chargesize<fullsize)
			{
				chargesize+=fullsize*0.08;
				dim.height=(int) chargesize;
				dim.width=(int) chargesize;
				if(chargesize>=fullsize)
				{
					charge=false;
					Main.sound.fireKiAttackSound();
				}
			}
			
		if(onehit==false&&bomb==false)
		{
			
			if(iststein){
				
				pos[1]+=fally;
				fally+=0.1;
			}
			
			if(Main.PAINT)
			{
			Graphics2D g2d=(Graphics2D)g;	  	  
		    AffineTransform affineTransform = new AffineTransform(); 
		    
			dreh+=rotate;
			if(rotate>0)
				{
				 affineTransform.setToTranslation((int)((pos[0]-dim.width/2)),(int)((pos[1]-dim.height/2)));
					
				 affineTransform.rotate(Math.toRadians(dreh),dim.width/2,dim.height/2); 	
				    affineTransform.scale((double)dim.width/(double)70,(double)dim.height/ (double)70);
					
			      g2d.drawImage(GameImages.effects[art],affineTransform,null);
				}
			else
			{
			if(speed[0]<0)
			{
			g.drawImage(GameImages.effects[art],(int)((pos[0]-dim.width/2+dim.width)),(int)((pos[1]-dim.height/2)),(int)(-dim.width),(int)(dim.height),null);
			
			}
			else
			{
			g.drawImage(GameImages.effects[art],(int)((pos[0]-dim.width/2)),(int)((pos[1]-dim.height/2)),(int)(dim.width),(int)(dim.height),null);
			
			}	
			}
			
			}
			//Schwanz zeichnen
			/*
			for(int i=0; i<5; i++)
			{
				int sx=(int) (pos[0]-(speed[0]*i*5));
				int sy=(int) (pos[1]-(speed[1]*i*5));
				
				int sb=(int)(dim.width*((6-(double)i))/8);
				
				g.drawImage(Main.effects[11],sx-sb/2,sy-sb/2,sb,sb,null);
			}*/
			
			if(stopmovement==false&&charge==false)
			{
			move();
			
			
			
			}
		}
		else
		{
			
		
	
			time++;
			if(time>=maxtime)
			{
			
			kill=true;
			}	
		}
		}
		else
		{
			wait--;
			if(wait==0)
			{
				if(charge==false)
				{
				Main.sound.fireKiAttackSound();
				}
				else
				{
					Main.sound.playSound(13, true);
				}
			}
			
		
		}
		
	}

	@Override
	public Rectangle getSize() {
		Rectangle rec=new Rectangle((int)((pos[0]-dim.width/2)),(int)((pos[1]-dim.height/2)),(int)(dim.width),(int)(dim.height));
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
		
		pos[0]+=s;	
	}

 

	
}

