package Stages;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import DBZ.Main;
import Images.GameImages;


public class StagePlain extends Stage{


	
	public StagePlain()
	{
		clouds=new CloudStage(10);
	}
	
	public void paint(Graphics g)
	{
		g.setColor(new Color(150,150,250));
		g.fillRect( 0,0,dimension.width,dimension.height);
		
		clouds.paintBackgroundClouds(g);
		
		g.drawImage(GameImages.maps[0], 0,0,null);
	
		
	}
	
	
	

	public String getName() {
		
		return "Plain";
	}
	
public int getID() {
		
		return 0;
	}
}
