package DBZ.World;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import DBZ.Main;
import Images.GameImages;



public class Chara {

	private float xpos,ypos;
	private int stufe;
	private float s=2;
	private int winkel=0;
	
	private int animation;
	private int[] borders=new int[2];
	private boolean fly=false;
	private boolean[] block;
	static int UPPERLEFT=0,LEFT=1,DOWNLEFT=2,UPPERMIDDLE=3,MIDDLE=4,DOWNMIDDLE=5,UPPERRIGHT=6,RIGHT=7,DOWNRIGHT=8;
	
	public Chara()
	{
		
	xpos=724*32-400;
	ypos=497*32-200;
	}
	
	
	

	
	public void setMapBorder(int[] b)
	{
		borders=b;
	}
	
	public void setStufe(int t)
	{
		stufe=t;
	}
	
	private int az=0;
	
	
	
	public void paint(Graphics g)
	{
	//paint player
		fly=true;
		if(fly)
		{
			//Schatten
			g.setColor(new Color(0,0,0,150));
			g.fillOval(500,300,20,20);
			
			
			//Strahl
			
			int x=512;
			int y=288;
			double w=Math.toRadians(winkel);
			double w2=Math.toRadians(winkel+90);
		
			for(int i=1; i<10; i=i+2)
			{			
				g.setColor(new Color(255-(int)(Math.random()*50+1),255-(int)(Math.random()*50+1),50-(int)(Math.random()*20+1)));
				int b=0;
				switch(i)
				{
				case 1: b=10; break;
				case 3: b=5;break;
				case 5: b=3;break;
				case 7: b=5;break;
				case 9: b=10;break;
				}
				b+=(int)(Math.random()*5+1)-3;
				int l=(int) (s*2*b)+(int)(Math.random()*5+1)-3;
			     int a=20-i*4+(int)(Math.random()*3+1)-2;
			     int c=20+(int)(Math.random()*10+1)-5;
				int x1=x+(int)(Math.cos(w)*a)+(int)(Math.cos(w2)*c);
				int y1=y+(int)(Math.sin(w)*a)+(int)(Math.sin(w2)*c);
			     g.drawLine(x1,y1,x1+(int)(Math.cos(w2)*l),y1+(int)(Math.sin(w2)*l));
			}
			
			
			//Energybollen
	
			Graphics2D g2d=(Graphics2D)g;	  	  
		    AffineTransform affineTransform = new AffineTransform(); 
		    affineTransform.setToTranslation(480,254); 
		  
		    affineTransform.rotate(Math.toRadians(winkel-90),35,35); 	
		 
		 
			g2d.drawImage(GameImages.effects[41],affineTransform,null);
				  
		}
		
		
		Graphics2D g2d=(Graphics2D)g;	  	  
	    AffineTransform affineTransform = new AffineTransform(); 
	    affineTransform.setToTranslation(500,272);
        affineTransform.rotate(Math.toRadians(winkel),12,16); 	   	     	   
	    g2d.drawImage(GameImages.mapchars[stufe*12+animation],affineTransform, null); 
	    
		
	}
	
	public void makeMove(int r)
	{
		
		
		int an=0;
		az++;
		
		
		if(fly)
		{
			animation=1;
			switch(r)
			{
			case 0:  winkel--;   break;//Links
			case 1: winkel++;  break;//Rechts
			case 2:   s+=0.1;  break;//Hoch
			case 3: s-=0.2; break;//Runter
			case 4: 
			if(s==0&&block[MIDDLE]==false)
			{
				fly=false;
				
				
				
				
				winkel=0;
				
				
				
			}
			
			break;
			}
			
		if(s>20){
			s=20;			
		}
		if(s<0)
		{
			s=-0;
		}
		
		
	
		}
		else
		{
	
			s=2;
			int nxpos=(int) xpos;
			int nypos=(int) ypos;
			int richt=0;
			switch(r)
			{
			case 0: nxpos-=s; an=12; richt=LEFT;  break;//Links
			case 1: nxpos+=s; an=6; richt=RIGHT; break;//Rechts
			case 2: nypos-=s; an=3; richt=UPPERMIDDLE;  break;//Hoch
			case 3: nypos+=s; an=9; richt=DOWNMIDDLE; break;//Runter
			case 4: fly=true; s=0;  
			
			
			switch(animation/3)
			{
			case 0: winkel=0; break;
			case 1: winkel=90; break;
			case 2: winkel=180; break;
			case 3: winkel=-90; break;
			}
		
			an=3; break;
			}
			
			
			boolean darf=true;
		
		
			
			
			if((nxpos/32)!=(xpos/32)||(nypos/32)!=(ypos/32))
			{
			
				if(block[richt])
				{
					darf=false;
					animation=an-2;
				}
			}
			
			
			if(darf)
			{
		    xpos=nxpos;
		    ypos=nypos;
			if(animation<an&&animation>=an-3)
			{
				if(az>15)
				{
				animation++;
				if(animation>=an)
				{
					animation=an-3;
				}
				
				az=0;
				}
			}
			else
			{
				animation=an-3;
				az=0;
			}
			}
		}
		
	
	
		
	
	}
	
	
	
	private void grenzen() {
		
		int maxx=borders[0];
		int maxy=borders[1];
		
		if(xpos<0)
		{
			xpos=maxx;
		}
		else if(xpos>maxx)
		{
			xpos=0;
		}
		if(ypos<0)
		{
			ypos=maxy;
		}
		else if(ypos>maxy)
		{
			ypos=0;
		}
	}

	public void move()
	{
		if(fly)
		{
			xpos+=Math.cos(Math.toRadians(winkel-90))*s;
			ypos+=Math.sin(Math.toRadians(winkel-90))*s;
			
			
		}
		   grenzen();
	}
	
	
	public int getWinkel()
	{
		return winkel;
	}
	
	public boolean isFlying()
	{
		return fly;
	}
	
	public int getAnimation(){
		return animation;
	}
	
	public int getStufe()
	{
		return stufe;
	}
	
	public void setPos(int x, int y)
	{
		xpos=x;
		ypos=y;
	}
	
	public int getX()
	{
		return (int)xpos;
	}
	
	public int getY()
	{
		return (int)ypos;
	}

	public void blockRoute(boolean[] move) {
	
		block=move;
	
	}
	
}
