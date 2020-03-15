package Stages;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import DBZ.Main;
import Images.GameImages;


public class StageField extends Stage{


	
	public StageField()
	{
		clouds=new CloudStage(10);
	}
	
	public void paint(Graphics g)
	{
		g.setColor(new Color(150,150,250));
		g.fillRect( 0,0,(int)(1000),(int)(600));
		
		
		
		g.drawImage(GameImages.maps[14], 0,0,(int)(1000),(int)(600),null);
		clouds.paintBackgroundClouds(g);
	}
	

	public String getName() {
		
		return "Field";
	}
	
public int getID() {
		
		return 14;
	}
}
