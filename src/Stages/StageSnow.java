package Stages;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import DBZ.Main;
import Images.GameImages;


public class StageSnow extends Stage{

    
	
	public StageSnow()
	{
		clouds=new CloudStage(10);
		clouds.setForeground(3);
	}
	
	public void paint(Graphics g)
	{
		g.setColor(new Color(26,152,216));
		g.fillRect( 0,0,(int)(1000),(int)(600));
		
	    clouds.paintBackgroundClouds(g);
		
		g.drawImage(GameImages.maps[9], 0,0,(int)(1000),(int)(600),null);
		
		clouds.paintForegroundClouds(g);
		
	
	}
	

	public String getName() {
		
		return "Muscle Tower";
	}
	
public int getID() {
		
		return 9;
	}
}
