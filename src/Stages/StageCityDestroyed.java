package Stages;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import DBZ.Main;
import Images.GameImages;


public class StageCityDestroyed extends Stage{

	public StageCityDestroyed()
	{
		
	}
	
	public void paint(Graphics g)
	{	
		g.drawImage(GameImages.maps[1], 0,0,(int)(1000),(int)(600),null);		
	}


	public String getName() {
		
		return "Destroyed City";
	}

	
	public int getID() {
		
		return 3;
	}
	
}
