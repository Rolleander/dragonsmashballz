package Stages;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

import DBZ.Main;
import Images.GameImages;


public class StageSpace extends Stage{


	
	private double x,y,sx,sy;
	int w;
	
	public StageSpace()
	{
	
		x=-150;
	}
	
	public void paint(Graphics g)
	{	
	
		/*for(int i=0; i<60; i++)
		{
			g.setColor(new Color(i/2,i/2,i/2));
			g.fillRect(0,i*10,1000,10);		
		}*/
		g.drawImage(GameImages.stars,0,0,null);
		Graphics2D g2d=(Graphics2D)g;	  	  
	    AffineTransform affineTransform = new AffineTransform(); 	
	    affineTransform.setToTranslation(x,y);
	        affineTransform.rotate(Math.toRadians(w),25,25); 	   	  
	        w++;
		    g2d.drawImage(GameImages.pod,affineTransform, null);
      x+=sx;
      y+=sy;
	   if(x<-100||x>1050||y<-100||y>650)
	   {
		   if((int)(Math.random()*20+1)==1)
		   {
			   if((int)(Math.random()*2+1)==1)
			   {
				   x=(int)(Math.random()*1000+1);
				   y=(int)(Math.random()*2+1)*700-50;
				
				   
			   }
			   else
			   {
				   y=(int)(Math.random()*600+1);
				   x=(int)(Math.random()*2+1)*1100-50;
			   }
			   sx=Math.cos((int)(Math.random()*360+1));
			   sy=Math.cos((int)(Math.random()*360+1));
		   }
	   }
	   
		
		g.drawImage(GameImages.maps[4], 0,0,null);		
	}
	

	public String getName() {
		
		return "Space";
	}
	
public int getID() {
		
		return 6;
	}
}
