package Stages;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import DBZ.Main;
import Images.GameImages;


public class StageKaioworld extends Stage{


	
	public StageKaioworld()
	{
		clouds=new CloudStage(10);
		
	}
	
	public void paint(Graphics g)
	{
		
		g.setColor(new Color(150,150,250));
		g.fillRect( 0,0,dimension.width,dimension.height);
		
		g.drawImage(GameImages.maps[17], 0,0,null);
		clouds.paintBackgroundClouds(g);
	}
	

	public String getName() {
		
		return "Kaioshin World";
	}
	
public int getID() {
		
		return 17;
	}
}
