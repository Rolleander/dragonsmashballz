package Stages;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import DBZ.Main;
import Images.GameImages;


public class StageLookout extends Stage{

	
	
	public StageLookout()
	{
		clouds=new CloudStage(10);
		clouds.setForeground(3);
		floor=false;
	}
	
	private int sy=0;
	private boolean up=true;
	
	public void paint(Graphics g)
	{
		g.setColor(new Color(150,150,250));
		g.fillRect( 0,0,(int)(1000),(int)(600));
	   
		clouds.paintBackgroundClouds(g);
		
		if(up)
		{
			sy--;
			if(sy<-50)
			{
				up=false;
			}
		}		
		else
		{
			sy++;
			if(sy>50)
			{
				up=true;
			}
				
		}
		g.drawImage(GameImages.maps[5], 244,108+sy/10,null);
		
		clouds.paintForegroundClouds(g);
		
	}
	

	public String getName() {
		
		return "Lookout";
	}
	
public int getID() {
		
		return 4;
	}
}
