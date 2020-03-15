package Stages;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import DBZ.Main;
import Images.GameImages;


public class StageIce extends Stage{


	
	public StageIce()
	{
		clouds=new CloudStage(10);
	}
	
	public void paint(Graphics g)
	{
		
		
		g.drawImage(GameImages.maps[15], 0,0,null);
		clouds.paintBackgroundClouds(g);
	}
	

	public String getName() {
		
		return "Icefield";
	}
	
public int getID() {
		
		return 15;
	}
}
