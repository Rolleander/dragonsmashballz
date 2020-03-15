package Stages;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import DBZ.Main;
import Images.GameImages;


public class StageTournament extends Stage{

	
	
	public StageTournament()
	{
		clouds=new CloudStage(10);
	}
	
	public void paint(Graphics g)
	{
		g.setColor(new Color(150,150,250));
		g.fillRect( 0,0,(int)(1000),(int)(600));
		
		clouds.paintBackgroundClouds(g);
		
		g.drawImage(GameImages.maps[7], 0,0,(int)(1000),(int)(600),null);
		
	}
	

	public String getName() {
		
		return "Tournament";
	}
	
public int getID() {
		
		return 7;
	}
}
