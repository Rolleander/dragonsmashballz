package Stages;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import DBZ.Main;
import Images.GameImages;


public class StageTimeChamber extends Stage{

	public StageTimeChamber()
	{
		
	}
	
	public void paint(Graphics g)
	{	
		g.drawImage(GameImages.maps[6], 0,0,null);		
	}


	public String getName() {
		
		return "Time Chamber";
	}

	
	public int getID() {
		
		return 5;
	}
	
}
