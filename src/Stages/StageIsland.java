package Stages;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import DBZ.Main;
import Images.GameImages;


public class StageIsland extends Stage{


	
	public StageIsland()
	{
		clouds=new CloudStage(10);
		
	}
	
	public void paint(Graphics g)
	{
		
		g.setColor(new Color(123,198,239));
		g.fillRect( 0,0,dimension.width,dimension.height);
		
		g.drawImage(GameImages.maps[16], 0,0,null);
		clouds.paintBackgroundClouds(g);
	}
	

	public String getName() {
		
		return "Island";
	}
	
public int getID() {
		
		return 16;
	}
}
