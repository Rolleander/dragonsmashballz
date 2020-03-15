package Stages;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import DBZ.Main;
import Images.GameImages;


public class StageForrest extends Stage{


	
	public StageForrest()
	{
		clouds=new CloudStage(10);
		
	}
	
	public void paint(Graphics g)
	{
		
		g.drawImage(GameImages.maps[19], 0,0,null);
		clouds.paintBackgroundClouds(g);
	}
	

	public String getName() {
		
		return "Forest";
	}
	

	public int getID() {
		
		return 19;
	}
}
